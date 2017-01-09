package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.EmpPosition;

public class EmpPositionDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from emp_position order by id desc limit 1";
	private String getAllQuery = "select * from emp_position where delete_date is null and delete_by is null";
	private String insertQuery = "insert into emp_position(id, employee_id, start_date, end_date, probation, position_id, department_id, division_id, reference_doc, note, input_date, input_by, edit_date, edit_by)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private String updateQuery = "update emp_position set employee_id = ?, start_date = ?, end_date = ?, probation = ?, position_id = ?, department_id = ?, division_id = ?, reference_doc = ?, note = ?, input_date = ?, input_by = ?, edit_date = ?, edit_by = ? WHERE id = ?;";
	private String deleteQuery = "update emp_position set delete_date = ?, delete_by = ? where id = ?";
	
	public EmpPositionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmpPosition> getAllData(String query) {
		return null;
	}
	
	public void insert(EmpPosition employeePosition) {
		
	}
	
	public void update(EmpPosition employeePosition) {
		
	}
	
	public void delete(EmpPosition employeePosition) {
		
	}
	
	public Integer getLastId() {
		List<EmpPosition> employeePositions = null;
		
		try {
			employeePositions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				EmpPosition employeePosition = new EmpPosition();
				employeePosition.setId("id");

				employeePositions.add(employeePosition);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return employeePositions.size() < 1 ? 1 : Integer.parseInt(employeePositions.get(0).getId().substring(3))+1;
	}
}
