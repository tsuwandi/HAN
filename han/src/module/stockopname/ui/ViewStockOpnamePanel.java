package module.stockopname.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;
import module.stockopname.model.StockOpname;
import module.stockopname.model.StockOpnameProduct;
import module.stockopname.ui.CreateNewScheduledSOPanel.SoScheduleTableModel;
import module.util.Bridging;
import module.util.Pagination;

public class ViewStockOpnamePanel extends JPanel implements Bridging {
	private JLabel soNameLbl;
	private JLabel soDateLbl;
	private JLabel soTypeLbl;
	private JLabel soNameErrorLbl;
	
	private TextField soNameField;
	private TextField soTypeField;
	private JDateChooser soDateChooser;
	
	private JButton productBtn;
	private JButton editBtn;
	private JButton backBtn;
	
	private JScrollPane soScrollPane;
	private JTable soTable;
	private Map<Integer, StockOpnameProduct> productMap;
	private StockOpname stockOpname;
	private List<StockOpnameProduct> products;
	private ViewStockOpnamePanel parent;
	boolean editMode=false;
	boolean scheduled=false;
	
	public ViewStockOpnamePanel(){
		parent = this;
		createGUI();
		setData();
		listener();
	}
	
	private void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Stock Opname Manual");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("View Stock Opname");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		soNameLbl = new JLabel("Stock Opname Name");
		soNameLbl.setBounds(30,90,150,30);
		add(soNameLbl);
		
		soNameField = new TextField();
		soNameField.setBounds(190,90,150,30);
		add(soNameField);
		
		soNameErrorLbl = new JLabel();
		soNameErrorLbl.setBounds(350, 90, 150, 30);
		add(soNameErrorLbl);
		
		
		soDateLbl = new JLabel("Tanggal Stock Opname");
		soDateLbl.setBounds(30,130,150,30);
		add(soDateLbl);
		
		soDateChooser = new JDateChooser();
		soDateChooser.setBounds(190,130,150,30);
		soDateChooser.setDate(new Date());
		soDateChooser.setEnabled(false);
		soDateChooser.getDateEditor().setEnabled(false);
		add(soDateChooser);
		
		soTypeLbl = new JLabel("Tipe");
		soTypeLbl.setBounds(30,170,150,30);
		add(soTypeLbl);
		
		soTypeField = new TextField();
		soTypeField.setBounds(190,170,150,30);
		soTypeField.setEnabled(false);
		add(soTypeField);
		
		productBtn = new JButton("Pilih Produk");
		productBtn.setBounds(30,210,150,30);
		add(productBtn);
		
