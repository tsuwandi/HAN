package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ReceivedDAOFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import main.panel.MainPanel;
import module.pembelian.model.Delivery;
import module.pembelian.model.DocumentType;
import module.pembelian.model.Received;
import module.pembelian.model.SupplierCP;
import module.pembelian.model.WoodResource;
import module.sn.woodtype.model.WoodType;
import module.supplier.model.Supplier;
import module.util.Bridging;

public class ViewReceivedDetailSecurityPanel extends JPanel implements Bridging{
	Logger log = LogManager.getLogger(ViewReceivedDetailSecurityPanel.class.getName());
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
	JLabel supplierCPLbl;
	JLabel supplierAddressLbl;
	JLabel docDateLbl;
	
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
	JLabel errorSupplierLbl;
	JLabel errorSupplierCPLbl;
	
	NumberField receivedCodeField;
	NumberField receivedCodeDateField;
	NumberField receivedCodeMonthField;
	NumberField receivedCodeYearField;
	NumberField ritNumberField;
	NumberField totalLogField;
	NumberField totalVolumeField;
	
	TextField driverField;
	TextField woodDomicileField;
	TextField driverIDField;
	TextField docNoField;
	TextField licensePlateField;
	TextField docTypeField;
	
	JTextArea supplierAddressArea;
	
	ComboBox<SupplierCP> supplierCPComboBox;
	ComboBox<WoodType> woodTypeComboBox;
	
	ComboBox<WoodResource> woodResourceComboBox;
	ComboBox<Supplier> supplierComboBox;
	
	JScrollPane palletScrollPane;
	JScrollPane dockingPicScrollPane;

	JDateChooser receivedDateChooser;
	JDateChooser docDateChooser;

	JButton editBtn;
	JButton printBtn;
	JButton backBtn;

	List<Supplier> suppliers;
	List<SupplierCP> supplierCPs;
	List<WoodResource> woodResources;
	List<DocumentType> documentTypes;
	List<WoodType> woodTypes;

	ViewReceivedDetailSecurityPanel parent;
	JScrollPane scrollPane;
	JPanel containerPnl;

	Received received;
	Delivery delivery;

	public ViewReceivedDetailSecurityPanel(){
		createGUI();
		setData();
		listener();
	}
	
