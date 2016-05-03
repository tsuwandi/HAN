package module.pembelian.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controller.ReceivedDAOFactory;
import main.component.ComboBox;
import model.User;
import module.pembelian.model.Grade;
import module.pembelian.model.PalletCardDetail;

public class AddPopUpPalletCard extends JDialog{

	JLabel palletCardCodeLbl;
	JLabel graderLbl;
	JLabel gradeLbl;
	JLabel longLbl;
	JLabel wideLbl;
	JLabel thickLbl;
	JLabel totalLbl;
	JLabel volumeLbl;
	JLabel totalpcLbl;
	JLabel totalVolumeLbl;
	JLabel productNameLbl;
	JLabel uomLongLbl;
	JLabel uomWideLbl;
	JLabel uomThickLbl;
	JLabel uomTotalLbl;
	JLabel uomVolumeLbl;
	JLabel uomTotalpcLbl;
	JLabel uomTotalVolumeLbl;
	
	JTextField codePalletCardField;
	JTextField longField;
	JTextField wideField;
	JTextField thickField;
	JTextField totalField;
	JTextField volumeField;
	JTextField totalpcField;
	JTextField totalVolumeField;
	JTextField productNameField;
	
	ComboBox<Grade> graderComboBox;
	ComboBox<Grade> gradeComboBox;
	
	JButton insertButton;
	JButton confirmButton;
	
	JTable pcTable;
	JScrollPane pcScrollPane;
	
	PCTableModel pcTableModel;
	List<PalletCardDetail> pcs;
	List<Grade> grades;
	
	public AddPopUpPalletCard() {
		setLayout(null);
		setTitle("Kartu Pallet");
		setSize(700, 700);
		
		//Code Pallet
		palletCardCodeLbl = new JLabel("Kode Kartu Pallet");
		palletCardCodeLbl.setBounds(30,30,150,20);
		add(palletCardCodeLbl);
		
		codePalletCardField = new JTextField();
		codePalletCardField.setBounds(150, 30, 150, 20);
		add(codePalletCardField);
		
		//Grader 
		graderLbl = new JLabel("Grader");
		graderLbl.setBounds(30,70,150,20);
		add(graderLbl);
		
		graderComboBox = new ComboBox<Grade>();
		graderComboBox.setBounds(150,70,150,20);
		add(graderComboBox);
		
		//Grade
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(30,100,150,20);
		add(gradeLbl);

		gradeComboBox = new ComboBox<Grade>();
		gradeComboBox.setBounds(150, 100, 150, 20);
		add(gradeComboBox);
		
		
		//Long 
		longLbl = new JLabel("Panjang");
		longLbl.setBounds(30,140,150,20);
		add(longLbl);
		
		longField = new JTextField();
		longField.setBounds(150, 140, 150, 20);
		add(longField);
		
		//ProductName
		productNameLbl = new JLabel("Nama Produk");
		productNameLbl.setBounds(30,180,150,20);
		add(productNameLbl);
		
		productNameField = new JTextField();
		productNameField.setBounds(150, 180, 150, 20);
		add(productNameField);
		
		//Wide
		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(30,220,100,20);
		add(wideLbl);
		
		wideField = new JTextField();
		wideField.setBounds(150, 220, 150, 20);
		add(wideField);
		
		//Thickness
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(30,260,100,20);
		add(thickLbl);
		
		thickField = new JTextField();
		thickField.setBounds(150, 260, 150, 20);
		add(thickField);
		
		//Total
		totalLbl = new JLabel("Total");
		totalLbl.setBounds(30,300,100,20);
		add(totalLbl);
	
		totalField = new JTextField();
		totalField.setBounds(150, 300, 150, 20);
		add(totalField);
		
		//Volume
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(30,340,100,20);
		add(volumeLbl);
			
		volumeField = new JTextField();
		volumeField.setBounds(150, 340, 150, 20);
		add(volumeField);
		
		//insert Button
		insertButton = new JButton("Insert");
		insertButton.setBounds(300,380,150,30);
		add(insertButton);
		
		//Table pc
		
		pcs = new ArrayList<>();
		pcTableModel = new PCTableModel(pcs);
		pcTable = new JTable(pcTableModel);
		
		pcScrollPane = new JScrollPane(pcTable);
		pcScrollPane.setBounds(30,420,610,100);
		add(pcScrollPane);
		
		//Total Log
		totalpcLbl = new JLabel("Total Jumlah Kayu");
		totalpcLbl.setBounds(30,540,150,20);
		add(totalpcLbl);
	
		totalpcField =  new JTextField();
		totalpcField.setBounds(350, 540, 150, 20);
		add(totalpcField);
		
		//total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(30,580,150,20);
		add(totalVolumeLbl);

		totalVolumeField =  new JTextField();
		totalVolumeField.setBounds(350, 580, 150, 20);
		add(totalVolumeField);

		//Confirm Btn
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(500,610,150,30);
		add(confirmButton);
		
		try {
			grades = ReceivedDAOFactory.getGradeDAO().getAll();
			gradeComboBox.setList(grades);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	private class PCTableModel extends AbstractTableModel {
	    private List<PalletCardDetail> palleteCardDetails;
	    
	    public PCTableModel(List<PalletCardDetail> pcs) {
	        this.palleteCardDetails = pcs;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return palleteCardDetails.size();
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
	        PalletCardDetail p = palleteCardDetails.get(rowIndex);
	        switch(columnIndex){
	            case 0 : 
	                return p.getLength();
	            case 1 :
	                return p.getWidth();
	            case 2 :
	                return p.getThickness();
	            case 3 :
	                return p.getTotal();
	            case 4 :
	                return p.getVolume();
	            case 5 :
	                return "Edit";
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
	                return "Panjang";
	            case 1 :
	                return "Lebar";
	            case 2 :
	                return "Tebal";
	            case 3 :
	                return "Jumlah";
	            case 4 :
	                return "Volume";
	            case 5 :
	                return "Action";
	            case 6 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}
	
}