package module.stockopname.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import main.component.DialogBox;
import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.personalia.ui.ListFilesAttendancePanel;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.StockOpname;
import module.stockopname.ui.ListScheduledSOPanel.SetScheduledSOTableModel;
import module.util.Pagination;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;

public class ListSOManualPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ListSOManualPanel.class.getName());
	private JButton searchBtn;
	private TextField searchField;
	JTable stockOpnameTable;
	private JScrollPane scrollPane;

	private JButton advancedSearchBtn;
	private JButton createNewBtn;
	private PagingPanel<StockOpname> pagingPanel;


	SOManualTableModel receivedTableModel;
	List<StockOpname> stockOpnames;
	ListSOManualPanel listSoManual;
	
	public ListSOManualPanel() {
		setLayout(null);
		listSoManual = this;

		JLabel lblBreadcrumb = new JLabel("ERP > Stock Opname Product");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Stock Opname Manual");
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

		stockOpnames = new ArrayList<>();
		receivedTableModel = new SOManualTableModel(stockOpnames);
		stockOpnameTable = new JTable(receivedTableModel);
		stockOpnameTable.setFocusable(false);

		scrollPane =  new JScrollPane(stockOpnameTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);

		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
		stockOpnameTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(stockOpnameTable.columnAtPoint(e.getPoint())==5)
					MainPanel.changePanel("module.stockopname.ui.ViewStockOpnamePanel", pagingPanel.getSubListData().get(stockOpnameTable.getSelectedRow()));
			}
		});

		try {
			stockOpnames = ServiceFactory.getStockOpnameBL().getStockOpname();
			stockOpnameTable.setModel(new SOManualTableModel(stockOpnames));
			stockOpnameTable.updateUI();

			pagingPanel.setPage(1);
			pagingPanel.setMaxDataPerPage(20);
			pagingPanel.setData(stockOpnames);
			pagingPanel.setTable(stockOpnameTable);
			pagingPanel.setTableModel(receivedTableModel);
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
					stockOpnames = ServiceFactory.getStockOpnameBL().getStockOpname();
					stockOpnameTable.setModel(new SOManualTableModel(stockOpnames));
					stockOpnameTable.updateUI();
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
				MainPanel.changePanel("module.stockopname.ui.CreateNewStockOpnamePanel");
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
		stockOpnameTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		stockOpnameTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = stockOpnameTable.getColumnModel().getColumn(0);
		TableColumn column2 = stockOpnameTable.getColumnModel().getColumn(1);
		TableColumn column3 = stockOpnameTable.getColumnModel().getColumn(2);
		TableColumn column4 = stockOpnameTable.getColumnModel().getColumn(3);
		TableColumn column5 = stockOpnameTable.getColumnModel().getColumn(4);
		TableColumn column6 = stockOpnameTable.getColumnModel().getColumn(5);


		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);

		column2.setPreferredWidth(300);
		column2.setMinWidth(300);
		column2.setMaxWidth(300);

		column3.setPreferredWidth(200);
		column3.setMinWidth(200);
		column3.setMaxWidth(200);

		column4.setPreferredWidth(200);
		column4.setMinWidth(200);
		column4.setMaxWidth(200);

		column5.setPreferredWidth(150);
		column5.setMinWidth(150);
		column5.setMaxWidth(150);
		
		column6.setPreferredWidth(150);
		column6.setMinWidth(150);
		column6.setMaxWidth(150);


	}

	public class SOManualTableModel extends AbstractTableModel implements Pagination {
		private static final long serialVersionUID = 1L;
		private List<StockOpname> stockOpnames;
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		public SOManualTableModel(List<StockOpname> importFingerPrint) {
			this.stockOpnames = importFingerPrint;
		}

		/**
		 * Method to get row count
		 * @return int
		 */
		public int getRowCount() {
			return stockOpnames.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 6;
		}

		/**
		 * Method to get selected value
		 * @param rowIndex rowIndex of selected table
		 * @param columnIndex columnIndex of selected table 
		 * @return ({@link User}) Object 
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			StockOpname p = stockOpnames.get(rowIndex);
			switch(columnIndex){
			case 0 :
				return p.getId();
			case 1 : 
				return p.getSoName();
			case 2 :
				return sdf.format(p.getSoDate());
			case 3 :
				return p.getSoType();
			case 4 :
				return p.getStatus();
			case 5 :
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
				return "Tanggal Stock Opname";
			case 3 :
				return "Tipe";
			case 4 :
				return "Status";
			case 5 :
				return "Tindakan";
			default :
				return "";
			}
		}

		@Override
		public <T> void setList(List<T> list) {
			stockOpnames = (List<StockOpname>) list;
		}

	}
}
