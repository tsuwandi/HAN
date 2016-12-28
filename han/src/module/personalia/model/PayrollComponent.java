package module.personalia.model;

import java.util.Date;

public class PayrollComponent {

	String code;
	String description;
	Boolean payrollStatus;
	Boolean thrStatus;
	Boolean bonusStatus;
	String referenceDocument;
	Date inputDate;
	String inputBy;
	Date editDate;
	String editBy;
	Date deleteDate;
	String deleteBy;
	
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
	public Boolean getPayrollStatus() {
		return payrollStatus;
	}
	public void setPayrollStatus(Boolean payrollStatus) {
		this.payrollStatus = payrollStatus;
	}
	public Boolean getThrStatus() {
		return thrStatus;
	}
	public void setThrStatus(Boolean thrStatus) {
		this.thrStatus = thrStatus;
	}
	public Boolean getBonusStatus() {
		return bonusStatus;
	}
	public void setBonusStatus(Boolean bonusStatus) {
		this.bonusStatus = bonusStatus;
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
}