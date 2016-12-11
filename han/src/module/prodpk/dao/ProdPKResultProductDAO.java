package module.prodpk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.prodpk.model.ProdPKResultProduct;
import module.product.model.Product;
import module.util.DateUtil;

public class ProdPKResultProductDAO {
	private Connection connection;
	private PreparedStatement getAllByProdPKCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByProdPKCodeQuery = new StringBuilder()
			.append("select pp.id, pp.prod_pk_code, pp.product_code, pp.qty, ")
			.append("p.product_name, p.id as product_id from prod_pk_result_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("where pp.prod_pk_code = ? and pp.deleted_date is null and p.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder().append("insert into prod_pk_result_product (prod_pk_code, product_code, qty, ")
			.append("input_date, input_by) values (?,?,?,?,?)").toString();

	private String updateQuery = "update prod_pk_result_product set product_code=?, qty=?, edited_date=?, edited_by=? where id=?";

	private String deleteQuery = "update prod_pk_result_product set deleted_date=?, deleted_by=? ";

	public ProdPKResultProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<ProdPKResultProduct> getAllByProdPKCode(String pprCode) throws SQLException {
		List<ProdPKResultProduct> prodPKResultProducts = new ArrayList<ProdPKResultProduct>();

		try {
			String query = new StringBuilder().append(getAllByProdPKCodeQuery).append(" ORDER BY ID ASC").toString();
			
			getAllByProdPKCodeStatement = connection.prepareStatement(query);
			getAllByProdPKCodeStatement.setString(1, pprCode);

			ResultSet rs = getAllByProdPKCodeStatement.executeQuery();
			while (rs.next()) {
				ProdPKResultProduct prodPKResultProduct = new ProdPKResultProduct();
				prodPKResultProduct.setId(rs.getInt("id"));
				prodPKResultProduct.setProdPKCode(rs.getString("prod_pk_code"));
				prodPKResultProduct.setProductCode(rs.getString("product_code"));
				prodPKResultProduct.setQty(rs.getInt("qty"));

				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));

				prodPKResultProduct.setProduct(product);
				prodPKResultProducts.add(prodPKResultProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return prodPKResultProducts;
	}

	public void save(ProdPKResultProduct prodPKResultProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, prodPKResultProduct.getProdPKCode());
			insertStatement.setString(2, prodPKResultProduct.getProductCode());
			insertStatement.setInt(3, prodPKResultProduct.getQty());
			insertStatement.setDate(4, DateUtil.getCurrentDate());
			insertStatement.setString(5, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(ProdPKResultProduct prodPKResultProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, prodPKResultProduct.getProductCode());
			updateStatement.setInt(2, prodPKResultProduct.getQty());
			updateStatement.setDate(3, DateUtil.getCurrentDate());
			updateStatement.setString(4, "timotius");
			updateStatement.setInt(5, prodPKResultProduct.getId());
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
