package module.productsupportinggood.ui;

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

import main.component.DialogBox;
import main.panel.MainPanel;
import module.supplier.model.Supplier;

public class ProductSupportingGoodListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductSupportingGoodListPanel.class);
	
	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;
	
	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JTextField searchField;
	ProductSupportingGoodTableModel productSupportingGoodTableModel;
	
	ProductSupportingGoodListPanel productSupportingGoodListPanel;
	
	JScrollPane scrollPaneProductSupportingGood;
	
	JTable tblProductSupportingGood;
	
	List listOfProductSupportingGoods = new ArrayList<>();
	
	public ProductSupportingGoodListPanel() {
		productSupportingGoodListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Produk Barang Pendukung");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("PRODUK BARANG PENDUKUNG");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.productsupportinggood.ui.ProductSupportingGoodCreatePanel");
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
				showAdvancedSearchDialog(productSupportingGoodListPanel);
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

		scrollPaneProductSupportingGood = new JScrollPane();
		scrollPaneProductSupportingGood.setBounds(50, 200, 1000, 300);
		add(scrollPaneProductSupportingGood);

		productSupportingGoodTableModel = new ProductSupportingGoodTableModel(new ArrayList<>());
		tblProductSupportingGood = new JTable(productSupportingGoodTableModel);
		tblProductSupportingGood.setFocusable(false);
		tblProductSupportingGood.setAutoCreateRowSorter(true);
		scrollPaneProductSupportingGood.setViewportView(tblProductSupportingGood);

		//
		// List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		// sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		// sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		// sorter.setSortKeys(sortKeys);

		tblProductSupportingGood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4)
						MainPanel.changePanel("module.productsupportinggood.ui.ProductSupportingGoodViewPanel", listOfProductSupportingGoods.get(row));
				}
			}
		});

		try {
			listOfProductSupportingGoods = new ArrayList<Supplier>();
			//listOfProductSupportingGoods = ServiceFactory.getProductSupportingGoodBL().getAllProductSupportingGood();
			refreshTableProductSupportingGood();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
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
	
	public void doSearch(String value) {
		try {
			listOfProductSupportingGoods = new ArrayList<>();
			//listOfProductSupportingGoods = ServiceFactory.getProductSupportingGoodBL().getAllProductSupportingGoodBySimpleSearch(value);
			refreshTableProductSupportingGood();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	public void refreshTableProductSupportingGood() {
		try {
			tblProductSupportingGood.setModel(new ProductSupportingGoodTableModel(listOfProductSupportingGoods));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	/**
	 * Method to display add supp cp dialog
	 */
	protected void showAdvancedSearchDialog(ProductSupportingGoodListPanel productSupportingGoodListPanel) {
		
	}
	
	/**
	 * Class as TableModel for ProductSupportingGood table
	 * 
	 * @author TSI
	 */
	class ProductSupportingGoodTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("rawtypes")
		private List productSupportingGoods;

		public ProductSupportingGoodTableModel(@SuppressWarnings("rawtypes") List productSupportingGoods) {
			this.productSupportingGoods = productSupportingGoods;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return productSupportingGoods.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 4;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link Supplier}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object p = productSupportingGoods.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return null;
			case 1:
				return null;
			case 2:
				return null;
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
				return "Kode Produk";
			case 1:
				return "Nama Produk";
			case 2:
				return "Kategori Produk";
			case 3:
				return "Action";
			default:
				return "";
			}
		}
	}
	
	
}
