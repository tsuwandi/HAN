package module.production.model;

import java.util.Date;

public class ProdRM {
	boolean flag=false;
	int id;
	String productionCode;
	String palletCardCode;
	double length;
	double width;
	double thick;
	int log;
	double volume;
	Date dateDryOut;
	
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	public String getPalletCardCode() {
		return palletCardCode;
	}
	public void setPalletCardCode(String palletCardCode) {
		this.palletCardCode = palletCardCode;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getThick() {
		return thick;
	}
	public void setThick(double thick) {
		this.thick = thick;
	}
	public int getLog() {
		return log;
	}
	public void setLog(int log) {
		this.log = log;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Date getDateDryOut() {
		return dateDryOut;
	}
	public void setDateDryOut(Date dateDryOut) {
		this.dateDryOut = dateDryOut;
	}
	
	

}
