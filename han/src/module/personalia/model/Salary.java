package module.personalia.model;

import java.math.BigDecimal;

public class Salary {
	String employeeCode;
	String employeeName;
	String employeeType;
	String position;
	String department;
	String division;
	BigDecimal grossSalary;
	BigDecimal reductionSalary;
	BigDecimal nettSalary;
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
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public BigDecimal getGrossSalary() {
		return grossSalary;
	}
	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}
	public BigDecimal getReductionSalary() {
		return reductionSalary;
	}
	public void setReductionSalary(BigDecimal reductionSalary) {
		this.reductionSalary = reductionSalary;
	}
	public BigDecimal getNettSalary() {
		return nettSalary;
	}
	public void setNettSalary(BigDecimal nettSalary) {
		this.nettSalary = nettSalary;
	}
	
}
