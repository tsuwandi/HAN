package module.personalia.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.personalia.dao.DepartementDAO;
import module.personalia.dao.DivisionDAO;
import module.personalia.model.Departement;
import module.personalia.model.Division;

public class PersonaliaBL {
	
	private DataSource dataSource;
	
	public PersonaliaBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Division> getAllDivision(String query) {
		List<Division> divisions = new ArrayList<Division>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			divisions = new DivisionDAO(connection).getAllData(query);
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

	public void saveDivision(Division division) {
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
	
	public void updateDivision(Division division) {
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
	
	public void deleteDivision(Division division) {
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
	
	public Integer getLastIdDivision() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new DivisionDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Departement> getDepartements(String query) {
		List<Departement> departements = new ArrayList<Departement>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			departements = new DepartementDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return departements;
	}

	public void saveDepartement(Departement departement) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DepartementDAO(connection).insert(departement);
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
	
	public void updateDepartement(Departement departement) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DepartementDAO(connection).update(departement);
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
	
	public void deleteDepartement(Departement departement) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DepartementDAO(connection).delete(departement);
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
	
	public Integer getLastIdDepartement() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new DepartementDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
