package module.pembelian.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import main.component.NumberField;
import model.User;
import module.pembelian.model.Employee;
import module.pembelian.model.Grade;
import module.pembelian.model.PalletCard;
import module.pembelian.model.Product;
import module.pembelian.model.ReceivedDetail;
import module.pembelian.model.Thickness;
import controller.ReceivedDAOFactory;

public class EditPopUpPalletCard extends JDialog{

	JLabel noPalletLbl;
	JLabel palletCardCodeLbl;
	JLabel gradeLbl;
	JLabel longLbl;
	JLabel wideLbl;
	JLabel thickLbl;
	JLabel totalLbl;
	JLabel volumeLbl;
	JLabel totalLogLbl;
	JLabel totalVolumeLbl;
	JLabel productNameLbl;
	JLabel descriptionLbl;
	JLabel uomLongLbl;
	JLabel uomWideLbl;
	JLabel uomThickLbl;
	JLabel uomTotalLbl;
	JLabel uomVolumeLbl;
	JLabel uomTotalLogLbl;
	JLabel uomTotalVolumeLbl;
	
	JLabel errorNoPallet;
	JLabel errorGraderLbl;
	JLabel errorGradeLbl;
	JLabel errorLongLbl;
	JLabel errorWideLbl;
	JLabel errorThickLbl;
	JLabel errorTotalLbl;
	JLabel errorVolumeLbl;
	JLabel productCode;
	
	NumberField noPalletCardField;
	NumberField longField;
	NumberField wideField;
	NumberField thicknessField;
	NumberField totalField;
	
	JTextField codePalletCardField;
	JTextField volumeField;
	JTextField totalLogField;
	JTextField totalVolumeField;
	JTextField productNameField;
	
	JTextArea descriptionArea;
	
	JButton insertButton;
	JButton confirmButton;
	
	JTable pcTable;
	JScrollPane pcScrollPane;
	