		soTable = new JTable(new SODetailTableModel(new ArrayList<StockOpnameProduct>()));
		soTable.setRowSelectionAllowed(false);
		soScrollPane = new JScrollPane(soTable);
		soScrollPane.setBounds(30,250,1100,300);
		add(soScrollPane);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,560,150,30);
		add(backBtn);
		
		editBtn = new JButton("Ubah");
		editBtn.setBounds(990,560,150,30);
		add(editBtn);
		
		
	}
	
	private void setData(){
		stockOpname = new StockOpname();
		productMap = new HashMap<>();
		products = new ArrayList<>();
		setTableSize();
		soTypeField.setText("Stock Opname Manual");
	}
	
	private void listener(){
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.stockopname.ui.CreateNewStockOpnamePanel",stockOpname);
				
			}
		});
	}
	
	public Map<Integer, StockOpnameProduct> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Integer, StockOpnameProduct> productMap) {
		this.productMap = productMap;
		products.clear();
		products.addAll(productMap.values());
		soTable.setModel(new SODetailTableModel(products));
		setTableSize();
		soTable.updateUI();
	
	}
	
	public void setTableSize(){
		soTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		soTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = soTable.getColumnModel().getColumn(0);
		TableColumn column2 = soTable.getColumnModel().getColumn(1);
		TableColumn column3 = soTable.getColumnModel().getColumn(2);
		TableColumn column4 = soTable.getColumnModel().getColumn(3);
		TableColumn column5 = soTable.getColumnModel().getColumn(4);
		
		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);

		column2.setPreferredWidth(200);
		column2.setMinWidth(200);
		column2.setMaxWidth(200);

		column3.setPreferredWidth(200);
		column3.setMinWidth(200);
		column3.setMaxWidth(200);

		column4.setPreferredWidth(500);
		column4.setMinWidth(300);
		column4.setMaxWidth(300);
		
		column5.setPreferredWidth(100);
		column5.setMinWidth(100);
		column5.setMaxWidth(100);

		TableColumn tc= soTable.getColumnModel().getColumn(7);
		tc.setCellRenderer(new MyRenderer());
		JTextField textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				soTable.setValueAt(Double.valueOf(textField.getText()), soTable.getSelectedRow(), 7);
			}
		});
		tc.setCellEditor(new DefaultCellEditor(textField));

	}

	public class SODetailTableModel extends AbstractTableModel implements Pagination {
		private List<StockOpnameProduct> soProducts;
		
		

		public SODetailTableModel(List<StockOpnameProduct> stockOpnameProducts) {
			this.soProducts = stockOpnameProducts;
		}

		/**
		 * Method to get row count
		 * @return int
		 */
		public int getRowCount() {
			return soProducts.size();
		}


		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 13;
		}

		/**
		 * Method to get selected value
		 * @param rowIndex rowIndex of selected table
		 * @param columnIndex columnIndex of selected table 
		 * @return ({@link User}) Object 
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			StockOpnameProduct p = soProducts.get(rowIndex);
			switch(columnIndex){
			case 0 :
				return p.getId();
			case 1 : 
				return p.getProductCategory();
			case 2 :
				return p.getProductCode();
			case 3 :
				return p.getProductName();
			case 4 :
				return p.getLocation();
			case 5 :
				return p.getUom();
			case 6 :
				return p.getQtySystem();
			case 7 :
				return p.getQtyActual();
			case 8 :
				return p.getSelisihQty();
			case 9 :
				return p.getValueSystem();
			case 10 :
				return p.getValueActual();
			case 11 :
				return p.getSelisihValue();
			case 12 :
				return "Hapus";
			default :
				return "";
			}
		}
		public boolean isCellEditable(int row, int column) {
			return false;
		}


		/**
		 * Method to getColumnName
		 * @param column columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch(column){
			case 0 : 
				return "ID";
			case 1 : 
				return "Kategori Produk";
			case 2 :
				return "Kode Produk";
			case 3 :
				return "Nama Produk";
			case 4 :
				return "Lokasi";
			case 5 :
				return "Satuan";
			case 6 :
				return "Qty(System)";
			case 7 :
				return "Qty(Fisik)";
			case 8 :
				return "Selisih Qty";
			case 9 :
				return "Nilai(System)";
			case 10 :
				return "Nilai(Fisik)";
			case 11 :
				return "Selisih Nilai";
			case 12 :
				return "Tindakan";
			default :
				return "";
			}
		}

		@Override
		public <T> void setList(List<T> list) {
			soProducts = (List<StockOpnameProduct>) list;
		}
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			soProducts.get(rowIndex).setQtyActual(Double.valueOf((String)aValue));
			fireTableCellUpdated(rowIndex, columnIndex);
		}

	}
	
	class MyRenderer extends JTextField implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		      boolean hasFocus, int row, int column) {
		    if (value != null)
		      this.setText(value.toString());
		    return this;
		}
	
		
	}

	@Override
	public void invokeObjects(Object... objects) {
		if(objects.length!=0)stockOpname = (StockOpname)objects[0];
		if(stockOpname!=null){
			if(stockOpname.getSoType().equals("Stock Opname Terjadwal")){
				scheduled=true;
				if(stockOpname.getId()!=0)editMode=true;
			}else{
				editMode=true;
				soNameField.setText(stockOpname.getSoName());
				soDateChooser.setDate(stockOpname.getSoDate());
				soTypeField.setText(stockOpname.getSoType());
				soNameField.setEnabled(false);
				products = stockOpname.getStockOpnameProduct();
				soTable.setModel(new SODetailTableModel(products));
				setTableSize();
				soTable.updateUI();
				productBtn.setEnabled(false);
			}
		}
		
	}
	
}
