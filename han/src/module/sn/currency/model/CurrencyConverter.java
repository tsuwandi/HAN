package module.sn.currency.model;

import java.io.Serializable;
import java.util.Date;

import module.util.ComboBoxProperties;

public class CurrencyConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	private int currencyValue;
	private String currencyFrom;
	private String currencyTo;

	public int getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(int currencyValue) {
		this.currencyValue = currencyValue;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyFrom == null) ? 0 : currencyFrom.hashCode());
		result = prime * result + ((currencyTo == null) ? 0 : currencyTo.hashCode());
		result = prime * result + currencyValue;
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
		CurrencyConverter other = (CurrencyConverter) obj;
		if (currencyFrom == null) {
			if (other.currencyFrom != null)
				return false;
		} else if (!currencyFrom.equals(other.currencyFrom))
			return false;
		if (currencyTo == null) {
			if (other.currencyTo != null)
				return false;
		} else if (!currencyTo.equals(other.currencyTo))
			return false;
		if (currencyValue != other.currencyValue)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurrencyConverter [currencyValue=" + currencyValue + ", currencyFrom=" + currencyFrom + ", currencyTo="
				+ currencyTo + "]";
	}

}
