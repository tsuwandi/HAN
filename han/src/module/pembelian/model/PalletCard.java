package module.pembelian.model;

public class PalletCard {
	int id;
	int receivedDetailID;
	String palletCardCode;
	double length;
	double width;
	double thickness;
	int total;
	double volume;
	String productCode;
	String productName;
	String description;
	
	public int getId() {
		return id;
	}
	
	public int getReceivedDetailID() {
		return receivedDetailID;
	}

	public void setReceivedDetailID(int receivedDetailID) {
		this.receivedDetailID = receivedDetailID;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPalletCardCode() {
		return palletCardCode;
	}
	public void setPalletCardCode(String palletCardCode) {
		this.palletCardCode = palletCardCode;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getThickness() {
		return thickness;
	}
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
