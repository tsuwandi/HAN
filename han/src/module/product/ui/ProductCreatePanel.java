package module.product.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import module.pembelian.model.WoodType;
import module.product.model.Condition;
import module.product.model.Grade;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.JTextFieldLimit;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ProductCreatePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private static final Logger LOGGER = Logger.getLogger(ProductCreatePanel.class);

	private JLabel breadcrumb;
	private JButton backBtn;
	private JLabel titleLbl;

	private JLabel idLbl;
	private JLabel nameLbl;
	private JLabel catLbl;
	//private JLabel statLbl;
	private JLabel unitLbl;
	private JLabel maintainLbl;
	// private JLabel picLbl;

	private JLabel descTitleLbl;
	private JLabel brandLbl;
	private JLabel barcodeLbl;
	private JLabel descLbl;

	private JLabel attLbl;
	private JLabel typeLbl;
	private JLabel gradeLbl;
	private JLabel thickLbl;
	private JLabel longLbl;
	private JLabel wideLbl;
	// private JLabel conditionLbl;
	private JLabel minQtyLbl;

	private JLabel inventLbl;
	private JLabel flagSerialLbl;
	private JLabel flagAssetLbl;
	private JLabel warrantLbl;
	private JLabel weightLbl;

	private JLabel purchaseInfoLbl;
	private JLabel flagPurchaseLbl;
	private JLabel minOrderLbl;
	private JLabel leadTimeLbl;

	private JLabel defCostLbl;
	private JLabel defExpense;

	private JLabel conversionLbl;

	private JLabel suppInfoLbl;
	private JLabel suppLbl;
	private JLabel manufacturerLbl;

	private JLabel reservedSuppLbl;

	private JLabel salesInfoLbl;
	private JLabel flagSalesLbl;
	private JLabel flagProductLbl;

	private JLabel defSellCostLbl;
	private JLabel defIncomeLbl;
	private JLabel maxDiscountLbl;

	private JLabel customerListLbl;

	private JLabel taxLbl;

	public JTextField idField;
	public JTextField nameField;
	public ComboBox<ProductCategory> catField;
	//public JComboBox<String> statField;
	public ComboBox<Uom> uomField;
	public ButtonGroup maintain;
	public JRadioButton maintainYesField;
	public JRadioButton maintainNoField;
	// public JTextField pathField;
	// public JButton browseBtn;
	// public JButton uploadBtn;
	// public JLabel imageField;

	public JTextField brandField;
	public JTextField barcodeField;
	public JTextArea descField;

	public ComboBox<WoodType> typeField;
	public ComboBox<Grade> gradeField;
	public NumberField thickField;
	public NumberField longField;
	public NumberField wideField;
	// public ComboBox<Condition> conField;
	public NumberField minQtyField;

	public ButtonGroup flagSerial;
	public ButtonGroup flagAsset;
	public JRadioButton serialYesField;
	public JRadioButton serialNoField;
	public JRadioButton assetYesField;
	public JRadioButton assetNoField;
	public JTextField warrantField;
	private JLabel dayLbl;
	public JTextField nettoField;
	public JComboBox<String> nettoUnitField;

	public ButtonGroup purchase;
	public JRadioButton purchaseYesField;
	public JRadioButton purchaseNoField;
	public JTextField minOrderField;
	public JComboBox<String> minOrderUnitField;
	public JTextField leadTimeField;
	private JLabel day2Lbl;
	public JComboBox<String> buyCostField;
	public JComboBox<String> expenseField;

	public JButton uomAddBtn;
	public JButton uomDeleteBtn;
	public JTable uomTable;

	public JComboBox<String> supplierField;
	public JTextField manufacturerField;

	public JButton resSupSearchBtn;
	public JButton resSupDeleteBtn;
	public JTable resSupTable;

	public ButtonGroup sales;
	public ButtonGroup service;
	public JRadioButton salesYesField;
	public JRadioButton salesNoField;
	public JRadioButton serviceYesField;
	public JRadioButton serviceNoField;
	public JComboBox<String> sellCostField;
	public JComboBox<String> incomeField;
	public JTextField discountField;
	private JLabel percentLbl;

	public JButton custSearchBtn;
	public JButton custDeleteBtn;
	public JTable custTable;

	public JButton taxAddBtn;
	public JButton taxDeleteBtn;
	public JTable taxTable;

	public JButton saveBtn;

	JScrollPane scrollPane;
	JPanel parent;
	JPanel containerPnl;

	String filename;

	public ProductTableModel productTableModel;
	ReserveSupplierTableModel reserveSupplierTableModel;
	CustomerListTableModel customerListTableModel;
	TaxTableModel taxTableModel;
	public List<Product> products;
	private JScrollPane uomScroll;
	private JScrollPane resSupScroll;
	private JScrollPane custScroll;
	private JScrollPane taxScroll;

	public UOMConversionPanel uomConversionPanel;
	public SearchSupplierPanel searchSupplierPanel;
	public SearchCustomerPanel searchCustomerPanel;
	public TaxPanel taxPanel;

	public Product product;
	public ProductCategory productCategory;
	public List<ProductCategory> categories = null;
	public List<WoodType> woodTypes = null;
	public List<Grade> grades = null;
	public List<Uom> units = null;
	public List<Condition> conditions = null;

	private JButton copyFromBtn;

	Date todayDate;
	// SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy-MM-dd);

	ProductCreatePanel productCreatePanel;

	public ProductCreatePanel() {
		this.productCreatePanel = this;

		setLayout(null);
		// this.parent = this;
		setPreferredSize(new Dimension(1166, 620));

		todayDate = new Date();
		todayDate.getTime();

		// containerPnl = new JPanel();
		// containerPnl.setPreferredSize(new Dimension(1166, 620));
		// containerPnl.setLayout(null);

		// scrollPane = new JScrollPane(containerPnl);
		// scrollPane.setBounds(0,0,1166,630);
		// add(scrollPane);

		breadcrumb = new JLabel("ERP > Pembelian > Produk");
		breadcrumb.setFont(new Font(null, Font.BOLD, 12));
		breadcrumb.setBounds(50, 10, 320, 25);

		backBtn = new JButton("Kembali");
		backBtn.setBounds(50, 550, 75, 25);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.product.ui.ProductListPanel");
				}
			}
		});
		backBtn.setFocusable(false);
		backBtn.setHorizontalAlignment(SwingConstants.CENTER);

		titleLbl = new JLabel("BUAT BARU");
		titleLbl.setBounds(50, 40, 300, 25);
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 12));

		idLbl = new JLabel("<html>Kode Produk <font color=\"red\">*</font></html>");
		idLbl.setBounds(50, 80, 100, 25);
		nameLbl = new JLabel("<html>Nama Produk <font color=\"red\">*</font></html>");
		nameLbl.setBounds(50, 110, 100, 25);
		catLbl = new JLabel("<html>Kategori Produk <font color=\"red\">*</font></html>");
		catLbl.setBounds(50, 140, 100, 25);
