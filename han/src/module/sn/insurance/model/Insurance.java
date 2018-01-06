package module.sn.insurance.model;

import java.io.Serializable;
import java.util.Date;

import module.util.ComboBoxProperties;

public class Insurance implements Serializable{

	private static final long serialVersionUID = 1L;

	private int insuranceId;
	private String insuranceCompanyName;
	private String insuranceType;	
	
	public Insurance() {
	}
	
	public Insurance(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}

	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((insuranceCompanyName == null) ? 0 : insuranceCompanyName.hashCode());
		result = prime * result + insuranceId;
		result = prime * result + ((insuranceType == null) ? 0 : insuranceType.hashCode());
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
		Insurance other = (Insurance) obj;
		if (insuranceCompanyName == null) {
			if (other.insuranceCompanyName != null)
				return false;
		} else if (!insuranceCompanyName.equals(other.insuranceCompanyName))
			return false;
		if (insuranceId != other.insuranceId)
			return false;
		if (insuranceType == null) {
			if (other.insuranceType != null)
				return false;
		} else if (!insuranceType.equals(other.insuranceType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return insuranceCompanyName;
	}
}
