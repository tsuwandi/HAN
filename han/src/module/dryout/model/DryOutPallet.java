package module.dryout.model;

import java.io.Serializable;
import java.util.Date;

import module.pembelian.model.PalletCard;

public class DryOutPallet implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String dryOutCode;
	private String palletCardCode;
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

	public String getPalletCardCode() {
		return palletCardCode;
	}

	public void setPalletCardCode(String palletCardCode) {
		this.palletCardCode = palletCardCode;
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
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((dryOutCode == null) ? 0 : dryOutCode.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + ((palletCardCode == null) ? 0 : palletCardCode.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
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
		DryOutPallet other = (DryOutPallet) obj;
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
		if (palletCardCode == null) {
			if (other.palletCardCode != null)
				return false;
		} else if (!palletCardCode.equals(other.palletCardCode))
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
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DryOutPallet(id=" + id + ",dryOutCode=" + dryOutCode + ",palletCardCode=" + palletCardCode
				+ ",inputDate=" + inputDate + ",inputBy=" + inputBy + ",editDate=" + editDate + ",editedBy=" + editedBy
				+ ",deletedDate=" + deletedDate + ",deletedBy=" + deletedBy + ')';
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
