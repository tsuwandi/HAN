package module.pembelian.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ReceivedDAOFactory;
import main.component.ComboBox;
import model.User;
import module.pembelian.model.Delivery;
import module.pembelian.model.Employee;
import module.pembelian.model.Pallet;
import module.pembelian.model.SupplierVehicle;
import module.pembelian.model.WoodType;
import module.util.Bridging;

public class EditReceivedDetail extends JPanel implements Bridging{
	
	JLabel receivedCodeLbl;
	JLabel receivedDateLbl;
	JLabel ritNumberLbl;
	JLabel supplierLbl;
	JLabel licensePlateLbl;
	JLabel driverLbl;
	JLabel woodDomicileLbl;
	JLabel docNoLbl;
	JLabel woodResourceLbl;
	JLabel woodTypeLbl;
	JLabel firstCodeSeparator;
	JLabel secondCodeSeparator;
	JLabel thirdCodeSeparator;
	
	JTextField receivedCodeField;
	JTextField receivedCodeDateField;
	JTextField receivedCodeMonthField;
	JTextField receivedCodeYearField;
	
	JTextField ritNumberField;
	JTextField driverField;
	JTextField woodDomicileField;
	JTextField supplierTextField;
	JTextField woodResourceField;
	
	
	ComboBox<SupplierVehicle> licensePlateComboBox;
	ComboBox<WoodType> woodTypeComboBox;
	ComboBox<Delivery> docNoComboBox;
	
	JTable palletTable;
	JTable dockingPICTable;
	
	JScrollPane palletScrollPane;
	JScrollPane dockingPicScrollPane;
	
	JDateChooser receivedDateChooser;
	
	JButton addPalletBtn;
	JButton deletePalletBtn;
	
	JButton searchPicBtn;
	JButton deletePicBtn;
	
	JButton saveBtn;
	JButton editBtn;
	JButton deleteBtn;
	JButton printBtn;
	
	PalletTableModel palletTableModel;
	PicDockingTableModel picDockingTableModel;
	
	List<Pallet> pallets;
	List<Employee> picDockings;
	List<SupplierVehicle> suppVehicles;
	List<Delivery> deliveries;
	List<WoodType> woodTypes;
	
	JPanel parent;
	JScrollPane scrollPane;
	JPanel containerPnl;
	
