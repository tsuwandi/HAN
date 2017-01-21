package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.EmpPosition;
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
	private String insertQuery = "insert into employee (emp_code, name, current_address, current_city, npwp, ktp, ktp_address, ktp_city, hometown, total_child, bank_code, bank_account_number, group_shift_code, birth_date, email, phone, salary, gender_id, marital_id, emp_status_id, input_date, input_by, edit_date, edit_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update employee set emp_code = ?, name = ?, current_address = ?, current_city = ?, npwp = ?, ktp = ?, ktp_address = ?, ktp_city = ?, hometown = ?, total_child = ?, bank_code = ?, bank_account_number = ?, group_shift_code = ?, birth_date = ?, email = ?, phone = ?, salary = ?, gender_id = ?, marital_id = ?, emp_status_id = ?, edit_date = ?, edit_by = ? where id = ?";
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
				employee.setId(resultSet.getInt("id"));
				employee.setEmpCode(resultSet.getString("emp_code"));
				employee.setName(resultSet.getString("name"));
				employee.setCurrentAddress(resultSet.getString("current_address"));
				employee.setCurrentCity(resultSet.getString("current_city"));
				employee.setNpwp(resultSet.getString("npwp"));
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
				employee.setGenderId(resultSet.getInt("gender_id"));
				employee.setMaritalId(resultSet.getInt("marital_id"));
				employee.setEmplStatusId(resultSet.getString("emp_status_id"));
				employee.setInputDate(resultSet.getDate("input_date"));
				employee.setInputBy(resultSet.getString("input_by"));
				employee.setEditDate(resultSet.getDate("edit_date"));
				employee.setEditBy(resultSet.getString("edit_by"));
				employee.setDeleteDate(resultSet.getDate("delete_date"));
				employee.setDeleteBy(resultSet.getString("delete_by"));
				List<EmpPosition> empPositions = ServiceFactory.getPersonaliaBL().getEmpPositions(" and employee_id = '"+employee.getEmpCode()+"'");
				for (EmpPosition empPosition : empPositions) {
					if(empPosition.getEndDate()==null) {
						employee.setEmployeeType(empPosition.getEmployeeType());
						employee.setMsPosition(empPosition.getMsPosition());
						if(empPosition.getMsPosition()!=null) {
							employee.setDivision(employee.getMsPosition().getDivision());
							employee.setDepartment(employee.getMsPosition().getDepartment());
						}
					}
				}
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
			//insertStatement.setString(1, employee.getId());
			insertStatement.setString(1, employee.getEmpCode());
			insertStatement.setString(2, employee.getName());
			insertStatement.setString(3, employee.getCurrentAddress());
			insertStatement.setString(4, employee.getCurrentCity());
			insertStatement.setString(5, employee.getNpwp());
			insertStatement.setString(6, employee.getKtp());
			insertStatement.setString(7, employee.getKtpAddress());
			insertStatement.setString(8, employee.getKtpCity());
			insertStatement.setString(9, employee.getHometown());
			insertStatement.setInt(10, employee.getTotalChild());
			insertStatement.setString(11, employee.getBankCode());
			insertStatement.setString(12, employee.getBankAccountNumber());
			insertStatement.setString(13, employee.getGroupShiftCode());
			insertStatement.setDate(14, DateUtil.toDate(employee.getBirthDate()));
			insertStatement.setString(15, employee.getEmail());
			insertStatement.setString(16, employee.getPhone());
			insertStatement.setBigDecimal(17, employee.getSalary());
			insertStatement.setInt(18, employee.getGenderId());
			insertStatement.setInt(19, employee.getMaritalId());
			insertStatement.setString(20, employee.getEmplStatusId());
			insertStatement.setDate(21, DateUtil.toDate(employee.getInputDate()));
			insertStatement.setString(22, employee.getInputBy());
			insertStatement.setDate(23, DateUtil.toDate(employee.getEditDate()));
			insertStatement.setString(24, employee.getEditBy());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Employee employee) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, employee.getEmpCode());
			updateStatement.setString(2, employee.getName());
			updateStatement.setString(3, employee.getCurrentAddress());
			updateStatement.setString(4, employee.getCurrentCity());
			updateStatement.setString(5, employee.getNpwp());
			updateStatement.setString(6, employee.getKtp());
			updateStatement.setString(7, employee.getKtpAddress());
			updateStatement.setString(8, employee.getKtpCity());
			updateStatement.setString(9, employee.getHometown());
			updateStatement.setInt(10, employee.getTotalChild());
			updateStatement.setString(11, employee.getBankCode());
			updateStatement.setString(12, employee.getBankAccountNumber());
			updateStatement.setString(13, employee.getGroupShiftCode());
			updateStatement.setDate(14, DateUtil.toDate(employee.getBirthDate()));
			updateStatement.setString(15, employee.getEmail());
			updateStatement.setString(16, employee.getPhone());
			updateStatement.setBigDecimal(17, employee.getSalary());
			updateStatement.setInt(18, employee.getGenderId());
			updateStatement.setInt(19, employee.getMaritalId());
			updateStatement.setString(20, employee.getEmplStatusId());
			updateStatement.setDate(21, DateUtil.toDate(employee.getEditDate()));
			updateStatement.setString(22, employee.getEditBy());
			updateStatement.setInt(23, employee.getId());

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
			deleteStatement.setInt(3, employee.getId());

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
				employee.setId(resultSet.getInt("id"));

				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		//return employees.size() < 1 ? 1 : Integer.parseInt(employees.get(0).getId().substring(3))+1;
		return employees.size() < 1 ? 1 : employees.get(0).getId()+1;
	}
}