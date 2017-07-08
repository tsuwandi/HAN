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
	
	public CalculateSalaryPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		
		checkDataAndInsertAttendanceLog();
		
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghitung gaji ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			calculateSalary();
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
		}
		cycle = Integer.valueOf(AppConstants.commonMap.get("salary_cycle"));
	}
	
	public void checkDataAndInsertAttendanceLog(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, cycle+1);
		
		Calendar c2 = (Calendar)c.clone();
		c2.add(Calendar.MONTH, 1);
		c2.add(Calendar.DAY_OF_MONTH, 1);
		
		Map<Date, Map<String,AttendanceLog>> attendanceLogMap = new HashMap<>();
		List<AttendanceLog> attendanceLogs = ServiceFactory.getCalculateSalaryBL()
				.getAttendanceLog(" AND attendance_date >='"+sdf.format(c.getTime()+"' AND attendance_date <='"+sdf.format(c2.getTime()+"'")));

		filterAttendance(c, c2);
		
		if(attendanceLogs.size()>0){
			for(AttendanceLog attendanceLog : attendanceLogs){
				if(attendanceLogMap.get(attendanceLog.getDate())!=null){
					Map<String, AttendanceLog> map = new HashMap<>();
					map.put(attendanceLog.getNik(), attendanceLog);
					attendanceLogMap.put(attendanceLog.getDate(), map);
				}else{
					attendanceLogMap.get(attendanceLog.getDate()).put(attendanceLog.getNik(), attendanceLog);
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
		List<Attendance> attendances = ServiceFactory.getPersonaliaBL().getAttendances(" AND attendance_date >='"+sdf.format(c.getTime()+"' AND attendance_date <='"+sdf.format(c2.getTime()+"'")));
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
			e.printStackTrace();
		}
	}
	
	
	
	private void calculateSalary(){
		for(Map.Entry<String, Map<Date, Map<String,Attendance>>> attendanceData : attendanceMap.entrySet()){
			EmployeeR r = employeeMap.get(attendanceData.getKey());
			
			MasterShift ms = shiftMap.get(r.getShiftId());
			if(ms.getType().toUpperCase().equals("DAILY")){
				Map<Integer, ShiftTime> dayMap = new HashMap<>();
				Map<Integer, ShiftTime> holidayMap = new HashMap<>();
				for(MasterShiftDetail msd : ms.getMasterShiftDetails()){
					if(msd.getHoliday().equals("y")){
						String [] days = msd.getDay().split(",");
						for (String s : days) {
							ShiftTime st = new ShiftTime();
							st.setIn("00:00");
							st.setOut("00:00");
							holidayMap.put(Integer.valueOf(s), st);
						}
					}else{
						String [] days = msd.getDay().split(",");
						for (String s : days) {
							ShiftTime st = new ShiftTime();
							st.setIn(msd.getIn());
							st.setOut(msd.getOut());
							dayMap.put(Integer.valueOf(s), st);
						}
					}
				}
				
				Calendar cal = Calendar.getInstance();
				for(Map.Entry<Date, Map<String,Attendance>> dayData : attendanceData.getValue().entrySet()){
					cal.setTime(dayData.getKey());
					AttendanceLog aLog = new AttendanceLog();
					aLog.setDate(dayData.getKey());
					aLog.setNik(r.getEmpCode());
					ShiftTime shiftTime = new ShiftTime();
					if(dayMap.get(cal.get(Calendar.DAY_OF_MONTH))!=null){
						shiftTime = dayMap.get(cal.get(Calendar.DAY_OF_MONTH));
					}else if(holidayMap.get(cal.get(Calendar.DAY_OF_MONTH))!=null){
						shiftTime = holidayMap.get(cal.get(Calendar.DAY_OF_MONTH));
					}
					
					aLog.setShiftIn(shiftTime.getIn());
					aLog.setShiftOut(shiftTime.getOut());
					try {
						Date timeIn = sdfTime.parse(shiftTime.getIn());
						Date timeOut = sdfTime.parse(shiftTime.getOut());
						for(Map.Entry<String, Attendance> attTypeData : dayData.getValue().entrySet()){
							if(attTypeData.getValue().getMode().toUpperCase().equals("SCAN MASUK")){
								Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
								if(actualTime.after(timeIn)){
									aLog.setStatusIn("Absen Setelah Shift Masuk");
								}else{
									aLog.setStatusIn("Masuk");	
								}
								aLog.setAttendanceTime(attTypeData.getValue().getAttendanceTime());
							}else{
								Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
								if(actualTime.before(timeOut)){
									aLog.setStatusIn("Absen Sebelum Shift Pulang Selesai");
								}else{
									aLog.setStatusIn("Absen Keluar");	
								}
								aLog.setAttendanceOut(attTypeData.getValue().getAttendanceTime());
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					ServiceFactory.getCalculateSalaryBL().update(aLog);
				}
				
			}else if(ms.getType().toUpperCase().equals("WEEKLY")){
				Map<String, ShiftTime> dayMap = new HashMap<>();
				Map<String, ShiftTime> holidayMap = new HashMap<>();
				for(MasterShiftDetail msd : ms.getMasterShiftDetails()){
					if(msd.getHoliday().equals("y")){
						String [] days = msd.getDay().split(",");
						for (String s : days) {
							ShiftTime st = new ShiftTime();
							st.setIn("00:00");
							st.setOut("00:00");
							holidayMap.put(s, st);
						}
					}else{
						String [] days = msd.getDay().split(",");
						for (String s : days) {
							ShiftTime st = new ShiftTime();
							st.setIn(msd.getIn());
							st.setOut(msd.getOut());
							dayMap.put(s, st);
						}
					}
				}
				
				Calendar cal = Calendar.getInstance();
				for(Map.Entry<Date, Map<String,Attendance>> dayData : attendanceData.getValue().entrySet()){
					cal.setTime(dayData.getKey());
					AttendanceLog aLog = new AttendanceLog();
					aLog.setDate(dayData.getKey());
					aLog.setNik(r.getEmpCode());
					ShiftTime shiftTime = new ShiftTime();
					if(dayMap.get(cal.get(Calendar.DAY_OF_MONTH))!=null){
						shiftTime = dayMap.get(cal.get(Calendar.DAY_OF_MONTH));
					}else if(holidayMap.get(cal.get(Calendar.DAY_OF_MONTH))!=null){
						shiftTime = holidayMap.get(cal.get(Calendar.DAY_OF_MONTH));
					}
					
					aLog.setShiftIn(shiftTime.getIn());
					aLog.setShiftOut(shiftTime.getOut());
					try {
						Date timeIn = sdfTime.parse(shiftTime.getIn());
						Date timeOut = sdfTime.parse(shiftTime.getOut());
						for(Map.Entry<String, Attendance> attTypeData : dayData.getValue().entrySet()){
							if(attTypeData.getValue().getMode().toUpperCase().equals("SCAN MASUK")){
								Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
								if(actualTime.after(timeIn)){
									aLog.setStatusIn("Absen Setelah Shift Masuk");
								}else{
									aLog.setStatusIn("Masuk");	
								}
								if(shiftTime.getIn().equals("00:00")){
									aLog.setStatusIn("Libur");
								}
								aLog.setAttendanceTime(attTypeData.getValue().getAttendanceTime());
							}else{
								Date actualTime = sdfTime.parse(attTypeData.getValue().getAttendanceTime());
								if(actualTime.before(timeOut)){
									aLog.setStatusIn("Absen Sebelum Shift Pulang Selesai");
								}else{
									aLog.setStatusIn("Absen Keluar");	
								}
								if(shiftTime.getIn().equals("00:00")){
									aLog.setStatusIn("Libur");
								}
								aLog.setAttendanceOut(attTypeData.getValue().getAttendanceTime());
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					ServiceFactory.getCalculateSalaryBL().update(aLog);
				}
			}
			
		}
		
		
		
		
	}
}
