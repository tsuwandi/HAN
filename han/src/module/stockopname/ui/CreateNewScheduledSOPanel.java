package module.stockopname.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import main.component.NumberField;
import main.component.TextField;
import model.User;
import module.personalia.model.ImportFingerprint;
import module.production.model.Production;
import module.stockopname.model.ProductSO;
import module.stockopname.model.SetSOScheduled;
import module.stockopname.model.SetSoScheduledProduct;
import module.util.Bridging;
import module.util.Pagination;

public class CreateNewScheduledSOPanel extends JPanel implements Bridging{
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
	private JButton saveBtn;
	private JButton backBtn;
	
	private JScrollPane scrollPane;
	private JTable soProductTable;
	private CreateNewScheduledSOPanel parent;
	private Map<Integer, ProductSO> productMap;
	private SetSOScheduled setSoScheduled;
	
	public CreateNewScheduledSOPanel(){
		parent=this;
		createGUI();
		listener();
	}
	
	private void initData(){
		productMap = new HashMap<Integer, ProductSO>();
		setSoScheduled = new SetSOScheduled();
		
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
		
		soReccurenceCmb = new JComboBox<>();
		soReccurenceCmb.setBounds(190,140,150,20);
		add(soReccurenceCmb);
		
		soReccurenceErrorLbl = new JLabel();
		soReccurenceErrorLbl.setBounds(350,140,150,20);
		add(soReccurenceErrorLbl);
		
		soDayLbl = new JLabel("Hari");
		soDayLbl.setBounds(30,180,150,20);
		add(soDayLbl);
		
		soDayCmb = new JComboBox<>();
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
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(850,560,150,30);
		add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,560,150,30);
		add(backBtn);
		
		
	}
	
	private void listener(){
		productBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpProductSOSchedule pop = new PopUpProductSOSchedule(parent);
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
				pop.setModal(true);
			}
		});
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
			soNameField.setText(setSoScheduled.getSoName());
			soReccurenceCmb.setSelectedItem(setSoScheduled.getReccurence());
			if(setSoScheduled.getDay()!=null)soDayCmb.setSelectedItem(setSoScheduled.getDay());
			soDateField.setText(setSoScheduled.getDate()+"");
			if(setSoScheduled.getSetSoScheduledProducts().size()!=0){
				soProductTable.setModel(new SoScheduleTableModel(setSoScheduled.getSetSoScheduledProducts()));
			}
		}
		
	}
}
