package module.employeemanagement.ui;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.employeemanagement.model.EmployeeStructure;
import module.employeemanagement.model.PayrollComponent;
import module.util.Pagination;

public class ListEmpStructurePanel extends JPanel{
	Logger log = LogManager.getLogger(ListEmpStructurePanel.class.getName());
	private static final long serialVersionUID = 1L;
	
	private JButton searchBtn;
	private TextField searchField;
	private JTable empStructureTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputEmployeeStrBtn;
	private PagingPanel<EmployeeStructure> pagingPanel;
	
	private EmpStructureTableModel empStructureTableModel;
	private List<EmployeeStructure> empStructures;
	private ListEmpStructurePanel listEmployeePanel;
	
	public ListEmpStructurePanel(){
		createGUI();
		setData();
		listener();
	}
	
	public void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Payroll");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Payroll Komponen");
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
		
		inputEmployeeStrBtn = new JButton("Buat Baru");
		inputEmployeeStrBtn.setBounds(730,80,150,30);
		add(inputEmployeeStrBtn);
	
		empStructures = new ArrayList<>();
		empStructureTableModel = new EmpStructureTableModel(empStructures);
		empStructureTable = new JTable(empStructureTableModel);
		empStructureTable.setFocusable(false);
		
		scrollPane =  new JScrollPane(empStructureTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
	}
	
	private void setData(){
		empStructures = ServiceFactory.getEmployeeManagementBL().getEmpStructure();
		pagingPanel.setPage(1);
		pagingPanel.setMaxDataPerPage(20);
		pagingPanel.setData(empStructures);
		pagingPanel.setTable(empStructureTable);
		pagingPanel.setTableModel(empStructureTableModel);
		pagingPanel.setBounds(450,510,130,50);
	}
	
	public void setTableSize(){
		empStructureTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		empStructureTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = empStructureTable.getColumnModel().getColumn(0);
		TableColumn column2 = empStructureTable.getColumnModel().getColumn(1);
		TableColumn column3 = empStructureTable.getColumnModel().getColumn(2);
		TableColumn column4 = empStructureTable.getColumnModel().getColumn(3);
		TableColumn column5 = empStructureTable.getColumnModel().getColumn(4);
		
		
		
		
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
	

	}
	
	
	private void listener(){
		inputEmployeeStrBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.CreateEmpStructurePanel");
			}
		});
		
		empStructureTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(empStructureTable.columnAtPoint(e.getPoint())==4){
					MainPanel.changePanel("module.employeemanagement.ui.ViewEmpStructurePanel", pagingPanel.getSubListData().get(empStructureTable.getSelectedRow()));
				}
			}
		});
	}
	
	
	private class EmpStructureTableModel extends AbstractTableModel implements Pagination{
		private static final long serialVersionUID = 1L;
		private List<EmployeeStructure> empStructures;
	    
	    public EmpStructureTableModel(List<EmployeeStructure> empStructures) {
	        this.empStructures = empStructures;
	    }
	    
	    
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return empStructures.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 5;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	EmployeeStructure p = empStructures.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getCode();
	            case 2 : 
	                return p.getPosition();
	            case 3 :
	            	return p.getOrgValue();
	            case 4 :
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
	                return "Code";
	            case 2 :
	                return "Posisi";
	            case 3 :
	                return "Divisi";
	            case 4 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

		@Override
		public <T> void setList(List<T> list) {
			empStructures = (List<EmployeeStructure>) list;
		}
	}
}
