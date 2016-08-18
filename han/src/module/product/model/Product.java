package module.product.model;

import java.util.Date;

public class Product {

	private int productId;
	private String productCode;
	private String productName;
	private int productCat;
	private String productStat;
	private int productUom;
	private int isMaintain;
	private String imagePath;
	private String brand;
	private String barcode;
	private String description;
	private int woodType;
	private int grade;
	private Double thickness;
	private Double length;
	private Double width;
	private int condition;
	private int isSerial;
	private int isAsset;
	private int warranty;
	private Double netto;
	private int nettoUom;
	private int isPurchase;
	private int minor;
	private int minorUom;
	private int leadTime;
	private int buyCost;
	private int expense;
	private String mainSuppCode;
	private String manufacturer;
	private int isSales;
	private int isService;
	private int sellCost;
	private int income;
	private Double maxDisc;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editBy;
	private Date deletedDate;
	private String deletedBy;
	private String woodTypeName;
	private String gradeName;
	private String productCatName;
	private int minQy;
	private String unitName;
	private String categoryName;
	private String conditionName;
	
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getMinQy() {
		return minQy;
	}
	public void setMinQy(int minQy) {
		this.minQy = minQy;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductCat() {
		return productCat;
	}
	public void setProductCat(int productCat) {
		this.productCat = productCat;
	}
	public String getProductStat() {
		return productStat;
	}
	public void setProductStat(String productStat) {
		this.productStat = productStat;
	}
	public int getProductUom() {
		return productUom;
	}
	public void setProductUom(int productUom) {
		this.productUom = productUom;
	}
	public int getIsMaintain() {
		return isMaintain;
	}
	public void setIsMaintain(int isMaintain) {
		this.isMaintain = isMaintain;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWoodType() {
		return woodType;
	}
	public void setWoodType(int woodType) {
		this.woodType = woodType;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Double getThickness() {
		return thickness;
	}
	public void setThickness(Double thickness) {
		this.thickness = thickness;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(int isSerial) {
		this.isSerial = isSerial;
	}
	public int getIsAsset() {
		return isAsset;
	}
	public void setIsAsset(int isAsset) {
		this.isAsset = isAsset;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public Double getNetto() {
		return netto;
	}
	public void setNetto(Double netto) {
		this.netto = netto;
	}
	public int getNettoUom() {
		return nettoUom;
	}
	public void setNettoUom(int nettoUom) {
		this.nettoUom = nettoUom;
	}
	public int getIsPurchase() {
		return isPurchase;
	}
	public void setIsPurchase(int isPurchase) {
		this.isPurchase = isPurchase;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public int getMinorUom() {
		return minorUom;
	}
	public void setMinorUom(int minorUom) {
		this.minorUom = minorUom;
	}
	public int getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}
	public int getBuyCost() {
		return buyCost;
	}
	public void setBuyCost(int buyCost) {
		this.buyCost = buyCost;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	public String getMainSuppCode() {
		return mainSuppCode;
	}
	public void setMainSuppCode(String mainSuppCode) {
		this.mainSuppCode = mainSuppCode;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getIsSales() {
		return isSales;
	}
	public void setIsSales(int isSales) {
		this.isSales = isSales;
	}
	public int getIsService() {
		return isService;
	}
	public void setIsService(int isService) {
		this.isService = isService;
	}
	public int getSellCost() {
		return sellCost;
	}
	public void setSellCost(int sellCost) {
		this.sellCost = sellCost;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public Double getMaxDisc() {
		return maxDisc;
	}
	public void setMaxDisc(Double maxDisc) {
		this.maxDisc = maxDisc;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getEditBy() {
		return editBy;
	}
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	public String getWoodTypeName() {
		return woodTypeName;
	}
	public void setWoodTypeName(String woodTypeName) {
		this.woodTypeName = woodTypeName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getProductCatName() {
		return productCatName;
	}
	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}
}
