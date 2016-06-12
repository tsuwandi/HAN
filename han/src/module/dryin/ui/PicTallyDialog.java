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
import main.component.DialogBox;
import module.dryin.model.PicTally;
import module.employee.model.Employee;
import module.util.DateUtil;

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
	private DryInEditPanel dryInEditPanel;

	public PicTallyDialog(DryInCreatePanel dryInCreatePanel, DryInEditPanel dryInEditPanel) {
		this.dryInCreatePanel = dryInCreatePanel;
		this.dryInEditPanel = dryInEditPanel;

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

//		try {
//			listOfEmployee = new ArrayList<Employee>();
//			listOfEmployee = ServiceFactory.getDryInBL().getAllEmployee();
//			if (dryInCreatePanel != null) {
//				for (PicTally picTally : dryInCreatePanel.getListOfPicTally()) {
//					Integer index = listOfEmployee.indexOf(picTally.getEmployee());
//					listOfEmployee.set(index, picTally.getEmployee());
//				}
//			} else if (dryInEditPanel != null) {
//				for (PicTally picTally : dryInEditPanel.getListOfPicTally()) {
//					picTally.getEmployee().setFlag(true);
//					picTally.getEmployee().setRowNum(picTally.getId());
//					Integer index = listOfEmployee.indexOf(picTally.getEmployee());
//					listOfEmployee.set(index, picTally.getEmployee());
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			DialogBox.showErrorException();
//		}

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
//				int response = DialogBox.showInsertChoice();
//				if (response == JOptionPane.YES_OPTION) {
					doInsert();
//				}
			}
		});
		btnInsert.setBounds(379, 250, 95, 30);
		getContentPane().add(btnInsert);
	}

	private void doInsert() {
//		List<PicTally> listOfPicTally = new ArrayList<PicTally>();
//
//		for (Employee emp : listOfEmployee) {
//			PicTally picTally = new PicTally();
//			if (emp.isFlag()) {
//				picTally.setEmpCode(emp.getEmployeeId());
//				picTally.setEmployee(emp);
//				if (emp.getRowNum() != 0 && dryInEditPanel != null)
//					picTally.setId(emp.getRowNum());
//				listOfPicTally.add(picTally);
//			} else {
//				if (emp.getRowNum() != 0 && dryInEditPanel != null) {
//					picTally.setEmpCode(emp.getEmployeeId());
//					picTally.setEmployee(emp);
//					picTally.setId(emp.getRowNum());
//					dryInEditPanel.listOfDeletedPicTally.add(picTally);
//				}
//			}
//		}

//		if (dryInCreatePanel != null)
//			dryInCreatePanel.setListOfPicTally(listOfPicTally);
//		else if (dryInEditPanel != null)
//			dryInEditPanel.setListOfPicTally(listOfPicTally);
		closeDialog();
	}

	protected void closeDialog() {
		if (dryInCreatePanel != null)
			dryInCreatePanel.refreshTablePicTally();
		else if (dryInEditPanel != null)
			dryInEditPanel.refreshTablePicTally();
		dispose();
	}

	private void doSearchEmployee(String value) {
		try {
			listOfEmployee = new ArrayList<Employee>();
			listOfEmployee = ServiceFactory.getDryInBL().getAllEmployeeBySearch(value);
			refreshTableEmployee();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	public void refreshTableEmployee() {
		try {
			tblEmployee.setModel(new EmployeeTableModel(listOfEmployee));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
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
