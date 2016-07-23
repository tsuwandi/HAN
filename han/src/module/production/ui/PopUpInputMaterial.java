package module.production.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.User;
import module.pembelian.model.PalletCard;
import module.production.model.ProdRM;

public class PopUpInputMaterial extends JDialog{
	
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
	
	private JTextField ritNoField;
	private JTextField dateField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField sequenceField;
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField thickField;
	private JTextField logField;
	private JTextField volumeField;
	private JTextField palletCardField;
	private JTextField totalLogField;
	private JTextField totalVolumeField;
	
	private JButton searchPalletCardBtn;
	private JButton addBtn;
	private JButton saveBtn;
	
	private JTable materialTable;
	private JScrollPane scrollPane;
	private MaterialTableModel materialTableModel;
	
	
	public PopUpInputMaterial(){
		createGUI();
	}
	private void createGUI(){
		setLayout(null);
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
		
		ritNoField = new JTextField();
		ritNoField.setBounds(200, 100, 100, 20);
		add(ritNoField);
		
		firstSeparatorLbl = new JLabel("/BL/");
		firstSeparatorLbl.setBounds(300,100,40,20);
		add(firstSeparatorLbl);
		
		dateField = new JTextField();
		dateField.setBounds(340, 100, 50, 20);
		add(dateField);
		
		secondSeparatorLbl = new JLabel("/");
		secondSeparatorLbl.setBounds(390,100,10,20);
		add(secondSeparatorLbl);
		
		monthField = new JTextField();
		monthField.setBounds(400, 100, 50, 20);
		add(monthField);
		
		thirdSeparatorLbl = new JLabel("/");
		thirdSeparatorLbl.setBounds(450,100,10,20);
		add(thirdSeparatorLbl);
		
		yearField = new JTextField();
		yearField.setBounds(460, 100, 50, 20);
		add(yearField);
		
		sequenceField = new JTextField();
		sequenceField.setBounds(200, 140, 50, 20);
		add(sequenceField);
		
		//TODO length part
		lengthLbl = new JLabel("Panjang");
		lengthLbl.setBounds(50, 180, 150, 20);
		add(lengthLbl);
		
		lengthField = new JTextField();
		lengthField.setBounds(200, 180, 150, 20);
		add(lengthField);
		
		uomLengthLbl = new JLabel("cm");
		uomLengthLbl.setBounds(352,180,20,20);
		add(uomLengthLbl);
		
		//TODO thick part
		thickLbl = new JLabel("Panjang");
		thickLbl.setBounds(50, 220, 150, 20);
		add(thickLbl);
		
		thickField = new JTextField();
		thickField.setBounds(200, 220, 150, 20);
		add(thickField);
		
		uomThickLbl = new JLabel("cm");
		uomThickLbl.setBounds(352,220,20,20);
		add(uomThickLbl);
		
		//TODO width part
		widthLbl = new JLabel("Lebar");
		widthLbl.setBounds(50, 260, 150, 20);
		add(widthLbl);
		
		widthField = new JTextField();
		widthField.setBounds(200, 260, 150, 20);
		add(widthField);
		
		uomWidthLbl = new JLabel("cm");
		uomWidthLbl.setBounds(352,260,20,20);
		add(uomWidthLbl);
		
		//TODO log part
		logLbl = new JLabel("Jumlah Kayu");
		logLbl.setBounds(50, 300, 150, 20);
		add(logLbl);
		
		logField = new JTextField();
		logField.setBounds(200, 300, 150, 20);
		add(logField);
		
		uomLogLbl = new JLabel("batang");
		uomLogLbl.setBounds(352,300,40,20);
		add(uomLogLbl);
		
		//TODO volume part
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(50, 340, 150, 20);
		add(volumeLbl);
		
		volumeField = new JTextField();
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
		
		scrollPane = new JScrollPane(materialTable);
		scrollPane.setBounds(50,450,700,100);
		add(scrollPane);
		
		//TODO pallet card Area
		palletCardLbl = new JLabel("Total Kartu Pallet");
		palletCardLbl.setBounds(50, 570, 150, 20);
		add(palletCardLbl);
		
		palletCardField = new JTextField();
		palletCardField.setBounds(200, 570, 150, 20);
		add(palletCardField);
		
		uomPalletCardLbl = new JLabel("buah");
		uomPalletCardLbl.setBounds(352,570,50,20);
		add(uomPalletCardLbl);
		
		//TODO Total Kayu Area
		totalLogLbl = new JLabel("Total Kayu");
		totalLogLbl.setBounds(50, 610, 150, 20);
		add(totalLogLbl);
		
		totalLogField = new JTextField();
		totalLogField.setBounds(200, 610, 150, 20);
		add(totalLogField);
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(352,610,50,20);
		add(uomTotalLogLbl);
		
		//TODO Total Kayu Area
		totalVolumeLbl = new JLabel("Total Kartu Pallet");
		totalVolumeLbl.setBounds(50, 650, 150, 20);
		add(totalVolumeLbl);
		
		totalVolumeField = new JTextField();
		totalVolumeField.setBounds(200, 650, 150, 20);
		add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("m3");
		uomTotalVolumeLbl.setBounds(352,650,20,20);
		add(uomTotalVolumeLbl);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(600,680,150,30);
		add(saveBtn);
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
		                return p.getVolume();
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

