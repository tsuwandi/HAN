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
	private MSPositionConfigTableModel msPositionConfigTableModel;
	
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
		scrollPane.setViewportView(msPositionConfigTable);
		msPositionConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (msPositionConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewMSPositionPanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreateMSPositionPanel");
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

		getData();
	}
	
	protected MSPosition getSelectedData() {
		int row = msPositionConfigTable.getSelectedRow();

		MSPosition msPosition = new MSPosition();
		msPosition.setId(msPositionConfigTable.getValueAt(row, 1).toString());
		msPosition.setName(msPositionConfigTable.getValueAt(row, 2).toString());
		msPosition.setDepartementName(msPositionConfigTable.getValueAt(row, 3).toString());
		msPosition.setDivisionName(msPositionConfigTable.getValueAt(row, 4).toString());

		return msPosition;
	}

	private void getData() {
		msPositions.clear();
		msPositions = ServiceFactory.getPersonaliaBL().getMSPositions("");
		msPositionConfigTableModel = new MSPositionConfigTableModel(msPositions);
		msPositionConfigTable.setModel(msPositionConfigTableModel);
	}

	class MSPositionConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<MSPosition> msPositions;

		public MSPositionConfigTableModel(List<MSPosition> msPositions) {
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
