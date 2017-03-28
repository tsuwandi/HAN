package module.paymentprodresult.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import main.component.AppConstants;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.receiveprodresult.model.RPRNote;
import module.receiveprodresult.model.RPRProduct;
import module.receiveprodresult.model.ReceiveProdResult;
import module.util.Bridging;
import module.util.JTextFieldLimit;

import org.apache.log4j.Logger;

import module.paymentprodresult.model.PaymentProdResult;
import module.paymentprodresult.model.PayPrProduct;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;

public class PaymentProdResultViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(PaymentProdResultViewPanel.class);

	JLabel lblPurchaseProductResultCode;
	JLabel lblPurchaseDate;
	JLabel lblReceiveProductResultCode;
	JLabel lblReceiveDate;
	JLabel lblDueDate;
	JLabel lblSupplier;
	JLabel lblCurrency;
	JLabel lblCurrencyRate;
	JLabel lblPaymentStatus;
	JLabel lblSubtotal;
	JLabel lblDiscount;
	JLabel lblTax;
	JLabel lblOtherFee;
	JLabel lblTotal;
	
	JTextField txtSubtotal;
	JTextField txtDiscount;
	JTextField txtTax;
	JTextField txtOtherFee;
	JTextField txtTotal;
	
	JButton btnInsertPayPrProduct;
	JButton btnDeletePayPrProduct;

	JButton btnCancel;
	JButton btnSave;
	JButton btnDelete;
	JButton btnPrint;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtPurchaseProductResultCode;
	JTextField txtReceiveProductResultCode;
	JDateChooser dcPurchaseDate;
	JDateChooser dcReceiveDate;
	JDateChooser dcDueDate;
	JTextField txtSupplier;
	JTextField txtCurrency;
	JTextField txtCurrencyRate;
	JComboBox<String> cbPaymentStatus;

	JLabel lblErrorPurchaseProductResultCode;
	JLabel lblErrorPurchaseDate;
	JLabel lblErrorReceiveProductResultCode;
	JLabel lblErrorReceiveDate;
	JLabel lblErrorDueDate;
	JLabel lblErrorSupplier;
	JLabel lblErrorCurrency;
	JLabel lblErrorCurrencyRate;
	JLabel lblErrorPaymentStatus;

	PaymentProdResult paymentProductResult;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<PayPrProduct> listOfPayPrProduct = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePayPrProduct;
	JTable tblPayPrProduct;

	PayPrProductTableModel payPrProductTableModel = null;

	private PaymentProdResultViewPanel payProdResultViewPanel;

	JLabel lblPayPrProduct;

	public PaymentProdResultViewPanel() {
		payProdResultViewPanel = this;
		paymentProductResult = new PaymentProdResult();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 750));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Pembayaran Hasil Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("View Pembayaran Hasil Produksi");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblPurchaseProductResultCode = new JLabel(
				"<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductResultCode.setBounds(50, 80, 150, 25);
		panel.add(lblPurchaseProductResultCode);

		txtPurchaseProductResultCode = new JTextField();
		txtPurchaseProductResultCode.setBounds(220, 80, 150, 25);
		txtPurchaseProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtPurchaseProductResultCode.getDocument())
				.setDocumentFilter(filter);
		txtPurchaseProductResultCode.setEnabled(false);
		panel.add(txtPurchaseProductResultCode);

		lblErrorPurchaseProductResultCode = new JLabel();
		lblErrorPurchaseProductResultCode.setForeground(Color.RED);
		lblErrorPurchaseProductResultCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorPurchaseProductResultCode);

		lblPurchaseDate = new JLabel(
				"<html>Tanggal Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseDate.setBounds(50, 110, 150, 25);
		panel.add(lblPurchaseDate);

		dcPurchaseDate = new JDateChooser();
		dcPurchaseDate.setBounds(220, 110, 150, 25);
		dcPurchaseDate.setEnabled(false);
		dcPurchaseDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcPurchaseDate);

		lblErrorPurchaseDate = new JLabel();
		lblErrorPurchaseDate.setForeground(Color.RED);
		lblErrorPurchaseDate.setBounds(425, 110, 225, 25);
		panel.add(lblErrorPurchaseDate);

		lblReceiveProductResultCode = new JLabel(
				"<html>Kode Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveProductResultCode.setBounds(50, 140, 150, 25);
		panel.add(lblReceiveProductResultCode);

		txtReceiveProductResultCode = new JTextField();
		txtReceiveProductResultCode.setBounds(220, 140, 150, 25);
		txtReceiveProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtReceiveProductResultCode.getDocument())
				.setDocumentFilter(filter);
		txtReceiveProductResultCode.setEnabled(false);
		panel.add(txtReceiveProductResultCode);

		lblErrorReceiveProductResultCode = new JLabel();
		lblErrorReceiveProductResultCode.setForeground(Color.RED);
		lblErrorReceiveProductResultCode.setBounds(425, 140, 225, 25);
		panel.add(lblErrorReceiveProductResultCode);

		lblReceiveDate = new JLabel(
				"<html>Tanggal Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveDate.setBounds(50, 170, 150, 25);
		panel.add(lblReceiveDate);

		dcReceiveDate = new JDateChooser();
		dcReceiveDate.setBounds(220, 170, 150, 25);
		dcReceiveDate.setEnabled(false);
		dcReceiveDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcReceiveDate);

		lblErrorReceiveDate = new JLabel();
		lblErrorReceiveDate.setForeground(Color.RED);
		lblErrorReceiveDate.setBounds(425, 170, 225, 25);
		panel.add(lblErrorReceiveDate);
		
		lblDueDate = new JLabel(
				"<html>Tanggal Jatuh Tempo <font color=\"red\">*</font></html>");
		lblDueDate.setBounds(50, 200, 150, 25);
		panel.add(lblDueDate);

		dcDueDate = new JDateChooser();
		dcDueDate.setBounds(220, 200, 150, 25);
		dcDueDate.setEnabled(false);
		dcDueDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcDueDate);

		lblErrorDueDate = new JLabel();
		lblErrorDueDate.setForeground(Color.RED);
		lblErrorDueDate.setBounds(425, 200, 225, 25);
		panel.add(lblErrorDueDate);
		
		lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(50, 230, 150, 25);
		panel.add(lblSupplier);

		txtSupplier = new JTextField();
		txtSupplier.setBounds(220, 230, 150, 25);
		txtSupplier.setEnabled(false);
		panel.add(txtSupplier);

		lblErrorSupplier = new JLabel();
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(425, 230, 225, 25);
		panel.add(lblErrorSupplier);
		
		lblCurrency = new JLabel("Kurs");
		lblCurrency.setBounds(50, 260, 150, 25);
		panel.add(lblCurrency);

		txtCurrency = new JTextField();
		txtCurrency.setBounds(220, 260, 150, 25);
		txtCurrency.setEnabled(false);
		panel.add(txtCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 260, 225, 25);
		panel.add(lblErrorCurrency);
		
		lblCurrencyRate = new JLabel("Kurs Rate");
		lblCurrencyRate.setBounds(50, 290, 150, 25);
		panel.add(lblCurrencyRate);

		txtCurrencyRate = new JTextField();
		txtCurrencyRate.setBounds(220, 290, 150, 25);
		txtCurrencyRate.setEnabled(false);
		panel.add(txtCurrencyRate);

		lblErrorCurrencyRate = new JLabel();
		lblErrorCurrencyRate.setForeground(Color.RED);
		lblErrorCurrencyRate.setBounds(425, 290, 225, 25);
		panel.add(lblErrorCurrencyRate);
		
		lblPaymentStatus = new JLabel("Status Pembayaran");
		lblPaymentStatus.setBounds(50, 320, 150, 25);
		panel.add(lblPaymentStatus);
		
		String paymentStatus[]={"Ya", "Belum"}; 
		cbPaymentStatus = new JComboBox<String>(paymentStatus);
		cbPaymentStatus.setBounds(220, 320, 150, 25);
		cbPaymentStatus.setEnabled(false);
		panel.add(cbPaymentStatus);
		
		lblErrorPaymentStatus = new JLabel();
		lblErrorPaymentStatus.setForeground(Color.RED);
		lblErrorPaymentStatus.setBounds(425, 320, 225, 25);
		panel.add(lblErrorPaymentStatus);


		scrollPanePayPrProduct = new JScrollPane();
		scrollPanePayPrProduct.setBounds(50, 370, 975, 150);
		panel.add(scrollPanePayPrProduct);

		listOfPayPrProduct = new ArrayList<PayPrProduct>();
		payPrProductTableModel = new PayPrProductTableModel(listOfPayPrProduct);
		tblPayPrProduct = new JTable(payPrProductTableModel);
		tblPayPrProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPayPrProduct.setFocusable(false);
		tblPayPrProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 7) {
						// showViewPPRProductDialog(listOfRPRProduct.get(row),
						// pprViewPanel, row);
					}
				}
			}
		});
		scrollPanePayPrProduct.setViewportView(tblPayPrProduct);
		
		lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(800, 530, 150, 25);
		panel.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setBounds(875, 530, 150, 25);
		txtSubtotal.setText("0.00");
		txtSubtotal.setEnabled(false);
		panel.add(txtSubtotal);
		
		lblDiscount = new JLabel("Diskon");
		lblDiscount.setBounds(800, 560, 150, 25);
		panel.add(lblDiscount);
		
		txtDiscount = new JTextField();
		txtDiscount.setBounds(875, 560, 150, 25);
		txtDiscount.setText("0.00");
		txtDiscount.setEnabled(false);
		panel.add(txtDiscount);
		
		lblTax = new JLabel("Pajak");
		lblTax.setBounds(800, 590, 150, 25);
		panel.add(lblTax);
		
		txtTax = new JTextField();
		txtTax.setBounds(875, 590, 150, 25);
		txtTax.setText("0.00");
		txtTax.setEnabled(false);
		panel.add(txtTax);
		
		lblOtherFee = new JLabel("Biaya Lain-lain");
		lblOtherFee.setBounds(800, 620, 150, 25);
		panel.add(lblOtherFee);
		
		txtOtherFee = new JTextField();
		txtOtherFee.setBounds(875, 620, 150, 25);
		txtOtherFee.setText("0.00");
		txtOtherFee.setEnabled(false);
		panel.add(txtOtherFee);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(800, 650, 150, 25);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(875, 650, 150, 25);
		txtTotal.setText("0.00");
		txtTotal.setEnabled(false);
		panel.add(txtTotal);

		btnSave = new JButton("Input");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel
						.changePanel(
								"module.paymentprodresult.ui.PaymentProdResultCreatePanel",
								paymentProductResult);
			}
		});
		btnSave.setBounds(925, 700, 100, 25);
		btnSave.setFocusable(false);
		panel.add(btnSave);

		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// doPrint();
			}
		});
		btnPrint.setBounds(820, 700, 100, 25);
		btnPrint.setFocusable(false);
		panel.add(btnPrint);

		// btnDelete = new JButton("Hapus");
		// btnDelete.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// int response = DialogBox.showDeleteChoice();
		// if (response == JOptionPane.YES_OPTION) {
		// doDelete();
		// }
		// }
		// });
		// btnDelete.setBounds(820, 560, 100, 25);
		// panel.add(btnDelete);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel
							.changePanel("module.paymentprodresult.ui.PaymentProdResultListPanel");
				}
			}
		});
		btnCancel.setBounds(50, 700, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		scrollPane = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(scrollPane);

	}

	protected String doCalculateSubTotal() {
		BigDecimal subtotal = new BigDecimal("0.00");
		for (PayPrProduct pprProduct : listOfPayPrProduct) {
			subtotal = subtotal.add(pprProduct.getSubtotal());
		}

		return subtotal.toString();
	}

	protected void showViewPPRProductDialog(RPRProduct pprProduct,
			PaymentProdResultViewPanel pprViewPanel, Integer index) {
		// PPRProductDialog pprProductDialog = new PPRProductDialog(true,
		// pprProduct, pprViewPanel, index);
		// pprProductDialog.setTitle("Barang");
		// pprProductDialog.setLocationRelativeTo(null);
		// pprProductDialog.setVisible(true);
	}

	protected void doDelete() {
//		try {
//			ServiceFactory.getReceiveProductResultBL().deleteAll(
//					paymentProductResult);
//			DialogBox.showDelete();
//			MainPanel
//					.changePanel("module.purchaseprodresult.ui.ReceiveProdResultListPanel");
//		} catch (SQLException e1) {
//			LOGGER.error(e1.getMessage());
//			DialogBox.showErrorException();
//		}
	}

	/**
	 * Class as TableModel for PPR Product table
	 * 
	 * @author TSI
	 *
	 */
	class PayPrProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PayPrProduct> listOfPayPrProduct;

		public PayPrProductTableModel(List<PayPrProduct> listOfPayPrProduct) {
			this.listOfPayPrProduct = listOfPayPrProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPayPrProduct.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 7;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link PPRProduct}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PayPrProduct p = listOfPayPrProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProductCode();
			case 1:
				return p.getProduct().getProductName();
			case 2:
				return p.getQtyPurchase();
			case 3:
				return p.getQtyReceive();
			case 4:
				return p.getPrice();
			case 5:
				return p.getIdrPrice();
			case 6:
				return p.getSubtotal();
