package module.employeemanagement.bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.component.DialogBox;
import module.employeemanagement.dao.EmployeeDAO;
import module.employeemanagement.model.Employee;

public class EmployeeManagementBL {
	private EmployeeDAO employeeDAO;
	private DataSource dataSource;
	
	public EmployeeManagementBL(DataSource dataSource){
		Connection con = null;
		this.dataSource = dataSource;
		try {
			con = dataSource.getConnection();
			employeeDAO = new EmployeeDAO(con);
		} catch (SQLException e) {
			DialogBox.showError("Cannot Connect to Database");
			e.printStackTrace();
		}
		
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
	
	public String lastEmpCode(){
		try {
			String code = employeeDAO.getLastCode();
			if(code==null){
				return "EMP00001";
			}else{
				String codeNumber = code.substring(3, code.length()-1);
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
}
