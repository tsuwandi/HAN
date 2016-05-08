package module.pembelian.model;

import module.util.ComboBoxProperties;

public class Delivery implements ComboBoxProperties {
	int id;
	String deliveryNote;
	String woodDomicile;
	String woodResource;
	int woodResourceId;
	public Delivery(){	
	}
	public Delivery(String deliveryNote){
		this.deliveryNote= deliveryNote;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDeliveryNote() {
		return deliveryNote;
	}
	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
	public String getWoodDomicile() {
		return woodDomicile;
	}
	public void setWoodDomicile(String woodDomicile) {
		this.woodDomicile = woodDomicile;
	}
	public String getWoodResource() {
		return woodResource;
	}
	public void setWoodResource(String woodResource) {
		this.woodResource = woodResource;
	}
	public int getWoodResourceId() {
		return woodResourceId;
	}
	public void setWoodResourceId(int woodResourceId) {
		this.woodResourceId = woodResourceId;
	}
	@Override
	public Object getField() {
		return deliveryNote;
	}
	
	
}