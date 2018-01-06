package module.sn.shipment.model;

import java.io.Serializable;
import java.util.Date;

import module.util.ComboBoxProperties;

public class Shipment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String shipmentAgent;
	private String shipmentType;
	
	public Shipment() {
	}
	
	public Shipment(String shipmentAgent) {
		this.shipmentAgent = shipmentAgent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShipmentAgent() {
		return shipmentAgent;
	}

	public void setShipmentAgent(String shipmentAgent) {
		this.shipmentAgent = shipmentAgent;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((shipmentAgent == null) ? 0 : shipmentAgent.hashCode());
		result = prime * result + ((shipmentType == null) ? 0 : shipmentType.hashCode());
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
		Shipment other = (Shipment) obj;
		if (id != other.id)
			return false;
		if (shipmentAgent == null) {
			if (other.shipmentAgent != null)
				return false;
		} else if (!shipmentAgent.equals(other.shipmentAgent))
			return false;
		if (shipmentType == null) {
			if (other.shipmentType != null)
				return false;
		} else if (!shipmentType.equals(other.shipmentType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return shipmentAgent;
	}
}
