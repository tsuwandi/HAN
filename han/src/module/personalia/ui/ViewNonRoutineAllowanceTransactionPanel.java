package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.NonRoutineAllowanceTransaction;

public class ViewNonRoutineAllowanceTransactionPanel extends JPanel {

	private static final long serialVersionUID = 6305743140987285740L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JDateChooser transactionInputDateField;
	private ComboBox<?> nonRoutineAllowanceMasterTypeCmbox;
	private JTextField nominalField;
	private ComboBox<?> descriptionCmbox;
	private JTextField referenceNumberField;
	private JButton editBtn;
	private boolean editMode = false;

	public ViewNonRoutineAllowanceTransactionPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Input Tunjangan Non Rutin > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 430, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("INPUT TUNJANGAN NON RUTIN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 430, 25);
		add(lblHeader);
		// nik
		JLabel lblJenisTunjanganNon = new JLabel("<html>NIK</html>");
		lblJenisTunjanganNon.setBounds(30, 80, 100, 30);
		add(lblJenisTunjanganNon);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		employeeCodeField = new JTextField();
		employeeCodeField.setBounds(140, 80, 200, 30);
		add(employeeCodeField);
		// nama karyawan
		JLabel lblpajak = new JLabel("<html>Nama Karyawan</html>");
		lblpajak.setBounds(30, 120, 100, 30);
		add(lblpajak);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		employeeNameField = new JTextField();
		employeeNameField.setEnabled(false);
		employeeNameField.setEditable(false);
		employeeNameField.setBounds(140, 120, 200, 30);
		add(employeeNameField);
		// tanggal input
		JLabel lbldokumenReferensi = new JLabel("<html>Tanggal Input</html>");
		lbldokumenReferensi.setBounds(30, 160, 100, 30);
		add(lbldokumenReferensi);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		transactionInputDateField = new JDateChooser();
		transactionInputDateField.setBounds(140, 160, 200, 30);
		add(transactionInputDateField);
		// jenis tunjangan
		JLabel lbljenisTunjanganNon = new JLabel("<html>Jenis Tunjangan Non Rutin</html>");
		lbljenisTunjanganNon.setBounds(30, 200, 100, 30);
		add(lbljenisTunjanganNon);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 200, 10, 30);
		add(label_4);
		
		nonRoutineAllowanceMasterTypeCmbox = new ComboBox<>();
		nonRoutineAllowanceMasterTypeCmbox.setBounds(140, 200, 200, 30);
		add(nonRoutineAllowanceMasterTypeCmbox);
		// nominal
		JLabel lblnominal = new JLabel("<html>Nominal</html>");
		lblnominal.setBounds(30, 240, 100, 30);
		add(lblnominal);
		
		JLabel label = new JLabel(":");
		label.setBounds(130, 240, 10, 30);
		add(label);
		
		nominalField = new JTextField();
		nominalField.setBounds(140, 242, 200, 30);
		add(nominalField);
		// deskription
		JLabel lbldeskripsi = new JLabel("<html>Deskripsi</html>");
		lbldeskripsi.setBounds(30, 280, 100, 30);
		add(lbldeskripsi);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 280, 10, 30);
		add(label_5);
		
		descriptionCmbox = new ComboBox<>();
		descriptionCmbox.setBounds(140, 283, 200, 30);
		add(descriptionCmbox);
		// nomer referensi
		JLabel lblnomerReferensi = new JLabel("<html>Nomer Referensi</html>");
		lblnomerReferensi.setBounds(30, 320, 100, 30);
		add(lblnomerReferensi);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		add(label_6);

		referenceNumberField = new JTextField();
		referenceNumberField.setBounds(140, 324, 200, 30);
		add(referenceNumberField);
		// save
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.setBounds(10, 589, 90, 30);
		add(btnKembali);
		
		/*saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});*/
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
		
		JButton btnAttach = new JButton("Attach");
		btnAttach.setBounds(250, 242, 90, 30);
		add(btnAttach);
		
		getLastID();
	}

	protected void update() {
		
	}

	protected void delete() {
		
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("DIV");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		employeeCodeField.setText(lastId.toString());
	}

	protected void save() {
		NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction = new NonRoutineAllowanceTransaction();
		
		nonRoutineAllowanceTransaction.setInputDate(new Date());
		nonRoutineAllowanceTransaction.setInputBy("");
		nonRoutineAllowanceTransaction.setEditDate(new Date());
		nonRoutineAllowanceTransaction.setEditBy("");
		
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

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}