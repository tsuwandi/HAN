package module.sn.woodgenus.ui;

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
import module.sn.woodgenus.model.WoodGenus;

public class WoodGenusListPanel extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(WoodGenusListPanel.class);

	JButton btnCreateNew;
	JButton btnSearch;

	JTextField txtSearch;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneWoodGenus;

	private WoodGenusTableModel woodGenusTableModel;
	public List<WoodGenus> listOfWoodGenus = new ArrayList<WoodGenus>();

	JTable tblWoodGenus;

	private static final long serialVersionUID = 1L;

	public WoodGenusListPanel() {
		setLayout(null);

		setPreferredSize(new Dimension(1024, 768));

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblBreadcrumb = new JLabel("ERP > Konfigurasi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("Nama Latin Kayu");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		btnCreateNew = new JButton("Buat Baru");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.sn.woodgenus.ui.WoodGenusCreatePanel");
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

		scrollPaneWoodGenus = new JScrollPane();
		scrollPaneWoodGenus.setBounds(50, 200, 1000, 300);
		add(scrollPaneWoodGenus);

		woodGenusTableModel = new WoodGenusTableModel(new ArrayList<WoodGenus>());
		tblWoodGenus = new JTable(woodGenusTableModel);
		tblWoodGenus.setFocusable(false);
		tblWoodGenus.setAutoCreateRowSorter(true);
		scrollPaneWoodGenus.setViewportView(tblWoodGenus);

		tblWoodGenus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 2)
						MainPanel.changePanel("module.sn.woodgenus.ui.WoodGenusViewPanel", listOfWoodGenus.get(row));
				}
			}
		});

		try {
			listOfWoodGenus = new ArrayList<WoodGenus>();
			listOfWoodGenus = ServiceFactory.getWoodGenusBL().getAllWoodGenus();
			refreshTableWoodGenus();
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

	public void refreshTableWoodGenus() {
		try {
			tblWoodGenus.setModel(new WoodGenusTableModel(listOfWoodGenus));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void doSearch(String value) {
		try {
			listOfWoodGenus = new ArrayList<WoodGenus>();
			listOfWoodGenus = ServiceFactory.getWoodGenusBL().getAllWoodGenusBySimpleSearch(value);
			refreshTableWoodGenus();
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
	class WoodGenusTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<WoodGenus> listOfWoodGenus;

		public WoodGenusTableModel(List<WoodGenus> listOfWoodGenus) {
			this.listOfWoodGenus = listOfWoodGenus;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfWoodGenus.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
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
			WoodGenus p = listOfWoodGenus.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getId();
			case 1:
				return p.getWoodGenus();
			case 2:
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
				return "Kode Nama Latin Kayu";
			case 1:
				return "Nama Latin";
			case 2:
				return "Tindakan";
			default:
				return "";
			}
		}
	}
}
