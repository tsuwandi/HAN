package module.invoicebalken.ui;

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
import module.invoicebalken.model.InvBalkenProduct;
import module.invoicebalken.model.InvoiceBalken;
import module.util.Bridging;
import module.util.JTextFieldLimit;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;

public class InvoiceBalkenCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(InvoiceBalkenCreatePanel.class);

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
	
	JButton btnInsertInvBalkenProduct;
	JButton btnDeleteInvBalkenProduct;

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
	public JTextField txtCurrency;
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
	
	InvoiceBalken invoiceBalken;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<InvBalkenProduct> listOfInvBalkenProduct = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneInvBalkenProduct;
	JTable tblInvBalkenProduct;

	InvBalkenProductTableModel payPrProductTableModel = null;

	private InvoiceBalkenCreatePanel invProdResultCreatePanel;

	JLabel lblInvBalkenProduct;

	public InvoiceBalkenCreatePanel() {
		invProdResultCreatePanel = this;
		invoiceBalken = new InvoiceBalken();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 750));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Finance > Invoice Balken");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Input Invoice Balken");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblReceiveProductResultCode = new JLabel(
				"Kode Penerimaan ");
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
				"Tanggal Penerimaan");
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

		lblErrorPaymentStatus = new JLabel();
		lblErrorPaymentStatus.setForeground(Color.RED);
		lblErrorPaymentStatus.setBounds(425, 260, 225, 25);
		panel.add(lblErrorPaymentStatus);

		lblPaymentDate = new JLabel(
				"Tanggal Pembayaran");
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
		
		cbPaymentStatus.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if("Ya".equals(cbPaymentStatus.getSelectedItem())) {
		    		dcPaymentDate.setDate(new Date());
		    	} else {
		    		dcPaymentDate.setDate(null);
		    	}
		    }
		});

		scrollPaneInvBalkenProduct = new JScrollPane();
		scrollPaneInvBalkenProduct.setBounds(50, 390, 975, 150);
		panel.add(scrollPaneInvBalkenProduct);

		listOfInvBalkenProduct = new ArrayList<InvBalkenProduct>();
		payPrProductTableModel = new InvBalkenProductTableModel(listOfInvBalkenProduct);
		tblInvBalkenProduct = new JTable(payPrProductTableModel);
		tblInvBalkenProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblInvBalkenProduct.setFocusable(false);
		tblInvBalkenProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 6) {
						showEditInvBalkenProductDialog(listOfInvBalkenProduct.get(row), invProdResultCreatePanel, row);
					}
				}
			}
		});
		scrollPaneInvBalkenProduct.setViewportView(tblInvBalkenProduct);
		
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

				refreshTableInvBalkenProduct();
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
							.changePanel("module.paymentprodresult.ui.InvoiceBalkenViewPanel", invoiceBalken);
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
	
	protected void showEditInvBalkenProductDialog(InvBalkenProduct invBalkenProduct,
			InvoiceBalkenCreatePanel invoiceBalkenCreatePanel, Integer index) {
		InvBalkenProductDialog invBalkenProductDialog = new InvBalkenProductDialog(true, invBalkenProduct, invoiceBalkenCreatePanel, index);
		invBalkenProductDialog.setTitle("Edit Harga Produk");
		invBalkenProductDialog.setLocationRelativeTo(null);
		invBalkenProductDialog.setVisible(true);
	}
	
	protected void doSave() {
		if(AppConstants.SOURCE_RECEIVED.equals(invoiceBalken.getSource())) {
			try {
				invoiceBalken = new InvoiceBalken();
				invoiceBalken.setReceivedCode(txtReceiveProductResultCode.getText());
				invoiceBalken.setDueDate(dcDueDate.getDate());
				invoiceBalken.setRate(new BigDecimal(txtCurrencyRate.getText()));
				invoiceBalken.setSubtotal(new BigDecimal(txtSubtotal.getText()));
				invoiceBalken.setDisc(new BigDecimal(txtDiscount.getText()));
				invoiceBalken.setTax(new BigDecimal(txtTax.getText()));
				invoiceBalken.setOtherFee(new BigDecimal(txtOtherFee.getText()));
				invoiceBalken.setTotal(new BigDecimal(txtTotal.getText()));
				invoiceBalken.setPaymentStatus(cbPaymentStatus.getSelectedItem().toString());
				invoiceBalken.setPaymentDate(dcPaymentDate.getDate());
				
				ServiceFactory.getInvoiceBalkenBL().save(invoiceBalken, listOfInvBalkenProduct);
				DialogBox.showInsert();
				MainPanel.changePanel("module.invoicebalken.ui.InvoiceBalkenListPanel");
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}
		} else if(AppConstants.SOURCE_INVOICE_BALKEN.equals(invoiceBalken.getSource())) {
			try {
				invoiceBalken.setReceivedCode(txtReceiveProductResultCode.getText());
				invoiceBalken.setDueDate(dcDueDate.getDate());
				invoiceBalken.setRate(new BigDecimal(txtCurrencyRate.getText()));
				invoiceBalken.setSubtotal(new BigDecimal(txtSubtotal.getText()));
				invoiceBalken.setDisc(new BigDecimal(txtDiscount.getText()));
				invoiceBalken.setTax(new BigDecimal(txtTax.getText()));
				invoiceBalken.setOtherFee(new BigDecimal(txtOtherFee.getText()));
				invoiceBalken.setTotal(new BigDecimal(txtTotal.getText()));
				invoiceBalken.setPaymentStatus(cbPaymentStatus.getSelectedItem().toString());
				invoiceBalken.setPaymentDate(dcPaymentDate.getDate());
				ServiceFactory.getInvoiceBalkenBL().update(invoiceBalken, listOfInvBalkenProduct);
				DialogBox.showEdit();
				MainPanel.changePanel("module.invoicebalken.ui.InvoiceBalkenViewPanel", invoiceBalken);
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
		
		return isValid;
	}

	protected String doCalculateSubTotal() {
		BigDecimal subtotal = new BigDecimal("0.00");
		for (InvBalkenProduct pprProduct : listOfInvBalkenProduct) {
			subtotal = subtotal.add(pprProduct.getSubtotal());
		}

		return subtotal.setScale(2, BigDecimal.ROUND_DOWN).toString();
	}
	
	protected void calculateIdrPrice() {
		BigDecimal rate = new BigDecimal(txtCurrencyRate.getText());
		
		for (InvBalkenProduct pprProduct : listOfInvBalkenProduct) {
			BigDecimal newIdrPrice = new BigDecimal("0.00");
			newIdrPrice = pprProduct.getPrice().multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
			pprProduct.setIdrPrice(newIdrPrice);
			
			BigDecimal subtotal = new BigDecimal("0.00");
			subtotal = pprProduct.getIdrPrice().multiply(pprProduct.getQtyReceive()).setScale(2, BigDecimal.ROUND_DOWN);
			pprProduct.setSubtotal(subtotal);
		}
	}

	/**
	 * Class as TableModel for PPR Product table
	 * 
	 * @author TSI
	 *
	 */
	class InvBalkenProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<InvBalkenProduct> listOfInvBalkenProduct;

		public InvBalkenProductTableModel(List<InvBalkenProduct> listOfInvBalkenProduct) {
			this.listOfInvBalkenProduct = listOfInvBalkenProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfInvBalkenProduct.size();
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
			InvBalkenProduct p = listOfInvBalkenProduct.get(rowIndex);
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
			case 6:
				return "<html><u>Edit</u></html>";
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
			case 6:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTableInvBalkenProduct() {
		try {
			tblInvBalkenProduct.setModel(new InvBalkenProductTableModel(listOfInvBalkenProduct));
			
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
		this.invoiceBalken = (InvoiceBalken) objects[0];

		loadData(invoiceBalken.getId(), invoiceBalken.getSource());
	}

	protected void loadData(Integer id, String source) {
		try {
			if(AppConstants.SOURCE_INVOICE_BALKEN.equals(source)) {
				invoiceBalken = ServiceFactory.getInvoiceBalkenBL().getPayPrById(id);
				if (invoiceBalken != null) {
					listOfInvBalkenProduct = ServiceFactory.getInvoiceBalkenBL().getInvBalkenProductByPPRCode(invoiceBalken.getId());
				}
				btnSave.setText("Ubah");
			} 
			else if(AppConstants.SOURCE_RECEIVED.equals(source)) {
				invoiceBalken = ServiceFactory.getInvoiceBalkenBL().getRPRById(id);
				
				if (invoiceBalken != null) {
					listOfInvBalkenProduct = ServiceFactory.getInvoiceBalkenBL().getInvBalkenProductByRPRCode(invoiceBalken.getReceivedCode());
				}
				btnSave.setText("Simpan");
			}
		
			if (invoiceBalken != null) {
				txtReceiveProductResultCode.setText(invoiceBalken.getReceivedCode());
				dcReceiveDate.setDate(invoiceBalken.getReceivedDate());
				dcDueDate.setDate(invoiceBalken.getDueDate());
				txtSupplier.setText(invoiceBalken.getSuppName());
				txtCurrency.setText(invoiceBalken.getCurrency());
				dcPaymentDate.setDate(invoiceBalken.getPaymentDate());
				
				if(invoiceBalken.getRate() == null) {
					txtCurrencyRate.setText(new BigDecimal("1").toString());
				} else {
					txtCurrencyRate.setText(invoiceBalken.getRate().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(invoiceBalken.getSubtotal() == null) {
					String subtotal = doCalculateSubTotal();
					txtSubtotal.setText(subtotal);
				} else {
					txtSubtotal.setText(invoiceBalken.getSubtotal().toString());
				}
				
				if(invoiceBalken.getDisc() == null) {
					txtDiscount.setText(new BigDecimal("0.00").toString());
				} else {
					txtDiscount.setText(invoiceBalken.getDisc().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(invoiceBalken.getTax() == null) {
					txtTax.setText(new BigDecimal("0.00").toString());
				} else {
					txtTax.setText(invoiceBalken.getTax().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(invoiceBalken.getOtherFee() == null) {
					txtOtherFee.setText(new BigDecimal("0.00").toString());
				} else {
					txtOtherFee.setText(invoiceBalken.getOtherFee().setScale(2, BigDecimal.ROUND_DOWN).toString());
				}
				
				if(invoiceBalken.getTotal() == null) {
					String total = calculateTotal(txtSubtotal.getText(), txtTax.getText()
							, txtDiscount.getText(), txtOtherFee.getText()).toString();
					txtTotal.setText(total);
				} else {
					txtTotal.setText(invoiceBalken.getTotal().toString());
				}
				
				cbPaymentStatus.setSelectedItem(invoiceBalken.getPaymentStatus());
				
				refreshTableInvBalkenProduct();
				
				if(AppConstants.FINAL.equals(invoiceBalken.getStatus()) && AppConstants.SOURCE_INVOICE.equals(source))
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
