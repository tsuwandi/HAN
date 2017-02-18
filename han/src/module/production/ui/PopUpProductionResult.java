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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import model.User;
import module.production.model.Machine;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.productionpk.model.ProdPKMaterial;
import module.productionpk.model.ProdPKResult;
import module.productionpk.model.ProdPKResultProduct;

public class PopUpProductionResult extends JDialog{
	Logger log = LogManager.getLogger(PopUpProductionResult.class.getName());
	private static final long serialVersionUID = 1L;
	private JLabel titleLbl;
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
	
	private JLabel errorPressNoLbl;
	private JLabel errorTimeLbl;
	private JLabel errorKlemALbl;
	private JLabel errorKlemBLbl;
	private JLabel errorProtolALbl;
	private JLabel errorProtolBLbl;
	private JLabel errorGoodResultALbl;
	private JLabel errorGoodResultBLbl;
	private JLabel timeSeparator;

	private NumberField pressNoField;
	private NumberField hourField;
	private NumberField minuteField;
	private NumberField klemGradeAField;
	private NumberField klemGradeBField;
	private NumberField protolGradeAField;
	private NumberField protolGradeBField;
	private NumberField goodResultGradeAField;
	private NumberField goodResultGradeBField;
	private TextField totalOutputField;
	private TextField totalKlemField;
	private TextField totalProtolField;
	private TextField totalGoodResultAField;
	private TextField totalGoodResultBField;
	private TextField totalAllGoodResultField;
	
	private JButton addBtn;
	private JButton saveBtn;

	private JTable productionResultTable;
	private ResultTableModel productionResultTableModel;
	private JScrollPane scrollPane;
	
	private JScrollPane containerScrollPane;
	private JPanel containerPnl;
	private JPanel borderPanel;
	private CreateProductionPanel createProductionPanel;
	private List<ProductionResult> listOfPrd;
	private boolean editMode=false;
	private int indexEdit=0;
	private Map<Integer, Integer> pressMap;
	private Map<Integer, ProductionResult> deletedProdResult;
	
	static final String KA = "PDC009-7";
	static final String KB = "PDC009-8";
	static final String PA = "PDC009-5";
	static final String PB = "PDC009-6";
	static final String NA = "PDC009-1";
	static final String NB = "PDC009-2";
	
	public PopUpProductionResult(JPanel parent){
		super((JFrame)parent.getTopLevelAncestor());
		createGUI();
		initData(parent);
		listener();
	}
	private void initData(JPanel parent){
		totalOutputField.setEnabled(false);
		totalKlemField.setEnabled(false);
		totalProtolField.setEnabled(false);
		totalGoodResultAField.setEnabled(false);
		totalGoodResultBField.setEnabled(false);
		totalAllGoodResultField.setEnabled(false);
		pressMap = new HashMap<>();
		deletedProdResult = new HashMap<>();
		listOfPrd = new ArrayList<>();

		createProductionPanel = (CreateProductionPanel) parent;
		if(createProductionPanel.getProduction().getProductionResults()!=null){
			listOfPrd = createProductionPanel.getProduction().getProductionResults();
			productionResultTable.setModel(new ResultTableModel(listOfPrd));
			for (ProductionResult prd : listOfPrd) {
				pressMap.put(prd.getPressedNo(), prd.getPressedNo());
			}
			productionResultTable.updateUI();
			calculateTotal();
		}
	}
	private void createGUI(){
		setLayout(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Input Hasil Produksi 9");
		setSize(1020, 750);
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1000,1100));	
		containerPnl.setLayout(null);
		
		containerScrollPane = new JScrollPane(containerPnl);
		containerScrollPane.setBounds(0,0,1000,750);
		containerScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(containerScrollPane);
		
		titleLbl = new JLabel("Input Hasil Produksi 9");
		titleLbl.setBounds(50,10,200,30);
		titleLbl.setFont(new Font("Arial", 1, 18));
		containerPnl.add(titleLbl);

	
		//TODO borderPnl Area
		borderPanel = new JPanel();
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		borderPanel.setBounds(40,90,900,500);
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
		errorTimeLbl.setBounds(320,50,250,20);
		borderPanel.add(errorTimeLbl);
		
		//TODO klem Area
		klemLbl = new JLabel("Repair (Klem)");
		klemLbl.setBounds(10,90,150,20);
		borderPanel.add(klemLbl);
		
