package module.product.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.employee.dao.EmployeeDAO;
import module.employee.model.Employee;
import module.product.dao.ProductDAO;
import module.product.model.Product;

public class ProductBL {
	private DataSource dataSource;

	public ProductBL(DataSource dataSource) {
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
	
	public List<Product> getAll() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAll();
		} finally {
			con.close();
		}
	}
	
	public List<Product> getSearchProduct(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllBySimpleSearch(value);
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

//	public List<Product> getAllDivision() throws SQLException {
//		Connection con = null;
//		try {
//			con = dataSource.getConnection();
//			return new ProductDAO(con).getAllDivision();
//		} finally {
//			con.close();
//		}
//	}

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