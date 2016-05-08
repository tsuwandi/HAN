package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Employee;

public class EmployeeDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT employee_id, employee_type_id, employee_name" + " FROM employee WHERE position_id=? ";

	public EmployeeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<Employee> getEmployeeBasedOnPosition(String pos) throws SQLException {
		Connection con = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();

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
