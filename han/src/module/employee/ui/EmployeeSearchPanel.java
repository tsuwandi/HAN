package module.employee.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import main.panel.MainPanel;

public class EmployeeSearchPanel extends JPanel {
	
	private JLabel titleLbl;
	private JLabel nikLbl;
	private JLabel nameLbl;
	private JLabel employeeTypeLbl;
	private JLabel ktpLbl;
	private JLabel positionLbl;
	private JLabel departmentLbl;
	private JLabel divisionLbl;
	private JLabel startDateLbl;
	private JLabel cityLbl;
	private JLabel dobLbl;
	private JLabel genderLbl;
	private JLabel maritalLbl;
	private JLabel statusLbl;
	
	public JTextField nikField;
	public JTextField nameField;
	public JTextField employeeTypeField;
	public JTextField ktpField;
	public JTextField positionField;
	public JTextField departmentField;
	public JTextField divisionField;
	public JDateChooser startDateField;
	public JDateChooser endDateField;
	public JTextField cityField;
	public JDateChooser dobStartField;
	public JDateChooser dobEndField;
	public JComboBox<String> genderField;
	public JRadioButton singleField;
	public JRadioButton marriedField;
	public JRadioButton activeField;
	public JRadioButton inactiveField;
	public ButtonGroup marital;
	public ButtonGroup status;
	
	public JButton searchBtn;
	public JButton resetBtn;
	public JButton closeBtn;
	/**
	 * Create the panel.
	 */
	public EmployeeSearchPanel() {
		setLayout(null);
		
		titleLbl = new JLabel("Pencarian Lanjut");
		titleLbl.setBounds(20, 20, 300, 50);
		titleLbl.setFont(new Font(null, Font.BOLD, 24));
		
		nikLbl = new JLabel("NIK");
		nikLbl.setBounds(20, 120, 125, 30);
		
		nameLbl = new JLabel("Nama Karyawan");
		nameLbl.setBounds(20, 150, 125, 30);
		
		employeeTypeLbl = new JLabel("Tipe Karyawan");
		employeeTypeLbl.setBounds(20, 180, 125, 30);
		
		ktpLbl = new JLabel("Nomor KTP");
		ktpLbl.setBounds(20, 210, 125, 30);
		
		positionLbl = new JLabel("Jabatan");
		positionLbl.setBounds(20, 240, 125, 30);
		
		departmentLbl = new JLabel("Departemen");
		departmentLbl.setBounds(20, 270, 125, 30);
		
		divisionLbl = new JLabel("Divisi");
		divisionLbl.setBounds(20, 300, 125, 30);
		
		startDateLbl = new JLabel("Tanggal Mulai Kerja");
		startDateLbl.setBounds(20, 330, 125, 30);
		
		cityLbl = new JLabel("Kota Asal");
		cityLbl.setBounds(20, 360, 125, 30);
		
		dobLbl = new JLabel("Tanggal Lahir");
		dobLbl.setBounds(20, 390, 125, 30);
		
		genderLbl = new JLabel("Gender");
		genderLbl.setBounds(20, 420, 125, 30);
		
		maritalLbl = new JLabel("Status Perkawinan");
		maritalLbl.setBounds(20, 450, 125, 30);
		
		statusLbl = new JLabel("Status");
		statusLbl.setBounds(20, 480, 125, 30);
		
		
		
		nikField = new JTextField();
		nikField.setBounds(150, 120, 150, 25);
		
		nameField = new JTextField();
		nameField.setBounds(150, 150, 150, 25);
		
		employeeTypeField = new JTextField();
		employeeTypeField.setBounds(150, 180, 150, 25);
		
		ktpField = new JTextField();
		ktpField.setBounds(150, 210, 150, 25);
		
		positionField = new JTextField();
		positionField.setBounds(150, 240, 150, 25);
		
		departmentField = new JTextField();
		departmentField.setBounds(150, 270, 150, 25);
		
		divisionField = new JTextField();
		divisionField.setBounds(150, 300, 150, 25);
		
		startDateField = new JDateChooser();
		startDateField.setBounds(150, 330, 150, 25);
		
		endDateField = new JDateChooser();
		endDateField.setBounds(315, 330, 150, 25);
		
		cityField = new JTextField();
		cityField.setBounds(150, 360, 150, 25);
		
		dobStartField = new JDateChooser();
		dobStartField.setBounds(150, 390, 150, 25);
		
		dobEndField = new JDateChooser();
		dobEndField.setBounds(315, 390, 150, 25);
		
		genderField = new JComboBox<>();
		genderField.setBounds(150, 420, 150, 25);
		
		marital = new ButtonGroup();
		
		singleField = new JRadioButton("Lajang");
		singleField.setBounds(150, 450, 75, 25);
		
		marriedField = new JRadioButton("Menikah");
		marriedField.setBounds(230, 450, 75, 25);
		
		marital.add(singleField);
		marital.add(marriedField);
		
		status = new ButtonGroup();
		
		activeField = new JRadioButton("Aktif");
		activeField.setBounds(150, 480, 75, 25);
		
		inactiveField = new JRadioButton("Non Aktif");
		inactiveField.setBounds(230, 480, 75, 25);
		
		status.add(activeField);
		status.add(inactiveField);
		
		searchBtn = new JButton("Cari");
		searchBtn.setBounds(225, 530, 75, 25);
		
		resetBtn = new JButton("Reset");
		resetBtn.setBounds(305, 530, 75, 25);
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				
			}
		});
		
		closeBtn = new JButton("Tutup");
		closeBtn.setBounds(385, 530, 75, 25);
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employee.ui.EmployeeListPanel");
				
			}
		});
		
		add(titleLbl);
		add(nikLbl);
		add(nameLbl);
		add(employeeTypeLbl);
		add(ktpLbl);
		add(positionLbl);
		add(departmentLbl);
		add(divisionLbl);
		add(startDateLbl);
		add(cityLbl);
		add(dobLbl);
		add(genderLbl);
		add(maritalLbl);
		add(statusLbl);
		add(nikField);
		add(nameField);
		add(employeeTypeField);
		add(ktpField);
		add(positionField);
		add(departmentField);
		add(divisionField);
		add(startDateField);
		add(endDateField);
		add(cityField);
		add(dobStartField);
		add(dobEndField);
		add(genderField);
		add(singleField);
		add(marriedField);
		add(activeField);
		add(inactiveField);
		add(searchBtn);
		add(resetBtn);
		add(closeBtn);
		
	}
	
	public void clear(){
		
	}

}
