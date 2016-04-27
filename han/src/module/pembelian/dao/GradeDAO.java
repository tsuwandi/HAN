package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.pembelian.model.Grade;

public class GradeDAO {
	private DataSource dataSource;

	private PreparedStatement getAllStatement;

	private String getAllQuery = "SELECT id, grade"
			+ " FROM grade ";

	public GradeDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
		
	}

	public List<Grade> getAll() throws SQLException {
		Connection con = null;
		ArrayList<Grade> grades = new ArrayList<Grade>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);
			
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setId(rs.getInt("id"));
				grade.setGrade(rs.getString("grade"));
				grades.add(grade);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return grades;
	}
}
