package module.pembelian.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import controller.ReceivedDAOFactory;

import main.panel.MainPanel;
import model.User;
import module.pembelian.model.Received;



public class ListReceivedSecurityPanel extends JPanel {
	JButton searchBtn;
	JButton createBtn;
	JButton deleteBtn;
	JTextField searchField;
	JTable receivedTable;
	JScrollPane scrollPane;
	JLabel titleLabel;
	
	ReceivedTableModel receivedTableModel;
	List<Received> receiveds;
	
	public ListReceivedSecurityPanel() {
		setLayout(null);
		
		titleLabel = new JLabel("Penerimaan Balken");
		titleLabel.setBounds(400,30,300,80);
		titleLabel.setFont(new Font("Arial", 1, 30));
		add(titleLabel);
		
		searchBtn = new JButton("Cari");
		searchBtn.setBounds(1000,100,70,30);
		add(searchBtn);
		
		searchField = new JTextField();
		searchField.setBounds(900, 100, 100, 30);
		add(searchField);
	
		receiveds = new ArrayList<>();
		receivedTableModel = new ReceivedTableModel(receiveds);
		receivedTable = new JTable(receivedTableModel);
		receivedTable.getTableHeader().setResizingAllowed(false);
		receivedTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(30);
		receivedTable.getColumnModel().getColumn(0).setMaxWidth(30);
		
		scrollPane =  new JScrollPane(receivedTable);
		scrollPane.setBounds(50,150,1000,400);
		add(scrollPane);
		
		createBtn = new JButton("Buat Baru");
		createBtn.setBounds(920,570,100,30);
		add(createBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(800,570,100,30);
		add(deleteBtn);
		
		receivedTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(receivedTable.columnAtPoint(e.getPoint())==7)
				MainPanel.changePanel("module.pembelian.ui.ViewReceivedDetailSecurityPanel", receiveds.get(receivedTable.getSelectedRow()));
			}
		});
		
		createBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.pembelian.ui.AddReceivedDetailSecurityPanel");
			}
		});

		try {
			receiveds = ReceivedDAOFactory.getReceivedDAO().getAll();
			receivedTable.setModel(new ReceivedTableModel(receiveds));
			receivedTable.updateUI();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
	}
	
	class ReceivedTableModel extends AbstractTableModel {
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
