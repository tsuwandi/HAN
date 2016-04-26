package module.dryin.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DryInPalletDAO {

	private Connection connection;

	public DryInPalletDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
}
