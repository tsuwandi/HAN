package module.sn.grade.model;

import module.util.ComboBoxProperties;

public class Grade implements ComboBoxProperties{
	int id;
	String grade;
	private int productCategoryId;
	
	public Grade(String grade) {
		this.grade = grade;
	}
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
	
	public Grade(){
	}
	
	public int getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
}
