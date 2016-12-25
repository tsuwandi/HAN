package module.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.util.DateUtil;
import module.product.model.ProductPP;

public class ProductPPDAO {
	private Connection connection;
	private PreparedStatement getAllByProductCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByProductCodeQuery = new StringBuilder()
			.append("select pp.id, pp.product_code, pp.price, pp.effective_start_date, pp.effective_end_date ")
			.append("from product_pp pp ")
			.append("where pp.product_code = ? and pp.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder()
			.append("insert into product_pp (product_code, price, effective_start_date, effective_end_date, ")
			.append("input_date, input_by) values (?,?,?,?,?,?)").toString();

	private String updateQuery = "update product_pp set price=?, effective_start_date=?, effective_end_date=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update product_pp set deleted_date=?, deleted_by=? ";
	
	public ProductPPDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	
	public List<ProductPP> getAllByProductCode(String productCode) throws SQLException {
		List<ProductPP> productPPs = new ArrayList<ProductPP>();

		try {
			getAllByProductCodeStatement = connection.prepareStatement(getAllByProductCodeQuery);
			getAllByProductCodeStatement.setString(1, productCode);
			
			ResultSet rs = getAllByProductCodeStatement.executeQuery();
			while (rs.next()) {
				ProductPP productPP = new ProductPP();
				productPP.setId(rs.getInt("id"));
				productPP.setProductCode(rs.getString("product_code"));
				productPP.setPrice(rs.getDouble("price"));
				productPP.setEffectiveStartDate(rs.getDate("effective_start_date"));
				productPP.setEffectiveEndDate(rs.getDate("effective_end_date"));
				
				productPPs.add(productPP);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return productPPs;
	}
	
	public void save(ProductPP productPP) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, productPP.getProductCode());
			insertStatement.setDouble(2, productPP.getPrice());
			insertStatement.setDate(3,  DateUtil.toDate(productPP.getEffectiveStartDate()));
			insertStatement.setDate(4, DateUtil.toDate(productPP.getEffectiveEndDate()));
			insertStatement.setDate(5, DateUtil.getCurrentDate());
			insertStatement.setString(6, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(ProductPP productPP) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setDouble(1, productPP.getPrice());
			updateStatement.setDate(2,  DateUtil.toDate(productPP.getEffectiveStartDate()));
			updateStatement.setDate(3, DateUtil.toDate(productPP.getEffectiveEndDate()));
			updateStatement.setDate(4, DateUtil.getCurrentDate());
			updateStatement.setString(5, "timotius");
			updateStatement.setInt(6, productPP.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteAll(String productCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where product_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, productCode);
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
