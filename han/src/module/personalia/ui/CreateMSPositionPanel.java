package module.personalia.ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateMSPositionPanel extends JPanel {

	private static final long serialVersionUID = -7130979348188499012L;

	public CreateMSPositionPanel() {

		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Jabatan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN JABATAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 180, 25);
		add(lblHeader);
	}
}
