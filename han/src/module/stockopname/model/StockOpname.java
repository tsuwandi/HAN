package module.stockopname.model;

import java.util.Date;
import java.util.List;

public class StockOpname {
	int id;
	String soName;
	Date soDate;
	String soType;
	String status;
	String confirmCode;
	Date confirmDate;
	List<StockOpnameProduct> stockOpnameProduct;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSoName() {
		return soName;
	}
	public void setSoName(String soName) {
		this.soName = soName;
	}
	public Date getSoDate() {
		return soDate;
	}
	public void setSoDate(Date soDate) {
		this.soDate = soDate;
	}
	public String getSoType() {
		return soType;
	}
	public void setSoType(String soType) {
		this.soType = soType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public List<StockOpnameProduct> getStockOpnameProduct() {
		return stockOpnameProduct;
	}
	public void setStockOpnameProduct(List<StockOpnameProduct> stockOpnameProduct) {
		this.stockOpnameProduct = stockOpnameProduct;
	}
	
}
