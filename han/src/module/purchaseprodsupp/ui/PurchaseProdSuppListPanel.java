package module.purchaseprodsupp.ui;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.util.DateUtil;

public class PurchaseProdSuppListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(PurchaseProdSuppListPanel.class);

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePurchaseProdSupp;

	private PurchaseProdSuppTableModel purchaseProdSuppTableModel;
	public List<PurchaseProdSupp> listOfPurchaseProdSupp = new ArrayList<PurchaseProdSupp>();

	JTable tblPurchaseProdSupp;

	private PurchaseProdSuppListPanel purchaseProdSuppListPanel;

	private static final long serialVersionUID = 1L;
	
	public static final String COMPLETED = "COMPLETED";

	public PurchaseProdSuppListPanel() {
		purchaseProdSuppListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Produksi > Barang Pendukung");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 600, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("List PO Barang Pendukung");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.purchaseprodsupp.ui.PurchaseProdSuppCreatePanel");
			}
		});
		btnCreateNew.setBounds(700, 80, 100, 30);
		add(btnCreateNew);

		btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnExport.setBounds(800, 80, 100, 30);
		add(btnExport);

		btnAdvancedSearch = new JButton("Pencarian Lanjut");
		btnAdvancedSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//showAdvancedSearchDialog(purchaseProdSuppListPanel);
			}
		});
		btnAdvancedSearch.setBounds(900, 80, 150, 30);
		add(btnAdvancedSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(800, 131, 150, 28);
		add(txtSearch);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doSearch(txtSearch.getText());
			}
		});
		btnSearch.setBounds(950, 130, 100, 30);
		add(btnSearch);

		scrollPanePurchaseProdSupp = new JScrollPane();
		scrollPanePurchaseProdSupp.setBounds(50, 200, 1000, 300);
		add(scrollPanePurchaseProdSupp);

		purchaseProdSuppTableModel = new PurchaseProdSuppTableModel(new ArrayList<PurchaseProdSupp>());
		tblPurchaseProdSupp = new JTable(purchaseProdSuppTableModel);
		tblPurchaseProdSupp.setFocusable(false);
		tblPurchaseProdSupp.setAutoCreateRowSorter(true);
		scrollPanePurchaseProdSupp.setViewportView(tblPurchaseProdSupp);

		tblPurchaseProdSupp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 7)
						MainPanel.changePanel("module.purchaseprodsupp.ui.PurchaseProdSuppViewPanel", listOfPurchaseProdSupp.get(row));
				}
			}
		});

		try {
			listOfPurchaseProdSupp = new ArrayList<PurchaseProdSupp>();
			listOfPurchaseProdSupp = ServiceFactory.getPurchaseProductSuppBL().getAllPurchaseProdSupp(COMPLETED);
			refreshTablePurchaseProdSupp();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
			LOGGER.error(e1.getMessage());
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnCreateNew.requestFocusInWindow();
			}
		});

	}

	public void refreshTablePurchaseProdSupp() {
		try {
			tblPurchaseProdSupp.setModel(new PurchaseProdSuppTableModel(listOfPurchaseProdSupp));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfPurchaseProdSupp = new ArrayList<PurchaseProdSupp>();
			listOfPurchaseProdSupp = ServiceFactory.getPurchaseProductSuppBL().getAllPurchaseProdSuppBySimpleSearch(value, COMPLETED);
			refreshTablePurchaseProdSupp();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display advanced search dialog
	 */
	protected void showAdvancedSearchDialog(PurchaseProdSuppListPanel purchaseProdSuppListPanel) {
		
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
			return 8;
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
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			case 8:
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
				return p.getCostCenter().getCostCenter();
			case 4:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getPurchaseDate()));
			case 5:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getDeliveryDate()));
			case 6:
				return p.getStatus();
			case 7:
				return "<html><a><u>View</u></a></html>";
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
				return "Cost Center";
			case 4:
				return "Tanggal Pengajuan";
			case 5:
				return "Tanggal Pengiriman";
			case 6:
				return "Status";
			case 7:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
