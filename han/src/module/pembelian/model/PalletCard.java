package module.pembelian.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PalletCard {
	int id;
	int receivedDetailID;
	String palletCardCode;
	double length;
	double width;
	double thickness;
	double total;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
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

	@Override
	public String toString() {
		return "PalletCard [id=" + id + ", receivedDetailID=" + receivedDetailID + ", palletCardCode=" + palletCardCode
				+ ", length=" + length + ", width=" + width + ", thickness=" + thickness + ", total=" + total
				+ ", volume=" + volume + ", productCode=" + productCode + ", productName=" + productName
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((palletCardCode == null) ? 0 : palletCardCode.hashCode());
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + receivedDetailID;
		temp = Double.doubleToLongBits(thickness);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PalletCard other = (PalletCard) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
			return false;
		if (palletCardCode == null) {
			if (other.palletCardCode != null)
				return false;
		} else if (!palletCardCode.equals(other.palletCardCode))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (receivedDetailID != other.receivedDetailID)
			return false;
		if (Double.doubleToLongBits(thickness) != Double.doubleToLongBits(other.thickness))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}

	// skip receive detail
	private Received received;
	private boolean isFlag;
	private int rowNum;
	private Timestamp dateIn;

	public Received getReceived() {
		if (received == null)
			received = new Received();
		return received;
	}

	public void setReceived(Received received) {
		if (received == null)
			received = new Received();
		this.received = received;
	}

	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public Timestamp getDateIn() {
		return dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

}
