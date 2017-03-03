package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.Screen;
import module.util.DateUtil;

public class ScreenDAO {

private Connection connection;
	
	private PreparedStatement getAllGroupStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllGroupQuery = "select * from screen where delete_date is null and delete_by is null";
	private String insertQuery = "insert into screen (menu_name,screen_name,screen_title,module_name,input_date,input_by,edit_date,edit_by) values (?,?,?,?,?,?,?,?)";
	private String updateQuery = "update screen set menu_name = ?, screen_name = ?, screen_title = ?, module_name = ? edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update screen set delete_date = ?, delete_by = ? where id = ?";
	
	public ScreenDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Screen> getAll(String query){
		List<Screen> screens = new ArrayList<Screen>();
		try {
			getAllGroupStatement = connection.prepareStatement(getAllGroupQuery+query);

			ResultSet resultSet = getAllGroupStatement.executeQuery();
			
			while (resultSet.next()) {
				Screen screen = new Screen();
				screen.setId(resultSet.getInt("id"));
				screen.setMenuName(resultSet.getString("menu_name"));
				screen.setScreenName(resultSet.getString("screen_name"));
				screen.setScreenTitle(resultSet.getString("screen_title"));
				screen.setModuleName(resultSet.getString("module_name"));
				screen.setInputDate(resultSet.getDate("input_date"));
				screen.setInputBy(resultSet.getString("input_by"));
				screen.setEditDate(resultSet.getDate("edit_date"));
				screen.setEditedBy(resultSet.getString("edit_by"));
				screens.add(screen);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return screens;
	}
	
	public void insert(Screen screen){
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setString(1, screen.getMenuName());
			insertStatement.setString(2, screen.getScreenName());
			insertStatement.setString(3, screen.getScreenTitle());
			insertStatement.setString(4, screen.getModuleName());
			insertStatement.setDate(5, DateUtil.toDate(screen.getInputDate()));
			insertStatement.setString(6, screen.getInputBy());
			insertStatement.setDate(7, DateUtil.toDate(screen.getEditDate()));
			insertStatement.setString(8, screen.getEditedBy());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Screen screen){
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setString(1, screen.getMenuName());
			updateStatement.setString(2, screen.getScreenName());
			updateStatement.setString(2, screen.getScreenTitle());
			updateStatement.setString(2, screen.getModuleName());
			updateStatement.setDate(3, DateUtil.toDate(screen.getEditDate()));
			updateStatement.setString(4, screen.getEditedBy());
			updateStatement.setInt(5, screen.getId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Screen screen){
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setDate(1, DateUtil.toDate(screen.getDeletedDate()));
			deleteStatement.setString(2, screen.getDeletedBy());
			deleteStatement.setInt(3, screen.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}