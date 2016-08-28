package module.production.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import model.User;
import module.pembelian.model.PalletCard;
import module.production.model.Machine;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultDetail;

public class PopUpViewProductionResult extends JDialog{

	private static final long serialVersionUID = 1L;
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
	private JLabel prodResultCodeLbl;
	
	private JLabel errorMachineLbl;
	private JLabel errorPressNoLbl;
	private JLabel errorTimeLbl;
	private JLabel errorKlemALbl;
	private JLabel errorKlemBLbl;
	private JLabel errorProtolALbl;
	private JLabel errorProtolBLbl;
	private JLabel errorGoodResultALbl;
	private JLabel errorGoodResultBLbl;
	
	private TextField prodResultCodeField;
	
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
	private JButton printBtn;

	private JTable productionResultTable;
	private ProductionResultTableModel productionResultTableModel;
	private JScrollPane scrollPane;
	
	private JScrollPane containerScrollPane;
	private JPanel containerPnl;
	private JPanel borderPanel;
	private ViewProductionPanel viewProductionPanel;
	private List<Machine> machines;
	private List<ProductionResultDetail> listOfPrd;

	public PopUpViewProductionResult(JPanel parent){
		super((JFrame)parent.getTopLevelAncestor());
		createGUI();
		initData(parent);
		listener();
	}
	private void initData(JPanel parent){
		prodResultCodeField.setEnabled(false);
		totalOutputField.setEnabled(false);
		totalKlemField.setEnabled(false);
		totalProtolField.setEnabled(false);
		totalGoodResultAField.setEnabled(false);
		totalGoodResultBField.setEnabled(false);
		totalAllGoodResultField.setEnabled(false);
		listOfPrd = new ArrayList<>();
		
		try {
			machines = ServiceFactory.getProductionBL().getMachine();
			machines.add(0,new Machine("--Pilih--"));
			machineCmb.setList(machines);
			
			Date currentDate = new Date();
			String date = new SimpleDateFormat("dd").format(currentDate);
			String month = new SimpleDateFormat("MM").format(currentDate);
			String year = new SimpleDateFormat("yy").format(currentDate);
			prodResultCodeField.setText(ServiceFactory.getProductionBL().getProductionResultLastCode()+"/PR/"+date+"/"+month+"/"+year);
			resultDateChooser.setDate(currentDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		viewProductionPanel = (ViewProductionPanel) parent;
		if(viewProductionPanel.getProduction().getProductionResult()!=null){
			ProductionResult pr = viewProductionPanel.getProduction().getProductionResult();
			prodResultCodeField.setText(pr.getProdResultCode());
			resultDateChooser.setDate(pr.getProdResultDate());
			machineCmb.setSelectedItem(pr.getMachineDescription());
			totalOutputField.setText(pr.getTotalOutput()+"");
			totalKlemField.setText(pr.getTotalRepairKlem()+"");
			totalProtolField.setText(pr.getTotalRepairProtol()+"");
			totalGoodResultAField.setText(pr.getTotalFineA()+"");
			totalGoodResultBField.setText(pr.getTotalFineB()+"");
			totalAllGoodResultField.setText(pr.getTotalFineResult()+"");
			listOfPrd = pr.getListOfProductionResultDetail();
			productionResultTable.setModel(new ProductionResultTableModel(listOfPrd));
			productionResultTable.updateUI();
			calculateTotal();
			
			machineCmb.setEnabled(false);
			resultDateChooser.setEnabled(false);
			pressNoField.setEnabled(false);
			hourField.setEnabled(false);
			minuteField.setEnabled(false);
			klemGradeAField.setEnabled(false);
			klemGradeBField.setEnabled(false);
			protolGradeAField.setEnabled(false);
			protolGradeBField.setEnabled(false);
			goodResultGradeAField.setEnabled(false);
			goodResultGradeBField.setEnabled(false);
			addBtn.setEnabled(false);
		}
	}
	private void createGUI(){
		setLayout(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("View Hasil Produksi");
		setSize(1020, 750);
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1000,1200));	
		containerPnl.setLayout(null);
		
		containerScrollPane = new JScrollPane(containerPnl);
		containerScrollPane.setBounds(0,0,1000,750);
		containerScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(containerScrollPane);
		
		titleLbl = new JLabel("View Hasil Produksi");
		titleLbl.setBounds(50,10,200,30);
		titleLbl.setFont(new Font("Arial", 1, 18));
		containerPnl.add(titleLbl);
		
		//TODO prodResultCode Area
		prodResultCodeLbl = new JLabel("Production Result Code");
		prodResultCodeLbl.setBounds(50,50,150,20);
		containerPnl.add(prodResultCodeLbl);
		
		prodResultCodeField = new TextField();
		prodResultCodeField.setBounds(240,50,150,20);
		containerPnl.add(prodResultCodeField);
		
		//TODO date Area
		dateLbl = new JLabel("Tanggal");
		dateLbl.setBounds(50,90,150,20);
		containerPnl.add(dateLbl);
		
		resultDateChooser = new JDateChooser();
		resultDateChooser.setBounds(240,90,150,20);
		containerPnl.add(resultDateChooser);
		
		//TODO machine Area
		machineLbl = new JLabel("No Mesin");
		machineLbl.setBounds(50, 130,150,20);
		containerPnl.add(machineLbl);
		
		machineCmb = new ComboBox<>();
		machineCmb.setBounds(240,130,150,20);
		containerPnl.add(machineCmb);
		
		errorMachineLbl = new JLabel();
		errorMachineLbl.setBounds(395, 130,150,20);
		containerPnl.add(errorMachineLbl);
		
		//TODO borderPnl Area
		borderPanel = new JPanel();
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		borderPanel.setBounds(40,170,900,500);
		borderPanel.setLayout(null);
		containerPnl.add(borderPanel);
		
		//TODO pressNo Area
		pressNoLbl = new JLabel("Pengepresan Ke");
		pressNoLbl.setBounds(10,10,150,20);
		borderPanel.add(pressNoLbl);
		
		pressNoField = new NumberField(5);
		pressNoField.setBounds(200,10,150,20);
		borderPanel.add(pressNoField);
		
		errorPressNoLbl = new JLabel();
		errorPressNoLbl.setBounds(355,10,150,20);
		borderPanel.add(errorPressNoLbl);
		
		//TODO Time Area
		startTimeLbl = new JLabel("Jam Mulai");
		startTimeLbl.setBounds(10,50,150,20);
		borderPanel.add(startTimeLbl);
		
		hourField = new NumberField(2);
		hourField.setBounds(200,50,50,20);
		borderPanel.add(hourField);
		
		timeSeparator = new JLabel(" : ");
		timeSeparator.setBounds(255,50,15,20);
		borderPanel.add(timeSeparator);
		
		minuteField = new NumberField(2);
		minuteField.setBounds(265,50,50,20);
		borderPanel.add(minuteField);
		
		errorTimeLbl = new JLabel();
		errorTimeLbl.setBounds(270,50,150,20);
		borderPanel.add(errorTimeLbl);
		
		//TODO klem Area
		klemLbl = new JLabel("Repair (Klem)");
		klemLbl.setBounds(10,90,150,20);
		borderPanel.add(klemLbl);
		
		klemGradeALbl = new JLabel("Jumlah Grade A");
		klemGradeALbl.setBounds(10,130,150,20);
		borderPanel.add(klemGradeALbl);
		
		klemGradeAField = new NumberField(5);
		klemGradeAField.setBounds(200,130,150,20);
		borderPanel.add(klemGradeAField);
		
		errorKlemALbl = new JLabel();
		errorKlemALbl.setBounds(355,130,150,20);
		borderPanel.add(errorKlemALbl);
		
		klemGradeBLbl = new JLabel("Jumlah Grade B");
		klemGradeBLbl.setBounds(10,170,150,20);
		borderPanel.add(klemGradeBLbl);
		
		klemGradeBField = new NumberField(5);
		klemGradeBField.setBounds(200,170,150,20);
		borderPanel.add(klemGradeBField);
		
		errorKlemBLbl = new JLabel();
		errorKlemBLbl.setBounds(355,170,150,20);
		borderPanel.add(errorKlemBLbl);
		
		//TODO protol Area
		protolLbl = new JLabel("Repair (protol)");
		protolLbl.setBounds(10,210,150,20);
		borderPanel.add(protolLbl);
		
		protolGradeALbl = new JLabel("Jumlah Grade A");
		protolGradeALbl.setBounds(10,250,150,20);
		borderPanel.add(protolGradeALbl);
		
		protolGradeAField = new NumberField(5);
		protolGradeAField.setBounds(200,250,150,20);
		borderPanel.add(protolGradeAField);
		
		errorProtolALbl = new JLabel();
		errorProtolALbl.setBounds(355,250,150,20);
		borderPanel.add(errorProtolALbl);
		
		protolGradeBLbl = new JLabel("Jumlah Grade B");
		protolGradeBLbl.setBounds(10,290,150,20);
		borderPanel.add(protolGradeBLbl);
		
		protolGradeBField = new NumberField(5);
		protolGradeBField.setBounds(200,290,150,20);
		borderPanel.add(protolGradeBField);
		
		errorProtolBLbl = new JLabel();
		errorProtolBLbl.setBounds(355,290,150,20);
		borderPanel.add(errorProtolBLbl);
		
		//TODO goodResult Area
		goodResultLbl = new JLabel("Hasil Baik");
		goodResultLbl.setBounds(10,330,150,20);
		borderPanel.add(goodResultLbl);
		
		goodResultGradeALbl = new JLabel("Jumlah Grade A");
		goodResultGradeALbl.setBounds(10,370,150,20);
		borderPanel.add(goodResultGradeALbl);
		
		goodResultGradeAField = new NumberField(5);
		goodResultGradeAField.setBounds(200,370,150,20);
		borderPanel.add(goodResultGradeAField);
		
		errorGoodResultALbl = new JLabel();
		errorGoodResultALbl.setBounds(355,370,150,20);
		borderPanel.add(errorGoodResultALbl);
		
		goodResultGradeBLbl = new JLabel("Jumlah Grade B");
		goodResultGradeBLbl.setBounds(10,410,150,20);
		borderPanel.add(goodResultGradeBLbl);
		
		goodResultGradeBField = new NumberField(5);
		goodResultGradeBField.setBounds(200,410,150,20);
		borderPanel.add(goodResultGradeBField);
		
		errorGoodResultBLbl = new JLabel();
		errorGoodResultBLbl.setBounds(355,410,150,20);
		borderPanel.add(errorGoodResultBLbl);
		
		//TODO add BTN Area
		addBtn = new JButton("Tambah");
		addBtn.setBounds(740,450,150,30);
		borderPanel.add(addBtn);
		
		
		//Table Area
		productionResultTableModel = new ProductionResultTableModel(new ArrayList<ProductionResultDetail>());
		productionResultTable = new JTable(productionResultTableModel);
		
		scrollPane = new JScrollPane(productionResultTable);
		scrollPane.setBounds(40,700,900,150);
		containerPnl.add(scrollPane);
		
		//TODO totalOutput Area
		totalOutputLbl = new JLabel("Total Output");
		totalOutputLbl.setBounds(50,870,150,20);
		containerPnl.add(totalOutputLbl);
		
		totalOutputField = new NumberField(5);
		totalOutputField.setBounds(240,870,150,20);
		containerPnl.add(totalOutputField);
		
		//TODO totalKlem Area
		totalKlemLbl = new JLabel("Total Repair(Klem)");
		totalKlemLbl.setBounds(50,910,150,20);
		containerPnl.add(totalKlemLbl);
		
		totalKlemField = new NumberField(5);
		totalKlemField.setBounds(240,910,150,20);
		containerPnl.add(totalKlemField);
		
		//TODO totalProtol Area
		totalProtolLbl = new JLabel("Total Repair(Protol)");
		totalProtolLbl.setBounds(50,950,150,20);
		containerPnl.add(totalProtolLbl);
		
		totalProtolField = new NumberField(5);
		totalProtolField.setBounds(240,950,150,20);
		containerPnl.add(totalProtolField);
		
		//TODO totalGoodResultA Area
		totalGoodResultALbl = new JLabel("Total Hasil Produk Baik Grade(A)");
		totalGoodResultALbl.setBounds(50,990,200,20);
		containerPnl.add(totalGoodResultALbl);
		
		totalGoodResultAField = new NumberField(5);
		totalGoodResultAField.setBounds(240,990,150,20);
		containerPnl.add(totalGoodResultAField);
		
		//TODO totalGoodResultB Area
		totalGoodResultBLbl = new JLabel("Total Hasil Produk Baik Grade(B)");
		totalGoodResultBLbl.setBounds(50,1030,200,20);
		containerPnl.add(totalGoodResultBLbl);
		
		totalGoodResultBField = new NumberField(5);
		totalGoodResultBField.setBounds(240,1030,150,20);
		containerPnl.add(totalGoodResultBField);
		
		//TODO totalAllGoodResult Area
		totalAllGoodResultLbl = new JLabel("Total Hasil Produk Baik");
		totalAllGoodResultLbl.setBounds(50,1070,200,20);
		containerPnl.add(totalAllGoodResultLbl);
		
		totalAllGoodResultField = new NumberField(5);
		totalAllGoodResultField.setBounds(240,1070,150,20);
		containerPnl.add(totalAllGoodResultField);
		
		printBtn = new JButton("Cetak");
		printBtn.setBounds(750,1110,150,30);
		containerPnl.add(printBtn);
	}
	
	
	private void listener(){
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	


	private void calculateTotal(){
		int totalOutput=0;
		int totalKlem=0;
		int totalProtol=0;
		int totalFineA=0;
		int totalFineB=0;
		int totalAllFine=0;
		for (ProductionResultDetail prd : listOfPrd) {
			totalOutput+=prd.getTotal();
			totalKlem+=(prd.getRepairKlemA()+prd.getRepairKlemB());
			totalProtol+=(prd.getRepairProtolA()+prd.getRepairProtolB());
			totalFineA+=prd.getFineA();
			totalFineB+=prd.getFineB();
			totalAllFine+=(prd.getFineA()+prd.getFineB());
		}
		
		totalOutputField.setText(totalOutput+"");
		totalKlemField.setText(totalKlem+"");
		totalProtolField.setText(totalProtol+"");
		totalGoodResultAField.setText(totalFineA+"");
		totalGoodResultBField.setText(totalFineB+"");
		totalAllGoodResultField.setText(totalAllFine+"");
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
