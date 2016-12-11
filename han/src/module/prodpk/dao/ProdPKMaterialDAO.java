package module.prodpk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.prodpk.model.ProdPKMaterial;
import module.product.model.Product;
import module.util.DateUtil;

public class ProdPKMaterialDAO {
	private Connection connection;
	private PreparedStatement getAllByProdPKCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByProdPKCodeQuery = new StringBuilder()
			.append("select pp.id, pp.prod_pk_code, pp.product_code, pp.qty, ")
			.append("p.product_name, p.id as product_id from prod_pk_material pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("where pp.prod_pk_code = ? and pp.deleted_date is null and p.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder().append("insert into prod_pk_material (prod_pk_code, product_code, qty, ")
			.append("input_date, input_by) values (?,?,?,?,?)").toString();

	private String updateQuery = "update prod_pk_material set product_code=?, qty=?, edited_date=?, edited_by=? where id=?";

	private String deleteQuery = "update prod_pk_material set deleted_date=?, deleted_by=? ";

	public ProdPKMaterialDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProdPKMaterial> getAllByProdPKCode(String pprCode) throws SQLException {
		List<ProdPKMaterial> prodPKMaterials = new ArrayList<ProdPKMaterial>();

		try {
			String query = new StringBuilder().append(getAllByProdPKCodeQuery).append(" ORDER BY ID ASC").toString();
			
			getAllByProdPKCodeStatement = connection.prepareStatement(query);
			getAllByProdPKCodeStatement.setString(1, pprCode);

			ResultSet rs = getAllByProdPKCodeStatement.executeQuery();
			while (rs.next()) {
				ProdPKMaterial prodPKMaterial = new ProdPKMaterial();
				prodPKMaterial.setId(rs.getInt("id"));
				prodPKMaterial.setProdPKCode(rs.getString("prod_pk_code"));
				prodPKMaterial.setProductCode(rs.getString("product_code"));
				prodPKMaterial.setQty(rs.getInt("qty"));

				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));

				prodPKMaterial.setProduct(product);
				prodPKMaterials.add(prodPKMaterial);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return prodPKMaterials;
	}

	public void save(ProdPKMaterial prodPKMaterial) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, prodPKMaterial.getProdPKCode());
			insertStatement.setString(2, prodPKMaterial.getProductCode());
			insertStatement.setInt(3, prodPKMaterial.getQty());
			insertStatement.setDate(4, DateUtil.getCurrentDate());
			insertStatement.setString(5, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ProdPKMaterial prodPKMaterial) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, prodPKMaterial.getProductCode());
			updateStatement.setInt(2, prodPKMaterial.getQty());
			updateStatement.setDate(3, DateUtil.getCurrentDate());
			updateStatement.setString(4, "timotius");
			updateStatement.setInt(5, prodPKMaterial.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String pwCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where prod_pk_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, pwCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