//		statLbl = new JLabel("<html>Status Produk <font color=\"red\">*</font></html>");
//		statLbl.setBounds(50, 170, 100, 25);
		unitLbl = new JLabel("<html>Satuan Produk <font color=\"red\">*</font></html>");
		unitLbl.setBounds(50, 170, 100, 25);
		maintainLbl = new JLabel("<html>Maintain Stock <font color=\"red\">*</font></html>");
		maintainLbl.setBounds(50, 200, 100, 25);
		// picLbl = new JLabel("Gambar");
		// picLbl.setBounds(20, 260, 100, 25);

		descTitleLbl = new JLabel("<html><u>Description</u></html>");
		descTitleLbl.setBounds(20, 420, 100, 25);
		descTitleLbl.setFont(new Font(null, Font.BOLD, 12));
		brandLbl = new JLabel("Brand");
		brandLbl.setBounds(20, 450, 100, 25);
		barcodeLbl = new JLabel("Barcode");
		barcodeLbl.setBounds(20, 480, 100, 25);
		descLbl = new JLabel("Deskripsi");
		descLbl.setBounds(20, 510, 100, 25);

		attLbl = new JLabel("<html><u>Atribut Produk</u></html>");
		attLbl.setBounds(50, 260, 100, 25);
		attLbl.setFont(new Font(null, Font.BOLD, 12));
		typeLbl = new JLabel("Jenis Kayu");
		typeLbl.setBounds(50, 290, 100, 25);
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(50, 320, 100, 25);
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(50, 380, 100, 25);
		longLbl = new JLabel("Panjang");
		longLbl.setBounds(50, 350, 100, 25);
		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(50, 410, 100, 25);
		// conditionLbl = new JLabel("<html>Kondisi <font
		// color=\"red\">*</font></html>");
		// conditionLbl.setBounds(50, 440, 100, 25);
		minQtyLbl = new JLabel("<html>Minimum Qty <font color=\"red\">*</font></html>");
		minQtyLbl.setBounds(50, 440, 100, 25);

		inventLbl = new JLabel("<html><u>Inventory</u></html>");
		inventLbl.setBounds(20, 780, 100, 25);
		inventLbl.setFont(new Font(null, Font.BOLD, 12));
		flagSerialLbl = new JLabel("Flag Serial No");
		flagSerialLbl.setBounds(20, 750, 100, 25);
		flagAssetLbl = new JLabel("Flag Aktiva Tetap");
		flagAssetLbl.setBounds(20, 780, 100, 25);
		warrantLbl = new JLabel("Garansi");
		warrantLbl.setBounds(20, 810, 100, 25);
		weightLbl = new JLabel("Berat Netto");
		weightLbl.setBounds(20, 840, 100, 25);

		purchaseInfoLbl = new JLabel("<html><u>Purchase Information</u></html>");
		purchaseInfoLbl.setBounds(20, 870, 150, 25);
		purchaseInfoLbl.setFont(new Font(null, Font.BOLD, 12));
		flagPurchaseLbl = new JLabel("Flag Purchase Item");
		flagPurchaseLbl.setBounds(20, 900, 100, 25);
		minOrderLbl = new JLabel("Minimum Order");
		minOrderLbl.setBounds(20, 930, 100, 25);
		leadTimeLbl = new JLabel("Lead Time");
		leadTimeLbl.setBounds(20, 960, 100, 25);

		defCostLbl = new JLabel("Default Buying Cost Center");
		defCostLbl.setBounds(20, 990, 140, 25);
		defExpense = new JLabel("Default Expense Account");
		defExpense.setBounds(20, 1020, 130, 25);

		conversionLbl = new JLabel("Konversi Unit of Measurement");
		conversionLbl.setFont(new Font(null, Font.BOLD, 12));
		conversionLbl.setBounds(20, 1050, 200, 25);

		suppInfoLbl = new JLabel("<html><u>Informasi Supplier</u></html>");
		suppInfoLbl.setBounds(20, 1190, 150, 25);
		suppInfoLbl.setFont(new Font(null, Font.BOLD, 12));
		suppLbl = new JLabel("Supplier");
		suppLbl.setBounds(20, 1220, 100, 25);
		manufacturerLbl = new JLabel("Manufacturer");
		manufacturerLbl.setBounds(20, 1250, 100, 25);

		reservedSuppLbl = new JLabel("Supplier Cadangan");
		reservedSuppLbl.setFont(new Font(null, Font.BOLD, 12));
		reservedSuppLbl.setBounds(20, 1280, 150, 25);

		salesInfoLbl = new JLabel("<html><u>Sales Information</u></html>");
		salesInfoLbl.setFont(new Font(null, Font.BOLD, 12));
		salesInfoLbl.setBounds(20, 1420, 100, 25);
		flagSalesLbl = new JLabel("Flag Sales Item");
		flagSalesLbl.setBounds(20, 1450, 100, 25);
		flagProductLbl = new JLabel("Flag Produk Jasa");
		flagProductLbl.setBounds(20, 1480, 100, 25);

		defSellCostLbl = new JLabel("Default Selling Cost Center");
		defSellCostLbl.setBounds(20, 1510, 130, 25);
		defIncomeLbl = new JLabel("Default Income Account");
		defIncomeLbl.setBounds(20, 1540, 120, 25);
		maxDiscountLbl = new JLabel("Diskon Maksimum");
		maxDiscountLbl.setBounds(20, 1570, 100, 25);

		customerListLbl = new JLabel("List Customer");
		customerListLbl.setFont(new Font(null, Font.BOLD, 12));
		customerListLbl.setBounds(20, 1600, 100, 25);

		taxLbl = new JLabel("Pajak");
		taxLbl.setFont(new Font(null, Font.BOLD, 12));
		taxLbl.setBounds(20, 1740, 100, 25);

		idField = new JTextField();
		idField.setDocument(new JTextFieldLimit(18));
		// idField.setText(generateCode());
		// idField.setEnabled(false);
		idField.setBounds(220, 80, 150, 25);

		nameField = new JTextField();
		nameField.setDocument(new JTextFieldLimit(50));
		nameField.setBounds(220, 110, 150, 25);

		try {
			categories = ServiceFactory.getProductBL().getAllProductCategory();
			categories.add(0, new ProductCategory("-- Pilih Kategori Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
		catField = new ComboBox<ProductCategory>();
		catField.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (catField.getDataIndex().getId() == BALKEN_BASAH
						|| catField.getDataIndex().getId() == BALKEN_KERING) {
					typeLbl.setText("<html>Jenis Kayu <font color=\"red\">*</font></html>");
					gradeLbl.setText("<html>Grade <font color=\"red\">*</font></html>");
					thickLbl.setText("<html>Tebal <font color=\"red\">*</font></html>");
					longLbl.setText("<html>Panjang <font color=\"red\">*</font></html>");
					wideLbl.setText("<html>Lebar <font color=\"red\">*</font></html>");
				} else {
					typeLbl.setText("Jenis Kayu");
					gradeLbl.setText("Grade");
					thickLbl.setText("Tebal");
					longLbl.setText("Panjang");
					wideLbl.setText("Lebar");
				}
			}
		});
		catField.setList(categories);
		catField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (catField.getSelectedItem().toString().equals("Kayu")) {
					typeField.setEnabled(true);
					gradeField.setEnabled(true);
					thickField.setEnabled(true);
				}
			}
		});
		catField.setBounds(220, 140, 150, 25);

