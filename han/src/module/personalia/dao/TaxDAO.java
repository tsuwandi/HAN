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

	private String getAllQuery = "select * from tax where delete_date is null and delete_by is null";

	public TaxDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Tax> getAllData(String query) {
		List<Tax> taxs = new ArrayList<>();
		
		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);
			
			ResultSet resultSet = getAllStatement.executeQuery();
			while (resultSet.next()) {
				Tax tax = new Tax();
				tax.setId(resultSet.getInt("id"));
				tax.setTax(resultSet.getString("tax"));
				
				taxs.add(tax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taxs;
	}
}