package module.pembelian.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ReceivedDAOFactory;
import main.component.AppConstants;
import main.component.NumberField;
import main.component.TextField;
import model.User;
import module.pembelian.model.Employee;
import module.pembelian.model.Grade;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Product;
import module.pembelian.model.ReceivedDetail;
import module.pembelian.model.Thickness;

public class ViewPopUpPalletCard extends JDialog{
	Logger log = LogManager.getLogger(ViewPopUpPalletCard.class.getName());
	
	JLabel noPalletLbl;
	JLabel palletCardCodeLbl;
	JLabel gradeLbl;
	JLabel longLbl;
	JLabel wideLbl;
	JLabel thickLbl;
	JLabel totalLbl;
	JLabel volumeLbl;
	JLabel totalLogLbl;
	JLabel totalVolumeLbl;
	JLabel productNameLbl;
	JLabel descriptionLbl;
	JLabel uomLongLbl;
	JLabel uomWideLbl;
	JLabel uomThickLbl;
	JLabel uomTotalLbl;
	JLabel uomVolumeLbl;
	JLabel uomTotalLogLbl;
	JLabel uomTotalVolumeLbl;
	
	JLabel errorNoPallet;
	JLabel errorGraderLbl;
	JLabel errorGradeLbl;
	JLabel errorLongLbl;
	JLabel errorWideLbl;
	JLabel errorThickLbl;
	JLabel errorTotalLbl;
	JLabel errorVolumeLbl;
	JLabel errorProductLbl;
	JLabel errorPalletCardLbl;
	JLabel productCode;
	
	NumberField noPalletCardField;
	NumberField longField;
	NumberField wideField;
	NumberField thicknessField;
	NumberField totalField;
	
	TextField codePalletCardField;
	TextField volumeField;
	TextField totalLogField;
	TextField totalVolumeField;
	TextField productNameField;
	
	JTextArea descriptionArea;
	
	JButton insertButton;
	JButton confirmButton;
	
	JTable pcTable;
	JScrollPane pcScrollPane;
	JScrollPane areaScrollPane;
	
	PCTableModel pcTableModel;
	List<PalletCard> pcs;
	List<Grade> grades;
	List<Thickness> thicknesses;
	List<Employee> employees;
	ViewReceivedDetailPanel addReceivedDetail;
	boolean editMode = false;
	int indexEdit = 0;
	int index;
	ReceivedDetail receivedDetail;
	String volumeHidden="";
	String totalVolumeHidden="";
	Map<Integer, PalletCard> deletedPallets;
	
