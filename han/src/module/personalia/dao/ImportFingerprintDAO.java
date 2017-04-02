package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.ImportFingerprint;
import module.util.DateUtil;

public class ImportFingerprintDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from import_fingerprint order by id desc limit 1";
	private String getAllQuery = "select * from import_fingerprint where deleted_date is null and deleted_by is null";
	private String insertQuery = "insert into import_fingerprint (id, name, input_date, input_by, edited_date, edited_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update import_fingerprint set name = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update import_fingerprint set deleted_date = ?, deleted_by = ? where id = ?";

	public ImportFingerprintDAO(Connection connection) {
		this.connection = connection;
	}

	public List<ImportFingerprint> getAllData(String query) throws SQLException{
		List<ImportFingerprint> attendances = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				ImportFingerprint importFingerprint = new ImportFingerprint();
				importFingerprint.setId(resultSet.getInt("id"));
				
				importFingerprint.setInputDate(resultSet.getDate("input_date"));
				importFingerprint.setInputBy(resultSet.getString("input_by"));
				importFingerprint.setEditDate(resultSet.getDate("edit_date"));
				importFingerprint.setDeleteDate(resultSet.getDate("deleted_date"));
				importFingerprint.setDeleteBy(resultSet.getString("delete_by"));
				attendances.add(importFingerprint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attendances;
	}

	public void insert(ImportFingerprint importFingerprint) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			
			insertStatement.setDate(15, DateUtil.toDate(importFingerprint.getInputDate()));
			insertStatement.setString(16, importFingerprint.getInputBy());
			insertStatement.setDate(17, DateUtil.toDate(importFingerprint.getEditDate()));
			insertStatement.setString(18, importFingerprint.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(ImportFingerprint importFingerprint) throws SQLException{
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			
			updateStatement.setDate(15, DateUtil.toDate(importFingerprint.getEditDate()));
			updateStatement.setString(16, importFingerprint.getEditBy());
			updateStatement.setInt(17, importFingerprint.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(ImportFingerprint importFingerprint) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(importFingerprint.getDeleteDate()));
			deleteStatement.setString(2, importFingerprint.getDeleteBy());
			deleteStatement.setInt(3, importFingerprint.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<ImportFingerprint> importFingerprints = null;
		
		try {
			importFingerprints = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				ImportFingerprint importFingerprint = new ImportFingerprint();
				importFingerprint.setId(resultSet.getInt("id"));
				
				importFingerprints.add(importFingerprint);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return importFingerprints.size() < 1 ? 1 :importFingerprints.get(0).getId()+1;
	}
}