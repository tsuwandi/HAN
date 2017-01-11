package module.report.ui;

import javax.swing.JPanel;

import main.panel.MainPanel;

public class KDOUTReportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KDOUTReportPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		KDOUTReportDialog kdOUTReportDialog = new KDOUTReportDialog();
		kdOUTReportDialog.setTitle("Laporan KD OUT");
		kdOUTReportDialog.setLocationRelativeTo(null);
		kdOUTReportDialog.setVisible(true);
	}
}
