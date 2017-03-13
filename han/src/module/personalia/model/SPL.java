package module.personalia.model;

import java.util.Date;

public class SPL {

	Integer id;
	String splCode;
	Date incentiveDate;
	Date otDate;
	Date otTimeStart;
	Date otTimeEnd;
	String description;
	String documentReference;
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
	public String getSplCode() {
		return splCode;
	}
	public void setSplCode(String splCode) {
		this.splCode = splCode;
	}
	public Date getIncentiveDate() {
		return incentiveDate;
	}
	public void setIncentiveDate(Date incentiveDate) {
		this.incentiveDate = incentiveDate;
	}
	public Date getOtDate() {
		return otDate;
	}
	public void setOtDate(Date otDate) {
		this.otDate = otDate;
	}
	public Date getOtTimeStart() {
		return otTimeStart;
	}
	public void setOtTimeStart(Date otTimeStart) {
		this.otTimeStart = otTimeStart;
	}
	public Date getOtTimeEnd() {
		return otTimeEnd;
	}
	public void setOtTimeEnd(Date otTimeEnd) {
		this.otTimeEnd = otTimeEnd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocumentReference() {
		return documentReference;
	}
	public void setDocumentReference(String documentReference) {
		this.documentReference = documentReference;
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