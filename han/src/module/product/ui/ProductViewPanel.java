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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.pembelian.model.WoodType;
import module.product.model.Condition;
import module.product.model.Grade;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.supplier.model.Supplier;
import module.util.Bridging;



public class ProductViewPanel extends JPanel implements Bridging {

	/**
	 * Create the panel.
	 */
	private JLabel breadcrumb;
	private JButton backBtn;
	private JLabel titleLbl;
	
	private JLabel idLbl;
	private JLabel nameLbl;
	private JLabel catLbl;
	private JLabel statLbl;
	private JLabel unitLbl;
	private JLabel maintainLbl;
	private JLabel picLbl;
	
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
	private JLabel conditionLbl;
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
	public JComboBox<String> catField;
	public JComboBox<String> statField;
	public JComboBox<String> uomField;
	public ButtonGroup maintain;
	public JRadioButton maintainYesField;
	public JRadioButton maintainNoField;
	public JTextField pathField;
	public JButton browseBtn;
	public JButton uploadBtn;
	public JLabel imageField;
	
	public JTextField brandField;
	public JTextField barcodeField;
	public JTextArea descField;
	
	public JComboBox<String> typeField;
	public JComboBox<String> gradeField;
	public JTextField thickField;
	public JTextField longField;
	public JTextField wideField;
	public JComboBox<String> conField;
	public JTextField minQtyField;
	
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
	
	public JButton printBtn;
	public JButton deleteBtn;
	public JButton editBtn;
	
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
	
	Date todayDate;
	//SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy-MM-dd);
	
	@Override
	public void invokeObjects(Object... objects) {
		this.product = (Product) objects[0];

		loadData(product.getProductCode());
	}
	
	public ProductViewPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(1080, 675));
		
		todayDate = new Date();
		todayDate.getTime();
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1166, 1950));
		containerPnl.setLayout(null);
		
//		scrollPane = new JScrollPane(containerPnl);
//		scrollPane.setBounds(0,0,1166,630);
//		add(scrollPane);
		
		breadcrumb = new JLabel("ERP > Pembelian > Produk");
		breadcrumb.setFont(new Font(null, Font.BOLD, 12));
		breadcrumb.setBounds(20, 10, 320, 30);
		
