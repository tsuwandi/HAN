package module.report.ui;

import javax.swing.JPanel;

import main.panel.MainPanel;

public class StockBalkenBasahReportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockBalkenBasahReportPanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();

		StockBalkenBasahReportDialog stockBalkenBasahReportDialog = new StockBalkenBasahReportDialog();
		stockBalkenBasahReportDialog.setTitle("Laporan Stock Balken Basah");
		stockBalkenBasahReportDialog.setLocationRelativeTo(null);
		stockBalkenBasahReportDialog.setVisible(true);
	}
}
