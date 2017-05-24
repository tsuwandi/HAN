package module.mastershift.model;

import java.util.Date;

public class MasterShift {
	int id;
	String shiftCode;
	String shiftName;
	String type;
	Date inputDate;
	Date editedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public Date getEditedDate() {
		return editedDate;
	}
	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}
	@Override
	public String toString() {
		return "MasterShift [id=" + id + ", shiftCode=" + shiftCode + ", shiftName=" + shiftName + ", type=" + type
				+ ", inputDate=" + inputDate + ", editedDate=" + editedDate + "]";
	}
	
	
}
