package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dailyclosing.model.Inventory;
import module.dailyclosing.model.InventoryLog;
import module.report.model.DryStockFlow;
import module.util.DateUtil;

public class InventoryLogDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement getAllDryStockStatement;

	private String insertQuery = new StringBuilder()
			.append("insert into inventory_log (product_code, warehouse, prev_stock, plus_stock, min_stock, curr_stock, prev_stock_date, curr_stock_date, confirm_code, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?,?,?,?,?)").toString();
	
	private String getAllDryStockQuery = new StringBuilder()
			.append("select i.id, i.product_code, i.warehouse, i.prev_stock, i.plus_stock, i.min_stock, i.curr_stock, i.prev_stock_date, i.curr_stock_date, p.product_name, i.input_date ")
			.append("from inventory_log i inner join product p on i.product_code = p.product_code where i.curr_stock_date between ? and ? ").toString();
	
	public InventoryLogDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public InventoryLog save(InventoryLog inventoryLog) throws SQLException {
		ResultSet generatedKeys = null;
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, inventoryLog.getProductCode());
			insertStatement.setInt(2, inventoryLog.getWarehouse());
			insertStatement.setDouble(3, inventoryLog.getPrevStock());
			insertStatement.setDouble(4, inventoryLog.getPlusStock());
			insertStatement.setDouble(5, inventoryLog.getMinStock());
			insertStatement.setDouble(6, inventoryLog.getCurrStock());
			insertStatement.setDate(7, DateUtil.toDate(inventoryLog.getPrevStockDate()));
			insertStatement.setDate(8, DateUtil.toDate(inventoryLog.getCurrStockDate()));
			insertStatement.setString(9, inventoryLog.getConfirmCode());
			insertStatement.setDate(10, DateUtil.getCurrentDate());
			insertStatement.setString(11, "ADMIN");
			insertStatement.executeUpdate();

			generatedKeys = insertStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				inventoryLog.setId(generatedKeys.getInt(1));
				generatedKeys.close();
			}
			
			return inventoryLog;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

	}

	

	public List<InventoryLog> getAllDryStock(DryStockFlow dryStockFlow) throws SQLException {
		List<InventoryLog> inventoryLogs = new ArrayList<InventoryLog>();
		
		try {
			getAllDryStockStatement = connection.prepareStatement(getAllDryStockQuery);
			getAllDryStockStatement.setDate(1, DateUtil.toDate(dryStockFlow.getStartFrom()));
			getAllDryStockStatement.setDate(2, DateUtil.toDate(dryStockFlow.getEndTo()));
			
			ResultSet rs = getAllDryStockStatement.executeQuery();
			while (rs.next()) {
				InventoryLog inventoryLog = new InventoryLog();
				inventoryLog.setId(rs.getInt("id"));
				inventoryLog.setProductCode(rs.getString("product_code"));
				inventoryLog.setWarehouse(rs.getInt("warehouse"));
				inventoryLog.setPrevStock(rs.getDouble("prev_stock"));
				inventoryLog.setPlusStock(rs.getDouble("plus_stock"));
				inventoryLog.setMinStock(rs.getDouble("min_stock"));
				inventoryLog.setCurrStock(rs.getDouble("curr_stock"));
				inventoryLog.setPrevStockDate(rs.getDate("prev_stock_date"));
				inventoryLog.setCurrStockDate(rs.getDate("curr_stock_date"));
				inventoryLog.setProductName(rs.getString("product_name"));
				inventoryLog.setInputDate(rs.getDate("input_date"));
				inventoryLogs.add(inventoryLog);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		
		return inventoryLogs;
	}
}
