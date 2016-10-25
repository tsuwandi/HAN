package module.productionpk.model;

import java.util.List;

public class ProdPKResult {
	int id;
	String prodPKCode;
	int pressedNo;
	String startTime;
	double totalProtol;
	double totalKlem;
	double totalFineA;
	double totalFineB;
	
	List<ProdPKResultProduct> listProductPKResultProduct;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdPKCode() {
		return prodPKCode;
	}
	public void setProdPKCode(String prodPKCode) {
		this.prodPKCode = prodPKCode;
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
	public List<ProdPKResultProduct> getListProductPKResultProduct() {
		return listProductPKResultProduct;
	}
	public void setListProductPKResultProduct(List<ProdPKResultProduct> listProductPKResultProduct) {
		this.listProductPKResultProduct = listProductPKResultProduct;
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
	
	
	
}
