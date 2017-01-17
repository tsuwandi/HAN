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
import module.util.Bridging;

public class ViewNonRoutineAllowanceMasterPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<NonRoutineAllowanceMasterType> nonRoutineAllowanceTypeCmbox;
	private JTextField nonRoutineAllowanceField;
	private JTextField taxField;
	private JTextField referenceDocumentField;
	private JButton editBtn;
	private JButton attachBtn;
	private boolean editMode = false;
	private NonRoutineAllowanceMaster nonRoutineAllowanceMaster;

	public ViewNonRoutineAllowanceMasterPanel() {
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

		nonRoutineAllowanceField = new JTextField();
		nonRoutineAllowanceField.setBounds(140, 80, 200, 30);
		nonRoutineAllowanceField.setEditable(false);
		nonRoutineAllowanceField.setEnabled(false);
		add(nonRoutineAllowanceField);
		// pajak
		JLabel lblpajak = new JLabel("<html>Jenis Tunjangan Non Rutin</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);

		nonRoutineAllowanceTypeCmbox = new ComboBox<>();
		nonRoutineAllowanceTypeCmbox.setEnabled(false);
		nonRoutineAllowanceTypeCmbox.setBounds(140, 120, 200, 30);
		add(nonRoutineAllowanceTypeCmbox);
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

		JButton btnKembali = new JButton("Kembali");
		btnKembali.setBounds(10, 589, 90, 30);
		add(btnKembali);

		JLabel label = new JLabel("<html>Dokumen Referensi</html>");
		label.setBounds(30, 201, 100, 30);
		add(label);

		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 201, 10, 30);
		add(label_4);

		referenceDocumentField = new JTextField();
		referenceDocumentField.setEnabled(false);
		referenceDocumentField.setEditable(false);
		referenceDocumentField.setBounds(140, 201, 200, 30);
		add(referenceDocumentField);

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

		attachBtn = new JButton("Attach");
		attachBtn.setEnabled(false);
		attachBtn.setBounds(250, 242, 90, 30);
		add(attachBtn);
		
		getData();
	}

	private void getData() {
		nonRoutineAllowanceTypeCmbox.setList(ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasterTypes(""));
	}

	protected void update() {
		nonRoutineAllowanceMaster.setTnr(nonRoutineAllowanceField.getText());
		nonRoutineAllowanceMaster.setTnrTypeId(nonRoutineAllowanceTypeCmbox.getDataIndex().getId());
		nonRoutineAllowanceMaster.setNonRoutineAllowanceMasterType(nonRoutineAllowanceTypeCmbox.getDataIndex());
		nonRoutineAllowanceMaster.setReferenceDocument(referenceDocumentField.getText());
		nonRoutineAllowanceMaster.setInputDate(new Date());
		nonRoutineAllowanceMaster.setInputBy("");
		nonRoutineAllowanceMaster.setEditDate(new Date());
		nonRoutineAllowanceMaster.setEditBy("");

		try {
			ServiceFactory.getPersonaliaBL().updateNonRoutineAllowanceMaster(nonRoutineAllowanceMaster);
			option();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			nonRoutineAllowanceMaster.setDeleteDate(new Date());
			nonRoutineAllowanceMaster.setDeleteBy("");
			ServiceFactory.getPersonaliaBL().deleteNonRoutineAllowanceMaster(nonRoutineAllowanceMaster);
			MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceTransactionConfigPanel");
		} else {
			
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
		nonRoutineAllowanceField.setText("");
		nonRoutineAllowanceTypeCmbox.setSelectedIndex(0);
		referenceDocumentField.setText("");
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;

		nonRoutineAllowanceField.setEnabled(true);
		nonRoutineAllowanceField.setEditable(true);
		nonRoutineAllowanceTypeCmbox.setEnabled(true);
		referenceDocumentField.setEnabled(true);
		referenceDocumentField.setEditable(true);
		attachBtn.setEnabled(true);
		
		editBtn.setText("Simpan");
		editBtn.updateUI();
	}

	@Override
	public void invokeObjects(Object... objects) {
		nonRoutineAllowanceMaster = (NonRoutineAllowanceMaster) objects[0];
		
		nonRoutineAllowanceField.setText(nonRoutineAllowanceMaster.getTnr());
		nonRoutineAllowanceTypeCmbox.setSelectedItem(nonRoutineAllowanceMaster.getNonRoutineAllowanceMasterType());
		taxField.setText(nonRoutineAllowanceMaster.getNonRoutineAllowanceMasterType().getTax().getTax());
		referenceDocumentField.setText(nonRoutineAllowanceMaster.getReferenceDocument());
	}
}