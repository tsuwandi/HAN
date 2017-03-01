package module.system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VersionDAO {

	private Connection connection;
	
	private PreparedStatement getVersionDateStatement;
	
	private String getVersionDateQuery = "select * from version";
	
	public VersionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public Timestamp getVersionDate(){
		Timestamp date = null;
		
		try {
			getVersionDateStatement = connection.prepareStatement(getVersionDateQuery);
			
			ResultSet rs = getVersionDateStatement.executeQuery();
			
			while (rs.next()) {
				date = rs.getTimestamp("date");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
}
