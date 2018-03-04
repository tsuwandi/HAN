package module.sales.model;

import java.io.Serializable;
import java.util.Date;

import module.customer.model.CustAddress;
import module.customer.model.Customer;
import module.sn.bank.model.BankCust;
import module.sn.currency.model.Currency;
import module.util.ComboBoxProperties;

public class Sales implements Serializable, ComboBoxProperties {

	private static final long serialVersionUID = 1L;

	private int id;
	private int customerId;
	private int custAddrId;
	private int currencyId;
	private int freightCostCurrencyId;
	private int insuranceCostCurrencyId;
	private String poNo;
	private Date poDate;
	private String soNo;
	private Date soDate;
	private Double surcharge;
	private Double discount;
	private Double freightCost;
	private Double insuranceCost;
	private Double vat;
	private String description;
	private Date inputDate;
	private String inputBy;
	private Date editedDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	private String deleteReason;
	private String priceTerm;
	private int bankId;
	private int currencyToRupiah;
	private String currencyBank;
	private String confirmCode;
	private Date confirmDate;

	private Customer customer;
	private CustAddress custAddress;
	private BankCust bankCust;
	private Currency currency;
	private Currency fcCurrency;
	private Currency icCurrency;

	public Sales() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustAddrId() {
		return custAddrId;
	}

