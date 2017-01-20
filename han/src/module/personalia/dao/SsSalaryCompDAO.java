package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.SsSalaryComp;
import module.util.DateUtil;

public class SsSalaryCompDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from ss_salary_comp order by id desc limit 1";
	private String getAllQuery = "select * from ss_salary_comp where delete_date is null and delete_by is null";
	private String insertQuery = "insert into ss_salary_comp (salary_setting_id, payroll_component_code, nominal, input_date, input_by, edit_date, edit_by) values (?,?,?,?,?,?,?)";
	private String updateQuery = "update ss_salary_comp set salary_setting_id = ?, payroll_component_code = ?, nominal = ?, edit_date = ?, edit_by = ? WHERE id = ?";
	private String deleteQuery = "update ss_salary_comp set delete_date = ?, delete_by = ? where id = ?";

	public SsSalaryCompDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<SsSalaryComp> getAllData(String query){
		List<SsSalaryComp> ssSalaryComps = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				SsSalaryComp ssSalaryComp = new SsSalaryComp();
				ssSalaryComp.setId(resultSet.getInt("id"));
				ssSalaryComp.setSalarySettingId(resultSet.getInt("salary_setting_id"));
				ssSalaryComp.setPayrollComponentCode(resultSet.getString("payroll_component_code"));
				ssSalaryComp.setPayrollComponent(ServiceFactory.getPersonaliaBL().getPayrollComponents(" and code = '"+ssSalaryComp.getPayrollComponentCode()+"'").get(0));
				ssSalaryComp.setNominal(resultSet.getBigDecimal("nominal"));
				ssSalaryComp.setInputDate(resultSet.getDate("input_date"));
				ssSalaryComp.setInputBy(resultSet.getString("input_by"));
				ssSalaryComp.setEditDate(resultSet.getDate("edit_date"));
				ssSalaryComp.setEditBy(resultSet.getString("edit_by"));
				ssSalaryComp.setDeleteDate(resultSet.getDate("delete_date"));
				ssSalaryComp.setDeleteBy(resultSet.getString("delete_by"));
				
				ssSalaryComps.add(ssSalaryComp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ssSalaryComps;
	}

	public void insert(SsSalaryComp ssSalaryComp) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setInt(1, ssSalaryComp.getSalarySettingId());
			insertStatement.setString(2, ssSalaryComp.getPayrollComponentCode());
			insertStatement.setBigDecimal(3, ssSalaryComp.getNominal());
			insertStatement.setDate(4, DateUtil.toDate(ssSalaryComp.getInputDate()));
			insertStatement.setString(5, ssSalaryComp.getInputBy());
			insertStatement.setDate(6, DateUtil.toDate(ssSalaryComp.getEditDate()));
			insertStatement.setString(7, ssSalaryComp.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(SsSalaryComp ssSalaryComp) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setInt(1, ssSalaryComp.getSalarySettingId());
			updateStatement.setString(2, ssSalaryComp.getPayrollComponentCode());
			updateStatement.setBigDecimal(3, ssSalaryComp.getNominal());
			updateStatement.setDate(4, DateUtil.toDate(ssSalaryComp.getEditDate()));
			updateStatement.setString(5, ssSalaryComp.getEditBy());
			updateStatement.setInt(6, ssSalaryComp.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(SsSalaryComp ssSalaryComp) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(ssSalaryComp.getDeleteDate()));
			deleteStatement.setString(2, ssSalaryComp.getDeleteBy());
			deleteStatement.setInt(3, ssSalaryComp.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<SsSalaryComp> ssSalaryComps = null;
		
		try {
			ssSalaryComps = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				SsSalaryComp ssSalaryComp = new SsSalaryComp();
				ssSalaryComp.setId(resultSet.getInt("id"));
				ssSalaryComps.add(ssSalaryComp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return ssSalaryComps.size() < 1 ? 1 : ssSalaryComps.get(0).getId()+1;
	}
}