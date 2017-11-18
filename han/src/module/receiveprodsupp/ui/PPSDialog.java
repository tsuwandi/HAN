package module.receiveprodsupp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.receiveprodsupp.model.RPSProduct;
import module.supplier.model.Supplier;
import module.util.DateUtil;
import module.util.JTextFieldLimit;

public class PPSDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(PPSDialog.class);

	JPanel panel;

	JLabel lblPOCode;
	JLabel lblSuppName;
	JLabel lblSuppCode;
	JLabel lblPurchaseDate;

	JTextField txtSuppName;
	JTextField txtSuppCode;
	JTextField txtPOCode;
	JDateChooser dcPurchaseDate;

	JButton btnSearch;
	JButton btnSave;
	JButton btnClose;
	
	List<PurchaseProdSupp> purchaseProdSupps = new ArrayList<PurchaseProdSupp>();
	
	JScrollPane scrollPanePurchaseProdSupp;

	private PurchaseProdSuppTableModel purchaseProdSuppTableModel;
	
	JTable tblPurchaseProdSupp;

	private ReceiveProdSuppCreatePanel rpsCreatePanel;
	private ReceiveProdSuppEditPanel rpsEditPanel;

	private PurchaseProdSupp addPurchaseProdSupp = null;

	public PPSDialog(ReceiveProdSuppCreatePanel rpsCreatePanel) {
		this.rpsCreatePanel = rpsCreatePanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);

		load();
	}
	
	public PPSDialog(ReceiveProdSuppEditPanel rpsEditPanel) {
		this.rpsEditPanel = rpsEditPanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		getContentPane().setLayout(null);

		load();
	}
	
	public void load() {
		lblPOCode = new JLabel("Kode Pembelian");
		lblPOCode.setBounds(25, 15, 150, 25);
		getContentPane().add(lblPOCode);

		txtPOCode = new JTextField();
		txtPOCode.setBounds(120, 15, 150, 25);
		txtPOCode.setDocument(new JTextFieldLimit(50));
		getContentPane().add(txtPOCode);
		
		lblSuppName = new JLabel("Nama Supplier");
		lblSuppName.setBounds(25, 45, 150, 25);
		getContentPane().add(lblSuppName);

		txtSuppName = new JTextField();
		txtSuppName.setBounds(120, 45, 150, 25);
		txtSuppName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtSuppName);

		lblSuppCode = new JLabel("Kode Supplier");
		lblSuppCode.setBounds(25, 75, 150, 25);
		getContentPane().add(lblSuppCode);

		txtSuppCode = new JTextField();
		txtSuppCode.setBounds(120, 75, 150, 25);
		txtSuppCode.setDocument(new JTextFieldLimit(100));
		getContentPane().add(txtSuppCode);

