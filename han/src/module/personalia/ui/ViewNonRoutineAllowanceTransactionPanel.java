package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.NonRoutineAllowanceTransaction;
import module.util.Bridging;
import module.personalia.model.NonRoutineAllowanceMaster;
import module.personalia.model.NonRoutineAllowanceMasterType;

public class ViewNonRoutineAllowanceTransactionPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 6305743140987285740L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private ComboBox<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypeCmbox;
	private ComboBox<NonRoutineAllowanceMaster> nonRoutineAllowanceMasterCmbox;
	private JTextField nominalField;
	private JTextField referenceNumberField;
	private JButton editBtn;
	private boolean editMode = false;
	private NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction;
	private JDateChooser transactionEndInputDateField;
	private JDateChooser transactionStartInputDateField;

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
		employeeCodeField.setEnabled(false);
		employeeCodeField.setEditable(false);
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

		employeeNameField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateName();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateName();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateName();
			}
		});
		// tanggal input
		JLabel lbldokumenReferensi = new JLabel("<html>Periodik Efektif</html>");
		lbldokumenReferensi.setBounds(30, 160, 100, 30);
		add(lbldokumenReferensi);

		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		// jenis tunjangan
		JLabel lbljenisTunjanganNon = new JLabel("<html>Jenis Tunjangan Non Rutin</html>");
		lbljenisTunjanganNon.setBounds(30, 200, 100, 30);
		add(lbljenisTunjanganNon);

		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 200, 10, 30);
		add(label_4);

		nonRoutineAllowanceMasterTypeCmbox = new ComboBox<>();
		nonRoutineAllowanceMasterTypeCmbox.setEnabled(false);
		nonRoutineAllowanceMasterTypeCmbox.setBounds(140, 200, 200, 30);
		add(nonRoutineAllowanceMasterTypeCmbox);
		// nominal
		JLabel lblnominal = new JLabel("<html>Tunjangan Non Rutin</html>");
		lblnominal.setBounds(30, 240, 100, 30);
		add(lblnominal);

		JLabel label = new JLabel(":");
		label.setBounds(130, 240, 10, 30);
		add(label);
		// deskription
		JLabel lbldeskripsi = new JLabel("<html>Nominal</html>");
		lbldeskripsi.setBounds(30, 280, 100, 30);
		add(lbldeskripsi);

		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 280, 10, 30);
		add(label_5);

		nominalField = new JTextField();
		nominalField.setEnabled(false);
		nominalField.setBounds(140, 283, 200, 30);
		add(nominalField);
		// nomer referensi
		JLabel lblnomerReferensi = new JLabel("<html>Nomer Referensi</html>");
		lblnomerReferensi.setBounds(30, 320, 100, 30);
		add(lblnomerReferensi);

		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		add(label_6);

		referenceNumberField = new JTextField();
		referenceNumberField.setEnabled(false);
		referenceNumberField.setEditable(false);
		referenceNumberField.setBounds(140, 324, 200, 30);
		add(referenceNumberField);
		// save
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);

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

		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(350, 80, 90, 30);
		add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateName();
			}
		});

		transactionStartInputDateField = new JDateChooser();
		transactionStartInputDateField.setDateFormatString("MM/yyyy");
		transactionStartInputDateField.setBounds(140, 160, 95, 30);
		transactionStartInputDateField.setEnabled(false);
		add(transactionStartInputDateField);

		JLabel label_7 = new JLabel(" -");
		label_7.setBounds(235, 160, 10, 30);
		add(label_7);

		transactionEndInputDateField = new JDateChooser();
		transactionEndInputDateField.setDateFormatString("MM/yyyy");
		transactionEndInputDateField.setBounds(245, 160, 95, 30);
		transactionEndInputDateField.setEnabled(false);
		add(transactionEndInputDateField);
		
		nonRoutineAllowanceMasterCmbox = new ComboBox<NonRoutineAllowanceMaster>();
		nonRoutineAllowanceMasterCmbox.setEnabled(false);
		nonRoutineAllowanceMasterCmbox.setBounds(140, 241, 200, 30);
		add(nonRoutineAllowanceMasterCmbox);
	}

	protected void update() {
		NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction = new NonRoutineAllowanceTransaction();

		nonRoutineAllowanceTransaction.setInputDate(new Date());
		nonRoutineAllowanceTransaction.setInputBy("");
		nonRoutineAllowanceTransaction.setEditDate(new Date());
		nonRoutineAllowanceTransaction.setEditBy("");

		try {
			ServiceFactory.getPersonaliaBL().updateNonRoutineAllowanceTransaction(nonRoutineAllowanceTransaction);
			option();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	protected void delete() {

	}

	private void option() {
		if (DialogBox.showAfterChoiceInsert()==0) {
			clear();
		} else {
			MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceConfigPanel");
		}
	}

	private void clear() {

	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		
		
	}

	@Override
	public void invokeObjects(Object... objects) {
		nonRoutineAllowanceTransaction = (NonRoutineAllowanceTransaction) objects[0];

		employeeCodeField.setText(nonRoutineAllowanceTransaction.getEmployeeCode());
		Date effectiveStartDate = null;
		Date effectiveEndDate = null;
		try {
			effectiveStartDate = new SimpleDateFormat("MM/yyyy").parse(nonRoutineAllowanceTransaction.getEffectiveStartMonth()+"/"+nonRoutineAllowanceTransaction.getEffectiveStartYear());
			effectiveEndDate = new SimpleDateFormat("MM/yyyy").parse(nonRoutineAllowanceTransaction.getEffectiveEndMonth()+"/"+nonRoutineAllowanceTransaction.getEffectiveEndYear());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		transactionStartInputDateField.setDate(effectiveStartDate);
		transactionEndInputDateField.setDate(effectiveEndDate);
		nonRoutineAllowanceMasterTypeCmbox.setSelectedItem(nonRoutineAllowanceTransaction.getNonRoutineAllowanceMasterType());
		nonRoutineAllowanceMasterCmbox.setSelectedItem(nonRoutineAllowanceTransaction.getNonRoutineAllowanceMaster());
		nominalField.setText(""+nonRoutineAllowanceTransaction.getNominal().intValueExact());
		referenceNumberField.setText(nonRoutineAllowanceTransaction.getReferenceNumber());
	}

	private void updateName() {
		employeeNameField.setText(ServiceFactory.getPersonaliaBL().getEmployees(" and LOWER(emp_code) like ('"+employeeCodeField.getText()+"')").get(0).getName());
	}
}