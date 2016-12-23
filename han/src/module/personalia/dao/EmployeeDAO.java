package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import module.personalia.model.Division;
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
	private String insertQuery = "insert into employee(employee_id, employee_name, npwp, ktp, ktp_address, address, origin_city, birth_date, email, phone_number, gender_id, marital_id, child_no, bank_name, bank_account_code, group_shift_code, photo, employee_status_id, input_date, input_by, edit_date, edit_by, delete_date, delete_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update employee set employee_name = ?, npwp = ?, ktp = ?, ktp_address = ?, address = ?, origin_city = ?, birth_date = ?, email = ?, phone_number = ?, gender_id = ?, marital_id = ?, child_no = ?, bank_name = ?, bank_account_code = ?, group_shift_code = ?, photo = ?, employee_status_id = ?, edit_date = ?, edit_by = ? WHERE employee_id = ?";
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
				employee.setEmployeeId(resultSet.getString("employee_id"));
				employee.setEmployeeName(resultSet.getString("employee_name"));
				employee.setNpwp(resultSet.getString("npwp"));
				employee.setKtp(resultSet.getString("ktp"));
				employee.setKtpAddress(resultSet.getString("ktp_address"));
				employee.setAddress(resultSet.getString("address"));
				employee.setOriginCity(resultSet.getString("origin_city"));
				employee.setBirthDate(resultSet.getDate("birth_date"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhoneNumber(resultSet.getString("phone_number"));
				employee.setGenderId(resultSet.getString("gender_id"));
				employee.setMaritalId(resultSet.getString("marital_id"));
				employee.setChildNo(resultSet.getInt("child_no"));
				employee.setBankName(resultSet.getString("bank_name"));
				employee.setBankAccountNo(resultSet.getString("bank_account_code"));
				employee.setGroupShiftCode(resultSet.getString("group_shift_code"));
				employee.setPhoto(resultSet.getString("photo"));
				employee.setEmployeeStatusId(resultSet.getString("employee_status_id"));

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

			insertStatement.setString(1, employee.getEmployeeId());
			insertStatement.setString(2, employee.getEmployeeName());
			insertStatement.setString(3, employee.getNpwp());
			insertStatement.setString(4, employee.getKtp());
			insertStatement.setString(5, employee.getKtpAddress());
			insertStatement.setString(6, employee.getAddress());
			insertStatement.setString(7, employee.getOriginCity());
			insertStatement.setDate(8, DateUtil.toDate(employee.getBirthDate()));
			insertStatement.setString(9, employee.getEmail());
			insertStatement.setString(10, employee.getPhoneNumber());
			insertStatement.setString(11, employee.getGenderId());
			insertStatement.setString(12, employee.getMaritalId());
			insertStatement.setInt(13, employee.getChildNo());
			insertStatement.setString(14, employee.getBankName());
			insertStatement.setString(15, employee.getBankAccountNo());
			insertStatement.setString(16, employee.getGroupShiftCode());
			insertStatement.setString(17, employee.getPhoto());
			insertStatement.setString(18, employee.getEmployeeStatusId());
			insertStatement.setDate(19, DateUtil.toDate(employee.getInputDate()));
			insertStatement.setString(20, employee.getInputBy());
			insertStatement.setDate(21, DateUtil.toDate(employee.getEditDate()));
			insertStatement.setString(22, employee.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Employee employee) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, employee.getEmployeeName());
			updateStatement.setString(2, employee.getNpwp());
			updateStatement.setString(3, employee.getKtp());
			updateStatement.setString(4, employee.getKtpAddress());
			updateStatement.setString(5, employee.getAddress());
			updateStatement.setString(6, employee.getOriginCity());
			updateStatement.setDate(7, DateUtil.toDate(employee.getBirthDate()));
			updateStatement.setString(8, employee.getEmail());
			updateStatement.setString(9, employee.getPhoneNumber());
			updateStatement.setString(10, employee.getGenderId());
			updateStatement.setString(11, employee.getMaritalId());
			updateStatement.setInt(12, employee.getChildNo());
			updateStatement.setString(13, employee.getBankName());
			updateStatement.setString(14, employee.getBankAccountNo());
			updateStatement.setString(15, employee.getGroupShiftCode());
			updateStatement.setString(16, employee.getPhoto());
			updateStatement.setString(17, employee.getEmployeeStatusId());
			updateStatement.setDate(18, DateUtil.toDate(employee.getEditDate()));
			updateStatement.setString(19, employee.getEditBy());
			updateStatement.setString(20, employee.getEmployeeId());

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
			deleteStatement.setString(3, employee.getEmployeeId());

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
				employee.setEmployeeId(resultSet.getString("employee_id"));

				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return employees.size() < 1 ? 1 : Integer.parseInt(employees.get(0).getEmployeeId().substring(3))+1;
	}
}