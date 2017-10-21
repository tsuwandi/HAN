package module.employeemanagement.model;

import java.math.BigDecimal;

import module.util.ComboBoxProperties;

public class Position implements ComboBoxProperties{
	  Integer id;
	  String code;
	  String name;
	  BigDecimal minSalary;
	  BigDecimal maxSalary;
	  
	public Position(){
		
	}
	public Position(String name){
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}
	public BigDecimal getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(BigDecimal maxSalary) {
		this.maxSalary = maxSalary;
	}
	@Override
	public Object getField() {

		return name;
	}
	  
	  
}
