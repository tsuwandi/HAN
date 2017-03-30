package module.receiveprodresult.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import main.component.DialogBox;
import main.panel.MainPanel;
import module.receiveprodresult.model.ReceiveProdResult;
import module.util.DateUtil;

import org.apache.log4j.Logger;

import controller.ServiceFactory;

public class ReceiveProdResultListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(ReceiveProdResultListPanel.class);

//	JButton btnCreateNew;
//	JButton btnExport;
//	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneReceiveProdResult;

	private ReceiveProdResultTableModel purchaseProdResultTableModel;
	public List<ReceiveProdResult> listOfReceiveProdResult = new ArrayList<ReceiveProdResult>();

	JTable tblReceiveProdResult;

	private ReceiveProdResultListPanel receiveProdResultListPanel;

	private static final long serialVersionUID = 1L;
	
	public static final String COMPLETED = "COMPLETED";

	public ReceiveProdResultListPanel() {
		receiveProdResultListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Produksi > Hasil Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 600, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("List Penerimaan Hasil Produksi ");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

//		btnCreateNew = new JButton("Buat Baru");
//		btnCreateNew.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				MainPanel.changePanel("module.purchaseprodresult.ui.ReceiveProdResultCreatePanel");
//			}
//		});
//		btnCreateNew.setBounds(700, 80, 100, 30);
//		add(btnCreateNew);
//
//		btnExport = new JButton("Export");
//		btnExport.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//
//			}
//		});
//		btnExport.setBounds(800, 80, 100, 30);
//		add(btnExport);
//
//		btnAdvancedSearch = new JButton("Pencarian Lanjut");
//		btnAdvancedSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				//showAdvancedSearchDialog(receiveProdResultListPanel);
//			}
//		});
//		btnAdvancedSearch.setBounds(900, 80, 150, 30);
//		add(btnAdvancedSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(800, 131, 150, 28);
		add(txtSearch);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doSearch(txtSearch.getText());
			}
		});
		btnSearch.setBounds(950, 130, 100, 30);
		add(btnSearch);

		scrollPaneReceiveProdResult = new JScrollPane();
		scrollPaneReceiveProdResult.setBounds(50, 200, 1000, 300);
		add(scrollPaneReceiveProdResult);

		purchaseProdResultTableModel = new ReceiveProdResultTableModel(new ArrayList<ReceiveProdResult>());
		tblReceiveProdResult = new JTable(purchaseProdResultTableModel);
		tblReceiveProdResult.setFocusable(false);
		tblReceiveProdResult.setAutoCreateRowSorter(true);
		scrollPaneReceiveProdResult.setViewportView(tblReceiveProdResult);

		tblReceiveProdResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 5)
						MainPanel.changePanel("module.receiveprodresult.ui.ReceiveProdResultViewPanel", listOfReceiveProdResult.get(row));
				}
			}
		});

		try {
			listOfReceiveProdResult = new ArrayList<ReceiveProdResult>();
			listOfReceiveProdResult = ServiceFactory.getReceiveProductResultBL().getAllReceiveProdResult();
			refreshTableReceiveProdResult();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
			LOGGER.error(e1.getMessage());
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnSearch.requestFocusInWindow();
			}
		});

	}

	public void refreshTableReceiveProdResult() {
		try {
			tblReceiveProdResult.setModel(new ReceiveProdResultTableModel(listOfReceiveProdResult));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfReceiveProdResult = new ArrayList<ReceiveProdResult>();
			listOfReceiveProdResult = ServiceFactory.getReceiveProductResultBL().getAllReceiveProdResultBySimpleSearch(value);
			refreshTableReceiveProdResult();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display advanced search dialog
	 */
	protected void showAdvancedSearchDialog(ReceiveProdResultListPanel receiveProdResultListPanel) {
		
	}

	/**
	 * Class as TableModel for ReceiveProdResult table
	 * 
	 * @author TSI
	 *
	 */
	class ReceiveProdResultTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<ReceiveProdResult> listOfReceiveProdResult;

		public ReceiveProdResultTableModel(List<ReceiveProdResult> listOfReceiveProdResult) {
			this.listOfReceiveProdResult = listOfReceiveProdResult;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfReceiveProdResult.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 6;
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column) {
			switch (column) {
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

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link ReceiveProdResultAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			ReceiveProdResult p = listOfReceiveProdResult.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getPprCode();
			case 1:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getPurchaseProdResult().getPurchaseDate()));
			case 2:
				return p.getRprCode();
			case 3:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getReceiveDate()));
			case 4:
				return p.getStatus();
			case 5:
				return "<html><a><u>View</u></a></html>";
			default:
	            throw new IllegalArgumentException("Invalid column index");
	        }
		}
		
		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "Kode Pembelian";
			case 1:
				return "Tanggal Pembelian";
			case 2:
				return "Kode Penerimaan";
			case 3:
				return "Tanngal Penerimaan";
			case 4:
				return "Status";
			case 5:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
