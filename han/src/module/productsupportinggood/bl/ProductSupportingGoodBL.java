package module.productsupportinggood.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.AppConstants;
import module.product.dao.ProductDAO;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.productsupportinggood.dao.ProductSuppDAO;
import module.productsupportinggood.model.ProductSupp;
import module.supplier.dao.SuppAddressDAO;
import module.supplier.dao.SuppCpDAO;
import module.supplier.dao.SuppVehicleDAO;
import module.supplier.dao.SupplierDAO;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppVehicle;
import module.supplier.model.Supplier;

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

	public String getOrdinalOfCodeNumber(String productCategory) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();

			if (AppConstants.HASIL_PRODUKSI.equalsIgnoreCase(productCategory)) {
				return String.format("%02d", new ProductSuppDAO(con).getOrdinalOfCodeNumber(productCategory) + 1);
			} else {
				return String.format("%04d", new ProductSuppDAO(con).getOrdinalOfCodeNumber(productCategory) + 1);
			}

		} finally {
			con.close();
		}
	}

	public void save(ProductSupp productSupp) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProductSuppDAO(con).save(productSupp);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public List<ProductSupp> getAllProductSuppBySimpleSearch(String value) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).getAllBySimpleSearch(value);
		} finally {
			con.close();
		}
	}

	public List<ProductSupp> getAllProductSupp() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public ProductSupp getProductSuppById(Integer productSuppId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductSuppDAO(con).getById(productSuppId);
		} finally {
			con.close();
		}
	}
	
	public void delete(ProductSupp productSupp) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProductSuppDAO(con).delete(productSupp.getId());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void update(ProductSupp productSupp) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			new ProductSuppDAO(con).update(productSupp);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}
}
