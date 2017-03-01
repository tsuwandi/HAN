package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.NonRoutineAllowanceMasterType;
import module.personalia.model.Tax;

public class CreateNonRoutineAllowanceMasterTypePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<Tax> taxCmbox;
	private JTextField nonRoutineAllowanceField;
	private JTextField referenceDocumentField;

	public CreateNonRoutineAllowanceMasterTypePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Payroll > Master Jenis Tunjangan Non Rutin > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 430, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN MASTER JENIS TUNJANGAN NON RUTIN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 430, 25);
		add(lblHeader);
		// jenis
		JLabel lblJenisTunjanganNon = new JLabel("<html>Jenis Tunjangan Non Rutin</html>");
		lblJenisTunjanganNon.setBounds(30, 80, 100, 30);
		add(lblJenisTunjanganNon);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		nonRoutineAllowanceField = new JTextField();
		nonRoutineAllowanceField.setBounds(140, 80, 200, 30);
		add(nonRoutineAllowanceField);
		// pajak
		JLabel lblpajak = new JLabel("<html>Pajak</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		taxCmbox = new ComboBox<>();
		taxCmbox.setBounds(140, 120, 200, 30);
		add(taxCmbox);
		// dokumen referensi
		JLabel lbldokumenReferensi = new JLabel("<html>Dokumen Referensi</html>");
		lbldokumenReferensi.setBounds(30, 160, 100, 30);
		add(lbldokumenReferensi);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		referenceDocumentField = new JTextField();
		referenceDocumentField.setBounds(140, 160, 200, 30);
		add(referenceDocumentField);
		// save
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton btnAttach = new JButton("Attach");
		btnAttach.setBounds(250, 201, 90, 30);
		add(btnAttach);
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		getData();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				nonRoutineAllowanceField.requestFocusInWindow();
			}
		});
	}

	private void getData() {
		taxCmbox.setList(ServiceFactory.getPersonaliaBL().getTaxs(""));
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceMasterTypeConfigPanel");
	}

	protected void save() {
		NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType = new NonRoutineAllowanceMasterType();
		
		nonRoutineAllowanceMasterType.setTnrType(nonRoutineAllowanceField.getText());
		nonRoutineAllowanceMasterType.setTaxId(taxCmbox.getDataIndex().getId());
		nonRoutineAllowanceMasterType.setReferenceDocument(referenceDocumentField.getText());
		nonRoutineAllowanceMasterType.setInputDate(new Date());
		nonRoutineAllowanceMasterType.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		nonRoutineAllowanceMasterType.setEditDate(new Date());
		nonRoutineAllowanceMasterType.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());
		
		try {
			ServiceFactory.getPersonaliaBL().saveNonRoutineAllowanceMasterType(nonRoutineAllowanceMasterType);
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
			MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceMasterTypeConfigPanel");
		}
	}

	private void clear() {
		nonRoutineAllowanceField.setText("");
		taxCmbox.setSelectedIndex(0);
		referenceDocumentField.setText("");
	}
}