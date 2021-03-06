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
import module.personalia.model.Tax;
import module.util.Bridging;

public class ViewNonRoutineAllowanceMasterTypePanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<Tax> taxCmbox;
	private JTextField nonRoutineAllowanceMasterTypeField;
	private JTextField referenceDocumentField;
	private NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType;
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

		nonRoutineAllowanceMasterTypeField = new JTextField();
		nonRoutineAllowanceMasterTypeField.setBounds(140, 80, 200, 30);
		nonRoutineAllowanceMasterTypeField.setEditable(false);
		nonRoutineAllowanceMasterTypeField.setEnabled(false);
		add(nonRoutineAllowanceMasterTypeField);
		// pajak
		JLabel lblpajak = new JLabel("<html>Pajak</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);

		taxCmbox = new ComboBox<>();
		taxCmbox.setEnabled(false);
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
		referenceDocumentField.setEnabled(false);
		referenceDocumentField.setEditable(false);
		referenceDocumentField.setBounds(140, 160, 200, 30);
		add(referenceDocumentField);

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
		
		getData();
	}

	private void getData() {
		taxCmbox.setList(ServiceFactory.getPersonaliaBL().getTaxs(""));
	}

	protected void update() {
		NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType = new NonRoutineAllowanceMasterType();

		nonRoutineAllowanceMasterType.setInputDate(new Date());
		nonRoutineAllowanceMasterType.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		nonRoutineAllowanceMasterType.setEditDate(new Date());
		nonRoutineAllowanceMasterType.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());

		try {
			ServiceFactory.getPersonaliaBL().updateNonRoutineAllowanceMasterType(nonRoutineAllowanceMasterType);
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			nonRoutineAllowanceMasterType.setDeleteDate(new Date());
			nonRoutineAllowanceMasterType.setDeleteBy(ServiceFactory.getSystemBL().getUsernameActive());
			ServiceFactory.getPersonaliaBL().deleteNonRoutineAllowanceMasterType(nonRoutineAllowanceMasterType);
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		} else {
			
		}
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		
		nonRoutineAllowanceMasterTypeField.setEditable(true);
		nonRoutineAllowanceMasterTypeField.setEnabled(true);
		taxCmbox.setEnabled(true);
		taxCmbox.setEditable(true);
		referenceDocumentField.setEditable(true);
		referenceDocumentField.setEnabled(true);
		
		editBtn.setText("Simpan");
		editBtn.repaint();
	}

	@Override
	public void invokeObjects(Object... objects) {
		nonRoutineAllowanceMasterType = (NonRoutineAllowanceMasterType) objects[0];

		nonRoutineAllowanceMasterTypeField.setText(nonRoutineAllowanceMasterType.getTnrType());
		taxCmbox.setSelectedItem(nonRoutineAllowanceMasterType);
		referenceDocumentField.setText(nonRoutineAllowanceMasterType.getReferenceDocument());
	}
}