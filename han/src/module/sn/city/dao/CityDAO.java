package module.sn.city.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.city.model.City;

public class CityDAO {
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
	private String getAllQuery = "select id, city, province_id from city where province_id = ? order by city";

	public CityDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<City> getAllByProvinceId(int provinceId) throws SQLException {
		List<City> cities = new ArrayList<City>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			getAllStatement.setInt(1, provinceId);
		
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				City city = new City();
				city.setId(rs.getInt("id"));
				city.setCity(rs.getString("city"));
				city.setProvinceId(rs.getInt("province_id"));

				cities.add(city);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return cities;
	}
}
