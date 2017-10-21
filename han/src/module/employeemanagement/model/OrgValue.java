package module.employeemanagement.model;

import module.util.ComboBoxProperties;

public class OrgValue implements ComboBoxProperties {
	int id;
	String parentValue;
	String value;
	int parentId;
	int strucureId;
	
	public OrgValue(){}
	public OrgValue(String value, String parentValue){
		this.value=value;
		this.parentValue =parentValue;
	}
	
	public String getParentValue() {
		return parentValue;
	}
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getStrucureId() {
		return strucureId;
	}
	public void setStrucureId(int strucureId) {
		this.strucureId = strucureId;
	}
	@Override
	public Object getField() {
		return parentValue+"-"+value;
	}
	
	
}
