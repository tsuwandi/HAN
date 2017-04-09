package module.paymentbalken.ui;

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
import module.paymentbalken.model.PaymentBalken;
import module.util.Bridging;
import module.util.JTextFieldLimit;

import org.apache.log4j.Logger;

import module.paymentbalken.model.PayBalkenProduct;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;

public class PaymentBalkenViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(PaymentBalkenViewPanel.class);

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
	JLabel lblPaymentDate;
	
	JTextField txtSubtotal;
	JTextField txtDiscount;
	JTextField txtTax;
	JTextField txtOtherFee;
	JTextField txtTotal;
	
	JButton btnInsertPayBalkenProduct;
	JButton btnDeletePayBalkenProduct;

	JButton btnCancel;
	JButton btnSave;
	JButton btnDelete;
	JButton btnPrint;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtReceiveProductResultCode;
	JDateChooser dcReceiveDate;
	JDateChooser dcDueDate;
	JTextField txtSupplier;
	JTextField txtCurrency;
	JTextField txtCurrencyRate;
	JComboBox<String> cbPaymentStatus;
	JDateChooser dcPaymentDate;

	JLabel lblErrorReceiveProductResultCode;
	JLabel lblErrorReceiveDate;
	JLabel lblErrorDueDate;
	JLabel lblErrorSupplier;
	JLabel lblErrorCurrency;
	JLabel lblErrorCurrencyRate;
	JLabel lblErrorPaymentStatus;
	JLabel lblErrorPaymentDate;

	PaymentBalken paymentBalken;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<PayBalkenProduct> listOfPayBalkenProduct = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePayBalkenProduct;
	JTable tblPayBalkenProduct;

	PayBalkenProductTableModel payPrProductTableModel = null;

	private PaymentBalkenViewPanel payProdResultViewPanel;

	JLabel lblPayBalkenProduct;

	public PaymentBalkenViewPanel() {
		payProdResultViewPanel = this;
		paymentBalken = new PaymentBalken();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 750));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Finance > Pembayaran Balken");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("View Pembayaran Balken");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblReceiveProductResultCode = new JLabel(
				"<html>Kode Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveProductResultCode.setBounds(50, 80, 150, 25);
		panel.add(lblReceiveProductResultCode);

		txtReceiveProductResultCode = new JTextField();
		txtReceiveProductResultCode.setBounds(220, 80, 150, 25);
		txtReceiveProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtReceiveProductResultCode.getDocument())
				.setDocumentFilter(filter);
		txtReceiveProductResultCode.setEnabled(false);
		panel.add(txtReceiveProductResultCode);

		lblErrorReceiveProductResultCode = new JLabel();
		lblErrorReceiveProductResultCode.setForeground(Color.RED);
		lblErrorReceiveProductResultCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorReceiveProductResultCode);

		lblReceiveDate = new JLabel(
				"<html>Tanggal Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveDate.setBounds(50, 110, 150, 25);
		panel.add(lblReceiveDate);

		dcReceiveDate = new JDateChooser();
		dcReceiveDate.setBounds(220, 110, 150, 25);
		dcReceiveDate.setEnabled(false);
		dcReceiveDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcReceiveDate);

		lblErrorReceiveDate = new JLabel();
		lblErrorReceiveDate.setForeground(Color.RED);
		lblErrorReceiveDate.setBounds(425, 110, 225, 25);
		panel.add(lblErrorReceiveDate);
		
		lblDueDate = new JLabel(
				"<html>Tanggal Jatuh Tempo <font color=\"red\">*</font></html>");
		lblDueDate.setBounds(50, 140, 150, 25);
		panel.add(lblDueDate);

		dcDueDate = new JDateChooser();
		dcDueDate.setBounds(220, 140, 150, 25);
		dcDueDate.setEnabled(false);
		dcDueDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcDueDate);

		lblErrorDueDate = new JLabel();
		lblErrorDueDate.setForeground(Color.RED);
		lblErrorDueDate.setBounds(425, 140, 225, 25);
		panel.add(lblErrorDueDate);
		
		lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(50, 170, 150, 25);
		panel.add(lblSupplier);

		txtSupplier = new JTextField();
		txtSupplier.setBounds(220, 170, 150, 25);
		txtSupplier.setEnabled(false);
		panel.add(txtSupplier);

		lblErrorSupplier = new JLabel();
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(425, 170, 225, 25);
		panel.add(lblErrorSupplier);
		
		lblCurrency = new JLabel("Kurs");
		lblCurrency.setBounds(50, 200, 150, 25);
		panel.add(lblCurrency);

		txtCurrency = new JTextField();
		txtCurrency.setBounds(220, 200, 150, 25);
		txtCurrency.setEnabled(false);
		panel.add(txtCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 200, 225, 25);
		panel.add(lblErrorCurrency);
		
		lblCurrencyRate = new JLabel("Kurs Rate");
		lblCurrencyRate.setBounds(50, 230, 150, 25);
		panel.add(lblCurrencyRate);

		txtCurrencyRate = new JTextField();
		txtCurrencyRate.setBounds(220, 230, 150, 25);
		txtCurrencyRate.setEnabled(false);
		panel.add(txtCurrencyRate);

		lblErrorCurrencyRate = new JLabel();
		lblErrorCurrencyRate.setForeground(Color.RED);
		lblErrorCurrencyRate.setBounds(425, 230, 225, 25);
		panel.add(lblErrorCurrencyRate);
		
		lblPaymentStatus = new JLabel("Status Pembayaran");
		lblPaymentStatus.setBounds(50, 260, 150, 25);
		panel.add(lblPaymentStatus);
		
		String paymentStatus[]={"Ya", "Belum"}; 
		cbPaymentStatus = new JComboBox<String>(paymentStatus);
		cbPaymentStatus.setBounds(220, 260, 150, 25);
		cbPaymentStatus.setEnabled(false);
		panel.add(cbPaymentStatus);
		
		lblErrorPaymentStatus = new JLabel();
		lblErrorPaymentStatus.setForeground(Color.RED);
		lblErrorPaymentStatus.setBounds(425, 260, 225, 25);
		panel.add(lblErrorPaymentStatus);
		
		lblPaymentDate = new JLabel(
				"<html>Tanggal Pembayaran <font color=\"red\">*</font></html>");
		lblPaymentDate.setBounds(50, 290, 150, 25);
		panel.add(lblPaymentDate);

		dcPaymentDate = new JDateChooser();
		dcPaymentDate.setBounds(220, 290, 150, 25);
		dcPaymentDate.setEnabled(false);
		dcPaymentDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcPaymentDate);

		lblErrorPaymentDate = new JLabel();
		lblErrorPaymentDate.setForeground(Color.RED);
		lblErrorPaymentDate.setBounds(425, 290, 225, 25);
		panel.add(lblErrorPaymentDate);


		scrollPanePayBalkenProduct = new JScrollPane();
		scrollPanePayBalkenProduct.setBounds(50, 390, 975, 150);
		panel.add(scrollPanePayBalkenProduct);

		listOfPayBalkenProduct = new ArrayList<PayBalkenProduct>();
		payPrProductTableModel = new PayBalkenProductTableModel(listOfPayBalkenProduct);
		tblPayBalkenProduct = new JTable(payPrProductTableModel);
		tblPayBalkenProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPayBalkenProduct.setFocusable(false);
		tblPayBalkenProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 7) {
						// showViewPPRProductDialog(listOfBalkenProduct.get(row),
						// pprViewPanel, row);
					}
				}
			}
		});
		scrollPanePayBalkenProduct.setViewportView(tblPayBalkenProduct);
		
		lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(800, 550, 150, 25);
		panel.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setBounds(875, 550, 150, 25);
		txtSubtotal.setText("0.00");
		txtSubtotal.setEnabled(false);
		panel.add(txtSubtotal);
		
		lblDiscount = new JLabel("Diskon");
		lblDiscount.setBounds(800, 580, 150, 25);
		panel.add(lblDiscount);
		
		txtDiscount = new JTextField();
		txtDiscount.setBounds(875, 580, 150, 25);
		txtDiscount.setText("0.00");
		txtDiscount.setEnabled(false);
		panel.add(txtDiscount);
		
		lblTax = new JLabel("Pajak");
		lblTax.setBounds(800, 610, 150, 25);
		panel.add(lblTax);
		
		txtTax = new JTextField();
		txtTax.setBounds(875, 610, 150, 25);
		txtTax.setText("0.00");
		txtTax.setEnabled(false);
		panel.add(txtTax);
		
		lblOtherFee = new JLabel("Biaya Lain-lain");
		lblOtherFee.setBounds(800, 640, 150, 25);
		panel.add(lblOtherFee);
		
		txtOtherFee = new JTextField();
		txtOtherFee.setBounds(875, 640, 150, 25);
		txtOtherFee.setText("0.00");
		txtOtherFee.setEnabled(false);
		panel.add(txtOtherFee);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(800, 670, 150, 25);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(875, 670, 150, 25);
		txtTotal.setText("0.00");
		txtTotal.setEnabled(false);
		panel.add(txtTotal);

		btnSave = new JButton("Input");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel
						.changePanel(
								"module.paymentbalken.ui.PaymentBalkenCreatePanel",
								paymentBalken);
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
							.changePanel("module.paymentbalken.ui.PaymentBalkenListPanel");
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
		for (PayBalkenProduct pprProduct : listOfPayBalkenProduct) {
			subtotal = subtotal.add(pprProduct.getSubtotal());
		}

		return subtotal.setScale(2, BigDecimal.ROUND_DOWN).toString();
	}

