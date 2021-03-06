package module.production.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductionResult {
	int id;
	String prodCode;
	int pressedNo;
	String startTime;
	double totalProtol;
	double totalKlem;
	double totalFineA;
	double totalFineB;
	
	
	List<ProductionResultProduct> listProductionResultProduct;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPressedNo() {
		return pressedNo;
	}
	public void setPressedNo(int pressedNo) {
		this.pressedNo = pressedNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public double getTotalProtol() {
		return totalProtol;
	}
	public void setTotalProtol(double totalProtol) {
		this.totalProtol = totalProtol;
	}
	public double getTotalKlem() {
		return totalKlem;
	}
	public void setTotalKlem(double totalKlem) {
		this.totalKlem = totalKlem;
	}
	
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public List<ProductionResultProduct> getListProductionResultProduct() {
		return listProductionResultProduct;
	}
	public void setListProductionResultProduct(List<ProductionResultProduct> listProductionResultProduct) {
		this.listProductionResultProduct = listProductionResultProduct;
	}
	public double getTotalFineA() {
		return totalFineA;
	}
	public void setTotalFineA(double totalFineA) {
		this.totalFineA = totalFineA;
	}
	public double getTotalFineB() {
		return totalFineB;
	}
	public void setTotalFineB(double totalFineB) {
		this.totalFineB = totalFineB;
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
