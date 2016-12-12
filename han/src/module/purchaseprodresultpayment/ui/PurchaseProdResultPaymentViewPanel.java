package module.purchaseprodresultpayment.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import module.util.Bridging;
import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.purchaseprodresult.model.PPRNote;
import module.purchaseprodresult.model.PPRProduct;
import module.purchaseprodresult.model.PurchaseProdResult;
import module.purchaseprodresultpayment.ui.PPRNoteDialog;
import module.purchaseprodresultpayment.ui.PPRProductDialog;
import module.supplier.model.Supplier;
import module.util.JTextFieldLimit;

	public class PurchaseProdResultPaymentViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(PurchaseProdResultPaymentViewPanel.class);

	JLabel lblPurchaseProductResultCode;
	JLabel lblSupplier;
	JLabel lblPurchaseDate;
	JLabel lblDueDate;
	JLabel lblPaymentDate;

	JButton btnInsertPPRProduct;
	JButton btnDeletePPRProduct;
	
	JButton btnInsertPPRNote;
	JButton btnDeletePPRNote;
	JButton btnCancel;
	JButton btnSave;
	JButton btnDelete;
	JButton btnPrint;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtPurchaseProductResultCode;
	ComboBox<Supplier> cbSupplier;
	JDateChooser dcPurchaseDate;
	JDateChooser dcDueDate;
	JDateChooser dcPaymentDate;

	JLabel lblErrorPurchaseProductResultCode;
	JLabel lblErrorSupplier;
	JLabel lblErrorPurchaseDate;
	JLabel lblErrorDueDate;
	JLabel lblErrorPaymentDate;

	PurchaseProdResult purchaseProductResult;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<Supplier> listOfSupplier = null;

	List<PPRProduct> listOfPPRProduct = null;
	List<PPRNote> listOfPPRNote = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePPRProduct;
	JTable tblPPRProduct;
	JScrollPane scrollPanePPRNote;
	JTable tblPPRNote;

	PPRProductTableModel pprProductTableModel = null;
	PPRNoteTableModel pprNoteTableModel = null;

	final int SUPP_TYPE_ID_HASIL_PRODUKSI = 3;
	
	private PurchaseProdResultPaymentViewPanel pprViewPanel;
	
	JLabel lblTotal;
	JLabel lblDiscount;
	JLabel lblTax;
	JLabel lblGrandTotal;
	
	JTextField txtTotal;
	JTextField txtDiscount;
	JTextField txtTax;
	JTextField txtGrandTotal;

	public PurchaseProdResultPaymentViewPanel() {
		pprViewPanel = this;
		purchaseProductResult = new PurchaseProdResult();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 870));
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
		cbSupplier.setEnabled(false);
		panel.add(cbSupplier);

		lblErrorSupplier = new JLabel();
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(425, 110, 225, 25);
		panel.add(lblErrorSupplier);

		lblPurchaseDate = new JLabel("<html>Tanggal Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseDate.setBounds(50, 140, 150, 25);
		panel.add(lblPurchaseDate);

		dcPurchaseDate = new JDateChooser(new Date());
		dcPurchaseDate.setBounds(220, 140, 150, 25);
		dcPurchaseDate.setEnabled(false);
		dcPurchaseDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcPurchaseDate);

		lblErrorPurchaseDate = new JLabel();
		lblErrorPurchaseDate.setForeground(Color.RED);
		lblErrorPurchaseDate.setBounds(425, 140, 225, 25);
		panel.add(lblErrorPurchaseDate);

		lblDueDate = new JLabel("<html>Tanggal Jatuh Tempo <font color=\"red\">*</font></html>");
		lblDueDate.setBounds(50, 170, 150, 25);
		panel.add(lblDueDate);

		dcDueDate = new JDateChooser();
		dcDueDate.setBounds(220, 170, 150, 25);
		dcDueDate.setDateFormatString("dd-MM-yyyy");
		dcDueDate.setEnabled(false);
		panel.add(dcDueDate);

		lblErrorDueDate = new JLabel();
		lblErrorDueDate.setForeground(Color.RED);
		lblErrorDueDate.setBounds(425, 170, 225, 25);
		panel.add(lblErrorDueDate);
		
		lblPaymentDate = new JLabel("<html>Tanggal Pembayaran <font color=\"red\">*</font></html>");
		lblPaymentDate.setBounds(50, 200, 150, 25);
		panel.add(lblPaymentDate);
		
		dcPaymentDate = new JDateChooser();
		dcPaymentDate.setBounds(220, 200, 150, 25);
		dcPaymentDate.setDateFormatString("dd-MM-yyyy");
		dcPaymentDate.setEnabled(false);
		panel.add(dcPaymentDate);

		lblErrorPaymentDate = new JLabel();
		lblErrorPaymentDate.setForeground(Color.RED);
		lblErrorPaymentDate.setBounds(425, 200, 225, 25);
		panel.add(lblErrorPaymentDate);

