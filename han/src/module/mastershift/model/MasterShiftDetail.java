package module.mastershift.model;

public class MasterShiftDetail {
	int id;
	int shiftID;
	String day;
	int week;
	String in;
	String out;
	String holiday;
	int rest;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShiftID() {
		return shiftID;
	}
	public void setShiftID(int shiftID) {
		this.shiftID = shiftID;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public int getRest() {
		return rest;
	}
	public void setRest(int rest) {
		this.rest = rest;
	}
	@Override
	public String toString() {
		return "MasterShiftDetail [id=" + id + ", shiftID=" + shiftID + ", day=" + day + ", week=" + week + ", in=" + in
				+ ", out=" + out + ", holiday=" + holiday + ", rest=" + rest + "]";
	}
	
	
}
