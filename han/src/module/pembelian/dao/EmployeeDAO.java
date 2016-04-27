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

	private String getAllQuery = "SELECT id, employee_code, employee_name" + " FROM employee WHERE role = 1 ";

	public EmployeeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}

	public List<Employee> getAllPICDocking() throws SQLException {
		Connection con = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmpCode(rs.getString("employee_code"));
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
}