//		btnInsertPPRProduct = new JButton("Tambah");
//		btnInsertPPRProduct.setBounds(820, 460, 100, 25);
//		btnInsertPPRProduct.setFocusable(false);
//		btnInsertPPRProduct.setEnabled(false);
//		panel.add(btnInsertPPRProduct);
//
//		btnDeletePPRProduct = new JButton("Hapus");
//		btnDeletePPRProduct.setFocusable(false);
//		btnDeletePPRProduct.setEnabled(false);
//		btnDeletePPRProduct.setBounds(925, 460, 100, 25);
//		panel.add(btnDeletePPRProduct);

		scrollPanePPRProduct = new JScrollPane();
		scrollPanePPRProduct.setBounds(50, 500, 975, 150);
		panel.add(scrollPanePPRProduct);

		listOfPPRProduct = new ArrayList<PPRProduct>();
		pprProductTableModel = new PPRProductTableModel(listOfPPRProduct);
		tblPPRProduct = new JTable(pprProductTableModel);
		tblPPRProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPPRProduct.setFocusable(false);
		tblPPRProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 5) {
						showViewPPRProductDialog(listOfPPRProduct.get(row), pprViewPanel, row);
					}
				}
			}
		});
		scrollPanePPRProduct.setViewportView(tblPPRProduct);
		
//		btnInsertPPRNote = new JButton("Tambah");
//		btnInsertPPRNote.setBounds(820, 250, 100, 25);
//		btnInsertPPRNote.setFocusable(false);
//		btnInsertPPRNote.setEnabled(false);
//		panel.add(btnInsertPPRNote);
//
//		btnDeletePPRNote = new JButton("Hapus");
//		btnDeletePPRNote.setFocusable(false);
//		btnDeletePPRNote.setEnabled(false);
//		btnDeletePPRNote.setBounds(925, 250, 100, 25);
//		panel.add(btnDeletePPRNote);
		
		scrollPanePPRNote = new JScrollPane();
		scrollPanePPRNote.setBounds(50, 290, 975, 150);
		panel.add(scrollPanePPRNote);
		
		listOfPPRNote = new ArrayList<PPRNote>();
		pprNoteTableModel = new PPRNoteTableModel(listOfPPRNote);
		tblPPRNote = new JTable(pprNoteTableModel);
		tblPPRNote.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPPRNote.setFocusable(false);
		tblPPRNote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 2) {
						showViewPPRNoteDialog(listOfPPRNote.get(row), pprViewPanel, row);
					}
				}
			}
		});
		scrollPanePPRNote.setViewportView(tblPPRNote);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(640, 670, 150, 25);
		panel.add(lblTotal);

		txtTotal = new NumberField(10);
		txtTotal.setEnabled(false);
		txtTotal.setBounds(700, 670, 150, 25);
		panel.add(txtTotal);

		lblDiscount = new JLabel("Diskon");
		lblDiscount.setBounds(640, 700, 150, 25);
		panel.add(lblDiscount);

		txtDiscount = new NumberField(10);
		txtDiscount.setBounds(700, 700, 150, 25);
		txtDiscount.setEnabled(false);
		panel.add(txtDiscount);

		lblTax = new JLabel("Pajak");
		lblTax.setBounds(640, 730, 150, 25);
		panel.add(lblTax);

		txtTax = new NumberField(10);
		txtTax.setBounds(700, 730, 150, 25);
		txtTax.setEnabled(false);
		panel.add(txtTax);

		lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(640, 760, 150, 25);
		panel.add(lblGrandTotal);

		txtGrandTotal = new NumberField(10);
		txtGrandTotal.setEnabled(false);
		txtGrandTotal.setBounds(700, 760, 150, 25);
		panel.add(txtGrandTotal);


		btnSave = new JButton("Ubah");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.purchaseprodresultpayment.ui.PurchaseProdResultPaymentEditPanel", purchaseProductResult);
			}
		});
		btnSave.setBounds(925, 800, 100, 25);
		panel.add(btnSave);
		
		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//doPrint();
			}
		});
		btnPrint.setBounds(820, 800, 100, 25);
		panel.add(btnPrint);
		
