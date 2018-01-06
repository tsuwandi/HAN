package module.sales.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.customer.model.CustAddress;
import module.customer.model.Customer;
import module.sn.bank.model.BankCust;

public class CustomerListDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CustomerListDialog.class);

	JPanel panel;

	JButton btnSearch;

	JTextField txtSearch;

	JScrollPane scrollPaneCustomer;

	private CustomerTableModel customerTableModel;

	public List<Customer> listOfCustomer = new ArrayList<Customer>();

	JTable tblCustomer;

	private SalesCreatePanel salesCreate;

	public CustomerListDialog(SalesCreatePanel salesCreate) {
		this.salesCreate = salesCreate;
		init();
	}
	
	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 420);
		getContentPane().setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setBounds(25, 15, 150, 25);
		getContentPane().add(txtSearch);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doSearch(txtSearch.getText());
			}
		});
		btnSearch.setBounds(190, 15, 75, 25);
		add(btnSearch);

		scrollPaneCustomer = new JScrollPane();
		scrollPaneCustomer.setBounds(25, 45, 480, 300);
		add(scrollPaneCustomer);

		customerTableModel = new CustomerTableModel(new ArrayList<Customer>());
		tblCustomer = new JTable(customerTableModel);
		tblCustomer.setFocusable(false);
		tblCustomer.setAutoCreateRowSorter(true);
		scrollPaneCustomer.setViewportView(tblCustomer);

		tblCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 3) {
						try {
							doInsert(listOfCustomer.get(row));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		try {
			listOfCustomer = new ArrayList<Customer>();
			listOfCustomer = ServiceFactory.getCustomerBL().getAllCustomer();
			refreshTableCustomer();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	protected void doInsert(Customer customer) throws SQLException {

		salesCreate.txtCustCode.setText(customer.getCustCode());
		salesCreate.txtCustName.setText(customer.getCustName());
		salesCreate.txtCustId.setText(Integer.toString(customer.getId()));

		salesCreate.cbCustAddress.removeAllItems();
		salesCreate.listOfCustAddress = ServiceFactory.getSalesBL().getCustAddressByCustCode(customer.getCustCode());
		if (salesCreate.listOfCustAddress.isEmpty()) {
			salesCreate.cbCustAddress.setEnabled(false);
			salesCreate.txtAddress.setEnabled(false);
		} else {
			for (CustAddress custAddress : salesCreate.listOfCustAddress) {
				salesCreate.cbCustAddress.addItem(custAddress);
			}
			salesCreate.cbCustAddress.setEnabled(true);
		}
		
		salesCreate.cbBankCust.removeAllItems();
		salesCreate.listOfBankCust = ServiceFactory.getSalesBL().getBankCustByCustCode(customer.getCustCode());
		if (salesCreate.listOfBankCust.isEmpty()) {
			salesCreate.cbBankCust.setEnabled(false);
		} else {
			for (BankCust bankCust : salesCreate.listOfBankCust) {
				salesCreate.cbBankCust.addItem(bankCust);
			}
			salesCreate.cbBankCust.setEnabled(true);
		}

		DialogBox.showInsert();

		dispose();
	}

	public void refreshTableCustomer() {
		try {
			tblCustomer.setModel(new CustomerTableModel(listOfCustomer));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfCustomer = new ArrayList<Customer>();
			listOfCustomer = ServiceFactory.getCustomerBL().getAllCustomerBySimpleSearch(value);
			refreshTableCustomer();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for Customer table
	 * 
	 * @author TLO
	 *
	 */
	class CustomerTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<Customer> listOfCustomer;

		public CustomerTableModel(List<Customer> listOfCustomer) {
			this.listOfCustomer = listOfCustomer;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfCustomer.size();
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

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
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
		 * @return ({@link CustomerAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Customer p = listOfCustomer.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getCustCode();
			case 1:
				return p.getCustName();
			case 2:
				return p.getPt();
			case 3:
				return "<html><a><u>Pilih</u></a></html>";
			default:
				throw new IllegalArgumentException("Invalid column index");
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
				return "Kode Customer";
			case 1:
				return "Nama Customer";
			case 2:
				return "PT";
			case 3:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
