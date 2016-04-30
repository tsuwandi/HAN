package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ReceivedDAOFactory;
import main.component.ComboBox;
import model.User;
import module.pembelian.model.Employee;
import module.pembelian.model.Pallet;
import module.pembelian.model.SupplierVehicle;
import module.util.Bridging;

public class AddReceivedDetail extends JPanel implements Bridging{
	
	JLabel receivedCodeLbl;
	JLabel receivedDateLbl;
	JLabel ritNumberLbl;
	JLabel supplierLbl;
	JLabel licensePlateLbl;
	JLabel driverLbl;
	JLabel itemOriginLbl;
	
	JTextField receivedCodeField;
	JTextField ritNumberField;
	JTextField driverField;
	JTextField itemOriginField;
	
	JTextField supplierTextField;
	ComboBox<SupplierVehicle> licensePlateComboBox;
	
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
	
	PalletTableModel palletTableModel;
	PicDockingTableModel picDockingTableModel;
	
	List<Pallet> pallets;
	List<Employee> picDockings;
	List<SupplierVehicle> suppVehicles;
	
	JPanel parent;
	
	public AddReceivedDetail(){
		setLayout(null);
		this.parent = this;
		
		receivedCodeLbl = new JLabel("Kode Penerimaan");
		receivedCodeLbl.setBounds(50,70,150,30);
		add(receivedCodeLbl);
		
		receivedDateLbl = new JLabel("Tanggal Penerimaan");
		receivedDateLbl.setBounds(50,120,150,30);
		add(receivedDateLbl);
		
		ritNumberLbl = new JLabel("No Rit");
		ritNumberLbl.setBounds(50,170,150,30);
		add(ritNumberLbl);

		supplierLbl = new JLabel("Supplier");
		supplierLbl.setBounds(50,220,150,30);
		add(supplierLbl);
		
		receivedCodeField = new JTextField();
		receivedCodeField.setBounds(220, 70, 150, 30);
//		receivedCodeField.setEnabled(false);
		add(receivedCodeField);
		
		receivedDateChooser = new JDateChooser();
		receivedDateChooser.setBounds(220,120,150,30);
		add(receivedDateChooser);
		
		ritNumberField = new JTextField();
		ritNumberField.setBounds(220, 170, 150, 30);
		add(ritNumberField);
		
		supplierTextField = new JTextField();
		supplierTextField.setBounds(220,220,150,30);
		add(supplierTextField);
		
		licensePlateLbl = new JLabel("No Kendaraan");
		licensePlateLbl.setBounds(450,70,150,30);
		add(licensePlateLbl);
		
		driverLbl =  new JLabel("Supir");
		driverLbl.setBounds(450,120,150,30);
		add(driverLbl);
		
		itemOriginLbl = new JLabel("Asal Barang");
		itemOriginLbl.setBounds(450,170,150,30);
		add(itemOriginLbl);
		
		licensePlateComboBox = new ComboBox<SupplierVehicle>();
		licensePlateComboBox.setBounds(620,70,150,30);
		add(licensePlateComboBox);
		
		driverField = new JTextField();
		driverField.setBounds(620, 120, 150, 30);
		add(driverField);
		
		itemOriginField = new JTextField();
		itemOriginField.setBounds(620, 170, 150, 30);
		add(itemOriginField);
		
		pallets = new ArrayList<>();
		palletTableModel = new PalletTableModel(pallets);
		palletTable = new JTable(palletTableModel);
		
		palletScrollPane = new JScrollPane(palletTable);
		palletScrollPane.setBounds(50,300,1000,150);
		add(palletScrollPane);
		
		addPalletBtn = new JButton("Add");
		addPalletBtn.setBounds(900,250,100,30);
		add(addPalletBtn);
		
		deletePalletBtn = new JButton("Delete");
		deletePalletBtn.setBounds(800,250,100,30);
		add(deletePalletBtn);
		
		picDockings = new ArrayList<>();
		picDockingTableModel = new PicDockingTableModel(picDockings);
		dockingPICTable = new JTable(picDockingTableModel);
		
		dockingPicScrollPane = new JScrollPane(dockingPICTable);
		dockingPicScrollPane.setBounds(50,500,500,100);
		add(dockingPicScrollPane);
		
		deletePicBtn = new JButton("Delete");
		deletePicBtn.setBounds(570,550,100,30);
		add(deletePicBtn);
		
		searchPicBtn = new JButton("Search");
		searchPicBtn.setBounds(570,500,100,30);
		add(searchPicBtn);
		
		saveBtn = new JButton("Save");
		saveBtn.setBounds(950,570,100,30);
		add(saveBtn);
		
		
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
