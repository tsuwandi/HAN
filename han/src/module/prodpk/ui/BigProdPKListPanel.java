package module.prodpk.ui;

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

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.prodpk.model.ProdPK;

public class BigProdPKListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(BigProdPKListPanel.class);

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneProdPK;

	private ProdPKTableModel productionWasteTableModel;
	public List<ProdPK> listOfProdPK = new ArrayList<ProdPK>();

	JTable tblProdPK;

	private BigProdPKListPanel prodPKListPanel;

	private static final long serialVersionUID = 1L;

	public BigProdPKListPanel() {
		prodPKListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Produksi > Input Hasil Produksi > Produksi PK (Hasil Klem) 12");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("Input Produksi PK (Hasil Klem) ");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.prodpk.ui.BigProdPKCreatePanel");
			}
		});
		btnCreateNew.setBounds(700, 80, 100, 30);
		add(btnCreateNew);

		btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnExport.setBounds(800, 80, 100, 30);
		add(btnExport);

		btnAdvancedSearch = new JButton("Pencarian Lanjut");
		btnAdvancedSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//showAdvancedSearchDialog(prodPKListPanel);
			}
		});
		btnAdvancedSearch.setBounds(900, 80, 150, 30);
		add(btnAdvancedSearch);

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

		scrollPaneProdPK = new JScrollPane();
		scrollPaneProdPK.setBounds(50, 200, 1000, 300);
		add(scrollPaneProdPK);

		productionWasteTableModel = new ProdPKTableModel(new ArrayList<ProdPK>());
		tblProdPK = new JTable(productionWasteTableModel);
		tblProdPK.setFocusable(false);
		tblProdPK.setAutoCreateRowSorter(true);
		scrollPaneProdPK.setViewportView(tblProdPK);

		tblProdPK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 6)
						MainPanel.changePanel("module.prodpk.ui.BigProdPKViewPanel", listOfProdPK.get(row));
				}
			}
		});

		try {
			listOfProdPK = new ArrayList<ProdPK>();
			listOfProdPK = ServiceFactory.getProductionPKBL().getAllProdPK(AppConstants.TYPE_12);
			refreshTableProdPK();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
			LOGGER.error(e1.getMessage());
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnCreateNew.requestFocusInWindow();
			}
		});

	}

	public void refreshTableProdPK() {
		try {
			tblProdPK.setModel(new ProdPKTableModel(listOfProdPK));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfProdPK = new ArrayList<ProdPK>();
			listOfProdPK = ServiceFactory.getProductionPKBL().getAllProdPKBySimpleSearch(value, AppConstants.TYPE_12);
			refreshTableProdPK();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display advanced search dialog
	 */
	protected void showAdvancedSearchDialog(BigProdPKListPanel prodPKListPanel) {
		
	}

	/**
	 * Class as TableModel for ProdPK table
	 * 
	 * @author TSI
	 *
	 */
	class ProdPKTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<ProdPK> listOfProdPK;

		public ProdPKTableModel(List<ProdPK> listOfProdPK) {
			this.listOfProdPK = listOfProdPK;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfProdPK.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 7;
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
			case 5:
				return String.class;
			case 6:
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
		 * @return ({@link ProdPKAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			ProdPK p = listOfProdPK.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getProdPKCode();
			case 1:
				return p.getProductionDate();
			case 2:
				return p.getGroupShift().getDescription();
			case 3:
				return p.getShift().getShiftName();
			case 4:
				return p.getLine().getDescription();
			case 5:
				return p.getStatus();
			case 6:
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
				return "Kode Produksi";
			case 1:
				return "Tanggal Produksi";
			case 2:
				return "Group Shift";
			case 3:
				return "Shift";
			case 4:
				return "Line";
			case 5:
				return "Status";
			case 6:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
