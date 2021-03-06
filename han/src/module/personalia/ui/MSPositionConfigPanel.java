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
import module.personalia.model.MSPosition;

public class MSPositionConfigPanel extends JPanel{

	private static final long serialVersionUID = 4318234045211155813L;
	private JTable msPositionConfigTable;
	private JTextField searchField;
	private List<MSPosition> msPositions = new ArrayList<>();
	private EmployeeTypeConfigTableModel msPositionConfigTableModel;

	public MSPositionConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Jabatan");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR JABATAN");
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

		msPositionConfigTable = new JTable();
		msPositionConfigTable.setFocusable(false);
		msPositionConfigTable.setAutoCreateRowSorter(true);
		msPositionConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (msPositionConfigTable.columnAtPoint(e.getPoint())==5) {
					MainPanel.changePanel("module.personalia.ui.ViewMSPositionPanel", getSelectedData());
				}
			}
		});
		
		scrollPane.setViewportView(msPositionConfigTable);

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
				MainPanel.changePanel("module.personalia.ui.CreateMSPositionPanel");
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
				MainPanel.changePanel("module.personalia.ui.SearchMSPositionPanel");
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
				sb.append("%' ");
				sb.append(" or name like '%");
				sb.append(var);
				sb.append("%' ");
				sb.append(" or division_id like '%");
				sb.append(var);
				sb.append("%' ");
				sb.append(" or department_id like '%");
				sb.append(var);
				sb.append("%' ");
				
				msPositions = ServiceFactory.getPersonaliaBL().getMSPositions(sb.toString());
				msPositionConfigTableModel.setMsPositions(msPositions);
				msPositionConfigTable.updateUI();
			}
		});

		getData();
	}

	protected MSPosition getSelectedData() {
		int row = msPositionConfigTable.getSelectedRow();

		MSPosition msPosition = new MSPosition();
		msPosition.setId(msPositionConfigTable.getValueAt(row, 1).toString());
		for (MSPosition position : msPositions) {
			if(position.getId().equals(msPosition.getId())){
				msPosition.setName(position.getName());
				msPosition.setDepartementId(position.getDepartementId());
				msPosition.setDepartementName(position.getDepartementName());
				msPosition.setDepartment(position.getDepartment());
				msPosition.setDivisionId(position.getDivisionId());
				msPosition.setDivisionName(position.getDivisionName());
				msPosition.setDivision(position.getDivision());
				msPosition.setSalaryMin(position.getSalaryMin());
				msPosition.setSalaryMax(position.getSalaryMax());
			}
		}

		return msPosition;
	}

	private void getData() {
		msPositions.clear();
		msPositions = ServiceFactory.getPersonaliaBL().getMSPositions("");
		msPositionConfigTableModel = new EmployeeTypeConfigTableModel(msPositions);
		msPositionConfigTable.setModel(msPositionConfigTableModel);
	}

	class EmployeeTypeConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<MSPosition> msPositions;

		public EmployeeTypeConfigTableModel(List<MSPosition> msPositions) {
			this.msPositions = msPositions;
		}

		public void setMsPositions(List<MSPosition> msPositions) {
			this.msPositions = msPositions;
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public int getRowCount() {
			return msPositions == null ? 0 : msPositions.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			MSPosition msPosition = msPositions.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return msPositions.indexOf(msPosition) + 1;
			case 1:
				return msPosition.getId();
			case 2:
				return msPosition.getName();
			case 3:
				return msPosition.getDepartementName();
			case 4:
				return msPosition.getDivisionName();
			case 5:
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
				return "ID Jabatan";
			case 2:
				return "Nama Jabatan";
			case 3:
				return "Departemen";
			case 4:
				return "Divisi";
			case 5:
				return "Action";
			default:
				return "";
			}
		}
	}
}