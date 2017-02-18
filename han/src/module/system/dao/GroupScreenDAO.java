package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.GroupScreen;

public class GroupScreenDAO {

	private Connection connection;
	
	private PreparedStatement getAllGroupScreenStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllGroupScreenQuery = "select * from group_screen where delete_date is null and delete_by is null";
	private String insertQuery = "insert into group_screen(group_id,screen_id,screen_name,right_access,input_date,input_by,edit_date,edit_by)VALUES(?,?,?,?,?,?,?,?)";
	private String updateQuery = "update group_screen set group_id = ?, screen_id = ?, screen_name = ?, right_access = ?, input_date = ?, input_by = ?, edit_date = ?,edit_by = ?,delete_date = ?,delete_by = ? WHERE id = ?";
	private String deleteQuery = "update group_screen set delete_date = ?, delete_by = ? where id = ?";
	
	public GroupScreenDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<GroupScreen> getAllGroupScreen() {
		List<GroupScreen> groupScreens = new ArrayList<GroupScreen>();
		
		try {
			getAllGroupScreenStatement = connection.prepareStatement(getAllGroupScreenQuery);
			
			ResultSet resultSet = getAllGroupScreenStatement.executeQuery();
			
			while (resultSet.next()) {
				GroupScreen groupScreen = new GroupScreen();
				groupScreen.setId(resultSet.getInt("id"));
				groupScreen.setGroupId(resultSet.getInt("group_id"));
				groupScreen.setScreenId(resultSet.getInt("screen_id"));
				groupScreen.setScreenName(resultSet.getString("screen_name"));
				groupScreen.setRightAccess(resultSet.getString("right_access"));
				groupScreen.setInputDate(resultSet.getDate("input_date"));
				groupScreen.setInputBy(resultSet.getString("input_by"));
				groupScreen.setEditDate(resultSet.getDate("edit_date"));
				groupScreen.setEditedBy(resultSet.getString("edit_by"));
				
				groupScreens.add(groupScreen);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return groupScreens;
	}
	
	public void insertGroupScreen(GroupScreen groupScreen){
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setInt(1, groupScreen.getId());
			insertStatement.setInt(2, groupScreen.getGroupId());
			insertStatement.setInt(3, groupScreen.getScreenId());
			insertStatement.setString(4, groupScreen.getScreenName());
			insertStatement.setString(5, groupScreen.getRightAccess());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGroupScreen(GroupScreen groupScreen){
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, groupScreen.getId());
			updateStatement.setInt(2, groupScreen.getGroupId());
			updateStatement.setInt(3, groupScreen.getScreenId());
			updateStatement.setString(4, groupScreen.getScreenName());
			updateStatement.setString(5, groupScreen.getRightAccess());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGroupScreen(GroupScreen groupScreen){
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setInt(1, groupScreen.getId());
			deleteStatement.setInt(2, groupScreen.getGroupId());
			deleteStatement.setInt(3, groupScreen.getScreenId());
			deleteStatement.setString(4, groupScreen.getScreenName());
			deleteStatement.setString(5, groupScreen.getRightAccess());
			
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}