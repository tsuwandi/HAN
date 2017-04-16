package module.stockopname.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;

public class SetSoScheduledDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	private PreparedStatement lastIDStatement;
	
	private String getAllQuery = "SELECT id, so_name, reccurance, day, date, so_type FROM set_so_schedule  WHERE deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE set_so_schedule SET deleted_date = ? , deleted_by=? WHERE id=?";
	
	private String insertQuery = "INSERT INTO set_so_schedule (so_name, reccurance, day, date, so_type, input_by, input_date,id) "
			+ "VALUES (?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE set_so_schedule SET so_name=?, reccurance=?, day=?, date=?, so_type=?, edited_by=?, edited_date=? "
			+ "WHERE id=?";
	
	private String deleteQuery = "DELETE FROM set_so_schedule WHERE id = ?";
	
	private String lastIDQuery = "SELECT ID from set_so_schedule ORDER BY ID DESC LIMIT 1";
	
	public SetSoScheduledDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<SetSOScheduled> getAll() throws SQLException {
		List<SetSOScheduled> setSoScheduleds = new ArrayList<SetSOScheduled>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				SetSOScheduled setSoScheduled = new SetSOScheduled();
				setSoScheduled.setId(rs.getInt("id"));
				setSoScheduled.setSoName(rs.getString("so_name"));
				setSoScheduled.setReccurence(rs.getString("reccurance"));
				setSoScheduled.setDay(rs.getString("day"));
				setSoScheduled.setDate(rs.getInt("date"));
				setSoScheduled.setSoType(rs.getString("so_type"));
				setSoScheduleds.add(setSoScheduled);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return setSoScheduleds;
	}
	
	public int getLastID() throws SQLException {
		int id=0;
		try {
			lastIDStatement = connection.prepareStatement(lastIDQuery);

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
	
	public List<SetSOScheduled> getAdvancedSearch(String sql, List<Object> objs) throws SQLException {
		List<SetSOScheduled> setSoScheduleds = new ArrayList<SetSOScheduled>();

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
				SetSOScheduled setSoScheduled = new SetSOScheduled();
				setSoScheduled.setId(rs.getInt("id"));
				setSoScheduled.setSoName(rs.getString("so_name"));
				setSoScheduled.setReccurence(rs.getString("reccurence"));
				setSoScheduled.setDay(rs.getString("day"));
				setSoScheduled.setDate(rs.getInt("date"));
				setSoScheduled.setSoType(rs.getString("so_type"));
				setSoScheduleds.add(setSoScheduled);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return setSoScheduleds;
	}
	
	public void save(SetSOScheduled setSoScheduledProduct) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, setSoScheduledProduct.getSoName());
			insertStatement.setString(2, setSoScheduledProduct.getReccurence());
			insertStatement.setString(3, setSoScheduledProduct.getDay());
			insertStatement.setInt(4, setSoScheduledProduct.getDate());
			insertStatement.setString(5, setSoScheduledProduct.getSoType());
			insertStatement.setString(6, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(7, new Date(new java.util.Date().getTime()));
			insertStatement.setInt(8, setSoScheduledProduct.getId());
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(SetSOScheduled setSoScheduledProduct) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, setSoScheduledProduct.getSoName());
			updateStatement.setString(2, setSoScheduledProduct.getReccurence());
			updateStatement.setString(3, setSoScheduledProduct.getDay());
			updateStatement.setInt(4, setSoScheduledProduct.getDate());
			updateStatement.setString(5, setSoScheduledProduct.getSoType());
			updateStatement.setString(6, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(7, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(8, setSoScheduledProduct.getId());
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
			updateDeleteStatement.setInt(3, setSoScheduledProduct.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
