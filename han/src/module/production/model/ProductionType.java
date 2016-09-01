package module.production.model;

import module.util.ComboBoxProperties;

public class ProductionType implements ComboBoxProperties {
	int id;
	String productionTypeCode;
	String productionType;
	public String getProductionType() {
		return productionType;
	}

	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}
	String description;
	
	public ProductionType(){
		
	}
	
	public ProductionType(String productionType){
		this.productionType = productionType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductionTypeCode() {
		return productionTypeCode;
	}
	public void setProductionTypeCode(String productionTypeCode) {
		this.productionTypeCode = productionTypeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public Object getField() {
		return productionType;
	}
	
	
}
