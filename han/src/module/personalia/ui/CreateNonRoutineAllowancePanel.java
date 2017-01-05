package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Division;

public class CreateNonRoutineAllowancePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField divisionNameField;
	private JTextField divisionIdField;
	private JTextField textField;

	public CreateNonRoutineAllowancePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Master Jenis Tunjangan Non Rutin > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN MASTER JENIS TUNJANGAN NON RUTIN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		
		JLabel lblJenisTunjanganNon = new JLabel("<html>Jenis Tunjangan Non Rutin</html>");
		lblJenisTunjanganNon.setBounds(30, 80, 100, 30);
		add(lblJenisTunjanganNon);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		JLabel lblpajak = new JLabel("<html>Pajak</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		divisionNameField = new JTextField();
		divisionNameField.setBounds(140, 120, 200, 30);
		add(divisionNameField);
		
		divisionIdField = new JTextField();
		divisionIdField.setBounds(140, 80, 200, 30);
		divisionIdField.setEditable(false);
		divisionIdField.setEnabled(false);
		add(divisionIdField);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JLabel lbldokumenReferensi = new JLabel("<html>Dokumen Referensi</html>");
		lbldokumenReferensi.setBounds(30, 161, 100, 30);
		add(lbldokumenReferensi);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 161, 10, 30);
		add(label_2);
		
		textField = new JTextField();
		textField.setBounds(140, 161, 200, 30);
		add(textField);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		getLastID();
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("DIV");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		divisionIdField.setText(lastId.toString());
	}

	protected void save() {
		Division division = new Division();
		division.setId(divisionIdField.getText());
		division.setName(divisionNameField.getText());
		division.setInputDate(new Date());
		division.setInputBy("");
		division.setEditDate(new Date());
		division.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().saveDivision(division);
			option();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
		
	}

	private void option() {
		if (DialogBox.showAfterChoiceInsert()==0) {
			clear();
		} else {
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		divisionNameField.setText("");
	}
}