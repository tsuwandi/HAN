package module.pembelian.ui;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ReceivedDAOFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import model.User;
import module.pembelian.model.Delivery;
import module.pembelian.model.Employee;
import module.pembelian.model.Grade;
import module.pembelian.model.PalletCard;
import module.pembelian.model.PicDocking;
import module.pembelian.model.Received;
import module.pembelian.model.ReceivedDetail;
import module.pembelian.model.SupplierCP;
import module.pembelian.model.SupplierVehicle;
import module.sn.woodtype.model.WoodType;
import module.util.Bridging;
import main.component.TextField;

public class AddReceivedDetailPanel extends JPanel implements Bridging{
	Logger log = LogManager.getLogger(AddReceivedDetailPanel.class.getName());
	
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
	JLabel supplierCPLbl;
	JLabel supplierAddressLbl;
	JLabel totalLogLbl;
	JLabel totalVolumeLbl;
	JLabel graderLbl;
	JLabel gradeLbl;
	JLabel totalVolumeByAdminLbl;
	JLabel uomTotalLogLbl;
	JLabel uomTotalVolumeLbl;
	JLabel uomTotalVolumeByAdminLbl;
	JLabel docDateLbl;
	JLabel receivedByLbl;
	JLabel docTypeLbl;
	
	JLabel errorCodeLbl;
	JLabel errorRitNumberLbl;
	JLabel errorLicenseLbl;
	JLabel errorDocNoLbl;
	JLabel errorWoodTypeLbl;
	JLabel errorDriverLbl;
	JLabel errorDriverIDLbl;
	JLabel errorSupplierLbl;
	JLabel errorSupplierCPLbl;
	JLabel errorGradeLbl;
	JLabel errorGraderLbl;
	JLabel errorTotalVolumeByAdminLbl;
	JLabel errorReceivedByLbl;
	
	NumberField receivedCodeField;
	NumberField receivedCodeDateField;
	NumberField receivedCodeMonthField;
	NumberField receivedCodeYearField;
	NumberField totalVolumeByAdminField;
	
	TextField ritNumberField;
	TextField dateField;
	TextField supplierField;
	TextField subSupplierField;
	TextField licensePlateField;
	TextField driverIDField;
	TextField driverField;
	TextField deliveryNoteField;
	TextField woodDomicileField;
	TextField woodResourceField;
	TextField woodTypeField;
	TextField totalVolumeField;
	TextField totalLogField;
	TextField receivedByField;
	TextField docDateField;
	TextField docTypeField;
	
	JTextArea supplierAddressArea;
	
	ComboBox<Employee> graderComboBox;
	ComboBox<Grade> gradeComboBox;
	
	public JTable receivedDetailTable;
	JTable dockingPICTable;
	
	JScrollPane receivedDetailScrollPane;
	JScrollPane dockingPicScrollPane;
	
	JButton addPalletBtn;
	JButton deletePalletBtn;
	
	JButton searchPicBtn;
	JButton deletePicBtn;
	
	JButton saveBtn;
	JButton backBtn;

	ReceivedDetailModel receivedDetailModel;
	PicDockingTableModel picDockingTableModel;
	
	public List<ReceivedDetail> receivedDetails;
	List<PicDocking> picDockings;
	List<SupplierVehicle> suppVehicles;
	List<Delivery> deliveries;
	List<WoodType> woodTypes;
	List<Employee> graders;
	List<Grade> grades;
	
	AddReceivedDetailPanel parent;
	JScrollPane scrollPane;
	JPanel containerPnl;
	public Received received;
	public Map<String, PalletCard> palletMaps;
	public List<Grade> gradeCollection;
	
	Map<Integer, ReceivedDetail> deletedDetails;
	
	public AddReceivedDetailPanel(){
		createGUI();
		setData();
		listener();	
	}
	
	public void setTablePic(List<PicDocking> picDockings){
		this.picDockings = new ArrayList<>();
		this.picDockings = picDockings;
		dockingPICTable.setModel(new PicDockingTableModel(this.picDockings));
		dockingPICTable.updateUI();
	}
	
