package module.purchaseproductresult.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.product.model.Product;
import module.purchaseproductresult.model.PurchaseProductResult;
import module.purchaseproductresult.model.PurchaseProductResultProduct;
import module.sn.currency.model.Currency;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class PurchaseProductResultCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
	}
	
	private static final Logger LOGGER = Logger.getLogger(PurchaseProductResultCreatePanel.class);
	
	JLabel lblPurchaseProductResultCode;
	JLabel lblSupplier;
	JLabel lblPurchaseNote;
	JLabel lblPurchaseDate;
	JLabel lblDueDate;
	JLabel lblCurrency;
	JLabel lblExchangeRate;
	JLabel lblProduct;
	JLabel lblQty;
	JLabel lblUnitPrice;
	JLabel lblSubTotal;
	JLabel lblTotal;
	JLabel lblDiscount;
	JLabel lblTax;
	JLabel lblGrandTotal;
	
	JButton btnInsert;
	JButton btnCancel;
	JButton btnSave;
	
	JPanel panel;
	JScrollPane scrollPane;
	
	JTextField txtPurchaseProductResultCode;
	ComboBox<Supplier> cbSupplier;
	JTextField txtPurchaseNote;
	JDateChooser dcPurchaseDate;
	JDateChooser dcDueDate;
	ComboBox<Currency> cbCurrency;
	JTextField txtExchangeRate;
	
	ComboBox<Product> cbProduct;
	JTextField txtQty;
	JTextField txtUnitPrice;
	JTextField txtSubTotal;
	JTextField txtTotal;
	JTextField txtDiscount;
	JTextField txtTax;
	JTextField txtGrandTotal;
	
	JLabel lblErrorSupplier;
	JLabel lblErrorPurchaseDate;
	JLabel lblErrorDueDate;
	JLabel lblErrorCurrency;
	JLabel lblErrorProduct;
	JLabel lblErrorQty;
	JLabel lblErrorUnitPrice;
	
	PurchaseProductResult purchaseProductResult;
	DocumentFilter filter = new UppercaseDocumentFilter();
	
	List<Supplier> listOfSupplier = null;
	List<Currency> listOfCurrency = null;
	List<Product> listOfProduct = null;
	List<PurchaseProductResultProduct> listOfPPRProduct = null;
	
	JLabel lblBreadcrumb;
	JLabel lblHeader;
	
	JScrollPane scrollPanePPRProductPallet;
	JTable tblPPRPallet;
	
	PPRProductTableModel pprTableTableModel = null;
	
	public PurchaseProductResultCreatePanel() {
		purchaseProductResult = new PurchaseProductResult();
		
		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(
				new Dimension(800, 800));
		panel.setLayout(null);
		
		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi > Pembelian");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 414, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblPurchaseProductResultCode = new JLabel("<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductResultCode.setBounds(50, 80, 150, 25);
		panel.add(lblPurchaseProductResultCode);

		txtPurchaseProductResultCode = new JTextField();
		txtPurchaseProductResultCode.setBounds(220, 80, 150, 25);
		txtPurchaseProductResultCode.setDocument(new JTextFieldLimit(9));
		txtPurchaseProductResultCode.setEnabled(false);
		((AbstractDocument) txtPurchaseProductResultCode.getDocument()).setDocumentFilter(filter);
		panel.add(txtPurchaseProductResultCode);

		lblSupplier = new JLabel("<html>Supplier <font color=\"red\">*</font></html>");
		lblSupplier.setBounds(50, 110, 150, 25);
		panel.add(lblSupplier);
		
		listOfSupplier = new ArrayList<Supplier>();
		listOfSupplier.add(0, new Supplier("-- Pilih Supplier --"));
		
		cbSupplier = new ComboBox<Supplier>();
		cbSupplier.setList(listOfSupplier);
		cbSupplier.setBounds(220, 110, 150, 25);
		panel.add(cbSupplier);
		
		lblErrorSupplier = new JLabel();
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(425, 110, 225, 25);
		panel.add(lblErrorSupplier);
		
		lblPurchaseNote = new JLabel("Nota");
		lblPurchaseNote.setBounds(50, 140, 150, 25);
		panel.add(lblPurchaseNote);
		
		txtPurchaseNote = new JTextField();
		txtPurchaseNote.setBounds(220, 140, 150, 25);
		txtPurchaseNote.setDocument(new JTextFieldLimit(60));
		((AbstractDocument) txtPurchaseNote.getDocument()).setDocumentFilter(filter);
		panel.add(txtPurchaseNote);
		
		lblPurchaseDate = new JLabel("<html>Tanggal Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseDate.setBounds(50, 170, 150, 25);
		panel.add(lblPurchaseDate);
		
		dcPurchaseDate = new JDateChooser();
		dcPurchaseDate.setBounds(220, 170, 150, 25);
		dcPurchaseDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcPurchaseDate);
		
		lblErrorPurchaseDate = new JLabel();
		lblErrorPurchaseDate.setForeground(Color.RED);
		lblErrorPurchaseDate.setBounds(425, 170, 225, 25);
		panel.add(lblErrorPurchaseDate);
		
		lblDueDate = new JLabel("<html>Tanggal Jatuh Tempo <font color=\"red\">*</font></html>");
		lblDueDate.setBounds(50, 200, 150, 25);
		panel.add(lblDueDate);
		
		dcDueDate = new JDateChooser();
		dcDueDate.setBounds(220, 200, 150, 25);
		dcDueDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcDueDate);
		
		lblErrorDueDate = new JLabel();
		lblErrorDueDate.setForeground(Color.RED);
		lblErrorDueDate.setBounds(425, 200, 225, 25);
		panel.add(lblErrorDueDate);
		
		lblCurrency = new JLabel("<html>Kurs <font color=\"red\">*</font></html>");
		lblCurrency.setBounds(50, 230, 150, 25);
		panel.add(lblCurrency);
		
		listOfCurrency = new ArrayList<Currency>();
		listOfCurrency.add(0, new Currency("-- Pilih Kurs --"));
		
		cbCurrency = new ComboBox<Currency>();
		cbCurrency.setList(listOfCurrency);
		cbCurrency.setBounds(220, 230, 150, 25);
		panel.add(cbCurrency);
		
		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 230, 225, 25);
		panel.add(lblErrorCurrency);
		
		lblExchangeRate = new JLabel("Kurs Rate");
		lblExchangeRate.setBounds(50, 260, 150, 25);
		panel.add(lblExchangeRate);

		txtExchangeRate = new NumberField(10);
		txtExchangeRate.setBounds(220, 260, 150, 25);
		panel.add(txtExchangeRate);
		
		JLabel lblDiv = new JLabel("--------------------------------------------------------------------------------");
		lblDiv.setBounds(50, 290, 448, 25);
		panel.add(lblDiv);
		
		lblProduct = new JLabel("<html>Produk <font color=\"red\">*</font></html>");
		lblProduct.setBounds(50, 320, 150, 25);
		panel.add(lblProduct);
		
		listOfProduct = new ArrayList<Product>();
		listOfProduct.add(0, new Product("-- Pilih Produk --"));
		
		cbProduct = new ComboBox<Product>();
		cbProduct.setList(listOfProduct);
		cbProduct.setBounds(220, 320, 150, 25);
		panel.add(cbProduct);
		
		lblErrorProduct = new JLabel();
		lblErrorProduct.setForeground(Color.RED);
		lblErrorProduct.setBounds(425, 320, 225, 25);
		panel.add(lblErrorProduct);
		
		lblQty = new JLabel("<html>Qty <font color=\"red\">*</font></html>");
		lblQty.setBounds(50, 350, 150, 25);
		panel.add(lblQty);
		
		txtQty = new NumberField(5);
		txtQty.setBounds(220, 350, 150, 25);
		panel.add(txtQty);
		
		lblErrorQty = new JLabel();
		lblErrorQty.setForeground(Color.RED);
		lblErrorQty.setBounds(425, 350, 225, 25);
		panel.add(lblErrorQty);
		
		lblUnitPrice = new JLabel("<html>Harga Satuan <font color=\"red\">*</font></html>");
		lblUnitPrice.setBounds(50, 380, 150, 25);
		panel.add(lblUnitPrice);
		
		txtUnitPrice = new NumberField(10);
		txtUnitPrice.setBounds(220, 380, 150, 25);
		panel.add(txtUnitPrice);
		
		lblErrorUnitPrice = new JLabel();
		lblErrorUnitPrice.setForeground(Color.RED);
		lblErrorUnitPrice.setBounds(425, 380, 225, 25);
		panel.add(lblErrorUnitPrice);
		
		lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setBounds(50, 410, 150, 25);
		panel.add(lblSubTotal);
		
		txtSubTotal = new NumberField(10);
		txtSubTotal.setEnabled(false);
		txtSubTotal.setBounds(220, 410, 150, 25);
		panel.add(txtSubTotal);
		
		btnInsert = new JButton("Tambah");
		btnInsert.setBounds(220, 440, 100, 25);
		btnInsert.setFocusable(false);
		panel.add(btnInsert);
		
		scrollPanePPRProductPallet = new JScrollPane();
		scrollPanePPRProductPallet.setBounds(50, 480, 975, 150);
		panel.add(scrollPanePPRProductPallet);
	
		listOfPPRProduct = new ArrayList<PurchaseProductResultProduct>();
		pprTableTableModel = new PPRProductTableModel(listOfPPRProduct);
		tblPPRPallet = new JTable(pprTableTableModel);
		tblPPRPallet.setFocusable(false);
		tblPPRPallet.setBorder(new EmptyBorder(5, 5, 5, 5));