		klemGradeALbl = new JLabel("Jumlah Grade A");
		klemGradeALbl.setBounds(10,130,150,20);
		borderPanel.add(klemGradeALbl);
		
		klemGradeAField = new NumberField(7);
		klemGradeAField.setBounds(200,130,150,20);
		borderPanel.add(klemGradeAField);
		
		errorKlemALbl = new JLabel();
		errorKlemALbl.setBounds(355,130,150,20);
		borderPanel.add(errorKlemALbl);
		
		klemGradeBLbl = new JLabel("Jumlah Grade B");
		klemGradeBLbl.setBounds(10,170,150,20);
		borderPanel.add(klemGradeBLbl);
		
		klemGradeBField = new NumberField(7);
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
		
		protolGradeAField = new NumberField(7);
		protolGradeAField.setBounds(200,250,150,20);
		borderPanel.add(protolGradeAField);
		
		errorProtolALbl = new JLabel();
		errorProtolALbl.setBounds(355,250,150,20);
		borderPanel.add(errorProtolALbl);
		
		protolGradeBLbl = new JLabel("Jumlah Grade B");
		protolGradeBLbl.setBounds(10,290,150,20);
		borderPanel.add(protolGradeBLbl);
		
		protolGradeBField = new NumberField(7);
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
		
		goodResultGradeAField = new NumberField(7);
		goodResultGradeAField.setBounds(200,370,150,20);
		borderPanel.add(goodResultGradeAField);
		
		errorGoodResultALbl = new JLabel();
		errorGoodResultALbl.setBounds(355,370,150,20);
		borderPanel.add(errorGoodResultALbl);
		
		goodResultGradeBLbl = new JLabel("Jumlah Grade B");
		goodResultGradeBLbl.setBounds(10,410,150,20);
		borderPanel.add(goodResultGradeBLbl);
		
		goodResultGradeBField = new NumberField(7);
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
		productionResultTableModel = new ResultTableModel(new ArrayList<ProductionResult>());
		productionResultTable = new JTable(productionResultTableModel);
		productionResultTable.setFocusable(false);
		
		scrollPane = new JScrollPane(productionResultTable);
		scrollPane.setBounds(40,600,900,150);
		containerPnl.add(scrollPane);
		
		//TODO totalOutput Area
		totalOutputLbl = new JLabel("Total Output");
		totalOutputLbl.setBounds(50,770,150,20);
		containerPnl.add(totalOutputLbl);
		
		totalOutputField = new TextField();
		totalOutputField.setBounds(240,770,150,20);
		containerPnl.add(totalOutputField);
		
		//TODO totalKlem Area
		totalKlemLbl = new JLabel("Total Repair(Klem)");
		totalKlemLbl.setBounds(50,810,150,20);
		containerPnl.add(totalKlemLbl);
		
		totalKlemField = new TextField();
		totalKlemField.setBounds(240,810,150,20);
		containerPnl.add(totalKlemField);
		
		//TODO totalProtol Area
		totalProtolLbl = new JLabel("Total Repair(Protol)");
		totalProtolLbl.setBounds(50,850,150,20);
		containerPnl.add(totalProtolLbl);
		
		totalProtolField = new TextField();
		totalProtolField.setBounds(240,850,150,20);
		containerPnl.add(totalProtolField);
		
		//TODO totalGoodResultA Area
		totalGoodResultALbl = new JLabel("Total Hasil Produk Baik Grade(A)");
		totalGoodResultALbl.setBounds(50,890,200,20);
		containerPnl.add(totalGoodResultALbl);
		
		totalGoodResultAField = new TextField();
		totalGoodResultAField.setBounds(240,890,150,20);
		containerPnl.add(totalGoodResultAField);
		
		//TODO totalGoodResultB Area
		totalGoodResultBLbl = new JLabel("Total Hasil Produk Baik Grade(B)");
		totalGoodResultBLbl.setBounds(50,930,200,20);
		containerPnl.add(totalGoodResultBLbl);
		
		totalGoodResultBField = new TextField();
		totalGoodResultBField.setBounds(240,930,150,20);
		containerPnl.add(totalGoodResultBField);
		
		//TODO totalAllGoodResult Area
		totalAllGoodResultLbl = new JLabel("Total Hasil Produk Baik");
		totalAllGoodResultLbl.setBounds(50,970,200,20);
		containerPnl.add(totalAllGoodResultLbl);
		
