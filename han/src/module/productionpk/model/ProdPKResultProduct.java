package module.productionpk.model;

public class ProdPKResultProduct{
	int id;
	int prodPKResultID;
	String productCode;
	double qty;
	String prodPKCode;
	
	public String getProdPKCode() {
		return prodPKCode;
	}
	public void setProdPKCode(String prodPKCode) {
		this.prodPKCode = prodPKCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProdPKResultID() {
		return prodPKResultID;
	}
	public void setProdPKResultID(int prodPKResultID) {
		this.prodPKResultID = prodPKResultID;
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

