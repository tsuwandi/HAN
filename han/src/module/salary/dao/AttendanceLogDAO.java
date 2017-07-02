package module.salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.salary.model.AttendanceLog;
import module.util.DateUtil;

public class AttendanceLogDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateStatement;
	
	private String getAllQuery = "SELECT id, period, cycle, nik, attendance_date, attendance_time, attendance_out, shift_id, shift_in, shift_out, status_in, status_out, lembur "
			+ "FROM attendance_log WHERE deleted_date IS NULL";
	private String insertQuery = "INSERT INTO attendance_log (period, cycle, nik, attendance_date, attendance_time, attendance_out, shift_id, shift_in, shift_out, status_in, status_out, lembur, input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String deleteQuery = "UPDATE attendance_log SET deleted_date = ? , delete_by=? WHERE id=?";
	private String updateQuery = "UPDATE attendance_log SET "
			+ "period=?, cycle=?, nik=?, attendance_date=?, attendance_time=?, attendance_out=?, "
			+ "shift_id=?, shift_in=?, shift_out=?, status_in=?, status_out=?, lembur=?,edit_by=?, edited_date=?  "
			+ "WHERE id=?";
	
	public AttendanceLogDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<AttendanceLog> getAll(String query) throws SQLException {
		List<AttendanceLog> attendanceLogs = new ArrayList<AttendanceLog>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				AttendanceLog attendanceLog = new AttendanceLog();
				attendanceLog.setId(rs.getInt("id"));
				attendanceLog.setPeriod(rs.getInt("period"));
				attendanceLog.setCycle(rs.getInt("cycle"));
				attendanceLog.setNik(rs.getString("nik"));
				attendanceLog.setDate(rs.getDate("attendance_date"));
				attendanceLog.setAttendanceTime(rs.getString("attendance_time"));
				attendanceLog.setAttendanceOut(rs.getString("attendance_out"));
				attendanceLog.setShiftId(rs.getInt("shift_id"));
				attendanceLog.setShiftIn(rs.getString("shift_in"));
				attendanceLog.setShiftOut(rs.getString("shift_out"));
				attendanceLog.setStatusOut(rs.getString("status_in"));
				attendanceLog.setStatusOut(rs.getString("status_out"));
				attendanceLog.setLembur(rs.getInt("lembur"));
				attendanceLogs.add(attendanceLog);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return attendanceLogs;
	}
	
	public void save(AttendanceLog attendanceLog) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, attendanceLog.getPeriod());
			insertStatement.setInt(2, attendanceLog.getCycle());
			insertStatement.setString(3, attendanceLog.getNik());
			insertStatement.setDate(4, DateUtil.toDate(attendanceLog.getDate()));
			insertStatement.setString(5, attendanceLog.getAttendanceTime());
			insertStatement.setString(6, attendanceLog.getAttendanceOut());
			insertStatement.setInt(7, attendanceLog.getShiftId());
			insertStatement.setString(8, attendanceLog.getShiftIn());
			insertStatement.setString(9, attendanceLog.getShiftOut());
			insertStatement.setString(10, attendanceLog.getStatusIn());
			insertStatement.setString(11, attendanceLog.getStatusOut());
			insertStatement.setInt(12, attendanceLog.getLembur());
			insertStatement.setString(13, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(14, DateUtil.toDate(new java.util.Date()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(AttendanceLog attendanceLog) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, attendanceLog.getPeriod());
			updateStatement.setInt(2, attendanceLog.getCycle());
			updateStatement.setString(3, attendanceLog.getNik());
			updateStatement.setDate(4, DateUtil.toDate(attendanceLog.getDate()));
			updateStatement.setString(5, attendanceLog.getAttendanceTime());
			updateStatement.setString(6, attendanceLog.getAttendanceOut());
			updateStatement.setInt(7, attendanceLog.getShiftId());
			updateStatement.setString(8, attendanceLog.getShiftIn());
			updateStatement.setString(9, attendanceLog.getShiftOut());
			updateStatement.setString(10, attendanceLog.getStatusIn());
			updateStatement.setString(11, attendanceLog.getStatusOut());
			updateStatement.setInt(12, attendanceLog.getLembur());
			updateStatement.setString(13, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(14, DateUtil.toDate(new java.util.Date()));
			updateStatement.setInt(15, attendanceLog.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(AttendanceLog attendanceLog) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.toDate(new java.util.Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, attendanceLog.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
}