//	protected void showViewPPRProductDialog(BalkenProduct pprProduct,
//			PaymentBalkenViewPanel pprViewPanel, Integer index) {
//		// PPRProductDialog pprProductDialog = new PPRProductDialog(true,
//		// pprProduct, pprViewPanel, index);
//		// pprProductDialog.setTitle("Barang");
//		// pprProductDialog.setLocationRelativeTo(null);
//		// pprProductDialog.setVisible(true);
//	}

	protected void doDelete() {
//		try {
//			ServiceFactory.getReceiveProductResultBL().deleteAll(
//					paymentBalken);
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
	class PayBalkenProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PayBalkenProduct> listOfPayBalkenProduct;

		public PayBalkenProductTableModel(List<PayBalkenProduct> listOfPayBalkenProduct) {
			this.listOfPayBalkenProduct = listOfPayBalkenProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPayBalkenProduct.size();
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
		 * @return ({@link PPRProduct}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PayBalkenProduct p = listOfPayBalkenProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProductCode();
			case 1:
				return p.getProduct().getProductName();
			case 2:
				return p.getQtyReceive();
			case 3:
				return p.getPrice();
			case 4:
				return p.getIdrPrice();
			case 5:
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
				return "Qty Penerimaan (m3)";
			case 3:
				return "Harga Satuan";
			case 4:
				return "Harga Satuan (IDR)";
			case 5:
				return "Subtotal";
//			case 7:
//				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTableBalkenProduct() {
		try {
			tblPayBalkenProduct.setModel(new PayBalkenProductTableModel(listOfPayBalkenProduct));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.paymentBalken = (PaymentBalken) objects[0];

		loadData(paymentBalken.getId(), paymentBalken.getSource());
	}

	protected void loadData(Integer id, String source) {
		try {
			if(AppConstants.SOURCE_PAYMENT_BALKEN.equals(source)) {
				paymentBalken = ServiceFactory.getPaymentBalkenBL().getPayPrById(id);
				if (paymentBalken != null) {
					listOfPayBalkenProduct = ServiceFactory.getPaymentBalkenBL().getPayBalkenProductByPPRCode(paymentBalken.getId());
				}
			} 
			else if(AppConstants.SOURCE_INVOICE_BALKEN.equals(source)) {
				paymentBalken = ServiceFactory.getPaymentBalkenBL().getRPRById(id);
				if (paymentBalken != null) {
					listOfPayBalkenProduct = ServiceFactory.getPaymentBalkenBL().getPayBalkenProductByIdInvPr(paymentBalken.getInventoryBalkenId());
				}
			}
		
			if (paymentBalken != null) {
				txtReceiveProductResultCode.setText(paymentBalken.getReceivedCode());
				dcReceiveDate.setDate(paymentBalken.getReceivedDate());
				dcDueDate.setDate(paymentBalken.getDueDate());
				txtSupplier.setText(paymentBalken.getSuppName());
				txtCurrency.setText(paymentBalken.getCurrency());
				dcPaymentDate.setDate(paymentBalken.getPaymentDate());
				
				if(paymentBalken.getRate() == null) {
					txtCurrencyRate.setText(new BigDecimal("1").toString());
				} else {
					txtCurrencyRate.setText(paymentBalken.getRate().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(paymentBalken.getSubtotal() == null) {
					txtSubtotal.setText(new BigDecimal("0.00").toString());
				} else {
					txtSubtotal.setText(paymentBalken.getSubtotal().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(paymentBalken.getDisc() == null) {
					txtDiscount.setText(new BigDecimal("0.00").toString());
				} else {
					txtDiscount.setText(paymentBalken.getDisc().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(paymentBalken.getTax() == null) {
					txtTax.setText(new BigDecimal("0.00").toString());
				} else {
					txtTax.setText(paymentBalken.getTax().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(paymentBalken.getOtherFee() == null) {
					txtOtherFee.setText(new BigDecimal("0.00").toString());
				} else {
					txtOtherFee.setText(paymentBalken.getOtherFee().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(paymentBalken.getTotal() == null) {
					txtTotal.setText(new BigDecimal("0.00").toString());
				} else {
					txtTotal.setText(paymentBalken.getTotal().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				cbPaymentStatus.setSelectedItem(paymentBalken.getPaymentStatus());
				
				refreshTableBalkenProduct();
				
				if(AppConstants.FINAL.equals(paymentBalken.getStatus()) && AppConstants.SOURCE_PAYMENT.equals(source))
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
