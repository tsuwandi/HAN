package module.pembelian.model;

import java.util.List;

public class ReceivedDetail {
	int id;
	String receivedCode;
	int gradeID;
	String grade;
	double totalVolume;
	int totalLog;
	List<PalletCard> pallets;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceivedCode() {
		return receivedCode;
	}
	public void setReceivedCode(String receivedCode) {
		this.receivedCode = receivedCode;
	}
	public int getGradeID() {
		return gradeID;
	}
	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public double getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
	}
	public int getTotalLog() {
		return totalLog;
	}
	public void setTotalLog(int totalLog) {
		this.totalLog = totalLog;
	}
	public List<PalletCard> getPallets() {
		return pallets;
	}
	public void setPallets(List<PalletCard> pallets) {
		this.pallets = pallets;
	}
}
