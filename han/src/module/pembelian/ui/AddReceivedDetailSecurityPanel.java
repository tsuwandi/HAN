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
import module.pembelian.model.Received;
import module.pembelian.model.SupplierVehicle;
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
	
	JLabel errorCodeLbl;
	JLabel errorRitNumberLbl;
	JLabel errorLicenseLbl;
	JLabel errorDocNoLbl;
	JLabel errorWoodTypeLbl;
	JLabel errorDriverLbl;
	JLabel errorDriverIDLbl;
	
	NumberField receivedCodeField;
	NumberField receivedCodeDateField;
	NumberField receivedCodeMonthField;
	NumberField receivedCodeYearField;
	NumberField ritNumberField;
	
	JTextField driverField;
	JTextField woodDomicileField;
	JTextField supplierTextField;
	JTextField woodResourceField;
	JTextField driverIDField;
	
	
	ComboBox<SupplierVehicle> licensePlateComboBox;
	ComboBox<WoodType> woodTypeComboBox;
	ComboBox<Delivery> docNoComboBox;

	JScrollPane palletScrollPane;
	JScrollPane dockingPicScrollPane;
	
	JDateChooser receivedDateChooser;
	
	JButton saveBtn;

	List<SupplierVehicle> suppVehicles;
	List<Delivery> deliveries;
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
		
		receivedCodeField = new NumberField();
		receivedCodeField.setBounds(220, 70, 100, 20);
		containerPnl.add(receivedCodeField);
		
		receivedCodeDateField = new NumberField();
		receivedCodeDateField.setBounds(340, 70, 30, 20);
		containerPnl.add(receivedCodeDateField);
		
		receivedCodeMonthField = new NumberField();
		receivedCodeMonthField.setBounds(380, 70, 30, 20);
		containerPnl.add(receivedCodeMonthField);
		
		receivedCodeYearField = new NumberField();
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
		
		ritNumberField = new NumberField();
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
		docNoLbl.setBounds(50,350,150,20);
		containerPnl.add(docNoLbl);
		
		docNoComboBox = new ComboBox<>();
		docNoComboBox.setBounds(220, 350, 150, 20);
		containerPnl.add(docNoComboBox);
		
		errorDocNoLbl = new JLabel();
		errorDocNoLbl.setBounds(380,350,180,20);
		containerPnl.add(errorDocNoLbl);

		
		//Wood Domicile
		woodDomicileLbl = new JLabel("Asal Barang");
		woodDomicileLbl.setBounds(50,390,150,20);
		containerPnl.add(woodDomicileLbl);
		
		woodDomicileField = new JTextField();
		woodDomicileField.setBounds(220, 390, 150, 20);
		containerPnl.add(woodDomicileField);
	
		//Wood Resource
		woodResourceLbl = new JLabel("Asal Sumber Bahan Baku");
		woodResourceLbl.setBounds(50, 430, 150, 20);
		containerPnl.add(woodResourceLbl);
		
		woodResourceField = new JTextField();
		woodResourceField.setBounds(220, 430, 150, 20);
		containerPnl.add(woodResourceField);
		
		
		//Wood Type
		woodTypeLbl = new JLabel("Tipe Kayu");
		woodTypeLbl.setBounds(50, 470, 150, 20);
		containerPnl.add(woodTypeLbl);
		
		woodTypeComboBox = new ComboBox<>();
		woodTypeComboBox.setBounds(220, 470, 150, 20);
		containerPnl.add(woodTypeComboBox);
		
		errorWoodTypeLbl = new JLabel();
		errorWoodTypeLbl.setBounds(380,470,150,20);
		containerPnl.add(errorWoodTypeLbl);

		saveBtn = new JButton("Save");
		saveBtn.setBounds(450,540,100,30);
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

			deliveries = ReceivedDAOFactory.getDeliveryDAO().getDeliveryNote();
			deliveries.add(0,new Delivery("--Pilih--"));
			docNoComboBox.setList(deliveries);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		woodResourceField.setEnabled(false);
		woodDomicileField.setEnabled(false);
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
		
		docNoComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(docNoComboBox.getSelectedIndex()!=0){
					woodDomicileField.setText(docNoComboBox.getDataIndex().getWoodDomicile());
					woodResourceField.setText(docNoComboBox.getDataIndex().getWoodResource());
				}else{
					woodDomicileField.setText("");
					woodResourceField.setText("");
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
				if(docNoComboBox.getSelectedIndex()==0){
					errorDocNoLbl.setText("<html><font color='red'>Document Number harus dipilih !</font></html>");
					error++;
				}else{
					errorDocNoLbl.setText("");
				}
				
				if(error==0){
					Received rec = new Received();
					String code = receivedCodeField.getText()+firstCodeSeparator.getText()
							+receivedCodeDateField.getText()+secondCodeSeparator.getText()
							+receivedCodeMonthField.getText()+thirdCodeSeparator.getText()+receivedCodeYearField.getText();
					rec.setDeliveryNote(docNoComboBox.getDataIndex().getDeliveryNote());
					rec.setDriver(driverField.getText());
					rec.setLicensePlate(licensePlateComboBox.getDataIndex().getLicensePlate());
					rec.setReceivedCode(code);
					rec.setRitNo(ritNumberField.getText());
					rec.setSupplier(supplierTextField.getText());
					rec.setWoodTypeID(woodTypeComboBox.getDataIndex().getId());
					rec.setReceivedDate(receivedDateChooser.getDate());
					rec.setDriverID(driverIDField.getText());
					try {
						ReceivedDAOFactory.getReceivedDAO().save(rec);
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
				receivedCodeField.setText("001");
			}else{
				String codeTemp = ReceivedDAOFactory.getReceivedDAO().getLastCode();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void invokeObjects(Object... objects) {
	
	}
}