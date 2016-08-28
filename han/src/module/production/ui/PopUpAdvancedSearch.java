package module.production.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import controller.ReceivedDAOFactory;
import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.TextField;
import module.pembelian.ui.PopUpAdvanceSearch.ReceivedTableModel;
import module.production.bl.ProductionBL;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.Production;
import module.production.model.Shift;

public class PopUpAdvancedSearch extends JDialog{
	private JLabel productionCodeLbl;
	private JLabel startDateLbl;
	private JLabel endDateLbl;
	private JLabel groupShiftLbl;
	private JLabel lineLbl;
	private JLabel shiftLbl;
	private JLabel statusProductionLbl;
	
	private TextField productionCodeField;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private ComboBox<GroupShift> groupShiftCmb;
	private ComboBox<Shift> shiftCmb;
	private ComboBox<Line> lineCmb;
	private JComboBox<String> statusCmb;
	
	private JButton searchBtn;
	private JButton resetBtn;
	
	public PopUpAdvancedSearch(ListProductionPanel parent){
		super((JFrame) parent.getTopLevelAncestor());
		createGUI();
		initData();
		listener(parent);
	}
	
	private void createGUI(){
		setLayout(null);
		setSize(500,500);
		
		JLabel lblHeader = new JLabel("PENCARIAN LANJUT");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(30, 45, 320, 30);
		add(lblHeader);
		
		//TODO ProductionCode Area
		productionCodeLbl = new JLabel("Kode Produksi");
		productionCodeLbl.setBounds(30,120,150,20);
		add(productionCodeLbl);
		
		productionCodeField = new TextField();
		productionCodeField.setBounds(190, 120, 150, 20);
		add(productionCodeField);
		
		//TODO startDate Area
		startDateLbl = new JLabel("Tanggal Produksi");
		startDateLbl.setBounds(30,160,150,20);
		add(startDateLbl);
		
		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(190,160,150,20);
		add(startDateChooser);
		
		//TODO endDate Area
		endDateLbl = new JLabel("Tanggal Produksi");
		endDateLbl.setBounds(30,200,150,20);
		add(endDateLbl);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setBounds(190,200,150,20);
		add(endDateChooser);
		
		//TODO GroupShift Area
		groupShiftLbl = new JLabel("Group Shift");
		groupShiftLbl.setBounds(30,240,150,20);
		add(groupShiftLbl);
		
		groupShiftCmb = new ComboBox<>();
		groupShiftCmb.setBounds(190,240,150,20);
		add(groupShiftCmb);

		
		//TODO Shift Area
		shiftLbl = new JLabel("Shift");
		shiftLbl.setBounds(30,280,150,20);
		add(shiftLbl);
		
		shiftCmb = new ComboBox<>();
		shiftCmb.setBounds(190,280,150,20);
		add(shiftCmb);

		//TODO Line Area
		lineLbl = new JLabel("Line");
		lineLbl.setBounds(30,320,150,20);
		add(lineLbl);
		
		lineCmb = new ComboBox<>();
		lineCmb.setBounds(190,320,150,20);
		add(lineCmb);
		
		statusProductionLbl = new JLabel("Status");
		statusProductionLbl.setBounds(30, 360, 150, 20);
		add(statusProductionLbl);
		
		statusCmb = new JComboBox<>();
		statusCmb.setBounds(190,360,150,20);
		add(statusCmb);
		
		searchBtn = new JButton("Cari");
		searchBtn.setBounds(90,400,150,30);
		add(searchBtn);
		
		resetBtn = new JButton("Reset");
		resetBtn.setBounds(260,400,150,30);
		add(resetBtn);

	}
	
	private void initData(){
		List<Shift> shifts = new ArrayList<>();
		List<Line> lines = new ArrayList<>();
		List<GroupShift> groupShifts = new ArrayList<>();
		try {
			lines = ServiceFactory.getProductionBL().getLine();
			shifts = ServiceFactory.getProductionBL().getShift();
			groupShifts = ServiceFactory.getProductionBL().getGroupShift();
			
			lines.add(0,new Line("--Pilih--"));
			shifts.add(0,new Shift("--Pilih--"));
			groupShifts.add(0,new GroupShift("--Pilih--"));
			
			lineCmb.setList(lines);
			shiftCmb.setList(shifts);
			groupShiftCmb.setList(groupShifts);
			
			statusCmb.addItem("--Pilih--");
			statusCmb.addItem("InComplete");
			statusCmb.addItem("Completed");
			statusCmb.addItem("Final");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listener(ListProductionPanel parent){
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb = new StringBuffer();
				List<Object> objs = new ArrayList<>();
				if(!productionCodeField.getText().equals("")){
					sb.append(" AND production_code LIKE ?");
					objs.add(productionCodeField.getText());
				}else{
					objs.add(null);
				}
				if(startDateChooser.getDate()!=null&&endDateChooser.getDate()!=null){
					sb.append(" AND DATE(production_date) BETWEEN DATE(?) AND DATE(?)");
					objs.add(startDateChooser.getDate());
					objs.add(endDateChooser.getDate());
				}else{
					objs.add(null);
					objs.add(null);
				}
				
				if(groupShiftCmb.getSelectedIndex()!=0){
					sb.append(" AND d.description LIKE ?");
					objs.add(groupShiftCmb.getDataIndex().getDescription());
				}else{
					objs.add(null);
				}
				if(shiftCmb.getSelectedIndex()!=0){
					sb.append(" AND c.shift_name LIKE ?");
					objs.add(shiftCmb.getDataIndex().getShiftName());
				}else{
					objs.add(null);
				}
				if(lineCmb.getSelectedIndex()!=0){
					sb.append(" AND b.description LIKE ?");
					objs.add(lineCmb.getDataIndex().getDescription());
				}else{
					objs.add(null);
				}
				if(statusCmb.getSelectedIndex()!=0){
					sb.append(" AND status LIKE ?");
					objs.add(statusCmb.getSelectedItem().toString());
				}else{
					objs.add(null);
				}
				
				try {
					List<Production> productions = ServiceFactory.getProductionBL().advancedSearchProduction(sb.toString(), objs);
					parent.updateTableData(productions);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				productionCodeField.setText("");
				startDateChooser.setDate(null);
				endDateChooser.setDate(null);
				groupShiftCmb.setSelectedIndex(0);
				shiftCmb.setSelectedIndex(0);
				lineCmb.setSelectedIndex(0);
				statusCmb.setSelectedIndex(0);
			}
		});
	}

	
}
