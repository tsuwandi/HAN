package module.employee.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import main.panel.MainPanel;
import module.employee.model.Employee;
import module.util.Bridging;

public class EmployeeDisplayPanel extends JPanel implements Bridging {

	private JLabel titleLbl;
	private JLabel nikLbl;
	private JLabel nameLbl;
	private JLabel ktpLbl;
	private JLabel addressLbl;
	private JLabel cityLbl;
	private JLabel dobLbl;
	private JLabel phoneLbl;
	private JLabel genderLbl;
	private JLabel maritalLbl;
	private JLabel employeeTypeLbl;
	private JLabel positionLbl;
	private JLabel departmentLbl;
	private JLabel divisionLbl;
	private JLabel startDateLbl;
	private JLabel statusLbl;
	
	public JTextField nikField;
	public JTextField nameField;
	public JTextField ktpField;
	public JTextField addressField;
	public JTextField cityField;
	public JDateChooser dobField;
	public JTextField phoneField;
	public JTextField genderField;
	public JTextField maritalField;
	public JTextField employeeTypeField;
	public JTextField positionField;
	public JTextField departmentField;
	public JTextField divisionField;
	public JDateChooser startDateField;
	public JTextField statusField;
	
	public JButton editBtn;
	public JButton saveBtn;
	public JButton printBtn;
	public JButton closeBtn;
	
	public JTable payroll1Table;
	public JTable payroll2Table;
	
	private JLabel grossLbl;
	private JLabel netLbl;
	private JLabel totalPayroll1Lbl;
	private JLabel totalPayroll2Lbl;
	private JLabel netAllowanceLbl;
	
	
	public JTextField grossField;
	public JTextField netField;
	public JTextField totalPayroll1Field;
	public JTextField totalPayroll2Field;
	public JTextField newAllowanceField;
	
	public Employee employee = new Employee();
	
	@Override
	public void invokeObjects(Object... objects) {
		this.employee = (Employee) objects[0];
		nikField.setText(employee.getEmployeeId());
		nameField.setText(employee.getEmployeeName());
		addressField.setText(employee.getAddress());
		dobField.setDate(employee.getDob());
		cityField.setText(employee.getCity());
		phoneField.setText(employee.getPhone());
		departmentField.setText(employee.getDepartment());
		genderField.setText(employee.getGender());
		maritalField.setText(employee.getMarital());
		startDateField.setDate(employee.getStartDate());
		divisionField.setText(employee.getDivision());
		positionField.setText(employee.getPosition());
		employeeTypeField.setText(employee.getEmployeeType());
	}
	
