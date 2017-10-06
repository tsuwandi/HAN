package module.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.customer.model.CustAddress;
import module.customer.model.CustBank;
import module.util.DateUtil;

public class CustomerBankDAO {
	private Connection connection;

	private PreparedStatement getAllByCustCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByCustCodeQuery = "select id, cust_code, cust_id, swiftcode, "
			+ "bankname, accountno, currency_id, accname, note "
			+ "from bank_cust where cust_code = ? and deleted_date is null";

	private String insertQuery = "insert into bank_cust (cust_code, cust_id, swiftcode, bankname, accountno, currency_id, accname, note,"
			+ " input_date, input_by) values (?,?,?,?,?,?,?,?,?,?)";

	private String updateQuery = "update bank_cust set swiftcode=?, bankname=?, accountno=?, "
			+ "currency_id=?, accname=?, note=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update bank_cust set deleted_date=?, deleted_by=? ";

	public CustomerBankDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<CustBank> getAllByCustCode(String custCode) throws SQLException {
		List<CustBank> custBanks = new ArrayList<CustBank>();

		try {
			getAllByCustCodeStatement = connection.prepareStatement(getAllByCustCodeQuery);

			getAllByCustCodeStatement.setString(1, custCode);

			ResultSet rs = getAllByCustCodeStatement.executeQuery();
			while (rs.next()) {
				CustBank customerBank = new CustBank();
				customerBank.setId(rs.getInt("id"));
				customerBank.setCustCode(rs.getString("cust_code"));
				customerBank.setCustId(rs.getInt("cust_id"));
				customerBank.setSwiftCode(rs.getString("swiftcode"));
				customerBank.setBankName(rs.getString("bankname"));
				customerBank.setAccountNo(rs.getString("accountno"));
				customerBank.setCurrencyId(rs.getInt("currency_id"));
				customerBank.setAccountName(rs.getString("accname"));
				customerBank.setNote(rs.getString("note"));

				custBanks.add(customerBank);

			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return custBanks;
	}

	public CustBank save(CustBank custBank) throws SQLException {
		ResultSet generatedKeys = null;

		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, custBank.getCustCode());
			insertStatement.setInt(2, custBank.getCustId());
			insertStatement.setString(3, custBank.getSwiftCode());
			insertStatement.setString(4, custBank.getBankName());
			insertStatement.setString(5, custBank.getAccountNo());
			insertStatement.setInt(6, custBank.getCurrencyId());
			insertStatement.setString(7, custBank.getAccountName());
			insertStatement.setString(8, custBank.getNote());
			insertStatement.setDate(9, DateUtil.getCurrentDate());
			insertStatement.setString(10, "Sandy");
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				custBank.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			return custBank;

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(CustBank custBank) throws SQLException {
		try {

			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, custBank.getSwiftCode());
			updateStatement.setString(2, custBank.getBankName());
			updateStatement.setString(3, custBank.getAccountNo());
			updateStatement.setInt(4, custBank.getCurrencyId());
			updateStatement.setString(5, custBank.getAccountName());
			updateStatement.setString(6, custBank.getNote());
			updateStatement.setDate(7, DateUtil.getCurrentDate());
			updateStatement.setString(8, "Sandy");
			updateStatement.setInt(9, custBank.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String custCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where cust_code = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setString(3, custCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "Sandy");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
