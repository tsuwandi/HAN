package module.purchaseprodresult.ui;

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
import module.purchaseprodresult.model.PurchaseProdResult;

public class PurchaseProdResultListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(PurchaseProdResultListPanel.class);

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePurchaseProdResult;

	private PurchaseProdResultTableModel purchaseProdResultTableModel;
	public List<PurchaseProdResult> listOfPurchaseProdResult = new ArrayList<PurchaseProdResult>();

	JTable tblPurchaseProdResult;

	private PurchaseProdResultListPanel purchaseProdResultListPanel;

	private static final long serialVersionUID = 1L;
	
	public static final String COMPLETED = "COMPLETED";

	public PurchaseProdResultListPanel() {
		purchaseProdResultListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Produksi > Input Hasil Produksi > Pembelian");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("Input Hasil Produksi Pembelian ");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.purchaseprodresult.ui.PurchaseProdResultCreatePanel");
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
				//showAdvancedSearchDialog(purchaseProdResultListPanel);
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

		scrollPanePurchaseProdResult = new JScrollPane();
		scrollPanePurchaseProdResult.setBounds(50, 200, 1000, 300);
		add(scrollPanePurchaseProdResult);

		purchaseProdResultTableModel = new PurchaseProdResultTableModel(new ArrayList<PurchaseProdResult>());
		tblPurchaseProdResult = new JTable(purchaseProdResultTableModel);
		tblPurchaseProdResult.setFocusable(false);
		tblPurchaseProdResult.setAutoCreateRowSorter(true);
		scrollPanePurchaseProdResult.setViewportView(tblPurchaseProdResult);

		tblPurchaseProdResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4)
						MainPanel.changePanel("module.purchaseprodresult.ui.PurchaseProdResultViewPanel", listOfPurchaseProdResult.get(row));
				}
			}
		});

		try {
			listOfPurchaseProdResult = new ArrayList<PurchaseProdResult>();
			listOfPurchaseProdResult = ServiceFactory.getPurchaseProductResultBL().getAllPurchaseProdResult(COMPLETED);
			refreshTablePurchaseProdResult();
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

	public void refreshTablePurchaseProdResult() {
		try {
			tblPurchaseProdResult.setModel(new PurchaseProdResultTableModel(listOfPurchaseProdResult));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfPurchaseProdResult = new ArrayList<PurchaseProdResult>();
			listOfPurchaseProdResult = ServiceFactory.getPurchaseProductResultBL().getAllPurchaseProdResultBySimpleSearch(value, COMPLETED);
			refreshTablePurchaseProdResult();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display advanced search dialog
	 */
	protected void showAdvancedSearchDialog(PurchaseProdResultListPanel purchaseProdResultListPanel) {
		
	}

	/**
	 * Class as TableModel for PurchaseProdResult table
	 * 
	 * @author TSI
	 *
	 */
	class PurchaseProdResultTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<PurchaseProdResult> listOfPurchaseProdResult;

		public PurchaseProdResultTableModel(List<PurchaseProdResult> listOfPurchaseProdResult) {
			this.listOfPurchaseProdResult = listOfPurchaseProdResult;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPurchaseProdResult.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 5;
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
		 * @return ({@link PurchaseProdResultAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PurchaseProdResult p = listOfPurchaseProdResult.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getPprCode();
			case 1:
				return p.getSuppCode();
			case 2:
				return p.getSupplier().getSuppName();
			case 3:
				return p.getStatus();
			case 4:
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
				return "Status";
			case 4:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
