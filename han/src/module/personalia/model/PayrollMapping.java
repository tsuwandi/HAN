package module.personalia.model;

import java.util.Date;

public class PayrollMapping {

	Integer id;
	String code;
	String positionId;
	MSPosition msPosition;
	String payrollComponentCode;
	PayrollComponent payrollComponent;
	Integer isAbsent;
	Integer isLeave;
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
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public MSPosition getMsPosition() {
		return msPosition;
	}
	public void setMsPosition(MSPosition msPosition) {
		this.msPosition = msPosition;
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
	public Integer getIsAbsent() {
		return isAbsent;
	}
	public void setIsAbsent(Integer isAbsent) {
		this.isAbsent = isAbsent;
	}
	public Integer getIsLeave() {
		return isLeave;
	}
	public void setIsLeave(Integer isLeave) {
		this.isLeave = isLeave;
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