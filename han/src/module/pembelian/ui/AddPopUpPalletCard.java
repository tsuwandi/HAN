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
		setSize(700, 600);
		//Field left
		palletCardCodeLbl = new JLabel("Kode Kartu Pallet");
		palletCardCodeLbl.setBounds(30,30,150,30);
		add(palletCardCodeLbl);
		
		graderLbl = new JLabel("Grader");
		graderLbl.setBounds(30,80,150,30);
		add(graderLbl);
		
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(30,130,150,30);
		add(gradeLbl);
		
		longLbl = new JLabel("Panjang");
		longLbl.setBounds(30,180,150,30);
		add(longLbl);
		
		productNameLbl = new JLabel("Nama Produk");
		productNameLbl.setBounds(30,230,150,30);
		add(productNameLbl);
		
		codePalletCardField = new JTextField();
		codePalletCardField.setBounds(150, 30, 150, 30);
		add(codePalletCardField);
		
		graderComboBox = new ComboBox<Grade>();
		graderComboBox.setBounds(150,80,150,30);
		add(graderComboBox);
		
		gradeComboBox = new ComboBox<Grade>();
		gradeComboBox.setBounds(150, 130, 150, 30);
		add(gradeComboBox);
		
		longField = new JTextField();
		longField.setBounds(150, 180, 150, 30);
		add(longField);
		
		productNameField = new JTextField();
		productNameField.setBounds(150, 230, 150, 30);
		add(productNameField);
		
		//right Field
		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(390,30,100,30);
		add(wideLbl);
		
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(390,80,100,30);
		add(thickLbl);
		
		totalLbl = new JLabel("Total");
		totalLbl.setBounds(390,130,100,30);
		add(totalLbl);
		
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(390,180,100,30);
		add(volumeLbl);
		
		wideField = new JTextField();
		wideField.setBounds(490, 30, 150, 30);
		add(wideField);
		
		thickField = new JTextField();
		thickField.setBounds(490, 80, 150, 30);
		add(thickField);
		
		totalField = new JTextField();
		totalField.setBounds(490, 130, 150, 30);
		add(totalField);
		
		volumeField = new JTextField();
		volumeField.setBounds(490, 180, 150, 30);
		add(volumeField);
		
		//Table pc
		insertButton = new JButton("Insert");
		insertButton.setBounds(200,280,150,30);
		
		pcs = new ArrayList<>();
		pcTableModel = new PCTableModel(pcs);
		pcTable = new JTable(pcTableModel);
		
		pcScrollPane = new JScrollPane(pcTable);
		pcScrollPane.setBounds(30,330,610,100);
		add(pcScrollPane);
		
		totalpcLbl = new JLabel("Total Jumlah Kayu");
		totalpcLbl.setBounds(30,450,150,30);
		add(totalpcLbl);
		
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(30,490,150,30);
		add(totalVolumeLbl);
		
		totalpcField =  new JTextField();
		totalpcField.setBounds(350, 450, 150, 30);
		add(totalpcField);
		
		totalVolumeField =  new JTextField();
		totalVolumeField.setBounds(350, 490, 150, 30);
		add(totalVolumeField);

		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(500,520,150,30);
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
