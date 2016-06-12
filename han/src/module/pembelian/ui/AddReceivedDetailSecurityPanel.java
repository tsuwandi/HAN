package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controller.ReceivedDAOFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import module.pembelian.model.Delivery;
import module.pembelian.model.DocumentType;
import module.pembelian.model.Received;
import module.pembelian.model.SupplierVehicle;
import module.pembelian.model.WoodResource;
import module.pembelian.model.WoodType;
import module.util.Bridging;

public class AddReceivedDetailSecurityPanel extends JPanel implements Bridging{
	
	JLabel receivedCodeLbl;
	JLabel receivedDateLbl;
	JLabel ritNumberLbl;
	JLabel supplierLbl;
	JLabel licensePlateLbl;
	JLabel driverLbl;
	JLabel woodDomicileLbl;
	JLabel docNoLbl;
	JLabel woodResourceLbl;
	JLabel woodTypeLbl;
	JLabel firstCodeSeparator;
	JLabel secondCodeSeparator;
	JLabel thirdCodeSeparator;
	JLabel driverIDLbl;
	JLabel documentTypeLbl;
	JLabel totalLogLbl;
	JLabel totalVolumeLbl;
	JLabel uomTotalLogLbl;
	JLabel uomTotalVolumeLbl;
	
	JLabel errorCodeLbl;
	JLabel errorRitNumberLbl;
	JLabel errorLicenseLbl;
	JLabel errorDocNoLbl;
	JLabel errorWoodTypeLbl;
	JLabel errorDriverLbl;
	JLabel errorDriverIDLbl;
	JLabel errorDocTypeLbl;
	JLabel errorWoodResourceLbl;
	JLabel errorTotalLogLbl;
	JLabel errorTotalVolumeLbl;
	JLabel errorWoodDomicileLbl;
	
	NumberField receivedCodeField;
	NumberField receivedCodeDateField;
	NumberField receivedCodeMonthField;
	NumberField receivedCodeYearField;
	NumberField ritNumberField;
	NumberField totalLogField;
	NumberField totalVolumeField;
	
	JTextField driverField;
	JTextField woodDomicileField;
	JTextField supplierTextField;
	JTextField driverIDField;
	JTextField docNoField;
	
	
	ComboBox<SupplierVehicle> licensePlateComboBox;
	ComboBox<WoodType> woodTypeComboBox;
	ComboBox<DocumentType> docTypeComboBox;
	ComboBox<WoodResource> woodResourceComboBox;

	JScrollPane palletScrollPane;
	JScrollPane dockingPicScrollPane;
	
	JDateChooser receivedDateChooser;
	
	JButton saveBtn;

	List<SupplierVehicle> suppVehicles;
	List<WoodResource> woodResources;
	List<DocumentType> documentTypes;
	List<WoodType> woodTypes;
	
	AddReceivedDetailSecurityPanel parent;
	JScrollPane scrollPane;
	JPanel containerPnl;
	
