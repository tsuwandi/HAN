package module.pembelian.ui;

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

import controller.ReceivedDAOFactory;
import main.component.DialogBox;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.pembelian.model.Received;



public class ListReceivedSecurityPanel extends JPanel {
	private JButton searchBtn;
	private JButton createBtn;
	private JButton deleteBtn;
	private TextField searchField;
	JTable receivedTable;
	private JScrollPane scrollPane;
	private JLabel titleLabel;
	private JButton advancedSearchBtn;
	ReceivedTableModel receivedTableModel;
	List<Received> receiveds;
	
	ListReceivedSecurityPanel parent;
	public ListReceivedSecurityPanel() {
		setLayout(null);
		parent = this;
//		titleLabel = new JLabel("Penerimaan Balken");
//		titleLabel.setBounds(400,30,300,80);
//		titleLabel.setFont(new Font("Arial", 1, 30));
//		add(titleLabel);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Penerimaan Balken");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("PENERIMAAN BALKEN");
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
	
		receiveds = new ArrayList<>();
		receivedTableModel = new ReceivedTableModel(receiveds);
		receivedTable = new JTable(receivedTableModel);
		receivedTable.setFocusable(false);
		receivedTable.getTableHeader().setResizingAllowed(false);
		receivedTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(30);
		receivedTable.getColumnModel().getColumn(0).setMaxWidth(30);
		
		scrollPane =  new JScrollPane(receivedTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		createBtn = new JButton("Buat Baru");
		createBtn.setBounds(800,80,100,30);
		add(createBtn);

		
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
			setTableSize();
		} catch (SQLException e1) {
			DialogBox.showError("Tidak Dapat Terhubung ke Database");
			e1.printStackTrace();
		}
		
		advancedSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpAdvanceSearchSecurity pop = new PopUpAdvanceSearchSecurity(parent);
				pop.show();
				pop.setLocationRelativeTo(null);
				pop.setModal(true);
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					receiveds = ReceivedDAOFactory.getReceivedDAO().getAllBySearch(searchField.getText());
					receivedTable.setModel(new ReceivedTableModel(receiveds));
					receivedTable.updateUI();
					setTableSize();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createBtn.requestFocusInWindow();
			}
		});
		
	}
	public void setTableSize(){
		receivedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		receivedTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = receivedTable.getColumnModel().getColumn(0);
		TableColumn column2 = receivedTable.getColumnModel().getColumn(1);
		TableColumn column3 = receivedTable.getColumnModel().getColumn(2);
		TableColumn column4 = receivedTable.getColumnModel().getColumn(3);
		TableColumn column5 = receivedTable.getColumnModel().getColumn(4);
		TableColumn column6 = receivedTable.getColumnModel().getColumn(6);
		TableColumn column7 = receivedTable.getColumnModel().getColumn(7);
		
		
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
	                return new SimpleDateFormat("dd-MM-yyyy").format(p.getReceivedDate());
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
