package module.pembelian.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.DataSourceFactory;
import controller.ReceivedDAOFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ViewReceivedDetailPanel extends JPanel implements Bridging{
	Logger log = LogManager.getLogger(ViewReceivedDetailPanel.class.getName());
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
	
	NumberField receivedCodeField;
	NumberField receivedCodeDateField;
	NumberField receivedCodeMonthField;
	NumberField receivedCodeYearField;
	
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
	NumberField totalVolumeByAdminField;
	
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
	
	JButton inputBtn;
	JButton deleteBtn;
	JButton printBtn;
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
	
	ViewReceivedDetailPanel parent;
	JScrollPane scrollPane;
	JPanel containerPnl;
	Received received;
	
	public ViewReceivedDetailPanel(){
		createGUI();
		setData();
		listener();

	}
	
	public void setData(){
		try {
			graders = ReceivedDAOFactory.getPICDockingDAO().getEmployeeGrader("POS0002");
			graders.add(0,new Employee("--Pilih--"));
			graderComboBox.setList(graders);
			
			grades = ReceivedDAOFactory.getGradeDAO().getAll();
			grades.add(0,new Grade("--Pilih--"));
			gradeComboBox.setList(grades);
		} catch (Exception e) {
			log.error(e.getMessage());
			DialogBox.showError("Tidak Dapat Terhubung ke Database");
			e.printStackTrace();
		}
	}
		
	public void listener(){
		receivedDetailTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(receivedDetailTable.columnAtPoint(e.getPoint())==5){
					ViewPopUpPalletCard pop = new ViewPopUpPalletCard(parent, receivedDetails.get(receivedDetailTable.getSelectedRow()));
					pop.show();
					pop.setLocationRelativeTo(null);
				}
			}
		});

		woodResourceField.setEnabled(false);
		woodDomicileField.setEnabled(false);
		supplierField.setEnabled(false);
		receivedCodeDateField.setText(new SimpleDateFormat("dd").format(new Date()));
		receivedCodeMonthField.setText(new SimpleDateFormat("MM").format(new Date()));
		receivedCodeYearField.setText(new SimpleDateFormat("yy").format(new Date()));

		inputBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.pembelian.ui.AddReceivedDetailPanel",received);
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)*/ 
					MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel");
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = DialogBox.showDeleteChoice();
				if(choice==JOptionPane.YES_OPTION){
					try {
						ReceivedDAOFactory.getReceivedDAO().delete(received);
						if(receivedDetails.size()>0){
							for (ReceivedDetail rd : receivedDetails) {
								if(rd.getPallets().size()>0){
									for (PalletCard pc : rd.getPallets()) {
										ReceivedDAOFactory.getPalletCardDAO().updateDelete(pc.getId());
									}
								}
								ReceivedDAOFactory.getReceivedDetailDAO().updateDelete(rd.getId());
							}
						}
						
						MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel",received);
					} catch (Exception e2) {
						log.error(e2.getMessage());
						e2.printStackTrace();
					}
				}
			}
		});
		
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					java.sql.Connection conn = DataSourceFactory.getDataSource().getConnection();
					JasperDesign jDesign = JRXmlLoader.load("src/module/pembelian/report/ReceivedReport.jrxml");
					String sql = "SELECT length,thickness,width,grade,total,volume FROM received_detail a INNER JOIN pallet_card b ON a.id = b.received_detail_id INNER JOIN grade c ON  a.grade_id = c.id WHERE a.received_code = '"+received.getReceivedCode()+"'";
					JRDesignQuery jDesignQuery = new JRDesignQuery();		
					jDesignQuery.setText(sql);
					jDesign.setQuery(jDesignQuery);
					JasperReport jreprt = JasperCompileManager.compileReport(jDesign);
					Map<String, Object> maps = new HashMap<>();
					maps.put("received_code",received.getReceivedCode());
					maps.put("driver_name", received.getDriver());
					maps.put("license_plate", received.getLicensePlate());
					maps.put("date", new SimpleDateFormat("dd/MM/yy").format(received.getReceivedDate()));
					maps.put("delivery_note", received.getDeliveryNote());
					maps.put("wood_domicile", received.getWoodDomicile());
					JasperPrint jprintt = JasperFillManager.fillReport(jreprt, maps, conn);
					JasperViewer.viewReport(jprintt, false);

//					 String namafile= "src/module/pembelian/report/PurchaseReport.jasper"; 
//					    File report = new File(namafile);
//					    JasperReport jreprt = (JasperReport)JRLoader.loadObject(report.getPath());
//					    JasperPrint jprintt = JasperFillManager.fillReport(jreprt,null,conn);
//					    JasperViewer.viewReport(jprintt,false);
				} catch (Exception ex) {
					log.error(ex.getMessage());
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Gagal Membuka Laporan", "Cetak Laporan",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				printBtn.requestFocusInWindow();
			}
		});
		
