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

import com.toedter.calendar.JDateChooser;

import module.system.model.User;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class UserConfigPanel extends JPanel {

	private static final long serialVersionUID = 4653177447116577211L;
	private JTable userConfigTabel;
	private List<User> users = new ArrayList<>();
	private UserConfigTabelModel userConfigTabelModel;
	private JTextField nameTxt;
	private JTextField passwordTxt;

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
		userConfigTabelModel = new UserConfigTabelModel(users);
		userConfigTabel.setModel(userConfigTabelModel);
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
		nameTxt.setBounds(90, 45, 86, 30);
		add(nameTxt);
		nameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(90, 85, 86, 30);
		add(passwordTxt);
		
		JLabel groupLbl = new JLabel("Group User");
		groupLbl.setBounds(10, 125, 70, 30);
		add(groupLbl);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(80, 125, 10, 30);
		add(label_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(90, 125, 86, 30);
		add(comboBox);
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
			return users.size();
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