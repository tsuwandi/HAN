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
import module.production.model.Production;
import module.production.ui.ListBigProductionPanel;
import module.production.ui.ListProductionPanel;
import module.stockopname.model.ProductSO;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;
import module.stockopname.model.StockOpnameProduct;

public class PopUpSOManualProduct extends JDialog{
	Logger log = LogManager.getLogger(PopUpSOManualProduct.class.getName());
	JTable productSOTable;
	ProductTableModel productSOTableModel;
	List<StockOpnameProduct> soProducts;
	JScrollPane setSoScrollPane;
	
	JLabel productCategoryLbl;
	JLabel productCodeLbl;
	JLabel productNameLbl;
	
	TextField productCategoryField;
	TextField productCodeField;
	TextField productNameField;
	
	JButton addBtn;
	JButton searchBtn;
	CreateNewStockOpnamePanel createNewStockOpnamePanel;
	JDialog dialog;
	Map<Integer, StockOpnameProduct> productMap;
	
	public PopUpSOManualProduct(JPanel parentPanel) {
		super((JFrame)parentPanel.getTopLevelAncestor());
		setLayout(null);
		setSize(500,500);
		setTitle("Cari Produk");
		this.dialog = this;
		createNewStockOpnamePanel = (CreateNewStockOpnamePanel)parentPanel;
		
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

		productSOTable = new JTable(new ProductTableModel(new ArrayList<StockOpnameProduct>()));
		
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
				StockOpnameProduct setSoScheduledProduct = soProducts.get(productSOTable.getSelectedRow());
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
				Iterator<StockOpnameProduct> i = productMap.values().iterator();
				while (i.hasNext()) {
				   if(i.next().isFlag()==false)i.remove();
				}
				createNewStockOpnamePanel.setProductMap(productMap);
				dialog.dispose();
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb = new StringBuffer();
				List<Object> objs = new ArrayList<>();
				if(!productCategoryField.getText().equals("")){
					sb.append(" AND product_category LIKE ?");
					objs.add(productCategoryField.getText());
				}
				if(!productCodeField.getText().equals("")){
					sb.append(" AND product_code LIKE ?");
					objs.add(productCodeField.getText());
				}
				if(!productNameField.getText().equals("")){
					sb.append(" AND product_name LIKE ?");
					objs.add(productNameField.getText());
				}
				try {
					soProducts = ServiceFactory.getStockOpnameBL().getSearchSOManualProduct(sb.toString(), objs);
					if(productMap!=null){
						for (StockOpnameProduct setSoScheduledProduct : soProducts) {
							if(productMap.get(setSoScheduledProduct.getProductID())!=null)setSoScheduledProduct.setFlag(true);
						}
					}
					productSOTableModel = new ProductTableModel(soProducts);
					productSOTable.setModel(productSOTableModel);
					productSOTable.updateUI();
				} catch (Exception e2) {
					log.error(e2.getMessage());
					e2.printStackTrace();
				}
			
			}
		});
	}
	
	private void setData(){
		try {
			soProducts = ServiceFactory.getStockOpnameBL().getSOProduct();
			productMap = createNewStockOpnamePanel.getProductMap()!=null ?createNewStockOpnamePanel.getProductMap():new HashMap<>();
			if(productMap!=null){
				for (StockOpnameProduct setSoScheduledProduct : soProducts) {
					if(productMap.get(setSoScheduledProduct.getProductID())!=null)setSoScheduledProduct.setFlag(true);
				}
			}
			productSOTableModel = new ProductTableModel(soProducts);
			productSOTable.setModel(productSOTableModel);
			productSOTable.updateUI();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError(e.getMessage());
			return;
		}
	}
	

	class ProductTableModel extends AbstractTableModel {
	    private List<StockOpnameProduct> productSOs;
	    
	    public ProductTableModel(List<StockOpnameProduct> productSOs) {
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
	    	StockOpnameProduct p = productSOs.get(rowIndex);
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
	            	return p.getProductID();
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
