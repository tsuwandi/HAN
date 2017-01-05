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
import module.personalia.model.Division;
import module.personalia.model.SalarySetting;
import module.util.Bridging;

public class SalarySettingConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -3127283027621703632L;
	private JTable salaryConfigTable;
	private JTextField searchField;
	private List<SalarySetting> salaries = new ArrayList<>();
	private SalaryConfigTableModel salaryConfigTableModel;

	public SalarySettingConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > SETTING NOMINAL GAJI");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR NOMINAL GAJI");
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

		salaryConfigTable = new JTable();
		salaryConfigTable.setFocusable(false);
		salaryConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(salaryConfigTable);
		salaryConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (salaryConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewDivisionPanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreateSalarySettingPanel");
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
				MainPanel.changePanel("module.personalia.ui.SearchDivisionPanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getUserData();
	}

	protected Division getSelectedData() {
		int row = salaryConfigTable.getSelectedRow();

		Division division = new Division();
		division.setId(salaryConfigTable.getValueAt(row, 1).toString());
		division.setName(salaryConfigTable.getValueAt(row, 2).toString());

		return division;
	}

	private void getUserData() {
		salaries.clear();
		//salaries = ServiceFactory.getPersonaliaBL().getDivisions("");
		salaryConfigTableModel = new SalaryConfigTableModel(salaries);
		salaryConfigTable.setModel(salaryConfigTableModel);
	}

	class SalaryConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<SalarySetting> salaries;

		public SalaryConfigTableModel(List<SalarySetting> salaries) {
			this.salaries = salaries;
		}

		@Override
		public int getColumnCount() {
			return 11;
		}

		@Override
		public int getRowCount() {
			return salaries == null ? 0 : salaries.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			SalarySetting salary = salaries.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return salaries.indexOf(salary) + 1;
			case 1:
				return salary.getEmployeeCode();
			case 2:
				return salary.getEmployeeName();
			case 3:
				return salary.getEmployeeType();
			case 4:
				return salary.getPosition();
			case 5:
				return salary.getDepartment();
			case 6:
				return salary.getDivision();
			case 7:
				return salary.getSalaryBruto();
			case 8:
				return salary.getTax();
			case 9:
				return salary.getSalaryNett();
			case 10:
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
				return "Gaji Kotor";
			case 8:
				return "Total Potongan";
			case 9:
				return "Gaji Bersih";
			case 10:
				return "Action";
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		salaries = (List<SalarySetting>) objects[0];
		salaryConfigTableModel = new SalaryConfigTableModel(salaries);
		salaryConfigTable.setModel(salaryConfigTableModel);
	}
}