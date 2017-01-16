package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;
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
import module.personalia.model.NonRoutineAllowanceMaster;
import module.personalia.model.NonRoutineAllowanceMasterType;
import module.personalia.model.NonRoutineAllowanceTransaction;

public class CreateNonRoutineAllowanceTransactionPanel extends JPanel {

	private static final long serialVersionUID = 3932828429629704157L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JDateChooser transactionStartInputDateField;
	private JDateChooser transactionEndInputDateField;
	private ComboBox<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypeCmbox;
	private ComboBox<NonRoutineAllowanceMaster> nonRoutineAllowanceMasterCmbox;
	private JTextField referenceNumberField;
	private JTextField nominalField;

	public CreateNonRoutineAllowanceTransactionPanel() {
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
		JLabel lbldokumenReferensi = new JLabel("<html>Periode Efektif</html>");
		lbldokumenReferensi.setBounds(30, 160, 100, 30);
		add(lbldokumenReferensi);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		transactionStartInputDateField = new JDateChooser();
		transactionStartInputDateField.setBounds(140, 160, 95, 30);
		transactionStartInputDateField.setDateFormatString("MM/yyyy");
		add(transactionStartInputDateField);
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
		// nomer referensi
		JLabel lblnomerReferensi = new JLabel("<html>Nomer Referensi</html>");
		lblnomerReferensi.setBounds(30, 320, 100, 30);
		add(lblnomerReferensi);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		add(label_6);

		referenceNumberField = new JTextField();
		referenceNumberField.setBounds(140, 320, 200, 30);
		add(referenceNumberField);
		// save
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		JButton searchEmployeeCodeBtn = new JButton("Cari");
		searchEmployeeCodeBtn.setBounds(350, 80, 90, 30);
		add(searchEmployeeCodeBtn);
		
		searchEmployeeCodeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeNameField.setText(ServiceFactory.getPersonaliaBL().getEmployees(" and LOWER(emp_code) like ('"+employeeCodeField.getText()+"')").get(0).getName());
			}
		});
		
		JLabel lblNewLabel = new JLabel(" -");
		lblNewLabel.setBounds(235, 160, 10, 30);
		add(lblNewLabel);
		
		transactionEndInputDateField = new JDateChooser();
		transactionEndInputDateField.setBounds(245, 160, 95, 30);
		transactionEndInputDateField.setDateFormatString("MM/yyyy");
		add(transactionEndInputDateField);
		
		nonRoutineAllowanceMasterCmbox = new ComboBox<>();
		nonRoutineAllowanceMasterCmbox.setBounds(140, 240, 200, 30);
		add(nonRoutineAllowanceMasterCmbox);
		
		nominalField = new JTextField();
		nominalField.setBounds(140, 280, 200, 30);
		add(nominalField);
		
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
	}

	private void getData() {
		nonRoutineAllowanceMasterTypeCmbox.setList(ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasterTypes(""));
		nonRoutineAllowanceMasterCmbox.setList(ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasters(""));
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceTransactionConfigPanel");
	}

	protected void save() {
		NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction = new NonRoutineAllowanceTransaction();
		
		nonRoutineAllowanceTransaction.setEmployeeCode(employeeCodeField.getText());
		nonRoutineAllowanceTransaction.setEmployeeName(employeeNameField.getText());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(transactionStartInputDateField.getDate());
		
		nonRoutineAllowanceTransaction.setEffectiveStartMonth(cal.get(Calendar.MONTH));
		nonRoutineAllowanceTransaction.setEffectiveStartYear(cal.get(Calendar.YEAR));
		
		cal.setTime(transactionEndInputDateField.getDate());
		
		nonRoutineAllowanceTransaction.setEffectiveEndMonth(cal.get(Calendar.MONTH));
		nonRoutineAllowanceTransaction.setEffectiveEndYear(cal.get(Calendar.YEAR));
		
		nonRoutineAllowanceTransaction.setTnrId(nonRoutineAllowanceMasterCmbox.getDataIndex().getId());
		nonRoutineAllowanceTransaction.setTnrTypeId(nonRoutineAllowanceMasterTypeCmbox.getDataIndex().getId());
		nonRoutineAllowanceTransaction.setNominal(new BigDecimal(nominalField.getText()));
		nonRoutineAllowanceTransaction.setReferenceNumber(referenceNumberField.getText());
		nonRoutineAllowanceTransaction.setInputDate(new Date());
		nonRoutineAllowanceTransaction.setInputBy("");
		nonRoutineAllowanceTransaction.setEditDate(new Date());
		nonRoutineAllowanceTransaction.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().saveNonRoutineAllowanceTransaction(nonRoutineAllowanceTransaction);
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
			MainPanel.changePanel("module.personalia.ui.NonRoutineAllowanceTransactionConfigPanel");
		}
	}

	private void clear() {
		employeeCodeField.setText("");
		employeeNameField.setText("");
		transactionStartInputDateField.setDate(null);
		transactionEndInputDateField.setDate(null);
		nonRoutineAllowanceMasterTypeCmbox.setSelectedIndex(0);
		nonRoutineAllowanceMasterCmbox.setSelectedIndex(0);
		nominalField.setText("");
		referenceNumberField.setText("");
	}
}