package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.employeemanagement.model.EmpTaxDetail;

public class EmpTaxDetailDAO {
	private Connection connection;

	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllQuery = "select * from emp_tax_detail where emp_position_id = ?";
	private String insertQuery = "insert into emp_tax_detail (tax_id,tax_name,nominal,emp_position_id) VALUES (?,?,?,?)";
	private String updateQuery = "update emp_tax_detail set tax_id=?, tax_name = ?, nominal = ?  where id = ? and emp_position_id = ?";
	private String deleteQuery = "delete emp_tax_detail where id = ?";
	
	public EmpTaxDetailDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<EmpTaxDetail> getAllData(int empPositionId){
		List<EmpTaxDetail> empTaxDetails = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery);
			getAllStatement.setInt(1, empPositionId);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				EmpTaxDetail empStructure = new EmpTaxDetail();
				empStructure.setId(resultSet.getInt("id"));
				empStructure.setEmpPositionId(resultSet.getInt("emp_position_id"));
				empStructure.setTaxId(resultSet.getInt("tax_id"));
				empStructure.setTaxName(resultSet.getString("tax_name"));
				empStructure.setNominal(resultSet.getBigDecimal("nominal"));
				empTaxDetails.add(empStructure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empTaxDetails;
	}

	public void insert(EmpTaxDetail empTaxDetail) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, empTaxDetail.getTaxId());
			insertStatement.setString(2, empTaxDetail.getTaxName());
			insertStatement.setBigDecimal(3, empTaxDetail.getNominal());
			insertStatement.setInt(4, empTaxDetail.getEmpPositionId());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(EmpTaxDetail empTaxDetail) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, empTaxDetail.getTaxId());
			updateStatement.setString(2, empTaxDetail.getTaxName());
			updateStatement.setBigDecimal(3, empTaxDetail.getNominal());
			updateStatement.setInt(4, empTaxDetail.getId());
			insertStatement.setInt(5, empTaxDetail.getEmpPositionId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(EmpTaxDetail empTaxDetail) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, empTaxDetail.getId());
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
