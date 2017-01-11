package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Gender;

public class GenderDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select * from gender";

	public GenderDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Gender> getAllData(String query) {
		List<Gender> genders = new ArrayList<>();
		
		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);
			
			ResultSet resultSet = getAllStatement.executeQuery();
			while (resultSet.next()) {
				Gender gender = new Gender();
				gender.setId(resultSet.getInt("id"));
				gender.setGender(resultSet.getString("gender"));
				
				genders.add(gender);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genders;
	}
}