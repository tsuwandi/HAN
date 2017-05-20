package module.invoicebalken.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.util.DateUtil;
import module.invoicebalken.model.InvoiceBalken;

public class InvoiceBalkenDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getAllPayPrStatement;
	private PreparedStatement getAllRPRStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement updatePaymentStatusStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("select id, received_code, due_date, rate, payment_status, payment_date, subtotal, ")
			.append("disc, tax, other_fee, total, status, received_date, supp_name, source, currency_abbr, delivery_note from ( ")
			.append("select pay_pr.id, pay_pr.received_code, pay_pr.due_date, pay_pr.rate, pay_pr.payment_status, pay_pr.payment_date, pay_pr.subtotal, ")
			.append("pay_pr.disc, pay_pr.tax, pay_pr.other_fee, pay_pr.total, pay_pr.status, rpr.received_date, s.supp_name, 'INVOICE_BALKEN' as source, c.currency_abbr, rpr.delivery_note ")
			.append("from invoice_balken pay_pr ")
			.append("inner join received rpr on rpr.received_code = pay_pr.received_code ")
			.append("left join supplier s on s.supp_code = rpr.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where rpr.deleted_date is null and pay_pr.deleted_date is null ")
			.append("union ")
			.append("select r.id, r.received_code, null as due_date, null as rate, 'Belum' as payment_status, null as payment_date, null as subtotal, ")
			.append("null as disc, null as tax, null as other_fee, null as total, '' as status, r.received_date, s.supp_name, 'RECEIVED' as source, c.currency_abbr, r.delivery_note ")
			.append("from received r ")
			.append("left join supplier s on s.supp_code = r.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where r.received_status = 'FINAL' and r.deleted_date is null and r.received_code not in (select received_code from invoice_balken)) result order by received_code ")
			.toString();

	private String getAllPayPrQuery = new StringBuilder()
			.append("select pay_pr.id, pay_pr.received_code, pay_pr.due_date, pay_pr.rate, pay_pr.payment_status, pay_pr.payment_date, pay_pr.subtotal, ")
			.append("pay_pr.disc, pay_pr.tax, pay_pr.other_fee, pay_pr.total, pay_pr.status, rpr.received_date, s.supp_name, 'INVOICE_BALKEN' as source, c.currency_abbr, rpr.delivery_note ")
			.append("from invoice_balken pay_pr ")
			.append("inner join received rpr on rpr.received_code = pay_pr.received_code ")
			.append("left join supplier s on s.supp_code = rpr.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where rpr.deleted_date is null and pay_pr.deleted_date is null ")
			.toString();

	private String getAllRPRQuery = new StringBuilder()
			.append("select r.id, r.received_code, null as due_date, null as rate, 'Belum' as payment_status, null as payment_date, null as subtotal, ")
			.append("null as disc, null as tax, null as other_fee, null as total, '' as status, r.received_date, s.supp_name, 'RECEIVED' as source, c.currency_abbr, r.delivery_note ")
			.append("from received r ")
			.append("left join supplier s on s.supp_code = r.supplier_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where r.received_status = 'FINAL' and r.deleted_date is null and r.received_code not in (select received_code from invoice_balken) ")
			.toString();

	private String insertQuery = new StringBuilder()
			.append("insert into invoice_balken (payment_date, received_code, due_date, rate, payment_status, ")
			.append("subtotal, disc, tax, other_fee, total, status, input_date, input_by) ")
			.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?)").toString();

	private String updateQuery = new StringBuilder()
			.append("update invoice_balken set received_code=?, due_date=?, rate=?, payment_status=?, ")
			.append("subtotal=?, disc=?, tax=?, other_fee=?, total=?, status=?, ")
			.append("edited_date=?, edited_by=?, payment_date=? where id=?")
			.toString();

	private String deleteQuery = "update invoice_balken set deleted_date=?, deleted_by=? where id=?";

	private String updatePaymentStatusQuery = new StringBuilder()
			.append("update invoice_balken set payment_status=?, ")
			.append("payment_date=? where id=?").toString();

	public InvoiceBalkenDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<InvoiceBalken> getAll() throws SQLException {
		List<InvoiceBalken> rprs = new ArrayList<InvoiceBalken>();

		String query = new StringBuilder().append(getAllQuery).toString();

		try {
			getAllStatement = connection.prepareStatement(query);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				InvoiceBalken ppr = new InvoiceBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setReceivedCode(rs.getString("received_code"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceivedDate(rs.getDate("received_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setSource(rs.getString("source"));
				ppr.setDeliveryNote(rs.getString("delivery_note"));
				rprs.add(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public List<InvoiceBalken> getAllBySimpleSearch(String value)
			throws SQLException {
		List<InvoiceBalken> rprs = new ArrayList<InvoiceBalken>();
		try {

			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value)
						.append("%").toString();
				String query = new StringBuilder().append(" and")
						.append(" (lower(received_code) like lower('%s')")
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
				InvoiceBalken ppr = new InvoiceBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setReceivedCode(rs.getString("received_code"));
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
				rprs.add(ppr);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public InvoiceBalken save(InvoiceBalken ppr) throws SQLException {
		try {
			ResultSet generatedKeys = null;

			insertStatement = connection.prepareStatement(insertQuery);
			if (ppr.getPaymentDate() == null) {
				insertStatement.setDate(1, null);
			} else {
				insertStatement.setDate(1,
						DateUtil.toDate(ppr.getPaymentDate()));
			}

			insertStatement.setString(2, ppr.getReceivedCode());
			if (ppr.getDueDate() == null) {
				insertStatement.setDate(3, null);
			} else {
				insertStatement.setDate(3, DateUtil.toDate(ppr.getDueDate()));
			}

			insertStatement.setBigDecimal(4, ppr.getRate());
			insertStatement.setString(5, ppr.getPaymentStatus());
			insertStatement.setBigDecimal(6, ppr.getSubtotal());
			insertStatement.setBigDecimal(7, ppr.getDisc());
			insertStatement.setBigDecimal(8, ppr.getTax());
			insertStatement.setBigDecimal(9, ppr.getOtherFee());
			insertStatement.setBigDecimal(10, ppr.getTotal());
			insertStatement.setString(11, ppr.getStatus());
			insertStatement.setDate(12, DateUtil.getCurrentDate());
			insertStatement.setString(13, "timotius");
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

	public void update(InvoiceBalken rpr) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, rpr.getReceivedCode());
			if (rpr.getDueDate() == null) {
				updateStatement.setDate(2, null);
			} else {
				updateStatement.setDate(2, DateUtil.toDate(rpr.getDueDate()));
			}

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
			if (rpr.getPaymentDate() == null) {
				updateStatement.setDate(13, null);
			} else {
				updateStatement.setDate(13,
						DateUtil.toDate(rpr.getPaymentDate()));
			}

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

	public InvoiceBalken getPayPrById(int id) throws SQLException {
		InvoiceBalken ppr = null;
		String query = new StringBuilder().append(getAllPayPrQuery)
				.append(" and pay_pr.id=?").toString();
		try {
			getAllPayPrStatement = connection.prepareStatement(query);
			getAllPayPrStatement.setInt(1, id);
			ResultSet rs = getAllPayPrStatement.executeQuery();

			while (rs.next()) {
				ppr = new InvoiceBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setReceivedCode(rs.getString("received_code"));
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
				ppr.setDeliveryNote(rs.getString("delivery_note"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppr;
	}

	public InvoiceBalken getRPRById(int id) throws SQLException {
		InvoiceBalken ppr = null;
		String query = new StringBuilder().append(getAllRPRQuery)
				.append(" and r.id=?").toString();
		try {
			getAllRPRStatement = connection.prepareStatement(query);
			getAllRPRStatement.setInt(1, id);
			ResultSet rs = getAllRPRStatement.executeQuery();

			while (rs.next()) {
				ppr = new InvoiceBalken();
				ppr.setId(rs.getInt("id"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				ppr.setReceivedCode(rs.getString("received_code"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setRate(rs.getBigDecimal("rate"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceivedDate(rs.getDate("received_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setDeliveryNote(rs.getString("delivery_note"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppr;
	}

	public void updatePaymentStatusStatement(InvoiceBalken rpr)
			throws SQLException {
		try {
			updatePaymentStatusStatement = connection
					.prepareStatement(updatePaymentStatusQuery);

			updatePaymentStatusStatement.setString(1, rpr.getPaymentStatus());
			if (rpr.getPaymentDate() == null) {
				updatePaymentStatusStatement.setDate(2, null);
			} else {
				updatePaymentStatusStatement.setDate(2,
						DateUtil.toDate(rpr.getPaymentDate()));
			}
			updatePaymentStatusStatement.setInt(3, rpr.getId());
			updatePaymentStatusStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
