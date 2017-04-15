package module.stockopname.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.stockopname.model.ProductSO;
import module.stockopname.model.SetSoScheduledProduct;
import module.stockopname.model.StockOpnameProduct;

public class ProductSODAO {
	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT a.id, product_code, product_name, a.product_category_id, d.product_category, e.uom " 
			+"FROM product a INNER JOIN product_category d on a.product_category_id = d.id INNER JOIN uom e ON a.product_uom_id = e.id WHERE  a.deleted_date IS NULL";


	public ProductSODAO(Connection connection) throws SQLException {
		this.connection = connection;

	}

	
	
	public List<ProductSO> getAllProduct() throws SQLException {
		ArrayList<ProductSO> products = new ArrayList<ProductSO>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductSO product = new ProductSO();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductCategoryName(rs.getString("product_category"));
				product.setProductCategoryId(rs.getInt("product_category_id"));
				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	public List<SetSoScheduledProduct> getAllProductSchedule() throws SQLException {
		ArrayList<SetSoScheduledProduct> products = new ArrayList<SetSoScheduledProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				SetSoScheduledProduct product = new SetSoScheduledProduct();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductCategory(rs.getString("product_category"));
				product.setProductCategoryID(rs.getInt("product_category_id"));
				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	public List<StockOpnameProduct> getAllProductSO() throws SQLException {
		ArrayList<StockOpnameProduct> products = new ArrayList<StockOpnameProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				StockOpnameProduct product = new StockOpnameProduct();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductCategory(rs.getString("product_category"));
				product.setProductCategoryID(rs.getInt("product_category_id"));
				product.setUom(rs.getString("UOM"));
				products.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return products;
	}
	
	
}
