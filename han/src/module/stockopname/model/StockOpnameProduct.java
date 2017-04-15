package module.stockopname.model;

public class StockOpnameProduct {
	int id;
	int stockOpnameID;
	String productCode;
	String productName;
	int productID;
	String productCategory;
	int productCategoryID;
	int productUomID;
	String uom;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductCategoryID() {
		return productCategoryID;
	}
	public void setProductCategoryID(int productCategoryID) {
		this.productCategoryID = productCategoryID;
	}
	public int getProductUomID() {
		return productUomID;
	}
	public void setProductUomID(int productUomID) {
		this.productUomID = productUomID;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
}
