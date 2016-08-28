package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ReceivedDAOFactory;
import main.component.TextField;
import model.User;
import module.pembelian.model.Received;

public class PopUpAdvanceSearchSecurity extends JDialog{
	JLabel receivedCodeLbl;
	JLabel receivedDateLbl;
	JLabel licensePlateLbl;
	JLabel supplierLbl;
	JLabel docNoLbl;
	
	TextField receivedCodeField;
	JDateChooser startDateChooser;
	JDateChooser endDateChooser;
	TextField licensePlateField;
	TextField supplierField;
	TextField docNoField;
	
	JButton searchBtn;
	
	ListReceivedSecurityPanel parent;
	
	public PopUpAdvanceSearchSecurity(ListReceivedSecurityPanel parent) {
		super((JFrame)parent.getTopLevelAncestor());
		this.parent =parent;
		setLayout(null);
		setTitle("Advance Search");
		setSize(500, 400);
		
		receivedCodeLbl = new JLabel("Kode Penerimaan");
		receivedCodeLbl.setBounds(40,40,120,20);
		add(receivedCodeLbl);
		
		receivedCodeField = new TextField();
		receivedCodeField.setBounds(180, 40, 120, 20);
		add(receivedCodeField);
	
		receivedDateLbl = new JLabel("Tanggal Penerimaan");
		receivedDateLbl.setBounds(40,70,120,20);
		add(receivedDateLbl);
		
		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(180,70,120,20);
		startDateChooser.setDate(null);
		add(startDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setBounds(320,70,120,20);
		endDateChooser.setDate(null);
		add(endDateChooser);
		
		licensePlateLbl = new JLabel("Nomor Kendaraan");
		licensePlateLbl.setBounds(40,100,120,20);
		add(licensePlateLbl);
		
		licensePlateField = new TextField();
		licensePlateField.setBounds(180, 100, 120, 20);
		add(licensePlateField);
		
		supplierLbl = new JLabel("Supplier");
		supplierLbl.setBounds(40,140,120,20);
		add(supplierLbl);
		
		supplierField = new TextField();
		supplierField.setBounds(180, 140, 120, 20);
		add(supplierField);
		

		docNoLbl = new JLabel("No Dokumen");
		docNoLbl.setBounds(40,180,120,20);
		add(docNoLbl);
		
		docNoField = new TextField();
		docNoField.setBounds(180, 180, 120, 20);
		add(docNoField);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(180,220,120,20);
		add(searchBtn);
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb = new StringBuffer();
				List<Object> objs = new ArrayList<>();
				if(!receivedCodeField.getText().equals("")){
					sb.append(" AND received_code LIKE ?");
					objs.add(receivedCodeField.getText());
				}else{
					objs.add(null);
				}
				if(startDateChooser.getDate()!=null&&endDateChooser.getDate()!=null){
					sb.append(" AND DATE(received_date) BETWEEN DATE(?) AND DATE(?)");
					objs.add(startDateChooser.getDate());
					objs.add(endDateChooser.getDate());
				}else{
					objs.add(null);
					objs.add(null);
				}
				
				if(!licensePlateField.getText().equals("")){
					sb.append(" AND a.license_plate LIKE ?");
					objs.add(licensePlateField.getText());
				}else{
					objs.add(null);
				}
				if(!supplierField.getText().equals("")){
					sb.append(" AND supp_name LIKE ?");
					objs.add(supplierField.getText());
				}else{
					objs.add(null);
				}
				if(!docNoField.getText().equals("")){
					sb.append(" AND a.delivery_note LIKE ?");
					objs.add(docNoField.getText());
				}else{
					objs.add(null);
				}
				
				try {
					parent.receiveds = ReceivedDAOFactory.getReceivedDAO().getAdvancedSearchData(sb.toString(), objs);
					parent.receivedTable.setModel(new ReceivedTableModel(parent.receiveds));
					parent.receivedTable.updateUI();
					parent.setTableSize();
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		
	}
	public class ReceivedTableModel extends AbstractTableModel {
	    private List<Received> receiveds;
	    
	    public ReceivedTableModel(List<Received> receiveds) {
	        this.receiveds = receiveds;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return receiveds.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 8;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	Received p = receiveds.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getReceivedCode();
	            case 2 :
	                return p.getReceivedDate();
	            case 3 :
	                return p.getRitNo();
	            case 4 :
	                return p.getSupplier();
	            case 5 :
	                return p.getDeliveryNote();
	            case 6 :
	                return p.getReceivedStatus();
	            case 7 :
	                return "View";
	            default :
	                return "";
	        }
	    }
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	

	    /**
	     * Method to getColumnName
	     * @param column columnIndex
	     * @return String column name
	     */
	    public String getColumnName(int column) {
	        switch(column){
	            case 0 : 
	                return "ID";
	            case 1 :
	                return "Kode Penerimaan";
	            case 2 :
	                return "Tanggal Penerimaan";
	            case 3 :
	                return "No Rit";
	            case 4 :
	                return "Supplier";
	            case 5 :
	                return "No Dokumen";
	            case 6 :
	                return "Status";
	            case 7 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}
}
