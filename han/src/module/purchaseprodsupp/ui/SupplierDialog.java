package module.purchaseprodsupp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.supplier.model.Supplier;
import module.util.JTextFieldLimit;

public class SupplierDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(SupplierDialog.class);

	JPanel panel;

	JLabel lblSuppName;
	JLabel lblSuppCode;
	JLabel lblContactPerson;
	JLabel lblCity;
	JLabel lblAddress;
	JLabel lblPhone;

	JTextField txtSuppName;
	JTextField txtSuppCode;
	JTextField txtContactPerson;
	JTextField txtCity;
	JTextField txtAddress;
	JTextField txtPhone;

	JButton btnSearch;
	JButton btnSave;
	JButton btnClose;
	
	List<Supplier> suppliers = new ArrayList<Supplier>();
	
	JScrollPane scrollPaneSupplier;

	private SupplierTableModel supplierTableModel;
	
	JTable tblSupplier;

	private PurchaseProdSuppCreatePanel ppsCreatePanel;
	private PurchaseProdSuppEditPanel ppsEditPanel;

	private Supplier addSupplier = null;

	public SupplierDialog(PurchaseProdSuppCreatePanel ppsCreatePanel) {
		this.ppsCreatePanel = ppsCreatePanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		getContentPane().setLayout(null);

		load();
	}
	
	public SupplierDialog(PurchaseProdSuppEditPanel ppsEditPanel) {
		this.ppsEditPanel = ppsEditPanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		getContentPane().setLayout(null);

		load();
	}
	
	public void load() {
		lblSuppName = new JLabel("Nama Supplier");
		lblSuppName.setBounds(25, 15, 150, 25);
		getContentPane().add(lblSuppName);

		txtSuppName = new JTextField();
		txtSuppName.setBounds(120, 15, 150, 25);
		txtSuppName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtSuppName);

		lblSuppCode = new JLabel("Kode Supplier");
		lblSuppCode.setBounds(25, 45, 150, 25);
		getContentPane().add(lblSuppCode);

		txtSuppCode = new JTextField();
		txtSuppCode.setBounds(120, 45, 150, 25);
		txtSuppCode.setDocument(new JTextFieldLimit(100));
		getContentPane().add(txtSuppCode);

		lblContactPerson = new JLabel("Contact Person");
		lblContactPerson.setBounds(25, 75, 150, 25);
		getContentPane().add(lblContactPerson);

		txtContactPerson = new JTextField();
		txtContactPerson.setBounds(120, 75, 150, 25);
		txtContactPerson.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtContactPerson);

		lblCity = new JLabel("Kota");
		lblCity.setBounds(25, 105, 150, 25);
		getContentPane().add(lblCity);

		txtCity = new JTextField();
		txtCity.setBounds(120, 105, 150, 25);
		txtCity.setDocument(new JTextFieldLimit(30));
		getContentPane().add(txtCity);

		lblAddress = new JLabel("Alamat");
		lblAddress.setBounds(25, 135, 150, 25);
		getContentPane().add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(120, 135, 150, 25);
		txtAddress.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtAddress);
		
		lblPhone = new JLabel("Telepon");
		lblPhone.setBounds(25, 165, 150, 25);
		getContentPane().add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(120, 165, 150, 25);
		txtPhone.setDocument(new JTextFieldLimit(30));
		getContentPane().add(txtPhone);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdvancedSearch();
			}
		});
		btnSearch.setBounds(280, 165, 100, 25);
		getContentPane().add(btnSearch);
		
		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});
		btnSave.setBounds(349, 385, 100, 25);
		getContentPane().add(btnSave);
		
		btnClose = new JButton("Tutup");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		btnClose.setBounds(459, 385, 100, 25);
		getContentPane().add(btnClose);
		
		scrollPaneSupplier = new JScrollPane();
		scrollPaneSupplier.setBounds(25, 210, 535, 150);
		add(scrollPaneSupplier);

		supplierTableModel = new SupplierTableModel(new ArrayList<Supplier>());
		tblSupplier = new JTable(supplierTableModel);
		tblSupplier.setFocusable(false);
		tblSupplier.setAutoCreateRowSorter(true);
		scrollPaneSupplier.setViewportView(tblSupplier);

		tblSupplier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				addSupplier = new Supplier();
				addSupplier = suppliers.get(row);
			}
		});
		
		doAdvancedSearch();
	}

	public void doAdvancedSearch() {
		try {
			Supplier supplier = new Supplier();
			supplier.setSuppCode(txtSuppCode.getText());
			supplier.setSuppName(txtSuppName.getText());
			supplier.setContactPerson(txtContactPerson.getText());
			supplier.setCity(txtCity.getText());
			supplier.setAddress(txtAddress.getText());
			supplier.setPhone(txtPhone.getText());
			
			suppliers = ServiceFactory.getPurchaseProductSuppBL().getAllSupplierByAdvancedSearch(supplier);
			refreshTableSupplier(suppliers);
			//closeDialog();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}
	
	public void doSave() {
		if(addSupplier != null) {
			if(ppsCreatePanel != null) {
				ppsCreatePanel.txtSupplier.setText(addSupplier.getSuppName());;
				ppsCreatePanel.setSupplierCode(addSupplier.getSuppCode());
			}
			
			if(ppsEditPanel != null) {
				ppsEditPanel.txtSupplier.setText(addSupplier.getSuppName());;
				ppsEditPanel.setSupplierCode(addSupplier.getSuppCode());
			}
		}
		closeDialog();
	}

	protected void closeDialog() {
		dispose();
	}
	
	public void refreshTableSupplier(List<Supplier> suppliers) {
		try {
			tblSupplier.setModel(new SupplierTableModel(suppliers));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	/**
	 * Class as TableModel for Supplier table
	 * 
	 * @author TSI
	 *
	 */
	class SupplierTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<Supplier> listOfSupplier;

		public SupplierTableModel(List<Supplier> listOfSupplier) {
			this.listOfSupplier = listOfSupplier;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSupplier.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 6;
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
			Supplier p = listOfSupplier.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getSuppCode();
			case 1:
				return p.getSuppName();
			case 2:
				return p.getContactPerson();
			case 3:
				return p.getCity();
			case 4:
				return p.getAddress();
			case 5:
				return p.getPhone();
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
				return "Kode Supplier";
			case 1:
				return "Nama Supplier";
			case 2:
				return "Contact Person";
			case 3:
				return "Kota";
			case 4:
				return "Alamat";
			case 5:
				return "Telepon";
			default:
				return "";
			}
		}
	}
}
