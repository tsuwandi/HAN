package module.dryin.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.employee.model.Employee;
import module.employee.dao.EmployeeDAO;

public class DryInBL {

	private DataSource dataSource;

	public DryInBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Employee> getAllEmployee() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Employee> getAllEmployeeBySearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new EmployeeDAO(con).getAllBySearch(value);
		} finally {
			con.close();
		}
	}
}
