package module.production.model;

import module.util.ComboBoxProperties;

public class Line implements ComboBoxProperties{
	int id;
	String lineCode;
	String description;
	
	public Line(){
		
	}
	public Line(String description){
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
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