	public void setData(){
		try {
			graders = ReceivedDAOFactory.getPICDockingDAO().getEmployeeGrader("POS0002");
			graders.add(0,new Employee("--Pilih--"));
			graderComboBox.setList(graders);
			
			grades = ReceivedDAOFactory.getGradeDAO().getAll();
			grades.add(0,new Grade("--Pilih--"));
			gradeComboBox.setList(grades);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		receivedCodeDateField.setText(new SimpleDateFormat("dd").format(new Date()));
		receivedCodeMonthField.setText(new SimpleDateFormat("MM").format(new Date()));
		receivedCodeYearField.setText(new SimpleDateFormat("yy").format(new Date()));
		
	}
	
	public void listener(){
		addPalletBtn.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gradeComboBox.getSelectedIndex()==0){
					errorGradeLbl.setText("<html><font color='red'>Grade harus dipilih !</font></html>");
				}else{
					errorGradeLbl.setText("");
					if(gradeCollection.size()!=0){
						int index = 0;
						for (int i = 0; i < gradeCollection.size(); i++) {
							if(gradeCollection.get(i).getId()==gradeComboBox.getDataIndex().getId()){
								System.out.println("Index :"+i);
								index = i+1;
							}
						}
						if(index!=0){
							System.out.println("Edit");
							EditPopUpPalletCard pop = new EditPopUpPalletCard(parent, receivedDetails.get(index-1),index-1);
							pop.show();
							pop.setLocationRelativeTo(null);
							pop.setModal(true);
						}else{
							System.out.println("Add");
							AddPopUpPalletCard pop = new AddPopUpPalletCard(parent);
							pop.show();
							pop.setLocationRelativeTo(null);
							pop.setAlwaysOnTop(true);
							pop.setModal(true);
						}
					}else{
						AddPopUpPalletCard pop = new AddPopUpPalletCard(parent);
						pop.show();
						pop.setLocationRelativeTo(null);
						pop.setModal(true);
					}
					
				}
			}
		});
		
		deletePalletBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int totalChecked=0;
				for (ReceivedDetail receivedDetail : receivedDetails) {
					if(receivedDetail.isFlag()) totalChecked++;
				}
				if(totalChecked>0){
					int choice = DialogBox.showDeleteChoice();
					if(choice==JOptionPane.YES_OPTION){
						for (int i = 0; i < receivedDetails.size(); i++) {
							ReceivedDetail rd = receivedDetails.get(i);
							if(rd.isFlag()) {
								deletedDetails.put(rd.getId(), rd);
								for (PalletCard palletCard : rd.getPallets()) {
									if(palletMaps.containsKey(palletCard.getPalletCardCode())){
										palletMaps.remove(palletCard.getPalletCardCode());
									}
										
								}
								for (int j = 0; j < gradeCollection.size(); j++) {
									if(gradeCollection.get(j).getId()==rd.getGradeID()){
										gradeCollection.remove(j);
									}
								}
								receivedDetails.remove(i);
							}
							receivedDetailTable.updateUI();
						}
					}
				}
			}
		});

		dockingPICTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dockingPICTable.columnAtPoint(e.getPoint())==0){
					if(picDockings.get(dockingPICTable.getSelectedRow()).isFlag()){
						picDockings.get(dockingPICTable.getSelectedRow()).setFlag(false);
						dockingPICTable.updateUI();
					}else{
						picDockings.get(dockingPICTable.getSelectedRow()).setFlag(true);
						dockingPICTable.updateUI();
					}
				}
			}
		});
		
		receivedDetailTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(receivedDetailTable.columnAtPoint(e.getPoint())==5){
					EditPopUpPalletCard pop = new EditPopUpPalletCard(parent, receivedDetails.get(receivedDetailTable.getSelectedRow()),receivedDetailTable.getSelectedRow());
					pop.show();
					pop.setLocationRelativeTo(null);
					pop.setModal(true);
				}
				if(receivedDetailTable.columnAtPoint(e.getPoint())==0){
					if(receivedDetails.get(receivedDetailTable.getSelectedRow()).isFlag()){
						receivedDetails.get(receivedDetailTable.getSelectedRow()).setFlag(false);
						receivedDetailTable.updateUI();
					}else{
						receivedDetails.get(receivedDetailTable.getSelectedRow()).setFlag(true);
						receivedDetailTable.updateUI();
					}
				}
			}
		});
		
		searchPicBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpPicDocking pop = new PopUpPicDocking(parent);
				pop.show();
				pop.setLocationRelativeTo(null);
				pop.setModal(true);
			}
		});
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.pembelian.ui.ViewReceivedDetailPanel", received);
			}
		});
		deletePicBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int totalChecked=0;
				for (PicDocking picDocking : picDockings) {
					if(picDocking.isFlag()) totalChecked++;
				}
				if(totalChecked>0){
					int choice = DialogBox.showDeleteChoice();
					if(choice==JOptionPane.YES_OPTION){
						for (int i = 0; i < picDockings.size(); i++) {
							PicDocking pd = picDockings.get(i);
							if(pd.isFlag()) picDockings.remove(i);
							dockingPICTable.updateUI();
						}
					}
				}
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int error = 0;
					if(graderComboBox.getSelectedIndex()==0){
						errorGraderLbl.setText("<html><font color='red'>Grader harus dipilih !</font></html>");
						error++;
					}else{
						errorGraderLbl.setText("");
					}
					if(totalVolumeByAdminField.getText().equals("")||totalVolumeByAdminField.getText().equals("0.0")||totalVolumeByAdminField.getText().equals("0")){
						errorTotalVolumeByAdminLbl.setText("<html><font color='red'>Total Volume harus diisi !</font></html>");
						error++;
					}else{
						errorTotalVolumeByAdminLbl.setText("");
					}
									
					if(error==0){
						if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
							try {
								ReceivedDAOFactory.getPicDockingReceivedDAO().delete(received.getReceivedCode());
								List<ReceivedDetail> receivedDetailTemp = ReceivedDAOFactory.getReceivedDetailDAO().getAllReceivedDetail(received.getReceivedCode());
								Map<Integer, ReceivedDetail> receivedMaps = new HashMap<>();
								for(ReceivedDetail rd : receivedDetailTemp){
									receivedMaps.put(rd.getId(), rd);
								}
								for(ReceivedDetail receivedDetail : receivedDetails){
									
									if(receivedDetail.getId()==0){
										receivedDetail.setReceivedCode(received.getReceivedCode());
										ReceivedDAOFactory.getReceivedDetailDAO().save(receivedDetail);	
										int detailLastID = ReceivedDAOFactory.getReceivedDetailDAO().getLastID();
										for(PalletCard palletCardDetail : receivedDetail.getPallets()){
											palletCardDetail.setReceivedDetailID(detailLastID);
											ReceivedDAOFactory.getPalletCardDAO().save(palletCardDetail);
										}
									}
									else{
										receivedDetail.setReceivedCode(received.getReceivedCode());
										ReceivedDAOFactory.getReceivedDetailDAO().update(receivedDetail);
										ReceivedDetail rdTemp = receivedMaps.get(receivedDetail.getId());
										Map<Integer, PalletCard> palletMapsTemp = new HashMap<>();
										for(PalletCard palletCarde:rdTemp.getPallets()){
											palletMapsTemp.put(palletCarde.getId(), palletCarde);
										}
										for(PalletCard palletCardDetail : receivedDetail.getPallets()){
											if(palletMapsTemp.get(palletCardDetail.getId())==null){
												palletCardDetail.setReceivedDetailID(receivedDetail.getId());
												ReceivedDAOFactory.getPalletCardDAO().save(palletCardDetail);
											}else{
												ReceivedDAOFactory.getPalletCardDAO().update(palletCardDetail);
											}
										}
										for(PalletCard palletCardDetail : receivedDetail.getDeletedPallets().values()){
											ReceivedDAOFactory.getPalletCardDAO().updateDelete(palletCardDetail.getId());
										}
									}
								}
								for (ReceivedDetail receivedDetailDeleted :deletedDetails.values()) {
									ReceivedDAOFactory.getReceivedDetailDAO().updateDelete(receivedDetailDeleted.getId());
									for(PalletCard palletCardTemp: receivedDetailDeleted.getPallets()){
										ReceivedDAOFactory.getPalletCardDAO().updateDelete(palletCardTemp.getId());
									}
								}
								for(PicDocking picDocking : picDockings){
									picDocking.setReceivedCode(received.getReceivedCode());
									ReceivedDAOFactory.getPicDockingReceivedDAO().save(picDocking);
								}
								ReceivedDAOFactory.getReceivedDAO().updateStatus(Double.valueOf(totalVolumeByAdminField.getText()),graderComboBox.getDataIndex().getEmployeeId(),received.getReceivedCode());
								DialogBox.showInsert();
								MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel");
							} catch (Exception e) {
								log.error(e.getMessage());
								e.printStackTrace();
							}
						}
					}
				}
			});
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					totalVolumeByAdminField.requestFocusInWindow();
				}
			});
			
			 KeyboardFocusManager.getCurrentKeyboardFocusManager().
	         addPropertyChangeListener("focusOwner", new PropertyChangeListener() {
	
	     @Override
	     public void propertyChange(PropertyChangeEvent evt) {
		         if (!(evt.getNewValue() instanceof JComponent)) {
		             return;
		         }
		         JViewport viewport = (JViewport) containerPnl.getParent();
		         JComponent focused = (JComponent) evt.getNewValue();
		         if (containerPnl.isAncestorOf(focused)) {
		             Rectangle rect = focused.getBounds();
		             Rectangle r2 = viewport.getVisibleRect();
		             containerPnl.scrollRectToVisible(new Rectangle(rect.x, rect.y, (int) r2.getWidth(), (int) r2.getHeight()));
		         }
	     	}
		 });
		
	}
	
	public void createGUI(){
		setLayout(null);
		this.parent = this;
		palletMaps =  new HashMap<>();
		gradeCollection = new ArrayList<>();
		deletedDetails = new HashMap<>();
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1100, 900));
		containerPnl.setLayout(null);
		
		scrollPane = new JScrollPane(containerPnl);
		scrollPane.setBounds(0,0,1100,630);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);
		
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
		
		dateField = new TextField();
		dateField.setBounds(220,110,150,20);
		containerPnl.add(dateField);
		
		//Rit Number
		ritNumberLbl = new JLabel("No Rit");
		ritNumberLbl.setBounds(50,150,150,20);
		containerPnl.add(ritNumberLbl);
		
		ritNumberField = new TextField();
		ritNumberField.setBounds(220, 150, 150, 20);
		containerPnl.add(ritNumberField);
		
		errorRitNumberLbl = new JLabel();
		errorRitNumberLbl.setBounds(380,150,180,20);
		containerPnl.add(errorRitNumberLbl);
		
		
	
		// Document Number
		docNoLbl =  new JLabel("No Dokumen");
		docNoLbl.setBounds(50,190,150,20);
		containerPnl.add(docNoLbl);
		
		deliveryNoteField = new TextField();
		deliveryNoteField.setBounds(220, 190, 150, 20);
		containerPnl.add(deliveryNoteField);
		
		// Document Date
		docDateLbl =  new JLabel("Tanggal Dokumen");
		docDateLbl.setBounds(50,230,150,20);
		containerPnl.add(docDateLbl);
		
		docDateField = new TextField();
		docDateField.setBounds(220, 230, 150, 20);
		containerPnl.add(docDateField);
		
		//Document Type
		docTypeLbl =  new JLabel("Tipe Dokumen");
		docTypeLbl.setBounds(50,270,150,20);
		containerPnl.add(docTypeLbl);
		
		docTypeField = new TextField();
		docTypeField.setBounds(220, 270, 150, 20);
		containerPnl.add(docTypeField);
		
	
		//Wood Domicile
		woodDomicileLbl = new JLabel("Asal Barang");
		woodDomicileLbl.setBounds(50,310,150,20);
		containerPnl.add(woodDomicileLbl);
		
		woodDomicileField = new TextField();
		woodDomicileField.setBounds(220, 310, 150, 20);
		containerPnl.add(woodDomicileField);
	
		//Wood Resource
		woodResourceLbl = new JLabel("Asal Sumber Bahan Baku");
		woodResourceLbl.setBounds(50, 350, 150, 20);
		containerPnl.add(woodResourceLbl);
		
		woodResourceField = new TextField();
		woodResourceField.setBounds(220, 350, 150, 20);
		containerPnl.add(woodResourceField);
		
		
		//Wood Type
		woodTypeLbl = new JLabel("Jenis Kayu");
		woodTypeLbl.setBounds(50, 390, 150, 20);
		containerPnl.add(woodTypeLbl);
		
		woodTypeField = new TextField();
		woodTypeField.setBounds(220, 390, 150, 20);
		containerPnl.add(woodTypeField);
		
		//Total Log
		totalLogLbl = new JLabel("Total Log");
		totalLogLbl.setBounds(50,430,150,20);
		containerPnl.add(totalLogLbl);
		
		totalLogField = new TextField();
		totalLogField.setBounds(220, 430, 150, 20);
		containerPnl.add(totalLogField);
		
		uomTotalLogLbl = new JLabel("batang");
		uomTotalLogLbl.setBounds(380,430,40,20);
		containerPnl.add(uomTotalLogLbl);
		
		//Total Volume
		totalVolumeLbl = new JLabel("Total Volume");
		totalVolumeLbl.setBounds(50,470,150,20);
		containerPnl.add(totalVolumeLbl);
		
		totalVolumeField = new TextField();
		totalVolumeField.setBounds(220, 470, 150, 20);
		containerPnl.add(totalVolumeField);
		
		uomTotalVolumeLbl = new JLabel("m3");
		uomTotalVolumeLbl.setBounds(380,470,50,20);
		containerPnl.add(uomTotalVolumeLbl);
		
		//Supplier
		supplierLbl = new JLabel("Supplier");
		supplierLbl.setBounds(550,70,150,20);
		containerPnl.add(supplierLbl);
		
		supplierField = new TextField();
		supplierField.setBounds(720,70,150,20);
		containerPnl.add(supplierField);
		
		//Supplier CP
		supplierCPLbl = new JLabel("Sub Supplier");
		supplierCPLbl.setBounds(550,110,150,20);
		containerPnl.add(supplierCPLbl);
		
		subSupplierField = new TextField();
		subSupplierField.setBounds(720,110,150,20);
		containerPnl.add(subSupplierField);
		
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
		
		//Driver
		driverLbl =  new JLabel("Supir");
		driverLbl.setBounds(550,250,150,20);
		containerPnl.add(driverLbl);
		
		driverField = new TextField();
		driverField.setBounds(720, 250, 150, 20);
		containerPnl.add(driverField);
		
		//Driver Id
		driverIDLbl =  new JLabel("KTP Supir");
		driverIDLbl.setBounds(550,290,150,20);
		containerPnl.add(driverIDLbl);
		
		driverIDField = new TextField();
		driverIDField.setBounds(720, 290, 150, 20);
		containerPnl.add(driverIDField);
		
	
		
		//TotalVolumeByAdmin
		totalVolumeByAdminLbl = new JLabel("<html>Total Volume <font color='red'>*</font></html>");
		totalVolumeByAdminLbl.setBounds(550,330,150,20);
		containerPnl.add(totalVolumeByAdminLbl);
		
		totalVolumeByAdminField = new NumberField(10);
		totalVolumeByAdminField.setBounds(720, 330, 150, 20);
		containerPnl.add(totalVolumeByAdminField);
		
		uomTotalVolumeByAdminLbl = new JLabel("m3");
		uomTotalVolumeByAdminLbl.setBounds(880,330,30,20);
		containerPnl.add(uomTotalVolumeByAdminLbl);
		
		errorTotalVolumeByAdminLbl = new JLabel();
		errorTotalVolumeByAdminLbl.setBounds(890,330,150,20);
		containerPnl.add(errorTotalVolumeByAdminLbl);
		
		
		//Received By
		receivedByLbl = new JLabel("<html>Diterima oleh <font color='red'>*</font></html>");
		receivedByLbl.setBounds(550,370,150,20);
		containerPnl.add(receivedByLbl);
		
		receivedByField = new TextField();
		receivedByField.setBounds(720, 370, 150, 20);
		containerPnl.add(receivedByField);
		
		errorReceivedByLbl = new JLabel();
		errorReceivedByLbl.setBounds(890,370,150,20);
		containerPnl.add(errorReceivedByLbl);

		
		//Grader
		graderLbl = new JLabel("<html>Grader <font color='red'>*</font></html>");
		graderLbl.setBounds(550,410,150,20);
		containerPnl.add(graderLbl);
		
		graderComboBox = new ComboBox<>();
		graderComboBox.setBounds(720, 410, 150, 20);
		containerPnl.add(graderComboBox);
		
		errorGraderLbl = new JLabel();
		errorGraderLbl.setBounds(890,410,150,20);
		containerPnl.add(errorGraderLbl);
		
		//Grade
		gradeLbl = new JLabel("<html>Grade <font color='red'>*</font></html>");
		gradeLbl.setBounds(550,450,150,20);
		containerPnl.add(gradeLbl);
		
		gradeComboBox = new ComboBox<>();
		gradeComboBox.setBounds(720, 450, 150, 20);
		containerPnl.add(gradeComboBox);
		
		errorGradeLbl = new JLabel();
		errorGradeLbl.setBounds(890,450,150,20);
		containerPnl.add(errorGradeLbl);
		
		//Received Detail Card
		receivedDetails = new ArrayList<>();
		receivedDetailModel = new ReceivedDetailModel(receivedDetails);
		receivedDetailTable = new JTable(receivedDetailModel);
		receivedDetailTable.setFocusable(false);
		
		receivedDetailScrollPane = new JScrollPane(receivedDetailTable);
		receivedDetailScrollPane.setBounds(50,540,1000,150);
		containerPnl.add(receivedDetailScrollPane);
		
		addPalletBtn = new JButton("Tambah");
		addPalletBtn.setBounds(800,490,100,30);
		containerPnl.add(addPalletBtn);
		
		deletePalletBtn = new JButton("Hapus");
		deletePalletBtn.setBounds(900,490,100,30);
