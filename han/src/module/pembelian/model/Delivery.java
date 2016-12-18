package module.pembelian.model;

import java.util.Date;

import module.util.ComboBoxProperties;

public class Delivery implements ComboBoxProperties {
	int id;
	String deliveryNote;
	String woodDomicile;
	String woodResource;
	int woodResourceId;
	int woodTypeID;
	String documentType;
	String woodType;
	int totalLog;
	double totalVolume;
	Date docIssuedDate;
	String receivedCode;
	
	
	
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	public String getWoodType() {
		return woodType;
	}
	public void setWoodType(String woodType) {
		this.woodType = woodType;
	}
	
	public int getWoodTypeID() {
		return woodTypeID;
	}
	public void setWoodTypeID(int woodTypeID) {
		this.woodTypeID = woodTypeID;
	}
	public int getTotalLog() {
		return totalLog;
	}
	public void setTotalLog(int totalLog) {
		this.totalLog = totalLog;
	}
	public double getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
	}
	public Delivery(){	
	}
	public Delivery(String deliveryNote){
		this.deliveryNote= deliveryNote;
	}
	
	public Date getDocIssuedDate() {
		return docIssuedDate;
	}
	public void setDocIssuedDate(Date docIssuedDate) {
		this.docIssuedDate = docIssuedDate;
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
	public String getReceivedCode() {
		return receivedCode;
	}
	public void setReceivedCode(String receivedCode) {
		this.receivedCode = receivedCode;
	}
	
	
}