package module.productionwaste.model;

import java.util.List;
import java.util.Map;

public class ProductionResultWaste {
	int id;
	String prodCode;
	int pressedNo;
	String startTime;
	double totalProtol;
	double totalKlem;
	double totalFineA;
	double totalFineB;
	
	
	List<ProductionResultProductWaste> listProductionResultProduct;
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "ProductionResultWaste [id=" + id + ", prodCode=" + prodCode + ", pressedNo=" + pressedNo
				+ ", startTime=" + startTime + ", totalProtol=" + totalProtol + ", totalKlem=" + totalKlem
				+ ", totalFineA=" + totalFineA + ", totalFineB=" + totalFineB + ", listProductionResultProduct="
				+ listProductionResultProduct + "]";
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
	public List<ProductionResultProductWaste> getListProductionResultProduct() {
		return listProductionResultProduct;
	}
	public void setListProductionResultProduct(List<ProductionResultProductWaste> listProductionResultProduct) {
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
}
