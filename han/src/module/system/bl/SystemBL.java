package module.system.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.system.dao.GroupDAO;
import module.system.dao.UserDAO;
import module.system.model.Group;
import module.system.model.User;

public class SystemBL {

	private DataSource dataSource;
	
	public SystemBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Group> getAllGroup(){
		List<Group> groups = new ArrayList<Group>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			groups = new GroupDAO(connection).getAllGroup();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return groups;
	}
	
	public void saveGroup(Group group) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new GroupDAO(connection).insertGroup(group);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			users = new UserDAO(connection).getAllUser(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
