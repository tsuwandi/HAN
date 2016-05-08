package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.User;

public class UserDao {
	private DataSource dataSource;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement getUserByIdAndPassword;
	private String insertQuery =
			"insert into users (employee_id, name, address, phone_no, email, password) " +
			"values (?,?,?,?,?,?)";
	private String updateQuery =
			"update users set name=?, address=?, phone_no=?, email=?, password=? " +
			"where employee_id=?";
	private String deleteQuery =
			"delete from users where employee_id=?";
	private String getAllQuery =
			"select * from users order by employee_id";
	private String getUserByIdPasswordQuery =
			"select * from users where employee_id=? and password=md5(?)";
	
	public UserDao(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;		
	}
	
	public void save(User user) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		insertStatement = con.prepareStatement(insertQuery);
    		insertStatement.setString(1, user.getEmployeeId());
    		insertStatement.setString(2, user.getName());
    		insertStatement.setString(3, user.getAddress());
    		insertStatement.setString(4, user.getPhoneNo());
    		insertStatement.setString(5, user.getEmail());
    		insertStatement.setString(6, user.getPassword());
    		insertStatement.executeUpdate();
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public void update(User user) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		
    		updateStatement = con.prepareStatement(updateQuery);	    		
    		updateStatement.setString(1, user.getName());
    		updateStatement.setString(2, user.getAddress());
    		updateStatement.setString(3, user.getPhoneNo());
    		updateStatement.setString(4, user.getEmail());
    		updateStatement.setString(5, user.getPassword());
    		updateStatement.setString(6, user.getEmployeeId());
    		updateStatement.executeUpdate();
            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public void delete(String employeeId) throws SQLException {
        Connection con = null;
    	try {
    		con = dataSource.getConnection();
    		deleteStatement = con.prepareStatement(deleteQuery);

    		deleteStatement.setString(1, employeeId);
    		deleteStatement.executeUpdate();

        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
	}
	
	public List<User> getAll() throws SQLException {
        Connection con = null;
        List<User> users = new ArrayList<User>();
        
        try {
    		con = dataSource.getConnection();
    		getAllStatement = con.prepareStatement(getAllQuery);

    		ResultSet rs = getAllStatement.executeQuery();
    		while(rs.next()) {
    			User user = new User();
    			user.setEmployeeId(rs.getString("employee_id"));
    			user.setName(rs.getString("name"));
    			user.setAddress(rs.getString("address"));
    			user.setPhoneNo(rs.getString("phone_no"));
    			user.setEmail(rs.getString("email"));
    			user.setPassword(rs.getString("password"));
    			
    			users.add(user);
    		}

        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
		
		return users;
	}
	
	public User getUserByIdAndPassword(String nik, String password) throws SQLException {
        Connection con = null;
		User user = null;
        
    	try {
    		con = dataSource.getConnection();
    		getUserByIdAndPassword= con.prepareStatement(getUserByIdPasswordQuery);
    		
    		getUserByIdAndPassword.setString(1, nik);
    		getUserByIdAndPassword.setString(2, password);
    		//System.out.println(getUserByIdAndPassword.toString());
    		ResultSet rs = getUserByIdAndPassword.executeQuery();

    		while(rs.next()) {
    			user = new User();
    			user.setEmployeeId(rs.getString("employee_id"));
    			user.setName(rs.getString("name"));
    			user.setAddress(rs.getString("address"));
    			user.setPhoneNo(rs.getString("phone_no"));
    			user.setEmail(rs.getString("email"));
    			user.setPassword(rs.getString("password"));
    		}

        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new SQLException(ex.getMessage());
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
			}
        }
		
		return user;
	}
}
