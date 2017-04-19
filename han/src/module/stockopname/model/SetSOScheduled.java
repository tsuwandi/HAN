package module.stockopname.model;

import java.util.List;

public class SetSOScheduled {
	int id;
	String soName;
	String reccurence;
	String day;
	int date;
	String soType;
	List<SetSoScheduledProduct> setSoScheduledProducts;
	List<SetSoScheduledProduct> deletedProducts;
	
	
	public List<SetSoScheduledProduct> getDeletedProducts() {
		return deletedProducts;
	}
	public void setDeletedProducts(List<SetSoScheduledProduct> deletedProducts) {
		this.deletedProducts = deletedProducts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSoName() {
		return soName;
	}
	public void setSoName(String soName) {
		this.soName = soName;
	}
	public String getReccurence() {
		return reccurence;
	}
	public void setReccurence(String reccurence) {
		this.reccurence = reccurence;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getSoType() {
		return soType;
	}
	public void setSoType(String soType) {
		this.soType = soType;
	}
	public List<SetSoScheduledProduct> getSetSoScheduledProducts() {
		return setSoScheduledProducts;
	}
	public void setSetSoScheduledProducts(List<SetSoScheduledProduct> setSoScheduledProducts) {
		this.setSoScheduledProducts = setSoScheduledProducts;
	}
	
	
}
