package module.product.model;

import module.util.ComboBoxProperties;

public class ProductCategory implements ComboBoxProperties {
	int id;
	String productCategory;

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
