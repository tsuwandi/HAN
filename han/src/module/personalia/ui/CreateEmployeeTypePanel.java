package module.personalia.ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateEmployeeTypePanel extends JPanel {

	private static final long serialVersionUID = -7395785136651915827L;

	public CreateEmployeeTypePanel() {

		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Tipe Karyawan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN TIPE KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 180, 25);
		add(lblHeader);
	}
}
