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
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.dryout.model.DryOut;
import module.util.DateUtil;

public class DryOutListPanel extends JPanel {

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

		lblBreadcrumb = new JLabel("ERP > Pengeringan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("PENGELUARAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Create New");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryout.ui.DryOutCreatePanel");
			}
		});
		btnCreateNew.setBounds(750, 80, 100, 40);
		add(btnCreateNew);

		btnExport = new JButton("Eksport");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnExport.setBounds(850, 80, 100, 40);
		add(btnExport);

		btnAdvancedSearch = new JButton("<html><center>Advanced <br> Search<center></html>");
		btnAdvancedSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAdvancedSearchDialog(dryOutListPanel);
			}
		});
		btnAdvancedSearch.setBounds(950, 80, 100, 40);
		add(btnAdvancedSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(775, 130, 150, 30);
		add(txtSearch);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doSearch(txtSearch.getText());
			}
		});
		btnSearch.setBounds(950, 130, 100, 40);
		add(btnSearch);

		scrollPaneDryOut = new JScrollPane();
		scrollPaneDryOut.setBounds(50, 200, 1000, 200);
		add(scrollPaneDryOut);

		dryOutTableModel = new DryOutTableModel(new ArrayList<DryOut>());
		tblDryOut = new JTable(dryOutTableModel);
		scrollPaneDryOut.setViewportView(tblDryOut);

		tblDryOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4)
						MainPanel.changePanel("module.dryout.ui.DryOutViewPanel", listOfDryOut.get(row));
				}
			}
		});

		try {
			listOfDryOut = new ArrayList<DryOut>();
			listOfDryOut = ServiceFactory.getDryOutBL().getAllDryOut();
			refreshTableDryOut();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

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
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add supp cp dialog
	 */
	protected void showAdvancedSearchDialog(DryOutListPanel dryOutListPanel) {
		// DryOutAdvSearchDialog dryOutAdvSearchDialog = new
		// DryOutAdvSearchDialog(dryOutListPanel);
		// dryOutAdvSearchDialog.setTitle("Advanced Search");
		// dryOutAdvSearchDialog.setLocationRelativeTo(null);
		// dryOutAdvSearchDialog.setVisible(true);
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
			return 5;
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

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
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
