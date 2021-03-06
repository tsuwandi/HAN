package module.stockopname.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.personalia.model.ImportFingerprint;
import module.personalia.ui.ListFilesAttendancePanel;
import module.stockopname.model.SetSOScheduled;
import module.util.Pagination;

public class ListScheduledSOPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ListScheduledSOPanel.class.getName());
	private JButton searchBtn;
	private TextField searchField;
	JTable setSoScheduleTable;
	private JScrollPane scrollPane;

	private JButton advancedSearchBtn;
	private JButton createNewBtn;
	private PagingPanel<SetSOScheduled> pagingPanel;


	SetScheduledSOTableModel setScheduledTableModel;
	List<SetSOScheduled> setSoSchedule;
	ListScheduledSOPanel listScheduledSOPanel;
	public ListScheduledSOPanel() {
		setLayout(null);
		listScheduledSOPanel = this;

		JLabel lblBreadcrumb = new JLabel("ERP > Stock Opname Product");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Jadwal Stock Opname");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		searchBtn = new JButton("Cari");
		searchBtn.setBounds(950,130,100,30);
		add(searchBtn);

		searchField = new TextField();
		searchField.setBounds(800, 131, 150, 28);
		add(searchField);

		createNewBtn = new JButton("Buat Baru");
		createNewBtn.setBounds(750,80,150,30);
		add(createNewBtn);

		advancedSearchBtn = new JButton("Pencarian Lanjut");
		advancedSearchBtn.setBounds(900,80,150,30);
		add(advancedSearchBtn);

		setSoSchedule = new ArrayList<>();
		setScheduledTableModel = new SetScheduledSOTableModel(setSoSchedule);
		setSoScheduleTable = new JTable(setScheduledTableModel);
		setSoScheduleTable.setFocusable(false);

		scrollPane =  new JScrollPane(setSoScheduleTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);

		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
		setSoScheduleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(setSoScheduleTable.columnAtPoint(e.getPoint())==3)
					MainPanel.changePanel("module.stockopname.ui.ViewScheduledSOPanel", pagingPanel.getSubListData().get(setSoScheduleTable.getSelectedRow()));
			}
		});

		try {
			setSoSchedule = ServiceFactory.getStockOpnameBL().getSetSoSchedule();
			setSoScheduleTable.setModel(new SetScheduledSOTableModel(setSoSchedule));
			setSoScheduleTable.updateUI();

			pagingPanel.setPage(1);
			pagingPanel.setMaxDataPerPage(20);
			pagingPanel.setData(setSoSchedule);
			pagingPanel.setTable(setSoScheduleTable);
			pagingPanel.setTableModel(setScheduledTableModel);
			pagingPanel.setBounds(450,510,130,50);


			setTableSize();
		} catch (Exception e1) {
			DialogBox.showError(e1.getMessage());
			log.error(e1.getMessage());
			e1.printStackTrace();
		}

		advancedSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//				PopUpAdvanceSearch pop = new PopUpAdvanceSearch(listFilesAttendancePanel);
				//				pop.show();
				//				pop.setLocationRelativeTo(null);
				//				pop.setModal(true);
			}
		});

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
//					importFingerPrints = ServiceFactory.getPersonaliaBL().getImportFingerprints("");
					setSoScheduleTable.setModel(new SetScheduledSOTableModel(setSoSchedule));
					setSoScheduleTable.updateUI();
					setTableSize();
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}

			}
		});
		
		createNewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.stockopname.ui.CreateNewScheduledSOPanel");
			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				searchField.requestFocusInWindow();
			}
		});
		

	}



	public void setTableSize(){
		setSoScheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setSoScheduleTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = setSoScheduleTable.getColumnModel().getColumn(0);
		TableColumn column2 = setSoScheduleTable.getColumnModel().getColumn(1);
		TableColumn column3 = setSoScheduleTable.getColumnModel().getColumn(2);
		TableColumn column4 = setSoScheduleTable.getColumnModel().getColumn(3);


		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);

		column2.setPreferredWidth(700);
		column2.setMinWidth(700);
		column2.setMaxWidth(700);

		column3.setPreferredWidth(200);
		column3.setMinWidth(200);
		column3.setMaxWidth(200);

		column4.setPreferredWidth(100);
		column4.setMinWidth(100);
		column4.setMaxWidth(100);


	}

	public class SetScheduledSOTableModel extends AbstractTableModel implements Pagination {
		private List<SetSOScheduled> setSOSchedules;

		public SetScheduledSOTableModel(List<SetSOScheduled> setSOSchedules) {
			this.setSOSchedules = setSOSchedules;
		}

		/**
		 * Method to get row count
		 * @return int
		 */
		public int getRowCount() {
			return setSOSchedules.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 4;
		}

		/**
		 * Method to get selected value
		 * @param rowIndex rowIndex of selected table
		 * @param columnIndex columnIndex of selected table 
		 * @return ({@link User}) Object 
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SetSOScheduled p = setSOSchedules.get(rowIndex);
			switch(columnIndex){
			case 0 :
				return p.getId();
			case 1 : 
				return p.getSoName();
			case 2 :
				return p.getReccurence();
			case 3 :
				return "View";
			default :
				return "";
			}
		}
		public boolean isCellEditable(int row, int column) {
			return false;
		}


		/**
		 * Method to getColumnName
		 * @param column columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch(column){
			case 0 : 
				return "ID";
			case 1 :
				return "Nama Stock Opname";
			case 2 :
				return "Perulangan";
			case 3 :
				return "Tindakan";
			default :
				return "";
			}
		}

		@Override
		public <T> void setList(List<T> list) {
			setSOSchedules = (List<SetSOScheduled>) list;
		}

	}
}
