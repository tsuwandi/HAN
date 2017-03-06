package module.production.model;

import module.util.ComboBoxProperties;

public class GroupShift implements ComboBoxProperties{
	int id;
	String groupShiftCode;
	String description;
	String employeeLeaderCode;
	String lineCode;
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public GroupShift(){
	}
	public GroupShift(String description){
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGroupShiftCode() {
		return groupShiftCode;
	}
	public void setGroupShiftCode(String groupShiftCode) {
		this.groupShiftCode = groupShiftCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmployeeLeaderCode() {
		return employeeLeaderCode;
	}
	public void setEmployeeLeaderCode(String employeeLeaderCode) {
		this.employeeLeaderCode = employeeLeaderCode;
	}
	@Override
	public Object getField() {
		return description;
	}
	
}
