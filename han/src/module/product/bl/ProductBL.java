package module.product.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import module.product.dao.ProductDAO;
import module.product.model.Condition;
import module.product.model.Grade;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.sn.production.quality.dao.ProductionQualityDAO;
import module.sn.production.quality.model.ProductionQuality;
import module.sn.production.type.dao.ProductionTypeDAO;
import module.sn.production.type.model.ProductionType;
import module.sn.woodtype.dao.WoodTypeDAO;
import module.sn.woodtype.model.WoodType;

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
			throw new SQLException(e.getMessage());
		} finally {
			con.close();
		}
	}

	public void delete(Product product) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			new ProductDAO(con).delete(product.getProductCode());

			con.commit();
		} catch (SQLException e) {
			con.rollback();
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
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllProductCategory();
		} finally {
			con.close();
		}
	}

	public List<WoodType> getAllWoodType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new WoodTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<Grade> getAllGrade() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllGrade();
		} finally {
			con.close();
		}
	}
	
	public List<Grade> getAllGradeByCategoryProductId(int productCategoryId) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllGradeByCategoryProductId(productCategoryId);
		} finally {
			con.close();
		}
	}

	public List<Uom> getAllUom() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllUom();
		} finally {
			con.close();
		}
	}

	public List<Condition> getAllCondition() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).getAllCondition();
		} finally {
			con.close();
		}
	}

	public int isProductCodeExists(String productCode) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).isProductCodeExists(productCode);
		} finally {
			con.close();
		}
	}

	public int isProductNameExists(String productName) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).isProductNameExists(productName);
		} finally {
			con.close();
		}
	}

	public List<ProductionType> getAllProductionType() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionTypeDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public List<ProductionQuality> getAllProductionQuality() throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductionQualityDAO(con).getAll();
		} finally {
			con.close();
		}
	}

	public Product isProductExists(Boolean isEdit, Product product) throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			return new ProductDAO(con).isProductExists(isEdit, product);
		} finally {
			con.close();
		}
	}

}