	public EditReceivedDetail(){
		setLayout(null);
		this.parent = this;
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1100, 800));
		containerPnl.setLayout(null);
		
		scrollPane = new JScrollPane(containerPnl);
		scrollPane.setBounds(0,0,1100,630);
		add(scrollPane);
		
		//Code Label
		receivedCodeLbl = new JLabel("Kode Penerimaan");
		receivedCodeLbl.setBounds(50,70,150,20);
		containerPnl.add(receivedCodeLbl);
		
		firstCodeSeparator = new JLabel("/BL/");
		firstCodeSeparator.setBounds(320,70,20,20);
		firstCodeSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		containerPnl.add(firstCodeSeparator);

		secondCodeSeparator = new JLabel("/");
		secondCodeSeparator.setBounds(370,70,10,20);
		secondCodeSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		containerPnl.add(secondCodeSeparator);
		
		thirdCodeSeparator = new JLabel("/");
		thirdCodeSeparator.setBounds(410,70,10,20);
		thirdCodeSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		containerPnl.add(thirdCodeSeparator);
		
		receivedCodeField = new JTextField();
		receivedCodeField.setBounds(220, 70, 100, 20);
		containerPnl.add(receivedCodeField);
		
		receivedCodeDateField = new JTextField();
		receivedCodeDateField.setBounds(340, 70, 30, 20);
		containerPnl.add(receivedCodeDateField);
		
		receivedCodeMonthField = new JTextField();
		receivedCodeMonthField.setBounds(380, 70, 30, 20);
		containerPnl.add(receivedCodeMonthField);
		
		receivedCodeYearField = new JTextField();
		receivedCodeYearField.setBounds(420, 70, 50, 20);
		containerPnl.add(receivedCodeYearField);
		
		
		//Receive Date
		receivedDateLbl = new JLabel("Tanggal Penerimaan");
		receivedDateLbl.setBounds(50,110,150,20);
		containerPnl.add(receivedDateLbl);
		
		receivedDateChooser = new JDateChooser();
		receivedDateChooser.setBounds(220,110,150,20);
		containerPnl.add(receivedDateChooser);
		
		//Rit Number
		ritNumberLbl = new JLabel("No Rit");
		ritNumberLbl.setBounds(50,150,150,20);
		containerPnl.add(ritNumberLbl);
		
		ritNumberField = new JTextField();
		ritNumberField.setBounds(220, 150, 150, 20);
		containerPnl.add(ritNumberField);
		
		//License Plate
		licensePlateLbl = new JLabel("No Kendaraan");
		licensePlateLbl.setBounds(50,190,150,20);
		containerPnl.add(licensePlateLbl);
		
		licensePlateComboBox = new ComboBox<SupplierVehicle>();
		licensePlateComboBox.setBounds(220,190,150,20);
		containerPnl.add(licensePlateComboBox);
		
		//Supplier
		supplierLbl = new JLabel("Supplier");
		supplierLbl.setBounds(50,230,150,20);
		containerPnl.add(supplierLbl);
		
		supplierTextField = new JTextField();
		supplierTextField.setBounds(220,230,150,20);
		containerPnl.add(supplierTextField);
		
		//Driver
		driverLbl =  new JLabel("Supir");
		driverLbl.setBounds(50,270,150,20);
		containerPnl.add(driverLbl);
		
		driverField = new JTextField();
		driverField.setBounds(220, 270, 150, 20);
		containerPnl.add(driverField);
	
		// Document Number
		docNoLbl =  new JLabel("No Dokumen");
		docNoLbl.setBounds(50,310,150,20);
		containerPnl.add(docNoLbl);
		
		docNoComboBox = new ComboBox<>();
		docNoComboBox.setBounds(220, 310, 150, 20);
		containerPnl.add(docNoComboBox);
		
		//Wood Domicile
		woodDomicileLbl = new JLabel("Asal Barang");
		woodDomicileLbl.setBounds(50,350,150,20);
		containerPnl.add(woodDomicileLbl);
		
		woodDomicileField = new JTextField();
		woodDomicileField.setBounds(220, 350, 150, 20);
		containerPnl.add(woodDomicileField);
	
		//Wood Resource
		woodResourceLbl = new JLabel("Asal Sumber Bahan Baku");
		woodResourceLbl.setBounds(50, 390, 150, 20);
		containerPnl.add(woodResourceLbl);
		
		woodResourceField = new JTextField();
		woodResourceField.setBounds(220, 390, 150, 20);
		containerPnl.add(woodResourceField);
		
		
		//Wood Type
		woodTypeLbl = new JLabel("Asal Sumber Bahan Baku");
		woodTypeLbl.setBounds(50, 430, 150, 20);
		containerPnl.add(woodTypeLbl);
		
		woodTypeComboBox = new ComboBox<>();
		woodTypeComboBox.setBounds(220, 430, 150, 20);
		containerPnl.add(woodTypeComboBox);
		
		//Pallet Card
		pallets = new ArrayList<>();
		palletTableModel = new PalletTableModel(pallets);
		palletTable = new JTable(palletTableModel);
		
		palletScrollPane = new JScrollPane(palletTable);
		palletScrollPane.setBounds(50,500,1000,150);
		containerPnl.add(palletScrollPane);
		
		addPalletBtn = new JButton("Add");
		addPalletBtn.setBounds(900,450,100,30);
		containerPnl.add(addPalletBtn);
		
		deletePalletBtn = new JButton("Delete");
		deletePalletBtn.setBounds(800,450,100,30);
		containerPnl.add(deletePalletBtn);
		
		//Pic Docking
		picDockings = new ArrayList<>();
		picDockingTableModel = new PicDockingTableModel(picDockings);
		dockingPICTable = new JTable(picDockingTableModel);
		
		dockingPicScrollPane = new JScrollPane(dockingPICTable);
		dockingPicScrollPane.setBounds(50,670,500,100);
		containerPnl.add(dockingPicScrollPane);
		
		deletePicBtn = new JButton("Delete");
		deletePicBtn.setBounds(570,710,100,30);
		containerPnl.add(deletePicBtn);
		
		searchPicBtn = new JButton("Search");
		searchPicBtn.setBounds(570,670,100,30);
		containerPnl.add(searchPicBtn);
		
		saveBtn = new JButton("Save");
		saveBtn.setBounds(950,750,100,30);
		containerPnl.add(saveBtn);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(950,750,100,30);
		containerPnl.add(editBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(840,750,100,30);
		containerPnl.add(deleteBtn);
		
		printBtn = new JButton("Print");
		printBtn.setBounds(730,750,100,30);
		containerPnl.add(printBtn);
		
		
		addPalletBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPopUpPalletCard pop = new AddPopUpPalletCard();
				pop.show();
				pop.setLocationRelativeTo(null);
			}
		});
		
		searchPicBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpPicDocking pop = new PopUpPicDocking(parent);
				pop.show();
				pop.setLocationRelativeTo(null);
				
			}
		});
		
		deletePicBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		try {
			suppVehicles = ReceivedDAOFactory.getSupplierVehicleDAO().getListSuppVehicle();
			licensePlateComboBox.setList(suppVehicles);

			woodTypes = ReceivedDAOFactory.getWoodTypeDAO().getWoodType();
			woodTypeComboBox.setList(woodTypes);

			deliveries = ReceivedDAOFactory.getDeliveryDAO().getDeliveryNote();
			docNoComboBox.setList(deliveries);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ritNumberField.setEnabled(false);
		receivedDateChooser.setEnabled(false);
		receivedCodeField.setEnabled(false);
		receivedCodeDateField.setEnabled(false);
		receivedCodeMonthField.setEnabled(false);
		receivedCodeYearField.setEnabled(false);
		woodResourceField.setEnabled(false);
		woodDomicileField.setEnabled(false);
		supplierTextField.setEnabled(false);
		receivedCodeDateField.setText(new SimpleDateFormat("dd").format(new Date()));
		receivedCodeMonthField.setText(new SimpleDateFormat("MM").format(new Date()));
		receivedCodeYearField.setText(new SimpleDateFormat("yy").format(new Date()));
	}
	
	public void setTablePic(List<Employee> picDockings){
		this.picDockings = new ArrayList<>();
		this.picDockings = picDockings;
		dockingPICTable.setModel(new PicDockingTableModel(this.picDockings));
		dockingPICTable.updateUI();
	}
	
	
	private class PalletTableModel extends AbstractTableModel {
	    private List<Pallet> pallets;
	    
	    public PalletTableModel(List<Pallet> pallets) {
	        this.pallets = pallets;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return pallets.size();
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
	        Pallet p = pallets.get(rowIndex);
	        switch(columnIndex){
	            case 1 : 
	                return p.getPalletCardCode();
	            case 2 :
	                return p.getTotalLog();
	            case 3 :
	                return p.getTotalVolume();
	            case 4 :
	                return p.getGrade();
	            case 5 :
	                return p.getEmpName();
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
	                return "";
	            case 1 :
	                return "Kode Kartu Pallet";
	            case 2 :
	                return "Total Jumlah Kayu";
	            case 3 :
	                return "Total Volume";
	            case 4 :
	                return "Grade";
	            case 5 :
	                return "Grader";
	            case 6 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}
	
	
	class PicDockingTableModel extends AbstractTableModel {
	    private List<Employee> picDockings;
	    
	    public PicDockingTableModel(List<Employee> picDockings) {
	        this.picDockings = picDockings;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return picDockings.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 3;
	    }
	    
	    public Class<?> getColumnClass(int col) {
	    	switch(col){
	    	case 0 : 
	    		return Boolean.class;
	    	 default :
	                return String.class;
	    		
	    	}
      }
	    
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	Employee p = picDockings.get(rowIndex);
	        switch(columnIndex){
		        case 0 :
		        	return p.isFlag();
	            case 1 : 
	                return p.getEmpCode();
	            case 2 :
	                return p.getEmpName();
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
	                return "";
	            case 1 :
	                return "Nik";
	            case 2 :
	                return "Name";
	            default :
	                return "";
	        }
	    }

	}


	@Override
	public void invokeObjects(Object... objects) {
		/*System.out.println(objects[0]);*/
		
	}
}
