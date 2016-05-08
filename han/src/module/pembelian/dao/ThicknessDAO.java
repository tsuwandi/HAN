package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Grade;
import module.pembelian.model.Thickness;

public class ThicknessDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT id, thickness"
			+ " FROM thickness ";

	public ThicknessDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
		
	}

	public List<Thickness> getThickness() throws SQLException {
		Connection con = null;
		ArrayList<Thickness> thicknesses = new ArrayList<Thickness>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Thickness thickness = new Thickness();
				thickness.setId(rs.getInt("id"));
				thickness.setThickness(rs.getDouble("thickness"));
				thickness.setThick(rs.getString("thickness"));
				thicknesses.add(thickness);
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

		return thicknesses;
	}
}
