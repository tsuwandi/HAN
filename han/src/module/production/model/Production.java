package module.production.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import module.pembelian.model.PalletCard;

public class Production {
	int id;
	String productionCode;
	String groupShiftCode;
	String lineCode;
	String shiftCode;
	String productionTypeCode;
	Date productionDate;
	String information;
	int totalPalletCard;
	int totalLog;
	double totalVolume;
	String groupShiftDescription;
	String lineDescription;
	String shiftName;
	String productionTypeDescription;
	String status;
	String type;
	List<ProdRM> listOfProdRM;
	Map<String, ProdRM> deletedProdRMs = new HashMap<>();
	List<ProductionResult> productionResults;
	Map<Integer, ProductionResult> deletedProductionResult= new HashMap<>();
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
	
	public List<ProductionResult> getProductionResults() {
		return productionResults;
	}
	public void setProductionResults(List<ProductionResult> productionResults) {
		this.productionResults = productionResults;
	}
	public String getProductionTypeCode() {
		return productionTypeCode;
	}
	public void setProductionTypeCode(String productionTypeCode) {
		this.productionTypeCode = productionTypeCode;
	}
	public String getProductionTypeDescription() {
		return productionTypeDescription;
	}
	public void setProductionTypeDescription(String productionTypeDescription) {
		this.productionTypeDescription = productionTypeDescription;
	}
	public Map<String, ProdRM> getDeletedProdRMs() {
		return deletedProdRMs;
	}
	public void setDeletedProdRMs(Map<String, ProdRM> deletedProdRMs) {
		this.deletedProdRMs = deletedProdRMs;
	}
	public Map<Integer, ProductionResult> getDeletedProductionResult() {
		return deletedProductionResult;
	}
	public void setDeletedProductionResult(Map<Integer, ProductionResult> deletedProductionResult) {
		this.deletedProductionResult = deletedProductionResult;
	}
	
	//timotius@20170107_start_tutup_harian_produksi
	private Date confirmDate;
	private String confirmCode;
	private PalletCard palletCard;
	private ProductionResultProduct productionResultProduct;
	
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
	public PalletCard getPalletCard() {
		if (palletCard == null)
			palletCard = new PalletCard();
		return palletCard;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPalletCard(PalletCard palletCard) {
		if (palletCard == null)
			palletCard = new PalletCard();
		this.palletCard = palletCard;
	}
	
	public ProductionResultProduct getProductionResultProduct() {
		if (productionResultProduct == null)
			productionResultProduct = new ProductionResultProduct();
		return productionResultProduct;
	}

	public void setProductionResultProduct(ProductionResultProduct productionResultProduct) {
		if (productionResultProduct == null)
			productionResultProduct = new ProductionResultProduct();
		this.productionResultProduct = productionResultProduct;
	}
	//timotius@20170107_end_tutup_harian_produksi

	
	
}
