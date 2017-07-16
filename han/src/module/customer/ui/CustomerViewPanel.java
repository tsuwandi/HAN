package module.customer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import module.customer.model.CustAddress;
import module.customer.model.CustType;
import module.customer.model.Customer;
import module.sn.bank.model.Bank;
import module.sn.country.model.Country;
import module.sn.currency.model.Currency;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class CustomerViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CustomerCreatePanel.class);

	private Customer customer;
	public List<CustAddress> listOfCustAddress = new ArrayList<CustAddress>();
	private CustAddressTableModel custAddressTableModel;

	JLabel lblCustCode;
	JLabel lblCustName;
	JLabel lblPt;
	JLabel lblNpwp;
	JLabel lblCountry;
	JLabel lblCustType;
	JLabel lblCurrency;
	JLabel lblTop;
	JLabel lblBankAccount;
	JLabel lblBank;
	JLabel lblAccountOwner;
	JLabel lblDefaultTax;
	JLabel lblTopDays;
	JLabel lblDefaultTaxPercentage;

	JTextField txtCustCode;
	JTextField txtCustName;
	JTextField txtPt;
	JTextField txtNpwp;
	JComboBox<String> cbCountry;
	JComboBox<String> cbCustType;
	JComboBox<String> cbCurrency;
	JComboBox<String> cbBank;
	JTextField txtBankAccount;
	JTextField txtAccountOwner;
	NumberField txtTop;
	NumberField txtDefaultTax;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JLabel lblCustAddress;
	JLabel lblErrorCustAddress;
	JScrollPane scrollPaneCustAddress;
	JTable tblCustAddress;
	JButton btnAddCustAddress;
	JButton btnDeleteCustAddress;

	JPanel panel;
	JScrollPane scrollPane;

	JButton btnCancel;
	JButton btnPrint;
	JButton btnDelete;
	JButton btnEdit;

	JLabel lblErrorCustCode;
	JLabel lblErrorCustName;
	JLabel lblErrorCountry;
	JLabel lblErrorCustType;
	JLabel lblErrorCurrency;
	JLabel lblErrorBank;
	JLabel lblErrorBankAccount;
	JLabel lblErrorAccountOwner;
	JLabel lblErrorTop;
	JLabel lblErrorDefaultTax;

	private CustomerViewPanel customerView;

	List<Country> listOfCountry;
	List<CustType> listOfCustType;
	List<Bank> listOfBank;
	List<Currency> listOfCurrency;

	public CustomerViewPanel() {
		customerView = this;
		setLayout(null);
		panel = new JPanel();
		// panel.setPreferredSize(new Dimension(MainPanel.bodyPanel.getWidth() - 100,
		// MainPanel.bodyPanel.getHeight()));
		panel.setPreferredSize(new Dimension(800, 740));
		panel.setLayout(null);

		lblCustCode = new JLabel("<html>Kode Customer <font color=\"red\">*</font></html>");
		lblCustCode.setBounds(50, 80, 150, 25);
		panel.add(lblCustCode);

		txtCustCode = new JTextField();
		txtCustCode.setBounds(220, 80, 150, 25);
		txtCustCode.setDocument(new JTextFieldLimit(9));
		txtCustCode.setEnabled(false);
		panel.add(txtCustCode);

		lblErrorCustCode = new JLabel();
		lblErrorCustCode.setForeground(Color.RED);
		lblErrorCustCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorCustCode);

		lblCustName = new JLabel("<html>Nama Customer <font color=\"red\">*</font></html>");
		lblCustName.setBounds(50, 110, 150, 25);
		panel.add(lblCustName);

		txtCustName = new JTextField();
		txtCustName.setBounds(220, 110, 150, 25);
		txtCustName.setDocument(new JTextFieldLimit(200));
		txtCustName.setEnabled(false);
		panel.add(txtCustName);

		lblErrorCustName = new JLabel();
		lblErrorCustName.setForeground(Color.RED);
		lblErrorCustName.setBounds(425, 110, 225, 25);
		panel.add(lblErrorCustName);

		lblPt = new JLabel("PT");
		lblPt.setBounds(50, 140, 150, 25);
		panel.add(lblPt);

		txtPt = new JTextField();
		txtPt.setBounds(220, 140, 150, 25);
		txtPt.setDocument(new JTextFieldLimit(200));
		txtPt.setEnabled(false);
		panel.add(txtPt);

		lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(50, 170, 150, 25);
		panel.add(lblNpwp);

		txtNpwp = new JTextField();
		txtNpwp.setBounds(220, 170, 150, 25);
		txtNpwp.setDocument(new JTextFieldLimit(30));
		txtNpwp.setEnabled(false);
		panel.add(txtNpwp);

		lblCustType = new JLabel("<html>Tipe Customer <font color=\"red\">*</font></html>");
		lblCustType.setBounds(50, 200, 150, 25);
		panel.add(lblCustType);

		cbCustType = new JComboBox<String>();
		cbCustType.setEnabled(false);
		cbCustType.addItem("-- Pilih Tipe Customer --");
		cbCustType.setBounds(220, 200, 150, 25);
		panel.add(cbCustType);

		lblErrorCustType = new JLabel();
		lblErrorCustType.setForeground(Color.RED);
		lblErrorCustType.setBounds(425, 200, 225, 25);
		panel.add(lblErrorCustType);

		lblCountry = new JLabel("Country");
		lblCountry.setBounds(50, 230, 150, 25);
		panel.add(lblCountry);

		cbCountry = new JComboBox<String>();
		cbCountry.setEnabled(false);
		cbCountry.addItem("-- Pilih Country --");
		cbCountry.setBounds(220, 230, 150, 25);
		panel.add(cbCountry);

		lblErrorCountry = new JLabel();
		lblErrorCountry.setForeground(Color.RED);
		lblErrorCountry.setBounds(425, 230, 225, 25);
		panel.add(lblErrorCountry);

		lblBreadcrumb = new JLabel("ERP > Penjualan > Customer");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("View Detail");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		/////// Table CustAddress ///////
		lblCustAddress = new JLabel("Alamat");
		lblCustAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustAddress.setBounds(50, 290, 150, 25);
		panel.add(lblCustAddress);

		scrollPaneCustAddress = new JScrollPane();
		scrollPaneCustAddress.setBounds(50, 325, 975, 150);
		panel.add(scrollPaneCustAddress);

		custAddressTableModel = new CustAddressTableModel(new ArrayList<CustAddress>());
		tblCustAddress = new JTable(custAddressTableModel);
		tblCustAddress.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblCustAddress.setFocusable(false);
		scrollPaneCustAddress.setViewportView(tblCustAddress);

		tblCustAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 2)
						showViewCustAddressDialog(listOfCustAddress.get(row), customerView, row);
				}
			}
		});

		btnAddCustAddress = new JButton("Tambah");
		btnAddCustAddress.setEnabled(false);
		btnAddCustAddress.setBounds(820, 290, 100, 25);
		panel.add(btnAddCustAddress);

		btnDeleteCustAddress = new JButton("Hapus");
		btnDeleteCustAddress.setEnabled(false);
		btnDeleteCustAddress.setBounds(925, 290, 100, 25);
		panel.add(btnDeleteCustAddress);

		lblBankAccount = new JLabel("No Akun Bank");
		lblBankAccount.setBounds(50, 485, 150, 25);
		panel.add(lblBankAccount);

		txtBankAccount = new JTextField();
		txtBankAccount.setBounds(220, 485, 150, 25);
		txtBankAccount.setDocument(new JTextFieldLimit(30));
		txtBankAccount.setEnabled(false);
		panel.add(txtBankAccount);

		lblErrorBankAccount = new JLabel();
		lblErrorBankAccount.setForeground(Color.RED);
		lblErrorBankAccount.setBounds(425, 685, 225, 25);
		panel.add(lblErrorBankAccount);

		lblBank = new JLabel("Bank");
		lblBank.setBounds(50, 515, 150, 25);
		panel.add(lblBank);

		cbBank = new JComboBox<String>();
		cbBank.setEnabled(false);
		cbBank.addItem("-- Pilih Bank --");
		cbBank.setBounds(220, 515, 150, 25);
		panel.add(cbBank);

		lblErrorBank = new JLabel();
		lblErrorBank.setForeground(Color.RED);
		lblErrorBank.setBounds(425, 545, 225, 25);
		panel.add(lblErrorBank);

		lblAccountOwner = new JLabel("Nama Pemilik Account");
		lblAccountOwner.setBounds(50, 545, 150, 25);
		panel.add(lblAccountOwner);

		txtAccountOwner = new JTextField();
		txtAccountOwner.setBounds(220, 545, 150, 25);
		txtAccountOwner.setDocument(new JTextFieldLimit(30));
		txtAccountOwner.setEnabled(false);
		panel.add(txtAccountOwner);

		lblErrorAccountOwner = new JLabel();
		lblErrorAccountOwner.setForeground(Color.RED);
		lblErrorAccountOwner.setBounds(425, 545, 225, 25);
		panel.add(lblErrorAccountOwner);

		lblCurrency = new JLabel("Kurs");
		lblCurrency.setBounds(50, 575, 150, 25);
		panel.add(lblCurrency);

		cbCurrency = new JComboBox<String>();
		cbCurrency.setEnabled(false);
		cbCurrency.addItem("-- Pilih Kurs --");
		cbCurrency.setBounds(220, 575, 150, 25);
		panel.add(cbCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 575, 225, 25);
		panel.add(lblErrorCurrency);

		lblTop = new JLabel("TOP");
		lblTop.setBounds(50, 605, 150, 25);
		panel.add(lblTop);

		txtTop = new NumberField(3);
		txtTop.setEnabled(false);
		txtTop.setBounds(220, 605, 150, 25);
		panel.add(txtTop);

		lblTopDays = new JLabel("hari");
		lblTopDays.setBounds(380, 605, 150, 25);
		panel.add(lblTopDays);

		lblErrorTop = new JLabel();
		lblErrorTop.setForeground(Color.RED);
		lblErrorTop.setBounds(425, 605, 225, 25);
		panel.add(lblErrorTop);

		lblDefaultTax = new JLabel("Default Pajak");
		lblDefaultTax.setBounds(50, 635, 150, 25);
		panel.add(lblDefaultTax);

		txtDefaultTax = new NumberField(6);
		txtDefaultTax.setEnabled(false);
		txtDefaultTax.setBounds(220, 635, 150, 25);
		panel.add(txtDefaultTax);

		lblDefaultTaxPercentage = new JLabel("%");
		lblDefaultTaxPercentage.setBounds(380, 635, 150, 25);
		panel.add(lblDefaultTaxPercentage);

		lblErrorDefaultTax = new JLabel();
		lblErrorDefaultTax.setForeground(Color.RED);
		lblErrorDefaultTax.setBounds(425, 635, 225, 25);
		panel.add(lblErrorDefaultTax);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPane);

		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPrint();
			}
		});
		btnPrint.setBounds(715, 680, 100, 25);
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
		btnDelete.setBounds(820, 680, 100, 25);
		panel.add(btnDelete);

		btnEdit = new JButton("Ubah");
		btnEdit.setBounds(925, 680, 100, 25);
		panel.add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.customer.ui.CustomerEditPanel", customer);
			}
		});

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.customer.ui.CustomerListPanel");
			}
		});
		btnCancel.setBounds(50, 680, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
	}

	protected void showViewCustAddressDialog(CustAddress custAddress, CustomerViewPanel customerView, Integer index) {
		CustomerAddressDialog custAddressDialog = new CustomerAddressDialog(true, custAddress, customerView, index);
		custAddressDialog.setTitle("Alamat");
		custAddressDialog.setLocationRelativeTo(null);
		custAddressDialog.setVisible(true);
	}

	protected void loadData(Integer customerId) {
		try {
			customer = ServiceFactory.getCustomerBL().getCustomerById(customerId);
			listOfCustAddress = ServiceFactory.getCustomerBL().getCustAddressByCustCode(customer.getCustCode());

			if (customer != null) {
				txtCustCode.setText(customer.getCustCode());
				txtCustName.setText(customer.getCustName());
				txtPt.setText(customer.getPt());
				txtNpwp.setText(customer.getNpwp());
				cbCustType.addItem(customer.getCustType());
				cbCustType.setSelectedIndex(1);
				cbCountry.addItem(customer.getCountry());
				cbCountry.setSelectedIndex(1);
				txtBankAccount.setText(customer.getAccountNo());
				cbBank.addItem(customer.getBank().getBank());
				cbBank.setSelectedIndex(1);
				txtAccountOwner.setText(customer.getAccountName());
				txtDefaultTax.setText(String.valueOf(customer.getDefaultTax()));
				cbCurrency.addItem(customer.getCurrency().getCurrency());
				cbCurrency.setSelectedIndex(1);
				txtTop.setText(String.valueOf(customer.getTop()));

				refreshTableCustAddress();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void refreshTableCustAddress() {
		try {
			tblCustAddress.setModel(new CustAddressTableModel(listOfCustAddress));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void doPrint() {

	}

	protected void doDelete() {
		try {
			ServiceFactory.getCustomerBL().deleteAll(customer);
			DialogBox.showDelete();
			MainPanel.changePanel("module.customer.ui.CustomerListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for Cust Address table
	 * 
	 * @author Sandy
	 *
	 */
	class CustAddressTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<CustAddress> listOfCustAddress;

		public CustAddressTableModel(List<CustAddress> listOfCustAddress) {
			this.listOfCustAddress = listOfCustAddress;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfCustAddress.size();
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
		 * @return ({@link CustomerAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			CustAddress p = listOfCustAddress.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getAddressType();
			case 2:
				return p.getAddress();
			case 3:
				return p.getName();
			case 4:
				return p.getPhone();
			case 5:
				return p.getFax();
			case 6:
				return "<html><u>Edit</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
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

	@Override
	public void invokeObjects(Object... objects) {
		this.customer = (Customer) objects[0];

		loadData(customer.getId());
	}
}