	public ViewPopUpPalletCard(ViewReceivedDetailPanel parent, ReceivedDetail receivedDetail, int index) {
		super((JFrame)parent.getTopLevelAncestor());
		addReceivedDetail = parent;
		setLayout(null);
		setTitle("Kartu Pallet");
		setSize(810, 750);
		
		//Grade
		gradeLbl = new JLabel("Grade : " + receivedDetail.getGrade());
		gradeLbl.setFont(new Font("Tahoma",Font.BOLD,20));
		gradeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		gradeLbl.setOpaque(true);
		gradeLbl.setBackground(Color.white);
		gradeLbl.setBounds(600,30,150,50);
		add(gradeLbl);

		
		//Nomor Pallet
		noPalletLbl = new JLabel("<html>No Pallet <font color='red'>*</font></html>");
		noPalletLbl.setBounds(30,30,150,20);
		add(noPalletLbl);
		
		noPalletCardField = new NumberField(4);
		noPalletCardField.setBounds(150, 30, 150, 20);
		add(noPalletCardField);
		
		errorNoPallet = new JLabel();
		errorNoPallet.setBounds(320, 30, 180, 20);
		add(errorNoPallet);
		
		//Code Pallet
		palletCardCodeLbl = new JLabel("Kode Kartu Pallet");
		palletCardCodeLbl.setBounds(30,70,150,20);
		add(palletCardCodeLbl);
		
		codePalletCardField = new TextField();
		codePalletCardField.setBounds(150, 70, 150, 20);
		add(codePalletCardField);
		
		errorPalletCardLbl = new JLabel();
		errorPalletCardLbl.setBounds(320, 70, 180, 20);
		add(errorPalletCardLbl);
	
		//Long 
		longLbl = new JLabel("<html>Panjang <font color='red'>*</font></html>");
		longLbl.setBounds(30,110,150,20);
		add(longLbl);
		
		longField = new NumberField(10);
		longField.setBounds(150, 110, 150, 20);
		add(longField);
		
		uomLongLbl = new JLabel("cm");
		uomLongLbl.setBounds(302,110,15,20);
		add(uomLongLbl);
		
		errorLongLbl = new JLabel();
		errorLongLbl.setBounds(325,110,150,20);
		add(errorLongLbl);
		
		//Thickness
		thickLbl = new JLabel("<html>Tebal <font color='red'>*</font></html>");
		thickLbl.setBounds(30,150,100,20);
		add(thickLbl);
		
		thicknessField = new NumberField(10);
		thicknessField.setBounds(150, 150, 150, 20);
		add(thicknessField);
		
		uomThickLbl = new JLabel("cm");
		uomThickLbl.setBounds(302,150,15,20);
		add(uomThickLbl);
		
		errorThickLbl = new JLabel();
		errorThickLbl.setBounds(325,150,150,20);
		add(errorThickLbl);
		
		//Wide
		wideLbl = new JLabel("<html>Lebar <font color='red'>*</font></html>");
		wideLbl.setBounds(30,190,100,20);
		add(wideLbl);
		
		wideField = new NumberField(10);
		wideField.setBounds(150, 190, 150, 20);
		add(wideField);
		
		uomWideLbl = new JLabel("cm");
		uomWideLbl.setBounds(302,190,15,20);
		add(uomWideLbl);
		
		errorWideLbl = new JLabel();
		errorWideLbl.setBounds(325,190,150,20);
		add(errorWideLbl);
		
	
	
		//Total
		totalLbl = new JLabel("<html>Jumlah <font color='red'>*</font></html>");
		totalLbl.setBounds(30,230,100,20);
		add(totalLbl);
	
		totalField = new NumberField(10);
		totalField.setBounds(150, 230, 150, 20);
		add(totalField);
		
		uomTotalLbl = new JLabel("batang");
		uomTotalLbl.setBounds(302,230,40,20);
		add(uomTotalLbl);
		
		errorTotalLbl = new JLabel();
		errorTotalLbl.setBounds(352,230,150,20);
		add(errorTotalLbl);
		
		//Volume
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(30,270,100,20);
		add(volumeLbl);
			
		volumeField = new TextField();
		volumeField.setBounds(150, 270, 150, 20);
		add(volumeField);
		
		uomVolumeLbl = new JLabel("<html><span>m&#179;</span></html>");
		uomVolumeLbl.setBounds(302,270,16,20);
		add(uomVolumeLbl);
		
		errorVolumeLbl = new JLabel();
		errorVolumeLbl.setBounds(325,270,150,20);
		add(errorVolumeLbl);
		
		
		//ProductName
		productNameLbl = new JLabel("Nama Produk");
		productNameLbl.setBounds(30,310,150,20);
		add(productNameLbl);
		
		productNameField = new TextField();
		productNameField.setBounds(150, 310, 150, 20);
		add(productNameField);
		
		errorProductLbl = new JLabel();
		errorProductLbl.setBounds(352,310,150,20);
		add(errorProductLbl);

		productCode = new JLabel();
		productCode.setBounds(800, 100, 310, 30);
		productCode.setVisible(false);
		add(productCode);
		
		//Description Area
		descriptionLbl = new JLabel("Keterangan");
		descriptionLbl.setBounds(30,350,150,20);
		add(descriptionLbl);
		
		descriptionArea = new JTextArea();
		descriptionArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		descriptionArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		
		areaScrollPane = new JScrollPane(descriptionArea);
		areaScrollPane.setBounds(150, 350, 150, 50);
		areaScrollPane.setFocusable(false);
		add(areaScrollPane);
		
		//insert Button
		insertButton = new JButton("Tambah");
		insertButton.setBounds(360,420,150,30);
		insertButton.setEnabled(false);
		add(insertButton);
		
		//Table pc
		
		pcs = new ArrayList<>();
		pcTableModel = new PCTableModel(pcs);
		pcTable = new JTable(pcTableModel);
		pcTable.setFocusable(false);
		
		pcScrollPane = new JScrollPane(pcTable);
		pcScrollPane.setBounds(30,460,740,200);
		add(pcScrollPane);
		
		//Total Log
		totalLogLbl = new JLabel("Total Jumlah Kayu");
		totalLogLbl.setBounds(500,110,150,20);
		add(totalLogLbl);
	
		totalLogField =  new TextField();
		totalLogField.setBounds(600, 110, 150, 20);
		add(totalLogField);
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(752,110,40,20);
		add(uomTotalLogLbl);
		
		//total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(500,150,150,20);
		add(totalVolumeLbl);

		totalVolumeField =  new TextField();
		totalVolumeField.setBounds(600, 150, 150, 20);
		add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("<html><span>m&#179;</span></html>");
		uomTotalVolumeLbl.setBounds(752,150,16,20);
		add(uomTotalVolumeLbl);

		//Confirm Btn
		confirmButton = new JButton("Konfirmasi");
		confirmButton.setBounds(600,680,150,30);
		confirmButton.setEnabled(false);
		add(confirmButton);
		
		totalVolumeField.setEnabled(false);
		totalLogField.setEnabled(false);
		productNameField.setEnabled(false);
		volumeField.setEnabled(false);
		codePalletCardField.setEnabled(false);
		
		this.receivedDetail = receivedDetail;
		this.index = index;
		pcs = receivedDetail.getPallets();
		deletedPallets = receivedDetail.getDeletedPallets();
		pcTable.setModel(new PCTableModel(pcs));
		pcTable.updateUI();
	
		int total = 0;
		double volume = 0;
		for(PalletCard pcd : pcs){
			total+=pcd.getTotal();
			volume+=pcd.getVolume();
		}
		totalLogField.setText(total+"");
		totalVolumeField.setText(AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(volume/AppConstants.DIVIDER_VOLUME)+"");
		totalVolumeHidden=volume+"";
		
	
	}
	
