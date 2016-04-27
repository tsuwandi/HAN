package module.product.ui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	public CreateProductPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(1166, 2000));
		
		titleLbl = new JLabel("CREATE NEW");
		titleLbl.setBounds(20, 20, 200, 50);
		
		idLbl = new JLabel("Kode Produk");
		idLbl.setBounds(20, 20, 200, 50);
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
		
		
	}

}
