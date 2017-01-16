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
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.NonRoutineAllowanceMaster;
import module.personalia.model.NonRoutineAllowanceMasterType;

public class CreateNonRoutineAllowanceMasterPanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypeCmbox;
	private JTextField nonRoutineAllowanceMasterField;
	private JTextField taxField;
	private JTextField referenceDocumentField;

	public CreateNonRoutineAllowanceMasterPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Master Tunjangan Non Rutin > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 430, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN MASTER TUNJANGAN NON RUTIN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 430, 25);
		add(lblHeader);
		// jenis
		JLabel lblJenisTunjanganNon = new JLabel("<html>Tunjangan Non Rutin</html>");
		lblJenisTunjanganNon.setBounds(30, 80, 100, 30);
		add(lblJenisTunjanganNon);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		nonRoutineAllowanceMasterField = new JTextField();
		nonRoutineAllowanceMasterField.setBounds(140, 80, 200, 30);
		add(nonRoutineAllowanceMasterField);
		// pajak
		JLabel lblpajak = new JLabel("<html>Jenis Tunjangan Non Rutin</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		nonRoutineAllowanceMasterTypeCmbox = new ComboBox<>();
		nonRoutineAllowanceMasterTypeCmbox.setBounds(140, 120, 200, 30);
		add(nonRoutineAllowanceMasterTypeCmbox);
		
		nonRoutineAllowanceMasterTypeCmbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTax();
			}
		});
		// dokumen referensi
		JLabel lbldokumenReferensi = new JLabel("<html>Pajak</html>");
		lbldokumenReferensi.setBounds(30, 160, 100, 30);
		add(lbldokumenReferensi);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		taxField = new JTextField();
		taxField.setEnabled(false);
		taxField.setEditable(false);
		taxField.setBounds(140, 160, 200, 30);
		add(taxField);
		// save
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
		JLabel label = new JLabel("<html>Dokumen Referensi</html>");
		label.setBounds(30, 201, 100, 30);
		add(label);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 201, 10, 30);
		add(label_4);
		
		referenceDocumentField = new JTextField();
		referenceDocumentField.setBounds(140, 201, 200, 30);
		add(referenceDocumentField);
		
		JButton button = new JButton("Attach");
		button.setBounds(250, 242, 90, 30);
		add(button);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		getData();
	}

	protected void updateTax() {
		taxField.setText(nonRoutineAllowanceMasterTypeCmbox.getDataIndex().getTax().getTax());
	}

	private void getData() {
		nonRoutineAllowanceMasterTypeCmbox.setList(ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasterTypes(""));
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceMasterConfigPanel");
	}

	protected void save() {
		NonRoutineAllowanceMaster nonRoutineAllowanceMaster = new NonRoutineAllowanceMaster();
		
		nonRoutineAllowanceMaster.setTnr(nonRoutineAllowanceMasterField.getText());
		nonRoutineAllowanceMaster.setNonRoutineAllowanceMasterType(nonRoutineAllowanceMasterTypeCmbox.getDataIndex());
		nonRoutineAllowanceMaster.setTnrTypeId(nonRoutineAllowanceMasterTypeCmbox.getDataIndex().getId());
		nonRoutineAllowanceMaster.setReferenceDocument(referenceDocumentField.getText());
		nonRoutineAllowanceMaster.setInputDate(new Date());
		nonRoutineAllowanceMaster.setInputBy("");
		nonRoutineAllowanceMaster.setEditDate(new Date());
		nonRoutineAllowanceMaster.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().saveNonRoutineAllowanceMaster(nonRoutineAllowanceMaster);
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
			MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceMasterConfigPanel");
		}
	}

	private void clear() {
		nonRoutineAllowanceMasterField.setText("");
		nonRoutineAllowanceMasterTypeCmbox.setSelectedIndex(0);
		referenceDocumentField.setText("");
	}
}