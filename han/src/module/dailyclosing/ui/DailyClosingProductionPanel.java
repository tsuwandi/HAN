package module.dailyclosing.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.panel.MainPanel;

public class DailyClosingProductionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DailyClosingProductionPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk tutup harian ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {

			DailyClosingProductionDialog dailyClosingProductionDialog = new DailyClosingProductionDialog();
			dailyClosingProductionDialog.setTitle("Tutup Harian Produksi");
			dailyClosingProductionDialog.setLocationRelativeTo(null);
			dailyClosingProductionDialog.setVisible(true);

		}
	}
}
