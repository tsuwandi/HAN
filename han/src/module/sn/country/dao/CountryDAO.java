package module.sn.country.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.bank.model.Bank;
import module.sn.country.model.Country;

public class CountryDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select id, name, idx, country_name, date_format, code, time_zones from country order by name";

	public CountryDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Country> getAll() throws SQLException {
		List<Country> countries = new ArrayList<Country>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setId(rs.getInt("id"));
				country.setName(rs.getString("name"));
				country.setIdx(rs.getInt("idx"));
				country.setCountryName(rs.getString("country_name"));
				country.setDateFormat(rs.getString("date_format"));
				country.setCode(rs.getString("code"));
				country.setTimeZones(rs.getString("time_zones"));

				countries.add(country);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return countries;
	}
}
