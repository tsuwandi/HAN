package module.dailyclosing.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InventoryLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String productCode;
	private int warehouse;
	private BigDecimal prevStock;
	private BigDecimal plusStock;
	private BigDecimal minStock;
	private Date prevStockDate;
	private Date currStockDate;
	private String confirmCode;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(int warehouse) {
		this.warehouse = warehouse;
	}
	public BigDecimal getPrevStock() {
		return prevStock;
	}
	public void setPrevStock(BigDecimal prevStock) {
		this.prevStock = prevStock;
	}
	public BigDecimal getPlusStock() {
		return plusStock;
	}
	public void setPlusStock(BigDecimal plusStock) {
		this.plusStock = plusStock;
	}
	public BigDecimal getMinStock() {
		return minStock;
	}
	public void setMinStock(BigDecimal minStock) {
		this.minStock = minStock;
	}
	public Date getPrevStockDate() {
		return prevStockDate;
	}
	public void setPrevStockDate(Date prevStockDate) {
		this.prevStockDate = prevStockDate;
	}
	public Date getCurrStockDate() {
		return currStockDate;
	}
	public void setCurrStockDate(Date currStockDate) {
		this.currStockDate = currStockDate;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
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
