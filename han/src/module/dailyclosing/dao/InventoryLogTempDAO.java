package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dailyclosing.model.Inventory;
import module.dailyclosing.model.InventoryLogTemp;
import module.util.DateUtil;

public class InventoryLogTempDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement updateStatusConfirmDateStatement;
	
	private String insertQuery = "insert into inventory_log_temp (product_code, warehouse, qty, mutasi, srctable, confirm_code, "
			+ "input_date, input_by) values (?,?,?,?,?,?,?,?)";
	
	private String getAllQuery = new StringBuilder().append("select id, product_code, warehouse, qty, mutasi, srctable, confirm_code ")
			.append("from inventory_log_temp where input_date <= curdate() and confirm_date is null ").toString();
	
	private String updateStatusConfirmDateQuery = "update inventory_log_temp set confirm_date = ?, edit_date =?, edited_by =? where id = ?";
	
	public InventoryLogTempDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public void save(InventoryLogTemp inventoryLogTemp) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, inventoryLogTemp.getProductCode());
			insertStatement.setInt(2, inventoryLogTemp.getWarehouse());
			insertStatement.setDouble(3, inventoryLogTemp.getQty());
			insertStatement.setString(4, inventoryLogTemp.getMutasi());
			insertStatement.setString(5, inventoryLogTemp.getSrcTable());
			insertStatement.setString(6, inventoryLogTemp.getConfirmCode());
			insertStatement.setDate(7, DateUtil.getCurrentDate());
			insertStatement.setString(8, "timotius");
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<InventoryLogTemp> getAll() throws SQLException {
		List<InventoryLogTemp> inventoryLogTemps = new ArrayList<InventoryLogTemp>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				InventoryLogTemp inventoryLogTemp = new InventoryLogTemp();
				inventoryLogTemp.setId(rs.getInt("id"));
				inventoryLogTemp.setProductCode(rs.getString("product_code"));
				inventoryLogTemp.setWarehouse(rs.getInt("warehouse"));
				inventoryLogTemp.setQty(rs.getDouble("qty"));
				inventoryLogTemp.setMutasi(rs.getString("mutasi"));
				inventoryLogTemp.setSrcTable(rs.getString("srctable"));
				inventoryLogTemp.setConfirmCode(rs.getString("confirm_code"));
				inventoryLogTemps.add(inventoryLogTemp);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return inventoryLogTemps;
	}
	
	public void updateStatusConfirmDate(InventoryLogTemp inventoryLogTemp) throws SQLException {
		try {
			updateStatusConfirmDateStatement = connection.prepareStatement(updateStatusConfirmDateQuery);
			updateStatusConfirmDateStatement.setDate(1, DateUtil.getCurrentDate());
			updateStatusConfirmDateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatusConfirmDateStatement.setString(3, "timotius");
			updateStatusConfirmDateStatement.setInt(4, inventoryLogTemp.getId());
			updateStatusConfirmDateStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