	/**
	 * Create the panel.
	 */
	public EmployeeDisplayPanel() {
		setLayout(null);
				
		titleLbl = new JLabel("Detail Karyawan");
		titleLbl.setBounds(20, 20, 300, 50);
		titleLbl.setFont(new Font(null, Font.BOLD, 24));
		
		nikLbl = new JLabel("NIK");
		nikLbl.setBounds(20, 120, 100, 30);
		
		nameLbl = new JLabel("Nama Karyawan");
		nameLbl.setBounds(20, 150, 100, 30);
		
		ktpLbl = new JLabel("Nomor KTP");
		ktpLbl.setBounds(20, 180, 100, 30);
		
		addressLbl = new JLabel("Alamat");
		addressLbl.setBounds(20, 210, 100, 30);
		
		cityLbl = new JLabel("Kota Asal");
		cityLbl.setBounds(20, 240, 100, 30);
		
		dobLbl = new JLabel("Tanggal Lahir");
		dobLbl.setBounds(20, 270, 100, 30);
		
		phoneLbl = new JLabel("Nomor Telepon");
		phoneLbl.setBounds(20, 300, 100, 30);
		
		genderLbl = new JLabel("Gender");
		genderLbl.setBounds(20, 330, 100, 30);
		
		maritalLbl = new JLabel("Status Perkawinan");
		maritalLbl.setBounds(20, 360, 120, 30);
		
		employeeTypeLbl = new JLabel("Tipe Karyawan");
		employeeTypeLbl.setBounds(350, 120, 100, 30);
		
		positionLbl = new JLabel("Jabatan");
		positionLbl.setBounds(350, 150, 100, 30);
		
		departmentLbl = new JLabel("Departemen");
		departmentLbl.setBounds(350, 180, 100, 30);
		
		divisionLbl = new JLabel("Divisi");
		divisionLbl.setBounds(350, 210, 100, 30);
		
		startDateLbl = new JLabel("Tanggal Mulai Kerja");
		startDateLbl.setBounds(350, 240, 120, 30);
		
		statusLbl = new JLabel("Status");
		statusLbl.setBounds(350, 270, 100, 30);
		
		
		nikField = new JTextField();
		nikField.setBounds(150, 120, 150, 25);
		nikField.setEditable(false);
		
		nameField = new JTextField();
		nameField.setBounds(150, 150, 150, 25);
		nameField.setEnabled(false);
		
		ktpField = new JTextField();
		ktpField.setBounds(150, 180, 150, 25);
		ktpField.setEnabled(false);
		
		addressField = new JTextField();
		addressField.setBounds(150, 210, 150, 25);
		addressField.setEnabled(false);
		
		cityField = new JTextField();
		cityField.setBounds(150, 240, 150, 25);
		cityField.setEnabled(false);
		
		dobField = new JDateChooser();
		dobField.setBounds(150, 270, 150, 25);
		dobField.setEnabled(false);
		
		phoneField = new JTextField();
		phoneField.setBounds(150, 300, 150, 25);
		phoneField.setEnabled(false);
		
		genderField = new JTextField();
		genderField.setBounds(150, 330, 150, 25);
		genderField.setEnabled(false);
		
		maritalField = new JTextField();
		maritalField.setBounds(150, 360, 150, 25);
		maritalField.setEnabled(false);
		
		employeeTypeField = new JTextField();
		employeeTypeField.setBounds(480, 120, 150, 25);
		employeeTypeField.setEnabled(false);
		
		positionField = new JTextField();
		positionField.setBounds(480, 150, 150, 25);
		positionField.setEnabled(false);
		
		departmentField = new JTextField();
		departmentField.setBounds(480, 180, 150, 25);
		departmentField.setEnabled(false);
		
		divisionField = new JTextField();
		divisionField.setBounds(480, 210, 150, 25);
		divisionField.setEnabled(false);
		
		startDateField = new JDateChooser();
		startDateField.setBounds(480, 240, 150, 25);
		startDateField.setEnabled(false);
		
		statusField = new JTextField();
		statusField.setBounds(480, 270, 150, 25);
		statusField.setEnabled(false);
		
		
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(150, 550, 100, 25);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(275, 550, 100, 25);
		
		printBtn = new JButton("Cetak");
		printBtn.setBounds(400, 550, 100, 25);
		
		closeBtn = new JButton("Tutup");
		closeBtn.setBounds(525, 550, 100, 25);
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.employee.ui.EmployeeListPanel");
				
			}
		});
		
		add(titleLbl);
		add(nikLbl);
		add(nameLbl);
		add(ktpLbl);
		add(addressLbl);
		add(cityLbl);
		add(dobLbl);
		add(phoneLbl);
		add(genderLbl);
		add(maritalLbl);
		add(employeeTypeLbl);
		add(positionLbl);
		add(departmentLbl);
		add(divisionLbl);
		add(startDateLbl);
		add(statusLbl);
		add(nikField);
		add(nameField);
		add(ktpField);
		add(addressField);
		add(cityField);
		add(dobField);
		add(phoneField);
		add(genderField);
		add(maritalField);
		add(employeeTypeField);
		add(positionField);
		add(departmentField);
		add(divisionField);
		add(startDateField);
		add(statusField);
		add(editBtn);
		add(saveBtn);
		add(printBtn);
		add(closeBtn);
	}

}
