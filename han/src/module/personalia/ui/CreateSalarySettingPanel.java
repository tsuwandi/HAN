package module.personalia.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberFormat;
import main.component.NumericField;
import main.panel.MainPanel;
import module.personalia.model.Employee;
import module.personalia.model.PayrollComponent;
import module.personalia.model.SalarySetting;
import module.personalia.model.SsSalaryComp;
import module.personalia.model.SsTax;
import module.personalia.model.Tax;

public class CreateSalarySettingPanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JTextField employeeTypeField;
	private JTextField positionNameField;
	private JTextField departmentNameField;
	private JTextField divisionNameField;
	private JDateChooser startDateEffectiveField;
	private JDateChooser endDateEffectiveField;
	private ComboBox<PayrollComponent> payrollComponentCmbox;
	private ComboBox<Tax> taxCmbox;
	private JTextField payrollComponentNominalField;
	private NumericField taxNominalField;
	private JTextField brutoSalaryField;
	private JTable payrollComponentTable;
	private JTable taxTable;
	private PayrollComponentTableModel payrollComponentTableModel;
	private TaxTableModel taxTableModel;
	private JTextField totalCutField;
	private JTextField nettSalaryField;
	private List<SsSalaryComp> ssSalaryComps;
	private List<SsTax> ssTaxs;
	private Employee employee;

	public CreateSalarySettingPanel() {
		setLayout(null);

		JPanel containerPanel = new JPanel();
		containerPanel.setPreferredSize(new Dimension(1024, 1536));
		containerPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(containerPanel);
		scrollPane.setBounds(0, 0, 1024, 630);
		add(scrollPane);

		JLabel breadCrumbLbl = new JLabel("Payroll > Setting Nominal Gaji > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 380, 25);
		containerPanel.add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN SETTING NOMINAL GAJI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 380, 25);
		containerPanel.add(lblHeader);
		// nik
		JLabel lblNik = new JLabel("NIK");
		lblNik.setBounds(30, 80, 100, 30);
		containerPanel.add(lblNik);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		containerPanel.add(label_1);

		employeeCodeField = new JTextField();
		employeeCodeField.setBounds(140, 80, 200, 30);
		containerPanel.add(employeeCodeField);

		employeeCodeField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					updateData();
				}
			}
		});

		// nama karyawan
		JLabel lblnamaKaryawan = new JLabel("<html>Nama Karyawan</html>");
		lblnamaKaryawan.setBounds(30, 120, 100, 30);
		containerPanel.add(lblnamaKaryawan);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		containerPanel.add(label_3);

		employeeNameField = new JTextField();
		employeeNameField.setEditable(false);
		employeeNameField.setEnabled(false);
		employeeNameField.setBounds(140, 120, 200, 30);
		containerPanel.add(employeeNameField);
		// tipe karyawan
		JLabel lbltipeKaryawan = new JLabel("<html>Tipe Karyawan</html>");
		lbltipeKaryawan.setBounds(30, 160, 100, 30);
		containerPanel.add(lbltipeKaryawan);

		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		containerPanel.add(label_2);

		employeeTypeField = new JTextField();
		employeeTypeField.setEnabled(false);
		employeeTypeField.setEditable(false);
		employeeTypeField.setBounds(140, 160, 200, 30);
		containerPanel.add(employeeTypeField);
		// jabatan
		JLabel lbljabatan = new JLabel("<html>Jabatan</html>");
		lbljabatan.setBounds(30, 200, 100, 30);
		containerPanel.add(lbljabatan);

		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 200, 10, 30);
		containerPanel.add(label_5);

		positionNameField = new JTextField();
		positionNameField.setEnabled(false);
		positionNameField.setEditable(false);
		positionNameField.setBounds(140, 200, 200, 30);
		containerPanel.add(positionNameField);
		// departemen
		JLabel lbldepartemen = new JLabel("<html>Departemen</html>");
		lbldepartemen.setBounds(30, 240, 100, 30);
		containerPanel.add(lbldepartemen);

		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 240, 10, 30);
		containerPanel.add(label_7);

		departmentNameField = new JTextField();
		departmentNameField.setEnabled(false);
		departmentNameField.setEditable(false);
		departmentNameField.setBounds(140, 240, 200, 30);
		containerPanel.add(departmentNameField);
		// divisi
		JLabel lbldivisi = new JLabel("<html>Divisi</html>");
		lbldivisi.setBounds(30, 280, 100, 30);
		containerPanel.add(lbldivisi);

		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 280, 10, 30);
		containerPanel.add(label_9);

		divisionNameField = new JTextField();
		divisionNameField.setEnabled(false);
		divisionNameField.setEditable(false);
		divisionNameField.setBounds(140, 280, 200, 30);
		containerPanel.add(divisionNameField);
		// tanggal mulai efektif
		JLabel lbltanggalMulaiEfektif = new JLabel("<html>Tanggal Mulai Efektif</html>");
		lbltanggalMulaiEfektif.setBounds(30, 320, 100, 30);
		containerPanel.add(lbltanggalMulaiEfektif);

		JLabel label_11 = new JLabel(":");
		label_11.setBounds(130, 320, 10, 30);
		containerPanel.add(label_11);

		startDateEffectiveField = new JDateChooser();
		startDateEffectiveField.setBounds(140, 320, 200, 30);
		containerPanel.add(startDateEffectiveField);
		// tanggal ekhir efektif
		JLabel lbltanggalAkhirEfektif = new JLabel("<html>Tanggal Akhir Efektif</html>");
		lbltanggalAkhirEfektif.setBounds(30, 360, 100, 30);
		containerPanel.add(lbltanggalAkhirEfektif);

		JLabel label_13 = new JLabel(":");
		label_13.setBounds(130, 360, 10, 30);
		containerPanel.add(label_13);

		endDateEffectiveField = new JDateChooser();
		endDateEffectiveField.setBounds(140, 360, 200, 30);
		containerPanel.add(endDateEffectiveField);
		// pembatas
		JLabel firstFlip = new JLabel("________________________________________________________________________________");
		firstFlip.setBounds(30, 390, 500, 20);
		containerPanel.add(firstFlip);
		// komponen gaji
		JLabel label_4 = new JLabel("<html>Komponen Gaji</html>");
		label_4.setBounds(30, 420, 100, 30);
		containerPanel.add(label_4);

		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 420, 10, 30);
		containerPanel.add(label_6);

		payrollComponentCmbox = new ComboBox<>();
		payrollComponentCmbox.setBounds(140, 420, 200, 30);
		containerPanel.add(payrollComponentCmbox);
		// nominal komponen gaji
		JLabel label_8 = new JLabel("<html>Nominal</html>");
		label_8.setBounds(30, 460, 100, 30);
		containerPanel.add(label_8);

		JLabel label_10 = new JLabel(":");
		label_10.setBounds(130, 460, 10, 30);
		containerPanel.add(label_10);

		payrollComponentNominalField = new JTextField();
		payrollComponentNominalField.setBounds(140, 460, 200, 30);
		containerPanel.add(payrollComponentNominalField);

		// tombol tambah
		JButton addPayrollComponentBtn = new JButton("Tambah");
		addPayrollComponentBtn.setBounds(140, 500, 70, 30);
		containerPanel.add(addPayrollComponentBtn);

		addPayrollComponentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SsSalaryComp ssSalaryComp = new SsSalaryComp();
				ssSalaryComp.setPayrollComponent(payrollComponentCmbox.getDataIndex());
				ssSalaryComp.setPayrollComponentCode(payrollComponentCmbox.getDataIndex().getCode());
				ssSalaryComp.setNominal(new BigDecimal(payrollComponentNominalField.getText()));
				ssSalaryComp.setInputDate(new Date());
				ssSalaryComp.setInputBy("");
				ssSalaryComp.setEditDate(new Date());
				ssSalaryComp.setEditBy("");
				ssSalaryComps.add(ssSalaryComp);
				payrollComponentTableModel.setPayrollComponentSettings(ssSalaryComps);
				payrollComponentTable.updateUI();

				BigDecimal totalComponent = BigDecimal.ZERO;

				for (SsSalaryComp nominal : ssSalaryComps) {
					totalComponent = totalComponent.add(nominal.getNominal());
				}
				
				brutoSalaryField.setText(NumberFormat.onTypeNum(9, totalComponent.toString()));
			}
		});

		// tabel komponen payroll 
		JScrollPane componentPayrollScrollPane = new JScrollPane();
		componentPayrollScrollPane.setBounds(30, 540, 340, 180);
		containerPanel.add(componentPayrollScrollPane);

		payrollComponentTable = new JTable();
		payrollComponentTable.setFocusable(false);
		payrollComponentTable.setAutoCreateRowSorter(true);
		componentPayrollScrollPane.setViewportView(payrollComponentTable);

		payrollComponentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (payrollComponentTable.columnAtPoint(e.getPoint())==2) {
					int row = payrollComponentTable.getSelectedRow();

					payrollComponentCmbox.setSelectedItem(ssSalaryComps.get(row));
					payrollComponentNominalField.setText(ssSalaryComps.get(row).getNominal().toString());

				} else if(payrollComponentTable.columnAtPoint(e.getPoint())==3){
					int row = payrollComponentTable.getSelectedRow();

					ssSalaryComps.remove(row);
					payrollComponentTableModel.setPayrollComponentSettings(ssSalaryComps);
					payrollComponentTable.updateUI();
				}
			}
		});

		// gaji kotor
		JLabel label_14 = new JLabel("<html>Gaji Kotor</html>");
		label_14.setBounds(30, 730, 100, 30);
		containerPanel.add(label_14);

		JLabel label_15 = new JLabel(":");
		label_15.setBounds(130, 730, 10, 30);
		containerPanel.add(label_15);

		brutoSalaryField = new JTextField();
		brutoSalaryField.setBounds(140, 730, 200, 30);
		containerPanel.add(brutoSalaryField);

		brutoSalaryField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSalary();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSalary();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSalary();
			}
		});

		// pembatas
		JLabel secondFlip = new JLabel("________________________________________________________________________________");
		secondFlip.setBounds(30, 770, 500, 20);
		containerPanel.add(secondFlip);
		// tax
		JLabel label_16 = new JLabel("<html>Pajak</html>");
		label_16.setBounds(30, 800, 100, 30);
		containerPanel.add(label_16);

		JLabel label_17 = new JLabel(":");
		label_17.setBounds(130, 800, 10, 30);
		containerPanel.add(label_17);

		taxCmbox = new ComboBox<>();
		taxCmbox.setBounds(140, 800, 200, 30);
		containerPanel.add(taxCmbox);
		// nonimal pajak
		JLabel label_18 = new JLabel("<html>Nominal</html>");
		label_18.setBounds(30, 840, 100, 30);
		containerPanel.add(label_18);

		JLabel label_19 = new JLabel(":");
		label_19.setBounds(130, 840, 10, 30);
		containerPanel.add(label_19);

		taxNominalField = new NumericField(15, "###,###,###,###");
		taxNominalField.setBounds(140, 840, 200, 30);
		containerPanel.add(taxNominalField);
		// tax tombol add
		JButton addTaxBtn = new JButton("Tambah");
		addTaxBtn.setBounds(140, 880, 70, 30);
		containerPanel.add(addTaxBtn);

		addTaxBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SsTax ssTax = new SsTax();
				ssTax.setTax(taxCmbox.getDataIndex());
				ssTax.setTaxId(taxCmbox.getDataIndex().getId());
				if("".equals(taxNominalField.getText()) || "0".equals(taxNominalField.getText())) {
					DialogBox.showError("<html>Field <font color='red'>Nominal</font> tidak boleh kosong atau 0</html>");
					return;
				} else {
					ssTax.setNominal(new BigDecimal(taxNominalField.getText()));
				}
				ssTax.setInputDate(new Date());
				ssTax.setInputBy("");
				ssTax.setEditDate(new Date());
				ssTax.setEditBy("");
				ssTaxs.add(ssTax);
				taxTableModel.setTaxComponentSettings(ssTaxs);
				taxTable.updateUI();

				updateCut();
			}
		});

		// tabel tax
		JScrollPane taxScrollPane = new JScrollPane();
		taxScrollPane.setBounds(30, 920, 340, 180);
		containerPanel.add(taxScrollPane);

		taxTable = new JTable();
		taxTable.setFocusable(false);
		taxTable.setAutoCreateRowSorter(true);
		taxScrollPane.setViewportView(taxTable);

		taxTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (taxTable.columnAtPoint(e.getPoint())==2) {
					int row = taxTable.getSelectedRow();

					taxCmbox.setSelectedItem(ssTaxs.get(row));
					taxNominalField.setText(ssTaxs.get(row).getNominal().toString());

				} else if(taxTable.columnAtPoint(e.getPoint())==3){
					int row = taxTable.getSelectedRow();

					ssTaxs.remove(row);
					taxTableModel.setTaxComponentSettings(ssTaxs);
					taxTable.updateUI();
					
					updateCut();
				}
			}
		});

		//total potongan field
		JLabel label_20 = new JLabel("<html>Total Potongan</html>");
		label_20.setBounds(30, 1110, 100, 30);
		containerPanel.add(label_20);

		JLabel label_21 = new JLabel(":");
		label_21.setBounds(130, 1110, 10, 30);
		containerPanel.add(label_21);

		totalCutField = new JTextField();
		totalCutField.setBounds(140, 1110, 200, 30);
		containerPanel.add(totalCutField);

		totalCutField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSalary();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSalary();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSalary();
			}
		});

		//gaji bersih
		JLabel label_22 = new JLabel("<html>Gaji Bersih</html>");
		label_22.setBounds(30, 1150, 100, 30);
		containerPanel.add(label_22);

		JLabel label_23 = new JLabel(":");
		label_23.setBounds(130, 1150, 10, 30);
		containerPanel.add(label_23);

		nettSalaryField = new JTextField();
		nettSalaryField.setBounds(140, 1150, 200, 30);
		containerPanel.add(nettSalaryField);

		// tombol simpan
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 1190, 90, 30);
		containerPanel.add(saveBtn);

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		getData();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				employeeCodeField.requestFocusInWindow();
			}
		});
	}

	protected void updateData() {
		employee = ServiceFactory.getPersonaliaBL().getEmployees(" and LOWER(emp_code) like ('"+employeeCodeField.getText()+"')").get(0);
		if (employee!=null) {
			employeeNameField.setText(employee.getName());
			employeeTypeField.setText(employee.getEmployeeType().getName());
			positionNameField.setText(employee.getMsPosition().getName());
			departmentNameField.setText(employee.getDepartment().getName());
			divisionNameField.setText(employee.getDivision().getName());
		} else {
			DialogBox.showError("Data tidak ditemukan, silahkan coba lagi");
		}
	}
	
	protected void updateCut() {
		BigDecimal totalCut = BigDecimal.ZERO;

		for (SsTax nominal : ssTaxs) {
			totalCut = totalCut.add(nominal.getNominal());
		}

		totalCutField.setText(totalCut.toString());
	}

	protected void updateSalary() {
		BigDecimal brutoSalary;
		BigDecimal netSalary = BigDecimal.ZERO;
		BigDecimal totalTax;

		if(brutoSalaryField.getText() == null | "".equals(brutoSalaryField.getText())) {
			brutoSalary = BigDecimal.ZERO;
		} else {
			brutoSalary = new BigDecimal(brutoSalaryField.getText());
		}

		if(taxNominalField.getText() == null | "".equals(taxNominalField.getText())) {
			totalTax = BigDecimal.ZERO;	
		} else {
			totalTax = new BigDecimal(taxNominalField.getText());
		}

		netSalary = brutoSalary.subtract(totalTax);
		nettSalaryField.setText(netSalary.toString());
	}

	private void getData() {
		ssSalaryComps = new ArrayList<SsSalaryComp>();
		payrollComponentTableModel = new PayrollComponentTableModel(ssSalaryComps);
		payrollComponentTable.setModel(payrollComponentTableModel);
		payrollComponentCmbox.setList(ServiceFactory.getPersonaliaBL().getPayrollComponents(""));

		ssTaxs = new ArrayList<SsTax>();
		taxTableModel = new TaxTableModel(ssTaxs);
		taxTable.setModel(taxTableModel);
		taxCmbox.setList(ServiceFactory.getPersonaliaBL().getTaxs(""));
	}

	protected void save() {
		SalarySetting salarySetting = new SalarySetting();
		salarySetting.setEmployeeCode(employeeCodeField.getText());
		salarySetting.setEffectiveStartDate(startDateEffectiveField.getDate());
		salarySetting.setEffectiveEndDate(endDateEffectiveField.getDate());
		salarySetting.setSalaryBruto(new BigDecimal(brutoSalaryField.getText()));
		salarySetting.setTax(new BigDecimal(totalCutField.getText()));
		salarySetting.setSalaryNett(new BigDecimal(nettSalaryField.getText()));
		salarySetting.setInputDate(new Date());
		salarySetting.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		salarySetting.setEditDate(new Date());
		salarySetting.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());

		try {
			ServiceFactory.getPersonaliaBL().saveSalarySetting(salarySetting);

			Integer salarySettingId = ServiceFactory.getPersonaliaBL().getLastIdSalarySetting();

			for (SsSalaryComp ssSalaryComp : ssSalaryComps) {
				ssSalaryComp.setSalarySettingId(salarySettingId-1);
				ServiceFactory.getPersonaliaBL().saveSsSalaryComp(ssSalaryComp);
			}
			for (SsTax ssTax : ssTaxs) {
				ssTax.setSalarySettingId(salarySettingId-1);
				ServiceFactory.getPersonaliaBL().saveSsTax(ssTax);
			}

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
			MainPanel.changePanel("module.personalia.ui.SalarySettingConfigPanel");
		}
	}

	private void clear() {
		//getLastID();
		employeeCodeField.setText("");
		employeeNameField.setText("");
		employeeTypeField.setText("");
		positionNameField.setText("");
		departmentNameField.setText("");
		divisionNameField.setText("");

		startDateEffectiveField.setDate(null);
		endDateEffectiveField.setDate(null);

		payrollComponentCmbox.setSelectedIndex(0);
		payrollComponentNominalField.setText("");

		ssSalaryComps.clear();
		payrollComponentTable.updateUI();

		brutoSalaryField.setText("");
		taxCmbox.setSelectedIndex(0);
		taxNominalField.setText("");

		ssTaxs.clear();
		taxTable.updateUI();

		totalCutField.setText("");
		nettSalaryField.setText("");
	}

	class PayrollComponentTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 5617235600148018029L;
		private List<SsSalaryComp> ssSalaryComps;

		public PayrollComponentTableModel(List<SsSalaryComp> ssSalaryComps) {
			this.ssSalaryComps = ssSalaryComps;
		}

		public void setPayrollComponentSettings(List<SsSalaryComp> payrollComponentSettings) {
			this.ssSalaryComps = payrollComponentSettings;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return ssSalaryComps == null ? 0 : ssSalaryComps.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			SsSalaryComp ssSalaryComp = ssSalaryComps.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return ssSalaryComp.getPayrollComponent().getDescription();
			case 1:
				return ssSalaryComp.getNominal();
			case 2:
				return "<html><u>Edit</u></html>";
			case 3:
				return "<html><u>Hapus</u></html>";
			default:
				return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return BigDecimal.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			default:
				return String.class;
			}
		}

		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "Komponen Gaji";
			case 1:
				return "Nominal";
			case 2:
				return "<html><u>Tindakan 1</u></html>";
			case 3:
				return "<html><u>Tindakan 2</html>";
			default:
				return "";
			}
		}
	}

	class TaxTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 8994908579910879062L;
		private List<SsTax> ssTaxs;

		public TaxTableModel(List<SsTax> ssTaxs) {
			this.ssTaxs = ssTaxs;
		}

		public void setTaxComponentSettings(List<SsTax> ssTaxs) {
			this.ssTaxs = ssTaxs;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return ssTaxs == null ? 0 : ssTaxs.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			SsTax taxComponentSetting = ssTaxs.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return taxComponentSetting.getTax().getTax();
			case 1:
				return taxComponentSetting.getNominal();
			case 2:
				return "<html><u>Edit</u></html>";
			case 3:
				return "<html><u>Hapus</u></html>";
			default:
				return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return BigDecimal.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			default:
				return String.class;
			}
		}

		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "Pajak";
			case 1:
				return "Nominal";
			case 2:
				return "<html><u>Tindakan 1</u></html>";
			case 3:
				return "<html><u>Tindakan 2</html>";
			default:
				return "";
			}
		}
	}
}