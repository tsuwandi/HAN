package module.paymentprodresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.paymentprodresult.model.PaymentProdResult;
import module.util.DateUtil;

public class PaymentProdResultDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getAllPayPrStatement;
	private PreparedStatement getAllRPRStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isPPRCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("select id, pay_pr_code, id_inv_pr, due_date, rate, payment_status, payment_date, subtotal, ")
			.append("disc, tax, other_fee, total, status, rpr_code, receive_date, supp_name, ppr_code, purchase_date, source, currency_abbr from ( ")
			.append("select pay_pr.id, pay_pr.pay_pr_code, pay_pr.id_inv_pr, pay_pr.due_date, pay_pr.rate, pay_pr.payment_status, pay_pr.payment_date, pay_pr.subtotal, ")
			.append("pay_pr.disc, pay_pr.tax, pay_pr.other_fee, pay_pr.total, pay_pr.status, rpr.rpr_code, rpr.receive_date, s.supp_name, ppr.ppr_code, ppr.purchase_date, 'PAYMENT' as source, c.currency_abbr ")
			.append("from payment_prod_result pay_pr ")
			.append("inner join invoice_prod_result ipr on ipr.id = pay_pr.id_inv_pr ")
			.append("inner join receive_prod_result rpr on rpr.rpr_code = ipr.rpr_code ")
			.append("inner join purchase_prod_result ppr on rpr.ppr_code = ppr.ppr_code ")
			.append("inner join supplier s on s.supp_code = ppr.supp_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and ppr.deleted_date is null and rpr.deleted_date is null and pay_pr.deleted_date is null ")
			.append("union ")
			.append("select r.id, null as pay_pr_code, r.id as id_inv_pr, r.due_date as due_date, r.rate as rate, r.payment_status as payment_status, r.payment_date as payment_date, r.subtotal as subtotal, ")
			.append("r.disc as disc, r.tax as tax, r.other_fee as other_fee, r.total as total, '' as status, rpr.rpr_code, rpr.receive_date, s.supp_name, ppr.ppr_code, ppr.purchase_date, 'INVOICE' as source, c.currency_abbr ")
			.append("from invoice_prod_result r ")
			.append("inner join receive_prod_result rpr on rpr.rpr_code = r.rpr_code ")
			.append("inner join purchase_prod_result ppr on rpr.ppr_code = ppr.ppr_code ")
			.append("inner join supplier s on s.supp_code = ppr.supp_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and ppr.deleted_date is null and r.status = 'FINAL' and r.deleted_date is null and r.id not in (select id_inv_pr from payment_prod_result)) result ")
			.toString();

	private String getAllPayPrQuery = new StringBuilder()
			.append("select pay_pr.id, pay_pr.pay_pr_code, pay_pr.id_inv_pr, pay_pr.due_date, pay_pr.rate, pay_pr.payment_status, pay_pr.payment_date, pay_pr.subtotal, ")
			.append("pay_pr.disc, pay_pr.tax, pay_pr.other_fee, pay_pr.total, pay_pr.status, rpr.rpr_code, rpr.receive_date, s.supp_name, ppr.ppr_code, ppr.purchase_date, 'PAYMENT' as source, c.currency_abbr ")
			.append("from payment_prod_result pay_pr ")
			.append("inner join invoice_prod_result ipr on ipr.id = pay_pr.id_inv_pr ")
			.append("inner join receive_prod_result rpr on rpr.rpr_code = ipr.rpr_code ")
			.append("inner join purchase_prod_result ppr on rpr.ppr_code = ppr.ppr_code ")
			.append("inner join supplier s on s.supp_code = ppr.supp_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and ppr.deleted_date is null and rpr.deleted_date is null and pay_pr.deleted_date is null ")
			.toString();

	private String getAllRPRQuery = new StringBuilder()
			.append("select r.id, null as pay_pr_code, r.id as id_inv_pr, r.due_date as due_date, r.rate as rate, r.payment_status as payment_status, r.payment_date, r.subtotal as subtotal, ")
			.append("r.disc as disc, r.tax as tax, r.other_fee as other_fee, r.total as total,  '' as status, rpr.rpr_code, rpr.receive_date, s.supp_name, ppr.ppr_code, ppr.purchase_date, 'INVOICE' as source, c.currency_abbr ")
			.append("from invoice_prod_result r ")
			.append("inner join receive_prod_result rpr on rpr.rpr_code = r.rpr_code ")
			.append("inner join purchase_prod_result ppr on rpr.ppr_code = ppr.ppr_code ")
			.append("inner join supplier s on s.supp_code = ppr.supp_code ")
			.append("left join currency c on c.id = s.currency_id ")
			.append("where s.deleted_date is null and ppr.deleted_date is null and r.status = 'FINAL' and r.deleted_date is null and r.id not in (select id_inv_pr from payment_prod_result) ")
			.toString();

	private String isPPRCodeExistsQuery = "select count(*) as is_exists from payment_prod_result where pay_pr_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("insert into payment_prod_result (pay_pr_code, id_inv_pr, due_date, rate, payment_status, ")
			.append("subtotal, disc, tax, other_fee, total, status, input_date, input_by, payment_date) ")
			.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)").toString();

	private String updateQuery = new StringBuilder()
			.append("update payment_prod_result set id_inv_pr=?, due_date=?, rate=?, payment_status=?, ")
			.append("subtotal=?, disc=?, tax=?, other_fee=?, total=?, status=?, ")
			.append("edit_date=?, edited_by=?, payment_date=? where pay_pr_code=?").toString();

	private String deleteQuery = "update payment_prod_result set deleted_date=?, deleted_by=? where id=?";

	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(pay_pr_code, '/', 1),UNSIGNED INTEGER) AS ordinal FROM payment_prod_result "
			+ "WHERE SUBSTRING_INDEX(pay_pr_code, '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";

	public PaymentProdResultDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<PaymentProdResult> getAll() throws SQLException {
		List<PaymentProdResult> rprs = new ArrayList<PaymentProdResult>();

		String query = new StringBuilder().append(getAllQuery).toString();

		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PaymentProdResult ppr = new PaymentProdResult();
				ppr.setId(rs.getInt("id"));
				ppr.setPayPrCode(rs.getString("pay_pr_code"));
				ppr.setRprCode(rs.getString("rpr_code"));
				ppr.setIdInvPr(rs.getInt("id_inv_pr"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceiveDate(rs.getDate("receive_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));
				ppr.setSource(rs.getString("source"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				rprs.add(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public List<PaymentProdResult> getAllBySimpleSearch(String value)
			throws SQLException {
		List<PaymentProdResult> rprs = new ArrayList<PaymentProdResult>();
		try {

			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value)
						.append("%").toString();
				String query = new StringBuilder().append(" and")
						.append(" (lower(pay_pr_code) like lower('%s')")
						.append(" or lower(rpr_code) like lower('%s')")
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
				PaymentProdResult ppr = new PaymentProdResult();
				ppr.setId(rs.getInt("id"));
				ppr.setPayPrCode(rs.getString("pay_pr_code"));
				ppr.setRprCode(rs.getString("rpr_code"));
				ppr.setIdInvPr(rs.getInt("id_inv_pr"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceiveDate(rs.getDate("receive_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
				rprs.add(ppr);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public int isPPRCodeExists(String pprCode) throws SQLException {
		int count = 0;
		try {
			isPPRCodeExistsStatement = connection
					.prepareStatement(isPPRCodeExistsQuery);
			isPPRCodeExistsStatement.setString(1, pprCode);

			ResultSet rs = isPPRCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(PaymentProdResult ppr) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, ppr.getPayPrCode());
		
			if(ppr.getIdInvPr() == 0) {
				insertStatement.setNull(2, java.sql.Types.INTEGER);  
			} else {
				insertStatement.setInt(2, ppr.getIdInvPr());
			}
			
			insertStatement.setDate(3, DateUtil.toDate(ppr.getDueDate()));
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
			insertStatement.setDate(14, DateUtil.toDate(ppr.getPaymentDate()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(PaymentProdResult rpr) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, rpr.getIdInvPr());
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
			updateStatement.setString(14, rpr.getPayPrCode());

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

	public PaymentProdResult getPayPrById(int id) throws SQLException {
		PaymentProdResult ppr = null;
		String query = new StringBuilder().append(getAllPayPrQuery)
				.append(" and pay_pr.id=?").toString();
		try {
			getAllPayPrStatement = connection.prepareStatement(query);
			getAllPayPrStatement.setInt(1, id);
			ResultSet rs = getAllPayPrStatement.executeQuery();

			while (rs.next()) {
				ppr = new PaymentProdResult();
				ppr.setId(rs.getInt("id"));
				ppr.setPayPrCode(rs.getString("pay_pr_code"));
				ppr.setRprCode(rs.getString("rpr_code"));
				ppr.setIdInvPr(rs.getInt("id_inv_pr"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setRate(rs.getBigDecimal("rate"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceiveDate(rs.getDate("receive_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppr;
	}

	public PaymentProdResult getRPRById(int id) throws SQLException {
		PaymentProdResult ppr = null;
		String query = new StringBuilder().append(getAllRPRQuery)
				.append(" and r.id=?").toString();
		try {
			getAllRPRStatement = connection.prepareStatement(query);
			getAllRPRStatement.setInt(1, id);
			ResultSet rs = getAllRPRStatement.executeQuery();

			while (rs.next()) {
				ppr = new PaymentProdResult();
				ppr.setId(rs.getInt("id"));
				ppr.setPayPrCode(rs.getString("pay_pr_code"));
				ppr.setRprCode(rs.getString("rpr_code"));
				ppr.setIdInvPr(rs.getInt("id_inv_pr"));
				ppr.setDueDate(rs.getDate("due_date"));
				ppr.setPaymentStatus(rs.getString("payment_status"));
				ppr.setSubtotal(rs.getBigDecimal("subtotal"));
				ppr.setRate(rs.getBigDecimal("rate"));
				ppr.setDisc(rs.getBigDecimal("disc"));
				ppr.setTax(rs.getBigDecimal("tax"));
				ppr.setOtherFee(rs.getBigDecimal("other_fee"));
				ppr.setTotal(rs.getBigDecimal("total"));
				ppr.setStatus(rs.getString("status"));
				ppr.setReceiveDate(rs.getDate("receive_date"));
				ppr.setSuppName(rs.getString("supp_name"));
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));
				ppr.setSource(rs.getString("source"));
				ppr.setCurrency(rs.getString("currency_abbr"));
				ppr.setPaymentDate(rs.getDate("payment_date"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return ppr;
	}

	public int getOrdinalOfCodeNumberByYear(int year) throws SQLException {
		int ordinal = 0;
		try {
			getOrdinalOfCodeNumberStatement = connection
					.prepareStatement(getOrdinalOfCodeNumberQuery);
			getOrdinalOfCodeNumberStatement.setInt(1, year);

			ResultSet rs = getOrdinalOfCodeNumberStatement.executeQuery();
			while (rs.next()) {
				ordinal = rs.getInt("ordinal");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return ordinal;
	}

}
