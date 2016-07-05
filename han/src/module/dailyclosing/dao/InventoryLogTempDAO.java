package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import module.dailyclosing.model.InventoryLogTemp;
import module.util.DateUtil;

public class InventoryLogTempDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	
	private String insertQuery = "insert into inventory_log_temp (product_code, warehouse, qty, mutasi, src_table, confirm_code"
			+ "input_date, input_by) values (?,?,?,?,?,?,?,?)";
	
	public InventoryLogTempDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public void save(InventoryLogTemp inventoryLogTemp) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, inventoryLogTemp.getProductCode());
			insertStatement.setInt(2, inventoryLogTemp.getWarehouse());
			insertStatement.setBigDecimal(3, inventoryLogTemp.getQty());
			insertStatement.setString(4, inventoryLogTemp.getMutasi());
			insertStatement.setString(5, inventoryLogTemp.getSrcTable());
			insertStatement.setString(6, inventoryLogTemp.getConfirmCode());
			insertStatement.setDate(7, DateUtil.getCurrentDate());
			insertStatement.setString(8, "timotius");
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}
}
