package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Department;
import module.util.DateUtil;

public class DepartmentDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getLastIdStatement;

	private String getAllQuery = "select * from department where delete_date is null and delete_by is null";
	private String insertQuery = "insert into department (id, name, division_id, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update department set name = ?, division_id = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update department set delete_date = ?, delete_by = ? where id = ?";
	private String getLastIdQuery = "select * from department group by id desc limit 1";
	
	public DepartmentDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Department> getAllData(String query){
		List<Department> departments = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				Department departement = new Department();
				departement.setId(resultSet.getString("id"));
				departement.setName(resultSet.getString("name"));
				departement.setDivisionId(resultSet.getString("division_id"));
				
				departments.add(departement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return departments;
	}

	public void insert(Department departement) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, departement.getId());
			insertStatement.setString(2, departement.getName());
			insertStatement.setString(3, departement.getDivisionId());
			insertStatement.setDate(4, DateUtil.toDate(departement.getInputDate()));
			insertStatement.setString(5, departement.getInputBy());
			insertStatement.setDate(6, DateUtil.toDate(departement.getEditDate()));
			insertStatement.setString(7, departement.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Department departement) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
		
			updateStatement.setString(1, departement.getName());
			updateStatement.setString(2, departement.getDivisionId());
			updateStatement.setDate(3, DateUtil.toDate(departement.getEditDate()));
			updateStatement.setString(4, departement.getEditBy());
			updateStatement.setString(5, departement.getId());
			
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Department departement) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.toDate(departement.getDeleteDate()));
			deleteStatement.setString(2, departement.getDeleteBy());
			deleteStatement.setString(3, departement.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<Department> departments = null;
		try {
			departments = new ArrayList<>();
			getLastIdStatement = connection.prepareStatement(getLastIdQuery);

			ResultSet resultSet = getLastIdStatement.executeQuery();
			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getString("id"));
				department.setName(resultSet.getString("name"));
				department.setDivisionId(resultSet.getString("division_id"));
				
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departments.size() < 1 ? 1 : Integer.parseInt(departments.get(0).getId().substring(3)) + 1; 
	}
}
