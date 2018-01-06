package module.sales.model;

import java.io.Serializable;
import java.util.Date;

import module.sn.shipment.model.Shipment;

public class ShipmentSalesOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int salesOrderId;
	private String shipmentAgent;
	private String shipmentType;
	private String originAddress;
	private String destinationAddress;
	private Date dateOfShipment;
	private Date pickupDate;
	private Double shipmentCost;

	private boolean flag;
	
	private Shipment shipment;

	public ShipmentSalesOrder(){
		shipment = new Shipment();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
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

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getDestionationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destionationAddress) {
		this.destinationAddress = destionationAddress;
	}

	public Date getDateOfShipment() {
		return dateOfShipment;
	}

	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Double getShipmentCost() {
		return shipmentCost;
	}

	public void setShipmentCost(Double shipmentCost) {
		this.shipmentCost = shipmentCost;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfShipment == null) ? 0 : dateOfShipment.hashCode());
		result = prime * result + ((destinationAddress == null) ? 0 : destinationAddress.hashCode());
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((originAddress == null) ? 0 : originAddress.hashCode());
		result = prime * result + ((pickupDate == null) ? 0 : pickupDate.hashCode());
		result = prime * result + salesOrderId;
		result = prime * result + ((shipmentAgent == null) ? 0 : shipmentAgent.hashCode());
		result = prime * result + ((shipmentCost == null) ? 0 : shipmentCost.hashCode());
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
		ShipmentSalesOrder other = (ShipmentSalesOrder) obj;
		if (dateOfShipment == null) {
			if (other.dateOfShipment != null)
				return false;
		} else if (!dateOfShipment.equals(other.dateOfShipment))
			return false;
		if (destinationAddress == null) {
			if (other.destinationAddress != null)
				return false;
		} else if (!destinationAddress.equals(other.destinationAddress))
			return false;
		if (flag != other.flag)
			return false;
		if (id != other.id)
			return false;
		if (originAddress == null) {
			if (other.originAddress != null)
				return false;
		} else if (!originAddress.equals(other.originAddress))
			return false;
		if (pickupDate == null) {
			if (other.pickupDate != null)
				return false;
		} else if (!pickupDate.equals(other.pickupDate))
			return false;
		if (salesOrderId != other.salesOrderId)
			return false;
		if (shipmentAgent == null) {
			if (other.shipmentAgent != null)
				return false;
		} else if (!shipmentAgent.equals(other.shipmentAgent))
			return false;
		if (shipmentCost == null) {
			if (other.shipmentCost != null)
				return false;
		} else if (!shipmentCost.equals(other.shipmentCost))
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
		return "ShipmentSalesOrder [id=" + id + ", salesOrderId=" + salesOrderId + ", shipmentAgent=" + shipmentAgent
				+ ", shipmentType=" + shipmentType + ", originAddress=" + originAddress + ", destionationAddress="
				+ destinationAddress + ", dateOfShipment=" + dateOfShipment + ", pickupDate=" + pickupDate
				+ ", shipmentCost=" + shipmentCost + ", flag=" + flag + "]";
	}

}
