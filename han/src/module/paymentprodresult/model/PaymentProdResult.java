package module.paymentprodresult.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentProdResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String payPrCode;
	private String rprCode;
	private Date dueDate;
	private BigDecimal rate;
	private String paymentStatus;
	private BigDecimal subtotal;
	private BigDecimal disc;
	private BigDecimal tax;
	private BigDecimal otherFee;
	private BigDecimal total;
	private String status;
	private String confirmCode;
	private Date confirmDate;
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

	public String getPayPrCode() {
		return payPrCode;
	}

	public void setPayPrCode(String payPrCode) {
		this.payPrCode = payPrCode;
	}

	public String getRprCode() {
		return rprCode;
	}

	public void setRprCode(String rprCode) {
		this.rprCode = rprCode;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDisc() {
		return disc;
	}

	public void setDisc(BigDecimal disc) {
		this.disc = disc;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confirmCode == null) ? 0 : confirmCode.hashCode());
		result = prime * result
				+ ((confirmDate == null) ? 0 : confirmDate.hashCode());
		result = prime * result
				+ ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result
				+ ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((disc == null) ? 0 : disc.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result
				+ ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result
				+ ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result
				+ ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result
				+ ((otherFee == null) ? 0 : otherFee.hashCode());
		result = prime * result
				+ ((payPrCode == null) ? 0 : payPrCode.hashCode());
		result = prime * result
				+ ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((rprCode == null) ? 0 : rprCode.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((subtotal == null) ? 0 : subtotal.hashCode());
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		PaymentProdResult other = (PaymentProdResult) obj;
		if (confirmCode == null) {
			if (other.confirmCode != null)
				return false;
		} else if (!confirmCode.equals(other.confirmCode))
			return false;
		if (confirmDate == null) {
			if (other.confirmDate != null)
				return false;
		} else if (!confirmDate.equals(other.confirmDate))
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
		if (disc == null) {
			if (other.disc != null)
				return false;
		} else if (!disc.equals(other.disc))
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
		if (otherFee == null) {
			if (other.otherFee != null)
				return false;
		} else if (!otherFee.equals(other.otherFee))
			return false;
		if (payPrCode == null) {
			if (other.payPrCode != null)
				return false;
		} else if (!payPrCode.equals(other.payPrCode))
			return false;
		if (paymentStatus == null) {
			if (other.paymentStatus != null)
				return false;
		} else if (!paymentStatus.equals(other.paymentStatus))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (rprCode == null) {
			if (other.rprCode != null)
				return false;
		} else if (!rprCode.equals(other.rprCode))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (subtotal == null) {
			if (other.subtotal != null)
				return false;
		} else if (!subtotal.equals(other.subtotal))
			return false;
		if (tax == null) {
			if (other.tax != null)
				return false;
		} else if (!tax.equals(other.tax))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentProdResult [id=" + id + ", payPrCode=" + payPrCode
				+ ", rprCode=" + rprCode + ", dueDate=" + dueDate + ", rate="
				+ rate + ", paymentStatus=" + paymentStatus + ", subtotal="
				+ subtotal + ", disc=" + disc + ", tax=" + tax + ", otherFee="
				+ otherFee + ", total=" + total + ", status=" + status
				+ ", confirmCode=" + confirmCode + ", confirmDate="
				+ confirmDate + ", inputDate=" + inputDate + ", inputBy="
				+ inputBy + ", editDate=" + editDate + ", editedBy=" + editedBy
				+ ", deletedDate=" + deletedDate + ", deletedBy=" + deletedBy
				+ "]";
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getSuppName() {
		return suppName;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public String getPprCode() {
		return pprCode;
	}

	public void setPprCode(String pprCode) {
		this.pprCode = pprCode;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	private Date receiveDate;
	private String suppName;
	private String pprCode;
	private Date purchaseDate;
	private String source;
	private String currency;
}
