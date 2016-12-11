package module.purchaseprodresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.purchaseprodresult.model.PPRNote;
import module.util.DateUtil;

public class PPRNoteDAO {
	private Connection connection;
	private PreparedStatement getAllByPPRCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByPPRCodeQuery = new StringBuilder()
			.append("select pp.id, pp.ppr_code, pp.note ")
			.append("from ppr_note pp ")
			.append("where pp.ppr_code = ? and pp.deleted_date is null ").toString();

	private String insertQuery = new StringBuilder()
			.append("insert into ppr_note (ppr_code, note, ")
			.append("input_date, input_by) values (?,?,?,?)").toString();

	private String updateQuery = "update ppr_note set note=?, edit_date=?, edited_by=? where id=?";

	private String deleteQuery = "update ppr_note set deleted_date=?, deleted_by=? ";
	
	public PPRNoteDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	
	public List<PPRNote> getAllByPPRCode(String pprCode) throws SQLException {
		List<PPRNote> pprNotes = new ArrayList<PPRNote>();

		try {
			getAllByPPRCodeStatement = connection.prepareStatement(getAllByPPRCodeQuery);
			getAllByPPRCodeStatement.setString(1, pprCode);
			
			ResultSet rs = getAllByPPRCodeStatement.executeQuery();
			while (rs.next()) {
				PPRNote pprNote = new PPRNote();
				pprNote.setId(rs.getInt("id"));
				pprNote.setPprCode(rs.getString("ppr_code"));
				pprNote.setNote(rs.getString("note"));
				
				pprNotes.add(pprNote);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return pprNotes;
	}
	
	public void save(PPRNote pprNote) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, pprNote.getPprCode());
			insertStatement.setString(2, pprNote.getNote());
			insertStatement.setDate(3, DateUtil.getCurrentDate());
			insertStatement.setString(4, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(PPRNote pprNote) throws SQLException {
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
			String query = new StringBuilder().append(deleteQuery).append("where ppr_code=? ").toString();

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
