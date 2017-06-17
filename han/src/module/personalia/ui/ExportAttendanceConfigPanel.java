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
import module.personalia.model.Attendance;
import module.personalia.model.ImportFingerprint;
import module.util.Bridging;
import module.util.DateUtil;

public class ExportAttendanceConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -3127283027621703632L;
	private JTable exportAttendanceConfigTable;
	private JTextField searchField;
	private List<ImportFingerprint> importFingerprints = new ArrayList<>();
	private ExportAttendanceConfigTableModel exportAttendanceConfigTableModel;
	private PopUpSearchAttendancePanel popUpSearchAttendancePanel = new PopUpSearchAttendancePanel(this);

	public ExportAttendanceConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Presensi");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 190, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("EXPORT DATA PRESENSI");
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

		exportAttendanceConfigTable = new JTable();
		exportAttendanceConfigTable.setFocusable(false);
		exportAttendanceConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(exportAttendanceConfigTable);
		exportAttendanceConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (exportAttendanceConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewManualAttendancePanel", getSelectedData());
				}
			}
		});

		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(724, 140, 190, 30);
		add(searchField);

		JButton exportBtn = new JButton("Import");
		exportBtn.setBounds(824, 100, 90, 30);
		add(exportBtn);

		JButton explicitSearchBtn = new JButton("<html>Pencarian<br/>Lanjut</html>");
		explicitSearchBtn.setBounds(924, 100, 90, 30);
		add(explicitSearchBtn);
		explicitSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				popUpSearchAttendancePanel.setVisible(true);
			}
		});

		JButton searchBtn = new JButton("Pencarian");
		searchBtn.setBounds(924, 140, 90, 30);
		add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String var  = searchField.getText();
				StringBuffer sb = new StringBuffer();
				sb.append(" and id like '%");
				sb.append(var);
				sb.append("%' ");
				sb.append(" or name like '%");
				sb.append(var);
				sb.append("%' ");
				//attendances = ServiceFactory.getPersonaliaBL().getDivisions(sb.toString());
				//attendanceConfigTableModel.setDivisions(attendances);
				exportAttendanceConfigTable.updateUI();
			}
		});

		getUserData();
	}

	protected ImportFingerprint getSelectedData() {
		return importFingerprints.get(exportAttendanceConfigTable.getSelectedRow());
	}

	private void getUserData() {
		importFingerprints.clear();
		//importFingerprints = ServiceFactory.getPersonaliaBL().getAttendances("");
		exportAttendanceConfigTableModel = new ExportAttendanceConfigTableModel(importFingerprints);
		exportAttendanceConfigTable.setModel(exportAttendanceConfigTableModel);
	}

	class ExportAttendanceConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<ImportFingerprint> importFingerprints;

		public ExportAttendanceConfigTableModel(List<ImportFingerprint> importFingerprints) {
			this.importFingerprints = importFingerprints;
		}

		public void setDivisions(List<ImportFingerprint> importFingerprints) {
			this.importFingerprints = importFingerprints;
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return importFingerprints == null ? 0 : importFingerprints.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ImportFingerprint attendance = importFingerprints.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return importFingerprints.indexOf(attendance) + 1;
			case 1:
				return DateUtil.setFormatedDate(attendance.getDate());
		
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
				return "Tanggal Abensi";
			case 2:
				return "Nama File";
			case 3:
				return "Status Upload";
			case 4:
				return "Status Gajian";
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		//importFingerprints = (List<Attendance>) objects[0];
		exportAttendanceConfigTableModel = new ExportAttendanceConfigTableModel(importFingerprints);
		exportAttendanceConfigTable.setModel(exportAttendanceConfigTableModel);
	}

	public List<ImportFingerprint> getImportFingerprints() {
		return importFingerprints;
	}

	public void setImportFingerprints(List<ImportFingerprint> importFingerprints) {
		this.importFingerprints = importFingerprints;
	}
}