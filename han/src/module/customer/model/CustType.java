package module.customer.model;

import java.io.Serializable;
import java.util.Date;

import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.util.ComboBoxProperties;

public class CustType implements Serializable, ComboBoxProperties {

	private static final long serialVersionUID = 1L;

	private int id;
	private String custType;

	public CustType() {

	}

	public CustType(String custType) {
		this.custType = custType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custType == null) ? 0 : custType.hashCode());
		result = prime * result + id;
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
		CustType other = (CustType) obj;
		if (custType == null) {
			if (other.custType != null)
				return false;
		} else if (!custType.equals(other.custType))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustType [id=" + id + ", custType=" + custType + "]";
	}

	@Override
	public Object getField() {
		return custType;
	}

}
