package module.invoiceprodresult.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import module.product.model.Product;

public class InvPrProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int idInvPr;
	private String productCode;
	private BigDecimal qty;
	private BigDecimal price;
	private BigDecimal idrPrice;
	private BigDecimal subtotal;
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getIdrPrice() {
		return idrPrice;
	}

	public void setIdrPrice(BigDecimal idrPrice) {
		this.idrPrice = idrPrice;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getQtyPurchase() {
		return qtyPurchase;
	}

	public void setQtyPurchase(BigDecimal qtyPurchase) {
		this.qtyPurchase = qtyPurchase;
	}

	public BigDecimal getQtyReceive() {
		return qtyReceive;
	}

	public void setQtyReceive(BigDecimal qtyReceive) {
		this.qtyReceive = qtyReceive;
	}

	private Product product;
	private String productName;
	private BigDecimal qtyPurchase;
	private BigDecimal qtyReceive;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result
				+ ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result
				+ ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result
				+ ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + idInvPr;
		result = prime * result
				+ ((idrPrice == null) ? 0 : idrPrice.hashCode());
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result
				+ ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		result = prime * result
				+ ((qtyPurchase == null) ? 0 : qtyPurchase.hashCode());
		result = prime * result
				+ ((qtyReceive == null) ? 0 : qtyReceive.hashCode());
		result = prime * result
				+ ((subtotal == null) ? 0 : subtotal.hashCode());
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
		InvPrProduct other = (InvPrProduct) obj;
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
		if (idInvPr != other.idInvPr)
			return false;
		if (idrPrice == null) {
			if (other.idrPrice != null)
				return false;
		} else if (!idrPrice.equals(other.idrPrice))
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		if (qtyPurchase == null) {
			if (other.qtyPurchase != null)
				return false;
		} else if (!qtyPurchase.equals(other.qtyPurchase))
			return false;
		if (qtyReceive == null) {
			if (other.qtyReceive != null)
				return false;
		} else if (!qtyReceive.equals(other.qtyReceive))
			return false;
		if (subtotal == null) {
			if (other.subtotal != null)
				return false;
		} else if (!subtotal.equals(other.subtotal))
			return false;
		return true;
	}

	public int getIdInvPr() {
		return idInvPr;
	}

	public void setIdInvPr(int idInvPr) {
		this.idInvPr = idInvPr;
	}
	
	
}
