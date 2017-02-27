package module.production.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Product;
import module.pembelian.model.Received;
import module.production.model.Production;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.util.Pagination;

public class ListBigProductionPanel extends JPanel {
	Logger log = LogManager.getLogger(ListBigProductionPanel.class.getName());
	private static final long serialVersionUID = 1L;
	private JButton searchBtn;
	private TextField searchField;
	JTable productionTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputProductionBtn;
	private PagingPanel<Production> pagingPanel;
	
	ProductionTableModel productionTableModel;
	List<Production> productions;
	ListBigProductionPanel listProductionPanel;
	Map<String, Product> productMap;
	Map<String, PalletCard> palletMap;

	public ListBigProductionPanel() {
		createGUI();
		listener();
		initData();
	}
	
	private void listener(){
		productionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*if(productionTable.columnAtPoint(e.getPoint())==7){
					if(!productions.get(productionTable.getSelectedRow()).getStatus().equals("Complete")){
						MainPanel.changePanel("module.production.ui.CreateProductionPanel", productions.get(productionTable.getSelectedRow()));
					}
				}*/
				if(productionTable.columnAtPoint(e.getPoint())==11){
					MainPanel.changePanel("module.production.ui.ViewBigProductionPanel", pagingPanel.getSubListData().get(productionTable.getSelectedRow()));
				}
			}
		});
		
		inputProductionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.production.ui.CreateBigProductionPanel");
			}
		});
	

		advancedSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpAdvancedSearch pop = new PopUpAdvancedSearch(listProductionPanel);
				pop.show();
				pop.setLocationRelativeTo(null);
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!searchField.getText().equals("")){
					try {
						List<Production> productions = ServiceFactory.getProductionBL().searchProduction(" AND type = '13' AND production_code LIKE '%"+searchField.getText()+"%'");
						updateTableData(productions);
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				inputProductionBtn.requestFocusInWindow();
			}
		});
		
	}
	
	private void initData(){
		try {
			productions = ServiceFactory.getProductionBL().getProduction(" AND type = '13'");
			productMap = new HashMap<>();
			palletMap = new HashMap<>();
			for (Product product : ServiceFactory.getProductionBL().getAllProduct()) {
				productMap.put(product.getProductCode(), product);
			}

			for (PalletCard palletCard : ServiceFactory.getProductionBL().getAllPalletCard()) {
				palletMap.put(palletCard.getPalletCardCode(), palletCard);
			}
			
			productionTable.setModel(new ProductionTableModel(productions));
			
			pagingPanel.setPage(1);
			pagingPanel.setMaxDataPerPage(20);
			pagingPanel.setData(productions);
			pagingPanel.setTable(productionTable);
			pagingPanel.setTableModel(productionTableModel);
			pagingPanel.setBounds(450,510,130,50);
			
			productionTable.updateUI();
			setTableSize();
		} catch (SQLException e) {
			log.error(e.getMessage());
			DialogBox.showError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void createGUI(){
		setLayout(null);
		listProductionPanel = this;
		
		JLabel lblBreadcrumb = new JLabel("ERP > Produksi 13");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("PRODUKSI 13");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		searchBtn = new JButton("Cari");
		searchBtn.setBounds(950,130,100,30);
		add(searchBtn);
		
		searchField = new TextField();
		searchField.setBounds(800, 131, 150, 28);
		add(searchField);
		
		advancedSearchBtn = new JButton("Pencarian Lanjut");
		advancedSearchBtn.setBounds(900,80,150,30);
		add(advancedSearchBtn);
		
		inputProductionBtn = new JButton("Buat Baru");
		inputProductionBtn.setBounds(730,80,150,30);
		add(inputProductionBtn);
	
		productions = new ArrayList<>();
		productionTableModel = new ProductionTableModel(productions);
		productionTable = new JTable(productionTableModel);
		productionTable.setFocusable(false);
		
		scrollPane =  new JScrollPane(productionTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
		
	}
	public void setTableSize(){
		productionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		productionTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = productionTable.getColumnModel().getColumn(0);
		TableColumn column2 = productionTable.getColumnModel().getColumn(1);
		TableColumn column3 = productionTable.getColumnModel().getColumn(2);
		TableColumn column4 = productionTable.getColumnModel().getColumn(3);
		TableColumn column5 = productionTable.getColumnModel().getColumn(4);
		TableColumn column6 = productionTable.getColumnModel().getColumn(6);
		TableColumn column7 = productionTable.getColumnModel().getColumn(7);
		TableColumn column8 = productionTable.getColumnModel().getColumn(8);
		TableColumn column9 = productionTable.getColumnModel().getColumn(9);
		TableColumn column10 = productionTable.getColumnModel().getColumn(10);
		
		
		
		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);
		
		column2.setPreferredWidth(150);
		column2.setMinWidth(150);
		column2.setMaxWidth(150);
		
		column3.setPreferredWidth(100);
		column3.setMinWidth(100);
		column3.setMaxWidth(100);
		
		column4.setPreferredWidth(100);
		column4.setMinWidth(100);
		column4.setMaxWidth(100);
		
		column5.setPreferredWidth(100);
		column5.setMinWidth(100);
		column5.setMaxWidth(100);
		
		column6.setPreferredWidth(100);
		column6.setMinWidth(100);
		column6.setMaxWidth(100);
		
		column7.setPreferredWidth(100);
		column7.setMinWidth(100);
		column7.setMaxWidth(100);
		
		column8.setPreferredWidth(100);
		column8.setMinWidth(100);
		column8.setMaxWidth(100);

		column9.setPreferredWidth(100);
		column9.setMinWidth(100);
		column9.setMaxWidth(100);

		column10.setPreferredWidth(100);
		column10.setMinWidth(100);
		column10.setMaxWidth(100);

	}
	
	public void updateTableData(List<Production> productions){
		productionTable.setModel(new ProductionTableModel(productions));
		productionTable.updateUI();
		setTableSize();
	}
	
	public class ProductionTableModel extends AbstractTableModel implements Pagination{
		private static final long serialVersionUID = 1L;
		private List<Production> productions;
	    
	    public ProductionTableModel(List<Production> productions) {
	        this.productions = productions;
	    }
	    
	    
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return productions.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 12;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	Production p = productions.get(rowIndex);
	    	double productionTotal=0;
		    double rendemen=0;
		    if(p.getProductionResults()!=null){
		    	for (ProductionResult pr : p.getProductionResults()) {
					for (ProductionResultProduct prp : pr.getListProductionResultProduct()) {
						if(productMap.get(prp.getProductCode())!=null){
							Product prod = productMap.get(prp.getProductCode());
							productionTotal+= (prp.getQty()*prod.getLength()*prod.getWidth()*prod.getThickness());
						}
					}
				}
		    }
	    	
	    	rendemen = (productionTotal/1000000)/p.getTotalVolume();
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getProductionCode();
	            case 2 :
	                return new SimpleDateFormat("dd-MM-yyyy").format(p.getProductionDate());
	            case 3 :
	                return p.getGroupShiftDescription();
	            case 4 :
	                return p.getShiftName();
	            case 5 :
	                return p.getLineDescription();
	            case 6 :
	                return p.getProductionTypeDescription();
	            case 7 :
	                return p.getTotalVolume();
	            case 8 :
	                return productionTotal/1000000;
	            case 9 :
	                return p.getTotalVolume()==0 ? 0 : rendemen;
	            case 10 :
	                return p.getStatus();
	            case 11 :
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
	                return "Kode Produksi";
	            case 2 :
	                return "Tanggal Produksi";
	            case 3 :
	                return "Group Shift";
	            case 4 :
	                return "Shift";
	            case 5 :
	                return "Line";
	            case 6 :
	                return "Tipe Produksi";
	            case 7 :
	                return "Total Bahan Baku(cm3)";
	            case 8 :
	                return "Total Hasil Produksi (M3)";
	            case 9 :
	                return "Rendemen (%)";
	            case 10 :
	                return "Status";
	            case 11 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

		@Override
		public <T> void setList(List<T> list) {
			productions = (List<Production>) list;
		}

	}
}
