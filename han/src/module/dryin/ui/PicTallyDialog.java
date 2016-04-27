package module.dryin.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import module.dryin.model.PicTally;
import module.employee.model.Employee;

public class PicTallyDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JScrollPane scrollPaneEmployee;
	JTable tblEmployee;
	JTextField txtSearch;
	JButton btnSearch;
	JButton btnInsert;
	JLabel lblError;

	private EmployeeTableModel employeeTableModel;

	List<Employee> listOfEmployee;

	private DryInCreatePanel dryInCreatePanel;

	public PicTallyDialog(DryInCreatePanel dryInCreatePanel) {
		this.dryInCreatePanel = dryInCreatePanel;
	
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		getContentPane().setLayout(null);

		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 10, 225, 30);
		getContentPane().add(lblError);

		txtSearch = new JTextField();
		txtSearch.setBounds(215, 10, 150, 30);
		getContentPane().add(txtSearch);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearchEmployee(txtSearch.getText());
			}
		});
		btnSearch.setBounds(379, 10, 95, 30);
		getContentPane().add(btnSearch);

		scrollPaneEmployee = new JScrollPane();
		scrollPaneEmployee.setBounds(10, 55, 464, 190);
		getContentPane().add(scrollPaneEmployee);

		try {
			listOfEmployee = new ArrayList<Employee>();
			listOfEmployee = ServiceFactory.getDryInBL().getAllEmployee();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Data gagal diload.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		employeeTableModel = new EmployeeTableModel(listOfEmployee);
		tblEmployee = new JTable(employeeTableModel);
		tblEmployee.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPaneEmployee.setViewportView(tblEmployee);

		tblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).equals(true))
					listOfEmployee.get(tblEmployee.getSelectedRow()).setFlag(false);
				else
					listOfEmployee.get(tblEmployee.getSelectedRow()).setFlag(true);

				refreshTableEmployee();
			}
		});

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsert();
			}
		});
		btnInsert.setBounds(379, 250, 95, 30);
		getContentPane().add(btnInsert);
	}

	private void doInsert() {
		List<PicTally> listOfPicTally = new ArrayList<PicTally>();

		for (Employee emp : listOfEmployee) {
			if (emp.isFlag()) {
				PicTally picTally = new PicTally();
				picTally.setEmpCode(emp.getEmployeeId());
				picTally.setEmployee(emp);
				listOfPicTally.add(picTally);
			}
		}

		dryInCreatePanel.setListOfPicTally(listOfPicTally);
		closeDialog();
	}

	protected void closeDialog() {
		dryInCreatePanel.refreshTablePicTally();

		dispose();
	}

	private void doSearchEmployee(String value) {
		try {
			listOfEmployee = new ArrayList<Employee>();
			listOfEmployee = ServiceFactory.getDryInBL().getAllEmployeeBySearch(value);
			refreshTableEmployee();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Data gagal diload.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void refreshTableEmployee() {
		try {
			tblEmployee.setModel(new EmployeeTableModel(listOfEmployee));
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
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
				return p.getEmployeeId();
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
