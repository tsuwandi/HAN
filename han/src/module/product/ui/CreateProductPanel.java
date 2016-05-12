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
		setPreferredSize(new Dimension(1166, 1000));
		
		titleLbl = new JLabel("CREATE NEW");
		titleLbl.setBounds(20, 20, 100, 50);
		
		idLbl = new JLabel("Kode Produk");
		idLbl.setBounds(20, 80, 100, 30);
		nameLbl = new JLabel("Nama Produk");
		nameLbl.setBounds(20, 110, 100, 50);
		catLbl = new JLabel("Kategori Produk");
		catLbl.setBounds(20, 140, 100, 50);
		statLbl = new JLabel("Status Produk");
		statLbl.setBounds(20, 170, 100, 50);
		unitLbl = new JLabel("Satuan Produk");
		unitLbl.setBounds(20, 100, 100, 50);
		maintainLbl = new JLabel("Maintain Stock");
		maintainLbl.setBounds(20, 230, 100, 50);
		picLbl = new JLabel("Gambar");
		picLbl.setBounds(20, 260, 100, 50);
		
		descTitleLbl = new JLabel("Description");
		descTitleLbl.setBounds(20, 290, 100, 50);
		brandLbl = new JLabel("Brand");
		brandLbl.setBounds(20, 20, 100, 50);
		barcodeLbl = new JLabel("Barcode");
		barcodeLbl.setBounds(20, 20, 100, 50);
		descLbl = new JLabel("Deskripsi");
		descLbl.setBounds(20, 20, 100, 50);
		
		attLbl = new JLabel("Atribut Produk");
		attLbl.setBounds(20, 20, 100, 50);
		typeLbl = new JLabel("Jenis Kayu");
		typeLbl.setBounds(20, 20, 100, 50);
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(20, 20, 100, 50);
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(20, 20, 100, 50);
		conditionLbl = new JLabel("Kondisi");
		conditionLbl.setBounds(20, 20, 100, 50);
		
		inventLbl = new JLabel("Inventory");
		inventLbl.setBounds(20, 20, 100, 50);
		flagSerialLbl = new JLabel("Flag Serial No");
		flagSerialLbl.setBounds(20, 20, 100, 50);
		flagAssetLbl = new JLabel("Flag Aktiva Tetap");
		flagAssetLbl.setBounds(20, 20, 100, 50);
		warrantLbl = new JLabel("Garansi");
		warrantLbl.setBounds(20, 20, 100, 50);
		weightLbl = new JLabel("Berat Netto");
		weightLbl.setBounds(20, 20, 100, 50);
		
		purchaseInfoLbl = new JLabel("Purchase Information");
		purchaseInfoLbl.setBounds(20, 20, 100, 50);
		flagPurchaseLbl = new JLabel("Flag Purchase Item");
		flagPurchaseLbl.setBounds(20, 20, 100, 50);
		minOrderLbl = new JLabel("Minimum Order");
		minOrderLbl.setBounds(20, 20, 100, 50);
		leadTimeLbl = new JLabel("Lead Time");
		leadTimeLbl.setBounds(20, 20, 100, 50);
		
		defCostLbl = new JLabel("Default Buying Cost Center");
		defCostLbl.setBounds(20, 20, 100, 50);
		defExpense = new JLabel("Default Expense Account");
		defExpense.setBounds(20, 20, 100, 50);
		
		conversionLbl = new JLabel("Konversi Unit of Measurement");
		titleLbl.setBounds(20, 20, 100, 50);
		
		suppInfoLbl = new JLabel("Informasi Supplier");
		suppInfoLbl.setBounds(20, 20, 100, 50);
		suppLbl = new JLabel("Supplier");
		suppLbl.setBounds(20, 20, 100, 50);
		manufacturerLbl = new JLabel("Manufacturer");
		manufacturerLbl.setBounds(20, 20, 100, 50);
		
		reservedSuppLbl = new JLabel("Supplier Cadangan");
		reservedSuppLbl.setBounds(20, 20, 100, 50);
		
		salesInfoLbl = new JLabel("Sales Information");
		salesInfoLbl.setBounds(20, 20, 100, 50);
		flagSalesLbl = new JLabel("Flag Sales Item");
		flagSalesLbl.setBounds(20, 20, 100, 50);
		flagProductLbl = new JLabel("Flag Produk Jasa");
		flagProductLbl.setBounds(20, 20, 100, 50);
		
		defSellCostLbl = new JLabel("Default Selling Cost Center");
		defSellCostLbl.setBounds(20, 20, 100, 50);
		defIncomeLbl = new JLabel("Default Income Account");
		defIncomeLbl.setBounds(20, 20, 100, 50);
		maxDiscountLbl = new JLabel("Diskon Maksimum");
		maxDiscountLbl.setBounds(20, 20, 100, 50);
		
		customerListLbl = new JLabel("List Customer");
		customerListLbl.setBounds(20, 20, 100, 50);
		
		taxLbl = new JLabel("Pajak");
		taxLbl.setBounds(20, 20, 100, 50);
		
		idField = new JTextField();
		idField.setBounds(145, 20, 100, 50);
		
		nameField = new JTextField();
		nameField.setBounds(145, 20, 150, 50);
		
		catField = new JComboBox<>();
		catField.setBounds(145, 20, 150, 50);
		
		statField = new JComboBox<>();
		statField.setBounds(145, 20, 150, 50);
		
		uomField = new JComboBox<>();
		uomField.setBounds(145, 20, 150, 50);
		
		maintain = new ButtonGroup();
		
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setBounds(145, 20, 150, 50);
		
		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setBounds(145, 20, 150, 50);
		
		picField = new JFileChooser();
		picField.setBounds(145, 20, 150, 50);
		
		imageField = new JPanel();
		imageField.setBounds(145, 20, 150, 50);
		
		brandField = new JTextField();
		brandField.setBounds(145, 20, 150, 50);
		
		barcodeField = new JTextField();
		barcodeField.setBounds(145, 20, 150, 50);
		
		descField = new JTextArea();
		descField.setBounds(145, 20, 150, 50);
		
		typeField = new JComboBox<>();
		typeField.setBounds(145, 20, 150, 50);
		
		gradeField = new JComboBox<>();
		gradeField.setBounds(145, 20, 150, 50);
		
		thickField = new JComboBox<>();
		thickField.setBounds(145, 20, 150, 50);
		
		conField = new JComboBox<>();
		conField.setBounds(145, 20, 150, 50);
		
		flagSerial = new ButtonGroup();
		
		flagAsset = new ButtonGroup();
		
		serialYesField = new JRadioButton("Ya");
		serialYesField.setBounds(145, 20, 150, 50);
		
		serialNoField = new JRadioButton("Tidak");
		serialNoField.setBounds(145, 20, 150, 50);
		
		assetYesField = new JRadioButton("Ya");
		assetYesField.setBounds(145, 20, 150, 50);
		
		assetNoField = new JRadioButton("Tidak");
		assetNoField.setBounds(145, 20, 150, 50);
		
		warrantField = new JTextField();
		warrantField.setBounds(145, 20, 150, 50);
		
		nettoField = new JTextField();
		nettoField.setBounds(145, 20, 150, 50);
		
		nettoUnitField = new JComboBox<>();
		nettoUnitField.setBounds(145, 20, 150, 50);
		
		purchase = new ButtonGroup();
		
		purchaseYesField = new JRadioButton("Ya");
		purchaseYesField.setBounds(145, 20, 150, 50);
		
		purchaseNoField = new JRadioButton("Tidak");
		purchaseNoField.setBounds(145, 20, 150, 50);
		
		minOrderField = new JTextField();
		minOrderField.setBounds(145, 20, 150, 50);
		
		minOrderUnitField = new JComboBox<>();
		minOrderUnitField.setBounds(145, 20, 150, 50);
		
		leadTimeField = new JTextField();
		leadTimeField.setBounds(145, 20, 150, 50);
		
		buyCostField = new JComboBox<>();
		buyCostField.setBounds(145, 20, 150, 50);
		
		expenseField = new JComboBox<>();
		expenseField.setBounds(145, 20, 150, 50);
		
		uomAddBtn = new JButton("Add");
		uomAddBtn.setBounds(145, 20, 150, 50);
		
		uomDeleteBtn = new JButton("Delete");
		uomDeleteBtn.setBounds(145, 20, 150, 50);
		
		uomTable = new JTable();
		uomTable.setBounds(145, 20, 150, 50);
		
		supplierField = new JComboBox<>();
		supplierField.setBounds(145, 20, 150, 50);
		
		manufacturerField = new JTextField();
		manufacturerField.setBounds(145, 20, 150, 50);
		
		resSupSearchBtn = new JButton("Search");
		resSupSearchBtn.setBounds(145, 20, 150, 50);
		
		resSupDeleteBtn = new JButton("Delete");
		resSupDeleteBtn.setBounds(145, 20, 150, 50);
		
		resSupTable = new JTable();
		resSupTable.setBounds(145, 20, 150, 50);
		
		sales = new ButtonGroup();
		
		service = new ButtonGroup();
		
		salesYesField = new JRadioButton("Ya");
		salesYesField.setBounds(145, 20, 150, 50);
		
		salesNoField = new JRadioButton("Tidak");
		salesNoField.setBounds(145, 20, 150, 50);
		
		serviceYesField = new JRadioButton("Ya");
		serviceYesField.setBounds(145, 20, 150, 50);
		
		serviceNoField = new JRadioButton("Tidak");
		serviceNoField.setBounds(145, 20, 150, 50);
		
		sellCostField = new JComboBox<>();
		sellCostField.setBounds(145, 20, 150, 50);
		
		incomeField = new JComboBox<>();
		incomeField.setBounds(145, 20, 150, 50);
		
		discountField = new JTextField();
		discountField.setBounds(145, 20, 150, 50);
		
		custSearchBtn = new JButton("Search");
		custSearchBtn.setBounds(145, 20, 150, 50);
		
		custDeleteBtn = new JButton("Delete");
		custDeleteBtn.setBounds(145, 20, 150, 50);
		
		custTable = new JTable();
		custTable.setBounds(145, 20, 150, 50);
		
		taxAddBtn = new JButton("Add");
		taxAddBtn.setBounds(145, 20, 150, 50);
		
		taxDeleteBtn = new JButton("Delete");
		taxDeleteBtn.setBounds(145, 20, 150, 50);
		
		taxTable = new JTable();
		taxTable.setBounds(145, 20, 150, 50);
		
		saveBtn = new JButton("Save");
		saveBtn.setBounds(145, 20, 150, 50);
		
		add(titleLbl);
		add(idLbl);
	    add(nameLbl);
	    add(catLbl);
	    add(statLbl);
	    add(unitLbl);
	    add(maintainLbl);
	    add(picLbl);
	    add(descTitleLbl);
	    add(brandLbl);
	    add(barcodeLbl);
	    add(descLbl);
	    add(attLbl);
	    add(typeLbl);
	    add(gradeLbl);
	    add(thickLbl);
	    add(conditionLbl);
	    add(inventLbl);
	    add(flagSerialLbl);
	    add(flagAssetLbl);
	    add(warrantLbl);
	    add(weightLbl);
	    add(purchaseInfoLbl);
	    add(flagPurchaseLbl);
	    add(minOrderLbl);
	    add(leadTimeLbl);
	    add(defCostLbl);
	    add(defExpense);
	    add(conversionLbl);
	    add(suppInfoLbl);
	    add(suppLbl);
	    add(manufacturerLbl);
	    add(reservedSuppLbl);
	    add(salesInfoLbl);
	    add(flagSalesLbl);
	    add(flagProductLbl);
	    add(defSellCostLbl);
	    add(defIncomeLbl);
	    add(maxDiscountLbl);
	    add(customerListLbl);
	    add(taxLbl);
	    add(idField);
	    add(nameField);
	    add(catField);
	    add(statField);
	    add(uomField);
	    add(maintainYesField);
	    add(maintainNoField);
	    add(picField);
	    add(imageField);
	    add(brandField);
	    add(barcodeField);
	    add(descField);
	    add(typeField);
	    add(gradeField);
	    add(thickField);
	    add(conField);
	    add(serialYesField);
	    add(serialNoField);
	    add(assetYesField);
	    add(assetNoField);
	    add(warrantField);
	    add(nettoField);
	    add(nettoUnitField);
	    add(purchaseYesField);
	    add(purchaseNoField);
	    add(minOrderField);
	    add(minOrderUnitField);
	    add(leadTimeField);
	    add(buyCostField);
	    add(expenseField);
	    add(uomAddBtn);
	    add(uomDeleteBtn);
	    add(uomTable);
	    add(supplierField);
	    add(manufacturerField);
	    add(resSupSearchBtn);
	    add(resSupDeleteBtn);
	    add(resSupTable);
	    add(salesYesField);
	    add(salesNoField);
	    add(serviceYesField);
	    add(serviceNoField);
	    add(sellCostField);
	    add(incomeField);
	    add(discountField);
	    add(custSearchBtn);
	    add(custDeleteBtn);
	    add(custTable);
	    add(taxAddBtn);
	    add(taxDeleteBtn);
	    add(taxTable);
	    add(saveBtn);
		
	}

}
