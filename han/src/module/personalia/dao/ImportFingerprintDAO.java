package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ServiceFactory;
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
	private String insertQuery = "insert into import_fingerprint (file_name, date,  input_date, input_by) values (?, ?, ?, ?)";
	private String updateQuery = "update import_fingerprint set file_name = ?,date = ?, edit_date = ?, edited_by = ? where id = ?";
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
				importFingerprint.setFileName(resultSet.getString("file_name"));
				importFingerprint.setDate(resultSet.getDate("date"));
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
			insertStatement.setString(1, importFingerprint.getFileName());
			insertStatement.setDate(2, DateUtil.toDate(importFingerprint.getDate()));
			insertStatement.setDate(3, DateUtil.toDate(new Date()));
			insertStatement.setString(4, ServiceFactory.getSystemBL().getUsernameActive());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(ImportFingerprint importFingerprint) throws SQLException{
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, importFingerprint.getFileName());
			updateStatement.setDate(2, DateUtil.toDate(importFingerprint.getDate()));
			updateStatement.setDate(3, DateUtil.toDate(new Date()));
			updateStatement.setString(4, ServiceFactory.getSystemBL().getUsernameActive());
			updateStatement.setInt(5, importFingerprint.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(ImportFingerprint importFingerprint) throws SQLException {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(new Date()));
			deleteStatement.setString(2, ServiceFactory.getSystemBL().getUsernameActive());
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