package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.system.model.Menu;
import module.util.DateUtil;

public class MenuDAO {

	
private Connection connection;
	
	private PreparedStatement getAllGroupStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private String getAllGroupQuery = "select * from menu where delete_date is null and delete_by is null";
	private String insertQuery = "insert into menu (menu_name, menu_title, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update menu set menu_name = ? , menu_title = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update menu set delete_date = ?, delete_by = ? where id = ?";
	
	public MenuDAO(Connection connection) throws SQLException{
		this.connection = connection;
	}
	
	public List<Menu> getAll() {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			getAllGroupStatement = connection.prepareStatement(getAllGroupQuery);
			
			ResultSet resultSet = getAllGroupStatement.executeQuery();
			
			while (resultSet.next()) {
				Menu menu = new Menu();
				menu.setId(resultSet.getInt("id"));
				menu.setMenuName(resultSet.getString("menu_name"));
				menu.setMenuTitle(resultSet.getString("menu_title"));
				menu.setInputDate(resultSet.getDate("input_date"));
				menu.setInputBy(resultSet.getString("input_by"));
				menu.setEditDate(resultSet.getDate("edit_date"));
				menu.setEditedBy(resultSet.getString("edit_by"));
				menus.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
	
	public void insert(Menu menu) {
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setString(1, menu.getMenuName());
			insertStatement.setString(2, menu.getMenuTitle());
			insertStatement.setDate(3, DateUtil.toDate(menu.getInputDate()));
			insertStatement.setString(4, menu.getInputBy());
			insertStatement.setDate(5, DateUtil.toDate(menu.getEditDate()));
			insertStatement.setString(6, menu.getEditedBy());
			
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Menu menu) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setString(1, menu.getMenuName());
			updateStatement.setString(2, menu.getMenuTitle());
			updateStatement.setDate(3, DateUtil.toDate(menu.getEditDate()));
			updateStatement.setString(4, menu.getEditedBy());
			updateStatement.setInt(5, menu.getId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Menu menu) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			
			deleteStatement.setDate(1, DateUtil.toDate(menu.getDeletedDate()));
			deleteStatement.setString(2, menu.getDeletedBy());
			deleteStatement.setInt(3, menu.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
