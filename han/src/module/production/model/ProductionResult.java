package module.production.model;

import java.util.Date;
import java.util.List;

public class ProductionResult {
	int id;
	String prodResultCode;
	String productionCode;
	Date prodResultDate;
	int totalOutput;
	int totalRepairKlem;
	int totalRepairProtol;
	int totalFineA;
	int totalFineB;
	int totalFineResult;
	List<ProductionResultProduct> listOfProductionResultDetail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdResultCode() {
		return prodResultCode;
	}
	public void setProdResultCode(String prodResultCode) {
		this.prodResultCode = prodResultCode;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	
	public Date getProdResultDate() {
		return prodResultDate;
	}
	public void setProdResultDate(Date prodResultDate) {
		this.prodResultDate = prodResultDate;
	}
	
	public int getTotalOutput() {
		return totalOutput;
	}
	public void setTotalOutput(int totalOutput) {
		this.totalOutput = totalOutput;
	}
	public int getTotalRepairKlem() {
		return totalRepairKlem;
	}
	public void setTotalRepairKlem(int totalRepairKlem) {
		this.totalRepairKlem = totalRepairKlem;
	}
	public int getTotalRepairProtol() {
		return totalRepairProtol;
	}
	public void setTotalRepairProtol(int totalRepairProtol) {
		this.totalRepairProtol = totalRepairProtol;
	}
	public int getTotalFineA() {
		return totalFineA;
	}
	public void setTotalFineA(int totalFineA) {
		this.totalFineA = totalFineA;
	}
	public int getTotalFineB() {
		return totalFineB;
	}
	public void setTotalFineB(int totalFineB) {
		this.totalFineB = totalFineB;
	}
	public int getTotalFineResult() {
		return totalFineResult;
	}
	public void setTotalFineResult(int totalFineResult) {
		this.totalFineResult = totalFineResult;
	}
	public List<ProductionResultProduct> getListOfProductionResultDetail() {
		return listOfProductionResultDetail;
	}
	public void setListOfProductionResultDetail(List<ProductionResultProduct> listOfProductionResultDetail) {
		this.listOfProductionResultDetail = listOfProductionResultDetail;
	}
	
}
