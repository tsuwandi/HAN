package module.personalia.model;

import java.math.BigDecimal;
import java.util.Date;

public class SsTax {
	Integer id;
	Integer salarySettingId;
	Integer taxId;
	Tax tax;
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
	public Integer getTaxId() {
		return taxId;
	}
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
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