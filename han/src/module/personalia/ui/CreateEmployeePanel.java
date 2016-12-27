package module.personalia.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Division;
import module.personalia.model.EmployeePosition;

public class CreateEmployeePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField nameField;
	private JTextField codeField;
	private JTextField npwpField;
	private JTextField ktpField;
	private JTextField ktpAddressField;
	private JTextField domicileField;
	private JTextField originCityField;
	private JDateChooser bornDateField;
	private JTextField emailField;
	private JTextField telpField;
	private ComboBox<String> genderCmbox;
	private ComboBox<String> maritalCmbox;
	private JTextField numberChildField;
	private JTextField bankNameField;
	private JTextField bankAccountField;
	private ComboBox<String> shiftGroupCmbox;
	private JRadioButton activeRdbtn;
	private JRadioButton notActiveRdbtn;
	private JPanel photoPnl;

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
		
		codeField = new JTextField();
		codeField.setBounds(140, 80, 200, 30);
		codeField.setEditable(false);
		codeField.setEnabled(false);
		containerPanel.add(codeField);
		// nama
		JLabel label_2 = new JLabel("<html>Nama Karyawan<font color='red'> * </font></html>");
		label_2.setBounds(30, 120, 100, 30);
		containerPanel.add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		containerPanel.add(label_3);
		
		nameField = new JTextField();
		nameField.setBounds(140, 120, 200, 30);
		containerPanel.add(nameField);
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
		
		ktpAddressField = new JTextField();
		ktpAddressField.setBounds(140, 240, 200, 30);
		containerPanel.add(ktpAddressField);
		// alamat domisili
		JLabel lblalamatDomisili = new JLabel("<html>Alamat Domisili<font color='red'> * </font></html>");
		lblalamatDomisili.setBounds(30, 280, 100, 30);
		containerPanel.add(lblalamatDomisili);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 280, 10, 30);
		containerPanel.add(label_5);
		
		domicileField = new JTextField();
		domicileField.setBounds(140, 280, 200, 30);
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
		
		numberChildField = new JTextField();
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
		
		photoPnl = new JPanel();
		photoPnl.setBounds(140, 760, 236, 300);
		photoPnl.setBackground(Color.BLACK);
		containerPanel.add(photoPnl);
		
		JButton searchfileBtn = new JButton("Cari File");
		searchfileBtn.setBounds(140, 1070, 75, 30);
		containerPanel.add(searchfileBtn);
		
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
		
		// tabel history jabatan
		JScrollPane historyPositionScroolPane = new JScrollPane();
		historyPositionScroolPane.setBounds(30, 1150, 900, 300);
		containerPanel.add(historyPositionScroolPane);
		
		JTable positionHistoryTable = new JTable();
		positionHistoryTable.setFocusable(false);
		positionHistoryTable.setAutoCreateRowSorter(true);
		historyPositionScroolPane.setViewportView(positionHistoryTable);
		
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
	}

	protected void addPositionHistory() {
		MainPanel.changePanel("module.personalia.ui.CreatePositionHistoryPanel");
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("EMP");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		codeField.setText(lastId.toString());
	}

	protected void save() {
		Division division = new Division();
		division.setId(codeField.getText());
		division.setName(nameField.getText());
		division.setInputDate(new Date());
		division.setInputBy("");
		division.setEditDate(new Date());
		division.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().saveDivision(division);
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
		nameField.setText("");
	}
	
	class PositionHistoryTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -8720800573929798216L;
		private List<EmployeePosition> employeePositions;

		public PositionHistoryTableModel(List<EmployeePosition> employeePositions) {
			this.employeePositions = employeePositions;
		}

		@Override
		public int getColumnCount() {
			return 13;
		}

		@Override
		public int getRowCount() {
			return employeePositions == null ? 0 : employeePositions.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			EmployeePosition employeePosition = employeePositions.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return employeePositions.indexOf(employeePosition) + 1;
			case 1:
				return employeePosition.getEmployeeId();
			case 2:
				return employeePosition.getStartDate();
			case 3:
				return employeePosition.getEndDate();
			case 4:
				return employeePosition.getProbation();
			case 5:
				return employeePosition.getPositionId();
			case 6:
				return employeePosition.getReferenceDoc();
			case 7:
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
				return "Tanggal Mulai Kerja";
			case 2:
				return "Tanggal Berhenti Kerja";
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
}