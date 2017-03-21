package module.receiveprodresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.purchaseprodresult.model.PurchaseProdResult;
import module.receiveprodresult.model.ReceiveProdResult;
import module.util.DateUtil;

public class ReceiveProdResultDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement getAllRPRStatement;
	private PreparedStatement getAllPPRStatement;
	private PreparedStatement getOrdinalOfCodeNumberStatement;
	private PreparedStatement isRPRCodeExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = new StringBuilder()
			.append("select id, rpr_code, ppr_code, receive_date, status, purchase_date from ( ")
			.append("select r.id, r.rpr_code, r.ppr_code, r.receive_date, ")
			.append("r.status, p.purchase_date ")
			.append("from receive_prod_result r ")
			.append("inner join purchase_prod_result p on r.ppr_code = p.ppr_code ")
			.append("where p.deleted_date is null and r.deleted_date is null ")
			.append("union ")
			.append("select ppr.id as id, '' as rpr_code, ppr.ppr_code, null as receive_date,  ")
			.append("'NEW' as status, ppr.purchase_date ")
			.append("from purchase_prod_result ppr ")
			.append("where ppr.deleted_date is null and ppr.ppr_code not in (select ppr_code from receive_prod_result)) result ")
			.toString();

	private String getAllRPRQuery = new StringBuilder()
			.append("select r.id, r.rpr_code, r.ppr_code, r.receive_date, ")
			.append("r.status, p.purchase_date ")
			.append("from receive_prod_result r ")
			.append("inner join purchase_prod_result p on r.ppr_code = p.ppr_code ")
			.append("where p.deleted_date is null and r.deleted_date is null ")
			.toString();

	private String getAllPPRQuery = new StringBuilder()
			.append("select ppr.id as id, '' as rpr_code, ppr.ppr_code, null as receive_date,  ")
			.append("'NEW' as status, ppr.purchase_date ")
			.append("from purchase_prod_result ppr ")
			.append("where ppr.deleted_date is null and ppr.ppr_code not in (select ppr_code from receive_prod_result) ")
			.toString();

	private String isRPRCodeExistsQuery = "select count(*) as is_exists from receive_prod_result where rpr_code = ? and deleted_date is null ";

	private String insertQuery = new StringBuilder()
			.append("insert into receive_prod_result (rpr_code, ppr_code, receive_date, ")
			.append("status, input_date, input_by) ")
			.append("values (?,?,?,?,?,?)").toString();

	private String updateQuery = new StringBuilder()
			.append("update receive_prod_result set ppr_code=?, receive_date=?, ")
			.append("status=?, ")
			.append("edit_date=?, edited_by=? where rpr_code=?").toString();

	private String deleteQuery = "update receive_prod_result set deleted_date=?, deleted_by=? where id=?";

	private String getOrdinalOfCodeNumberQuery = "SELECT CONVERT(SUBSTRING_INDEX(rpr_code, '/', 1),UNSIGNED INTEGER) AS ordinal FROM receive_prod_result "
			+ "WHERE SUBSTRING_INDEX(rpr_code, '/', -1) = ? "
			+ "ORDER BY ordinal DESC LIMIT 1 ";

	public ReceiveProdResultDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ReceiveProdResult> getAll() throws SQLException {
		List<ReceiveProdResult> rprs = new ArrayList<ReceiveProdResult>();

		String query = new StringBuilder().append(getAllQuery).toString();

		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ReceiveProdResult rpr = new ReceiveProdResult();
				rpr.setId(rs.getInt("id"));
				rpr.setRprCode(rs.getString("rpr_code"));
				rpr.setPprCode(rs.getString("ppr_code"));
				rpr.setReceiveDate(rs.getDate("receive_date"));
				rpr.setStatus(rs.getString("status"));

				PurchaseProdResult ppr = new PurchaseProdResult();
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));

				rpr.setPurchaseProdResult(ppr);

				rprs.add(rpr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public List<ReceiveProdResult> getAllBySimpleSearch(String value)
			throws SQLException {
		List<ReceiveProdResult> rprs = new ArrayList<ReceiveProdResult>();
		try {

			if (null != value && !"".equals(value)) {
				String keyword = new StringBuilder().append("%").append(value)
						.append("%").toString();
				String query = new StringBuilder().append(" and")
						.append(" (lower(p.rpr_code) like lower('%s')")
						.append(" or lower(p.rpr_code) like lower('%s')")
						.append(" or lower(p.status) like lower('%s'))")
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
				ReceiveProdResult rpr = new ReceiveProdResult();
				rpr.setId(rs.getInt("id"));
				rpr.setRprCode(rs.getString("rpr_code"));
				rpr.setPprCode(rs.getString("ppr_code"));
				rpr.setReceiveDate(rs.getDate("receive_date"));
				rpr.setStatus(rs.getString("status"));

				PurchaseProdResult ppr = new PurchaseProdResult();
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));

				rpr.setPurchaseProdResult(ppr);

				rprs.add(rpr);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return rprs;
	}

	public int isRPRCodeExists(String rprCode) throws SQLException {
		int count = 0;
		try {
			isRPRCodeExistsStatement = connection
					.prepareStatement(isRPRCodeExistsQuery);
			isRPRCodeExistsStatement.setString(1, rprCode);

			ResultSet rs = isRPRCodeExistsStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return count;
	}

	public void save(ReceiveProdResult rpr) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, rpr.getRprCode());
			insertStatement.setString(2, rpr.getPprCode());
			insertStatement.setDate(3, DateUtil.toDate(rpr.getReceiveDate()));
			insertStatement.setString(4, rpr.getStatus());
			insertStatement.setDate(5, DateUtil.getCurrentDate());
			insertStatement.setString(6, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.getCause();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ReceiveProdResult rpr) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, rpr.getPprCode());
			updateStatement.setDate(2, DateUtil.toDate(rpr.getReceiveDate()));
			updateStatement.setString(3, rpr.getStatus());
			updateStatement.setDate(4, DateUtil.getCurrentDate());
			updateStatement.setString(5, "timotius");
			updateStatement.setString(6, rpr.getPprCode());

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

	public ReceiveProdResult getRPRById(int id) throws SQLException {
		ReceiveProdResult rpr = null;
		String query = new StringBuilder().append(getAllRPRQuery)
				.append(" and r.id=?").toString();
		try {
			getAllRPRStatement = connection.prepareStatement(query);
			getAllRPRStatement.setInt(1, id);
			ResultSet rs = getAllRPRStatement.executeQuery();

			while (rs.next()) {
				rpr = new ReceiveProdResult();
				rpr.setId(rs.getInt("id"));
				rpr.setRprCode(rs.getString("rpr_code"));
				rpr.setPprCode(rs.getString("ppr_code"));
				rpr.setReceiveDate(rs.getDate("receive_date"));
				rpr.setStatus(rs.getString("status"));

				PurchaseProdResult ppr = new PurchaseProdResult();
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));

				rpr.setPurchaseProdResult(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rpr;
	}

	public ReceiveProdResult getPPRById(int id) throws SQLException {
		ReceiveProdResult rpr = null;
		String query = new StringBuilder().append(getAllPPRQuery)
				.append(" and id=?").toString();
		try {
			getAllPPRStatement = connection.prepareStatement(query);
			getAllPPRStatement.setInt(1, id);
			ResultSet rs = getAllPPRStatement.executeQuery();

			while (rs.next()) {
				rpr = new ReceiveProdResult();
				rpr.setId(rs.getInt("id"));
				rpr.setRprCode(rs.getString("rpr_code"));
				rpr.setPprCode(rs.getString("ppr_code"));
				rpr.setReceiveDate(rs.getDate("receive_date"));
				rpr.setStatus(rs.getString("status"));

				PurchaseProdResult ppr = new PurchaseProdResult();
				ppr.setPprCode(rs.getString("ppr_code"));
				ppr.setPurchaseDate(rs.getDate("purchase_date"));

				rpr.setPurchaseProdResult(ppr);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return rpr;
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
