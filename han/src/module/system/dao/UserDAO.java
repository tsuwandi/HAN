package module.system.dao;

import java.sql.Connection;
import java.sql.Date;
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
	
	private String getAllUserQuery = "select * from user";
	private String insertQuery = "insert into user (username, password, group_id, last_change, last_login) values (?, ?, ?, ?, ?)";
	private String updateQuery = "update user set username = ?, password = ?, group = ?,last_login = ?, last_change = ? where id = ?";
	private String deleteQuery = "delete from user where id = ?";
	
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
				user.setUserId(resultSet.getInt("id"));
				user.setGroupId(resultSet.getInt("group_id"));
				user.setUserName(resultSet.getString("username"));
				user.setUserPassword(resultSet.getString("password"));
				user.setLastChanged(resultSet.getDate("last_change"));
				user.setLastLogin(resultSet.getDate("last_login"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public void insertUser(User user) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setString(1, user.getUserName());
			insertStatement.setString(2, user.getUserPassword());
			insertStatement.setInt(3, user.getGroupId());
			
			insertStatement.setDate(4, user.getLastChanged());
			insertStatement.setDate(5, user.getLastLogin());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setString(1, user.getUserName());
			updateStatement.setString(2, user.getUserPassword());
			updateStatement.setInt(3, user.getGroupId());
			updateStatement.setDate(4, (Date) user.getLastChanged());
			updateStatement.setDate(5, (Date) user.getLastLogin());
			updateStatement.setInt(6, user.getUserId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(User user) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setInt(1, user.getUserId());
			
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
