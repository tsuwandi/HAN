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
import module.pembelian.model.SupplierCP;
import module.pembelian.model.SupplierVehicle;
import module.pembelian.model.WoodResource;
import module.pembelian.model.WoodType;
import module.supplier.model.Supplier;
import module.util.Bridging;

public class ViewReceivedDetailSecurityPanel extends JPanel implements Bridging{

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
	
	JTextField driverField;
	JTextField woodDomicileField;
	JTextField driverIDField;
	JTextField docNoField;
	JTextField licensePlateField;
	
	JTextArea supplierAddressArea;
	
	ComboBox<SupplierCP> supplierCPComboBox;
	ComboBox<WoodType> woodTypeComboBox;
	ComboBox<DocumentType> docTypeComboBox;
	ComboBox<WoodResource> woodResourceComboBox;
	ComboBox<Supplier> supplierComboBox;
	
	JScrollPane palletScrollPane;
	JScrollPane dockingPicScrollPane;

	JDateChooser receivedDateChooser;

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

		//Supplier
		supplierLbl = new JLabel("<html><font color='red'>Supplier *</font></html>");
		supplierLbl.setBounds(50,190,150,20);
		containerPnl.add(supplierLbl);
		
		supplierComboBox = new ComboBox<Supplier>();
		supplierComboBox.setBounds(220,190,150,20);
		containerPnl.add(supplierComboBox);
		
		errorSupplierLbl = new JLabel();
		errorSupplierLbl.setBounds(380,190,180,20);
		containerPnl.add(errorSupplierLbl);
		
		//Supplier CP
		supplierCPLbl = new JLabel("<html><font color='red'>Sub Supplier *</font></html>");
		supplierCPLbl.setBounds(50,230,150,20);
		containerPnl.add(supplierCPLbl);
		
		supplierCPComboBox = new ComboBox<SupplierCP>();
		supplierCPComboBox.setBounds(220,230,150,20);
		containerPnl.add(supplierCPComboBox);
		
		errorSupplierCPLbl = new JLabel();
		errorSupplierCPLbl.setBounds(380, 230, 150, 20);
		containerPnl.add(errorSupplierCPLbl);
		
		//Supplier Address
		supplierAddressLbl = new JLabel("Alamat Supplier");
		supplierAddressLbl.setBounds(50,270,150,20);
		containerPnl.add(supplierAddressLbl);
		
		supplierAddressArea = new JTextArea();
		supplierAddressArea.setBounds(220,270,150,50);
		containerPnl.add(supplierAddressArea);
		
		//License Plate
		licensePlateLbl = new JLabel("No Kendaraan");
		licensePlateLbl.setBounds(50,340,150,20);
		containerPnl.add(licensePlateLbl);
		
		licensePlateField = new JTextField();
		licensePlateField.setBounds(220,340,150,20);
		containerPnl.add(licensePlateField);
		
		errorLicenseLbl = new JLabel();
		errorLicenseLbl.setBounds(380,340,180,20);
		containerPnl.add(errorLicenseLbl);
		
		//Driver
		driverLbl =  new JLabel("Supir");
		driverLbl.setBounds(50,380,150,20);
		containerPnl.add(driverLbl);
		
		driverField = new JTextField();
		driverField.setBounds(220, 380, 150, 20);
		containerPnl.add(driverField);
		
		errorDriverLbl = new JLabel();
		errorDriverLbl.setBounds(380,380,180,20);
		containerPnl.add(errorDriverLbl);
		
		//Driver Id
		driverIDLbl =  new JLabel("KTP Supir");
		driverIDLbl.setBounds(50,420,150,20);
		containerPnl.add(driverIDLbl);
		
		driverIDField = new JTextField();
		driverIDField.setBounds(220, 420, 150, 20);
		containerPnl.add(driverIDField);
		
		errorDriverIDLbl = new JLabel();
		errorDriverIDLbl.setBounds(380,420,180,20);
		containerPnl.add(errorDriverIDLbl);


		// Document Number
		docNoLbl =  new JLabel("<html><font color='red'>No Dokumen *</font></html>");
		docNoLbl.setBounds(550,70,150,20);
		containerPnl.add(docNoLbl);
		
		docNoField = new JTextField();
		docNoField.setBounds(720, 70, 150, 20);
		containerPnl.add(docNoField);
		
		errorDocNoLbl = new JLabel();
		errorDocNoLbl.setBounds(890,70,180,20);
		containerPnl.add(errorDocNoLbl);
		
		
		// Document Type
		documentTypeLbl =  new JLabel("<html><font color='red'>Tipe Dokumen *</font></html>");
		documentTypeLbl.setBounds(550,110,150,20);
		containerPnl.add(documentTypeLbl);
		
		docTypeComboBox = new ComboBox<>();
		docTypeComboBox.setBounds(720, 110, 150, 20);
		containerPnl.add(docTypeComboBox);
		
