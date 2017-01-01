package module.packingresult.model;

import java.sql.Timestamp;

public class PackingConversion {
	private int id;
	private String productCodeFrom;
	private int qtyFrom;
	private String productCodeTo;
	private int qtyTo;
	private Timestamp effectiveStartDate;
	private Timestamp effectiveEndDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductCodeFrom() {
		return productCodeFrom;
	}
	public void setProductCodeFrom(String productCodeFrom) {
		this.productCodeFrom = productCodeFrom;
	}
	public int getQtyFrom() {
		return qtyFrom;
	}
	public void setQtyFrom(int qtyFrom) {
		this.qtyFrom = qtyFrom;
	}
	public String getProductCodeTo() {
		return productCodeTo;
	}
	public void setProductCodeTo(String productCodeTo) {
		this.productCodeTo = productCodeTo;
	}
	public int getQtyTo() {
		return qtyTo;
	}
	public void setQtyTo(int qtyTo) {
		this.qtyTo = qtyTo;
	}
	public Timestamp getEffectiveStartDate() {
		return effectiveStartDate;
	}
	public void setEffectiveStartDate(Timestamp effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
	public Timestamp getEffectiveEndDate() {
		return effectiveEndDate;
	}
	public void setEffectiveEndDate(Timestamp effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
	
	
	
}
