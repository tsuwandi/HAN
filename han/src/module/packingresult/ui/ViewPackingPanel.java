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
import org.joda.time.Years;

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
import module.packingresult.model.PackingResult;
import module.util.Bridging;

public class ViewPackingPanel extends JPanel implements Bridging{
	Logger log = LogManager.getLogger(ViewPackingPanel.class.getName());
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
	
	private JLabel bigCrateAResultLbl;
	private JLabel smallCrateAResultLbl;
	private JLabel bigCrateA1ResultLbl;
	private JLabel smallCrateA1ResultLbl;
	private JLabel bigCrateBResultLbl;
	private JLabel smallCrateBResultLbl;
	private JLabel bigCrateBNPResultLbl;
	private JLabel smallCrateBNPResultLbl;
	
	private JLabel bigCrateAEqualsLbl;
	private JLabel smallCrateAEqualsLbl;
	private JLabel bigCrateA1EqualsLbl;
	private JLabel smallCrateA1EqualsLbl;
	private JLabel bigCrateBEqualsLbl;
	private JLabel smallCrateBEqualsLbl;
	private JLabel bigCrateBNPEqualsLbl;
	private JLabel smallCrateBNPEqualsLbl;
	
	private JLabel bigCrateAErrorLbl;
	private JLabel smallCrateAErrorLbl;
	private JLabel bigCrateA1ErrorLbl;
	private JLabel smallCrateA1ErrorLbl;
	private JLabel bigCrateBErrorLbl;
	private JLabel smallCrateBErrorLbl;
	private JLabel bigCrateBNPErrorLbl;
	private JLabel smallCrateBNPErrorLbl;
	
	private JButton backBtn;
	private JButton editBtn;
	private JButton deleteBtn;
	
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
	
	private TextField bigCrateAResultField;
	private TextField smallCrateAResultField;
	private TextField bigCrateA1ResultField;
	private TextField smallCrateA1ResultField;
	private TextField bigCrateBResultField;
	private TextField smallCrateBResultField;
	private TextField bigCrateBNPResultField;
	private TextField smallCrateBNPResultField;
	
	JScrollPane scrollPane;
	JPanel containerPnl;
	
	private String EQUALS="=";
	private String NORMAL_A="lembar barcore normal A";
	private String NORMAL_B="lembar barcore normal B";
	
	private static final String BIG_CRATE_A="PDC009-9";
	private static final String SMALL_CRATE_A="PDC009-10";
	private static final String BIG_CRATE_A1="PDC009-11";
	private static final String SMALL_CRATE_A1="PDC009-12";
	private static final String BIG_CRATE_B="PDC009-13";
	private static final String SMALL_CRATE_B="PDC009-14";
	private static final String BIG_CRATE_BNP="PDC009-15";
	private static final String SMALL_CRATE_BNP="PDC009-16";
	
	private static final String KA = "PDC009-7";
	private static final String KB = "PDC009-8";
	private static final String NA = "PDC009-1";
	private static final String NB = "PDC009-2";
	
	private List<Inventory> inventories;
	private List<PackingConversion> packingConversions;
	private Map<String, PackingConversion> mapPackingConversion;
	private Map<String, String> inventoryTableData;
	private StockTableModel stockTableModel;
	private JScrollPane tableScrollPane;
	private Packing packing;
	private boolean editMode = false;
	
	public ViewPackingPanel() {
		createGUI();
		initData();
		listener();
	}
	
