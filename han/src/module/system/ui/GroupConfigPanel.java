package module.system.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import module.system.model.Group;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class GroupConfigPanel extends JPanel {

	private static final long serialVersionUID = -2664236735117199463L;
	private JTable groupConfigTable;
	private List<Group> groups = new ArrayList<>();
	private GroupConfigTabelModel groupConfigTabelModel;
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
		groupConfigTabelModel = new GroupConfigTabelModel(groups);
		groupConfigTable.setModel(groupConfigTabelModel);
		scrollPane.setViewportView(groupConfigTable);

		JButton btnCancel = new JButton("Tutup");
		btnCancel.setBounds(10, 589, 89, 30);
		add(btnCancel);

		JButton btnSave = new JButton("OK");
		btnSave.setBounds(925, 589, 89, 30);
		add(btnSave);
		
		JLabel groupNameLbl = new JLabel("Nama Group");
		groupNameLbl.setBounds(10, 45, 100, 30);
		add(groupNameLbl);
		
		JLabel label = new JLabel(":");
		label.setBounds(110, 45, 10, 30);
		add(label);
		
		textField = new JTextField();
		textField.setBounds(120, 45, 200, 30);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDeskripsiGroup = new JLabel("Deskripsi Group");
		lblDeskripsiGroup.setBounds(10, 85, 100, 30);
		add(lblDeskripsiGroup);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 86, 10, 30);
		add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(120, 88, 200, 90);
		add(textArea);
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
		public Class getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return String.class;
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