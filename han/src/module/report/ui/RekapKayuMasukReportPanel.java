package module.report.ui;

import javax.swing.JPanel;

import main.panel.MainPanel;

public class RekapKayuMasukReportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RekapKayuMasukReportPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		RekapKayuMasukReportDialog receivedReportDialog = new RekapKayuMasukReportDialog();
		receivedReportDialog.setTitle("Laporan Rekap Kayu Masuk");
		receivedReportDialog.setLocationRelativeTo(null);
		receivedReportDialog.setVisible(true);
	}
}
