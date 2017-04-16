package module.stockopname.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.TextField;
import model.User;
import module.stockopname.model.ProductSO;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;

public class PopUpProductSOSchedule extends JDialog{
	Logger log = LogManager.getLogger(PopUpProductSOSchedule.class.getName());
	JTable productSOTable;
	ProductTableModel productSOTableModel;
	List<SetSoScheduledProduct> soScheduledProducts;
	JScrollPane setSoScrollPane;
	
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
	Map<Integer, SetSoScheduledProduct> productMap;
	
	public PopUpProductSOSchedule(JPanel parentPanel) {
		super((JFrame)parentPanel.getTopLevelAncestor());
		setLayout(null);
		setSize(500,500);
		setTitle("Cari Produk");
		this.dialog = this;
		createNewScheduledSOPanel = (CreateNewScheduledSOPanel)parentPanel;
		
		searchBtn = new JButton("Cari");
		searchBtn.setBounds(350,20,100,30);
		add(searchBtn);
		
		productCategoryLbl = new JLabel("Kategori Produk");
		productCategoryLbl.setBounds(30,20,150,30);
		add(productCategoryLbl);
		
		productCategoryField = new TextField();
		productCategoryField.setBounds(190,20,150,30);
		add(productCategoryField);
		
		productCodeLbl = new JLabel("Kode Produk");
		productCodeLbl.setBounds(30,60,150,30);
		add(productCodeLbl);
		
		productCodeField = new TextField();
		productCodeField.setBounds(190,60,150,30);
		add(productCodeField);
		
		productNameLbl = new JLabel("Nama Produk");
		productNameLbl.setBounds(30,100,150,30);
		add(productNameLbl);
		
		productNameField = new TextField();
		productNameField.setBounds(190,100,150,30);
		add(productNameField);

		productSOTable = new JTable(new ProductTableModel(new ArrayList<SetSoScheduledProduct>()));
		
		setSoScrollPane = new JScrollPane(productSOTable);
		setSoScrollPane.setBounds(20,140,450,200);
		add(setSoScrollPane);
		
		addBtn = new JButton("Tambah");
		addBtn.setBounds(360,360,100,30);
		add(addBtn);
		
		setData();
		
		productSOTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SetSoScheduledProduct setSoScheduledProduct = soScheduledProducts.get(productSOTable.getSelectedRow());
				if(setSoScheduledProduct.isFlag()){
					setSoScheduledProduct.setFlag(false);
					productMap.remove(setSoScheduledProduct.getProductID());
				}
				else{	
					setSoScheduledProduct.setFlag(true);
					productMap.put(setSoScheduledProduct.getProductID(), setSoScheduledProduct);
				}
				productSOTable.updateUI();
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterator<SetSoScheduledProduct> i = productMap.values().iterator();
				while (i.hasNext()) {
				   if(i.next().isFlag()==false)i.remove();
				}
				createNewScheduledSOPanel.setProductMap(productMap);
				dialog.dispose();
			}
		});
	}
	
	private void setData(){
		try {
			soScheduledProducts = ServiceFactory.getStockOpnameBL().getSOScheduledProduct();
			productMap = createNewScheduledSOPanel.getProductMap()!=null ?createNewScheduledSOPanel.getProductMap():new HashMap<>();
			if(productMap!=null){
				for (SetSoScheduledProduct setSoScheduledProduct : soScheduledProducts) {
					if(productMap.get(setSoScheduledProduct.getProductID())!=null)setSoScheduledProduct.setFlag(true);
				}
			}
			
			productSOTableModel = new ProductTableModel(soScheduledProducts);
			productSOTable.setModel(productSOTableModel);
			productSOTable.updateUI();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError(e.getMessage());
			return;
		}
	}
	

	class ProductTableModel extends AbstractTableModel {
	    private List<SetSoScheduledProduct> productSOs;
	    
	    public ProductTableModel(List<SetSoScheduledProduct> productSOs) {
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
	        return 5;
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
	    	SetSoScheduledProduct p = productSOs.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.isFlag();
	            case 1 : 
	                return p.getProductCategory();
	            case 2 :
	                return p.getProductCode();
	            case 3 :
	                return p.getProductName();
	            case 4 :
	            	return p.getId();
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
	            case 4 :
	            	return "ID";
	            default :
	                return "";
	        }
	    }

	}
}
