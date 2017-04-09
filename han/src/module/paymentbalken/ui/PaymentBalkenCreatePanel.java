package module.paymentbalken.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.util.Bridging;
import module.util.JTextFieldLimit;

import org.apache.log4j.Logger;

import module.paymentbalken.model.PayBalkenProduct;
import module.paymentbalken.model.PaymentBalken;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;

public class PaymentBalkenCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(PaymentBalkenCreatePanel.class);

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
	NumberField txtDiscount;
	NumberField txtTax;
	NumberField txtOtherFee;
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
	NumberField txtCurrencyRate;
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

	private PaymentBalkenCreatePanel payProdResultCreatePanel;

	JLabel lblPayBalkenProduct;

	public PaymentBalkenCreatePanel() {
		payProdResultCreatePanel = this;
		paymentBalken = new PaymentBalken();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 750));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Pembayaran Balken");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Input Pembayaran Balken");
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
		dcDueDate.setDateFormatString("dd-MM-yyyy");
		dcDueDate.setEnabled(false);
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

		txtCurrencyRate = new NumberField(10);
		txtCurrencyRate.setBounds(220, 230, 150, 25);
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
		panel.add(cbPaymentStatus);
		
		cbPaymentStatus.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if("Ya".equals(cbPaymentStatus.getSelectedItem())) {
		    		dcPaymentDate.setDate(new Date());
		    	} else {
		    		dcPaymentDate.setDate(null);
		    	}
		    }
		});

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
						showEditPayBalkenProductDialog(listOfPayBalkenProduct.get(row), payProdResultCreatePanel, row);
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
		txtSubtotal.setEnabled(false);
		panel.add(txtSubtotal);
		
		lblDiscount = new JLabel("Diskon");
		lblDiscount.setBounds(800, 580, 150, 25);
		panel.add(lblDiscount);
		
		txtDiscount = new NumberField(20);
		txtDiscount.setBounds(875, 580, 150, 25);
		panel.add(txtDiscount);
		
		lblTax = new JLabel("Pajak");
		lblTax.setBounds(800, 610, 150, 25);
		panel.add(lblTax);
		
		txtTax = new NumberField(20);
		txtTax.setBounds(875, 610, 150, 25);
		panel.add(txtTax);
		
		lblOtherFee = new JLabel("Biaya Lain-lain");
		lblOtherFee.setBounds(800, 640, 150, 25);
		panel.add(lblOtherFee);
		
		txtOtherFee = new NumberField(20);
		txtOtherFee.setBounds(875, 640, 150, 25);
		panel.add(txtOtherFee);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(800, 670, 150, 25);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(875, 670, 150, 25);
		txtTotal.setEnabled(false);
		panel.add(txtTotal);
		
		txtTax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					String total = calculateTotal(txtSubtotal.getText(), txtTax.getText()
							, txtDiscount.getText(), txtOtherFee.getText()).toString();
					txtTotal.setText(total);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		
		txtDiscount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					String total = calculateTotal(txtSubtotal.getText(), txtTax.getText()
							, txtDiscount.getText(), txtOtherFee.getText()).toString();
					txtTotal.setText(total);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		
		txtOtherFee.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					String total = calculateTotal(txtSubtotal.getText(), txtTax.getText()
							, txtDiscount.getText(), txtOtherFee.getText()).toString();
					txtTotal.setText(total);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		
		txtCurrencyRate.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent arg0) {
				calculateIdrPrice();

				refreshTablePayBalkenProduct();
			}
        });

		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidate() == false) {
					return;
				}
				int response = DialogBox.showInsertChoice();
				if (response == JOptionPane.YES_OPTION) {
					doSave();
				}
			}
		});
		btnSave.setBounds(925, 700, 100, 25);
		btnSave.setFocusable(false);
		panel.add(btnSave);

