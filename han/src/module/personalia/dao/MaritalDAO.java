package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Marital;

public class MaritalDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select * from marital";

	public MaritalDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Marital> getAllData(String query) {
		List<Marital> maritals = new ArrayList<>();
		
		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);
			
			ResultSet resultSet = getAllStatement.executeQuery();
			while (resultSet.next()) {
				Marital marital = new Marital();
				marital.setId(resultSet.getInt("id"));
				marital.setMarital(resultSet.getString("marital"));
				
				maritals.add(marital);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maritals;
	}
}
