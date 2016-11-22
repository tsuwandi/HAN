package module.system.ui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import module.system.model.Group;
import controller.ServiceFactory;

public class GroupConfigPanel extends JPanel {

	private static final long serialVersionUID = -2664236735117199463L;
	private JTable groupConfigTable;
	private List<Group> groups = new ArrayList<>();
	private GroupConfigTabelModel groupConfigTabelModel;
	private JTextField groupNameTxt;
	private JTextArea groupDescTxt;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GroupConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Group");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(10, 10, 1004, 25);
		add(breadCrumbLbl);

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
				updateData();
			}
		});
		
		JLabel groupNameLbl = new JLabel("Nama Group");
		groupNameLbl.setBounds(10, 45, 100, 30);
		add(groupNameLbl);
		
		JLabel label = new JLabel(":");
		label.setBounds(110, 45, 10, 30);
		add(label);
		
		groupNameTxt = new JTextField();
		groupNameTxt.setBounds(120, 45, 200, 30);
		groupNameTxt.setColumns(10);
		groupNameTxt.setEditable(false);
		groupNameTxt.setEnabled(false);
		add(groupNameTxt);
		
		JLabel lblDeskripsiGroup = new JLabel("Deskripsi Group");
		lblDeskripsiGroup.setBounds(10, 85, 100, 30);
		add(lblDeskripsiGroup);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 86, 10, 30);
		add(label_1);
		
		groupDescTxt = new JTextArea();
		groupDescTxt.setBounds(120, 88, 200, 90);
		groupDescTxt.setEditable(false);
		groupDescTxt.setEnabled(false);
		add(groupDescTxt);
		
		JButton button = new JButton("Buat Baru");
		button.setBounds(724, 100, 90, 30);
		add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(724, 140, 190, 30);
		add(textField);
		
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
	}

	protected Group getSelectedData() {
		int row = groupConfigTable.getSelectedRow();
		
		Group group = new Group();
		group.setGroupId(Integer.parseInt(groupConfigTable.getValueAt(row, 0).toString()));
		group.setGroupName(groupConfigTable.getValueAt(row, 1).toString());
		group.setGroupDesc(groupConfigTable.getValueAt(row, 2).toString());
		
		return group;
	}
	
	protected void updateData() {
		groupNameTxt.setText(getSelectedData().getGroupName());
		groupDescTxt.setText(getSelectedData().getGroupDesc());
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
			return 3;
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
			default:
				return String.class;
			}
		}
	}
}