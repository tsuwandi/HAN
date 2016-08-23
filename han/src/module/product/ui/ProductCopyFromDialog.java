package module.product.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.product.model.Product;
import module.supplier.model.Supplier;

public class ProductCopyFromDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ProductCopyFromDialog.class);
	JScrollPane scrollPaneProduct;
	JTable tblProduct;
	JTextField txtSearch;
	JButton btnSearch;
	JButton btnCopyTo;
	JLabel lblError;

	ProductTableModel productTableModel;

	List<Product> products;

	ProductCreatePanel productCreatePanel;

	public ProductCopyFromDialog(ProductCreatePanel productCreatePanel) {
		this.productCreatePanel = productCreatePanel;

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		getContentPane().setLayout(null);

		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 10, 225, 25);
		getContentPane().add(lblError);

		txtSearch = new JTextField();
		txtSearch.setBounds(320, 10, 150, 25);
		getContentPane().add(txtSearch);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearchProduct(txtSearch.getText());
			}
		});
		btnSearch.setBounds(480, 10, 95, 25);
		getContentPane().add(btnSearch);

		scrollPaneProduct = new JScrollPane();
		scrollPaneProduct.setBounds(10, 50, 564, 190);
		getContentPane().add(scrollPaneProduct);

		try {
			products = new ArrayList<Product>();
			products = ServiceFactory.getProductBL().getAll();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showErrorException();
		}

		productTableModel = new ProductTableModel(products);
		tblProduct = new JTable(productTableModel);
		tblProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblProduct.setFocusable(false);
		scrollPaneProduct.setViewportView(tblProduct);

		btnCopyTo = new JButton("Copy");
		btnCopyTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productCreatePanel.loadData(getSelectedProduct().getProductCode());
				dispose();
			}
		});
		btnCopyTo.setBounds(480, 250, 95, 25);
		getContentPane().add(btnCopyTo);
	}

	/**
	 * Method to get Product Object from selected table
	 * 
	 * @return {@link Product}
	 */
	private Product getSelectedProduct() {
		if (tblProduct != null && tblProduct.getSelectedRow() >= 0) {
			return (Product) products.get(tblProduct.getSelectedRow());
		} else {
			JOptionPane.showMessageDialog(this, "Silahkan pilih data terlebih dahulu", "Informasi",
					JOptionPane.PLAIN_MESSAGE);
			return null;
		}
	}

	public void refreshTable() {
		try {
			tblProduct.setModel(new ProductTableModel(products));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearchProduct(String value) {
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

		int seq = 0;

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
			Product p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProductCode();
			case 1:
				return p.getProductName();
			case 2:
				return p.getProductCatName();
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
