package module.pembelian.model;

import java.util.Date;

public class Received {
	int id;
	String receivedCode;
	Date receivedDate;
	String ritNo;
	String licensePlate;
	String driver;
	String deliveryNote;
	int woodTypeID;
	String supplier;
	String driverID;
	String receivedStatus;
	String woodTypeName;
	String woodDomicile;
	String woodResource;
	String supplierCode;
	int supplierCpID;
	
	
	
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
	public String getWoodTypeName() {
		return woodTypeName;
	}
	public void setWoodTypeName(String woodTypeName) {
		this.woodTypeName = woodTypeName;
	}
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getReceivedStatus() {
		return receivedStatus;
	}
	public void setReceivedStatus(String receivedStatus) {
		this.receivedStatus = receivedStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceivedCode() {
		return receivedCode;
	}
	public void setReceivedCode(String receivedCode) {
		this.receivedCode = receivedCode;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getRitNo() {
		return ritNo;
	}
	public void setRitNo(String ritNo) {
		this.ritNo = ritNo;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDeliveryNote() {
		return deliveryNote;
	}
	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
	public int getWoodTypeID() {
		return woodTypeID;
	}
	public void setWoodTypeID(int woodTypeID) {
		this.woodTypeID = woodTypeID;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public int getSupplierCpID() {
		return supplierCpID;
	}
	public void setSupplierCpID(int supplierCpID) {
		this.supplierCpID = supplierCpID;
	}
	
	
}
