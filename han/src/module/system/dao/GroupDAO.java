package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.Group;

public class GroupDAO {

	private Connection connection;
	
	private PreparedStatement getAllGroupStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllGroupQuery = "select * from system_group";
	private String insertQuery = "insert into system_group (name, description) values (?, ?)";
	private String updateQuery = "update system_group set name = ? , description = ? where id = ?";
	private String deleteQuery = "delete from system_group where id = ?";
	
	public GroupDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<Group> getAllGroup() {
		List<Group> groups = new ArrayList<Group>();
		try {
			getAllGroupStatement = connection.prepareStatement(getAllGroupQuery);
			
			ResultSet resultSet = getAllGroupStatement.executeQuery();
			
			while (resultSet.next()) {
				Group group = new Group();
				group.setGroupId(resultSet.getInt("id"));
				group.setGroupName(resultSet.getString("name"));
				group.setGroupDesc(resultSet.getString("description"));
				groups.add(group);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return groups;
	}
	
	public void insertGroup(Group group) {
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setString(1, group.getGroupName());
			insertStatement.setString(2, group.getGroupDesc());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGroup(Group group) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, group.getGroupId());
			updateStatement.setString(2, group.getGroupName());
			updateStatement.setString(3, group.getGroupDesc());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGroup(Group group) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setInt(1, group.getGroupId());
			deleteStatement.setString(2, group.getGroupName());
			deleteStatement.setString(3, group.getGroupDesc());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
