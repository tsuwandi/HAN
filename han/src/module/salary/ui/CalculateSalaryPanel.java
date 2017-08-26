package module.salary.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.mastershift.model.MasterShift;
import module.mastershift.model.MasterShiftDetail;
import module.personalia.model.Attendance;
import module.personalia.model.Employee;
import module.personalia.model.EmployeeR;
import module.salary.model.AttendanceLog;
import module.salary.model.ShiftTime;

public class CalculateSalaryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, EmployeeR> employeeMap;
	Map<Integer,MasterShift> shiftMap;
	int cycle=0;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	Map<String, Map<Date,Map<String,Attendance>>> attendanceMap;
	Map<Integer, ShiftTime> dailyDayMap = new HashMap<>();
	Map<Integer, ShiftTime> dailyHolidayMap = new HashMap<>();

	Map<Integer, ShiftTime> weeklyDayMap = new HashMap<>();
	Map<Integer, ShiftTime> weeklyHolidayMap = new HashMap<>();

	Map<Integer,Map<Integer,ShiftTime>> patternDayMap = new HashMap<>();
	Map<Integer, Map<Integer,ShiftTime>> patternHolidayMap = new HashMap<>();

	public CalculateSalaryPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghitung gaji ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			try {
				initData();
				calculateWorkDay();
				calculateHoliday();
				DialogBox.showInfo("Sukses Menghitung Gaji");
			} catch (Exception e) {
				DialogBox.showError("Gagal Menghitung Gaji:"+e.getMessage());
			}
			
		}
	}

	private void initData(){
		employeeMap = new HashMap<>();
		shiftMap = new HashMap<>();

		for(EmployeeR e : ServiceFactory.getPersonaliaBL().getAllEmployeeR()){
			employeeMap.put(e.getEmpCode(), e);
		}
		for(MasterShift s : ServiceFactory.getMasterShiftBL().getMasterShift()){
			shiftMap.put(s.getId(), s);
			makeShiftDetailMap(s);

		}
		cycle = Integer.valueOf(AppConstants.commonMap.get("salary_cycle"));


		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		if(c.get(Calendar.DAY_OF_MONTH)<cycle) c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, cycle+1);

		Calendar c2 = (Calendar)c.clone();
		c2.add(Calendar.MONTH, 1);
		c2.add(Calendar.DAY_OF_MONTH, 1);

		filterAttendance(c, c2);
		checkDataAndInsertAttendanceLog(c,c2);
	
	}

	private void makeShiftDetailMap(MasterShift s){
		if(s.getType().toUpperCase().equals("DAILY")){
			for(MasterShiftDetail msd : s.getMasterShiftDetails()){
				if(msd.getHoliday().equals("y")){
					String [] days = msd.getDay().split(",");
					for (String day : days) {
						ShiftTime st = new ShiftTime();
						st.setIn("00:00");
						st.setOut("00:00");
						dailyHolidayMap.put(Integer.valueOf(day), st);
					}
				}else{
					String [] days = msd.getDay().split(",");
					for (String day : days) {
						ShiftTime st = new ShiftTime();
						st.setIn(msd.getIn());
						st.setOut(msd.getOut());
						dailyDayMap.put(Integer.valueOf(day), st);
					}
				}
			}
		}else if(s.getType().toUpperCase().equals("WEEKLY")){

			for(MasterShiftDetail msd : s.getMasterShiftDetails()){
				if(msd.getHoliday().equals("y")){
					String [] days = msd.getDay().split(",");
					for (String day : days) {
						ShiftTime st = new ShiftTime();
						st.setIn("00:00");
						st.setOut("00:00");
						weeklyHolidayMap.put(getDayOfWeek(day), st);
					}
				}else{
					String [] days = msd.getDay().split(",");
					for (String day : days) {
						ShiftTime st = new ShiftTime();
						st.setIn(msd.getIn());
						st.setOut(msd.getOut());
						weeklyDayMap.put(getDayOfWeek(day), st);
					}
				}
			}
		}else if(s.getType().toUpperCase().equals("PATTERN")){
			for(MasterShiftDetail msd : s.getMasterShiftDetails()){
				if(msd.getHoliday().equals("y")){
					String [] days = msd.getDay().split(",");
					for (String day : days) {
						ShiftTime st = new ShiftTime();
						st.setIn("00:00");
						st.setOut("00:00");
						if(patternHolidayMap.get(msd.getWeek())==null){
							Map<Integer, ShiftTime> shiftMap = new HashMap<>();
							shiftMap.put(getDayOfWeek(day), st);
							patternHolidayMap.put(msd.getWeek(), shiftMap);
						}else{
							patternHolidayMap.get(msd.getWeek()).put(getDayOfWeek(day), st);
						}
					}
				}else{
					String [] days = msd.getDay().split(",");
					for (String day : days) {
						ShiftTime st = new ShiftTime();
						st.setIn(msd.getIn());
						st.setOut(msd.getOut());
						if(patternDayMap.get(msd.getWeek())==null){
							Map<Integer, ShiftTime> shiftMap = new HashMap<>();
							shiftMap.put(getDayOfWeek(day), st);
							patternDayMap.put(msd.getWeek(), shiftMap);
						}else{
							patternDayMap.get(msd.getWeek()).put(getDayOfWeek(day), st);
						}
					}
				}
			}
		}

	}

	private void checkDataAndInsertAttendanceLog(Calendar c, Calendar c2){
		Map<Date, Map<String,AttendanceLog>> attendanceLogMap = new HashMap<>();
		List<AttendanceLog> attendanceLogs = ServiceFactory.getCalculateSalaryBL()
				.getAttendanceLog(" AND attendance_date >='"+sdf.format(c.getTime())+"' AND attendance_date <='"+sdf.format(c2.getTime())+"'");
		if(attendanceLogs.size()>0){
			for(AttendanceLog attendanceLog : attendanceLogs){
				if(attendanceLogMap.get(attendanceLog.getDate())==null){
					Map<String, AttendanceLog> map = new HashMap<>();
					map.put(attendanceLog.getNik(), attendanceLog);
					attendanceLogMap.put(attendanceLog.getDate(), map);
				}else{
					if(attendanceLogMap.get(attendanceLog.getDate())==null){
						Map<String, AttendanceLog> map = new HashMap<>();
						map.put(attendanceLog.getNik(), attendanceLog);
					}else{
						attendanceLogMap.get(attendanceLog.getDate()).put(attendanceLog.getNik(), attendanceLog);
					}
					
				}
			}
		}
		try {
			while (!c.equals(c2)) {
				Date date = sdf.parse(sdf.format(c.getTime()));
				for (EmployeeR r : employeeMap.values()) {
					if(attendanceLogMap.get(date)!=null){
						if(attendanceLogMap.get(date).get(r.getEmpCode())==null){
							AttendanceLog attendanceLog = new AttendanceLog();
							attendanceLog.setDate(date);
							attendanceLog.setNik(r.getEmpCode());
							attendanceLog.setCycle(cycle);
							ServiceFactory.getCalculateSalaryBL().save(attendanceLog);
						}
					}else{
						AttendanceLog attendanceLog = new AttendanceLog();
						attendanceLog.setDate(date);
						attendanceLog.setNik(r.getEmpCode());
						attendanceLog.setCycle(cycle);
						ServiceFactory.getCalculateSalaryBL().save(attendanceLog);
					}
				}
				c.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			DialogBox.showError("Error Parsing Date :" +e.getMessage());
		}
	}

	private void filterAttendance(Calendar c , Calendar c2){
		attendanceMap = new HashMap<>();
		List<Attendance> attendances = ServiceFactory.getPersonaliaBL().getAttendances(" AND attendance_date >='"+sdf.format(c.getTime())+"' AND attendance_date <='"+sdf.format(c2.getTime())+"'");
		try {
			for (Attendance a : attendances) {
				if(attendanceMap.get(a.getNik().toString())==null){
					Map<Date, Map<String,Attendance>> dateMap = new HashMap<>();
					Map<String, Attendance> attTypeMap = new HashMap<>();
					attTypeMap.put(a.getMode().toUpperCase(), a);
					dateMap.put(a.getAttendanceDate(), attTypeMap);
					attendanceMap.put(a.getNik().toString(), dateMap);
				}else{
					if(attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate())==null){
						Map<String, Attendance> attTypeMap = new HashMap<>();
						attTypeMap.put(a.getMode().toUpperCase(), a);
						attendanceMap.get(a.getNik().toString()).put(a.getAttendanceDate(), attTypeMap);
					}else{
						if(attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get("SCAN MASUK")==null&&attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get("SCAN KELUAR")!=null){
							attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).put(a.getMode().toUpperCase(), a);
						}
						else if(attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get("SCAN KELUAR")==null&&attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get("SCAN MASUK")!=null){
							attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).put(a.getMode().toUpperCase(), a);
						}
						else if(attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get("SCAN MASUK")!=null||attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get("SCAN KELUAR")!=null){
							Date newTime = sdfTime.parse(a.getAttendanceTime());
							Date current = sdfTime.parse(attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).get(a.getMode().toUpperCase()).getAttendanceTime());
							if(a.getMode().toUpperCase().equals("SCAN MASUK")){
								if(newTime.before(current)){
									attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).put(a.getMode().toUpperCase(), a);
								}
							}else{
								if(newTime.after(current)){
									attendanceMap.get(a.getNik().toString()).get(a.getAttendanceDate()).put(a.getMode().toUpperCase(), a);
								}
							}	
						}


					}
				}
			}
		} catch (ParseException e) {
			DialogBox.showError("Gagal Menghitung Gaji:"+e.getMessage());
			e.printStackTrace();
		}
	}

	private void calculateHoliday(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		if(c.get(Calendar.DAY_OF_MONTH)<cycle) c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, cycle+1);

		Calendar c2 = (Calendar)c.clone();
		c2.add(Calendar.MONTH, 1);
		c2.add(Calendar.DAY_OF_MONTH, 1);
		
		List<AttendanceLog> attendanceLogs = ServiceFactory.getCalculateSalaryBL()
				.getAttendanceLog(" AND attendance_date >='"+sdf.format(c.getTime())+"' AND attendance_date <='"+sdf.format(c2.getTime())+"'");
		
		
		Calendar calendar = Calendar.getInstance();
		
		for(AttendanceLog attendanceLog : attendanceLogs){
			calendar.setTime(attendanceLog.getDate());
			if(employeeMap.get(attendanceLog.getNik())!=null){
				EmployeeR employeeR = employeeMap.get(attendanceLog.getNik());
				if(shiftMap.get(employeeR.getShiftId())!=null){
					MasterShift ms = shiftMap.get(employeeR.getShiftId());
					if(ms.getType().toUpperCase().equals("DAILY"))checkHolidayDaily(attendanceLog, calendar, ms.getId());
					else if(ms.getType().toUpperCase().equals("WEEKLY"))checkHolidayWeekly(attendanceLog, calendar, ms.getId());
					else checkHolidayPattern(attendanceLog, calendar, ms.getId());
					
				}
			}
		}
	}
	
	private void checkHolidayDaily(AttendanceLog attendanceLog, Calendar cal,int shiftID){
		if(dailyHolidayMap.get(cal.get(Calendar.DAY_OF_MONTH))!=null){
			ShiftTime sf = dailyHolidayMap.get(cal.get(Calendar.DAY_OF_MONTH));
			attendanceLog.setShiftId(shiftID);
			attendanceLog.setShiftIn(sf.getIn());
			attendanceLog.setShiftOut(sf.getOut());
			attendanceLog.setAttendanceTime(sf.getIn());
			attendanceLog.setAttendanceOut(sf.getOut());
			attendanceLog.setStatusIn("Libur");
			attendanceLog.setStatusOut("Libur");
			ServiceFactory.getCalculateSalaryBL().update(attendanceLog);
		}
	}
	private void checkHolidayWeekly(AttendanceLog attendanceLog, Calendar cal,int shiftID){
		if(weeklyHolidayMap.get(cal.get(Calendar.DAY_OF_WEEK))!=null){
			ShiftTime sf = weeklyHolidayMap.get(cal.get(Calendar.DAY_OF_WEEK));
			attendanceLog.setShiftId(shiftID);
			attendanceLog.setShiftIn(sf.getIn());
			attendanceLog.setShiftOut(sf.getOut());
			attendanceLog.setAttendanceTime(sf.getIn());
			attendanceLog.setAttendanceOut(sf.getOut());
			attendanceLog.setStatusIn("Libur");
			attendanceLog.setStatusOut("Libur");
			ServiceFactory.getCalculateSalaryBL().update(attendanceLog);
		}
	}
	private void checkHolidayPattern(AttendanceLog attendanceLog, Calendar cal,int shiftID){
		if(patternHolidayMap.get(cal.get(Calendar.DAY_OF_WEEK))!=null){
			if(patternHolidayMap.get(cal.get(Calendar.WEEK_OF_MONTH)).get(cal.get(Calendar.DAY_OF_WEEK))!=null){
				ShiftTime sf = patternHolidayMap.get(cal.get(Calendar.WEEK_OF_MONTH)).get(cal.get(Calendar.DAY_OF_WEEK));
				attendanceLog.setShiftId(shiftID);
				attendanceLog.setShiftIn(sf.getIn());
				attendanceLog.setAttendanceTime(sf.getIn());
				attendanceLog.setAttendanceOut(sf.getOut());
				attendanceLog.setShiftOut(sf.getOut());
				attendanceLog.setStatusIn("Libur");
				attendanceLog.setStatusOut("Libur");
				ServiceFactory.getCalculateSalaryBL().update(attendanceLog);
			}
				
		}
	}

	private void calculateWorkDay(){
		try {
			for(Map.Entry<String, Map<Date, Map<String,Attendance>>> attendanceData : attendanceMap.entrySet()){
				EmployeeR r = employeeMap.get(attendanceData.getKey());
				MasterShift ms = shiftMap.get(r.getShiftId());
				if(ms.getType().toUpperCase().equals("DAILY")){
					Calendar cal = Calendar.getInstance();
					for(Map.Entry<Date, Map<String,Attendance>> dayData : attendanceData.getValue().entrySet()){
					 	cal.setTime(dayData.getKey());
						AttendanceLog aLog = new AttendanceLog();
						aLog.setDate(dayData.getKey());
						aLog.setNik(r.getEmpCode());
						ShiftTime shiftTime = new ShiftTime();
						if(dailyDayMap.get(cal.get(Calendar.DAY_OF_MONTH))!=null){
							shiftTime = dailyDayMap.get(cal.get(Calendar.DAY_OF_MONTH));
							aLog.setShiftIn(shiftTime.getIn());
							aLog.setShiftOut(shiftTime.getOut());
							
							aLog.setShiftId(ms.getId());

							Date timeIn = sdfTime.parse(shiftTime.getIn());
							Date timeOut = sdfTime.parse(shiftTime.getOut());
							for(Map.Entry<String, Attendance> attTypeData : dayData.getValue().entrySet()){
								if(attTypeData.getValue().getMode().toUpperCase().equals("SCAN MASUK")){
									Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
									if(actualTime.after(timeIn)){
										aLog.setStatusIn("Telat");
									}else{
										aLog.setStatusIn("Masuk");	
									}
									aLog.setAttendanceTime(attTypeData.getValue().getAttendanceTime());
								}else{
									Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
									if(actualTime.before(timeOut)){
										aLog.setStatusOut("Telat");
									}else{
										aLog.setStatusOut("Keluar");	
									}
									aLog.setAttendanceOut(attTypeData.getValue().getAttendanceTime());
								}
							}

							ServiceFactory.getCalculateSalaryBL().updateByDateAndNik(aLog);
						}				
					}

				}else if(ms.getType().toUpperCase().equals("WEEKLY")){
					Calendar cal = Calendar.getInstance();
					for(Map.Entry<Date, Map<String,Attendance>> dayData : attendanceData.getValue().entrySet()){
						cal.setTime(dayData.getKey());
						AttendanceLog aLog = new AttendanceLog();
						aLog.setDate(dayData.getKey());
						aLog.setNik(r.getEmpCode());
						ShiftTime shiftTime = new ShiftTime();
						if(weeklyDayMap.get(cal.get(Calendar.DAY_OF_WEEK))!=null){
							shiftTime = weeklyDayMap.get(cal.get(Calendar.DAY_OF_WEEK));
							aLog.setShiftIn(shiftTime.getIn());
							aLog.setShiftOut(shiftTime.getOut());
							aLog.setShiftId(ms.getId());

							Date timeIn = sdfTime.parse(shiftTime.getIn());
							Date timeOut = sdfTime.parse(shiftTime.getOut());
							for(Map.Entry<String, Attendance> attTypeData : dayData.getValue().entrySet()){
								if(attTypeData.getValue().getMode().toUpperCase().equals("SCAN MASUK")){
									Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
									if(actualTime.after(timeIn)){
										aLog.setStatusIn("Telat");
									}else{
										aLog.setStatusIn("Masuk");	
									}
									aLog.setAttendanceTime(attTypeData.getValue().getAttendanceTime());
								}else{
									Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
									if(actualTime.before(timeOut)){
										aLog.setStatusOut("Telat");
									}else{
										aLog.setStatusOut("Keluar");	
									}

									aLog.setAttendanceOut(attTypeData.getValue().getAttendanceTime());
								}
							}

							ServiceFactory.getCalculateSalaryBL().updateByDateAndNik(aLog);
							
						}
						
						
					}
				}else if(ms.getType().toUpperCase().equals("PATTERN")){
					Calendar cal = Calendar.getInstance();
					for(Map.Entry<Date, Map<String,Attendance>> dayData : attendanceData.getValue().entrySet()){
						cal.setTime(dayData.getKey());
						AttendanceLog aLog = new AttendanceLog();
						aLog.setDate(dayData.getKey());
						aLog.setNik(r.getEmpCode());
						ShiftTime shiftTime = new ShiftTime();
						if(patternDayMap.get(cal.get(Calendar.WEEK_OF_MONTH))!=null){
							if(patternDayMap.get(cal.get(Calendar.WEEK_OF_MONTH)).get(Calendar.DAY_OF_WEEK)!=null){
								shiftTime = patternDayMap.get(cal.get(Calendar.WEEK_OF_MONTH)).get(Calendar.DAY_OF_WEEK);
								aLog.setShiftIn(shiftTime.getIn());
								aLog.setShiftOut(shiftTime.getOut());
								aLog.setShiftId(ms.getId());

								Date timeIn = sdfTime.parse(shiftTime.getIn());
								Date timeOut = sdfTime.parse(shiftTime.getOut());
								for(Map.Entry<String, Attendance> attTypeData : dayData.getValue().entrySet()){
									if(attTypeData.getValue().getMode().toUpperCase().equals("SCAN MASUK")){
										Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
										if(actualTime.after(timeIn)){
											aLog.setStatusIn("Telat");
										}else{
											aLog.setStatusIn("Masuk");	
										}
										aLog.setAttendanceTime(attTypeData.getValue().getAttendanceTime());
									}else{
										Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
										if(actualTime.before(timeOut)){
											aLog.setStatusOut("Telat");
										}else{
											aLog.setStatusOut("Keluar");	
										}

										aLog.setAttendanceOut(attTypeData.getValue().getAttendanceTime());
									}
								}

								ServiceFactory.getCalculateSalaryBL().updateByDateAndNik(aLog);
							}
						}
					}
				}

			}
		} catch (ParseException e) {
			DialogBox.showError("Gagal Menghitung Gaji:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private int getDayOfWeek(String day){
		int dow = 0;
		switch (day) {
		case "Senin":
			 dow = Calendar.MONDAY;
			break;
		case "Selasa":
			 dow = Calendar.TUESDAY;
			break;
		case "Rabu":
			 dow = Calendar.WEDNESDAY;
			break;
		case "Kamis":
			 dow = Calendar.THURSDAY;
			break;
		case "Jumat":
			 dow = Calendar.FRIDAY;
			break;
		case "Sabtu":
			 dow = Calendar.SATURDAY;
			break;
		case "Minggu":
			 dow = Calendar.SUNDAY;
			break;

		default:
			break;
		}
		
		return dow;
		
	}
}
