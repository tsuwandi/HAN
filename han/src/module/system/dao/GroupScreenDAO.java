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
	
	private String getAllGroupScreenQuery = "";
	private String insertQuery = "";
	private String updateQuery = "";
	private String deleteQuery = "";
	
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
				groupScreen.setGroupScreenId(resultSet.getInt(""));
				groupScreen.setGroupId(resultSet.getInt(""));
				groupScreen.setScreenId(resultSet.getInt(""));
				groupScreen.setScreenName(resultSet.getString(""));
				groupScreen.setAccess(resultSet.getBoolean(""));
				
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
			
			insertStatement.setInt(1, groupScreen.getGroupScreenId());
			insertStatement.setInt(2, groupScreen.getGroupId());
			insertStatement.setInt(3, groupScreen.getScreenId());
			insertStatement.setString(4, groupScreen.getScreenName());
			insertStatement.setBoolean(5, groupScreen.getAccess());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGroupScreen(GroupScreen groupScreen){
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, groupScreen.getGroupScreenId());
			updateStatement.setInt(2, groupScreen.getGroupId());
			updateStatement.setInt(3, groupScreen.getScreenId());
			updateStatement.setString(4, groupScreen.getScreenName());
			updateStatement.setBoolean(5, groupScreen.getAccess());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGroupScreen(GroupScreen groupScreen){
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setInt(1, groupScreen.getGroupScreenId());
			deleteStatement.setInt(2, groupScreen.getGroupId());
			deleteStatement.setInt(3, groupScreen.getScreenId());
			deleteStatement.setString(4, groupScreen.getScreenName());
			deleteStatement.setBoolean(5, groupScreen.getAccess());
			
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
