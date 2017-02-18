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

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.system.model.Screen;

public class ScreenConfigPanel extends JPanel{

	private static final long serialVersionUID = -1894049927788941207L;
	private JTable screenConfigTable;
	private List<Screen> screens = new ArrayList<>();
	private ScreenConfigTabelModel screenConfigTabelModel;
	private JTextField searchField;

	public ScreenConfigPanel() {

		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Menu Layar");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 200, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR MENU LAYAR");
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

		screenConfigTable = new JTable();
		screenConfigTable.setFocusable(false);
		screenConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(screenConfigTable);

		screenConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(screenConfigTable.columnAtPoint(e.getPoint())==6) {
					MainPanel.changePanel("module.system.ui.EditScreenPanel", getSelectedData());
				}
			}
		});

		JButton newGroupBtn = new JButton("Buat Baru");
		newGroupBtn.setBounds(724, 100, 90, 30);
		add(newGroupBtn);
		newGroupBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.system.ui.CreateScreenPanel");
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
		getGroupData();

		updateTableSize();
	}

	protected Object getSelectedData() {
		return screens.get(screenConfigTable.getSelectedRow());
	}

	private void getGroupData() {
		screens.clear();
		screens = ServiceFactory.getSystemBL().getAllScreen("");
		screenConfigTabelModel = new ScreenConfigTabelModel(screens);
		screenConfigTable.setModel(screenConfigTabelModel);
	}

	class ScreenConfigTabelModel extends AbstractTableModel {

		private static final long serialVersionUID = 2384701170397548994L;

		private List<Screen> screens;

		public ScreenConfigTabelModel(List<Screen> screens) {
			this.screens = screens;
		}

		@Override
		public int getColumnCount() {
			return 6;
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
				return screens.indexOf(screen) + 1;
			case 1:
				return screen.getMenuName();
			case 2:
				return screen.getScreenName();
			case 3:
				return screen.getScreenTitle();
			case 4:
				return screen.getModuleName();
			case 5:
				return "<html><u>View</u></html>";
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
				return "Nama Menu";
			case 2:
				return "Nama Layar";
			case 3:
				return "Judul Layar";
			case 4:
				return "Nama Module";
			case 5:
				return "Aksi";
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
	}

	private void updateTableSize() {
		screenConfigTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		screenConfigTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = screenConfigTable.getColumnModel().getColumn(0);
		TableColumn column2 = screenConfigTable.getColumnModel().getColumn(1);
		TableColumn column3 = screenConfigTable.getColumnModel().getColumn(2);
		TableColumn column4 = screenConfigTable.getColumnModel().getColumn(3);
		TableColumn column5 = screenConfigTable.getColumnModel().getColumn(4);
		TableColumn column6 = screenConfigTable.getColumnModel().getColumn(5);

		column1.setPreferredWidth(20);
		column1.setMinWidth(15);
		column1.setMinWidth(25);

		column2.setPreferredWidth(70);
		column2.setMinWidth(60);
		column2.setMinWidth(80);

		column3.setPreferredWidth(200);
		column3.setMinWidth(150);
		column3.setMinWidth(250);

		column4.setPreferredWidth(200);
		column4.setMinWidth(150);
		column4.setMinWidth(250);
		
		column5.setPreferredWidth(300);
		column5.setMinWidth(250);
		column5.setMinWidth(350);
		
		column6.setPreferredWidth(100);
		column6.setMinWidth(90);
		column6.setMinWidth(110);
	}
}