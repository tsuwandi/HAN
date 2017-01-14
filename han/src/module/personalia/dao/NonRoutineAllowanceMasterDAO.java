package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.NonRoutineAllowanceMaster;
import module.util.DateUtil;

public class NonRoutineAllowanceMasterDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from tnr_type order by id desc limit 1";
	private String getAllQuery = "select * from tnr_type where delete_date is null and delete_by is null";
	private String insertQuery = "insert into tnr_type (tnr_type, tax_id, reference_doc, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update tnr_type set tnr_type = ?, tax_id = ?, reference_doc = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update tnr_type set delete_date = ?, delete_by = ? where id = ?";

	public NonRoutineAllowanceMasterDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<NonRoutineAllowanceMaster> getAllData(String query){
		List<NonRoutineAllowanceMaster> nonRoutineAllowanceMasters = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				NonRoutineAllowanceMaster nonRoutineAllowanceMaster = new NonRoutineAllowanceMaster();
				nonRoutineAllowanceMaster.setId(resultSet.getInt("id"));
				nonRoutineAllowanceMaster.setTnr(resultSet.getString("tnr"));
				nonRoutineAllowanceMaster.setTnrTypeId(resultSet.getInt("tnr_type_id"));
				nonRoutineAllowanceMaster.setReferenceDocument(resultSet.getString("reference_doc"));
				nonRoutineAllowanceMaster.setInputDate(resultSet.getDate("input_date"));
				nonRoutineAllowanceMaster.setInputBy(resultSet.getString("input_by"));
				nonRoutineAllowanceMaster.setEditDate(resultSet.getDate("edit_date"));
				nonRoutineAllowanceMaster.setEditBy(resultSet.getString("edit_by"));
				nonRoutineAllowanceMaster.setDeleteDate(resultSet.getDate("delete_date"));
				nonRoutineAllowanceMaster.setDeleteBy(resultSet.getString("delete_by"));
				
				nonRoutineAllowanceMasters.add(nonRoutineAllowanceMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nonRoutineAllowanceMasters;
	}

	public void insert(NonRoutineAllowanceMaster nonRoutineAllowanceMaster) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, nonRoutineAllowanceMaster.getTnr());
			insertStatement.setInt(2, nonRoutineAllowanceMaster.getTnrTypeId());
			insertStatement.setString(3, nonRoutineAllowanceMaster.getReferenceDocument());
			insertStatement.setDate(4, DateUtil.toDate(nonRoutineAllowanceMaster.getInputDate()));
			insertStatement.setString(5, nonRoutineAllowanceMaster.getInputBy());
			insertStatement.setDate(6, DateUtil.toDate(nonRoutineAllowanceMaster.getEditDate()));
			insertStatement.setString(7, nonRoutineAllowanceMaster.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(NonRoutineAllowanceMaster nonRoutineAllowanceMaster) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, nonRoutineAllowanceMaster.getTnr());
			updateStatement.setInt(2, nonRoutineAllowanceMaster.getTnrTypeId());
			updateStatement.setString(3, nonRoutineAllowanceMaster.getReferenceDocument());
			updateStatement.setDate(4, DateUtil.toDate(nonRoutineAllowanceMaster.getEditDate()));
			updateStatement.setString(5, nonRoutineAllowanceMaster.getEditBy());
			updateStatement.setInt(6, nonRoutineAllowanceMaster.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(NonRoutineAllowanceMaster nonRoutineAllowanceMasterType) {
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
		List<NonRoutineAllowanceMaster> nonRoutineAllowanceMasters = null;
		
		try {
			nonRoutineAllowanceMasters = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				NonRoutineAllowanceMaster nonRoutineAllowanceMaster = new NonRoutineAllowanceMaster();
				nonRoutineAllowanceMaster.setId(resultSet.getInt("id"));
				nonRoutineAllowanceMasters.add(nonRoutineAllowanceMaster);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return nonRoutineAllowanceMasters.size() < 1 ? 1 : nonRoutineAllowanceMasters.get(0).getId()+1;
	}
}