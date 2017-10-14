package module.employeemanagement.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextArea;
import main.component.TextField;
import main.panel.MainPanel;
import module.employeemanagement.model.Employee;
import module.util.Bridging;
import module.util.EmailValidator;

public class ViewEmployeePanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ViewEmployeePanel.class.getName());
	private JLabel empCodeLbl;
	private JLabel empFNameLbl;
	private JLabel empLNameLbl;
	private JLabel npwpLbl;
	private JLabel ktpLbl;
	private JLabel ktpAddressLbl;
	private JLabel currentAddressLbl;
	private JLabel currentCityLbl;
	private JLabel dateOfBirthLbl;
	private JLabel emailLbl;
	private JLabel phoneNumberLbl;
	private JLabel emergencyContactNameLbl;
	private JLabel emergencyContactPhoneLbl;
	private JLabel emergencyContactRelationLbl;
	private JLabel genderLbl;
	private JLabel maritalLbl;
	private JLabel childrenLbl;
	private JLabel bankNameLbl;
	private JLabel bankAccountNoLbl;
	private JLabel rfidLbl;
	private JLabel statusLbl;
	private JLabel photoLbl;
	private JLabel imageLbl;
	
	private JLabel empFNameErrorLbl;
	private JLabel npwpErrorLbl;
	private JLabel ktpErrorLbl;
	private JLabel ktpAddressErrorLbl;
	private JLabel currentAddressErrorLbl;
	private JLabel currentCityErrorLbl;
	private JLabel emailErrorLbl;
	private JLabel phoneNumberErrorLbl;
	private JLabel emergencyContactNameErrorLbl;
	private JLabel emergencyContactPhoneErrorLbl;
	private JLabel emergencyContactRelationErrorLbl;
	private JLabel genderErrorLbl;
	private JLabel maritalErrorLbl;
	private JLabel childrenErrorLbl;
	private JLabel bankNameErrorLbl;
	private JLabel bankAccountNoErrorLbl;
	private JLabel rfidErrorLbl;
	private JLabel photoErrorLbl;
	
	
	private TextField empCodeField;
	private TextField fNameField;
	private TextField lNameField;
	private TextField npwpField;
	private TextField ktpField;
	private TextField currentCityField;
	private TextField emailField;
	private TextField bankNameField;
	private TextField bankAccountNoField;
	private TextField emergencyContactNameField;
	private TextField emergencyContactRelationField;

	private TextArea ktpAddressArea;
	private TextArea currentAddressArea;
	
	private JDateChooser dobChooser;
	
	private NumberField phoneNumberField;
	private NumberField emergencyPhoneNumberField;
	private NumberField childrenField;
	private NumberField rfidField;
	
	private JComboBox<String> genderCmb;
	private JComboBox<String> maritalCmb;
	
	private JPanel containerPnl;
	private JScrollPane scrollPane;
	
	private JFileChooser photoChooser;
	
	private String [] genderArr = {"-- Pilih --","Laki-Laki","Perempuan"};
	private String [] maritalArr = {"-- Pilih --","Belum Nikah","Nikah","Duda/Janda"};
	
	private JCheckBox statusActive;
	private JCheckBox statusInactive;
	private ButtonGroup statusGroup;
	
	private Map<Integer, String> genderMap;
	private Map<Integer, String> maritalMap;
	
	private FileNameExtensionFilter filter;
	
	private JButton editBtn;
	private JButton deleteBtn;
	private JButton backBtn;
	private JButton uploadBtn;
	
	
	private ViewEmployeePanel createEmployeePanel;
	
	private Employee emp;
	
	public ViewEmployeePanel(){
		createGUI();
		setData();
		listener();
	}
	
	private void createGUI(){
		createEmployeePanel = this;
		setLayout(null);
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1100,1400));
		containerPnl.setLayout(null);
		
		scrollPane = new JScrollPane(containerPnl);
		scrollPane.setBounds(0,0,1100,630);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Karyawan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		containerPnl.add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("INPUT KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		containerPnl.add(lblHeader);
		
		empCodeLbl = new JLabel("Kode Karyawan");
		empCodeLbl.setBounds(30,120,150,20);
		containerPnl.add(empCodeLbl);
		
		empCodeField = new TextField();
		empCodeField.setBounds(180,120,150,20);
		empCodeField.setEnabled(false);
		containerPnl.add(empCodeField);
		
		empFNameLbl = new JLabel("<html>Nama Pertama<font color='red'>*</font></html>");
		empFNameLbl.setBounds(30,160,150,20);
		containerPnl.add(empFNameLbl);
		
		fNameField = new TextField();
		fNameField.setBounds(180,160,150,20);
		containerPnl.add(fNameField);
		
		
		empFNameErrorLbl = new JLabel();
		empFNameErrorLbl.setBounds(340,160,150,20);
		containerPnl.add(empFNameErrorLbl);
		
		empLNameLbl = new JLabel("<html>Nama Akhir<font color='red'></font></html>");
		empLNameLbl.setBounds(30,200,150,20);
		containerPnl.add(empLNameLbl);
		
		lNameField = new TextField();
		lNameField.setBounds(180,200,150,20);
		containerPnl.add(lNameField);
		
		npwpLbl = new JLabel("<html>NPWP<font color='red'>*</font></html>");
		npwpLbl.setBounds(30,240,150,20);
		containerPnl.add(npwpLbl);
		
		npwpField = new TextField();
		npwpField.setBounds(180,240,150,20);
		containerPnl.add(npwpField);
		
		npwpErrorLbl = new JLabel();
		npwpErrorLbl.setBounds(340,240,150,20);
		containerPnl.add(npwpErrorLbl);
		
		ktpLbl = new JLabel("<html>NO KTP<font color='red'>*</font></html>");
		ktpLbl.setBounds(30,280,150,20);
		containerPnl.add(ktpLbl);
		
		ktpField = new TextField();
		ktpField.setBounds(180,280,150,20);
		containerPnl.add(ktpField);
		
		ktpErrorLbl = new JLabel();
		ktpErrorLbl.setBounds(340,280,150,20);
		containerPnl.add(ktpErrorLbl);
		
		ktpAddressLbl = new JLabel("<html>Alamat KTP<font color='red'>*</font></html>");
		ktpAddressLbl.setBounds(30,320,150,20);
		containerPnl.add(ktpAddressLbl);
		
		ktpAddressArea = new TextArea();
		ktpAddressArea.setBounds(180,320,200,50);
		containerPnl.add(ktpAddressArea);
		
		ktpAddressErrorLbl = new JLabel();
		ktpAddressErrorLbl.setBounds(390,320,150,20);
		containerPnl.add(ktpAddressErrorLbl);
		
		currentAddressLbl = new JLabel("<html>Alamat Domisili<font color='red'>*</font></html>");
		currentAddressLbl.setBounds(30,390,150,20);
		containerPnl.add(currentAddressLbl);
		
		currentAddressArea = new TextArea();
		currentAddressArea.setBounds(180,390,200,50);
		containerPnl.add(currentAddressArea);
		
		currentAddressErrorLbl = new JLabel();
		currentAddressErrorLbl.setBounds(390,390,150,20);
		containerPnl.add(currentAddressErrorLbl);
		
		currentCityLbl = new JLabel("<html>Kota<font color='red'>*</font></html>");
		currentCityLbl.setBounds(30,460,150,20);
		containerPnl.add(currentCityLbl);
		
		currentCityField = new TextField();
		currentCityField.setBounds(180,460,150,20);
		containerPnl.add(currentCityField);
		
		currentCityErrorLbl = new JLabel();
		currentCityErrorLbl.setBounds(340,460,150,20);
		containerPnl.add(currentCityErrorLbl);
		
		
		dateOfBirthLbl = new JLabel("<html>Tanggal Lahir<font color='red'>*</font></html>");
		dateOfBirthLbl.setBounds(30,500,150,20);
		containerPnl.add(dateOfBirthLbl);
		
		dobChooser = new JDateChooser();
		dobChooser.setBounds(180,500,150,20);
		dobChooser.setDateFormatString("dd-MM-yyyy");
		dobChooser.getDateEditor().setEnabled(false);
		dobChooser.setDate(new Date());
		containerPnl.add(dobChooser);
		
		emailLbl = new JLabel("<html>Email<font color='red'>*</font></html>");
		emailLbl.setBounds(30,540,150,20);
		containerPnl.add(emailLbl);
		
		emailField = new TextField();
		emailField.setBounds(180,540,150,20);
		containerPnl.add(emailField);
		
		emailErrorLbl = new JLabel();
		emailErrorLbl.setBounds(340,540,150,20);
		containerPnl.add(emailErrorLbl);
		
		phoneNumberLbl = new JLabel("<html>Nomor Telpon<font color='red'>*</font></html>");
		phoneNumberLbl.setBounds(30,580,150,20);
		containerPnl.add(phoneNumberLbl);
		
		phoneNumberField = new NumberField(15);
		phoneNumberField.setBounds(180,580,150,20);
		containerPnl.add(phoneNumberField);
		
		phoneNumberErrorLbl = new JLabel();
		phoneNumberErrorLbl.setBounds(340,580,150,20);
		containerPnl.add(phoneNumberErrorLbl);
		
		emergencyContactNameLbl = new JLabel("<html>Nama Kontak Darurat<font color='red'>*</font></html>");
		emergencyContactNameLbl.setBounds(30,620,150,20);
		containerPnl.add(emergencyContactNameLbl);
		
		emergencyContactNameField = new TextField();
		emergencyContactNameField.setBounds(180,620,150,20);
		containerPnl.add(emergencyContactNameField);
		
		emergencyContactNameErrorLbl = new JLabel();
		emergencyContactNameErrorLbl.setBounds(340,620,150,20);
		containerPnl.add(emergencyContactNameErrorLbl);
		
		emergencyContactPhoneLbl = new JLabel("<html>Nomor Telpon Kontak Darurat<font color='red'>*</font></html>");
		emergencyContactPhoneLbl.setBounds(30,660,150,20);
		containerPnl.add(emergencyContactPhoneLbl);
		
		emergencyPhoneNumberField = new NumberField(15);
		emergencyPhoneNumberField.setBounds(180,660,150,20);
		containerPnl.add(emergencyPhoneNumberField);
		
		emergencyContactPhoneErrorLbl = new JLabel();
		emergencyContactPhoneErrorLbl.setBounds(340,660,150,20);
		containerPnl.add(emergencyContactPhoneErrorLbl);
		
		emergencyContactRelationLbl = new JLabel("<html>Relasi Kontak Darurat<font color='red'>*</font></html>");
		emergencyContactRelationLbl.setBounds(30,700,150,20);
		containerPnl.add(emergencyContactPhoneLbl);
		
		emergencyContactRelationField = new TextField();
		emergencyContactRelationField.setBounds(180,700,150,20);
		containerPnl.add(emergencyContactRelationField);
		
		emergencyContactRelationErrorLbl = new JLabel();
		emergencyContactRelationErrorLbl.setBounds(340,700,150,20);
		containerPnl.add(emergencyContactRelationErrorLbl);
		
		
		genderLbl = new JLabel("<html>Jenis Kelamin<font color='red'>*</font></html>");
		genderLbl.setBounds(30,740,150,20);
		containerPnl.add(genderLbl);
		
		genderCmb = new JComboBox<>(genderArr);
		genderCmb.setBounds(180,740,150,20);
		containerPnl.add(genderCmb);
		
		genderErrorLbl = new JLabel();
		genderErrorLbl.setBounds(340,740,150,20);
		containerPnl.add(genderErrorLbl);
		
		maritalLbl = new JLabel("<html>Status Nikah<font color='red'>*</font></html>");
		maritalLbl.setBounds(30,780,150,20);
		containerPnl.add(maritalLbl);
		
		maritalCmb = new JComboBox<>(maritalArr);
		maritalCmb.setBounds(180,780,150,20);
		containerPnl.add(maritalCmb);
		
		maritalErrorLbl = new JLabel();
		maritalErrorLbl.setBounds(340,780,150,20);
		containerPnl.add(maritalErrorLbl);
		
		childrenLbl = new JLabel("<html>Jumlah Anak<font color='red'>*</font></html>");
		childrenLbl.setBounds(30,820,150,20);
		containerPnl.add(childrenLbl);
		
		childrenField = new NumberField(2);
		childrenField.setBounds(180,820,150,20);
		containerPnl.add(childrenField);
		
		childrenErrorLbl = new JLabel();
		childrenErrorLbl.setBounds(340,820,150,20);
		containerPnl.add(childrenErrorLbl);
		
		bankNameLbl = new JLabel("<html>Nama Bank<font color='red'>*</font></html>");
		bankNameLbl.setBounds(30,860,150,20);
		containerPnl.add(bankNameLbl);
		
		bankNameField = new TextField();
		bankNameField.setBounds(180,860,150,20);
		containerPnl.add(bankNameField);
		
		bankNameErrorLbl = new JLabel();
		bankNameErrorLbl.setBounds(340,860,150,20);
		containerPnl.add(bankNameErrorLbl);
		
		bankAccountNoLbl = new JLabel("<html>Nomor Rekening<font color='red'>*</font></html>");
		bankAccountNoLbl.setBounds(30,900,150,20);
		containerPnl.add(bankAccountNoLbl);
		
		bankAccountNoField = new TextField();
		bankAccountNoField.setBounds(180,900,150,20);
		containerPnl.add(bankAccountNoField);
		
		bankAccountNoErrorLbl = new JLabel();
		bankAccountNoErrorLbl.setBounds(340,900,150,20);
		containerPnl.add(bankAccountNoErrorLbl);
		
		rfidLbl = new JLabel("<html>RFID<font color='red'>*</font></html>");
		rfidLbl.setBounds(30,940,150,20);
		containerPnl.add(rfidLbl);
		
		rfidField = new NumberField(15);
		rfidField.setBounds(180,940,150,20);
		containerPnl.add(rfidField);
		
		rfidErrorLbl = new JLabel();
		rfidErrorLbl.setBounds(340,940,150,20);
		containerPnl.add(rfidErrorLbl);
		
		statusLbl = new JLabel("Status");
		statusLbl.setBounds(30,980,150,20);
		containerPnl.add(statusLbl);
		
		statusActive = new JCheckBox("Aktif");
		statusActive.setBounds(180,980,70,20);
		statusActive.setSelected(true);
		containerPnl.add(statusActive);
		
		statusInactive = new JCheckBox("Tidak Aktif");
		statusInactive.setBounds(260,980,150,20);
		containerPnl.add(statusInactive);
		
		statusGroup = new ButtonGroup();
		statusGroup.add(statusActive);
		statusGroup.add(statusInactive);
		
		photoLbl = new JLabel("<html>Foto<font color='red'>*</font></html>");
		photoLbl.setBounds(30,1020,150,20);
		containerPnl.add(photoLbl);
		
		uploadBtn = new JButton("<html>Pilih Foto<font color='red'></font></html>");
		uploadBtn.setBounds(180,1020,150,20);
		containerPnl.add(uploadBtn);
		
		photoErrorLbl = new JLabel();
		photoErrorLbl.setBounds(340,1020,150,20);
		containerPnl.add(photoErrorLbl);
		
		imageLbl = new JLabel();
		imageLbl.setBounds(150,1060,200,200);
		containerPnl.add(imageLbl);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(850,1300,150,30);
		containerPnl.add(editBtn);
		
		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(690,1300,150,30);
		containerPnl.add(deleteBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,1300,150,30);
		containerPnl.add(backBtn);
		
		
	}
	
	private void setData(){
		genderMap = new HashMap<>();
		genderMap.put(1, "Laki-Laki");
		genderMap.put(2, "Perempuan");
		
		maritalMap = new HashMap<>();
		maritalMap.put(1, "Belum Nikah");
		maritalMap.put(2, "Nikah");
		maritalMap.put(3, "Duda/Janda");
		
		empCodeField.setText(ServiceFactory.getEmployeeManagementBL().lastEmpCode());
		
	}
	
	private void listener(){
		uploadBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
				photoChooser = new JFileChooser();
				photoChooser.setFileFilter(filter);
				photoChooser.addChoosableFileFilter(filter);
				
				try {
					int result = photoChooser.showOpenDialog(createEmployeePanel);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = photoChooser.getSelectedFile();
						setPhoto(file);
					} else {
						log.info("Open command cancelled by user.");
					}
				} catch (Exception e2) {
					DialogBox.showError("Gagal Memproses Foto");
					log.info("Failed processing file"+e2.getMessage());
					e2.printStackTrace();
				}
				
			}
		});
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.CreateEmployeePanel",emp);
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.ListEmployeePanel");
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showDeleteChoice()==JOptionPane.YES_OPTION)ServiceFactory.getEmployeeManagementBL().delete(emp);
			}
		});
	}
	
	private void setPhoto(File file) throws IOException{
		ImageIcon icon = new ImageIcon(ImageIO.read(file));
		Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon newIcon = new ImageIcon(image);
		imageLbl.setIcon(newIcon);
	}
	


	@Override
	public void invokeObjects(Object... objects) {
		if(objects.length>0) emp = (Employee) objects[0];
		try {
			empCodeField.setText(emp.getEmpCode());
			fNameField.setText(emp.getFname());
			lNameField.setText(emp.getLname());
			ktpField.setText(emp.getKtp());
			npwpField.setText(emp.getNpwp());
			ktpAddressArea.getTextArea().setText(emp.getKtpAddress());
			currentAddressArea.getTextArea().setText(emp.getCurrentAddress());
			currentCityField.setText(emp.getCurrentCity());
			dobChooser.setDate(emp.getBirthDate());
			emailField.setText(emp.getEmail());
			phoneNumberField.setText(emp.getPhone());
			emergencyContactNameField.setText(emp.getEmergencyContact());
			emergencyPhoneNumberField.setText(emp.getEmergencyPhone());
			emergencyContactRelationField.setText(emp.getEmergencyRelation());
			genderCmb.setSelectedItem(genderMap.get(emp.getGenderId()));
			maritalCmb.setSelectedItem(maritalMap.get(emp.getMaritalId()));
			childrenField.setText(emp.getTotalChild().toString());
			bankNameField.setText(emp.getBankName());
			bankAccountNoField.setText(emp.getBankAccount());
			rfidField.setText(emp.getRfid().toString());
			File file = new File("image/"+emp.getImage());
			setPhoto(file);
			
			empCodeField.setEnabled(false);
			fNameField.setEnabled(false);
			lNameField.setEnabled(false);
			ktpField.setEnabled(false);
			npwpField.setEnabled(false);
			ktpAddressArea.getTextArea().setEnabled(false);
			currentAddressArea.getTextArea().setEnabled(false);
			currentCityField.setEnabled(false);
			dobChooser.setEnabled(false);
			emailField.setEnabled(false);
			phoneNumberField.setEnabled(false);
			emergencyContactNameField.setEnabled(false);
			emergencyContactRelationField.setEnabled(false);
			emergencyPhoneNumberField.setEnabled(false);
			genderCmb.setEnabled(false);
			maritalCmb.setEnabled(false);
			childrenField.setEnabled(false);
			bankNameField.setEnabled(false);
			bankAccountNoField.setEnabled(false);
			rfidField.setEnabled(false);
			photoChooser.setEnabled(false);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
