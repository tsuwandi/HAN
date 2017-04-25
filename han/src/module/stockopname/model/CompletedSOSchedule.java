package module.stockopname.model;

import java.util.Date;

public class CompletedSOSchedule {
	int id;
	int setSOScheduleID;
	Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSetSOScheduleID() {
		return setSOScheduleID;
	}
	public void setSetSOScheduleID(int setSOScheduleID) {
		this.setSOScheduleID = setSOScheduleID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CompletedSOSchedule [id=" + id + ", setSOScheduleID=" + setSOScheduleID + ", date=" + date + "]";
	}
	
	
	
}
