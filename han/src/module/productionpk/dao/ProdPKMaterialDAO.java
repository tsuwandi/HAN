package module.productionpk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.ProdRM;
import module.productionpk.model.ProdPK;
import module.productionpk.model.ProdPKMaterial;

public class ProdPKMaterialDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getProdResultByProdPKCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = "SELECT id, prod_pk_code, product_code, qty FROM prod_pk_material WHERE deleted_date IS NULL";
	
	
	private String insertQuery = "INSERT INTO prod_pk_material (prod_pk_code, product_code, qty , input_by, input_date) "
			+ "VALUES (?,?,?,?,?)";
	private String updateQuery = "UPDATE prod_pk_material SET product_code=?, qty=?, edited_by=?, edited_date=? "
			+ "WHERE prod_pk_code =? AND id=?";
	
	private String deleteQuery = "DELETE FROM prod_pk_material WHERE prod_pk_code = ?";
	
	public ProdPKMaterialDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<ProdPKMaterial> getAll() throws SQLException {
		List<ProdPKMaterial> prodPKMaterials = new ArrayList<ProdPKMaterial>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				ProdPKMaterial prodPKMaterial = new ProdPKMaterial();
				prodPKMaterial.setId(rs.getInt("id"));
				prodPKMaterial.setProdPKCode(rs.getString("prod_pk_code"));
				prodPKMaterial.setProductCode(rs.getString("product_code"));
				prodPKMaterial.setQty(rs.getDouble("qty"));
				prodPKMaterials.add(prodPKMaterial);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKMaterials;
	}
	
	public List<ProdPKMaterial> getAllByProdPKCode(String prodPKCode) throws SQLException {
		List<ProdPKMaterial> prodPKMaterials = new ArrayList<ProdPKMaterial>();

		try {
			StringBuffer sb  = new StringBuffer(getAllQuery);
			sb.append(" AND prod_pk_code = ?");
			
			getProdResultByProdPKCodeStatement = connection.prepareStatement(sb.toString());
			getProdResultByProdPKCodeStatement.setString(1, prodPKCode);
			
			ResultSet rs = getProdResultByProdPKCodeStatement.executeQuery();
			while (rs.next()) {
				ProdPKMaterial prodPKMaterial = new ProdPKMaterial();
				prodPKMaterial.setId(rs.getInt("id"));
				prodPKMaterial.setProductCode(rs.getString("product_code"));
				prodPKMaterial.setProdPKCode(rs.getString("prod_pk_code"));
				prodPKMaterial.setQty(rs.getDouble("qty"));
				prodPKMaterials.add(prodPKMaterial);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return prodPKMaterials;
	}
	
	public void save(ProdPKMaterial prodPKMaterial) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, prodPKMaterial.getProdPKCode());
			insertStatement.setString(2, prodPKMaterial.getProductCode());
			insertStatement.setDouble(3, prodPKMaterial.getQty());
			insertStatement.setString(4, "Michael");
			insertStatement.setDate(5, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(ProdPKMaterial prodPKMaterial) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, prodPKMaterial.getProductCode());
			updateStatement.setDouble(2, prodPKMaterial.getQty());
			updateStatement.setString(3, "Michael");
			updateStatement.setDate(4, new Date(new java.util.Date().getTime()));
			updateStatement.setString(5, prodPKMaterial.getProdPKCode());
			updateStatement.setInt(6, prodPKMaterial.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(String prodPKCode) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setString(1, prodPKCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