		totalAllGoodResultField = new TextField();
		totalAllGoodResultField.setBounds(240,970,150,20);
		containerPnl.add(totalAllGoodResultField);
		
		saveBtn = new JButton("OK");
		saveBtn.setBounds(750,1010,150,30);
		saveBtn.setFocusable(false);
		containerPnl.add(saveBtn);
	}
	
	
	private void listener(){
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(DialogBox.showCloseChoice()==JOptionPane.YES_OPTION) dispose();
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				validatingDetail();
			}
		});
		
		productionResultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(productionResultTable.columnAtPoint(e.getPoint())==6){
					editMode=true;
					indexEdit=productionResultTable.getSelectedRow();
					ProductionResult prd = listOfPrd.get(productionResultTable.getSelectedRow());
					pressNoField.setText(prd.getPressedNo()+"");
					String [] splitTime = prd.getStartTime().split(":");
					hourField.setText(splitTime[0]);
					minuteField.setText(splitTime[1]);
					for (ProductionResultProduct pkpr : prd.getListProductionResultProduct()) {
						if(pkpr.getProductCode().equals(KA)){
							klemGradeAField.setText(pkpr.getQty()+"");
						}
						if(pkpr.getProductCode().equals(KB)){
							klemGradeBField.setText(pkpr.getQty()+"");
						}
						if(pkpr.getProductCode().equals(PA)){
							protolGradeAField.setText(pkpr.getQty()+"");
						}
						if(pkpr.getProductCode().equals(PB)){
							protolGradeBField.setText(pkpr.getQty()+"");
						}
						if(pkpr.getProductCode().equals(NA)){
							goodResultGradeAField.setText(pkpr.getQty()+"");
						}
						if(pkpr.getProductCode().equals(NB)){
							goodResultGradeBField.setText(pkpr.getQty()+"");
						}
					}		
				}
				if(productionResultTable.columnAtPoint(e.getPoint())==7){
					if(DialogBox.showDeleteChoice()==JOptionPane.YES_OPTION){
						pressMap.remove(listOfPrd.get(productionResultTable.getSelectedRow()).getPressedNo());
						ProductionResult pr = listOfPrd.get(productionResultTable.getSelectedRow());
						if(pr.getId()!=0)deletedProdResult.put(pr.getId(), pr);
						listOfPrd.remove(productionResultTable.getSelectedRow());
						productionResultTable.updateUI();
						calculateTotal();
					}
				}
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveProductResult();
			}
		});
	}
	

	private void validatingDetail(){
		int error = 0;
		if(pressNoField.getText().equals("")){
			errorPressNoLbl.setText("<html><font color='red'>Pengepresan harus diisi !</font></html>");
			error++;
		}else{
			if(!editMode){
				if(pressMap.containsKey(Integer.valueOf(pressNoField.getText()))){
					errorPressNoLbl.setText("<html><font color='red'>No Pengepresan sudah ada!</font></html>");
					error++;
				}else{
					errorPressNoLbl.setText("");
				}
			}
		}
		
		if(hourField.getText().equals("")||minuteField.getText().equals("")){
			errorTimeLbl.setText("");
		}else{
			if(hourField.getText().length()!=2||minuteField.getText().length()!=2){
				errorTimeLbl.setText("<html><font color='red'>Format Jam mulai harus 00:00 !</font></html>");
				error++;
			}else{
				if(Integer.valueOf(hourField.getText())>23||Integer.valueOf(minuteField.getText())>59){
					errorTimeLbl.setText("<html><font color='red'>Format Jam Tidak boleh lebih dari 23:59 !</font></html>");
					error++;
				}else{
					errorTimeLbl.setText("");
				}
			}
		}
	
		
		/*if(klemGradeAField.getText().equals("")){
			errorKlemALbl.setText("<html><font color='red'>Repair Klem A harus diisi !</font></html>");
			error++;
		}else{
			errorKlemALbl.setText("");
		}
		if(klemGradeBField.getText().equals("")){
			errorKlemBLbl.setText("<html><font color='red'>Repair Klem B harus diisi !</font></html>");
			error++;
		}else{
			errorKlemBLbl.setText("");
		}
		
		if(protolGradeAField.getText().equals("")){
			errorProtolALbl.setText("<html><font color='red'>Repair Protol A harus diisi !</font></html>");
			error++;
		}else{
			errorProtolALbl.setText("");
		}
		if(protolGradeBField.getText().equals("")){
			errorProtolBLbl.setText("<html><font color='red'>Repair Protol B harus diisi !</font></html>");
			error++;
		}else{
			errorProtolBLbl.setText("");
		}
		if(goodResultGradeAField.getText().equals("")){
			errorGoodResultALbl.setText("<html><font color='red'>Hasil Baik A harus diisi !</font></html>");
			error++;
		}else{
			errorGoodResultALbl.setText("");
		}
		if(goodResultGradeBField.getText().equals("")){
			errorGoodResultBLbl.setText("<html><font color='red'>Hasil Baik B harus diisi !</font></html>");
			error++;
		}else{
			errorGoodResultBLbl.setText("");
		}*/
		
		if(error==0){
			if(editMode){
				ProductionResult prd = listOfPrd.get(indexEdit);
				int tempPressedNo = prd.getPressedNo();
				prd.setPressedNo(Integer.parseInt(pressNoField.getText()));
				if(!hourField.getText().equals("")||!minuteField.getText().equals("")){
					prd.setStartTime(hourField.getText()+":"+minuteField.getText());
				}else{
					prd.setStartTime("00:00");
				}
				
				double klemA = klemGradeAField.getText().equals("")?0:Double.valueOf(klemGradeAField.getText());
				double klemB = klemGradeBField.getText().equals("")?0:Double.valueOf(klemGradeBField.getText());
				double protolA = protolGradeAField.getText().equals("")?0:Double.valueOf(protolGradeAField.getText());
				double protolB = protolGradeBField.getText().equals("")?0:Double.valueOf(protolGradeBField.getText());
				double fineA = goodResultGradeAField.getText().equals("")?0:Double.valueOf(goodResultGradeAField.getText());
				double fineB = goodResultGradeBField.getText().equals("")?0:Double.valueOf(goodResultGradeBField.getText());
				
				prd.setTotalKlem(klemA+klemB);
				prd.setTotalProtol(protolA+protolB);
				prd.setTotalFineA(fineA);
				prd.setTotalFineB(fineB);
				
				if(prd.getListProductionResultProduct().size()>0){
					for (ProductionResultProduct prodPKResultProduct : prd.getListProductionResultProduct()) {
						if(prodPKResultProduct.getProductCode().equals(KA)){
							prodPKResultProduct.setQty(klemA);
						}
						if(prodPKResultProduct.getProductCode().equals(KB)){
							prodPKResultProduct.setQty(klemB);
						}
						if(prodPKResultProduct.getProductCode().equals(PA)){
							prodPKResultProduct.setQty(protolA);
						}
						if(prodPKResultProduct.getProductCode().equals(PB)){
							prodPKResultProduct.setQty(protolB);
						}
						if(prodPKResultProduct.getProductCode().equals(NA)){
							prodPKResultProduct.setQty(fineA);
						}
						if(prodPKResultProduct.getProductCode().equals(NB)){
							prodPKResultProduct.setQty(fineB);
						}
					}
				}
				
				if(tempPressedNo!=prd.getPressedNo()) pressMap.remove(tempPressedNo);
				pressMap.put(prd.getPressedNo(),prd.getPressedNo());
				productionResultTable.updateUI();
				editMode=false;
				indexEdit=0;
			}else{
				ProductionResult prd = new ProductionResult();
				prd.setPressedNo(Integer.parseInt(pressNoField.getText()));
				if(!hourField.getText().equals("")||!minuteField.getText().equals("")){
					prd.setStartTime(hourField.getText()+":"+minuteField.getText());
				}else{
					prd.setStartTime("00:00");
				}
				double klemA = klemGradeAField.getText().equals("")?0:Double.valueOf(klemGradeAField.getText());
				double klemB = klemGradeBField.getText().equals("")?0:Double.valueOf(klemGradeBField.getText());
				double protolA = protolGradeAField.getText().equals("")?0:Double.valueOf(protolGradeAField.getText());
				double protolB = protolGradeBField.getText().equals("")?0:Double.valueOf(protolGradeBField.getText());
				double fineA = goodResultGradeAField.getText().equals("")?0:Double.valueOf(goodResultGradeAField.getText());
				double fineB = goodResultGradeBField.getText().equals("")?0:Double.valueOf(goodResultGradeBField.getText());
				
				prd.setTotalKlem(klemA+klemB);
				prd.setTotalProtol(protolA+protolB);
				prd.setTotalFineA(fineA);
				prd.setTotalFineB(fineB);
				
				List<ProductionResultProduct> prodPKResultProducts = new ArrayList<>();
				for(int i=0;i<6;i++){
					ProductionResultProduct prodPK = new ProductionResultProduct();
					if(i==0){
						prodPK.setProductCode(KA);
						prodPK.setQty(Double.valueOf(klemA));
					}
					if(i==1){
						prodPK.setProductCode(KB);
						prodPK.setQty(Double.valueOf(klemB));
					}
					if(i==2){
						prodPK.setProductCode(PA);
						prodPK.setQty(Double.valueOf(protolA));
					}
					if(i==3){
						prodPK.setProductCode(PB);
						prodPK.setQty(Double.valueOf(protolB));
					}
					if(i==4){
						prodPK.setProductCode(NA);
						prodPK.setQty(Double.valueOf(fineA));
					}
					if(i==5){
						prodPK.setProductCode(NB);
						prodPK.setQty(Double.valueOf(fineB));
					}
					prodPKResultProducts.add(prodPK);
				}
				prd.setListProductionResultProduct(prodPKResultProducts);
				listOfPrd.add(prd);
				pressMap.put(prd.getPressedNo(),prd.getPressedNo());
				productionResultTable.setModel(new ResultTableModel(listOfPrd));
				productionResultTable.updateUI();
			}
			calculateTotal();
			clearDetail();
		}
	}
	
	private void clearDetail(){
		pressNoField.setText("");
		hourField.setText("");
		minuteField.setText("");
		goodResultGradeAField.setText("");
		goodResultGradeBField.setText("");
		protolGradeAField.setText("");
		protolGradeBField.setText("");
		klemGradeAField.setText("");
		klemGradeBField.setText("");
	}
	private void calculateTotal(){
		int totalOutput=0;
		int totalKlem=0;
		int totalProtol=0;
		int totalFineA=0;
		int totalFineB=0;
		int totalAllFine=0;
		for (ProductionResult prd : listOfPrd) {	
			totalKlem+=(prd.getTotalKlem());
			totalProtol+=(prd.getTotalProtol());
			totalFineA+=prd.getTotalFineA();
			totalFineB+=prd.getTotalFineB();
			totalAllFine+=(prd.getTotalFineA()+prd.getTotalFineB());
			totalOutput=totalAllFine+totalKlem+totalProtol;
		}
		
		totalOutputField.setText(totalOutput+"");
		totalKlemField.setText(totalKlem+"");
		totalProtolField.setText(totalProtol+"");
		totalGoodResultAField.setText(totalFineA+"");
		totalGoodResultBField.setText(totalFineB+"");
		totalAllGoodResultField.setText(totalAllFine+"");
	}
	
	private void saveProductResult(){
		if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
			createProductionPanel.getProduction().setProductionResults(listOfPrd);
			createProductionPanel.getProduction().setDeletedProductionResult(deletedProdResult);
			DialogBox.showInsert();
			dispose();
		}
	}
	
	private class ResultTableModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private List<ProductionResult> productionResults;
		    
		    public ResultTableModel(List<ProductionResult> productionResults) {
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
		        return 8;
		    }
		    
		    /**
		     * Method to get selected value
		     * @param rowIndex rowIndex of selected table
		     * @param columnIndex columnIndex of selected table 
		     * @return ({@link User}) Object 
		     */
		    public Object getValueAt(int rowIndex, int columnIndex) {
		    	ProductionResult p = productionResults.get(rowIndex);
		        switch(columnIndex){
		        	case 0:
		        		return p.getPressedNo();
		            case 1 : 
		                return p.getStartTime();
		            case 2 :
		                return p.getTotalKlem();
		            case 3 :
		                return p.getTotalProtol();
		            case 4 :
		                return p.getTotalFineA();
		            case 5 :
		                return p.getTotalFineB();
		            case 6 :
		            	return "Edit";
		            case 7 :
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
		            	return "Total Klem";
		            case 3 :
		                return "Total Protol";
		            case 4 :
		                return "Hasil Baik A";
		            case 5 :
		                return "Hasil Baik B";
		            case 6 :
		                return "Action";
		            case 7 :
		                return "Action";
		            default :
		                return "";
		        }
		    }

		}
}
