package module.packingresult.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import module.packingresult.model.Packing;
import module.packingresult.model.PackingResult;
import module.production.ui.ListProductionPanel;
import module.util.Pagination;

public class ListPackingResultPanel extends JPanel{

	Logger log = LogManager.getLogger(ListProductionPanel.class.getName());
	private static final long serialVersionUID = 1L;
	private JButton searchBtn;
	private TextField searchField;
	JTable packingTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputPackingBtn;
	private PagingPanel<Packing> pagingPanel;
	
	PackingTableModel packingTableModel;
	List<Packing> packings;
	ListPackingResultPanel listPackagingResultPanel;
	
	private static final String BIG_CRATE_A="PDC009-9";
	private static final String SMALL_CRATE_A="PDC009-10";
	private static final String BIG_CRATE_A1="PDC009-11";
	private static final String SMALL_CRATE_A1="PDC009-12";
	private static final String BIG_CRATE_B="PDC009-13";
	private static final String SMALL_CRATE_B="PDC009-14";
	private static final String BIG_CRATE_BNP="PDC009-15";
	private static final String SMALL_CRATE_BNP="PDC009-16";
	

	public ListPackingResultPanel() {
		createGUI();
		listener();
		initData();
	}
	
	private void listener(){
		packingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(packingTable.columnAtPoint(e.getPoint())==11){
					MainPanel.changePanel("module.packingresult.ui.ViewPackingPanel", pagingPanel.getSubListData().get(packingTable.getSelectedRow()));
				}
			}
		});
		
		inputPackingBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.packingresult.ui.CreateNewPackingPanel");
			}
		});
	

		advancedSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				PopUpAdvancedSearch pop = new PopUpAdvancedSearch(listProductionPanel);
//				pop.show();
//				pop.setLocationRelativeTo(null);
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!searchField.getText().equals("")){
					try {
						//TODO repair 
						//List<Production> productions = ServiceFactory.getProductionBL().searchProduction(" AND production_code LIKE '%"+searchField.getText()+"%'");
						updateTableData(packings);
					} catch (Exception e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				inputPackingBtn.requestFocusInWindow();
			}
		});
		
	}
	
	private void initData(){
		try {
			packings = ServiceFactory.getPackingBL().getPackings();
			packingTable.setModel(new PackingTableModel(packings));
			
			pagingPanel.setPage(1);
			pagingPanel.setMaxDataPerPage(20);
			pagingPanel.setData(packings);
			pagingPanel.setTable(packingTable);
			pagingPanel.setTableModel(packingTableModel);
			pagingPanel.setBounds(450,510,130,50);
			
			packingTable.updateUI();
			setTableSize();
		} catch (Exception e) {
			log.error(e.getMessage());
			DialogBox.showError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void createGUI(){
		setLayout(null);
		listPackagingResultPanel = this;
		
		JLabel lblBreadcrumb = new JLabel("ERP > PACKING");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Packing");
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
		
		inputPackingBtn = new JButton("Buat Baru");
		inputPackingBtn.setBounds(730,80,150,30);
		add(inputPackingBtn);
	
		packings = new ArrayList<>();
		packingTableModel = new PackingTableModel(packings);
		packingTable = new JTable(packingTableModel);
		packingTable.setFocusable(false);
		
		scrollPane =  new JScrollPane(packingTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
		
	}
	public void setTableSize(){
		packingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		packingTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = packingTable.getColumnModel().getColumn(0);
		TableColumn column2 = packingTable.getColumnModel().getColumn(1);
		TableColumn column3 = packingTable.getColumnModel().getColumn(2);
		TableColumn column4 = packingTable.getColumnModel().getColumn(3);
		TableColumn column5 = packingTable.getColumnModel().getColumn(4);
		TableColumn column6 = packingTable.getColumnModel().getColumn(6);
		TableColumn column7 = packingTable.getColumnModel().getColumn(7);
		TableColumn column8 = packingTable.getColumnModel().getColumn(8);
		TableColumn column9 = packingTable.getColumnModel().getColumn(9);
		TableColumn column10 = packingTable.getColumnModel().getColumn(10);
		TableColumn column11 = packingTable.getColumnModel().getColumn(11);
		
		
		
		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);
		
		column2.setPreferredWidth(100);
		column2.setMinWidth(100);
		column2.setMaxWidth(100);
		
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
		
		column11.setPreferredWidth(50);
		column11.setMinWidth(50);
		column11.setMaxWidth(50);

	}
	
	public void updateTableData(List<Packing> productions){
		packingTable.setModel(new PackingTableModel(productions));
		packingTable.updateUI();
		setTableSize();
	}
	
	public class PackingTableModel extends AbstractTableModel implements Pagination{
		private static final long serialVersionUID = 1L;
		private List<Packing> packings;
	    
	    public PackingTableModel(List<Packing> packings) {
	        this.packings = packings;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return packings.size();
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
	    	Packing p = packings.get(rowIndex);
	    	List<PackingResult> packResults = p.getPackingResults();
	    	double bigCrateA = 0;
	    	double smallCrateA = 0;
	    	double bigCrateA1 = 0;
	    	double smallCrateA1 = 0;
	    	double bigCrateB = 0;
	    	double smallCrateB = 0;
	    	double bigCrateBNP = 0;
	    	double smallCrateBNP = 0;
	    	
	    	for(PackingResult packResult : packResults){
	    		if(packResult.getProductCode().equals(BIG_CRATE_A)) bigCrateA = packResult.getQty();
	    		else if (packResult.getProductCode().equals(SMALL_CRATE_A)) smallCrateA = packResult.getQty();
	    		else if (packResult.getProductCode().equals(BIG_CRATE_A1)) bigCrateA1 = packResult.getQty();
	    		else if (packResult.getProductCode().equals(SMALL_CRATE_A1)) smallCrateA1 = packResult.getQty();
	    		else if (packResult.getProductCode().equals(BIG_CRATE_B)) bigCrateB = packResult.getQty();
	    		else if (packResult.getProductCode().equals(SMALL_CRATE_B)) smallCrateB = packResult.getQty();
	    		else if (packResult.getProductCode().equals(BIG_CRATE_BNP)) bigCrateBNP = packResult.getQty();
	    		else if (packResult.getProductCode().equals(SMALL_CRATE_BNP)) smallCrateBNP = packResult.getQty();
	    	}
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	            	 new SimpleDateFormat("dd-MM-yyyy").format(p.getPackingDate());
	            case 2 :
	                return bigCrateA;
	            case 3 :
	                return smallCrateA;
	            case 4 :
	                return bigCrateA1;
	            case 5 :
	                return smallCrateA1;
	            case 6 :
	                return bigCrateB;
	            case 7 :
	                return smallCrateB;
	            case 8 :
	                return bigCrateBNP;
	            case 9 :
	                return smallCrateBNP;
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
	                return "Tanggal Packing";
	            case 2 :
	                return "Crate Besar A";
	            case 3 :
	                return "Crate Kecil A";
	            case 4 :
	                return "Crate Besar A1";
	            case 5 :
	                return "Crate Kecil A1";
	            case 6 :
	                return "Crate Besar B";
	            case 7 :
	                return "Crate Kecil B";
	            case 8 :
	                return "Crate Besar BNP";
	            case 9 :
	                return "Crate Kecil BNP";
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
			packings = (List<Packing>) list;
		}

	}

}
