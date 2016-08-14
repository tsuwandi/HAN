package module.production.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import model.User;
import module.production.model.ProdRM;

public class PopUpSearchMaterial extends JDialog{
	
	private static final long serialVersionUID = 1L;
	JButton searchBtn;
	JButton addBtn;
	
	JTextField searchField;
	
	JTable materialTable;
	JScrollPane scrollPane;
	
	MaterialTableModel materialTableModel;
	List<ProdRM> prodRms;
	PopUpInputMaterial popUpInputMaterial;
	
	public PopUpSearchMaterial(JDialog parent){
		super(parent);
		createGUI(parent);
	}
	private void createGUI(JDialog parent){
		setLayout(null);
		setSize(700,400);
		popUpInputMaterial = (PopUpInputMaterial) parent;
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(555,20,100,30);
		add(searchBtn);
		
		searchField = new JTextField();
		searchField.setBounds(425,20,100,30);
		add(searchField);
		
		try {
			prodRms = ServiceFactory.getProductionBL().getSearchProdRM(popUpInputMaterial.getProdRms());
			materialTableModel = new MaterialTableModel(prodRms);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		materialTable = new JTable(materialTableModel);
		
		scrollPane = new JScrollPane(materialTable);
		scrollPane.setBounds(20,70,655,200);
		add(scrollPane);
		
		addBtn = new JButton("Tambah");
		addBtn.setBounds(555,300,100,30);
		add(addBtn);
		
		materialTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(prodRms.get(materialTable.getSelectedRow()).isFlag())
					prodRms.get(materialTable.getSelectedRow()).setFlag(false);
				else	
					prodRms.get(materialTable.getSelectedRow()).setFlag(true);
				materialTable.updateUI();
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterator<ProdRM> i = prodRms.iterator();
				while (i.hasNext()) {
				   if(i.next().isFlag()==false)i.remove();
				}
				popUpInputMaterial.updateTableFromSearch(prodRms);
				dispose();
			}
		});
	}
	
	
	
	
	private class MaterialTableModel extends AbstractTableModel{
		
		private static final long serialVersionUID = 1L;
		private List<ProdRM> prodRMs;
		    
		    public MaterialTableModel(List<ProdRM> prodRMs) {
		        this.prodRMs = prodRMs;
		    }
		    
		    /**
		     * Method to get row count
		     * @return int
		     */
		    public int getRowCount() {
		        return prodRMs.size();
		    }
		    
		    /**
		     * Method to get Column Count
		     */
		    public int getColumnCount() {
		        return 8;
		    }
		    
		    public Class<?> getColumnClass(int col) {
		    	switch(col){
		    	case 0 : 
		    		return Boolean.class;
		    	 default :
	                return String.class; 		
		    	}
		    }
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		    
		    /**
		     * Method to get selected value
		     * @param rowIndex rowIndex of selected table
		     * @param columnIndex columnIndex of selected table 
		     * @return ({@link User}) Object 
		     */
		    public Object getValueAt(int rowIndex, int columnIndex) {
		    	ProdRM p = prodRMs.get(rowIndex);
		        switch(columnIndex){
		        	case 0:
		        		return p.isFlag();
		        	case 1:
		        		return p.getDateDryOut();
		        	case 2:
		        		return p.getPalletCardCode();
		            case 3 : 
		                return p.getLength();
		            case 4 :
		                return p.getThick();
		            case 5 :
		                return p.getWidth();
		            case 6 :
		                return p.getLog();
		            case 7 :
		                return p.getVolume();
		            default :
		                return "";
		        }
		    }

		    /**
		     * Method to getColumnName
		     * @param column columnIndex
		     * @return String column name
		     */
		    public String getColumnName(int column) {
		        switch(column){
			        case 0 : 
		                return "";
			        case 1 : 
		                return "Tanggal Pengeluaran";
		            case 2 : 
		                return "Kode Kartu Pallet";
		            case 3 :
		                return "Panjang";
		            case 4 :
		                return "Tebal";
		            case 5 :
		            	return "Lebar";
		            case 6 :
		                return "Jumlah";
		            case 7 :
		                return "Volume";
		            default :
		                return "";
		        }
		    }

		}
	
}
