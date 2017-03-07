package module.production.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import model.User;
import module.production.model.ProdRM;
import module.production.model.Production;

public class PopUpBigInputMaterial extends JDialog{
	Logger log = LogManager.getLogger(PopUpBigInputMaterial.class.getName());
	private static final long serialVersionUID = 1L;
	private JLabel titleLbl;
	private JLabel palletCardCodeLbl;
	private JLabel lengthLbl;
	private JLabel widthLbl;
	private JLabel thickLbl;
	private JLabel logLbl;
	private JLabel volumeLbl;
	private JLabel materialLbl;
	private JLabel palletCardLbl;
	private JLabel totalLogLbl;
	private JLabel totalVolumeLbl;
	
	private JLabel uomLengthLbl;
	private JLabel uomWidthLbl;
	private JLabel uomThickLbl;
	private JLabel uomLogLbl;
	private JLabel uomVolumeLbl;
	private JLabel uomPalletCardLbl;
	private JLabel uomTotalLogLbl;
	private JLabel uomTotalVolumeLbl;
	private JLabel firstSeparatorLbl;
	private JLabel secondSeparatorLbl;
	private JLabel thirdSeparatorLbl;
	
	private NumberField ritNoField;
	private NumberField dateField;
	private NumberField monthField;
	private NumberField yearField;
	private NumberField sequenceField;
	private TextField lengthField;
	private TextField widthField;
	private TextField thickField;
	private TextField logField;
	private TextField volumeField;
	private TextField palletCardField;
	private TextField totalLogField;
	private TextField totalVolumeField;
	
	private JButton searchPalletCardBtn;
	private JButton addBtn;
	private JButton saveBtn;
	
	private JTable materialTable;
	private JScrollPane scrollPane;
	private MaterialTableModel materialTableModel;
	
	private CreateBigProductionPanel createProductionPanel;
	private List<ProdRM> prodRms;
	private Map<String,ProdRM> deletedProdRms;
	private ProdRM tempProdRM;
	private PopUpBigInputMaterial parentDialog;
	private String productionCode;
	
