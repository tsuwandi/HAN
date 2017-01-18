package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.EmpPosition;
import module.personalia.model.EmployeeType;
import module.personalia.model.MSPosition;
import module.util.DateUtil;

public class EmpPositionDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from emp_position order by id desc limit 1";
	private String getAllQuery = "select * from emp_position where delete_date is null and delete_by is null";
	private String insertQuery = "insert into emp_position(employee_id, start_date, end_date, probation, position_id, employee_type_id, reference_doc, note, input_date, input_by, edit_date, edit_by)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update emp_position set employee_id = ?, start_date = ?, end_date = ?, probation = ?, position_id = ?, employee_type_id = ?, reference_doc = ?, note = ?, edit_date = ?, edit_by = ? WHERE id = ?";
	private String deleteQuery = "update emp_position set delete_date = ?, delete_by = ? where id = ?";
	
	public EmpPositionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmpPosition> getAllData(String query) {
		List<EmpPosition> empPositions = new ArrayList<>();
		
		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);
			
			ResultSet resultSet = getAllStatement.executeQuery();
			while (resultSet.next()) {
				EmpPosition empPosition = new EmpPosition();
				empPosition.setId(resultSet.getInt("id"));
				empPosition.setEmployeeId(resultSet.getString("employee_id"));
				empPosition.setStartDate(resultSet.getDate("start_date"));
				empPosition.setEndDate(resultSet.getDate("end_date"));
				empPosition.setProbation(resultSet.getInt("probation"));
				empPosition.setPositionId(resultSet.getString("position_id"));
				List<MSPosition> msPositions = ServiceFactory.getPersonaliaBL().getMSPositions(" and id = '"+empPosition.getPositionId()+"'");
				empPosition.setMsPosition(msPositions.get(0));
				empPosition.setEmployeeTypeId(resultSet.getString("employee_type_id"));
				List<EmployeeType> employeeTypes = ServiceFactory.getPersonaliaBL().getEmployeeTypes(" and id = '"+empPosition.getEmployeeTypeId()+"'");
				empPosition.setEmployeeType(employeeTypes.get(0));
				empPosition.setReferenceDoc(resultSet.getString("reference_doc"));
				empPosition.setNotes(resultSet.getString("note"));
				empPosition.setInputDate(resultSet.getDate("input_date"));
				empPosition.setInputBy(resultSet.getString("input_by"));
				empPosition.setEditDate(resultSet.getDate("edit_date"));
				empPosition.setEditBy(resultSet.getString("edit_by"));
				empPosition.setDeleteDate(resultSet.getDate("delete_date"));
				empPosition.setDeleteBy(resultSet.getString("delete_by"));
				
				empPositions.add(empPosition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empPositions;
	}
	
	public void insert(EmpPosition employeePosition) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, employeePosition.getEmployeeId());
			insertStatement.setDate(2, DateUtil.toDate(employeePosition.getStartDate()));
			if (employeePosition.getEndDate()==null) insertStatement.setDate(3, null);
			else insertStatement.setDate(3, DateUtil.toDate(employeePosition.getEndDate()));
			insertStatement.setInt(4, employeePosition.getProbation());
			insertStatement.setString(5, employeePosition.getPositionId());
			insertStatement.setString(6, employeePosition.getEmployeeTypeId());
			insertStatement.setString(7, employeePosition.getReferenceDoc());
			insertStatement.setString(8, employeePosition.getNotes());
			insertStatement.setDate(9, DateUtil.toDate(employeePosition.getInputDate()));
			insertStatement.setString(10, employeePosition.getInputBy());
			insertStatement.setDate(11, DateUtil.toDate(employeePosition.getEditDate()));
			insertStatement.setString(12, employeePosition.getEditBy());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(EmpPosition employeePosition) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, employeePosition.getEmployeeId());
			updateStatement.setDate(2, DateUtil.toDate(employeePosition.getStartDate()));
			updateStatement.setDate(3, DateUtil.toDate(employeePosition.getEndDate()));
			updateStatement.setInt(4, employeePosition.getProbation());
			updateStatement.setString(5, employeePosition.getPositionId());
			updateStatement.setString(6, employeePosition.getEmployeeTypeId());
			updateStatement.setString(7, employeePosition.getReferenceDoc());
			updateStatement.setString(8, employeePosition.getNotes());
			updateStatement.setDate(8, DateUtil.toDate(employeePosition.getEditDate()));
			updateStatement.setString(9, employeePosition.getEditBy());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(EmpPosition employeePosition) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.toDate(employeePosition.getStartDate()));
			deleteStatement.setString(2, employeePosition.getDeleteBy());
			deleteStatement.setString(3, employeePosition.getEmployeeId());
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getLastId() {
		List<EmpPosition> employeePositions = null;
		
		try {
			employeePositions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				EmpPosition employeePosition = new EmpPosition();
				employeePosition.setId(resultSet.getInt("id"));

				employeePositions.add(employeePosition);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		//return employeePositions.size() < 1 ? 1 : Integer.parseInt(employeePositions.get(0).getId().substring(3))+1;
		return employeePositions.size() < 1 ? 1 : employeePositions.get(0).getId()+1;
	}
}
