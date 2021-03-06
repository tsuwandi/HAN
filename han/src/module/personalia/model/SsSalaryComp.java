package module.personalia.model;

import java.math.BigDecimal;
import java.util.Date;

public class SsSalaryComp {
	Integer id;
	Integer salarySettingId;
	String payrollComponentCode;
	PayrollComponent payrollComponent;
	BigDecimal nominal;
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
	public Integer getSalarySettingId() {
		return salarySettingId;
	}
	public void setSalarySettingId(Integer salarySettingId) {
		this.salarySettingId = salarySettingId;
	}
	public String getPayrollComponentCode() {
		return payrollComponentCode;
	}
	public void setPayrollComponentCode(String payrollComponentCode) {
		this.payrollComponentCode = payrollComponentCode;
	}
	public PayrollComponent getPayrollComponent() {
		return payrollComponent;
	}
	public void setPayrollComponent(PayrollComponent payrollComponent) {
		this.payrollComponent = payrollComponent;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
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
}