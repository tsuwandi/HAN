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
import module.personalia.model.NonRoutineAllowanceMasterType;
import module.util.Bridging;

public class NonRoutineAllowanceMasterTypeConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -9089874016146253402L;
	private JTable nonRoutineAllowanceMasterTypeConfigTable;
	private JTextField searchField;
	private List<NonRoutineAllowanceMasterType> nonRoutineAllowanceMasterTypes = new ArrayList<>();
	private NonRoutineAllowanceMasterTypeConfigTableModel nonRoutineAllowanceMasterTypeConfigTableModel;

	public NonRoutineAllowanceMasterTypeConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Payroll > Master Jenis Tunjangan Non Rutin");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 350, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR MASTER JENIS TUNJANGAN NON RUTIN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 350, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		nonRoutineAllowanceMasterTypeConfigTable = new JTable();
		nonRoutineAllowanceMasterTypeConfigTable.setFocusable(false);
		nonRoutineAllowanceMasterTypeConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(nonRoutineAllowanceMasterTypeConfigTable);
		nonRoutineAllowanceMasterTypeConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nonRoutineAllowanceMasterTypeConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewNonRoutineAllowanceMasterTypePanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreateNonRoutineAllowanceMasterTypePanel");
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
				MainPanel.changePanel("module.personalia.ui.SearchNonRoutineAllowancePanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getUserData();
	}

	protected NonRoutineAllowanceMasterType getSelectedData() {
		int row = nonRoutineAllowanceMasterTypeConfigTable.getSelectedRow();
		return nonRoutineAllowanceMasterTypes.get(row);
	}

	private void getUserData() {
		nonRoutineAllowanceMasterTypes.clear();
		nonRoutineAllowanceMasterTypes = ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceMasterTypes("");
		nonRoutineAllowanceMasterTypeConfigTableModel = new NonRoutineAllowanceMasterTypeConfigTableModel(nonRoutineAllowanceMasterTypes);
		nonRoutineAllowanceMasterTypeConfigTable.setModel(nonRoutineAllowanceMasterTypeConfigTableModel);
	}

	class NonRoutineAllowanceMasterTypeConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<NonRoutineAllowanceMasterType> nonRoutineAllowances;

		public NonRoutineAllowanceMasterTypeConfigTableModel(List<NonRoutineAllowanceMasterType> nonRoutineAllowances) {
			this.nonRoutineAllowances = nonRoutineAllowances;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return nonRoutineAllowances == null ? 0 : nonRoutineAllowances.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			NonRoutineAllowanceMasterType nonRoutineAllowanceMasterType = nonRoutineAllowances.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return nonRoutineAllowances.indexOf(nonRoutineAllowanceMasterType) + 1;
			case 1:
				return nonRoutineAllowanceMasterType.getTnrType();
			case 2:
				return nonRoutineAllowanceMasterType.getReferenceDocument();
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
				return "Jenis Tunjangan Non Rutin";
			case 2:
				return "Dokumen Referensi";
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
		nonRoutineAllowanceMasterTypes = (List<NonRoutineAllowanceMasterType>) objects[0];
		nonRoutineAllowanceMasterTypeConfigTableModel = new NonRoutineAllowanceMasterTypeConfigTableModel(nonRoutineAllowanceMasterTypes);
		nonRoutineAllowanceMasterTypeConfigTable.setModel(nonRoutineAllowanceMasterTypeConfigTableModel);
	}
}