	public void clear(){
		noPalletCardField.setText("");
		longField.setText("");
		wideField.setText("");
		thicknessField.setText("");
		totalField.setText("");
		if(addReceivedDetail.receivedDetails.get(index).getGradeID()!=100){
			productCode.setText("");
			productNameField.setText("");
		}
		volumeField.setText("");
		descriptionArea.setText("");
		volumeHidden="";
	}
	
	
	
	public void calculateVolume(){
		if(!longField.getText().equals("")&&!wideField.getText().equals("")&&!thicknessField.getText().equals("")&&!totalField.getText().equals("")){
			double volume = Double.valueOf(longField.getText())*Double.valueOf(wideField.getText())*Double.valueOf(thicknessField.getText())*Double.valueOf(totalField.getText());
			volumeField.setText(AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(volume/AppConstants.DIVIDER_VOLUME)+"");
			volumeHidden = volume+"";
		}
	}
	
	private class PCTableModel extends AbstractTableModel {
	    private List<PalletCard> palletCards;
	    
	    public PCTableModel(List<PalletCard> pcs) {
	        this.palletCards = pcs;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return palletCards.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 10;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	PalletCard p = palletCards.get(rowIndex);
	        switch(columnIndex){
	        	case 0:
	        		return p.getPalletCardCode();
	            case 1 : 
	                return p.getLength();
	            case 2 :
	                return p.getThickness();
	            case 3 :
	                return p.getWidth();
	            case 4 :
	                return p.getTotal();
	            case 5 :
	                return AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(p.getVolume()/AppConstants.DIVIDER_VOLUME);
	            case 6 :
	            	return p.getProductName();
	            case 7 :
	                return p.getDescription();
	            case 8 :
	                return "Edit";
	            case 9 :
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
	                return "Nama Produk";
	            case 7 :
	                return "Keterangan";
	            case 8 :
	                return "Action";
	            case 9 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}

	
}
