package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dailyclosing.model.Inventory;
import module.report.model.DryStockFlow;
import module.util.DateUtil;

public class InventoryDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement getInventoryByProductCodeAndWarehouseStatement;
	private PreparedStatement getInventoryBySelectedCode;
	
	private String insertQuery = new StringBuilder().append("insert into inventory (product_code, qty, warehouse, stock_date, inventory_log_id, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?)").toString();
	
	private String updateQuery = new StringBuilder().append("update inventory set product_code = ?, qty = ?, warehouse = ?, stock_date = ?, inventory_log_id =?, ")
			.append("edit_date =?, edited_by =? where id = ?").toString();
	
	private String getInventoryByProductCodeAndWarehouseQuery = new StringBuilder().append("select id, product_code, qty, warehouse, stock_date, inventory_log_id ")
			.append("from inventory where product_code = ? and warehouse = ? and deleted_date is null").toString();
	
	private String getInventoryBySelectedCodeQuery = new StringBuilder().append("select id, product_code, qty, warehouse, stock_date, inventory_log_id ")
			.append("from inventory WHERE deleted_date is null").toString();
	
	public InventoryDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public void save(Inventory inventory) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, inventory.getProductCode());
			insertStatement.setDouble(2, inventory.getQty());
			insertStatement.setInt(3, inventory.getWarehouse());
			insertStatement.setDate(4, DateUtil.toDate(inventory.getStockDate()));
			insertStatement.setInt(5, inventory.getInventoryLogId());
			insertStatement.setDate(6, DateUtil.getCurrentDate());
			insertStatement.setString(7, "ADMIN");
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public void update(Inventory inventory) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, inventory.getProductCode());
			updateStatement.setDouble(2, inventory.getQty());
			updateStatement.setInt(3, inventory.getWarehouse());
			updateStatement.setDate(4, DateUtil.toDate(inventory.getStockDate()));
			updateStatement.setInt(5, inventory.getInventoryLogId());
			updateStatement.setDate(6, DateUtil.getCurrentDate());
			updateStatement.setString(7, "ADMIN");
			updateStatement.setInt(8, inventory.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public Inventory getInventoryByProductCodeAndWarehouse(String productCode, int warehouse) throws SQLException {
		Inventory inventory = null;

		try {
			getInventoryByProductCodeAndWarehouseStatement = connection.prepareStatement(getInventoryByProductCodeAndWarehouseQuery);
			getInventoryByProductCodeAndWarehouseStatement.setString(1, productCode);
			getInventoryByProductCodeAndWarehouseStatement.setInt(2, warehouse);
			
			ResultSet rs = getInventoryByProductCodeAndWarehouseStatement.executeQuery();
			while (rs.next()) {
				inventory = new Inventory();
				inventory.setId(rs.getInt("id"));
				inventory.setProductCode(rs.getString("product_code"));
				inventory.setQty(rs.getDouble("qty"));
				inventory.setWarehouse(rs.getInt("warehouse"));
				inventory.setStockDate(rs.getDate("stock_date"));
				inventory.setInventoryLogId(rs.getInt("inventory_log_id"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return inventory;
	}
	
	public List<Inventory> getInventoryBySelectedCode(String sql) throws SQLException {
		List<Inventory> inventories = new ArrayList<>();
		try {
			getInventoryBySelectedCode = connection.prepareStatement(getInventoryBySelectedCodeQuery+sql);
			
			ResultSet rs = getInventoryBySelectedCode.executeQuery();
			while (rs.next()) {
				Inventory inventory = new Inventory();
				inventory.setId(rs.getInt("id"));
				inventory.setProductCode(rs.getString("product_code"));
				inventory.setQty(rs.getDouble("qty"));
				inventory.setWarehouse(rs.getInt("warehouse"));
				inventory.setStockDate(rs.getDate("stock_date"));
				inventory.setInventoryLogId(rs.getInt("inventory_log_id"));
				inventories.add(inventory);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return inventories;
	}
}
