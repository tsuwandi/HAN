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
import module.personalia.model.OverTime;
import module.util.Bridging;

public class OverTimeConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -2723703481779319127L;

	private JTable overTimeConfigTable;
	private JTextField searchField;
	private List<OverTime> overTimes = new ArrayList<>();
	private OverTimeConfigTableModel overTimeConfigTableModel;
	private PopUpSearchOverTimePanel popUpSearchOverTimePanel;
	
	public OverTimeConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Lembur");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR LEMBUR");
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

		overTimeConfigTable = new JTable();
		overTimeConfigTable.setFocusable(false);
		overTimeConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(overTimeConfigTable);
		overTimeConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (overTimeConfigTable.columnAtPoint(e.getPoint())==3) {
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
				MainPanel.changePanel("module.personalia.ui.CreateOverTimePanel");
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
				showExplicitSearch();
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
				//overTimes = ServiceFactory.getPersonaliaBL().getDivisions(sb.toString());
				overTimeConfigTableModel.setDivisions(overTimes);
				overTimeConfigTable.updateUI();
			}
		});

		getUserData();
	}
	
	protected void showExplicitSearch() {
		popUpSearchOverTimePanel = new PopUpSearchOverTimePanel(this);
		popUpSearchOverTimePanel.setLocationRelativeTo(null);
		popUpSearchOverTimePanel.setTitle("Pencarian Lanjut Lembur");
		popUpSearchOverTimePanel.setVisible(true);
	}

	protected Division getSelectedData() {
		int row = overTimeConfigTable.getSelectedRow();

		Division division = new Division();
		division.setId(overTimeConfigTable.getValueAt(row, 1).toString());
		division.setName(overTimeConfigTable.getValueAt(row, 2).toString());

		return division;
	}

	private void getUserData() {
		overTimes.clear();
		//divisions = ServiceFactory.getPersonaliaBL().getDivisions("");
		overTimeConfigTableModel = new OverTimeConfigTableModel(overTimes);
		overTimeConfigTable.setModel(overTimeConfigTableModel);
	}

	class OverTimeConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<OverTime> overTimes;

		public OverTimeConfigTableModel(List<OverTime> overTimes) {
			this.overTimes = overTimes;
		}

		public void setDivisions(List<OverTime> overTimes) {
			this.overTimes = overTimes;
		}

		@Override
		public int getColumnCount() {
			return 10;
		}

		@Override
		public int getRowCount() {
			return overTimes == null ? 0 : overTimes.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			OverTime division = overTimes.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return overTimes.indexOf(division) + 1;
			case 1:
				return division.getEmployeeCode();
			case 2:
				return division.getEmployeeName();
			case 3:
				return division.getMsPosition().getName();
			case 4:
				return division.getDepartment().getName();
			case 5:
				return division.getOverTimeDate();
			case 6:
				return division.getStartTime();
			case 7:
				return division.getEndTime();
			case 8:
				return division.getDocumentRef();
			case 9:
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
				return "Tanggal Lembur";
			case 7:
				return "Jam Mulai";
			case 8:
				return "Jam Selesai";
			case 9:
				return "Action";
			default:
				return "";
			}
		}
	}
	
	@Override
	public void invokeObjects(Object... objects) {
		overTimes = (List<OverTime>) objects[0];
		overTimeConfigTableModel = new OverTimeConfigTableModel(overTimes);
		overTimeConfigTable.setModel(overTimeConfigTableModel);
	}

}
