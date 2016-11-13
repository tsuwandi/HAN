package module.report.ui;

import javax.swing.JPanel;

import main.panel.MainPanel;

public class DryStokFlowReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DryStokFlowReportPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		DryStokFlowReportDialog dryStokFlowReportDialog = new DryStokFlowReportDialog();
		dryStokFlowReportDialog.setTitle("Form Parameter Laporan");
		dryStokFlowReportDialog.setLocationRelativeTo(null);
		dryStokFlowReportDialog.setVisible(true);
	}

}