//			case 7:
//				return "<html><u>Edit</u></html>";
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
				return String.class;
			case 2:
				return BigDecimal.class;
			case 3:
				return BigDecimal.class;
			case 4:
				return BigDecimal.class;
			case 5:
				return BigDecimal.class;
			case 6:
				return BigDecimal.class;
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
				return "Kode Produk";
			case 1:
				return "Nama Produk";
			case 2:
				return "Qty Pembelian (m3)";
			case 3:
				return "Qty Penerimaan (m3)";
			case 4:
				return "Harga Satuan";
			case 5:
				return "Harga Satuan (IDR)";
			case 6:
				return "Subtotal";
//			case 7:
//				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTableRPRProduct() {
		try {
			tblPayPrProduct.setModel(new PayPrProductTableModel(listOfPayPrProduct));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.paymentProductResult = (PaymentProdResult) objects[0];

		loadData(paymentProductResult.getId(), paymentProductResult.getSource());
	}

	protected void loadData(Integer id, String source) {
		try {
			if(AppConstants.SOURCE_PAYMENT.equals(source)) {
				paymentProductResult = ServiceFactory.getPaymentProductResultBL().getPayPrById(id);
				if (paymentProductResult != null) {
					listOfPayPrProduct = ServiceFactory.getPaymentProductResultBL().getPayPrProductByPPRCode(paymentProductResult.getPayPrCode());
				}
			} 
			else if(AppConstants.SOURCE_RECEIVE.equals(source)) {
				paymentProductResult = ServiceFactory.getPaymentProductResultBL().getRPRById(id);
				
				if (paymentProductResult != null) {
					listOfPayPrProduct = ServiceFactory.getPaymentProductResultBL().getPayPrProductByRPRCode(paymentProductResult.getRprCode());
				}
			}
		
			if (paymentProductResult != null) {
				txtPurchaseProductResultCode.setText(paymentProductResult.getPprCode());
				dcPurchaseDate.setDate(paymentProductResult.getPurchaseDate());
				txtReceiveProductResultCode.setText(paymentProductResult.getRprCode());
				dcReceiveDate.setDate(paymentProductResult.getReceiveDate());
				dcDueDate.setDate(paymentProductResult.getDueDate());
				txtSupplier.setText(paymentProductResult.getSuppName());
				txtCurrency.setText(paymentProductResult.getCurrency());
				
				if(paymentProductResult.getRate() == null) {
					txtCurrencyRate.setText(new BigDecimal("1").toString());
				} else {
					txtCurrencyRate.setText(paymentProductResult.getRate().toString());
				}
				
				if(paymentProductResult.getSubtotal() == null) {
					txtSubtotal.setText(new BigDecimal("0.00").toString());
				} else {
					txtSubtotal.setText(paymentProductResult.getSubtotal().toString());
				}
				
				if(paymentProductResult.getDisc() == null) {
					txtDiscount.setText(new BigDecimal("0.00").toString());
				} else {
					txtDiscount.setText(paymentProductResult.getDisc().toString());
				}
				
				if(paymentProductResult.getTax() == null) {
					txtTax.setText(new BigDecimal("0.00").toString());
				} else {
					txtTax.setText(paymentProductResult.getTax().toString());
				}
				
				if(paymentProductResult.getOtherFee() == null) {
					txtOtherFee.setText(new BigDecimal("0.00").toString());
				} else {
					txtOtherFee.setText(paymentProductResult.getOtherFee().toString());
				}
				
				if(paymentProductResult.getTotal() == null) {
					txtTotal.setText(new BigDecimal("0.00").toString());
				} else {
					txtTotal.setText(paymentProductResult.getTotal().toString());
				}
				
				cbPaymentStatus.setSelectedItem(paymentProductResult.getPaymentStatus());
				
				refreshTableRPRProduct();
				
				if(AppConstants.FINAL.equals(paymentProductResult.getStatus()))
				{
					btnSave.setEnabled(false);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
}
