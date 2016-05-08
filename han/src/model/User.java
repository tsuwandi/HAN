package model;

/**
 * 
 * <p>Description : Class User as a model from Users entity</p>
 * 
 * @author EOP
 * @version 1.0
 * 
 * <table>
 * 	<tr><td colspan='4'>History of modification : </td></tr>
 * 	<tr><td>Version</td><td>Updated Date</td><td>Updated By</td><td>Description</td></tr>
 * 	<tr><td>1.0</td><td>24-Jan-2014</td><td>EOP</td><td>Draft coding</td></tr>
 * </table>
 *
 */
public class User {
	private String employeeId;
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	private String password;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
