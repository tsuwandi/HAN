package module.employeemanagement.model;

import java.math.BigDecimal;

public class EmpTaxDetail {
	int id;
	int empPositionId;
	int taxId;
	String taxName;
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
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
	
	
}
