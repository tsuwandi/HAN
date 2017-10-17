package module.purchaseprodsupp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import module.sn.costcenter.model.CostCenter;
import module.supplier.model.Supplier;

public class PurchaseProdSupp implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String ppsCode;
	private String suppCode;
	private Integer costCenterId;
	private Date purchaseDate;
	private Date deliveryDate;
	private String note;
	private String status;
	private String confirmCode;
	private Date confirmDate;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	
	private BigDecimal total;
	private BigDecimal tax;
	private BigDecimal grandTotal;
	
	private Supplier supplier;
	private CostCenter costCenter;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPpsCode() {
		return ppsCode;
	}
	public void setPpsCode(String ppsCode) {
		this.ppsCode = ppsCode;
	}
	public String getSuppCode() {
		return suppCode;
	}
	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}
	public Integer getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(Integer costCenterId) {
		this.costCenterId = costCenterId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Supplier getSupplier() {
		if(supplier == null) {
			supplier = new Supplier();
		}
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		if(supplier == null) {
			supplier = new Supplier();
		}
		this.supplier = supplier;
	}
	public CostCenter getCostCenter() {
		if(costCenter == null) {
			costCenter = new CostCenter();
		}
		return costCenter;
	}
	public void setCostCenter(CostCenter costCenter) {
		if(costCenter == null) {
			costCenter = new CostCenter();
		}
		this.costCenter = costCenter;
	}
}
