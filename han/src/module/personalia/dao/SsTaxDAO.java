package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.SsTax;
import module.util.DateUtil;

public class SsTaxDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from ss_tax order by id desc limit 1";
	private String getAllQuery = "select * from ss_tax where delete_date is null and delete_by is null";
	private String insertQuery = "insert into ss_tax(salary_setting_id,tax_id,nominal,input_date,input_by,edit_date,edit_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update ss_tax set salary_setting_id = ?, tax_id = ?, nominal = ?, edit_date = ?, edit_by = ? WHERE id = ?";
	private String deleteQuery = "update ss_tax set delete_date = ?, delete_by = ? where id = ?";

	public SsTaxDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<SsTax> getAllData(String query){
		List<SsTax> ssTaxs = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				SsTax ssTax = new SsTax();
				ssTax.setId(resultSet.getInt("id"));
				ssTax.setSalarySettingId(resultSet.getInt("salary_setting_id"));
				ssTax.setTaxId(resultSet.getInt("tax_id"));
				ssTax.setTax(ServiceFactory.getPersonaliaBL().getTaxs(" and id = "+ssTax.getTaxId()).get(0));
				ssTax.setNominal(resultSet.getBigDecimal("nominal"));
				ssTax.setInputDate(resultSet.getDate("input_date"));
				ssTax.setInputBy(resultSet.getString("input_by"));
				ssTax.setEditDate(resultSet.getDate("edit_date"));
				ssTax.setEditBy(resultSet.getString("edit_by"));
				ssTax.setDeleteDate(resultSet.getDate("delete_date"));
				ssTax.setDeleteBy(resultSet.getString("delete_by"));
				
				ssTaxs.add(ssTax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ssTaxs;
	}

	public void insert(SsTax ssTax) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setInt(1, ssTax.getSalarySettingId());
			insertStatement.setInt(2, ssTax.getTaxId());
			insertStatement.setBigDecimal(3, ssTax.getNominal());
			insertStatement.setDate(4, DateUtil.toDate(ssTax.getInputDate()));
			insertStatement.setString(5, ssTax.getInputBy());
			insertStatement.setDate(6, DateUtil.toDate(ssTax.getEditDate()));
			insertStatement.setString(7, ssTax.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(SsTax ssTax) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, ssTax.getSalarySettingId());
			updateStatement.setInt(2, ssTax.getTaxId());
			updateStatement.setBigDecimal(3, ssTax.getNominal());
			updateStatement.setDate(4, DateUtil.toDate(ssTax.getEditDate()));
			updateStatement.setString(5, ssTax.getEditBy());
			updateStatement.setInt(6, ssTax.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(SsTax ssTax) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(ssTax.getDeleteDate()));
			deleteStatement.setString(2, ssTax.getDeleteBy());
			deleteStatement.setInt(3, ssTax.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<SsTax> ssTaxs = null;
		
		try {
			ssTaxs = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				SsTax salarySetting = new SsTax();
				salarySetting.setId(resultSet.getInt("id"));
				ssTaxs.add(salarySetting);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return ssTaxs.size() < 1 ? 1 : ssTaxs.get(0).getId()+1;
	}
}