//		 KeyboardFocusManager.getCurrentKeyboardFocusManager().
//         addPropertyChangeListener("focusOwner", new PropertyChangeListener() {
//
//	     @Override
//	     public void propertyChange(PropertyChangeEvent evt) {
//		         if (!(evt.getNewValue() instanceof JComponent)) {
//		             return;
//		         }
//		         JViewport viewport = (JViewport) containerPnl.getParent();
//		         JComponent focused = (JComponent) evt.getNewValue();
//		         if (containerPnl.isAncestorOf(focused)) {
//		             Rectangle rect = focused.getBounds();
//		             Rectangle r2 = viewport.getVisibleRect();
//		             containerPnl.scrollRectToVisible(new Rectangle(rect.x, rect.y, (int) r2.getWidth(), (int) r2.getHeight()));
//		         }
//	     	}
//		 });
		

	}
	
	public void setTablePic(List<PicDocking> picDockings){
		this.picDockings = new ArrayList<>();
		this.picDockings = picDockings;
		dockingPICTable.setModel(new PicDockingTableModel(this.picDockings));
		dockingPICTable.updateUI();
	}
	
	public void createGUI(){
		setLayout(null);
		this.parent = this;
		
		containerPnl = new JPanel();
		containerPnl.setPreferredSize(new Dimension(1100, 900));
		containerPnl.setLayout(null);
		
		scrollPane = new JScrollPane(containerPnl);
		scrollPane.setBounds(0,0,1100,630);
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
	
		//Received By
		receivedByLbl = new JLabel("<html>Diterima oleh <font color='red'>*</font></html>");
		receivedByLbl.setBounds(550,370,150,20);
		containerPnl.add(receivedByLbl);
		
		receivedByField = new TextField();
		receivedByField.setBounds(720, 370, 150, 20);
		containerPnl.add(receivedByField);
			
		//Grader
		graderLbl = new JLabel("<html>Grader <font color='red'>*</font></html>");
		graderLbl.setBounds(550,410,150,20);
		containerPnl.add(graderLbl);
		
		graderComboBox = new ComboBox<>();
		graderComboBox.setBounds(720, 410, 150, 20);
		containerPnl.add(graderComboBox);
		
		
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
		addPalletBtn.setEnabled(false);
		containerPnl.add(addPalletBtn);
		
		deletePalletBtn = new JButton("Hapus");
		deletePalletBtn.setBounds(900,490,100,30);
		deletePalletBtn.setEnabled(false);
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
		deletePicBtn.setEnabled(false);
		containerPnl.add(deletePicBtn);
		
		searchPicBtn = new JButton("Cari");
		searchPicBtn.setBounds(570,710,100,30);
		searchPicBtn.setEnabled(false);
		containerPnl.add(searchPicBtn);
		
		inputBtn = new JButton("Input");
		inputBtn.setBounds(950,840,100,30);
		containerPnl.add(inputBtn);
		
		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(830,840,100,30);
		containerPnl.add(deleteBtn);
		
		printBtn = new JButton("Cetak");
		printBtn.setBounds(710,840,100,30);
		containerPnl.add(printBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,840,100,30);
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
	                return p.getTotalVolume()/1000000;
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
		totalVolumeByAdminField.setEnabled(false);
		docTypeField.setEnabled(false);
		docDateField.setEnabled(false);
		receivedByField.setEnabled(false);

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
		subSupplierField.setText(received.getSubSupplierName());
		graderComboBox.setEnabled(false);
		gradeComboBox.setEnabled(false);
		totalVolumeByAdminField.setText(received.getTotalVolume()+"");
		receivedByField.setText(received.getReceivedBy());

		Delivery delivery;
		SupplierCP subSupplier;
		try {
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
		} catch (SQLException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			receivedDetails = ReceivedDAOFactory.getReceivedDetailDAO().getAllReceivedDetail(received.getReceivedCode());
			receivedDetailTable.setModel(new ReceivedDetailModel(receivedDetails));
			receivedDetailTable.updateUI();
			
			picDockings = ReceivedDAOFactory.getPicDockingReceivedDAO().getPICDocking(received.getReceivedCode());
			dockingPICTable.setModel(new PicDockingTableModel(picDockings));
			dockingPICTable.updateUI();
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		if(received.getReceivedStatus().equals("FINAL")){
			inputBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
		}
	

	}
}