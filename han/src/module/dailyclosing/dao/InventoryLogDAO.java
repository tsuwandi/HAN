package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import module.dailyclosing.model.InventoryLog;
import module.util.DateUtil;

public class InventoryLogDAO {
	private Connection connection;
	private PreparedStatement insertStatement;

	private String insertQuery = new StringBuilder()
			.append("insert into inventory_log (product_code, warehouse, prev_stock, plus_stock, min_stock, curr_stock, prev_stock_date, curr_stock_date, confirm_code, ")
			.append("input_date, input_by) values (?,?,?,?,?,?,?,?,?,?,?)").toString();

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
}
