package module.employee.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.employee.model.Employee;

public class CreateEmployeePanel extends JPanel {

	private JLabel titleLbl;
	private JLabel nikLbl;
	private JLabel employeeTypeLbl;
	private JLabel nameLbl;
	private JLabel ktpLbl;
	private JLabel addressLbl;
	private JLabel cityLbl;
	private JLabel dobLbl;
	private JLabel phoneLbl;
	private JLabel genderLbl;
	private JLabel maritalLbl;
	private JLabel positionLbl;
	private JLabel departmentLbl;
	private JLabel divisionLbl;
	private JLabel startDateLbl;
	
	public JTextField nikField;
	public JComboBox<String> employeeTypeField;
	public JTextField nameField;
	public JTextField ktpField;
	public JTextField addressField;
	public JTextField cityField;
	public JDateChooser dobField;
	public JTextField phoneField;
	public JComboBox<String> genderField;
	public JComboBox<String> maritalField;
	public JComboBox<String> positionField;
	public JComboBox<String> departmentField;
	public JComboBox<String> divisionField;
	public JDateChooser startDateField;
	
	public JButton saveBtn;
	public JButton closeBtn;
	
	public List<Employee> genders = null;
	public List<Employee> positions = null;
	public List<Employee> divisions =  null;
	public List<Employee> departments = null;
	public List<Employee> employeeTypes = null;
	public List<Employee> maritals = null;
	
	public Employee employee;

	/**
	 * Create the panel.
	 */
	public CreateEmployeePanel() {
		setLayout(null);
		
		titleLbl = new JLabel("Pendaftaran Karyawan");
		titleLbl.setBounds(20, 20, 300, 50);
		titleLbl.setFont(new Font(null, Font.BOLD, 24));
		
		nikLbl = new JLabel("NIK");
		nikLbl.setBounds(20, 120, 100, 30);
		
		employeeTypeLbl = new JLabel("Tipe Karyawan");
		employeeTypeLbl.setBounds(20, 150, 100, 30);
		
		nameLbl = new JLabel("Nama Karyawan");
		nameLbl.setBounds(20, 180, 100, 30);
		
		ktpLbl = new JLabel("Nomor KTP");
		ktpLbl.setBounds(20, 210, 100, 30);
		
		addressLbl = new JLabel("Alamat");
		addressLbl.setBounds(20, 240, 100, 30);
		
		cityLbl = new JLabel("Kota Asal");
		cityLbl.setBounds(20, 270, 100, 30);
		
		dobLbl = new JLabel("Tanggal Lahir");
		dobLbl.setBounds(20, 300, 100, 30);
		
		phoneLbl = new JLabel("Nomor Telepon");
		phoneLbl.setBounds(20, 330, 100, 30);
		
		genderLbl = new JLabel("Gender");
		genderLbl.setBounds(20, 360, 100, 30);
		
		maritalLbl = new JLabel("Status Perkawinan");
		maritalLbl.setBounds(20, 390, 120, 30);
		
		positionLbl = new JLabel("Jabatan");
		positionLbl.setBounds(350, 120, 100, 30);
		
		departmentLbl = new JLabel("Departemen");
		departmentLbl.setBounds(350, 150, 100, 30);
		
		divisionLbl = new JLabel("Divisi");
		divisionLbl.setBounds(350, 180, 100, 30);
		
		startDateLbl = new JLabel("Tanggal Mulai Kerja");
		startDateLbl.setBounds(350, 210, 100, 30);
		
		
		nikField = new JTextField();
		nikField.setBounds(145, 120, 150, 25);
		//nikField.setEnabled(false);
		
		try {
			employeeTypes = ServiceFactory.getEmployeeBL().getAllEmployeeType();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		employeeTypeField = new JComboBox<>();
		employeeTypeField.setBounds(145, 150, 150, 25);
		for(int i=0; i<employeeTypes.size(); i++){
			employeeTypeField.addItem(employeeTypes.get(i).getEmployeeType());
		}
		
		nameField = new JTextField();
		nameField.setBounds(145, 180, 150, 25);
		
		ktpField = new JTextField();
		ktpField.setBounds(145, 210, 150, 25);
		
		addressField = new JTextField();
		addressField.setBounds(145, 240, 150, 25);
		
		cityField = new JTextField();
		cityField.setBounds(145, 270, 150, 25);
		
		dobField = new JDateChooser();
		dobField.setBounds(145, 300, 150, 25);
		
		phoneField = new JTextField();
		phoneField.setBounds(145, 330, 150, 25);
		
		try {
			genders = ServiceFactory.getEmployeeBL().getAllGender();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		genderField = new JComboBox<>();
		genderField.setBounds(145, 360, 150, 25);
		for(int i=0; i<genders.size(); i++){
			genderField.addItem(genders.get(i).getGender());
		}
		
		try {
			maritals = ServiceFactory.getEmployeeBL().getAllMarital();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		maritalField = new JComboBox<>();
		maritalField.setBounds(145, 390, 150, 25);
		for(int i=0; i<maritals.size(); i++){
			maritalField.addItem(maritals.get(i).getMarital());
		}
		
		try {
			positions = ServiceFactory.getEmployeeBL().getAllPosition();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		positionField = new JComboBox<>();
		positionField.setBounds(475, 120, 150, 25);
		for(int i=0; i<positions.size(); i++){
			positionField.addItem(positions.get(i).getPosition());
		}
		
		try {
			departments = ServiceFactory.getEmployeeBL().getAllDepartment();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		departmentField = new JComboBox<>();
		departmentField.setBounds(475, 150, 150, 25);
		for(int i=0; i<departments.size(); i++){
			departmentField.addItem(departments.get(i).getDepartment());
		}
		
		try {
			divisions = ServiceFactory.getEmployeeBL().getAllDivision();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		divisionField = new JComboBox<>();
		divisionField.setBounds(475, 180, 150, 25);
		for(int i=0; i<divisions.size(); i++){
			divisionField.addItem(divisions.get(i).getDivision());
		}
		
		startDateField = new JDateChooser();
		startDateField.setBounds(475, 210, 150, 25);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(450, 450, 75, 25);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doInsert();
				clear();
			}
		});
		
		closeBtn = new JButton("Tutup");
		closeBtn.setBounds(550, 450, 75, 25);
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employee.ui.EmployeeListPanel");
				
			}
		});
		
		add(titleLbl);
		add(nikLbl);
		add(employeeTypeLbl);
		add(nameLbl);
		add(ktpLbl);
		add(addressLbl);
		add(cityLbl);
		add(dobLbl);
		add(phoneLbl);
		add(genderLbl);
		add(maritalLbl);
		add(positionLbl);
		add(departmentLbl);
		add(divisionLbl);
		add(startDateLbl);
		add(nikField);
		add(employeeTypeField);
		add(nameField);
		add(ktpField);
		add(addressField);
		add(cityField);
		add(dobField);
		add(phoneField);
		add(genderField);
		add(maritalField);
		add(positionField);
		add(departmentField);
		add(divisionField);
		add(startDateField);
		add(saveBtn);
		add(closeBtn);

	}
	
