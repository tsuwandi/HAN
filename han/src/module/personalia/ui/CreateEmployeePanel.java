package module.personalia.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.ImageFileView;
import main.component.ImageFilter;
import main.component.ImagePanel;
import main.component.ImagePreview;
import main.component.NumberField;
import main.panel.MainPanel;
import module.personalia.model.EmpPosition;
import module.personalia.model.Employee;
import module.personalia.model.Gender;
import module.personalia.model.Marital;

public class CreateEmployeePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JTextField npwpField;
	private JTextField ktpField;
	private JTextArea ktpAddressField;
	private JTextArea domicileField;
	private JTextField originCityField;
	private JDateChooser bornDateField;
	private JTextField emailField;
	private JTextField telpField;
	private ComboBox<Gender> genderCmbox;
	private ComboBox<Marital> maritalCmbox;
	private NumberField numberChildField;
	private JTextField bankNameField;
	private JTextField bankAccountField;
	private ComboBox<String> shiftGroupCmbox;
	private JRadioButton activeRdbtn;
	private JRadioButton notActiveRdbtn;
	private ImagePanel photoPnl;
	private JFileChooser jfc;
	private BufferedImage image = null;
	private JTable empPositionTable;
	private List<EmpPosition> empPositions = new ArrayList<>();
	private EmployeePositionHistoryTableModel employeePositionHistoryTableModel;
	private PopUpPositionHistoryPanel popUpPositionHistoryPanel;

	public CreateEmployeePanel() {
		setLayout(null);
		
		JPanel containerPanel = new JPanel();
		containerPanel.setPreferredSize(new Dimension(1024, 1536));
		containerPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(containerPanel);
		scrollPane.setBounds(0, 0, 1024, 630);
		add(scrollPane);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Karyawan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		containerPanel.add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 180, 25);
		containerPanel.add(lblHeader);
		// nik
		JLabel label = new JLabel("NIK");
		label.setBounds(30, 80, 100, 30);
		containerPanel.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		containerPanel.add(label_1);
		
		employeeCodeField = new JTextField();
		employeeCodeField.setBounds(140, 80, 200, 30);
		employeeCodeField.setEditable(false);
		employeeCodeField.setEnabled(false);
		containerPanel.add(employeeCodeField);
		// nama
		JLabel label_2 = new JLabel("<html>Nama Karyawan<font color='red'> * </font></html>");
		label_2.setBounds(30, 120, 100, 30);
		containerPanel.add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		containerPanel.add(label_3);
		
		employeeNameField = new JTextField();
		employeeNameField.setBounds(140, 120, 200, 30);
		containerPanel.add(employeeNameField);
		// npwp
		JLabel lblnpwp = new JLabel("<html>NPWP<font color='red'> * </font></html>");
		lblnpwp.setBounds(30, 160, 100, 30);
		containerPanel.add(lblnpwp);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 160, 10, 30);
		containerPanel.add(label_4);
		
		npwpField = new JTextField();
		npwpField.setBounds(140, 160, 200, 30);
		containerPanel.add(npwpField);
		// ktp
		JLabel lblnomorKtp = new JLabel("<html>Nomor KTP<font color='red'> * </font></html>");
		lblnomorKtp.setBounds(30, 200, 100, 30);
		containerPanel.add(lblnomorKtp);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 200, 10, 30);
		containerPanel.add(label_7);
		
		ktpField = new JTextField();
		ktpField.setBounds(140, 200, 200, 30);
		containerPanel.add(ktpField);
		// alamat ktp
		JLabel lblalamatKtp = new JLabel("<html>Alamat KTP<font color='red'> * </font></html>");
		lblalamatKtp.setBounds(30, 240, 100, 30);
		containerPanel.add(lblalamatKtp);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(130, 240, 10, 30);
		containerPanel.add(label_8);
		
		ktpAddressField = new JTextArea();
		ktpAddressField.setBounds(140, 240, 600, 30);
		containerPanel.add(ktpAddressField);
		// alamat domisili
		JLabel lblalamatDomisili = new JLabel("<html>Alamat Domisili<font color='red'> * </font></html>");
		lblalamatDomisili.setBounds(30, 280, 100, 30);
		containerPanel.add(lblalamatDomisili);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 280, 10, 30);
		containerPanel.add(label_5);
		
		domicileField = new JTextArea();
		domicileField.setBounds(140, 280, 600, 30);
		containerPanel.add(domicileField);
		// kota asal
		JLabel lblkotaAsal = new JLabel("<html>Kota Asal<font color='red'> * </font></html>");
		lblkotaAsal.setBounds(30, 320, 100, 30);
		containerPanel.add(lblkotaAsal);

		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		containerPanel.add(label_6);
		
		originCityField = new JTextField();
		originCityField.setBounds(140, 320, 200, 30);
		containerPanel.add(originCityField);
		// tanggal lahir
		JLabel lbltanggalLahir = new JLabel("<html>Tanggal Lahir<font color='red'> * </font></html>");
		lbltanggalLahir.setBounds(30, 360, 100, 30);
		containerPanel.add(lbltanggalLahir);
		
		JLabel label_10 = new JLabel(":");
		label_10.setBounds(130, 360, 10, 30);
		containerPanel.add(label_10);
		
		bornDateField = new JDateChooser();
		bornDateField.setBounds(140, 360, 200, 30);
		containerPanel.add(bornDateField);
		// email
		JLabel lblemail = new JLabel("<html>Email<font color='red'> * </font></html>");
		lblemail.setBounds(30, 400, 100, 30);
		containerPanel.add(lblemail);
		
		JLabel label_11 = new JLabel(":");
		label_11.setBounds(130, 400, 10, 30);
		containerPanel.add(label_11);
		
		emailField = new JTextField();
		emailField.setBounds(140, 400, 200, 30);
		containerPanel.add(emailField);
		// nomer telepon
		JLabel lblnomerTelepon = new JLabel("<html>Nomer Telepon<font color='red'> * </font></html>");
		lblnomerTelepon.setBounds(30, 440, 100, 30);
		containerPanel.add(lblnomerTelepon);
		
		JLabel label_12 = new JLabel(":");
		label_12.setBounds(130, 440, 10, 30);
		containerPanel.add(label_12);
		
		telpField = new JTextField();
		telpField.setBounds(140, 440, 200, 30);
		containerPanel.add(telpField);
		// gender
		JLabel lblgender = new JLabel("<html>Gender<font color='red'> * </font></html>");
		lblgender.setBounds(30, 480, 100, 30);
		containerPanel.add(lblgender);
		
		JLabel label_13 = new JLabel(":");
		label_13.setBounds(130, 480, 10, 30);
		containerPanel.add(label_13);
		
		genderCmbox = new ComboBox<>();
		genderCmbox.setBounds(140, 480, 200, 30);
		containerPanel.add(genderCmbox);
		// status perkawinan
		JLabel lblstatusPerkawinan = new JLabel("<html>Status Perkawinan<font color='red'> * </font></html>");
		lblstatusPerkawinan.setBounds(30, 520, 100, 30);
		containerPanel.add(lblstatusPerkawinan);
		
		JLabel label_14 = new JLabel(":");
		label_14.setBounds(130, 520, 10, 30);
		containerPanel.add(label_14);
		
		maritalCmbox = new ComboBox<>();
		maritalCmbox.setBounds(140, 520, 200, 30);
		containerPanel.add(maritalCmbox);
		// jumlah tanggungan anak
		JLabel lbljumlahTanggunganAnak = new JLabel("<html>Jumlah Tanggungan Anak<font color='red'> * </font></html>");
		lbljumlahTanggunganAnak.setBounds(30, 560, 100, 30);
		containerPanel.add(lbljumlahTanggunganAnak);
		
		JLabel label_15 = new JLabel(":");
		label_15.setBounds(130, 560, 10, 30);
		containerPanel.add(label_15);
		
		numberChildField = new NumberField(2);
		numberChildField.setBounds(140, 560, 200, 30);
		containerPanel.add(numberChildField);
		// nama bank
		JLabel lblnamaBank = new JLabel("<html>Nama Bank<font color='red'> * </font></html>");
		lblnamaBank.setBounds(30, 600, 100, 30);
		containerPanel.add(lblnamaBank);
		
		JLabel label_16 = new JLabel(":");
		label_16.setBounds(130, 600, 10, 30);
		containerPanel.add(label_16);
		
		bankNameField = new JTextField();
		bankNameField.setBounds(140, 600, 200, 30);
		containerPanel.add(bankNameField);
		// nomer rekening bank
		JLabel lblnomorRekeningBank = new JLabel("<html>Nomor Rekening Bank<font color='red'> * </font></html>");
		lblnomorRekeningBank.setBounds(30, 640, 100, 30);
		containerPanel.add(lblnomorRekeningBank);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 640, 10, 30);
		containerPanel.add(label_9);
		
		bankAccountField = new JTextField();
		bankAccountField.setBounds(140, 640, 200, 30);
		containerPanel.add(bankAccountField);
		// group shift kerja
		JLabel lblgroupShiftKerja = new JLabel("<html>Group Shift Kerja<font color='red'> * </font></html>");
		lblgroupShiftKerja.setBounds(30, 680, 100, 30);
		containerPanel.add(lblgroupShiftKerja);
		
		JLabel label_18 = new JLabel(":");
		label_18.setBounds(130, 680, 10, 30);
		containerPanel.add(label_18);
		
		shiftGroupCmbox = new ComboBox<>();
		shiftGroupCmbox.setBounds(140, 680, 200, 30);
		containerPanel.add(shiftGroupCmbox);
		// status
		JLabel statusLbl = new JLabel("<html>Status<font color='red'> * </font></html>");
		statusLbl.setBounds(30, 720, 100, 30);
		containerPanel.add(statusLbl);
		
		JLabel label_17 = new JLabel(":");
		label_17.setBounds(130, 720, 10, 30);
		containerPanel.add(label_17);
		
		activeRdbtn = new JRadioButton("Aktif");
		activeRdbtn.setBounds(140, 720, 90, 30);
		containerPanel.add(activeRdbtn);
		
		notActiveRdbtn = new JRadioButton("Non Aktif");
		notActiveRdbtn.setBounds(240, 720, 90, 30);
		containerPanel.add(notActiveRdbtn);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(activeRdbtn);
		buttonGroup.add(notActiveRdbtn);
		activeRdbtn.setSelected(true);
		// upload foto
		JLabel photoLbl = new JLabel("<html>Upload Foto<font color='red'> * </font></html>");
		photoLbl.setBounds(30, 760, 100, 30);
		containerPanel.add(photoLbl);
		
		JLabel label_19 = new JLabel(":");
		label_19.setBounds(130, 760, 10, 30);
		containerPanel.add(label_19);
		
		photoPnl = new ImagePanel();
		photoPnl.setBounds(140, 760, 236, 300);
		photoPnl.setBackground(Color.BLACK);
		containerPanel.add(photoPnl);
		
		final JButton searchfileBtn = new JButton("Cari File");
		searchfileBtn.setBounds(140, 1070, 75, 30);
		containerPanel.add(searchfileBtn);
		
		jfc = new JFileChooser();
		jfc.addChoosableFileFilter(new ImageFilter());
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setFileView(new ImageFileView());
		jfc.setAccessory(new ImagePreview(jfc));
		
		searchfileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchfileBtn) {
					int returnValue =  jfc.showDialog(CreateEmployeePanel.this, "Upload");
					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = jfc.getSelectedFile();
						try {
							image = ImageIO.read(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						photoPnl.setImage(image);
						photoPnl.validate();
						photoPnl.updateUI();
					}
					else {
						System.out.println("gagal");
					}
					jfc.setSelectedFile(null);
				}
			}
		});
		
		JButton uploadBtn = new JButton("Upload");
		uploadBtn.setBounds(225, 1070, 75, 30);
		containerPanel.add(uploadBtn);
		
		// history jabatan
		JLabel positionHistoryLbl = new JLabel("<html>Riwayat Jabatan<font color='red'> * </font></html>");
		positionHistoryLbl.setBounds(30, 1110, 100, 30);
		containerPanel.add(positionHistoryLbl);
		
		JLabel label_20 = new JLabel(":");
		label_20.setBounds(130, 1110, 10, 30);
		containerPanel.add(label_20);
		
		JButton addHistoryPositionBtn = new JButton("Tambah");
		addHistoryPositionBtn.setBounds(140, 1110, 75, 30);
		containerPanel.add(addHistoryPositionBtn);
		addHistoryPositionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addPositionHistory();
			}
		});
		
		JButton deleteHistoryPositionBtn = new JButton("Hapus");
		deleteHistoryPositionBtn.setBounds(225, 1110, 75, 30);
		containerPanel.add(deleteHistoryPositionBtn);
		
		deleteHistoryPositionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deletePositionHistory();
			}
		});
		
		// tabel history jabatan
		JScrollPane empPositionScrollPane = new JScrollPane();
		empPositionScrollPane.setBounds(30, 1150, 900, 300);
		containerPanel.add(empPositionScrollPane);
		
		empPositionTable = new JTable();
		empPositionScrollPane.setViewportView(empPositionTable);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(900, 1460, 90, 30);
		containerPanel.add(saveBtn);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		getLastID();
		empPositionTableConfig();
		getData();
	}

	protected void deletePositionHistory() {
		int row = empPositionTable.getSelectedRow();
		
		empPositions.remove(row);
		employeePositionHistoryTableModel.setEmployeePositions(empPositions);
		empPositionTable.updateUI();
	}

	private void getData() {
		genderCmbox.setList(ServiceFactory.getPersonaliaBL().getGenders(""));
		maritalCmbox.setList(ServiceFactory.getPersonaliaBL().getMaritals(""));
	}

	protected void addPositionHistory() {
		popUpPositionHistoryPanel = new PopUpPositionHistoryPanel(this);
		popUpPositionHistoryPanel.setLocationRelativeTo(null);
		popUpPositionHistoryPanel.setTitle("History Jabatan");
		popUpPositionHistoryPanel.setVisible(true);
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("EMP");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdEmployee()));
		employeeCodeField.setText(lastId.toString());
	}

	protected void save() {
		Employee employee = new Employee();
		employee.setEmpCode(employeeCodeField.getText());
		employee.setName(employeeNameField.getText());
		employee.setKtp(ktpField.getText());
		employee.setKtpAddress(ktpAddressField.getText());
		employee.setCurrentAddress(domicileField.getText());
		employee.setCurrentCity("");
		employee.setHometown(originCityField.getText());
		employee.setTotalChild(Integer.parseInt(numberChildField.getText()));
		employee.setBankCode(bankNameField.getText());
		employee.setBankAccountNumber(bankAccountField.getText());
		//employee.setGroupShiftCode(shiftGroupCmbox.getDataIndex());	
		employee.setBirthDate(bornDateField.getDate());
		employee.setEmail(emailField.getText());
		employee.setPhone(telpField.getText());
		employee.setSalary(BigDecimal.ZERO);
		employee.setGenderId(genderCmbox.getDataIndex().getId());
		employee.setMaritalId(maritalCmbox.getDataIndex().getId());
		employee.setInputDate(new Date());
		employee.setInputBy("");
		employee.setEditDate(new Date());
		employee.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().saveEmployee(employee);
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
			MainPanel.changePanel("module.personalia.ui.EmployeeConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		employeeNameField.setText("");
	}
	
	class EmployeePositionHistoryTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -8720800573929798216L;
		private List<EmpPosition> employeePositions;

		public EmployeePositionHistoryTableModel(List<EmpPosition> employeePositions) {
			this.employeePositions = employeePositions;
		}
		
		public void setEmployeePositions(List<EmpPosition> employeePositions) {
			this.employeePositions = employeePositions;
		}

		@Override
		public int getColumnCount() {
			return 10;
		}

		@Override
		public int getRowCount() {
			return employeePositions == null ? 0 : employeePositions.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			EmpPosition employeePosition = employeePositions.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return employeePositions.indexOf(employeePosition) + 1;
			case 1:
				return employeePosition.getStartDate();
			case 2:
				return employeePosition.getEndDate();
			case 3:
				return employeePosition.getProbation();
			case 4:
				return employeePosition.getMsPosition().getName();
			case 5:
				return employeePosition.getMsPosition().getDepartment().getName();
			case 6:
				return employeePosition.getMsPosition().getDivision().getName();
			case 7:
				return employeePosition.getEmployeeType().getName();
			case 8:
				return employeePosition.getReferenceDoc();
			case 9:
				return employeePosition.getNotes();
			default:
				return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return Integer.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			case 8:
				return String.class;
			case 9:
				return String.class;
			default:
				return String.class;
			}
		}

		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "No";
			case 1:
				return "Mulai Kerja";
			case 2:
				return "Berhenti Kerja";
			case 3:
				return "Masa Percobaan (bulan)";
			case 4:
				return "Jabatan";
			case 5:
				return "Department";
			case 6:
				return "Divisi";
			case 7:
				return "Tipe Karyawan";
			case 8:
				return "Dokumen Referensi";
			case 9:
				return "Keterangan";
			default:
				return "";
			}
		}
	}
	
	private void empPositionTableConfig() {
		empPositionTable.setFocusable(false);
		empPositionTable.setAutoCreateRowSorter(true);
		employeePositionHistoryTableModel = new EmployeePositionHistoryTableModel(empPositions);
		empPositionTable.setModel(employeePositionHistoryTableModel);
		
		TableColumn noColumn = empPositionTable.getColumnModel().getColumn(0);
		TableColumn startDateColumn = empPositionTable.getColumnModel().getColumn(1);
		TableColumn endDateColumn = empPositionTable.getColumnModel().getColumn(2);
		TableColumn prohibitionColumn = empPositionTable.getColumnModel().getColumn(3);
		TableColumn positionColumn = empPositionTable.getColumnModel().getColumn(4);
		TableColumn departmentColumn = empPositionTable.getColumnModel().getColumn(5);
		TableColumn divisionColumn = empPositionTable.getColumnModel().getColumn(6);
		TableColumn employeeTypeColumn = empPositionTable.getColumnModel().getColumn(7);
		TableColumn referenceDocColumn = empPositionTable.getColumnModel().getColumn(8);
		TableColumn descColumn = empPositionTable.getColumnModel().getColumn(9);
		
		noColumn.setPreferredWidth(30);
		noColumn.setMinWidth(20);
		noColumn.setMaxWidth(40);
		
		startDateColumn.setPreferredWidth(100);
		startDateColumn.setMinWidth(90);
		startDateColumn.setMaxWidth(110);
		
		endDateColumn.setPreferredWidth(100);
		endDateColumn.setMinWidth(90);
		endDateColumn.setMaxWidth(110);
		
		prohibitionColumn.setPreferredWidth(30);
		prohibitionColumn.setMinWidth(20);
		prohibitionColumn.setMaxWidth(40);
		
		positionColumn.setPreferredWidth(100);
		positionColumn.setMinWidth(90);
		positionColumn.setMaxWidth(110);
		
		departmentColumn.setPreferredWidth(100);
		departmentColumn.setMinWidth(90);
		departmentColumn.setMaxWidth(110);
		
		divisionColumn.setPreferredWidth(100);
		divisionColumn.setMinWidth(90);
		divisionColumn.setMaxWidth(110);
		
		employeeTypeColumn.setPreferredWidth(100);
		employeeTypeColumn.setMinWidth(90);
		employeeTypeColumn.setMaxWidth(110);
		
		referenceDocColumn.setPreferredWidth(100);
		referenceDocColumn.setMinWidth(90);
		referenceDocColumn.setMaxWidth(110);
		
		descColumn.setPreferredWidth(100);
		descColumn.setMinWidth(90);
		descColumn.setMaxWidth(110);
	}

	public List<EmpPosition> getEmpPositions() {
		return empPositions;
	}

	public void setEmpPositions(List<EmpPosition> empPositions) {
		this.empPositions = empPositions;
	}

	public JTable getEmpPositionTable() {
		return empPositionTable;
	}

	public PopUpPositionHistoryPanel getPopUpPositionHistoryPanel() {
		return popUpPositionHistoryPanel;
	}
	
	public EmployeePositionHistoryTableModel getEmployeePositionHistoryTableModel() {
		return employeePositionHistoryTableModel;
	}
}