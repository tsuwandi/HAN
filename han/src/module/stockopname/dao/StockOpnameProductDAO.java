package module.stockopname.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.stockopname.model.SetSoScheduledProduct;
import module.stockopname.model.StockOpnameProduct;
import controller.ServiceFactory;

public class StockOpnameProductDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	
	
	private String getAllQuery = "SELECT a.id, a.stock_opname_id, b.id AS product_id ,a.product_code, product_name, product_category, uom, location, qty_system, qty_actual, selisih_qty, "
			+" value_system, value_actual, selisih_value "
			+" FROM stock_opname_prod a INNER JOIN product b ON a.product_code = b.product_code "
			+" INNER JOIN product_category c ON b.product_category_id = c.id INNER JOIN uom e ON b.product_uom_id = e.id WHERE a.deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE stock_opname_prod SET deleted_date = ? , deleted_by=? WHERE stock_opname_id = ? AND id=?";
	
	private String insertQuery = "INSERT INTO stock_opname_prod (stock_opname_id, product_code, location, qty_system, qty_actual, "
			+ " selisih_qty, value_system, value_actual, selisih_value, input_by, input_date) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE stock_opname_prod SET product_code=?, location=?, qty_system=?, qty_actual=?, "
			+ " selisih_qty=?, value_system=?, value_actual=?, selisih_value=?, edited_by=?, edited_date=? "
			+ " WHERE stock_opname_id =? AND id=?";
	
	private String deleteQuery = "DELETE FROM stock_opname_prod WHERE stock_opname_id = ?";
	
	public StockOpnameProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<StockOpnameProduct> getAll() throws SQLException {
		List<StockOpnameProduct> stockOpnameProducts = new ArrayList<StockOpnameProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				StockOpnameProduct stockOpnameProduct = new StockOpnameProduct();
				stockOpnameProduct.setId(rs.getInt("id"));
				stockOpnameProduct.setStockOpnameID(rs.getInt("stock_opname_id"));
				stockOpnameProduct.setProductCode(rs.getString("product_code"));
				stockOpnameProduct.setProductName(rs.getString("product_name"));
				stockOpnameProduct.setProductCategory(rs.getString("product_category"));
				stockOpnameProduct.setProductID(rs.getInt("product_id"));
				stockOpnameProduct.setLocation(rs.getString("location"));
				stockOpnameProduct.setUom(rs.getString("uom"));
				stockOpnameProduct.setQtySystem(rs.getDouble("qty_system"));
				stockOpnameProduct.setQtyActual(rs.getDouble("qty_actual"));
				stockOpnameProduct.setSelisihQty(rs.getDouble("selisih_qty"));
				stockOpnameProduct.setValueSystem(rs.getDouble("value_system"));
				stockOpnameProduct.setValueActual(rs.getDouble("value_actual"));
				stockOpnameProduct.setSelisihValue(rs.getDouble("selisih_value"));
				stockOpnameProducts.add(stockOpnameProduct);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return stockOpnameProducts;
	}
	
	public List<StockOpnameProduct> getAllStockOpnameProductByID(int stockOpnameID) throws SQLException {
		List<StockOpnameProduct> stockOpnameProducts = new ArrayList<StockOpnameProduct>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+" AND stock_opname_id = ?");
			getAllStatement.setInt(1,stockOpnameID);
			

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				StockOpnameProduct stockOpnameProduct = new StockOpnameProduct();
				stockOpnameProduct.setId(rs.getInt("id"));
				stockOpnameProduct.setStockOpnameID(rs.getInt("stock_opname_id"));
				stockOpnameProduct.setProductCode(rs.getString("product_code"));
				stockOpnameProduct.setProductName(rs.getString("product_name"));
				stockOpnameProduct.setProductCategory(rs.getString("product_category"));
				stockOpnameProduct.setProductID(rs.getInt("product_id"));
				stockOpnameProduct.setLocation(rs.getString("location"));
				stockOpnameProduct.setUom(rs.getString("uom"));
				stockOpnameProduct.setQtySystem(rs.getDouble("qty_system"));
				stockOpnameProduct.setQtyActual(rs.getDouble("qty_actual"));
				stockOpnameProduct.setSelisihQty(rs.getDouble("selisih_qty"));
				stockOpnameProduct.setValueSystem(rs.getDouble("value_system"));
				stockOpnameProduct.setValueActual(rs.getDouble("value_actual"));
				stockOpnameProduct.setSelisihValue(rs.getDouble("selisih_value"));
				stockOpnameProducts.add(stockOpnameProduct);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return stockOpnameProducts;
	}
	
	public void save(StockOpnameProduct stockOpnameProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, stockOpnameProduct.getStockOpnameID());
			insertStatement.setString(2, stockOpnameProduct.getProductCode());
			insertStatement.setString(3, stockOpnameProduct.getLocation());
			insertStatement.setDouble(4, stockOpnameProduct.getQtySystem());
			insertStatement.setDouble(5, stockOpnameProduct.getQtyActual());
			insertStatement.setDouble(6, stockOpnameProduct.getSelisihQty());
			insertStatement.setDouble(7, stockOpnameProduct.getValueSystem());
			insertStatement.setDouble(8, stockOpnameProduct.getValueActual());
			insertStatement.setDouble(9, stockOpnameProduct.getSelisihValue());
			insertStatement.setString(10, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(11, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(StockOpnameProduct stockOpnameProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, stockOpnameProduct.getProductCode());
			updateStatement.setString(2, stockOpnameProduct.getLocation());
			updateStatement.setDouble(3, stockOpnameProduct.getQtySystem());
			updateStatement.setDouble(4, stockOpnameProduct.getQtyActual());
			updateStatement.setDouble(5, stockOpnameProduct.getSelisihQty());
			updateStatement.setDouble(6, stockOpnameProduct.getValueSystem());
			updateStatement.setDouble(7, stockOpnameProduct.getValueActual());
			updateStatement.setDouble(8, stockOpnameProduct.getSelisihValue());
			updateStatement.setString(9, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(10, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(11, stockOpnameProduct.getStockOpnameID());
			updateStatement.setInt(12, stockOpnameProduct.getId());
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
	public void updateDelete(StockOpnameProduct stockOpnameProduct) throws SQLException {
		try {
			updateDeleteStatement = connection.prepareStatement(updateDeleteQuery);
			updateDeleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			updateDeleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			updateDeleteStatement.setInt(3, stockOpnameProduct.getStockOpnameID());
			updateDeleteStatement.setInt(4, stockOpnameProduct.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
