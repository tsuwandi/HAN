package module.personalia.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.personalia.dao.AttendanceDAO;
import module.personalia.dao.DepartmentDAO;
import module.personalia.dao.DivisionDAO;
import module.personalia.dao.EmpPositionDAO;
import module.personalia.dao.EmployeeDAO;
import module.personalia.dao.EmployeeTypeDAO;
import module.personalia.dao.GenderDAO;
import module.personalia.dao.MSPositionDAO;
import module.personalia.dao.MaritalDAO;
import module.personalia.dao.NonRoutineAllowanceMasterDAO;
import module.personalia.dao.NonRoutineAllowanceMasterTypeDAO;
import module.personalia.dao.NonRoutineAllowanceTransactionDAO;
import module.personalia.dao.PayrollComponentDAO;
import module.personalia.dao.PayrollMappingDAO;
import module.personalia.dao.SalarySettingDAO;
import module.personalia.dao.SsSalaryCompDAO;
import module.personalia.dao.SsTaxDAO;
import module.personalia.dao.TaxDAO;
import module.personalia.model.Attendance;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.personalia.model.EmpPosition;
import module.personalia.model.Employee;
import module.personalia.model.EmployeeType;
import module.personalia.model.Gender;
import module.personalia.model.MSPosition;
import module.personalia.model.Marital;
import module.personalia.model.NonRoutineAllowanceMaster;
import module.personalia.model.NonRoutineAllowanceMasterType;
import module.personalia.model.NonRoutineAllowanceTransaction;
import module.personalia.model.PayrollComponent;
import module.personalia.model.PayrollMapping;
import module.personalia.model.SalarySetting;
import module.personalia.model.SsSalaryComp;
import module.personalia.model.SsTax;
import module.personalia.model.Tax;

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
	// employee types
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
	// employee
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
	//
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
	
	public List<Tax> getTaxs(String query){
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new TaxDAO(connection).getAllData(query);
	}
	// payroll component
	public List<PayrollComponent> getPayrollComponents(String query) {
		List<PayrollComponent> payrollComponents = new ArrayList<PayrollComponent>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			payrollComponents = new PayrollComponentDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return payrollComponents;
	}
	
	public void savePayrollComponent(PayrollComponent payrollComponent) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new PayrollComponentDAO(connection).insert(payrollComponent);
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
	
	public void updatePayrollComponent(PayrollComponent payrollComponent) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new PayrollComponentDAO(connection).update(payrollComponent);
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
	
	public void deletePayrollComponent(PayrollComponent payrollComponent) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new PayrollComponentDAO(connection).delete(payrollComponent);
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
	
	public Integer getLastIdPayrollComponent() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new PayrollComponentDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	// payroll mapping
	public List<PayrollMapping> getPayrollMappings(String query) {
		List<PayrollMapping> payrollMappings = new ArrayList<PayrollMapping>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			payrollMappings = new PayrollMappingDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return payrollMappings;
	}
	
	public void savePayrollMapping(PayrollMapping payrollMapping) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new PayrollMappingDAO(connection).insert(payrollMapping);
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
	
	public void updatePayrollMapping(PayrollMapping payrollMapping) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new PayrollMappingDAO(connection).update(payrollMapping);
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
	
	public void deletePayrollMapping(PayrollMapping payrollMapping) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new PayrollMappingDAO(connection).delete(payrollMapping);
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
	
	public Integer getLastIdpayrollMapping() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new PayrollMappingDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	// salary settings
	public List<SalarySetting> getSalarySettings(String query) {
		List<SalarySetting> salarySettings = new ArrayList<SalarySetting>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			salarySettings = new SalarySettingDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salarySettings;
	}
	
	public void saveSalarySetting(SalarySetting salarySetting) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SalarySettingDAO(connection).insert(salarySetting);
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
	
	public void updateSalarySetting(SalarySetting salarySetting) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SalarySettingDAO(connection).update(salarySetting);
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
	
	public void deleteSalarySetting(SalarySetting salarySetting) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SalarySettingDAO(connection).delete(salarySetting);
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
	
	public Integer getLastIdSalarySetting() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new SalarySettingDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	// tnr master type
	public List<NonRoutineAllowanceMasterType> getNonRoutineAllowanceMasterTypes(String query) {
		List<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypes = new ArrayList<NonRoutineAllowanceMasterType>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			nonRoutineAllowanceMasterTypes = new NonRoutineAllowanceMasterTypeDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nonRoutineAllowanceMasterTypes;
	}
	
	public void saveNonRoutineAllowanceMasterType(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceMasterTypeDAO(connection).insert(nonRoutineAllowanceMasterType);
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
	
	public void updateNonRoutineAllowanceMasterType(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceMasterTypeDAO(connection).update(nonRoutineAllowanceMasterType);
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
	
	public void deleteNonRoutineAllowanceMasterType(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceMasterTypeDAO(connection).delete(nonRoutineAllowanceMasterType);
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
	
	public Integer getLastIdNonRoutineAllowanceMasterType() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new NonRoutineAllowanceMasterTypeDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	// tnr master
	public List<NonRoutineAllowanceMaster> getNonRoutineAllowanceMasters(String query) {
		List<NonRoutineAllowanceMaster> nonRoutineAllowanceMasters = new ArrayList<NonRoutineAllowanceMaster>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			nonRoutineAllowanceMasters = new NonRoutineAllowanceMasterDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nonRoutineAllowanceMasters;
	}
	
	public void saveNonRoutineAllowanceMaster(NonRoutineAllowanceMaster nonRoutineAllowanceMaster) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceMasterDAO(connection).insert(nonRoutineAllowanceMaster);
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
	
	public void updateNonRoutineAllowanceMaster(NonRoutineAllowanceMaster nonRoutineAllowanceMaster) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceMasterDAO(connection).update(nonRoutineAllowanceMaster);
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
	
	public void deleteNonRoutineAllowanceMaster(NonRoutineAllowanceMaster nonRoutineAllowanceMaster) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceMasterDAO(connection).delete(nonRoutineAllowanceMaster);
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
	
	public Integer getLastIdNonRoutineAllowanceMaster() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new NonRoutineAllowanceMasterDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	// tnr transaction
	public List<NonRoutineAllowanceTransaction> getNonRoutineAllowanceTransactions(String query) {
		List<NonRoutineAllowanceTransaction> nonRoutineAllowanceTransactions = new ArrayList<NonRoutineAllowanceTransaction>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			nonRoutineAllowanceTransactions = new NonRoutineAllowanceTransactionDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nonRoutineAllowanceTransactions;
	}
	
	public void saveNonRoutineAllowanceTransaction(NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceTransactionDAO(connection).insert(nonRoutineAllowanceTransaction);
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
	
	public void updateNonRoutineAllowanceTransaction(NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceTransactionDAO(connection).delete(nonRoutineAllowanceTransaction);
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
	
	public void deleteNonRoutineAllowanceTransaction(NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new NonRoutineAllowanceTransactionDAO(connection).delete(nonRoutineAllowanceTransaction);
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
	
	public Integer getLastIdNonRoutineAllowanceTransaction() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new NonRoutineAllowanceMasterDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	// emp position
	public List<EmpPosition> getEmpPositions(String query) {
		List<EmpPosition> empPositions = new ArrayList<EmpPosition>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			empPositions = new EmpPositionDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empPositions;
	}
	
	public void saveEmpPosition(EmpPosition empPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmpPositionDAO(connection).insert(empPosition);
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
	
	public void updateEmpPosition(EmpPosition empPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmpPositionDAO(connection).update(empPosition);
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
	
	public void deleteEmpPosition(EmpPosition empPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new EmpPositionDAO(connection).delete(empPosition);
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
	
	public Integer getLastIdEmpPosition() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new EmpPositionDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// ss salary
	public List<SsSalaryComp> getSsSalaryComps(String query) {
		List<SsSalaryComp> empPositions = new ArrayList<SsSalaryComp>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			empPositions = new SsSalaryCompDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empPositions;
	}
	
	public void saveSsSalaryComp(SsSalaryComp empPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SsSalaryCompDAO(connection).insert(empPosition);
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
	
	public void updateSsSalaryComp(SsSalaryComp empPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SsSalaryCompDAO(connection).update(empPosition);
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
	
	public void deleteSsSalaryComp(SsSalaryComp empPosition) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SsSalaryCompDAO(connection).delete(empPosition);
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
	
	public Integer getLastIdSsSalaryComp() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new SsSalaryCompDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// ss tax
	public List<SsTax> getSsTaxs(String query) {
		List<SsTax> ssTaxs = new ArrayList<SsTax>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			ssTaxs = new SsTaxDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ssTaxs;
	}
	
	public void saveSsTax(SsTax ssTax) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SsTaxDAO(connection).insert(ssTax);
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
	
	public void updateSsTax(SsTax ssTax) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SsTaxDAO(connection).update(ssTax);
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
	
	public void deleteSsTax(SsTax ssTax) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new SsTaxDAO(connection).delete(ssTax);
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
	
	public Integer getLastIdSsTax(){
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return new SsTaxDAO(connection).getLastId();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// overtime
	
	// attendance
	public List<Attendance> getAttendances(String query) {
		List<Attendance> attendances = new ArrayList<Attendance>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			attendances = new AttendanceDAO(connection).getAllData(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return attendances;
	}
	
	public void saveAttendance(Attendance attendance) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new AttendanceDAO(connection).insert(attendance);
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
	
	public void updateAttendance(Attendance attendance) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new AttendanceDAO(connection).update(attendance);
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
	
	public void deleteAttendance(Attendance attendance) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			new AttendanceDAO(connection).delete(attendance);
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
}