	public void doInsert(){
		employee = new Employee();
		employee.setEmployeeId(nikField.getText());
		String empType = employeeTypes.get(employeeTypeField.getSelectedIndex()).getEmployeeTypeId();
		employee.setEmployeeTypeId(empType);
		employee.setEmployeeName(nameField.getText());
		employee.setAddress(addressField.getText());
		employee.setCity(cityField.getText());
		employee.setDob(dobField.getDate());
		employee.setPhone(phoneField.getText());
		String gdr = genders.get(genderField.getSelectedIndex()).getGenderId();
		employee.setGenderId(gdr);
		String mrtl = maritals.get(maritalField.getSelectedIndex()).getMaritalId();
		employee.setMaritalId(mrtl);
		String pstn = positions.get(positionField.getSelectedIndex()).getPositionId();
		employee.setPositionId(pstn);
		String dptmn = departments.get(departmentField.getSelectedIndex()).getDepartmentId();
		employee.setDepartmentId(dptmn);
		String dvsn = divisions.get(divisionField.getSelectedIndex()).getDivisionId();
		employee.setDivisionId(dvsn);
		employee.setStartDate(startDateField.getDate());
		
		try {
			ServiceFactory.getEmployeeBL().save(employee);
			JOptionPane.showMessageDialog(null, "Sukses Menyimpan Data");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data");
			e.printStackTrace();
		}
	}
	
	public void clear(){
		Date today = new Date();
		today.getTime();
		nikField.setText("");
		employeeTypeField.setSelectedIndex(0);
		nameField.setText("");
		ktpField.setText("");
		addressField.setText("");
		cityField.setText("");
		dobField.setDate(today);
		phoneField.setText("");
		genderField.setSelectedIndex(0);
		maritalField.setSelectedIndex(0);
		positionField.setSelectedIndex(0);
		departmentField.setSelectedIndex(0);
		divisionField.setSelectedIndex(0);
		startDateField.setDate(today);
	}

}
