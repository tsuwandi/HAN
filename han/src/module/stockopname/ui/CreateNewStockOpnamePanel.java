package module.stockopname.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import main.component.TextField;
import model.User;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.StockOpnameProduct;
import module.util.Pagination;

public class CreateNewStockOpnamePanel extends JPanel {
	private JLabel soNameLbl;
	private JLabel soDateLbl;
	private JLabel soTypeLbl;
	
	private TextField soNameField;
	private TextField soTypeField;
	private JDateChooser soDateChooser;
	
	private JButton productBtn;
	private JButton saveBtn;
	private JButton backBtn;
	private JButton draftBtn;
	
	private SODetailTableModel soDetailTableModel;
	private JScrollPane soScrollPane;
	private JTable soTable;
	
	
	public CreateNewStockOpnamePanel(){
		createGUI();
	}
	
	private void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Stock Opname Manual");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Stock Opname");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		soNameLbl = new JLabel("Stock Opname Name");
		soNameLbl.setBounds(30,90,150,30);
		add(soNameLbl);
		
		soNameField = new TextField();
		soNameField.setBounds(190,90,150,30);
		add(soNameField);
		
		
		soDateLbl = new JLabel("Tanggal Stock Opname");
		soDateLbl.setBounds(30,130,150,30);
		add(soDateLbl);
		
		soDateChooser = new JDateChooser();
		soDateChooser.setBounds(190,130,150,30);
		add(soDateChooser);
		
		soTypeLbl = new JLabel("Tipe");
		soTypeLbl.setBounds(30,170,150,30);
		add(soTypeLbl);
		
		soTypeField = new TextField();
		soTypeField.setBounds(190,170,150,30);
		add(soTypeField);
		
		productBtn = new JButton("Pilih Produk");
		productBtn.setBounds(30,210,150,30);
		add(productBtn);
		
		soTable = new JTable(new SODetailTableModel(new ArrayList<StockOpnameProduct>()));
		
		soScrollPane = new JScrollPane(soTable);
		soScrollPane.setBounds(30,250,1100,300);
		add(soScrollPane);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,560,150,30);
		add(backBtn);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(830,560,150,30);
		add(saveBtn);
		
		draftBtn = new JButton("Simpan Draft");
		draftBtn.setBounds(990,560,150,30);
		add(draftBtn);
		
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

	}
	
}
