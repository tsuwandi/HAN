package module.purchaseprodsupp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.productsupportinggood.model.ProductSupp;
import module.purchaseprodsupp.model.PPSProduct;
import module.purchaseprodsupp.model.PurchaseProdSupp;
import module.purchaseprodsupp.ui.PurchaseProdSuppCreatePanel.PPSProductTableModel;
import module.sn.productcategory.model.ProductCategory;
import module.sn.uom.model.Uom;
import module.supplier.model.SuppAddress;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class PurchaseProdSuppEditPanel extends JPanel implements Bridging {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6884532933327487366L;

	@Override
	public void invokeObjects(Object... objects) {
		this.purchaseProductSupp = (PurchaseProdSupp) objects[0];
		
		loadData(purchaseProductSupp.getId());
	}
	
	protected void loadData(Integer id) {
		try {
			purchaseProductSupp = ServiceFactory.getPurchaseProductSuppBL().getPPSById(id);
			
			if (purchaseProductSupp != null) {
				txtPurchaseProductSuppCode.setText(purchaseProductSupp.getPpsCode());
				
				txtSupplier.setText(purchaseProductSupp.getSupplier().getSuppName());
				supplierCode = purchaseProductSupp.getSupplier().getSuppCode();
				
				txtCostCenter.setText(purchaseProductSupp.getCostCenter().getCostCenter());
				costCenterId = purchaseProductSupp.getCostCenter().getId();
				
				dcPurchaseDate.setDate(purchaseProductSupp.getPurchaseDate());
				dcDeliveryDate.setDate(purchaseProductSupp.getDeliveryDate());
				
				txtNote.setText(purchaseProductSupp.getNote());
				txtTotal.setText(String.format("%.2f", purchaseProductSupp.getTotal()));
				
				String tax = String.format("%.2f",purchaseProductSupp.getTax());
				//txtTax = new NumberField(10);

				txtTax.setText(tax);
				txtGrandTotal.setText(String.format("%.2f",purchaseProductSupp.getGrandTotal()));
				
				listOfPPSProduct = ServiceFactory.getPurchaseProductSuppBL().getPPSProductByPPSCode(purchaseProductSupp.getPpsCode());
				
				refreshTablePPSProduct();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	private static final Logger LOGGER = Logger
			.getLogger(PurchaseProdSuppEditPanel.class);

	JLabel lblPurchaseProductSuppCode;
	JLabel lblSupplier;
	JLabel lblCostCenter;
	JLabel lblPurchaseDate;
	JLabel lblDeliveryDate;
	JLabel lblNote;

	JButton btnInsertPPSProduct;
	JButton btnDeletePPSProduct;

	JButton btnInsertPPSNote;
	JButton btnDeletePPSNote;
	JButton btnCancel;
	JButton btnSave;
	
	JButton btnSearchSupplier;
	JButton btnSearchCostCenter;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtPurchaseProductSuppCode;
	JTextField txtSupplier;
	JTextField txtCostCenter;
	JDateChooser dcPurchaseDate;
	JDateChooser dcDeliveryDate;
	JTextArea txtNote;

	JLabel lblErrorPurchaseProductSuppCode;
	JLabel lblErrorSupplier;
	JLabel lblErrorCostCenter;
	JLabel lblErrorPurchaseDate;
	JLabel lblErrorDeliveryDate;

	PurchaseProdSupp purchaseProductSupp;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<Supplier> listOfSupplier = null;

	List<PPSProduct> listOfPPSProduct = null;
	List<PPSProduct> listOfDeletedPPSProduct = new ArrayList<PPSProduct>();
	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePPSProduct;
	JTable tblPPSProduct;

	PPSProductTableModel pprProductTableModel = null;

	PurchaseProdSuppEditPanel pprEditPanel;

	JLabel lblPPSProduct;
	
	private String supplierCode;
	private Integer costCenterId;
	private String productCode;
	private Integer productUomId;

	public PurchaseProdSuppEditPanel() {
		purchaseProductSupp = new PurchaseProdSupp();

		pprEditPanel = this;

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 800));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Barang Pendukung");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		addPOBarangPendukung();
		
		addProductSupp();
		
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
		btnSave.setBounds(925, 690, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.purchaseprodsupp.ui.PurchaseProdSuppListPanel");
				}
			}
		});
		btnCancel.setBounds(50, 690, 100, 25);
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
	
	public void addPOBarangPendukung() {
		lblHeader = new JLabel("Buat Baru PO Barang Pendukung");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblPurchaseProductSuppCode = new JLabel(
				"<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductSuppCode.setBounds(50, 80, 150, 25);
		panel.add(lblPurchaseProductSuppCode);

		txtPurchaseProductSuppCode = new JTextField();
		txtPurchaseProductSuppCode.setBounds(220, 80, 150, 25);
		txtPurchaseProductSuppCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtPurchaseProductSuppCode.getDocument())
				.setDocumentFilter(filter);
		txtPurchaseProductSuppCode.setEnabled(false);
		panel.add(txtPurchaseProductSuppCode);
		
		lblNote = new JLabel("Catatan");
		lblNote.setBounds(585, 80, 150, 25);
		panel.add(lblNote);
		
		txtNote = new JTextArea();
		txtNote.setBounds(755, 80, 200, 100);
		txtNote.setDocument(new JTextFieldLimit(200));
		((AbstractDocument) txtNote.getDocument())
				.setDocumentFilter(filter);
		panel.add(txtNote);

		lblErrorPurchaseProductSuppCode = new JLabel();
		lblErrorPurchaseProductSuppCode.setForeground(Color.RED);
		lblErrorPurchaseProductSuppCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorPurchaseProductSuppCode);
		
		lblPurchaseDate = new JLabel(
				"<html>Tanggal Pengajuan <font color=\"red\">*</font></html>");
		lblPurchaseDate.setBounds(50, 110, 150, 25);
		panel.add(lblPurchaseDate);

		dcPurchaseDate = new JDateChooser(new Date());
		dcPurchaseDate.setBounds(220, 110, 150, 25);
		dcPurchaseDate.setDateFormatString("dd-MM-yyyy");
		dcPurchaseDate.setEnabled(false);
