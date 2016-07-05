package module.dryin.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import module.pembelian.model.PalletCard;
import module.sn.chamber.model.Chamber;

public class DryIn implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String dryInCode;
	private Timestamp dateIn;
	private int chamberId;
	private double totalVolume;
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

	public String getDryInCode() {
		return dryInCode;
	}

	public void setDryInCode(String dryInCode) {
		this.dryInCode = dryInCode;
	}

	public Timestamp getDateIn() {
		return dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

	public int getChamberId() {
		return chamberId;
	}

	public void setChamberId(int chamberId) {
		this.chamberId = chamberId;
	}

	public double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
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
		result = prime * result + chamberId;
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((dryInCode == null) ? 0 : dryInCode.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalVolume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		DryIn other = (DryIn) obj;
		if (chamberId != other.chamberId)
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
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
		if (dryInCode == null) {
			if (other.dryInCode != null)
				return false;
		} else if (!dryInCode.equals(other.dryInCode))
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
		if (Double.doubleToLongBits(totalVolume) != Double.doubleToLongBits(other.totalVolume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DryIn [id=" + id + ", dryInCode=" + dryInCode + ", dateIn=" + dateIn + ", chamberId=" + chamberId
				+ ", totalVolume=" + totalVolume + ", inputDate=" + inputDate + ", inputBy=" + inputBy + ", editDate="
				+ editDate + ", editedBy=" + editedBy + ", deletedDate=" + deletedDate + ", deletedBy=" + deletedBy
				+ "]";
	}

	private Chamber chamber;

	public Chamber getChamber() {
		if (chamber == null)
			chamber = new Chamber();
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		if (chamber == null)
			chamber = new Chamber();
		this.chamber = chamber;
	}

	private Date confirmDate;

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	private PalletCard palletCard;

	public PalletCard getPalletCard() {
		if (palletCard == null)
			palletCard = new PalletCard();
		return palletCard;
	}

	public void setPalletCard(PalletCard palletCard) {
		if (palletCard == null)
			palletCard = new PalletCard();
		this.palletCard = palletCard;
	}
}