	private void createGUI(){
		setLayout(null);
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1050, 800));
		
		containerPnl.setLayout(null);
		
		scrollPane = new JScrollPane(containerPnl);
		scrollPane.setBounds(0,0,1170,630);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);

		JLabel lblBreadcrumb = new JLabel("ERP > Produksi > Hasil Packing");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		containerPnl.add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("INPUT HASIL PACKING");
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
		tableScrollPane.setBounds(50,165,200,187);
		containerPnl.add(tableScrollPane);
		
		packingResultLbl = new JLabel("<html><b>Hasil Produksi</b></html>");
		packingResultLbl.setBounds(50,385,150,20);
		containerPnl.add(packingResultLbl);
		
		//Big Crate A
		bigCrateALbl= new JLabel("Crate Besar A");
		bigCrateALbl.setBounds(50,425,150,20);
		containerPnl.add(bigCrateALbl);
		
		bigCrateAField= new NumberField(7);
		bigCrateAField.setBounds(210,425,150,20);
		containerPnl.add(bigCrateAField);
		
		bigCrateAEqualsLbl= new JLabel(EQUALS);
		bigCrateAEqualsLbl.setBounds(368,425,150,20);
		containerPnl.add(bigCrateAEqualsLbl);
		
		bigCrateAResultField= new TextField();
		bigCrateAResultField.setBounds(380,425,150,20);
		containerPnl.add(bigCrateAResultField);
		
		bigCrateAResultLbl= new JLabel(NORMAL_A);
		bigCrateAResultLbl.setBounds(540,425,150,20);
		containerPnl.add(bigCrateAResultLbl);
		
		bigCrateAErrorLbl = new JLabel();
		bigCrateAErrorLbl.setBounds(700,425,150,20);
		containerPnl.add(bigCrateAErrorLbl);
		
		//Small Crate A
		smallCrateALbl= new JLabel("Crate Kecil A");
		smallCrateALbl.setBounds(50,465,150,20);
		containerPnl.add(smallCrateALbl);
		
		smallCrateAField= new NumberField(7);
		smallCrateAField.setBounds(210,465,150,20);
		containerPnl.add(smallCrateAField);
		
		smallCrateAEqualsLbl= new JLabel(EQUALS);
		smallCrateAEqualsLbl.setBounds(368,465,150,20);
		containerPnl.add(smallCrateAEqualsLbl);
		
		smallCrateAResultField= new TextField();
		smallCrateAResultField.setBounds(380,465,150,20);
		containerPnl.add(smallCrateAResultField);
		
		smallCrateAResultLbl= new JLabel(NORMAL_A);
		smallCrateAResultLbl.setBounds(540,465,150,20);
		containerPnl.add(smallCrateAResultLbl);
		
		smallCrateAErrorLbl = new JLabel();
		smallCrateAErrorLbl.setBounds(700,465,150,20);
		containerPnl.add(smallCrateAErrorLbl);
		
		//Big Crate A1
		bigCrateA1Lbl= new JLabel("Crate Besar A1");
		bigCrateA1Lbl.setBounds(50,505,150,20);
		containerPnl.add(bigCrateA1Lbl);
		
		bigCrateA1Field= new NumberField(7);
		bigCrateA1Field.setBounds(210,505,150,20);
		containerPnl.add(bigCrateA1Field);
		
		bigCrateA1EqualsLbl= new JLabel(EQUALS);
		bigCrateA1EqualsLbl.setBounds(368,505,150,20);
		containerPnl.add(bigCrateA1EqualsLbl);
		
		bigCrateA1ResultField= new TextField();
		bigCrateA1ResultField.setBounds(380,505,150,20);
		containerPnl.add(bigCrateA1ResultField);
		
		bigCrateA1ResultLbl= new JLabel(NORMAL_A);
		bigCrateA1ResultLbl.setBounds(540,505,150,20);
		containerPnl.add(bigCrateA1ResultLbl);
		
		bigCrateA1ErrorLbl = new JLabel();
		bigCrateA1ErrorLbl.setBounds(700,505,150,20);
		containerPnl.add(bigCrateA1ErrorLbl);
	
		//Small Crate A1
		smallCrateA1Lbl= new JLabel("Crate Kecil A1");
		smallCrateA1Lbl.setBounds(50,545,150,20);
		containerPnl.add(smallCrateA1Lbl);
		
		smallCrateA1Field= new NumberField(7);
		smallCrateA1Field.setBounds(210,545,150,20);
		containerPnl.add(smallCrateA1Field);
		
		smallCrateA1EqualsLbl= new JLabel(EQUALS);
		smallCrateA1EqualsLbl.setBounds(368,545,150,20);
		containerPnl.add(smallCrateA1EqualsLbl);
		
		smallCrateA1ResultField= new TextField();
		smallCrateA1ResultField.setBounds(380,545,150,20);
		containerPnl.add(smallCrateA1ResultField);
		
		smallCrateA1ResultLbl= new JLabel(NORMAL_A);
		smallCrateA1ResultLbl.setBounds(540,545,150,20);
		containerPnl.add(smallCrateA1ResultLbl);
		
		smallCrateA1ErrorLbl = new JLabel();
		smallCrateA1ErrorLbl.setBounds(700,545,150,20);
		containerPnl.add(smallCrateA1ErrorLbl);
		
		//Big Crate B
		bigCrateBLbl= new JLabel("Crate Besar B");
		bigCrateBLbl.setBounds(50,585,150,20);
		containerPnl.add(bigCrateBLbl);
		
		bigCrateBField= new NumberField(7);
		bigCrateBField.setBounds(210,585,150,20);
		containerPnl.add(bigCrateBField);
		
		bigCrateBEqualsLbl= new JLabel(EQUALS);
		bigCrateBEqualsLbl.setBounds(368,585,150,20);
		containerPnl.add(bigCrateBEqualsLbl);
		
		bigCrateBResultField= new TextField();
		bigCrateBResultField.setBounds(380,585,150,20);
		containerPnl.add(bigCrateBResultField);
		
		bigCrateBResultLbl= new JLabel(NORMAL_A);
		bigCrateBResultLbl.setBounds(540,585,150,20);
		containerPnl.add(bigCrateBResultLbl);
		
		bigCrateBErrorLbl = new JLabel();
		bigCrateBErrorLbl.setBounds(700,585,150,20);
		containerPnl.add(bigCrateBErrorLbl);
		
		//Small Crate B
		smallCrateBLbl= new JLabel("Crate Kecil B");
		smallCrateBLbl.setBounds(50,625,150,20);
		containerPnl.add(smallCrateBLbl);
		
		smallCrateBField= new NumberField(7);
		smallCrateBField.setBounds(210,625,150,20);
		containerPnl.add(smallCrateBField);
		
		smallCrateBEqualsLbl= new JLabel(EQUALS);
		smallCrateBEqualsLbl.setBounds(368,625,150,20);
		containerPnl.add(smallCrateBEqualsLbl);
		
		smallCrateBResultField= new TextField();
		smallCrateBResultField.setBounds(380,625,150,20);
		containerPnl.add(smallCrateBResultField);
		
		smallCrateBResultLbl= new JLabel(NORMAL_B);
		smallCrateBResultLbl.setBounds(540,625,150,20);
		containerPnl.add(smallCrateBResultLbl);
		
		smallCrateBErrorLbl = new JLabel();
		smallCrateBErrorLbl.setBounds(700,625,150,20);
		containerPnl.add(smallCrateBErrorLbl);
		
		//Big Crate BNP
		bigCrateBNPLbl= new JLabel("Crate Besar BNP");
		bigCrateBNPLbl.setBounds(50,665,150,20);
		containerPnl.add(bigCrateBNPLbl);
		
		bigCrateBNPField= new NumberField(7);
		bigCrateBNPField.setBounds(210,665,150,20);
		containerPnl.add(bigCrateBNPField);
		
		bigCrateBNPEqualsLbl= new JLabel(EQUALS);
		bigCrateBNPEqualsLbl.setBounds(368,665,150,20);
		containerPnl.add(bigCrateBNPEqualsLbl);
		
		bigCrateBNPResultField= new TextField();
		bigCrateBNPResultField.setBounds(380,665,150,20);
		containerPnl.add(bigCrateBNPResultField);
		
		bigCrateBNPResultLbl= new JLabel(NORMAL_B);
		bigCrateBNPResultLbl.setBounds(540,665,150,20);
		containerPnl.add(bigCrateBNPResultLbl);
		
		bigCrateBNPErrorLbl = new JLabel();
		bigCrateBNPErrorLbl.setBounds(700,665,150,20);
		containerPnl.add(bigCrateBNPErrorLbl);
		
		//Small Crate BNP
		smallCrateBNPLbl= new JLabel("Crate Kecil BNP");
		smallCrateBNPLbl.setBounds(50,705,150,20);
		containerPnl.add(smallCrateBNPLbl);
		
		smallCrateBNPField= new NumberField(7);
		smallCrateBNPField.setBounds(210,705,150,20);
		containerPnl.add(smallCrateBNPField);
		
		smallCrateBNPEqualsLbl= new JLabel(EQUALS);
		smallCrateBNPEqualsLbl.setBounds(368,705,150,20);
		containerPnl.add(smallCrateBNPEqualsLbl);
		
		smallCrateBNPResultField= new TextField();
		smallCrateBNPResultField.setBounds(380,705,150,20);
		containerPnl.add(smallCrateBNPResultField);
		
		smallCrateBNPResultLbl= new JLabel(NORMAL_B);
		smallCrateBNPResultLbl.setBounds(540,705,150,20);
		containerPnl.add(smallCrateBNPResultLbl);
		
		smallCrateBNPErrorLbl = new JLabel();
		smallCrateBNPErrorLbl.setBounds(700,705,150,20);
		containerPnl.add(smallCrateBNPErrorLbl);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(850,745,150,30);
		containerPnl.add(editBtn);
		
		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(700,745,150,30);
		containerPnl.add(deleteBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,745,150,30);
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
		bigCrateA1Field.setEnabled(false);
		smallCrateA1Field.setEnabled(false);
		bigCrateAField.setEnabled(false);
		smallCrateAField.setEnabled(false);
		bigCrateBField.setEnabled(false);
		smallCrateBField.setEnabled(false);
		bigCrateBNPField.setEnabled(false);
		smallCrateBNPField.setEnabled(false);
		packingDateChooser.setEnabled(false);
		stockTable.setEnabled(false);
		
		inventories = new ArrayList<>();
		packingConversions = new ArrayList<>();
		inventoryTableData = new HashMap<>();
		mapPackingConversion = new HashMap<>();
		packing = new Packing();
		
		try {
			String queryInventoryCode = " AND product_code IN ('"+NA+"','"+NB+"','"+KA+"','"+KB+"')";
			inventories = ServiceFactory.getPackingBL().getLastInventoryData(queryInventoryCode);
			if(inventories!=null||inventories.size()!=0){
				for (Inventory inventory : inventories) {
					if(inventory.getProductCode().equals(NA)) inventoryTableData.put(NA, "Barecore Normal A");
					else if(inventory.getProductCode().equals(NB)) inventoryTableData.put(NB, "Barecore Normal B");
					else if(inventory.getProductCode().equals(KA)) inventoryTableData.put(KA, "Barecore Klem A");
					else if(inventory.getProductCode().equals(KB)) inventoryTableData.put(KB, "Barecore Klem B");
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
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.packingresult.ui.ListPackingResultPanel");
			}
		});
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.packingresult.ui.CreateNewPackingPanel",packing);
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showDeleteChoice()==JOptionPane.YES_OPTION){
					try {
						ServiceFactory.getPackingBL().delete(packing);
						MainPanel.changePanel("module.packingresult.ui.ListPackingResultPanel");
					} catch (SQLException e1) {
						log.error(e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});
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
		}
		if(packing.getStatus().equals("FINAL")){
			editBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
		}
	}
	
}
