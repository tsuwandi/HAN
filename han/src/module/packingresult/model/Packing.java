package module.packingresult.model;

import java.util.Date;
import java.util.List;

public class Packing {
	private int id;
	private Date packingDate;
	private String status;
	private String confirmCode;
	private Date confirmDate;
	private List<PackingResult> packingResults;
	private List<PackingRM> packingRMs;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPackingDate() {
		return packingDate;
	}
	public void setPackingDate(Date packingDate) {
		this.packingDate = packingDate;
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
	public List<PackingResult> getPackingResults() {
		return packingResults;
	}
	public void setPackingResults(List<PackingResult> packingResults) {
		this.packingResults = packingResults;
	}
	public List<PackingRM> getPackingRMs() {
		return packingRMs;
	}
	public void setPackingRMs(List<PackingRM> packingRMs) {
		this.packingRMs = packingRMs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
