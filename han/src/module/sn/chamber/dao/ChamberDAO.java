package module.sn.chamber.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import module.sn.chamber.model.Chamber;

public class ChamberDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select id, chamber from chamber order by id";

	public ChamberDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public HashMap<String, Integer> getAll() throws SQLException {

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("id"));
				chamber.setChamber(rs.getString("chamber"));
				map.put(chamber.getChamber(), chamber.getId());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return map;
	}

}
