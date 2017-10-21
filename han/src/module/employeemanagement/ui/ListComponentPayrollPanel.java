package module.employeemanagement.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
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
import module.employeemanagement.model.PayrollComponent;
import module.employeemanagement.model.Position;
import module.production.ui.ListProductionPanel;
import module.util.Pagination;

public class ListComponentPayrollPanel extends JPanel {
	Logger log = LogManager.getLogger(ListComponentPayrollPanel.class.getName());
	private static final long serialVersionUID = 1L;
	
	private JButton searchBtn;
	private TextField searchField;
	private JTable payrollTable;
	private JScrollPane scrollPane;
	
	private JButton advancedSearchBtn;
	private JButton inputEmployeeBtn;
	private PagingPanel<PayrollComponent> pagingPanel;
	
	private PayrollTableModel payrollTableModel;
	private List<PayrollComponent> payrollComponents;
	private ListComponentPayrollPanel listEmployeePanel;
	
	public ListComponentPayrollPanel(){
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
		
		inputEmployeeBtn = new JButton("Buat Baru");
		inputEmployeeBtn.setBounds(730,80,150,30);
		add(inputEmployeeBtn);
	
		payrollComponents = new ArrayList<>();
		payrollTableModel = new PayrollTableModel(payrollComponents);
		payrollTable = new JTable(payrollTableModel);
		payrollTable.setFocusable(false);
		
		scrollPane =  new JScrollPane(payrollTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);
		
		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
	}
	
	private void setData(){
		payrollComponents = ServiceFactory.getEmployeeManagementBL().getPayrollComponent();
		pagingPanel.setPage(1);
		pagingPanel.setMaxDataPerPage(20);
		pagingPanel.setData(payrollComponents);
		pagingPanel.setTable(payrollTable);
		pagingPanel.setTableModel(payrollTableModel);
		pagingPanel.setBounds(450,510,130,50);
	}
	
	public void setTableSize(){
		payrollTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		payrollTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = payrollTable.getColumnModel().getColumn(0);
		TableColumn column2 = payrollTable.getColumnModel().getColumn(1);
		TableColumn column3 = payrollTable.getColumnModel().getColumn(2);
		TableColumn column4 = payrollTable.getColumnModel().getColumn(3);
		TableColumn column5 = payrollTable.getColumnModel().getColumn(4);
		
		
		
		
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
		inputEmployeeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.CreatePayrollComponentPanel");
			}
		});
		
		payrollTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(payrollTable.columnAtPoint(e.getPoint())==7){
					MainPanel.changePanel("module.employeemanagement.ui.ViewPayrollComponentPanel", pagingPanel.getSubListData().get(payrollTable.getSelectedRow()));
				}
			}
		});
	}
	
	
	private class PayrollTableModel extends AbstractTableModel implements Pagination{
		private static final long serialVersionUID = 1L;
		private List<PayrollComponent> payrollComponents;
	    
	    public PayrollTableModel(List<PayrollComponent> payrollComponents) {
	        this.payrollComponents = payrollComponents;
	    }
	    
	    
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return payrollComponents.size();
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
	    	PayrollComponent p = payrollComponents.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.getId();
	            case 1 : 
	                return p.getCode();
	            case 2 : 
	                return p.getDescription();
	            case 3 :
	                return p.getIsDaily()==1?"Ya":"Tidak";
	            case 4 :
	            	return p.getIsSalary()==1?"Ya":"Tidak";
	            case 5 :
	            	return p.getIsThr()==1?"Ya":"Tidak";
	            case 6 :
	            	return p.getIsBonus()==1?"Ya":"Tidak";
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
	                return "Code";
	            case 2 :
	                return "Deskripsi";
	            case 3 :
	                return "Harian";
	            case 4 :
	                return "Gaji";
	            case 5 :
	                return "THR";
	            case 6 :
	                return "Bonus";
	            case 7 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

		@Override
		public <T> void setList(List<T> list) {
			payrollComponents = (List<PayrollComponent>) list;
		}
	}
}
