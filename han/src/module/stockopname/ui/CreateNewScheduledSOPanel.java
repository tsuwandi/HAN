package module.stockopname.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
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
	private Map<Integer, SetSoScheduledProduct> productMap;
	private List<SetSoScheduledProduct> products;
	private SetSOScheduled setSoScheduled;
	private String [] days = {"-Pilih-","Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"};
	private String [] reccure = {"-Pilih-","Harian","Mingguan","Bulanan"};
	List<SetSoScheduledProduct> deletedProducts;
	
	boolean editMode=false;
	public CreateNewScheduledSOPanel(){
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
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(850,560,150,30);
		add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,560,150,30);
		add(backBtn);
		
		
	}
	
	public void save(){
		int error=0;
		
			if(soReccurenceCmb.getSelectedIndex()==0){
				soReccurenceErrorLbl.setText("<html><font color='red'>Perulangan harus dipilih !</font></html>");
				error++;
			}else{
				soReccurenceErrorLbl.setText("");
			}
			
			if(soReccurenceCmb.getSelectedIndex()==2){
				if(soDayCmb.getSelectedIndex()==0){
					soDayErrorLbl.setText("<html><font color='red'>Hari harus dipilih !</font></html>");
					error++;
				}else{
					soDayErrorLbl.setText("");
				}
			}
			if(soReccurenceCmb.getSelectedIndex()==3){
				if(soDateField.getText().equals("")){
					soDateErrorLbl.setText("<html><font color='red'>Tanggal harus diisi !</font></html>");
					error++;
				}else{
					soDateErrorLbl.setText("");
				}
			}
			if(soNameField.getText().equals("")){
				soNameErrorLbl.setText("<html><font color='red'>Nama harus diisi !</font></html>");
				error++;
			}else{
				soNameErrorLbl.setText("");
			}
			
			if(error==0){
				try {
					if(editMode){
						if(DialogBox.showEditChoice()==JOptionPane.YES_OPTION){
							setSoScheduled.setReccurence(soReccurenceCmb.getSelectedItem().toString());
							if(soReccurenceCmb.getSelectedIndex()==2)setSoScheduled.setDay(soDayCmb.getSelectedItem().toString());
							else setSoScheduled.setDay("");
							if(soReccurenceCmb.getSelectedIndex()==3)setSoScheduled.setDate(Integer.valueOf(soDateField.getText()));
							else setSoScheduled.setDate(0);
							setSoScheduled.setSoName(soNameField.getText());
							setSoScheduled.setSetSoScheduledProducts(products);
							Iterator<SetSoScheduledProduct> i = deletedProducts.iterator();
							while (i.hasNext()) {
								if(productMap.get(i.next().getProductID())!=null)i.remove();
							}
							setSoScheduled.setDeletedProducts(deletedProducts);
							ServiceFactory.getStockOpnameBL().updateSetSoSchedule(setSoScheduled);
							DialogBox.showEdit();
							MainPanel.changePanel("module.stockopname.ui.ListScheduledSOPanel");
						}
					}else{
						if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
							setSoScheduled.setReccurence(soReccurenceCmb.getSelectedItem().toString());
							if(soReccurenceCmb.getSelectedIndex()==2)setSoScheduled.setDay(soDayCmb.getSelectedItem().toString());
							if(soReccurenceCmb.getSelectedIndex()==3)setSoScheduled.setDate(Integer.valueOf(soDateField.getText()));
							setSoScheduled.setSoName(soNameField.getText());
							setSoScheduled.setSoType("Stock Opname Terjadwal");
							setSoScheduled.setSetSoScheduledProducts(products);
							ServiceFactory.getStockOpnameBL().saveSetSoSchedule(setSoScheduled);
							DialogBox.showInsert();
							MainPanel.changePanel("module.stockopname.ui.ListScheduledSOPanel");
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					DialogBox.showError(e.getMessage());
				}
				
			}
	
	
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
		productBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpProductSOSchedule pop = new PopUpProductSOSchedule(parent);
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
				pop.setModal(true);
			}
		});
		
		soProductTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(soProductTable.columnAtPoint(e.getPoint())==4){
					SetSoScheduledProduct so = products.get(soProductTable.getSelectedRow());
					productMap.remove(so.getProductID());
					products.remove(so);
					setTableSize();
					soProductTable.updateUI();
				}
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.stockopname.ui.ListScheduledSOPanel");
			}
		});
		
		soReccurenceCmb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(soReccurenceCmb.getSelectedIndex()==1){
					soDateField.setEnabled(false);
					soDateField.setText("");
					soDayCmb.setEnabled(false);
					soDayCmb.setSelectedIndex(0);
				}else if(soReccurenceCmb.getSelectedIndex()==2){
					soDayCmb.setEnabled(true);
					soDateField.setEnabled(false);
					soDateField.setText("");
				}else if(soReccurenceCmb.getSelectedIndex()==3){
					soDateField.setEnabled(true);
					soDayCmb.setEnabled(false);
					soDayCmb.setSelectedIndex(0);
				}else{
					soDateField.setEnabled(true);
					soDayCmb.setEnabled(true);
				}
				
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
			editMode=true;
			saveBtn.setText("Ubah");
			products = setSoScheduled.getSetSoScheduledProducts();
			for (SetSoScheduledProduct o : products) {
				o.setFlag(true);
				productMap.put(o.getProductID(), o);
			}
			deletedProducts = new ArrayList<>(setSoScheduled.getSetSoScheduledProducts());
			soNameField.setText(setSoScheduled.getSoName());
			soReccurenceCmb.setSelectedItem(setSoScheduled.getReccurence());
			if(setSoScheduled.getDay()!=null)soDayCmb.setSelectedItem(setSoScheduled.getDay());
			soDateField.setText(setSoScheduled.getDate()+"");
			if(setSoScheduled.getSetSoScheduledProducts().size()!=0){
				soProductTable.setModel(new SoScheduleTableModel(setSoScheduled.getSetSoScheduledProducts()));
				setTableSize();
				soProductTable.updateUI();
			}
		}
		
	}
}
