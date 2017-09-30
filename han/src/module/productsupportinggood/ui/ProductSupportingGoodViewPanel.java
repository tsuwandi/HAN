package module.productsupportinggood.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.sn.productcategory.model.ProductCategory;
import module.sn.uom.model.Uom;
import module.productsupportinggood.model.ProductSupp;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class ProductSupportingGoodViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductSupportingGoodViewPanel.class);

	//section_header_start
	JLabel lblProductCode;
	JLabel lblProductName;
	JLabel lblProductCategory;
	JLabel lblProductUom;
	JLabel lblMaintainStock;
	JLabel lblImagePath;
	JTextField txtProductCode;
	JTextField txtProductName;
	ComboBox<ProductCategory> cbProductCategory;
	ComboBox<Uom> cbProductUom;
	ButtonGroup maintainStock;
	JRadioButton maintainYesField;
	JRadioButton maintainNoField;
	JLabel lblErrorProductCode;
	JLabel lblErrorProductName;
	JLabel lblErrorProductCategory;
	JLabel lblErrorProductUom;
	//section_header_end
	
	//section_description_start
	JLabel lblBarcode;
	JLabel lblDescription;
	JTextField txtBarcode;
	JTextArea txtDescription;
	//section_description_end
	
	//section_attribute_product_start
	JLabel lblLength;
	JLabel lblWidth;
	JLabel lblThickness;
	JLabel lblUomVolume;
	JLabel lblWeightNet;
	JLabel lblWeightGross;
	JLabel lblUomWeight;
	NumberField txtLength;
	NumberField txtWidth;
	NumberField txtThickness;
	ComboBox<Uom> cbVolumeUom;
	NumberField txtWeightNet;
	NumberField txtWeightGross;
	ComboBox<Uom> cbWeightUom;
	//section_attribute_product_end
	
	//section_invetory_start
	JLabel lblFixedAsset;
	ButtonGroup fixedAsset;
	JRadioButton fixedAssetYesField;
	JRadioButton fixedAssetNoField;
	
	JLabel lblWarranty;
	NumberField txtWarranty;
	JLabel lblWarrantyDay;
	//section_invetory_end
	
	//section_purchase_information_start
	JLabel lblPurchaseItem;
	ButtonGroup purchaseItem;
	JRadioButton purchaseItemYesField;
	JRadioButton purchaseItemNoField;
	
	JLabel lblMininumOrder;
	NumberField txtMininumOrder;
	ComboBox<Uom> cbMininumUom;
	JLabel lblLeadTime;
	NumberField txtLeadTime;
	JLabel lblDefaultBuyingCostCenter;
	ComboBox<Object> cbDefaultBuyingCostCenter;
	JLabel lblDefaultBuyingExpenseAccount;
	ComboBox<Object> cbDefaultBuyingExpenseAccount;
	//section_purchase_information_end
	
	//section_sales_information_start
	JLabel lblSalesItem;
	ButtonGroup salesItem;
	JRadioButton salesItemYesField;
	JRadioButton salesItemNoField;
	
	JLabel lblServiceItem;
	ButtonGroup serviceItem;
	JRadioButton serviceItemYesField;
	JRadioButton serviceItemNoField;
	
	JLabel lblDefaultSellingCostCenter;
	ComboBox<Object> cbDefaultSellingCostCenter;
	JLabel lblDefaultIncomeAccount;
	ComboBox<Object> cbDefaultIncomeAccount;
	JLabel lblDefaultAsset;
	ComboBox<Object> cbDefaultAsset;
	JLabel lblMaximumDiscount;
	NumberField txtMaximumDiscount;
	JLabel lblErrorMaximumDiscount;
	JLabel lblTax;
	ComboBox<Object> cbTax;
	JLabel lblErrorTax;
	//section_sales_information_end
	
	
	//section_uom_conversion_start
	JScrollPane scrollPaneUomConversion;
	UomConversionTableModel uomConversionTableModel;
	JTable tblUomConversion;
	//section_uom_conversion_end
	
	JButton btnUpdate;
	JButton btnDelete;
	JButton btnCancel;
	JButton btnPrint;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	
	ProductSupp productSupp;
	DocumentFilter filter = new UppercaseDocumentFilter();

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	JLabel lblHeaderDescription;
	JLabel lblHeaderAtributeProduct;
	JLabel lblHeaderInventory;
	JLabel lblHeaderPurchaseInformation;
	JLabel lblHeaderConversionUOM;
	JLabel lblHeaderSalesInformation;
	
	public ProductSupportingGoodViewPanel() {
		//productSupp = new ProductSupp();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 1450));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > General > Produk Barang Pendukung");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		//timotius_section_1_start
		doAddSectionHeader();
		//timotius_section_1_end
		
		//timotius_section_description_start
		doAddSectionDescription();
		//timotius_section_description_end
		
		//timotius_section_attribute_product_start
		doAddSectionAttributeProduct();
		//timotius_section_attribute_product_end
		
		//timotius_section_inventory_start
		doAddSectionInventory();
		//timotius_section_inventory_end
		
		//timotius_section_purchase_information_start
		doAddPurchaseInformation();
		//timotius_section_purchase_information_end
		
		//timotius_section_conversion_uom_start
		doAddConversionUomInformation();
		//timotius_section_conversion_uom_end
		
		//timotius_section_sales_information_start
		doAddSalesInformation();
		//timotius_section_sales_information_end
		
		//timotius_section_add_button_start
		doViewButton();
		//timotius_section_add_button_end
		
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(scrollPane);
	}
	
	public void doAddSectionHeader() {
		lblProductCode = new JLabel("<html>Kode Produk <font color=\"red\">*</font></html>");
		lblProductCode.setBounds(50, 80, 150, 25);
		panel.add(lblProductCode);

		txtProductCode = new JTextField();
		txtProductCode.setBounds(220, 80, 150, 25);
		txtProductCode.setDocument(new JTextFieldLimit(20));
		((AbstractDocument) txtProductCode.getDocument()).setDocumentFilter(filter);
		txtProductCode.setEnabled(false);
		panel.add(txtProductCode);
		
		lblErrorProductCode = new JLabel();
		lblErrorProductCode.setForeground(Color.RED);
		lblErrorProductCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorProductCode);
		
		lblProductName = new JLabel("<html>Nama Produk<font color=\"red\">*</font></html>");
		lblProductName.setBounds(50, 110, 150, 25);
		panel.add(lblProductName);

		txtProductName = new JTextField();
		txtProductName.setBounds(220, 110, 150, 25);
		txtProductName.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtProductName.getDocument()).setDocumentFilter(filter);
		txtProductName.setEnabled(false);
		panel.add(txtProductName);
		
		lblErrorProductName = new JLabel();
		lblErrorProductName.setForeground(Color.RED);
		lblErrorProductName.setBounds(425, 110, 225, 25);
		panel.add(lblErrorProductName);
		
		lblProductCategory = new JLabel("<html>Kategori Produk <font color=\"red\">*</font></html>");
		lblProductCategory.setBounds(50, 140, 150, 25);
		panel.add(lblProductCategory);
		
		cbProductCategory = new ComboBox<ProductCategory>();
		
		cbProductCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbProductCategory.getSelectedIndex() != 0) {
					makeCodeNumber(cbProductCategory.getDataIndex().getProductCategory());
				} else {
					//do nothing
				}
			}
		});
		
		cbProductCategory.setList(getAllProductCategory());
		cbProductCategory.setBounds(220, 140, 150, 25);
		cbProductCategory.setEnabled(false);
		panel.add(cbProductCategory);

		lblErrorProductCategory = new JLabel();
		lblErrorProductCategory.setForeground(Color.RED);
		lblErrorProductCategory.setBounds(425, 140, 225, 25);
		panel.add(lblErrorProductCategory);
		
		lblProductUom= new JLabel("<html>Satuan Produk <font color=\"red\">*</font></html>");
		lblProductUom.setBounds(50, 170, 150, 25);
		panel.add(lblProductUom);
		
		cbProductUom = new ComboBox<Uom>();
		cbProductUom.setList(getAllProductUom());
		cbProductUom.setBounds(220, 170, 150, 25);
		cbProductUom.setEnabled(false);
		panel.add(cbProductUom);

		lblErrorProductUom = new JLabel();
		lblErrorProductUom.setForeground(Color.RED);
		lblErrorProductUom.setBounds(425, 170, 225, 25);
		panel.add(lblErrorProductUom);
		
		lblMaintainStock= new JLabel("<html>Maintain Produk <font color=\"red\">*</font></html>");
		lblMaintainStock.setBounds(50, 200, 150, 25);
		panel.add(lblMaintainStock);
		
		maintainStock = new ButtonGroup();
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setSelected(true);
		maintainYesField.setBounds(220, 200, 50, 25);

		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setSelected(false);
		maintainNoField.setBounds(290, 200, 50, 25);

		maintainStock.add(maintainYesField);
		maintainStock.add(maintainNoField);
		
		panel.add(maintainYesField);
		panel.add(maintainNoField);
		
		lblImagePath= new JLabel("<html>Gambar</html>");
		lblImagePath.setBounds(50, 230, 150, 25);
		panel.add(lblImagePath);
	}
	
	public void doAddSectionDescription() {
		lblHeaderDescription = new JLabel("Deskripsi");
		lblHeaderDescription.setBounds(50, 265, 200, 25);
		lblHeaderDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderDescription);
		
		lblBarcode = new JLabel("<html>Barcode</html>");
		lblBarcode.setBounds(50, 295, 150, 25);
		panel.add(lblBarcode);

		txtBarcode = new JTextField();
		txtBarcode.setBounds(220, 295, 150, 25);
		txtBarcode.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtBarcode.getDocument()).setDocumentFilter(filter);
		txtBarcode.setEnabled(false);
		panel.add(txtBarcode);
		
		lblDescription = new JLabel("<html>Deskripsi</html>");
		lblDescription.setBounds(50, 325, 150, 25);
		panel.add(lblDescription);

		txtDescription = new JTextArea();
		txtDescription.setBounds(220, 325, 200, 75);
		txtDescription.setDocument(new JTextFieldLimit(200));
		((AbstractDocument) txtDescription.getDocument()).setDocumentFilter(filter);
		txtDescription.setEnabled(false);
		txtDescription.setBackground(Color.GRAY);
		panel.add(txtDescription);
		
	}
	
	public void doAddSectionAttributeProduct() {
		lblHeaderAtributeProduct = new JLabel("Atribut Produk");
		lblHeaderAtributeProduct.setBounds(50, 415, 200, 25);
		lblHeaderAtributeProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderAtributeProduct);
		
		lblLength = new JLabel("<html>Panjang</html>");
		lblLength.setBounds(50, 445, 150, 25);
		panel.add(lblLength);

		txtLength = new NumberField(4);
		txtLength.setBounds(220, 445, 150, 25);
		txtLength.setEnabled(false);
		panel.add(txtLength);
		
		lblWidth = new JLabel("<html>Lebar</html>");
		lblWidth.setBounds(50, 475, 150, 25);
		panel.add(lblWidth);

		txtWidth = new NumberField(4);
		txtWidth.setBounds(220, 475, 150, 25);
		txtWidth.setEnabled(false);
		panel.add(txtWidth);
		
		lblThickness = new JLabel("<html>Tebal</html>");
		lblThickness.setBounds(50, 505, 150, 25);
		panel.add(lblThickness);

		txtThickness = new NumberField(4);
		txtThickness.setBounds(220, 505, 150, 25);
		txtThickness.setEnabled(false);
		panel.add(txtThickness);
		
		lblUomVolume = new JLabel("<html>Satuan Volume</html>");
		lblUomVolume.setBounds(400, 445, 150, 25);
		panel.add(lblUomVolume);

		cbVolumeUom = new ComboBox<Uom>();
		cbVolumeUom.setList(getAllProductUom());
		cbVolumeUom.setBounds(480, 445, 150, 25);
		cbVolumeUom.setEnabled(false);
		panel.add(cbVolumeUom);
		
		lblWeightNet = new JLabel("<html>Berat Netto</html>");
		lblWeightNet.setBounds(50, 535, 150, 25);
		panel.add(lblWeightNet);

		txtWeightNet = new NumberField(4);
		txtWeightNet.setBounds(220, 535, 150, 25);
		txtWeightNet.setEnabled(false);
		panel.add(txtWeightNet);
		
		lblWeightGross = new JLabel("<html>Berat Gross</html>");
		lblWeightGross.setBounds(50, 565, 150, 25);
		panel.add(lblWeightGross);

		txtWeightGross = new NumberField(4);
		txtWeightGross.setBounds(220, 565, 150, 25);
		txtWeightGross.setEnabled(false);
		panel.add(txtWeightGross);
		
		lblUomWeight = new JLabel("<html>Satuan Berat</html>");
		lblUomWeight.setBounds(400, 535, 150, 25);
		panel.add(lblUomWeight);

		cbWeightUom = new ComboBox<Uom>();
		cbWeightUom.setList(getAllProductUom());
		cbWeightUom.setBounds(480, 535, 150, 25);
		cbWeightUom.setEnabled(false);
		panel.add(cbWeightUom);
	}
	
	public void doAddSectionInventory() {
		lblHeaderInventory = new JLabel("Inventori");
		lblHeaderInventory.setBounds(50, 595, 200, 25);
		lblHeaderInventory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderInventory);
		
		lblFixedAsset = new JLabel("<html>Flag Aktiva Tetap</html>");
		lblFixedAsset.setBounds(50, 625, 150, 25);
		panel.add(lblFixedAsset);

		fixedAsset = new ButtonGroup();
		fixedAssetYesField = new JRadioButton("Ya");
		fixedAssetYesField.setSelected(false);
		fixedAssetYesField.setBounds(220, 625, 50, 25);
		fixedAssetYesField.setEnabled(false);

		fixedAssetNoField = new JRadioButton("Tidak");
		fixedAssetNoField.setSelected(true);
		fixedAssetNoField.setBounds(290, 625, 50, 25);
		fixedAssetNoField.setEnabled(false);

		fixedAsset.add(fixedAssetYesField);
		fixedAsset.add(fixedAssetNoField);
		
		panel.add(fixedAssetYesField);
		panel.add(fixedAssetNoField);
		
		lblWarranty = new JLabel("<html>Garansi</html>");
		lblWarranty.setBounds(50, 655, 150, 25);
		panel.add(lblWarranty);
		
		txtWarranty = new NumberField(3);
		txtWarranty.setBounds(220, 655, 150, 25);
		txtWarranty.setEnabled(false);
		panel.add(txtWarranty);
		
		lblWarranty = new JLabel("<html>Hari</html>");
		lblWarranty.setBounds(400, 655, 150, 25);
		panel.add(lblWarranty);
		
	}
	
	public void doAddPurchaseInformation() {
		
		lblHeaderPurchaseInformation = new JLabel("Purchase Information");
		lblHeaderPurchaseInformation.setBounds(50, 685, 200, 25);
		lblHeaderPurchaseInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderPurchaseInformation);
		
		lblPurchaseItem = new JLabel("<html>Flag Purchase Item</html>");
		lblPurchaseItem.setBounds(50, 715, 150, 25);
		panel.add(lblPurchaseItem);
		
		purchaseItem = new ButtonGroup();
		purchaseItemYesField = new JRadioButton("Ya");
		purchaseItemYesField.setSelected(false);
		purchaseItemYesField.setBounds(220, 715, 50, 25);
		purchaseItemYesField.setEnabled(false);

		purchaseItemNoField = new JRadioButton("Tidak");
		purchaseItemNoField.setSelected(true);
		purchaseItemNoField.setBounds(290, 715, 50, 25);
		purchaseItemNoField.setEnabled(false);

		purchaseItem.add(purchaseItemYesField);
		purchaseItem.add(purchaseItemNoField);
		
		panel.add(purchaseItemYesField);
		panel.add(purchaseItemNoField);
		
		lblMininumOrder = new JLabel("<html>Minimum Order</html>");
		lblMininumOrder.setBounds(50, 745, 150, 25);
		panel.add(lblMininumOrder);
		
		txtMininumOrder = new NumberField(3);
		txtMininumOrder.setBounds(220, 745, 150, 25);
		txtMininumOrder.setEnabled(false);
		panel.add(txtMininumOrder);
		
		cbMininumUom = new ComboBox<Uom>();
		cbMininumUom.setList(getAllProductUom());
		cbMininumUom.setBounds(480, 745, 150, 25);
		cbMininumUom.setEnabled(false);
		panel.add(cbMininumUom);
		
		lblLeadTime = new JLabel("<html>Lead Time</html>");
		lblLeadTime.setBounds(50, 775, 150, 25);
		panel.add(lblLeadTime);
		
		txtLeadTime = new NumberField(3);
		txtLeadTime.setBounds(220, 775, 150, 25);
		txtLeadTime.setEnabled(false);
		panel.add(txtLeadTime);
		
		lblDefaultBuyingCostCenter = new JLabel("<html>Default Buying Cost Center</html>");
		lblDefaultBuyingCostCenter.setBounds(50, 805, 150, 25);
		panel.add(lblDefaultBuyingCostCenter);
		
		cbDefaultBuyingCostCenter = new ComboBox<Object>();
		cbDefaultBuyingCostCenter.setList(new ArrayList<>());
		cbDefaultBuyingCostCenter.setBounds(220, 805, 150, 25);
		cbDefaultBuyingCostCenter.setEnabled(false);
		panel.add(cbDefaultBuyingCostCenter);
		
		lblDefaultBuyingExpenseAccount = new JLabel("<html>Default Expense Account</html>");
		lblDefaultBuyingExpenseAccount.setBounds(50, 835, 150, 25);
		panel.add(lblDefaultBuyingExpenseAccount);
		
		cbDefaultBuyingExpenseAccount = new ComboBox<Object>();
		cbDefaultBuyingExpenseAccount.setList(new ArrayList<>());
		cbDefaultBuyingExpenseAccount.setBounds(220, 835, 150, 25);
		cbDefaultBuyingExpenseAccount.setEnabled(false);
		panel.add(cbDefaultBuyingExpenseAccount);
	}
	
	
	
	public void doAddConversionUomInformation() {
		lblHeaderConversionUOM = new JLabel("Konversi Unit Of Measurement");
		lblHeaderConversionUOM.setBounds(50, 865, 200, 25);
		lblHeaderConversionUOM.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderConversionUOM);
		
		scrollPaneUomConversion = new JScrollPane();
		scrollPaneUomConversion.setBounds(50, 905, 975, 150);
		panel.add(scrollPaneUomConversion);
		
		uomConversionTableModel = new UomConversionTableModel(new ArrayList<Uom>());
		tblUomConversion = new JTable(uomConversionTableModel);
		tblUomConversion.setFocusable(false);
		tblUomConversion.setAutoCreateRowSorter(true);
		scrollPaneUomConversion.setViewportView(tblUomConversion);
	}
	
	public void doAddSalesInformation() {
		lblHeaderSalesInformation = new JLabel("Sales Information");
		lblHeaderSalesInformation.setBounds(50, 1065, 200, 25);
		lblHeaderSalesInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderSalesInformation);
		
		lblSalesItem = new JLabel("<html>Flag Sales Item</html>");
		lblSalesItem.setBounds(50, 1095, 150, 25);
		panel.add(lblSalesItem);
		
		salesItem = new ButtonGroup();
		salesItemYesField = new JRadioButton("Ya");
		salesItemYesField.setSelected(false);
		salesItemYesField.setBounds(220, 1095, 50, 25);
		salesItemYesField.setEnabled(false);
		
		salesItemNoField = new JRadioButton("Tidak");
		salesItemNoField.setSelected(true);
		salesItemNoField.setBounds(290, 1095, 50, 25);
		salesItemNoField.setEnabled(false);
		salesItem.add(salesItemYesField);
		salesItem.add(salesItemNoField);
		
		panel.add(salesItemYesField);
		panel.add(salesItemNoField);
		
		lblServiceItem = new JLabel("<html>Flag Service Item</html>");
		lblServiceItem.setBounds(50, 1125, 150, 25);
		panel.add(lblServiceItem);
		
		serviceItem = new ButtonGroup();
		serviceItemYesField = new JRadioButton("Ya");
		serviceItemYesField.setSelected(false);
		serviceItemYesField.setBounds(220, 1125, 50, 25);
		serviceItemYesField.setEnabled(false);

		serviceItemNoField = new JRadioButton("Tidak");
		serviceItemNoField.setSelected(true);
		serviceItemNoField.setBounds(290, 1125, 50, 25);
		serviceItemNoField.setEnabled(false);

		serviceItem.add(serviceItemYesField);
		serviceItem.add(serviceItemNoField);
		
		panel.add(serviceItemYesField);
		panel.add(serviceItemNoField);
		
		lblDefaultSellingCostCenter = new JLabel("<html>Default Selling Cost Center</html>");
		lblDefaultSellingCostCenter.setBounds(50, 1155, 150, 25);
		panel.add(lblDefaultSellingCostCenter);
		
		cbDefaultSellingCostCenter = new ComboBox<Object>();
		cbDefaultSellingCostCenter.setList(new ArrayList<>());
		cbDefaultSellingCostCenter.setBounds(220, 1155, 150, 25);
		cbDefaultSellingCostCenter.setEnabled(false);
		panel.add(cbDefaultSellingCostCenter);
		
		lblDefaultIncomeAccount = new JLabel("<html>Default Income Account</html>");
		lblDefaultIncomeAccount.setBounds(50, 1185, 150, 25);
		panel.add(lblDefaultIncomeAccount);
		
		cbDefaultIncomeAccount = new ComboBox<Object>();
		cbDefaultIncomeAccount.setList(new ArrayList<>());
		cbDefaultIncomeAccount.setBounds(220, 1185, 150, 25);
		cbDefaultIncomeAccount.setEnabled(false);
		panel.add(cbDefaultIncomeAccount);
		
		lblDefaultAsset = new JLabel("<html>Default Asset</html>");
		lblDefaultAsset.setBounds(50, 1215, 150, 25);
		panel.add(lblDefaultAsset);
		
		cbDefaultAsset = new ComboBox<Object>();
		cbDefaultAsset.setList(new ArrayList<>());
		cbDefaultAsset.setBounds(220, 1215, 150, 25);
		cbDefaultAsset.setEnabled(false);
		panel.add(cbDefaultAsset);
		
		lblMaximumDiscount = new JLabel("<html>Diskon Maximum</html>");
		lblMaximumDiscount.setBounds(50, 1275, 150, 25);
		panel.add(lblMaximumDiscount);
		
		txtMaximumDiscount = new NumberField(6);
		txtMaximumDiscount.setBounds(220, 1275, 150, 25);
		txtMaximumDiscount.setEnabled(false);
		panel.add(txtMaximumDiscount);
		
		lblErrorMaximumDiscount = new JLabel();
		lblErrorMaximumDiscount.setForeground(Color.RED);
		lblErrorMaximumDiscount.setBounds(425, 1275, 225, 25);
		panel.add(lblErrorMaximumDiscount);
		
		lblTax = new JLabel("<html>Tax</html>");
		lblTax.setBounds(50, 1305, 150, 25);
		panel.add(lblTax);
		
		cbTax = new ComboBox<Object>();
		cbTax.setList(new ArrayList<>());
		cbTax.setBounds(220, 1305, 150, 25);
		cbTax.setEnabled(false);
		panel.add(cbTax);
		
		lblErrorTax = new JLabel();
		lblErrorTax.setForeground(Color.RED);
		lblErrorTax.setBounds(425, 1305, 225, 25);
		panel.add(lblErrorTax);
	}
	
	public void doViewButton() {
		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//doPrint();
			}
		});
		btnPrint.setBounds(715, 1345, 100, 25);
		panel.add(btnPrint);

		btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showDeleteChoice();
				if (response == JOptionPane.YES_OPTION) {
					//doDelete();
				}
			}
		});
		btnDelete.setBounds(820, 1345, 100, 25);
		panel.add(btnDelete);
		
		btnUpdate = new JButton("Ubah");
		btnUpdate.setBounds(925, 1345, 100, 25);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.productsupportinggood.ui.ProductSupportingGoodEditPanel", productSupp);
			}
		});
		panel.add(btnUpdate);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 int response = DialogBox.showCloseChoice();
				 if (response == JOptionPane.YES_OPTION) {
					 MainPanel.changePanel("module.productsupportinggood.ui.ProductSupportingGoodListPanel");
				 }
				
			}
		});
		btnCancel.setBounds(50, 1345, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
	}
	
	protected void doPrint() {

	}

	protected void doDelete() {
		try {
			ServiceFactory.getProductSupportingGoodBL().delete(productSupp);
			DialogBox.showDelete();
			MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	/*protected boolean doValidate() {
		boolean isValid = true;

		lblErrorProductCode.setText("");
		lblErrorProductName.setText("");
		lblErrorProductCategory.setText("");
		lblErrorProductUom.setText("");
		

		if (txtProductCode.getText() == null || txtProductCode.getText().length() == 0) {
			lblErrorProductCode.setText("Textbox Kode Produk harus diisi.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getProductSupportingGoodBL().isProductCodeExists(txtProductCode.getText()) > 0) {
					lblErrorProductCode.setText("Kode Produk sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		if (txtProductName.getText() == null || txtProductName.getText().length() == 0) {
			lblErrorProductName.setText("Textbox Nama Produk harus diisi.");
			isValid = false;
		}

		if (cbProductCategory.getSelectedItem() == null || cbProductCategory.getSelectedIndex() == 0) {
			lblErrorProductCategory.setText("Combobox Kategori Produk harus dipilih.");
			isValid = false;
		}

		if (cbProductUom.getSelectedItem() == null || cbProductUom.getSelectedIndex() == 0) {
			lblErrorProductUom.setText("Combobox Satuan Produk harus dipilih.");
			isValid = false;
		}

		if (!"".equals(txtMaximumDiscount.getText())) {
			if (Double.valueOf(txtMaximumDiscount.getText()) > 100.00) {
				lblErrorMaximumDiscount.setText("Diskon Maksimum tidak lebih dari 100%");
				isValid = false;
			}
		}
		
		return isValid;
	}
	
	public void doSave() {
		productSupp = new ProductSupp();
		
		//header
		productSupp.setProductCode(txtProductCode.getText());
		productSupp.setProductName(txtProductName.getText());
		productSupp.setProductCategoryId(cbProductCategory.getDataIndex().getId());
		productSupp.setProductUomId(cbProductUom.getDataIndex().getId());
		productSupp.setIsMaintainStock(maintainYesField.isSelected() ? 0 : 1);
		
		//deskripsi
		productSupp.setBarcode(txtBarcode.getText());
		productSupp.setDescription(txtDescription.getText());
		
		//atribute product
		productSupp.setLength("".equals(txtLength.getText()) ? null : BigDecimal.valueOf(Double.valueOf(txtLength.getText())));
		productSupp.setWidth("".equals(txtWidth.getText()) ? null : BigDecimal.valueOf(Double.valueOf(txtWidth.getText())));
		productSupp.setThickness("".equals(txtThickness.getText()) ? null : BigDecimal.valueOf(Double.valueOf(txtThickness.getText())));
		productSupp.setWeightNet("".equals(txtWeightNet.getText()) ? null : BigDecimal.valueOf(Double.valueOf(txtWeightNet.getText())));
		productSupp.setWeightGross("".equals(txtWeightGross.getText()) ? null : BigDecimal.valueOf(Double.valueOf(txtWeightGross.getText())));
		productSupp.setVolumeUomId(cbVolumeUom.getDataIndex().getId());
		productSupp.setWeightUomId(cbWeightUom.getDataIndex().getId());
		
		//inventory
		productSupp.setIsFixedAsset(fixedAssetYesField.isSelected() ? 0 : 1);
		productSupp.setWarranty("".equals(txtWarranty.getText()) ? null : Integer.valueOf(txtWarranty.getText()));
		
		//purchase information
		productSupp.setIsPurchaseItem(purchaseItemYesField.isSelected() ? 0 : 1);
		productSupp.setMinor("".equals(txtMininumOrder.getText()) ? null : Integer.valueOf(txtMininumOrder.getText()));
		productSupp.setMinorUomId(Integer.valueOf(cbMininumUom.getDataIndex().getId()));
		productSupp.setLeadTime("".equals(txtLeadTime.getText()) ? null : Integer.valueOf(txtLeadTime.getText()));
		productSupp.setBuyCostCenterId(null);
		productSupp.setExpenseAccId(null);
		
		//sales information
		productSupp.setIsSalesItem(salesItemYesField.isSelected() ? 0 : 1);
		productSupp.setIsServiceItem(serviceItemYesField.isSelected() ? 0 : 1);
		productSupp.setSellCostCenterId(null);
		productSupp.setIncomeAccId(null);
		productSupp.setMaxDisc("".equals(txtMaximumDiscount.getText()) ? null : BigDecimal.valueOf(Double.valueOf(txtMaximumDiscount.getText())));
		productSupp.setTaxId(null);
		
		try {
			ServiceFactory.getProductSupportingGoodBL().save(productSupp);
			DialogBox.showInsert();
			MainPanel.changePanel("module.productsupportinggood.ui.ProductSupportingGoodListPanel");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}*/
	
	
	public List<ProductCategory> getAllProductCategory() {
		List<ProductCategory> listOfProductCategory = null;
		try {
			listOfProductCategory = new ArrayList<ProductCategory>();
			listOfProductCategory = ServiceFactory.getProductSupportingGoodBL().getAllProductCategory();
			listOfProductCategory.add(0, new ProductCategory("-- Pilih Kategori Produk --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		
		return listOfProductCategory;
	}
	
	public List<Uom> getAllProductUom() {
		List<Uom> listOfProductUom = null;
		try {
			listOfProductUom = new ArrayList<Uom>();
			listOfProductUom = ServiceFactory.getProductSupportingGoodBL().getAllUom();
			listOfProductUom.add(0, new Uom("-- Pilih Satuan Produk --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		
		return listOfProductUom;
	}
	
	public void makeCodeNumber(String productCategory) {
		String constantProductCategory = "";

		if (AppConstants.BALKEN_BASAH.equalsIgnoreCase(productCategory)) {
			constantProductCategory = AppConstants.BALKEN_BASAH_CD;
		} else if (AppConstants.BALKEN_KERING.equalsIgnoreCase(productCategory)) {
			constantProductCategory = AppConstants.BALKEN_KERING_CD;
		} else if (AppConstants.HASIL_PRODUKSI.equalsIgnoreCase(productCategory)) {
			constantProductCategory = AppConstants.HASIL_PRODUKSI_CD;
		} else if (AppConstants.BARANG_PENDUKUNG.equalsIgnoreCase(productCategory)) {
			constantProductCategory = AppConstants.BARANG_PENDUKUNG_CD;
		} else {
			constantProductCategory = AppConstants.PRODUCT_SUPP_1_CD;
		}

		String ordinal = null;
		try {
			ordinal = ServiceFactory.getProductSupportingGoodBL().getOrdinalOfCodeNumber(productCategory);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}

		txtProductCode.setText(new StringBuilder().append(constantProductCategory).append("-").append(ordinal).toString());
	}
	
	/**
	 * Class as TableModel for ProductSupportingGood table
	 * 
	 * @author TSI
	 */
	class UomConversionTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("rawtypes")
		private List<Uom> uomConversions;

		public UomConversionTableModel(@SuppressWarnings("rawtypes") List<Uom> uomConversions) {
			this.uomConversions = uomConversions;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return uomConversions.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 4;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link Supplier}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object p = uomConversions.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return null;
			case 1:
				return null;
			case 2:
				return null;
			case 3:
				return "<html><u>View</u></html>";
			default:
				return "";
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
				return "UOM Acuan";
			case 1:
				return "Jumlah";
			case 2:
				return "Uom Tujuan";
			case 3:
				return "Action";
			default:
				return "";
			}
		}
	}
	
	@Override
	public void invokeObjects(Object... objects) {
		this.productSupp = (ProductSupp) objects[0];

		loadData(productSupp.getId());
	}
	
	protected void loadData(Integer productSuppId) {
		try {
			productSupp = ServiceFactory.getProductSupportingGoodBL().getProductSuppById(productSuppId);
			
			if (productSupp != null) {
				txtProductCode.setText(productSupp.getProductCode());
				txtProductName.setText(productSupp.getProductName());
				cbProductCategory.setSelectedItem(productSupp.getProductCategory().getProductCategory());
				cbProductUom.setSelectedItem(productSupp.getProductUom().getUom());
				if (productSupp.getIsMaintainStock() == 0) {
					maintainYesField.setSelected(true);
					maintainNoField.setSelected(false);
				} else {
					maintainYesField.setSelected(false);
					maintainNoField.setSelected(true);
				}
				
				//deskripsi
				txtBarcode.setText(productSupp.getBarcode());
				txtDescription.setText(productSupp.getDescription());
				
				//atribute product
				txtLength.setText(String.valueOf(productSupp.getLength()));
				txtWidth.setText(String.valueOf(productSupp.getWidth()));
				txtThickness.setText(String.valueOf(productSupp.getThickness()));
				txtWeightNet.setText(String.valueOf(productSupp.getWeightNet()));
				txtWeightGross.setText(String.valueOf(productSupp.getWeightGross()));
				cbVolumeUom.setSelectedItem(productSupp.getVolumeUom().getUom());
				cbWeightUom.setSelectedItem(productSupp.getWeightUom().getUom());
				
				//inventory
				if (productSupp.getIsFixedAsset() == 0) {
					fixedAssetYesField.setSelected(true);
					fixedAssetNoField.setSelected(false);
				} else {
					fixedAssetYesField.setSelected(false);
					fixedAssetNoField.setSelected(true);
				}
				txtWarranty.setText(String.valueOf(productSupp.getWarranty()));
				
				//purchase information
				if (productSupp.getIsPurchaseItem() == 0) {
					purchaseItemYesField.setSelected(true);
					purchaseItemNoField.setSelected(false);
				} else {
					purchaseItemYesField.setSelected(false);
					purchaseItemNoField.setSelected(true);
				}
				txtMininumOrder.setText(String.valueOf(productSupp.getMinor()));
				cbMininumUom.setSelectedItem(productSupp.getMinorUom().getUom());
				txtLeadTime.setText(String.valueOf(productSupp.getLeadTime()));
				cbDefaultBuyingCostCenter.setSelectedItem(null);
				cbDefaultBuyingExpenseAccount.setSelectedItem(null);
				
				//sales information
				if (productSupp.getIsSalesItem() == 0) {
					salesItemYesField.setSelected(true);
					salesItemNoField.setSelected(false);
				} else {
					salesItemYesField.setSelected(false);
					salesItemNoField.setSelected(true);
				}
				if (productSupp.getIsServiceItem() == 0) {
					serviceItemYesField.setSelected(true);
					serviceItemNoField.setSelected(false);
				} else {
					serviceItemYesField.setSelected(false);
					serviceItemNoField.setSelected(true);
				}
				cbDefaultSellingCostCenter.setSelectedItem(null);
				cbDefaultIncomeAccount.setSelectedItem(null);
				txtMaximumDiscount.setText(String.valueOf(productSupp.getTax().getTax()));
				cbTax.setSelectedItem(null);
				
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	
}