	public PopUpBigInputMaterial(JPanel parent){
		super((JFrame)parent.getTopLevelAncestor());
		createGUI();
		initData(parent);
		listener();
	}
	private void createGUI(){
		setLayout(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Input Bahan Baku");
		setSize(800, 750);
		
		titleLbl = new JLabel("Input Bahan Baku");
		titleLbl.setBounds(50,10,200,30);
		titleLbl.setFont(new Font("Arial", 1, 18));
		add(titleLbl);
		
		searchPalletCardBtn = new JButton("Cari Kartu Pallet");
		searchPalletCardBtn.setBounds(50,50,150,30);
		add(searchPalletCardBtn);
		
		//TODO Pallet Card Area
		palletCardCodeLbl = new JLabel("Kode Kartu Pallet");
		palletCardCodeLbl.setBounds(50,100,150,20);
		add(palletCardCodeLbl);
		
		ritNoField = new NumberField(4);
		ritNoField.setBounds(200, 100, 50, 20);
		
		add(ritNoField);
		
		firstSeparatorLbl = new JLabel("/BL/");
		firstSeparatorLbl.setBounds(250,100,30,20);
		add(firstSeparatorLbl);
		
		dateField = new NumberField(2);
		dateField.setBounds(280, 100, 50, 20);
		add(dateField);
		
		secondSeparatorLbl = new JLabel("/");
		secondSeparatorLbl.setBounds(330,100,10,20);
		add(secondSeparatorLbl);
		
		monthField = new NumberField(2);
		monthField.setBounds(340, 100, 50, 20);
		add(monthField);
		
		thirdSeparatorLbl = new JLabel("/");
		thirdSeparatorLbl.setBounds(390,100,10,20);
		add(thirdSeparatorLbl);
		
		yearField = new NumberField(2);
		yearField.setBounds(400, 100, 50, 20);
		add(yearField);
		
		sequenceField = new NumberField(4);
		sequenceField.setBounds(200, 140, 50, 20);
		add(sequenceField);
		
		//TODO length part
		lengthLbl = new JLabel("Panjang");
		lengthLbl.setBounds(50, 180, 150, 20);
		add(lengthLbl);
		
		lengthField = new TextField();
		lengthField.setBounds(200, 180, 150, 20);
		add(lengthField);
		
		uomLengthLbl = new JLabel("cm");
		uomLengthLbl.setBounds(352,180,20,20);
		add(uomLengthLbl);
		
		//TODO thick part
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(50, 260, 150, 20);
		add(thickLbl);
		
		thickField = new TextField();
		thickField.setBounds(200, 260, 150, 20);
		add(thickField);
		
		uomThickLbl = new JLabel("cm");
		uomThickLbl.setBounds(352,260,20,20);
		add(uomThickLbl);
		
		//TODO width part
		widthLbl = new JLabel("Lebar");
		widthLbl.setBounds(50, 220, 150, 20);
		add(widthLbl);
		
		widthField = new TextField();
		widthField.setBounds(200, 220, 150, 20);
		add(widthField);
		
		uomWidthLbl = new JLabel("cm");
		uomWidthLbl.setBounds(352,220,20,20);
		add(uomWidthLbl);
		
		//TODO log part
		logLbl = new JLabel("Jumlah Kayu");
		logLbl.setBounds(50, 300, 150, 20);
		add(logLbl);
		
		logField = new TextField();
		logField.setBounds(200, 300, 150, 20);
		add(logField);
		
		uomLogLbl = new JLabel("batang");
		uomLogLbl.setBounds(352,300,40,20);
		add(uomLogLbl);
		
		//TODO volume part
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(50, 340, 150, 20);
		add(volumeLbl);
		
		volumeField = new TextField();
		volumeField.setBounds(200, 340, 150, 20);
		add(volumeField);
		
		uomVolumeLbl = new JLabel("m3");
		uomVolumeLbl.setBounds(352,340,20,20);
		add(uomVolumeLbl);
		
		//Add Btn Area
		addBtn = new JButton("Tambah");
		addBtn.setBounds(600,380,150,30);
		add(addBtn);
		
		//Table Area
		materialLbl = new JLabel("Bahan Baku :");
		materialLbl.setBounds(50,420,150,20);
		add(materialLbl);
		
		materialTableModel = new MaterialTableModel(new ArrayList<ProdRM>());
		
		materialTable = new JTable(materialTableModel);
		materialTable.setFocusable(false);
		scrollPane = new JScrollPane(materialTable);
		scrollPane.setBounds(50,450,700,100);
		add(scrollPane);
		
		//TODO pallet card Area
		palletCardLbl = new JLabel("Total Kartu Pallet");
		palletCardLbl.setBounds(50, 570, 150, 20);
		add(palletCardLbl);
		
		palletCardField = new TextField();
		palletCardField.setBounds(200, 570, 150, 20);
		add(palletCardField);
		
		uomPalletCardLbl = new JLabel("buah");
		uomPalletCardLbl.setBounds(352,570,50,20);
		add(uomPalletCardLbl);
		
		//TODO Total Kayu Area
		totalLogLbl = new JLabel("Total Kayu");
		totalLogLbl.setBounds(50, 610, 150, 20);
		add(totalLogLbl);
		
		totalLogField = new TextField();
		totalLogField.setBounds(200, 610, 150, 20);
		add(totalLogField);
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(352,610,50,20);
		add(uomTotalLogLbl);
		
		//TODO Total Kayu Area
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(50, 650, 150, 20);
		add(totalVolumeLbl);
		
		totalVolumeField = new TextField();
		totalVolumeField.setBounds(200, 650, 150, 20);
		add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("m3");
		uomTotalVolumeLbl.setBounds(352,650,20,20);
		add(uomTotalVolumeLbl);
		
		saveBtn = new JButton("OK");
		saveBtn.setBounds(600,640,150,30);
		add(saveBtn);
		
	}
	
	private void initData(JPanel parent){
		deletedProdRms = new HashMap<>();
		prodRms = new ArrayList<>();
		parentDialog = this;
		createProductionPanel = (CreateBigProductionPanel) parent;
		lengthField.setEnabled(false);
		widthField.setEnabled(false);
		thickField.setEnabled(false);
		logField.setEnabled(false);
		volumeField.setEnabled(false);
		totalLogField.setEnabled(false);
		totalVolumeField.setEnabled(false);
		palletCardField.setEnabled(false);
		ritNoField.setNextFocusableComponent(sequenceField);
		productionCode=createProductionPanel.getProductionCodeField().getText();
		Date currentDate = new Date();
		String date = new SimpleDateFormat("dd").format(currentDate);
		String month = new SimpleDateFormat("MM").format(currentDate);
		String year = new SimpleDateFormat("yy").format(currentDate);
		dateField.setText(date);
		monthField.setText(month);
		yearField.setText(year);
		
		if(createProductionPanel.getProduction().getListOfProdRM()!=null){
			prodRms = createProductionPanel.getProduction().getListOfProdRM();
			materialTable.setModel(new MaterialTableModel(prodRms));
			materialTable.updateUI();
			calculateTotal();
		}
	}
	
	private void listener(){
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(DialogBox.showCloseChoice()==JOptionPane.YES_OPTION)dispose();
			}
		});
		
		searchPalletCardBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpBigSearchMaterial pop = new PopUpBigSearchMaterial(parentDialog);
				pop.show();
				pop.setLocationRelativeTo(null);
			}
		});
		
		sequenceField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER)checkPallet();
			}
		});
		ritNoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER)checkPallet();
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tempProdRM!=null){
					if(deletedProdRms.get(tempProdRM.getPalletCardCode())!=null) deletedProdRms.remove(tempProdRM.getPalletCardCode());
					prodRms.add(tempProdRM);
					materialTable.setModel(new MaterialTableModel(prodRms));
					materialTable.updateUI();
					calculateTotal();
					clearPallet();	
				}
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Production prod = createProductionPanel.getProduction();
				prod.setTotalLog(Integer.parseInt(totalLogField.getText()));
				prod.setTotalPalletCard(Integer.parseInt(totalLogField.getText()));
				prod.setTotalVolume(Double.parseDouble(totalVolumeField.getText())*AppConstants.DIVIDER_VOLUME);
				for (ProdRM prodRM : prodRms) {
					prodRM.setProductionCode(productionCode);
				}
				for(ProdRM prodRM : deletedProdRms.values()){
					prodRM.setProductionCode(productionCode);
				}
				prod.setListOfProdRM(prodRms);
				prod.setDeletedProdRMs(deletedProdRms);
				DialogBox.showInsert();
				dispose();
			}
		});
		
		materialTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(materialTable.columnAtPoint(e.getPoint())==6){
					if(DialogBox.showDeleteChoice()==JOptionPane.YES_OPTION){
						deletedProdRms.put(prodRms.get(materialTable.getSelectedRow()).getPalletCardCode(), prodRms.get(materialTable.getSelectedRow()));
						prodRms.remove(materialTable.getSelectedRow());
						materialTable.updateUI();
					}
				}
			}
		});
		
		ritNoField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!ritNoField.getText().equals("")){
					switch (ritNoField.getText().length()) {
					case 1:
						ritNoField.setText("000"+ritNoField.getText());
						break;
					case 2:
						ritNoField.setText("00"+ritNoField.getText());
						break;
					case 3:
						ritNoField.setText("0"+ritNoField.getText());
						break;
					}
				}
			}
		});
		
		sequenceField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!sequenceField.getText().equals("")){
					switch (sequenceField.getText().length()) {
					case 1:
						sequenceField.setText("000"+sequenceField.getText());
						break;
					case 2:
						sequenceField.setText("00"+sequenceField.getText());
						break;
					case 3:
						sequenceField.setText("0"+sequenceField.getText());
						break;
					}
				}
				checkPallet();
			}
		});
	}
	
	private void checkPallet(){
		String palletCardCode = null;
		if(ritNoField.getText().equals("")||sequenceField.equals("")){
			DialogBox.showError("Rit No dan sequence harus diisi !");
		}else{
			try {
				if(!sequenceField.getText().equals("")){
					switch (sequenceField.getText().length()) {
					case 1:
						sequenceField.setText("000"+sequenceField.getText());
						break;
					case 2:
						sequenceField.setText("00"+sequenceField.getText());
						break;
					case 3:
						sequenceField.setText("0"+sequenceField.getText());
						break;
					}
				}
				palletCardCode = ritNoField.getText()+"/BL/"+dateField.getText()+"/"+monthField.getText()+"/"+yearField.getText()+"/"+sequenceField.getText();
				ProdRM prodRM = ServiceFactory.getProductionBL().getSearchProdRMByPalletCard(palletCardCode, prodRms, productionCode);
				if(prodRM==null)DialogBox.showError("Pallet Card tidak ditemukan");
				else{
					tempProdRM = prodRM;
					lengthField.setText(prodRM.getLength()+"");
					widthField.setText(prodRM.getWidth()+"");
					thickField.setText(prodRM.getThick()+"");
					logField.setText(prodRM.getLog()+"");
					volumeField.setText(AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(prodRM.getVolume()/AppConstants.DIVIDER_VOLUME)+"");
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private void clearPallet(){
		lengthField.setText("");
		widthField.setText("");
		thickField.setText("");
		logField.setText("");
		volumeField.setText("");
		tempProdRM=null;
	}
	
	private void calculateTotal(){
		int totalPalletCard = 0;
		int totalLog = 0;
		double totalVolume = 0;
		for(ProdRM prodRM : prodRms) {
			totalVolume+=prodRM.getVolume();
			totalLog+=prodRM.getLog();
		}
		totalPalletCard = prodRms.size();
		totalLogField.setText(totalLog+"");
		totalVolumeField.setText(AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(totalVolume/AppConstants.DIVIDER_VOLUME)+"");
		palletCardField.setText(totalPalletCard+"");
	}
	
	public void updateTableFromSearch(List<ProdRM> prodRMs){
		for(ProdRM pr : prodRMs) if(deletedProdRms.get(pr.getPalletCardCode())!=null)deletedProdRms.remove(pr.getPalletCardCode());
		this.prodRms.addAll(prodRMs);
		materialTable.setModel(new MaterialTableModel(prodRms));
		materialTable.updateUI();
		calculateTotal();
	}
	
	
	public List<ProdRM> getProdRms() {
		return prodRms;
	}
	public void setProdRms(List<ProdRM> prodRms) {
		this.prodRms = prodRms;
	}
	
	
	public Map<String, ProdRM> getDeletedProdRms() {
		return deletedProdRms;
	}
	public void setDeletedProdRms(Map<String, ProdRM> deletedProdRms) {
		this.deletedProdRms = deletedProdRms;
	}

	

	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}



	private class MaterialTableModel extends AbstractTableModel{
		
		private static final long serialVersionUID = 1L;
		private List<ProdRM> prodRMs;
		    
		    public MaterialTableModel(List<ProdRM> prodRMs) {
		        this.prodRMs = prodRMs;
		    }
		    
		    /**
		     * Method to get row count
		     * @return int
		     */
		    public int getRowCount() {
		        return prodRMs.size();
		    }
		    
		    /**
		     * Method to get Column Count
		     */
		    public int getColumnCount() {
		        return 7;
		    }
		    
		    /**
		     * Method to get selected value
		     * @param rowIndex rowIndex of selected table
		     * @param columnIndex columnIndex of selected table 
		     * @return ({@link User}) Object 
		     */
		    public Object getValueAt(int rowIndex, int columnIndex) {
		    	ProdRM p = prodRMs.get(rowIndex);
		        switch(columnIndex){
		        	case 0:
		        		return p.getPalletCardCode();
		            case 1 : 
		                return p.getLength();
		            case 2 :
		                return p.getThick();
		            case 3 :
		                return p.getWidth();
		            case 4 :
		                return p.getLog();
		            case 5 :
		                return AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(p.getVolume()/AppConstants.DIVIDER_VOLUME);
		            case 6 :
		            	return "Delete";
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
		                return "Kode Kartu Pallet";
		            case 1 :
		                return "Panjang";
		            case 2 :
		                return "Tebal";
		            case 3 :
		            	return "Lebar";
		            case 4 :
		                return "Jumlah";
		            case 5 :
		                return "Volume";
		            case 6 :
		                return "Action";
		            default :
		                return "";
		        }
		    }

		}
	
}

