package module.dryin.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class PicTallyDAO {

	private Connection connection;

	public PicTallyDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
}
