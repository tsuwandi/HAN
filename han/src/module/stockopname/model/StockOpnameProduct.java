package module.stockopname.model;

public class StockOpnameProduct {
	int id;
	int stockOpnameID;
	String productCode;
	String location;
	double qtySystem;
	double qtyActual;
	double selisihQty;
	double valueSystem;
	double valueActual;
	double selisihValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStockOpnameID() {
		return stockOpnameID;
	}
	public void setStockOpnameID(int stockOpnameID) {
		this.stockOpnameID = stockOpnameID;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getQtySystem() {
		return qtySystem;
	}
	public void setQtySystem(double qtySystem) {
		this.qtySystem = qtySystem;
	}
	public double getQtyActual() {
		return qtyActual;
	}
	public void setQtyActual(double qtyActual) {
		this.qtyActual = qtyActual;
	}
	public double getSelisihQty() {
		return selisihQty;
	}
	public void setSelisihQty(double selisihQty) {
		this.selisihQty = selisihQty;
	}
	public double getValueSystem() {
		return valueSystem;
	}
	public void setValueSystem(double valueSystem) {
		this.valueSystem = valueSystem;
	}
	public double getValueActual() {
		return valueActual;
	}
	public void setValueActual(double valueActual) {
		this.valueActual = valueActual;
	}
	public double getSelisihValue() {
		return selisihValue;
	}
	public void setSelisihValue(double selisihValue) {
		this.selisihValue = selisihValue;
	}
	
}
