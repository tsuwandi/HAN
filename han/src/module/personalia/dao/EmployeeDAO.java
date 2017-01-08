package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Employee;
import module.util.DateUtil;

public class EmployeeDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from employee order by id desc limit 1";
	private String getAllQuery = "select * from employee where delete_date is null and delete_by is null";
	private String insertQuery = "insert into employee (id, emp_code, name, emp_type_id, current_address, current_city, ktp, ktp_address, ktp_city, hometown, total_child, bank_code, bank_account_number, group_shift_code, birth_date, email, phone, salary, department_id, gender_id, position_id, marital_id, emp_status_id, input_date, input_by, edit_date, edit_by, delete_date, delete_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update employee set emp_code = ?, name = ?, emp_type_id = ?, current_address = ?, current_city = ?, ktp = ?, ktp_address = ?, ktp_city = ?, hometown = ?, total_child = ?, bank_code = ?, bank_account_number = ?, group_shift_code = ?, birth_date = ?, email = ?, phone = ?, salary = ?, department_id = ?, gender_id = ?, position_id = ?, marital_id = ?, emp_status_id = ?, edit_date = ?, edit_by = ?, delete_date = ?, delete_by = ? where id = ?;";
	private String deleteQuery = "update employee set delete_date = ?, delete_by = ? where id = ?";
	
	public EmployeeDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Employee> getAllData(String query){
		List<Employee> employees = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getString("id"));
				employee.setEmpCode(resultSet.getString("emp_code"));
				employee.setName(resultSet.getString("name"));
				employee.setEmpTypeId(resultSet.getString("emp_type_id"));
				employee.setCurrentAddress(resultSet.getString("current_address"));
				employee.setCurrentCity(resultSet.getString("current_city"));
				employee.setKtp(resultSet.getString("ktp"));
				employee.setKtpAddress(resultSet.getString("ktp_address"));
				employee.setKtpCity(resultSet.getString("ktp_city"));
				employee.setHometown(resultSet.getString("hometown"));
				employee.setTotalChild(resultSet.getInt("total_child"));
				employee.setBankCode(resultSet.getString("bank_code"));
				employee.setBankAccountNumber(resultSet.getString("bank_account_number"));
				employee.setGroupShiftCode(resultSet.getString("group_shift_code"));
				employee.setBirthDate(resultSet.getDate("birth_date"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setSalary(resultSet.getBigDecimal("salary"));
				employee.setDepartmentId(resultSet.getString("department_id"));
				employee.setGenderId(resultSet.getString("gender_id"));
				employee.setPositionId(resultSet.getString("posisiton_id"));
				employee.setMaritalId(resultSet.getString("marital_id"));
				employee.setEmplStatusId(resultSet.getString("emp_status_id"));
				employee.setInputDate(resultSet.getDate("input_date"));
				employee.setInputBy(resultSet.getString("input_by"));
				employee.setEditDate(resultSet.getDate("edit_date"));
				employee.setEditBy(resultSet.getString("edit_by"));
				employee.setDeleteDate(resultSet.getDate("delete_date"));
				employee.setDeleteBy(resultSet.getString("delete_by"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public void insert(Employee employee) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Employee employee) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Employee employee) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(employee.getDeleteDate()));
			deleteStatement.setString(2, employee.getDeleteBy());
			deleteStatement.setString(3, employee.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getLastId() {
		List<Employee> employees = null;
		
		try {
			employees = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId("id");

				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return employees.size() < 1 ? 1 : Integer.parseInt(employees.get(0).getId().substring(3))+1;
	}
}