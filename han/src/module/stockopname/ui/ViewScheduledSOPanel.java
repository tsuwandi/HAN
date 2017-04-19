package module.stockopname.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.personalia.model.ImportFingerprint;
import module.production.model.Production;
import module.stockopname.model.ProductSO;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;
import module.util.Bridging;
import module.util.Pagination;

public class ViewScheduledSOPanel extends JPanel implements Bridging{
	private JLabel soNameLbl;
	private JLabel soReccurenceLbl;
	private JLabel soDayLbl;
	private JLabel soDateLbl;
	
	private JLabel soNameErrorLbl;
	private JLabel soReccurenceErrorLbl;
	private JLabel soDayErrorLbl;
	private JLabel soDateErrorLbl;
	
	private TextField soNameField;
	private JComboBox<String> soReccurenceCmb;
	private JComboBox<String> soDayCmb;
	private NumberField soDateField;
	
	private JButton productBtn;
	private JButton editBtn;
	private JButton backBtn;
	
	private JScrollPane scrollPane;
	private JTable soProductTable;
	private ViewScheduledSOPanel parent;
	private Map<Integer, SetSoScheduledProduct> productMap;
	private List<SetSoScheduledProduct> products;
	private SetSOScheduled setSoScheduled;
	private String [] days = {"-Pilih-","Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"};
	private String [] reccure = {"-Pilih-","Harian","Mingguan","Bulanan"};
	
	public ViewScheduledSOPanel(){
		parent=this;
		createGUI();
		initData();
		listener();
	}
	
	private void initData(){
		productMap = new HashMap<Integer, SetSoScheduledProduct>();
		products = new ArrayList<>();
		setSoScheduled = new SetSOScheduled();	
		setTableSize();
		
	}
	private void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Stock Opname Terjadwal");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("Stock Opname Terjadwal");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		soNameLbl = new JLabel("Nama Stock Opname");
		soNameLbl.setBounds(30,100,150,20);
		add(soNameLbl);
		
		soNameField = new TextField();
		soNameField.setBounds(190, 100, 150, 20);
		add(soNameField);
		
		soNameErrorLbl = new JLabel();
		soNameErrorLbl.setBounds(350,100,150,20);
		add(soNameErrorLbl);
		
		soReccurenceLbl = new JLabel("Perulangan");
		soReccurenceLbl.setBounds(30,140,150,20);
		add(soReccurenceLbl);
		
		soReccurenceCmb = new JComboBox<>(reccure);
		soReccurenceCmb.setBounds(190,140,150,20);
		add(soReccurenceCmb);
		
		soReccurenceErrorLbl = new JLabel();
		soReccurenceErrorLbl.setBounds(350,140,150,20);
		add(soReccurenceErrorLbl);
		
		soDayLbl = new JLabel("Hari");
		soDayLbl.setBounds(30,180,150,20);
		add(soDayLbl);
		
		soDayCmb = new JComboBox<>(days);
		soDayCmb.setBounds(190,180,150,20);
		add(soDayCmb);
		
		soDayErrorLbl = new JLabel();
		soDayErrorLbl.setBounds(350, 180, 150, 20);
		add(soDayErrorLbl);
		
		soDateLbl = new JLabel("Tanggal");
		soDateLbl.setBounds(30,220,150,20);
		add(soDateLbl);
		
		soDateField = new NumberField(2);
		soDateField.setBounds(190,220,150,20);
		add(soDateField);
		
		soDateErrorLbl = new JLabel();
		soDateErrorLbl.setBounds(350, 220, 150, 20);
		add(soDateErrorLbl);
		
		productBtn = new JButton("Pilih Produk");
		productBtn.setBounds(30,260,150,30);
		add(productBtn);
		
		soProductTable = new JTable(new SoScheduleTableModel(new ArrayList<SetSoScheduledProduct>()));
		
		scrollPane = new JScrollPane(soProductTable);
		scrollPane.setBounds(30,300,1000,250);
		add(scrollPane);
		
		editBtn = new JButton("Ubah");
		editBtn.setBounds(850,560,150,30);
		add(editBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,560,150,30);
		add(backBtn);
		
		
	}
	
	
	public Map<Integer, SetSoScheduledProduct> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Integer, SetSoScheduledProduct> productMap) {
		this.productMap = productMap;
		products.clear();
		products.addAll(productMap.values());
		soProductTable.setModel(new SoScheduleTableModel(products));
		setTableSize();
		soProductTable.updateUI();
		
	}

	private void listener(){
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.stockopname.ui.CreateNewScheduledSOPanel",setSoScheduled);
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.stockopname.ui.ListScheduledSOPanel");
				
			}
		});
	}
	public void setTableSize(){
		soProductTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		soProductTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = soProductTable.getColumnModel().getColumn(0);
		TableColumn column2 = soProductTable.getColumnModel().getColumn(1);
		TableColumn column3 = soProductTable.getColumnModel().getColumn(2);
		TableColumn column4 = soProductTable.getColumnModel().getColumn(3);
		TableColumn column5 = soProductTable.getColumnModel().getColumn(4);


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
		column4.setMinWidth(500);
		column4.setMaxWidth(500);
		
		column5.setPreferredWidth(100);
		column5.setMinWidth(100);
		column5.setMaxWidth(100);


	}
	
	
	public class SoScheduleTableModel extends AbstractTableModel implements Pagination {
		private List<SetSoScheduledProduct> setSoScheduledProducts;

		public SoScheduleTableModel(List<SetSoScheduledProduct> setSoScheduledProducts) {
			this.setSoScheduledProducts = setSoScheduledProducts;
		}

		/**
		 * Method to get row count
		 * @return int
		 */
		public int getRowCount() {
			return setSoScheduledProducts.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 5;
		}

		/**
		 * Method to get selected value
		 * @param rowIndex rowIndex of selected table
		 * @param columnIndex columnIndex of selected table 
		 * @return ({@link User}) Object 
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SetSoScheduledProduct p = setSoScheduledProducts.get(rowIndex);
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
				return "Tindakan";
			default :
				return "";
			}
		}

		@Override
		public <T> void setList(List<T> list) {
			setSoScheduledProducts = (List<SetSoScheduledProduct>) list;
		}

	}



	@Override
	public void invokeObjects(Object... objects) {
		if(objects.length!=0)setSoScheduled = (SetSOScheduled)objects[0];
		if(setSoScheduled!=null){
			products = setSoScheduled.getSetSoScheduledProducts();
			for (SetSoScheduledProduct o : products) {
				productMap.put(o.getId(), o);
			}
			soNameField.setText(setSoScheduled.getSoName());
			soReccurenceCmb.setSelectedItem(setSoScheduled.getReccurence());
			if(setSoScheduled.getDay()!=null)soDayCmb.setSelectedItem(setSoScheduled.getDay());
			soDateField.setText(setSoScheduled.getDate()+"");
			if(setSoScheduled.getSetSoScheduledProducts().size()!=0){
				soProductTable.setModel(new SoScheduleTableModel(setSoScheduled.getSetSoScheduledProducts()));
				setTableSize();
				soProductTable.updateUI();
			}
			
			soNameField.setEnabled(false);
			soReccurenceCmb.setEnabled(false);
			soDayCmb.setEnabled(false);
			soDateField.setEnabled(false);
			soProductTable.setEnabled(false);
			productBtn.setEnabled(false);
		}
		
	}
}
