package module.pembelian.model;

import java.sql.Timestamp;
import java.util.List;

public class Pallet {
	int id;
	String palletCardCode;
	String receivedCode;
	String empCode;
	String empName;
	String grade;
	int gradeID;
	int totalLog;
	double totalVolume;
	List<PalletCardDetail> palletCardDetails;

	public List<PalletCardDetail> getPalletCardDetails() {
		return palletCardDetails;
	}

	public void setPalletCardDetails(List<PalletCardDetail> palletCardDetails) {
		this.palletCardDetails = palletCardDetails;
	}

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

	public double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(double d) {
		this.totalVolume = d;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empCode == null) ? 0 : empCode.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + gradeID;
		result = prime * result + id;
		result = prime * result + ((palletCardCode == null) ? 0 : palletCardCode.hashCode());
		result = prime * result + ((receivedCode == null) ? 0 : receivedCode.hashCode());
		result = prime * result + totalLog;
		long temp;
		temp = Double.doubleToLongBits(totalVolume);
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
		Pallet other = (Pallet) obj;
		if (empCode == null) {
			if (other.empCode != null)
				return false;
		} else if (!empCode.equals(other.empCode))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (gradeID != other.gradeID)
			return false;
		if (id != other.id)
			return false;
		if (palletCardCode == null) {
			if (other.palletCardCode != null)
				return false;
		} else if (!palletCardCode.equals(other.palletCardCode))
			return false;
		if (receivedCode == null) {
			if (other.receivedCode != null)
				return false;
		} else if (!receivedCode.equals(other.receivedCode))
			return false;
		if (totalLog != other.totalLog)
			return false;
		if (Double.doubleToLongBits(totalVolume) != Double.doubleToLongBits(other.totalVolume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pallet [id=" + id + ", palletCardCode=" + palletCardCode + ", receivedCode=" + receivedCode
				+ ", empCode=" + empCode + ", empName=" + empName + ", grade=" + grade + ", gradeID=" + gradeID
				+ ", totalLog=" + totalLog + ", totalVolume=" + totalVolume + "]";
	}

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
	
	private PalletCardDetail palletCardDetail;

	public PalletCardDetail getPalletCardDetail() {
		if(palletCardDetail == null)
			palletCardDetail = new PalletCardDetail();
		return palletCardDetail;
	}

	public void setPalletCardDetail(PalletCardDetail palletCardDetail) {
		if(palletCardDetail == null)
			palletCardDetail = new PalletCardDetail();
		this.palletCardDetail = palletCardDetail;
	}
}