		errorDocTypeLbl = new JLabel();
		errorDocTypeLbl.setBounds(890,110,180,20);
		containerPnl.add(errorDocTypeLbl);

		
		//Wood Domicile
		woodDomicileLbl = new JLabel("<html><font color='red'>Asal Barang *</font></html>");
		woodDomicileLbl.setBounds(550,150,150,20);
		containerPnl.add(woodDomicileLbl);
		
		woodDomicileField = new JTextField();
		woodDomicileField.setBounds(720, 150, 150, 20);
		containerPnl.add(woodDomicileField);
		
		errorWoodDomicileLbl = new JLabel();
		errorWoodDomicileLbl.setBounds(890,150,150,20);
		containerPnl.add(errorWoodDomicileLbl);
	
		//Wood Resource
		woodResourceLbl = new JLabel("<html><font color='red'>Asal Sumber Bahan Baku *</font></html>");
		woodResourceLbl.setBounds(550, 190, 150, 20);
		containerPnl.add(woodResourceLbl);
		
		woodResourceComboBox = new ComboBox<WoodResource>();
		woodResourceComboBox.setBounds(720, 190, 150, 20);
		containerPnl.add(woodResourceComboBox);
		
		errorWoodResourceLbl = new JLabel();
		errorWoodResourceLbl.setBounds(890, 190, 150, 20);
		containerPnl.add(errorWoodResourceLbl);
		
		//Wood Type
		woodTypeLbl = new JLabel("<html><font color='red'>Tipe Kayu *</font></html>");
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
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(880,270,40,20);
		containerPnl.add(uomTotalLogLbl);
		
		errorTotalLogLbl = new JLabel();
		errorTotalLogLbl.setBounds(930,270,150,20);
		containerPnl.add(errorTotalLogLbl);
		
		//Total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(550,310,150,20);
		containerPnl.add(totalVolumeLbl);
		
		totalVolumeField = new NumberField(10);
		totalVolumeField.setBounds(720, 310, 150, 20);
		containerPnl.add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("cm3");
		uomTotalVolumeLbl.setBounds(880,310,50,20);
		containerPnl.add(uomTotalVolumeLbl);
		
		errorTotalVolumeLbl = new JLabel();
		errorTotalVolumeLbl.setBounds(930,310,150,20);
		containerPnl.add(errorTotalVolumeLbl);

		editBtn = new JButton("Ubah");
		editBtn.setBounds(850,540,100,30);
		containerPnl.add(editBtn);

		printBtn = new JButton("Cetak");
		printBtn.setBounds(740,540,100,30);
		containerPnl.add(printBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,540,100,30);
		containerPnl.add(backBtn);

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

			documentTypes = ReceivedDAOFactory.getDocumentTypeDAO().getDocumentType();
			documentTypes.add(0,new DocumentType("--Pilih--"));
			docTypeComboBox.setList(documentTypes);
			
			woodResources = ReceivedDAOFactory.getWoodResourceDAO().getWoodResource();
			woodResources.add(0,new WoodResource("--Pilih--"));
			woodResourceComboBox.setList(woodResources);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		supplierAddressArea.setEnabled(false);
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
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION) MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
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
					docTypeComboBox.setEnabled(true);
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
					if(supplierComboBox.getSelectedIndex()==0){
						errorSupplierLbl.setText("<html><font color='red'>Supplier harus dipilih !</font></html>");
						error++;
					}else{
						errorTotalLogLbl.setText("");
					}
					if(supplierCPComboBox.getSelectedIndex()==0){
						errorSupplierCPLbl.setText("<html><font color='red'>Sub Supplier harus dipilih !</font></html>");
						error++;
					}
					if(licensePlateField.getText().equals("")){
						errorLicenseLbl.setText("<html><font color='red'>Plat Nomor harus diisi !</font></html>");
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
							del.setDocumentTypeID(docTypeComboBox.getDataIndex().getId());
							del.setWoodDomicile(woodDomicileField.getText());
							del.setWoodResourceId(woodResourceComboBox.getDataIndex().getId());
							del.setWoodTypeID(woodTypeComboBox.getDataIndex().getId());
							del.setTotalLog(Integer.valueOf(totalLogField.getText()));
							del.setTotalVolume(Double.valueOf(totalVolumeField.getText()));
							del.setId(delivery.getId());
							try {
								ReceivedDAOFactory.getReceivedDAO().update(rec);
								ReceivedDAOFactory.getDeliveryDAO().update(del);
								DialogBox.showEdit();
								MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

				}
			}
		});

	}

	@Override
	public void invokeObjects(Object... objects) {
		received = (Received)objects[0];
		woodResourceComboBox.setEnabled(false);
		woodDomicileField.setEnabled(false);
		receivedCodeField.setEnabled(false);
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
		docTypeComboBox.setEnabled(false);
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
			docTypeComboBox.setSelectedItem(delivery.getDocumentType());
			totalLogField.setText(""+delivery.getTotalLog());
			totalVolumeField.setText(""+delivery.getTotalVolume());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		if(!received.getReceivedStatus().equals("Baru")){
			editBtn.setEnabled(false);
		}
	}
}