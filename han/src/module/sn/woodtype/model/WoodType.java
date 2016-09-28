package module.sn.woodtype.model;

import java.io.Serializable;
import java.util.Date;

import module.sn.woodgenus.model.WoodGenus;
import module.util.ComboBoxProperties;

public class WoodType implements Serializable, ComboBoxProperties {

	private static final long serialVersionUID = 1L;

	private int id;
	private String woodType;
	private int woodGenusId;
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

	public String getWoodType() {
		return woodType;
	}

	public void setWoodType(String woodType) {
		this.woodType = woodType;
	}

	public int getWoodGenusId() {
		return woodGenusId;
	}

	public void setWoodGenusId(int woodGenusId) {
		this.woodGenusId = woodGenusId;
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
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + woodGenusId;
		result = prime * result + ((woodType == null) ? 0 : woodType.hashCode());
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
		WoodType other = (WoodType) obj;
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
		if (woodGenusId != other.woodGenusId)
			return false;
		if (woodType == null) {
			if (other.woodType != null)
				return false;
		} else if (!woodType.equals(other.woodType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WoodType [id=" + id + ", woodType=" + woodType + ", woodGenusId=" + woodGenusId + ", inputDate="
				+ inputDate + ", inputBy=" + inputBy + ", editDate=" + editDate + ", editedBy=" + editedBy
				+ ", deletedDate=" + deletedDate + ", deletedBy=" + deletedBy + "]";
	}

	@Override
	public Object getField() {
		return woodType;
	}

	public WoodType() {

	}

	public WoodType(String woodType) {
		this.woodType = woodType;
	}
	
	public WoodGenus getWoodGenus() {
		if(woodGenus == null)
			woodGenus = new WoodGenus();
		return woodGenus;
	}

	public void setWoodGenus(WoodGenus woodGenus) {
		if(woodGenus == null)
			woodGenus = new WoodGenus();
		this.woodGenus = woodGenus;
	}

	private WoodGenus woodGenus;
	

}
