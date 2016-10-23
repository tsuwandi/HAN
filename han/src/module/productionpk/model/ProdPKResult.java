package module.productionpk.model;

public class ProdPKResult {
	int id;
	String prodPKCode;
	int pressedNo;
	String startTime;
	double totalProtol;
	double totalKlem;
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
	
	
	
}
