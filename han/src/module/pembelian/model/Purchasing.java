package module.pembelian.model;

import java.util.Date;

public class Purchasing {
	boolean flag;
	String purchasingCode;
	Date purchasingDate;
	int ritNumber;
	String supplier;
	String itemOrigin;
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getPurchasingCode() {
		return purchasingCode;
	}
	public void setPurchasingCode(String purchasingCode) {
		this.purchasingCode = purchasingCode;
	}
	public Date getPurchasingDate() {
		return purchasingDate;
	}
	public void setPurchasingDate(Date purchasingDate) {
		this.purchasingDate = purchasingDate;
	}
	public int getRitNumber() {
		return ritNumber;
	}
	public void setRitNumber(int ritNumber) {
		this.ritNumber = ritNumber;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getItemOrigin() {
		return itemOrigin;
	}
	public void setItemOrigin(String itemOrigin) {
		this.itemOrigin = itemOrigin;
	}
	
	
}
