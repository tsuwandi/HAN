package module.receiveprodresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.receiveprodresult.model.RPRNote;
import module.util.DateUtil;

public class RPRNoteDAO {
	private Connection connection;
	private PreparedStatement getAllByRPRCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByRPRCodeQuery = new StringBuilder()
			.append("select pp.id, pp.rpr_code, pp.note ")
			.append("from rpr_note pp ")
			.append("where pp.rpr_code = ? and pp.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder()
			.append("insert into rpr_note (rpr_code, note, ")
			.append("input_date, input_by) values (?,?,?,?)").toString();

	private String updateQuery = "update rpr_note set note=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update rpr_note set deleted_date=?, deleted_by=? ";
	
	public RPRNoteDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	
	public List<RPRNote> getAllByRPRCode(String pprCode) throws SQLException {
		List<RPRNote> pprNotes = new ArrayList<RPRNote>();

		try {
			getAllByRPRCodeStatement = connection.prepareStatement(getAllByRPRCodeQuery);
			getAllByRPRCodeStatement.setString(1, pprCode);
			
			ResultSet rs = getAllByRPRCodeStatement.executeQuery();
			while (rs.next()) {
				RPRNote pprNote = new RPRNote();
				pprNote.setId(rs.getInt("id"));
				pprNote.setRprCode(rs.getString("rpr_code"));
				pprNote.setNote(rs.getString("note"));
				
				pprNotes.add(pprNote);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pprNotes;
	}
	
	public void save(RPRNote pprNote) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, pprNote.getRprCode());
			insertStatement.setString(2, pprNote.getNote());
			insertStatement.setDate(3, DateUtil.getCurrentDate());
			insertStatement.setString(4, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(RPRNote pprNote) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, pprNote.getNote());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "timotius");
			updateStatement.setInt(4, pprNote.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteAll(String pprCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where rpr_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, pprCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
