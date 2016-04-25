package module.dryin.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DryInDAO {

	private Connection connection;

	public DryInDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
}
