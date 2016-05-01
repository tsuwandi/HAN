package module.sn.chamber.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.chamber.model.Chamber;

public class ChamberDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "select id, chamber from chamber order by id";

	public ChamberDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Chamber> getAll() throws SQLException {

		List<Chamber> chambers = new ArrayList<Chamber>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Chamber chamber = new Chamber();
				chamber.setId(rs.getInt("id"));
				chamber.setChamber(rs.getString("chamber"));
				chambers.add(chamber);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return chambers;
	}

}
