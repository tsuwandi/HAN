package module.sendtofinance.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.panel.MainPanel;

public class SendToFinancePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public SendToFinancePanel() {
		MainPanel.bodyPanel.removeAll();
		MainPanel.bodyPanel.revalidate();
		MainPanel.bodyPanel.repaint();
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk send to finance ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			SendToFinanceDialog sendToFinanceDialog = new SendToFinanceDialog();
			sendToFinanceDialog.setTitle("Send To Finance");
			sendToFinanceDialog.setLocationRelativeTo(null);
			sendToFinanceDialog.setVisible(true);
		}
	}
}
