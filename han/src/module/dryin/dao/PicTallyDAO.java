package module.dryin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.dryin.model.PicTally;
import module.employee.model.Employee;
import module.util.DateUtil;

public class PicTallyDAO {

	private Connection connection;

	private PreparedStatement getAllByDryInCodeStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;

	private String getAllByDryInCodeQuery = "select p.id, p.dry_in_code, p.emp_code, e.employee_id, e.employee_name "
			+ " from pic_tally p " + " inner join employee e on e.employee_id = p.emp_code "
			+ " where p.dry_in_code = ? and p.deleted_date is null ";
	private String insertQuery = "insert into pic_tally (dry_in_code, emp_code, "
			+ "input_date, input_by) values (?,?,?,?)";
	private String updateQuery = "update pic_tally set emp_code=?, "
			+ "edit_date=?, edited_by=? where id=? ";
	private String deleteQuery = "update pic_tally set deleted_date=?, deleted_by=? ";

	public PicTallyDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<PicTally> getAllByDryInCode(String dryInCode) throws SQLException {
		List<PicTally> picTallies = new ArrayList<PicTally>();

		try {
			getAllByDryInCodeStatement = connection.prepareStatement(getAllByDryInCodeQuery);
			getAllByDryInCodeStatement.setString(1, dryInCode);

			ResultSet rs = getAllByDryInCodeStatement.executeQuery();
			while (rs.next()) {
				PicTally picTally = new PicTally();
				picTally.setId(rs.getInt("id"));
				picTally.setDryInCode(rs.getString("dry_in_code"));
				picTally.setEmpCode(rs.getString("emp_code"));
				
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeeName(rs.getString("employee_name"));
				
				picTally.setEmployee(employee);
				
				picTallies.add(picTally);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return picTallies;
	}

	public void save(PicTally picTally) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, picTally.getDryInCode());
			insertStatement.setString(2, picTally.getEmpCode());
			insertStatement.setDate(3, DateUtil.getCurrentDate());
			insertStatement.setString(4, "timotius");
			insertStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void update(PicTally picTally) throws SQLException {
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, picTally.getEmpCode());
			updateStatement.setDate(2, DateUtil.getCurrentDate());
			updateStatement.setString(3, "timotius");
			updateStatement.setInt(4, picTally.getId());
			updateStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
	
	public void deleteById(int id) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where id = ? ").toString();

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

	public void deleteAll(String dryInCode) throws SQLException {
		try {
			String query = new StringBuilder().append(deleteQuery).append("where dry_in_code = ? ").toString();

			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setDate(1, DateUtil.getCurrentDate());
			deleteStatement.setString(2, "timotius");
			deleteStatement.setString(3, dryInCode);
			deleteStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}
}
