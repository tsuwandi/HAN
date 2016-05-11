package module.product.ui;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
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
	private JLabel conditionLbl;
	
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
	public JFileChooser picField;
	public JPanel imageField;
	
	public JTextField brandField;
	public JTextField barcodeField;
	public JTextArea descField;
	
	public JComboBox<String> typeField;
	public JComboBox<String> gradeField;
	public JComboBox<String> thickField;
	public JComboBox<String> conField;
	
	public ButtonGroup flagSerial;
	public ButtonGroup flagAsset;
	public JRadioButton serialYesField;
	public JRadioButton serialNoField;
	public JRadioButton assetYesField;
	public JRadioButton assetNoField;
	public JTextField warrantField;
	public JTextField nettoField;
	public JComboBox<String> nettoUnitField;
	
	public ButtonGroup purchase;
	public JRadioButton purchaseYesField;
	public JRadioButton purchaseNoField;
	public JTextField minOrderField;
	public JComboBox<String> minOrderUnitField;
	public JTextField leadTimeField;
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
	
	public JButton custSearchBtn;
	public JButton custDeleteBtn;
	public JTable custTable;
	
	public JButton taxAddBtn;
	public JButton taxDeleteBtn;
	public JTable taxTable;
	
	public JButton saveBtn;
	
	public CreateProductPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(1166, 2000));
		
		titleLbl = new JLabel("CREATE NEW");
		titleLbl.setBounds(20, 20, 200, 50);
		
		idLbl = new JLabel("Kode Produk");
		idLbl.setBounds(20, 80, 100, 30);
		nameLbl = new JLabel("Nama Produk");
		nameLbl.setBounds(20, 20, 200, 50);
		catLbl = new JLabel("Kategori Produk");
		catLbl.setBounds(20, 20, 200, 50);
		statLbl = new JLabel("Status Produk");
		statLbl.setBounds(20, 20, 200, 50);
		unitLbl = new JLabel("Satuan Produk");
		unitLbl.setBounds(20, 20, 200, 50);
		maintainLbl = new JLabel("Maintain Stock");
		maintainLbl.setBounds(20, 20, 200, 50);
		picLbl = new JLabel("Gambar");
		picLbl.setBounds(20, 20, 200, 50);
		
		descTitleLbl = new JLabel("Description");
		descTitleLbl.setBounds(20, 20, 200, 50);
		brandLbl = new JLabel("Brand");
		brandLbl.setBounds(20, 20, 200, 50);
		barcodeLbl = new JLabel("Barcode");
		barcodeLbl.setBounds(20, 20, 200, 50);
		descLbl = new JLabel("Deskripsi");
		descLbl.setBounds(20, 20, 200, 50);
		
		attLbl = new JLabel("Atribut Produk");
		attLbl.setBounds(20, 20, 200, 50);
		typeLbl = new JLabel("Jenis Kayu");
		typeLbl.setBounds(20, 20, 200, 50);
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(20, 20, 200, 50);
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(20, 20, 200, 50);
		conditionLbl = new JLabel("Kondisi");
		conditionLbl.setBounds(20, 20, 200, 50);
		
		inventLbl = new JLabel("Inventory");
		inventLbl.setBounds(20, 20, 200, 50);
		flagSerialLbl = new JLabel("Flag Serial No");
		flagSerialLbl.setBounds(20, 20, 200, 50);
		flagAssetLbl = new JLabel("Flag Aktiva Tetap");
		flagAssetLbl.setBounds(20, 20, 200, 50);
		warrantLbl = new JLabel("Garansi");
		warrantLbl.setBounds(20, 20, 200, 50);
		weightLbl = new JLabel("Berat Netto");
		weightLbl.setBounds(20, 20, 200, 50);
		
		purchaseInfoLbl = new JLabel("Purchase Information");
		purchaseInfoLbl.setBounds(20, 20, 200, 50);
		flagPurchaseLbl = new JLabel("Flag Purchase Item");
		flagPurchaseLbl.setBounds(20, 20, 200, 50);
		minOrderLbl = new JLabel("Minimum Order");
		minOrderLbl.setBounds(20, 20, 200, 50);
		leadTimeLbl = new JLabel("Lead Time");
		leadTimeLbl.setBounds(20, 20, 200, 50);
		
		defCostLbl = new JLabel("Default Buying Cost Center");
		defCostLbl.setBounds(20, 20, 200, 50);
		defExpense = new JLabel("Default Expense Account");
		defExpense.setBounds(20, 20, 200, 50);
		
		conversionLbl = new JLabel("Konversi Unit of Measurement");
		titleLbl.setBounds(20, 20, 200, 50);
		
		suppInfoLbl = new JLabel("Informasi Supplier");
		suppInfoLbl.setBounds(20, 20, 200, 50);
		suppLbl = new JLabel("Supplier");
		suppLbl.setBounds(20, 20, 200, 50);
		manufacturerLbl = new JLabel("Manufacturer");
		manufacturerLbl.setBounds(20, 20, 200, 50);
		
		reservedSuppLbl = new JLabel("Supplier Cadangan");
		reservedSuppLbl.setBounds(20, 20, 200, 50);
		
		salesInfoLbl = new JLabel("Sales Information");
		salesInfoLbl.setBounds(20, 20, 200, 50);
		flagSalesLbl = new JLabel("Flag Sales Item");
		flagSalesLbl.setBounds(20, 20, 200, 50);
		flagProductLbl = new JLabel("Flag Produk Jasa");
		flagProductLbl.setBounds(20, 20, 200, 50);
		
		defSellCostLbl = new JLabel("Default Selling Cost Center");
		defSellCostLbl.setBounds(20, 20, 200, 50);
		defIncomeLbl = new JLabel("Default Income Account");
		defIncomeLbl.setBounds(20, 20, 200, 50);
		maxDiscountLbl = new JLabel("Diskon Maksimum");
		maxDiscountLbl.setBounds(20, 20, 200, 50);
		
		customerListLbl = new JLabel("List Customer");
		customerListLbl.setBounds(20, 20, 200, 50);
		
		taxLbl = new JLabel("Pajak");
		taxLbl.setBounds(20, 20, 200, 50);
		
		idField = new JTextField();
		idField.setBounds(20, 20, 200, 50);
		
		nameField = new JTextField();
		nameField.setBounds(20, 20, 200, 50);
		
		catField = new JComboBox<>();
		catField.setBounds(20, 20, 200, 50);
		
		statField = new JComboBox<>();
		statField.setBounds(20, 20, 200, 50);
		
		uomField = new JComboBox<>();
		uomField.setBounds(20, 20, 200, 50);
		
		maintain = new ButtonGroup();
		
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setBounds(20, 20, 200, 50);
		
		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setBounds(20, 20, 200, 50);
		
		picField = new JFileChooser();
		picField.setBounds(20, 20, 200, 50);
		
		imageField = new JPanel();
		imageField.setBounds(20, 20, 200, 50);
		
		brandField = new JTextField();
		brandField.setBounds(20, 20, 200, 50);
		
		barcodeField = new JTextField();
		barcodeField.setBounds(20, 20, 200, 50);
		
		descField = new JTextArea();
		descField.setBounds(20, 20, 200, 50);
		
		typeField = new JComboBox<>();
		typeField.setBounds(20, 20, 200, 50);
		
		gradeField = new JComboBox<>();
		gradeField.setBounds(20, 20, 200, 50);
		
		thickField = new JComboBox<>();
		thickField.setBounds(20, 20, 200, 50);
		
		conField = new JComboBox<>();
		conField.setBounds(20, 20, 200, 50);
		
		flagSerial = new ButtonGroup();
		
		flagAsset = new ButtonGroup();
		
		serialYesField = new JRadioButton("Ya");
		serialYesField.setBounds(20, 20, 200, 50);
		
		serialNoField = new JRadioButton("Tidak");
		serialNoField.setBounds(20, 20, 200, 50);
		
		assetYesField = new JRadioButton("Ya");
		assetYesField.setBounds(20, 20, 200, 50);
		
		assetNoField = new JRadioButton("Tidak");
		assetNoField.setBounds(20, 20, 200, 50);
		
		warrantField = new JTextField();
		warrantField.setBounds(20, 20, 200, 50);
		
		nettoField = new JTextField();
		nettoField.setBounds(20, 20, 200, 50);
		
		nettoUnitField = new JComboBox<>();
		nettoUnitField.setBounds(20, 20, 200, 50);
		
		purchase = new ButtonGroup();
		
		purchaseYesField = new JRadioButton("Ya");
		purchaseYesField.setBounds(20, 20, 200, 50);
		
		purchaseNoField = new JRadioButton("Tidak");
		purchaseNoField.setBounds(20, 20, 200, 50);
		
		minOrderField = new JTextField();
		minOrderField.setBounds(20, 20, 200, 50);
		
		minOrderUnitField = new JComboBox<>();
		minOrderUnitField.setBounds(20, 20, 200, 50);
		
		leadTimeField = new JTextField();
		leadTimeField.setBounds(20, 20, 200, 50);
		
		buyCostField = new JComboBox<>();
		buyCostField.setBounds(20, 20, 200, 50);
		
		expenseField = new JComboBox<>();
		expenseField.setBounds(20, 20, 200, 50);
		
		uomAddBtn = new JButton("Add");
		uomAddBtn.setBounds(20, 20, 200, 50);
		
		uomDeleteBtn = new JButton("Delete");
		uomDeleteBtn.setBounds(20, 20, 200, 50);
		
		uomTable = new JTable();
		uomTable.setBounds(20, 20, 200, 50);
		
		supplierField = new JComboBox<>();
		supplierField.setBounds(20, 20, 200, 50);
		
		manufacturerField = new JTextField();
		manufacturerField.setBounds(20, 20, 200, 50);
		
		resSupSearchBtn = new JButton("Search");
		resSupSearchBtn.setBounds(20, 20, 200, 50);
		
		resSupDeleteBtn = new JButton("Delete");
		resSupDeleteBtn.setBounds(20, 20, 200, 50);
		
		resSupTable = new JTable();
		resSupTable.setBounds(20, 20, 200, 50);
		
		sales = new ButtonGroup();
		
		service = new ButtonGroup();
		
		salesYesField = new JRadioButton("Ya");
		salesYesField.setBounds(20, 20, 200, 50);
		
		salesNoField = new JRadioButton("Tidak");
		salesNoField.setBounds(20, 20, 200, 50);
		
		serviceYesField = new JRadioButton("Ya");
		serviceYesField.setBounds(20, 20, 200, 50);
		
		serviceNoField = new JRadioButton("Tidak");
		serviceNoField.setBounds(20, 20, 200, 50);
		
		sellCostField = new JComboBox<>();
		sellCostField.setBounds(20, 20, 200, 50);
		
		incomeField = new JComboBox<>();
		incomeField.setBounds(20, 20, 200, 50);
		
		discountField = new JTextField();
		discountField.setBounds(20, 20, 200, 50);
		
		custSearchBtn = new JButton("Search");
		custSearchBtn.setBounds(20, 20, 200, 50);
		
		custDeleteBtn = new JButton("Delete");
		custDeleteBtn.setBounds(20, 20, 200, 50);
		
		custTable = new JTable();
		custTable.setBounds(20, 20, 200, 50);
		
		taxAddBtn = new JButton("Add");
		taxAddBtn.setBounds(20, 20, 200, 50);
		
		taxDeleteBtn = new JButton("Delete");
		taxDeleteBtn.setBounds(20, 20, 200, 50);
		
		taxTable = new JTable();
		taxTable.setBounds(20, 20, 200, 50);
		
		saveBtn = new JButton("Save");
		saveBtn.setBounds(20, 20, 200, 50);
		
		add(titleLbl);
		add(idLbl);
		
	}

}
