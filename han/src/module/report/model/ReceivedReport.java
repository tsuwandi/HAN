package module.report.model;

public class ReceivedReport {
	private String receivedCode;
	private String receivedDate;
	private int supplierCPID;
	private String noDocLegalitas;
	private int woodTypeID;
	private String createdDate;
	private String docLegalitas;
	private int woodResourceID;
	private int totalLogSupplier;
	private double totalVolumeSupplier;
	private String woodResource;
	private int suppAddressID;
	private String nameOwner;
	private String woodDomicile;
	private String supplierName;
	private String woodType;
	private String woodGenus;
	private int totalLogGrader;
	private double totalVolumeGrader;
	
	
	
	
	public String getWoodResource() {
		return woodResource;
	}
	public void setWoodResource(String woodResource) {
		this.woodResource = woodResource;
	}
	public String getReceivedCode() {
		return receivedCode;
	}
	public void setReceivedCode(String receivedCode) {
		this.receivedCode = receivedCode;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		if(supplierName==null)supplierName="-";
		this.supplierName = supplierName;
	}
	public int getSupplierCPID() {
		return supplierCPID;
	}
	public void setSupplierCPID(int supplierCPID) {
		this.supplierCPID = supplierCPID;
	}
	public String getNoDocLegalitas() {
		return noDocLegalitas;
	}
	public void setNoDocLegalitas(String noDocLegalitas) {
		this.noDocLegalitas = noDocLegalitas;
	}
	public int getWoodTypeID() {
		return woodTypeID;
	}
	public void setWoodTypeID(int woodTypeID) {
		this.woodTypeID = woodTypeID;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getDocLegalitas() {
		return docLegalitas;
	}
	public void setDocLegalitas(String docLegalitas) {
		this.docLegalitas = docLegalitas;
	}
	public int getWoodResourceID() {
		return woodResourceID;
	}
	public void setWoodResourceID(int woodResourceID) {
		this.woodResourceID = woodResourceID;
	}
	public int getTotalLogSupplier() {
		return totalLogSupplier;
	}
	public void setTotalLogSupplier(int totalLogSupplier) {
		this.totalLogSupplier = totalLogSupplier;
	}
	public double getTotalVolumeSupplier() {
		return totalVolumeSupplier;
	}
	public void setTotalVolumeSupplier(double totalVolumeSupplier) {
		this.totalVolumeSupplier = totalVolumeSupplier;
	}
	public int getSuppAddressID() {
		return suppAddressID;
	}
	public void setSuppAddressID(int suppAddressID) {
		this.suppAddressID = suppAddressID;
	}
	public String getNameOwner() {
		return nameOwner;
	}
	public void setNameOwner(String nameOwner) {
		if(nameOwner==null) nameOwner="-";
		this.nameOwner = nameOwner;
	}
	public String getWoodDomicile() {
		return woodDomicile;
	}
	public void setWoodDomicile(String woodDomicile) {
		if(woodDomicile == null)woodDomicile="-";
		this.woodDomicile = woodDomicile;
	}
	public String getWoodType() {
		return woodType;
	}
	public void setWoodType(String woodType) {
		this.woodType = woodType;
	}
	public String getWoodGenus() {
		return woodGenus;
	}
	public void setWoodGenus(String woodGenus) {
		this.woodGenus = woodGenus;
	}
	public int getTotalLogGrader() {
		return totalLogGrader;
	}
	public void setTotalLogGrader(int totalLogGrader) {
		this.totalLogGrader = totalLogGrader;
	}
	public double getTotalVolumeGrader() {
		return totalVolumeGrader;
	}
	public void setTotalVolumeGrader(double totalVolumeGrader) {
		this.totalVolumeGrader = totalVolumeGrader;
	}
	@Override
	public String toString() {
		return "ReceivedReport [receivedCode=" + receivedCode + ", receivedDate=" + receivedDate + ", supplierCPID="
				+ supplierCPID + ", noDocLegalitas=" + noDocLegalitas + ", woodTypeID=" + woodTypeID + ", createdDate="
				+ createdDate + ", docLegalitas=" + docLegalitas + ", woodResourceID=" + woodResourceID
				+ ", totalLogSupplier=" + totalLogSupplier + ", totalVolumeSupplier=" + totalVolumeSupplier
				+ ", woodResource=" + woodResource + ", suppAddressID=" + suppAddressID + ", nameOwner=" + nameOwner
				+ ", woodDomicile=" + woodDomicile + ", supplierName=" + supplierName + ", woodType=" + woodType
				+ ", woodGenus=" + woodGenus + ", totalLogGrader=" + totalLogGrader + ", totalVolumeGrader="
				+ totalVolumeGrader + "]";
	}
	
	}
