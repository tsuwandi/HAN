package module.system.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ServiceFactory;
import module.system.model.Group;
import module.system.model.User;
import module.util.Bridging;

public class EditUserPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 2090225881350809172L;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox groupUserCombobox;
	private List<Group> groups = new ArrayList<>();
	private Integer groupId;
	private User user;

	public EditUserPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Sistem > Pengguna");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 214, 25);
		add(breadCrumbLbl);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(924, 589, 90, 30);
		add(btnSimpan);
		
		btnSimpan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		JLabel lblUsername = new JLabel("<html>Username<font color='red'> * </font></html>");
		lblUsername.setBounds(40, 98, 70, 30);
		add(lblUsername);
		
		JLabel label_1 = new JLabel(" : ");
		label_1.setBounds(110, 98, 10, 30);
		add(label_1);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(120, 98, 150, 30);
		add(usernameField);
		
		JLabel label_2 = new JLabel("<html>Password<font color='red'> * </html>");
		label_2.setBounds(40, 138, 70, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel(" : ");
		label_3.setBounds(110, 138, 10, 30);
		add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(120, 138, 150, 30);
		add(passwordField);
		
		JLabel label_4 = new JLabel("Group User");
		label_4.setBounds(40, 178, 70, 30);
		add(label_4);
		
		JLabel label_5 = new JLabel(" : ");
		label_5.setBounds(110, 178, 10, 30);
		add(label_5);
		
		groupUserCombobox = new JComboBox();
		groupUserCombobox.setBounds(120, 178, 150, 30);
		getGroupData();
		add(groupUserCombobox);
		groupUserCombobox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				groupId = groups.get(groupUserCombobox.getSelectedIndex()).getGroupId();
			}
		});
		
		JLabel lblHeader = new JLabel("EDIT DATA USER");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		
		JButton showBtn = new JButton("Show");
		showBtn.setBounds(280, 138, 60, 30);
		add(showBtn);
		showBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				passwordField.setText("You get fool");
			};
			
			@Override
			public void mouseReleased(MouseEvent e) {
				passwordField.setEchoChar('*');
			}
		});
	}

	protected void save() {
		User user = new User();
		
		user.setGroupId(groupId);
		user.setUserName(usernameField.getText());
		user.setUserPassword(new String(passwordField.getPassword()));
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		user.setLastLogin(date);
		user.setLastChanged(date);
		
		ServiceFactory.getSystemBL().saveUser(user);
	}

	private void getGroupData() {
		groups = ServiceFactory.getSystemBL().getAllGroup();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		for (Group group : groups) {
			comboBoxModel.addElement(group.getGroupName());
		}
		groupUserCombobox.setModel(comboBoxModel);
	}

	@Override
	public void invokeObjects(Object... objects) {
		user = (User) objects[0];
		System.out.println(user);
		getUsernameField().setText(user.getUserName());
		getPasswordField().setText(user.getUserPassword());
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
