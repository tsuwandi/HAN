package module.production.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.GroupShift;

public class GroupShiftDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT gs.id, gs.group_shift_code, gs.description, gs.line_code, l.description as line_desc FROM group_shift gs INNER JOIN line l ON gs.line_code = l.line_code WHERE gs.deleted_date IS NULL ";
	
	public GroupShiftDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<GroupShift> getAll() throws SQLException {
		List<GroupShift> shifts = new ArrayList<GroupShift>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				GroupShift shift = new GroupShift();
				shift.setId(rs.getInt("id"));
				shift.setGroupShiftCode(rs.getString("group_shift_code"));
				shift.setDescription(rs.getString("description"));
				shift.setLineCode(rs.getString("line_code"));
				shift.setLineDescription(rs.getString("line_desc"));
				shifts.add(shift);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return shifts;
	}
}
