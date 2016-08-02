package module.supplier.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppCp;
import module.supplier.model.SuppVehicle;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class SupplierViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private Supplier supplier;
	public List<SuppAddress> listOfSuppAddress = new ArrayList<SuppAddress>();
	private SuppAddressTableModel suppAddressTableModel;
//	public List<SuppCp> listOfSuppCp = new ArrayList<SuppCp>();
//	private SuppCpTableModel suppCpTableModel;
	public List<SuppVehicle> listOfSuppVehicle = new ArrayList<SuppVehicle>();
	private SuppVehicleTableModel suppVehicleTableModel;

	JLabel lblSuppCode;
	JLabel lblSuppName;
	JLabel lblPt;
	JLabel lblNpwp;
	JLabel lblSuppType;
	JLabel lblSuppStatus;
//	JLabel lblAccountNo;
//	JLabel lblBank;
//	JLabel lblAccountName;
	JLabel lblCurrency;
	JLabel lblTop;
	JLabel lblDefaultTax;
	JLabel lblTopDays;
	JLabel lblDefaultTaxPercentage;

	JTextField txtSuppCode;
	JTextField txtSuppName;
	JTextField txtPt;
	JTextField txtNpwp;
	JComboBox<String> cbSuppType;
	JComboBox<String> cbSuppStatus;
//	JTextField txtAccountNo;
//	JComboBox<String> cbBank;
//	JTextField txtAccountName;
	JComboBox<String> cbCurrency;
	JTextField txtTop;
	JTextField txtDefaultTax;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JLabel lblSuppAddress;
	JScrollPane scrollPaneSuppAddress;
	JTable tblSuppAddress;
	JButton btnAddSuppAddress;
	JButton btnDeleteSuppAddress;

//	JLabel lblSuppCp;
//	JScrollPane scrollPaneSuppCp;
//	JTable tblSuppCp;
//	JButton btnAddSuppCp;
//	JButton btnDeleteSuppCp;

	JLabel lblSuppVehicle;
	JScrollPane scrollPaneSuppVehicle;
	JTable tblSuppVehicle;
	JButton btnAddSuppVehicle;
	JButton btnDeleteSuppVehicle;

	JPanel panel;
	JScrollPane scrollPane;

	JButton btnCancel;
	JButton btnPrint;
	JButton btnDelete;
	JButton btnEdit;

	JLabel lblErrorSuppCode;
	JLabel lblErrorSuppName;
	JLabel lblErrorSuppType;
	JLabel lblErrorSuppStatus;
	JLabel lblErrorTop;
	JLabel lblErrorDefaultTax;

	public SupplierViewPanel() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		panel.setPreferredSize(new Dimension(1050, 950));
		panel.setLayout(null);

		lblSuppCode = new JLabel("<html>Kode Supplier <font color=\"red\">*</font></html>");
		lblSuppCode.setBounds(50, 80, 150, 30);
		panel.add(lblSuppCode);

		txtSuppCode = new JTextField();
		txtSuppCode.setEnabled(false);
		txtSuppCode.setBounds(220, 80, 150, 30);
		txtSuppCode.setDocument(new JTextFieldLimit(9));
		panel.add(txtSuppCode);

		lblErrorSuppCode = new JLabel();
		lblErrorSuppCode.setForeground(Color.RED);
		lblErrorSuppCode.setBounds(425, 80, 225, 30);
		panel.add(lblErrorSuppCode);

		lblSuppName = new JLabel("<html>Nama Supplier <font color=\"red\">*</font></html>");
		lblSuppName.setBounds(50, 120, 150, 30);
		panel.add(lblSuppName);

		txtSuppName = new JTextField();
		txtSuppName.setEnabled(false);
		txtSuppName.setBounds(220, 120, 150, 30);
		txtSuppName.setDocument(new JTextFieldLimit(200));
		panel.add(txtSuppName);

		lblErrorSuppName = new JLabel();
		lblErrorSuppName.setForeground(Color.RED);
		lblErrorSuppName.setBounds(425, 120, 225, 30);
		panel.add(lblErrorSuppName);

		lblPt = new JLabel("PT");
		lblPt.setBounds(50, 160, 150, 30);
		panel.add(lblPt);

		txtPt = new JTextField();
		txtPt.setEnabled(false);
		txtPt.setBounds(220, 160, 150, 30);
		txtPt.setDocument(new JTextFieldLimit(200));
		panel.add(txtPt);

		lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(50, 200, 150, 30);
		panel.add(lblNpwp);

		txtNpwp = new JTextField();
		txtNpwp.setEnabled(false);
		txtNpwp.setBounds(220, 200, 150, 30);
		txtNpwp.setDocument(new JTextFieldLimit(30));
		panel.add(txtNpwp);

		lblSuppType = new JLabel("<html>Tipe Supplier <font color=\"red\">*</font></html>");
		lblSuppType.setBounds(50, 240, 150, 30);
		panel.add(lblSuppType);

		cbSuppType = new JComboBox<String>();
		cbSuppType.setEnabled(false);
		cbSuppType.addItem("-- Pilih Tipe Supplier --");
		cbSuppType.setBounds(220, 240, 150, 30);
		panel.add(cbSuppType);

		lblErrorSuppType = new JLabel();
		lblErrorSuppType.setForeground(Color.RED);
		lblErrorSuppType.setBounds(425, 240, 225, 30);
		panel.add(lblErrorSuppType);

		lblSuppStatus = new JLabel("<html>Status Supplier <font color=\"red\">*</font></html>");
		lblSuppStatus.setBounds(50, 280, 150, 30);
		panel.add(lblSuppStatus);

		cbSuppStatus = new JComboBox<String>();
		cbSuppStatus.setEnabled(false);
		cbSuppStatus.addItem("-- Pilih Status Supplier --");
		cbSuppStatus.setBounds(220, 280, 150, 30);
		panel.add(cbSuppStatus);

		lblErrorSuppStatus = new JLabel();
		lblErrorSuppStatus.setForeground(Color.RED);
		lblErrorSuppStatus.setBounds(425, 280, 225, 30);
		panel.add(lblErrorSuppStatus);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Supplier");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("VIEW DETAIL");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		panel.add(lblHeader);

		/////// Table SuppAddress ///////
		lblSuppAddress = new JLabel("Alamat");
		lblSuppAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSuppAddress.setBounds(50, 330, 150, 30);
		panel.add(lblSuppAddress);

		scrollPaneSuppAddress = new JScrollPane();
		scrollPaneSuppAddress.setBounds(50, 370, 975, 150);
		panel.add(scrollPaneSuppAddress);

		suppAddressTableModel = new SuppAddressTableModel(new ArrayList<SuppAddress>());
		tblSuppAddress = new JTable(suppAddressTableModel);
		tblSuppAddress.setEnabled(false);
		tblSuppAddress.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblSuppAddress.setFocusable(false);
		scrollPaneSuppAddress.setViewportView(tblSuppAddress);

		btnAddSuppAddress = new JButton("Tambah");
		btnAddSuppAddress.setEnabled(false);
		btnAddSuppAddress.setBounds(820, 330, 100, 30);
		panel.add(btnAddSuppAddress);

		btnDeleteSuppAddress = new JButton("Hapus");
		btnDeleteSuppAddress.setEnabled(false);
		btnDeleteSuppAddress.setBounds(925, 330, 100, 30);
		panel.add(btnDeleteSuppAddress);

		/////// Table SuppCP ///////

