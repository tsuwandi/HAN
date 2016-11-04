package module.system.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import controller.ServiceFactory;
import module.system.model.Group;

public class GroupConfigPanel extends JPanel {

	private static final long serialVersionUID = -2664236735117199463L;
	private JTable groupConfigTable;
	private List<Group> groups = new ArrayList<>();
	private GroupConfigTabelModel groupConfigTabelModel;
	private JTextField groupNameTxt;
	private JTextField searchTxt;
	private JTextArea groupDescTxt;

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
				getSelectedData();
			}
			
		});

		JButton btnCancel = new JButton("Tutup");
		btnCancel.setBounds(10, 589, 89, 30);
		add(btnCancel);

		JButton btnSave = new JButton("OK");
		btnSave.setBounds(925, 589, 89, 30);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnSave);
		
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
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(694, 174, 75, 30);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		add(saveBtn);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				groupNameTxt.setEditable(false);
				groupNameTxt.setEnabled(false);
				groupDescTxt.setEditable(false);
				groupDescTxt.setEnabled(false);
			}
		});
		
		searchTxt = new JTextField();
		searchTxt.setBounds(779, 174, 150, 30);
		add(searchTxt);
		searchTxt.setColumns(10);
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(939, 174, 75, 30);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		add(searchBtn);
		
		JButton editBtn = new JButton("Edit");
		editBtn.setBounds(609, 174, 75, 30);
		add(editBtn);
		
		JButton deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(524, 174, 75, 30);
		add(deleteBtn);
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				groupNameTxt.setEditable(true);
				groupNameTxt.setEnabled(true);
				groupDescTxt.setEditable(true);
				groupDescTxt.setEnabled(true);				
			}
		});
		
		getGroupData();
	}

	protected Group getSelectedData() {
		int row = groupConfigTable.getSelectedRow();
		int column = groupConfigTable.getSelectedColumn();
		
		Group group = new Group();
		group.setGroupId(Integer.parseInt(groupConfigTable.getValueAt(row, 0).toString()));
		group.setGroupName(groupConfigTable.getValueAt(row, 1).toString());
		group.setGroupDesc(groupConfigTable.getValueAt(row, 2).toString());
		
		return (Group) groupConfigTable.getValueAt(row, column);
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

	protected void search() {
		
	}

	protected void save() {
		Group group = new Group();
		group.setGroupName(groupNameTxt.getText());
		group.setGroupDesc(groupDescTxt.getText());
		System.out.println("save");
		ServiceFactory.getSystemBL().saveGroup(group);
		System.out.println("done");
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