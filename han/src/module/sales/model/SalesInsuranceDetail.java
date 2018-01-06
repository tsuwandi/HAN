package module.sales.model;

import java.io.Serializable;
import java.util.Date;

import module.pembelian.model.Product;
import module.sn.insurance.model.Insurance;
import module.sn.uom.model.Uom;

public class SalesInsuranceDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int salesId;
	private int insuranceId;
	private Double cost;
	
	private boolean flag;
	
	private Insurance insurance;
	
	public SalesInsuranceDetail(){
		insurance = new Insurance();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((insurance == null) ? 0 : insurance.hashCode());
		result = prime * result + insuranceId;
		result = prime * result + salesId;
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
		SalesInsuranceDetail other = (SalesInsuranceDetail) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (flag != other.flag)
			return false;
		if (id != other.id)
			return false;
		if (insurance == null) {
			if (other.insurance != null)
				return false;
		} else if (!insurance.equals(other.insurance))
			return false;
		if (insuranceId != other.insuranceId)
			return false;
		if (salesId != other.salesId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesInsuranceDetail [id=" + id + ", salesId=" + salesId + ", insuranceId=" + insuranceId + ", cost="
				+ cost + ", insurance=" + insurance + "]";
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