//		tblPPRPallet.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					JTable target = (JTable) e.getSource();
//					int row = target.getSelectedRow();
//					int column = target.getSelectedColumn();
//
//					if (column == 4) {
//						// TO DO
//					}
//				}
//			}
//		});
		scrollPanePPRProductPallet.setViewportView(tblPPRPallet);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(640, 640, 150, 25);
		panel.add(lblTotal);
		
		txtTotal = new NumberField(10);
		txtTotal.setEnabled(false);
		txtTotal.setBounds(700, 640, 150, 25);
		panel.add(txtTotal);
		
		lblDiscount = new JLabel("Diskon");
		lblDiscount.setBounds(640, 670, 150, 25);
		panel.add(lblDiscount);
		
		txtDiscount = new NumberField(10);
		txtDiscount.setBounds(700, 670, 150, 25);
		panel.add(txtDiscount);
		
		lblTax = new JLabel("Pajak");
		lblTax.setBounds(640, 700, 150, 25);
		panel.add(lblTax);
		
		txtTax = new NumberField(10);
		txtTax.setBounds(700, 700, 150, 25);
		panel.add(txtTax);
		
		lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(640, 730, 150, 25);
		panel.add(lblGrandTotal);
		
		txtGrandTotal = new NumberField(10);
		txtGrandTotal.setEnabled(false);
		txtGrandTotal.setBounds(700, 730, 150, 25);
		panel.add(txtGrandTotal);
		
		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				if (doValidate() == false) {
//					return;
//				}
//				int response = DialogBox.showInsertChoice();
//				if (response == JOptionPane.YES_OPTION) {
//					doSave();
//				}
			}
		});
		btnSave.setBounds(925, 760, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				int response = DialogBox.showCloseChoice();
//				if (response == JOptionPane.YES_OPTION) {
//					MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
//				}
			}
		});
		btnCancel.setBounds(50, 760, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		add(scrollPane);
		
	}
	
	/**
	 * Class as TableModel for PPR Product table
	 * 
	 * @author TSI
	 *
	 */
	class PPRProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PurchaseProductResultProduct> listOfPPRProduct;

		public PPRProductTableModel(List<PurchaseProductResultProduct> listOfPPRProduct) {
			this.listOfPPRProduct = listOfPPRProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPPRProduct.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 5;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PurchaseProductResultProduct p = listOfPPRProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProduct().getProductName();
			case 1:
				return p.getQty();
			case 2:
				return p.getUnitPrice();
			case 3:
				return p.getSubTotal();
			case 4:
				return "<html><u>Hapus</u></html>";
			default:
				return "";
			}
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
				return Double.class;
			case 2:
				return Double.class;
			case 3:
				return Integer.class;
			case 4:
				return String.class;
			default:
				return String.class;
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
				return "Produk";
			case 1:
				return "Qty";
			case 2:
				return "Harga Satuan";
			case 3:
				return "Sub Total";
			case 4:
				return "Tindakan";
			default:
				return "";
			}
		}

	}
}
