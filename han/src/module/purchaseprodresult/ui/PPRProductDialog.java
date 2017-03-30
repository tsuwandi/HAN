package module.purchaseprodresult.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
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
import module.production.model.Line;
import module.purchaseprodresult.model.PPRProduct;
import module.sn.production.type.model.ProductionType;

public class PPRProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(PPRProductDialog.class);

	JPanel panel;

	JLabel lblProductionType;
	JLabel lblProduct;
	JLabel lblQty;
	JLabel lblUnitPrice;
	JLabel lblSubTotal;

	ComboBox<ProductionType> cbProductionType;
	ComboBox<Product> cbProduct;
	NumberField txtQty;
	NumberField txtUnitPrice;
	JTextField txtSubTotal;

	JLabel lblErrorProductionType;
	JLabel lblErrorProduct;
	JLabel lblErrorQty;
	JLabel lblErrorUnitPrice;
	JLabel lblErrorSubTotal;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private PPRProduct pprProduct;
	private PurchaseProdResultCreatePanel pprCreatePanel;
	private PurchaseProdResultEditPanel pprEditPanel;
	private PurchaseProdResultViewPanel pprViewPanel;
	
	List<ProductionType> listOfProductionType = null;
	List<Product> listOfProduct = null;

	private Integer index;
	

	public PPRProductDialog(boolean edit, PPRProduct pprProduct, PurchaseProdResultCreatePanel pprCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.pprProduct = pprProduct;
		this.pprCreatePanel = pprCreatePanel;
		this.index = index;
		init();
	}

	public PPRProductDialog(boolean edit, PPRProduct pprProduct, PurchaseProdResultEditPanel pprEditPanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.pprProduct = pprProduct;
		this.pprEditPanel = pprEditPanel;
		this.index = index;
		init();
	}
	
	public PPRProductDialog(boolean view, PPRProduct pprProduct, PurchaseProdResultViewPanel pprViewPanel,
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
		setBounds(100, 100, 630, 250);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblProductionType = new JLabel("<html>Tipe Hasil Produksi <font color=\"red\">*</font></html>");
		lblProductionType.setBounds(25, 15, 150, 25);
		getContentPane().add(lblProductionType);
		
		listOfProductionType = new ArrayList<ProductionType>();
		try {
			listOfProductionType = ServiceFactory.getPurchaseProductResultBL().getAllByProductionType();
			listOfProductionType.add(0, new ProductionType("-- Pilih Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		listOfProduct = new ArrayList<Product>();
		cbProduct = new ComboBox<Product>();
		
		cbProductionType = new ComboBox<ProductionType>();
		cbProductionType.setList(listOfProductionType);
		cbProductionType.setBounds(150, 15, 200, 25);
		cbProductionType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					listOfProduct = ServiceFactory.getPurchaseProductResultBL().getAllByProductionTypeId(cbProductionType.getDataIndex().getId());
					listOfProduct.add(0, new Product("-- Pilih Produk --"));
					cbProduct.setList(listOfProduct);
				} catch (Exception e1) {
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		getContentPane().add(cbProductionType);

		
		lblErrorProductionType = new JLabel();
		lblErrorProductionType.setForeground(Color.RED);
		lblErrorProductionType.setBounds(360, 15, 250, 25);
		getContentPane().add(lblErrorProductionType);
		
		lblProduct = new JLabel("<html>Produk <font color=\"red\">*</font></html>");
		lblProduct.setBounds(25, 45, 150, 25);
		getContentPane().add(lblProduct);
		listOfProduct.add(0, new Product("-- Pilih Produk --"));
		cbProduct.setList(listOfProduct);
		cbProduct.setBounds(150, 45, 200, 25);
		getContentPane().add(cbProduct);

		lblErrorProduct = new JLabel();
		lblErrorProduct.setForeground(Color.RED);
		lblErrorProduct.setBounds(360, 45, 250, 25);
		getContentPane().add(lblErrorProduct);

		lblQty = new JLabel("<html>Qty <font color=\"red\">*</font></html>");
		lblQty.setBounds(25, 75, 150, 25);
		getContentPane().add(lblQty);

		txtQty = new NumberField(20);
		txtQty.setBounds(150, 75, 200, 25);
		txtQty.setText("0");
		getContentPane().add(txtQty);

		lblErrorQty = new JLabel();
		lblErrorQty.setForeground(Color.RED);
		lblErrorQty.setBounds(360, 75, 250, 25);
		getContentPane().add(lblErrorQty);
		
		lblUnitPrice  = new JLabel("<html>Harga Satuan <font color=\"red\">*</font></html>");
		lblUnitPrice.setBounds(25, 105, 150, 25);
		getContentPane().add(lblUnitPrice); 
		
		txtUnitPrice = new NumberField(20);
		txtUnitPrice.setBounds(150, 105, 200, 25);
		txtUnitPrice.setText("0.00");
		getContentPane().add(txtUnitPrice);
		
		lblErrorUnitPrice = new JLabel();
		lblErrorUnitPrice.setForeground(Color.RED);
		lblErrorUnitPrice.setBounds(360, 105, 225, 25);
		getContentPane().add(lblErrorUnitPrice);
		
		lblSubTotal  = new JLabel("<html>Subtotal <font color=\"red\">*</font></html>");
		lblSubTotal.setBounds(25, 135, 150, 25);
		getContentPane().add(lblSubTotal); 
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(150, 135, 200, 25);
		txtSubTotal.setText("0.00");
		txtSubTotal.setEnabled(false);;
		getContentPane().add(txtSubTotal);
		
		lblErrorSubTotal = new JLabel();
		lblErrorSubTotal.setForeground(Color.RED);
		lblErrorSubTotal.setBounds(360, 135, 250, 25);
		getContentPane().add(lblErrorSubTotal);

		txtQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					String subtotal = calculateSubTotal(txtQty.getText(), txtUnitPrice.getText()).toString();
					txtSubTotal.setText(subtotal);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		
		txtUnitPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					String subtotal = calculateSubTotal(txtQty.getText(), txtUnitPrice.getText()).toString();
					txtSubTotal.setText(subtotal);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}

				doInsert();
			}
		});
		btnInsert.setBounds(480, 165, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtQty.setText(String.valueOf(pprProduct.getQty()));
			txtUnitPrice.setText(String.valueOf(pprProduct.getUnitPrice()));
			txtSubTotal.setText(String.valueOf(pprProduct.getSubTotal()));
			
			cbProductionType.setSelectedItem(pprProduct.getProduct().getProductionType());
			
			try {
				listOfProduct = ServiceFactory.getPurchaseProductResultBL().getAllByProductionTypeId(pprProduct.getProduct().getProductionTypeId());
				listOfProduct.add(0, new Product("-- Pilih Produk --"));
				cbProduct.setList(listOfProduct);
			} catch (Exception e1) {
				LOGGER.error(e1.getMessage());
				DialogBox.showErrorException();
			}
			
			cbProduct.setSelectedItem(pprProduct.getProduct().getProductName());
		}
		
		if(isView == true) {
			txtQty.setEnabled(false);
			txtUnitPrice.setEnabled(false);
			txtSubTotal.setEnabled(false);
			cbProduct.setEnabled(false);
			cbProductionType.setEnabled(false);
			btnInsert.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorProduct.setText("");
		lblErrorQty.setText("");
		
		if (cbProductionType.getSelectedItem() == null || cbProductionType.getSelectedIndex() == 0) {
			lblErrorProductionType.setText("Combobox Tipe Hasil Produksi harus dipilih.");
			isValid = false;
		}
		
		if (cbProduct.getSelectedItem() == null || cbProduct.getSelectedIndex() == 0) {
			lblErrorProduct.setText("Combobox Product harus dipilih.");
			isValid = false;
		}

		if (txtQty.getText() == null || txtQty.getText().length() == 0) {
			lblErrorQty.setText("Textbox Qty harus diisi.");
			isValid = false;
		}
		
		if (txtUnitPrice.getText() == null || txtUnitPrice.getText().length() == 0) {
			lblErrorUnitPrice.setText("Textbox Harga Satuan harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		pprProduct.setProductCode(cbProduct.getDataIndex().getProductCode());
		pprProduct.setQty(new BigDecimal(txtQty.getText()));
		pprProduct.setUnitPrice(new BigDecimal(txtUnitPrice.getText()));
		pprProduct.setSubTotal(new BigDecimal(txtSubTotal.getText()));
		
		Product product = new Product();
		product.setProductCode(cbProduct.getDataIndex().getProductCode());
		product.setProductName(cbProduct.getDataIndex().getProductName());
		product.setProductionType(cbProductionType.getDataIndex().getProductionType());
		product.setProductionTypeId(cbProductionType.getDataIndex().getId());
		pprProduct.setProduct(product);
		try {
			if (isEdit == false) {
				if (pprCreatePanel != null) {
					boolean isExists = false;
					
					for(PPRProduct p : pprCreatePanel.listOfPPRProduct) {
						if(pprProduct.getProductCode().equals(p.getProductCode())) {
//							BigDecimal qty = new BigDecimal(txtQty.getText());
//							p.setQty(p.getQty().add(qty));
//							
//							BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
//							p.setUnitPrice(p.getUnitPrice().add(unitPrice));
//							
//							BigDecimal subtotal = new BigDecimal(txtSubTotal.getText());
//							p.setSubTotal(p.getSubTotal().add(subtotal));
							
							isExists = true;
							break;
						}
					}
					if(isExists == false) {
						pprCreatePanel.listOfPPRProduct.add(pprProduct);
						DialogBox.showInsert();
					} else {
						DialogBox.showError("Produk sudah pernah diinput, jika ada perubahan qty dan harga silahkan diedit.");
					}
					
				} else if (pprEditPanel != null) {
					boolean isExists = false;
					
					for(PPRProduct p : pprEditPanel.listOfPPRProduct) {
						if(pprProduct.getProductCode().equals(p.getProductCode())) {
//							BigDecimal qty = new BigDecimal(txtQty.getText());
//							p.setQty(p.getQty().add(qty));
//							
//							BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
//							p.setUnitPrice(p.getUnitPrice().add(unitPrice));
//							
//							BigDecimal subtotal = new BigDecimal(txtSubTotal.getText());
//							p.setSubTotal(p.getSubTotal().add(subtotal));
							
							isExists = true;
							break;
						}
					}
					if(isExists == false) {
						pprEditPanel.listOfPPRProduct.add(pprProduct);
						DialogBox.showInsert();
					} else {
						DialogBox.showError("Produk sudah pernah diinput, jika ada perubahan qty dan harga silahkan diedit.");
					}
					
				}

				
			} else {
				if (pprCreatePanel != null) {
					pprCreatePanel.listOfPPRProduct.set(index, pprProduct);
				} else if (pprEditPanel != null) {
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
		if (pprCreatePanel != null)
			pprCreatePanel.refreshTablePPRProduct();
		 else if (pprEditPanel != null)
			 pprEditPanel.refreshTablePPRProduct();

		dispose();
	}
	
	private BigDecimal calculateSubTotal(String unitPrice, String qty) {
		if("".equals(unitPrice))
			unitPrice = "0";
		if("".equals(qty))
			qty = "0";
		
		if("".equals(unitPrice) && "".equals(qty))
			return new BigDecimal("0.00");
		else {
			BigDecimal bUnitPrice = new BigDecimal(unitPrice);
			BigDecimal bQty = new BigDecimal(qty);
			return bUnitPrice.multiply(bQty);
		}
		
	}
}
