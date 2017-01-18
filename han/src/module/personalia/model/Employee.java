package module.personalia.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {

	Integer id;
	String empCode;
	String name;
	String currentAddress;
	String currentCity;
	String npwp;
	String ktp;
	String ktpAddress;
	String ktpCity;
	String hometown;
	Integer totalChild;
	String bankCode;
	String bankAccountNumber;
	String groupShiftCode;
	Date birthDate;
	String email;
	String phone;
	BigDecimal salary;
	Integer genderId;
	Integer maritalId;
	String emplStatusId;
	Date inputDate;
	String inputBy;
	Date editDate;
	String editBy;
	Date deleteDate;
	String deleteBy;
	EmployeeType employeeType;
	MSPosition msPosition;
	Department department;
	Division division;
	List<EmpPosition> empPositions = new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getKtp() {
		return ktp;
	}
	public void setKtp(String ktp) {
		this.ktp = ktp;
	}
	public String getKtpAddress() {
		return ktpAddress;
	}
	public void setKtpAddress(String ktpAddress) {
		this.ktpAddress = ktpAddress;
	}
	public String getKtpCity() {
		return ktpCity;
	}
	public void setKtpCity(String ktpCity) {
		this.ktpCity = ktpCity;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public Integer getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getGroupShiftCode() {
		return groupShiftCode;
	}
	public void setGroupShiftCode(String groupShiftCode) {
		this.groupShiftCode = groupShiftCode;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public Integer getMaritalId() {
		return maritalId;
	}
	public void setMaritalId(Integer maritalId) {
		this.maritalId = maritalId;
	}
	public String getEmplStatusId() {
		return emplStatusId;
	}
	public void setEmplStatusId(String emplStatusId) {
		this.emplStatusId = emplStatusId;
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
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
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
	public List<EmpPosition> getEmpPositions() {
		return empPositions;
	}
	public void setEmpPositions(List<EmpPosition> empPositions) {
		this.empPositions = empPositions;
	}
}