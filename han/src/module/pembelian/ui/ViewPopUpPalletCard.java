package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.component.ComboBox;
import main.component.NumberField;
import model.User;
import module.pembelian.model.Employee;
import module.pembelian.model.Grade;
import module.pembelian.model.Pallet;
import module.pembelian.model.PalletCardDetail;
import module.pembelian.model.Product;
import module.pembelian.model.Thickness;
import controller.ReceivedDAOFactory;

public class ViewPopUpPalletCard extends JDialog{

	JLabel palletCardCodeLbl;
	JLabel graderLbl;
	JLabel gradeLbl;
	JLabel longLbl;
	JLabel wideLbl;
	JLabel thickLbl;
	JLabel totalLbl;
	JLabel volumeLbl;
	JLabel totalLogLbl;
	JLabel totalVolumeLbl;
	JLabel productNameLbl;
	JLabel uomLongLbl;
	JLabel uomWideLbl;
	JLabel uomThickLbl;
	JLabel uomTotalLbl;
	JLabel uomVolumeLbl;
	JLabel uomTotalLogLbl;
	JLabel uomTotalVolumeLbl;
	
	JLabel errorGraderLbl;
	JLabel errorGradeLbl;
	JLabel errorLongLbl;
	JLabel errorWideLbl;
	JLabel errorThickLbl;
	JLabel errorTotalLbl;
	JLabel errorVolumeLbl;
	JLabel productCode;
	
	JTextField codePalletCardField;
	NumberField longField;
	NumberField wideField;
	NumberField totalField;
	NumberField volumeField;
	JTextField totalLogField;
	JTextField totalVolumeField;
	JTextField productNameField;
	
	ComboBox<Employee> graderComboBox;
	ComboBox<Grade> gradeComboBox;
	ComboBox<Thickness> thicknessComboBox;
	
	
	JButton insertButton;
	JButton confirmButton;
	
	JTable pcTable;
	JScrollPane pcScrollPane;
	
