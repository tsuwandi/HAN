package module.productionwaste.model;

public class ProductionResultProductWaste{
	int id;
	int prodResultID;
	String productCode;
	double qty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProdResultID() {
		return prodResultID;
	}
	public void setProdResultID(int prodResultID) {
		this.prodResultID = prodResultID;
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

	public int getProdWasteResultId() {
		return prodWasteResultId;
	}
	public void setProdWasteResultId(int prodWasteResultId) {
		this.prodWasteResultId = prodWasteResultId;
	}

	public String getPwCode() {
		return pwCode;
	}
	public void setPwCode(String pwCode) {
		this.pwCode = pwCode;
	}

	private int prodWasteResultId;
	private String pwCode;
}

