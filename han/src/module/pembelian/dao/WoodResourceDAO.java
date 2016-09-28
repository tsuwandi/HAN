package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.WoodResource;

public class WoodResourceDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT id, wood_resource FROM wood_resource WHERE 1 = 1 ";

	public WoodResourceDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<WoodResource> getWoodResource() throws SQLException {
		Connection con = null;
		ArrayList<WoodResource> woodResources = new ArrayList<WoodResource>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				WoodResource woodResource = new WoodResource();
				woodResource.setId(rs.getInt("id"));
				woodResource.setWoodResource(rs.getString("wood_resource"));
				woodResources.add(woodResource);
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

		return woodResources;
	}
}
