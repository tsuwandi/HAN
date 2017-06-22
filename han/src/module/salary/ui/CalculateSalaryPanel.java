package module.salary.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.mastershift.model.MasterShift;
import module.personalia.model.Employee;

public class CalculateSalaryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Employee> employeeMap;
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
		
		for(Employee e : ServiceFactory.getPersonaliaBL().getEmployees("")){
			employeeMap.put(e.getEmpCode(), e);
		}
		for(MasterShift s : ServiceFactory.getMasterShiftBL().getMasterShift()){
			shiftMap.put(s.getId(), s);
		}
	}
	
	private void calculateSalary(){
		
	}
}
