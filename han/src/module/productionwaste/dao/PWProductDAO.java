package module.productionwaste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.product.model.Product;
import module.productionwaste.model.PWProduct;
import module.util.DateUtil;

public class PWProductDAO {
	private Connection connection;
	private PreparedStatement getAllByPPRCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByPPRCodeQuery = new StringBuilder()
			.append("select pp.id, pp.pw_code, pp.product_code, pp.qty, ")
			.append("p.product_name, p.id as product_id from pw_product pp ")
			.append("inner join product p on pp.product_code = p.product_code ")
			.append("where pp.pw_code = ? and pp.deleted_date is null and p.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder().append("insert into pw_product (pw_code, product_code, qty, ")
			.append("input_date, input_by) values (?,?,?,?,?)").toString();

	private String updateQuery = "update pw_product set product_code=?, qty=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update pw_product set deleted_date=?, deleted_by=? ";

	public PWProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<PWProduct> getAllByPwCode(String pprCode) throws SQLException {
		List<PWProduct> pwProducts = new ArrayList<PWProduct>();

		try {
			String query = new StringBuilder().append(getAllByPPRCodeQuery).append(" ORDER BY ID ASC").toString();
			
			getAllByPPRCodeStatement = connection.prepareStatement(query);
			getAllByPPRCodeStatement.setString(1, pprCode);

			ResultSet rs = getAllByPPRCodeStatement.executeQuery();
			while (rs.next()) {
				PWProduct pwProduct = new PWProduct();
				pwProduct.setId(rs.getInt("id"));
				pwProduct.setPwCode(rs.getString("pw_code"));
				pwProduct.setProductCode(rs.getString("product_code"));
				pwProduct.setQty(rs.getInt("qty"));

				Product product = new Product();
				product.setProductCode(rs.getString("product_code"));
				product.setProductName(rs.getString("product_name"));

				pwProduct.setProduct(product);
				pwProducts.add(pwProduct);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pwProducts;
	}

	public void save(PWProduct pwProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, pwProduct.getPwCode());
			insertStatement.setString(2, pwProduct.getProductCode());
			insertStatement.setInt(3, pwProduct.getQty());
			insertStatement.setDate(4, DateUtil.getCurrentDate());
			insertStatement.setString(5, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(PWProduct pwProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, pwProduct.getProductCode());
			updateStatement.setInt(2, pwProduct.getQty());
			updateStatement.setDate(3, DateUtil.getCurrentDate());
			updateStatement.setString(4, "timotius");
			updateStatement.setInt(5, pwProduct.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String pwCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where pw_code=? ").toString();

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
