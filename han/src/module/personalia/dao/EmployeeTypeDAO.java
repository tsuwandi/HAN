package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.EmployeeType;
import module.util.DateUtil;

public class EmployeeTypeDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getLastIdStatement;

	private String getAllQuery = "select * from employee_type where delete_date is null and delete_by is null";
	private String insertQuery = "insert into employee_type (id, name, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update employee_type set name = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update employee_type set delete_date = ?, delete_by = ? where id = ?";
	private String getLastIdQuery = "select * from employee_type group by id desc limit 1";

	public EmployeeTypeDAO(Connection connection) {
		this.connection = connection;
	}

	public List<EmployeeType> getAllData(String query) {
		List<EmployeeType> employeeTypes = new ArrayList<>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeType employeeType = new EmployeeType();
				employeeType.setId(resultSet.getString("id"));
				employeeType.setName(resultSet.getString("name"));
				employeeTypes.add(employeeType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeTypes;
	}

	public void insert(EmployeeType employeeType) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, employeeType.getId());
			insertStatement.setString(2, employeeType.getName());
			insertStatement.setDate(3, DateUtil.toDate(employeeType.getInputDate()));
			insertStatement.setString(4, employeeType.getInputBy());
			insertStatement.setDate(5, DateUtil.toDate(employeeType.getEditDate()));
			insertStatement.setString(6, employeeType.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(EmployeeType employeeType) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, employeeType.getName());
			updateStatement.setDate(2, DateUtil.toDate(employeeType.getEditDate()));
			updateStatement.setString(3, employeeType.getEditBy());
			updateStatement.setString(4, employeeType.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(EmployeeType employeeType) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(employeeType.getDeleteDate()));
			deleteStatement.setString(2, employeeType.getDeleteBy());
			deleteStatement.setString(3, employeeType.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<EmployeeType> employeeTypes = null;
		try {
			employeeTypes = new ArrayList<>();
			getLastIdStatement = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatement.executeQuery();
			while (resultSet.next()) {
				EmployeeType employeeType = new EmployeeType();
				employeeType.setId(resultSet.getString("id"));
				employeeType.setName(resultSet.getString("name"));
				employeeTypes.add(employeeType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeTypes == null? 1 : Integer.parseInt(employeeTypes.get(0).getId())  + 1;
	}
}