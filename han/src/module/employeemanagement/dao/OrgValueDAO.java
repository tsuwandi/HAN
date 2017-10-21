package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.employeemanagement.model.OrgValue;

public class OrgValueDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	private String getAllQuery = "select b.id, b.parent_id, a.value as parent_value , b.value  from org_structure_value a LEFT JOIN (select * from org_structure_value WHERE parent_id !=0) b On a.id = b.parent_id where  a.parent_id = 0";
	
	public OrgValueDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<OrgValue> getAllData(String query){
		List<OrgValue> orgValues = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				OrgValue orgValue = new OrgValue();
				orgValue.setId(resultSet.getInt("id"));
				orgValue.setParentId(resultSet.getInt("parent_id"));
				orgValue.setValue(resultSet.getString("value"));
				orgValue.setParentValue(resultSet.getString("parent_value"));
				orgValues.add(orgValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orgValues;
	}
}
