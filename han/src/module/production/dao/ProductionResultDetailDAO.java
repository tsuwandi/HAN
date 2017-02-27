package module.production.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.production.model.ProdRM;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.productionpk.model.ProdPK;
import module.productionpk.model.ProdPKMaterial;
import module.productionpk.model.ProdPKResultProduct;

public class ProductionResultDetailDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProdPKCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	
	private String getAllQuery = "SELECT id, prod_result_id, product_code, qty FROM prod_result_product WHERE deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE prod_result_product SET deleted_date = ? , delete_by=? WHERE prod_result_id = ? AND id=?";
	
	private String insertQuery = "INSERT INTO prod_result_product (prod_result_id, product_code, qty , input_by, input_date) "
			+ "VALUES (?,?,?,?,?)";
	private String updateQuery = "UPDATE prod_result_product SET product_code=?, qty=?, edited_by=?, edited_date=? "
			+ "WHERE prod_result_id =? AND id=?";
	
	private String deleteQuery = "DELETE FROM prod_result_product WHERE prod_result_id = ?";
	
	public ProductionResultDetailDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<ProductionResultProduct> getAll() throws SQLException {
		List<ProductionResultProduct> prodPKResultProducts = new ArrayList<ProductionResultProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProduct prodPKResultProduct = new ProductionResultProduct();
				prodPKResultProduct.setId(rs.getInt("id"));
				prodPKResultProduct.setProdResultID(rs.getInt("prod_pk_result_id"));
				prodPKResultProduct.setProductCode(rs.getString("product_code"));
				prodPKResultProduct.setQty(rs.getDouble("qty"));
				prodPKResultProducts.add(prodPKResultProduct);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKResultProducts;
	}
	
	public List<ProductionResultProduct> getAllByProdPKResultID(int prodPKCode) throws SQLException {
		List<ProductionResultProduct> prodPKResultProducts = new ArrayList<ProductionResultProduct>();

		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND prod_result_id = ?");
			
			getProdResultByProdPKCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProdPKCodeStatement.setInt(1, prodPKCode);
			
			ResultSet rs = getProdResultByProdPKCodeStatement.executeQuery();
			while (rs.next()) {
				ProductionResultProduct prodPKResultProduct = new ProductionResultProduct();
				prodPKResultProduct.setId(rs.getInt("id"));
				prodPKResultProduct.setProdResultID(rs.getInt("prod_result_id"));
				prodPKResultProduct.setProductCode(rs.getString("product_code"));
				prodPKResultProduct.setQty(rs.getDouble("qty"));
				prodPKResultProducts.add(prodPKResultProduct);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKResultProducts;
	}
	
	public void save(ProductionResultProduct prodPKResultProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, prodPKResultProduct.getProdResultID());
			insertStatement.setString(2, prodPKResultProduct.getProductCode());
			insertStatement.setDouble(3, prodPKResultProduct.getQty());
			insertStatement.setString(4, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(5, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(ProductionResultProduct prodPKResultProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, prodPKResultProduct.getProductCode());
			updateStatement.setDouble(2, prodPKResultProduct.getQty());
			updateStatement.setString(3, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(4, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(5, prodPKResultProduct.getProdResultID());
			updateStatement.setInt(6, prodPKResultProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(int prodResultId) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, prodResultId);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void updateDelete(ProductionResultProduct productionResultProduct) throws SQLException {
		try {
			updateDeleteStatement = connection.prepareStatement(updateDeleteQuery);
			updateDeleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			updateDeleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			updateDeleteStatement.setInt(3, productionResultProduct.getProdResultID());
			updateDeleteStatement.setInt(4, productionResultProduct.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
