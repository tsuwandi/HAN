package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.SupplierVehicle;

public class SupplierVehicleDAO {
	private DataSource dataSource;

	private PreparedStatement getAllBySuppCodeStatement;

	private String getAllBySuppCodeQuery = "select sv.id, sv.supp_code, sv.license_plate, s.supp_name"
			+ " FROM supp_vehicle sv INNER JOIN supplier s ON s.supp_code = sv.supp_code";

	public SupplierVehicleDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	public List<SupplierVehicle> getListSuppVehicle() throws SQLException {
		Connection con = null;
		List<SupplierVehicle> suppVehicles = new ArrayList<SupplierVehicle>();

		try {
			con = dataSource.getConnection();
			getAllBySuppCodeStatement = con.prepareStatement(getAllBySuppCodeQuery);
			
			ResultSet rs = getAllBySuppCodeStatement.executeQuery();
			while (rs.next()) {
				SupplierVehicle suppVehicle = new SupplierVehicle();
				suppVehicle.setId(rs.getInt("id"));
				suppVehicle.setSuppCode(rs.getString("supp_code"));
				suppVehicle.setSupplierName(rs.getString("supp_name"));
				suppVehicle.setLicensePlate(rs.getString("license_plate"));

				suppVehicles.add(suppVehicle);
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

		return suppVehicles;
	}
}
