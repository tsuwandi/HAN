package module.productsupportinggood.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.product.dao.ProductDAO;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.productsupportinggood.dao.ProductSuppDAO;

public class ProductSupportingGoodBL {

	private DataSource dataSource;

	public ProductSupportingGoodBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int isProductCodeExists(String productCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).isProductCodeExists(productCode);
		} finally {
			con.close();
		}
	}
	
	public List<ProductCategory> getAllProductCategory() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).getAllProductCategory();
		} finally {
			con.close();
		}
	}
	
	public List<Uom> getAllUom() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).getAllUom();
		} finally {
			con.close();
		}
	}

	public int isProductNameExists(String productName) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).isProductNameExists(productName);
		} finally {
			con.close();
		}
	}
}
