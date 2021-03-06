package module.personalia.ui;

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

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.personalia.model.EmpPosition;
import module.personalia.model.Employee;
import module.util.Bridging;

public class EmployeeConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 3894105577889074273L;
	private JTable employeeConfigTable;
	private JTextField searchField;
	private List<Employee> employees = new ArrayList<>();
	private EmployeeConfigTableModel employeeConfigTableModel;
	private List<EmpPosition> empPositions = new ArrayList<>(); 
	private EmpPosition empPosition;

	public EmployeeConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Karyawan");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 150, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		employeeConfigTable = new JTable();
		employeeConfigTable.setFocusable(false);
		employeeConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(employeeConfigTable);
		employeeConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (employeeConfigTable.columnAtPoint(e.getPoint())==6) {
					MainPanel.changePanel("module.personalia.ui.ViewEmployeePanel", getSelectedData());
				}
			}
		});

		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(724, 140, 190, 30);
		add(searchField);

		JButton newBtn = new JButton("Buat Baru");
		newBtn.setBounds(724, 100, 90, 30);
		add(newBtn);
		newBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.personalia.ui.CreateEmployeePanel");
			}
		});

		JButton exportBtn = new JButton("Export");
		exportBtn.setBounds(824, 100, 90, 30);
		add(exportBtn);

		JButton explicitSearchBtn = new JButton("<html>Pencarian<br/>Lanjut</html>");
		explicitSearchBtn.setBounds(924, 100, 90, 30);
		add(explicitSearchBtn);
		explicitSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.personalia.ui.SearchEmployeePanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getData();
	}

	protected Employee getSelectedData() {
		int row = employeeConfigTable.getSelectedRow();
		return employees.get(row);
	}

	private void getData() {
		employees.clear();
		employees = ServiceFactory.getPersonaliaBL().getEmployees("");
		empPositions = ServiceFactory.getPersonaliaBL().getEmpPositions("");
		for (Employee employee : employees) {
			for (EmpPosition empPosition : empPositions) {
				if(employee.getEmpCode().equals(empPosition.getEmployeeId())){
					employee.getEmpPositions().add(empPosition);
				}
			}
		}
		employeeConfigTableModel = new EmployeeConfigTableModel(employees);
		employeeConfigTable.setModel(employeeConfigTableModel);
	}

	class EmployeeConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<Employee> employees;

		public EmployeeConfigTableModel(List<Employee> employees) {
			this.employees = employees;
		}

		@Override
		public int getColumnCount() {
			return 7;
		}

		@Override
		public int getRowCount() {
			return employees == null ? 0 : employees.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Employee employee = employees.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return employees.indexOf(employee) + 1;
			case 1:
				return employee.getEmpCode();
			case 2:
				return employee.getName();
			case 3:
				return employee.getHometown();
			case 4:
				return employee.getBirthDate();
			case 5:
				return employee.getGenderId();
			case 6:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			case 8:
				return String.class;
			case 9:
				return String.class;
			case 10:
				return String.class;
			case 11:
				return String.class;
			case 12:
				return String.class;
			default:
				return String.class;
			}
		}

		@Override
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
				return "Department";
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		employees = (List<Employee>) objects[0];
		employeeConfigTableModel = new EmployeeConfigTableModel(employees);
		employeeConfigTable.setModel(employeeConfigTableModel);
	}
}