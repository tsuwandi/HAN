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

import main.panel.MainPanel;
import module.personalia.model.EmployeeType;
import module.util.Bridging;
import controller.ServiceFactory;

public class EmployeeTypeConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 5857002598291577322L;
	private JTable employeeTypeConfigTable;
	private JTextField searchField;
	private List<EmployeeType> employeeTypes = new ArrayList<>();
	private EmployeeTypeConfigTableModel employeeTypeConfigTableModel;

	public EmployeeTypeConfigPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Tipe Karyawan");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 200, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR TIPE KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 200, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		employeeTypeConfigTable = new JTable();
		employeeTypeConfigTable.setFocusable(false);
		employeeTypeConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(employeeTypeConfigTable);
		employeeTypeConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (employeeTypeConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewEmployeeTypePanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreateEmployeeTypePanel");
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
				MainPanel.changePanel("module.personalia.ui.SearchEmployeeTypePanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getData();
	}
	
	protected EmployeeType getSelectedData() {
		int row = employeeTypeConfigTable.getSelectedRow();

		EmployeeType employeeType = new EmployeeType();
		employeeType.setId(employeeTypeConfigTable.getValueAt(row, 1).toString());
		employeeType.setName(employeeTypeConfigTable.getValueAt(row, 2).toString());

		return employeeType;
	}

	private void getData() {
		employeeTypes.clear();
		employeeTypes = ServiceFactory.getPersonaliaBL().getEmployeeTypes("");
		employeeTypeConfigTableModel = new EmployeeTypeConfigTableModel(employeeTypes);
		employeeTypeConfigTable.setModel(employeeTypeConfigTableModel);
	}

	class EmployeeTypeConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<EmployeeType> employeeTypes;

		public EmployeeTypeConfigTableModel(List<EmployeeType> employeeTypes) {
			this.employeeTypes = employeeTypes;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return employeeTypes == null ? 0 : employeeTypes.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			EmployeeType employeeType = employeeTypes.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return employeeTypes.indexOf(employeeType) + 1;
			case 1:
				return employeeType.getId();
			case 2:
				return employeeType.getName();
			case 3:
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
				return "ID Jabatan";
			case 2:
				return "Nama Jabatan";
			case 3:
				return "Action";
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		employeeTypes = (List<EmployeeType>) objects[0];
		employeeTypeConfigTableModel = new EmployeeTypeConfigTableModel(employeeTypes);
		employeeTypeConfigTable.setModel(employeeTypeConfigTableModel);
	}
}