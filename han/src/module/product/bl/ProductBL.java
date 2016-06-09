package module.product.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.employee.dao.EmployeeDAO;
import module.employee.model.Employee;
import module.pembelian.model.WoodType;
import module.product.dao.ProductDAO;
import module.product.model.Grade;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.supplier.dao.SuppAddressDAO;
import module.supplier.dao.SuppCpDAO;
import module.supplier.dao.SuppVehicleDAO;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppCp;
import module.supplier.model.SuppVehicle;
import module.supplier.model.Supplier;

public class ProductBL {
	private DataSource dataSource;

	public ProductBL(DataSource dataSource) {
		this.dataSource = dataSource;
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
	
	public List<Product> getProductId() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getProductId();
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
	
	public void save(Product product) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			new ProductDAO(con).save(product);
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public void update(Product product) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			new ProductDAO(con).update(product);
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	public Product getProductByCode(String productCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getById(productCode);
		} finally {
			con.close();
		}
	}
	
	public List<ProductCategory> getAllProductCategory() throws SQLException {
		Connection con = null;
		try{
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllProductCategory();
		} finally {
			con.close();
		}
	}
	
	public List<WoodType> getAllWoodType() throws SQLException {
		Connection con = null;
		try{
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllWoodType();
		} finally {
			con.close();
		}
	}
	
	public List<Grade> getAllGrade() throws SQLException {
		Connection con = null;
		try{
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllGrade();
		} finally {
			con.close();
		}
	}
	
	public List<Uom> getAllUom() throws SQLException {
		Connection con = null;
		try{
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllUom();
		} finally {
			con.close();
		}
	}
	
	public void deleteAll(Product product) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new SupplierDAO(con).delete(product.getProductId());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
}
