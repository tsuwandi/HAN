package module.production.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import main.component.ComboBox;
import main.component.NumberField;
import model.User;
import module.production.model.Machine;
import module.production.model.ProductionResultDetail;

public class PopUpProductionResult extends JDialog{
	private JLabel titleLbl;
	private JLabel dateLbl;
	private JLabel machineLbl;
	private JLabel pressNoLbl;
	private JLabel startTimeLbl;
	private JLabel klemLbl;
	private JLabel klemGradeALbl;
	private JLabel klemGradeBLbl;
	private JLabel protolLbl;
	private JLabel protolGradeALbl;
	private JLabel protolGradeBLbl;
	private JLabel goodResultLbl;
	private JLabel goodResultGradeALbl;
	private JLabel goodResultGradeBLbl;
	private JLabel totalOutputLbl;
	private JLabel totalKlemLbl;
	private JLabel totalProtolLbl;
	private JLabel totalGoodResultALbl;
	private JLabel totalGoodResultBLbl;
	private JLabel totalAllGoodResultLbl;
	
	private JLabel timeSeparator;
	
	private JDateChooser resultDateChooser;
	private ComboBox<Machine> machineCmb;
	
	private NumberField pressNoField;
	private NumberField hourField;
	private NumberField minuteField;
	private NumberField klemGradeAField;
	private NumberField klemGradeBField;
	private NumberField protolGradeAField;
	private NumberField protolGradeBField;
	private NumberField goodResultGradeAField;
	private NumberField goodResultGradeBField;
	private NumberField totalOutputField;
	private NumberField totalKlemField;
	private NumberField totalProtolField;
	private NumberField totalGoodResultAField;
	private NumberField totalGoodResultBField;
	private NumberField totalAllGoodResultField;
	
	private JButton addBtn;
	private JButton saveBtn;

	private JTable productionResultTable;
	private ProductionResultTableModel productionResultTableModel;
	private JScrollPane scrollPane;
	
	private JScrollPane containerScrollPane;
	private JPanel containerPnl;
	private JPanel borderPanel;
	
