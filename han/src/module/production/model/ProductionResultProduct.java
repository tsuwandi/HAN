package module.production.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductionResultProduct{
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

	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}

	private String productionCode;
	
	
}