	public AddReceivedDetailSecurityPanel(){
		setLayout(null);
		this.parent = this;
		
		containerPnl = new JPanel();
//		containerPnl.setPreferredSize(new Dimension(1100, 840));
		containerPnl.setBounds(0, 0, 1100, 630);
		containerPnl.setLayout(null);
		add(containerPnl);
		
		//Code Label
		receivedCodeLbl = new JLabel("Kode Penerimaan");
		receivedCodeLbl.setBounds(50,70,150,20);
		containerPnl.add(receivedCodeLbl);
		
		firstCodeSeparator = new JLabel("/BL/");
		firstCodeSeparator.setBounds(320,70,20,20);
		firstCodeSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		containerPnl.add(firstCodeSeparator);

		secondCodeSeparator = new JLabel("/");
		secondCodeSeparator.setBounds(370,70,10,20);
		secondCodeSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		containerPnl.add(secondCodeSeparator);
		
		thirdCodeSeparator = new JLabel("/");
		thirdCodeSeparator.setBounds(410,70,10,20);
		thirdCodeSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		containerPnl.add(thirdCodeSeparator);
		
		receivedCodeField = new NumberField(10);
		receivedCodeField.setBounds(220, 70, 100, 20);
		containerPnl.add(receivedCodeField);
		
		receivedCodeDateField = new NumberField(10);
		receivedCodeDateField.setBounds(340, 70, 30, 20);
		containerPnl.add(receivedCodeDateField);
		
		receivedCodeMonthField = new NumberField(10);
		receivedCodeMonthField.setBounds(380, 70, 30, 20);
		containerPnl.add(receivedCodeMonthField);
		
		receivedCodeYearField = new NumberField(10);
		receivedCodeYearField.setBounds(420, 70, 50, 20);
		containerPnl.add(receivedCodeYearField);
		
		errorCodeLbl = new JLabel();
		errorCodeLbl.setBounds(480,70,180,20);
		containerPnl.add(errorCodeLbl);
		
		
		//Receive Date
		receivedDateLbl = new JLabel("Tanggal Penerimaan");
		receivedDateLbl.setBounds(50,110,150,20);
		containerPnl.add(receivedDateLbl);
		
		receivedDateChooser = new JDateChooser();
		receivedDateChooser.setBounds(220,110,150,20);
		receivedDateChooser.setDate(new Date());
		containerPnl.add(receivedDateChooser);
		
		//Rit Number
		ritNumberLbl = new JLabel("No Rit");
		ritNumberLbl.setBounds(50,150,150,20);
		containerPnl.add(ritNumberLbl);
		
		ritNumberField = new NumberField(10);
		ritNumberField.setBounds(220, 150, 150, 20);
		containerPnl.add(ritNumberField);
		
		errorRitNumberLbl = new JLabel();
		errorRitNumberLbl.setBounds(380,150,180,20);
		containerPnl.add(errorRitNumberLbl);
		
		//License Plate
		licensePlateLbl = new JLabel("No Kendaraan");
		licensePlateLbl.setBounds(50,190,150,20);
		containerPnl.add(licensePlateLbl);
		
		licensePlateComboBox = new ComboBox<SupplierVehicle>();
		licensePlateComboBox.setBounds(220,190,150,20);
		containerPnl.add(licensePlateComboBox);
		
		errorLicenseLbl = new JLabel();
		errorLicenseLbl.setBounds(380,190,180,20);
		containerPnl.add(errorLicenseLbl);
		
		//Supplier
		supplierLbl = new JLabel("Supplier");
		supplierLbl.setBounds(50,230,150,20);
		containerPnl.add(supplierLbl);
		
		supplierTextField = new JTextField();
		supplierTextField.setBounds(220,230,150,20);
		containerPnl.add(supplierTextField);
		
		//Driver
		driverLbl =  new JLabel("Supir");
		driverLbl.setBounds(50,270,150,20);
		containerPnl.add(driverLbl);
		
		driverField = new JTextField();
		driverField.setBounds(220, 270, 150, 20);
		containerPnl.add(driverField);
		
		errorDriverLbl = new JLabel();
		errorDriverLbl.setBounds(380,270,180,20);
		containerPnl.add(errorDriverLbl);
		
		//Driver Id
		driverIDLbl =  new JLabel("KTP Supir");
		driverIDLbl.setBounds(50,310,150,20);
		containerPnl.add(driverIDLbl);
		
		driverIDField = new JTextField();
		driverIDField.setBounds(220, 310, 150, 20);
		containerPnl.add(driverIDField);
		
		errorDriverIDLbl = new JLabel();
		errorDriverIDLbl.setBounds(380,310,180,20);
		containerPnl.add(errorDriverIDLbl);
		
	
		// Document Number
		docNoLbl =  new JLabel("No Dokumen");
		docNoLbl.setBounds(550,70,150,20);
		containerPnl.add(docNoLbl);
		
		docNoField = new JTextField();
		docNoField.setBounds(720, 70, 150, 20);
		containerPnl.add(docNoField);
		
		errorDocNoLbl = new JLabel();
		errorDocNoLbl.setBounds(890,70,180,20);
		containerPnl.add(errorDocNoLbl);
		
		
		// Document Type
		documentTypeLbl =  new JLabel("Tipe Dokumen");
		documentTypeLbl.setBounds(550,110,150,20);
		containerPnl.add(documentTypeLbl);
		
		docTypeComboBox = new ComboBox<>();
		docTypeComboBox.setBounds(720, 110, 150, 20);
		containerPnl.add(docTypeComboBox);
		
		errorDocTypeLbl = new JLabel();
		errorDocTypeLbl.setBounds(890,110,180,20);
		containerPnl.add(errorDocTypeLbl);

		
		//Wood Domicile
		woodDomicileLbl = new JLabel("Asal Barang");
		woodDomicileLbl.setBounds(550,150,150,20);
		containerPnl.add(woodDomicileLbl);
		
		woodDomicileField = new JTextField();
		woodDomicileField.setBounds(720, 150, 150, 20);
		containerPnl.add(woodDomicileField);
		
		errorWoodDomicileLbl = new JLabel();
		errorWoodDomicileLbl.setBounds(890,150,150,20);
		containerPnl.add(errorWoodDomicileLbl);
	
		//Wood Resource
		woodResourceLbl = new JLabel("Asal Sumber Bahan Baku");
		woodResourceLbl.setBounds(550, 190, 150, 20);
		containerPnl.add(woodResourceLbl);
		
		woodResourceComboBox = new ComboBox<WoodResource>();
		woodResourceComboBox.setBounds(720, 190, 150, 20);
		containerPnl.add(woodResourceComboBox);
		
		errorWoodResourceLbl = new JLabel();
		errorWoodResourceLbl.setBounds(890, 190, 150, 20);
		containerPnl.add(errorWoodResourceLbl);
		
		//Wood Type
		woodTypeLbl = new JLabel("Tipe Kayu");
		woodTypeLbl.setBounds(550, 230, 150, 20);
		containerPnl.add(woodTypeLbl);
		
		woodTypeComboBox = new ComboBox<>();
		woodTypeComboBox.setBounds(720, 230, 150, 20);
		containerPnl.add(woodTypeComboBox);
		
		errorWoodTypeLbl = new JLabel();
		errorWoodTypeLbl.setBounds(890,230,150,20);
		containerPnl.add(errorWoodTypeLbl);
		
		//Total Log
		totalLogLbl = new JLabel("Total Log");
		totalLogLbl.setBounds(550,270,150,20);
		containerPnl.add(totalLogLbl);
		
		totalLogField = new NumberField(10);
		totalLogField.setBounds(720, 270, 150, 20);
		containerPnl.add(totalLogField);
		
		uomTotalLogLbl = new JLabel("Batang");
		uomTotalLogLbl.setBounds(880,270,40,20);
		containerPnl.add(uomTotalLogLbl);
		
		errorTotalLogLbl = new JLabel();
		errorTotalLogLbl.setBounds(930,270,150,20);
		containerPnl.add(errorTotalLogLbl);
		
		//Total Volume
		totalVolumeLbl = new JLabel("Total Log");
		totalVolumeLbl.setBounds(550,310,150,20);
		containerPnl.add(totalVolumeLbl);
		
		totalVolumeField = new NumberField(10);
		totalVolumeField.setBounds(720, 310, 150, 20);
		containerPnl.add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("M3");
		uomTotalVolumeLbl.setBounds(880,310,50,20);
		containerPnl.add(uomTotalVolumeLbl);
		
		errorTotalVolumeLbl = new JLabel();
		errorTotalVolumeLbl.setBounds(930,310,150,20);
		containerPnl.add(errorTotalVolumeLbl);

		saveBtn = new JButton("Save");
		saveBtn.setBounds(480,540,100,30);
		containerPnl.add(saveBtn);
		
		getLastCode();
		ritNumberField.setText(receivedCodeField.getText());
		ritNumberField.setEnabled(false);

		try {
			suppVehicles = ReceivedDAOFactory.getSupplierVehicleDAO().getListSuppVehicle();
			suppVehicles.add(0,new SupplierVehicle("--Pilih--"));
			licensePlateComboBox.setList(suppVehicles);

			woodTypes = ReceivedDAOFactory.getWoodTypeDAO().getWoodType();
			woodTypes.add(0,new WoodType("--Pilih--"));
			woodTypeComboBox.setList(woodTypes);
			
			documentTypes = ReceivedDAOFactory.getDocumentTypeDAO().getDocumentType();
			documentTypes.add(0,new DocumentType("--Pilih--"));
			docTypeComboBox.setList(documentTypes);
			
			woodResources = ReceivedDAOFactory.getWoodResourceDAO().getWoodResource();
			woodResources.add(0,new WoodResource("--Pilih--"));
			woodResourceComboBox.setList(woodResources);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		supplierTextField.setEnabled(false);
		receivedCodeField.setEnabled(false);
		receivedCodeDateField.setEnabled(false);
		receivedCodeMonthField.setEnabled(false);
		receivedCodeYearField.setEnabled(false);
		receivedCodeDateField.setText(new SimpleDateFormat("dd").format(new Date()));
		receivedCodeMonthField.setText(new SimpleDateFormat("MM").format(new Date()));
		receivedCodeYearField.setText(new SimpleDateFormat("yy").format(new Date()));
		
		ritNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_BACK_SPACE)
					if(ritNumberField.getText().length()!=0)		
						receivedCodeField.setText(ritNumberField.getText().substring(0, ritNumberField.getText().length()-1));
					else
						receivedCodeField.setText(ritNumberField.getText()+e.getKeyChar());
				else
					receivedCodeField.setText(ritNumberField.getText()+e.getKeyChar());
			}
		});
		
		licensePlateComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(licensePlateComboBox.getSelectedIndex()!=0){
					supplierTextField.setText(licensePlateComboBox.getDataIndex().getSupplierName());
				}else{
					supplierTextField.setText("");
				}
			}
		});
		
	
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int error = 0;
				if(receivedCodeDateField.getText().equals("")||receivedCodeField.getText().equals("")||receivedCodeMonthField.getText().equals("")||receivedCodeYearField.getText().equals("")){
					errorCodeLbl.setText("<html><font color='red'>Code tidak bole kosong !</font></html>");
					error++;
				}else{
					errorCodeLbl.setText("");
				}
				if(ritNumberField.getText().equals("")){
					errorRitNumberLbl.setText("<html><font color='red'>Nomor RIT tidak bole kosong !</font></html>");
					error++;
				}else{
					errorRitNumberLbl.setText("");
				}
				if(driverField.getText().equals("")){
					errorDriverLbl.setText("<html><font color='red'>Nama Supir tidak bole kosong !</font></html>");
					error++;
				}else{
					errorDriverLbl.setText("");
				}
				if(driverIDField.getText().equals("")){
					errorDriverIDLbl.setText("<html><font color='red'>KTP Supir tidak bole kosong !</font></html>");
					error++;
				}else{
					errorDriverIDLbl.setText("");
				}
				if(licensePlateComboBox.getSelectedIndex()==0){
					errorLicenseLbl.setText("<html><font color='red'>Plat Nomor harus dipilih !</font></html>");
					error++;
				}else{
					errorLicenseLbl.setText("");
				}
				if(woodTypeComboBox.getSelectedIndex()==0){
					errorWoodTypeLbl.setText("<html><font color='red'>Tipe Kayu harus dipilih !</font></html>");
					error++;
				}else{
					errorWoodTypeLbl.setText("");
				}
				if(docTypeComboBox.getSelectedIndex()==0){
					errorDocTypeLbl.setText("<html><font color='red'>Tipe Dokumen harus dipilih !</font></html>");
					error++;
				}else{
					errorDocTypeLbl.setText("");
				}
				
				if(docNoField.getText().equals("")){
					errorDocNoLbl.setText("<html><font color='red'>Nomor Dokumen harus diisi !</font></html>");
					error++;
				}else{
					errorDocNoLbl.setText("");
				}
				if(woodResourceComboBox.getSelectedIndex()==0){
					errorWoodResourceLbl.setText("<html><font color='red'>Asal Sumber harus dipilih !</font></html>");
					error++;
				}else{
					errorWoodResourceLbl.setText("");
				}
				
				if(woodDomicileField.getText().equals("")){
					errorWoodDomicileLbl.setText("<html><font color='red'>Asal Barang harus diisi !</font></html>");
					error++;
				}else{
					errorWoodDomicileLbl.setText("");
				}
				
				if(totalLogField.getText().equals("")){
					errorTotalLogLbl.setText("<html><font color='red'>Total Kayu harus dipilih !</font></html>");
					error++;
				}else{
					errorTotalLogLbl.setText("");
				}
				
				if(totalVolumeField.getText().equals("")){
					errorTotalVolumeLbl.setText("<html><font color='red'>Total Volume harus diisi !</font></html>");
					error++;
				}else{
					errorTotalVolumeLbl.setText("");
				}
				
				if(error==0){
					Received rec = new Received();
					String code = receivedCodeField.getText()+firstCodeSeparator.getText()
							+receivedCodeDateField.getText()+secondCodeSeparator.getText()
							+receivedCodeMonthField.getText()+thirdCodeSeparator.getText()+receivedCodeYearField.getText();
					rec.setDeliveryNote(docNoField.getText());
					rec.setDriver(driverField.getText());
					rec.setLicensePlate(licensePlateComboBox.getDataIndex().getLicensePlate());
					rec.setReceivedCode(code);
					rec.setRitNo(ritNumberField.getText());
					rec.setSupplier(supplierTextField.getText());
					rec.setWoodTypeID(woodTypeComboBox.getDataIndex().getId());
					rec.setReceivedDate(receivedDateChooser.getDate());
					rec.setDriverID(driverIDField.getText());
					
					Delivery del = new Delivery();
					del.setDeliveryNote(docNoField.getText());
					del.setDocumentTypeID(docTypeComboBox.getDataIndex().getId());
					del.setWoodDomicile(woodDomicileField.getText());
					del.setWoodResourceId(woodResourceComboBox.getDataIndex().getId());
					del.setWoodTypeID(woodTypeComboBox.getDataIndex().getId());
					del.setTotalLog(Integer.valueOf(totalLogField.getText()));
					del.setTotalVolume(Double.valueOf(totalVolumeField.getText()));
					try {
						ReceivedDAOFactory.getReceivedDAO().save(rec);
						ReceivedDAOFactory.getDeliveryDAO().save(del);
						DialogBox.showInsert();
						MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	
	}
	
	public void getLastCode(){
		try {
			Date date = new Date();
			if(date.getDate()==1){
				receivedCodeField.setText("0001");
			}else{
				String codeTemp = ReceivedDAOFactory.getReceivedDAO().getLastCode();
				if(codeTemp==null){
					receivedCodeField.setText("0001");
				}else{
					String [] splittedCode = codeTemp.split("/");
					int tempIntCode = Integer.valueOf(splittedCode[0])+1;
					String textTemp = String.valueOf(tempIntCode);
					if(textTemp.length()==1){
						receivedCodeField.setText("000"+textTemp);
					}else if(textTemp.length()==2){
						receivedCodeField.setText("00"+textTemp);
					}else if(textTemp.length()==3){
						receivedCodeField.setText("0"+textTemp);
					}else{
						receivedCodeField.setText(textTemp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void invokeObjects(Object... objects) {
	
	}
}