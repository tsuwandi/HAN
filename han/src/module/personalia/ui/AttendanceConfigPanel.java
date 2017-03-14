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
import module.personalia.model.Attendance;
import module.util.Bridging;

public class AttendanceConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -3127283027621703632L;
	private JTable attendanceConfigTable;
	private JTextField searchField;
	private List<Attendance> attendances = new ArrayList<>();
	private AttendanceConfigTableModel attendanceConfigTableModel;
	private PopUpSearchAttendancePanel popUpSearchAttendancePanel = new PopUpSearchAttendancePanel(this);

	public AttendanceConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Presensi");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 190, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR PRESENSI");
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

		attendanceConfigTable = new JTable();
		attendanceConfigTable.setFocusable(false);
		attendanceConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(attendanceConfigTable);
		attendanceConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (attendanceConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewAttendancePanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreateManualAttendancePanel");
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
				attendanceConfigTable.updateUI();
			}
		});

		getUserData();
	}

	protected Attendance getSelectedData() {
		return attendances.get(attendanceConfigTable.getSelectedRow());
	}

	private void getUserData() {
		attendances.clear();
		//attendances = ServiceFactory.getPersonaliaBL().getA
		attendanceConfigTableModel = new AttendanceConfigTableModel(attendances);
		attendanceConfigTable.setModel(attendanceConfigTableModel);
	}

	class AttendanceConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<Attendance> attendances;

		public AttendanceConfigTableModel(List<Attendance> attendances) {
			this.attendances = attendances;
		}

		public void setDivisions(List<Attendance> divisions) {
			this.attendances = divisions;
		}

		@Override
		public int getColumnCount() {
			return 8;
		}

		@Override
		public int getRowCount() {
			return attendances == null ? 0 : attendances.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Attendance attendance = attendances.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return attendances.indexOf(attendance) + 1;
			case 1:
				return attendance.getEmployee().getEmpCode();
			case 2:
				return attendance.getEmployeeName();
			case 3:
				return attendance.getEmployee().getMsPosition().getName();
			case 4:
				return attendance.getEmployee().getDepartment().getName();
			case 5:
				return attendance.getEmployee().getDivision().getName();
			case 6:
				return attendance.getAttendanceDate();
			case 7:
				return attendance.getAttendanceTime();
			case 8:
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
				return "NIK Karyawan";
			case 2:
				return "Nama Karyawan";
			case 3:
				return "Jabatan";
			case 4:
				return "Departemen";
			case 5:
				return "Divisi";
			case 6:
				return "Tanggal Presensi";
			case 7:
				return "Jam Presensi";
			case 8:
				return "Action";
				/*case 8:
				return "Jam Keluar";
			case 9:
				return "Sumber data";*/
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		attendances = (List<Attendance>) objects[0];
		attendanceConfigTableModel = new AttendanceConfigTableModel(attendances);
		attendanceConfigTable.setModel(attendanceConfigTableModel);
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}
}