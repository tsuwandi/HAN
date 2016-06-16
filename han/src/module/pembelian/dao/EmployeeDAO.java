package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Employee;
import module.pembelian.model.PicDocking;

public class EmployeeDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;
	private PreparedStatement getEmpByCodeStatement;

	private String getAllQuery = "SELECT employee_id, employee_type_id, employee_name" + " FROM employee WHERE position_id=? ";
	private String getEmpByCodeQuery = "SELECT employee_id, employee_type_id, employee_name" + " FROM employee WHERE employee_id =? ";
	
	public EmployeeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<PicDocking> getEmployeeBasedOnPosition(String pos) throws SQLException {
		Connection con = null;
		List<PicDocking> employees = new ArrayList<PicDocking>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);
			getAllStatement.setString(1, pos);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PicDocking employee = new PicDocking();
				employee.setEmpCode(rs.getString("employee_id"));
				employee.setEmpName(rs.getString("employee_name"));
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return employees;
	}
	
	public Employee getEmployeeByCode(String code) throws SQLException {
		Connection con = null;
		Employee employee = new Employee();
		try {
			con = dataSource.getConnection();
			getEmpByCodeStatement = con.prepareStatement(getEmpByCodeQuery);
			getEmpByCodeStatement.setString(1, code);
			
			ResultSet rs = getEmpByCodeStatement.executeQuery();
			rs.next();
			
			employee.setEmployeeId(rs.getString("employee_id"));
			employee.setEmployeeName(rs.getString("employee_name"));	

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return employee;
	}
	
	public List<Employee> getEmployeeGrader(String pos) throws SQLException {
		Connection con = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);
			getAllStatement.setString(1, pos);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeeType(rs.getString("employee_type_id"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employees.add(employee);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return employees;
	}
}