	public void listener(){
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
		
		supplierComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(supplierComboBox.getSelectedIndex()==0){
					supplierCPs.clear();
					supplierCPs.add(new SupplierCP("--Pilih--"));
					supplierCPComboBox.setList(supplierCPs);
					supplierAddressArea.setText("");
				}else{
					try {
						supplierCPs = new ArrayList<>();
						supplierCPs = ReceivedDAOFactory.getSupplierCPDAO().getSuppCPBySupplier(supplierComboBox.getDataIndex().getSuppCode());
						supplierCPs.add(0,new SupplierCP("--Pilih--"));
						supplierCPComboBox.setList(supplierCPs);
					} catch (Exception e2) {
						log.error(e2.getMessage());
						e2.printStackTrace();
					}
				}
			}
		});
		
		supplierCPComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(supplierCPComboBox.getSelectedIndex()!=0){
					supplierAddressArea.setText(supplierCPComboBox.getDataIndex().getSuppAddress());
				}else{
					supplierAddressArea.setText("");
				}
			}
		});

		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editBtn.getText().equals("Simpan")){
					if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)
						MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
				}
				else MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
			}
		});
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(editBtn.getText().equals("Ubah")){
					editBtn.setText("Simpan");
					printBtn.setVisible(false);
					supplierComboBox.setEnabled(true);
					supplierCPComboBox.setEnabled(true);
					licensePlateField.setEnabled(true);
					driverField.setEnabled(true);
					driverIDField.setEnabled(true);
					docNoField.setEnabled(true);
					docTypeField.setEnabled(true);
					totalLogField.setEnabled(true);
					totalVolumeField.setEnabled(true);
					woodDomicileField.setEnabled(true);
					woodResourceComboBox.setEnabled(true);
					woodTypeComboBox.setEnabled(true);
				}else{
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
					/*if(driverField.getText().equals("")){
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
					}*/
					if(supplierComboBox.getSelectedIndex()==0){
						errorSupplierLbl.setText("<html><font color='red'>Supplier harus dipilih !</font></html>");
						error++;
					}else{
						errorSupplierLbl.setText("");
					}
					if(supplierCPComboBox.getSelectedIndex()==0){
						errorSupplierCPLbl.setText("<html><font color='red'>Sub Supplier harus dipilih !</font></html>");
						error++;
					}
					else{
						errorSupplierCPLbl.setText("");
					}
					/*if(licensePlateField.getText().equals("")){
						errorLicenseLbl.setText("<html><font color='red'>Plat Nomor harus diisi !</font></html>");
						error++;
					}else{
						errorLicenseLbl.setText("");
					}*/
				
					if(woodTypeComboBox.getSelectedIndex()==0){
						errorWoodTypeLbl.setText("<html><font color='red'>Jenis Kayu harus dipilih !</font></html>");
						error++;
					}else{
						errorWoodTypeLbl.setText("");
					}
					if(docTypeField.getText().equals("")){
						errorDocTypeLbl.setText("<html><font color='red'>Tipe Dokumen harus diisi !</font></html>");
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
					
					/*if(totalLogField.getText().equals("")){
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
					}*/
					
					if(error==0){
						if(DialogBox.showEditChoice()==JOptionPane.YES_OPTION){
							Received rec = new Received();
							String code = receivedCodeField.getText()+firstCodeSeparator.getText()
							+receivedCodeDateField.getText()+secondCodeSeparator.getText()
							+receivedCodeMonthField.getText()+thirdCodeSeparator.getText()+receivedCodeYearField.getText();
							rec.setDeliveryNote(docNoField.getText());
							rec.setDriver(driverField.getText());
							rec.setLicensePlate(licensePlateField.getText());
							rec.setReceivedCode(code);
							rec.setRitNo(ritNumberField.getText());
							rec.setSupplier(supplierComboBox.getDataIndex().getSuppName());
							rec.setWoodTypeID(woodTypeComboBox.getDataIndex().getId());
							rec.setReceivedDate(receivedDateChooser.getDate());
							rec.setDriverID(driverIDField.getText());
							rec.setSupplierCode(supplierComboBox.getDataIndex().getSuppCode());
							rec.setSupplierCpID(supplierCPComboBox.getDataIndex().getId());
							rec.setId(received.getId());
							
							Delivery del = new Delivery();
							del.setDeliveryNote(docNoField.getText());
							del.setDocIssuedDate(docDateChooser.getDate());
							del.setDocumentType(docTypeField.getText());
							del.setWoodDomicile(woodDomicileField.getText());
							del.setWoodResourceId(woodResourceComboBox.getDataIndex().getId());
							del.setWoodTypeID(woodTypeComboBox.getDataIndex().getId());
							if(totalLogField.getText().equals("")) del.setTotalLog(0);
							else del.setTotalLog(Integer.valueOf(totalLogField.getText()));
							if(totalVolumeField.getText().equals("")) del.setTotalVolume(0);
							else del.setTotalVolume(Double.valueOf(totalVolumeField.getText()));
							del.setId(delivery.getId());
							try {
								ReceivedDAOFactory.getReceivedDAO().update(rec);
								ReceivedDAOFactory.getDeliveryDAO().update(del);
								DialogBox.showEdit();
								MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
							} catch (Exception e) {
								log.error(e.getMessage());
								e.printStackTrace();
							}
						}
					}

				}
			}
		});
	}
	public void setData(){

		try {
			supplierCPs = new ArrayList<>();
			supplierCPs.add(new SupplierCP("--Pilih--"));
			supplierCPComboBox.setList(supplierCPs);

			suppliers = ReceivedDAOFactory.getSupplierDAO().getAll();
			suppliers.add(0,new Supplier("--Pilih--"));
			supplierComboBox.setList(suppliers);
			
			woodTypes = ReceivedDAOFactory.getWoodTypeDAO().getWoodType();
			woodTypes.add(0,new WoodType("--Pilih--"));
			woodTypeComboBox.setList(woodTypes);

			woodResources = ReceivedDAOFactory.getWoodResourceDAO().getWoodResource();
			woodResources.add(0,new WoodResource("--Pilih--"));
			woodResourceComboBox.setList(woodResources);
		
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			DialogBox.showError("Tidak Dapat Terhubung ke Database");
		}
		
		supplierAddressArea.setEnabled(false);
		receivedCodeField.setEnabled(false);
		receivedCodeDateField.setEnabled(false);
		receivedCodeMonthField.setEnabled(false);
		receivedCodeYearField.setEnabled(false);

		receivedCodeDateField.setText(new SimpleDateFormat("dd").format(new Date()));
		receivedCodeMonthField.setText(new SimpleDateFormat("MM").format(new Date()));
		receivedCodeYearField.setText(new SimpleDateFormat("yy").format(new Date()));
	}
	
	public void createGUI(){
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
		receivedDateLbl = new JLabel("<html>Tanggal Penerimaan <font color='red'>*</font></html>");
		receivedDateLbl.setBounds(50,110,150,20);
		containerPnl.add(receivedDateLbl);
		
		receivedDateChooser = new JDateChooser();
		receivedDateChooser.setBounds(220,110,150,20);
		receivedDateChooser.setDate(new Date());
		receivedDateChooser.setDateFormatString("dd-MM-yyyy");
//		receivedDateChooser.getDateEditor().setEnabled(false);
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
		

		// Document Number
		docNoLbl =  new JLabel("<html>No Dokumen <font color='red'>*</font></html>");
		docNoLbl.setBounds(50,190,190,20);
		containerPnl.add(docNoLbl);
		
		docNoField = new TextField();
		docNoField.setBounds(220, 190, 150, 20);
		containerPnl.add(docNoField);
		
		errorDocNoLbl = new JLabel();
		errorDocNoLbl.setBounds(380,190,180,20);
		containerPnl.add(errorDocNoLbl);
		
		// Document Date
		docDateLbl =  new JLabel("<html>Tanggal Dokumen <font color='red'>*</font></html>");
		docDateLbl.setBounds(50,230,190,20);
		containerPnl.add(docDateLbl);
		
		docDateChooser = new JDateChooser();
		docDateChooser.setDate(new Date());
		docDateChooser.setDateFormatString("dd-MM-yyyy");
		docDateChooser.setBounds(220, 230, 150, 20);
		containerPnl.add(docDateChooser);
		
		// Document Type
		documentTypeLbl =  new JLabel("<html>Tipe Dokumen <font color='red'>*</font></html>");
		documentTypeLbl.setBounds(50,270,150,20);
		containerPnl.add(documentTypeLbl);
		
		docTypeField = new TextField();
		docTypeField.setBounds(220, 270, 150, 20);
		containerPnl.add(docTypeField);
		
		errorDocTypeLbl = new JLabel();
		errorDocTypeLbl.setBounds(380,270,180,20);
		containerPnl.add(errorDocTypeLbl);

		
		//Wood Domicile
		woodDomicileLbl = new JLabel("<html>Asal Barang <font color='red'>*</font></html>");
		woodDomicileLbl.setBounds(50,310,150,20);
		containerPnl.add(woodDomicileLbl);
		
		woodDomicileField = new TextField();
		woodDomicileField.setBounds(220, 310, 150, 20);
		containerPnl.add(woodDomicileField);
		
		errorWoodDomicileLbl = new JLabel();
		errorWoodDomicileLbl.setBounds(380,310,150,20);
		containerPnl.add(errorWoodDomicileLbl);
	
		//Wood Resource
		woodResourceLbl = new JLabel("<html>Asal Sumber Bahan Baku <font color='red'>*</font></html>");
		woodResourceLbl.setBounds(50, 350, 150, 20);
		containerPnl.add(woodResourceLbl);
		
		woodResourceComboBox = new ComboBox<WoodResource>();
		woodResourceComboBox.setBounds(220, 350, 150, 20);
		containerPnl.add(woodResourceComboBox);
		
		errorWoodResourceLbl = new JLabel();
		errorWoodResourceLbl.setBounds(380, 350, 150, 20);
		containerPnl.add(errorWoodResourceLbl);
		
		//Wood Type
		woodTypeLbl = new JLabel("<html>Jenis Kayu <font color='red'>*</font></html>");
		woodTypeLbl.setBounds(50, 390, 150, 20);
		containerPnl.add(woodTypeLbl);
		
		woodTypeComboBox = new ComboBox<>();
		woodTypeComboBox.setBounds(220, 390, 150, 20);
		containerPnl.add(woodTypeComboBox);
		
		errorWoodTypeLbl = new JLabel();
		errorWoodTypeLbl.setBounds(380,390,150,20);
		containerPnl.add(errorWoodTypeLbl);
		
		//Total Log
		totalLogLbl = new JLabel("Total Log");
		totalLogLbl.setBounds(50,430,150,20);
		containerPnl.add(totalLogLbl);
		
		totalLogField = new NumberField(10);
		totalLogField.setBounds(220, 430, 150, 20);
		containerPnl.add(totalLogField);
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(380,430,40,20);
		containerPnl.add(uomTotalLogLbl);
		
		errorTotalLogLbl = new JLabel();
		errorTotalLogLbl.setBounds(430,430,150,20);
		containerPnl.add(errorTotalLogLbl);
		
		//Total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(50,470,150,20);
		containerPnl.add(totalVolumeLbl);
		
		totalVolumeField = new NumberField(10);
		totalVolumeField.setBounds(220, 470, 150, 20);
		containerPnl.add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("m3");
		uomTotalVolumeLbl.setBounds(380,470,50,20);
		containerPnl.add(uomTotalVolumeLbl);
		
		errorTotalVolumeLbl = new JLabel();
		errorTotalVolumeLbl.setBounds(430,310,150,20);
		containerPnl.add(errorTotalVolumeLbl);

		
		//Supplier
		supplierLbl = new JLabel("<html>Supplier <font color='red'>*</font></html>");
		supplierLbl.setBounds(550,70,150,20);
		containerPnl.add(supplierLbl);
		
		supplierComboBox = new ComboBox<Supplier>();
		supplierComboBox.setBounds(720,70,150,20);
		containerPnl.add(supplierComboBox);
		
		errorSupplierLbl = new JLabel();
		errorSupplierLbl.setBounds(880,70,180,20);
		containerPnl.add(errorSupplierLbl);
		
		//Supplier CP
		supplierCPLbl = new JLabel("<html>Sub Supplier <font color='red'>*</font></html>");
		supplierCPLbl.setBounds(550,110,150,20);
		containerPnl.add(supplierCPLbl);
		
		supplierCPComboBox = new ComboBox<SupplierCP>();
		supplierCPComboBox.setBounds(720,110,150,20);
		containerPnl.add(supplierCPComboBox);
		
		errorSupplierCPLbl = new JLabel();
		errorSupplierCPLbl.setBounds(880, 110, 150, 20);
		containerPnl.add(errorSupplierCPLbl);
		
		//Supplier Address
		supplierAddressLbl = new JLabel("Alamat Supplier");
		supplierAddressLbl.setBounds(550,150,150,20);
		containerPnl.add(supplierAddressLbl);
		
		supplierAddressArea = new JTextArea();
		supplierAddressArea.setBounds(720,150,150,50);
		containerPnl.add(supplierAddressArea);
		
		//License Plate
		licensePlateLbl = new JLabel("No Kendaraan");
		licensePlateLbl.setBounds(550,210,150,20);
		containerPnl.add(licensePlateLbl);
		
		licensePlateField = new TextField();
		licensePlateField.setBounds(720,210,150,20);
		containerPnl.add(licensePlateField);
		
		errorLicenseLbl = new JLabel();
		errorLicenseLbl.setBounds(880,210,180,20);
		containerPnl.add(errorLicenseLbl);
		
		//Driver
		driverLbl =  new JLabel("Supir");
		driverLbl.setBounds(550,250,150,20);
		containerPnl.add(driverLbl);
		
		driverField = new TextField();
		driverField.setBounds(720, 250, 150, 20);
		containerPnl.add(driverField);
		
		errorDriverLbl = new JLabel();
		errorDriverLbl.setBounds(880,250,180,20);
		containerPnl.add(errorDriverLbl);
		
		//Driver Id
		driverIDLbl =  new JLabel("KTP Supir");
		driverIDLbl.setBounds(550,290,150,20);
		containerPnl.add(driverIDLbl);
		
		driverIDField = new TextField();
		driverIDField.setBounds(720, 290, 150, 20);
		containerPnl.add(driverIDField);
		
		errorDriverIDLbl = new JLabel();
		errorDriverIDLbl.setBounds(880,290,180,20);
		containerPnl.add(errorDriverIDLbl);
	
		editBtn = new JButton("Ubah");
		editBtn.setBounds(850,540,100,30);
		containerPnl.add(editBtn);

		printBtn = new JButton("Cetak");
		printBtn.setBounds(740,540,100,30);
		containerPnl.add(printBtn);
	
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,540,100,30);
		containerPnl.add(backBtn);

	}
	
	@Override
	public void invokeObjects(Object... objects) {
		received = (Received)objects[0];
		woodResourceComboBox.setEnabled(false);
		woodDomicileField.setEnabled(false);
		receivedCodeField.setEnabled(false);
		docDateChooser.setEnabled(false);
		receivedCodeDateField.setEnabled(false);
		receivedCodeMonthField.setEnabled(false);
		receivedCodeYearField.setEnabled(false);
		receivedDateChooser.setEnabled(false);
		receivedDateChooser.getDateEditor().setEnabled(false);
		ritNumberField.setEnabled(false);
		supplierComboBox.setEnabled(false);
		supplierCPComboBox.setEnabled(false);
		licensePlateField.setEnabled(false);
		driverField.setEnabled(false);
		driverIDField.setEnabled(false);
		docNoField.setEnabled(false);
		docTypeField.setEnabled(false);
		totalLogField.setEnabled(false);
		totalVolumeField.setEnabled(false);
		woodTypeComboBox.setEnabled(false);
		

		String[] codes;
		codes = received.getReceivedCode().split("/");

		receivedCodeField.setText(codes[0]);
		receivedCodeDateField.setText(codes[2]);
		receivedCodeMonthField.setText(codes[3]);
		receivedCodeYearField.setText(codes[4]);
		receivedDateChooser.setDate(received.getReceivedDate());
		ritNumberField.setText(received.getRitNo());
		supplierComboBox.setSelectedItem(received.getSupplier());
		supplierCPComboBox.setSelectedItem(received.getSubSupplierName());
		licensePlateField.setText(received.getLicensePlate());
		driverField.setText(received.getDriver());
		driverIDField.setText(received.getDriverID());
		docNoField.setText(received.getDeliveryNote());;
		woodDomicileField.setText(received.getWoodDomicile());
		woodResourceComboBox.setSelectedItem(received.getWoodResource());
		woodTypeComboBox.setSelectedItem(received.getWoodTypeName());
		
		
		try {
			delivery = ReceivedDAOFactory.getDeliveryDAO().getDeliveryNoteByCode(received.getDeliveryNote());
			docTypeField.setText(delivery.getDocumentType());
			docDateChooser.setDate(delivery.getDocIssuedDate());
			totalLogField.setText(""+delivery.getTotalLog());
			totalVolumeField.setText(""+delivery.getTotalVolume());
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		
		if(received.getReceivedStatus().equals("FINAL")){
			editBtn.setEnabled(false);
		}
	}
}