package module.sn.woodtype.ui;

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
import module.sn.woodtype.model.WoodType;

public class WoodTypeListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(WoodTypeListPanel.class);

	JButton btnCreateNew;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneWoodType;

	private WoodTypeTableModel woodTypeTableModel;
	public List<WoodType> listOfWoodType = new ArrayList<WoodType>();

	JTable tblWoodType;

	private static final long serialVersionUID = 1L;

	public WoodTypeListPanel() {
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblBreadcrumb = new JLabel("ERP > Konfigurasi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("Jenis Kayu");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.sn.woodtype.ui.WoodTypeCreatePanel");
			}
		});
		btnCreateNew.setBounds(950, 80, 100, 30);
		add(btnCreateNew);

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

		scrollPaneWoodType = new JScrollPane();
		scrollPaneWoodType.setBounds(50, 200, 1000, 300);
		add(scrollPaneWoodType);

		woodTypeTableModel = new WoodTypeTableModel(new ArrayList<WoodType>());
		tblWoodType = new JTable(woodTypeTableModel);
		tblWoodType.setFocusable(false);
		tblWoodType.setAutoCreateRowSorter(true);
		scrollPaneWoodType.setViewportView(tblWoodType);

		tblWoodType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 3)
						MainPanel.changePanel("module.sn.woodtype.ui.WoodTypeViewPanel", listOfWoodType.get(row));
				}
			}
		});

		try {
			listOfWoodType = new ArrayList<WoodType>();
			listOfWoodType = ServiceFactory.getWoodTypeBL().getAllWoodType();
			refreshTableWoodType();
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

	public void refreshTableWoodType() {
		try {
			tblWoodType.setModel(new WoodTypeTableModel(listOfWoodType));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfWoodType = new ArrayList<WoodType>();
			listOfWoodType = ServiceFactory.getWoodTypeBL().getAllWoodTypeBySimpleSearch(value);
			refreshTableWoodType();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for Wood Genus table
	 * 
	 * @author TSI
	 *
	 */
	class WoodTypeTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<WoodType> listOfWoodType;

		public WoodTypeTableModel(List<WoodType> listOfWoodType) {
			this.listOfWoodType = listOfWoodType;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfWoodType.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 4;
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
			WoodType p = listOfWoodType.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getId();
			case 1:
				return p.getWoodType();
			case 2:
				return p.getWoodGenus().getWoodGenus();
			case 3:
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
				return "Kode Jenis Kayu";
			case 1:
				return "Jenis Kayu";
			case 3:
				return "Nama Latin";
			case 2:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
