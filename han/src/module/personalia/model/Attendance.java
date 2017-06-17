package module.personalia.model;

import java.util.Date;

public class Attendance {

	Integer id;
	Integer pin;
	Integer nik;
	String employeeName;
	Employee employee;
	Date attendanceDate;
	String attendanceTime;
	String machineSerialNumber;
	String machineName;
	String verificationType;
	String mode;
	String updateMode;
	String branchOffice;
	String department;
	String employeeRole;
	String status;
	String notes;
	Date inputDate;
	String inputBy;
	Date editDate;
	String editBy;
	Date deleteDate;
	String deleteBy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public Integer getNik() {
		return nik;
	}
	public void setNik(Integer nik) {
		this.nik = nik;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public String getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getMachineSerialNumber() {
		return machineSerialNumber;
	}
	public void setMachineSerialNumber(String machineSerialNumber) {
		this.machineSerialNumber = machineSerialNumber;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getVerificationType() {
		return verificationType;
	}
	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(String updateMode) {
		this.updateMode = updateMode;
	}
	public String getBranchOffice() {
		return branchOffice;
	}
	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getEditBy() {
		return editBy;
	}
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getDeleteBy() {
		return deleteBy;
	}
	public void setDeleteBy(String deleteBy) {
		this.deleteBy = deleteBy;
	}
	@Override
	public String toString() {
		return "Attendance [id=" + id + ", pin=" + pin + ", nik=" + nik + ", employeeName=" + employeeName
				+ ", employee=" + employee + ", attendanceDate=" + attendanceDate + ", attendanceTime=" + attendanceTime
				+ ", machineSerialNumber=" + machineSerialNumber + ", machineName=" + machineName
				+ ", verificationType=" + verificationType + ", mode=" + mode + ", updateMode=" + updateMode
				+ ", branchOffice=" + branchOffice + ", department=" + department + ", employeeRole=" + employeeRole
				+ ", status=" + status + ", notes=" + notes + ", inputDate=" + inputDate + ", inputBy=" + inputBy
				+ ", editDate=" + editDate + ", editBy=" + editBy + ", deleteDate=" + deleteDate + ", deleteBy="
				+ deleteBy + "]";
	}
	
	
}