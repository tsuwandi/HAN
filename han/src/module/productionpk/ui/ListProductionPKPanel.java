package module.productionpk.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import module.pembelian.model.Received;
import module.production.model.Production;
import module.productionpk.model.ProdPK;
import module.util.Pagination;

public class ListProductionPKPanel extends JPanel {
	Logger log = LogManager.getLogger(ListProductionPKPanel.class.getName());
	private static final long serialVersionUID = 1L;
	private JButton searchBtn;
	private TextField searchField;
	JTable productionTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputProductionBtn;
	private PagingPanel<ProdPK> pagingPanel;
	
	ProductionTableModel productionTableModel;
	List<ProdPK> productions;
	ListProductionPKPanel listProductionPKPanel;

	public ListProductionPKPanel() {
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
				if(productionTable.columnAtPoint(e.getPoint())==8){
					MainPanel.changePanel("module.productionpk.ui.ViewProductionPKPanel", pagingPanel.getSubListData().get(productionTable.getSelectedRow()));
				}
			}
		});
		
		inputProductionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.productionpk.ui.CreateProductionPKPanel");
			}
		});
	

		advancedSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				PopUpAdvancedSearch pop = new PopUpAdvancedSearch(listProductionPKPanel);
//				pop.show();
//				pop.setLocationRelativeTo(null);
			}
		});
		
		/*searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!searchField.getText().equals("")){
					try {
						List<ProdPK> productions = ServiceFactory.getProductionBL().searchProduction(" AND production_code LIKE '%"+searchField.getText()+"%'");
						updateTableData(productions);
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});*/
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				inputProductionBtn.requestFocusInWindow();
			}
		});
		
	}
	
	private void initData(){
		try {
			productions = ServiceFactory.getProdPKBL().getProduction();
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
		listProductionPKPanel = this;
		
		JLabel lblBreadcrumb = new JLabel("ERP > Produksi Protol Klem");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("PRODUKSI PROTOL KLEM");
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
		
		column5.setPreferredWidth(150);
		column5.setMinWidth(150);
		column5.setMaxWidth(150);
		
		column6.setPreferredWidth(150);
		column6.setMinWidth(150);
		column6.setMaxWidth(150);
		
		column7.setPreferredWidth(150);
		column7.setMinWidth(150);
		column7.setMaxWidth(150);
		
		column8.setPreferredWidth(150);
		column8.setMinWidth(150);
		column8.setMaxWidth(150);


	}
	
	public void updateTableData(List<ProdPK> productions){
		productionTable.setModel(new ProductionTableModel(productions));
		productionTable.updateUI();
		setTableSize();
	}
	
	public class ProductionTableModel extends AbstractTableModel implements Pagination{
		private static final long serialVersionUID = 1L;
		private List<ProdPK> productions;
	    
	    public ProductionTableModel(List<ProdPK> productions) {
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
	        return 9;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	ProdPK p = productions.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getProdPKCode();
	            case 2 :
	                return new SimpleDateFormat("dd-MM-yyyy").format(p.getProductionDate());
	            case 3 :
	                return p.getGroupShiftDescription();
	            case 4 :
	                return p.getShiftName();
	            case 5 :
	                return p.getLineDescription();
	            case 6 :
	                return p.getTotalMaterialKlem()+p.getTotalMaterialProtol();
	            case 7 :
	                return p.getStatus();
	            case 8 :
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
	                return "Total Bahan Baku";
	            case 7 :
	                return "Status";
	            case 8 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

		@Override
		public <T> void setList(List<T> list) {
			productions = (List<ProdPK>) list;
		}

	}
}
