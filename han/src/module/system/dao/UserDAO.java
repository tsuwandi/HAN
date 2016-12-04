package module.system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.User;
import module.util.DateUtil;

public class UserDAO {

	private Connection connection;
	
	private PreparedStatement getAllUserStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement validateUserStatement;
	
	private String getAllUserQuery = "select * from user";
	private String insertQuery = "insert into user (group_id, username, password, employee_id, last_login, input_date, input_by, edit_date, edit_by) values (?, ?, SHA2(?,512), ?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update user set group = ?, username = ?, password = SHA2(?,512), employee_id = ?, last_login = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update user set delete_date = ?, delete_by = ? where id = ?";
	private String validateUserQuery = "select * from user where username = ? and password = SHA2(?,512)";
	
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
				user.setId(resultSet.getInt("id"));
				user.setGroupId(resultSet.getInt("group_id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmployeeId(resultSet.getString("employee_id"));
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
			
			insertStatement.setInt(1, user.getGroupId());
			insertStatement.setString(2, user.getUsername());
			insertStatement.setString(3, user.getPassword());
			insertStatement.setString(4, user.getEmployeeId());
			insertStatement.setDate(5, DateUtil.toDate(user.getLastLogin()));
			insertStatement.setDate(6, DateUtil.toDate(user.getInputDate()));
			insertStatement.setString(7, user.getInputBy());
			insertStatement.setDate(8, DateUtil.toDate(user.getEditDate()));
			insertStatement.setString(9, user.getEditedBy());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, user.getGroupId());
			updateStatement.setString(1, user.getUsername());
			updateStatement.setString(2, user.getPassword());
			updateStatement.setString(4, user.getEmployeeId());
			updateStatement.setDate(5, (Date) user.getLastLogin());
			updateStatement.setDate(6, DateUtil.toDate(user.getEditDate()));
			updateStatement.setString(7, user.getEditedBy());
			updateStatement.setInt(8, user.getId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(User user) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setDate(1, DateUtil.toDate(user.getDeletedDate()));
			deleteStatement.setString(2, user.getDeletedBy());
			deleteStatement.setInt(3, user.getId());
			
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User selectUser(User user) {
		try {
			validateUserStatement = connection.prepareStatement(validateUserQuery);
			validateUserStatement.setString(1, user.getUsername());
			validateUserStatement.setString(2, user.getPassword());
			
			ResultSet resultSet = validateUserStatement.executeQuery();
			
			List<User> results = new ArrayList<>();
			
			while (resultSet.next()) {
				User result = new User();
				result.setId(resultSet.getInt("id"));
				result.setGroupId(resultSet.getInt("group_id"));
				result.setUsername(resultSet.getString("username"));
				result.setPassword(resultSet.getString("password"));
				result.setEmployeeId(resultSet.getString("employee_id"));
				result.setLastLogin(resultSet.getDate("last_login"));
				
				results.add(result);
			}
			
			return results.size()>0 ? results.get(0):null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
