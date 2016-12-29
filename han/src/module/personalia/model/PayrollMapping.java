package module.personalia.model;

import java.util.Date;

public class PayrollMapping {

	String code;
	MSPosition msPosition;
	PayrollComponent payrollComponent;
	Boolean skipStatus;
	Boolean permitStatus;
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
	public MSPosition getMsPosition() {
		return msPosition;
	}
	public void setMsPosition(MSPosition msPosition) {
		this.msPosition = msPosition;
	}
	public PayrollComponent getPayrollComponent() {
		return payrollComponent;
	}
	public void setPayrollComponent(PayrollComponent payrollComponent) {
		this.payrollComponent = payrollComponent;
	}
	public Boolean getSkipStatus() {
		return skipStatus;
	}
	public void setSkipStatus(Boolean skipStatus) {
		this.skipStatus = skipStatus;
	}
	public Boolean getPermitStatus() {
		return permitStatus;
	}
	public void setPermitStatus(Boolean permitStatus) {
		this.permitStatus = permitStatus;
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