//		btnDelete = new JButton("Hapus");
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				int response = DialogBox.showDeleteChoice();
//				if (response == JOptionPane.YES_OPTION) {
//					doDelete();
//				}
//			}
//		});
//		btnDelete.setBounds(820, 800, 100, 25);
//		panel.add(btnDelete);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int response = DialogBox.showCloseChoice();
				 if (response == JOptionPane.YES_OPTION) {
					 MainPanel.changePanel("module.purchaseprodresultpayment.ui.PurchaseProdResultPaymentListPanel");
				 }
			}
		});
		btnCancel.setBounds(50, 800, 100, 25);
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
	
	protected void showViewPPRProductDialog(PPRProduct pprProduct,
			PurchaseProdResultPaymentViewPanel pprViewPanel, Integer index) {
		PPRProductDialog pprProductDialog = new PPRProductDialog(true, pprProduct, pprViewPanel, index);
		pprProductDialog.setTitle("Barang");
		pprProductDialog.setLocationRelativeTo(null);
		pprProductDialog.setVisible(true);
	}
	
	protected void doDelete() {
		try {
			ServiceFactory.getPurchaseProductResultBL().deleteAll(purchaseProductResult);
			DialogBox.showDelete();
			MainPanel.changePanel("module.purchaseprodresultpayment.ui.PurchaseProdResultPaymentListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
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
				return Integer.class;
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
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	protected void showViewPPRNoteDialog(PPRNote pprNote,
			PurchaseProdResultPaymentViewPanel pprViewPanel, Integer index) {
		PPRNoteDialog pprNoteDialog = new PPRNoteDialog(true, pprNote, pprViewPanel, index);
		pprNoteDialog.setTitle("Nota Pembelian");
		pprNoteDialog.setLocationRelativeTo(null);
		pprNoteDialog.setVisible(true);
	}
	
	/**
	 * Class as TableModel for PPR Note table
	 * 
	 * @author TSI
	 *
	 */
	class PPRNoteTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PPRNote> listOfPPRNote;

		public PPRNoteTableModel(List<PPRNote> listOfPPRNote) {
			this.listOfPPRNote = listOfPPRNote;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPPRNote.size();
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
		 * @return ({@link PPRNote}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PPRNote p = listOfPPRNote.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getNote();
			case 2:
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
				return "Note";
			case 2:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
	
	public void refreshTablePPRNote() {
		try {
			tblPPRNote.setModel(new PPRNoteTableModel(listOfPPRNote));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	@Override
	public void invokeObjects(Object... objects) {
		this.purchaseProductResult = (PurchaseProdResult) objects[0];

		loadData(purchaseProductResult.getId());
	}
	
	protected void loadData(Integer pprId) {
		try {
			purchaseProductResult = ServiceFactory.getPurchaseProductResultBL().getPPRById(pprId);
			listOfPPRProduct = ServiceFactory.getPurchaseProductResultBL().getPPRProductByPPRCode(purchaseProductResult.getPprCode());
			listOfPPRNote = ServiceFactory.getPurchaseProductResultBL().getPPRNoteByPPRCode(purchaseProductResult.getPprCode());
			
			if (purchaseProductResult != null) {
				txtPurchaseProductResultCode.setText(purchaseProductResult.getPprCode());
				cbSupplier.setSelectedItem(purchaseProductResult.getSupplier().getSuppName());
				dcPurchaseDate.setDate(purchaseProductResult.getPurchaseDate());
				dcDueDate.setDate(purchaseProductResult.getDueDate());
				
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
				
				refreshTablePPRProduct();
				refreshTablePPRNote();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	
	public int getTotal() {
		int total = 0;
		
		for (PPRProduct p : listOfPPRProduct)
			total += (p.getQty() * p.getUnitPrice());

        return total;
	}
	
	public int getGrandTotal() {
		if ("".equals(txtDiscount.getText())) {
			if("".equals(txtTax.getText()))
				return getTotal();
			else
				return getTotal() + Integer.valueOf(txtTax.getText());
		}else if ("".equals(txtTax.getText())) {
			if("".equals(txtDiscount.getText()))
				return getTotal();
			else
				return getTotal() - Integer.valueOf(txtDiscount.getText());
		} else 
			return getTotal() - Integer.valueOf(txtDiscount.getText()) + Integer.valueOf(txtTax.getText());
	}
}
