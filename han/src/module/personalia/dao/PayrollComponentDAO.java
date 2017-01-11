package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.PayrollComponent;
import module.util.DateUtil;

public class PayrollComponentDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from payroll_component order by id desc limit 1";
	private String getAllQuery = "select * from payroll_component where delete_date is null and delete_by is null";
	private String insertQuery = "insert into payroll_component(code,description,is_salary,is_thr,is_bonus,reference_document,input_date,input_by,edit_date,edit_by)VALUES(?,?,?,?,?,?,?,?,?,?);";
	private String updateQuery = "update payroll_component set code = ?,description = ?,is_salary = ?,is_thr = ?,is_bonus = ?,reference_document = ?,edit_date = ?,edit_by = ? WHERE id = ?;";
	private String deleteQuery = "update payroll_component set delete_date = ?, delete_by = ? where id = ?";

	public PayrollComponentDAO(Connection connection) {
		this.connection = connection;
	}

	public List<PayrollComponent> getAllData(String query){
		List<PayrollComponent> payrollComponents = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				PayrollComponent payrollComponent = new PayrollComponent();
				payrollComponent.setId(resultSet.getInt("id"));
				payrollComponent.setCode(resultSet.getString("code"));
				payrollComponent.setDescription(resultSet.getString("description"));
				payrollComponent.setIsSalary(resultSet.getInt("is_salary"));
				payrollComponent.setIsThr(resultSet.getInt("is_thr"));
				payrollComponent.setIsBonus(resultSet.getInt("is_bonus"));
				payrollComponent.setReferenceDocument(resultSet.getString("reference_document"));
				payrollComponent.setInputDate(resultSet.getDate("input_date"));
				payrollComponent.setInputBy(resultSet.getString("input_by"));
				payrollComponent.setEditDate(resultSet.getDate("edit_date"));
				payrollComponent.setEditBy(resultSet.getString("edit_by"));
				payrollComponent.setDeleteDate(resultSet.getDate("delete_date"));
				payrollComponent.setDeleteBy(resultSet.getString("delete_by"));
				payrollComponents.add(payrollComponent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return payrollComponents;
	}

	public void insert(PayrollComponent payrollComponent) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, payrollComponent.getCode());
			insertStatement.setString(2, payrollComponent.getDescription());
			insertStatement.setInt(3, payrollComponent.getIsSalary());
			insertStatement.setInt(4, payrollComponent.getIsThr());
			insertStatement.setInt(5, payrollComponent.getIsBonus());
			insertStatement.setString(6, payrollComponent.getReferenceDocument());
			insertStatement.setDate(7, DateUtil.toDate(payrollComponent.getInputDate()));
			insertStatement.setString(8, payrollComponent.getInputBy());
			insertStatement.setDate(9, DateUtil.toDate(payrollComponent.getEditDate()));
			insertStatement.setString(10, payrollComponent.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(PayrollComponent payrollComponent) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, payrollComponent.getCode());
			updateStatement.setString(2, payrollComponent.getDescription());
			updateStatement.setInt(3, payrollComponent.getIsSalary());
			updateStatement.setInt(4, payrollComponent.getIsThr());
			updateStatement.setInt(5, payrollComponent.getIsBonus());
			updateStatement.setString(6, payrollComponent.getReferenceDocument());
			updateStatement.setDate(7, DateUtil.toDate(payrollComponent.getEditDate()));
			updateStatement.setString(8, payrollComponent.getEditBy());
			updateStatement.setInt(9, payrollComponent.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(PayrollComponent payrollComponent) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(payrollComponent.getDeleteDate()));
			deleteStatement.setString(2, payrollComponent.getDeleteBy());
			deleteStatement.setInt(3, payrollComponent.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<PayrollComponent> payrollComponents = null;

		try {
			payrollComponents = new ArrayList<>();

			getLastIdStatment = connection.prepareStatement(getLastIdQuery);

			ResultSet resultSet = getLastIdStatment.executeQuery();

			while (resultSet.next()) {
				PayrollComponent PayrollComponent = new PayrollComponent();
				PayrollComponent.setId(resultSet.getInt("id"));

				payrollComponents.add(PayrollComponent);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return payrollComponents.size() < 1 ? 1 : payrollComponents.get(0).getId()+1;
	}
}