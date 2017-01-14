package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.PayrollMapping;
import module.util.DateUtil;

public class PayrollMappingDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from salary_mapping order by id desc limit 1";
	private String getAllQuery = "select * from salary_mapping where delete_date is null and delete_by is null";
	private String insertQuery = "insert into salary_mapping(code,position_id,payroll_component_code,is_absent,is_leave,reference_document,input_date,input_by,edit_date,edit_by) values (?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update salary_mapping set code = ?, position_id = ?, payroll_component_code = ?, is_absent = ?, is_leave = ?, reference_document = ?, edit_date = ?, edit_by = ? WHERE id = ?";
	private String deleteQuery = "update salary_mapping set delete_date = ?, delete_by = ? where id = ?";

	public PayrollMappingDAO(Connection connection) {
		this.connection = connection;
	}

	public List<PayrollMapping> getAllData(String query){
		List<PayrollMapping> payrollMappings = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				PayrollMapping payrollMapping = new PayrollMapping();
				payrollMapping.setId(resultSet.getInt("id"));
				payrollMapping.setCode(resultSet.getString("code"));
				payrollMapping.setPositionId(resultSet.getString("position_id"));
				payrollMapping.setMsPosition(ServiceFactory.getPersonaliaBL().getMSPositions(" and id = '"+payrollMapping.getPositionId()+"'").get(0));
				payrollMapping.setPayrollComponentCode(resultSet.getString("payroll_component_code"));
				payrollMapping.setPayrollComponent(ServiceFactory.getPersonaliaBL().getPayrollComponents(" and code = '"+payrollMapping.getPayrollComponentCode()+"'").get(0));
				payrollMapping.setIsAbsent(resultSet.getInt("is_absent"));
				payrollMapping.setIsLeave(resultSet.getInt("is_leave"));
				payrollMapping.setReferenceDocument(resultSet.getString("reference_document"));
				payrollMapping.setInputDate(resultSet.getDate("input_date"));
				payrollMapping.setInputBy(resultSet.getString("input_by"));
				payrollMapping.setEditDate(resultSet.getDate("edit_date"));
				payrollMapping.setEditBy(resultSet.getString("edit_by"));
				payrollMapping.setDeleteDate(resultSet.getDate("delete_date"));
				payrollMapping.setDeleteBy(resultSet.getString("delete_by"));
				payrollMappings.add(payrollMapping);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return payrollMappings;
	}

	public void insert(PayrollMapping payrollMapping) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, payrollMapping.getCode());
			insertStatement.setString(2, payrollMapping.getPositionId());
			insertStatement.setString(3, payrollMapping.getPayrollComponentCode());
			insertStatement.setInt(4, payrollMapping.getIsAbsent());
			insertStatement.setInt(5, payrollMapping.getIsLeave());
			insertStatement.setString(6, payrollMapping.getReferenceDocument());
			insertStatement.setDate(7, DateUtil.toDate(payrollMapping.getInputDate()));
			insertStatement.setString(8, payrollMapping.getInputBy());
			insertStatement.setDate(9, DateUtil.toDate(payrollMapping.getEditDate()));
			insertStatement.setString(10, payrollMapping.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(PayrollMapping payrollMapping) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, payrollMapping.getCode());
			updateStatement.setString(2, payrollMapping.getPositionId());
			updateStatement.setString(3, payrollMapping.getPayrollComponentCode());
			updateStatement.setInt(4, payrollMapping.getIsAbsent());
			updateStatement.setInt(5, payrollMapping.getIsLeave());
			updateStatement.setString(6, payrollMapping.getReferenceDocument());
			updateStatement.setDate(7, DateUtil.toDate(payrollMapping.getEditDate()));
			updateStatement.setString(8, payrollMapping.getEditBy());
			updateStatement.setInt(9, payrollMapping.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(PayrollMapping payrollMapping) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(payrollMapping.getDeleteDate()));
			deleteStatement.setString(2, payrollMapping.getDeleteBy());
			deleteStatement.setInt(3, payrollMapping.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<PayrollMapping> payrollMappings = null;

		try {
			payrollMappings = new ArrayList<>();

			getLastIdStatment = connection.prepareStatement(getLastIdQuery);

			ResultSet resultSet = getLastIdStatment.executeQuery();

			while (resultSet.next()) {
				PayrollMapping PayrollComponent = new PayrollMapping();
				PayrollComponent.setId(resultSet.getInt("id"));

				payrollMappings.add(PayrollComponent);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return payrollMappings.size() < 1 ? 1 : payrollMappings.get(0).getId()+1;
	}
}