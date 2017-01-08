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
import module.personalia.model.NonRoutineAllowanceMaster;
import module.personalia.model.NonRoutineAllowanceMasterType;
import module.util.Bridging;

public class NonRoutineAllowanceMasterConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -4588733710608236153L;
	private JTable nonRoutineAllowanceMasterConfigTable;
	private JTextField searchField;
	private List<NonRoutineAllowanceMaster> nonRoutineAllowanceMasters = new ArrayList<>();
	private NonRoutineAllowanceMasterConfigTableModel nonRoutineAllowanceMasterConfigTableModel;

	public NonRoutineAllowanceMasterConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Master Tunjangan Non Rutin");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 350, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR MASTER TUNJANGAN NON RUTIN");
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

		nonRoutineAllowanceMasterConfigTable = new JTable();
		nonRoutineAllowanceMasterConfigTable.setFocusable(false);
		nonRoutineAllowanceMasterConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(nonRoutineAllowanceMasterConfigTable);
		nonRoutineAllowanceMasterConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nonRoutineAllowanceMasterConfigTable.columnAtPoint(e.getPoint())==3) {
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
				MainPanel.changePanel("module.personalia.ui.CreateNonRoutineAllowanceMasterPanel");
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
		int row = nonRoutineAllowanceMasterConfigTable.getSelectedRow();

		NonRoutineAllowanceMasterType nonRoutineAllowance = new NonRoutineAllowanceMasterType();

		return nonRoutineAllowance;
	}

	private void getUserData() {
		nonRoutineAllowanceMasters.clear();
		//nonRoutineAllowances = ServiceFactory.getPersonaliaBL().getDivisions("");
		nonRoutineAllowanceMasterConfigTableModel = new NonRoutineAllowanceMasterConfigTableModel(nonRoutineAllowanceMasters);
		nonRoutineAllowanceMasterConfigTable.setModel(nonRoutineAllowanceMasterConfigTableModel);
	}

	class NonRoutineAllowanceMasterConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -6612060578598517739L;
		private List<NonRoutineAllowanceMaster> nonRoutineAllowanceMasters;

		public NonRoutineAllowanceMasterConfigTableModel(List<NonRoutineAllowanceMaster> nonRoutineAllowanceMasters) {
			this.nonRoutineAllowanceMasters = nonRoutineAllowanceMasters;
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return nonRoutineAllowanceMasters == null ? 0 : nonRoutineAllowanceMasters.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			NonRoutineAllowanceMaster nonRoutineAllowanceMaster = nonRoutineAllowanceMasters.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return nonRoutineAllowanceMasters.indexOf(nonRoutineAllowanceMaster) + 1;
			case 1:
				return nonRoutineAllowanceMaster.getTnr();
			case 2:
				return nonRoutineAllowanceMaster.getNonRoutineAllowanceMasterType().getTnrType();
			case 3:
				return nonRoutineAllowanceMaster.getReferenceDocument();
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
				return "Deskripsi Tunjangan Non Rutin";
			case 2:
				return "Jenis Tunjangan Non Rutin";
			case 3:
				return "Dokumen Referensi";
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
		nonRoutineAllowanceMasters = (List<NonRoutineAllowanceMaster>) objects[0];
		nonRoutineAllowanceMasterConfigTableModel = new NonRoutineAllowanceMasterConfigTableModel(nonRoutineAllowanceMasters);
		nonRoutineAllowanceMasterConfigTable.setModel(nonRoutineAllowanceMasterConfigTableModel);
	}
}