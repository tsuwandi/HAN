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
import module.personalia.model.NonRoutineAllowanceMasterType;

public class ViewNonRoutineAllowanceMasterTypePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<?> nonRoutineAllowanceTypeCmbox;
	private JTextField nonRoutineAllowanceField;
	private JTextField referenceDocumentField;
	private JButton editBtn;
	private boolean editMode = false;

	public ViewNonRoutineAllowanceMasterTypePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Master Jenis Tunjangan Non Rutin > Pendaftaran Baru");
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
		nonRoutineAllowanceField.setEditable(false);
		nonRoutineAllowanceField.setEnabled(false);
		add(nonRoutineAllowanceField);
		// pajak
		JLabel lblpajak = new JLabel("<html>Pajak</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		nonRoutineAllowanceTypeCmbox = new ComboBox<>();
		nonRoutineAllowanceTypeCmbox.setBounds(140, 120, 200, 30);
		add(nonRoutineAllowanceTypeCmbox);
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
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.setBounds(10, 589, 90, 30);
		add(btnKembali);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(924, 589, 90, 30);
		add(editBtn);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isEditMode()==false) {
					setEditMode(true);
				}
				else {
					update();
				}
			}
		});
		
		JButton deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(824, 589, 90, 30);
		add(deleteBtn);
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		JButton printBtn = new JButton("Cetak");
		printBtn.setBounds(724, 589, 90, 30);
		add(printBtn);
		
		getLastID();
	}

	protected void update() {
		
	}

	protected void delete() {
		
	}
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("DIV");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		nonRoutineAllowanceField.setText(lastId.toString());
	}

	protected void save() {
		NonRoutineAllowanceMasterType nonRoutineAllowance = new NonRoutineAllowanceMasterType();
		
		nonRoutineAllowance.setInputDate(new Date());
		nonRoutineAllowance.setInputBy("");
		nonRoutineAllowance.setEditDate(new Date());
		nonRoutineAllowance.setEditBy("");
		
		try {
			//ServiceFactory.getPersonaliaBL().saveDivision(nonRoutineAllowance);
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
			MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceConfigPanel");
		}
	}

	private void clear() {
		getLastID();
	}
}