//		deletePalletBtn.setFocusable(false);
		containerPnl.add(deletePalletBtn);
		
		//Pic Docking
		picDockings = new ArrayList<>();
		picDockingTableModel = new PicDockingTableModel(picDockings);
		dockingPICTable = new JTable(picDockingTableModel);
		dockingPICTable.setFocusable(false);
		
		dockingPicScrollPane = new JScrollPane(dockingPICTable);
		dockingPicScrollPane.setBounds(50,710,500,100);
		containerPnl.add(dockingPicScrollPane);
		
		deletePicBtn = new JButton("Hapus");
		deletePicBtn.setBounds(570,750,100,30);
//		deletePicBtn.setFocusable(false);
		containerPnl.add(deletePicBtn);
		
		searchPicBtn = new JButton("Cari");
		searchPicBtn.setBounds(570,710,100,30);
		containerPnl.add(searchPicBtn);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(950,830,100,30);
		containerPnl.add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,830,100,30);
		backBtn.setFocusable(false);
		containerPnl.add(backBtn);
		
	}
	
	
	private class ReceivedDetailModel extends AbstractTableModel {
	    private List<ReceivedDetail> pallets;
	    
	    public ReceivedDetailModel(List<ReceivedDetail> pallets) {
	        this.pallets = pallets;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return pallets.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 6;
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
	    	ReceivedDetail p = pallets.get(rowIndex);
	        switch(columnIndex){
	        	case 0 :
	        		return p.isFlag();
	            case 1 : 
	                return p.getGrade();
	            case 2 :
	                return p.getTotalLog();
	            case 3 :
	                return AppConstants.FOUR_DIGIT_DECIMAL_FORMAT.format(p.getTotalVolume()/AppConstants.DIVIDER_VOLUME);
	            case 4 :
	                return p.getPallets().size();
	            case 5 :
	                return "View";
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
	                return "Check";
	            case 1 :
	                return "Grade";
	            case 2 :
	                return "Total Jumlah Kayu";
	            case 3 :
	                return "Total Volume";
	            case 4 :
	                return "Total Kartu Pallet";
	            case 5 :
	                return "Action";
	            default :
	                return "";
	        }
	    }

	}
	
	
	class PicDockingTableModel extends AbstractTableModel {
	    private List<PicDocking> picDockings;
	    
	    public PicDockingTableModel(List<PicDocking> picDockings) {
	        this.picDockings = picDockings;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return picDockings.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 3;
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
	    	PicDocking p = picDockings.get(rowIndex);
	        switch(columnIndex){
		        case 0 :
		        	return p.isFlag();
	            case 1 : 
	                return p.getEmpCode();
	            case 2 :
	                return p.getEmpName();
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
	                return "Nik";
	            case 2 :
	                return "Name";
	            default :
	                return "";
	        }
	    }

	}


	@Override
	public void invokeObjects(Object... objects) {
		received = (Received)objects[0];
		woodResourceField.setEnabled(false);
		woodDomicileField.setEnabled(false);
		supplierField.setEnabled(false);
		receivedCodeField.setEnabled(false);
		receivedCodeDateField.setEnabled(false);
		receivedCodeMonthField.setEnabled(false);
		receivedCodeYearField.setEnabled(false);
		dateField.setEnabled(false);
		ritNumberField.setEnabled(false);
		licensePlateField.setEnabled(false);
		driverField.setEnabled(false);
		driverIDField.setEnabled(false);
		deliveryNoteField.setEnabled(false);
		woodTypeField.setEnabled(false);
		subSupplierField.setEnabled(false);
		supplierAddressArea.setEnabled(false);
		totalLogField.setEnabled(false);
		totalVolumeField.setEnabled(false);
		docTypeField.setEnabled(false);
		docDateField.setEnabled(false);

		String[] codes;
		codes = received.getReceivedCode().split("/");

		receivedCodeField.setText(codes[0]);
		receivedCodeDateField.setText(codes[2]);
		receivedCodeMonthField.setText(codes[3]);
		receivedCodeYearField.setText(codes[4]);
		dateField.setText(new SimpleDateFormat("dd-MM-yyyy").format(received.getReceivedDate()));
		ritNumberField.setText(received.getRitNo());
		licensePlateField.setText(received.getLicensePlate());
		supplierField.setText(received.getSupplier());
		driverField.setText(received.getDriver());
		driverIDField.setText(received.getDriverID());
		deliveryNoteField.setText(received.getDeliveryNote());
		woodDomicileField.setText(received.getWoodDomicile());
		woodResourceField.setText(received.getWoodResource());
		woodTypeField.setText(received.getWoodTypeName());
		subSupplierField.setText(received.getCity());
		totalVolumeByAdminField.setText(received.getTotalVolume()+"");
		receivedByField.setText(received.getReceivedBy());
		
		Delivery delivery;
		SupplierCP subSupplier;
		try {
			receivedDetails = ReceivedDAOFactory.getReceivedDetailDAO().getAllReceivedDetail(received.getReceivedCode());
			receivedDetailTable.setModel(new ReceivedDetailModel(receivedDetails));
			receivedDetailTable.updateUI();
			
			
			for (ReceivedDetail receivedDetail : receivedDetails) {
				Grade grade = new Grade();
				grade.setId(receivedDetail.getGradeID());
				gradeCollection.add(grade);
				
				for(PalletCard pc : receivedDetail.getPallets()){
					palletMaps.put(pc.getPalletCardCode(), pc);
				}
			}
			
			picDockings = ReceivedDAOFactory.getPicDockingReceivedDAO().getPICDocking(received.getReceivedCode());
			dockingPICTable.setModel(new PicDockingTableModel(picDockings));
			dockingPICTable.updateUI();
			
			delivery = ReceivedDAOFactory.getDeliveryDAO().getDeliveryNoteByCode(received.getReceivedCode());
			subSupplier = ReceivedDAOFactory.getSupplierCPDAO().getSuppCPBySupplierByID(received.getSupplierCpID());
			supplierAddressArea.setText(subSupplier.getSuppAddress());
			docTypeField.setText(delivery.getDocumentType());
			docDateField.setText(new SimpleDateFormat("dd-MM-yyyy").format(delivery.getDocIssuedDate()));
			totalLogField.setText(delivery.getTotalLog()+"");
			totalVolumeField.setText(delivery.getTotalVolume()+"");
			
			if(received.getEmpCode()!=null){
				if(!received.getEmpCode().equals("")){
					Employee emp = ReceivedDAOFactory.getPICDockingDAO().getEmployeeByCode(received.getEmpCode());
					graderComboBox.setSelectedItem(emp.getEmployeeName());
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	

	}

	public Map<Integer, ReceivedDetail> getDeletedDetails() {
		return deletedDetails;
	}

	public void setDeletedDetails(Map<Integer, ReceivedDetail> deletedDetails) {
		this.deletedDetails = deletedDetails;
	}
	
}