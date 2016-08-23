package module.product.ui;

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
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.product.model.Product;
import module.supplier.model.Supplier;

public class ProductListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductListPanel.class);
	
	private JButton createNewBtn;
	private JButton exportBtn;
	private JButton advanceSearchBtn;
	private JButton searchBtn;

	private JTextField searchField;
	private JTable productTable;
	
	private ProductTableModel productTableModel;
	private JScrollPane scrollPane;
	public List<Product> products = null;
	
	public ProductListPanel() {
		setLayout(null);
		
		if(products!=null){
			refreshTable();
		}
		
		JLabel lblBreadcrumb = new JLabel("ERP > Produk");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("PRODUK");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		createNewBtn = new JButton("Buat Baru");
		createNewBtn.setBounds(700, 80, 100, 30);
		createNewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.product.ui.ProductCreatePanel");
			}
		});

		exportBtn = new JButton("Export");
		exportBtn.setBounds(800, 80, 100, 30);

		advanceSearchBtn = new JButton("Pencarian Lanjut");
		advanceSearchBtn.setBounds(900, 80, 150, 30);
		advanceSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//MainPanel.changePanel("module.product.ui.EmployeeSearchPanel");
			}
		});

		searchBtn = new JButton("Cari");
		searchBtn.setBounds(950, 130, 100, 30);
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doSearch(searchField.getText());
			}
		});

		searchField = new JTextField();
		searchField.setBounds(800, 131, 150, 28);

		try{
			products = ServiceFactory.getProductBL().getAll();
		}catch(SQLException e1){
			LOGGER.error(e1.getMessage());
		}
		
		productTableModel = new ProductTableModel(products);
		productTable = new JTable(productTableModel);
		productTable.setFocusable(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		productTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					Product product;
					if(column == 3){
						product = new Product();
						product.setProductCode(productTable.getValueAt(row, 0).toString());
						MainPanel.changePanel("module.product.ui.ProductViewPanel", product);
					}
				}
			}
		});

		scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(50, 200, 1000, 300);

		add(createNewBtn);
		add(exportBtn);
		add(advanceSearchBtn);
		add(searchField);
		add(searchBtn);
		add(scrollPane);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createNewBtn.requestFocusInWindow();
			}
		});
	}
	
	public void refreshTable() {
		try {
			productTable.setModel(new ProductTableModel(products));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	public void doSearch(String value) {
		try {
			products = new ArrayList<Product>();
			products = ServiceFactory.getProductBL().getSearchProduct(value);
			refreshTable();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	class ProductTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq=0;

		public ProductTableModel(List<Product> products) {
			this.products = products;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return products.size();
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
			Product p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProductCode();
			case 1:
				return p.getProductName();
			case 2:
				return p.getProductCatName();
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
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

}
