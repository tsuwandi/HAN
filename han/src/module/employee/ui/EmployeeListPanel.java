package module.employee.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.DaoFactory;
import controller.ServiceFactory;
import main.panel.MainPanel;
import module.employee.model.Employee;

public class EmployeeListPanel extends JPanel {
	
	private JLabel employeeLbl;
	private JButton createNewBtn;
	private JButton exportBtn;
	private JButton advanceSearchBtn;
	private JButton searchBtn;

	private JTextField searchField;
	private JTable employeeTable;
	
	private EmployeeTableModel employeeTableModel;
	private JScrollPane scrollPane;
	public List<Employee> employees = null;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Create the panel.
	 */
	public EmployeeListPanel() {
		setLayout(null);
		
		employeeLbl = new JLabel("KARYAWAN");
		employeeLbl.setFont(new Font("", Font.BOLD, 24));
		employeeLbl.setBounds(20, 20, 200, 50);
		
		createNewBtn = new JButton("Buat Baru");
		createNewBtn.setBounds(760, 30, 120, 50);
		createNewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.employee.ui.CreateEmployeePanel");
			}
		});

		exportBtn = new JButton("Export");
		exportBtn.setBounds(890, 30, 120, 50);

		advanceSearchBtn = new JButton("<html><p>Pencarian Lanjut</p></html>");
		advanceSearchBtn.setBounds(1020, 30, 120, 50);
		advanceSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.employee.ui.EmployeeSearchPanel");
				
			}
		});

		searchBtn = new JButton("Cari");
		searchBtn.setBounds(1060, 100, 80, 25);

		searchField = new JTextField();
		searchField.setBounds(900, 100, 150, 25);

		try{
			employees = ServiceFactory.getEmployeeBL().getAll();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		
		employeeTableModel = new EmployeeTableModel(employees);
		employeeTable = new JTable(employeeTableModel);
		employeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		employeeTable.getTableHeader().setReorderingAllowed(false);
		employeeTable.getTableHeader().setResizingAllowed(false);
		employeeTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		employeeTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		employeeTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		employeeTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		employeeTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		employeeTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		employeeTable.getColumnModel().getColumn(6).setPreferredWidth(80);
		employeeTable.getColumnModel().getColumn(7).setPreferredWidth(120);
		employeeTable.getColumnModel().getColumn(8).setPreferredWidth(80);
		employeeTable.getColumnModel().getColumn(9).setPreferredWidth(100);
		employeeTable.getColumnModel().getColumn(10).setPreferredWidth(80);
		employeeTable.getColumnModel().getColumn(11).setPreferredWidth(80);
		employeeTable.getColumnModel().getColumn(12).setPreferredWidth(77);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		employeeTable.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
		
//		JLabel headerLbl = new JLabel();
//		headerLbl.setBorder(new LineBorder(Color.black, 1));
//		headerLbl.setBackground(Color.gray);
//		headerLbl.setOpaque(false);
//		DefaultTableCellRenderer centerHeaderRenderer = new DefaultTableCellRenderer();
//		centerHeaderRenderer.setHorizontalAlignment( headerLbl.CENTER );
//		employeeTable.getTableHeader().setDefaultRenderer(centerHeaderRenderer);
		
		employeeTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      Employee employee;
			      // do some action if appropriate column
			      if(column == 12){
			    	  employee = new Employee();
			    	  Date startDate = null;
					try {
						startDate = dateFormat.parse(employeeTable.getValueAt(row, 7).toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				      Date dob = null;
					try {
						dob = dateFormat.parse(employeeTable.getValueAt(row, 9).toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  employee.setEmployeeId(employeeTable.getValueAt(row, 1).toString());
				      employee.setEmployeeName(employeeTable.getValueAt(row, 2).toString());
				      employee.setEmployeeType(employeeTable.getValueAt(row, 3).toString());
				      employee.setPosition(employeeTable.getValueAt(row, 4).toString());
				      employee.setDepartment(employeeTable.getValueAt(row, 5).toString());
				      employee.setDivision(employeeTable.getValueAt(row, 6).toString());
				      employee.setStartDate(startDate);
				      employee.setCity(employeeTable.getValueAt(row, 8).toString());
				      employee.setDob(dob);
				      employee.setGender(employeeTable.getValueAt(row, 10).toString());
				      employee.setEmployeeStatus(employeeTable.getValueAt(row, 11).toString());
				      
				      MainPanel.changePanel("module.employee.ui.EmployeeDisplayPanel", employee);
			      }
			    }
			  }
			});
		
		scrollPane = new JScrollPane(employeeTable);
		scrollPane.setBounds(20, 200, 1130, 265);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		add(employeeLbl);
		add(createNewBtn);
		add(exportBtn);
		add(advanceSearchBtn);
		add(searchField);
		add(searchBtn);
		add(scrollPane);
	}
	
	class EmployeeTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		
		private List<Employee> employees;
		int seq=0;

		public EmployeeTableModel(List<Employee> employees) {
			this.employees = employees;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return employees.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 13;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link Supplier}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Employee p = employees.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return seq = rowIndex+1;
			case 1:
				return p.getEmployeeId();
			case 2:
				return p.getEmployeeName();
			case 3:
				return p.getEmployeeType();
			case 4: 
				return p.getPosition();
			case 5:
				return p.getDepartment();
			case 6:
				return p.getDivision();
			case 7:
				return p.getStartDate();
			case 8:
				return p.getCity();
			case 9:
				return p.getDob();
			case 10:
				return p.getGender();
			case 11:
				return p.getStatus();
			case 12:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */		
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "No";
			case 1:
				return "NIK";
			case 2:
				return "Nama Karyawan";
			case 3:
				return "Tipe Karyawan";
			case 4:
				return "Jabatan";
			case 5:
				return "Departemen";
			case 6:
				return "Divisi";
			case 7:
				return "Tanggal Mulai Kerja";
			case 8:
				return "Kota Asal";
			case 9:
				return "Tanggal Lahir";
			case 10:
				return "Gender";
			case 11:
				return "Status";
			case 12:
				return "Action";
			default:
				return "";
			}
		}
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }

	}


}
