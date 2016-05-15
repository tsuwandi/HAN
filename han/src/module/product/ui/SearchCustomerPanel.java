package module.product.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.product.model.Product;
import module.product.ui.SearchSupplierPanel.SupplierTableModel;
import module.supplier.model.Supplier;

public class SearchCustomerPanel extends JDialog {

	public JTable customerTable;
	public JTextField searchField;
	public JButton searchBtn;
	public JButton addBtn;
	
	public List<Product> customers = null;
	public CustomerTableModel customerTableModel;
	private JScrollPane customerScroll;
	/**
	 * Create the dialog.
	 */
	public SearchCustomerPanel() {
		setLayout(null);
		setTitle("Customer");
		setSize(400, 275);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		searchField = new JTextField();
		searchField.setBounds(120, 20, 150, 25);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(270, 20, 100, 25);
		
//		try{
//			customers = ServiceFactory.getSupplierBL().getAllSupplier();
//		}catch(SQLException e1){
//			e1.printStackTrace();
//		}
		
		customerTableModel = new CustomerTableModel(customers);
		customerTable = new JTable(customerTableModel);
		customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		customerTable.getTableHeader().setReorderingAllowed(false);
		customerTable.getTableHeader().setResizingAllowed(false);
		customerTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		customerTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		customerTable.getColumnModel().getColumn(2).setPreferredWidth(160);
		customerScroll= new JScrollPane(customerTable);
		customerScroll.setBounds(20, 50, 400, 75);
		customerScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				MainPanel.glassPane.setVisible(false);
				setVisible(false);
			}
			public void windowClosing(WindowEvent e){
				MainPanel.glassPane.setVisible(false);
				setVisible(false);
			}
		});
		
		addBtn = new JButton("Add");
		addBtn.setBounds(300, 200, 75, 25);
		
		add(searchField);
		add(searchBtn);
		add(customerScroll);
		add(addBtn);
	}
	
	class CustomerTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private List<Product> customers;
		int seq=0;

		public CustomerTableModel(List<Product> customers) {
			this.customers = customers;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return 0;
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link Supplier}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Product p = customers.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return seq = rowIndex+1;
			case 1:
				return p.getProductId();
			case 2:
				return p.getProductName();
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
				return " ";
			case 1:
				return "Kode Customer";
			case 2:
				return "Nama Customer";
			default:
				return "";
			}
		}
		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}
		
		@Override
        public Class getColumnClass(int column) {
            switch (column) {
                case 0:
                    return Boolean.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                default:
                    return String.class;
            }
        }

	}

}
