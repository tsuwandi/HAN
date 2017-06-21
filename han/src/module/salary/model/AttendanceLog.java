package module.salary.model;

import java.util.Date;

public class AttendanceLog {
	private int id;
	private int period;
	private int cycle;
	private String nik;
	private Date date;
	private String attendanceTime;
	private String attendanceOut;
	private int shiftId;
	private String shiftIn;
	private String shiftOut;
	private String statusIn;
	private String statusOut;
	private int lembur;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getAttendanceOut() {
		return attendanceOut;
	}
	public void setAttendanceOut(String attendanceOut) {
		this.attendanceOut = attendanceOut;
	}
	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftIn() {
		return shiftIn;
	}
	public void setShiftIn(String shiftIn) {
		this.shiftIn = shiftIn;
	}
	public String getShiftOut() {
		return shiftOut;
	}
	public void setShiftOut(String shiftOut) {
		this.shiftOut = shiftOut;
	}
	public String getStatusIn() {
		return statusIn;
	}
	public void setStatusIn(String statusIn) {
		this.statusIn = statusIn;
	}
	public String getStatusOut() {
		return statusOut;
	}
	public void setStatusOut(String statusOut) {
		this.statusOut = statusOut;
	}
	public int getLembur() {
		return lembur;
	}
	public void setLembur(int lembur) {
		this.lembur = lembur;
	}
}
