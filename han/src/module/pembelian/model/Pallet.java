package module.pembelian.model;

public class Pallet {
	int id;
	String palletCardCode;
	String receivedCode;
	String empCode;
	String empName;
	String grade;
	int gradeID;
	int totalLog;
	int totalVolume;
	
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getTotalLog() {
		return totalLog;
	}
	public void setTotalLog(int totalLog) {
		this.totalLog = totalLog;
	}
	public int getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(int totalVolume) {
		this.totalVolume = totalVolume;
	}
	public int getId() {
		return id;
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
	public String getReceivedCode() {
		return receivedCode;
	}
	public void setReceivedCode(String receivedCode) {
		this.receivedCode = receivedCode;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public int getGradeID() {
		return gradeID;
	}
	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}
	
}
