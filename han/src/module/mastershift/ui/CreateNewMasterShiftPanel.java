package module.mastershift.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.mastershift.bl.MasterShiftBL;
import module.mastershift.model.MasterShift;
import module.mastershift.model.MasterShiftDetail;
import module.mastershift.ui.ViewMasterShiftPanel.ShiftDtlTableModel;
import module.util.Bridging;

public class CreateNewMasterShiftPanel extends JPanel implements Bridging{
	Logger log = LogManager.getLogger(CreateNewMasterShiftPanel.class.getName());
	private static final long serialVersionUID = 1L;
	
	private JLabel shiftCodeLbl;
	private JLabel shiftNameLbl;
	private JLabel typeLbl;
	private JLabel inLbl;
	private JLabel outLbl;
	private JLabel restLbl;
	private JLabel holidayLbl;
	private JLabel weekLbl;
	private JLabel inSeparatorLbl;
	private JLabel outSeparatorLbl;
	private JLabel restHourLbl;
	private JLabel unAssignedDateLbl;
	private JLabel unAssignedDayLbl;
	
	private JLabel shiftNameErrorLbl;
	private JLabel shiftTypeErrorLbl;
	private JLabel inErrorLbl;
	private JLabel outErrorLbl;
	private JLabel restErrorLbl;
	private JLabel weekErrorLbl;
	private JLabel dayErrorLbl;
	private JLabel dateErrorLbl;
	
	private JPanel dayPanel;
	private JPanel datePanel;
	
	private TextField shiftCodeField;
	private TextField shiftNameField;
	private JComboBox<String> typeCmb;
	private NumberField inMinuteField;
	private NumberField inHourField;
	private NumberField outMinuteField;
	private NumberField outHourField;
	private NumberField restField;
	private NumberField weekField;
	private JRadioButton yesRadio;
	private JRadioButton noRadio;
	private ButtonGroup bgHoliday;
	
	private JButton addShiftDtlBtn;
	private JButton saveBtn;
	private JButton backBtn;
	
	private List<JCheckBox> unAssignedDates;
	private List<JCheckBox> unAssignedDays;
	
	private JTable shiftDtlTable;
	private JScrollPane scrollPane;
	
	private MasterShift masterShift;
	private boolean editMode=false;
	private boolean editDetailMode=false;
	private int indexEdited=0;
	
//	private JPanel container;
//	private JScrollPane containerPane;
	
	private String [] types = {"-Pilih-","Daily","Weekly","Pattern"};
	private String[] days = {"Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"};
	private List<MasterShiftDetail> masterShiftDtls;
	private List<MasterShiftDetail> deletedDetails;
	DecimalFormat df = new DecimalFormat("#.00"); 
	
	public CreateNewMasterShiftPanel(){
		createGUI();
		listener();
		initData();
	}
	
	private void showWeeklyPanel(){
		datePanel.setVisible(false);
		weekField.setEnabled(false);
		dayPanel.setVisible(true);
	}
	
