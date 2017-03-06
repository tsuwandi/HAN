package module.dryout.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import module.pembelian.model.PalletCard;
import module.sn.chamber.model.Chamber;

public class DryOut implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String dryOutCode;
	private Timestamp dateOut;
	private int chamberId;
	private BigDecimal totalVolume;
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

	public String getDryOutCode() {
		return dryOutCode;
	}

	public void setDryOutCode(String dryOutCode) {
		this.dryOutCode = dryOutCode;
	}

	public Timestamp getDateOut() {
		return dateOut;
	}

	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}

	public int getChamberId() {
		return chamberId;
	}

	public void setChamberId(int chamberId) {
		this.chamberId = chamberId;
	}

	public BigDecimal getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(BigDecimal totalVolume) {
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
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((dryOutCode == null) ? 0 : dryOutCode.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((totalVolume == null) ? 0 : totalVolume.hashCode());
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
		DryOut other = (DryOut) obj;
		if (chamberId != other.chamberId)
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
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
		if (dryOutCode == null) {
			if (other.dryOutCode != null)
				return false;
		} else if (!dryOutCode.equals(other.dryOutCode))
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
		if (totalVolume == null) {
			if (other.totalVolume != null)
				return false;
		} else if (!totalVolume.equals(other.totalVolume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DryOut [id=" + id + ", dryOutCode=" + dryOutCode + ", dateOut=" + dateOut + ", chamberId=" + chamberId
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

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