//		lblPurchaseDate = new JLabel("Tanggal Pembelian");
//		lblPurchaseDate.setBounds(25, 105, 150, 25);
//		getContentPane().add(lblPurchaseDate);
//
//		dcPurchaseDate = new JDateChooser();
//		dcPurchaseDate.setBounds(120, 105, 150, 25);
//		getContentPane().add(dcPurchaseDate);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdvancedSearch();
			}
		});
		btnSearch.setBounds(280, 75, 100, 25);
		getContentPane().add(btnSearch);
		
		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});
		btnSave.setBounds(349, 295, 100, 25);
		getContentPane().add(btnSave);
		
		btnClose = new JButton("Tutup");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		btnClose.setBounds(459, 295, 100, 25);
		getContentPane().add(btnClose);
		
		scrollPanePurchaseProdSupp = new JScrollPane();
		scrollPanePurchaseProdSupp.setBounds(25, 135, 535, 150);
		add(scrollPanePurchaseProdSupp);

		purchaseProdSuppTableModel = new PurchaseProdSuppTableModel(new ArrayList<PurchaseProdSupp>());
		tblPurchaseProdSupp = new JTable(purchaseProdSuppTableModel);
		tblPurchaseProdSupp.setFocusable(false);
		tblPurchaseProdSupp.setAutoCreateRowSorter(true);
		scrollPanePurchaseProdSupp.setViewportView(tblPurchaseProdSupp);

		tblPurchaseProdSupp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				addPurchaseProdSupp = new PurchaseProdSupp();
				addPurchaseProdSupp = purchaseProdSupps.get(row);
			}
		});
		
		doAdvancedSearch();
	}

	public void doAdvancedSearch() {
		try {
			PurchaseProdSupp purchaseProdSupp = new PurchaseProdSupp();
			purchaseProdSupp.setPpsCode(txtPOCode.getText());
			Supplier supplier = new Supplier();
			supplier.setSuppCode(txtSuppCode.getText());
			supplier.setSuppName(txtSuppName.getText());
			purchaseProdSupp.setSupplier(supplier);
			purchaseProdSupps = ServiceFactory.getReceiveProductSuppBL().getAllPurchaseProdSuppByAdvancedSearch(purchaseProdSupp);
			refreshTablePurchaseProdSupp(purchaseProdSupps);
			//closeDialog();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}
	
	public void doSave() {
		if(addPurchaseProdSupp != null) {
			if(rpsCreatePanel != null) {
				rpsCreatePanel.txtPurchaseProductSuppCode.setText(addPurchaseProdSupp.getPpsCode());
				rpsCreatePanel.txtSupplier.setText(addPurchaseProdSupp.getSupplier().getSuppName());
				rpsCreatePanel.txtCostCenter.setText(addPurchaseProdSupp.getCostCenter().getCostCenter());
				rpsCreatePanel.setListOfRPSProduct(new ArrayList<RPSProduct>());
				rpsCreatePanel.refreshTableRPSProduct();
			}
			
			if(rpsEditPanel != null) {
				rpsEditPanel.txtPurchaseProductSuppCode.setText(addPurchaseProdSupp.getPpsCode());
				rpsEditPanel.txtSupplier.setText(addPurchaseProdSupp.getSupplier().getSuppName());
				rpsEditPanel.txtCostCenter.setText(addPurchaseProdSupp.getCostCenter().getCostCenter());
				rpsEditPanel.setListOfRPSProduct(new ArrayList<RPSProduct>());
				rpsEditPanel.setListOfDeletedRPSProduct(new ArrayList<RPSProduct>());
				rpsEditPanel.refreshTableRPSProduct();
			}
		}
		closeDialog();
	}

	protected void closeDialog() {
		dispose();
	}
	
	public void refreshTablePurchaseProdSupp(List<PurchaseProdSupp> purchaseProdSupps) {
		try {
			tblPurchaseProdSupp.setModel(new PurchaseProdSuppTableModel(purchaseProdSupps));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	/**
	 * Class as TableModel for PurchaseProdSupp table
	 * 
	 * @author TSI
	 *
	 */
	class PurchaseProdSuppTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<PurchaseProdSupp> listOfPurchaseProdSupp;

		public PurchaseProdSuppTableModel(List<PurchaseProdSupp> listOfPurchaseProdSupp) {
			this.listOfPurchaseProdSupp = listOfPurchaseProdSupp;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPurchaseProdSupp.size();
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
			case 3:
				return String.class;	
			case 4:
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
		 * @return ({@link PurchaseProdSuppAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PurchaseProdSupp p = listOfPurchaseProdSupp.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getPpsCode();
			case 1:
				return p.getSuppCode();
			case 2:
				return p.getSupplier().getSuppName();
			case 3:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getPurchaseDate()));
			case 4:
				return p.getStatus();
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
				return "Kode Pembelian";
			case 1:
				return "Kode Supplier";
			case 2:
				return "Nama Supplier";
			case 3:
				return "Tanggal Pembelian";
			case 4:
				return "Status";
			default:
				return "";
			}
		}
	}
}
