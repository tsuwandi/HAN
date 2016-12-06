package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Departement;
import module.util.DateUtil;

public class DepartementDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select * from departement where delete_date is null and delete_by is null";
	private String insertQuery = "insert into departement (id, name, division_id, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update departement set name = ?, division_id = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update departement set delete_date = ?, delete_by = ? where id = ?";
	
	public DepartementDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Departement> getAllData(String query){
		List<Departement> departements = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				Departement departement = new Departement();
				departement.setId(resultSet.getString("id"));
				departement.setName(resultSet.getString("name"));
				departement.setDivisionId(resultSet.getString("division_id"));
				
				departements.add(departement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return departements;
	}

	public void insert(Departement departement) {
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

	public void update(Departement departement) {
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

	public void delete(Departement departement) {
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
		return getAllData("").size()+1;
	}
}
