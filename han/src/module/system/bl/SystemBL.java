package module.system.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import module.system.dao.CommonConfigDAO;
import module.system.dao.GroupDAO;
import module.system.dao.LoginDAO;
import module.system.dao.MenuDAO;
import module.system.dao.ScreenDAO;
import module.system.dao.UserDAO;
import module.system.dao.VersionDAO;
import module.system.model.CommonConfig;
import module.system.model.Group;
import module.system.model.Login;
import module.system.model.Screen;
import module.system.model.User;
import module.system.model.Menu;

public class SystemBL {

	private DataSource dataSource;
	private CommonConfigDAO commonConfigDAO;
	public SystemBL(DataSource dataSource) {
		Connection con = null;
		try {
			this.dataSource = dataSource;
			con = dataSource.getConnection();
			this.commonConfigDAO = new CommonConfigDAO(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> commonConfigMap(){
		Map<String, String> commonMap = new HashMap<>();
		for (CommonConfig common : commonConfigDAO.getAll() ) {
			commonMap.put(common.getKeyConfig(), common.getValueConfig());
		}
		return commonMap;
		
	}
	
	public List<Group> getAllGroup(){
		List<Group> groups = new ArrayList<Group>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			groups = new GroupDAO(connection).getAll();
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
			new GroupDAO(connection).insert(group);
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
	
	public void saveUser(User user) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new UserDAO(connection).insertUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean validateUser(User user){
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			return new UserDAO(connection).selectUser(user) == null ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void checkLogin(User user){
		Connection connection = null;
		Login login = new Login();
		login.setUsername(user.getUsername());
		try {
			connection = dataSource.getConnection();
			login = new LoginDAO(connection).validate(login);
			if(login==null) {
				login = new Login();
				login.setUsername(user.getUsername());
				login.setEmployeeId("");
				login.setLastLogin(new Date());
				login.setLoginStatus("1");
				login.setInputDate(new Date());
				login.setInputBy(user.getUsername());
				login.setEditDate(new Date());
				login.setEditedBy(user.getUsername());
				new LoginDAO(connection).insert(login);
			}
			else {
				new LoginDAO(connection).update(login);
			}
			setActiveLogin(new LoginDAO(connection).getAll().get(0));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getUsernameActive(){
		return getActiveLogin() == null ? "ADMIN" : getActiveLogin().getUsername();
	}
	
	Login activeLogin;
	
	private void setActiveLogin(Login login){
		activeLogin = new Login();
		activeLogin.setId(login.getId());
		activeLogin.setUsername(login.getUsername());
		activeLogin.setEmployeeId(login.getEmployeeId());
		activeLogin.setLastLogin(login.getLastLogin());
		activeLogin.setLoginStatus(login.getLoginStatus());
		activeLogin.setInputDate(login.getInputDate());
		activeLogin.setInputBy(login.getInputBy());
		activeLogin.setEditedBy(login.getEditedBy());
		activeLogin.setEditDate(login.getEditDate());
		activeLogin.setDeletedBy(login.getDeletedBy());
		activeLogin.setDeletedDate(login.getDeletedDate());
	}

	public Login getActiveLogin() {
		return activeLogin;
	}
	// Screen
	public List<Screen> getAllScreen(String query) {
		List<Screen> screen = new ArrayList<Screen>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			screen = new ScreenDAO(connection).getAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return screen;
	}

	public void saveScreen(Screen screen) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new ScreenDAO(connection).insert(screen);
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
	
	public void updateScreen(Screen screen) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new ScreenDAO(connection).update(screen);
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
	
	public void deleteScreen(Screen screen) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new ScreenDAO(connection).delete(screen);
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
	
	public Timestamp validateVersion() throws SQLException{
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			return new VersionDAO(connection).getVersionDate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	public List<Menu> getAllMenu(){
		List<Menu> menus = new ArrayList<Menu>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			menus = new MenuDAO(connection).getAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return menus;
	}
}