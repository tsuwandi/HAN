package module.personalia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.personalia.model.Division;
import module.personalia.model.SPL;
import module.util.DateUtil;

public class SPLDAO {

	private Connection connection;

	private PreparedStatement getLastIdStatment;
	private PreparedStatement getAllStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getLastIdQuery = "select * from division order by id desc limit 1";
	private String getAllQuery = "select * from division where delete_date is null and delete_by is null";
	private String insertQuery = "insert into division (id, name, input_date, input_by, edit_date, edit_by) values (?, ?, ?, ?, ?, ?)";
	private String updateQuery = "update division set name = ?, edit_date = ?, edit_by = ? where id = ?";
	private String deleteQuery = "update division set delete_date = ?, delete_by = ? where id = ?";
	
	public SPLDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<SPL> getAllData(String query){
		List<SPL> spls = new ArrayList<>();

		try {
			getAllStatement = connection.prepareStatement(getAllQuery+query);

			ResultSet resultSet = getAllStatement.executeQuery();

			while (resultSet.next()) {
				SPL spl = new SPL();
				spl.setId(resultSet.getInt("id"));
				spl.setSplCode(resultSet.getString("spl_code"));
				spl.setIncentiveDate(resultSet.getDate("incentive_date"));
				spl.setOtDate(resultSet.getDate("ot_date"));
				spl.setOtTimeStart(resultSet.getDate("ot_time_start"));
				spl.setOtTimeEnd(resultSet.getDate("ot_time_end"));
				spl.setDescription(resultSet.getString("description"));
				spl.setDocumentReference(resultSet.getString("document_reference"));
				spl.setInputDate(resultSet.getDate("input_date"));
				spl.setInputBy(resultSet.getString("input_by"));
				spl.setEditDate(resultSet.getDate("edit_date"));
				spl.setEditBy(resultSet.getString("edit_by"));
				spl.setDeleteDate(resultSet.getDate("delete_date"));
				spl.setDeleteBy(resultSet.getString("delete_by"));
				spls.add(spl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return spls;
	}

	public void insert(SPL spl) {
		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setString(1, spl.getSplCode());
			insertStatement.setDate(2, DateUtil.toDate(spl.getIncentiveDate()));
			insertStatement.setDate(3, DateUtil.toDate(spl.getInputDate()));
			insertStatement.setString(4, spl.getInputBy());
			insertStatement.setDate(5, DateUtil.toDate(spl.getEditDate()));
			insertStatement.setString(6, spl.getEditBy());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Division division) {
		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, division.getName());
			updateStatement.setDate(2, DateUtil.toDate(division.getEditDate()));
			updateStatement.setString(3, division.getEditBy());
			updateStatement.setString(4, division.getId());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Division division) {
		try {
			deleteStatement = connection.prepareStatement(deleteQuery);

			deleteStatement.setDate(1, DateUtil.toDate(division.getDeleteDate()));
			deleteStatement.setString(2, division.getDeleteBy());
			deleteStatement.setString(3, division.getId());

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getLastId() {
		List<Division> divisions = null;
		
		try {
			divisions = new ArrayList<>();
			
			getLastIdStatment = connection.prepareStatement(getLastIdQuery);
			
			ResultSet resultSet = getLastIdStatment.executeQuery();
			
			while (resultSet.next()) {
				Division division = new Division();
				division.setId(resultSet.getString("id"));
				division.setName(resultSet.getString("name"));

				divisions.add(division);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return divisions.size() < 1 ? 1 : Integer.parseInt(divisions.get(0).getId().substring(3))+1;
	}
}