//		backBtn = new JButton("X");
//		backBtn.setBounds(1100, 5, 40, 25);
//		backBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.product.ui.ProductListPanel");
//			}
//		});
//		backBtn.setHorizontalAlignment(SwingConstants.CENTER);
		
		titleLbl = new JLabel("VIEW DETAIL");
		titleLbl.setBounds(20, 40, 300, 30);
		titleLbl.setFont(new Font(null, Font.BOLD, 12));
		
		idLbl = new JLabel("Kode Produk");
		idLbl.setBounds(20, 80, 100, 30);
		nameLbl = new JLabel("Nama Produk");
		nameLbl.setBounds(20, 110, 100, 30);
		catLbl = new JLabel("Kategori Produk");
		catLbl.setBounds(20, 140, 100, 30);
		statLbl = new JLabel("Status Produk");
		statLbl.setBounds(20, 170, 100, 30);
		unitLbl = new JLabel("Satuan Produk");
		unitLbl.setBounds(20, 200, 100, 30);
		maintainLbl = new JLabel("Maintain Stock");
		maintainLbl.setBounds(20, 230, 100, 30);
		picLbl = new JLabel("Gambar");
		picLbl.setBounds(20, 260, 100, 30);
		
		descTitleLbl = new JLabel("<html><u>Description</u></html>");
		descTitleLbl.setBounds(20, 420, 100, 30);
		descTitleLbl.setFont(new Font(null, Font.BOLD,  12));
		brandLbl = new JLabel("Brand");
		brandLbl.setBounds(20, 450, 100, 30);
		barcodeLbl = new JLabel("Barcode");
		barcodeLbl.setBounds(20, 480, 100, 30);
		descLbl = new JLabel("Deskripsi");
		descLbl.setBounds(20, 510, 100, 30);
		
		attLbl = new JLabel("<html><u>Atribut Produk</u></html>");
		attLbl.setBounds(20, 260, 100, 30);
		attLbl.setFont(new Font(null, Font.BOLD, 12));
		typeLbl = new JLabel("Jenis Kayu");
		typeLbl.setBounds(20, 290, 100, 30);
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(20, 320, 100, 30);
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(20, 380, 100, 30);
		longLbl = new JLabel("Panjang");
		longLbl.setBounds(20, 350, 100, 30);
		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(20, 410, 100, 30);
		conditionLbl = new JLabel("Kondisi");
		conditionLbl.setBounds(20, 440, 100, 30);
		minQtyLbl = new JLabel("Minimum Qty");
		minQtyLbl.setBounds(20, 470, 100, 30);
		
		inventLbl = new JLabel("<html><u>Inventory</u></html>");
		inventLbl.setBounds(20, 720, 100, 30);
		inventLbl.setFont(new Font(null, Font.BOLD, 12));
		flagSerialLbl = new JLabel("Flag Serial No");
		flagSerialLbl.setBounds(20, 750, 100, 30);
		flagAssetLbl = new JLabel("Flag Aktiva Tetap");
		flagAssetLbl.setBounds(20, 780, 100, 30);
		warrantLbl = new JLabel("Garansi");
		warrantLbl.setBounds(20, 810, 100, 30);
		weightLbl = new JLabel("Berat Netto");
		weightLbl.setBounds(20, 840, 100, 30);
		
		purchaseInfoLbl = new JLabel("<html><u>Purchase Information</u></html>");
		purchaseInfoLbl.setBounds(20, 870, 150, 30);
		purchaseInfoLbl.setFont(new Font(null, Font.BOLD, 12));
		flagPurchaseLbl = new JLabel("Flag Purchase Item");
		flagPurchaseLbl.setBounds(20, 900, 100, 30);
		minOrderLbl = new JLabel("Minimum Order");
		minOrderLbl.setBounds(20, 930, 100, 30);
		leadTimeLbl = new JLabel("Lead Time");
		leadTimeLbl.setBounds(20, 960, 100, 30);
		
		defCostLbl = new JLabel("Default Buying Cost Center");
		defCostLbl.setBounds(20, 990, 140, 30);
		defExpense = new JLabel("Default Expense Account");
		defExpense.setBounds(20, 1020, 130, 30);
		
		conversionLbl = new JLabel("Konversi Unit of Measurement");
		conversionLbl.setFont(new Font(null, Font.BOLD, 12));
		conversionLbl.setBounds(20, 1050, 200, 30);
		
		suppInfoLbl = new JLabel("<html><u>Informasi Supplier</u></html>");
		suppInfoLbl.setBounds(20, 1190, 150, 30);
		suppInfoLbl.setFont(new Font(null, Font.BOLD, 12));
		suppLbl = new JLabel("Supplier");
		suppLbl.setBounds(20, 1220, 100, 30);
		manufacturerLbl = new JLabel("Manufacturer");
		manufacturerLbl.setBounds(20, 1250, 100, 30);
		
		reservedSuppLbl = new JLabel("Supplier Cadangan");
		reservedSuppLbl.setFont(new Font(null, Font.BOLD, 12));
		reservedSuppLbl.setBounds(20, 1280, 150, 30);
		
		salesInfoLbl = new JLabel("<html><u>Sales Information</u></html>");
		salesInfoLbl.setFont(new Font(null, Font.BOLD, 12));
		salesInfoLbl.setBounds(20, 1420, 100, 30);
		flagSalesLbl = new JLabel("Flag Sales Item");
		flagSalesLbl.setBounds(20, 1450, 100, 30);
		flagProductLbl = new JLabel("Flag Produk Jasa");
		flagProductLbl.setBounds(20, 1480, 100, 30);
		
		defSellCostLbl = new JLabel("Default Selling Cost Center");
		defSellCostLbl.setBounds(20, 1510, 130, 30);
		defIncomeLbl = new JLabel("Default Income Account");
		defIncomeLbl.setBounds(20, 1540, 120, 30);
		maxDiscountLbl = new JLabel("Diskon Maksimum");
		maxDiscountLbl.setBounds(20, 1570, 100, 30);
		
		customerListLbl = new JLabel("List Customer");
		customerListLbl.setFont(new Font(null, Font.BOLD, 12));
		customerListLbl.setBounds(20, 1600, 100, 30);
		
		taxLbl = new JLabel("Pajak");
		taxLbl.setFont(new Font(null, Font.BOLD, 12));
		taxLbl.setBounds(20, 1740, 100, 30);
		
		idField = new JTextField();
		idField.setEnabled(false);
		idField.setBounds(195, 80, 150, 25);
		
		nameField = new JTextField();
		nameField.setEnabled(false);
		nameField.setBounds(195, 110, 150, 25);
		
		try {
			categories = ServiceFactory.getProductBL().getAllProductCategory();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catField = new JComboBox<>();
		catField.addItem("Pilih");
		for(int i=0; i<categories.size(); i++){
			catField.addItem(categories.get(i).getProductCategory());
		}
		catField.setEnabled(false);
		catField.setBounds(195, 140, 150, 25);
		
		statField = new JComboBox<>();
		statField.addItem("Pilih");
		statField.addItem("Aktif");
		statField.addItem("Tidak Aktif");
		statField.setEnabled(false);
		statField.setBounds(195, 170, 150, 25);
		
		try {
			units = ServiceFactory.getProductBL().getAllUom();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		uomField = new JComboBox<>();
		uomField.addItem("Pilih");
		for(int i=0; i<units.size(); i++){
			uomField.addItem(units.get(i).getUom());
		}
		uomField.setEnabled(false);
		uomField.setBounds(195, 200, 150, 25);
		
		maintain = new ButtonGroup();
		
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setEnabled(false);
		maintainYesField.setBounds(195, 230, 50, 25);
		
		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setEnabled(false);
		maintainNoField.setBounds(250, 230, 50, 25);
		
		maintain.add(maintainYesField);
		maintain.add(maintainNoField);
		
		pathField = new JTextField();
		pathField.setEnabled(false);
		pathField.setBounds(195, 260, 150, 25);
		
		browseBtn = new JButton("Browse");
		browseBtn.setEnabled(false);
		browseBtn.setBounds(345, 260, 75, 25);
		
		uploadBtn = new JButton("Upload");
		uploadBtn.setEnabled(false);
		uploadBtn.setBounds(425, 260, 75, 25);
		
		imageField = new JLabel();
		imageField.setBounds(20, 290, 300, 125);
		imageField.setBorder(new LineBorder(Color.black));
		
		brandField = new JTextField();
		brandField.setEnabled(false);
		brandField.setBounds(195, 450, 150, 25);
		
		barcodeField = new JTextField();
		barcodeField.setEnabled(false);
		barcodeField.setBounds(195, 480, 150, 25);
		
		descField = new JTextArea();
		descField.setEnabled(false);
		descField.setBounds(195, 510, 250, 55);
		
		try {
			woodTypes = ServiceFactory.getProductBL().getAllWoodType();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		typeField = new JComboBox<>();
		typeField.addItem("Pilih");
		for(int i=0; i<woodTypes.size(); i++){
			typeField.addItem(woodTypes.get(i).getWoodType());
		}
		typeField.setEnabled(false);
		typeField.setBounds(195, 290, 150, 25);
		
		try {
			grades = ServiceFactory.getProductBL().getAllGrade();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gradeField = new JComboBox<>();
		gradeField.addItem("Pilih");
		for(int i=0; i<grades.size(); i++){
			gradeField.addItem(grades.get(i).getGrade());
		}
		gradeField.setEnabled(false);
		gradeField.setBounds(195, 320, 150, 25);
		
		thickField = new JTextField();
		thickField.setEnabled(false);
		thickField.setBounds(195, 380, 150, 25);
		
		longField = new JTextField();
		longField.setEnabled(false);
		longField.setBounds(195, 350, 150, 25);
		
		wideField = new JTextField();
		wideField.setEnabled(false);
		wideField.setBounds(195, 410, 150, 25);
		
		try {
			conditions = ServiceFactory.getProductBL().getAllCondition();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conField = new JComboBox<>();
		conField.addItem("Pilih");
		for(int i=0; i<conditions.size(); i++){
			conField.addItem(conditions.get(i).getCondition());
		}
		conField.setEnabled(false);
		conField.setBounds(195, 440, 150, 25);
		
		minQtyField = new JTextField();
		minQtyField.setEnabled(false);
		minQtyField.setBounds(195, 470, 150, 25);
		
		flagSerial = new ButtonGroup();
		
		flagAsset = new ButtonGroup();
		
		serialYesField = new JRadioButton("Ya");
		serialYesField.setEnabled(false);
		serialYesField.setBounds(195, 750, 50, 25);
		
		serialNoField = new JRadioButton("Tidak");
		serialNoField.setEnabled(false);
		serialNoField.setBounds(250, 750, 50, 25);
		
		flagSerial.add(serialYesField);
		flagSerial.add(serialNoField);
		
		assetYesField = new JRadioButton("Ya");
		assetYesField.setEnabled(false);
		assetYesField.setBounds(195, 780, 50, 25);
		
		assetNoField = new JRadioButton("Tidak");
		assetNoField.setEnabled(false);
		assetNoField.setBounds(250, 780, 50, 25);
		
		flagAsset.add(assetYesField);
		flagAsset.add(assetNoField);
		
		warrantField = new JTextField();
		warrantField.setEnabled(false);
		warrantField.setBounds(195, 810, 150, 25);
		
		dayLbl = new JLabel("Hari");
		dayLbl.setBounds(350, 810, 50, 30);
		
		nettoField = new JTextField();
		nettoField.setEnabled(false);
		nettoField.setBounds(195, 840, 150, 25);
		
		nettoUnitField = new JComboBox<>();
		nettoUnitField.setEnabled(false);
		nettoUnitField.setBounds(350, 840, 100, 25);
		
		purchase = new ButtonGroup();
		
		purchaseYesField = new JRadioButton("Ya");
		purchaseYesField.setEnabled(false);
		purchaseYesField.setBounds(195, 900, 50, 25);
		
		purchaseNoField = new JRadioButton("Tidak");
		purchaseNoField.setEnabled(false);
		purchaseNoField.setBounds(250, 900, 50, 25);
		
		purchase.add(purchaseYesField);
		purchase.add(purchaseNoField);
		
		minOrderField = new JTextField();
		minOrderField.setEnabled(false);
		minOrderField.setBounds(195, 930, 150, 25);
		
		minOrderUnitField = new JComboBox<>();
		minOrderUnitField.setEnabled(false);
		minOrderUnitField.setBounds(350, 930, 100, 25);
		
		leadTimeField = new JTextField();
		leadTimeField.setEnabled(false);
		leadTimeField.setBounds(195, 960, 150, 25);
		
		day2Lbl = new JLabel("Hari");
		day2Lbl.setBounds(350, 960, 50, 30);
		
		buyCostField = new JComboBox<>();
		buyCostField.setEnabled(false);
		buyCostField.setBounds(195, 990, 150, 25);
		
		expenseField = new JComboBox<>();
		expenseField.setEnabled(false);
		expenseField.setBounds(195, 1020, 150, 25);
		
		uomAddBtn = new JButton("Add");
		uomAddBtn.setEnabled(false);
		uomAddBtn.setBounds(395, 1050, 75, 25);
		
		uomDeleteBtn = new JButton("Delete");
		uomDeleteBtn.setEnabled(false);
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
		uomScroll= new JScrollPane(uomTable);
		uomScroll.setBounds(20, 1080, 1130, 75);
		uomScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		supplierField = new JComboBox<>();
		supplierField.setEnabled(false);
		supplierField.setBounds(195, 1220, 150, 25);
		
		manufacturerField = new JTextField();
		manufacturerField.setEnabled(false);
		manufacturerField.setBounds(195, 1250, 150, 25);
		
		resSupSearchBtn = new JButton("Search");
		resSupSearchBtn.setEnabled(false);
		resSupSearchBtn.setBounds(395, 1280, 75, 25);
		
		resSupDeleteBtn = new JButton("Delete");
		resSupDeleteBtn.setEnabled(false);
		resSupDeleteBtn.setBounds(475, 1280, 75, 25);
		
		reserveSupplierTableModel = new ReserveSupplierTableModel(products);
		resSupTable = new JTable(reserveSupplierTableModel);
		resSupTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resSupTable.getTableHeader().setReorderingAllowed(false);
		resSupTable.getTableHeader().setResizingAllowed(false);
		resSupTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		resSupTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		resSupTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		resSupScroll= new JScrollPane(resSupTable);
		resSupScroll.setBounds(20, 1310, 1130, 75);
		resSupScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		sales = new ButtonGroup();
		
		service = new ButtonGroup();
		
		salesYesField = new JRadioButton("Ya");
		salesYesField.setEnabled(false);
		salesYesField.setBounds(195, 1450, 50, 25);
		
		salesNoField = new JRadioButton("Tidak");
		salesNoField.setEnabled(false);
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
		sellCostField.setEnabled(false);
		sellCostField.setBounds(195, 1510, 150, 25);
		
		incomeField = new JComboBox<>();
		incomeField.setEnabled(false);
		incomeField.setBounds(195, 1540, 150, 25);
		
		discountField = new JTextField();
		discountField.setEnabled(false);
		discountField.setBounds(195, 1570, 150, 25);
		
		percentLbl = new JLabel("%");
		percentLbl.setBounds(350, 1570, 50, 30);
		
		custSearchBtn = new JButton("Search");
		custSearchBtn.setEnabled(false);
		custSearchBtn.setBounds(395, 1600, 75, 25);
		custSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchCustomerPanel = new SearchCustomerPanel();
				searchCustomerPanel.setVisible(true);
				//uomConversionPanel.setBounds(0, 0, 700, 600);
				MainPanel.glassPane.setVisible(true);
			}
		});
		
		custDeleteBtn = new JButton("Delete");
		custDeleteBtn.setEnabled(false);
		custDeleteBtn.setBounds(475, 1600, 75, 25);
		
		customerListTableModel = new CustomerListTableModel(products);
		custTable = new JTable(customerListTableModel);
		custTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		custTable.getTableHeader().setReorderingAllowed(false);
		custTable.getTableHeader().setResizingAllowed(false);
		custTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		custTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		custTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		custScroll= new JScrollPane(custTable);
		custScroll.setBounds(20, 1630, 1130, 75);
		custScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		taxAddBtn = new JButton("Add");
		taxAddBtn.setEnabled(false);
		taxAddBtn.setBounds(395, 1740, 75, 25);
		
		taxDeleteBtn = new JButton("Delete");
		taxDeleteBtn.setEnabled(false);
		taxDeleteBtn.setBounds(475, 1740, 75, 25);
		
		taxTableModel = new TaxTableModel(products);
		taxTable = new JTable(taxTableModel);
		taxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		taxTable.getTableHeader().setReorderingAllowed(false);
		taxTable.getTableHeader().setResizingAllowed(false);
		taxTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		taxTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		taxTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		taxScroll= new JScrollPane(taxTable);
		taxScroll.setBounds(20, 1770, 1130, 75);
		taxScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		printBtn = new JButton("Cetak");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		printBtn.setBounds(745, 550, 75, 25);
		
		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(825, 550, 75, 25);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int dialogResult = JOptionPane.showConfirmDialog (null, "Apakah Anda ingin menghapus data?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					doDelete();
				}
			}
		});
		
		editBtn = new JButton("Ubah");
		editBtn.setBounds(905, 550, 75, 25);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				product = new Product();
				
				product.setProductCode(idField.getText());
				MainPanel.changePanel("module.product.ui.ProductEditPanel", product);
			}
		});
		
		add(breadcrumb);
		//add(backBtn);
		add(titleLbl);
		add(idLbl);
		add(nameLbl);
		add(catLbl);
		add(statLbl);
		add(unitLbl);
		add(maintainLbl);
