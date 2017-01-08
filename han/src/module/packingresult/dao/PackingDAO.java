package module.packingresult.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.packingresult.model.Packing;


public class PackingDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getLastCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = "SELECT id, packing_date, status, confirm_code, confirm_date FROM packing WHERE deleted_date IS NULL";
			
	private String getLastIDQuery = "SELECT id FROM packing WHERE deleted_date IS NULL ORDER BY id DESC LIMIT 1";
	
	private String insertQuery = "INSERT INTO packing (id,packing_date, status, input_date, input_by) VALUES (?,?,?,?,?)";  
	private String updateQuery = "UPDATE packing SET packing_date=?, status=?, edited_by=?, edited_date=? WHERE id =?";
	
	private String deleteQuery = "UPDATE packing SET deleted_date = ? , delete_by=? WHERE id=?";
	
	public PackingDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public int getLastID() throws SQLException{
		int lastID = 0;
		try {
			getLastCodeStatement = connection.prepareStatement(getLastIDQuery);
			ResultSet rs = getLastCodeStatement.executeQuery();
			if(rs.next()) lastID =  rs.getInt("id");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastID;
	}
	
	public List<Packing> getAll() throws SQLException {
		List<Packing> packings = new ArrayList<Packing>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Packing packing = new Packing();
				packing.setId(rs.getInt("id"));
				packing.setPackingDate(rs.getDate("packing_date"));
				packing.setStatus(rs.getString("status"));
				packing.setConfirmCode(rs.getString("confirm_code"));
				packing.setConfirmDate(rs.getDate("confirm_date"));
				packings.add(packing);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return packings;
	}
	
	public List<Packing> getSearchAll(String sql) throws SQLException {
		List<Packing> packings = new ArrayList<Packing>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+sql);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Packing packing = new Packing();
				packing.setId(rs.getInt("id"));
				packing.setPackingDate(rs.getDate("packing_date"));
				packing.setStatus(rs.getString("status"));
				packing.setConfirmCode(rs.getString("confirm_code"));
				packing.setConfirmDate(rs.getDate("confirm_date"));
				packings.add(packing);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return packings;
	}


	public void save(Packing packing) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, packing.getId());
			insertStatement.setDate(2, new Date(packing.getPackingDate().getTime()));
			insertStatement.setString(3, packing.getStatus());
			insertStatement.setDate(4, new Date(new java.util.Date().getTime()));
			insertStatement.setString(5, "Michael");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(Packing packing) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, "Michael");
			deleteStatement.setInt(3, packing.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(Packing packing) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setDate(1, new Date(packing.getPackingDate().getTime()));
			updateStatement.setString(2, packing.getStatus());
			updateStatement.setString(3, "Michael");
			updateStatement.setDate(4, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(5, packing.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public List<Packing> advancedSearchProduction(String sql, List<Object> objs) throws SQLException{
		List<Packing> packings = new ArrayList<Packing>();

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

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Packing packing = new Packing();
				packing.setId(rs.getInt("id"));
				packing.setPackingDate(rs.getDate("packing_date"));
				packing.setStatus(rs.getString("status"));
				packing.setConfirmCode(rs.getString("confirm_code"));
				packing.setConfirmDate(rs.getDate("confirm_date"));
				packings.add(packing);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return packings;
	}
}
