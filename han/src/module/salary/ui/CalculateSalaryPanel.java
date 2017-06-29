package module.salary.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.mastershift.model.MasterShift;
import module.mastershift.model.MasterShiftDetail;
import module.personalia.model.Employee;
import module.personalia.model.EmployeeR;
import module.salary.model.ShiftTime;

public class CalculateSalaryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, EmployeeR> employeeMap;
	Map<Integer,MasterShift> shiftMap;
	
	public CalculateSalaryPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		
		initData();
		
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
	}
	
	private void calculateSalary(){
		MasterShift ms = shiftMap.get(1);
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
		}
		
		if(ms.getType().toUpperCase().equals("DAILY")){
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
		}
		
	}
}
