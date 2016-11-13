package module.report.ui;

import javax.swing.JPanel;

import main.panel.MainPanel;

public class ReceivedReportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReceivedReportPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		ReceivedReportDialog receivedReportDialog = new ReceivedReportDialog();
		receivedReportDialog.setTitle("Form Parameter Laporan");
		receivedReportDialog.setLocationRelativeTo(null);
		receivedReportDialog.setVisible(true);
	}
}
