package module.pembelian.model;

import module.util.ComboBoxProperties;

public class Grade implements ComboBoxProperties{
	int id;
	String grade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public Object getField() {
		return grade;
	}
	
}
