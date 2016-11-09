package module.system.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import module.system.model.Group;
import module.system.model.User;

public class UserConfigPanel extends JPanel {

	private static final long serialVersionUID = 4653177447116577211L;
	private JTable userConfigTabel;
	private List<User> users = new ArrayList<>();
	private List<Group> groups = new ArrayList<>();
	private UserConfigTabelModel userConfigTabelModel;
	private JTextField nameTxt;
	private JPasswordField passwordTxt;
	private JTextField textField;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private Integer groupId;

	@SuppressWarnings("rawtypes")
	public UserConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Pengguna");
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

		userConfigTabel = new JTable();
		userConfigTabel.setFocusable(false);
		userConfigTabel.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(userConfigTabel);

		JButton btnCancel = new JButton("Tutup");
		btnCancel.setBounds(10, 589, 89, 30);
		add(btnCancel);

		JButton btnSave = new JButton("OK");
		btnSave.setBounds(925, 589, 89, 30);
		add(btnSave);
		
		JLabel nameLbl = new JLabel("Nama");
		nameLbl.setBounds(10, 45, 70, 30);
		add(nameLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(10, 85, 70, 30);
		add(passwordLbl);
		
		JLabel label = new JLabel(":");
		label.setBounds(80, 45, 10, 30);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(80, 85, 10, 30);
		add(label_1);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(90, 45, 150, 30);
		add(nameTxt);
		nameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(90, 85, 150, 30);
		add(passwordTxt);
		
		JLabel groupLbl = new JLabel("Group User");
		groupLbl.setBounds(10, 125, 70, 30);
		add(groupLbl);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(80, 125, 10, 30);
		add(label_3);
		
		comboBox = new JComboBox();
		comboBox.setBounds(90, 125, 150, 30);
		add(comboBox);
		getGroupData();
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				groupId = groups.get(comboBox.getSelectedIndex()).getGroupId();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(939, 174, 75, 30);
		add(searchBtn);
		
		textField = new JTextField();
		textField.setBounds(779, 174, 150, 30);
		add(textField);
		textField.setColumns(10);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(694, 174, 75, 30);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		add(saveBtn);
		
		JButton editBtn = new JButton("Edit");
		editBtn.setBounds(609, 174, 75, 30);
		add(editBtn);
		
		JButton deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(524, 174, 75, 30);
		add(deleteBtn);
		
		getUserData();
	}

	private void getUserData() {
		users.clear();
		users = ServiceFactory.getSystemBL().getAllUser();
		userConfigTabelModel = new UserConfigTabelModel(users);
		userConfigTabel.setModel(userConfigTabelModel);
	}

	protected void save() {
		User user = new User();
		System.out.println(groupId);
		user.setGroupId(groupId);
		user.setUserName(nameTxt.getText());
		user.setUserPassword(new String(passwordTxt.getPassword()));
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		user.setLastLogin(date);
		user.setLastChanged(date);
		
		ServiceFactory.getSystemBL().saveUser(user);
		
		getUserData();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getGroupData(){
		groups = ServiceFactory.getSystemBL().getAllGroup();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		for (Group group : groups) {
			comboBoxModel.addElement(group.getGroupName());
		}
		comboBox.setModel(comboBoxModel);
	}

	class UserConfigTabelModel extends AbstractTableModel {

		private static final long serialVersionUID = -2284507998303016977L;

		private List<User> users;

		public UserConfigTabelModel(List<User> users){
			this.users = users;
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return users == null? 0 : users.size() ;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			User user = users.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return user.getUserId();
			case 1:
				return user.getUserName();
			case 2:
				return user.getUserPassword();
			case 3:
				return user.getLastChanged();
			case 4:
				return user.getLastLogin();
			default:
				return "";
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Class getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			default:
				return String.class;
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
				return "Password";
			case 3:
				return "Last Changed";
			case 4:
				return "Last Login";
			default:
				return "";
			}
		}
	}
}