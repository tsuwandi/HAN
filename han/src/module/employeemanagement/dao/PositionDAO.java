package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
import module.employeemanagement.model.Position;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.personalia.model.MSPosition;
import module.util.DateUtil;

public class PositionDAO {

	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getLastCodeStatment;

	private String getAllQuery = "select * from ms_position where delete_date is null and delete_by is null";
	private String insertQuery = "insert into ms_position (code, name, min_salary, max_salary, input_date, input_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update ms_position set code = ?, name = ?, min_salary = ?, max_salary = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update ms_position set delete_date = ?, delete_by = ? where id = ?";
	private String getLastCodeQuery = "select * from ms_position group by id desc limit 1";
	
	
	public PositionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Position> getAllData() {
		List<Position> positions = new ArrayList<>();
		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				Position position = new Position();
				position.setId(resultSet.getInt("id"));
				position.setCode(resultSet.getString("code"));
				position.setName(resultSet.getString("name"));
				position.setMinSalary(resultSet.getBigDecimal("min_salary"));
				position.setMaxSalary(resultSet.getBigDecimal("max_salary"));
				positions.add(position);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positions;
	}
	
	public void insert(Position position) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, position.getCode());
			insertStatement.setString(2, position.getName());
			insertStatement.setBigDecimal(3, position.getMinSalary());
			insertStatement.setBigDecimal(4, position.getMaxSalary());
			insertStatement.setDate(5, DateUtil.toDate(new Date()));
			insertStatement.setString(6, ServiceFactory.getSystemBL().getUsernameActive());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Position position) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, position.getCode());
			updateStatement.setString(2, position.getName());
			updateStatement.setBigDecimal(3, position.getMinSalary());
			updateStatement.setBigDecimal(4, position.getMaxSalary());
			updateStatement.setDate(5, DateUtil.toDate(new Date()));
			updateStatement.setString(6, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(7, position.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Position position) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(new Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, position.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastCode() throws SQLException{
		String lastCode = null;
		try {
			getLastCodeStatment = connection.prepareStatement(getLastCodeQuery);
			ResultSet rs = getLastCodeStatment.executeQuery();
			if(rs.next()) lastCode =  rs.getString("code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
}
