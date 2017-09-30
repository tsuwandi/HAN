package module.sn.productcategory.model;

import module.util.ComboBoxProperties;

public class ProductCategory implements ComboBoxProperties {
	int id;
	String productCategory;
	int productCategoryTypeId;

	public int getProductCategoryTypeId() {
		return productCategoryTypeId;
	}

	public void setProductCategoryTypeId(int productCategoryTypeId) {
		this.productCategoryTypeId = productCategoryTypeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public Object getField() {
		return productCategory;
	}

	public ProductCategory() {

	}

	public ProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
}
