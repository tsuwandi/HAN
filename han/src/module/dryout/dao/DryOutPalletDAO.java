package module.dryout.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DryOutPalletDAO {

	private Connection connection;

	public DryOutPalletDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
}
