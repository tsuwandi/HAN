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

	public static final String BALKEN_BASAH_CD = "B";
	public static final String BALKEN_KERING_CD = "K";
	public static final String HASIL_PRODUKSI_CD = "PDC009";
	public static final String BARANG_PENDUKUNG_CD = "P";
	
	public static final String BALKEN_BASAH = "Balken Basah";
	public static final String BALKEN_KERING = "Balken Kering";
	public static final String HASIL_PRODUKSI = "Hasil Produksi";
	public static final String BARANG_PENDUKUNG = "Barang Pendukung";
}
