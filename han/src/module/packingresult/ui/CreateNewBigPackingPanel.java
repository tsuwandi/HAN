package module.packingresult.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.dailyclosing.model.Inventory;
import module.packingresult.model.Packing;
import module.packingresult.model.PackingConversion;
import module.packingresult.model.PackingRM;
import module.packingresult.model.PackingResult;
import module.util.Bridging;

public class CreateNewBigPackingPanel extends JPanel implements Bridging {
	Logger log = LogManager.getLogger(CreateNewBigPackingPanel.class.getName());
	private JLabel packingDateLbl;
	private JLabel unpackedStockLbl;
	private JLabel packingResultLbl;
	private JLabel bigCrateALbl;
	private JLabel smallCrateALbl;
	private JLabel bigCrateA1Lbl;
	private JLabel smallCrateA1Lbl;
	private JLabel bigCrateBLbl;
	private JLabel smallCrateBLbl;
	private JLabel bigCrateBNPLbl;
	private JLabel smallCrateBNPLbl;
	private JLabel bigCrateCLbl;
	private JLabel smallCrateCLbl;
	private JLabel crateWasteALbl;
	private JLabel crateWasteBLbl;
	
	private JLabel bigCrateAResultLbl;
	private JLabel smallCrateAResultLbl;
	private JLabel bigCrateA1ResultLbl;
	private JLabel smallCrateA1ResultLbl;
	private JLabel bigCrateBResultLbl;
	private JLabel smallCrateBResultLbl;
	private JLabel bigCrateBNPResultLbl;
	private JLabel smallCrateBNPResultLbl;
	private JLabel bigCrateCResultLbl;
	private JLabel smallCrateCResultLbl;
	private JLabel crateWasteAResultLbl;
	private JLabel crateWasteBResultLbl;
	
	private JLabel bigCrateAEqualsLbl;
	private JLabel smallCrateAEqualsLbl;
	private JLabel bigCrateA1EqualsLbl;
	private JLabel smallCrateA1EqualsLbl;
	private JLabel bigCrateBEqualsLbl;
	private JLabel smallCrateBEqualsLbl;
	private JLabel bigCrateBNPEqualsLbl;
	private JLabel smallCrateBNPEqualsLbl;
	private JLabel bigCrateCEqualsLbl;
	private JLabel smallCrateCEqualsLbl;
	private JLabel crateWasteAEqualsLbl;
	private JLabel crateWasteBEqualsLbl;
	
	
	private JLabel bigCrateAErrorLbl;
	private JLabel smallCrateAErrorLbl;
	private JLabel bigCrateA1ErrorLbl;
	private JLabel smallCrateA1ErrorLbl;
	private JLabel bigCrateBErrorLbl;
	private JLabel smallCrateBErrorLbl;
	private JLabel bigCrateBNPErrorLbl;
	private JLabel smallCrateBNPErrorLbl;
	
	private JButton backBtn;
	private JButton saveBtn;
	
	private JTable stockTable;
	
	private JDateChooser packingDateChooser;
	
	private NumberField bigCrateAField;
	private NumberField smallCrateAField;
	private NumberField bigCrateA1Field;
	private NumberField smallCrateA1Field;
	private NumberField bigCrateBField;
	private NumberField smallCrateBField;
	private NumberField bigCrateBNPField;
	private NumberField smallCrateBNPField;
	private NumberField bigCrateCField;
	private NumberField smallCrateCField;
	private NumberField crateWasteAField;
	private NumberField crateWasteBField;
	
	private TextField bigCrateAResultField;
	private TextField smallCrateAResultField;
	private TextField bigCrateA1ResultField;
	private TextField smallCrateA1ResultField;
	private TextField bigCrateBResultField;
	private TextField smallCrateBResultField;
	private TextField bigCrateBNPResultField;
	private TextField smallCrateBNPResultField;
	private TextField bigCrateCResultField;
	private TextField smallCrateCResultField;
	private TextField crateWasteAResultField;
	private TextField crateWasteBResultField;
	
	JScrollPane scrollPane;
	JPanel containerPnl;
	
	private String EQUALS="=";
	private String NORMAL_A="lembar barcore normal A";
	private String NORMAL_B="lembar barcore normal B";
	
	private static final String BIG_CRATE_A="PDC009-23";
	private static final String SMALL_CRATE_A="PDC009-24";
	private static final String BIG_CRATE_A1="PDC009-25";
	private static final String SMALL_CRATE_A1="PDC009-26";
	private static final String BIG_CRATE_B="PDC009-27";
	private static final String SMALL_CRATE_B="PDC009-28";
	private static final String BIG_CRATE_BNP="PDC009-29";
	private static final String SMALL_CRATE_BNP="PDC009-30";
	private static final String BIG_CRATE_C="PDC009-31";
	private static final String SMALL_CRATE_C="PDC009-32";
	private static final String CRATE_WASTE_A="PDC009-19";
	private static final String CRATE_WASTE_B="PDC009-20";
	
	private static final String NA = "PDC009-17";
	private static final String NB = "PDC009-18";
	
	private List<Inventory> inventories;
	private List<PackingConversion> packingConversions;
	private Map<String, PackingConversion> mapPackingConversion;
	private Map<String, String> inventoryTableData;
	private StockTableModel stockTableModel;
	private JScrollPane tableScrollPane;
	private Packing packing;
	private boolean editMode = false;
	
