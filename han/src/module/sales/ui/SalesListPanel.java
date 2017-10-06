package module.sales.ui;

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
import module.sales.model.Sales;

public class SalesListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(SalesListPanel.class);

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneSales;

	private SalesTableModel salesTableModel;
	public List<Sales> listOfSales = new ArrayList<Sales>();

	JTable tblSales;

	private SalesListPanel salesListPanel;

	private static final long serialVersionUID = 1L;

	public SalesListPanel() {
		salesListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Penjualan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("Sales");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.sales.ui.SalesCreatePanel");
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
				showAdvancedSearchDialog(salesListPanel);
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

		scrollPaneSales = new JScrollPane();
		scrollPaneSales.setBounds(50, 200, 1000, 300);
		add(scrollPaneSales);

		salesTableModel = new SalesTableModel(new ArrayList<Sales>());
		tblSales = new JTable(salesTableModel);
		tblSales.setFocusable(false);
		tblSales.setAutoCreateRowSorter(true);
		scrollPaneSales.setViewportView(tblSales);

		//
		// List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		// sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		// sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		// sorter.setSortKeys(sortKeys);

		tblSales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 3)
						MainPanel.changePanel("module.sales.ui.SalesViewPanel", listOfSales.get(row));
				}
			}
		});

		try {
			listOfSales = new ArrayList<Sales>();
			listOfSales = ServiceFactory.getSalesBL().getAllSales();
			refreshTableSales();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnCreateNew.requestFocusInWindow();
			}
		});

	}

	public void refreshTableSales() {
		try {
			tblSales.setModel(new SalesTableModel(listOfSales));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfSales = new ArrayList<Sales>();
			listOfSales = ServiceFactory.getSalesBL().getAllSalesBySimpleSearch(value);
			refreshTableSales();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add sales cp dialog
	 */
	protected void showAdvancedSearchDialog(SalesListPanel salesListPanel) {
		SalesAdvSearchDialog salesAdvSearchDialog = new SalesAdvSearchDialog(salesListPanel);
		salesAdvSearchDialog.setTitle("Pencarian Lanjut");
		salesAdvSearchDialog.setLocationRelativeTo(null);
		salesAdvSearchDialog.setVisible(true);
	}

	/**
	 * Class as TableModel for Sales table
	 * 
	 * @author TLO
	 *
	 */
	class SalesTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<Sales> listOfSales;

		public SalesTableModel(List<Sales> listOfSales) {
			this.listOfSales = listOfSales;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSales.size();
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
		 * @return ({@link Sales}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Sales s = listOfSales.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return s.getSoNo();
			case 1:
				return s.getSoDate();
			case 2:
				return s.getCustomer().getCustName();
			case 3:
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
				return "Nomor SO";
			case 1:
				return "Tanggal SO";
			case 2:
				return "Nama Customer";
			case 3:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
