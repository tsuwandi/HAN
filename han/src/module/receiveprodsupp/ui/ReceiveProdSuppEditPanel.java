package module.receiveprodsupp.ui;

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
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.productsupportinggood.model.ProductSupp;
import module.purchaseprodsupp.model.PPSProduct;
import module.receiveprodsupp.model.RPSProduct;
import module.receiveprodsupp.model.ReceiveProdSupp;
import module.sn.productcategory.model.ProductCategory;
import module.sn.uom.model.Uom;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class ReceiveProdSuppEditPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		this.receiveProductSupp = (ReceiveProdSupp) objects[0];
		
		loadData(receiveProductSupp.getId());
	}
	
	protected void loadData(Integer id) {
		try {
			receiveProductSupp = ServiceFactory.getReceiveProductSuppBL().getRPSById(id);
			
			if (receiveProductSupp != null) {
				txtReceiveProductSuppCode.setText(receiveProductSupp.getRpsCode());
				txtPurchaseProductSuppCode.setText(receiveProductSupp.getPpsCode());
				
				txtSupplier.setText(receiveProductSupp.getPurchaseProdSupp().getSupplier().getSuppName());
				supplierCode = receiveProductSupp.getPurchaseProdSupp().getSupplier().getSuppCode();
				
				txtCostCenter.setText(receiveProductSupp.getPurchaseProdSupp().getCostCenter().getCostCenter());
				costCenterId = receiveProductSupp.getPurchaseProdSupp().getCostCenter().getId();
				
				dcReceiveDate.setDate(receiveProductSupp.getReceiveDate());
				
				txtNote.setText(receiveProductSupp.getNote());
				txtTotal.setText(String.format("%.2f", receiveProductSupp.getTotal()));
				
				String tax = String.format("%.2f",receiveProductSupp.getTax());
				
				txtTax.setText(tax);
				txtGrandTotal.setText(String.format("%.2f",receiveProductSupp.getGrandTotal()));
				
				listOfRPSProduct = ServiceFactory.getReceiveProductSuppBL().getRPSProductByRPSCode(receiveProductSupp.getRpsCode());
				
				refreshTableRPSProduct();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	private static final Logger LOGGER = Logger
			.getLogger(ReceiveProdSuppEditPanel.class);

	JLabel lblReceiveProductSuppCode;
	JLabel lblPurchaseProductSuppCode;
	JLabel lblSupplier;
	JLabel lblCostCenter;
	JLabel lblReceiveDate;
	JLabel lblNote;

	JButton btnInsertRPSProduct;
	JButton btnDeleteRPSProduct;

	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtReceiveProductSuppCode;
	JTextField txtPurchaseProductSuppCode;
	JTextField txtSupplier;
	JTextField txtCostCenter;
	JDateChooser dcReceiveDate;
	JTextArea txtNote;
	
	JButton btnSearchPPS;

	JLabel lblErrorReceiveProductSuppCode;
	JLabel lblErrorPurchaseProductSuppCode;
	JLabel lblErrorSupplier;
	JLabel lblErrorCostCenter;
	JLabel lblErrorReceiveDate;

	ReceiveProdSupp receiveProductSupp;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<Supplier> listOfSupplier = null;

	private List<RPSProduct> listOfRPSProduct = null;
	private List<RPSProduct> listOfDeletedRPSProduct = new ArrayList<RPSProduct>();

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneRPSProduct;
	JTable tblRPSProduct;

	RPSProductTableModel rprProductTableModel = null;

	ReceiveProdSuppEditPanel rprEditPanel;

	JLabel lblRPSProduct;
	
	private String supplierCode;
	private Integer costCenterId;
	private String productCode;
	private Integer productUomId;
	private BigDecimal qty;

	public ReceiveProdSuppEditPanel() {
		receiveProductSupp = new ReceiveProdSupp();

		rprEditPanel = this;

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 800));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Penerimaan > Barang Pendukung");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		addPenerimaanBarangPendukung();
		
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
					MainPanel.changePanel("module.receiveprodsupp.ui.ReceiveProdSuppListPanel");
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
	
	public void addPenerimaanBarangPendukung() {
		lblHeader = new JLabel("Buat Baru Penerimaan Barang Pendukung");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);
		
		lblReceiveProductSuppCode = new JLabel(
				"<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblReceiveProductSuppCode.setBounds(50, 80, 150, 25);
		panel.add(lblReceiveProductSuppCode);

		txtReceiveProductSuppCode = new JTextField();
		txtReceiveProductSuppCode.setBounds(220, 80, 150, 25);
		txtReceiveProductSuppCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtReceiveProductSuppCode.getDocument())
				.setDocumentFilter(filter);
		txtReceiveProductSuppCode.setEnabled(false);
		panel.add(txtReceiveProductSuppCode);
		
		lblErrorReceiveProductSuppCode = new JLabel();
		lblErrorReceiveProductSuppCode.setForeground(Color.RED);
		lblErrorReceiveProductSuppCode.setBounds(525, 80, 225, 25);
		panel.add(lblErrorReceiveProductSuppCode);
		
		lblReceiveDate = new JLabel(
				"<html>Tanggal Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveDate.setBounds(50, 110, 150, 25);
		panel.add(lblReceiveDate);

		dcReceiveDate = new JDateChooser(new Date());
		dcReceiveDate.setBounds(220, 110, 150, 25);
		dcReceiveDate.setDateFormatString("dd-MM-yyyy");
		dcReceiveDate.setEnabled(false);
		panel.add(dcReceiveDate);

		lblErrorReceiveDate = new JLabel();
		lblErrorReceiveDate.setForeground(Color.RED);
		lblErrorReceiveDate.setBounds(425, 110, 225, 25);
		panel.add(lblErrorReceiveDate);
		
		lblNote = new JLabel("Catatan");
		lblNote.setBounds(585, 80, 150, 25);
		panel.add(lblNote);
		
		txtNote = new JTextArea();
		txtNote.setBounds(755, 80, 200, 100);
		txtNote.setDocument(new JTextFieldLimit(200));
		((AbstractDocument) txtNote.getDocument())
				.setDocumentFilter(filter);
		panel.add(txtNote);

		lblPurchaseProductSuppCode = new JLabel(
				"<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductSuppCode.setBounds(50, 140, 150, 25);
		panel.add(lblPurchaseProductSuppCode);

		txtPurchaseProductSuppCode = new JTextField();
		txtPurchaseProductSuppCode.setBounds(220, 140, 150, 25);
		txtPurchaseProductSuppCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtPurchaseProductSuppCode.getDocument())
				.setDocumentFilter(filter);
		txtPurchaseProductSuppCode.setEnabled(false);
		panel.add(txtPurchaseProductSuppCode);
	
		lblErrorPurchaseProductSuppCode = new JLabel();
		lblErrorPurchaseProductSuppCode.setForeground(Color.RED);
		lblErrorPurchaseProductSuppCode.setBounds(525, 140, 225, 25);
		panel.add(lblErrorPurchaseProductSuppCode);
		
		btnSearchPPS = new JButton("Cari PO");
		btnSearchPPS.setBounds(385, 140, 125, 25);
		btnSearchPPS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showPPSDialog(rprEditPanel);
			}
		});
		panel.add(btnSearchPPS);
		
		lblSupplier = new JLabel(
				"<html>Supplier <font color=\"red\">*</font></html>");
		lblSupplier.setBounds(50, 170, 150, 25);
		panel.add(lblSupplier);

		txtSupplier = new JTextField();
		txtSupplier.setBounds(220, 170, 150, 25);
		txtSupplier.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtSupplier.getDocument())
				.setDocumentFilter(filter);
		txtSupplier.setEnabled(false);
		panel.add(txtSupplier);

		lblErrorSupplier = new JLabel("");
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(425, 170, 225, 25);
		panel.add(lblErrorSupplier);

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
		lblErrorCostCenter.setBounds(425, 200, 225, 25);
		panel.add(lblErrorCostCenter);
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

	JTextField txtProductCategory;
	JTextField txtProduct;
	NumberField txtQty;
	JTextField txtUnitPrice;
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
		
		lblProduct = new JLabel(
				"<html>Produk <font color=\"red\">*</font></html>");
		lblProduct.setBounds(50, 260, 100, 25);
		panel.add(lblProduct);
		
		txtProduct = new JTextField();
		txtProduct.setBounds(220, 260, 150, 25);
		txtProduct.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtProduct.getDocument())
				.setDocumentFilter(filter);
		txtProduct.setEnabled(false);
		panel.add(txtProduct);
		
		lblErrorProduct = new JLabel("");
		lblErrorProduct.setForeground(Color.RED);
		lblErrorProduct.setBounds(525, 260, 225, 25);
		panel.add(lblErrorProduct);
		
		lblProductCategory = new JLabel(
				"<html>Kategori Produk <font color=\"red\">*</font></html>");
		lblProductCategory.setBounds(50, 290, 100, 25);
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
		
		txtProductCategory = new JTextField();
		txtProductCategory.setBounds(220, 290, 150, 25);
		txtProductCategory.setEnabled(false);
		panel.add(txtProductCategory);

		lblErrorProductCategory = new JLabel("");
		lblErrorProductCategory.setForeground(Color.RED);
		lblErrorProductCategory.setBounds(525, 290, 225, 25);
		panel.add(lblErrorProductCategory);
		
		btnSearchProduct = new JButton("Cari Barang");
		btnSearchProduct.setBounds(385, 260, 125, 25);
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if("".equals(txtPurchaseProductSuppCode.getText()))
				{
					lblErrorProduct.setText("Kode Pembelian harus dipilih.");
				}
				else 
				{
					showProductDialog(rprEditPanel);
				}
				
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
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setBounds(220, 380, 150, 25);
		txtUnitPrice.setEnabled(false);
		panel.add(txtUnitPrice);
		
		lblErrorUnitPrice = new JLabel("");
		lblErrorUnitPrice.setForeground(Color.RED);
		lblErrorUnitPrice.setBounds(525, 380, 225, 25);
		panel.add(lblErrorUnitPrice);
		

		btnInsertRPSProduct = new JButton("Tambah");
		btnInsertRPSProduct.setBounds(220, 410, 100, 25);
		btnInsertRPSProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidateProductSupp() == false) {
					return;
				}
				doAddProductSupp();
			}
		});
		panel.add(btnInsertRPSProduct);

		scrollPaneRPSProduct = new JScrollPane();
		scrollPaneRPSProduct.setBounds(50, 440, 975, 150);
		panel.add(scrollPaneRPSProduct);

		listOfRPSProduct = new ArrayList<RPSProduct>();
		rprProductTableModel = new RPSProductTableModel(listOfRPSProduct);
		tblRPSProduct = new JTable(rprProductTableModel);
		tblRPSProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblRPSProduct.setFocusable(false);
		tblRPSProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 8) {
						deleteRPSProduct(listOfRPSProduct, row);
					}
				}
			}
		});
		scrollPaneRPSProduct.setViewportView(tblRPSProduct);

		lblTotal = new JLabel("<html>Total Penerimaan<font color=\"red\"></font></html>");
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
		
		txtTax = new NumberField(20);
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
	


	private void deleteRPSProduct(List<RPSProduct> listOfRPSProduct, int row) {
		if(listOfRPSProduct.size() > 0) {
			listOfDeletedRPSProduct.add(listOfRPSProduct.get(row));
			listOfRPSProduct.remove(row);
		}
		
		String totalPurchase = calculateTotalReceive(listOfRPSProduct).toString();
		
		txtTotal.setText(totalPurchase);
		
		String grandTotal = calculateGrandTotal(txtTotal.getText(), txtTax.getText()).toString();
		
		txtGrandTotal.setText(grandTotal);
		
		refreshTableRPSProduct();
		
		
	}
	
	protected boolean doValidateProductSupp() {
		boolean isValid = true;
		lblErrorProduct.setText("");
		lblErrorUnitPrice.setText("");
		lblErrorQty.setText("");
		if (txtProduct.getText() == null || txtProduct.getText().length() == 0) {
			lblErrorProduct.setText("Textbox Produk harus dipilih.");
			isValid = false;
		}
		
		if (txtQty.getText() == null || txtQty.getText().length() == 0) {
			lblErrorQty.setText("Textbox Qty harus diisi.");
			isValid = false;
		} else {
			BigDecimal qtyBD = new BigDecimal(txtQty.getText());
			if(qtyBD.compareTo(qty) > 0)
			{
				lblErrorQty.setText("Qty penerimaan lebih besar dari pembelian.");
				isValid = false;
			}
		}
		
		if (txtUnitPrice.getText() == null || txtUnitPrice.getText().length() == 0) {
			lblErrorUnitPrice.setText("Textbox Harga Satuan harus diisi.");
			isValid = false;
		}
		
		for(RPSProduct ppsp : listOfRPSProduct) {
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
		productCategory.setProductCategory(txtProductCategory.getText());
		productSupp.setProductCategory(productCategory);
		productSupp.setProductUomId(productUomId);
		
		Uom productUom = new Uom();
		productUom.setId(productUomId);
		productUom.setUom(txtUOM.getText());
		productSupp.setProductUom(productUom);
		
		String subtotal = calculateSubTotal(txtQty.getText(), txtUnitPrice.getText()).toString();
		
		RPSProduct rpsProduct = new RPSProduct();
		rpsProduct.setProductSupp(productSupp);
		rpsProduct.setQty(new BigDecimal(txtQty.getText()));
		rpsProduct.setUnitPrice(new BigDecimal(txtUnitPrice.getText()));
		rpsProduct.setSubTotal(new BigDecimal(subtotal));
		listOfRPSProduct.add(rpsProduct);
		
		String totalReceive = calculateTotalReceive(listOfRPSProduct).toString();
		
		txtTotal.setText(totalReceive);
		
		String grandTotal = calculateGrandTotal(txtTotal.getText(), txtTax.getText()).toString();
		
		txtGrandTotal.setText(grandTotal);
		
		refreshTableRPSProduct();
		
		clearAddProduct();
	}
	
	public void clearAddProduct() {
		productCode = "";
		txtProduct.setText("");
		productCategoryId = 0;
		txtProductCategory.setText("");
		productUomId = null;
		txtUOM.setText("");
		txtQty.setText("0");
		txtUnitPrice.setText("0.00");
	};
	
	private BigDecimal calculateTotalReceive(List<RPSProduct> listOfRPSProduct) {
		BigDecimal totalReceive = new BigDecimal("0.00");
		for (RPSProduct ppsp : listOfRPSProduct) {
			totalReceive = totalReceive.add(ppsp.getSubTotal());
		}
		return totalReceive;
	}
	
	private BigDecimal calculateGrandTotal(String sTotalReceive, String sTax) {
		BigDecimal totalReceive = new BigDecimal("0.00");
		if(!"".equals(sTotalReceive)) {
			totalReceive = new BigDecimal(sTotalReceive);
		}
		
		BigDecimal tax = new BigDecimal("0.00");
		if(!"".equals(sTax)) {
			tax = new BigDecimal(sTax);
		}
		
		BigDecimal grandTotal = totalReceive.add(tax);
		
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
		for (RPSProduct pprProduct : listOfRPSProduct) {
			total = total.add(pprProduct.getSubTotal());
		}

		return total.toString();
	}

	protected void doSave() {
		receiveProductSupp.setRpsCode(txtReceiveProductSuppCode.getText());
		receiveProductSupp.setPpsCode(txtPurchaseProductSuppCode.getText());
		receiveProductSupp.setReceiveDate(dcReceiveDate.getDate());
		receiveProductSupp.setTotal(new BigDecimal(txtTotal.getText()));
		receiveProductSupp.setTax(new BigDecimal(txtTax.getText()));
		receiveProductSupp.setGrandTotal(new BigDecimal(txtGrandTotal.getText()));
		receiveProductSupp.setNote(txtNote.getText());
		try {
			ServiceFactory.getReceiveProductSuppBL().update(receiveProductSupp, listOfRPSProduct, listOfDeletedRPSProduct);
			DialogBox.showInsert();
			MainPanel.changePanel("module.receiveprodsupp.ui.ReceiveProdSuppListPanel");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorReceiveProductSuppCode.setText("");
		lblErrorPurchaseProductSuppCode.setText("");
		lblErrorSupplier.setText("");
		lblErrorReceiveDate.setText("");
		
		if (txtReceiveProductSuppCode.getText() == null
				|| txtReceiveProductSuppCode.getText().length() == 0) {
			lblErrorReceiveProductSuppCode
					.setText("Textbox Kode Pembelian harus diisi.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory
						.getReceiveProductSuppBL()
						.isRPSCodeExists(txtReceiveProductSuppCode.getText()) > 0) {
					lblErrorReceiveProductSuppCode
							.setText("Kode Penerimaan sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DialogBox.showErrorException();
				isValid = false;
			}
		}
		System.out.println(txtPurchaseProductSuppCode.getText());
		if (txtPurchaseProductSuppCode.getText() == null
				|| txtPurchaseProductSuppCode.getText().length() == 0) {
			lblErrorPurchaseProductSuppCode
					.setText("Textbox Kode Pembelian harus dipilih.");
			isValid = false;
		}

		if (dcReceiveDate.getDate() == null) {
			lblErrorReceiveDate.setText("Tanggal Pengiriman harus dipilih.");
			isValid = false;
		}
		
		if("".equals(lblErrorPurchaseProductSuppCode.getText()) &&
				"".equals(lblErrorReceiveDate.getText()) &&
				"".equals(lblErrorReceiveProductSuppCode.getText()))
		{
			if(listOfRPSProduct.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Produk tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
				isValid = false;
			}
		}
		

		return isValid;
	}
	
	
	
	
	protected void showPPSDialog(
			ReceiveProdSuppEditPanel rprEditPanel) {
		PPSDialog ppsDialog = new PPSDialog(rprEditPanel);
		ppsDialog.setTitle("PO Barang Pendukung");
		ppsDialog.setLocationRelativeTo(null);
		ppsDialog.setVisible(true);
	}
	
	protected void showCostCenterDialog(
			ReceiveProdSuppEditPanel pprCreatePanel) {
	}
	
	protected void showProductDialog(
			ReceiveProdSuppEditPanel pprCreatePanel) {
		ProductDialog productDialog = new ProductDialog(rprEditPanel);
		productDialog.setTitle("Produk");
		productDialog.setLocationRelativeTo(null);
		productDialog.setVisible(true);
	}

	protected void showEditPPSProductDialog(PPSProduct pprProduct,
			ReceiveProdSuppEditPanel pprCreatePanel, Integer index) {
	}

	protected void doDeletePPSProduct() {
		if (listOfRPSProduct.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<RPSProduct> temp = new ArrayList<RPSProduct>();
			for (RPSProduct s : listOfRPSProduct) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfRPSProduct.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (RPSProduct s : temp) {
					listOfRPSProduct.remove(s);
				}
				refreshTableRPSProduct();
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
	class RPSProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<RPSProduct> listOfRPSProduct;

		public RPSProductTableModel(List<RPSProduct> listOfRPSProduct) {
			this.listOfRPSProduct = listOfRPSProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfRPSProduct.size();
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
		 * @return ({@link RPSProduct}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			RPSProduct p = listOfRPSProduct.get(rowIndex);
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
				return "Kategori Produk";
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

	public void refreshTableRPSProduct() {
		try {
			tblRPSProduct.setModel(new RPSProductTableModel(listOfRPSProduct));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void makeCodeNumber(Date receiveDate) {
		final String constant = "RCV";

		Calendar cal = Calendar.getInstance();
		if(receiveDate != null) {
			cal.setTime(receiveDate);

			String date = String.valueOf(cal.get(Calendar.DATE));
			String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
			String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);

			String ordinal = null;
			try {
				ordinal = ServiceFactory.getReceiveProductSuppBL()
						.getOrdinalOfCodeNumber(Integer.valueOf(year));
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}

			txtReceiveProductSuppCode.setText(new StringBuilder()
					.append(ordinal).append("/").append(constant).append("/")
					.append(date).append("/").append(month).append("/")
					.append(year).toString());
		}
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	
	public List<RPSProduct> getListOfRPSProduct() {
		return listOfRPSProduct;
	}

	public void setListOfRPSProduct(List<RPSProduct> listOfRPSProduct) {
		this.listOfRPSProduct = listOfRPSProduct;
	}
	
	public List<RPSProduct> getListOfDeletedRPSProduct() {
		return listOfDeletedRPSProduct;
	}

	public void setListOfDeletedRPSProduct(List<RPSProduct> listOfDeletedRPSProduct) {
		this.listOfDeletedRPSProduct = listOfDeletedRPSProduct;
	}

}
