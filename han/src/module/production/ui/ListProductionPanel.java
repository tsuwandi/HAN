package module.production.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import main.panel.MainPanel;
import model.User;
import module.production.model.Production;

public class ListProductionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton searchBtn;
	private JTextField searchField;
	JTable productionTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputProductionBtn;
	
	ProductionTableModel productionTableModel;
	List<Production> productions;
	ListProductionPanel listProductionPanel;
	public ListProductionPanel() {
		setLayout(null);
		listProductionPanel = this;
		
		JLabel lblBreadcrumb = new JLabel("ERP > Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("PRODUKSI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		searchBtn = new JButton("Cari");
		searchBtn.setBounds(950,130,100,30);
		add(searchBtn);
		
		searchField = new JTextField();
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
		
		scrollPane =  new JScrollPane(productionTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		productionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(productionTable.columnAtPoint(e.getPoint())==7)
				MainPanel.changePanel("module.pembelian.ui.ViewReceivedDetailPanel", productions.get(productionTable.getSelectedRow()));
			}
		});
		
		inputProductionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.production.ui.CreateProductionPanel");
			}
		});
	
//		try {
//			productions = ReceivedDAOFactory.getReceivedDAO().getAll();
//			productionTable.setModel(new ProductionTableModel(productions));
//			productionTable.updateUI();
//			setTableSize();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
		advancedSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				PopUpAdvanceSearch pop = new PopUpAdvanceSearch(listProductionPanel);
//				pop.show();
//				pop.setLocationRelativeTo(null);
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				try {
//					productions = ReceivedDAOFactory.getReceivedDAO().getAllBySearch(searchField.getText());
//					productionTable.setModel(new ProductionTableModel(productions));
//					productionTable.updateUI();
//					setTableSize();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
				
			}
		});
		
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
		
		
		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);
		
		column2.setPreferredWidth(200);
		column2.setMinWidth(200);
		column2.setMaxWidth(200);
		
		column3.setPreferredWidth(150);
		column3.setMinWidth(150);
		column3.setMaxWidth(150);
		
		column4.setPreferredWidth(150);
		column4.setMinWidth(150);
		column4.setMaxWidth(150);
		
		column5.setPreferredWidth(150);
		column5.setMinWidth(150);
		column5.setMaxWidth(150);
		
		column6.setPreferredWidth(150);
		column6.setMinWidth(150);
		column6.setMaxWidth(150);
		
		column7.setPreferredWidth(150);
		column7.setMinWidth(150);
		column7.setMaxWidth(150);

	}
	
	public class ProductionTableModel extends AbstractTableModel {
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
	        return 8;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	Production p = productions.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getProductionCode();
	            case 2 :
	                return p.getProductionDate();
	            case 3 :
	                return p.getGroupShiftDescription();
	            case 4 :
	                return p.getShiftName();
	            case 5 :
	                return p.getLineDescription();
	            case 6 :
	                return p.getStatus();
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
	                return "Status";
	            case 7 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}
}
