package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.WoodType;

public class WoodTypeDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT id, wood_type FROM wood_type WHERE 1 = 1 ";

	public WoodTypeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<WoodType> getWoodType() throws SQLException {
		Connection con = null;
		ArrayList<WoodType> woodTypes = new ArrayList<WoodType>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				WoodType woodType = new WoodType();
				woodType.setId(rs.getInt("id"));
				woodType.setWoodType(rs.getString("wood_type"));
				woodTypes.add(woodType);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return woodTypes;
	}
}