//		statField = new JComboBox<>();
//		statField.addItem("-- Pilih Status Produk --");
//		statField.addItem("Aktif");
//		statField.addItem("Tidak Aktif");
//		statField.setBounds(220, 170, 150, 25);

		try {
			units = ServiceFactory.getProductBL().getAllUom();
			units.add(0, new Uom("-- Pilih Satuan Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
		uomField = new ComboBox<Uom>();
		uomField.setList(units);
		uomField.setBounds(220, 170, 150, 25);

		maintain = new ButtonGroup();

		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setSelected(true);
		maintainYesField.setBounds(220, 200, 50, 25);

		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setSelected(false);
		maintainNoField.setBounds(290, 200, 50, 25);

		maintain.add(maintainYesField);
		maintain.add(maintainNoField);

		brandField = new JTextField();
		brandField.setBounds(195, 450, 150, 25);

		barcodeField = new JTextField();
		barcodeField.setBounds(195, 480, 150, 25);

		descField = new JTextArea();
		descField.setBounds(195, 510, 250, 55);

		try {
			woodTypes = ServiceFactory.getProductBL().getAllWoodType();
			woodTypes.add(0, new WoodType("-- Pilih Jenis Kayu --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
		typeField = new ComboBox<WoodType>();
		typeField.setList(woodTypes);
		typeField.setBounds(220, 290, 150, 25);

		try {
			grades = ServiceFactory.getProductBL().getAllGrade();
			grades.add(0, new Grade("-- Pilih Grade --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
		gradeField = new ComboBox<Grade>();
		gradeField.setList(grades);
		gradeField.setBounds(220, 320, 150, 25);

		thickField = new NumberField(10);
		thickField.setBounds(220, 380, 150, 25);

		longField = new NumberField(10);
		longField.setBounds(220, 350, 150, 25);

		wideField = new NumberField(10);
		wideField.setBounds(220, 410, 150, 25);

		// try {
		// conditions = ServiceFactory.getProductBL().getAllCondition();
		// conditions.add(0, new Condition("-- Pilih Kondisi --"));
		// } catch (SQLException e1) {
		// LOGGER.error(e1.getMessage());
		// }
		// conField = new ComboBox<Condition>();
		// conField.setList(conditions);
		// conField.setBounds(220, 440, 150, 25);

		minQtyField = new NumberField(5);
		minQtyField.setText("0");
		minQtyField.setBounds(220, 440, 150, 25);

		flagSerial = new ButtonGroup();

		flagAsset = new ButtonGroup();

		serialYesField = new JRadioButton("Ya");
		serialYesField.setBounds(195, 750, 50, 25);

		serialNoField = new JRadioButton("Tidak");
		serialNoField.setSelected(true);
		serialNoField.setBounds(250, 750, 50, 25);

		flagSerial.add(serialYesField);
		flagSerial.add(serialNoField);

		assetYesField = new JRadioButton("Ya");
		assetYesField.setBounds(195, 780, 50, 25);

		assetNoField = new JRadioButton("Tidak");
		assetNoField.setSelected(true);
		assetNoField.setBounds(250, 780, 50, 25);

		flagAsset.add(assetYesField);
		flagAsset.add(assetNoField);

		warrantField = new JTextField();
		warrantField.setBounds(195, 810, 150, 25);

		dayLbl = new JLabel("Hari");
		dayLbl.setBounds(350, 810, 50, 25);

		nettoField = new JTextField();
		nettoField.setBounds(195, 840, 150, 25);

		nettoUnitField = new JComboBox<>();
		nettoUnitField.addItem("Pilih");
		nettoUnitField.setBounds(350, 840, 100, 25);

		purchase = new ButtonGroup();

		purchaseYesField = new JRadioButton("Ya");
		purchaseYesField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				minOrderField.setEnabled(true);
				minOrderUnitField.setEnabled(true);
				leadTimeField.setEnabled(true);
				buyCostField.setEnabled(true);
				expenseField.setEnabled(true);
			}
		});
		purchaseYesField.setBounds(195, 900, 50, 25);

		purchaseNoField = new JRadioButton("Tidak");
		purchaseNoField.setSelected(true);
		purchaseNoField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				minOrderField.setEnabled(false);
				minOrderUnitField.setEnabled(false);
				leadTimeField.setEnabled(false);
				buyCostField.setEnabled(false);
				expenseField.setEnabled(false);
			}
		});
		purchaseNoField.setBounds(250, 900, 50, 25);

		purchase.add(purchaseYesField);
		purchase.add(purchaseNoField);

		minOrderField = new JTextField();
		minOrderField.setEnabled(false);
		minOrderField.setBounds(195, 930, 150, 25);

		minOrderUnitField = new JComboBox<>();
		minOrderUnitField.addItem("Pilih");
		minOrderUnitField.setEnabled(false);
		minOrderUnitField.setBounds(350, 930, 100, 25);

		leadTimeField = new JTextField();
		leadTimeField.setEnabled(false);
		leadTimeField.setBounds(195, 960, 150, 25);

		day2Lbl = new JLabel("Hari");
		day2Lbl.setBounds(350, 960, 50, 25);

		buyCostField = new JComboBox<>();
		buyCostField.addItem("Pilih");
		buyCostField.setEnabled(false);
		buyCostField.setBounds(195, 990, 150, 25);

		expenseField = new JComboBox<>();
		expenseField.addItem("Pilih");
		expenseField.setEnabled(false);
		expenseField.setBounds(195, 1020, 150, 25);

		uomAddBtn = new JButton("Add");
		uomAddBtn.setBounds(395, 1050, 75, 25);
		uomAddBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				uomConversionPanel = new UOMConversionPanel();
				uomConversionPanel.setVisible(true);
				// uomConversionPanel.setBounds(0, 0, 700, 600);
				MainPanel.glassPane.setVisible(true);
			}
		});

		uomDeleteBtn = new JButton("Delete");
		uomDeleteBtn.setBounds(475, 1050, 75, 25);

		productTableModel = new ProductTableModel(products);
		uomTable = new JTable(productTableModel);
		uomTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		uomTable.getTableHeader().setReorderingAllowed(false);
		uomTable.getTableHeader().setResizingAllowed(false);
		uomTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		uomTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		uomTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		uomTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		uomTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		uomScroll = new JScrollPane(uomTable);
		uomScroll.setBounds(20, 1080, 1130, 75);
		uomScroll.setBorder(new EmptyBorder(0, 0, 0, 0));

		supplierField = new JComboBox<>();
		supplierField.addItem("Pilih");
		supplierField.setBounds(195, 1220, 150, 25);

		manufacturerField = new JTextField();
		manufacturerField.setBounds(195, 1250, 150, 25);

		resSupSearchBtn = new JButton("Search");
		resSupSearchBtn.setBounds(395, 1280, 75, 25);
		resSupSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchSupplierPanel = new SearchSupplierPanel();
				searchSupplierPanel.setVisible(true);
				MainPanel.glassPane.setVisible(true);
			}
		});

		resSupDeleteBtn = new JButton("Delete");
		resSupDeleteBtn.setBounds(475, 1280, 75, 25);

		reserveSupplierTableModel = new ReserveSupplierTableModel(products);
		resSupTable = new JTable(reserveSupplierTableModel);
		resSupTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resSupTable.getTableHeader().setReorderingAllowed(false);
		resSupTable.getTableHeader().setResizingAllowed(false);
		resSupTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		resSupTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		resSupTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		resSupScroll = new JScrollPane(resSupTable);
		resSupScroll.setBounds(20, 1310, 1130, 75);
		resSupScroll.setBorder(new EmptyBorder(0, 0, 0, 0));

		sales = new ButtonGroup();

		service = new ButtonGroup();

		salesYesField = new JRadioButton("Ya");
		salesYesField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serviceYesField.setEnabled(true);
				serviceNoField.setEnabled(true);
				sellCostField.setEnabled(true);
				incomeField.setEnabled(true);
				discountField.setEnabled(true);
			}
		});
		salesYesField.setBounds(195, 1450, 50, 25);

		salesNoField = new JRadioButton("Tidak");
		salesNoField.setSelected(true);
		salesNoField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serviceYesField.setEnabled(false);
				serviceNoField.setEnabled(false);
				sellCostField.setEnabled(false);
				incomeField.setEnabled(false);
				discountField.setEnabled(false);
			}
		});
		salesNoField.setBounds(250, 1450, 50, 25);

		sales.add(salesYesField);
		sales.add(salesNoField);

		serviceYesField = new JRadioButton("Ya");
		serviceYesField.setEnabled(false);
		serviceYesField.setBounds(195, 1480, 50, 25);

		serviceNoField = new JRadioButton("Tidak");
		serviceNoField.setSelected(true);
		serviceNoField.setEnabled(false);
		serviceNoField.setBounds(250, 1480, 50, 25);

		service.add(serviceYesField);
		service.add(serviceNoField);

		sellCostField = new JComboBox<>();
		sellCostField.addItem("Pilih");
		sellCostField.setEnabled(false);
		sellCostField.setBounds(195, 1510, 150, 25);

		incomeField = new JComboBox<>();
		incomeField.addItem("Pilih");
		incomeField.setEnabled(false);
		incomeField.setBounds(195, 1540, 150, 25);

		discountField = new JTextField();
		discountField.setEnabled(false);
		discountField.setBounds(195, 1570, 150, 25);

		percentLbl = new JLabel("%");
		percentLbl.setBounds(350, 1570, 50, 25);

		custSearchBtn = new JButton("Search");
		custSearchBtn.setBounds(395, 1600, 75, 25);
		custSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchCustomerPanel = new SearchCustomerPanel();
				searchCustomerPanel.setVisible(true);
				// uomConversionPanel.setBounds(0, 0, 700, 600);
				MainPanel.glassPane.setVisible(true);
			}
		});

		custDeleteBtn = new JButton("Delete");
		custDeleteBtn.setBounds(475, 1600, 75, 25);

		customerListTableModel = new CustomerListTableModel(products);
		custTable = new JTable(customerListTableModel);
		custTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		custTable.getTableHeader().setReorderingAllowed(false);
		custTable.getTableHeader().setResizingAllowed(false);
		custTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		custTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		custTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		custScroll = new JScrollPane(custTable);
		custScroll.setBounds(20, 1630, 1130, 75);
		custScroll.setBorder(new EmptyBorder(0, 0, 0, 0));

		taxAddBtn = new JButton("Add");
		taxAddBtn.setBounds(395, 1740, 75, 25);
		taxAddBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				taxPanel = new TaxPanel();
				taxPanel.setVisible(true);
				MainPanel.glassPane.setVisible(true);
			}
		});

		taxDeleteBtn = new JButton("Delete");
		taxDeleteBtn.setBounds(475, 1740, 75, 25);

		taxTableModel = new TaxTableModel(products);
		taxTable = new JTable(taxTableModel);
		taxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		taxTable.getTableHeader().setReorderingAllowed(false);
		taxTable.getTableHeader().setResizingAllowed(false);
		taxTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		taxTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		taxTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		taxScroll = new JScrollPane(taxTable);
		taxScroll.setBounds(20, 1770, 1130, 75);
		taxScroll.setBorder(new EmptyBorder(0, 0, 0, 0));

		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(925, 550, 75, 25);
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (doValidate() == false) {
					return;
				} else {
					int response = DialogBox.showInsertChoice();
					if (response == JOptionPane.YES_OPTION) {
						// TODO Auto-generated method stub
						boolean isExists = false;
						try {
							if (ServiceFactory.getProductBL().isProductNameExists(nameField.getText()) > 0) {
								int mes = JOptionPane.showConfirmDialog(null,
										"Nama Produk sudah pernah diinput. Apakah Anda ingin tetap menyimpan data?",
										"Warning", JOptionPane.YES_NO_OPTION);
								if (mes == JOptionPane.YES_OPTION) {
									isExists = false;
								} else {
									isExists = true;
								}
							}
						} catch (SQLException e1) {
							LOGGER.error(e1.getMessage());
							DialogBox.showErrorException();
							isExists = true;
						}

						if (isExists == false) {
							doInsert();
						}
					}
				}

			}
		});

		copyFromBtn = new JButton("Copy From");
		copyFromBtn.setBounds(825, 550, 100, 25);
		copyFromBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductCopyFromDialog productCopyFromDialog = new ProductCopyFromDialog(productCreatePanel);
				productCopyFromDialog.setTitle("Detail");
				productCopyFromDialog.setLocationRelativeTo(null);
				productCopyFromDialog.setVisible(true);
			}
		});

		add(copyFromBtn);
		add(breadcrumb);
		add(backBtn);
		add(titleLbl);
		add(idLbl);
		add(nameLbl);
		add(catLbl);
		//add(statLbl);
		add(unitLbl);
		add(maintainLbl);
		// containerPnl.add(picLbl);
		// containerPnl.add(descTitleLbl);
		// containerPnl.add(brandLbl);
		// containerPnl.add(barcodeLbl);
		// containerPnl.add(descLbl);
		add(attLbl);
		add(typeLbl);
		add(gradeLbl);
		add(thickLbl);
		add(longLbl);
		add(wideLbl);
		// add(conditionLbl);
		add(minQtyLbl);
		// containerPnl.add(inventLbl);
		// containerPnl.add(flagSerialLbl);
		// containerPnl.add(flagAssetLbl);
		// containerPnl.add(warrantLbl);
		// containerPnl.add(weightLbl);
		// containerPnl.add(purchaseInfoLbl);
		// containerPnl.add(flagPurchaseLbl);
		// containerPnl.add(minOrderLbl);
		// containerPnl.add(leadTimeLbl);
		// containerPnl.add(defCostLbl);
		// containerPnl.add(defExpense);
		// containerPnl.add(conversionLbl);
		// containerPnl.add(suppInfoLbl);
		// containerPnl.add(suppLbl);
		// containerPnl.add(manufacturerLbl);
		// containerPnl.add(reservedSuppLbl);
		// containerPnl.add(salesInfoLbl);
		// containerPnl.add(flagSalesLbl);
		// containerPnl.add(flagProductLbl);
		// containerPnl.add(defSellCostLbl);
		// containerPnl.add(defIncomeLbl);
		// containerPnl.add(maxDiscountLbl);
		// containerPnl.add(customerListLbl);
		// containerPnl.add(taxLbl);
		add(idField);
		add(nameField);
		add(catField);
		//add(statField);
		add(uomField);
		add(maintainYesField);
		add(maintainNoField);
		// containerPnl.add(pathField);
		// containerPnl.add(browseBtn);
		// containerPnl.add(uploadBtn);
		// containerPnl.add(imageField);
		// containerPnl.add(brandField);
		// containerPnl.add(barcodeField);
		// containerPnl.add(descField);
		add(typeField);
		add(gradeField);
		add(thickField);
		add(longField);
		add(wideField);
		// add(conField);
		add(minQtyField);
		// containerPnl.add(serialYesField);
		// containerPnl.add(serialNoField);
		// containerPnl.add(assetYesField);
		// containerPnl.add(assetNoField);
		// containerPnl.add(warrantField);
		// containerPnl.add(dayLbl);
		// containerPnl.add(nettoField);
		// containerPnl.add(nettoUnitField);
		// containerPnl.add(purchaseYesField);
		// containerPnl.add(purchaseNoField);
		// containerPnl.add(minOrderField);
		// containerPnl.add(minOrderUnitField);
		// containerPnl.add(leadTimeField);
		// containerPnl.add(day2Lbl);
		// containerPnl.add(buyCostField);
		// containerPnl.add(expenseField);
		// containerPnl.add(uomAddBtn);
		// containerPnl.add(uomDeleteBtn);
		// containerPnl.add(uomScroll);
		// containerPnl.add(supplierField);
		// containerPnl.add(manufacturerField);
		// containerPnl.add(resSupSearchBtn);
		// containerPnl.add(resSupDeleteBtn);
		// containerPnl.add(resSupScroll);
		// containerPnl.add(salesYesField);
		// containerPnl.add(salesNoField);
		// containerPnl.add(serviceYesField);
		// containerPnl.add(serviceNoField);
		// containerPnl.add(sellCostField);
		// containerPnl.add(incomeField);
		// containerPnl.add(discountField);
		// containerPnl.add(percentLbl);
		// containerPnl.add(custSearchBtn);
		// containerPnl.add(custDeleteBtn);
		// containerPnl.add(custScroll);
		// containerPnl.add(taxAddBtn);
		// containerPnl.add(taxDeleteBtn);
		// containerPnl.add(taxScroll);
		add(saveBtn);

		idLblError = new JLabel("");
		idLblError.setForeground(Color.RED);
		idLblError.setBounds(425, 80, 225, 25);

		nameLblError = new JLabel("");
		nameLblError.setForeground(Color.RED);
		nameLblError.setBounds(425, 110, 225, 25);

		catLblError = new JLabel("");
		catLblError.setForeground(Color.RED);
		catLblError.setBounds(425, 140, 225, 25);