	public void setCustAddrId(int custAddrId) {
		this.custAddrId = custAddrId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getFreightCostCurrencyId() {
		return freightCostCurrencyId;
	}

	public void setFreightCostCurrencyId(int freightCostCurrencyId) {
		this.freightCostCurrencyId = freightCostCurrencyId;
	}

	public int getInsuranceCostCurrencyId() {
		return insuranceCostCurrencyId;
	}

	public void setInsuranceCostCurrencyId(int insuranceCostCurrencyId) {
		this.insuranceCostCurrencyId = insuranceCostCurrencyId;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getSoNo() {
		return soNo;
	}

	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}

	public Date getSoDate() {
		return soDate;
	}

	public void setSoDate(Date soDate) {
		this.soDate = soDate;
	}

	public Double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getFreightCost() {
		return freightCost;
	}

	public void setFreightCost(Double freightCost) {
		this.freightCost = freightCost;
	}

	public Double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
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

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer( Customer customer) {
		this.customer = customer;
	}

	public CustAddress getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(CustAddress custAddress) {
		this.custAddress = custAddress;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Currency getFcCurrency() {
		return fcCurrency;
	}

	public void setFcCurrency(Currency fcCurrency) {
		this.fcCurrency = fcCurrency;
	}

	public Currency getIcCurrency() {
		return icCurrency;
	}

	public void setIcCurrency(Currency icCurrency) {
		this.icCurrency = icCurrency;
	}

	public String getPriceTerm() {
		return priceTerm;
	}

	public void setPriceTerm(String priceTerm) {
		this.priceTerm = priceTerm;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getCurrencyToRupiah() {
		return currencyToRupiah;
	}

	public void setCurrencyToRupiah(int currencyToRupiah) {
		this.currencyToRupiah = currencyToRupiah;
	}

	public String getCurrencyBank() {
		return currencyBank;
	}

	public void setCurrencyBank(String currencyBank) {
		this.currencyBank = currencyBank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bankId;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((currencyBank == null) ? 0 : currencyBank.hashCode());
		result = prime * result + currencyId;
		result = prime * result + currencyToRupiah;
		result = prime * result + custAddrId;
		result = prime * result + ((custAddress == null) ? 0 : custAddress.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + customerId;
		result = prime * result + ((deleteReason == null) ? 0 : deleteReason.hashCode());
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + ((editedDate == null) ? 0 : editedDate.hashCode());
		result = prime * result + ((fcCurrency == null) ? 0 : fcCurrency.hashCode());
		result = prime * result + ((freightCost == null) ? 0 : freightCost.hashCode());
		result = prime * result + freightCostCurrencyId;
		result = prime * result + ((icCurrency == null) ? 0 : icCurrency.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((insuranceCost == null) ? 0 : insuranceCost.hashCode());
		result = prime * result + insuranceCostCurrencyId;
		result = prime * result + ((poDate == null) ? 0 : poDate.hashCode());
		result = prime * result + ((poNo == null) ? 0 : poNo.hashCode());
		result = prime * result + ((priceTerm == null) ? 0 : priceTerm.hashCode());
		result = prime * result + ((soDate == null) ? 0 : soDate.hashCode());
		result = prime * result + ((soNo == null) ? 0 : soNo.hashCode());
		result = prime * result + ((surcharge == null) ? 0 : surcharge.hashCode());
		result = prime * result + ((vat == null) ? 0 : vat.hashCode());
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
		Sales other = (Sales) obj;
		if (bankId != other.bankId)
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (currencyBank == null) {
			if (other.currencyBank != null)
				return false;
		} else if (!currencyBank.equals(other.currencyBank))
			return false;
		if (currencyId != other.currencyId)
			return false;
		if (currencyToRupiah != other.currencyToRupiah)
			return false;
		if (custAddrId != other.custAddrId)
			return false;
		if (custAddress == null) {
			if (other.custAddress != null)
				return false;
		} else if (!custAddress.equals(other.custAddress))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (customerId != other.customerId)
			return false;
		if (deleteReason == null) {
			if (other.deleteReason != null)
				return false;
		} else if (!deleteReason.equals(other.deleteReason))
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (editedBy == null) {
			if (other.editedBy != null)
				return false;
		} else if (!editedBy.equals(other.editedBy))
			return false;
		if (editedDate == null) {
			if (other.editedDate != null)
				return false;
		} else if (!editedDate.equals(other.editedDate))
			return false;
		if (fcCurrency == null) {
			if (other.fcCurrency != null)
				return false;
		} else if (!fcCurrency.equals(other.fcCurrency))
			return false;
		if (freightCost == null) {
			if (other.freightCost != null)
				return false;
		} else if (!freightCost.equals(other.freightCost))
			return false;
		if (freightCostCurrencyId != other.freightCostCurrencyId)
			return false;
		if (icCurrency == null) {
			if (other.icCurrency != null)
				return false;
		} else if (!icCurrency.equals(other.icCurrency))
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
		if (insuranceCost == null) {
			if (other.insuranceCost != null)
				return false;
		} else if (!insuranceCost.equals(other.insuranceCost))
			return false;
		if (insuranceCostCurrencyId != other.insuranceCostCurrencyId)
			return false;
		if (poDate == null) {
			if (other.poDate != null)
				return false;
		} else if (!poDate.equals(other.poDate))
			return false;
		if (poNo == null) {
			if (other.poNo != null)
				return false;
		} else if (!poNo.equals(other.poNo))
			return false;
		if (priceTerm == null) {
			if (other.priceTerm != null)
				return false;
		} else if (!priceTerm.equals(other.priceTerm))
			return false;
		if (soDate == null) {
			if (other.soDate != null)
				return false;
		} else if (!soDate.equals(other.soDate))
			return false;
		if (soNo == null) {
			if (other.soNo != null)
				return false;
		} else if (!soNo.equals(other.soNo))
			return false;
		if (surcharge == null) {
			if (other.surcharge != null)
				return false;
		} else if (!surcharge.equals(other.surcharge))
			return false;
		if (vat == null) {
			if (other.vat != null)
				return false;
		} else if (!vat.equals(other.vat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", customerId=" + customerId + ", custAddrId=" + custAddrId + ", currencyId="
				+ currencyId + ", freightCostCurrencyId=" + freightCostCurrencyId + ", insuranceCostCurrencyId="
				+ insuranceCostCurrencyId + ", poNo=" + poNo + ", poDate=" + poDate + ", soNo=" + soNo + ", soDate="
				+ soDate + ", surcharge=" + surcharge + ", discount=" + discount + ", freightCost=" + freightCost
				+ ", insuranceCost=" + insuranceCost + ", vat=" + vat + ", description=" + description + ", inputDate="
				+ inputDate + ", inputBy=" + inputBy + ", editedDate=" + editedDate + ", editedBy=" + editedBy
				+ ", deletedDate=" + deletedDate + ", deletedBy=" + deletedBy + ", deleteReason=" + deleteReason
				+ ", priceTerm=" + priceTerm + ", bankId=" + bankId + ", currencyToRupiah=" + currencyToRupiah
				+ ", currencyBank=" + currencyBank + ", customer=" + customer + ", custAddress=" + custAddress
				+ ", currency=" + currency + ", fcCurrency=" + fcCurrency + ", icCurrency=" + icCurrency + "]";
	}

	@Override
	public Object getField() {
		return soNo;
	}

	public BankCust getBankCust() {
		return bankCust;
	}

	public void setBankCust(BankCust bankCust) {
		this.bankCust = bankCust;
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

}
