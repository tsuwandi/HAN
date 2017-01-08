package module.personalia.model;

import java.util.Date;

public class EmployeePosition {

	String id;
	String employeeId;
	Date startDate;
	Date endDate;
	Integer probation;
	String positionId;
	MSPosition msPosition;
	String employeeTypeId;
	EmployeeType employeeType;
	String referenceDoc;
	String notes;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getProbation() {
		return probation;
	}
	public void setProbation(Integer probation) {
		this.probation = probation;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getReferenceDoc() {
		return referenceDoc;
	}
	public void setReferenceDoc(String referenceDoc) {
		this.referenceDoc = referenceDoc;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}	
}