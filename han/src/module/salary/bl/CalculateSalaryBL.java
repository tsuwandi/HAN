package module.salary.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.DialogBox;
import module.salary.dao.AttendanceLogDAO;
import module.salary.model.AttendanceLog;

public class CalculateSalaryBL {
	AttendanceLogDAO attendanceLogDAO;
	
	public CalculateSalaryBL(DataSource datasource){
		Connection con=null;
		try {
			con = datasource.getConnection();
			attendanceLogDAO = new AttendanceLogDAO(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<AttendanceLog> getAttendanceLog(String query){
		try {
			return attendanceLogDAO.getAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void save(AttendanceLog attendanceLog){
		try {
			attendanceLogDAO.save(attendanceLog);
		} catch (SQLException e) {
			e.printStackTrace();
			DialogBox.showError(e.getMessage());
		}
	}
	
	public void update(AttendanceLog attendanceLog){
		try {
			attendanceLogDAO.update(attendanceLog);
		} catch (SQLException e) {
			e.printStackTrace();
			DialogBox.showError(e.getMessage());
		}
	}
}
