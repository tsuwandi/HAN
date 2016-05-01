package module.supplier.model;

import java.io.Serializable;
import java.util.Date;

import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;

public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String suppCode;
	private String suppName;
	private String pt;
	private String npwp;
	private int suppTypeId;
	private String suppStatus;
	private double defaultTax;
	private String accountNo;
	private int bankId;
	private String accountName;
	private int currencyId;
	private int top;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	
	private SuppType suppType;
	private Bank bank;
	private Currency currency;
	
	private boolean isFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSuppCode() {
		return suppCode;
	}

	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}

	public String getSuppName() {
		return suppName;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public int getSuppTypeId() {
		return suppTypeId;
	}

	public void setSuppTypeId(int suppTypeId) {
		this.suppTypeId = suppTypeId;
	}

	public String getSuppStatus() {
		return suppStatus;
	}

	public void setSuppStatus(String suppStatus) {
		this.suppStatus = suppStatus;
	}

	public double getDefaultTax() {
		return defaultTax;
	}

	public void setDefaultTax(double defaultTax) {
		this.defaultTax = defaultTax;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
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
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + bankId;
		result = prime * result + currencyId;
		result = (int) (prime * result + defaultTax);
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((npwp == null) ? 0 : npwp.hashCode());
		result = prime * result + ((pt == null) ? 0 : pt.hashCode());
		result = prime * result + ((suppCode == null) ? 0 : suppCode.hashCode());
		result = prime * result + ((suppName == null) ? 0 : suppName.hashCode());
		result = prime * result + ((suppStatus == null) ? 0 : suppStatus.hashCode());
		result = prime * result + suppTypeId;
		result = prime * result + top;
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
		Supplier other = (Supplier) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (bankId != other.bankId)
			return false;
		if (currencyId != other.currencyId)
			return false;
		if (defaultTax != other.defaultTax)
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
		if (npwp == null) {
			if (other.npwp != null)
				return false;
		} else if (!npwp.equals(other.npwp))
			return false;
		if (pt == null) {
			if (other.pt != null)
				return false;
		} else if (!pt.equals(other.pt))
			return false;
		if (suppCode == null) {
			if (other.suppCode != null)
				return false;
		} else if (!suppCode.equals(other.suppCode))
			return false;
		if (suppName == null) {
			if (other.suppName != null)
				return false;
		} else if (!suppName.equals(other.suppName))
			return false;
		if (suppStatus == null) {
			if (other.suppStatus != null)
				return false;
		} else if (!suppStatus.equals(other.suppStatus))
			return false;
		if (suppTypeId != other.suppTypeId)
			return false;
		if (top != other.top)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier(id=" + id + ",suppCode=" + suppCode + ",suppName=" + suppName + ",pt=" + pt + ",npwp=" + npwp
				+ ",suppTypeId=" + suppTypeId + ",suppStatus=" + suppStatus + ",defaultTax=" + defaultTax
				+ ",accountNo=" + accountNo + ",bankId=" + bankId + ",accountName=" + accountName + ",currencyId="
				+ currencyId + ",top=" + top + ",inputDate=" + inputDate + ",inputBy=" + inputBy + ",editDate="
				+ editDate + ",editedBy=" + editedBy + ",deletedDate=" + deletedDate + ",deletedBy=" + deletedBy + ')';
	}

	public SuppType getSuppType() {
		if(suppType == null)
			suppType = new SuppType();
		return suppType;
	}

	public void setSuppType(SuppType suppType) {
		if(suppType == null)
			suppType = new SuppType();
		this.suppType = suppType;
	}

	public Bank getBank() {
		if(bank == null)
			bank = new Bank();
		return bank;
	}

	public void setBank(Bank bank) {
		if(bank == null)
			bank = new Bank();
		this.bank = bank;
	}

	public Currency getCurrency() {
		if(currency == null)
			currency = new Currency();
		return currency;
	}

	public void setCurrency(Currency currency) {
		if(currency == null)
			currency = new Currency();
		this.currency = currency;
	}

	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
}
