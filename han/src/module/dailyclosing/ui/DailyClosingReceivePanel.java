package module.dailyclosing.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.panel.MainPanel;

public class DailyClosingReceivePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DailyClosingReceivePanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk tutup harian ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {

			DailyClosingReceiveDialog dailyClosingReceiveDialog = new DailyClosingReceiveDialog();
			dailyClosingReceiveDialog.setTitle("Tutup Harian");
			dailyClosingReceiveDialog.setLocationRelativeTo(null);
			dailyClosingReceiveDialog.setVisible(true);

		}
	}
}
