package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.EmployeePosition;

public class EmployeePositionDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from emp_position order by id desc limit 1";
	private String getAllQuery = "select * from emp_position where delete_date is null and delete_by is null";
	private String insertQuery = "insert into emp_position(employee_id, employee_name, npwp, ktp, ktp_address, address, origin_city, birth_date, email, phone_number, gender_id, marital_id, child_no, bank_name, bank_account_code, group_shift_code, photo, employee_status_id, input_date, input_by, edit_date, edit_by, delete_date, delete_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update emp_position set employee_name = ?, npwp = ?, ktp = ?, ktp_address = ?, address = ?, origin_city = ?, birth_date = ?, email = ?, phone_number = ?, gender_id = ?, marital_id = ?, child_no = ?, bank_name = ?, bank_account_code = ?, group_shift_code = ?, photo = ?, employee_status_id = ?, edit_date = ?, edit_by = ? WHERE employee_id = ?";
	private String deleteQuery = "update emp_position set delete_date = ?, delete_by = ? where id = ?";
	
	public EmployeePositionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmployeePosition> getAllData(String query) {
		return null;
	}
	
	public void insert(EmployeePosition employeePosition) {
		
	}
	
	public void update(EmployeePosition employeePosition) {
		
	}
	
	public void delete(EmployeePosition employeePosition) {
		
	}
	
	public Integer getLastId() {
		List<EmployeePosition> employeePositions = null;
		
		try {
			employeePositions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				EmployeePosition employeePosition = new EmployeePosition();
				employeePosition.setId("id");

				employeePositions.add(employeePosition);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return employeePositions.size() < 1 ? 1 : Integer.parseInt(employeePositions.get(0).getId().substring(3))+1;
	}
}
