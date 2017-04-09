package module.invoicebalken.ui;

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

import main.component.DialogBox;
import main.panel.MainPanel;
import module.util.DateUtil;

import org.apache.log4j.Logger;

import module.invoicebalken.model.InvoiceBalken;
import controller.ServiceFactory;

public class InvoiceBalkenListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(InvoiceBalkenListPanel.class);

//	JButton btnCreateNew;
//	JButton btnExport;
//	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneInvoiceBalken;

	private InvoiceBalkenTableModel invoiceProdResultTableModel;
	public List<InvoiceBalken> listOfInvoiceBalken = new ArrayList<InvoiceBalken>();

	JTable tblInvoiceBalken;

	private InvoiceBalkenListPanel invoiceProdResultListPanel;

	private static final long serialVersionUID = 1L;
	
	public static final String COMPLETED = "COMPLETED";

	public InvoiceBalkenListPanel() {
		invoiceProdResultListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Finance > Hasil Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 600, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("List Invoice Hasil Produksi ");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

//		btnCreateNew = new JButton("Buat Baru");
//		btnCreateNew.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				MainPanel.changePanel("module.invoiceprodresult.ui.InvoiceBalkenCreatePanel");
//			}
//		});
//		btnCreateNew.setBounds(700, 80, 100, 30);
//		add(btnCreateNew);
//
//		btnExport = new JButton("Export");
//		btnExport.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//
//			}
//		});
//		btnExport.setBounds(800, 80, 100, 30);
//		add(btnExport);
//
//		btnAdvancedSearch = new JButton("Pencarian Lanjut");
//		btnAdvancedSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				//showAdvancedSearchDialog(invoiceProdResultListPanel);
//			}
//		});
//		btnAdvancedSearch.setBounds(900, 80, 150, 30);
//		add(btnAdvancedSearch);

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

		scrollPaneInvoiceBalken = new JScrollPane();
		scrollPaneInvoiceBalken.setBounds(50, 200, 1000, 300);
		add(scrollPaneInvoiceBalken);

		invoiceProdResultTableModel = new InvoiceBalkenTableModel(new ArrayList<InvoiceBalken>());
		tblInvoiceBalken = new JTable(invoiceProdResultTableModel);
		tblInvoiceBalken.setFocusable(false);
		tblInvoiceBalken.setAutoCreateRowSorter(true);
		scrollPaneInvoiceBalken.setViewportView(tblInvoiceBalken);

		tblInvoiceBalken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 6)
						MainPanel.changePanel("module.invoicebalken.ui.InvoiceBalkenViewPanel", listOfInvoiceBalken.get(row));
				}
			}
		});

		try {
			listOfInvoiceBalken = new ArrayList<InvoiceBalken>();
			listOfInvoiceBalken = ServiceFactory.getInvoiceBalkenBL().getAllInvoiceBalken();
			refreshTableInvoiceBalken();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
			LOGGER.error(e1.getMessage());
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnSearch.requestFocusInWindow();
			}
		});

	}

	public void refreshTableInvoiceBalken() {
		try {
			tblInvoiceBalken.setModel(new InvoiceBalkenTableModel(listOfInvoiceBalken));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfInvoiceBalken = new ArrayList<InvoiceBalken>();
			listOfInvoiceBalken = ServiceFactory.getInvoiceBalkenBL().getAllInvoiceBalkenBySimpleSearch(value);
			refreshTableInvoiceBalken();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display advanced search dialog
	 */
	protected void showAdvancedSearchDialog(InvoiceBalkenListPanel invoiceProdResultListPanel) {
		
	}

	/**
	 * Class as TableModel for InvoiceBalken table
	 * 
	 * @author TSI
	 *
	 */
	class InvoiceBalkenTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<InvoiceBalken> listOfInvoiceBalken;

		public InvoiceBalkenTableModel(List<InvoiceBalken> listOfInvoiceBalken) {
			this.listOfInvoiceBalken = listOfInvoiceBalken;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfInvoiceBalken.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 7;
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
		 * @return ({@link InvoiceBalkenAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			InvoiceBalken p = listOfInvoiceBalken.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getReceivedCode();
			case 1:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getReceivedDate()));
			case 2:
				return p.getSuppName();
			case 3:
				return p.getDeliveryNote();
			case 4:
				return p.getPaymentStatus();
			case 5:
				return p.getStatus();
			case 6:
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
				return "Kode Penerimaan";
			case 1:
				return "Tanggal Penerimaan";
			case 2:
				return "Supplier";
			case 3:
				return "No Dokumen";
			case 4:
				return "Status Pembayaran";
			case 5:
				return "Status Tutup Harian";
			case 6:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
