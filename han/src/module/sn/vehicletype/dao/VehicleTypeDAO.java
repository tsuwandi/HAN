package module.sn.vehicletype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import module.sn.vehicletype.model.VehicleType;

public class VehicleTypeDAO {
	private Connection connection;
	//private PreparedStatement insertStatement;
	//private PreparedStatement updateStatement;
	//private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	//private String insertQuery = "insert into bank (bank_abbr, bank, input_date, input_by) values (?,?,?,?)";
	//private String updateQuery = "update bank set bank_abbr=?, bank=?, edit_date=?, edited_by=? where id=?";
	//private String deleteQuery = "update bank set deleted_date=?, deleted_by=? where id=?";
	private String getAllQuery = "select id, vehicle_type from vehicle_type order by vehicle_type";

	public VehicleTypeDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

//	public List<VehicleType> getAll() throws SQLException {
//		Connection con = null;
//		List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
//
//		try {
//			con = dataSource.getConnection();
//			getAllStatement = con.prepareStatement(getAllQuery);
//
//			ResultSet rs = getAllStatement.executeQuery();
//			while (rs.next()) {
//				VehicleType vehicleType = new VehicleType();
//				vehicleType.setId(rs.getInt("id"));
//				vehicleType.setVehicleType(rs.getString("vehicle_type"));
//
//				vehicleTypes.add(vehicleType);
//			}
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new SQLException(ex.getMessage());
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//			}
//		}
//
//		return vehicleTypes;
//	}
	
	public HashMap<String, Integer> getAll() throws SQLException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				VehicleType vehicleType = new VehicleType();
				vehicleType.setId(rs.getInt("id"));
				vehicleType.setVehicleType(rs.getString("vehicle_type"));
				map.put(vehicleType.getVehicleType(), vehicleType.getId());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return map;
	}
}
