package module.pembelian.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceivedDetail {
	boolean flag;
	int id;
	String receivedCode;
	int gradeID;
	String grade;
	double totalVolume;
	int totalLog;
	List<PalletCard> pallets;
	Map<Integer, PalletCard> deletedPallets = new HashMap<>();
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
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
	public Map<Integer, PalletCard> getDeletedPallets() {
		return deletedPallets;
	}
	public void setDeletedPallets(Map<Integer, PalletCard> deletedPallets) {
		this.deletedPallets = deletedPallets;
	}
	
}
