package module.production.model;

import java.util.Date;
import java.util.List;

public class Production {
	int id;
	String productionCode;
	String groupShiftCode;
	String lineCode;
	String shiftCode;
	Date productionDate;
	String information;
	int totalPalletCard;
	int totalLog;
	double totalVolume;
	String groupShiftDescription;
	String lineDescription;
	String shiftName;
	String status;
	List<ProdRM> listOfProdRM;
	ProductionResult productionResult;
	
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

	public int getTotalPalletCard() {
		return totalPalletCard;
	}
	public void setTotalPalletCard(int totalPalletCard) {
		this.totalPalletCard = totalPalletCard;
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
	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
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
	public List<ProdRM> getListOfProdRM() {
		return listOfProdRM;
	}
	public void setListOfProdRM(List<ProdRM> listOfProdRM) {
		this.listOfProdRM = listOfProdRM;
	}
	public ProductionResult getProductionResult() {
		return productionResult;
	}
	public void setProductionResult(ProductionResult productionResult) {
		this.productionResult = productionResult;
	}
	
	
}
