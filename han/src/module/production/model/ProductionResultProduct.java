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

	//timotius@20170107_start_tutup_harian_produksi
	private Date confirmDate;
	private String confirmCode;
	private String status;
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//timotius@20170107_end_tutup_harian_produksi
}

