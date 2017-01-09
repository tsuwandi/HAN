package module.report.ui;

import javax.swing.JPanel;

import main.panel.MainPanel;

public class KDINReportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KDINReportPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		KDINReportDialog kdINReportDialog = new KDINReportDialog();
		kdINReportDialog.setTitle("Laporan KD IN");
		kdINReportDialog.setLocationRelativeTo(null);
		kdINReportDialog.setVisible(true);
	}
}
