package module.personalia.model;

import java.math.BigDecimal;
import java.util.Date;

public class NonRoutineAllowanceTransaction {

	Integer id;
	Date transactionInputDate;
	String employeeCode;
	String employeeName;
	Integer tnrTypeId;
	NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType;
	BigDecimal nominal;
	String description;
	String referenceNumber;
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
	public Date getTransactionInputDate() {
		return transactionInputDate;
	}
	public void setTransactionInputDate(Date transactionInputDate) {
		this.transactionInputDate = transactionInputDate;
	}
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
	public Integer getTnrTypeId() {
		return tnrTypeId;
	}
	public void setTnrTypeId(Integer tnrTypeId) {
		this.tnrTypeId = tnrTypeId;
	}
	public NonRoutineAllowanceMasterType getNonRoutineAllowanceMasterType() {
		return nonRoutineAllowanceMasterType;
	}
	public void setNonRoutineAllowanceMasterType(NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType) {
		this.nonRoutineAllowanceMasterType = nonRoutineAllowanceMasterType;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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