	public PopUpProductionResult(){
		setLayout(null);
		setTitle("Input Hasil Produksi");
		setSize(1000, 750);
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1000,1100));	
		containerPnl.setLayout(null);
		
		containerScrollPane = new JScrollPane(containerPnl);
		containerScrollPane.setBounds(0,0,1000,750);
		add(containerScrollPane);
		
		titleLbl = new JLabel("Input Hasil Produksi");
		titleLbl.setBounds(50,10,200,30);
		titleLbl.setFont(new Font("Arial", 1, 18));
		containerPnl.add(titleLbl);
		
		//TODO date Area
		dateLbl = new JLabel("Tanggal");
		dateLbl.setBounds(50,50,150,20);
		containerPnl.add(dateLbl);
		
		resultDateChooser = new JDateChooser();
		resultDateChooser.setBounds(240,50,150,20);
		containerPnl.add(resultDateChooser);
		
		//TODO machine Area
		machineLbl = new JLabel("No Mesin");
		machineLbl.setBounds(50,90,150,20);
		containerPnl.add(machineLbl);
		
		machineCmb = new ComboBox<>();
		machineCmb.setBounds(240,90,150,20);
		containerPnl.add(machineCmb);
		
		//TODO borderPnl Area
		borderPanel = new JPanel();
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		borderPanel.setBounds(40,130,900,450);
		borderPanel.setLayout(null);
		containerPnl.add(borderPanel);
		
		//TODO pressNo Area
		pressNoLbl = new JLabel("Pengepresan Ke");
		pressNoLbl.setBounds(10,10,150,20);
		borderPanel.add(pressNoLbl);
		
		pressNoField = new NumberField(5);
		pressNoField.setBounds(200,10,150,20);
		borderPanel.add(pressNoField);
		
		//TODO klem Area
		klemLbl = new JLabel("Repair (Klem)");
		klemLbl.setBounds(10,50,150,20);
		borderPanel.add(klemLbl);
		
		klemGradeALbl = new JLabel("Jumlah Grade A");
		klemGradeALbl.setBounds(10,90,150,20);
		borderPanel.add(klemGradeALbl);
		
		klemGradeAField = new NumberField(5);
		klemGradeAField.setBounds(200,90,150,20);
		borderPanel.add(klemGradeAField);
		
		klemGradeBLbl = new JLabel("Jumlah Grade B");
		klemGradeBLbl.setBounds(10,130,150,20);
		borderPanel.add(klemGradeBLbl);
		
		klemGradeBField = new NumberField(5);
		klemGradeBField.setBounds(200,130,150,20);
		borderPanel.add(klemGradeBField);
		
		//TODO protol Area
		protolLbl = new JLabel("Repair (protol)");
		protolLbl.setBounds(10,170,150,20);
		borderPanel.add(protolLbl);
		
		protolGradeALbl = new JLabel("Jumlah Grade A");
		protolGradeALbl.setBounds(10,210,150,20);
		borderPanel.add(protolGradeALbl);
		
		protolGradeAField = new NumberField(5);
		protolGradeAField.setBounds(200,210,150,20);
		borderPanel.add(protolGradeAField);
		
		protolGradeBLbl = new JLabel("Jumlah Grade B");
		protolGradeBLbl.setBounds(10,250,150,20);
		borderPanel.add(protolGradeBLbl);
		
		protolGradeBField = new NumberField(5);
		protolGradeBField.setBounds(200,250,150,20);
		borderPanel.add(protolGradeBField);
		
		//TODO goodResult Area
		goodResultLbl = new JLabel("Repair (goodResult)");
		goodResultLbl.setBounds(10,290,150,20);
		borderPanel.add(goodResultLbl);
		
		goodResultGradeALbl = new JLabel("Jumlah Grade A");
		goodResultGradeALbl.setBounds(10,330,150,20);
		borderPanel.add(goodResultGradeALbl);
		
		goodResultGradeAField = new NumberField(5);
		goodResultGradeAField.setBounds(200,330,150,20);
		borderPanel.add(goodResultGradeAField);
		
		goodResultGradeBLbl = new JLabel("Jumlah Grade B");
		goodResultGradeBLbl.setBounds(10,370,150,20);
		borderPanel.add(goodResultGradeBLbl);
		
		goodResultGradeBField = new NumberField(5);
		goodResultGradeBField.setBounds(200,370,150,20);
		borderPanel.add(goodResultGradeBField);
		
		//TODO add BTN Area
		addBtn = new JButton("Tambah");
		addBtn.setBounds(740,410,150,30);
		borderPanel.add(addBtn);
		
		
		//Table Area
		productionResultTableModel = new ProductionResultTableModel(new ArrayList<ProductionResultDetail>());
		productionResultTable = new JTable(productionResultTableModel);
		
		scrollPane = new JScrollPane(productionResultTable);
		scrollPane.setBounds(40,600,900,150);
		containerPnl.add(scrollPane);
		
		//TODO totalOutput Area
		totalOutputLbl = new JLabel("Total Output");
		totalOutputLbl.setBounds(50,770,150,20);
		containerPnl.add(totalOutputLbl);
		
		totalOutputField = new NumberField(5);
		totalOutputField.setBounds(240,770,150,20);
		containerPnl.add(totalOutputField);
		
		//TODO totalKlem Area
		totalKlemLbl = new JLabel("Total Repair(Klem)");
		totalKlemLbl.setBounds(50,810,150,20);
		containerPnl.add(totalKlemLbl);
		
		totalKlemField = new NumberField(5);
		totalKlemField.setBounds(240,810,150,20);
		containerPnl.add(totalKlemField);
		
		//TODO totalProtol Area
		totalProtolLbl = new JLabel("Total Repair(Protol)");
		totalProtolLbl.setBounds(50,850,150,20);
		containerPnl.add(totalProtolLbl);
		
		totalProtolField = new NumberField(5);
		totalProtolField.setBounds(240,850,150,20);
		containerPnl.add(totalProtolField);
		
		//TODO totalGoodResultA Area
		totalGoodResultALbl = new JLabel("Total Hasil Produk Baik Grade(A)");
		totalGoodResultALbl.setBounds(50,900,200,20);
		containerPnl.add(totalGoodResultALbl);
		
		totalGoodResultAField = new NumberField(5);
		totalGoodResultAField.setBounds(240,900,150,20);
		containerPnl.add(totalGoodResultAField);
		
		//TODO totalGoodResultB Area
		totalGoodResultBLbl = new JLabel("Total Hasil Produk Baik Grade(B)");
		totalGoodResultBLbl.setBounds(50,940,200,20);
		containerPnl.add(totalGoodResultBLbl);
		
		totalGoodResultBField = new NumberField(5);
		totalGoodResultBField.setBounds(240,940,150,20);
		containerPnl.add(totalGoodResultBField);
		
		//TODO totalAllGoodResult Area
		totalAllGoodResultLbl = new JLabel("Total Hasil Produk Baik");
		totalAllGoodResultLbl.setBounds(50,980,200,20);
		containerPnl.add(totalAllGoodResultLbl);
		
		totalAllGoodResultField = new NumberField(5);
		totalAllGoodResultField.setBounds(240,980,150,20);
		containerPnl.add(totalAllGoodResultField);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(750,1020,150,30);
		containerPnl.add(saveBtn);
		
	}
	
	
	private class ProductionResultTableModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private List<ProductionResultDetail> productionResults;
		    
		    public ProductionResultTableModel(List<ProductionResultDetail> productionResults) {
		        this.productionResults = productionResults;
		    }
		    
		    /**
		     * Method to get row count
		     * @return int
		     */
		    public int getRowCount() {
		        return productionResults.size();
		    }
		    
		    /**
		     * Method to get Column Count
		     */
		    public int getColumnCount() {
		        return 11;
		    }
		    
		    /**
		     * Method to get selected value
		     * @param rowIndex rowIndex of selected table
		     * @param columnIndex columnIndex of selected table 
		     * @return ({@link User}) Object 
		     */
		    public Object getValueAt(int rowIndex, int columnIndex) {
		    	ProductionResultDetail p = productionResults.get(rowIndex);
		        switch(columnIndex){
		        	case 0:
		        		return p.getPressedNo();
		            case 1 : 
		                return p.getStartTime();
		            case 2 :
		                return p.getTotal();
		            case 3 :
		                return p.getRepairKlemA();
		            case 4 :
		                return p.getRepairKlemB();
		            case 5 :
		                return p.getRepairProtolA();
		            case 6 :
		                return p.getRepairProtolB();
		            case 7 :
		                return p.getFineA();
		            case 8 :
		                return p.getFineB();
		            case 9 :
		            	return "Edit";
		            case 10 :
		            	return "Delete";
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
		                return "Pengepresan Ke";
		            case 1 :
		                return "Jam Mulai";
		            case 2 :
		                return "Total Output";
		            case 3 :
		            	return "Repair Klem A";
		            case 4 :
		                return "Repair Klem B";
		            case 5 :
		                return "Repair Protol A";
		            case 6 :
		                return "Repair Protol B";
		            case 7 :
		                return "Hasil Baik A";
		            case 8 :
		                return "Hasil Baik B";
		            case 9 :
		                return "Action";
		            case 10 :
		                return "Action";
		            default :
		                return "";
		        }
		    }

		}
}
