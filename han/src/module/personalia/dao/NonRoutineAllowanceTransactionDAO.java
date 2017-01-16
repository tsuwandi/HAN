package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.NonRoutineAllowanceTransaction;
import module.util.DateUtil;

public class NonRoutineAllowanceTransactionDAO {
	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from tnr_type order by id desc limit 1";
	private String getAllQuery = "select * from tnr_trx where delete_date is null and delete_by is null";
	private String insertQuery = "insert into tnr_trx (employee_id, effective_start_month, effective_start_year, effective_end_month, effective_end_year, tnr_id, tnr_type_id, nominal, ref_number, input_date, input_by, edit_date, edit_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "update tnr_trx set employee_id = ?, effective_start_month = ?, effective_start_year = ?, effective_end_month = ?, effective_end_year = ?, tnr_id = ?, tnr_type_id = ?, nominal = ?, ref_number = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update tnr_trx set delete_date = ?, delete_by = ? where id = ?";

	public NonRoutineAllowanceTransactionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<NonRoutineAllowanceTransaction> getAllData(String query){
		List<NonRoutineAllowanceTransaction> nonRoutineAllowanceTransactions = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction = new NonRoutineAllowanceTransaction();
				nonRoutineAllowanceTransaction.setId(resultSet.getInt("id"));
				nonRoutineAllowanceTransaction.setEmplyeeId(resultSet.getString("employee_id"));
				nonRoutineAllowanceTransaction.setEffectiveStartMonth(resultSet.getInt("effective_start_month"));
				nonRoutineAllowanceTransaction.setEffectiveStartYear(resultSet.getInt("effective_start_year"));
				nonRoutineAllowanceTransaction.setEffectiveEndMonth(resultSet.getInt("effective_end_month"));
				nonRoutineAllowanceTransaction.setEffectiveEndYear(resultSet.getInt("effective_end_year"));
				nonRoutineAllowanceTransaction.setTnrId(resultSet.getInt("tnr_id"));
				nonRoutineAllowanceTransaction.setNonRoutineAllowanceMaster(ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasters(" and id = "+nonRoutineAllowanceTransaction.getTnrId()).get(0));
				nonRoutineAllowanceTransaction.setTnrTypeId(resultSet.getInt("tnr_type_id"));
				nonRoutineAllowanceTransaction.setNonRoutineAllowanceMasterType(ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasterTypes(" and id = "+nonRoutineAllowanceTransaction.getTnrTypeId()).get(0));
				nonRoutineAllowanceTransaction.setNominal(resultSet.getBigDecimal("nominal"));
				nonRoutineAllowanceTransaction.setReferenceNumber(resultSet.getString("ref_number"));
				nonRoutineAllowanceTransaction.setInputDate(resultSet.getDate("input_date"));
				nonRoutineAllowanceTransaction.setInputBy(resultSet.getString("input_by"));
				nonRoutineAllowanceTransaction.setEditDate(resultSet.getDate("edit_date"));
				nonRoutineAllowanceTransaction.setEditBy(resultSet.getString("edit_by"));
				nonRoutineAllowanceTransaction.setDeleteDate(resultSet.getDate("delete_date"));
				nonRoutineAllowanceTransaction.setDeleteBy(resultSet.getString("delete_by"));
				
				nonRoutineAllowanceTransactions.add(nonRoutineAllowanceTransaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nonRoutineAllowanceTransactions;
	}

	public void insert(NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, nonRoutineAllowanceTransaction.getEmployeeCode());
			insertStatement.setInt(2, nonRoutineAllowanceTransaction.getEffectiveStartMonth());
			insertStatement.setInt(3, nonRoutineAllowanceTransaction.getEffectiveStartYear());
			insertStatement.setInt(4, nonRoutineAllowanceTransaction.getEffectiveEndMonth());
			insertStatement.setInt(5, nonRoutineAllowanceTransaction.getEffectiveEndYear());
			insertStatement.setInt(6, nonRoutineAllowanceTransaction.getTnrId());
			insertStatement.setInt(7, nonRoutineAllowanceTransaction.getTnrTypeId());
			insertStatement.setBigDecimal(8, nonRoutineAllowanceTransaction.getNominal());
			insertStatement.setString(9, nonRoutineAllowanceTransaction.getReferenceNumber());
			insertStatement.setDate(10, DateUtil.toDate(nonRoutineAllowanceTransaction.getInputDate()));
			insertStatement.setString(11, nonRoutineAllowanceTransaction.getInputBy());
			insertStatement.setDate(12, DateUtil.toDate(nonRoutineAllowanceTransaction.getEditDate()));
			insertStatement.setString(13, nonRoutineAllowanceTransaction.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, nonRoutineAllowanceTransaction.getEmplyeeId());
			updateStatement.setInt(2, nonRoutineAllowanceTransaction.getEffectiveStartMonth());
			updateStatement.setInt(3, nonRoutineAllowanceTransaction.getEffectiveStartYear());
			updateStatement.setInt(4, nonRoutineAllowanceTransaction.getEffectiveEndMonth());
			updateStatement.setInt(5, nonRoutineAllowanceTransaction.getEffectiveEndYear());
			updateStatement.setInt(6, nonRoutineAllowanceTransaction.getTnrId());
			updateStatement.setInt(7, nonRoutineAllowanceTransaction.getTnrTypeId());
			updateStatement.setString(8, nonRoutineAllowanceTransaction.getReferenceNumber());
			updateStatement.setDate(9, DateUtil.toDate(nonRoutineAllowanceTransaction.getEditDate()));
			updateStatement.setString(10, nonRoutineAllowanceTransaction.getEditBy());
			updateStatement.setInt(11, nonRoutineAllowanceTransaction.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(nonRoutineAllowanceTransaction.getDeleteDate()));
			deleteStatement.setString(2, nonRoutineAllowanceTransaction.getDeleteBy());
			deleteStatement.setInt(3, nonRoutineAllowanceTransaction.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<NonRoutineAllowanceTransaction> nonRoutineAllowanceTransactions = null;
		
		try {
			nonRoutineAllowanceTransactions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction = new NonRoutineAllowanceTransaction();
				nonRoutineAllowanceTransaction.setId(resultSet.getInt("id"));
				nonRoutineAllowanceTransactions.add(nonRoutineAllowanceTransaction);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return nonRoutineAllowanceTransactions.size() < 1 ? 1 : nonRoutineAllowanceTransactions.get(0).getId()+1;
	}
}