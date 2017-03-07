package module.productionwaste.ui;

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
import module.productionwaste.model.ProductionWaste;

public class ProductionWasteListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(ProductionWasteListPanel.class);

	JButton btnCreateNew;
	JButton btnExport;
	JButton btnAdvancedSearch;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneProductionWaste;

	private ProductionWasteTableModel productionWasteTableModel;
	public List<ProductionWaste> listOfProductionWaste = new ArrayList<ProductionWaste>();

	JTable tblProductionWaste;

	private ProductionWasteListPanel productionWasteListPanel;

	private static final long serialVersionUID = 1L;

	public ProductionWasteListPanel() {
		productionWasteListPanel = this;
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		lblBreadcrumb = new JLabel("ERP > Produksi > Input Hasil Produksi > Sisa Produksi 9");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("Input Hasil Produksi Sisa Produksi 9 ");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.productionwaste.ui.ProductionWasteCreatePanel");
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
				//showAdvancedSearchDialog(productionWasteListPanel);
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

		scrollPaneProductionWaste = new JScrollPane();
		scrollPaneProductionWaste.setBounds(50, 200, 1000, 300);
		add(scrollPaneProductionWaste);

		productionWasteTableModel = new ProductionWasteTableModel(new ArrayList<ProductionWaste>());
		tblProductionWaste = new JTable(productionWasteTableModel);
		tblProductionWaste.setFocusable(false);
		tblProductionWaste.setAutoCreateRowSorter(true);
		scrollPaneProductionWaste.setViewportView(tblProductionWaste);

		tblProductionWaste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 7)
						MainPanel.changePanel("module.productionwaste.ui.ProductionWasteViewPanel", listOfProductionWaste.get(row));
				}
			}
		});

		try {
			listOfProductionWaste = new ArrayList<ProductionWaste>();
			listOfProductionWaste = ServiceFactory.getProductionWasteBL().getAllProductionWaste("  AND p.production_type_code='"+AppConstants.BC_TYPE_9+"'");
			refreshTableProductionWaste();
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

	public void refreshTableProductionWaste() {
		try {
			tblProductionWaste.setModel(new ProductionWasteTableModel(listOfProductionWaste));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfProductionWaste = new ArrayList<ProductionWaste>();
			listOfProductionWaste = ServiceFactory.getProductionWasteBL().getAllProductionWasteBySimpleSearch("9",value);
			refreshTableProductionWaste();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display advanced search dialog
	 */
	protected void showAdvancedSearchDialog(ProductionWasteListPanel productionWasteListPanel) {
		
	}

	/**
	 * Class as TableModel for ProductionWaste table
	 * 
	 * @author TSI
	 *
	 */
	class ProductionWasteTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<ProductionWaste> listOfProductionWaste;

		public ProductionWasteTableModel(List<ProductionWaste> listOfProductionWaste) {
			this.listOfProductionWaste = listOfProductionWaste;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfProductionWaste.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 8;
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
		 * @return ({@link ProductionWasteAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			ProductionWaste p = listOfProductionWaste.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getPwCode();
			case 1:
				return p.getProductionDate();
			case 2:
				return p.getGroupShift().getDescription();
			case 3:
				return p.getShift().getShiftName();
			case 4:
				return p.getLine().getDescription();
			case 5:
				return p.getProductionType().getProductionType();
			case 6:
				return p.getStatus();
			case 7:
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
				return "Tipe Produksi";	
			case 6:
				return "Status";
			case 7:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
