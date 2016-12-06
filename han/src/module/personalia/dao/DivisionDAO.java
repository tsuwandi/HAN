package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Division;
import module.util.DateUtil;

public class DivisionDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select * from division where delete_date is null and delete_by is null";
	private String insertQuery = "insert into division (id, name, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update division set name = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update division set delete_date = ?, delete_by = ? where id = ?";

	public DivisionDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Division> getAllData(String query){
		List<Division> divisions = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				Division division = new Division();
				division.setId(resultSet.getString("id"));
				division.setName(resultSet.getString("name"));

				divisions.add(division);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return divisions;
	}

	public void insert(Division division) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, division.getId());
			insertStatement.setString(2, division.getName());
			insertStatement.setDate(3, DateUtil.toDate(division.getInputDate()));
			insertStatement.setString(4, division.getInputBy());
			insertStatement.setDate(5, DateUtil.toDate(division.getEditDate()));
			insertStatement.setString(6, division.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Division division) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, division.getName());
			updateStatement.setDate(2, DateUtil.toDate(division.getEditDate()));
			updateStatement.setString(3, division.getEditBy());
			updateStatement.setString(4, division.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Division division) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(division.getDeleteDate()));
			deleteStatement.setString(2, division.getDeleteBy());
			deleteStatement.setString(3, division.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		return getAllData("").size()+1;
	}
}
