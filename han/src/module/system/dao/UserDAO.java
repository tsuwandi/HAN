package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.User;

public class UserDAO {

	private Connection connection;
	
	private PreparedStatement getAllUserStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllUserQuery;
	private String insertQuery;
	private String updateQuery;
	private String deleteQuery;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		
		try {
			getAllUserStatement = connection.prepareStatement(getAllUserQuery);
			
			ResultSet resultSet = getAllUserStatement.executeQuery();
			
			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt(""));
				user.setUserName(resultSet.getString(""));
				user.setUserPassword(resultSet.getString(""));
				user.setLastChanged(resultSet.getDate(""));
				user.setLastLogin(resultSet.getDate(""));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertUser(User user) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setInt(1, user.getUserId());
			insertStatement.setString(1, user.getUserName());
			insertStatement.setString(1, user.getUserPassword());
			//insertStatement.setDate(1, user.getLastChanged());
			//insertStatement.setDate(1, user.getLastLogin());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, user.getUserId());
			updateStatement.setString(1, user.getUserName());
			updateStatement.setString(1, user.getUserPassword());
			//insertStatement.setDate(1, user.getLastChanged());
			//insertStatement.setDate(1, user.getLastLogin());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(User user) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setInt(1, user.getUserId());
			deleteStatement.setString(1, user.getUserName());
			deleteStatement.setString(1, user.getUserPassword());
			//insertStatement.setDate(1, user.getLastChanged());
			//insertStatement.setDate(1, user.getLastLogin());
			
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
