package module.dryout.ui;

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
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.dryout.model.DryOut;
import module.util.DateUtil;

public class DryOutListPanel extends JPanel {
	
	private static final Logger LOGGER = Logger.getLogger(DryOutListPanel.class);

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneDryOut;

	private DryOutTableModel dryOutTableModel;
	public List<DryOut> listOfDryOut = new ArrayList<DryOut>();

	JTable tblDryOut;

	private DryOutListPanel dryOutListPanel;

	private static final long serialVersionUID = 1L;

	public DryOutListPanel() {
		dryOutListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));
		
		DocumentFilter filter = new UppercaseDocumentFilter();

		lblBreadcrumb = new JLabel("ERP > Pengeringan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("PENGELUARAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryout.ui.DryOutCreatePanel");
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
				showAdvancedSearchDialog(dryOutListPanel);
			}
		});
		btnAdvancedSearch.setBounds(900, 80, 150, 30);
		add(btnAdvancedSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(800, 131, 150, 28);
		((AbstractDocument) txtSearch.getDocument()).setDocumentFilter(filter);
		add(txtSearch);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doSearch(txtSearch.getText());
			}
		});
		btnSearch.setBounds(950, 130, 100, 30);
		add(btnSearch);

		scrollPaneDryOut = new JScrollPane();
		scrollPaneDryOut.setBounds(50, 200, 1000, 300);
		add(scrollPaneDryOut);

		dryOutTableModel = new DryOutTableModel(new ArrayList<DryOut>());
		tblDryOut = new JTable(dryOutTableModel);
		tblDryOut.setFocusable(false);
		tblDryOut.setAutoCreateRowSorter(true);
		scrollPaneDryOut.setViewportView(tblDryOut);

		tblDryOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 5)
						MainPanel.changePanel("module.dryout.ui.DryOutViewPanel", listOfDryOut.get(row));
				}
			}
		});

		try {
			listOfDryOut = new ArrayList<DryOut>();
			listOfDryOut = ServiceFactory.getDryOutBL().getAllDryOut();
			refreshTableDryOut();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnCreateNew.requestFocusInWindow();
			}
		});
	}

	public void refreshTableDryOut() {
		try {
			tblDryOut.setModel(new DryOutTableModel(listOfDryOut));
		} catch (Exception e1) {
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfDryOut = new ArrayList<DryOut>();
			listOfDryOut = ServiceFactory.getDryOutBL().getAllDryOutBySimpleSearch(value);
			refreshTableDryOut();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add supp cp dialog
	 */
	protected void showAdvancedSearchDialog(DryOutListPanel dryOutListPanel) {

	}

	/**
	 * Class as TableModel for Dry In table
	 * 
	 * @author TSI
	 *
	 */
	class DryOutTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<DryOut> listOfDryOut;

		public DryOutTableModel(List<DryOut> listOfDryOut) {
			this.listOfDryOut = listOfDryOut;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfDryOut.size();
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
			case 5:
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
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			DryOut p = listOfDryOut.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getDryOutCode();
			case 1:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getDateOut()));
			case 2:
				return p.getChamber().getChamber();
			case 3:
				return p.getTotalVolume();
			case 4:
				return p.getStatus();
			case 5:
				return "<html><a><u>View</u></a></html>";
			default:
				return "";
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
				return "Kode Pengeluaran";
			case 1:
				return "Tanggal Keluar";
			case 2:
				return "Chamber";
			case 3:
				return "Total Volume";
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
