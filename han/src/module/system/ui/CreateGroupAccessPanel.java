package module.system.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.system.model.Group;
import module.system.model.GroupScreen;
import module.system.model.Menu;
import module.system.model.Screen;

public class CreateGroupAccessPanel extends JPanel{
	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<Group> groupsCmbox;
	private ComboBox<Menu> menuCmbox;
	private JTable groupAccessTabel;
	private ScreenTableModel screenTableModel;
	private List<Screen> screens = new ArrayList<>();

	public CreateGroupAccessPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Sistem > Group Screen");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN GROUP SCREEN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 200, 25);
		add(lblHeader);

		JLabel lblGroup = new JLabel("Daftar Group");
		lblGroup.setBounds(30, 80, 100, 30);
		add(lblGroup);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);

		groupsCmbox = new ComboBox<>();
		groupsCmbox.setBounds(140, 80, 200, 30);
		add(groupsCmbox);
		
		JPanel screenPanel = new JPanel();
		screenPanel.setLayout(null);
		screenPanel.setBounds(10, 215, 1004, 363);
		add(screenPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		screenPanel.add(scrollPane);
		
		groupAccessTabel = new JTable();
		groupAccessTabel.setFocusable(false);
		groupAccessTabel.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(groupAccessTabel);

		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);

		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		JLabel lblDaftarMenu = new JLabel("Daftar Menu");
		lblDaftarMenu.setBounds(30, 120, 100, 30);
		add(lblDaftarMenu);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 120, 10, 30);
		add(label_2);
		
		menuCmbox = new ComboBox<module.system.model.Menu>();
		menuCmbox.setBounds(140, 120, 200, 30);
		add(menuCmbox);
		
		JButton showBtn = new JButton("Show");
		showBtn.setBounds(30, 160, 90, 30);
		add(showBtn);
		
		showBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				back();
			}
		});

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
			}
		});

		getData();
	}

	protected void showData() {
		clearData();
		StringBuffer sb = new StringBuffer();
		sb.append(" and menu_name = '");
		sb.append(menuCmbox.getDataIndex().getMenuName());
		sb.append("'");
		screens = ServiceFactory.getSystemBL().getAllScreen(sb.toString());
		screenTableModel = new ScreenTableModel(screens);
		groupAccessTabel.setModel(screenTableModel);
	}
	
	private void clearData(){
		screens.clear();
		screenTableModel = new ScreenTableModel(screens);
		groupAccessTabel.setModel(screenTableModel);
	}

	private void getData() {
		groupsCmbox.setList(ServiceFactory.getSystemBL().getAllGroup());
		menuCmbox.setList(ServiceFactory.getSystemBL().getAllMenu());
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
	}

	protected void save() {
		GroupScreen groupScreen = new GroupScreen();

		groupScreen.setInputDate(new Date());
		groupScreen.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		groupScreen.setEditDate(new Date());
		groupScreen.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());

		try {
			//ServiceFactory.getPersonaliaBL().saveDivision(division);
			option();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}

	}

	private void option() {
		if (DialogBox.showAfterChoiceInsert()==0) {
			clear();
		} else {
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		}
	}

	private void clear() {
		
	}
	
	class ScreenTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -8395823722046022443L;
		private List<Screen> screens;
		
		public ScreenTableModel(List<Screen> screens) {
			this.screens = screens;
		}
		
		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return screens.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Screen screen = screens.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return screens.indexOf(screen)+1;
			case 1:
				return screen.getScreenName();
			case 2:
				return screen.getScreenTitle();
			case 3:
				return screen.getAccess();
			default:
				return "";
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			
			switch (columnIndex) {
			case 0:
				return "No";
			case 1:
				return "Nama Layar";
			case 2:
				return "Judul Layar";
			case 3:
				return "Access";
			default:
				return "";
			}
		}
	}
}