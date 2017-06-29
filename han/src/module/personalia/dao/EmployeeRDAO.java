package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.EmployeeR;
import module.production.model.Production;

public class EmployeeRDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	String getAllQuery = "SELECT * FROM employee WHERE delete_date IS NULL";
	
	public EmployeeRDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<EmployeeR> getAll() throws SQLException{
		List<EmployeeR> employees = new ArrayList<>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				EmployeeR employee = new EmployeeR();
				employee.setId(rs.getInt("id"));
				employee.setEmpCode(rs.getString("emp_code"));
				employee.setName(rs.getString("name"));
				employee.setNpwp(rs.getString("npwp"));
				employee.setCurrentAddress(rs.getString("current_address"));
				employee.setCurrentZipCode(rs.getString("current_zip_code"));
				employee.setCurrentCity(rs.getString("current_city"));
				employee.setKtp(rs.getString("ktp"));
				employee.setKtpAddress(rs.getString("ktp_address"));
				employee.setKtpZipCode(rs.getString("ktp_zip_code"));
				employee.setTotalChild(rs.getInt("total_child"));
				employee.setBankCode(rs.getString("bank_code"));
				employee.setBankAcctNo(rs.getString("bank_acctno"));
				employee.setGroupShiftID(rs.getString("group_shift_id"));
				employee.setBirthDate(rs.getDate("birth_date"));
				employee.setEmergencyContact(rs.getString("emergency_contact"));
				employee.setEmergencyPhone(rs.getString("emergency_phone"));
				employee.setEmail(rs.getString("email"));
				employee.setPhone(rs.getString("phone"));
				employee.setGenderID(rs.getString("gender_id"));
				employee.setPositionID(rs.getString("position_id"));
				employee.setMaritalID(rs.getString("marital_id"));
				employee.setEmployeeStatusID(rs.getString("employee_status_id"));
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		
		return employees;
	}
	
}
