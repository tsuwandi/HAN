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
import module.system.model.GroupScreen;

import javax.swing.JComboBox;

public class GroupAccessConfigPanel extends JPanel {

	private static final long serialVersionUID = -8643623752593867087L;
	private JTable groupAccessTabel;
	private List<Group> groups = new ArrayList<>();
	private List<GroupScreen> groupScreens = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public GroupAccessConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Group Akses");
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
		
		groupAccessTabel = new JTable();
		scrollPane.setViewportView(groupAccessTabel);
		
		JButton btnCancel = new JButton("Tutup");
		btnCancel.setBounds(10, 589, 89, 30);
		add(btnCancel);

		JButton btnSave = new JButton("OK");
		btnSave.setBounds(925, 589, 89, 30);
		add(btnSave);
		
		JLabel groupNameLbl = new JLabel("Nama Group");
		groupNameLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		groupNameLbl.setBounds(10, 45, 100, 30);
		add(groupNameLbl);
		
		JLabel label = new JLabel(":");
		label.setBounds(110, 46, 10, 30);
		add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 45, 100, 30);
		add(comboBox);
	}

	class GroupAccessConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 7503115091439792751L;
		private List<GroupScreen> groupScreens;
		
		public GroupAccessConfigTableModel(List<GroupScreen> groupScreens) {
			this.groupScreens = groupScreens;
		}
		
		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return groupScreens.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			GroupScreen groupScreen = groupScreens.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return groupScreen.getGroupScreenId();
			case 1:
				return groupScreen.getGroupId();
			case 2:
				return groupScreen.getScreenId();
			case 3:
				return groupScreen.getScreenName();
			case 4:
				return groupScreen.getAccess();
			default:
				return "";
			}
		}
		
	}
}
