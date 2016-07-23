package module.production.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.Machine;
import module.production.model.Shift;

public class ShiftDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT id, shift_code, shift_name FROM shift WHERE deleted_date IS NULL ";
	
	public ShiftDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<Shift> getAll() throws SQLException {
		List<Shift> shifts = new ArrayList<Shift>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Shift shift = new Shift();
				shift.setId(rs.getInt("id"));
				shift.setShiftCode(rs.getString("shift_code"));
				shift.setShiftName(rs.getString("shift_name"));
				shifts.add(shift);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return shifts;
	}
}
