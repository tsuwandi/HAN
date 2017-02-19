package module.productionwaste.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;

public class ProductionWaste implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String pwCode;
	private Date productionDate;
	private String groupShiftCode;
	private String shiftCode;
	private String lineCode;
	private String productionTypeCode;
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
	
	List<ProductionResultWaste> productionResultWastes;
	Map<Integer, ProductionResultWaste> deletedProductResultWaste;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwCode() {
		return pwCode;
	}

	public void setPwCode(String pwCode) {
		this.pwCode = pwCode;
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

	public String getProductionTypeCode() {
		return productionTypeCode;
	}

	public void setProductionTypeCode(String productionTypeCode) {
		this.productionTypeCode = productionTypeCode;
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

	
	public List<ProductionResultWaste> getProductionResultWastes() {
		return productionResultWastes;
	}

	public void setProductionResultWastes(List<ProductionResultWaste> productionResultWastes) {
		this.productionResultWastes = productionResultWastes;
	}


	public Map<Integer, ProductionResultWaste> getDeletedProductResultWaste() {
		return deletedProductResultWaste;
	}

	public void setDeletedProductResultWaste(Map<Integer, ProductionResultWaste> deletedProductResultWaste) {
		this.deletedProductResultWaste = deletedProductResultWaste;
	}


	private GroupShift groupShift;
	private Line line;
	private Shift shift;
	private ProductionType productionType;

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

	public ProductionType getProductionType() {
		if(productionType == null)
			productionType = new ProductionType();
		return productionType;
	}

	public void setProductionType(ProductionType productionType) {
		if(productionType == null)
			productionType = new ProductionType();
		this.productionType = productionType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
