package module.productionpk.model;

import java.util.Date;
import java.util.List;

public class ProdPK {
	int id;
	String prodPKCode; 
	String groupShiftCode;
	String lineCode;
	String shiftCode;
	Date productionDate;
	String information;
	double totalMaterialProtol;
	double totalMaterialKlem;
	String status;
	String confirmCode;
	Date confirmDate;
	String groupShiftDescription;
	String lineDescription;
	String shiftName;
	List<ProdPKMaterial> listPKMaterial;
	List<ProdPKResult> listProdPKResult;
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
	public double getTotalMaterialProtol() {
		return totalMaterialProtol;
	}
	public void setTotalMaterialProtol(double totalMaterialProtol) {
		this.totalMaterialProtol = totalMaterialProtol;
	}
	public double getTotalMaterialKlem() {
		return totalMaterialKlem;
	}
	public void setTotalMaterialKlem(double totalMaterialKlem) {
		this.totalMaterialKlem = totalMaterialKlem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
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
	public List<ProdPKMaterial> getListPKMaterial() {
		return listPKMaterial;
	}
	public void setListPKMaterial(List<ProdPKMaterial> listPKMaterial) {
		this.listPKMaterial = listPKMaterial;
	}
	public List<ProdPKResult> getListProdPKResult() {
		return listProdPKResult;
	}
	public void setListProdPKResult(List<ProdPKResult> listProdPKResult) {
		this.listProdPKResult = listProdPKResult;
	}
	

}
