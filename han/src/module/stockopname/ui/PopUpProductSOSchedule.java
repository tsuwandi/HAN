package module.stockopname.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.component.TextField;
import model.User;
import module.pembelian.ui.PopUpPicDocking;
import module.stockopname.model.ProductSO;

public class PopUpProductSOSchedule extends JDialog{
	Logger log = LogManager.getLogger(PopUpPicDocking.class.getName());
	JTable picTable;
	ProductTableModel productSOTableModel;
	List<ProductSO> picDockings;
	JScrollPane picScrollPane;
	
	JLabel productCategoryLbl;
	JLabel productCodeLbl;
	JLabel productNameLbl;
	
	TextField productCategoryField;
	TextField productCodeField;
	TextField productNameField;
	
	JButton addBtn;
	JButton searchBtn;
	CreateNewScheduledSOPanel createNewScheduledSOPanel;
	JDialog dialog;
	
	public PopUpProductSOSchedule(JPanel parentPanel) {
		super((JFrame)parentPanel.getTopLevelAncestor());
		setLayout(null);
		setSize(500,500);
		this.dialog = this;
		createNewScheduledSOPanel = (CreateNewScheduledSOPanel)parentPanel;
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(270,20,100,30);
		add(searchBtn);
		
		productCategoryLbl = new JLabel();
		productCategoryLbl.setBounds(30,20,150,30);
		add(productCategoryLbl);
		
		productCategoryField = new TextField();
		productCategoryField.setBounds(30,20,150,30);
		add(productCategoryField);
		
		productCodeLbl = new JLabel();
		productCodeLbl.setBounds(30,60,150,30);
		add(productCodeLbl);
		
		productCodeField = new TextField();
		productCodeField.setBounds(30,60,150,30);
		add(productCodeField);
		
		productNameLbl = new JLabel();
		productNameLbl.setBounds(30,100,150,30);
		add(productNameLbl);
		
		productNameField = new TextField();
		productNameField.setBounds(30,100,150,30);
		add(productNameField);

		picTable = new JTable(productSOTableModel);
		
		picScrollPane = new JScrollPane(picTable);
		picScrollPane.setBounds(20,140,350,200);
		add(picScrollPane);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(270,360,100,30);
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
				Iterator<ProductSO> i = picDockings.iterator();
				while (i.hasNext()) {
				   if(i.next().isFlag()==false)i.remove();
				}
				dialog.dispose();
			}
		});
		
	}
	

	class ProductTableModel extends AbstractTableModel {
	    private List<ProductSO> productSOs;
	    
	    public ProductTableModel(List<ProductSO> productSOs) {
	        this.productSOs = productSOs;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return productSOs.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 4;
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
	    	ProductSO p = productSOs.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.isFlag();
	            case 1 : 
	                return p.getProductCategoryName();
	            case 2 :
	                return p.getProductCode();
	            case 3 :
	                return p.getProductName();
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
	                return "Kategori Produk";
	            case 2 :
	                return "Kode Produk";
	            case 3 :
	                return "Nama Produk";
	            default :
	                return "";
	        }
	    }

	}
}
