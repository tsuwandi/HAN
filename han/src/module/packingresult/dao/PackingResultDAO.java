package module.packingresult.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.packingresult.model.PackingResult;


public class PackingResultDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProdPKCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	
	private String getAllQuery = "SELECT id, packing_id, product_code, qty FROM packing_result WHERE deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE packing_result SET deleted_date = ? , deleted_by=? WHERE packing_id = ? AND id=?";
	
	private String insertQuery = "INSERT INTO packing_result (packing_id, product_code, qty , input_by, input_date) "
			+ "VALUES (?,?,?,?,?)";
	private String updateQuery = "UPDATE packing_result SET product_code=?, qty=?, edited_by=?, edited_date=? "
			+ "WHERE packing_id =? AND id=?";
	
	private String deleteQuery = "DELETE FROM packing_result WHERE packing_id = ?";
	
	public PackingResultDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<PackingResult> getAll() throws SQLException {
		List<PackingResult> prodPKResultProducts = new ArrayList<PackingResult>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				PackingResult prodPKResultProduct = new PackingResult();
				prodPKResultProduct.setId(rs.getInt("id"));
				prodPKResultProduct.setPackingID(rs.getInt("packing_id"));
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
	
	public List<PackingResult> getAllPackingByID(int packingID) throws SQLException {
		List<PackingResult> prodPKResultProducts = new ArrayList<PackingResult>();

		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND packing_id = ?");
			
			getProdResultByProdPKCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProdPKCodeStatement.setInt(1, packingID);
			
			ResultSet rs = getProdResultByProdPKCodeStatement.executeQuery();
			while (rs.next()) {
				PackingResult prodPKResultProduct = new PackingResult();
				prodPKResultProduct.setId(rs.getInt("id"));
				prodPKResultProduct.setPackingID(rs.getInt("packing_id"));
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
	
	public void save(PackingResult packingResult) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, packingResult.getPackingID());
			insertStatement.setString(2, packingResult.getProductCode());
			insertStatement.setDouble(3, packingResult.getQty());
			insertStatement.setString(4, "Michael");
			insertStatement.setDate(5, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(PackingResult packingResult) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, packingResult.getProductCode());
			updateStatement.setDouble(2, packingResult.getQty());
			updateStatement.setString(3, "Michael");
			updateStatement.setDate(4, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(5, packingResult.getPackingID());
			updateStatement.setInt(6, packingResult.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(int packingResultID) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, packingResultID);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void updateDelete(PackingResult packingResult) throws SQLException {
		try {
			updateDeleteStatement = connection.prepareStatement(updateDeleteQuery);
			updateDeleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			updateDeleteStatement.setString(2, "Michael");
			updateDeleteStatement.setInt(3, packingResult.getPackingID());
			updateDeleteStatement.setInt(4, packingResult.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