	private JLabel lblHeader;
	
	public CreateNewBigPackingPanel() {
		createGUI();
		initData();
		listener();
	}
	
	private void createGUI(){
		setLayout(null);
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1050, 850));
		
		containerPnl.setLayout(null);
		
		scrollPane = new JScrollPane(containerPnl);
		scrollPane.setBounds(0,0,1170,630);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);

		JLabel lblBreadcrumb = new JLabel("ERP > Produksi > Hasil Packing 12");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		containerPnl.add(lblBreadcrumb);

		lblHeader= new JLabel("INPUT HASIL PACKING 12");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		containerPnl.add(lblHeader);
		
		//Packing Date
		packingDateLbl = new JLabel("<html>Tanggal Packing <font color='red'>*</font></html>");
		packingDateLbl.setBounds(50,85,150,20);
		containerPnl.add(packingDateLbl);
		
		packingDateChooser = new JDateChooser();
		packingDateChooser.setDate(new Date());
		packingDateChooser.setBounds(210,85,150,20);
		containerPnl.add(packingDateChooser);
		
		
		//Table Inventory
		unpackedStockLbl = new JLabel("Stock yang belum dipacking : ");
		unpackedStockLbl.setBounds(50,125,200,20);
		containerPnl.add(unpackedStockLbl);
		
		stockTable = new JTable();
		stockTable.setRowHeight(40);
		
		tableScrollPane = new JScrollPane(stockTable);
		tableScrollPane.setBounds(50,165,200,107);
		containerPnl.add(tableScrollPane);
		
		packingResultLbl = new JLabel("<html><b>Hasil Produksi</b></html>");
		packingResultLbl.setBounds(50,285,150,20);
		containerPnl.add(packingResultLbl);
		
		//Big Crate A
		bigCrateALbl= new JLabel("Crate Besar A");
		bigCrateALbl.setBounds(50,325,150,20);
		containerPnl.add(bigCrateALbl);
		
		bigCrateAField= new NumberField(7);
		bigCrateAField.setBounds(210,325,150,20);
		containerPnl.add(bigCrateAField);
		
		bigCrateAEqualsLbl= new JLabel(EQUALS);
		bigCrateAEqualsLbl.setBounds(368,325,150,20);
		containerPnl.add(bigCrateAEqualsLbl);
		
		bigCrateAResultField= new TextField();
		bigCrateAResultField.setBounds(380,325,150,20);
		containerPnl.add(bigCrateAResultField);
		
		bigCrateAResultLbl= new JLabel(NORMAL_A);
		bigCrateAResultLbl.setBounds(540,325,150,20);
		containerPnl.add(bigCrateAResultLbl);
		
		bigCrateAErrorLbl = new JLabel();
		bigCrateAErrorLbl.setBounds(700,325,150,20);
		containerPnl.add(bigCrateAErrorLbl);
		
		//Small Crate A
		smallCrateALbl= new JLabel("Crate Kecil A");
		smallCrateALbl.setBounds(50,365,150,20);
		containerPnl.add(smallCrateALbl);
		
		smallCrateAField= new NumberField(7);
		smallCrateAField.setBounds(210,365,150,20);
		containerPnl.add(smallCrateAField);
		
		smallCrateAEqualsLbl= new JLabel(EQUALS);
		smallCrateAEqualsLbl.setBounds(368,365,150,20);
		containerPnl.add(smallCrateAEqualsLbl);
		
		smallCrateAResultField= new TextField();
		smallCrateAResultField.setBounds(380,365,150,20);
		containerPnl.add(smallCrateAResultField);
		
		smallCrateAResultLbl= new JLabel(NORMAL_A);
		smallCrateAResultLbl.setBounds(540,365,150,20);
		containerPnl.add(smallCrateAResultLbl);
		
		smallCrateAErrorLbl = new JLabel();
		smallCrateAErrorLbl.setBounds(700,365,150,20);
		containerPnl.add(smallCrateAErrorLbl);
		
		//Big Crate A1
		bigCrateA1Lbl= new JLabel("Crate Besar A1");
		bigCrateA1Lbl.setBounds(50,405,150,20);
		containerPnl.add(bigCrateA1Lbl);
		
		bigCrateA1Field= new NumberField(7);
		bigCrateA1Field.setBounds(210,405,150,20);
		containerPnl.add(bigCrateA1Field);
		
		bigCrateA1EqualsLbl= new JLabel(EQUALS);
		bigCrateA1EqualsLbl.setBounds(368,405,150,20);
		containerPnl.add(bigCrateA1EqualsLbl);
		
		bigCrateA1ResultField= new TextField();
		bigCrateA1ResultField.setBounds(380,405,150,20);
		containerPnl.add(bigCrateA1ResultField);
		
		bigCrateA1ResultLbl= new JLabel(NORMAL_A);
		bigCrateA1ResultLbl.setBounds(540,405,150,20);
		containerPnl.add(bigCrateA1ResultLbl);
		
		bigCrateA1ErrorLbl = new JLabel();
		bigCrateA1ErrorLbl.setBounds(700,405,150,20);
		containerPnl.add(bigCrateA1ErrorLbl);
	
		//Small Crate A1
		smallCrateA1Lbl= new JLabel("Crate Kecil A1");
		smallCrateA1Lbl.setBounds(50,445,150,20);
		containerPnl.add(smallCrateA1Lbl);
		
		smallCrateA1Field= new NumberField(7);
		smallCrateA1Field.setBounds(210,445,150,20);
		containerPnl.add(smallCrateA1Field);
		
		smallCrateA1EqualsLbl= new JLabel(EQUALS);
		smallCrateA1EqualsLbl.setBounds(368,445,150,20);
		containerPnl.add(smallCrateA1EqualsLbl);
		
		smallCrateA1ResultField= new TextField();
		smallCrateA1ResultField.setBounds(380,445,150,20);
		containerPnl.add(smallCrateA1ResultField);
		
		smallCrateA1ResultLbl= new JLabel(NORMAL_A);
		smallCrateA1ResultLbl.setBounds(540,445,150,20);
		containerPnl.add(smallCrateA1ResultLbl);
		
		smallCrateA1ErrorLbl = new JLabel();
		smallCrateA1ErrorLbl.setBounds(700,445,150,20);
		containerPnl.add(smallCrateA1ErrorLbl);
		
		//Big Crate B
		bigCrateBLbl= new JLabel("Crate Besar B");
		bigCrateBLbl.setBounds(50,485,150,20);
		containerPnl.add(bigCrateBLbl);
		
		bigCrateBField= new NumberField(7);
		bigCrateBField.setBounds(210,485,150,20);
		containerPnl.add(bigCrateBField);
		
		bigCrateBEqualsLbl= new JLabel(EQUALS);
		bigCrateBEqualsLbl.setBounds(368,485,150,20);
		containerPnl.add(bigCrateBEqualsLbl);
		
		bigCrateBResultField= new TextField();
		bigCrateBResultField.setBounds(380,485,150,20);
		containerPnl.add(bigCrateBResultField);
		
		bigCrateBResultLbl= new JLabel(NORMAL_B);
		bigCrateBResultLbl.setBounds(540,485,150,20);
		containerPnl.add(bigCrateBResultLbl);
		
		bigCrateBErrorLbl = new JLabel();
		bigCrateBErrorLbl.setBounds(700,485,150,20);
		containerPnl.add(bigCrateBErrorLbl);
		
		//Small Crate B
		smallCrateBLbl= new JLabel("Crate Kecil B");
		smallCrateBLbl.setBounds(50,525,150,20);
		containerPnl.add(smallCrateBLbl);
		
		smallCrateBField= new NumberField(7);
		smallCrateBField.setBounds(210,525,150,20);
		containerPnl.add(smallCrateBField);
		
		smallCrateBEqualsLbl= new JLabel(EQUALS);
		smallCrateBEqualsLbl.setBounds(368,525,150,20);
		containerPnl.add(smallCrateBEqualsLbl);
		
		smallCrateBResultField= new TextField();
		smallCrateBResultField.setBounds(380,525,150,20);
		containerPnl.add(smallCrateBResultField);
		
		smallCrateBResultLbl= new JLabel(NORMAL_B);
		smallCrateBResultLbl.setBounds(540,525,150,20);
		containerPnl.add(smallCrateBResultLbl);
		
		smallCrateBErrorLbl = new JLabel();
		smallCrateBErrorLbl.setBounds(700,525,150,20);
		containerPnl.add(smallCrateBErrorLbl);
		
		//Big Crate BNP
		bigCrateBNPLbl= new JLabel("Crate Besar BNP");
		bigCrateBNPLbl.setBounds(50,565,150,20);
		containerPnl.add(bigCrateBNPLbl);
		
		bigCrateBNPField= new NumberField(7);
		bigCrateBNPField.setBounds(210,565,150,20);
		containerPnl.add(bigCrateBNPField);
		
		bigCrateBNPEqualsLbl= new JLabel(EQUALS);
		bigCrateBNPEqualsLbl.setBounds(368,565,150,20);
		containerPnl.add(bigCrateBNPEqualsLbl);
		
		bigCrateBNPResultField= new TextField();
		bigCrateBNPResultField.setBounds(380,565,150,20);
		containerPnl.add(bigCrateBNPResultField);
		
		bigCrateBNPResultLbl= new JLabel(NORMAL_B);
		bigCrateBNPResultLbl.setBounds(540,565,150,20);
		containerPnl.add(bigCrateBNPResultLbl);
		
		bigCrateBNPErrorLbl = new JLabel();
		bigCrateBNPErrorLbl.setBounds(700,565,150,20);
		containerPnl.add(bigCrateBNPErrorLbl);
		
		//Small Crate BNP
		smallCrateBNPLbl= new JLabel("Crate Kecil BNP");
		smallCrateBNPLbl.setBounds(50,605,150,20);
		containerPnl.add(smallCrateBNPLbl);
		
		smallCrateBNPField= new NumberField(7);
		smallCrateBNPField.setBounds(210,605,150,20);
		containerPnl.add(smallCrateBNPField);
		
		smallCrateBNPEqualsLbl= new JLabel(EQUALS);
		smallCrateBNPEqualsLbl.setBounds(368,605,150,20);
		containerPnl.add(smallCrateBNPEqualsLbl);
		
		smallCrateBNPResultField= new TextField();
		smallCrateBNPResultField.setBounds(380,605,150,20);
		containerPnl.add(smallCrateBNPResultField);
		
		smallCrateBNPResultLbl= new JLabel(NORMAL_B);
		smallCrateBNPResultLbl.setBounds(540,605,150,20);
		containerPnl.add(smallCrateBNPResultLbl);
		
		smallCrateBNPErrorLbl = new JLabel();
		smallCrateBNPErrorLbl.setBounds(700,605,150,20);
		containerPnl.add(smallCrateBNPErrorLbl);
		
		
		//Big Crate C
		bigCrateCLbl= new JLabel("Crate Besar C");
		bigCrateCLbl.setBounds(50,645,150,20);
		containerPnl.add(bigCrateCLbl);
		
		bigCrateCField= new NumberField(7);
		bigCrateCField.setBounds(210,645,150,20);
		containerPnl.add(bigCrateCField);
		
		bigCrateCEqualsLbl= new JLabel(EQUALS);
		bigCrateCEqualsLbl.setBounds(368,645,150,20);
		containerPnl.add(bigCrateCEqualsLbl);
		
		bigCrateCResultField= new TextField();
		bigCrateCResultField.setBounds(380,645,150,20);
		containerPnl.add(bigCrateCResultField);
		
		bigCrateCResultLbl= new JLabel(NORMAL_B);
		bigCrateCResultLbl.setBounds(540,645,150,20);
		containerPnl.add(bigCrateCResultLbl);

		
		//Small Crate C
		smallCrateCLbl= new JLabel("Crate Kecil C");
		smallCrateCLbl.setBounds(50,685,150,20);
		containerPnl.add(smallCrateCLbl);
		
		smallCrateCField= new NumberField(7);
		smallCrateCField.setBounds(210,685,150,20);
		containerPnl.add(smallCrateCField);
		
		smallCrateCEqualsLbl= new JLabel(EQUALS);
		smallCrateCEqualsLbl.setBounds(368,685,150,20);
		containerPnl.add(smallCrateCEqualsLbl);
		
		smallCrateCResultField= new TextField();
		smallCrateCResultField.setBounds(380,685,150,20);
		containerPnl.add(smallCrateCResultField);
		
		smallCrateCResultLbl= new JLabel(NORMAL_B);
		smallCrateCResultLbl.setBounds(540,685,150,20);
		containerPnl.add(smallCrateCResultLbl);
		
		//Crate Waste A
		crateWasteALbl= new JLabel("Cacat Finishing A");
		crateWasteALbl.setBounds(50,725,150,20);
		containerPnl.add(crateWasteALbl);
		
		crateWasteAField= new NumberField(7);
		crateWasteAField.setBounds(210,725,150,20);
		containerPnl.add(crateWasteAField);
		
		crateWasteAEqualsLbl= new JLabel(EQUALS);
		crateWasteAEqualsLbl.setBounds(368,725,150,20);
		containerPnl.add(crateWasteAEqualsLbl);
		
		crateWasteAResultField= new TextField();
		crateWasteAResultField.setBounds(380,725,150,20);
		containerPnl.add(crateWasteAResultField);
		
		crateWasteAResultLbl= new JLabel(NORMAL_A);
		crateWasteAResultLbl.setBounds(540,725,150,20);
		containerPnl.add(crateWasteAResultLbl);

		
		//Crate Waste B
		crateWasteBLbl= new JLabel("Cacat Finishing B");
		crateWasteBLbl.setBounds(50,765,150,20);
		containerPnl.add(crateWasteBLbl);
		
		crateWasteBField= new NumberField(7);
		crateWasteBField.setBounds(210,765,150,20);
		containerPnl.add(crateWasteBField);
		
		crateWasteBEqualsLbl= new JLabel(EQUALS);
		crateWasteBEqualsLbl.setBounds(368,765,150,20);
		containerPnl.add(crateWasteBEqualsLbl);
		
		crateWasteBResultField= new TextField();
		crateWasteBResultField.setBounds(380,765,150,20);
		containerPnl.add(crateWasteBResultField);
		
		crateWasteBResultLbl= new JLabel(NORMAL_B);
		crateWasteBResultLbl.setBounds(540,765,150,20);
		containerPnl.add(crateWasteBResultLbl);

		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(850,805,150,30);
		containerPnl.add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,805,150,30);
		backBtn.setFocusable(false);
		containerPnl.add(backBtn);
	}
	
	private void initData(){
		bigCrateA1ResultField.setEnabled(false);
		smallCrateA1ResultField.setEnabled(false);
		bigCrateAResultField.setEnabled(false);
		smallCrateAResultField.setEnabled(false);
		bigCrateBResultField.setEnabled(false);
		smallCrateBResultField.setEnabled(false);
		bigCrateBNPResultField.setEnabled(false);
		smallCrateBNPResultField.setEnabled(false);
		crateWasteAResultField.setEnabled(false);
		crateWasteBResultField.setEnabled(false);
		
		inventories = new ArrayList<>();
		packingConversions = new ArrayList<>();
		inventoryTableData = new HashMap<>();
		mapPackingConversion = new HashMap<>();
		packing = new Packing();
		
		try {
			String queryInventoryCode = " AND product_code IN ('"+NA+"','"+NB+"')";
			inventories = ServiceFactory.getPackingBL().getLastInventoryData(queryInventoryCode);
			if(inventories!=null||inventories.size()!=0){
				for (Inventory inventory : inventories) {
					if(inventory.getProductCode().equals(NA)) inventoryTableData.put(NA, "Barecore Normal A");
					else if(inventory.getProductCode().equals(NB)) inventoryTableData.put(NB, "Barecore Normal B");
				}
			}
			stockTableModel = new StockTableModel(inventories);
			stockTable.setModel(stockTableModel);
			
			packingConversions = ServiceFactory.getPackingBL().getPackingConversion();
			for (PackingConversion packingConversion : packingConversions) {
				mapPackingConversion.put(packingConversion.getProductCodeFrom(), packingConversion);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	private void listener(){
		bigCrateAField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(bigCrateAField);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(bigCrateAField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(bigCrateAField);
			}
		});
		
		smallCrateAField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(smallCrateAField);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(smallCrateAField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(smallCrateAField);
				
			}
		});
		
		bigCrateA1Field.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(bigCrateA1Field);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(bigCrateA1Field);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(bigCrateA1Field);
			}
		});
		
		smallCrateA1Field.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(smallCrateA1Field);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(smallCrateA1Field);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(smallCrateA1Field);
				
			}
		});
		
		bigCrateBField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(bigCrateBField);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(bigCrateBField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(bigCrateBField);
			}
		});
		
		smallCrateBField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(smallCrateBField);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(smallCrateBField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(smallCrateBField);
				
			}
		});
		
		bigCrateBNPField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(bigCrateBNPField);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(bigCrateBNPField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(bigCrateBNPField);
			}
		});
		
		smallCrateBNPField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(smallCrateBNPField);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(smallCrateBNPField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(smallCrateBNPField);
				
			}
		});
		
		crateWasteAField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(crateWasteAField);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(crateWasteAField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(crateWasteAField);
			}
		});
		
		crateWasteBField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateConversion(crateWasteBField);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateConversion(crateWasteBField);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateConversion(crateWasteBField);
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveData();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.packingresult.ui.ListBigPackingResultPanel");
			}
		});
	}
	private void saveData(){
		int error=0;
		/*if(bigCrateAField.getText().equals("")){
			error++;
			bigCrateAErrorLbl.setText("<html><font color='red'>Crate Besar A harus diisi !</font></html>");
		}else{
			bigCrateAErrorLbl.setText("");
		}
		
		if(smallCrateAField.getText().equals("")){
			error++;
			smallCrateAErrorLbl.setText("<html><font color='red'>Crate Kecil A harus diisi !</font></html>");
		}else{
			smallCrateAErrorLbl.setText("");
		}
		
		if(bigCrateA1Field.getText().equals("")){
			error++;
			bigCrateA1ErrorLbl.setText("<html><font color='red'>Crate Besar A1 harus diisi !</font></html>");
		}else{
			bigCrateA1ErrorLbl.setText("");
		}
		
		if(smallCrateA1Field.getText().equals("")){
			error++;
			smallCrateA1ErrorLbl.setText("<html><font color='red'>Crate Kecil A1 harus diisi !</font></html>");
		}else{
			smallCrateA1ErrorLbl.setText("");
		}
		
		if(bigCrateBField.getText().equals("")){
			error++;
			bigCrateBErrorLbl.setText("<html><font color='red'>Crate Besar B harus diisi !</font></html>");
		}else{
			bigCrateBErrorLbl.setText("");
		}
		
		if(smallCrateBField.getText().equals("")){
			error++;
			smallCrateBErrorLbl.setText("<html><font color='red'>Crate Kecil B harus diisi !</font></html>");
		}else{
			smallCrateBErrorLbl.setText("");
		}
		
		if(bigCrateBNPField.getText().equals("")){
			error++;
			bigCrateBNPErrorLbl.setText("<html><font color='red'>Crate Besar BNP harus diisi !</font></html>");
		}else{
			bigCrateBNPErrorLbl.setText("");
		}
		
		if(smallCrateBNPField.getText().equals("")){
			error++;
			smallCrateBNPErrorLbl.setText("<html><font color='red'>Crate Kecil BNP harus diisi !</font></html>");
		}else{
			smallCrateBNPErrorLbl.setText("");
		}*/
		
		if(bigCrateAField.getText().equals(""))bigCrateAField.setText("0");
	
		if(smallCrateAField.getText().equals(""))smallCrateAField.setText("0");
		
		if(bigCrateA1Field.getText().equals(""))bigCrateA1Field.setText("0");
		
		if(smallCrateA1Field.getText().equals(""))smallCrateA1Field.setText("0");
		
		if(bigCrateBField.getText().equals(""))bigCrateBField.setText("0");
		
		if(smallCrateBField.getText().equals(""))smallCrateBField.setText("0");
		
		if(bigCrateBNPField.getText().equals(""))bigCrateBNPField.setText("0");
		
		if(smallCrateBNPField.getText().equals(""))smallCrateBNPField.setText("0");
		
		if(bigCrateCField.getText().equals(""))bigCrateCField.setText("0");
		
		if(smallCrateCField.getText().equals(""))smallCrateCField.setText("0");
		
		if(bigCrateCResultField.getText().equals(""))bigCrateCResultField.setText("0");
		
		if(smallCrateCResultField.getText().equals(""))smallCrateCResultField.setText("0");
		
		if(crateWasteAField.getText().equals(""))crateWasteAField.setText("0");
		
		if(crateWasteBField.getText().equals(""))crateWasteBField.setText("0");
		if(error==0){
			if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
				if(!editMode){
					try {
						packing.setPackingDate(packingDateChooser.getDate());
						List<PackingResult> packingResults = new ArrayList<>();
						List<PackingRM> packingRMs = new ArrayList<>();
						double barecoreA=0;
						double barecoreB=0;
						for (int i = 0; i < 12; i++) {
							PackingResult packingResult = new PackingResult();
							if(i==0){
								packingResult.setProductCode(BIG_CRATE_A);
								packingResult.setQty(Double.valueOf(bigCrateAField.getText()));
								barecoreA+=Double.valueOf(bigCrateAResultField.getText());
							}else if(i==1){
								packingResult.setProductCode(SMALL_CRATE_A);
								packingResult.setQty(Double.valueOf(smallCrateAField.getText()));
								barecoreA+=Double.valueOf(smallCrateAResultField.getText());
							}else if(i==2){
								packingResult.setProductCode(BIG_CRATE_A1);
								packingResult.setQty(Double.valueOf(bigCrateA1Field.getText()));
								barecoreA+=Double.valueOf(bigCrateA1ResultField.getText());
							}else if(i==3){
								packingResult.setProductCode(SMALL_CRATE_A1);
								packingResult.setQty(Double.valueOf(smallCrateA1Field.getText()));
								barecoreA+=Double.valueOf(smallCrateA1ResultField.getText());
							}else if(i==4){
								packingResult.setProductCode(BIG_CRATE_B);
								packingResult.setQty(Double.valueOf(bigCrateBField.getText()));
								barecoreB+=Double.valueOf(bigCrateBResultField.getText());
							}else if(i==5){
								packingResult.setProductCode(SMALL_CRATE_B);
								packingResult.setQty(Double.valueOf(smallCrateBField.getText()));
								barecoreB+=Double.valueOf(smallCrateBResultField.getText());
							}else if(i==6){
								packingResult.setProductCode(BIG_CRATE_BNP);
								packingResult.setQty(Double.valueOf(bigCrateBNPField.getText()));
								barecoreB+=Double.valueOf(bigCrateBNPResultField.getText());
							}else if(i==7){
								packingResult.setProductCode(SMALL_CRATE_BNP);
								packingResult.setQty(Double.valueOf(smallCrateBNPField.getText()));
								barecoreB+=Double.valueOf(smallCrateBNPResultField.getText());
							}else if(i==8){
								packingResult.setProductCode(BIG_CRATE_C);
								packingResult.setQty(Double.valueOf(bigCrateCField.getText()));
								barecoreB+=Double.valueOf(bigCrateCResultField.getText());
							}else if(i==9){
								packingResult.setProductCode(SMALL_CRATE_C);
								packingResult.setQty(Double.valueOf(smallCrateCField.getText()));
								barecoreB+=Double.valueOf(smallCrateCResultField.getText());
							}else if(i==10){
								packingResult.setProductCode(CRATE_WASTE_A);
								packingResult.setQty(Double.valueOf(crateWasteAField.getText()));
								barecoreA+=Double.valueOf(crateWasteAResultField.getText());
							}else if(i==11){
								packingResult.setProductCode(CRATE_WASTE_B);
								packingResult.setQty(Double.valueOf(crateWasteBField.getText()));
								barecoreB+=Double.valueOf(crateWasteBResultField.getText());
							}
							
							packingResults.add(packingResult);
						}
						for(int i = 0; i < 4; i++){
							PackingRM packingRM = new PackingRM();
							if(i==0){
								packingRM.setProductCode(NA);
								packingRM.setQty(barecoreA);
							}else if(i==1){
								packingRM.setProductCode(NB);
								packingRM.setQty(barecoreB);
							}else if(i==2){
								packingRM.setProductCode(BIG_CRATE_C);
								packingRM.setQty(Double.valueOf(bigCrateCResultField.getText()));
							}else if(i==3){
								packingRM.setProductCode(SMALL_CRATE_C);
								packingRM.setQty(Double.valueOf(smallCrateCResultField.getText()));
							}
							
							packingRMs.add(packingRM);
						}
						packing.setPackingRMs(packingRMs);
						packing.setPackingResults(packingResults);
						packing.setType("12");
						ServiceFactory.getPackingBL().save(packing);
						DialogBox.showInsert();
						MainPanel.changePanel("module.packingresult.ui.ListBigPackingResultPanel");
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
				else{
					try {
						packing.setPackingDate(packingDateChooser.getDate());
						List<PackingResult> packingResults = packing.getPackingResults();
						List<PackingRM> packingRms = packing.getPackingRMs();
						double barecoreA=0;
						double barecoreB=0;;
						for (PackingResult packingResult : packingResults) {
							if(packingResult.getProductCode().equals(BIG_CRATE_A)){
								packingResult.setQty(Double.valueOf(bigCrateAField.getText()));
								barecoreA+=Double.valueOf(bigCrateAResultField.getText());
							}else if(packingResult.getProductCode().equals(SMALL_CRATE_A)){
								packingResult.setQty(Double.valueOf(smallCrateAField.getText()));
								barecoreA+=Double.valueOf(smallCrateAResultField.getText());
							}else if(packingResult.getProductCode().equals(BIG_CRATE_A1)){
								packingResult.setQty(Double.valueOf(bigCrateA1Field.getText()));
								barecoreA+=Double.valueOf(bigCrateA1ResultField.getText());
							}else if(packingResult.getProductCode().equals(SMALL_CRATE_A1)){
								packingResult.setQty(Double.valueOf(smallCrateA1Field.getText()));
								barecoreA+=Double.valueOf(smallCrateA1ResultField.getText());
							}else if(packingResult.getProductCode().equals(BIG_CRATE_B)){
								packingResult.setQty(Double.valueOf(bigCrateBField.getText()));
								barecoreB+=Double.valueOf(bigCrateBResultField.getText());
							}else if(packingResult.getProductCode().equals(SMALL_CRATE_B)){
								packingResult.setQty(Double.valueOf(smallCrateBField.getText()));
								barecoreB+=Double.valueOf(smallCrateBResultField.getText());
							}else if(packingResult.getProductCode().equals(BIG_CRATE_BNP)){
								packingResult.setQty(Double.valueOf(bigCrateBNPField.getText()));
								barecoreB+=Double.valueOf(bigCrateBNPResultField.getText());
							}else if(packingResult.getProductCode().equals(BIG_CRATE_BNP)){
								packingResult.setQty(Double.valueOf(smallCrateBNPField.getText()));
								barecoreB+=Double.valueOf(smallCrateBNPResultField.getText());
							}else if(packingResult.getProductCode().equals(BIG_CRATE_C)){
								packingResult.setQty(Double.valueOf(bigCrateCField.getText()));
								barecoreB+=Double.valueOf(bigCrateCResultField.getText());
							}else if(packingResult.getProductCode().equals(SMALL_CRATE_C)){
								packingResult.setQty(Double.valueOf(smallCrateCField.getText()));
								barecoreB+=Double.valueOf(smallCrateCResultField.getText());
							}else if(packingResult.getProductCode().equals(CRATE_WASTE_A)){
								packingResult.setQty(Double.valueOf(crateWasteAField.getText()));
								barecoreA+=Double.valueOf(crateWasteAResultField.getText());
							}else if(packingResult.getProductCode().equals(CRATE_WASTE_B)){
								packingResult.setQty(Double.valueOf(crateWasteBField.getText()));
								barecoreB+=Double.valueOf(crateWasteBResultField.getText());
							}
						}
						for(PackingRM packingRM : packingRms){
							if(packingRM.getProductCode().equals(NA)){
								packingRM.setQty(barecoreA);
							}else if(packingRM.getProductCode().equals(NB)){
								packingRM.setQty(barecoreB);
							}else if(packingRM.getProductCode().equals(BIG_CRATE_C)){
								packingRM.setQty(Double.valueOf(bigCrateCResultField.getText()));
							}else if(packingRM.getProductCode().equals(SMALL_CRATE_C)){
								packingRM.setQty(Double.valueOf(smallCrateCResultField.getText()));
							}
						}
						ServiceFactory.getPackingBL().update(packing);
						DialogBox.showEdit();
						MainPanel.changePanel("module.packingresult.ui.ListBigPackingResultPanel");
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}	
		}
	}
	
	private void validateConversion(JComponent component){
		if (component==bigCrateAField) {
			if(!bigCrateAField.getText().equals("")){
				if(mapPackingConversion.get(BIG_CRATE_A)!=null){
					bigCrateAResultField.setText(""+Double.valueOf(bigCrateAField.getText())*mapPackingConversion.get(BIG_CRATE_A).getQtyTo());
				}
			}else{
				bigCrateAResultField.setText("0");
			}
		}
		
		if (component==smallCrateAField) {
			if(!smallCrateAField.getText().equals("")){
				if(mapPackingConversion.get(SMALL_CRATE_A)!=null){
					smallCrateAResultField.setText(""+Double.valueOf(smallCrateAField.getText())*mapPackingConversion.get(SMALL_CRATE_A).getQtyTo());
				}
			}else{
				smallCrateAResultField.setText("0");
			}
		}
		
		if (component==bigCrateA1Field) {
			if(!bigCrateA1Field.getText().equals("")){
				if(mapPackingConversion.get(BIG_CRATE_A1)!=null){
					bigCrateA1ResultField.setText(""+Double.valueOf(bigCrateA1Field.getText())*mapPackingConversion.get(BIG_CRATE_A1).getQtyTo());
				}
			}else{
				bigCrateA1ResultField.setText("0");
			}
		}
		
		if (component==smallCrateA1Field) {
			if(!smallCrateA1Field.getText().equals("")){
				if(mapPackingConversion.get(SMALL_CRATE_A1)!=null){
					smallCrateA1ResultField.setText(""+Double.valueOf(smallCrateA1Field.getText())*mapPackingConversion.get(SMALL_CRATE_A1).getQtyTo());
				}
			}else{
				smallCrateA1ResultField.setText("0");
			}
		}
		
		if (component==bigCrateBField) {
			if(!bigCrateBField.getText().equals("")){
				if(mapPackingConversion.get(BIG_CRATE_B)!=null){
					bigCrateBResultField.setText(""+Double.valueOf(bigCrateBField.getText())*mapPackingConversion.get(BIG_CRATE_B).getQtyTo());
				}
			}else{
				bigCrateBResultField.setText("0");
			}
		}
		
		if (component==smallCrateBField) {
			if(!smallCrateBField.getText().equals("")){
				if(mapPackingConversion.get(SMALL_CRATE_B)!=null){
					smallCrateBResultField.setText(""+Double.valueOf(smallCrateBField.getText())*mapPackingConversion.get(SMALL_CRATE_B).getQtyTo());
				}
			}else{
				smallCrateBResultField.setText("0");
			}
		}
		if (component==bigCrateBNPField) {
			if(!bigCrateBNPField.getText().equals("")){
				if(mapPackingConversion.get(BIG_CRATE_BNP)!=null){
					bigCrateBNPResultField.setText(""+Double.valueOf(bigCrateBNPField.getText())*mapPackingConversion.get(BIG_CRATE_BNP).getQtyTo());
				}
			}else{
				bigCrateBNPResultField.setText("0");
			}
		}
		
		if (component==smallCrateBNPField) {
			if(!smallCrateBNPField.getText().equals("")){
				if(mapPackingConversion.get(SMALL_CRATE_BNP)!=null){
					smallCrateBNPResultField.setText(""+Double.valueOf(smallCrateBNPField.getText())*mapPackingConversion.get(SMALL_CRATE_BNP).getQtyTo());
				}
			}else{
				smallCrateBNPResultField.setText("0");
			}
		}
		
		if (component==crateWasteAField) {
			if(!crateWasteAField.getText().equals("")){
				if(mapPackingConversion.get(CRATE_WASTE_A)!=null){
					crateWasteAResultField.setText(""+Double.valueOf(crateWasteAField.getText())*mapPackingConversion.get(CRATE_WASTE_A).getQtyTo());
				}
			}else{
				crateWasteAResultField.setText("0");
			}
		}
		
		if (component==crateWasteBField) {
			if(!crateWasteBField.getText().equals("")){
				if(mapPackingConversion.get(CRATE_WASTE_B)!=null){
					crateWasteBResultField.setText(""+Double.valueOf(crateWasteBField.getText())*mapPackingConversion.get(CRATE_WASTE_B).getQtyTo());
				}
			}else{
				crateWasteBResultField.setText("0");
			}
		}
		
		
	}
	
	
	
	private class StockTableModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private List<Inventory> inventories;
		    
	    public StockTableModel(List<Inventory> inventories) {
	        this.inventories = inventories;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return inventories.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 2;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	Inventory p = inventories.get(rowIndex);
	        switch(columnIndex){
	        	case 0:
	        		return inventoryTableData.get(p.getProductCode());
	            case 1 : 
	                return p.getQty();
	            default :
	                return "";
	        }
	    }

	    /**
	     * Method to getColumnName
	     * @param column columnIndex
	     * @return String column name
	     */
	    public String getColumnName(int column) {
	        switch(column){
	            case 0 : 
	                return "Nama Produk";
	            case 1 :
	                return "Jumlah (Lembar)";
	            default :
	                return "";
	        }
	    }

	}



	@Override
	public void invokeObjects(Object... objects) {
		editMode=true;
		packing = (Packing) objects[0];
		packing.setPackingDate(packing.getPackingDate());
		for (PackingResult packingResult : packing.getPackingResults()) {
			if(packingResult.getProductCode().equals(BIG_CRATE_A))bigCrateAField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(SMALL_CRATE_A))smallCrateAField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(BIG_CRATE_A1))bigCrateA1Field.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(SMALL_CRATE_A1))smallCrateA1Field.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(BIG_CRATE_B))bigCrateBField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(SMALL_CRATE_B))smallCrateBField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(BIG_CRATE_BNP))bigCrateBNPField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(SMALL_CRATE_BNP))smallCrateBNPField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(BIG_CRATE_C))bigCrateCField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(SMALL_CRATE_C))smallCrateCField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(CRATE_WASTE_A))crateWasteAField.setText(Double.valueOf(packingResult.getQty())+"");
			else if(packingResult.getProductCode().equals(CRATE_WASTE_B))crateWasteBField.setText(Double.valueOf(packingResult.getQty())+"");
		}
		
		for (PackingRM packingRM : packing.getPackingRMs()) {
			if(packingRM.getProductCode().equals(BIG_CRATE_C))bigCrateCResultField.setText(Double.valueOf(packingRM.getQty())+"");
			else if(packingRM.getProductCode().equals(SMALL_CRATE_C))smallCrateCResultField.setText(Double.valueOf(packingRM.getQty())+"");
		}
		lblHeader.setText("EDIT HASIL PACKING");
	}
	
}
