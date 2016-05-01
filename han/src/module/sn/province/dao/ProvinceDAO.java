package module.sn.province.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.province.model.Province;

public class ProvinceDAO {
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
	private String getAllQuery = "select id, province from province order by province";

	public ProvinceDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Province> getAll() throws SQLException {
		List<Province> provinces = new ArrayList<Province>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Province province = new Province();
				province.setId(rs.getInt("id"));
				province.setProvince(rs.getString("province"));

				provinces.add(province);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return provinces;
	}
}
