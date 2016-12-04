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
import javax.swing.table.TableColumn;

import main.panel.MainPanel;
import module.system.model.Group;
import module.system.model.User;
import controller.ServiceFactory;

public class UserConfigPanel extends JPanel {

	private static final long serialVersionUID = 4653177447116577211L;
	private JTable userConfigTabel;
	private List<User> users = new ArrayList<>();
	private List<Group> groups = new ArrayList<>();
	private UserConfigTabelModel userConfigTabelModel;
	private JTextField textField;
	private Integer groupId;

	@SuppressWarnings("rawtypes")
	public UserConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Sistem");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 134, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("DAFTAR PENGGUNA");
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

		userConfigTabel = new JTable();
		userConfigTabel.setFocusable(false);
		userConfigTabel.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(userConfigTabel);
		userConfigTabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(userConfigTabel.columnAtPoint(e.getPoint())==5) {
					MainPanel.changePanel("module.system.ui.EditUserPanel", getSelectedData());
				}
			}
		});
		
		JButton searchBtn = new JButton("Pencarian");
		searchBtn.setBounds(924, 140, 90, 30);
		add(searchBtn);
		
		textField = new JTextField();
		textField.setBounds(724, 140, 190, 30);
		add(textField);
		textField.setColumns(10);
		
		JButton btnPencarianLanjut = new JButton("<html>Pencarian<br/>Lanjut</html>");
		btnPencarianLanjut.setBounds(924, 100, 90, 30);
		add(btnPencarianLanjut);
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(824, 100, 90, 30);
		add(btnExport);
		
		JButton newUserBtn = new JButton("Buat Baru");
		newUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.system.ui.CreateUserPanel");
			}
		});
		newUserBtn.setBounds(724, 100, 90, 30);
		add(newUserBtn);
		
		getUserData();
		
		updateTableSize();
	}
	
	private void updateTableSize() {
		userConfigTabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		userConfigTabel.getTableHeader().setResizingAllowed(false);
		
		TableColumn column1 = userConfigTabel.getColumnModel().getColumn(0);
		TableColumn column2 = userConfigTabel.getColumnModel().getColumn(1);
		TableColumn column3 = userConfigTabel.getColumnModel().getColumn(2);
		TableColumn column4 = userConfigTabel.getColumnModel().getColumn(3);
		TableColumn column5 = userConfigTabel.getColumnModel().getColumn(4);
		TableColumn column6 = userConfigTabel.getColumnModel().getColumn(5);
		
		column1.setPreferredWidth(20);
		column1.setMinWidth(15);
		column1.setMinWidth(25);
		
		column2.setPreferredWidth(70);
		column2.setMinWidth(60);
		column2.setMinWidth(80);
		
		column3.setPreferredWidth(70);
		column3.setMinWidth(60);
		column3.setMinWidth(80);
		
		column4.setPreferredWidth(70);
		column4.setMinWidth(60);
		column4.setMinWidth(80);
		
		column5.setPreferredWidth(70);
		column5.setMinWidth(60);
		column5.setMinWidth(80);
		
		column6.setPreferredWidth(70);
		column6.setMinWidth(60);
		column6.setMinWidth(80);
	}

	private User getSelectedData() {
		int row = userConfigTabel.getSelectedRow();
		
		User user = new User();
		user.setId(Integer.parseInt(userConfigTabel.getValueAt(row, 0).toString()));
		//user.setGroupId(Integer.parseInt(userConfigTabel.getValueAt(row, 1).toString()));
		user.setUsername(userConfigTabel.getValueAt(row, 1).toString());
		user.setPassword(userConfigTabel.getValueAt(row, 2).toString());
		//user.setLastLogin( userConfigTabel.getValueAt(row, 4).toString());
		//user.setLastChanged(userConfigTabel.getValueAt(row, 5).toString());
		
		System.out.println("execute");
		
		return user;
	}

	private void getUserData() {
		users.clear();
		users = ServiceFactory.getSystemBL().getAllUser();
		userConfigTabelModel = new UserConfigTabelModel(users);
		userConfigTabel.setModel(userConfigTabelModel);
	}

	class UserConfigTabelModel extends AbstractTableModel {

		private static final long serialVersionUID = -2284507998303016977L;

		private List<User> users;

		public UserConfigTabelModel(List<User> users){
			this.users = users;
		}

		@Override
		public int getColumnCount() {
			return 6;
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
				return user.getId();
			case 1:
				return user.getUsername();
			case 2:
				return user.getPassword();
			case 3:
				return null; //user.getLastChanged();
			case 4:
				return user.getLastLogin();
			case 5:
				return "Ubah";
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
			case 5:
				return String.class;
			default:
				return String.class;
			}
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "ID";
			case 1:
				return "Nama";
			case 2:
				return "Password";
			case 3:
				return "Last Changed";
			case 4:
				return "Last Login";
			case 5:
				return "Aksi";
			default:
				return "";
			}
		}
	}
}