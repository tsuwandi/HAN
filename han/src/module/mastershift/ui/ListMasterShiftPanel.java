package module.mastershift.ui;

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

import main.component.DialogBox;
import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.mastershift.model.MasterShift;
import module.util.Pagination;

public class ListMasterShiftPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ListMasterShiftPanel.class.getName());
	private JButton searchBtn;
	private TextField searchField;
	private JTable masterShiftTable;
	private JScrollPane scrollPane;

	private JButton advancedSearchBtn;
	private JButton createNewBtn;
	private PagingPanel<MasterShift> pagingPanel;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	MasterShiftTableModel masterShiftTableModel;
	List<MasterShift> masterShifts;
	ListMasterShiftPanel listMasterShiftPanel;
	public ListMasterShiftPanel() {
		setLayout(null);
		listMasterShiftPanel = this;

		JLabel lblBreadcrumb = new JLabel("ERP > Master Shift");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Master Shift");
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

		masterShifts = new ArrayList<>();
		masterShiftTableModel = new MasterShiftTableModel(masterShifts);
		masterShiftTable = new JTable(masterShiftTableModel);
		masterShiftTable.setFocusable(false);

		scrollPane =  new JScrollPane(masterShiftTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);

		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
		masterShiftTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(masterShiftTable.columnAtPoint(e.getPoint())==3)
					MainPanel.changePanel("module.mastershift.ui.ViewMasterShiftPanel", pagingPanel.getSubListData().get(masterShiftTable.getSelectedRow()));
			}
		});

		try {
//			setSoSchedule = ServiceFactory.getStockOpnameBL().getSetSoSchedule();
			masterShiftTable.setModel(new MasterShiftTableModel(masterShifts));
			masterShiftTable.updateUI();

			pagingPanel.setPage(1);
			pagingPanel.setMaxDataPerPage(20);
			pagingPanel.setData(masterShifts);
			pagingPanel.setTable(masterShiftTable);
			pagingPanel.setTableModel(masterShiftTableModel);
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
					masterShiftTable.setModel(new MasterShiftTableModel(masterShifts));
					masterShiftTable.updateUI();
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
				MainPanel.changePanel("module.mastershift.ui.CreateNewMasterShiftPanel");
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
		masterShiftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		masterShiftTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = masterShiftTable.getColumnModel().getColumn(0);
		TableColumn column2 = masterShiftTable.getColumnModel().getColumn(1);
		TableColumn column3 = masterShiftTable.getColumnModel().getColumn(2);
		TableColumn column4 = masterShiftTable.getColumnModel().getColumn(3);
		TableColumn column5 = masterShiftTable.getColumnModel().getColumn(4);
		TableColumn column6 = masterShiftTable.getColumnModel().getColumn(5);
		TableColumn column7 = masterShiftTable.getColumnModel().getColumn(6);


		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);

		column2.setPreferredWidth(200);
		column2.setMinWidth(200);
		column2.setMaxWidth(200);

		column3.setPreferredWidth(300);
		column3.setMinWidth(300);
		column3.setMaxWidth(300);

		column4.setPreferredWidth(100);
		column4.setMinWidth(100);
		column4.setMaxWidth(100);

		column5.setPreferredWidth(150);
		column5.setMinWidth(150);
		column5.setMaxWidth(150);

		column6.setPreferredWidth(150);
		column6.setMinWidth(150);
		column6.setMaxWidth(150);
		
		column7.setPreferredWidth(100);
		column7.setMinWidth(100);
		column7.setMaxWidth(100);

	}

	public class MasterShiftTableModel extends AbstractTableModel implements Pagination {
		private List<MasterShift> masterShifts;
		

		public MasterShiftTableModel(List<MasterShift> masterShifts) {
			this.masterShifts = masterShifts;
		}

		/**
		 * Method to get row count
		 * @return int
		 */
		public int getRowCount() {
			return masterShifts.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 7;
		}

		/**
		 * Method to get selected value
		 * @param rowIndex rowIndex of selected table
		 * @param columnIndex columnIndex of selected table 
		 * @return ({@link User}) Object 
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			MasterShift p = masterShifts.get(rowIndex);
			switch(columnIndex){
			case 0 :
				return p.getId();
			case 1 : 
				return p.getShiftCode();
			case 2 :
				return p.getShiftName();
			case 3 :
				return p.getType();
			case 4 :
				return p.getInputDate();
			case 5 :
				return p.getEditedDate();
			case 6 :
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
				return "Shfit Code";
			case 2 :
				return "Shift Name";
			case 3 :
				return "Type";
			case 4 :
				return "Input Date";
			case 5 :
				return "Edited Date";
			case 6 :
				return "Tindakan";
			default :
				return "";
			}
		}

		@Override
		public <T> void setList(List<T> list) {
			masterShifts = (List<MasterShift>) list;
		}

	}
}
