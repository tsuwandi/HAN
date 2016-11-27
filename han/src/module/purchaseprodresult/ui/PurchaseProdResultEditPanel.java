package module.purchaseprodresult.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
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

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.purchaseprodresult.model.PPRProduct;
import module.purchaseprodresult.model.PurchaseProdResult;
import module.sn.currency.model.Currency;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class PurchaseProdResultEditPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		this.purchaseProductResult = (PurchaseProdResult) objects[0];

		loadData(purchaseProductResult.getId());
	}

	protected void loadData(Integer pprId) {
		try {
			purchaseProductResult = ServiceFactory.getPurchaseProductResultBL().getPPRById(pprId);
			listOfPPRProduct = ServiceFactory.getPurchaseProductResultBL()
					.getPPRProductByPPRCode(purchaseProductResult.getPprCode());

			if (purchaseProductResult != null) {
				txtPurchaseProductResultCode.setText(purchaseProductResult.getPprCode());
				cbSupplier.setSelectedItem(purchaseProductResult.getSupplier().getSuppName());
				txtPurchaseNote.setText(purchaseProductResult.getPurchaseNote());
				dcPurchaseDate.setDate(purchaseProductResult.getPurchaseDate());
				dcDueDate.setDate(purchaseProductResult.getDueDate());
				cbCurrency.setSelectedItem(purchaseProductResult.getCurrency().getCurrency());
				txtExchangeRate.setText(String.valueOf(purchaseProductResult.getExchangeRate()));

				if (!"".equals(purchaseProductResult.getTotal()))
					txtTotal.setText(String.valueOf(purchaseProductResult.getTotal()));
				else
					txtTotal.setText("0.00");

				if (!"".equals(purchaseProductResult.getDiscount()))
					txtDiscount.setText(String.valueOf(purchaseProductResult.getDiscount()));
				else
					txtDiscount.setText("0.00");

				if (!"".equals(purchaseProductResult.getTax()))
					txtTax.setText(String.valueOf(purchaseProductResult.getTax()));
				else
					txtTax.setText("0.00");

				if (!"".equals(purchaseProductResult.getGrandTotal()))
					txtGrandTotal.setText(String.valueOf(purchaseProductResult.getGrandTotal()));
				else
					txtGrandTotal.setText("0.00");

				tblPPRProduct.setModel(new PPRProductTableModel(listOfPPRProduct));
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	private static final Logger LOGGER = Logger.getLogger(PurchaseProdResultEditPanel.class);

	JLabel lblPurchaseProductResultCode;
	JLabel lblSupplier;
	JLabel lblPurchaseNote;
	JLabel lblPurchaseDate;
	JLabel lblDueDate;
	JLabel lblCurrency;
	JLabel lblExchangeRate;

	JLabel lblTotal;
	JLabel lblDiscount;
	JLabel lblTax;
	JLabel lblGrandTotal;

	JButton btnInsert;
	JButton btnDelete;
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

	JTextField txtTotal;
	JTextField txtDiscount;
	JTextField txtTax;
	JTextField txtGrandTotal;

	JLabel lblErrorPurchaseProductResultCode;
	JLabel lblErrorSupplier;
	JLabel lblErrorPurchaseDate;
	JLabel lblErrorDueDate;
	JLabel lblErrorCurrency;

	PurchaseProdResult purchaseProductResult;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<Supplier> listOfSupplier = null;
	List<Currency> listOfCurrency = null;

	List<PPRProduct> listOfPPRProduct = null;
	public List<PPRProduct> listOfDeletedPPRProduct = new ArrayList<PPRProduct>();

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePPRProduct;
	JTable tblPPRProduct;

	PPRProductTableModel pprTableTableModel = null;

	PurchaseProdResultEditPanel pprEditPanel;

	final int SUPP_TYPE_ID_HASIL_PRODUKSI = 3;

	public PurchaseProdResultEditPanel() {
		purchaseProductResult = new PurchaseProdResult();

		pprEditPanel = this;

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 700));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi > Pembelian");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 414, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Ubah");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblPurchaseProductResultCode = new JLabel("<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductResultCode.setBounds(50, 80, 150, 25);
		panel.add(lblPurchaseProductResultCode);

		txtPurchaseProductResultCode = new JTextField();
		txtPurchaseProductResultCode.setBounds(220, 80, 150, 25);
		txtPurchaseProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtPurchaseProductResultCode.getDocument()).setDocumentFilter(filter);
		txtPurchaseProductResultCode.setEnabled(false);
		panel.add(txtPurchaseProductResultCode);

		lblErrorPurchaseProductResultCode = new JLabel();
		lblErrorPurchaseProductResultCode.setForeground(Color.RED);
		lblErrorPurchaseProductResultCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorPurchaseProductResultCode);

		lblSupplier = new JLabel("<html>Supplier <font color=\"red\">*</font></html>");
		lblSupplier.setBounds(50, 110, 150, 25);
		panel.add(lblSupplier);

		listOfSupplier = new ArrayList<Supplier>();
		try {
			listOfSupplier = ServiceFactory.getPurchaseProductResultBL().getAllSupplierBySuppTypeId(3);
			listOfSupplier.add(0, new Supplier("-- Pilih Supplier --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

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
		try {
			listOfCurrency = ServiceFactory.getPurchaseProductResultBL().getAllCurrency();
			listOfCurrency.add(0, new Currency("-- Pilih Kurs --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbCurrency = new ComboBox<Currency>();
		cbCurrency.setList(listOfCurrency);
		cbCurrency.setBounds(220, 230, 150, 25);
		panel.add(cbCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 230, 225, 25);
		panel.add(lblErrorCurrency);

		cbSupplier.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				cbCurrency.setSelectedItem(cbSupplier.getDataIndex().getCurrency().getCurrency());
			}
		});

		lblExchangeRate = new JLabel("Kurs Rate");
		lblExchangeRate.setBounds(50, 260, 150, 25);
		panel.add(lblExchangeRate);

		txtExchangeRate = new NumberField(10);
		txtExchangeRate.setBounds(220, 260, 150, 25);
		panel.add(txtExchangeRate);

		btnInsert = new JButton("Tambah");
		btnInsert.setBounds(820, 300, 100, 25);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddPPRProductDialog(pprEditPanel);
			}
		});
		panel.add(btnInsert);

		scrollPanePPRProduct = new JScrollPane();
		scrollPanePPRProduct.setBounds(50, 340, 975, 150);
		panel.add(scrollPanePPRProduct);

		listOfPPRProduct = new ArrayList<PPRProduct>();
		pprTableTableModel = new PPRProductTableModel(listOfPPRProduct);
		tblPPRProduct = new JTable(pprTableTableModel);
		tblPPRProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPPRProduct.setFocusable(false);
		tblPPRProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblPPRProduct.getValueAt(tblPPRProduct.getSelectedRow(), 0).equals(true))
					listOfPPRProduct.get(tblPPRProduct.getSelectedRow()).setFlag(false);
				else
					listOfPPRProduct.get(tblPPRProduct.getSelectedRow()).setFlag(true);

				tblPPRProduct.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 5) {
						showEditPPRProductDialog(listOfPPRProduct.get(row), pprEditPanel, row);
					}
				}
			}
		});
		scrollPanePPRProduct.setViewportView(tblPPRProduct);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(640, 500, 150, 25);
		panel.add(lblTotal);

		txtTotal = new NumberField(10);
		txtTotal.setEnabled(false);
		txtTotal.setBounds(700, 500, 150, 25);
		panel.add(txtTotal);

		lblDiscount = new JLabel("Diskon");
		lblDiscount.setBounds(640, 530, 150, 25);
		panel.add(lblDiscount);

		txtDiscount = new NumberField(10);
		txtDiscount.setBounds(700, 530, 150, 25);
		txtDiscount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtGrandTotal.setText(String.valueOf(getGrandTotal()));
				txtGrandTotal.updateUI();
			}
		});
		panel.add(txtDiscount);

		lblTax = new JLabel("Pajak");
		lblTax.setBounds(640, 560, 150, 25);
		panel.add(lblTax);

		txtTax = new NumberField(10);
		txtTax.setBounds(700, 560, 150, 25);
		txtTax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtGrandTotal.setText(String.valueOf(getGrandTotal()));
				txtGrandTotal.updateUI();
			}
		});
		panel.add(txtTax);

		lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(640, 590, 150, 25);
		panel.add(lblGrandTotal);

		txtGrandTotal = new NumberField(10);
		txtGrandTotal.setEnabled(false);
		txtGrandTotal.setBounds(700, 590, 150, 25);
		panel.add(txtGrandTotal);

		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidate() == false) {
					return;
				}
				int response = DialogBox.showEditChoice();
				if (response == JOptionPane.YES_OPTION) {
					doSave();
				}
			}
		});
		btnSave.setBounds(925, 640, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.purchaseprodresult.ui.PurchaseProductResultViewPanel",
							purchaseProductResult);
				}
			}
		});
		btnCancel.setBounds(50, 640, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtTotal.setText("");
				txtGrandTotal.setText("");
				doDeletePPRProduct();
				txtTotal.setText(String.valueOf(getTotal()));
				txtGrandTotal.setText(String.valueOf(getGrandTotal()));
				//txtTotal.updateUI();
				//txtGrandTotal.updateUI();
			}
		});
		btnDelete.setBounds(925, 300, 100, 25);
		panel.add(btnDelete);

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

	protected void doSave() {
		purchaseProductResult.setSuppCode(cbSupplier.getDataIndex().getSuppCode());
		purchaseProductResult.setPurchaseNote(txtPurchaseNote.getText());
		purchaseProductResult.setPurchaseDate(dcPurchaseDate.getDate());
		purchaseProductResult.setDueDate(dcDueDate.getDate());
		purchaseProductResult.setCurrencyId(cbCurrency.getDataIndex().getId());
		purchaseProductResult.setExchangeRate(Double.valueOf(txtExchangeRate.getText()));

		if (!"".equals(txtTotal.getText()))
			purchaseProductResult.setTotal(Double.valueOf(txtTotal.getText()));
		else
			purchaseProductResult.setTotal(0.00);

		if (!"".equals(txtDiscount.getText()))
			purchaseProductResult.setDiscount(Double.valueOf(txtDiscount.getText()));
		else
			purchaseProductResult.setDiscount(0.00);

		if (!"".equals(txtTax.getText()))
			purchaseProductResult.setTax(Double.valueOf(txtTax.getText()));
		else
			purchaseProductResult.setTax(0.00);

		if (!"".equals(txtGrandTotal.getText()))
			purchaseProductResult.setGrandTotal(Double.valueOf(txtGrandTotal.getText()));
		else
			purchaseProductResult.setGrandTotal(0.00);

		try {
			ServiceFactory.getPurchaseProductResultBL().update(purchaseProductResult, listOfPPRProduct,
					listOfDeletedPPRProduct);
			DialogBox.showEdit();
			MainPanel.changePanel("module.purchaseprodresult.ui.PurchaseProdResultViewPanel", purchaseProductResult);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorPurchaseProductResultCode.setText("");
		lblErrorSupplier.setText("");
		lblErrorPurchaseDate.setText("");
		lblErrorDueDate.setText("");
		lblErrorCurrency.setText("");

		if (cbSupplier.getSelectedItem() == null || cbSupplier.getSelectedIndex() == 0) {
			lblErrorSupplier.setText("Combobox Supplier harus dipilih.");
			isValid = false;
		}

		if (dcPurchaseDate.getDate() == null) {
			lblErrorPurchaseDate.setText("Tanggal Pembelian harus dipilih.");
			isValid = false;
		}

		if (dcDueDate.getDate() == null) {
			lblErrorDueDate.setText("Tanggal Jatuh Tempo harus dipilih.");
			isValid = false;
		}

		if (cbCurrency.getSelectedItem() == null || cbCurrency.getSelectedIndex() == 0) {
			lblErrorCurrency.setText("Combobox Kurs harus dipilih.");
			isValid = false;
		}

		return isValid;
	}

	protected void showAddPPRProductDialog(PurchaseProdResultEditPanel pprEditPanel) {
		PPRProductDialog pprProductDialog = new PPRProductDialog(false, new PPRProduct(), pprEditPanel, null);
		pprProductDialog.setTitle("Barang");
		pprProductDialog.setLocationRelativeTo(null);
		pprProductDialog.setVisible(true);
	}

	protected void showEditPPRProductDialog(PPRProduct pprProduct, PurchaseProdResultEditPanel pprEditPanel,
			Integer index) {
		PPRProductDialog pprProductDialog = new PPRProductDialog(true, pprProduct, pprEditPanel, index);
		pprProductDialog.setTitle("Barang");
		pprProductDialog.setLocationRelativeTo(null);
		pprProductDialog.setVisible(true);
	}

	protected void doDeletePPRProduct() {
		if (listOfPPRProduct.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<PPRProduct> temp = new ArrayList<PPRProduct>();
			for (PPRProduct s : listOfPPRProduct) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfPPRProduct.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (PPRProduct s : temp) {
					listOfDeletedPPRProduct.add(s);
					listOfPPRProduct.remove(s);
				}
				refreshTablePPRProduct();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Class as TableModel for PPR Product table
	 * 
	 * @author TSI
	 *
	 */
	class PPRProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PPRProduct> listOfPPRProduct;

		public PPRProductTableModel(List<PPRProduct> listOfPPRProduct) {
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
			return 6;
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
			PPRProduct p = listOfPPRProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getProduct().getProductName();
			case 2:
				return p.getQty();
			case 3:
				return p.getUnitPrice();
			case 4:
				return p.getQty() * p.getUnitPrice();
			case 5:
				return "<html><u>View</u></html>";
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
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return Double.class;
			case 3:
				return Double.class;
			case 4:
				return Integer.class;
			case 5:
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
				return "";
			case 1:
				return "Produk";
			case 2:
				return "Qty";
			case 3:
				return "Harga Satuan";
			case 4:
				return "Sub Total";
			case 5:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTablePPRProduct() {
		try {
			tblPPRProduct.setModel(new PPRProductTableModel(listOfPPRProduct));
		} catch (Exception e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public int getTotal() {
		int total = 0;

		for (PPRProduct p : listOfPPRProduct)
			total += (p.getQty() * p.getUnitPrice());

		return Integer.valueOf(total);

	}

	public int getGrandTotal() {
		if ("".equals(txtDiscount.getText())) {
			if ("".equals(txtTax.getText()))
				return getTotal();
			else
				return getTotal() + Integer.valueOf(txtTax.getText());
		} else if ("".equals(txtTax.getText())) {
			if ("".equals(txtDiscount.getText()))
				return getTotal();
			else
				return getTotal() - Integer.valueOf(txtDiscount.getText());
		} else {
			int discount = Integer.valueOf(txtDiscount.getText().replace(".0", ""));
			int tax = Integer.valueOf(txtTax.getText().replace(".0", ""));
			int total = this.getTotal();
			int grandTotal = (total - discount + tax);
			return grandTotal;
		}
	}
}
