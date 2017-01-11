package module.packingresult.model;

public class PackingRM {
	private int id;
	private int packingID;
	private String productCode;
	private double qty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPackingID() {
		return packingID;
	}
	public void setPackingID(int packingID) {
		this.packingID = packingID;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
}
