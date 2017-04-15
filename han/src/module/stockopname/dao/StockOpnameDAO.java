package module.stockopname.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;
import module.stockopname.model.StockOpname;
import controller.ServiceFactory;

public class StockOpnameDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	private PreparedStatement lastIDStatement;
	
	private String getAllQuery = "SELECT a.id, so_name, so_date, so_type, status, confirm_code, confirm_date FROM stock_opname  WHERE deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE stock_opname SET deleted_date = ? , deleted_by=? WHERE id=?";
	
	private String insertQuery = "INSERT INTO stock_opname (so_name, so_date, so_type, status, input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?)";
	private String updateQuery = "UPDATE stock_opname SET so_name=?, so_date=?, so_type=?, status=?, edited_by=?, edited_date=? "
			+ "WHERE id=?";
	
	private String deleteQuery = "DELETE FROM stock_opname WHERE id = ?";
	
	private String getLastIDQuery = "SELECT id FROM stock_opname ORDER BY ID DESC LIMIT 1";
	
	public StockOpnameDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public int getLastID() throws SQLException{
		int id = 0;
		try {
			lastIDStatement = connection.prepareStatement(getLastIDQuery);

			ResultSet rs = lastIDStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return id;
	}
	
	
	public List<StockOpname> getAll() throws SQLException {
		List<StockOpname> stockOpnames = new ArrayList<StockOpname>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				StockOpname stockOpname = new StockOpname();
				stockOpname.setId(rs.getInt("id"));
				stockOpname.setSoName(rs.getString("so_name"));
				stockOpname.setSoDate(rs.getDate("so_date"));
				stockOpname.setStatus(rs.getString("status"));
				stockOpname.setSoType(rs.getString("so_type"));
				stockOpname.setConfirmCode(rs.getString("confirm_code"));
				stockOpname.setConfirmDate(rs.getDate("confirm_date"));
				stockOpnames.add(stockOpname);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return stockOpnames;
	}
	
	public List<StockOpname> getAdvancedSearch(String sql, List<Object> objs) throws SQLException {
		List<StockOpname> stockOpnames = new ArrayList<StockOpname>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery + sql);

			int i = 1;
			for (int j = 0; j < objs.size(); j++) {
				Object obj = objs.get(j);
				if (obj instanceof String) {
					if (obj != null) {
						getAllStatement.setString(i, (String) "%" + obj + "%");
						i++;
					}
				} else {
					if (obj != null) {
						getAllStatement.setDate(i, new Date(((java.util.Date) obj).getTime()));
						i++;
					}
				}
			}
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				StockOpname stockOpname = new StockOpname();
				stockOpname.setId(rs.getInt("id"));
				stockOpname.setSoName(rs.getString("so_name"));
				stockOpname.setSoDate(rs.getDate("so_date"));
				stockOpname.setStatus(rs.getString("status"));
				stockOpname.setSoType(rs.getString("so_type"));
				stockOpname.setConfirmCode(rs.getString("confirm_code"));
				stockOpname.setConfirmDate(rs.getDate("confirm_date"));
				stockOpnames.add(stockOpname);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return stockOpnames;
	}
	
	public void save(StockOpname stockOpname) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, stockOpname.getSoName());
			insertStatement.setDate(2, new Date(stockOpname.getSoDate().getTime()));
			insertStatement.setString(3, stockOpname.getSoType());
			insertStatement.setString(4, stockOpname.getStatus());
			insertStatement.setString(5, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(6, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(StockOpname stockOpname) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, stockOpname.getSoName());
			updateStatement.setDate(2, new Date(stockOpname.getSoDate().getTime()));
			updateStatement.setString(3, stockOpname.getSoType());
			updateStatement.setString(4, stockOpname.getStatus());
			updateStatement.setString(5, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(6, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(7, stockOpname.getId());
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
	public void updateDelete(StockOpname stockOpname) throws SQLException {
		try {
			updateDeleteStatement = connection.prepareStatement(updateDeleteQuery);
			updateDeleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			updateDeleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			updateDeleteStatement.setInt(3, stockOpname.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
