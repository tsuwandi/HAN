package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controller.ReceivedDAOFactory;
import model.User;
import module.pembelian.model.Employee;
import module.pembelian.model.PicDocking;

public class PopUpPicDocking extends JDialog {
	JTable picTable;
	PicDockingTableModel picDockingTableModel;
	List<PicDocking> picDockings;
	JScrollPane picScrollPane;
	
	JButton addBtn;
	JButton searchBtn;
	JTextField searchField;
	AddReceivedDetailPanel addReceivedDetail;
	JDialog dialog;
	
	public PopUpPicDocking(JPanel parentPanel) {
		setLayout(null);
		setSize(400,400);
		this.dialog = this;
		addReceivedDetail = (AddReceivedDetailPanel)parentPanel;
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(270,20,100,30);
		add(searchBtn);
		
		searchField = new JTextField();
		searchField.setBounds(150,20,100,30);
		add(searchField);
		
		try {
			picDockings = ReceivedDAOFactory.getPICDockingDAO().getEmployeeBasedOnPosition("POS0001");
			picDockingTableModel = new PicDockingTableModel(picDockings);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		picTable = new JTable(picDockingTableModel);
		
		picScrollPane = new JScrollPane(picTable);
		picScrollPane.setBounds(20,70,350,200);
		add(picScrollPane);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(270,300,100,30);
		add(addBtn);
		
		picTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(picDockings.get(picTable.getSelectedRow()).isFlag())
					picDockings.get(picTable.getSelectedRow()).setFlag(false);
				else	
					picDockings.get(picTable.getSelectedRow()).setFlag(true);
				picTable.updateUI();
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterator<PicDocking> i = picDockings.iterator();
				while (i.hasNext()) {
				   if(i.next().isFlag()==false)i.remove();
				}
				   
				addReceivedDetail.setTablePic(picDockings);
				dialog.dispose();
			}
		});
		
	}
	

	class PicDockingTableModel extends AbstractTableModel {
	    private List<PicDocking> picDockings;
	    
	    public PicDockingTableModel(List<PicDocking> picDockings) {
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
	    	PicDocking p = picDockings.get(rowIndex);
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
}
