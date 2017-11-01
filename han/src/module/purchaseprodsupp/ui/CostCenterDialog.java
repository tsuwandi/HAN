package module.purchaseprodsupp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.sn.costcenter.model.CostCenter;
import module.util.JTextFieldLimit;

public class CostCenterDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(CostCenterDialog.class);

	JPanel panel;

	JLabel lblCostCenter;

	JTextField txtCostCenter;

	JButton btnSearch;
	JButton btnSave;
	JButton btnClose;
	
	List<CostCenter> costCenters = new ArrayList<CostCenter>();
	
	JScrollPane scrollPaneCostCenter;

	private CostCenterTableModel costCenterTableModel;
	
	JTable tblCostCenter;

	private PurchaseProdSuppCreatePanel ppsCreatePanel;
	private PurchaseProdSuppEditPanel ppsEditPanel;
	
	private CostCenter addCostCenter = null;
	
	public CostCenterDialog(PurchaseProdSuppEditPanel ppsEditPanel) {
		this.ppsEditPanel = ppsEditPanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 310);
		getContentPane().setLayout(null);

		load();
	}
	
	public CostCenterDialog(PurchaseProdSuppCreatePanel ppsCreatePanel) {
		this.ppsCreatePanel = ppsCreatePanel;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 310);
		getContentPane().setLayout(null);

		load();
	}
	
	public void load() {
		lblCostCenter = new JLabel("Cost Center");
		lblCostCenter.setBounds(25, 15, 150, 25);
		getContentPane().add(lblCostCenter);

		txtCostCenter = new JTextField();
		txtCostCenter.setBounds(120, 15, 150, 25);
		txtCostCenter.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtCostCenter);


		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdvancedSearch();
			}
		});
		btnSearch.setBounds(280, 15, 100, 25);
		getContentPane().add(btnSearch);
		
		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});
		btnSave.setBounds(349, 225, 100, 25);
		getContentPane().add(btnSave);
		
		btnClose = new JButton("Tutup");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		btnClose.setBounds(459, 225, 100, 25);
		getContentPane().add(btnClose);
		
		scrollPaneCostCenter = new JScrollPane();
		scrollPaneCostCenter.setBounds(25, 65, 535, 150);
		add(scrollPaneCostCenter);

		costCenterTableModel = new CostCenterTableModel(new ArrayList<CostCenter>());
		tblCostCenter = new JTable(costCenterTableModel);
		tblCostCenter.setFocusable(false);
		tblCostCenter.setAutoCreateRowSorter(true);
		scrollPaneCostCenter.setViewportView(tblCostCenter);

		tblCostCenter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				addCostCenter = new CostCenter();
				addCostCenter = costCenters.get(row);
			}
		});
		
		doAdvancedSearch();
	}

	public void doAdvancedSearch() {
		try {
			CostCenter costCenter = new CostCenter();
			costCenter.setCostCenter(txtCostCenter.getText());
			
			costCenters = ServiceFactory.getPurchaseProductSuppBL().getAllCostCenterByAdvancedSearch(costCenter);
			refreshTableCostCenter(costCenters);
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}
	
	public void doSave() {
		if(addCostCenter != null) {
			if(ppsCreatePanel != null) {
				ppsCreatePanel.txtCostCenter.setText(addCostCenter.getCostCenter());;
				ppsCreatePanel.setCostCenterId(addCostCenter.getId());
			}
			
			if(ppsEditPanel != null) {
				ppsEditPanel.txtCostCenter.setText(addCostCenter.getCostCenter());;
				ppsEditPanel.setCostCenterId(addCostCenter.getId());
			}
		}
		closeDialog();
	}

	protected void closeDialog() {
		dispose();
	}
	
	public void refreshTableCostCenter(List<CostCenter> costCenters) {
		try {
			tblCostCenter.setModel(new CostCenterTableModel(costCenters));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for CostCenter table
	 * 
	 * @author TSI
	 *
	 */
	class CostCenterTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<CostCenter> listOfCostCenter;

		public CostCenterTableModel(List<CostCenter> listOfCostCenter) {
			this.listOfCostCenter = listOfCostCenter;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfCostCenter.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 2;
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
		 * @return ({@link CostCenterAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			CostCenter p = listOfCostCenter.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return rowIndex + 1;
			case 1:
				return p.getCostCenter();
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
				return "No. ";
			case 1:
				return "Cost Center";
			default:
				return "";
			}
		}
	}
}
