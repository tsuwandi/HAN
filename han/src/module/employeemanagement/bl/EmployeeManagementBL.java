package module.employeemanagement.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.DialogBox;
import module.employeemanagement.dao.EmployeeDAO;
import module.employeemanagement.dao.EmployeeStructureDAO;
import module.employeemanagement.dao.OrgValueDAO;
import module.employeemanagement.dao.PayrollComponentDAO;
import module.employeemanagement.dao.PositionDAO;
import module.employeemanagement.model.Employee;
import module.employeemanagement.model.EmployeeStructure;
import module.employeemanagement.model.OrgValue;
import module.employeemanagement.model.PayrollComponent;
import module.employeemanagement.model.Position;

public class EmployeeManagementBL {
	private EmployeeDAO employeeDAO;
	private PositionDAO positionDAO;
	private PayrollComponentDAO payrollComponentDAO;
	private OrgValueDAO orgValueDAO;
	private EmployeeStructureDAO employeeStructureDAO;
 	private DataSource dataSource;
	
	public EmployeeManagementBL(DataSource dataSource){
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			positionDAO = new PositionDAO(con);
			employeeDAO = new EmployeeDAO(con);
			orgValueDAO = new OrgValueDAO(con);
			payrollComponentDAO = new PayrollComponentDAO(con);
			employeeStructureDAO = new EmployeeStructureDAO(con);
		} catch (SQLException e) {
			DialogBox.showError("Cannot Connect to Database");
			e.printStackTrace();
		}
		
	}
	
	public List<OrgValue> getDivisons(){
		return orgValueDAO.getAllData("");
	}
	
	public void saveEmpStructure(EmployeeStructure emp){
		employeeStructureDAO.insert(emp);
	}
	
	public void deleteEmpStructure(EmployeeStructure emp){
		employeeStructureDAO.delete(emp);
	}
	
	
	public void updateEmpStructure(EmployeeStructure emp){
		employeeStructureDAO.update(emp);
	}
	
	public List<EmployeeStructure> getEmpStructure(){
		return employeeStructureDAO.getAllData("");
	}
	
	public void save(Employee emp){
		employeeDAO.insert(emp);
	}
	
	public void delete(Employee emp){
		employeeDAO.delete(emp);
	}
	
	
	public void update(Employee emp){
		employeeDAO.update(emp);
	}
	
	public List<Employee> getEmployee(){
		return employeeDAO.getAllData("");
	}
	
	public void savePosition(Position pos){
		positionDAO.insert(pos);
	}
	
	public void deletePosition(Position pos){
		positionDAO.delete(pos);
	}
	
	
	public void updatePosition(Position pos){
		positionDAO.update(pos);
	}
	
	public List<Position> getPosition(){
		return positionDAO.getAllData();
	}
	
	public void savePayrollComponent(PayrollComponent payrollComponent){
		payrollComponentDAO.insert(payrollComponent);
	}
	
	public void deletePayrollComponent(PayrollComponent payrollComponent){
		payrollComponentDAO.delete(payrollComponent);
	}
	
	
	public void updatePayrollComponent(PayrollComponent payrollComponent){
		payrollComponentDAO.update(payrollComponent);
	}
	
	public List<PayrollComponent> getPayrollComponent(){
		return payrollComponentDAO.getAllData("");
	}
	
	public String lastEmpCode(){
		try {
			String code = employeeDAO.getLastCode();
			if(code==null){
				return "EMP00001";
			}else{
				String codeNumber = code.substring(3, code.length());
				int increment = Integer.valueOf(codeNumber)+1;
				String codeReturn="";
				switch (String.valueOf(increment).length()) {
				case 1:
					codeReturn = "EMP0000"+increment;
					break;
				case 2:
					codeReturn = "EMP000"+increment;
					break;
				case 3:
					codeReturn = "EMP00"+increment;
					break;
				case 4:
					codeReturn = "EMP0"+increment;
					break;
				case 5:
					codeReturn = "EMP"+increment;
					break;
				default:
					break;
				}
				
				return codeReturn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String lastPositionCode(){
		try {
			String code = positionDAO.getLastCode();
			if(code==null){
				return "POS00001";
			}else{
				String codeNumber = code.substring(3, code.length());
				int increment = Integer.valueOf(codeNumber)+1;
				String codeReturn="";
				switch (String.valueOf(increment).length()) {
				case 1:
					codeReturn = "POS0000"+increment;
					break;
				case 2:
					codeReturn = "POS000"+increment;
					break;
				case 3:
					codeReturn = "POS00"+increment;
					break;
				case 4:
					codeReturn = "POS0"+increment;
					break;
				case 5:
					codeReturn = "POS"+increment;
					break;
				default:
					break;
				}
				
				return codeReturn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String lastPayrollComponentCode(){
		try {
			String code = payrollComponentDAO.getLastCode();
			if(code==null){
				return "PRL00001";
			}else{
				String codeNumber = code.substring(3, code.length());
				int increment = Integer.valueOf(codeNumber)+1;
				String codeReturn="";
				switch (String.valueOf(increment).length()) {
				case 1:
					codeReturn = "PRL0000"+increment;
					break;
				case 2:
					codeReturn = "PRL000"+increment;
					break;
				case 3:
					codeReturn = "PRL00"+increment;
					break;
				case 4:
					codeReturn = "PRL0"+increment;
					break;
				case 5:
					codeReturn = "PRL"+increment;
					break;
				default:
					break;
				}
				
				return codeReturn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String lastEmpStructureCode(){
		try {
			String code = employeeStructureDAO.getLastCode();
			if(code==null){
				return "EST00001";
			}else{
				String codeNumber = code.substring(3, code.length());
				int increment = Integer.valueOf(codeNumber)+1;
				String codeReturn="";
				switch (String.valueOf(increment).length()) {
				case 1:
					codeReturn = "EST0000"+increment;
					break;
				case 2:
					codeReturn = "EST000"+increment;
					break;
				case 3:
					codeReturn = "EST00"+increment;
					break;
				case 4:
					codeReturn = "EST0"+increment;
					break;
				case 5:
					codeReturn = "EST"+increment;
					break;
				default:
					break;
				}
				return codeReturn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
