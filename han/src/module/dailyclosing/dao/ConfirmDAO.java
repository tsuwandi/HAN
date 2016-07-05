package module.dailyclosing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import module.dailyclosing.model.Confirm;
import module.util.DateUtil;

public class ConfirmDAO {
	private Connection connection;
	private PreparedStatement insertStatement;
	
	private String insertQuery = "insert into confirm (confirm_code, module, daily_closing_date, "
			+ "input_date, input_by) values (?,?,?,?,?)";
	
	public ConfirmDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	public void save(Confirm confirm) throws SQLException {
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, confirm.getConfirmCode());
			insertStatement.setString(2, confirm.getModule());
			insertStatement.setTimestamp(3, confirm.getDailyClosingDate());
			insertStatement.setDate(4, DateUtil.getCurrentDate());
			insertStatement.setString(5, "timotius");
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}

}
