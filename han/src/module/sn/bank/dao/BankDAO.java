package module.sn.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.bank.model.Bank;

public class BankDAO {
	private Connection connection;
	// private PreparedStatement insertStatement;
	// private PreparedStatement updateStatement;
	// private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	// private String insertQuery = "insert into bank (bank_abbr, bank,
	// input_date, input_by) values (?,?,?,?)";
	// private String updateQuery = "update bank set bank_abbr=?, bank=?,
	// edit_date=?, edited_by=? where id=?";
	// private String deleteQuery = "update bank set deleted_date=?,
	// deleted_by=? where id=?";
	private String getAllQuery = "select id, bank_abbr, bank from bank order by bank_abbr";

	public BankDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Bank> getAll() throws SQLException {
		List<Bank> banks = new ArrayList<Bank>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Bank bank = new Bank();
				bank.setId(rs.getInt("id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				banks.add(bank);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return banks;
	}
}