	PCTableModel pcTableModel;
	List<PalletCardDetail> pcs;
	List<Grade> grades;
	List<Thickness> thicknesses;
	List<Employee> employees;
	List<Product> products;
	Map<Integer, Map<Integer, Product>> productMap;
	ViewReceivedDetailPanel addReceivedDetail;
	public ViewPopUpPalletCard(ViewReceivedDetailPanel parent) {
		addReceivedDetail = parent;
		setLayout(null);
		setTitle("Kartu Pallet");
		setSize(700, 700);
		
		//Code Pallet
		palletCardCodeLbl = new JLabel("Kode Kartu Pallet");
		palletCardCodeLbl.setBounds(30,30,150,20);
		add(palletCardCodeLbl);
		
		codePalletCardField = new JTextField();
		codePalletCardField.setBounds(150, 30, 150, 20);
		codePalletCardField.setEnabled(false);
		add(codePalletCardField);
		
		//Grader 
		graderLbl = new JLabel("Grader");
		graderLbl.setBounds(30,70,150,20);
		add(graderLbl);
		
		graderComboBox = new ComboBox<Employee>();
		graderComboBox.setBounds(150,70,150,20);
		gradeComboBox.setEnabled(false);
		add(graderComboBox);
		
		errorGraderLbl = new JLabel();
		errorGraderLbl.setBounds(310,70,150,20);
		add(errorGraderLbl);
		
		//Grade
		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(30,100,150,20);
		add(gradeLbl);

		gradeComboBox = new ComboBox<Grade>();
		gradeComboBox.setBounds(150, 100, 150, 20);
		gradeComboBox.setEnabled(false);
		add(gradeComboBox);
		
		errorGradeLbl = new JLabel();
		errorGradeLbl.setBounds(310,100,150,20);
		add(errorGradeLbl);
		
		//Long 
		longLbl = new JLabel("Panjang");
		longLbl.setBounds(30,140,150,20);
		add(longLbl);
		
		longField = new NumberField();
		longField.setBounds(150, 140, 150, 20);
		longField.setEnabled(false);
		add(longField);
		
		uomLongLbl = new JLabel("/M");
		uomLongLbl.setBounds(302,140,15,20);
		add(uomLongLbl);
		
		errorLongLbl = new JLabel();
		errorLongLbl.setBounds(325,140,150,20);
		add(errorLongLbl);
		
		//ProductName
		productNameLbl = new JLabel("Nama Produk");
		productNameLbl.setBounds(30,180,150,20);
		add(productNameLbl);
		
		productNameField = new JTextField();
		productNameField.setBounds(150, 180, 150, 20);
		add(productNameField);
		
		//Wide
		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(30,220,100,20);
		add(wideLbl);
		
		wideField = new NumberField();
		wideField.setBounds(150, 220, 150, 20);
		wideField.setEnabled(false);
		add(wideField);
		
		uomWideLbl = new JLabel("/M");
		uomWideLbl.setBounds(302,220,15,20);
		add(uomWideLbl);
		
		errorWideLbl = new JLabel();
		errorWideLbl.setBounds(325,220,150,20);
		add(errorWideLbl);
		
		//Thickness
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(30,260,100,20);
		add(thickLbl);
		
		thicknessComboBox = new ComboBox<Thickness>();
		thicknessComboBox.setBounds(150, 260, 150, 20);
		thicknessComboBox.setEnabled(false);
		add(thicknessComboBox);
		
		uomThickLbl = new JLabel("/M");
		uomThickLbl.setBounds(302,260,15,20);
		add(uomThickLbl);
		
		errorThickLbl = new JLabel();
		errorThickLbl.setBounds(325,260,150,20);
		add(errorThickLbl);
		
		//Total
		totalLbl = new JLabel("Total");
		totalLbl.setBounds(30,300,100,20);
		add(totalLbl);
	
		totalField = new NumberField();
		totalField.setBounds(150, 300, 150, 20);
		totalField.setEnabled(false);
		add(totalField);
		
		uomTotalLbl = new JLabel("/Batang");
		uomTotalLbl.setBounds(302,300,40,20);
		add(uomTotalLbl);
		
		errorTotalLbl = new JLabel();
		errorTotalLbl.setBounds(352,300,150,20);
		add(errorTotalLbl);
		
		//Volume
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(30,340,100,20);
		add(volumeLbl);
			
		volumeField = new NumberField();
		volumeField.setBounds(150, 340, 150, 20);
		volumeField.setEnabled(false);
		add(volumeField);
		
		uomVolumeLbl = new JLabel("<html><span>/M&#179;</span></html>");
		uomVolumeLbl.setBounds(302,340,16,20);
		add(uomVolumeLbl);
		
		errorVolumeLbl = new JLabel();
		errorVolumeLbl.setBounds(325,340,150,20);
		add(errorVolumeLbl);
		
		
		//insert Button
		insertButton = new JButton("Insert");
		insertButton.setBounds(300,380,150,30);
		add(insertButton);
		
		//Table pc
		
		pcs = new ArrayList<>();
		pcTableModel = new PCTableModel(pcs);
		pcTable = new JTable(pcTableModel);
		
		pcScrollPane = new JScrollPane(pcTable);
		pcScrollPane.setBounds(30,420,610,100);
		add(pcScrollPane);
		
		//Total Log
		totalLogLbl = new JLabel("Total Jumlah Kayu");
		totalLogLbl.setBounds(30,540,150,20);
		add(totalLogLbl);
	
		totalLogField =  new JTextField();
		totalLogField.setBounds(350, 540, 150, 20);
		add(totalLogField);
		
		uomTotalLogLbl = new JLabel("/Batang");
		uomTotalLogLbl.setBounds(502,540,40,20);
		add(uomTotalLogLbl);
		
		//total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(30,580,150,20);
		add(totalVolumeLbl);

		totalVolumeField =  new JTextField();
		totalVolumeField.setBounds(350, 580, 150, 20);
		add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("<html><span>/M&#179;</span></html>");
		uomTotalVolumeLbl.setBounds(502,580,16,20);
		add(uomTotalVolumeLbl);

		//Confirm Btn
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(500,610,150,30);
		add(confirmButton);
		
		totalVolumeField.setEnabled(false);
		totalLogField.setEnabled(false);
		productNameField.setEnabled(false);
		
		productCode = new JLabel();
		add(productCode);
		
		productMap = new HashMap<Integer, Map<Integer,Product>>();
		
		try {
			grades = ReceivedDAOFactory.getGradeDAO().getAll();
			grades.add(0,new Grade("--Pilih--"));
			gradeComboBox.setList(grades);
			
			thicknesses = ReceivedDAOFactory.getThicknessDAO().getThickness();
			thicknesses.add(0,new Thickness("--Pilih--"));
			thicknessComboBox.setList(thicknesses);
			
			employees = ReceivedDAOFactory.getPICDockingDAO().getEmployeeGrader("POS0002");
			employees.add(0,new Employee("--Pilih--"));
			graderComboBox.setList(employees);
			
			products = ReceivedDAOFactory.getProductDAO().getAllProduct(1);
			for (Product product : products) {
				Map<Integer, Product> mapTemp = new HashMap<Integer, Product>();
				mapTemp.put(product.getGradeId(), product);
				productMap.put(product.getThicknessId(), mapTemp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		thicknessComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(thicknessComboBox.getSelectedIndex()!=0 && gradeComboBox.getSelectedIndex()!=0){
					if (productMap.get(thicknessComboBox.getDataIndex().getId())!=null) {
						if(productMap.get(thicknessComboBox.getDataIndex().getId()).get(gradeComboBox.getDataIndex().getId())!=null){
							productNameField.setText(productMap.get(thicknessComboBox.getDataIndex().getId()).get(gradeComboBox.getDataIndex().getId()).getProductName());
							productCode.setText(productMap.get(thicknessComboBox.getDataIndex().getId()).get(gradeComboBox.getDataIndex().getId()).getProductCode());
						}else productNameField.setText("");
					}else productNameField.setText("");
				}else productNameField.setText("");
			}
		});
		
		gradeComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(thicknessComboBox.getSelectedIndex()!=0 && gradeComboBox.getSelectedIndex()!=0){
					if (productMap.get(thicknessComboBox.getDataIndex().getId())!=null) {
						if(productMap.get(thicknessComboBox.getDataIndex().getId()).get(gradeComboBox.getDataIndex().getId())!=null){
							productNameField.setText(productMap.get(thicknessComboBox.getDataIndex().getId()).get(gradeComboBox.getDataIndex().getId()).getProductName());
							productCode.setText(productMap.get(thicknessComboBox.getDataIndex().getId()).get(gradeComboBox.getDataIndex().getId()).getProductCode());
						}else productNameField.setText("");
					}else productNameField.setText("");
				}else productNameField.setText("");
			}
		});
		
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int error=0;
				if(graderComboBox.getSelectedIndex()==0){
					errorGraderLbl.setText("<html><font color='red'>Grader harus dipilih !</font></html>");
					error++;
				}else{
					errorGraderLbl.setText("");
				}
				
				if(gradeComboBox.getSelectedIndex()==0){
					errorGradeLbl.setText("<html><font color='red'>Grade harus dipilih !</font></html>");
					error++;
				}else{
					errorGradeLbl.setText("");
				}
				if(thicknessComboBox.getSelectedIndex()==0){
					errorThickLbl.setText("<html><font color='red'>Tebal harus dipilih !</font></html>");
					error++;
				}else{
					errorThickLbl.setText("");
				}
				
				if(longField.getText().equals("")){
					errorLongLbl.setText("<html><font color='red'>Panjang harus diisi !</font></html>");
					error++;
				}else{
					errorLongLbl.setText("");
				}
				if(wideField.getText().equals("")){
					errorWideLbl.setText("<html><font color='red'>Lebar harus diisi !</font></html>");
					error++;
				}else{
					errorWideLbl.setText("");
				}
				if(totalField.getText().equals("")){
					errorTotalLbl.setText("<html><font color='red'>Total Kayu harus diisi !</font></html>");
					error++;
				}else{
					errorTotalLbl.setText("");
				}
				if(volumeField.getText().equals("")){
					errorVolumeLbl.setText("<html><font color='red'>Volume Kayu harus diisi !</font></html>");
					error++;
				}else{
					errorVolumeLbl.setText("");
				}
				
				if(error==0){
					PalletCardDetail pc = new PalletCardDetail();
					pc.setPalletCardCode(codePalletCardField.getText());
					pc.setThickness(thicknessComboBox.getDataIndex().getThickness());
					pc.setTotal(Integer.valueOf(totalField.getText()));
					pc.setVolume(Double.valueOf(volumeField.getText()));
					pc.setProductName(productNameField.getText());
					pc.setLength(Double.valueOf(longField.getText()));
					pc.setWidth(Double.valueOf(wideField.getText()));
					pc.setProductCode(productCode.getText());
					pcs.add(pc);
					pcTable.updateUI();
					
					int total = 0;
					double volume = 0;
					for(PalletCardDetail pcd : pcs){
						total+=pcd.getTotal();
						volume+=pcd.getVolume();
					}
					totalLogField.setText(total+"");
					totalVolumeField.setText(volume+"");
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int error=0;
				if(graderComboBox.getSelectedIndex()==0){
					errorGraderLbl.setText("<html><font color='red'>Grader harus dipilih !</font></html>");
					error++;
				}else{
					errorGraderLbl.setText("");
				}
				
				if(gradeComboBox.getSelectedIndex()==0){
					errorGradeLbl.setText("<html><font color='red'>Grade harus dipilih !</font></html>");
					error++;
				}else{
					errorGradeLbl.setText("");
				}
				
				if(error==0){
					Pallet pallet = new Pallet();
					pallet.setGradeID(gradeComboBox.getDataIndex().getId());
					pallet.setGrade(gradeComboBox.getDataIndex().getGrade());
					pallet.setEmpName(graderComboBox.getDataIndex().getEmployeeName());
					pallet.setEmpCode(graderComboBox.getDataIndex().getEmployeeId());
					pallet.setPalletCardCode(codePalletCardField.getText());
					pallet.setTotalLog(Integer.valueOf(totalLogField.getText()));
					pallet.setTotalVolume(Double.valueOf(totalVolumeField.getText()));
					pallet.setPalletCardDetails(pcs);
					
					addReceivedDetail.pallets.add(pallet);
					addReceivedDetail.palletTable.updateUI();
					dispose();
					
				}
			}
		});
	
	}
	
	private class PCTableModel extends AbstractTableModel {
	    private List<PalletCardDetail> palleteCardDetails;
	    
	    public PCTableModel(List<PalletCardDetail> pcs) {
	        this.palleteCardDetails = pcs;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return palleteCardDetails.size();
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
	        PalletCardDetail p = palleteCardDetails.get(rowIndex);
	        switch(columnIndex){
	            case 0 : 
	                return p.getLength();
	            case 1 :
	                return p.getWidth();
	            case 2 :
	                return p.getThickness();
	            case 3 :
	            	return p.getProductName();
	            case 4 :
	                return p.getTotal();
	            case 5 :
	                return p.getVolume();
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
	                return "Panjang";
	            case 1 :
	                return "Lebar";
	            case 2 :
	                return "Tebal";
	            case 3 :
	            	return "Nama Produk";
	            case 4 :
	                return "Jumlah";
	            case 5 :
	                return "Volume";
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