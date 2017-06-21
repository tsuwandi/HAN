package module.salary.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.panel.MainPanel;

public class CalculateSalaryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculateSalaryPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghitung gaji ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			calculateSalary();
		}
	}
	
	private void calculateSalary(){
		
	}
}