//		dcPurchaseDate.getDateEditor().addPropertyChangeListener(
//				new PropertyChangeListener() {
//					@Override
//					public void propertyChange(PropertyChangeEvent e) {
//						if ("date".equals(e.getPropertyName())) {
//							//makeCodeNumber(dcPurchaseDate.getDate());
//						}
//					}
//				});
		//makeCodeNumber(dcPurchaseDate.getDate());
		panel.add(dcPurchaseDate);

		lblErrorPurchaseDate = new JLabel();
		lblErrorPurchaseDate.setForeground(Color.RED);
		lblErrorPurchaseDate.setBounds(425, 110, 225, 25);
		panel.add(lblErrorPurchaseDate);
		
		lblDeliveryDate = new JLabel(
				"<html>Tanggal Pengiriman <font color=\"red\">*</font></html>");
		lblDeliveryDate.setBounds(50, 140, 150, 25);
		panel.add(lblDeliveryDate);

		dcDeliveryDate = new JDateChooser(new Date());
		dcDeliveryDate.setBounds(220, 140, 150, 25);
		dcDeliveryDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcDeliveryDate);

		lblErrorDeliveryDate = new JLabel();
		lblErrorDeliveryDate.setForeground(Color.RED);
		lblErrorDeliveryDate.setBounds(425, 140, 225, 25);
		panel.add(lblErrorDeliveryDate);
		

		lblSupplier = new JLabel(
				"<html>Supplier <font color=\"red\">*</font></html>");
		lblSupplier.setBounds(50, 170, 150, 25);
		panel.add(lblSupplier);

		listOfSupplier = new ArrayList<Supplier>();
		try {
			listOfSupplier = ServiceFactory.getPurchaseProductSuppBL()
					.getAllSupplierBySuppTypeId(
							AppConstants.SUPP_TYPE_ID_BARECORE);
			listOfSupplier.add(0, new Supplier("-- Pilih Supplier --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		txtSupplier = new JTextField();
		txtSupplier.setBounds(220, 170, 150, 25);
		txtSupplier.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtSupplier.getDocument())
				.setDocumentFilter(filter);
		txtSupplier.setEnabled(false);
		panel.add(txtSupplier);

		lblErrorSupplier = new JLabel("");
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(525, 170, 225, 25);
		panel.add(lblErrorSupplier);

		btnSearchSupplier = new JButton("Cari Supplier");
		btnSearchSupplier.setBounds(385, 170, 125, 25);
		btnSearchSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSupplierDialog(pprEditPanel);
			}
		});
		panel.add(btnSearchSupplier);
		
		lblCostCenter = new JLabel(
				"<html>Cost Center <font color=\"red\">*</font></html>");
		lblCostCenter.setBounds(50, 200, 150, 25);
		panel.add(lblCostCenter);
		
		txtCostCenter = new JTextField();
		txtCostCenter.setBounds(220, 200, 150, 25);
		txtCostCenter.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtCostCenter.getDocument())
				.setDocumentFilter(filter);
		txtCostCenter.setEnabled(false);
		panel.add(txtCostCenter);

		lblErrorCostCenter = new JLabel("");
		lblErrorCostCenter.setForeground(Color.RED);
		lblErrorCostCenter.setBounds(525, 200, 225, 25);
		panel.add(lblErrorCostCenter);

		btnSearchCostCenter = new JButton("Cari Cost Center");
		btnSearchCostCenter.setBounds(385, 200, 125, 25);
		btnSearchCostCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCostCenterDialog(pprEditPanel);
			}
		});
		panel.add(btnSearchCostCenter);
	}
	
	JLabel lblHeaderAddProduct;
	JLabel lblProductCategory;
	JLabel lblProduct;
	JLabel lblQty;
	JLabel lblUnitPrice;
	JLabel lblUOM;
	JLabel lblTotal;
	JLabel lblTax;
	JLabel lblGrandTotal;

	ComboBox<ProductCategory> cbProductCategory;
	JTextField txtProduct;
	NumberField txtQty;
	NumberField txtUnitPrice;
	JTextField txtUOM;
	JTextField txtTotal;
	NumberField txtTax;
	JTextField txtGrandTotal;

	JLabel lblErrorProductCategory;
	JLabel lblErrorProduct;
	JLabel lblErrorQty;
	JLabel lblErrorUnitPrice;
	JLabel lblErrorUOM;
	
	List<ProductCategory> listOfProductCategory = null;
	private int productCategoryId;
	List<ProductSupp> listOfProduct = null;
	
	JButton btnSearchProduct;
	
	public void addProductSupp() {
		lblHeaderAddProduct = new JLabel("Barang Pendukung");
		lblHeaderAddProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeaderAddProduct.setBounds(50, 230, 320, 25);
		panel.add(lblHeaderAddProduct);
		
		lblProductCategory = new JLabel(
				"<html>Kategori Produk <font color=\"red\">*</font></html>");
		lblProductCategory.setBounds(50, 260, 100, 25);
		panel.add(lblProductCategory);
		
		listOfProductCategory = new ArrayList<ProductCategory>();
		try {
			listOfProductCategory = ServiceFactory.getPurchaseProductSuppBL().getAllProductCategory(AppConstants.PRODUCT_CATEGORY_TYPE_ID_PRODUCT_SUPP);
			listOfProductCategory.add(0, new ProductCategory("-- Pilih Produk Kategori --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		listOfProduct = new ArrayList<ProductSupp>();
		
		cbProductCategory = new ComboBox<ProductCategory>();
		cbProductCategory.setList(listOfProductCategory);
		cbProductCategory.setBounds(220, 260, 150, 25);
		cbProductCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					txtProduct.setText("");
					productCode = "";
					txtUOM.setText("");
					int productCatId = cbProductCategory.getDataIndex().getId();
					if(productCatId == 0 || cbProductCategory == null) {
						productCategoryId = 0;
					} else {
						productCategoryId = cbProductCategory.getDataIndex().getId();
					}
				} catch (Exception e1) {
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		panel.add(cbProductCategory);

		lblErrorProductCategory = new JLabel("");
		lblErrorProductCategory.setForeground(Color.RED);
		lblErrorProductCategory.setBounds(525, 260, 225, 25);
		panel.add(lblErrorProductCategory);

		lblProduct = new JLabel(
				"<html>Produk <font color=\"red\">*</font></html>");
		lblProduct.setBounds(50, 290, 100, 25);
		panel.add(lblProduct);
		
		txtProduct = new JTextField();
		txtProduct.setBounds(220, 290, 150, 25);
		txtProduct.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtProduct.getDocument())
				.setDocumentFilter(filter);
		txtProduct.setEnabled(false);
		panel.add(txtProduct);
		
		lblErrorProduct = new JLabel("");
		lblErrorProduct.setForeground(Color.RED);
		lblErrorProduct.setBounds(525, 290, 225, 25);
		panel.add(lblErrorProduct);
		
		btnSearchProduct = new JButton("Cari Barang");
		btnSearchProduct.setBounds(385, 290, 125, 25);
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showProductDialog(pprEditPanel);
			}
		});
		panel.add(btnSearchProduct);
		
		lblUOM = new JLabel("<html>Satuan <font color=\"red\"></font></html>");
		lblUOM.setBounds(50, 320, 100, 25);
		panel.add(lblUOM);

		txtUOM = new JTextField();
		txtUOM.setBounds(220, 320, 150, 25);
		txtUOM.setEnabled(false);
		panel.add(txtUOM);
		
		lblQty = new JLabel("<html>Qty <font color=\"red\">*</font></html>");
		lblQty.setBounds(50, 350, 100, 25);
		panel.add(lblQty);

		txtQty = new NumberField(20);
		txtQty.setBounds(220, 350, 150, 25);
		txtQty.setText("0");
		panel.add(txtQty);

		lblErrorQty = new JLabel();
		lblErrorQty.setForeground(Color.RED);
		lblErrorQty.setBounds(525, 350, 225, 25);
		panel.add(lblErrorQty);
		
		lblUnitPrice  = new JLabel("<html>Harga Satuan (Net) <font color=\"red\">*</font></html>");
		lblUnitPrice.setBounds(50, 380, 120, 25);
		panel.add(lblUnitPrice); 
		
		txtUnitPrice = new NumberField(20);
		txtUnitPrice.setBounds(220, 380, 150, 25);
		txtUnitPrice.setText("0.00");
		panel.add(txtUnitPrice);
		
		lblErrorUnitPrice = new JLabel("");
		lblErrorUnitPrice.setForeground(Color.RED);
		lblErrorUnitPrice.setBounds(525, 380, 225, 25);
		panel.add(lblErrorUnitPrice);
		

		btnInsertPPSProduct = new JButton("Tambah");
		btnInsertPPSProduct.setBounds(220, 410, 100, 25);
		btnInsertPPSProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidateProductSupp() == false) {
					return;
				}
				doAddProductSupp();
			}
		});
		panel.add(btnInsertPPSProduct);

		scrollPanePPSProduct = new JScrollPane();
		scrollPanePPSProduct.setBounds(50, 440, 975, 150);
		panel.add(scrollPanePPSProduct);

		listOfPPSProduct = new ArrayList<PPSProduct>();
		pprProductTableModel = new PPSProductTableModel(listOfPPSProduct);
		tblPPSProduct = new JTable(pprProductTableModel);
		tblPPSProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPPSProduct.setFocusable(false);
		tblPPSProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 8) {
						deletePPSProduct(listOfPPSProduct, row);
					}
				}
			}
		});
		scrollPanePPSProduct.setViewportView(tblPPSProduct);

		lblTotal = new JLabel("<html>Total Pembelian<font color=\"red\"></font></html>");
		lblTotal.setBounds(770, 600, 150, 25);
		panel.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(875, 600, 150, 25);
		txtTotal.setText("0.00");
		txtTotal.setEnabled(false);
		panel.add(txtTotal);
		
		lblTax = new JLabel("<html>Tax<font color=\"red\"></font></html>");
		lblTax.setBounds(770, 630, 150, 25);
		panel.add(lblTax);
		
		txtTax = new NumberField(10);
		txtTax.setText("0.00");
		txtTax.setBounds(875, 630, 150, 25);
		txtTax.setMaxLength(10); //Set maximum length             
		txtTax.setPrecision(5); //Set precision (1 in your case)     
		txtTax.setFormat(3);
		txtTax.setAllowNegative(false); //Set false to disable negatives
		panel.add(txtTax);
		
		txtTax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try {
					String grandTotal = calculateGrandTotal(txtTotal.getText(), txtTax.getText()).toString();
					
					txtGrandTotal.setText(grandTotal);
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		
		lblGrandTotal = new JLabel("<html>Grand Total<font color=\"red\"></font></html>");
		lblGrandTotal.setBounds(770, 660, 150, 25);
		panel.add(lblGrandTotal);

		txtGrandTotal = new JTextField();
		txtGrandTotal.setBounds(875, 660, 150, 25);
		txtGrandTotal.setText("0.00");
		txtGrandTotal.setEnabled(false);
		panel.add(txtGrandTotal);
		
	}
	


	private void deletePPSProduct(List<PPSProduct> listOfPPSProduct, int row) {
		if(listOfPPSProduct.size() > 0) {
			listOfDeletedPPSProduct.add(listOfPPSProduct.get(row));
			listOfPPSProduct.remove(row);
		}
		
		String totalPurchase = calculateTotalPurchase(listOfPPSProduct).toString();
		
		txtTotal.setText(totalPurchase);
		
		String grandTotal = calculateGrandTotal(txtTotal.getText(), txtTax.getText()).toString();
		
		txtGrandTotal.setText(grandTotal);
		
		refreshTablePPSProduct();
		
		
	}
	
	protected boolean doValidateProductSupp() {
		boolean isValid = true;
		lblErrorProduct.setText("");
		lblErrorUnitPrice.setText("");
		lblErrorQty.setText("");
		lblErrorProductCategory.setText("");
		
		if (txtProduct.getText() == null || txtProduct.getText().length() == 0) {
			lblErrorProduct.setText("Textbox Produk harus dipilih.");
			isValid = false;
		}
		
		if (cbProductCategory.getSelectedItem() == null || cbProductCategory.getSelectedIndex() == 0) {
			lblErrorProductCategory.setText("Combobox Kategori Produk harus dipilih.");
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
		
		for(PPSProduct ppsp : listOfPPSProduct) {
			if(productCode.equals(ppsp.getProductSupp().getProductCode()))
			{
				lblErrorProduct.setText("Produk sudah dipilih.");
				isValid = false;
			}
		}

		return isValid;
	}
	
	private void doAddProductSupp() {
		ProductSupp productSupp = new ProductSupp();
		productSupp.setProductCode(productCode);
		productSupp.setProductName(txtProduct.getText());
		productSupp.setProductCategoryId(productCategoryId);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(productCategoryId);
		productCategory.setProductCategory(cbProductCategory.getDataIndex().getProductCategory());
		productSupp.setProductCategory(productCategory);
		productSupp.setProductUomId(productUomId);
		
		Uom productUom = new Uom();
		productUom.setId(productUomId);
		productUom.setUom(txtUOM.getText());
		productSupp.setProductUom(productUom);
		
		String subtotal = calculateSubTotal(txtQty.getText(), txtUnitPrice.getText()).toString();
		
		
		PPSProduct ppsProduct = new PPSProduct();
		ppsProduct.setProductSupp(productSupp);
		ppsProduct.setQty(new BigDecimal(txtQty.getText()));
		ppsProduct.setUnitPrice(new BigDecimal(txtUnitPrice.getText()));
		ppsProduct.setSubTotal(new BigDecimal(subtotal));
		listOfPPSProduct.add(ppsProduct);
		
		String totalPurchase = calculateTotalPurchase(listOfPPSProduct).toString();
		
		txtTotal.setText(totalPurchase);
		
		String grandTotal = calculateGrandTotal(txtTotal.getText(), txtTax.getText()).toString();
		
		txtGrandTotal.setText(grandTotal);
		
		refreshTablePPSProduct();
		
		clearAddProduct();
	}
	
	public void clearAddProduct() {
		productCode = "";
		txtProduct.setText("");
		productCategoryId = 0;
		cbProductCategory.setSelectedIndex(productCategoryId);
		productUomId = null;
		txtUOM.setText("");
		txtQty.setText("0");
		txtUnitPrice.setText("0.00");
	};
	
	private BigDecimal calculateTotalPurchase(List<PPSProduct> listOfPPSProduct) {
		BigDecimal totalPurchase = new BigDecimal("0.00");
		for (PPSProduct ppsp : listOfPPSProduct) {
			totalPurchase = totalPurchase.add(ppsp.getSubTotal());
		}
		return totalPurchase;
	}
	
	private BigDecimal calculateGrandTotal(String sTotalPurchase, String sTax) {
		BigDecimal totalPurchase = new BigDecimal("0.00");
		if(!"".equals(sTotalPurchase)) {
			totalPurchase = new BigDecimal(sTotalPurchase);
		}
		
		BigDecimal tax = new BigDecimal("0.00");
		if(!"".equals(sTax)) {
			tax = new BigDecimal(sTax);
		}
		
		BigDecimal grandTotal = totalPurchase.add(tax);
		
		return grandTotal;
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

	protected String doCalculateSubTotal() {
		BigDecimal total = new BigDecimal("0");
		for (PPSProduct pprProduct : listOfPPSProduct) {
			total = total.add(pprProduct.getSubTotal());
		}

		return total.toString();
	}

	protected void doSave() {
		purchaseProductSupp.setPpsCode(txtPurchaseProductSuppCode.getText());
		purchaseProductSupp.setSuppCode(supplierCode);
		purchaseProductSupp.setCostCenterId(costCenterId);
		purchaseProductSupp.setPurchaseDate(dcPurchaseDate.getDate());
		purchaseProductSupp.setDeliveryDate(dcDeliveryDate.getDate());
		purchaseProductSupp.setTotal(new BigDecimal(txtTotal.getText()));
		purchaseProductSupp.setTax(new BigDecimal(txtTax.getText()));
		purchaseProductSupp.setGrandTotal(new BigDecimal(txtGrandTotal.getText()));
		purchaseProductSupp.setNote(txtNote.getText());
		try {
			ServiceFactory.getPurchaseProductSuppBL().update(purchaseProductSupp, listOfPPSProduct, listOfDeletedPPSProduct);
			DialogBox.showEdit();
			MainPanel.changePanel("module.purchaseprodsupp.ui.PurchaseProdSuppListPanel");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorPurchaseProductSuppCode.setText("");
		lblErrorSupplier.setText("");
		lblErrorPurchaseDate.setText("");
		lblErrorDeliveryDate.setText("");
		lblErrorCostCenter.setText("");
		
		if (txtPurchaseProductSuppCode.getText() == null
				|| txtPurchaseProductSuppCode.getText().length() == 0) {
			lblErrorPurchaseProductSuppCode
					.setText("Textbox Kode Pembelian harus diisi.");
			isValid = false;
		} else {
//			try {
//				if (ServiceFactory
//						.getPurchaseProductSuppBL()
//						.isPPSCodeExists(txtPurchaseProductSuppCode.getText()) > 0) {
//					lblErrorPurchaseProductSuppCode
//							.setText("Kode Pembelian sudah pernah diinput.");
//					isValid = false;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				DialogBox.showErrorException();
//				isValid = false;
//			}
		}

		if (supplierCode == null || supplierCode == "") {
			lblErrorSupplier.setText("Supplier harus dipilih.");
			isValid = false;
		}

		if (dcPurchaseDate.getDate() == null) {
			lblErrorPurchaseDate.setText("Tanggal Pengajuan harus dipilih.");
			isValid = false;
		}
		
		if (dcDeliveryDate.getDate() == null) {
			lblErrorDeliveryDate.setText("Tanggal Pengiriman harus dipilih.");
			isValid = false;
		}
		
		if(costCenterId == null) {
			lblErrorCostCenter.setText("Cost Center harus dipilih.");
			isValid = false;
		}
		
		if("".equals(lblErrorPurchaseProductSuppCode.getText()) &&
				"".equals(lblErrorSupplier.getText()) &&
				"".equals(lblErrorPurchaseDate.getText()) &&
				"".equals(lblErrorDeliveryDate.getText()) &&
				"".equals(lblErrorCostCenter.getText()))
		{
			if(listOfPPSProduct.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Produk tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
				isValid = false;
			}
		}
		

		return isValid;
	}
	
	
	
	
	protected void showSupplierDialog(
			PurchaseProdSuppEditPanel pprEditPanel) {
		SupplierDialog supplierDialog = new SupplierDialog(pprEditPanel);
		supplierDialog.setTitle("Supplier");
		supplierDialog.setLocationRelativeTo(null);
		supplierDialog.setVisible(true);
	}
	
	protected void showCostCenterDialog(
			PurchaseProdSuppEditPanel pprEditPanel) {
		CostCenterDialog costCenterDialog = new CostCenterDialog(pprEditPanel);
		costCenterDialog.setTitle("Cost Center");
		costCenterDialog.setLocationRelativeTo(null);
		costCenterDialog.setVisible(true);
	}
	
	protected void showProductDialog(
			PurchaseProdSuppEditPanel pprEditPanel) {
		ProductDialog productDialog = new ProductDialog(pprEditPanel);
		productDialog.setTitle("Barang");
		productDialog.setLocationRelativeTo(null);
		productDialog.setVisible(true);
	}

	protected void showEditPPSProductDialog(PPSProduct pprProduct,
			PurchaseProdSuppCreatePanel pprEditPanel, Integer index) {
//		PPSProductDialog pprProductDialog = new PPSProductDialog(true,
//				pprProduct, pprEditPanel, index);
//		pprProductDialog.setTitle("Barang");
//		pprProductDialog.setLocationRelativeTo(null);
//		pprProductDialog.setVisible(true);
	}

	protected void doDeletePPSProduct() {
		if (listOfPPSProduct.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<PPSProduct> temp = new ArrayList<PPSProduct>();
			for (PPSProduct s : listOfPPSProduct) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfPPSProduct.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (PPSProduct s : temp) {
					listOfPPSProduct.remove(s);
				}
				refreshTablePPSProduct();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Class as TableModel for PPS Product table
	 * 
	 * @author TSI
	 *
	 */
	class PPSProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PPSProduct> listOfPPSProduct;

		public PPSProductTableModel(List<PPSProduct> listOfPPSProduct) {
			this.listOfPPSProduct = listOfPPSProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPPSProduct.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 9;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link PPSProduct}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PPSProduct p = listOfPPSProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return rowIndex + 1;
			case 1:
				return p.getProductSupp().getProductCategory().getProductCategory();
			case 2:
				return p.getProductSupp().getProductCode();
			case 3:
				return p.getProductSupp().getProductName();
			case 4:
				return p.getQty();
			case 5:
				return p.getProductSupp().getProductUom().getUom();
			case 6:
				return p.getUnitPrice();
			case 7:
				return p.getSubTotal();
			case 8:
				return "<html><u>Delete</u></html>";
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
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return BigDecimal.class;
			case 5:
				return String.class;
			case 6:
				return BigDecimal.class;
			case 7:
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
				return "No.";
			case 1:
				return "Kategori Product";
			case 2:
				return "Kode Produk";
			case 3:
				return "Nama Produk";
			case 4:
				return "Qty";
			case 5:
				return "Satuan";
			case 6:
				return "Harga Satuan (Net)";
			case 7:
				return "Total (Net)";
			case 8:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTablePPSProduct() {
		try {
			tblPPSProduct.setModel(new PPSProductTableModel(listOfPPSProduct));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void makeCodeNumber(Date producationDate) {
//		final String constant = "POB";
//
//		Calendar cal = Calendar.getInstance();
//		if(producationDate != null) {
//			cal.setTime(producationDate);
//
//			String date = String.valueOf(cal.get(Calendar.DATE));
//			String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
//			String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
//
//			String ordinal = null;
//			try {
//				ordinal = ServiceFactory.getPurchaseProductSuppBL()
//						.getOrdinalOfCodeNumber(Integer.valueOf(year));
//			} catch (SQLException e) {
//				LOGGER.error(e.getMessage());
//				DialogBox.showErrorException();
//			}
//
//			txtPurchaseProductSuppCode.setText(new StringBuilder()
//					.append(ordinal).append("/").append(constant).append("/")
//					.append(date).append("/").append(month).append("/")
//					.append(year).toString());
//		}
		
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Integer getCostCenterId() {
		return costCenterId;
	}

	public void setCostCenterId(Integer costCenterId) {
		this.costCenterId = costCenterId;
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getProductUomId() {
		return productUomId;
	}

	public void setProductUomId(Integer productUomId) {
		this.productUomId = productUomId;
	}
}
