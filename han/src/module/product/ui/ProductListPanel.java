package module.product.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import controller.DaoFactory;
import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.product.model.Product;
import module.supplier.model.Supplier;

public class ProductListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel titleLbl;
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
		
		titleLbl = new JLabel("PRODUK");
		titleLbl.setFont(new Font("", Font.BOLD, 24));
		titleLbl.setBounds(20, 20, 200, 50);

		createNewBtn = new JButton("Buat Baru");
		createNewBtn.setBounds(760, 30, 120, 50);
		createNewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.product.ui.CreateProductPanel");
			}
		});

		exportBtn = new JButton("Export");
		exportBtn.setBounds(890, 30, 120, 50);

		advanceSearchBtn = new JButton("<html><p>Pencarian Lanjut</p></html>");
		advanceSearchBtn.setBounds(1020, 30, 120, 50);
		advanceSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//MainPanel.changePanel("module.product.ui.EmployeeSearchPanel");

			}
		});

		searchBtn = new JButton("Cari");
		searchBtn.setBounds(1060, 100, 80, 25);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				doSearch(searchField.getText());
			}
		});

		searchField = new JTextField();
		searchField.setBounds(900, 100, 150, 25);

		try{
			products = ServiceFactory.getProductBL().getAll();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		
		System.out.println(products.size());
		productTableModel = new ProductTableModel(products);
		productTable = new JTable(productTableModel);
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		productTable.getTableHeader().setReorderingAllowed(false);
		productTable.getTableHeader().setResizingAllowed(false);
		productTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		productTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		productTable.getColumnModel().getColumn(2).setPreferredWidth(170);
		productTable.getColumnModel().getColumn(3).setPreferredWidth(170);
		productTable.getColumnModel().getColumn(4).setPreferredWidth(130);
		productTable.getColumnModel().getColumn(5).setPreferredWidth(140);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		productTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		productTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					Product product;
					// do some action if appropriate column
					if(column == 5){
						product = new Product();
						
						product.setProductCode(productTable.getValueAt(row, 1).toString());

						MainPanel.changePanel("module.product.ui.ProductViewPanel", product);
					}
				}
			}
		});

		scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(20, 200, 1130, 265);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(titleLbl);
		add(createNewBtn);
		add(exportBtn);
		add(advanceSearchBtn);
		add(searchField);
		add(searchBtn);
		add(scrollPane);
	}
	
	public void refreshTable() {
		try {
			productTable.setModel(new ProductTableModel(products));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}
	
	public void doSearch(String value) {
		try {
			products = new ArrayList<Product>();
			products = ServiceFactory.getProductBL().getSearchProduct(value);
			refreshTable();
			productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			productTable.getTableHeader().setReorderingAllowed(false);
			productTable.getTableHeader().setResizingAllowed(false);
			productTable.getColumnModel().getColumn(0).setPreferredWidth(30);
			productTable.getColumnModel().getColumn(1).setPreferredWidth(120);
			productTable.getColumnModel().getColumn(2).setPreferredWidth(170);
			productTable.getColumnModel().getColumn(3).setPreferredWidth(170);
			productTable.getColumnModel().getColumn(4).setPreferredWidth(130);
			productTable.getColumnModel().getColumn(5).setPreferredWidth(140);
		} catch (SQLException e1) {
			e1.printStackTrace();
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
			return 6;
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
				return seq = rowIndex+1;
			case 1:
				return p.getProductCode();
			case 2:
				return p.getProductName();
			case 3:
				return p.getProductCat();
			case 4: 
				return p.getProductStat();
			case 5:
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
				return "No";
			case 1:
				return "Kode Produk";
			case 2:
				return "Nama Produk";
			case 3:
				return "Kategori Produk";
			case 4:
				return "Status Produk";
			case 5:
				return "Action";
			default:
				return "";
			}
		}
		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}

	}

}
