package module.production.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.Line;

public class LineDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT id, line_code, description FROM line WHERE deleted_date IS NULL ";
	
	public LineDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<Line> getAll() throws SQLException {
		List<Line> lines = new ArrayList<Line>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Line line = new Line();
				line.setId(rs.getInt("id"));
				line.setLineCode(rs.getString("line_code"));
				line.setDescription(rs.getString("description"));
				lines.add(line);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return lines;
	}
}
