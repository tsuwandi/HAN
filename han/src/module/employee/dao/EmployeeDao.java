package module.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.DateUtil;
import module.employee.model.Employee;

public class EmployeeDao {
	private DataSource dataSource;
	private PreparedStatement insertEmployeeStatement;
	private PreparedStatement updateEmployeeStatement;
	private PreparedStatement deleteEmployeeStatement;
	private PreparedStatement getAllEmployeeStatement;
	private PreparedStatement searchEmployee;
	private PreparedStatement getAllGender;
	private PreparedStatement getAllPosition;
	private PreparedStatement getAllDivision;
	private PreparedStatement getAllDepartment;
	private PreparedStatement getAllEmployeeStatus;
	private PreparedStatement getAllEmployeeType;
	private PreparedStatement getAllMarital;
	
	private String selectAllQuery = 
			"select employee_id, employee_name, employee_type_name, position_name, department_name, division_name, "
			+ "hire_date, address, city, birth_date, e.name, marital_name, employee_status_name"
			+ " from employee a join department b on a.department_id = b.department_id "
			+ "join ms_position c on b.department_id = c.department_id "
			+ "join division d on b.division_id = d.division_id "
			+ "join gender e on a.gender_id = e.gender_id "
			+ "join employee_type f on a.employee_type_id = f.employee_type_id "
			+ "join marital g on a.marital_id = g.marital_id "
			+ "join employee_status h on a.employee_status_id = h.employee_status_id"
			+ " where 1 = 1 "
			+ "order by employee_id asc ";
	
	private String searchQuery =
			"select employee_id, employee_name, employee_type_name, position_name, department_name, division_name, "
					+ "hire_date, address, city, birth_date, e.name, marital_name, employee_status_name"
					+ " from employee a join department b on a.department_id = b.department_id "
					+ "join ms_position c on b.department_id = c.department_id "
					+ "join division d on b.division_id = d.division_id "
					+ "join gender e on a.gender_id = e.gender_id "
					+ "join employee_type f on a.employee_type_id = f.employee_type_id "
					+ "join marital g on a.marital_id = g.marital_id "
					+ "join employee_status h on a.employee_status_id = h.employee_status_id"
					+ " where 1 = 1 and "
					+ "employee_id = ?, employee_name = ?, position_name = ?, department_name = ?, "
					+ "division_name = ?, hire_date = ?, address = ?, birth_date = ?, e.name = ?";
	
	private String genderQuery = "select * from gender";
	
	private String positionQuery = "select * from ms_position";
	
	private String divisionQuery = "select * from division";
	
	private String departmentQuery = "select * from department";
	
	private String employeeStatusQuery = "select * from employee_status";
	
	private String employeeTypeQuery = "select * from employee_type";
	
	private String maritalQuery = "select * from marital";
	
	private String insertEmployeeQuery = 
			"insert into employee(employee_id, employee_name, employee_type_id, address, city, "
			+ "birth_date, hire_date, email, phone_number, salary, department_id, position_id, "
			+ "marital_id, employee_status_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	

	public EmployeeDao(DataSource dataSource) throws SQLException{
		this.dataSource = dataSource;
	}
	
	public void save(Employee employee) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		
    		insertEmployeeStatement = con.prepareStatement(insertEmployeeQuery);
    		java.sql.Date sqlStartDate = new java.sql.Date(employee.getStartDate().getTime());
    		java.sql.Date sqlDob = new java.sql.Date(employee.getDob().getTime());
    		insertEmployeeStatement.setString(1, employee.getEmployeeId());
    		insertEmployeeStatement.setString(2, employee.getEmployeeName());
    		insertEmployeeStatement.setString(3, employee.getEmployeeTypeId());
    		insertEmployeeStatement.setString(4, employee.getAddress());
    		insertEmployeeStatement.setString(5, employee.getCity());
    		insertEmployeeStatement.setDate(6, sqlDob);
    		insertEmployeeStatement.setDate(7, sqlStartDate);
    		insertEmployeeStatement.setString(8, employee.getEmail());
    		insertEmployeeStatement.setString(9, employee.getPhone());
    		insertEmployeeStatement.setInt(10, employee.getSalary());
    		insertEmployeeStatement.setString(11, employee.getDepartmentId());
    		insertEmployeeStatement.setString(12, employee.getPositionId());
    		insertEmployeeStatement.setString(13, employee.getMaritalId());
    		insertEmployeeStatement.setString(14, "1");
    		insertEmployeeStatement.executeUpdate();
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public List<Employee> getAll() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllEmployeeStatement = con.prepareStatement(selectAllQuery);	
			ResultSet rs = getAllEmployeeStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employee.setEmployeeType(rs.getString("employee_type_name"));
				employee.setPosition(rs.getString("position_name"));
				employee.setDepartment(rs.getString("department_name"));
				employee.setDivision(rs.getString("division_name"));
				employee.setStartDate(rs.getDate("hire_date"));
				employee.setCity(rs.getString("city"));
				employee.setDob(rs.getDate("birth_date"));
				employee.setGender(rs.getString("name"));
				employee.setStatus(rs.getString("employee_status_name"));
				
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	public List<Employee> getAllGender() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllGender = con.prepareStatement(genderQuery);	
			ResultSet rs = getAllGender.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setGenderId(rs.getString("gender_id"));
				employee.setGender(rs.getString("name"));				
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	public List<Employee> getAllPosition() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllPosition = con.prepareStatement(positionQuery);	
			ResultSet rs = getAllPosition.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setPositionId(rs.getString("position_id"));
				employee.setPosition(rs.getString("position_name"));				
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	public List<Employee> getAllDivision() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllDivision = con.prepareStatement(divisionQuery);	
			ResultSet rs = getAllDivision.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setDivisionId(rs.getString("division_id"));
				employee.setDivision(rs.getString("division_name"));				
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	public List<Employee> getAllDepartment() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllDepartment = con.prepareStatement(departmentQuery);	
			ResultSet rs = getAllDepartment.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setDepartmentId(rs.getString("department_id"));
				employee.setDepartment(rs.getString("department_name"));				
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	public List<Employee> getAllEmployeeType() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllEmployeeType = con.prepareStatement(employeeTypeQuery);	
			ResultSet rs = getAllEmployeeType.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeTypeId(rs.getString("employee_type_id"));
				employee.setEmployeeType(rs.getString("employee_type_name"));			
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	public List<Employee> getAllMarital() throws SQLException{
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllMarital = con.prepareStatement(maritalQuery);	
			ResultSet rs = getAllMarital.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setMaritalId(rs.getString("marital_id"));
				employee.setMarital(rs.getString("marital_name"));
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return employees;
	}
	
	
	

}
