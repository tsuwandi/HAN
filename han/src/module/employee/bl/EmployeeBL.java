package module.employee.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.employee.dao.EmployeeDAO;
import module.employee.model.Employee;

public class EmployeeBL {
	private DataSource dataSource;

	public EmployeeBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(Employee employee) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			new EmployeeDAO(con).save(employee);
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public List<Employee> getAll() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllGender() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllGender();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllMarital() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllMarital();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllPosition() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllPosition();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllDepartment() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllDepartment();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllDivision() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllDivision();
		} finally {
			con.close();
		}
	}

	public List<Employee> getAllEmployeeType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllEmployeeType();
		} finally {
			con.close();
		}
	}
}
