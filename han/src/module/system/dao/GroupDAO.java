package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.Group;
import module.util.DateUtil;

public class GroupDAO {

	private Connection connection;
	
	private PreparedStatement getAllGroupStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllGroupQuery = "select * from system_group where delete_date is null and delete_by is null";
	private String insertQuery = "insert into system_group (name, description, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update system_group set name = ? , description = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update system_group set delete_date = ?, delete_by = ? where id = ?";
	
	public GroupDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<Group> getAll() {
		List<Group> groups = new ArrayList<Group>();
		try {
			getAllGroupStatement = connection.prepareStatement(getAllGroupQuery);
			
			ResultSet resultSet = getAllGroupStatement.executeQuery();
			
			while (resultSet.next()) {
				Group group = new Group();
				group.setGroupId(resultSet.getInt("id"));
				group.setGroupName(resultSet.getString("name"));
				group.setGroupDesc(resultSet.getString("description"));
				group.setInputDate(resultSet.getDate("input_date"));
				group.setInputBy(resultSet.getString("input_by"));
				group.setEditDate(resultSet.getDate("edit_date"));
				group.setEditedBy(resultSet.getString("edit_by"));
				groups.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	public void insert(Group group) {
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setString(1, group.getGroupName());
			insertStatement.setString(2, group.getGroupDesc());
			insertStatement.setDate(3, DateUtil.toDate(group.getInputDate()));
			insertStatement.setString(4, group.getInputBy());
			insertStatement.setDate(5, DateUtil.toDate(group.getEditDate()));
			insertStatement.setString(6, group.getEditedBy());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Group group) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setString(1, group.getGroupName());
			updateStatement.setString(2, group.getGroupDesc());
			updateStatement.setDate(3, DateUtil.toDate(group.getEditDate()));
			updateStatement.setString(4, group.getEditedBy());
			updateStatement.setInt(5, group.getGroupId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Group group) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setDate(1, DateUtil.toDate(group.getDeletedDate()));
			deleteStatement.setString(2, group.getDeletedBy());
			deleteStatement.setInt(3, group.getGroupId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}