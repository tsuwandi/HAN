package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.Login;
import module.util.DateUtil;

public class LoginDAO {

private Connection connection;
	
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement validateStatement;
	
	private String getAllQuery = "select * from login order by last_login asc";
	private String insertQuery = "insert into login(username,employee_id,last_login,login_status,input_date,input_by,edit_date,edit_by) VALUES (?,?,?,?,?,?,?,?)";
	private String updateQuery = "update login set username = ?, employee_id = ?, last_login = ?, login_status = ?, edit_date = ?, edit_by = ? WHERE id = ?";
	private String deleteQuery = "update login set delete_date = ?, delete_by = ? where id = ?";
	private String validateQuery = "select * from login where username = ?";
	
	public LoginDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Login> getAll() {
		List<Login> logins = new ArrayList<Login>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			
			ResultSet resultSet = getAllStatement.executeQuery();
			
			while (resultSet.next()) {
				Login login = new Login();
				login.setId(resultSet.getInt("id"));
				login.setUsername(resultSet.getString("username"));
				login.setEmployeeId(resultSet.getString("employee_id"));
				login.setLastLogin(resultSet.getDate("last_login"));
				login.setLoginStatus(resultSet.getString("login_status"));
				login.setInputDate(resultSet.getDate("input_date"));
				login.setInputBy(resultSet.getString("input_by"));
				login.setEditedBy(resultSet.getString("edit_by"));
				login.setEditDate(resultSet.getDate("edit_date"));
				login.setDeletedBy(resultSet.getString("delete_by"));
				login.setDeletedDate(resultSet.getDate("delete_date"));
				
				logins.add(login);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logins;
	}
	
	public void insert(Login login) {
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setString(1, login.getUsername());
			insertStatement.setString(2, login.getEmployeeId());
			insertStatement.setDate(3, DateUtil.toDate(login.getLastLogin()));
			insertStatement.setString(4, login.getLoginStatus());
			insertStatement.setDate(5, DateUtil.toDate(login.getInputDate()));
			insertStatement.setString(6, login.getInputBy());
			insertStatement.setDate(7, DateUtil.toDate(login.getEditDate()));
			insertStatement.setString(8, login.getEditedBy());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Login login) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setString(1, login.getUsername());
			updateStatement.setString(2, login.getEmployeeId());
			updateStatement.setDate(3, DateUtil.toDate(login.getLastLogin()));
			updateStatement.setString(4, login.getLoginStatus());
			updateStatement.setDate(5, DateUtil.toDate(login.getEditDate()));
			updateStatement.setString(6, login.getEditedBy());
			updateStatement.setInt(7, login.getId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Login login) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setDate(1, DateUtil.toDate(login.getDeletedDate()));
			deleteStatement.setString(2, login.getDeletedBy());
			deleteStatement.setInt(3, login.getId());
			
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Login validate(Login login) {
		Login result = null;
		try {
			validateStatement = connection.prepareStatement(validateQuery);
			
			validateStatement.setString(1, login.getUsername());
			
			ResultSet resultSet = validateStatement.executeQuery();
			
			while (resultSet.next()) {
				result = new Login();
				result.setId(resultSet.getInt("id"));
				result.setUsername(resultSet.getString("username"));
				result.setEmployeeId(resultSet.getString("employee_id"));
				result.setLastLogin(resultSet.getDate("last_login"));
				result.setLoginStatus(resultSet.getString("login_status"));
				result.setInputDate(resultSet.getDate("input_date"));
				result.setInputBy(resultSet.getString("input_by"));
				result.setEditedBy(resultSet.getString("edit_by"));
				result.setEditDate(resultSet.getDate("edit_date"));
				result.setDeletedBy(resultSet.getString("delete_by"));
				result.setDeletedDate(resultSet.getDate("delete_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
}