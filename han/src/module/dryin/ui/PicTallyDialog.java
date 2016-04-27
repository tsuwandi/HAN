package module.dryin.ui;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import module.dryin.model.PicTally;
import module.dryin.ui.DryInCreatePanel.PicTallyTableModel;
import module.employee.model.Employee;

public class PicTallyDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JScrollPane scrollPaneEmployee;
	JTable tblEmployee;

	private EmployeeTableModel employeeTableModel;
	
	List<Employee> listOfEmployee;

	public PicTallyDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		
		listOfEmployee = new ArrayList<Employee>();
		//listOfEmployee = ServiceFactory.
		
//		employeeTableModel = new EmployeeTableModel(listOfEmployee);
//		tblEmployee = new JTable(employeeTableModel);
//		tblEmployee.setBorder(new EmptyBorder(5, 5, 5, 5));
//		scrollPaneEmployee.setViewportView(tblEmployee);
	}
	
	/**
	 * Class as TableModel for Supp Address table
	 * 
	 * @author TSI
	 *
	 */
	class EmployeeTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<Employee> listOfEmployee;

		public EmployeeTableModel(List<Employee> listOfEmployee) {
			this.listOfEmployee = listOfEmployee;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfEmployee.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Employee p = listOfEmployee.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getEmployeeCode();
			case 2:
				return p.getEmployeeName();
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			default:
				return String.class;
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
				return "";
			case 1:
				return "Nik";
			case 2:
				return "Nama";
			default:
				return "";
			}
		}
	}
}
