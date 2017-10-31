package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.employeemanagement.model.EmpPayrollDetail;

public class EmpPayrollDetailDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select * from emp_payroll_detail where emp_position_id = ?";
	private String insertQuery = "insert into emp_payroll_detail (payroll_component_id,payroll_component_name,nominal,emp_position_id) VALUES (?,?,?,?)";
	private String updateQuery = "update emp_payroll_detail set payroll_component_id=?, payroll_component_name = ?, nominal = ?  where id = ? and emp_position_id = ?";
	private String deleteQuery = "delete emp_payroll_detail where id = ?";
	
	public EmpPayrollDetailDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmpPayrollDetail> getAllData(int empPositionId){
		List<EmpPayrollDetail> EmpPayrollDetails = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			getAllStatement.setInt(1, empPositionId);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				EmpPayrollDetail empStructure = new EmpPayrollDetail();
				empStructure.setId(resultSet.getInt("id"));
				empStructure.setEmpPositionId(resultSet.getInt("emp_position_id"));
				empStructure.setPayrollComponentId(resultSet.getInt("payroll_component_id"));
				empStructure.setPayrollComponentName(resultSet.getString("payroll_component_name"));
				empStructure.setNominal(resultSet.getBigDecimal("nominal"));
				EmpPayrollDetails.add(empStructure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return EmpPayrollDetails;
	}

	public void insert(EmpPayrollDetail EmpPayrollDetail) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, EmpPayrollDetail.getPayrollComponentId());
			insertStatement.setString(2, EmpPayrollDetail.getPayrollComponentName());
			insertStatement.setBigDecimal(3, EmpPayrollDetail.getNominal());
			insertStatement.setInt(4, EmpPayrollDetail.getEmpPositionId());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(EmpPayrollDetail EmpPayrollDetail) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, EmpPayrollDetail.getPayrollComponentId());
			updateStatement.setString(2, EmpPayrollDetail.getPayrollComponentName());
			updateStatement.setBigDecimal(3, EmpPayrollDetail.getNominal());
			updateStatement.setInt(4, EmpPayrollDetail.getId());
			insertStatement.setInt(5, EmpPayrollDetail.getEmpPositionId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(EmpPayrollDetail EmpPayrollDetail) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, EmpPayrollDetail.getId());
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
