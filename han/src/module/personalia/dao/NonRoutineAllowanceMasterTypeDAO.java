package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ServiceFactory;
import module.personalia.model.NonRoutineAllowanceMasterType;
import module.util.DateUtil;

public class NonRoutineAllowanceMasterTypeDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from tnr_type order by id desc limit 1";
	private String getAllQuery = "select * from tnr_type where delete_date is null and delete_by is null";
	private String insertQuery = "insert into tnr_type (tnr_type, tax_id, ref_document, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update tnr_type set tnr_type = ?, tax_id = ?, ref_document = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update tnr_type set delete_date = ?, delete_by = ? where id = ?";

	public NonRoutineAllowanceMasterTypeDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<NonRoutineAllowanceMasterType> getAllData(String query){
		List<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypes = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType = new NonRoutineAllowanceMasterType();
				nonRoutineAllowanceMasterType.setId(resultSet.getInt("id"));
				nonRoutineAllowanceMasterType.setTnrType(resultSet.getString("tnr_type"));
				nonRoutineAllowanceMasterType.setTaxId(resultSet.getInt("tax_id"));
				nonRoutineAllowanceMasterType.setTax(ServiceFactory.getPersonaliaBL().getTaxs(" and id = "+nonRoutineAllowanceMasterType.getTaxId()).get(0));
				nonRoutineAllowanceMasterType.setReferenceDocument(resultSet.getString("ref_document"));
				nonRoutineAllowanceMasterType.setInputDate(resultSet.getDate("input_date"));
				nonRoutineAllowanceMasterType.setInputBy(resultSet.getString("input_by"));
				nonRoutineAllowanceMasterType.setEditDate(resultSet.getDate("edit_date"));
				nonRoutineAllowanceMasterType.setEditBy(resultSet.getString("edit_by"));
				nonRoutineAllowanceMasterType.setDeleteDate(resultSet.getDate("delete_date"));
				nonRoutineAllowanceMasterType.setDeleteBy(resultSet.getString("delete_by"));
				
				nonRoutineAllowanceMasterTypes.add(nonRoutineAllowanceMasterType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nonRoutineAllowanceMasterTypes;
	}

	public void insert(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, nonRoutineAllowanceMasterType.getTnrType());
			insertStatement.setInt(2, nonRoutineAllowanceMasterType.getTaxId());
			insertStatement.setString(3, nonRoutineAllowanceMasterType.getReferenceDocument());
			insertStatement.setDate(4, DateUtil.toDate(nonRoutineAllowanceMasterType.getInputDate()));
			insertStatement.setString(5, nonRoutineAllowanceMasterType.getInputBy());
			insertStatement.setDate(6, DateUtil.toDate(nonRoutineAllowanceMasterType.getEditDate()));
			insertStatement.setString(7, nonRoutineAllowanceMasterType.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, nonRoutineAllowanceMasterType.getTnrType());
			updateStatement.setInt(2, nonRoutineAllowanceMasterType.getTaxId());
			updateStatement.setString(3, nonRoutineAllowanceMasterType.getReferenceDocument());
			updateStatement.setDate(7, DateUtil.toDate(nonRoutineAllowanceMasterType.getEditDate()));
			updateStatement.setString(8, nonRoutineAllowanceMasterType.getEditBy());
			updateStatement.setInt(9, nonRoutineAllowanceMasterType.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(nonRoutineAllowanceMasterType.getDeleteDate()));
			deleteStatement.setString(2, nonRoutineAllowanceMasterType.getDeleteBy());
			deleteStatement.setInt(3, nonRoutineAllowanceMasterType.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypes = null;
		
		try {
			nonRoutineAllowanceMasterTypes = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				NonRoutineAllowanceMasterType salarySetting = new NonRoutineAllowanceMasterType();
				salarySetting.setId(resultSet.getInt("id"));
				nonRoutineAllowanceMasterTypes.add(salarySetting);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return nonRoutineAllowanceMasterTypes.size() < 1 ? 1 : nonRoutineAllowanceMasterTypes.get(0).getId()+1;
	}
}