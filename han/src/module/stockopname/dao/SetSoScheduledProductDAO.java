package module.stockopname.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.stockopname.model.SetSoScheduledProduct;

public class SetSoScheduledProductDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	
	private String getAllQuery = "SELECT a.id, set_so_schedule_id, b.id AS product_id ,a.product_code, product_name, product_category FROM set_so_schedule_prod a INNER JOIN product b ON a.product_code = b.product_code"+
			" INNER JOIN product_category c ON b.product_category_id = c.id WHERE a.deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE set_so_schedule_prod SET deleted_date = ? , deleted_by=? WHERE set_so_schedule_id = ? AND id=?";
	
	private String insertQuery = "INSERT INTO set_so_schedule_prod (set_so_schedule_id, product_code, input_by, input_date) "
			+ "VALUES (?,?,?,?)";
	private String updateQuery = "UPDATE set_so_schedule_prod SET product_code=?, edited_by=?, edited_date=? "
			+ "WHERE set_so_schedule_id =? AND id=?";
	
	private String deleteQuery = "DELETE FROM set_so_schedule_prod WHERE set_so_schedule_id = ?";
	
	public SetSoScheduledProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<SetSoScheduledProduct> getAll() throws SQLException {
		List<SetSoScheduledProduct> setSoScheduledProducts = new ArrayList<SetSoScheduledProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				SetSoScheduledProduct setSoScheduledProduct = new SetSoScheduledProduct();
				setSoScheduledProduct.setId(rs.getInt("id"));
				setSoScheduledProduct.setSetSOScheduledID(rs.getInt("set_so_schedule_id"));
				setSoScheduledProduct.setProductCode(rs.getString("product_code"));
				setSoScheduledProduct.setProductName(rs.getString("product_name"));
				setSoScheduledProduct.setProductCategory(rs.getString("product_category"));
				setSoScheduledProduct.setProductID(rs.getInt("product_id"));
				setSoScheduledProducts.add(setSoScheduledProduct);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return setSoScheduledProducts;
	}
	
	public List<SetSoScheduledProduct> getAllSetScheduledSOProductByID(int scheduleID) throws SQLException {
		List<SetSoScheduledProduct> setSoScheduledProducts = new ArrayList<SetSoScheduledProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+" AND set_so_schedule_id = ?");
			getAllStatement.setInt(1,scheduleID);
			

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				SetSoScheduledProduct setSoScheduledProduct = new SetSoScheduledProduct();
				setSoScheduledProduct.setId(rs.getInt("id"));
				setSoScheduledProduct.setSetSOScheduledID(rs.getInt("set_so_schedule_id"));
				setSoScheduledProduct.setProductCode(rs.getString("product_code"));
				setSoScheduledProduct.setProductName(rs.getString("product_name"));
				setSoScheduledProduct.setProductCategory(rs.getString("product_category"));
				setSoScheduledProduct.setProductID(rs.getInt("product_id"));
				setSoScheduledProducts.add(setSoScheduledProduct);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return setSoScheduledProducts;
	}
	
	public void save(SetSoScheduledProduct setSoScheduledProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, setSoScheduledProduct.getSetSOScheduledID());
			insertStatement.setString(2, setSoScheduledProduct.getProductCode());
			insertStatement.setString(3, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(4, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(SetSoScheduledProduct setSoScheduledProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, setSoScheduledProduct.getProductCode());
			updateStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(3, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(4, setSoScheduledProduct.getSetSOScheduledID());
			updateStatement.setInt(5, setSoScheduledProduct.getId());
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
	public void updateDelete(SetSoScheduledProduct setSoScheduledProduct) throws SQLException {
		try {
			updateDeleteStatement = connection.prepareStatement(updateDeleteQuery);
			updateDeleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			updateDeleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			updateDeleteStatement.setInt(3, setSoScheduledProduct.getSetSOScheduledID());
			updateDeleteStatement.setInt(4, setSoScheduledProduct.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
