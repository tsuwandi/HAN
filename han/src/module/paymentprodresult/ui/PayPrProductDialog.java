package module.paymentprodresult.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import module.paymentprodresult.model.PayPrProduct;
import module.product.model.Product;
import module.sn.production.type.model.ProductionType;

import org.apache.log4j.Logger;

public class PayPrProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(PayPrProductDialog.class);

	JPanel panel;

	JLabel lblProductCode;
	JLabel lblProductName;
	JLabel lblPrice;
	JLabel lblIdrPrice;

	JTextField txtProductCode;
	JTextField txtProductName;
	NumberField txtPrice;
	NumberField txtIdrPrice;

	JLabel lblErrorProductCode;
	JLabel lblErrorProductName;
	JLabel lblErrorPrice;
	JLabel lblErrorIdrPrice;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private PayPrProduct payPrProduct;
	private PaymentProdResultCreatePanel pprCreatePanel;
	
	List<ProductionType> listOfProductionType = null;
	List<Product> listOfProduct = null;

	private Integer index;
	

	public PayPrProductDialog(boolean edit, PayPrProduct payPrProduct, PaymentProdResultCreatePanel pprCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.payPrProduct = payPrProduct;
		this.pprCreatePanel = pprCreatePanel;
		this.index = index;
		init();
	}

	public void init() {

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 265);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblProductCode = new JLabel("<html>Kode Produk <font color=\"red\">*</font></html>");
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
		
		lblProductName = new JLabel("<html>Nama Produk <font color=\"red\">*</font></html>");
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
		
		lblPrice = new JLabel("<html>Harga Satuan<font color=\"red\">*</font></html>");
		lblPrice.setBounds(25, 75, 150, 25);
		getContentPane().add(lblPrice);
		
		txtPrice = new NumberField(20);
		txtPrice.setBounds(150,75, 150, 25);
		getContentPane().add(txtPrice);

		lblErrorPrice = new JLabel();
		lblErrorPrice.setForeground(Color.RED);
		lblErrorPrice.setBounds(335, 75, 225, 25);
		getContentPane().add(lblErrorPrice);
		
		lblIdrPrice = new JLabel("<html>Harga Satuan (IDR)<font color=\"red\">*</font></html>");
		lblIdrPrice.setBounds(25, 105, 150, 25);
		getContentPane().add(lblIdrPrice);
		
		txtIdrPrice = new NumberField(20);
		txtIdrPrice.setBounds(150,105, 150, 25);
		getContentPane().add(txtIdrPrice);

		lblErrorIdrPrice = new JLabel();
		lblErrorIdrPrice.setForeground(Color.RED);
		lblErrorIdrPrice.setBounds(335, 105, 225, 25);
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

		txtProductCode.setText(payPrProduct.getProductCode());
		txtProductName.setText(payPrProduct.getProduct().getProductName());
		
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
			payPrProduct.setPrice(new BigDecimal(txtPrice.getText()));
			payPrProduct.setIdrPrice(new BigDecimal(txtIdrPrice.getText()));
			
			BigDecimal subtotal = new BigDecimal("0.00");
			subtotal = payPrProduct.getIdrPrice().multiply(payPrProduct.getQtyReceive());
			payPrProduct.setSubtotal(subtotal);
			pprCreatePanel.listOfPayPrProduct.set(index, payPrProduct);
			DialogBox.showInsert();
			
			closeDialog();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (pprCreatePanel != null)
			pprCreatePanel.refreshTablePayPrProduct();

		dispose();
	}
}