//		statLblError = new JLabel("");
//		statLblError.setForeground(Color.RED);
//		statLblError.setBounds(425, 170, 225, 25);

		unitLblError = new JLabel("");
		unitLblError.setForeground(Color.RED);
		unitLblError.setBounds(425, 170, 225, 25);

		maintainLblError = new JLabel("");
		maintainLblError.setForeground(Color.RED);
		maintainLblError.setBounds(425, 200, 225, 25);

		typeLblError = new JLabel("");
		typeLblError.setForeground(Color.RED);
		typeLblError.setBounds(425, 290, 225, 25);

		gradeLblError = new JLabel("");
		gradeLblError.setForeground(Color.RED);
		gradeLblError.setBounds(425, 320, 225, 25);

		thickLblError = new JLabel("");
		thickLblError.setForeground(Color.RED);
		thickLblError.setBounds(425, 380, 225, 25);

		longLblError = new JLabel("");
		longLblError.setForeground(Color.RED);
		longLblError.setBounds(425, 350, 225, 25);

		wideLblError = new JLabel("");
		wideLblError.setForeground(Color.RED);
		wideLblError.setBounds(425, 410, 225, 25);

		conditionLblError = new JLabel("");
		conditionLblError.setForeground(Color.RED);
		conditionLblError.setBounds(425, 440, 225, 25);

		minQtyLblError = new JLabel("");
		minQtyLblError.setForeground(Color.RED);
		minQtyLblError.setBounds(425, 440, 225, 25);

		add(idLblError);
		add(nameLblError);
		add(catLblError);
		//add(statLblError);
		add(unitLblError);
		add(maintainLblError);
		add(typeLblError);
		add(gradeLblError);
		add(thickLblError);
		add(longLblError);
		add(wideLblError);
		add(conditionLblError);
		add(minQtyLblError);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				idField.requestFocusInWindow();
			}
		});

	}

	static final int BALKEN_BASAH = 1;
	static final int BALKEN_KERING = 2;

	public void doInsert() {
		try {
			Product product = new Product();

			product.setProductCode(idField.getText());
			product.setProductName(nameField.getText());
			product.setProductCat(catField.getSelectedIndex());
//			if (statField.getSelectedIndex() != 0) {
//				product.setProductStat(statField.getSelectedItem().toString());
//			} else {
//				product.setProductStat(null);
//			}
			product.setProductUom(uomField.getDataIndex().getId());

			if (maintainYesField.isSelected())
				product.setIsMaintain(0);
			else
				product.setIsMaintain(1);

			if (product.getProductCat() == BALKEN_BASAH || product.getProductCat() == BALKEN_KERING) {
				product.setWoodType(typeField.getDataIndex().getId());
				product.setGrade(gradeField.getDataIndex().getId());
				product.setThickness(Double.parseDouble(thickField.getText()));
				product.setLength(Double.parseDouble(longField.getText()));
				product.setWidth(Double.parseDouble(wideField.getText()));
			}

			product.setMinQy(Integer.parseInt(minQtyField.getText()));

			ServiceFactory.getProductBL().save(product);
			DialogBox.showInsert();
			MainPanel.changePanel("module.product.ui.ProductListPanel");
		} catch (Exception e) {
			DialogBox.showErrorException();
			e.printStackTrace();
		}
	}

	private JLabel idLblError;
	private JLabel nameLblError;
	private JLabel catLblError;
	private JLabel statLblError;
	private JLabel unitLblError;
	private JLabel maintainLblError;
	private JLabel typeLblError;
	private JLabel gradeLblError;
	private JLabel thickLblError;
	private JLabel longLblError;
	private JLabel wideLblError;
	private JLabel conditionLblError;
	private JLabel minQtyLblError;

	public boolean doValidate() {

		boolean isValid = true;

		nameLblError.setText("");
		catLblError.setText("");
		//statLblError.setText("");
		unitLblError.setText("");
		maintainLblError.setText("");
		typeLblError.setText("");
		gradeLblError.setText("");
		thickLblError.setText("");
		longLblError.setText("");
		wideLblError.setText("");
		conditionLblError.setText("");
		minQtyLblError.setText("");

		if (idField.getText() == null || idField.getText().length() == 0) {
			idLblError.setText("Kode Produk harus diisi");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getProductBL().isProductCodeExists(idField.getText()) > 0) {
					idLblError.setText("Kode Produk sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		if (nameField.getText() == null || nameField.getText().equals("")) {
			nameLblError.setText("Nama Produk harus diisi");
			isValid = false;
		}

		if (catField.getSelectedItem().toString() == "Pilih" || catField.getSelectedIndex() == 0) {
			catLblError.setText("Kategori Produk harus dipilih");
			isValid = false;
		}

//		if (statField.getSelectedItem().toString() == "Pilih" || statField.getSelectedIndex() == 0) {
//			statLblError.setText("Status Produk harus dipilih");
//			isValid = false;
//		}

		if (uomField.getSelectedItem().toString() == "Pilih" || uomField.getSelectedIndex() == 0) {
			unitLblError.setText("Satuan Produk harus dipilih");
			isValid = false;
		}

		if (catField.getDataIndex().getId() == BALKEN_BASAH || catField.getDataIndex().getId() == BALKEN_KERING) {

			if (typeField.getSelectedItem().toString() == "Pilih" || typeField.getSelectedIndex() == 0) {
				typeLblError.setText("Jenis Kayu harus dipilih");
				isValid = false;
			}

			if (gradeField.getSelectedItem().toString() == "Pilih" || gradeField.getSelectedIndex() == 0) {
				gradeLblError.setText("Grade Produk harus dipilih");
				isValid = false;
			}

			if (longField.getText() == null || longField.getText().equals("")) {
				longLblError.setText("Panjang Produk harus diisi");
				isValid = false;
			}
			if (wideField.getText() == null || wideField.getText().equals("")) {
				wideLblError.setText("Lebar Produk harus diisi");
				isValid = false;
			}

			if (thickField.getText() == null || thickField.getText().equals("")) {
				thickLblError.setText("Tebal Produk harus diisi");
				isValid = false;
			}
		}

		if (minQtyField.getText() == null || minQtyField.getText().equals("")) {
			minQtyLblError.setText("Minimal Qty Produk harus diisi");
			isValid = false;
		}

		// if (conField.getSelectedItem().toString() == "Pilih" ||
		// conField.getSelectedIndex() == 0) {
		// conditionLblError.setText("Kondisi Produk harus dipilih");
		// isValid = false;
		// }

		return isValid;
	}

	class ProductTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq = 0;

		public ProductTableModel(List<Product> products) {
			this.products = products;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return 0;
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 5;
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
			Product p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return seq = rowIndex + 1;
			case 1:
				return p.getProductId();
			case 2:
				return p.getProductName();
			case 3:
				return p.getProductCat();
			case 4:
				return "<html><u>Edit</u></html>";
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
				return " ";
			case 1:
				return "Uom Acuan";
			case 2:
				return "Jumlah";
			case 3:
				return "Uom Tujuan";
			case 4:
				return "Action";
			default:
				return "";
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}

		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			default:
				return String.class;
			}
		}

	}

	class ReserveSupplierTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq = 0;

		public ReserveSupplierTableModel(List<Product> products) {
			this.products = products;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return 0;
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
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
			Product p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return seq = rowIndex + 1;
			case 1:
				return p.getProductId();
			case 2:
				return p.getProductName();
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
				return " ";
			case 1:
				return "Kode Supplier";
			case 2:
				return "Nama Supplier";
			default:
				return "";
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}

	}

	class CustomerListTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq = 0;

		public CustomerListTableModel(List<Product> products) {
			this.products = products;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return 0;
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
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
			Product p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return seq = rowIndex + 1;
			case 1:
				return p.getProductId();
			case 2:
				return p.getProductName();
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
				return " ";
			case 1:
				return "Kode Customer";
			case 2:
				return "Nama Customer";
			default:
				return "";
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}

	}

	class TaxTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq = 0;

		public TaxTableModel(List<Product> products) {
			this.products = products;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return 0;
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
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
			Product p = products.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return seq = rowIndex + 1;
			case 1:
				return p.getProductId();
			case 2:
				return p.getProductName();
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
				return " ";
			case 1:
				return "Pajak";
			case 2:
				return "Nilai Pajak";
			default:
				return "";
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}

	}

	public String generateCode() {
		String productCode = null;
		int code;

		try {
			products = ServiceFactory.getProductBL().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (products.size() == 0) {
			productCode = "PRD001";
		} else if (products.size() > 0 && products.size() < 10) {
			productCode = "PRD00" + String.valueOf(products.size() + 1);
		} else if (products.size() > 10 && products.size() < 100) {
			productCode = "PRD0" + String.valueOf(products.size() + 1);
		} else if (products.size() > 100 && products.size() < 1000) {
			productCode = "PRD" + String.valueOf(products.size() + 1);
		}
		return productCode;
	}

	protected void loadData(String productCode) {
		try {
			product = ServiceFactory.getProductBL().getProductByCode(productCode);

			if (product != null) {
				nameField.setText(product.getProductName());
//				if (product.getProductStat() == null) {
//					statField.setSelectedIndex(0);
//				} else if (product.getProductStat().toUpperCase().equals("aktif".toUpperCase())) {
//					statField.setSelectedIndex(1);
//				} else if (product.getProductStat().toUpperCase().equals("tidak aktif".toUpperCase())) {
//					statField.setSelectedIndex(2);
//				}
				uomField.setSelectedIndex(product.getProductUom());

				typeField.setSelectedIndex(product.getWoodType());
				gradeField.setSelectedIndex(product.getGrade());
				thickField.setText(String.valueOf(product.getThickness()));
				longField.setText(String.valueOf(product.getLength()));
				wideField.setText(String.valueOf(product.getWidth()));
				// conField.setSelectedIndex(product.getCondition());
				minQtyField.setText(String.valueOf(product.getMinQy()));
				if (product.getIsMaintain() == 0) {
					maintainYesField.setSelected(true);
				} else {
					maintainNoField.setSelected(true);
				}

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

}
