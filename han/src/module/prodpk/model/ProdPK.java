package module.prodpk.model;

import java.io.Serializable;
import java.util.Date;

import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;

public class ProdPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String prodPKCode;
	private Date productionDate;
	private String groupShiftCode;
	private String shiftCode;
	private String lineCode;
	private String status;
	private String confirmCode;
	private Date confirmDate;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProdPKCode() {
		return prodPKCode;
	}

	public void setProdPKCode(String prodPKCode) {
		this.prodPKCode = prodPKCode;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getGroupShiftCode() {
		return groupShiftCode;
	}

	public void setGroupShiftCode(String groupShiftCode) {
		this.groupShiftCode = groupShiftCode;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
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
		result = prime * result + ((confirmCode == null) ? 0 : confirmCode.hashCode());
		result = prime * result + ((confirmDate == null) ? 0 : confirmDate.hashCode());
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + ((groupShiftCode == null) ? 0 : groupShiftCode.hashCode());
		result = prime * result + id;
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((lineCode == null) ? 0 : lineCode.hashCode());
		result = prime * result + ((productionDate == null) ? 0 : productionDate.hashCode());
		result = prime * result + ((prodPKCode == null) ? 0 : prodPKCode.hashCode());
		result = prime * result + ((shiftCode == null) ? 0 : shiftCode.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ProdPK other = (ProdPK) obj;
		if (confirmCode == null) {
			if (other.confirmCode != null)
				return false;
		} else if (!confirmCode.equals(other.confirmCode))
			return false;
		if (confirmDate == null) {
			if (other.confirmDate != null)
				return false;
		} else if (!confirmDate.equals(other.confirmDate))
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
		if (groupShiftCode == null) {
			if (other.groupShiftCode != null)
				return false;
		} else if (!groupShiftCode.equals(other.groupShiftCode))
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
		if (lineCode == null) {
			if (other.lineCode != null)
				return false;
		} else if (!lineCode.equals(other.lineCode))
			return false;
		if (productionDate == null) {
			if (other.productionDate != null)
				return false;
		} else if (!productionDate.equals(other.productionDate))
			return false;
		if (prodPKCode == null) {
			if (other.prodPKCode != null)
				return false;
		} else if (!prodPKCode.equals(other.prodPKCode))
			return false;
		if (shiftCode == null) {
			if (other.shiftCode != null)
				return false;
		} else if (!shiftCode.equals(other.shiftCode))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductionWaste [id=" + id + ", prodPKCode=" + prodPKCode + ", productionDate=" + productionDate
				+ ", groupShiftCode=" + groupShiftCode + ", shiftCode=" + shiftCode + ", lineCode=" + lineCode
				+ ", status=" + status + ", confirmCode=" + confirmCode
				+ ", confirmDate=" + confirmDate + ", inputDate=" + inputDate + ", inputBy=" + inputBy + ", editDate="
				+ editDate + ", editedBy=" + editedBy + ", deletedDate=" + deletedDate + ", deletedBy=" + deletedBy
				+ "]";
	}
	
	private GroupShift groupShift;
	private Line line;
	private Shift shift;

	public GroupShift getGroupShift() {
		if(groupShift == null)
			groupShift = new GroupShift();
		return groupShift;
	}

	public void setGroupShift(GroupShift groupShift) {
		if(groupShift == null)
			groupShift = new GroupShift();
		this.groupShift = groupShift;
	}

	public Line getLine() {
		if(line == null)
			line = new Line();
		return line;
	}

	public void setLine(Line line) {
		if(line == null)
			line = new Line();
		this.line = line;
	}

	public Shift getShift() {
		if(shift == null)
			shift = new Shift();
		return shift;
	}

	public void setShift(Shift shift) {
		if(shift == null)
			shift = new Shift();
		this.shift = shift;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