//		lblSuppCp = new JLabel("Contact Person");
//		lblSuppCp.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblSuppCp.setBounds(50, 540, 150, 30);
//		panel.add(lblSuppCp);
//
//		scrollPaneSuppCp = new JScrollPane();
//		scrollPaneSuppCp.setBounds(50, 580, 975, 150);
//		panel.add(scrollPaneSuppCp);
//
//		suppCpTableModel = new SuppCpTableModel(new ArrayList<SuppCp>());
//		tblSuppCp = new JTable(suppCpTableModel);
//		tblSuppCp.setEnabled(false);
//		tblSuppCp.setBorder(new EmptyBorder(5, 5, 5, 5));
//		scrollPaneSuppCp.setViewportView(tblSuppCp);
//
//		btnAddSuppCp = new JButton("Tambah");
//		btnAddSuppCp.setEnabled(false);
//		btnAddSuppCp.setBounds(820, 540, 100, 30);
//		panel.add(btnAddSuppCp);
//
//		btnDeleteSuppCp = new JButton("Hapus");
//		btnDeleteSuppCp.setEnabled(false);
//		btnDeleteSuppCp.setBounds(925, 540, 100, 30);
//		panel.add(btnDeleteSuppCp);

		/////// Table SuppCP ///////

		lblSuppVehicle = new JLabel("Kendaraan");
		lblSuppVehicle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSuppVehicle.setBounds(50, 540, 150, 30);
		panel.add(lblSuppVehicle);

		scrollPaneSuppVehicle = new JScrollPane();
		scrollPaneSuppVehicle.setBounds(50, 580, 975, 150);
		panel.add(scrollPaneSuppVehicle);

		suppVehicleTableModel = new SuppVehicleTableModel(new ArrayList<SuppVehicle>());
		tblSuppVehicle = new JTable(suppVehicleTableModel);
		tblSuppVehicle.setEnabled(false);
		tblSuppVehicle.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblSuppVehicle.setFocusable(false);
		scrollPaneSuppVehicle.setViewportView(tblSuppVehicle);

		btnAddSuppVehicle = new JButton("Tambah");
		btnAddSuppVehicle.setEnabled(false);
		btnAddSuppVehicle.setBounds(820, 540, 100, 30);
		panel.add(btnAddSuppVehicle);

		btnDeleteSuppVehicle = new JButton("Hapus");
		btnDeleteSuppVehicle.setEnabled(false);
		btnDeleteSuppVehicle.setBounds(925, 540, 100, 30);
		panel.add(btnDeleteSuppVehicle);

		///////////////////////////////////////////////////////////////

