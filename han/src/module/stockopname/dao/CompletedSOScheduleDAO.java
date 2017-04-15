package module.stockopname.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.stockopname.model.CompletedSOSchedule;

public class CompletedSOScheduleDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement updateDeleteStatement;
	
	private String getAllQuery = "SELECT id, set_so_schedule_id, date FROM completed_so_schedule  WHERE deleted_date IS NULL";
	
	private String updateDeleteQuery = "UPDATE completed_so_schedule SET deleted_date = ? , deleted_by=? WHERE id=?";
	
	private String insertQuery = "INSERT INTO completed_so_schedule (set_so_schedule_id, date, input_by, input_date) "
			+ "VALUES (?,?,?,?)";
	private String updateQuery = "UPDATE completed_so_schedule SET set_so_scheduled_id=?, date=?, edited_by=?, edited_date=? "
			+ "WHERE id=?";
	
	private String deleteQuery = "DELETE FROM completed_so_schedule WHERE id = ?";
	
	
	public CompletedSOScheduleDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	public List<CompletedSOSchedule> getAll() throws SQLException {
		List<CompletedSOSchedule> setSoScheduleds = new ArrayList<CompletedSOSchedule>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				CompletedSOSchedule setSoScheduled = new CompletedSOSchedule();
				setSoScheduled.setId(rs.getInt("id"));
				setSoScheduled.setSetSOScheduleID(rs.getInt("set_so_schedule_id"));
				setSoScheduled.setDate(rs.getDate("date"));
				setSoScheduleds.add(setSoScheduled);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return setSoScheduleds;
	}
	
	
	public void save(CompletedSOSchedule completedSoSchedule) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, completedSoSchedule.getSetSOScheduleID());
			insertStatement.setDate(2, new Date(completedSoSchedule.getDate().getTime()));
			insertStatement.setString(3, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(4, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
	
	
	public void update(CompletedSOSchedule completedSoSchedule) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			insertStatement.setInt(1, completedSoSchedule.getSetSOScheduleID());
			insertStatement.setDate(2, new Date(completedSoSchedule.getDate().getTime()));
			updateStatement.setString(3, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(4, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(5, completedSoSchedule.getId());
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
	public void updateDelete(CompletedSOSchedule completedSoSchedule) throws SQLException {
		try {
			updateDeleteStatement = connection.prepareStatement(updateDeleteQuery);
			updateDeleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			updateDeleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			updateDeleteStatement.setInt(3, completedSoSchedule.getId());
			updateDeleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

	}
}
