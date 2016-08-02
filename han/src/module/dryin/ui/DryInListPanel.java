package module.dryin.ui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.dryin.model.DryIn;
import module.util.DateUtil;
import module.util.JTextFieldLimit;

public class DryInListPanel extends JPanel {

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneDryIn;

	private DryInTableModel dryInTableModel;
	public List<DryIn> listOfDryIn = new ArrayList<DryIn>();

	JTable tblDryIn;

	private DryInListPanel dryInListPanel;

	private static final long serialVersionUID = 1L;

	public DryInListPanel() {
		dryInListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Pengeringan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("PEMASUKAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryin.ui.DryInCreatePanel");
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
				showAdvancedSearchDialog(dryInListPanel);
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

		scrollPaneDryIn = new JScrollPane();
		scrollPaneDryIn.setBounds(50, 200, 1000, 300);
		add(scrollPaneDryIn);

		dryInTableModel = new DryInTableModel(new ArrayList<DryIn>());
		tblDryIn = new JTable(dryInTableModel);
		tblDryIn.setFocusable(false);
		scrollPaneDryIn.setViewportView(tblDryIn);

		tblDryIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4)
						MainPanel.changePanel("module.dryin.ui.DryInViewPanel", listOfDryIn.get(row));
				}
			}
		});

		try {
			listOfDryIn = new ArrayList<DryIn>();
			listOfDryIn = ServiceFactory.getDryInBL().getAllDryIn();
			refreshTableDryIn();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				btnCreateNew.requestFocusInWindow();
			}
		});

	}

	public void refreshTableDryIn() {
		try {
			tblDryIn.setModel(new DryInTableModel(listOfDryIn));
		} catch (Exception e1) {
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfDryIn = new ArrayList<DryIn>();
			listOfDryIn = ServiceFactory.getDryInBL().getAllDryInBySimpleSearch(value);
			refreshTableDryIn();
		} catch (SQLException e1) {
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add supp cp dialog
	 */
	protected void showAdvancedSearchDialog(DryInListPanel dryInListPanel) {
//		DryInAdvSearchDialog dryInAdvSearchDialog = new DryInAdvSearchDialog(dryInListPanel);
//		dryInAdvSearchDialog.setTitle("Advanced Search");
//		dryInAdvSearchDialog.setLocationRelativeTo(null);
//		dryInAdvSearchDialog.setVisible(true);
	}

	/**
	 * Class as TableModel for Dry In table
	 * 
	 * @author TSI
	 *
	 */
	class DryInTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<DryIn> listOfDryIn;

		public DryInTableModel(List<DryIn> listOfDryIn) {
			this.listOfDryIn = listOfDryIn;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfDryIn.size();
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
			DryIn p = listOfDryIn.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getDryInCode();
			case 1:
				return DateUtil.setFormatedDate(DateUtil.toDate(p.getDateIn()));
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
				return "Kode Pemasukan";
			case 1:
				return "Tanggal Masuk";
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
