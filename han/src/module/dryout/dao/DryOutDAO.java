package module.dryout.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DryOutDAO {

	private Connection connection;

	public DryOutDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
}
