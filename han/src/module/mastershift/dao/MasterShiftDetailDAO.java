package module.mastershift.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.mastershift.model.MasterShiftDetail;


public class MasterShiftDetailDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllQuery = "SELECT * FROM shift_detail WHERE deleted_date IS NULL AND shift_id=? ";
	private String insertQuery = "INSERT INTO shift_detail (shift_id,day,week,`in`,`out`,holiday,rest,input_by,input_date) "
			+ "VALUES(?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "UPDATE shift_detail SET day =?, week=?, `in`=?, `out`=?, holiday=?, rest=?, edited_by=?, edit_date=? "
			+ "WHERE id =? AND shift_id = ?";
	
	private String deleteQuery = "UPDATE shift_detail SET deleted_date = ? , delete_by=? WHERE id=? AND shift_id=?";
	
	public MasterShiftDetailDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	
	
	public List<MasterShiftDetail> getAllByShiftID(int id) throws SQLException {
		List<MasterShiftDetail> shifts = new ArrayList<MasterShiftDetail>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			getAllStatement.setInt(1, id);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				MasterShiftDetail shift = new MasterShiftDetail();
				shift.setId(rs.getInt("id"));
				shift.setShiftID(rs.getInt("shift_id"));
				shift.setDay(rs.getString("day"));
				shift.setWeek(rs.getInt("week"));
				shift.setIn(rs.getString("in"));
				shift.setOut(rs.getString("out"));
				shift.setHoliday(rs.getString("holiday"));
				shift.setRest(rs.getInt("rest"));
				shifts.add(shift);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return shifts;
	}
	
	

	public void save(MasterShiftDetail masterShiftDetail) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, masterShiftDetail.getShiftID());
			insertStatement.setString(2, masterShiftDetail.getDay());
			insertStatement.setInt(3, masterShiftDetail.getWeek());
			insertStatement.setString(4, masterShiftDetail.getIn());
			insertStatement.setString(5, masterShiftDetail.getOut());
			insertStatement.setString(6, masterShiftDetail.getHoliday());
			insertStatement.setInt(7, masterShiftDetail.getRest());
			insertStatement.setString(8, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.setDate(9, new Date(new java.util.Date().getTime()));
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}

	}
	
	public void delete(MasterShiftDetail masterShiftDetail) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, new Date(new java.util.Date().getTime()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, masterShiftDetail.getId());
			deleteStatement.setInt(4, masterShiftDetail.getShiftID());
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public void update(MasterShiftDetail masterShiftDetail) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, masterShiftDetail.getDay());
			updateStatement.setInt(2, masterShiftDetail.getWeek());
			updateStatement.setString(3, masterShiftDetail.getIn());
			updateStatement.setString(4, masterShiftDetail.getOut());
			updateStatement.setString(5, masterShiftDetail.getHoliday());
			updateStatement.setInt(6, masterShiftDetail.getRest());
			updateStatement.setString(7, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setDate(8, new Date(new java.util.Date().getTime()));
			updateStatement.setInt(9, masterShiftDetail.getId());
			updateStatement.setInt(10, masterShiftDetail.getShiftID());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
}
