package module.pembelian.model;

import module.util.ComboBoxProperties;

public class WoodResource implements ComboBoxProperties{
	int id;
	String woodResource;
	
	public WoodResource(){
		
	}
	
	public WoodResource(String woodResource){
		this.woodResource = woodResource;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWoodResource() {
		return woodResource;
	}
	public void setWoodResource(String woodResource) {
		this.woodResource = woodResource;
	}
	@Override
	public Object getField() {
		return woodResource;
	}
	
}
