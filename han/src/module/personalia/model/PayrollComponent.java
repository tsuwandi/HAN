package module.personalia.model;

import java.util.Date;

import module.util.ComboBoxProperties;

public class PayrollComponent implements ComboBoxProperties{

	Integer id;
	String code;
	String description;
	Integer isSalary;
	Integer isThr;
	Integer isBonus;
	String referenceDocument;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsSalary() {
		return isSalary;
	}
	public void setIsSalary(Integer isSalary) {
		this.isSalary = isSalary;
	}
	public Integer getIsThr() {
		return isThr;
	}
	public void setIsThr(Integer isThr) {
		this.isThr = isThr;
	}
	public Integer getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(Integer isBonus) {
		this.isBonus = isBonus;
	}
	public String getReferenceDocument() {
		return referenceDocument;
	}
	public void setReferenceDocument(String referenceDocument) {
		this.referenceDocument = referenceDocument;
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
	public Object getField() {
		return code;
	}
}