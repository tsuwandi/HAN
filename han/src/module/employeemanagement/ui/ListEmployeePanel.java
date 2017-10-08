
package module.employeemanagement.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.employeemanagement.model.Employee;
import module.production.model.Production;
import module.production.ui.ListProductionPanel;
import module.util.Pagination;

public class ListEmployeePanel extends JPanel{
	Logger log = LogManager.getLogger(ListProductionPanel.class.getName());
	private static final long serialVersionUID = 1L;
	
	private JButton searchBtn;
	private TextField searchField;
	private JTable employeeTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputEmployeeBtn;
	private PagingPanel<Production> pagingPanel;
	
	private ListEmployeeTableModel employeeTableModel;
	private List<Employee> employees;
	private ListEmployeePanel listEmployeePanel;
	
	public ListEmployeePanel(){
		createGUI();
		listener();
	}
	
	public void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Employee");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Employee");
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
		
		inputEmployeeBtn = new JButton("Buat Baru");
		inputEmployeeBtn.setBounds(730,80,150,30);
		add(inputEmployeeBtn);
	
		employees = new ArrayList<>();
		employeeTableModel = new ListEmployeeTableModel(employees);
		employeeTable = new JTable(employeeTableModel);
		employeeTable.setFocusable(false);
		
		scrollPane =  new JScrollPane(employeeTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
	}
	
	public void setTableSize(){
		employeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		employeeTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = employeeTable.getColumnModel().getColumn(0);
		TableColumn column2 = employeeTable.getColumnModel().getColumn(1);
		TableColumn column3 = employeeTable.getColumnModel().getColumn(2);
		TableColumn column4 = employeeTable.getColumnModel().getColumn(3);
		TableColumn column5 = employeeTable.getColumnModel().getColumn(4);
		TableColumn column6 = employeeTable.getColumnModel().getColumn(6);
		TableColumn column7 = employeeTable.getColumnModel().getColumn(7);
		TableColumn column8 = employeeTable.getColumnModel().getColumn(8);
		TableColumn column9 = employeeTable.getColumnModel().getColumn(9);
		TableColumn column10 = employeeTable.getColumnModel().getColumn(10);
		
		
		
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
	
	
	private void listener(){
		inputEmployeeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.CreateEmployeePanel");
			}
		});
	}
	private class ListEmployeeTableModel extends AbstractTableModel implements Pagination{
		private static final long serialVersionUID = 1L;
		private List<Employee> employees;
	    
	    public ListEmployeeTableModel(List<Employee> employees) {
	        this.employees = employees;
	    }
	    
	    
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return employees.size();
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
	    	Employee p = employees.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getEmpCode();
	            case 2 :
	                return p.getFname()+" "+p.getLname();
	            case 3 :
	                return p.getCurrentAddress();
	            case 4 :
	                return p.getCurrentCity();
	            case 5 :
	                return p.getEmail();
	            case 6 :
	                return p.getPhone();
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
	                return "NIK";
	            case 2 :
	                return "Nama";
	            case 3 :
	                return "Alamat";
	            case 4 :
	                return "Kota";
	            case 5 :
	                return "Email";
	            case 6 :
	                return "Nomor Telpon";
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
			employees = (List<Employee>) list;
		}

	}
}