//		containerPnl.add(picLbl);
//	    containerPnl.add(descTitleLbl);
//	    containerPnl.add(brandLbl);
//	    containerPnl.add(barcodeLbl);
//	    containerPnl.add(descLbl);
	    add(attLbl);
	    add(typeLbl);
	    add(gradeLbl);
	    add(thickLbl);
	    add(longLbl);
	    add(wideLbl);
	    add(conditionLbl);
	    add(minQtyLbl);
//	    containerPnl.add(inventLbl);
//	    containerPnl.add(flagSerialLbl);
//	    containerPnl.add(flagAssetLbl);
//	    containerPnl.add(warrantLbl);
//	    containerPnl.add(weightLbl);
//	    containerPnl.add(purchaseInfoLbl);
//	    containerPnl.add(flagPurchaseLbl);
//	    containerPnl.add(minOrderLbl);
//	    containerPnl.add(leadTimeLbl);
//	    containerPnl.add(defCostLbl);
//	    containerPnl.add(defExpense);
//	    containerPnl.add(conversionLbl);
//	    containerPnl.add(suppInfoLbl);
//	    containerPnl.add(suppLbl);
//	    containerPnl.add(manufacturerLbl);
//	    containerPnl.add(reservedSuppLbl);
//	    containerPnl.add(salesInfoLbl);
//	    containerPnl.add(flagSalesLbl);
//	    containerPnl.add(flagProductLbl);
//	    containerPnl.add(defSellCostLbl);
//	    containerPnl.add(defIncomeLbl);
//	    containerPnl.add(maxDiscountLbl);
//	    containerPnl.add(customerListLbl);
//	    containerPnl.add(taxLbl);
	    add(idField);
	    add(nameField);
	    add(catField);
	    add(statField);
	    add(uomField);
	    add(maintainYesField);
	    add(maintainNoField);