//		btnPrint = new JButton("Cetak");
//		btnPrint.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				// doPrint();
//			}
//		});
//		btnPrint.setBounds(820, 700, 100, 25);
//		btnPrint.setFocusable(false);
//		panel.add(btnPrint);

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
							.changePanel("module.paymentprodresult.ui.PaymentBalkenViewPanel", paymentBalken);
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
	

	
	protected void calculateIdrPrice() {
		BigDecimal rate = new BigDecimal(txtCurrencyRate.getText());
		
		for (PayBalkenProduct pprProduct : listOfPayBalkenProduct) {
			BigDecimal newIdrPrice = new BigDecimal("0.00");
			newIdrPrice = pprProduct.getPrice().multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
			pprProduct.setIdrPrice(newIdrPrice);
			
			BigDecimal subtotal = new BigDecimal("0.00");
			subtotal = pprProduct.getIdrPrice().multiply(pprProduct.getQtyReceive()).setScale(2, BigDecimal.ROUND_DOWN);
			pprProduct.setSubtotal(subtotal);
		}
	}
	
	private BigDecimal calculateTotal(String subTotal, String tax, String discount, String otherFee) {
		if("".equals(subTotal) || "0.00".equals(subTotal))
			subTotal = "0";
		if("".equals(tax)  || "0.00".equals(tax))
			tax = "0";
		if("".equals(discount)  || "0.00".equals(discount))
			discount = "0";
		if("".equals(otherFee)  || "0.00".equals(otherFee))
			otherFee = "0";
		
		BigDecimal bSubTotal = new BigDecimal(subTotal);
		BigDecimal bTax = new BigDecimal(tax);
		BigDecimal bDiscount = new BigDecimal(discount);
		BigDecimal bOtherFee = new BigDecimal(otherFee);
		
		return bSubTotal.subtract(bDiscount).add(bTax).add(bOtherFee).setScale(2, BigDecimal.ROUND_DOWN);
		
	}
	
	protected void showEditPayBalkenProductDialog(PayBalkenProduct payPrProduct,
			PaymentBalkenCreatePanel payPrProductCreatePanel, Integer index) {
//		PayBalkenProductDialog payPrProductDialog = new PayBalkenProductDialog(true, payPrProduct, payPrProductCreatePanel, index);
//		payPrProductDialog.setTitle("Edit Harga Produk");
//		payPrProductDialog.setLocationRelativeTo(null);
//		payPrProductDialog.setVisible(true);
	}
	
	protected void doSave() {
		if(AppConstants.SOURCE_INVOICE_BALKEN.equals(paymentBalken.getSource())) {
			try {
				paymentBalken.setReceivedCode(txtReceiveProductResultCode.getText());
				paymentBalken.setDueDate(dcDueDate.getDate());
				paymentBalken.setRate(new BigDecimal(txtCurrencyRate.getText()));
				paymentBalken.setSubtotal(new BigDecimal(txtSubtotal.getText()));
				paymentBalken.setDisc(new BigDecimal(txtDiscount.getText()));
				paymentBalken.setTax(new BigDecimal(txtTax.getText()));
				paymentBalken.setOtherFee(new BigDecimal(txtOtherFee.getText()));
				paymentBalken.setTotal(new BigDecimal(txtTotal.getText()));
				paymentBalken.setPaymentStatus(cbPaymentStatus.getSelectedItem().toString());
				paymentBalken.setPaymentDate(dcPaymentDate.getDate());
				ServiceFactory.getPaymentBalkenBL().save(paymentBalken, listOfPayBalkenProduct);
				DialogBox.showInsert();
				MainPanel.changePanel("module.paymentbalken.ui.PaymentBalkenListPanel");
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}
		} else if(AppConstants.SOURCE_PAYMENT_BALKEN.equals(paymentBalken.getSource())) {
			try {
				paymentBalken.setReceivedCode(txtReceiveProductResultCode.getText());
				paymentBalken.setDueDate(dcDueDate.getDate());
				paymentBalken.setRate(new BigDecimal(txtCurrencyRate.getText()));
				paymentBalken.setSubtotal(new BigDecimal(txtSubtotal.getText()));
				paymentBalken.setDisc(new BigDecimal(txtDiscount.getText()));
				paymentBalken.setTax(new BigDecimal(txtTax.getText()));
				paymentBalken.setOtherFee(new BigDecimal(txtOtherFee.getText()));
				paymentBalken.setTotal(new BigDecimal(txtTotal.getText()));
				paymentBalken.setPaymentStatus(cbPaymentStatus.getSelectedItem().toString());
				paymentBalken.setPaymentDate(dcPaymentDate.getDate());
				ServiceFactory.getPaymentBalkenBL().update(paymentBalken, listOfPayBalkenProduct);
				DialogBox.showEdit();
				MainPanel.changePanel("module.paymentbalken.ui.PaymentBalkenViewPanel", paymentBalken);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}
		}
	}
	
	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorReceiveProductResultCode.setText("");
		lblErrorReceiveDate.setText("");
		lblErrorDueDate.setText("");
		lblErrorSupplier.setText("");
		lblErrorCurrency.setText("");
		lblErrorCurrencyRate.setText("");
		lblErrorPaymentStatus.setText("");
		
		
		if (txtReceiveProductResultCode.getText() == null || txtReceiveProductResultCode.getText().length() == 0) {
			lblErrorReceiveProductResultCode.setText("Textbox Kode Penerimaan harus diisi.");
			isValid = false;
		}
		
		if (dcReceiveDate.getDate() == null) {
			lblErrorReceiveDate.setText("Tanggal Penerimaan harus dipilih.");
			isValid = false;
		}
		
		if (dcDueDate.getDate() == null) {
			lblErrorDueDate.setText("Tanggal Jatuh Tempo harus dipilih.");
			isValid = false;
		}
		
		if (txtSupplier.getText() == null || txtSupplier.getText().length() == 0) {
			lblErrorSupplier.setText("Textbox Supplier harus diisi.");
			isValid = false;
		}
		
		if (txtCurrency.getText() == null || txtCurrency.getText().length() == 0) {
			lblErrorCurrency.setText("Textbox Kurs harus diisi.");
			isValid = false;
		}
		
		if (txtCurrencyRate.getText() == null || txtCurrencyRate.getText().length() == 0) {
			lblErrorCurrencyRate.setText("Textbox Kurs Rate harus diisi.");
			isValid = false;
		}
		
		if (cbPaymentStatus.getSelectedItem() == null) {
			lblErrorPaymentStatus.setText("Combobox Status Pembayaran harus dipilih.");
			isValid = false;
		}
		
		if (dcPaymentDate.getDate() == null) {
			lblErrorPaymentDate.setText("Tanggal Pembayaran harus dipilih.");
			isValid = false;
		}
		
		return isValid;
	}

	protected String doCalculateSubTotal() {
		BigDecimal subtotal = new BigDecimal("0.00");
		for (PayBalkenProduct pprProduct : listOfPayBalkenProduct) {
			subtotal = subtotal.add(pprProduct.getSubtotal());
		}

		return subtotal.toString();
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
//			case 6:
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
//			case 6:
//				return String.class;
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
//			case 6:
//				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTablePayBalkenProduct() {
		try {
			tblPayBalkenProduct.setModel(new PayBalkenProductTableModel(listOfPayBalkenProduct));
			
			String subtotal = doCalculateSubTotal();

			txtSubtotal.setText(subtotal);
			
			String total = calculateTotal(txtSubtotal.getText(), txtTax.getText()
					, txtDiscount.getText(), txtOtherFee.getText()).toString();
			
			txtTotal.setText(total);
			
			updateUI();
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
				btnSave.setText("Simpan");
			} 
			else if(AppConstants.SOURCE_INVOICE_BALKEN.equals(source)) {
				paymentBalken = ServiceFactory.getPaymentBalkenBL().getRPRById(id);
				
				if (paymentBalken != null) {
					listOfPayBalkenProduct = ServiceFactory.getPaymentBalkenBL().getPayBalkenProductByIdInvPr(paymentBalken.getInventoryBalkenId());
				}
				btnSave.setText("Simpan");
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
					txtCurrencyRate.setText(paymentBalken.getRate().toString());
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
				
				refreshTablePayBalkenProduct();
				
				if(AppConstants.FINAL.equals(paymentBalken.getStatus()) && AppConstants.SOURCE_PAYMENT_BALKEN.equals(source))
				{
					btnSave.setEnabled(false);
				}
				
				if("Ya".equals(paymentBalken.getPaymentStatus())) {
					cbPaymentStatus.setEnabled(false);
					txtCurrencyRate.setEnabled(false);
					
				}
				
				txtDiscount.setEnabled(false);
				txtTax.setEnabled(false);
				txtOtherFee.setEnabled(false);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
}
