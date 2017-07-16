package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.Attendance;
import module.util.DateUtil;

public class AttendanceDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from attendance order by id desc limit 1";
	private String getAllQuery = "select * from attendance where delete_date is null ";
	private String insertQuery = "insert into attendance (pin, nik, employee_name, attendance_date, attendance_time, "
			+ "machine_serial_number, machine_name, verification_type, mode, update_mode, branch, department, role, input_date, inputed_by) "
			+ "values (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update attendance set pin=?, nik=?, employee_name=?, attendance_date=?, attendance_time=?, "
			+ " machine_serial_number=?, machine_name=?, verification_type=?, mode=?, update_mode=?, branch=?, department=?, role=?, "
			+ " edit_date = ?, edited_by = ? where id = ?";
	private String deleteQuery = "update attendance set delete_date = ?, deleted_by = ? where id = ?";

	public AttendanceDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Attendance> getAllData(String query){
		List<Attendance> attendances = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);
			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				Attendance attendance = new Attendance();
				attendance.setId(resultSet.getInt("id"));
				attendance.setPin(resultSet.getInt("pin"));
				attendance.setNik(resultSet.getInt("nik"));
				attendance.setEmployeeName(resultSet.getString("employee_name"));
				attendance.setAttendanceDate(resultSet.getDate("attendance_date"));
				attendance.setAttendanceTime(resultSet.getString("attendance_time"));
				attendance.setMachineSerialNumber(resultSet.getString("machine_serial_number"));
				attendance.setMachineName(resultSet.getString("machine_name"));
				attendance.setVerificationType(resultSet.getString("verification_type"));
				attendance.setMode(resultSet.getString("mode"));
				attendance.setUpdateMode(resultSet.getString("update_mode"));
				attendance.setBranchOffice(resultSet.getString("branch"));
				attendance.setDepartment(resultSet.getString("department"));
				attendance.setEmployeeRole(resultSet.getString("role"));
				attendances.add(attendance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attendances;
	}

	public void insert(Attendance attendance) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, attendance.getPin());
			insertStatement.setInt(2, attendance.getNik());
			insertStatement.setString(3, attendance.getEmployeeName());
			insertStatement.setDate(4, DateUtil.toDate(attendance.getAttendanceDate()));
			insertStatement.setString(5, attendance.getAttendanceTime());
			insertStatement.setString(6, attendance.getMachineSerialNumber());
			insertStatement.setString(7, attendance.getMachineName());
			insertStatement.setString(8, attendance.getVerificationType());
			insertStatement.setString(9, attendance.getMode());
			insertStatement.setString(10, attendance.getUpdateMode());
			insertStatement.setString(11, attendance.getBranchOffice());
			insertStatement.setString(12, attendance.getDepartment());
			insertStatement.setString(13, attendance.getEmployeeRole());
			insertStatement.setDate(14, DateUtil.toDate(new Date()));
			insertStatement.setString(15, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Attendance attendance) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, attendance.getPin());
			updateStatement.setInt(2, attendance.getNik());
			updateStatement.setString(3, attendance.getEmployeeName());
			updateStatement.setDate(4, DateUtil.toDate(attendance.getAttendanceDate()));
			updateStatement.setString(5, attendance.getAttendanceTime());
			updateStatement.setString(6, attendance.getMachineSerialNumber());
			updateStatement.setString(7, attendance.getMachineName());
			updateStatement.setString(8, attendance.getVerificationType());
			updateStatement.setString(9, attendance.getMode());
			updateStatement.setString(10, attendance.getUpdateMode());
			updateStatement.setString(11, attendance.getBranchOffice());
			updateStatement.setString(12, attendance.getDepartment());
			updateStatement.setString(13, attendance.getEmployeeRole());
			updateStatement.setDate(14, DateUtil.toDate(new Date()));
			updateStatement.setString(15, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(16, attendance.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Attendance attendance) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(attendance.getDeleteDate()));
			deleteStatement.setString(2, attendance.getDeleteBy());
			deleteStatement.setInt(3, attendance.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<Attendance> attendances = null;
		
		try {
			attendances = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				Attendance attendance = new Attendance();
				attendance.setId(resultSet.getInt("id"));

				attendances.add(attendance);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return attendances.size() < 1 ? 1 :attendances.get(0).getId()+1;
	}
}