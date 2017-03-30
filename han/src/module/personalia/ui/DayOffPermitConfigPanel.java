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
import module.personalia.model.OnLeavePermit;
import module.util.Bridging;
import module.util.DateUtil;

public class DayOffPermitConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -3127283027621703632L;
	private JTable dayOffPermitConfigTable;
	private JTextField searchField;
	private List<OnLeavePermit> onLeavePermits = new ArrayList<>();
	private DivisionConfigTableModel divisionConfigTableModel;

	public DayOffPermitConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Ijin / Cuti");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 160, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PERMOHONAN IJIN/CUTI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 160, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		dayOffPermitConfigTable = new JTable();
		dayOffPermitConfigTable.setFocusable(false);
		dayOffPermitConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(dayOffPermitConfigTable);
		dayOffPermitConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dayOffPermitConfigTable.columnAtPoint(e.getPoint())==3) {
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
				//onLeavePermits = ServiceFactory.getPersonaliaBL().getDivisions(sb.toString());
				divisionConfigTableModel.setOnLeavePermits(onLeavePermits);
				dayOffPermitConfigTable.updateUI();
			}
		});

		getUserData();
	}

	protected Division getSelectedData() {
		int row = dayOffPermitConfigTable.getSelectedRow();

		Division division = new Division();
		division.setId(dayOffPermitConfigTable.getValueAt(row, 1).toString());
		division.setName(dayOffPermitConfigTable.getValueAt(row, 2).toString());

		return division;
	}

	private void getUserData() {
		onLeavePermits.clear();
		//onLeavePermits = ServiceFactory.getPersonaliaBL().getDivisions("");
		divisionConfigTableModel = new DivisionConfigTableModel(onLeavePermits);
		dayOffPermitConfigTable.setModel(divisionConfigTableModel);
	}

	class DivisionConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<OnLeavePermit> onLeavePermits;

		public DivisionConfigTableModel(List<OnLeavePermit> onLeavePermits) {
			this.onLeavePermits = onLeavePermits;
		}
		
		public void setOnLeavePermits(List<OnLeavePermit> onLeavePermits) {
			this.onLeavePermits = onLeavePermits;
		}

		@Override
		public int getColumnCount() {
			return 7;
		}

		@Override
		public int getRowCount() {
			return onLeavePermits == null ? 0 : onLeavePermits.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			OnLeavePermit onLeavePermit = onLeavePermits.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return onLeavePermits.indexOf(onLeavePermit) + 1;
			case 1:
				return onLeavePermit.getEmployee().getEmpCode();
			case 2:
				return onLeavePermit.getEmployee().getName();
			case 3:
				return onLeavePermit.getType();
			case 4:
				return DateUtil.setFormatedDate(onLeavePermit.getDateStart());
			case 5:
				return DateUtil.setFormatedDate(onLeavePermit.getDateEnd());
			case 6:
				return onLeavePermit.getDocumentReference();
			case 7:
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
				return "Jenis Permohonan";
			case 4:
				return "Tanggal Mulai";
			case 5:
				return "Tanggal Selesai";
			case 6:
				return "Referensi Dokumen";
			case 7:
				return "Action";
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		onLeavePermits = (List<OnLeavePermit>) objects[0];
		divisionConfigTableModel = new DivisionConfigTableModel(onLeavePermits);
		dayOffPermitConfigTable.setModel(divisionConfigTableModel);
	}
}