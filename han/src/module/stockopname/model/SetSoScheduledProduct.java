package module.stockopname.model;

public class SetSoScheduledProduct {
	int id;
	int setSOScheduledID;
	String productCode;
	String productName;
	String productCategory;
	int productCategoryID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSetSOScheduledID() {
		return setSOScheduledID;
	}
	public void setSetSOScheduledID(int setSOScheduledID) {
		this.setSOScheduledID = setSOScheduledID;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
