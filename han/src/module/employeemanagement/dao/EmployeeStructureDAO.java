package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
import module.employeemanagement.model.EmployeeStructure;
import module.util.DateUtil;

public class EmployeeStructureDAO {
	private Connection connection;

	private PreparedStatement getLastCodeStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastCodeQuery = "select code from emp_structure where deleted_date is null order by id desc limit 1";
	private String getAllQuery = "select a.id, a.code, a.org_value_id, a.position_id, b.NAME, concat(c.parent_value,'-',c.value) as value from emp_structure a inner join ms_position b on a.position_id = b.id"
			+ " inner join (select b.id, b.parent_id, a.value as parent_value , b.value  from org_structure_value a LEFT JOIN (select * from org_structure_value WHERE parent_id !=0) b On a.id = b.parent_id where  a.parent_id = 0)"
			+ " c ON a.org_value_id = c.id where a.deleted_date is null";
	private String insertQuery = "insert into emp_structure (code, org_value_id, position_id, input_date, input_by)"
			+ " VALUES (?,?,?,?,?)";
	private String updateQuery = "update emp_structure set code = ?, org_value_id = ?, position_id=?, edit_date = ?, edited_by = ?  where id = ?";
	private String deleteQuery = "update emp_structure set deleted_date = ?, deleted_by = ? where id = ?";
	
	public EmployeeStructureDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmployeeStructure> getAllData(String query){
		List<EmployeeStructure> empStructures = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeStructure empStructure = new EmployeeStructure();
				empStructure.setId(resultSet.getInt("id"));
				empStructure.setCode(resultSet.getString("code"));
				empStructure.setOrgValueId(resultSet.getInt("org_value_id"));
				empStructure.setPositionId(resultSet.getInt("position_id"));
				empStructure.setOrgValue(resultSet.getString("value"));
				empStructure.setPosition(resultSet.getString("Name"));
				empStructures.add(empStructure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empStructures;
	}

	public void insert(EmployeeStructure empStructure) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, empStructure.getCode());
			insertStatement.setInt(2, empStructure.getOrgValueId());
			insertStatement.setInt(3, empStructure.getPositionId());
			insertStatement.setDate(4, DateUtil.toDate(new Date()));
			insertStatement.setString(5, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(EmployeeStructure empStructure) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, empStructure.getCode());
			updateStatement.setInt(2, empStructure.getOrgValueId());
			updateStatement.setInt(3, empStructure.getPositionId());
			updateStatement.setDate(4, DateUtil.toDate(new Date()));
			updateStatement.setString(5, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(6, empStructure.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(EmployeeStructure empStructure) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.toDate(new Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, empStructure.getId());

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
			if(rs.next()) lastCode = rs.getString("code");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		return lastCode;
	}
	
}
