package module.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
import module.employeemanagement.model.PayrollComponent;
import module.util.DateUtil;

public class PayrollComponentDAO {
	private Connection connection;

	private PreparedStatement getLastCodeStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastCodeQuery = "select code from payroll_component where delete_date is null order by id desc limit 1";
	private String getAllQuery = "select * from payroll_component where delete_date is null";
	private String insertQuery = "insert into payroll_component (code, description, is_daily, is_salary, is_thr, is_bonus, reference_document, input_date, input_by)"
			+ " VALUES (?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update payroll_component set code = ?, description = ?, is_daily=?, is_salary= ?, is_thr= ?, is_bonus = ?"
			+ " ,reference_document=?, edit_date = ?, edit_by = ?  where id = ?";
	private String deleteQuery = "update payroll_component set delete_date = ?, delete_by = ? where id = ?";
	
	public PayrollComponentDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<PayrollComponent> getAllData(String query){
		List<PayrollComponent> payrolls = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				PayrollComponent payroll = new PayrollComponent();
				payroll.setId(resultSet.getInt("id"));
				payroll.setCode(resultSet.getString("code"));
				payroll.setDescription(resultSet.getString("description"));
				payroll.setIsDaily(resultSet.getInt("is_daily"));
				payroll.setIsSalary(resultSet.getInt("is_salary"));
				payroll.setIsThr(resultSet.getInt("is_thr"));
				payroll.setIsBonus(resultSet.getInt("is_bonus"));
				payroll.setReferenceDocument(resultSet.getString("reference_document"));
				payrolls.add(payroll);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payrolls;
	}

	public void insert(PayrollComponent payroll) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, payroll.getCode());
			insertStatement.setString(2, payroll.getDescription());
			insertStatement.setInt(3, payroll.getIsDaily());
			insertStatement.setInt(4, payroll.getIsSalary());
			insertStatement.setInt(5, payroll.getIsThr());
			insertStatement.setInt(6, payroll.getIsBonus());
			insertStatement.setString(7, payroll.getReferenceDocument());
			insertStatement.setDate(8, DateUtil.toDate(new Date()));
			insertStatement.setString(9, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(PayrollComponent payroll) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, payroll.getCode());
			updateStatement.setString(2, payroll.getDescription());
			updateStatement.setInt(3, payroll.getIsDaily());
			updateStatement.setInt(4, payroll.getIsSalary());
			updateStatement.setInt(5, payroll.getIsThr());
			updateStatement.setInt(6, payroll.getIsBonus());
			updateStatement.setString(7, payroll.getReferenceDocument());
			updateStatement.setDate(8, DateUtil.toDate(new Date()));
			updateStatement.setString(9, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(10, payroll.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(PayrollComponent payroll) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setDate(1, DateUtil.toDate(new Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
			deleteStatement.setInt(3, payroll.getId());

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
