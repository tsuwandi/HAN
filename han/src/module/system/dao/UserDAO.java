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
	private String insertQuery = "insert into user (group, name, password, last_login, last_change) values (?, ?, ?, ?, ?)";
	private String updateQuery = "update user set group = ?, name = ?, password = ?, last_login = ?, last_change = ? where id = ?";
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
				user.setGroupId(resultSet.getInt("group"));
				user.setUserName(resultSet.getString("name"));
				user.setUserPassword(resultSet.getString("password"));
				user.setLastChanged(resultSet.getDate("last_change"));
				user.setLastLogin(resultSet.getDate("last_login"));
				
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
			
			insertStatement.setInt(1, user.getGroupId());
			insertStatement.setString(2, user.getUserName());
			insertStatement.setString(3, user.getUserPassword());
			insertStatement.setDate(4, (Date) user.getLastChanged());
			insertStatement.setDate(5, (Date) user.getLastLogin());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, user.getGroupId());
			updateStatement.setString(2, user.getUserName());
			updateStatement.setString(3, user.getUserPassword());
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
