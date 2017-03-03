package module.system.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import main.component.ComboBox;
import main.panel.MainPanel;
import module.system.model.Group;
import module.system.model.GroupScreen;

public class GroupAccessConfigPanel extends JPanel {

	private static final long serialVersionUID = -8643623752593867087L;
	private JTable groupAccessTabel;
	private List<Group> groups = new ArrayList<>();
	private List<GroupScreen> groupScreens = new ArrayList<>();
	private GroupAccessConfigTableModel groupAccessConfigTableModel;
	private JTextField searchField;
	private ComboBox<Group> groupCmbox;

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
		groupAccessConfigTableModel = new GroupAccessConfigTableModel(groupScreens);
		groupAccessTabel.setModel(groupAccessConfigTableModel);
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
		
		groupCmbox = new ComboBox<Group>();
		groupCmbox.setBounds(120, 45, 150, 30);
		add(groupCmbox);
		
		JButton newGroupBtn = new JButton("Buat Baru");
		newGroupBtn.setBounds(724, 100, 90, 30);
		add(newGroupBtn);
		newGroupBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.system.ui.CreateGroupAccessPanel");
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
		
		getData();
	}

	private void getData() {
		groups = ServiceFactory.getSystemBL().getAllGroup();
		groupCmbox.setList(groups);
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
				return groupScreen.getId();
			case 1:
				return groupScreen.getGroupId();
			case 2:
				return groupScreen.getScreenId();
			case 3:
				return groupScreen.getScreenName();
			case 4:
				return groupScreen.getRightAccess();
			default:
				return "";
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			
			switch (columnIndex) {
			case 0:
				return "Group Screen Id";
			case 1:
				return "Group Id";
			case 2:
				return "Screen Id";
			case 3:
				return "Screen Name";
			case 4:
				return "Access";
			default:
				return "";
			}
		}
	}
}