//	    containerPnl.add(pathField);
//	    containerPnl.add(browseBtn);
//	    containerPnl.add(uploadBtn);
//	    containerPnl.add(imageField);
//	    containerPnl.add(brandField);
//	    containerPnl.add(barcodeField);
//	    containerPnl.add(descField);
	    add(typeField);
	    add(gradeField);
	    add(thickField);
	    add(longField);
	    add(wideField);
	    add(conField);
	    add(minQtyField);
//	    containerPnl.add(serialYesField);
//	    containerPnl.add(serialNoField);
//	    containerPnl.add(assetYesField);
//	    containerPnl.add(assetNoField);
//	    containerPnl.add(warrantField);
//	    containerPnl.add(dayLbl);
//	    containerPnl.add(nettoField);
//	    containerPnl.add(nettoUnitField);
//	    containerPnl.add(purchaseYesField);
//	    containerPnl.add(purchaseNoField);
//	    containerPnl.add(minOrderField);
//	    containerPnl.add(minOrderUnitField);
//	    containerPnl.add(leadTimeField);
//	    containerPnl.add(day2Lbl);
//	    containerPnl.add(buyCostField);
//	    containerPnl.add(expenseField);
//	    containerPnl.add(uomAddBtn);
//	    containerPnl.add(uomDeleteBtn);
//	    containerPnl.add(uomScroll);
//	    containerPnl.add(supplierField);
//	    containerPnl.add(manufacturerField);
//	    containerPnl.add(resSupSearchBtn);
//	    containerPnl.add(resSupDeleteBtn);
//	    containerPnl.add(resSupScroll);
//	    containerPnl.add(salesYesField);
//	    containerPnl.add(salesNoField);
//	    containerPnl.add(serviceYesField);
//	    containerPnl.add(serviceNoField);
//	    containerPnl.add(sellCostField);
//	    containerPnl.add(incomeField);
//	    containerPnl.add(discountField);
//	    containerPnl.add(percentLbl);
//	    containerPnl.add(custSearchBtn);
//	    containerPnl.add(custDeleteBtn);
//	    containerPnl.add(custScroll);
//	    containerPnl.add(taxAddBtn);
//	    containerPnl.add(taxDeleteBtn);
//	    containerPnl.add(taxScroll);
	    add(printBtn);
	    add(deleteBtn);
	    add(editBtn);
		
	}
	
	protected void doDelete() {
		try {
			Product deleteProduct = new Product();
			deleteProduct.setDeletedDate(todayDate);
			deleteProduct.setDeletedBy("Irvan");
			deleteProduct.setProductCode(idField.getText());			
			
			ServiceFactory.getProductBL().delete(deleteProduct);
			DialogBox.showDelete();
			MainPanel.changePanel("module.product.ui.ProductListPanel");
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}
	
	protected void loadData(String productCode) {
		try {
			product = ServiceFactory.getProductBL().getProductByCode(productCode);

			if (product != null) {
				idField.setText(product.getProductCode());
				nameField.setText(product.getProductName());
				catField.setSelectedIndex(product.getProductCat());
				if(product.getProductStat() == null){
					statField.setSelectedIndex(0);
				}else if(product.getProductStat().toUpperCase().equals("aktif".toUpperCase())){
					statField.setSelectedIndex(1);
				}else{
					statField.setSelectedIndex(2);
				}
				uomField.setSelectedIndex(product.getProductUom());
//				pathField.setText(product.getImagePath());
//				brandField.setText(product.getBrand());
//				barcodeField.setText(product.getBarcode());
//				descField.setText(product.getDescription());
				typeField.setSelectedIndex(product.getWoodType());
				gradeField.setSelectedIndex(product.getGrade());
				thickField.setText(String.valueOf(product.getThickness()));
				longField.setText(String.valueOf(product.getLength()));
				wideField.setText(String.valueOf(product.getWidth()));
				conField.setSelectedIndex(product.getCondition());
				minQtyField.setText(String.valueOf(product.getMinQy()));
//				warrantField.setText(String.valueOf(product.getWarranty()));
//				nettoField.setText(String.valueOf(product.getNetto()));
//				nettoUnitField.addItem(String.valueOf(product.getNettoUom()));
//				minOrderField.setText(String.valueOf(product.getMinor()));
//				minOrderUnitField.addItem(String.valueOf(product.getMinorUom()));
//				leadTimeField.setText(String.valueOf(product.getLeadTime()));
//				buyCostField.addItem(String.valueOf(product.getBuyCost()));
//				expenseField.addItem(String.valueOf(product.getExpense()));
//				supplierField.addItem(product.getMainSuppCode());
//				manufacturerField.setText(product.getManufacturer());
//				sellCostField.addItem(String.valueOf(product.getSellCost()));
//				incomeField.addItem(String.valueOf(product.getIncome()));
//				discountField.setText(String.valueOf(product.getMaxDisc()));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}
	
	class ProductTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq=0;

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
				return seq = rowIndex+1;
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
			//all cells false
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
		int seq=0;

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
				return seq = rowIndex+1;
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
			//all cells false
			return false;
		}

	}
	
	class CustomerListTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq=0;

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
				return seq = rowIndex+1;
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
			//all cells false
			return false;
		}

	}
	
	class TaxTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> products;
		int seq=0;

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
				return seq = rowIndex+1;
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
			//all cells false
			return false;
		}

	}

}
