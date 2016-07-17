package module.production.model;

import module.util.ComboBoxProperties;

public class Shift implements ComboBoxProperties{
	int id;
	String shiftCode;
	String shiftName;
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
	@Override
	public Object getField() {
		return shiftName;
	}
	
}
