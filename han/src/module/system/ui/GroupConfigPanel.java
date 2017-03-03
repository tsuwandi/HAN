package module.system.ui;

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
import module.system.model.Group;

public class GroupConfigPanel extends JPanel {

	private static final long serialVersionUID = -2664236735117199463L;
	private JTable groupConfigTable;
	private List<Group> groups = new ArrayList<>();
	private GroupConfigTabelModel groupConfigTabelModel;
	private JTextField searchField;

	/**
	 * Create the panel.
	 */
	public GroupConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Group");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("DAFTAR GROUP");
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

		groupConfigTable = new JTable();
		groupConfigTable.setFocusable(false);
		groupConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(groupConfigTable);
		
		groupConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(groupConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.system.ui.EditGroupPanel", getSelectedData());
				}
			}
		});
		
		JButton newGroupBtn = new JButton("Buat Baru");
		newGroupBtn.setBounds(724, 100, 90, 30);
		add(newGroupBtn);
		newGroupBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.system.ui.CreateGroupPanel");
			}
		});
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(724, 140, 190, 30);
		add(searchField);
		
		JButton button_1 = new JButton("Export");
		button_1.setBounds(824, 100, 90, 30);
		add(button_1);
		
		JButton button_2 = new JButton("<html>Pencarian<br/>Lanjut</html>");
		button_2.setBounds(924, 100, 90, 30);
		add(button_2);
		
		JButton button_3 = new JButton("Pencarian");
		button_3.setBounds(924, 140, 90, 30);
		add(button_3);
		getGroupData();
		
		updateTableSize();
	}

	protected Group getSelectedData() {
		int row = groupConfigTable.getSelectedRow();
		
		Group group = new Group();
		group.setGroupId(Integer.parseInt(groupConfigTable.getValueAt(row, 0).toString()));
		group.setGroupName(groupConfigTable.getValueAt(row, 1).toString());
		group.setGroupDesc(groupConfigTable.getValueAt(row, 2).toString());
		
		return group;
	}

	private void getGroupData() {
		groups.clear();
		groups = ServiceFactory.getSystemBL().getAllGroup();
		groupConfigTabelModel = new GroupConfigTabelModel(groups);
		groupConfigTable.setModel(groupConfigTabelModel);
	}

	class GroupConfigTabelModel extends AbstractTableModel {

		private static final long serialVersionUID = 2384701170397548994L;

		private List<Group> groups;

		public GroupConfigTabelModel(List<Group> groups) {
			this.groups = groups;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return groups.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Group group = groups.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return group.getGroupId();
			case 1:
				return group.getGroupName();
			case 2:
				return group.getGroupDesc();
			case 3:
				return "<html><u>ubah</u></html>";
			default:
				return "";
			}
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Id";
			case 1:
				return "Nama";
			case 2:
				return "Deskripsi";
			case 3:
				return "Aksi";
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
	}
	
	private void updateTableSize() {
		groupConfigTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		groupConfigTable.getTableHeader().setResizingAllowed(false);
		
		/*TableColumn column1 = groupConfigTable.getColumnModel().getColumn(0);
		TableColumn column2 = groupConfigTable.getColumnModel().getColumn(1);
		TableColumn column3 = groupConfigTable.getColumnModel().getColumn(2);
		TableColumn column4 = groupConfigTable.getColumnModel().getColumn(3);
		
		column1.setPreferredWidth(20);
		column1.setMinWidth(15);
		column1.setMinWidth(25);
		
		column2.setPreferredWidth(70);
		column2.setMinWidth(60);
		column2.setMinWidth(80);
		
		column3.setPreferredWidth(200);
		column3.setMinWidth(150);
		column3.setMinWidth(250);
		
		column4.setPreferredWidth(70);
		column4.setMinWidth(60);
		column4.setMinWidth(80);*/
	}
}