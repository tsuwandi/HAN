package module.stockopname.ui;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.EditableHeaderRenderer;
import main.component.TextField;
import model.User;
import module.production.model.Production;
import module.production.ui.ListBigProductionPanel;
import module.production.ui.ListProductionPanel;
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
					soScheduledProducts = ServiceFactory.getStockOpnameBL().getSOSearchScheduledProduct(sb.toString(), objs);
					if(productMap!=null){
						for (SetSoScheduledProduct setSoScheduledProduct : soScheduledProducts) {
							if(productMap.get(setSoScheduledProduct.getProductID())!=null)setSoScheduledProduct.setFlag(true);
						}
					}
					productSOTableModel = new ProductTableModel(soScheduledProducts);
					productSOTable.setModel(productSOTableModel);
					productSOTable.updateUI();
					setTableHeader();
				} catch (Exception e2) {
					log.error(e2.getMessage());
					e2.printStackTrace();
				}
			
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
			setTableHeader();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError(e.getMessage());
			return;
		}
		
	}
	
	public void setTableHeader(){
		TableColumn col = productSOTable.getColumnModel().getColumn(0);
		JPanel panel = new JPanel();
		JCheckBox checkBox = new JCheckBox();
		productSOTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		productSOTable.getTableHeader().setResizingAllowed(false);
		TableColumn column2 = productSOTable.getColumnModel().getColumn(1);
		TableColumn column3 = productSOTable.getColumnModel().getColumn(2);
		TableColumn column4 = productSOTable.getColumnModel().getColumn(3);
		TableColumn column5 = productSOTable.getColumnModel().getColumn(4);


		col.setPreferredWidth(50);
		col.setMinWidth(50);
		col.setMaxWidth(50);

		column2.setPreferredWidth(100);
		column2.setMinWidth(100);
		column2.setMaxWidth(100);

		column3.setPreferredWidth(100);
		column3.setMinWidth(100);
		column3.setMaxWidth(100);

		column4.setPreferredWidth(200);
		column4.setMinWidth(200);
		column4.setMaxWidth(200);
		
		column5.setPreferredWidth(0);
		column5.setMinWidth(0);
		column5.setMaxWidth(0);
		
		checkBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkBox.isSelected()==true){
					for (SetSoScheduledProduct setSoScheduledProduct : soScheduledProducts) {
						setSoScheduledProduct.setFlag(true);
						if(productMap.get(setSoScheduledProduct.getProductID())==null){
							productMap.put(setSoScheduledProduct.getProductID(), setSoScheduledProduct);
						}
					}
				}else{
					for (SetSoScheduledProduct setSoScheduledProduct : soScheduledProducts) {
						setSoScheduledProduct.setFlag(false);
						if(productMap.get(setSoScheduledProduct.getProductID())!=null){
							productMap.remove(setSoScheduledProduct.getProductID());
						}
					}
				}
			}
		});
		panel.add(checkBox);
		col.setHeaderRenderer(new EditableHeaderRenderer(panel));
		
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
