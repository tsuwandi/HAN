package module.supplier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.vehicletype.model.VehicleType;
import module.supplier.model.SuppVehicle;
import module.util.DateUtil;

public class SuppVehicleDAO {
	private Connection connection;

	private PreparedStatement getAllBySuppCodeStatement;
	private PreparedStatement isLicensePlateExistsStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySuppCodeQuery = "select sv.id, sv.supp_code, sv.license_plate, sv.vehicle_type_id,"
			+ "vt.id, vt.vehicle_type "
			+ "from supp_vehicle sv "
			+ "inner join vehicle_type vt on vt.id = sv.vehicle_type_id "
			+ "where sv.supp_code = ? and sv.deleted_date is null and vt.deleted_date is null ";
	
	private String isLicensePlateExistsQuery = "select count(*) as is_exists from supp_vehicle "
			+ "where license_plate = ? and deleted_date is null ";
	
	private String insertQuery = "insert into supp_vehicle (supp_code, license_plate, "
			+ "vehicle_type_id, input_date, input_by) values (?,?,?,?,?)";
	
	private String updateQuery = "update supp_vehicle set "
			+ "vehicle_type_id=?, edit_date=?, edited_by=? where id=?";
	
	private String deleteQuery = "update supp_vehicle set deleted_date=?, deleted_by=? ";

	public SuppVehicleDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<SuppVehicle> getAllBySuppCode(String suppCode) throws SQLException {
		List<SuppVehicle> suppVehicles = new ArrayList<SuppVehicle>();

		try {
			getAllBySuppCodeStatement = connection.prepareStatement(getAllBySuppCodeQuery);
			getAllBySuppCodeStatement.setString(1, suppCode);
			
			ResultSet rs = getAllBySuppCodeStatement.executeQuery();
			while (rs.next()) {
				SuppVehicle suppVehicle = new SuppVehicle();
				suppVehicle.setId(rs.getInt("id"));
				suppVehicle.setSuppCode(rs.getString("supp_code"));
				suppVehicle.setLicensePlate(rs.getString("license_plate"));
				suppVehicle.setVehicleTypeId(rs.getInt("vehicle_type_id"));
			
				VehicleType	vehicleType = new VehicleType();
				vehicleType.setId(rs.getInt("id"));
				vehicleType.setVehicleType(rs.getString("vehicle_type"));
				
				suppVehicle.setVehicleType(vehicleType);
				suppVehicles.add(suppVehicle);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return suppVehicles;
	}
	
	public int isLicensePlateExists(String licensePlate) throws SQLException {
		int count = 0;
		try {
			isLicensePlateExistsStatement = connection.prepareStatement(isLicensePlateExistsQuery);
			isLicensePlateExistsStatement.setString(1, licensePlate);
			
			ResultSet rs = isLicensePlateExistsStatement.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt("is_exists");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return count;
	}
	
	public void save(SuppVehicle suppVehicle) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, suppVehicle.getSuppCode());
			insertStatement.setString(2, suppVehicle.getLicensePlate());
			insertStatement.setInt(3, suppVehicle.getVehicleTypeId());
			insertStatement.setDate(4, DateUtil.getCurrentDate());
			insertStatement.setString(5, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(SuppVehicle suppVehicle) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, suppVehicle.getVehicleTypeId());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "timotius");
			updateStatement.setInt(4, suppVehicle.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteAll(String suppCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where supp_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, suppCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
}
