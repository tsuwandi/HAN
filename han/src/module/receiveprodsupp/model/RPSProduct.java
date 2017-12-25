package module.receiveprodsupp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import module.productsupportinggood.model.ProductSupp;

public class RPSProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String rpsCode;
	private String productCode;
	private BigDecimal qty;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	
	public ProductSupp getProductSupp() {
		if(productSupp == null)
			productSupp = new ProductSupp();
		return productSupp;
	}
	public void setProductSupp(ProductSupp productSupp) {
		if(productSupp == null)
			productSupp = new ProductSupp();
		this.productSupp = productSupp;
	}

	private ProductSupp productSupp;
	private BigDecimal unitPrice;
	private BigDecimal subTotal;
	private boolean isFlag;
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public boolean isFlag() {
		return isFlag;
	}
	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRpsCode() {
		return rpsCode;
	}
	public void setRpsCode(String rpsCode) {
		this.rpsCode = rpsCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
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
	public String getEditedBy() {
		return editedBy;
	}
	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
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
}
