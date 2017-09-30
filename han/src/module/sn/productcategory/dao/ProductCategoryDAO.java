package module.sn.productcategory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.currency.model.Currency;
import module.sn.productcategory.model.ProductCategory;

public class ProductCategoryDAO {
	private Connection connection;
	//private PreparedStatement insertStatement;
	//private PreparedStatement updateStatement;
	//private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;

	//private String insertQuery = "insert into bank (bank_abbr, bank, input_date, input_by) values (?,?,?,?)";
	//private String updateQuery = "update bank set bank_abbr=?, bank=?, edit_date=?, edited_by=? where id=?";
	//private String deleteQuery = "update bank set deleted_date=?, deleted_by=? where id=?";
	private String getAllQuery = "select id, product_category, product_category_type_id from product_category where product_category_type_id = ? and delete_date is null order by id asc";
	
	public ProductCategoryDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProductCategory> getAllByProductCategoryTypeId(int productCategoryTypeId) throws SQLException {
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			getAllStatement.setInt(1, productCategoryTypeId);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductCategory productCategory = new ProductCategory();
				productCategory.setId(rs.getInt("id"));
				productCategory.setProductCategory(rs.getString("product_category"));
				productCategory.setProductCategoryTypeId(rs.getInt("product_category_type_id"));
				
				productCategories.add(productCategory);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productCategories;
	}
}
