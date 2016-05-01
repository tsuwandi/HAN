package module.sn.currency.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.currency.model.Currency;

public class CurrencyDAO {
	private Connection connection;
	//private PreparedStatement insertStatement;
	//private PreparedStatement updateStatement;
	//private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	//private String insertQuery = "insert into bank (bank_abbr, bank, input_date, input_by) values (?,?,?,?)";
	//private String updateQuery = "update bank set bank_abbr=?, bank=?, edit_date=?, edited_by=? where id=?";
	//private String deleteQuery = "update bank set deleted_date=?, deleted_by=? where id=?";
	private String getAllQuery = "select id, currency_abbr, currency_symbol, currency from currency order by currency_abbr";
	
	public CurrencyDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Currency> getAll() throws SQLException {
		List<Currency> currencies = new ArrayList<Currency>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Currency currency = new Currency();
				currency.setId(rs.getInt("id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrencySymbol(rs.getString("currency_symbol"));
				currency.setCurrency(rs.getString("currency"));
				
				currencies.add(currency);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return currencies;
	}
}
