package module.production.model;

import module.util.ComboBoxProperties;

public class Machine implements ComboBoxProperties{
	int id;
	String machineCode;
	String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public Object getField() {
		return description;
	}
	
	
}
