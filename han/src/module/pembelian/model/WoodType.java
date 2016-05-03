package module.pembelian.model;

import module.util.ComboBoxProperties;

public class WoodType implements ComboBoxProperties{
	int id;
	String woodType;
	public WoodType(){
	}
	
	public WoodType(String woodType){
		this.woodType = woodType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWoodType() {
		return woodType;
	}
	public void setWoodType(String woodType) {
		this.woodType = woodType;
	}
	@Override
	public Object getField() {
		return woodType;
	}
	
}
