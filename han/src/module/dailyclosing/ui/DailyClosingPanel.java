package module.dailyclosing.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.panel.MainPanel;

public class DailyClosingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DailyClosingPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk tutup harian ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {

			DailyClosingDialog dailyClosingDialog = new DailyClosingDialog();
			dailyClosingDialog.setTitle("Tutup Harian");
			dailyClosingDialog.setLocationRelativeTo(null);
			dailyClosingDialog.setVisible(true);

		}
	}
}
