package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Attendance;
import module.personalia.model.Division;
import module.util.DateUtil;

public class AttendanceDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from attendance order by id desc limit 1";
	private String getAllQuery = "select * from attendance where delete_date is null and delete_by is null";
	private String insertQuery = "insert into attendance (id, name, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update attendance set name = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update attendance set delete_date = ?, delete_by = ? where id = ?";

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
				attendance.setAttendanceCode(resultSet.getString("attendance_code"));
				attendance.setPin(resultSet.getInt("pin"));
				attendance.setNik(resultSet.getInt("nik"));
				attendance.setEmployeeName(resultSet.getString("employee_name"));
				attendance.setAttendanceDate(resultSet.getDate("attendance_date"));
				attendance.setAttendanceTime(resultSet.getDate("attendance_time"));
				attendance.setMachineSerialNumber(resultSet.getString("machine_serial_number"));
				attendance.setMachineName(resultSet.getString("machine_name"));
				attendance.setVerificationType(resultSet.getString("verification_type"));
				attendance.setMode(resultSet.getString("mode"));
				attendance.setUpdateMode(resultSet.getString("update_mode"));
				attendance.setDepartment(resultSet.getString("department"));
				attendance.setRole(resultSet.getString("role"));
				attendance.setDocumentReference(resultSet.getString("document_reference"));
				attendance.setInputDate(resultSet.getDate("input_date"));
				attendance.setInputBy(resultSet.getString("input_by"));
				attendance.setEditDate(resultSet.getDate("edit_date"));
				attendance.setDeleteDate(resultSet.getDate("delete_date"));
				attendance.setDeleteBy(resultSet.getString("delete_by"));
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

			insertStatement.setInt(1, attendance.getId());
			insertStatement.setString(2, attendance.getAttendanceCode());
			insertStatement.setInt(3, attendance.getPin());
			insertStatement.setInt(4, attendance.getNik());
			insertStatement.setString(5, attendance.getEmployeeName());
			insertStatement.setDate(6, DateUtil.toDate(attendance.getAttendanceDate()));
			insertStatement.setDate(7, DateUtil.toDate(attendance.getAttendanceTime()));
			insertStatement.setString(8, attendance.getMachineSerialNumber());
			insertStatement.setString(9, attendance.getMachineName());
			insertStatement.setString(10, attendance.getVerificationType());
			insertStatement.setString(11, attendance.getMode());
			insertStatement.setString(12, attendance.getUpdateMode());
			insertStatement.setString(13, attendance.getDepartment());
			insertStatement.setString(14, attendance.getRole());
			insertStatement.setString(15, attendance.getDocumentReference());
			insertStatement.setDate(3, DateUtil.toDate(attendance.getInputDate()));
			insertStatement.setString(4, attendance.getInputBy());
			insertStatement.setDate(5, DateUtil.toDate(attendance.getEditDate()));
			insertStatement.setString(6, attendance.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Division division) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, division.getName());
			updateStatement.setDate(2, DateUtil.toDate(division.getEditDate()));
			updateStatement.setString(3, division.getEditBy());
			updateStatement.setString(4, division.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Division division) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(division.getDeleteDate()));
			deleteStatement.setString(2, division.getDeleteBy());
			deleteStatement.setString(3, division.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<Division> divisions = null;
		
		try {
			divisions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				Division division = new Division();
				division.setId(resultSet.getString("id"));
				division.setName(resultSet.getString("name"));

				divisions.add(division);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return divisions.size() < 1 ? 1 : Integer.parseInt(divisions.get(0).getId().substring(3))+1;
	}
}
