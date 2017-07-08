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
	
	String query = "SELECT emp.* ,struc.position_id, emppos.emp_structure_id, struc.org_value_id, org.value, org.id, sd.shift_id "+
					"FROM employee emp"+
					"INNER JOIN emp_position emppos ON emppos.employee_id = emp.id "+
					"INNER JOIN emp_structure struc ON struc.id = emppos.emp_structure_id "+
					"INNER JOIN org_structure_value org ON org.id = struc.org_value_id "+
					"INNER JOIN shift_department sd ON dept_id = struc.org_value_id ";

	
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
				employee.setfName(rs.getString("fname"));
				employee.setlName(rs.getString("lname"));
				employee.setNpwp(rs.getString("npwp"));
				employee.setCurrentAddress(rs.getString("current_address"));
				employee.setCurrentZipCode(rs.getString("current_zip_code"));
				employee.setCurrentCity(rs.getString("current_city"));
				employee.setKtp(rs.getString("ktp"));
				employee.setKtpAddress(rs.getString("ktp_address"));
				employee.setKtpZipCode(rs.getString("ktp_zip_code"));
				employee.setKtpCity(rs.getString("ktp_city"));
				employee.setTotalChild(rs.getInt("total_child"));
				employee.setGroupShiftID(rs.getString("group_shift_id"));
				employee.setBirthDate(rs.getDate("birth_date"));
				employee.setEmergencyContact(rs.getString("emergency_contact"));
				employee.setEmergencyPhone(rs.getString("emergency_phone"));
				employee.setEmail(rs.getString("email"));
				employee.setPhone(rs.getString("phone"));
				employee.setMobile(rs.getString("mobile"));
				employee.setGenderID(rs.getString("gender_id"));
				employee.setPositionID(rs.getInt("position_id"));
				employee.setMaritalID(rs.getString("marital_id"));
				employee.setEmpStructureID(rs.getInt("emp_structure_id"));
				employee.setShiftId(rs.getInt("shift_id"));
				employee.setOrgStructureID(rs.getInt("org_value_id"));
				employee.setOrgValue(rs.getString("value"));
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		
		return employees;
	}
	
}
