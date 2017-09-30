package module.sn.grade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.sn.grade.model.Grade;

public class GradeDAO {
	private Connection connection;
	
	private PreparedStatement getAllGrade;
	private String gradeQuery = "select * from grade ";

	
	public GradeDAO(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public List<Grade> getAllGrade() throws SQLException {
		List<Grade> grades = new ArrayList<Grade>();

		try {
			String query = new StringBuilder().append(gradeQuery).append(" where delete_date is null order by id asc").toString();
			getAllGrade = connection.prepareStatement(query);
			ResultSet rs = getAllGrade.executeQuery();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setId(rs.getInt("id"));
				grade.setGrade(rs.getString("grade"));
				grades.add(grade);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return grades;
	}
	
	public List<Grade> getAllGradeByCategoryProductId(int productCategoryId) throws SQLException {
		List<Grade> grades = new ArrayList<Grade>();

		try {
			
			String query = new StringBuilder().append(gradeQuery).append(" where product_category_id = ? and delete_date is null order by id").toString();
			
			getAllGrade = connection.prepareStatement(query);
			getAllGrade.setInt(1, productCategoryId);
			
			ResultSet rs = getAllGrade.executeQuery();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setId(rs.getInt("id"));
				grade.setProductCategoryId(rs.getInt("product_category_id"));
				grade.setGrade(rs.getString("grade"));
				grades.add(grade);
			}

		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}

		return grades;
	}
}
