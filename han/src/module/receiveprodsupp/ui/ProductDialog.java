package module.receiveprodsupp.ui;

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
import module.product.model.Product;
import module.productsupportinggood.model.ProductSupp;
import module.supplier.model.Supplier;
import module.util.JTextFieldLimit;

public class ProductDialog extends JDialog {

private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(ProductDialog.class);

	JPanel panel;

	JLabel lblProductName;
	JLabel lblProductCode;

	JTextField txtProductName;
	JTextField txtProductCode;

	JButton btnSearch;
	JButton btnSave;
	JButton btnClose;
	
	List<ProductSupp> products = new ArrayList<ProductSupp>();
	
	JScrollPane scrollPaneProduct;

	private ProductTableModel productTableModel;
	
	JTable tblProduct;

	private ReceiveProdSuppCreatePanel rpsCreatePanel;
	private ReceiveProdSuppEditPanel rpsEditPanel;

	private ProductSupp addProduct = null;

	public ProductDialog(ReceiveProdSuppCreatePanel rpsCreatePanel) {
		this.rpsCreatePanel = rpsCreatePanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		getContentPane().setLayout(null);

		load();
	}
	
	public ProductDialog(ReceiveProdSuppEditPanel ppsEditPanel) {
		this.rpsEditPanel = ppsEditPanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		getContentPane().setLayout(null);

		load();
	}
	
	public void load() {
		lblProductCode = new JLabel("Kode Produk");
		lblProductCode.setBounds(25, 15, 150, 25);
		getContentPane().add(lblProductCode);

		txtProductCode = new JTextField();
		txtProductCode.setBounds(120, 15, 150, 25);
		txtProductCode.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtProductCode);
		
		lblProductName = new JLabel("Nama Produk");
		lblProductName.setBounds(25, 45, 150, 25);
		getContentPane().add(lblProductName);

		txtProductName = new JTextField();
		txtProductName.setBounds(120, 45, 150, 25);
		txtProductName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtProductName);


		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String purchaseProductSuppCode = "";
				if(rpsCreatePanel != null) {
					purchaseProductSuppCode = rpsCreatePanel.txtPurchaseProductSuppCode.getText();
				}
				
				if(rpsEditPanel != null) {
					purchaseProductSuppCode = rpsEditPanel.txtPurchaseProductSuppCode.getText();
				}
				doAdvancedSearch(purchaseProductSuppCode);
			}
		});
		btnSearch.setBounds(280, 45, 100, 25);
		getContentPane().add(btnSearch);
		
		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				doSave();
			}
		});
		btnSave.setBounds(349, 245, 100, 25);
		getContentPane().add(btnSave);
		
		btnClose = new JButton("Tutup");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		btnClose.setBounds(459, 245, 100, 25);
		getContentPane().add(btnClose);
		
		scrollPaneProduct = new JScrollPane();
		scrollPaneProduct.setBounds(25, 85, 535, 150);
		add(scrollPaneProduct);

		productTableModel = new ProductTableModel(new ArrayList<ProductSupp>());
		tblProduct = new JTable(productTableModel);
		tblProduct.setFocusable(false);
		tblProduct.setAutoCreateRowSorter(true);
		scrollPaneProduct.setViewportView(tblProduct);

		tblProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				addProduct = new ProductSupp();
				addProduct = products.get(row);
			}
		});
		
		String purchaseProductSuppCode = "";
		if(rpsCreatePanel != null) {
			purchaseProductSuppCode = rpsCreatePanel.txtPurchaseProductSuppCode.getText();
		}
		
		if(rpsEditPanel != null) {
			purchaseProductSuppCode = rpsEditPanel.txtPurchaseProductSuppCode.getText();
		}
		
		doAdvancedSearch(purchaseProductSuppCode);
	}

	public void doAdvancedSearch(String purchaseProductSuppCode) {
		try {
			ProductSupp product = new ProductSupp();
			product.setProductCode(txtProductCode.getText());
			product.setProductName(txtProductName.getText());
			
			products = ServiceFactory.getReceiveProductSuppBL().getAllProductByAdvancedSearchAndPpsCode(product, purchaseProductSuppCode);
			refreshTableProduct(products);
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}
	
	public void doSave() {
		if(addProduct != null) {
			if(rpsCreatePanel != null) {
				rpsCreatePanel.txtProductCategory.setText(addProduct.getProductCategory().getProductCategory());
				rpsCreatePanel.setProductCategoryId(addProduct.getProductCategoryId());
				rpsCreatePanel.txtProduct.setText(addProduct.getProductName());
				rpsCreatePanel.setProductCode(addProduct.getProductCode());
				rpsCreatePanel.txtUOM.setText(addProduct.getProductUom().getUom());
				rpsCreatePanel.setProductUomId(Integer.valueOf(addProduct.getProductUomId()));
				rpsCreatePanel.txtUnitPrice.setText(String.format("%.2f", addProduct.getUnitPrice()));
				rpsCreatePanel.setQty(addProduct.getQty());
			}
			if(rpsEditPanel != null) {
				rpsEditPanel.txtProductCategory.setText(addProduct.getProductCategory().getProductCategory());
				rpsEditPanel.setProductCategoryId(addProduct.getProductCategoryId());
				rpsEditPanel.txtProduct.setText(addProduct.getProductName());
				rpsEditPanel.setProductCode(addProduct.getProductCode());
				rpsEditPanel.txtUOM.setText(addProduct.getProductUom().getUom());
				rpsEditPanel.setProductUomId(Integer.valueOf(addProduct.getProductUomId()));
				rpsEditPanel.txtUnitPrice.setText(String.format("%.2f", addProduct.getUnitPrice()));
				rpsEditPanel.setQty(addProduct.getQty());
			}
		}
		closeDialog();
	}

	protected void closeDialog() {
		dispose();
	}
	
	public void refreshTableProduct(List<ProductSupp> products) {
		try {
			tblProduct.setModel(new ProductTableModel(products));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	/**
	 * Class as TableModel for Product table
	 * 
	 * @author TSI
	 *
	 */
	class ProductTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<ProductSupp> products;
		int seq=0;

		public ProductTableModel(List<ProductSupp> products) {
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
			return 3;
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
			ProductSupp p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProductCode();
			case 1:
				return p.getProductName();
			case 2:
				return p.getProductCategory().getProductCategory();
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
