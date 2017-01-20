package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.SalarySetting;
import module.util.DateUtil;

public class SalarySettingDAO {
	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from salary_setting order by id desc limit 1";
	private String getAllQuery = "select * from salary_setting where delete_date is null and delete_by is null";
	private String insertQuery = "insert into salary_setting(employee_code,effective_start_date,effective_end_date,salary_bruto,tax,salary_net,input_date,input_by,edit_date,edit_by)VALUES(?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update salary_setting set employee_code = ?, effective_start_date = ?, effective_end_date = ?, salary_bruto = ?, tax = ?, salary_net = ? edit_date = ?, edit_by = ? WHERE id = ?";
	private String deleteQuery = "update salary_setting set delete_date = ?, delete_by = ? where id = ?";

	public SalarySettingDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<SalarySetting> getAllData(String query){
		List<SalarySetting> salarySettings = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				SalarySetting salarySetting = new SalarySetting();
				salarySetting.setId(resultSet.getInt("id"));
				salarySetting.setEmployeeCode(resultSet.getString("employee_code"));
				salarySetting.setEmployee(ServiceFactory.getPersonaliaBL().getEmployees(" and emp_code = '"+salarySetting.getEmployeeCode()+"'").get(0));
				salarySetting.setEffectiveStartDate(resultSet.getDate("effective_start_date"));
				salarySetting.setEffectiveEndDate(resultSet.getDate("effective_end_date"));
				salarySetting.setSalaryBruto(resultSet.getBigDecimal("salary_bruto"));
				salarySetting.setTax(resultSet.getBigDecimal("tax"));
				salarySetting.setSalaryNett(resultSet.getBigDecimal("salary_net"));
				salarySetting.setInputDate(resultSet.getDate("input_date"));
				salarySetting.setInputBy(resultSet.getString("input_by"));
				salarySetting.setEditDate(resultSet.getDate("edit_date"));
				salarySetting.setEditBy(resultSet.getString("edit_by"));
				salarySetting.setDeleteDate(resultSet.getDate("delete_date"));
				salarySetting.setDeleteBy(resultSet.getString("delete_by"));
				salarySetting.setSsSalaryComps(ServiceFactory.getPersonaliaBL().getSsSalaryComps(" and salary_setting_id = "+salarySetting.getId()));
				salarySetting.setSsTaxs(ServiceFactory.getPersonaliaBL().getSsTaxs(" and salary_setting_id = "+salarySetting.getId()));
				salarySettings.add(salarySetting);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salarySettings;
	}

	public void insert(SalarySetting salarySetting) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, salarySetting.getEmployeeCode());
			insertStatement.setDate(2, DateUtil.toDate(salarySetting.getEffectiveStartDate()));
			insertStatement.setDate(3, DateUtil.toDate(salarySetting.getEffectiveEndDate()));
			insertStatement.setBigDecimal(4, salarySetting.getSalaryBruto());
			insertStatement.setBigDecimal(5, salarySetting.getTax());
			insertStatement.setBigDecimal(6, salarySetting.getSalaryNett());
			insertStatement.setDate(7, DateUtil.toDate(salarySetting.getInputDate()));
			insertStatement.setString(8, salarySetting.getInputBy());
			insertStatement.setDate(9, DateUtil.toDate(salarySetting.getEditDate()));
			insertStatement.setString(10, salarySetting.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(SalarySetting salarySetting) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, salarySetting.getEmployeeCode());
			updateStatement.setDate(2, DateUtil.toDate(salarySetting.getEffectiveStartDate()));
			updateStatement.setDate(3, DateUtil.toDate(salarySetting.getEffectiveEndDate()));
			updateStatement.setBigDecimal(4, salarySetting.getSalaryBruto());
			updateStatement.setBigDecimal(5, salarySetting.getTax());
			updateStatement.setBigDecimal(6, salarySetting.getSalaryNett());
			updateStatement.setDate(7, DateUtil.toDate(salarySetting.getEditDate()));
			updateStatement.setString(8, salarySetting.getEditBy());
			updateStatement.setInt(9, salarySetting.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(SalarySetting salarySetting) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(salarySetting.getDeleteDate()));
			deleteStatement.setString(2, salarySetting.getDeleteBy());
			deleteStatement.setInt(3, salarySetting.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<SalarySetting> salarySettings = null;
		
		try {
			salarySettings = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				SalarySetting salarySetting = new SalarySetting();
				salarySetting.setId(resultSet.getInt("id"));
				salarySettings.add(salarySetting);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return salarySettings.size() < 1 ? 1 : salarySettings.get(0).getId()+1;
	}
}