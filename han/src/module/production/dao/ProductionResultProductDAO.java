package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.productionpk.model.ProdPKResult;

public class ProductionResultProductDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT id, prod_result_id, product_code, qty "
			+ "FROM prod_result_product WHERE deleted_date IS NULL";

	public ProductionResultProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProductionResultProduct> getAllProductionResultProductForDailyClosing() throws SQLException {
		
		List<ProductionResultProduct> productionResultProductResults = new ArrayList<ProductionResultProduct>();
		String query = new StringBuilder().append(getAllQuery)
				.append(" AND confirm_date is NOT NULL")
				.append(" AND input_date <= CURDATE()").toString();
		try {
			getAllStatement = connection.prepareStatement(query);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProduct productionResultProduct = new ProductionResultProduct();
				productionResultProduct.setId(rs.getInt("id"));
				productionResultProduct.setProdResultID(rs.getInt("prod_result_id"));
				productionResultProduct.setProductCode(rs.getString("product_code"));
				productionResultProduct.setQty(rs.getDouble("qty"));
				productionResultProductResults.add(productionResultProduct);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return productionResultProductResults;
		
		
	}
}
