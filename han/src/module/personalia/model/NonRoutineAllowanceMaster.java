package module.personalia.model;

import java.util.Date;

import module.util.ComboBoxProperties;

public class NonRoutineAllowanceMaster implements ComboBoxProperties{
	Integer id;
	String tnr;
	Integer tnrTypeId;
	NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType;
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
	public String getTnr() {
		return tnr;
	}
	public void setTnr(String tnr) {
		this.tnr = tnr;
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
		return tnr;
	}
}