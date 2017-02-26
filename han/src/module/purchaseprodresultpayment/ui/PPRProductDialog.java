package module.purchaseprodresultpayment.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.product.model.Product;
import module.purchaseprodresult.model.PPRProduct;

public class PPRProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(PPRProductDialog.class);

	JPanel panel;

	JLabel lblProduct;
	JLabel lblQty;
	JLabel lblUnitPrice;
	JLabel lblSubTotal;

	ComboBox<Product> cbProduct;
	JTextField txtQty;
	JTextField txtUnitPrice;
	JTextField txtSubTotal;

	JLabel lblErrorProduct;
	JLabel lblErrorQty;
	JLabel lblErrorUnitPrice;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private PPRProduct pprProduct;
	private PurchaseProdResultPaymentEditPanel pprEditPanel;
	private PurchaseProdResultPaymentViewPanel pprViewPanel;
	List<Product> listOfProduct = null;

	private Integer index;

	public PPRProductDialog(boolean edit, PPRProduct pprProduct, PurchaseProdResultPaymentEditPanel pprEditPanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.pprProduct = pprProduct;
		this.pprEditPanel = pprEditPanel;
		this.index = index;
		init();
	}
	
	public PPRProductDialog(boolean view, PPRProduct pprProduct, PurchaseProdResultPaymentViewPanel pprViewPanel,
			Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.pprProduct = pprProduct;
		this.pprViewPanel = pprViewPanel;
		this.index = index;
		init();
	}

	public void init() {

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 225);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblProduct = new JLabel("<html>Produk <font color=\"red\">*</font></html>");
		lblProduct.setBounds(25, 15, 150, 25);
		getContentPane().add(lblProduct);

		listOfProduct = new ArrayList<Product>();
		try {
			listOfProduct = ServiceFactory.getPurchaseProductResultPaymentBL().getAllByProductCode(AppConstants.PRODUCT_CODE_NORMAL_A_TYPE_9, AppConstants.PRODUCT_CODE_NORMAL_B_TYPE_9);
			listOfProduct.add(0, new Product("-- Pilih Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbProduct = new ComboBox<Product>();
		cbProduct.setList(listOfProduct);
		cbProduct.setBounds(150, 15, 150, 25);
		getContentPane().add(cbProduct);

		lblErrorProduct = new JLabel();
		lblErrorProduct.setForeground(Color.RED);
		lblErrorProduct.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorProduct);

		lblQty = new JLabel("<html>Qty <font color=\"red\">*</font></html>");
		lblQty.setBounds(25, 45, 150, 25);
		getContentPane().add(lblQty);

		txtQty = new NumberField(5);
		txtQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
//				txtSubTotal.setText(String.valueOf(getSubTotal()));
//				txtSubTotal.updateUI();
			}
		});
		txtQty.setBounds(150, 45, 150, 25);
		txtQty.setEnabled(false);
		getContentPane().add(txtQty);

		lblErrorQty = new JLabel();
		lblErrorQty.setForeground(Color.RED);
		lblErrorQty.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorQty);

		lblUnitPrice = new JLabel("<html>Harga Satuan <font color=\"red\">*</font></html>");
		lblUnitPrice.setBounds(25, 75, 150, 25);
		getContentPane().add(lblUnitPrice);

		txtUnitPrice = new NumberField(10);
		txtUnitPrice.setBounds(150, 75, 150, 25);
		txtUnitPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
//				txtSubTotal.setText(String.valueOf(getSubTotal()));
//				txtSubTotal.updateUI();
			}
		});
		getContentPane().add(txtUnitPrice);

		lblErrorUnitPrice = new JLabel();
		lblErrorUnitPrice.setForeground(Color.RED);
		lblErrorUnitPrice.setBounds(335, 75, 225, 25);
		getContentPane().add(lblErrorUnitPrice);

//		lblSubTotal = new JLabel("Sub Total");
//		lblSubTotal.setBounds(25, 105, 150, 25);
//		getContentPane().add(lblSubTotal);

//		txtSubTotal = new NumberField(10);
//		txtSubTotal.setEnabled(false);
//		txtSubTotal.setBounds(150, 105, 150, 25);
//		getContentPane().add(txtSubTotal);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}

				doInsert();
			}
		});
		btnInsert.setBounds(460, 135, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtQty.setText(String.valueOf(pprProduct.getQty()));
			txtUnitPrice.setText(String.valueOf(pprProduct.getUnitPrice()));
			cbProduct.setSelectedItem(pprProduct.getProduct().getProductName());
			//txtSubTotal.setText(String.valueOf(this.getSubTotal()));
		}
		
		if(isView == true) {
			txtQty.setEnabled(false);
			txtUnitPrice.setEnabled(false);
			cbProduct.setEnabled(false);
			//txtSubTotal.setEnabled(false);
			btnInsert.setEnabled(false);
		}
	}

	public double getSubTotal() {
		if ("".equals(txtQty.getText()))
			return 0;

		if ("".equals(txtUnitPrice.getText()))
			return 0;

		int qty = Integer.valueOf(txtQty.getText());
		double unitPrice = Double.valueOf(txtUnitPrice.getText());

		return qty * unitPrice;
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorProduct.setText("");
		lblErrorQty.setText("");
		lblErrorUnitPrice.setText("");

		if (cbProduct.getSelectedItem() == null || cbProduct.getSelectedIndex() == 0) {
			lblErrorProduct.setText("Combobox Product harus dipilih.");
			isValid = false;
		}

		if (txtQty.getText() == null || txtQty.getText().length() == 0) {
			lblErrorQty.setText("Textbox Qty harus diisi.");
			isValid = false;
		}

		if (txtUnitPrice.getText() == null || txtUnitPrice.getText().length() == 0) {
			lblErrorQty.setText("Textbox Harga Satuan harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {

		// suppVehicle = new SuppVehicle();
		pprProduct.setProductCode(cbProduct.getDataIndex().getProductCode());
		pprProduct.setQty(Integer.valueOf(txtQty.getText()));
		pprProduct.setUnitPrice(Double.valueOf(txtUnitPrice.getText()));
		pprProduct.setSubTotal(Double.valueOf(getSubTotal()));
		// add object vehicle type
		Product product = new Product();
		product.setProductCode(cbProduct.getDataIndex().getProductCode());
		product.setProductName(cbProduct.getDataIndex().getProductName());
		pprProduct.setProduct(product);
		try {
			if (isEdit == false) {
				if (pprEditPanel != null) {
					pprEditPanel.listOfPPRProduct.add(pprProduct);
				}

				DialogBox.showInsert();
			} else {
				if (pprEditPanel != null) {
					pprEditPanel.listOfPPRProduct.set(index, pprProduct);
				}

				DialogBox.showInsert();
			}

			closeDialog();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (pprEditPanel != null) {
			 pprEditPanel.refreshTablePPRProduct();
		}

		dispose();
	}
}
