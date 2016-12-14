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
import module.util.Bridging;
import controller.ServiceFactory;

public class DivisionConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -3127283027621703632L;
	private JTable divisionConfigTable;
	private JTextField searchField;
	private List<Division> divisions = new ArrayList<>();
	private DivisionConfigTableModel divisionConfigTableModel;

	public DivisionConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Divisi");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR DIVISI");
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

		divisionConfigTable = new JTable();
		divisionConfigTable.setFocusable(false);
		divisionConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(divisionConfigTable);
		divisionConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (divisionConfigTable.columnAtPoint(e.getPoint())==3) {
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
				MainPanel.changePanel("module.personalia.ui.CreateDivisionPanel");
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
		int row = divisionConfigTable.getSelectedRow();

		Division division = new Division();
		division.setId(divisionConfigTable.getValueAt(row, 1).toString());
		division.setName(divisionConfigTable.getValueAt(row, 2).toString());

		return division;
	}

	private void getUserData() {
		divisions.clear();
		divisions = ServiceFactory.getPersonaliaBL().getDivisions("");
		divisionConfigTableModel = new DivisionConfigTableModel(divisions);
		divisionConfigTable.setModel(divisionConfigTableModel);
	}

	class DivisionConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<Division> divisions;

		public DivisionConfigTableModel(List<Division> divisions) {
			this.divisions = divisions;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return divisions == null ? 0 : divisions.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Division division = divisions.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return divisions.indexOf(division) + 1;
			case 1:
				return division.getId();
			case 2:
				return division.getName();
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
				return "ID Divisi";
			case 2:
				return "Nama Divisi";
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
		divisions = (List<Division>) objects[0];
		divisionConfigTableModel = new DivisionConfigTableModel(divisions);
		divisionConfigTable.setModel(divisionConfigTableModel);
	}
}