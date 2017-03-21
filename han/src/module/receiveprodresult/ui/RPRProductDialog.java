package module.receiveprodresult.ui;

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

import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.product.model.Product;
import module.purchaseprodresult.model.PPRProduct;
import module.receiveprodresult.model.RPRProduct;
import module.sn.production.type.model.ProductionType;

import org.apache.log4j.Logger;

import controller.ServiceFactory;

public class RPRProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(RPRProductDialog.class);

	JPanel panel;

	JLabel lblProductionType;
	JLabel lblProduct;
	JLabel lblQty;

	ComboBox<ProductionType> cbProductionType;
	ComboBox<Product> cbProduct;
	NumberField txtQty;

	JLabel lblErrorProductionType;
	JLabel lblErrorProduct;
	JLabel lblErrorQty;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private RPRProduct rprProduct;
	private ReceiveProdResultCreatePanel rprCreatePanel;
	//private ReceiveProdResultEditPanel rprEditPanel;
	private ReceiveProdResultViewPanel rprViewPanel;
	
	List<ProductionType> listOfProductionType = null;
	List<Product> listOfProduct = null;

	private Integer index;
	

	public RPRProductDialog(boolean edit, RPRProduct rprProduct, ReceiveProdResultCreatePanel rprCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.rprProduct = rprProduct;
		this.rprCreatePanel = rprCreatePanel;
		this.index = index;
		init();
	}

//	public RPRProductDialog(boolean edit, RPRProduct rprProduct, ReceiveProdResultEditPanel rprEditPanel,
//			Integer index) {
//		this.isEdit = edit;
//		this.isView = false;
//		this.rprProduct = rprProduct;
//		this.rprEditPanel = rprEditPanel;
//		this.index = index;
//		init();
//	}
	
	public RPRProductDialog(boolean view, RPRProduct rprProduct, ReceiveProdResultViewPanel rprViewPanel,
			Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.rprProduct = rprProduct;
		this.rprViewPanel = rprViewPanel;
		this.index = index;
		init();
	}

	public void init() {

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 265);
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
		cbProductionType.setBounds(150, 15, 150, 25);
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
		lblErrorProductionType.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorProductionType);
		
		lblProduct = new JLabel("<html>Produk <font color=\"red\">*</font></html>");
		lblProduct.setBounds(25, 45, 150, 25);
		getContentPane().add(lblProduct);
		listOfProduct.add(0, new Product("-- Pilih Produk --"));
		cbProduct.setList(listOfProduct);
		cbProduct.setBounds(150, 45, 150, 25);
		getContentPane().add(cbProduct);

		lblErrorProduct = new JLabel();
		lblErrorProduct.setForeground(Color.RED);
		lblErrorProduct.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorProduct);

		lblQty = new JLabel("<html>Qty <font color=\"red\">*</font></html>");
		lblQty.setBounds(25, 75, 150, 25);
		getContentPane().add(lblQty);

		txtQty = new NumberField(20);
		txtQty.setBounds(150, 75, 150, 25);
		txtQty.setText("0");
		getContentPane().add(txtQty);

		lblErrorQty = new JLabel();
		lblErrorQty.setForeground(Color.RED);
		lblErrorQty.setBounds(335, 75, 225, 25);
		getContentPane().add(lblErrorQty);
		
		btnInsert = new JButton();
		if(isEdit) {
			btnInsert.setText("Ubah");
		} else {
			btnInsert.setText("Insert");
		}
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

		if (isEdit == true) {
			txtQty.setText(String.valueOf(rprProduct.getQty()));
			cbProductionType.setSelectedItem(rprProduct.getProduct().getProductionType());
			
			try {
				listOfProduct = ServiceFactory.getPurchaseProductResultBL().getAllByProductionTypeId(rprProduct.getProduct().getProductionTypeId());
				listOfProduct.add(0, new Product("-- Pilih Produk --"));
				cbProduct.setList(listOfProduct);
			} catch (Exception e1) {
				LOGGER.error(e1.getMessage());
				DialogBox.showErrorException();
			}
			
			cbProduct.setSelectedItem(rprProduct.getProduct().getProductName());
		}
		
		if(isView == true) {
			txtQty.setEnabled(false);
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

		return isValid;
	}

	protected void doInsert() {
		rprProduct.setProductCode(cbProduct.getDataIndex().getProductCode());
		rprProduct.setQty(new BigDecimal(txtQty.getText()));
		
		Product product = new Product();
		product.setProductCode(cbProduct.getDataIndex().getProductCode());
		product.setProductName(cbProduct.getDataIndex().getProductName());
		product.setProductionType(cbProductionType.getDataIndex().getProductionType());
		product.setProductionTypeId(cbProductionType.getDataIndex().getId());
		rprProduct.setProduct(product);
		try {
			if (isEdit == false) {
				if (rprCreatePanel != null) {
					boolean isExists = false;
					
					for(RPRProduct p : rprCreatePanel.listOfRPRProduct) {
						if(rprProduct.getProductCode().equals(p.getProductCode())) {
							BigDecimal qty = new BigDecimal(txtQty.getText());
							p.setQty(p.getQty().add(qty));
							isExists = true;
							break;
						}
					}
					if(isExists == false) {
						rprCreatePanel.listOfRPRProduct.add(rprProduct);
					}
					
				} 
//				else if (rprEditPanel != null) {
//					boolean isExists = false;
//					
//					for(RPRProduct p : rprEditPanel.listOfRPRProduct) {
//						if(rprProduct.getProductCode().equals(p.getProductCode())) {
//							BigDecimal qty = new BigDecimal(txtQty.getText());
//							p.setQty(p.getQty().add(qty));
//							isExists = true;
//							break;
//						}
//					}
//					if(isExists == false) {
//						rprEditPanel.listOfRPRProduct.add(rprProduct);
//					}
//				}

				DialogBox.showInsert();
			} else {
				if (rprCreatePanel != null) {
					rprCreatePanel.listOfRPRProduct.set(index, rprProduct);
				} 
//				else if (rprEditPanel != null) {
//					rprEditPanel.listOfRPRProduct.set(index, rprProduct);
//				}

				DialogBox.showInsert();
			}

			closeDialog();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (rprCreatePanel != null)
			rprCreatePanel.refreshTableRPRProduct();
//		 else if (rprEditPanel != null)
//			 rprEditPanel.refreshTableRPRProduct();

		dispose();
	}
	
	private BigDecimal calculateSubTotal(String unitPrice, String qty) {
		if("".equals(unitPrice) && "".equals(qty))
			return new BigDecimal("0.00");
		else {
			BigDecimal bUnitPrice = new BigDecimal(unitPrice);
			BigDecimal bQty = new BigDecimal(qty);
			return bUnitPrice.multiply(bQty);
		}
		
	}
}
