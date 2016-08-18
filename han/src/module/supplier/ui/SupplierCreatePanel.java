package module.supplier.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.SuppAddress;
import module.supplier.model.SuppCp;
import module.supplier.model.SuppVehicle;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class SupplierCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private Supplier supplier;
	public List<SuppAddress> listOfSuppAddress = new ArrayList<SuppAddress>();
	private SuppAddressTableModel suppAddressTableModel;
	public List<SuppVehicle> listOfSuppVehicle = new ArrayList<SuppVehicle>();
	private SuppVehicleTableModel suppVehicleTableModel;

	JLabel lblSuppCode;
	JLabel lblSuppName;
	JLabel lblPt;
	JLabel lblNpwp;
	JLabel lblSuppType;
	JLabel lblSuppStatus;
	JLabel lblCurrency;
	JLabel lblTop;
	JLabel lblDefaultTax;
	JLabel lblTopDays;
	JLabel lblDefaultTaxPercentage;

	JTextField txtSuppCode;
	JTextField txtSuppName;
	JTextField txtPt;
	JTextField txtNpwp;
	ComboBox<SuppType> cbSuppType;
	JComboBox<String> cbSuppStatus;
	ComboBox<Currency> cbCurrency;
	NumberField txtTop;
	NumberField txtDefaultTax;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JLabel lblSuppAddress;
	JLabel lblErrorSuppAddress;
	JScrollPane scrollPaneSuppAddress;
	JTable tblSuppAddress;
	JButton btnAddSuppAddress;
	JButton btnDeleteSuppAddress;

	JLabel lblSuppVehicle;
	JScrollPane scrollPaneSuppVehicle;
	JTable tblSuppVehicle;
	JButton btnAddSuppVehicle;
	JButton btnDeleteSuppVehicle;

	JPanel panel;
	JScrollPane scrollPane;

	JButton btnCancel;
	JButton btnSave;

	JLabel lblErrorSuppCode;
	JLabel lblErrorSuppName;
	JLabel lblErrorSuppType;
	JLabel lblErrorSuppStatus;
	JLabel lblErrorCurrency;
	JLabel lblErrorTop;
	JLabel lblErrorDefaultTax;

	private SupplierCreatePanel supplierCreate;

	List<SuppType> listOfSuppType;
	List<Bank> listOfBank;
	List<Currency> listOfCurrency;

	public SupplierCreatePanel() {
		supplier = new Supplier();
		supplierCreate = this;

		setLayout(null);
		panel = new JPanel();
		// panel.setBounds(0, 0, 800, 600);
		panel.setPreferredSize(
				new Dimension(MainPanel.bodyPanel.getWidth() - 100, MainPanel.bodyPanel.getHeight() + 200));
		panel.setLayout(null);

		lblSuppCode = new JLabel("<html>Kode Supplier <font color=\"red\">*</font></html>");
		lblSuppCode.setBounds(50, 80, 150, 25);
		panel.add(lblSuppCode);

		txtSuppCode = new JTextField();
		txtSuppCode.setBounds(220, 80, 150, 25);
		txtSuppCode.setDocument(new JTextFieldLimit(9));
		panel.add(txtSuppCode);

		lblErrorSuppCode = new JLabel("Example : BL001");
		lblErrorSuppCode.setForeground(Color.RED);
		lblErrorSuppCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorSuppCode);

		lblSuppName = new JLabel("<html>Nama Supplier <font color=\"red\">*</font></html>");
		lblSuppName.setBounds(50, 110, 150, 25);
		panel.add(lblSuppName);

		txtSuppName = new JTextField();
		txtSuppName.setBounds(220, 110, 150, 25);
		txtSuppName.setDocument(new JTextFieldLimit(200));
		panel.add(txtSuppName);

		lblErrorSuppName = new JLabel();
		lblErrorSuppName.setForeground(Color.RED);
		lblErrorSuppName.setBounds(425, 110, 225, 25);
		panel.add(lblErrorSuppName);

		lblPt = new JLabel("PT");
		lblPt.setBounds(50, 140, 150, 25);
		panel.add(lblPt);

		txtPt = new JTextField();
		txtPt.setBounds(220, 140, 150, 25);
		txtPt.setDocument(new JTextFieldLimit(200));
		panel.add(txtPt);

		lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(50, 170, 150, 25);
		panel.add(lblNpwp);

		txtNpwp = new JTextField();
		txtNpwp.setBounds(220, 170, 150, 25);
		txtNpwp.setDocument(new JTextFieldLimit(30));
		panel.add(txtNpwp);

		lblSuppType = new JLabel("<html>Tipe Supplier <font color=\"red\">*</font></html>");
		lblSuppType.setBounds(50, 200, 150, 25);
		panel.add(lblSuppType);

		listOfSuppType = new ArrayList<SuppType>();
		try {
			listOfSuppType = ServiceFactory.getSupplierBL().getAllSuppType();
			listOfSuppType.add(0, new SuppType("-- Pilih Tipe Supplier --"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
		cbSuppType = new ComboBox<SuppType>();
		cbSuppType.setList(listOfSuppType);
		cbSuppType.setBounds(220, 200, 150, 25);
		panel.add(cbSuppType);

		lblErrorSuppType = new JLabel();
		lblErrorSuppType.setForeground(Color.RED);
		lblErrorSuppType.setBounds(425, 200, 225, 25);
		panel.add(lblErrorSuppType);

//		lblSuppStatus = new JLabel("<html>Status Supplier <font color=\"red\">*</font></html>");
//		lblSuppStatus.setBounds(50, 230, 150, 25);
//		panel.add(lblSuppStatus);

//		cbSuppStatus = new JComboBox<String>();
//		cbSuppStatus.addItem("-- Pilih Status Supplier --");
//		cbSuppStatus.addItem("Aktif");
//		cbSuppStatus.addItem("Nonaktif Sementara");
//		cbSuppStatus.addItem("Nonaktif");
//		cbSuppStatus.setBounds(220, 230, 150, 25);
//		panel.add(cbSuppStatus);
//
//		lblErrorSuppStatus = new JLabel();
//		lblErrorSuppStatus.setForeground(Color.RED);
//		lblErrorSuppStatus.setBounds(425, 230, 225, 25);
//		panel.add(lblErrorSuppStatus);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Supplier");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		/////// Table SuppAddress ///////
		lblSuppAddress = new JLabel("Alamat");
		lblSuppAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSuppAddress.setBounds(50, 260, 150, 25);
		panel.add(lblSuppAddress);

		lblErrorSuppAddress = new JLabel("");
		lblErrorSuppAddress.setForeground(Color.RED);
		lblErrorSuppAddress.setBounds(220, 260, 225, 25);
		panel.add(lblErrorSuppAddress);

		scrollPaneSuppAddress = new JScrollPane();
		scrollPaneSuppAddress.setBounds(50, 295, 975, 150);
		panel.add(scrollPaneSuppAddress);

		suppAddressTableModel = new SuppAddressTableModel(new ArrayList<SuppAddress>());
		tblSuppAddress = new JTable(suppAddressTableModel);
		tblSuppAddress.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblSuppAddress.setFocusable(false);
		scrollPaneSuppAddress.setViewportView(tblSuppAddress);

		tblSuppAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblSuppAddress.getValueAt(tblSuppAddress.getSelectedRow(), 0).equals(true))
					listOfSuppAddress.get(tblSuppAddress.getSelectedRow()).setFlag(false);
				else
					listOfSuppAddress.get(tblSuppAddress.getSelectedRow()).setFlag(true);

				tblSuppAddress.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 6)
						showEditSuppAddressDialog(listOfSuppAddress.get(row), supplierCreate, row);
				}
			}
		});

		btnAddSuppAddress = new JButton("Tambah");
		btnAddSuppAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddSuppAddressDialog(supplierCreate);
			}
		});
		btnAddSuppAddress.setBounds(820, 260, 100, 25);
		panel.add(btnAddSuppAddress);

		btnDeleteSuppAddress = new JButton("Hapus");
		btnDeleteSuppAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteSuppAddress();
			}
		});
		btnDeleteSuppAddress.setBounds(925, 260, 100, 25);
		panel.add(btnDeleteSuppAddress);

		/////// Table SuppCP ///////

		lblSuppVehicle = new JLabel("Kendaraan");
		lblSuppVehicle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSuppVehicle.setBounds(50, 455, 150, 25);
		panel.add(lblSuppVehicle);

		scrollPaneSuppVehicle = new JScrollPane();
		scrollPaneSuppVehicle.setBounds(50, 490, 975, 150);
		panel.add(scrollPaneSuppVehicle);

		suppVehicleTableModel = new SuppVehicleTableModel(new ArrayList<SuppVehicle>());
		tblSuppVehicle = new JTable(suppVehicleTableModel);
		tblSuppVehicle.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblSuppVehicle.setFocusable(false);
		scrollPaneSuppVehicle.setViewportView(tblSuppVehicle);

		tblSuppVehicle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblSuppVehicle.getValueAt(tblSuppVehicle.getSelectedRow(), 0).equals(true))
					listOfSuppVehicle.get(tblSuppVehicle.getSelectedRow()).setFlag(false);
				else
					listOfSuppVehicle.get(tblSuppVehicle.getSelectedRow()).setFlag(true);

				tblSuppVehicle.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 3)
						showEditSuppVehicleDialog(listOfSuppVehicle.get(row), supplierCreate, row);
				}
			}
		});

		btnAddSuppVehicle = new JButton("Tambah");
		btnAddSuppVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddSuppVehicleDialog(supplierCreate);
			}
		});
		btnAddSuppVehicle.setBounds(820, 455, 100, 25);
		panel.add(btnAddSuppVehicle);

		btnDeleteSuppVehicle = new JButton("Hapus");
		btnDeleteSuppVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteSuppVehicle();
			}
		});
		btnDeleteSuppVehicle.setBounds(925, 455, 100, 25);
		panel.add(btnDeleteSuppVehicle);

		///////////////////////////////////////////////////////////////

		lblCurrency = new JLabel("<html>Kurs <font color=\"red\">*</font></html>");
		lblCurrency.setBounds(50, 655, 150, 25);
		panel.add(lblCurrency);

		listOfCurrency = new ArrayList<Currency>();
		try {
			listOfCurrency = ServiceFactory.getSupplierBL().getAllCurrency();
			listOfCurrency.add(0, new Currency("-- Pilih Kurs --"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
		cbCurrency = new ComboBox<Currency>();
		cbCurrency.setList(listOfCurrency);
		
		//default IDR
		final String CURRENCY_IDR = "IDR";
		
		int i = 0;
		for(Currency currency : listOfCurrency) {
			if(CURRENCY_IDR.equals(currency.getCurrencyAbbr())) {
				cbCurrency.setSelectedIndex(i);
				break;
			}
			i++;
		}
		
		cbCurrency.setBounds(220, 655, 150, 25);
		panel.add(cbCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 655, 225, 25);
		panel.add(lblErrorCurrency);

		lblTop = new JLabel("TOP");
		lblTop.setBounds(50, 685, 150, 25);
		panel.add(lblTop);

		txtTop = new NumberField(3);
		txtTop.setBounds(220, 685, 150, 25);
		panel.add(txtTop);

		lblTopDays = new JLabel("hari");
		lblTopDays.setBounds(380, 685, 150, 25);
		panel.add(lblTopDays);

		lblErrorTop = new JLabel();
		lblErrorTop.setForeground(Color.RED);
		lblErrorTop.setBounds(425, 715, 225, 25);
		panel.add(lblErrorTop);

		lblDefaultTax = new JLabel("Default Tax");
		lblDefaultTax.setBounds(50, 715, 150, 25);
		panel.add(lblDefaultTax);

		txtDefaultTax = new NumberField(6);
		txtDefaultTax.setBounds(220, 715, 150, 25);
		panel.add(txtDefaultTax);

		lblDefaultTaxPercentage = new JLabel("%");
		lblDefaultTaxPercentage.setBounds(380, 715, 150, 25);
		panel.add(lblDefaultTaxPercentage);

		lblErrorDefaultTax = new JLabel();
		lblErrorDefaultTax.setForeground(Color.RED);
		lblErrorDefaultTax.setBounds(425, 715, 225, 25);
		panel.add(lblErrorDefaultTax);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// scrollPane.setBounds(0, 0, 800, 605);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		cbCurrency.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				scrollPane.getViewport().setViewPosition(new Point(220, 655));
			}
		});
		add(scrollPane);

		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidate() == false) {
					return;
				}
				int response = DialogBox.showInsertChoice();
				if (response == JOptionPane.YES_OPTION) {
					doSave();
				}
			}
		});
		btnSave.setBounds(925, 760, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
				}
			}
		});
		btnCancel.setBounds(50, 760, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtSuppCode.requestFocusInWindow();
			}
		});

		// makeCodeNumber();
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorSuppCode.setText("");
		lblErrorSuppName.setText("");
		lblErrorSuppType.setText("");
		//lblErrorSuppStatus.setText("");
		lblErrorCurrency.setText("");
		lblErrorTop.setText("");
		lblErrorDefaultTax.setText("");
		lblErrorSuppAddress.setText("");

		if (txtSuppCode.getText() == null || txtSuppCode.getText().length() == 0) {
			lblErrorSuppCode.setText("Textbox Kode Supplier harus diisi.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getSupplierBL().isSuppCodeExists(txtSuppCode.getText()) > 0) {
					lblErrorSuppCode.setText("Kode Supplier sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		if (txtSuppName.getText() == null || txtSuppName.getText().length() == 0) {
			lblErrorSuppName.setText("Textbox Nama Supplier harus diisi.");
			isValid = false;
		}

		if (cbSuppType.getSelectedItem() == null || cbSuppType.getSelectedIndex() == 0) {
			lblErrorSuppType.setText("Combobox Tipe Supplier harus dipilih.");
			isValid = false;
		}

//		if (cbSuppStatus.getSelectedItem() == null || cbSuppStatus.getSelectedIndex() == 0) {
//			lblErrorSuppStatus.setText("Combobox Status Supplier harus dipilih.");
//			isValid = false;
//		}

		if (cbCurrency.getSelectedItem() == null || cbCurrency.getSelectedIndex() == 0) {
			lblErrorCurrency.setText("Combobox Kurs harus dipilih.");
			isValid = false;
		}

		if (!"".equals(txtDefaultTax.getText())) {
			if (Double.valueOf(txtDefaultTax.getText()) > 100.00) {
				lblErrorDefaultTax.setText("Default Tax tidak lebih dari 100%");
				isValid = false;
			}
		}

//		if (listOfSuppAddress.isEmpty()) {
//			lblErrorSuppAddress.setText("Alamat supplier harus diisi minimal 1.");
//			isValid = false;
//		}

		return isValid;
	}

	protected void doSave() {
		supplier = new Supplier();
		supplier.setSuppCode(txtSuppCode.getText());
		supplier.setSuppName(txtSuppName.getText());
		supplier.setPt(txtPt.getText());
		supplier.setNpwp(txtNpwp.getText());
		supplier.setSuppTypeId(cbSuppType.getDataIndex().getId());
		//supplier.setSuppStatus(cbSuppStatus.getSelectedItem().toString());
		supplier.setCurrencyId(cbCurrency.getDataIndex().getId());

		if (!"".equals(txtTop.getText()))
			supplier.setTop(Integer.valueOf(txtTop.getText()));
		else
			supplier.setTop(0);

		if (!"".equals(txtDefaultTax.getText()))
			supplier.setDefaultTax(Double.valueOf(txtDefaultTax.getText()));
		else
			supplier.setDefaultTax(0.00);

		try {
			ServiceFactory.getSupplierBL().save(supplier, listOfSuppAddress, listOfSuppVehicle);
			DialogBox.showInsert();
			MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
		} catch (SQLException e) {
			e.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add supp address dialog
	 */
	protected void showAddSuppAddressDialog(SupplierCreatePanel supplierCreate) {
		SuppAddressDialog suppAddressDialog = new SuppAddressDialog(false, new SuppAddress(), supplierCreate, null);
		suppAddressDialog.setTitle("Alamat");
		suppAddressDialog.setLocationRelativeTo(null);
		suppAddressDialog.setVisible(true);
	}

	protected void showEditSuppAddressDialog(SuppAddress suppAddress, SupplierCreatePanel supplierCreate,
			Integer index) {
		SuppAddressDialog suppAddressDialog = new SuppAddressDialog(true, suppAddress, supplierCreate, index);
		suppAddressDialog.setTitle("Alamat");
		suppAddressDialog.setLocationRelativeTo(null);
		suppAddressDialog.setVisible(true);
	}

	protected void doDeleteSuppAddress() {
		if (listOfSuppAddress.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<SuppAddress> temp = new ArrayList<SuppAddress>();
			for (SuppAddress s : listOfSuppAddress) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else
					count += 1;
			}

			if (count == listOfSuppAddress.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (SuppAddress s : temp) {
					listOfSuppAddress.remove(s);
				}
				refreshTableSuppAddress();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Method to display add supp cp dialog
	 */
	protected void showAddSuppVehicleDialog(SupplierCreatePanel supplierCreate) {
		SuppVehicleDialog suppVehicleDialog = new SuppVehicleDialog(false, new SuppVehicle(), supplierCreate, null);
		suppVehicleDialog.setTitle("Kendaraan");
		suppVehicleDialog.setLocationRelativeTo(null);
		suppVehicleDialog.setVisible(true);
	}

	protected void showEditSuppVehicleDialog(SuppVehicle suppVehicle, SupplierCreatePanel supplierCreate,
			Integer index) {
		SuppVehicleDialog suppVehicleDialog = new SuppVehicleDialog(true, suppVehicle, supplierCreate, index);
		suppVehicleDialog.setTitle("Kendaraan");
		suppVehicleDialog.setLocationRelativeTo(null);
		suppVehicleDialog.setVisible(true);
	}

	protected void doDeleteSuppVehicle() {
		if (listOfSuppVehicle.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<SuppVehicle> temp = new ArrayList<SuppVehicle>();
			for (SuppVehicle s : listOfSuppVehicle) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfSuppVehicle.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (SuppVehicle s : temp) {
					listOfSuppVehicle.remove(s);
				}
				refreshTableSuppVehicle();
				DialogBox.showDelete();
			}
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
		// try {
		// tblSuppCp.setModel(new SuppCpTableModel(listOfSuppCp));
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// DialogBox.showErrorException();
		// }
	}

	public void refreshTableSuppVehicle() {
		try {
			tblSuppVehicle.setModel(new SuppVehicleTableModel(listOfSuppVehicle));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}
}
