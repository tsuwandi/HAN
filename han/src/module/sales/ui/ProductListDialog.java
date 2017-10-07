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
import module.pembelian.model.Product;
import module.sales.model.SalesDetail;
import module.sn.uom.model.Uom;

public class ProductListDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductListDialog.class);

	JPanel panel;

	JButton btnSearch;

	JTextField txtSearch;

	JScrollPane scrollPaneProduct;

	private ProductTableModel productTableModel;

	public List<Product> listOfProduct = new ArrayList<Product>();

	JTable tblProduct;

	private SalesDetailDialog salesDetailDialog;

	public ProductListDialog(SalesDetailDialog salesDetailDialog) {
		this.salesDetailDialog = salesDetailDialog;
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

		scrollPaneProduct = new JScrollPane();
		scrollPaneProduct.setBounds(25, 45, 480, 300);
		add(scrollPaneProduct);

		productTableModel = new ProductTableModel(new ArrayList<Product>());
		tblProduct = new JTable(productTableModel);
		tblProduct.setFocusable(false);
		tblProduct.setAutoCreateRowSorter(true);
		scrollPaneProduct.setViewportView(tblProduct);

		tblProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 3) {
						try {
							doInsert(listOfProduct.get(row));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		try {
			listOfProduct = new ArrayList<Product>();
			listOfProduct = ServiceFactory.getSalesBL().getAllProduct();
			refreshTableProduct();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	protected void doInsert(Product product) throws SQLException {
		
		Uom uom = new Uom();

		salesDetailDialog.txtProductCode.setText(product.getProductCode());
		salesDetailDialog.txtProductName.setText(product.getProductName());
		
		salesDetailDialog.salesDetail.setProductId(product.getId());
		salesDetailDialog.salesDetail.getProduct().setLength(product.getLength());
		salesDetailDialog.salesDetail.getProduct().setWidth(product.getWidth());
		salesDetailDialog.salesDetail.getProduct().setThickness(product.getThickness());
		
		uom = ServiceFactory.getSalesBL().getUomByProductUomId(product.getProductUomId());
		
		salesDetailDialog.txtUom.setText(uom.getUom());

		DialogBox.showInsert();

		dispose();
	}

	public void refreshTableProduct() {
		try {
			tblProduct.setModel(new ProductTableModel(listOfProduct));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfProduct = new ArrayList<Product>();
			listOfProduct = ServiceFactory.getSalesBL().getSearchProduct(value);
			refreshTableProduct();
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
	class ProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<Product> listOfProduct;

		public ProductTableModel(List<Product> listOfProduct) {
			this.listOfProduct = listOfProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfProduct.size();
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
			Product p = listOfProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProductCode();
			case 1:
				return p.getProductName();
			case 2:
				return p.getDescription();
			case 3:
				return "<html><a><u>Pick</u></a></html>";
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
				return "Kode Produk";
			case 1:
				return "Nama Produk";
			case 2:
				return "Deskripsi";
			case 3:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
