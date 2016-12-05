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
import module.personalia.model.Departement;
import module.personalia.model.Division;
import controller.ServiceFactory;

public class DepartementConfigPanel extends JPanel {

	private static final long serialVersionUID = -523978922179504555L;
	private JTable departementConfigTable;
	private JTextField searchField;
	private List<Departement> departements = new ArrayList<>();
	private DepartementConfigTableModel divisionConfigTableModel;

	public DepartementConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Departemen");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
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

		departementConfigTable = new JTable();
		departementConfigTable.setFocusable(false);
		departementConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(departementConfigTable);
		departementConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (departementConfigTable.columnAtPoint(e.getPoint())==3) {
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

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getUserData();
	}
	
	protected Division getSelectedData() {
		int row = departementConfigTable.getSelectedRow();

		Division division = new Division();
		division.setId(departementConfigTable.getValueAt(row, 1).toString());
		division.setName(departementConfigTable.getValueAt(row, 2).toString());

		return division;
	}

	private void getUserData() {
		departements.clear();
		departements = ServiceFactory.getPersonaliaBL().getDepartements("");
		divisionConfigTableModel = new DepartementConfigTableModel(departements);
		departementConfigTable.setModel(divisionConfigTableModel);
	}

	class DepartementConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<Departement> departements;

		public DepartementConfigTableModel(List<Departement> departements) {
			this.departements = departements;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return departements == null ? 0 : departements.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Departement departement = departements.get(rowIndex);

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
}
