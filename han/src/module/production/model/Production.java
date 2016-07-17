package module.production.model;

import java.util.Date;

public class Production {
	int id;
	String productionCode;
	String groupShiftCode;
	String lineCode;
	String shiftCode;
	Date productionDate;
	String information;
	int sumPalletCard;
	int sumLog;
	int sumVolume;
	String groupShiftDescription;
	String lineDescription;
	String shiftName;
	String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	public String getGroupShiftCode() {
		return groupShiftCode;
	}
	public void setGroupShiftCode(String groupShiftCode) {
		this.groupShiftCode = groupShiftCode;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public int getSumPalletCard() {
		return sumPalletCard;
	}
	public void setSumPalletCard(int sumPalletCard) {
		this.sumPalletCard = sumPalletCard;
	}
	public int getSumLog() {
		return sumLog;
	}
	public void setSumLog(int sumLog) {
		this.sumLog = sumLog;
	}
	public int getSumVolume() {
		return sumVolume;
	}
	public void setSumVolume(int sumVolume) {
		this.sumVolume = sumVolume;
	}
	public String getGroupShiftDescription() {
		return groupShiftDescription;
	}
	public void setGroupShiftDescription(String groupShiftDescription) {
		this.groupShiftDescription = groupShiftDescription;
	}
	public String getLineDescription() {
		return lineDescription;
	}
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
