package module.personalia.model;

import java.math.BigDecimal;
import java.util.Date;

public class NonRoutineAllowanceTransaction {

	Integer id;
	String emplyeeId;
	String employeeCode;
	String employeeName;
	Employee employee;
	Integer effectiveStartMonth;
	Integer effectiveStartYear;
	Integer effectiveEndMonth;
	Integer effectiveEndYear;
	Integer tnrTypeId;
	NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType;
	Integer tnrId;
	NonRoutineAllowanceMaster nonRoutineAllowanceMaster;
	BigDecimal nominal;
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
	public String getEmplyeeId() {
		return emplyeeId;
	}
	public void setEmplyeeId(String emplyeeId) {
		this.emplyeeId = emplyeeId;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getEffectiveStartMonth() {
		return effectiveStartMonth;
	}
	public void setEffectiveStartMonth(Integer effectiveStartMonth) {
		this.effectiveStartMonth = effectiveStartMonth;
	}
	public Integer getEffectiveStartYear() {
		return effectiveStartYear;
	}
	public void setEffectiveStartYear(Integer effectiveStartYear) {
		this.effectiveStartYear = effectiveStartYear;
	}
	public Integer getEffectiveEndMonth() {
		return effectiveEndMonth;
	}
	public void setEffectiveEndMonth(Integer effectiveEndMonth) {
		this.effectiveEndMonth = effectiveEndMonth;
	}
	public Integer getEffectiveEndYear() {
		return effectiveEndYear;
	}
	public void setEffectiveEndYear(Integer effectiveEndYear) {
		this.effectiveEndYear = effectiveEndYear;
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
	public Integer getTnrId() {
		return tnrId;
	}
	public void setTnrId(Integer tnrId) {
		this.tnrId = tnrId;
	}
	public NonRoutineAllowanceMaster getNonRoutineAllowanceMaster() {
		return nonRoutineAllowanceMaster;
	}
	public void setNonRoutineAllowanceMaster(NonRoutineAllowanceMaster nonRoutineAllowanceMaster) {
		this.nonRoutineAllowanceMaster = nonRoutineAllowanceMaster;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
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