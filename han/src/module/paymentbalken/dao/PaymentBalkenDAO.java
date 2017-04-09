package module.paymentbalken.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.util.DateUtil;
import module.paymentbalken.model.PaymentBalken;

public class PaymentBalkenDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getAllPayPrStatement;
	private PreparedStatement getAllRPRStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("select id, inventory_balken_id, due_date, rate, payment_status, payment_date, subtotal, ")
			.append("disc, tax, other_fee, total, status, received_code, received_date, supp_name, source, currency_abbr, delivery_note from ( ")
			.append("select pay_pr.id, pay_pr.inventory_balken_id, pay_pr.due_date, pay_pr.rate, pay_pr.payment_status, pay_pr.payment_date, pay_pr.subtotal, ")
			.append("pay_pr.disc, pay_pr.tax, pay_pr.other_fee, pay_pr.total, pay_pr.status, rpr.received_code, rpr.received_date, s.supp_name, 'PAYMENT_BALKEN' as source, c.currency_abbr, rpr.delivery_note ")
			.append("from payment_balken pay_pr ")
			.append("inner join invoice_balken ipr on ipr.id = pay_pr.inventory_balken_id ")
			.append("inner join received rpr on rpr.received_code = ipr.received_code ")
			.append("left join supplier s on s.supp_code = rpr.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and rpr.deleted_date is null and ipr.deleted_date is null and pay_pr.deleted_date is null ")
			.append("union ")
			.append("select r.id, r.id as inventory_balken_id, r.due_date as due_date, r.rate as rate, r.payment_status as payment_status, r.payment_date as payment_date, r.subtotal as subtotal, ")
			.append("r.disc as disc, r.tax as tax, r.other_fee as other_fee, r.total as total, '' as status, rpr.received_code, rpr.received_date, s.supp_name, 'INVOICE_BALKEN' as source, c.currency_abbr, rpr.delivery_note ")
			.append("from invoice_balken r ")
			.append("inner join received rpr on rpr.received_code = r.received_code ")
			.append("left join supplier s on s.supp_code = rpr.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and rpr.deleted_date is null and r.status = 'FINAL' and r.deleted_date is null and r.id not in (select inventory_balken_id from payment_balken)) result ")
			.toString();

	private String getAllPayPrQuery = new StringBuilder()
			.append("select pay_pr.id, pay_pr.inventory_balken_id, pay_pr.due_date, pay_pr.rate, pay_pr.payment_status, pay_pr.payment_date, pay_pr.subtotal, ")
			.append("pay_pr.disc, pay_pr.tax, pay_pr.other_fee, pay_pr.total, pay_pr.status, rpr.received_code, rpr.received_date, s.supp_name, 'PAYMENT_BALKEN' as source, c.currency_abbr, rpr.delivery_note ")
			.append("from payment_balken pay_pr ")
			.append("inner join invoice_balken ipr on ipr.id = pay_pr.inventory_balken_id ")
			.append("inner join received rpr on rpr.received_code = ipr.received_code ")
			.append("left join supplier s on s.supp_code = rpr.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and rpr.deleted_date is null and ipr.deleted_date is null and pay_pr.deleted_date is null ")
			.toString();

	private String getAllRPRQuery = new StringBuilder()
			.append("select r.id, r.id as inventory_balken_id, r.due_date as due_date, r.rate as rate, r.payment_status as payment_status, r.payment_date as payment_date, r.subtotal as subtotal, ")
			.append("r.disc as disc, r.tax as tax, r.other_fee as other_fee, r.total as total, '' as status, rpr.received_code, rpr.received_date, s.supp_name, 'INVOICE_BALKEN' as source, c.currency_abbr, rpr.delivery_note ")
			.append("from invoice_balken r ")
			.append("inner join received rpr on rpr.received_code = r.received_code ")
			.append("left join supplier s on s.supp_code = rpr.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and rpr.deleted_date is null and r.status = 'FINAL' and r.deleted_date is null and r.id not in (select inventory_balken_id from payment_balken) ")
			.toString();

	private String insertQuery = new StringBuilder()
			.append("insert into payment_balken (inventory_balken_id, due_date, rate, payment_status, ")
			.append("subtotal, disc, tax, other_fee, total, status, input_date, input_by, payment_date) ")
			.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?)").toString();

	private String updateQuery = new StringBuilder()
			.append("update payment_balken set inventory_balken_id=?, due_date=?, rate=?, payment_status=?, ")
			.append("subtotal=?, disc=?, tax=?, other_fee=?, total=?, status=?, ")
			.append("edited_date=?, edited_by=?, payment_date=? where id=?")
			.toString();

	private String deleteQuery = "update payment_balken set deleted_date=?, deleted_by=? where id=?";

	public PaymentBalkenDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<PaymentBalken> getAll() throws SQLException {
		List<PaymentBalken> rprs = new ArrayList<PaymentBalken>();

		String query = new StringBuilder().append(getAllQuery).toString();

		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PaymentBalken ppr = new PaymentBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setReceivedCode(rs.getString("received_code"));
				ppr.setInventoryBalkenId(rs.getInt("inventory_balken_id"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceivedDate(rs.getDate("received_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setSource(rs.getString("source"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setDeliveryNote(rs.getString("delivery_note"));
				rprs.add(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public List<PaymentBalken> getAllBySimpleSearch(String value)
			throws SQLException {
		List<PaymentBalken> rprs = new ArrayList<PaymentBalken>();
		try {

			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value)
						.append("%").toString();
				String query = new StringBuilder().append(" and")
						.append(" or lower(received_code) like lower('%s')")
						.append(" or lower(payment_status) like lower('%s'))")
						.toString();
				getAllStatement = connection.prepareStatement(String.format(
						query, keyword, keyword, keyword));
			} else {
				String query = new StringBuilder().append(getAllQuery)
						.toString();
				getAllStatement = connection.prepareStatement(query);
			}

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PaymentBalken ppr = new PaymentBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setReceivedCode(rs.getString("received_code"));
				ppr.setInventoryBalkenId(rs.getInt("inventory_balken_id"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceivedDate(rs.getDate("received_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setDeliveryNote(rs.getString("delivery_note"));
				rprs.add(ppr);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public PaymentBalken save(PaymentBalken ppr) throws SQLException {
		try {
			ResultSet generatedKeys = null;
			
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, ppr.getInventoryBalkenId());
			insertStatement.setDate(2, DateUtil.toDate(ppr.getDueDate()));
			insertStatement.setBigDecimal(3, ppr.getRate());
			insertStatement.setString(4, ppr.getPaymentStatus());
			insertStatement.setBigDecimal(5, ppr.getSubtotal());
			insertStatement.setBigDecimal(6, ppr.getDisc());
			insertStatement.setBigDecimal(7, ppr.getTax());
			insertStatement.setBigDecimal(8, ppr.getOtherFee());
			insertStatement.setBigDecimal(9, ppr.getTotal());
			insertStatement.setString(10, ppr.getStatus());
			insertStatement.setDate(11, DateUtil.getCurrentDate());
			insertStatement.setString(12, "timotius");
			insertStatement.setDate(13, DateUtil.toDate(ppr.getPaymentDate()));
			insertStatement.executeUpdate();
			
			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				ppr.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}

			return ppr;
		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(PaymentBalken rpr) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, rpr.getInventoryBalkenId());
			updateStatement.setDate(2, DateUtil.toDate(rpr.getDueDate()));
			updateStatement.setBigDecimal(3, rpr.getRate());
			updateStatement.setString(4, rpr.getPaymentStatus());
			updateStatement.setBigDecimal(5, rpr.getSubtotal());
			updateStatement.setBigDecimal(6, rpr.getDisc());
			updateStatement.setBigDecimal(7, rpr.getTax());
			updateStatement.setBigDecimal(8, rpr.getOtherFee());
			updateStatement.setBigDecimal(9, rpr.getTotal());
			updateStatement.setString(10, rpr.getStatus());
			updateStatement.setDate(11, DateUtil.getCurrentDate());
			updateStatement.setString(12, "timotius");
			updateStatement.setDate(13, DateUtil.toDate(rpr.getPaymentDate()));
			updateStatement.setInt(14, rpr.getId());

			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void delete(int id) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public PaymentBalken getPayPrById(int id) throws SQLException {
		PaymentBalken ppr = null;
		String query = new StringBuilder().append(getAllPayPrQuery)
				.append(" and pay_pr.id=?").toString();
		try {
			getAllPayPrStatement = connection.prepareStatement(query);
			getAllPayPrStatement.setInt(1, id);
			ResultSet rs = getAllPayPrStatement.executeQuery();

			while (rs.next()) {
				ppr = new PaymentBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setReceivedCode(rs.getString("received_code"));
				ppr.setInventoryBalkenId(rs.getInt("inventory_balken_id"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setRate(rs.getBigDecimal("rate"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceivedDate(rs.getDate("received_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setDeliveryNote(rs.getString("delivery_note"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppr;
	}

	public PaymentBalken getRPRById(int id) throws SQLException {
		PaymentBalken ppr = null;
		String query = new StringBuilder().append(getAllRPRQuery)
				.append(" and r.id=?").toString();
		try {
			getAllRPRStatement = connection.prepareStatement(query);
			getAllRPRStatement.setInt(1, id);
			ResultSet rs = getAllRPRStatement.executeQuery();

			while (rs.next()) {
				ppr = new PaymentBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setReceivedCode(rs.getString("received_code"));
				ppr.setInventoryBalkenId(rs.getInt("inventory_balken_id"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setRate(rs.getBigDecimal("rate"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceivedDate(rs.getDate("received_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setDeliveryNote(rs.getString("delivery_note"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppr;
	}

}
