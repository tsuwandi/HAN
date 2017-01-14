package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Tax;

public class TaxDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select * from tax";

	public TaxDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Tax> getAllData(String query) {
		List<Tax> maritals = new ArrayList<>();
		
		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);
			
			ResultSet resultSet = getAllStatement.executeQuery();
			while (resultSet.next()) {
				Tax tax = new Tax();
				tax.setId(resultSet.getInt("id"));
				tax.setTax(resultSet.getString("tax"));
				
				maritals.add(tax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maritals;
	}
}