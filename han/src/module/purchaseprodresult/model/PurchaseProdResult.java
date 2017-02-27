package module.purchaseprodresult.model;

import java.io.Serializable;
import java.util.Date;

import module.sn.currency.model.Currency;
import module.supplier.model.Supplier;

public class PurchaseProdResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String pprCode;
	private String suppCode;
	private Date purchaseDate;
	private Date dueDate;
	private String status;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPprCode() {
		return pprCode;
	}

	public void setPprCode(String pprCode) {
		this.pprCode = pprCode;
	}

	public String getSuppCode() {
		return suppCode;
	}

	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((pprCode == null) ? 0 : pprCode.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((suppCode == null) ? 0 : suppCode.hashCode());
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
		PurchaseProdResult other = (PurchaseProdResult) obj;
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
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
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
		if (pprCode == null) {
			if (other.pprCode != null)
				return false;
		} else if (!pprCode.equals(other.pprCode))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (suppCode == null) {
			if (other.suppCode != null)
				return false;
		} else if (!suppCode.equals(other.suppCode))
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		return true;
	}

	public Supplier getSupplier() {
		if(supplier == null)
			supplier = new Supplier();
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		if(supplier == null)
			supplier = new Supplier();
		this.supplier = supplier;
	}

	private Supplier supplier;
	
	private Double total;
	private Double discount;
	private Double tax;
	private Double grandTotal;
	private Date paymentDate;
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
