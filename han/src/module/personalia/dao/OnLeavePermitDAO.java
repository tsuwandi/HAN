package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Division;
import module.personalia.model.OnLeavePermit;
import module.util.DateUtil;

public class OnLeavePermitDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from division order by id desc limit 1";
	private String getAllQuery = "select * from division where delete_date is null and delete_by is null";
	private String insertQuery = "insert into division (id, name, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update division set name = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update division set delete_date = ?, delete_by = ? where id = ?";

	public OnLeavePermitDAO(Connection connection) {
		this.connection = connection;
	}

	public List<OnLeavePermit> getAllData(String query){
		List<OnLeavePermit> onLeavePermits = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				OnLeavePermit onLeavePermit = new OnLeavePermit();
				onLeavePermit.setId(resultSet.getInt("id"));
				onLeavePermit.setOnleavepermitCode(resultSet.getString("onleavepermit_code"));
				onLeavePermit.setType(resultSet.getString("type"));
				onLeavePermit.setTimeStart(resultSet.getDate("time_start"));
				onLeavePermit.setTimeEnd(resultSet.getDate("time_end"));
				onLeavePermit.setDateStart(resultSet.getDate("date_start"));
				onLeavePermit.setDateEnd(resultSet.getDate("date_end"));
				onLeavePermit.setNotes(resultSet.getString("notes"));
				onLeavePermit.setDocumentReference(resultSet.getString("document_reference"));
				onLeavePermit.setEmpCode(resultSet.getString("emp_code"));
				onLeavePermit.setInputDate(resultSet.getDate("input_date"));
				onLeavePermit.setInputBy(resultSet.getString("input_by"));
				onLeavePermit.setEditDate(resultSet.getDate("edit_date"));
				onLeavePermit.setEditBy(resultSet.getString("edit_by"));
				onLeavePermit.setDeleteDate(resultSet.getDate("delete_date"));
				onLeavePermit.setDeleteBy(resultSet.getString("delete_by"));
				onLeavePermits.add(onLeavePermit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return onLeavePermits;
	}

	public void insert(OnLeavePermit onLeavePermit) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, onLeavePermit.getOnleavepermitCode());
			insertStatement.setString(2, onLeavePermit.getType());
			insertStatement.setDate(3, DateUtil.toDate(onLeavePermit.getTimeStart()));
			insertStatement.setDate(4, DateUtil.toDate(onLeavePermit.getTimeEnd()));
			insertStatement.setDate(5, DateUtil.toDate(onLeavePermit.getDateStart()));
			insertStatement.setDate(6, DateUtil.toDate(onLeavePermit.getDateEnd()));
			insertStatement.setString(7, onLeavePermit.getNotes());
			insertStatement.setString(8, onLeavePermit.getDocumentReference());
			insertStatement.setString(9, onLeavePermit.getEmpCode());
			insertStatement.setDate(10, DateUtil.toDate(onLeavePermit.getInputDate()));
			insertStatement.setString(11, onLeavePermit.getInputBy());
			insertStatement.setDate(12, DateUtil.toDate(onLeavePermit.getEditDate()));
			insertStatement.setString(13, onLeavePermit.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Division division) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, division.getName());
			updateStatement.setDate(2, DateUtil.toDate(division.getEditDate()));
			updateStatement.setString(3, division.getEditBy());
			updateStatement.setString(4, division.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Division division) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(division.getDeleteDate()));
			deleteStatement.setString(2, division.getDeleteBy());
			deleteStatement.setString(3, division.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<Division> divisions = null;
		
		try {
			divisions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				Division division = new Division();
				division.setId(resultSet.getString("id"));
				division.setName(resultSet.getString("name"));

				divisions.add(division);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return divisions.size() < 1 ? 1 : Integer.parseInt(divisions.get(0).getId().substring(3))+1;
	}
}
