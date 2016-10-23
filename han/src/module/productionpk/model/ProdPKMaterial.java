package module.productionpk.model;

public class ProdPKMaterial {
	int id;
	String prodPKCode;
	String productCode;
	double qty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdPKCode() {
		return prodPKCode;
	}
	public void setProdPKCode(String prodPKCode) {
		this.prodPKCode = prodPKCode;
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