	private void showDailyPanel(){
		datePanel.setVisible(true);
		dayPanel.setVisible(false);
		weekField.setEnabled(false);
	}
	private void showPatternPanel(){
		datePanel.setVisible(false);
		dayPanel.setVisible(true);
		weekField.setEnabled(true);
	}
	private void listener(){
		typeCmb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(typeCmb.getSelectedIndex()==1){
					showDailyPanel();
				}else if(typeCmb.getSelectedIndex()==2){
					showWeeklyPanel();
				}else if(typeCmb.getSelectedIndex()==3){
					showPatternPanel();
				}else{
					datePanel.setVisible(false);
					dayPanel.setVisible(false);
					weekField.setEnabled(false);
				}
			}
		});
		
		yesRadio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				disableHolidayComponent();
			}
		});
		
		noRadio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				enableHolidayComponent();
			}
		});
		
		addShiftDtlBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		shiftDtlTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(shiftDtlTable.columnAtPoint(e.getPoint())==8){
					MasterShiftDetail msd = masterShiftDtls.get(shiftDtlTable.getSelectedRow());
					if(msd.getHoliday().equals("y")){
						yesRadio.setSelected(true);
					}else{
						noRadio.setSelected(true);
						String [] in = msd.getIn().split(":");
						inHourField.setText(in[0]);
						inMinuteField.setText(in[1]);
						
						String [] out = msd.getOut().split(":");
						outHourField.setText(out[0]);
						outMinuteField.setText(out[1]);
						
						restField.setText(msd.getRest()+"");
						if(typeCmb.getSelectedIndex()==1){
							String [] dateSplit = msd.getDay().split(",");
							List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
							for(JCheckBox cb : unAssignedDates){
								if(listOfDate.contains(cb.getText())){
									cb.setVisible(true);
									cb.setSelected(true);
								}
							}
							datePanel.updateUI();
						}
						if(typeCmb.getSelectedIndex()==2){
							String [] dateSplit = msd.getDay().split(",");
							List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
							for(JCheckBox cb : unAssignedDays){
								if(listOfDate.contains(cb.getText())){
									cb.setVisible(true);
									cb.setSelected(true);
								}
							}
							dayPanel.updateUI();
						}
						if(typeCmb.getSelectedIndex()==3){
							weekField.setText(msd.getWeek()+"");
							String [] dateSplit = msd.getDay().split(",");
							List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
							for(JCheckBox cb : unAssignedDays){
								if(listOfDate.contains(cb.getText())){
									cb.setSelected(true);
								}
							}
							dayPanel.updateUI();
						}
						editDetailMode=true;
						indexEdited = shiftDtlTable.getSelectedRow();
						addShiftDtlBtn.setText("Ubah");
					}
				}
				
				if(shiftDtlTable.columnAtPoint(e.getPoint())==9){
					MasterShiftDetail msd = masterShiftDtls.get(shiftDtlTable.getSelectedRow());
					if(typeCmb.getSelectedIndex()==1){
						String [] dateSplit = msd.getDay().split(",");
						List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
						for(JCheckBox cb : unAssignedDates){
							if(listOfDate.contains(cb.getText())){
								cb.setVisible(true);
							}
						}
						datePanel.updateUI();
					}
					if(typeCmb.getSelectedIndex()==2){
						String [] dateSplit = msd.getDay().split(",");
						List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
						for(JCheckBox cb : unAssignedDays){
							if(listOfDate.contains(cb.getText())){
								cb.setVisible(true);
							}
						}
						dayPanel.updateUI();
					}
					deletedDetails.add(msd);
					masterShiftDtls.remove(msd);
					shiftDtlTable.updateUI();
					if(masterShiftDtls.size()==0)typeCmb.setEnabled(true);
				}
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.mastershift.ui.ListMasterShiftPanel");
			}
		});
	}
	
	private void clearData(){
		inHourField.setText("");
		inMinuteField.setText("");
		outHourField.setText("");
		outMinuteField.setText("");
		weekField.setText("");
		restField.setText("");
		if(editDetailMode){
			addShiftDtlBtn.setText("Tambah");
			editDetailMode=false;
			indexEdited=0;
		}
	}
	
	private void initData(){
		dayPanel.setVisible(false);
		datePanel.setVisible(false);
		masterShiftDtls = new ArrayList<>();
		deletedDetails = new ArrayList<>();
		noRadio.setSelected(true);
		weekField.setEnabled(false);
		shiftCodeField.setText(ServiceFactory.getMasterShiftBL().getLastShiftCode()+"/S/"+new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		shiftCodeField.setEnabled(false);
		masterShift = new MasterShift();
	}
	
	private void disableHolidayComponent(){
		inHourField.setEnabled(false);
		inMinuteField.setEnabled(false);
		outHourField.setEnabled(false);
		outMinuteField.setEnabled(false);
		restField.setEnabled(false);
		
		
		inHourField.setText("");
		outHourField.setText("");
		inMinuteField.setText("");
		outMinuteField.setText("");
		restField.setText("");
	}
	private void enableHolidayComponent(){
		inHourField.setEnabled(true);
		inMinuteField.setEnabled(true);
		outHourField.setEnabled(true);
		outMinuteField.setEnabled(true);
		restField.setEnabled(true);
	}
	
	private void createGUI(){
		setLayout(null);
		
//		container = new JPanel();
//		container.setLayout(null);
//		container.setPreferredSize(new Dimension(1100, 900));
//		
//		containerPane = new JScrollPane(container);
//		containerPane.setBounds(0,0,1170,630);
//		containerPane.getVerticalScrollBar().setUnitIncrement(16);
//		add(containerPane);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Master Shift");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("INPUT MASTER SHIFT");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		shiftCodeLbl = new JLabel("Kode Shift");
		shiftCodeLbl.setBounds(50,85,150,30);
		add(shiftCodeLbl);
		
		shiftCodeField = new TextField();
		shiftCodeField.setBounds(210,85,150,30);
		add(shiftCodeField);
		
		shiftNameLbl = new JLabel("Nama Shift");
		shiftNameLbl.setBounds(50,125,150,30);
		add(shiftNameLbl);
		
		shiftNameField = new TextField();
		shiftNameField.setBounds(210,125,150,30);
		add(shiftNameField);
		
		shiftNameErrorLbl = new JLabel();
		shiftNameErrorLbl.setBounds(365,125,150,30);
		add(shiftNameErrorLbl);
		
		typeLbl = new JLabel("Tipe");
		typeLbl.setBounds(50,165,150,30);
		add(typeLbl);
		
		typeCmb = new JComboBox<>(types);
		typeCmb.setBounds(210,165,150,30);
		add(typeCmb);
		
		shiftTypeErrorLbl = new JLabel();
		shiftTypeErrorLbl.setBounds(365,165,150,30);
		add(shiftTypeErrorLbl);
		
		inLbl = new JLabel("Jam Masuk");
		inLbl.setBounds(50,205,150,30);
		add(inLbl);
		
		inHourField = new NumberField(2);
		inHourField.setBounds(210,205,50,30);
		add(inHourField);
		
		inSeparatorLbl = new JLabel(":");
		inSeparatorLbl.setBounds(265,205,5,30);
		add(inSeparatorLbl);
		
		inMinuteField = new NumberField(2);
		inMinuteField.setBounds(271,205,50,30);
		add(inMinuteField);
		
		inErrorLbl = new JLabel();
		inErrorLbl.setBounds(326,205,150,30);
		add(inErrorLbl);
		
		outLbl = new JLabel("Jam Keluar");
		outLbl.setBounds(50,245,150,30);
		add(outLbl);
		
		outHourField = new NumberField(2);
		outHourField.setBounds(210,245,50,30);
		add(outHourField);
		
		outSeparatorLbl = new JLabel(":");
		outSeparatorLbl.setBounds(265,245,5,30);
		add(outSeparatorLbl);
		
		outMinuteField = new NumberField(2);
		outMinuteField.setBounds(271,245,50,30);
		add(outMinuteField);
		
		outErrorLbl = new JLabel();
		outErrorLbl.setBounds(326,245,150,30);
		add(outErrorLbl);
		
		restLbl = new JLabel("Istirahat");
		restLbl.setBounds(50,285,150,30);
		add(restLbl);
		
		restField = new NumberField(1);
		restField.setBounds(210,285,150,30);
		add(restField);
		
		restHourLbl = new JLabel("Jam");
		restHourLbl.setBounds(365,285,150,30);
		add(restHourLbl);
		
		restErrorLbl = new JLabel();
		restErrorLbl.setBounds(390,285,150,30);
		add(restErrorLbl);
		
		holidayLbl = new JLabel("Libur");
		holidayLbl.setBounds(50,325,150,30);
		add(holidayLbl);
		
		yesRadio = new JRadioButton("Yes");
		yesRadio.setBounds(210,325,50,30);
		add(yesRadio);
		
		noRadio = new JRadioButton("No");
		noRadio.setBounds(270,325,50,30);
		add(noRadio);
		
		bgHoliday = new ButtonGroup();
		bgHoliday.add(yesRadio);
		bgHoliday.add(noRadio);
		
		weekLbl = new JLabel("Minggu Ke ");
		weekLbl.setBounds(50,365,150,30);
		add(weekLbl);
		
		weekField = new NumberField(1);
		weekField.setBounds(210,365,50,30);
		add(weekField);
		
		weekErrorLbl = new JLabel();
		weekErrorLbl.setBounds(265,365,150,30);
		add(weekErrorLbl);
		
		addShiftDtlBtn = new JButton("Tambah");
		addShiftDtlBtn.setBounds(350,405,150,30);
		add(addShiftDtlBtn);
		
		shiftDtlTable = new JTable(new ShiftDtlTableModel(new ArrayList<>()));
		shiftDtlTable.setFocusable(false);
		
		scrollPane = new JScrollPane(shiftDtlTable);
		scrollPane.setBounds(50,445,1000,140);
		add(scrollPane);
		
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(900,595,150,30);
		add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,595,150,30);
		backBtn.setFocusable(false);
		add(backBtn);
		
		createDateCheckBoxs();
		createDayCheckBoxs();
	}
		
	private void save(){
		int error = 0;
		if(shiftNameField.getText().equals("")){
			shiftNameErrorLbl.setText("<html><font color='red'>Nama harus diisi !</font></html>");
			error++;
		}else{
			shiftNameErrorLbl.setText("");
		}
		
		if(error==0){
			masterShift.setShiftCode(shiftCodeField.getText());
			masterShift.setShiftName(shiftNameField.getText());
			masterShift.setType(String.valueOf(typeCmb.getSelectedItem()));
			masterShift.setMasterShiftDetails(masterShiftDtls);
			masterShift.setDeletedShiftDetails(deletedDetails);
			if(!editMode){
				if (DialogBox.showInsertChoice()==JOptionPane.YES_OPTION) {
					ServiceFactory.getMasterShiftBL().save(masterShift);
					MainPanel.changePanel("module.mastershift.ui.ListMasterShiftPanel");
				}
			}else{
				if (DialogBox.showEditChoice()==JOptionPane.YES_OPTION) {
					ServiceFactory.getMasterShiftBL().update(masterShift);
					MainPanel.changePanel("module.mastershift.ui.ListMasterShiftPanel");
				}
			}
		}
	
		
	}
	private void add(){
		int error=0;
		if(typeCmb.getSelectedIndex()==0){
			shiftTypeErrorLbl.setText("<html><font color='red'>Tipe harus dipilih !</font></html>");
			error++;
		}else{
			shiftTypeErrorLbl.setText("");
		}
		if(noRadio.isSelected()) {
			if(inHourField.getText().length()!=2||inMinuteField.getText().length()!=2){
				inErrorLbl.setText("<html><font color='red'>Format Jam masuk harus 00:00 !</font></html>");
				error++;
			}else{
				if(Integer.valueOf(inHourField.getText())>23||Integer.valueOf(inMinuteField.getText())>59){
					inErrorLbl.setText("<html><font color='red'>Format Jam keluar Tidak boleh lebih dari 23:59 !</font></html>");
					error++;
				}else{
					inErrorLbl.setText("");
				}
			}
			if(outHourField.getText().length()!=2||outMinuteField.getText().length()!=2){
				outErrorLbl.setText("<html><font color='red'>Format Jam keluar harus 00:00 !</font></html>");
				error++;
			}else{
				if(Integer.valueOf(outHourField.getText())>23||Integer.valueOf(outMinuteField.getText())>59){
					outErrorLbl.setText("<html><font color='red'>Format Jam keluar Tidak boleh lebih dari 23:59 !</font></html>");
					error++;
				}else{
					outErrorLbl.setText("");
				}
			}
			if(restField.getText().equals("")){
				restErrorLbl.setText("<html><font color='red'>Istirahat harus diisi !</font></html>");
				error++;
			}else{
				restErrorLbl.setText("");
			}
		}else{
			restErrorLbl.setText("");
			inErrorLbl.setText("");
			outErrorLbl.setText("");
		}
		if(typeCmb.getSelectedIndex()==1){
			boolean comboFlag = false;
			cbLoop:
			for(JCheckBox cb : unAssignedDates){
				if(cb.isSelected()==true){
					comboFlag=true;
					break cbLoop;
				}
			}
			if(!comboFlag){
				dateErrorLbl.setText("<html><font color='red'>Tanggal harus dipilih !</font></html>");
				error++;
			}else{
				dateErrorLbl.setText("");
			}
		}
		
		if(typeCmb.getSelectedIndex()==2||typeCmb.getSelectedIndex()==3){
			boolean comboFlag = false;
			cbLoop:
			for(JCheckBox cb : unAssignedDays){
				if(cb.isSelected()==true){
					comboFlag=true;
					break cbLoop;
				}
			}
			if(!comboFlag){
				dayErrorLbl.setText("<html><font color='red'>Hari harus dipilih !</font></html>");
				error++;
			}else{
				dayErrorLbl.setText("");
			}
		}
		if(typeCmb.getSelectedIndex()==3){
			if(weekField.getText().equals("")){
				weekErrorLbl.setText("<html><font color='red'>Minggu harus diisi !</font></html>");
				error++;
			}else{
				if(Integer.valueOf(weekField.getText())>4){
					weekErrorLbl.setText("<html><font color='red'>Minggu maksimal 4 !</font></html>");
				}else{
					weekErrorLbl.setText("");
				}
			}
		}
		
		if(error==0){
			MasterShiftDetail msd = new MasterShiftDetail();
			if(editDetailMode)msd = masterShiftDtls.get(indexEdited);
			if(yesRadio.isSelected()){
				msd.setHoliday("y");
			}else{
				msd.setHoliday("n");
				msd.setIn(inHourField.getText()+":"+inMinuteField.getText());
				msd.setOut(outHourField.getText()+":"+outMinuteField.getText());
				msd.setRest(Integer.valueOf(restField.getText()));
			}

			StringBuffer dates=new StringBuffer();
			if(typeCmb.getSelectedIndex()==1){
				for (JCheckBox cb : unAssignedDates) {
					if(cb.isSelected()){
						dates.append(cb.getText()+",");
						cb.setVisible(false);
						cb.setSelected(false);
					};
				}
			}else{
				for (JCheckBox cb : unAssignedDays) {
					if(cb.isSelected()){
						dates.append(cb.getText()+",");
						if(typeCmb.getSelectedIndex()==2)cb.setVisible(false);
						cb.setSelected(false);
					};
				}
			}
			if(!weekField.getText().equals(""))msd.setWeek(Integer.valueOf(weekField.getText()));
			msd.setDay(removeLastCharacter(dates.toString()));
			if(!editDetailMode)masterShiftDtls.add(msd);
			shiftDtlTable.setModel(new ShiftDtlTableModel(masterShiftDtls));
			shiftDtlTable.updateUI();
			typeCmb.setEnabled(false);
			clearData();
		}
	
	}
	public String removeLastCharacter(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length()-1)==',') {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}

	
	private void createDateCheckBoxs(){
		datePanel = new JPanel();
		datePanel.setBounds(600, 85, 400, 250);
		datePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		datePanel.setLayout(null);
		add(datePanel);
		
		unAssignedDateLbl = new JLabel("Tanggal");
		unAssignedDateLbl.setBounds(20,5,150,40);
		unAssignedDateLbl.setFont(new Font("Tahoma", 1, 14));
		datePanel.add(unAssignedDateLbl);
		
		JLabel line = new JLabel();
		line.setBounds(0,40,400,1);
		line.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		datePanel.add(line);
		
		dateErrorLbl = new JLabel();
		dateErrorLbl.setBounds(160,210,150,30);
		datePanel.add(dateErrorLbl);
		
		unAssignedDates = new ArrayList<>();
		int paddingTop = 0;
		int paddingRight = 40;
		int paddingLine=0;
		for (int i = 0; i < 31; i++) {
			if(i<9){
				paddingTop=0;
				paddingLine=i;
			}
			else if(i>8&&i<18){
				paddingTop=40;
				paddingLine=i-9;
			}
			else if(i>17&&i<27){
				paddingTop=80;
				paddingLine=i-18;
			}
			else if(i>26){
				paddingTop=120;
				paddingLine=i-27;
			}
			JCheckBox checkbox = new JCheckBox(i+1+"");
			checkbox.setBounds(20+(paddingLine*paddingRight),50+paddingTop,40,20);
			datePanel.add(checkbox);
			unAssignedDates.add(checkbox);
		}
		
	}

	private void createDayCheckBoxs(){
	
		dayPanel = new JPanel();
		dayPanel.setBounds(600, 85, 400, 250);
		dayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		dayPanel.setLayout(null);
		add(dayPanel);
		
		unAssignedDayLbl = new JLabel("Hari");
		unAssignedDayLbl.setBounds(20,5,150,40);
		unAssignedDayLbl.setFont(new Font("Tahoma", 1, 14));
		dayPanel.add(unAssignedDayLbl);
		
		dayErrorLbl = new JLabel();
		dayErrorLbl.setBounds(160,210,150,30);
		dayPanel.add(dayErrorLbl);
		
		JLabel line = new JLabel();
		line.setBounds(0,40,400,1);
		line.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dayPanel.add(line);
		
		unAssignedDays = new ArrayList<>();
		int paddingTop;
		int paddingRight;
		for (int i = 0; i < 7; i++) {
			if(i<4){
				paddingRight=0;
				paddingTop=i*40;
			}else{
				paddingRight=100;
				paddingTop=(i-3)*40;
			}
			JCheckBox checkBox = new JCheckBox(days[i]);
			checkBox.setBounds(20+paddingRight,60+paddingTop,100,20);
			dayPanel.add(checkBox);
			unAssignedDays.add(checkBox);
		}
		
	}
	
	class ShiftDtlTableModel extends AbstractTableModel {
	    private List<MasterShiftDetail> masterShiftDtls;
	    public ShiftDtlTableModel(List<MasterShiftDetail> masterShiftDtls) {
	        this.masterShiftDtls = masterShiftDtls;
	    }
	    
	    /**
	     * Method to get row count
	     * @return int
	     */
	    public int getRowCount() {
	        return masterShiftDtls.size();
	    }
	    
	    /**
	     * Method to get Column Count
	     */
	    public int getColumnCount() {
	        return 10;
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
	    	MasterShiftDetail p = masterShiftDtls.get(rowIndex);
	    	String workTime = "";
	    	if(p.getHoliday().equals("n")){
	    		String[] outTime = p.getOut().split(":");
		    	double outHour = Double.valueOf(outTime[0]) + (Double.valueOf(outTime[1])/60);
		    	String[] inTime = p.getIn().split(":");
		    	double inHour = Double.valueOf(inTime[0]) + (Double.valueOf(inTime[1])/60);
		    	workTime = df.format(outHour-inHour)+" Jam";
	    	}else{
	    		workTime="";
	    	}
	    	
	    	
	        switch(columnIndex){
		        case 0 :
		        	return rowIndex+1;
	            case 1 : 
	                return p.getIn();
	            case 2 :
	                return p.getOut();
	            case 3 :
	                return p.getRest();
	            case 4 :
	                return p.getWeek();
	            case 5 :
	                return p.getHoliday();
	            case 6 :
	                return workTime;
	            case 7 :
	                return p.getDay();
	            case 8 :
	                return "Ubah";
	            case 9 :
	                return "Hapus";
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
	                return "No";
	            case 1 :
	                return "Masuk";
	            case 2 :
	                return "Keluar";
	            case 3 :
	                return "Istirahat";
	            case 4 :
	                return "Minggu Ke";
	            case 5 :
	                return "Libur";
	            case 6 :
	                return "Total Jam Kerja";
	            case 7 :
	                return "Hari";
	            case 8 :
	                return "Tindakan";
	            case 9 :
	                return "Tindakan";
	            default :
	                return "";
	        }
	    }

	}
	
	@Override
	public void invokeObjects(Object... objects) {
		if(objects.length>0){
			masterShift = (MasterShift) objects[0];
			typeCmb.setSelectedItem(masterShift.getType());
			shiftNameField.setText(masterShift.getShiftName());
			shiftCodeField.setText(masterShift.getShiftCode());
			masterShiftDtls = masterShift.getMasterShiftDetails();
			for (MasterShiftDetail msd : masterShiftDtls) {
				if(typeCmb.getSelectedIndex()==1){
					String [] dateSplit = msd.getDay().split(",");
					List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
					for(JCheckBox cb : unAssignedDates){
						if(listOfDate.contains(cb.getText())){
							cb.setVisible(false);
						}
					}
					datePanel.updateUI();
				}
				if(typeCmb.getSelectedIndex()==2){
					String [] dateSplit = msd.getDay().split(",");
					List<String> listOfDate=  new ArrayList<String>(Arrays.asList(dateSplit));
					for(JCheckBox cb : unAssignedDays){
						if(listOfDate.contains(cb.getText())){
							cb.setVisible(false);
						}
					}
					dayPanel.updateUI();
				}
			}
			if(masterShiftDtls.size()>0)typeCmb.setEnabled(false);
			shiftDtlTable.setModel(new ShiftDtlTableModel(masterShiftDtls));
			shiftDtlTable.updateUI();
			editMode=true;
		}
		
	}
	
	
}
