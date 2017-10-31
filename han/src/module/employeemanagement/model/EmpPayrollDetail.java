package module.employeemanagement.model;

import java.math.BigDecimal;

public class EmpPayrollDetail {
	int id;
	int empPositionId;
	int payrollComponentId;
	String payrollComponentName;
	BigDecimal nominal;
	
	
	public int getEmpPositionId() {
		return empPositionId;
	}
	public void setEmpPositionId(int empPositionId) {
		this.empPositionId = empPositionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPayrollComponentId() {
		return payrollComponentId;
	}
	public void setPayrollComponentId(int payrollComponentId) {
		this.payrollComponentId = payrollComponentId;
	}
	public String getPayrollComponentName() {
		return payrollComponentName;
	}
	public void setPayrollComponentName(String payrollComponentName) {
		this.payrollComponentName = payrollComponentName;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
	
}
