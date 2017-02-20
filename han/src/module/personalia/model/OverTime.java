package module.personalia.model;

import java.util.Date;

public class OverTime {

	String employeeCode;
	String employeeName;
	MSPosition msPosition;
	Department department;
	Division division;
	Date overTimeDate;
	Date startTime;
	Date endTime;
	String documentRef;
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public MSPosition getMsPosition() {
		return msPosition;
	}
	public void setMsPosition(MSPosition msPosition) {
		this.msPosition = msPosition;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public Date getOverTimeDate() {
		return overTimeDate;
	}
	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDocumentRef() {
		return documentRef;
	}
	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}	
}