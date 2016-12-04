package module.personalia.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.personalia.dao.DivisionDAO;
import module.personalia.model.Division;

public class PersonaliaBL {
	
	private DataSource dataSource;
	
	public PersonaliaBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Division> getAllDivision() {
		List<Division> divisions = new ArrayList<Division>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			divisions = new DivisionDAO(connection).getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return divisions;
	}

	public void save(Division division) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DivisionDAO(connection).insert(division);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Division division) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DivisionDAO(connection).update(division);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Division division) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DivisionDAO(connection).delete(division);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Integer getLastId() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new DivisionDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
