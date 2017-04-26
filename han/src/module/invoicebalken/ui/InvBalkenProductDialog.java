package module.invoicebalken.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;

import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.invoicebalken.model.InvoiceBalken;
import module.product.model.Product;
import module.sn.production.type.model.ProductionType;

import org.apache.log4j.Logger;

import module.invoicebalken.model.InvBalkenProduct;

public class InvBalkenProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(InvBalkenProductDialog.class);

	JPanel panel;

	JLabel lblProductCode;
	JLabel lblProductName;
	JLabel lblQtyReceived;
	JLabel lblPrice;
	JLabel lblIdrPrice;

	JTextField txtProductCode;
	JTextField txtProductName;
	JTextField txtQtyReceived;
	NumberField txtPrice;
	JTextField txtIdrPrice;

	JLabel lblErrorProductCode;
	JLabel lblErrorProductName;
	JLabel lblErrorQtyReceived;
	JLabel lblErrorPrice;
	JLabel lblErrorIdrPrice;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private InvBalkenProduct invBalkenProduct;
	private InvoiceBalkenCreatePanel invoiceBalkenCreatePanel;
	
	List<ProductionType> listOfProductionType = null;
	List<Product> listOfProduct = null;

	private Integer index;
	

	public InvBalkenProductDialog(boolean edit, InvBalkenProduct invBalkenProduct, InvoiceBalkenCreatePanel invoiceBalkenCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.invBalkenProduct = invBalkenProduct;
		this.invoiceBalkenCreatePanel = invoiceBalkenCreatePanel;
		this.index = index;
		init();
	}

	public void init() {

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 265);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblProductCode = new JLabel("Kode Produk");
		lblProductCode.setBounds(25, 15, 150, 25);
		getContentPane().add(lblProductCode);
		
		txtProductCode = new JTextField();
		txtProductCode.setBounds(150, 15, 150, 25);
		txtProductCode.setEnabled(false);
		getContentPane().add(txtProductCode);

		lblErrorProductCode = new JLabel();
		lblErrorProductCode.setForeground(Color.RED);
		lblErrorProductCode.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorProductCode);
		
		lblProductName = new JLabel("Nama Produk");
		lblProductName.setBounds(25, 45, 150, 25);
		getContentPane().add(lblProductName);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(150,45, 150, 25);
		txtProductName.setEnabled(false);
		getContentPane().add(txtProductName);

		lblErrorProductName = new JLabel();
		lblErrorProductName.setForeground(Color.RED);
		lblErrorProductName.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorProductName);
		
		lblQtyReceived = new JLabel("Qty Penerimaan (m3)");
		lblQtyReceived.setBounds(25, 75, 150, 25);
		getContentPane().add(lblQtyReceived);
		
		txtQtyReceived = new JTextField();
		txtQtyReceived.setBounds(150,75, 150, 25);
		txtQtyReceived.setEnabled(false);
		getContentPane().add(txtQtyReceived);

		lblErrorQtyReceived = new JLabel();
		lblErrorQtyReceived.setForeground(Color.RED);
		lblErrorQtyReceived.setBounds(335, 75, 225, 25);
		getContentPane().add(lblErrorQtyReceived);
		
		lblPrice = new JLabel("<html>Harga Satuan<font color=\"red\">*</font></html>");
		lblPrice.setBounds(25, 105, 150, 25);
		getContentPane().add(lblPrice);
		
		txtPrice = new NumberField(20);
		txtPrice.setBounds(150, 105, 150, 25);
		getContentPane().add(txtPrice);

		lblErrorPrice = new JLabel();
		lblErrorPrice.setForeground(Color.RED);
		lblErrorPrice.setBounds(335, 105, 225, 25);
		getContentPane().add(lblErrorPrice);
		
		lblIdrPrice = new JLabel("Harga Satuan (IDR)");
		lblIdrPrice.setBounds(25, 135, 150, 25);
		getContentPane().add(lblIdrPrice);
		
		txtIdrPrice = new JTextField();
		txtIdrPrice.setBounds(150,135, 150, 25);
		txtIdrPrice.setEnabled(false);
		getContentPane().add(txtIdrPrice);

		lblErrorIdrPrice = new JLabel();
		lblErrorIdrPrice.setForeground(Color.RED);
		lblErrorIdrPrice.setBounds(335, 135, 225, 25);
		getContentPane().add(lblErrorIdrPrice);

		btnInsert = new JButton();
		btnInsert.setText("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}

				doInsert();
			}
		});
		btnInsert.setBounds(460, 165, 100, 25);
		getContentPane().add(btnInsert);
		
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					BigDecimal price = new BigDecimal(txtPrice.getText());
					BigDecimal currencyRate = new BigDecimal(invoiceBalkenCreatePanel.txtCurrencyRate.getText());
					
					BigDecimal idrPrice = new BigDecimal("0.00");
					idrPrice = price.multiply(currencyRate);
					String idrPriceString = idrPrice.toString();
					txtIdrPrice.setText(idrPriceString);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});

		txtProductCode.setText(invBalkenProduct.getProductCode());
		txtProductName.setText(invBalkenProduct.getProduct().getProductName());
		txtQtyReceived.setText(invBalkenProduct.getQtyReceive().toString());
		txtPrice.setText(invBalkenProduct.getPrice().toString());
		txtIdrPrice.setText(invBalkenProduct.getIdrPrice().toString());
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorPrice.setText("");
		lblErrorIdrPrice.setText("");
		
		
		if (txtPrice.getText() == null || txtPrice.getText().length() == 0) {
			lblErrorPrice.setText("Textbox Harga Satuan harus diisi.");
			isValid = false;
		}
		
		if (txtIdrPrice.getText() == null || txtIdrPrice.getText().length() == 0) {
			lblErrorIdrPrice.setText("Textbox Harga Satuan (IDR) harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		try {
			invBalkenProduct.setPrice(new BigDecimal(txtPrice.getText()));
			invBalkenProduct.setIdrPrice(new BigDecimal(txtIdrPrice.getText()));
			
			BigDecimal subtotal = new BigDecimal("0.00");
			subtotal = invBalkenProduct.getIdrPrice().multiply(invBalkenProduct.getQtyReceive());
			invBalkenProduct.setSubtotal(subtotal);
			invoiceBalkenCreatePanel.listOfInvBalkenProduct.set(index, invBalkenProduct);
			DialogBox.showInsert();
			
			closeDialog();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (invoiceBalkenCreatePanel != null)
			invoiceBalkenCreatePanel.refreshTableInvBalkenProduct();

		dispose();
	}
}
