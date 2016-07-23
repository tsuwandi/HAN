package module.production.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.production.model.Machine;

public class MachineDAO {
	private Connection connection;
	private PreparedStatement getAllStatement;
	
	private String getAllQuery = "SELECT id, machine_code, description FROM machine WHERE deleted_date IS NULL ";
	
	public MachineDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public List<Machine> getAll() throws SQLException {
		List<Machine> machines = new ArrayList<Machine>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Machine machine = new Machine();
				machine.setId(rs.getInt("id"));
				machine.setMachineCode(rs.getString("line_code"));
				machine.setDescription(rs.getString("description"));
				machines.add(machine);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return machines;
	}
}