//		lblAccountNo = new JLabel("No Akun Bank");
//		lblAccountNo.setBounds(50, 960, 150, 30);
//		panel.add(lblAccountNo);
//
//		txtAccountNo = new JTextField();
//		txtAccountNo.setEnabled(false);
//		txtAccountNo.setBounds(220, 960, 150, 30);
//		txtAccountNo.setDocument(new JTextFieldLimit(30));
//		panel.add(txtAccountNo);
//
//		lblBank = new JLabel("Bank");
//		lblBank.setBounds(50, 1000, 150, 30);
//		panel.add(lblBank);
//
//		cbBank = new JComboBox<String>();
//		cbBank.setEnabled(false);
//		cbBank.addItem("-- Pilih Bank --");
//		cbBank.setBounds(220, 1000, 150, 30);
//		panel.add(cbBank);
//
//		lblAccountName = new JLabel("Nama Pemilik Akun");
//		lblAccountName.setBounds(50, 1040, 150, 30);
//		panel.add(lblAccountName);
//
//		txtAccountName = new JTextField();
//		txtAccountName.setEnabled(false);
//		txtAccountName.setBounds(220, 1040, 150, 30);
//		txtAccountName.setDocument(new JTextFieldLimit(30));
//		panel.add(txtAccountName);

		lblCurrency = new JLabel("<html>Kurs <font color=\"red\">*</font></html>");
		lblCurrency.setBounds(50, 750, 150, 30);
		panel.add(lblCurrency);

		cbCurrency = new JComboBox<String>();
		cbCurrency.setEnabled(false);
		cbCurrency.addItem("-- Pilih Kurs --");
		cbCurrency.setBounds(220, 750, 150, 30);
		panel.add(cbCurrency);

		lblTop = new JLabel("TOP");
		lblTop.setBounds(50, 790, 150, 30);
		panel.add(lblTop);

		txtTop = new JTextField();
		txtTop.setEnabled(false);
		txtTop.setBounds(220, 790, 150, 30);
		panel.add(txtTop);

		lblTopDays = new JLabel("hari");
		lblTopDays.setBounds(380, 790, 150, 30);
		panel.add(lblTopDays);

		lblErrorTop = new JLabel();
		lblErrorTop.setForeground(Color.RED);
		lblErrorTop.setBounds(425, 790, 225, 30);
		panel.add(lblErrorTop);

		lblDefaultTax = new JLabel("Default Tax");
		lblDefaultTax.setBounds(50, 830, 150, 30);
		panel.add(lblDefaultTax);

		txtDefaultTax = new JTextField();
		txtDefaultTax.setEnabled(false);
		txtDefaultTax.setBounds(220, 830, 150, 30);
		panel.add(txtDefaultTax);

		lblDefaultTaxPercentage = new JLabel("%");
		lblDefaultTaxPercentage.setBounds(380, 830, 150, 30);
		panel.add(lblDefaultTaxPercentage);

		lblErrorDefaultTax = new JLabel();
		lblErrorDefaultTax.setForeground(Color.RED);
		lblErrorDefaultTax.setBounds(425, 830, 225, 30);
		panel.add(lblErrorDefaultTax);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 1155, 605);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		add(scrollPane);

		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPrint();
			}
		});
		btnPrint.setBounds(715, 870, 100, 30);
		panel.add(btnPrint);

		btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showDeleteChoice();
				if (response == JOptionPane.YES_OPTION) {
					doDelete();
				}
			}
		});
		btnDelete.setBounds(820, 870, 100, 30);
		panel.add(btnDelete);

		btnEdit = new JButton("Ubah");
		btnEdit.setBounds(925, 870, 100, 30);
		panel.add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.supplier.ui.SupplierEditPanel", supplier);
			}
		});

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
			}
		});
		btnCancel.setBounds(50, 870, 100, 30);
		panel.add(btnCancel);
	}

	protected void loadData(Integer supplierId) {
		try {
			supplier = ServiceFactory.getSupplierBL().getSupplierById(supplierId);
			listOfSuppAddress = ServiceFactory.getSupplierBL().getSuppAddressBySuppCode(supplier.getSuppCode());
			//listOfSuppCp = ServiceFactory.getSupplierBL().getSuppCpBySuppCode(supplier.getSuppCode());
			listOfSuppVehicle = ServiceFactory.getSupplierBL().getSuppVehicleBySuppCode(supplier.getSuppCode());

			if (supplier != null) {
				txtSuppCode.setText(supplier.getSuppCode());
				txtSuppName.setText(supplier.getSuppName());
				txtPt.setText(supplier.getPt());
				txtNpwp.setText(supplier.getNpwp());
				cbSuppType.addItem(supplier.getSuppType().getSuppType());
				cbSuppType.setSelectedIndex(1);
				cbSuppStatus.addItem(supplier.getSuppStatus());
				cbSuppStatus.setSelectedIndex(1);
				txtDefaultTax.setText(String.valueOf(supplier.getDefaultTax()));
//				txtAccountNo.setText(supplier.getAccountNo());
//				cbBank.addItem(supplier.getBank().getBank());
//				cbBank.setSelectedIndex(1);
//				txtAccountName.setText(supplier.getAccountName());
				cbCurrency.addItem(supplier.getCurrency().getCurrency());
				cbCurrency.setSelectedIndex(1);
				txtTop.setText(String.valueOf(supplier.getTop()));

				refreshTableSuppAddress();
				refreshTableSuppCp();
				refreshTableSuppVehicle();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	public void refreshTableSuppAddress() {
		try {
			tblSuppAddress.setModel(new SuppAddressTableModel(listOfSuppAddress));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	public void refreshTableSuppCp() {
//		try {
//			tblSuppCp.setModel(new SuppCpTableModel(listOfSuppCp));
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			DialogBox.showErrorException();
//		}
	}

	public void refreshTableSuppVehicle() {
		try {
			tblSuppVehicle.setModel(new SuppVehicleTableModel(listOfSuppVehicle));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	protected void doPrint() {

	}

	protected void doDelete() {
		try {
			ServiceFactory.getSupplierBL().deleteAll(supplier);
			DialogBox.showDelete();
			MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for Supp Address table
	 * 
	 * @author TSI
	 *
	 */
	class SuppAddressTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<SuppAddress> listOfSuppAddress;

		public SuppAddressTableModel(List<SuppAddress> listOfSuppAddress) {
			this.listOfSuppAddress = listOfSuppAddress;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSuppAddress.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 7;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SuppAddress p = listOfSuppAddress.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getAddressType();
			case 2:
				return p.getAddress();
			case 3:
				return p.getSuppCp().getName();
			case 4:
				return p.getPhone();
			case 5:
				return p.getFax();
			case 6:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "Tipe Alamat";
			case 2:
				return "Alamat";
			case 3:
				return "Contact Person";
			case 4:
				return "Telepon";
			case 5:
				return "Fax";
			case 6:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	/**
	 * Class as TableModel for Supplier Contact Person table
	 * 
	 * @author TSI
	 *
	 */
	class SuppCpTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<SuppCp> listOfSuppCp;

		public SuppCpTableModel(List<SuppCp> listOfSuppCp) {
			this.listOfSuppCp = listOfSuppCp;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSuppCp.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 6;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SuppCp p = listOfSuppCp.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getName();
			case 2:
				return p.getDepartment();
			case 3:
				return p.getPhone();
			case 4:
				return p.getEmail();
			case 5:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "Nama";
			case 2:
				return "Department";
			case 3:
				return "Telepon";
			case 4:
				return "Email";
			case 5:
				return "Tindakan";
			default:
				return "";
			}
		}
	}

	/**
	 * Class as TableModel for Supp Vehicle table
	 * 
	 * @author TSI
	 *
	 */
	class SuppVehicleTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<SuppVehicle> listOfSuppVehicle;

		public SuppVehicleTableModel(List<SuppVehicle> listOfSuppVehicle) {
			this.listOfSuppVehicle = listOfSuppVehicle;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSuppVehicle.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 4;
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SuppVehicle p = listOfSuppVehicle.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getLicensePlate();
			case 2:
				return p.getVehicleType().getVehicleType();
			case 3:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "No. Kendaraan";
			case 2:
				return "Tipe Kendaraan";
			case 3:
				return "Tindakan";
			default:
				return "";
			}
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.supplier = (Supplier) objects[0];

		loadData(supplier.getId());
	}
}
