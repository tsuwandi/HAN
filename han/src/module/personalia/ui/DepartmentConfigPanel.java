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
import module.personalia.model.Department;
import module.util.Bridging;

public class DepartmentConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -523978922179504555L;
	private JTable departmentConfigTable;
	private JTextField searchField;
	private List<Department> departements = new ArrayList<>();
	private DepartementConfigTableModel departementConfigTableModel;

	public DepartmentConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Departemen");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 200, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR DEPARTEMEN");
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

		departmentConfigTable = new JTable();
		departmentConfigTable.setFocusable(false);
		departmentConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(departmentConfigTable);
		departmentConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (departmentConfigTable.columnAtPoint(e.getPoint())==4) {
					MainPanel.changePanel("module.personalia.ui.ViewDepartmentPanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreateDepartmentPanel");
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
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.personalia.ui.SearchDivisionPanel");
			}
		});

		JButton searchBtn = new JButton("Pencarian");
		searchBtn.setBounds(924, 140, 90, 30);
		add(searchBtn);
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String var = searchField.getText();
				StringBuffer sb = new StringBuffer();
				sb.append(" and id like '%");
				sb.append(var);
				sb.append("%'");
				sb.append(" or name like '%");
				sb.append(var);
				sb.append("%'");
				sb.append(" or division_id like '%");
				sb.append(var);
				sb.append("%'");
				departements = ServiceFactory.getPersonaliaBL().getDepartments(sb.toString());
				departementConfigTableModel.setDepartements(departements);
				departmentConfigTable.updateUI();
			}
		});

		getUserData();
	}
	
	protected Department getSelectedData() {
		int row = departmentConfigTable.getSelectedRow();

		Department departement = new Department();
		departement.setId(departmentConfigTable.getValueAt(row, 1).toString());
		departement.setName(departmentConfigTable.getValueAt(row, 2).toString());
		departement.setDivisionId(departmentConfigTable.getValueAt(row, 3).toString());

		return departement;
	}

	private void getUserData() {
		departements.clear();
		departements = ServiceFactory.getPersonaliaBL().getDepartments("");
		departementConfigTableModel = new DepartementConfigTableModel(departements);
		departmentConfigTable.setModel(departementConfigTableModel);
	}

	class DepartementConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<Department> departements;

		public DepartementConfigTableModel(List<Department> departements) {
			this.departements = departements;
		}

		public void setDepartements(List<Department> departements) {
			this.departements = departements;
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return departements == null ? 0 : departements.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Department departement = departements.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return departements.indexOf(departement) + 1;
			case 1:
				return departement.getId();
			case 2:
				return departement.getName();
			case 3:
				return departement.getDivisionId();
			case 4:
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
				return "ID Departement";
			case 2:
				return "Nama Departement";
			case 3:
				return "Divisi";
			case 4:
				return "Action";
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		departements = (List<Department>) objects[0];
		
		departementConfigTableModel = new DepartementConfigTableModel(departements);
		departmentConfigTable.setModel(departementConfigTableModel);
	}
}
