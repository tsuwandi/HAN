package module.personalia.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.personalia.dao.DepartmentDAO;
import module.personalia.dao.DivisionDAO;
import module.personalia.dao.EmployeeDAO;
import module.personalia.dao.EmployeeTypeDAO;
import module.personalia.dao.GenderDAO;
import module.personalia.dao.MSPositionDAO;
import module.personalia.dao.MaritalDAO;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.personalia.model.Employee;
import module.personalia.model.EmployeeType;
import module.personalia.model.Gender;
import module.personalia.model.MSPosition;
import module.personalia.model.Marital;

public class PersonaliaBL {
	
	private DataSource dataSource;
	
	public PersonaliaBL(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Division> getDivisions(String query) {
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
	
	public List<Department> getDepartments(String query) {
		List<Department> departments = new ArrayList<Department>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			departments = new DepartmentDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return departments;
	}

	public void saveDepartment(Department department) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DepartmentDAO(connection).insert(department);
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
	
	public void updateDepartment(Department department) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DepartmentDAO(connection).update(department);
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
	
	public void deleteDepartment(Department department) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new DepartmentDAO(connection).delete(department);
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
	
	public Integer getLastIdDepartment() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new DepartmentDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<MSPosition> getMSPositions(String query) {
		List<MSPosition> msPositions = new ArrayList<MSPosition>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			msPositions = new MSPositionDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msPositions;
	}
	
	public void saveMSPosition(MSPosition msPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new MSPositionDAO(connection).insert(msPosition);
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
	
	public void updateMSPosition(MSPosition msPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new MSPositionDAO(connection).update(msPosition);
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
	
	public void deleteMSPosition(MSPosition msPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new MSPositionDAO(connection).delete(msPosition);
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
	
	public Integer getLastIdMSPosition() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new MSPositionDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<EmployeeType> getEmployeeTypes(String query) {
		List<EmployeeType> employeeTypes = new ArrayList<EmployeeType>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			employeeTypes = new EmployeeTypeDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employeeTypes;
	}
	
	public void saveEmployeeType(EmployeeType employeeType) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmployeeTypeDAO(connection).insert(employeeType);
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
	
	public void updateEmployeeType(EmployeeType employeeType) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmployeeTypeDAO(connection).update(employeeType);
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
	
	public void deleteEmployeeType(EmployeeType employeeType) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmployeeTypeDAO(connection).delete(employeeType);
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
	
	public Integer getLastIdEmployeeType() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new EmployeeTypeDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> getEmployees(String query) {
		List<Employee> employees = new ArrayList<Employee>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			employees = new EmployeeDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employees;
	}
	
	public void saveEmployee(Employee employee) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmployeeDAO(connection).insert(employee);
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
	
	public void updateEmployee(Employee employee) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmployeeDAO(connection).update(employee);
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
	
	public void deleteEmployee(Employee employee) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmployeeDAO(connection).delete(employee);
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
	
	public Integer getLastIdEmployee() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new EmployeeDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Gender> getGenders(String query){
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new GenderDAO(connection).getAllData(query);
	}
	
	public List<Marital> getMaritals(String query){
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new MaritalDAO(connection).getAllData(query);
	}
}