	PCTableModel pcTableModel;
	List<PalletCard> pcs;
	List<Grade> grades;
	List<Thickness> thicknesses;
	List<Employee> employees;
	List<Product> products;
	Map<Double, Map<Double, Map<Double, Product>>> productMap;
	AddReceivedDetailPanel addReceivedDetail;
	boolean editMode = false;
	int indexEdit = 0;
	int index;
	ReceivedDetail receivedDetail;
	public EditPopUpPalletCard(AddReceivedDetailPanel parent, ReceivedDetail receivedDetail, int index) {
		addReceivedDetail = parent;
		setLayout(null);
		setTitle("Kartu Pallet");
		setSize(800, 750);
		
		//Grade
		gradeLbl = new JLabel("Grade : " + addReceivedDetail.gradeComboBox.getDataIndex().getGrade());
		gradeLbl.setFont(new Font("Tahoma",Font.BOLD,20));
		gradeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		gradeLbl.setOpaque(true);
		gradeLbl.setBackground(Color.white);
		gradeLbl.setBounds(600,30,150,50);
		add(gradeLbl);

		
		//Nomor Pallet
		noPalletLbl = new JLabel("No Pallet");
		noPalletLbl.setBounds(30,30,150,20);
		add(noPalletLbl);
		
		noPalletCardField = new NumberField(4);
		noPalletCardField.setBounds(150, 30, 150, 20);
		add(noPalletCardField);
		
		errorNoPallet = new JLabel();
		errorNoPallet.setBounds(320, 30, 180, 20);
		add(errorNoPallet);
		
		//Code Pallet
		palletCardCodeLbl = new JLabel("Kode Kartu Pallet");
		palletCardCodeLbl.setBounds(30,70,150,20);
		add(palletCardCodeLbl);
		
		codePalletCardField = new JTextField();
		codePalletCardField.setBounds(150, 70, 150, 20);
		add(codePalletCardField);
	
		//Long 
		longLbl = new JLabel("Panjang");
		longLbl.setBounds(30,110,150,20);
		add(longLbl);
		
		longField = new NumberField(10);
		longField.setBounds(150, 110, 150, 20);
		add(longField);
		
		uomLongLbl = new JLabel("cm");
		uomLongLbl.setBounds(302,110,15,20);
		add(uomLongLbl);
		
		errorLongLbl = new JLabel();
		errorLongLbl.setBounds(325,110,150,20);
		add(errorLongLbl);
		
		//Thickness
		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(30,150,100,20);
		add(thickLbl);
		
		thicknessField = new NumberField(10);
		thicknessField.setBounds(150, 150, 150, 20);
		add(thicknessField);
		
		uomThickLbl = new JLabel("cm");
		uomThickLbl.setBounds(302,150,15,20);
		add(uomThickLbl);
		
		errorThickLbl = new JLabel();
		errorThickLbl.setBounds(325,150,150,20);
		add(errorThickLbl);
		
		//Wide
		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(30,190,100,20);
		add(wideLbl);
		
		wideField = new NumberField(10);
		wideField.setBounds(150, 190, 150, 20);
		add(wideField);
		
		uomWideLbl = new JLabel("cm");
		uomWideLbl.setBounds(302,190,15,20);
		add(uomWideLbl);
		
		errorWideLbl = new JLabel();
		errorWideLbl.setBounds(325,190,150,20);
		add(errorWideLbl);
		
	
	
		//Total
		totalLbl = new JLabel("Jumlah");
		totalLbl.setBounds(30,230,100,20);
		add(totalLbl);
	
		totalField = new NumberField(10);
		totalField.setBounds(150, 230, 150, 20);
		add(totalField);
		
		uomTotalLbl = new JLabel("batang");
		uomTotalLbl.setBounds(302,230,40,20);
		add(uomTotalLbl);
		
		errorTotalLbl = new JLabel();
		errorTotalLbl.setBounds(352,230,150,20);
		add(errorTotalLbl);
		
		//Volume
		volumeLbl = new JLabel("Volume");
		volumeLbl.setBounds(30,270,100,20);
		add(volumeLbl);
			
		volumeField = new JTextField();
		volumeField.setBounds(150, 270, 150, 20);
		add(volumeField);
		
		uomVolumeLbl = new JLabel("<html><span>cm&#179;</span></html>");
		uomVolumeLbl.setBounds(302,270,16,20);
		add(uomVolumeLbl);
		
		errorVolumeLbl = new JLabel();
		errorVolumeLbl.setBounds(325,270,150,20);
		add(errorVolumeLbl);
		
		
		//ProductName
		productNameLbl = new JLabel("Nama Produk");
		productNameLbl.setBounds(30,310,150,20);
		add(productNameLbl);
		
		productNameField = new JTextField();
		productNameField.setBounds(150, 310, 150, 20);
		add(productNameField);

		productCode = new JLabel();
		productCode.setBounds(800, 100, 310, 30);
		productCode.setVisible(false);
		add(productCode);
		
		//Description Area
		descriptionLbl = new JLabel("Keterangan");
		descriptionLbl.setBounds(30,350,150,20);
		add(descriptionLbl);
		
		descriptionArea = new JTextArea();
		descriptionArea.setBounds(150, 350, 150, 50);
		add(descriptionArea);		
		
		//insert Button
		insertButton = new JButton("Insert");
		insertButton.setBounds(360,420,150,30);
		add(insertButton);
		
		//Table pc
		
		pcs = new ArrayList<>();
		pcTableModel = new PCTableModel(pcs);
		pcTable = new JTable(pcTableModel);
		
		pcScrollPane = new JScrollPane(pcTable);
		pcScrollPane.setBounds(30,460,740,100);
		add(pcScrollPane);
		
		//Total Log
		totalLogLbl = new JLabel("Total Jumlah Kayu");
		totalLogLbl.setBounds(30,580,150,20);
		add(totalLogLbl);
	
		totalLogField =  new JTextField();
		totalLogField.setBounds(350, 580, 150, 20);
		add(totalLogField);
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(502,580,40,20);
		add(uomTotalLogLbl);
		
		//total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(30,620,150,20);
		add(totalVolumeLbl);

		totalVolumeField =  new JTextField();
		totalVolumeField.setBounds(350, 620, 150, 20);
		add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("<html><span>cm&#179;</span></html>");
		uomTotalVolumeLbl.setBounds(502,620,16,20);
		add(uomTotalVolumeLbl);

		//Confirm Btn
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(600,680,150,30);
		add(confirmButton);
		
		totalVolumeField.setEnabled(false);
		totalLogField.setEnabled(false);
		productNameField.setEnabled(false);
		volumeField.setEnabled(false);
		codePalletCardField.setEnabled(false);
		
		changePallet();
		productMap = new HashMap<Double, Map<Double,Map<Double, Product>>>();
		
		try {
			
			products = ReceivedDAOFactory.getProductDAO().getAllProduct(addReceivedDetail.received.getWoodTypeID(), addReceivedDetail.gradeComboBox.getDataIndex().getId());
			for (Product product : products) {
				Map<Double, Product> thicknessMap = new HashMap<Double, Product>();
				Map<Double, Map<Double, Product>> widthMap = new HashMap<>();
				thicknessMap.put(product.getThickness(), product);
				widthMap.put(product.getWidth(), thicknessMap);
				productMap.put(product.getLength(), widthMap);
			}
			this.receivedDetail = receivedDetail;
			this.index = index;
			pcs = receivedDetail.getPallets();
			pcTable.setModel(new PCTableModel(pcs));
			pcTable.updateUI();
		
			int total = 0;
			double volume = 0;
			for(PalletCard pcd : pcs){
				total+=pcd.getTotal();
				volume+=pcd.getVolume();
			}
			totalLogField.setText(total+"");
			totalVolumeField.setText(volume+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		longField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
		});
		
		wideField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
		});
		
		thicknessField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				calculateVolume();
				getProductName();
			}
		});
		
		noPalletCardField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				changePallet();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changePallet();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				changePallet();
			}
		});
	
		pcTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pcTable.columnAtPoint(e.getPoint())==8){
					editMode=true;
					indexEdit=pcTable.getSelectedRow();
					PalletCard pc = pcs.get(pcTable.getSelectedRow());
					codePalletCardField.setText(pc.getPalletCardCode());
					String [] splits = pc.getPalletCardCode().split("/");
					noPalletCardField.setText(splits[0]);
					longField.setText(pc.getLength()+"");
					wideField.setText(pc.getWidth()+"");
					thicknessField.setText(pc.getThickness()+"");
					productNameField.setText(pc.getProductName());
					productCode.setText(pc.getProductCode());
					totalField.setText(pc.getTotal()+"");
					volumeField.setText(pc.getVolume()+"");
					descriptionArea.setText(pc.getDescription());
				}
				if(pcTable.columnAtPoint(e.getPoint())==9){
					pcs.remove(pcTable.getSelectedRow());
					pcTable.updateUI();
					int total = 0;
					double volume = 0;
					for(PalletCard pcd : pcs){
						total+=pcd.getTotal();
						volume+=pcd.getVolume();
					}
					totalLogField.setText(total+"");
					totalVolumeField.setText(volume+"");
					
				}
			}
		});
	
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int error=0;
				if(thicknessField.getText().equals("")){
					errorThickLbl.setText("<html><font color='red'>Tebal diisi</font></html>");
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
				if(noPalletCardField.getText().equals("")||noPalletCardField.getText().equals("0000")){
					errorNoPallet.setText("<html><font color='red'>Nomor Pallet harus diisi !</font></html>");
					error++;
				}else{
					errorNoPallet.setText("");
				}
				
				if(error==0){
					if(!editMode){
						PalletCard pc = new PalletCard();
						pc.setPalletCardCode(codePalletCardField.getText());
						pc.setThickness(Double.valueOf(thicknessField.getText()));
						pc.setLength(Double.valueOf(longField.getText()));
						pc.setWidth(Double.valueOf(wideField.getText()));
						pc.setTotal(Integer.valueOf(totalField.getText()));
						pc.setVolume(Double.valueOf(volumeField.getText()));
						pc.setProductName(productNameField.getText());
						pc.setProductCode(productCode.getText());
						pc.setDescription(descriptionArea.getText());
						pcs.add(pc);
						pcTable.updateUI();
						
						int total = 0;
						double volume = 0;
						for(PalletCard pcd : pcs){
							total+=pcd.getTotal();
							volume+=pcd.getVolume();
						}
						totalLogField.setText(total+"");
						totalVolumeField.setText(volume+"");
						clear();
					}else{
						PalletCard pc = pcs.get(indexEdit);
						pc.setPalletCardCode(codePalletCardField.getText());
						pc.setThickness(Double.valueOf(thicknessField.getText()));
						pc.setTotal(Integer.valueOf(totalField.getText()));
						pc.setVolume(Double.valueOf(volumeField.getText()));
						pc.setProductName(productNameField.getText());
						pc.setLength(Double.valueOf(longField.getText()));
						pc.setWidth(Double.valueOf(wideField.getText()));
						pc.setProductCode(productCode.getText());
						pc.setDescription(descriptionArea.getText());
						pcTable.updateUI();
						int total = 0;
						double volume = 0;
						for(PalletCard pcd : pcs){
							total+=pcd.getTotal();
							volume+=pcd.getVolume();
						}
						totalLogField.setText(total+"");
						totalVolumeField.setText(volume+"");
						clear();
						editMode=false;
						indexEdit=0;
					}
				}
			}
		});		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int error=0;
				
				if(codePalletCardField.getText().equals("")){
					errorNoPallet.setText("<html><font color='red'>Code Pallet harus diisi !</font></html>");
					error++;
				}/*else if(addReceivedDetail.palletMaps.get(codePalletCardField.getText()+"/"+addReceivedDetail.received.getReceivedCode())!=null){
					errorNoPallet.setText("<html><font color='red'>Code Pallet sudah ada !</font></html>");
					error++;
				}*/else{
					errorNoPallet.setText("");
				}
			
				if(error==0){
					ReceivedDetail pallet = addReceivedDetail.receivedDetails.get(index);
					receivedDetail.setGradeID(addReceivedDetail.gradeComboBox.getDataIndex().getId());
					receivedDetail.setGrade(addReceivedDetail.gradeComboBox.getDataIndex().getGrade());
					receivedDetail.setTotalLog(Integer.valueOf(totalLogField.getText()));
					receivedDetail.setTotalVolume(Double.valueOf(totalVolumeField.getText()));
					receivedDetail.setPallets(pcs);
					addReceivedDetail.receivedDetails.add(receivedDetail);
					addReceivedDetail.receivedDetailTable.updateUI();
					dispose();
					
				}
			}
		});
	
	}
	
	public void clear(){
		noPalletCardField.setText("");
		longField.setText("");
		wideField.setText("");
		thicknessField.setText("");
		productCode.setText("");
		productNameField.setText("");
		totalField.setText("");
		volumeField.setText("");
		descriptionArea.setText("");
	}
	
	public void getProductName(){
		if(!thicknessField.getText().equals("") && !wideField.getText().equals("") && !longField.getText().equals("")){
			if(productMap.get(Double.valueOf(longField.getText()))!=null){
				if(productMap.get(Double.valueOf(longField.getText())).get(Double.valueOf(wideField.getText()))!=null){
					if(productMap.get(Double.valueOf(longField.getText())).get(Double.valueOf(wideField.getText())).get(Double.valueOf(thicknessField.getText()))!=null){
						productNameField.setText(productMap.get(Double.valueOf(longField.getText())).get(Double.valueOf(wideField.getText())).get(Double.valueOf(thicknessField.getText())).getProductName());
						productCode.setText(productMap.get(Double.valueOf(longField.getText())).get(Double.valueOf(wideField.getText())).get(Double.valueOf(thicknessField.getText())).getProductCode());
					}else{
						productNameField.setText("");
						productCode.setText("");
					}
				}else{
					productNameField.setText("");
					productCode.setText("");
				}
			}else{
				productNameField.setText("");
				productCode.setText("");
			}
		}else {
			productNameField.setText("");
			productCode.setText("");
		}
	}
	
	public void changePallet(){
		String noPallet = "";
		if(!noPalletCardField.getText().equals("")){
			int tempNo = Integer.valueOf(noPalletCardField.getText());
			if(String.valueOf(tempNo).length()==1){
				noPallet ="000"+String.valueOf(tempNo);
			}else if(String.valueOf(tempNo).length()==2){
				noPallet ="00"+String.valueOf(tempNo);
			}else if(String.valueOf(tempNo).length()==3){
				noPallet ="0"+String.valueOf(tempNo);
			}else{
				noPallet =String.valueOf(tempNo);
			}
		}else{
			noPallet="0000";
		}
		codePalletCardField.setText(noPallet+"/"+addReceivedDetail.received.getReceivedCode());
	}
	
	public void calculateVolume(){
		if(!longField.getText().equals("")&&!wideField.getText().equals("")&&!thicknessField.getText().equals("")){
			double volume = Double.valueOf(longField.getText())*Double.valueOf(wideField.getText())*Double.valueOf(thicknessField.getText());
			volumeField.setText(volume+"");
		}
	}
	
	private class PCTableModel extends AbstractTableModel {
	    private List<PalletCard> palletCards;
	    
	    public PCTableModel(List<PalletCard> pcs) {
	        this.palletCards = pcs;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return palletCards.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 10;
	    }
	    
	    /**
	     * Method to get selected value
	     * @param rowIndex rowIndex of selected table
	     * @param columnIndex columnIndex of selected table 
	     * @return ({@link User}) Object 
	     */
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	PalletCard p = palletCards.get(rowIndex);
	        switch(columnIndex){
	        	case 0:
	        		return p.getPalletCardCode();
	            case 1 : 
	                return p.getLength();
	            case 2 :
	                return p.getWidth();
	            case 3 :
	                return p.getThickness();
	            case 4 :
	                return p.getTotal();
	            case 5 :
	                return p.getVolume();
	            case 6 :
	            	return p.getProductName();
	            case 7 :
	                return p.getDescription();
	            case 8 :
	                return "Edit";
	            case 9 :
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
	                return "Kode Kartu Pallet";
	            case 1 :
	                return "Panjang";
	            case 2 :
	                return "Tebal";
	            case 3 :
	            	return "Lebar";
	            case 4 :
	                return "Jumlah";
	            case 5 :
	                return "Volume";
	            case 6 :
	                return "Nama Produk";
	            case 7 :
	                return "Keterangan";
	            case 8 :
	                return "Action";
	            case 9 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}

	
}
