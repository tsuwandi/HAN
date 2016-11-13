package module.report.model;

import java.util.Date;

public class DryStockFlow {
	private Date startFrom;
	private Date endTo;
	private String userName;
	private String generateDate;
	private String generateTime;
	
	public Date getStartFrom() {
		return startFrom;
	}
	public void setStartFrom(Date startFrom) {
		this.startFrom = startFrom;
	}
	public Date getEndTo() {
		return endTo;
	}
	public void setEndTo(Date endTo) {
		this.endTo = endTo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGenerateDate() {
		return generateDate;
	}
	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
}
