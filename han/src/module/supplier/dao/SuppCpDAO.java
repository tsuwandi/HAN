package module.supplier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.supplier.model.SuppCp;
import module.util.DateUtil;

public class SuppCpDAO {
	private Connection connection;

	private PreparedStatement getAllBySuppCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllBySuppCodeQuery = "select id, supp_code, name, department, phone,"
			+ "email from supp_cp where supp_code = ? and deleted_date is null";

	private String insertQuery = "insert into supp_cp (supp_code, name, department, phone, "
			+ "email, input_date, input_by) values (?,?,?,?,?,?,?)";

	private String updateQuery = "update supp_cp set name=?, department=?, phone=?, "
			+ "email=?, edit_date=?, edited_by=? where supp_code=?";

	private String deleteQuery = "update supp_cp set deleted_date=?, deleted_by=? ";

	public SuppCpDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<SuppCp> getAllBySuppCode(String suppCode) throws SQLException {
		List<SuppCp> suppCps = new ArrayList<SuppCp>();

		try {
			getAllBySuppCodeStatement = connection.prepareStatement(getAllBySuppCodeQuery);
			getAllBySuppCodeStatement.setString(1, suppCode);

			ResultSet rs = getAllBySuppCodeStatement.executeQuery();
			while (rs.next()) {
				SuppCp suppCp = new SuppCp();
				suppCp.setId(rs.getInt("id"));
				suppCp.setSuppCode(rs.getString("supp_code"));
				suppCp.setName(rs.getString("name"));
				suppCp.setDepartment(rs.getString("department"));
				suppCp.setPhone(rs.getString("phone"));
				suppCp.setEmail(rs.getString("email"));

				suppCps.add(suppCp);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return suppCps;
	}

	public void save(SuppCp suppCp) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, suppCp.getSuppCode());
			insertStatement.setString(2, suppCp.getName());
			insertStatement.setString(3, suppCp.getDepartment());
			insertStatement.setString(4, suppCp.getPhone());
			insertStatement.setString(5, suppCp.getEmail());
			insertStatement.setDate(6, DateUtil.getCurrentDate());
			insertStatement.setString(7, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}

	public void update(SuppCp suppCp) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, suppCp.getName());
			updateStatement.setString(2, suppCp.getDepartment());
			updateStatement.setString(3, suppCp.getPhone());
			updateStatement.setString(4, suppCp.getEmail());
			updateStatement.setDate(5, DateUtil.getCurrentDate());
			updateStatement.setString(6, "timotius");
			updateStatement.setString(7, suppCp.getSuppCode());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}

	public void deleteAll(String suppCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where supp_code=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, suppCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id=? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setInt(3, id);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
}
