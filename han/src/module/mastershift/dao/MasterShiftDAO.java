package module.mastershift.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.mastershift.model.MasterShift;


public class MasterShiftDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getLastCodeStatement;
	private PreparedStatement getLastIDStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = "SELECT * FROM shift WHERE deleted_date IS NULL";
	
	private String getLastCodeQuery = "SELECT shift_code FROM shift WHERE deleted_date IS NULL ORDER BY id DESC LIMIT 1";
	private String getLastIDQuery = "SELECT id FROM shift WHERE deleted_date IS NULL ORDER BY id DESC LIMIT 1";
	private String insertQuery = "INSERT INTO shift (id, shift_code, shift_name, type, input_by, input_date) "
			+ "VALUES (?,?,?,?,?,?)";
	private String updateQuery = "UPDATE shift SET shift_code =?, "
			+ "shift_name=?, type=?, edited_by=?, edited_date=? "
			+ "WHERE id =?";
	
	private String deleteQuery = "UPDATE shift SET deleted_date = ? , delete_by=? WHERE id=?";
	
	public MasterShiftDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public String getLastCode() throws SQLException{
		String lastCode = null;
		try {
			getLastCodeStatement = connection.prepareStatement(getLastCodeQuery);
			ResultSet rs = getLastCodeStatement.executeQuery();
			if(rs.next()) lastCode =  rs.getString("shift_code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
	
	public int getLastID() throws SQLException{
		int lastID = 0;
		try {
			getLastIDStatement = connection.prepareStatement(getLastIDQuery);
			ResultSet rs = getLastIDStatement.executeQuery();
			if(rs.next()) lastID =  rs.getInt("id");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastID;
	}
	
	public List<MasterShift> getAll() throws SQLException {
		List<MasterShift> shifts = new ArrayList<MasterShift>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				MasterShift shift = new MasterShift();
				shift.setId(rs.getInt("id"));
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));
				shift.setType(rs.getString("type"));
				shift.setInputDate(rs.getDate("input_date"));
				shift.setEditedDate(rs.getDate("edited_date"));
				shifts.add(shift);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return shifts;
	}
	
	public List<MasterShift> getSearchAll(String sql) throws SQLException {
		List<MasterShift> shifts = new ArrayList<MasterShift>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+sql);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				MasterShift shift = new MasterShift();
				shift.setId(rs.getInt("id"));
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));
				shift.setType(rs.getString("type"));
				shift.setInputDate(rs.getDate("input_date"));
				shift.setEditedDate(rs.getDate("edited_date"));
				shifts.add(shift);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return shifts;
	}


	public void save(MasterShift shift) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, shift.getId());
			insertStatement.setString(2, shift.getShiftCode());
			insertStatement.setString(3, shift.getShiftName());
			insertStatement.setString(4, shift.getType());
			insertStatement.setString(5, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(6, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void delete(MasterShift shift) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, shift.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	public void update(MasterShift shift) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, shift.getShiftCode());
			updateStatement.setString(2, shift.getShiftName());
			updateStatement.setString(3, shift.getType());
			updateStatement.setString(4, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(5, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(6, shift.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public List<MasterShift> advancedSearchProduction(String sql, List<Object> objs) throws SQLException{
		List<MasterShift> shifts = new ArrayList<MasterShift>();

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
				MasterShift shift = new MasterShift();
				shift.setId(rs.getInt("id"));
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));
				shift.setType(rs.getString("type"));
				shift.setInputDate(rs.getDate("input_date"));
				shift.setEditedDate(rs.getDate("edited_date"));
				shifts.add(shift);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return shifts;
	}
}
