package module.dailyclosing.model;

import java.io.Serializable;
import java.util.Date;

public class InventoryLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String productCode;
	private int warehouse;
	private Double prevStock;
	private Double plusStock;
	private Double minStock;
	private Double currStock;
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
	public Double getPrevStock() {
		return prevStock;
	}
	public void setPrevStock(Double prevStock) {
		this.prevStock = prevStock;
	}
	public Double getPlusStock() {
		return plusStock;
	}
	public void setPlusStock(Double plusStock) {
		this.plusStock = plusStock;
	}
	public Double getMinStock() {
		return minStock;
	}
	public void setMinStock(Double minStock) {
		this.minStock = minStock;
	}
	public Double getCurrStock() {
		return currStock;
	}
	public void setCurrStock(Double currStock) {
		this.currStock = currStock;
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
	@Override
	public String toString() {
		return "InventoryLog [id=" + id + ", productCode=" + productCode + ", warehouse=" + warehouse + ", prevStock="
				+ prevStock + ", plusStock=" + plusStock + ", minStock=" + minStock + ", currStock=" + currStock
				+ ", prevStockDate=" + prevStockDate + ", currStockDate=" + currStockDate + ", confirmCode="
				+ confirmCode + ", inputDate=" + inputDate + ", inputBy=" + inputBy + ", editDate=" + editDate
				+ ", editedBy=" + editedBy + ", deletedDate=" + deletedDate + ", deletedBy=" + deletedBy + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmCode == null) ? 0 : confirmCode.hashCode());
		result = prime * result + ((currStock == null) ? 0 : currStock.hashCode());
		result = prime * result + ((currStockDate == null) ? 0 : currStockDate.hashCode());
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((minStock == null) ? 0 : minStock.hashCode());
		result = prime * result + ((plusStock == null) ? 0 : plusStock.hashCode());
		result = prime * result + ((prevStock == null) ? 0 : prevStock.hashCode());
		result = prime * result + ((prevStockDate == null) ? 0 : prevStockDate.hashCode());
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + warehouse;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryLog other = (InventoryLog) obj;
		if (confirmCode == null) {
			if (other.confirmCode != null)
				return false;
		} else if (!confirmCode.equals(other.confirmCode))
			return false;
		if (currStock == null) {
			if (other.currStock != null)
				return false;
		} else if (!currStock.equals(other.currStock))
			return false;
		if (currStockDate == null) {
			if (other.currStockDate != null)
				return false;
		} else if (!currStockDate.equals(other.currStockDate))
			return false;
		if (deletedBy == null) {
			if (other.deletedBy != null)
				return false;
		} else if (!deletedBy.equals(other.deletedBy))
			return false;
		if (deletedDate == null) {
			if (other.deletedDate != null)
				return false;
		} else if (!deletedDate.equals(other.deletedDate))
			return false;
		if (editDate == null) {
			if (other.editDate != null)
				return false;
		} else if (!editDate.equals(other.editDate))
			return false;
		if (editedBy == null) {
			if (other.editedBy != null)
				return false;
		} else if (!editedBy.equals(other.editedBy))
			return false;
		if (id != other.id)
			return false;
		if (inputBy == null) {
			if (other.inputBy != null)
				return false;
		} else if (!inputBy.equals(other.inputBy))
			return false;
		if (inputDate == null) {
			if (other.inputDate != null)
				return false;
		} else if (!inputDate.equals(other.inputDate))
			return false;
		if (minStock == null) {
			if (other.minStock != null)
				return false;
		} else if (!minStock.equals(other.minStock))
			return false;
		if (plusStock == null) {
			if (other.plusStock != null)
				return false;
		} else if (!plusStock.equals(other.plusStock))
			return false;
		if (prevStock == null) {
			if (other.prevStock != null)
				return false;
		} else if (!prevStock.equals(other.prevStock))
			return false;
		if (prevStockDate == null) {
			if (other.prevStockDate != null)
				return false;
		} else if (!prevStockDate.equals(other.prevStockDate))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (warehouse != other.warehouse)
			return false;
		return true;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	private String productName;

	private String warehouseName;
	
}
