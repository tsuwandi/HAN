package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
import module.employeemanagement.model.Employee;
import module.util.DateUtil;

public class EmployeeDAO {
	private Connection connection;

	private PreparedStatement getLastCodeStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastCodeQuery = "select emp_code from employee order by id desc limit 1";
	private String getAllQuery = "select * from employee where deleted_date is null";
	private String insertQuery = "insert into employee (emp_code, fname, lname, current_address, current_city, npwp, ktp, ktp_address, total_child, birth_date, email, phone,  gender_id, marital_id, emergency_contact, emergency_phone, emergency_relation,bank_name,bank_account,rfid,image, input_date, input_by,status_id)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update employee set emp_code = ?, fname = ?, lname=?, current_address = ?, current_city = ?, npwp = ?, ktp = ?, "
			+ "ktp_address = ?, total_child = ?, birth_date = ?, email = ?, phone = ?, gender_id = ?, marital_id = ?,"
			+ " emergency_contact=?, emergency_phone=?, emergency_relation=?,bank_name=?, bank_account=?,rfid=?,image=?, edit_date = ?, edited_by = ? , status_id = ? where id = ?";
	private String deleteQuery = "update employee set delete_date = ?, deleted_by = ? where id = ?";
	
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
				employee.setFname(resultSet.getString("fname"));
				employee.setLname(resultSet.getString("lname"));
				employee.setCurrentAddress(resultSet.getString("current_address"));
				employee.setCurrentCity(resultSet.getString("current_city"));
				employee.setNpwp(resultSet.getString("npwp"));
				employee.setKtp(resultSet.getString("ktp"));
				employee.setKtpAddress(resultSet.getString("ktp_address"));
				employee.setTotalChild(resultSet.getInt("total_child"));
				employee.setBirthDate(resultSet.getDate("birth_date"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setGenderId(resultSet.getInt("gender_id"));
				employee.setMaritalId(resultSet.getInt("marital_id"));
				employee.setEmergencyContact(resultSet.getString("emergency_contact"));
				employee.setEmergencyPhone(resultSet.getString("emergency_phone"));
				employee.setEmergencyRelation(resultSet.getString("emergency_relation"));
				employee.setBankAccount(resultSet.getString("bank_account"));
				employee.setBankName(resultSet.getString("bank_name"));
				employee.setRfid(resultSet.getLong("rfid"));
				employee.setImage(resultSet.getString("image"));
				employee.setStatus(resultSet.getInt("status_id"));
				
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
			insertStatement.setString(1, employee.getEmpCode());
			insertStatement.setString(2, employee.getFname());
			insertStatement.setString(3, employee.getLname());
			insertStatement.setString(4, employee.getCurrentAddress());
			insertStatement.setString(5, employee.getCurrentCity());
			insertStatement.setString(6, employee.getNpwp());
			insertStatement.setString(7, employee.getKtp());
			insertStatement.setString(8, employee.getKtpAddress());
			insertStatement.setInt(9, employee.getTotalChild());
			insertStatement.setDate(10, DateUtil.toDate(employee.getBirthDate()));
			insertStatement.setString(11, employee.getEmail());
			insertStatement.setString(12, employee.getPhone());
			insertStatement.setInt(13, employee.getGenderId());
			insertStatement.setInt(14, employee.getMaritalId());
			insertStatement.setString(15, employee.getEmergencyContact());
			insertStatement.setString(16, employee.getEmergencyPhone());
			insertStatement.setString(17, employee.getEmergencyRelation());
			insertStatement.setString(18, employee.getBankName());
			insertStatement.setString(19, employee.getBankAccount());
			insertStatement.setLong(20, employee.getRfid());
			insertStatement.setString(21, employee.getImage());
			insertStatement.setDate(22, DateUtil.toDate(new Date()));
			insertStatement.setString(23, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setInt(24, employee.getStatus());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Employee employee) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, employee.getEmpCode());
			updateStatement.setString(2, employee.getFname());
			updateStatement.setString(3, employee.getLname());
			updateStatement.setString(4, employee.getCurrentAddress());
			updateStatement.setString(5, employee.getCurrentCity());
			updateStatement.setString(6, employee.getNpwp());
			updateStatement.setString(7, employee.getKtp());
			updateStatement.setString(8, employee.getKtpAddress());
			updateStatement.setInt(9, employee.getTotalChild());
			updateStatement.setDate(10, DateUtil.toDate(employee.getBirthDate()));
			updateStatement.setString(11, employee.getEmail());
			updateStatement.setString(12, employee.getPhone());
			updateStatement.setInt(13, employee.getGenderId());
			updateStatement.setInt(14, employee.getMaritalId());
			updateStatement.setString(15, employee.getEmergencyContact());
			updateStatement.setString(16, employee.getEmergencyPhone());
			updateStatement.setString(17, employee.getEmergencyRelation());
			updateStatement.setString(18, employee.getBankName());
			updateStatement.setString(19, employee.getBankAccount());
			updateStatement.setLong(20, employee.getRfid());
			updateStatement.setString(21, employee.getImage());
			updateStatement.setDate(22, DateUtil.toDate(new Date()));
			updateStatement.setString(23, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(24, employee.getStatus());
			updateStatement.setInt(25, employee.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Employee employee) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(new Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, employee.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastCode() throws SQLException{
		String lastCode = null;
		try {
			getLastCodeStatment = connection.prepareStatement(getLastCodeQuery);
			ResultSet rs = getLastCodeStatment.executeQuery();
			if(rs.next()) lastCode =  rs.getString("emp_code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
	
	
}
