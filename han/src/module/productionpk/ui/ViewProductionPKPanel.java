package module.productionpk.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.TextField;
import main.panel.MainPanel;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionpk.model.ProdPK;
import module.util.Bridging;

public class ViewProductionPKPanel extends JPanel implements Bridging{
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ViewProductionPKPanel.class.getName());
	private JLabel productionCodeLbl;
	private JLabel productionDateLbl;
	private JLabel groupShiftLbl;
	private JLabel shiftLbl;
	private JLabel lineLbl;
	
	private JLabel errorGroupShiftLbl;
	private JLabel errorLineLbl;
	private JLabel errorShiftLbl;
	
	private TextField productionCodeField;
	private JDateChooser productionDateChooser;
	private ComboBox<GroupShift> groupShiftCmb;
	private ComboBox<Shift> shiftCmb;
	private ComboBox<Line> lineCmb;
	
	private JButton inputMaterialBtn;
	private JButton inputProductionResultBtn;
	private JButton printBtn;
	private JButton backBtn;
	private JButton editBtn;
	
	private ProdPK production;
	private ViewProductionPKPanel parent;
	
	public ViewProductionPKPanel(){
		parent = this;
		production = new ProdPK();
		createGUI();
		listener();
		initData();
	}
	
	private void listener(){
		inputMaterialBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
//				PopUpViewMaterial pop = new PopUpViewMaterial(parent);
//				pop.show();
//				pop.setLocationRelativeTo(null);
			}
		});
		
		inputProductionResultBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
//				PopUpViewProductionResult pop = new PopUpViewProductionResult(parent);
//				pop.show();
//				pop.setLocationRelativeTo(null);
			}
		});
		
		printBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.production.ui.ListProductionPanel");
			}
		});
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.production.ui.CreateProductionPanel",production);
			}
		});
	}
	
	private void initData(){
		List<Shift> shifts = new ArrayList<>();
		List<Line> lines = new ArrayList<>();
		List<GroupShift> groupShifts = new ArrayList<>();
		List<ProductionType> productionTypes = new ArrayList<>();
		
		productionCodeField.setEnabled(false);
		try {
			lines = ServiceFactory.getProdPKBL().getLine();
			shifts = ServiceFactory.getProdPKBL().getShift();
			groupShifts = ServiceFactory.getProdPKBL().getGroupShift();
			
			lines.add(0,new Line("--Pilih--"));
			shifts.add(0,new Shift("--Pilih--"));
			groupShifts.add(0,new GroupShift("--Pilih--"));
			productionTypes.add(0,new ProductionType("--Pilih--"));
			
			lineCmb.setList(lines);
			shiftCmb.setList(shifts);
			groupShiftCmb.setList(groupShifts);
			
			Date currentDate = new Date();
			String date = new SimpleDateFormat("dd").format(currentDate);
			String month = new SimpleDateFormat("MM").format(currentDate);
			String year = new SimpleDateFormat("yy").format(currentDate);
			productionCodeField.setText(ServiceFactory.getProdPKBL().getProductionLastCode()+"/PD/"+date+"/"+month+"/"+year);
			productionDateChooser.setDate(currentDate);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void createGUI(){
		setLayout(null);
		
		//TODO Title Area
		JLabel lblBreadcrumb = new JLabel("ERP > Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("VIEW PRODUKSI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		//TODO ProductionCode Area
		productionCodeLbl = new JLabel("Kode Produksi");
		productionCodeLbl.setBounds(30,120,150,20);
		add(productionCodeLbl);
		
		productionCodeField = new TextField();
		productionCodeField.setBounds(190, 120, 150, 20);
		add(productionCodeField);
		
		//TODO ProductionDate Area
		productionDateLbl = new JLabel("Tanggal Produksi");
		productionDateLbl.setBounds(30,160,150,20);
		add(productionDateLbl);
		
		productionDateChooser = new JDateChooser();
		productionDateChooser.setBounds(190,160,150,20);
		productionDateChooser.setDateFormatString("dd-MM-yyyy");
		add(productionDateChooser);
		
		//TODO GroupShift Area
		groupShiftLbl = new JLabel("Group Shift");
		groupShiftLbl.setBounds(30,200,150,20);
		add(groupShiftLbl);
		
		groupShiftCmb = new ComboBox<>();
		groupShiftCmb.setBounds(190,200,150,20);
		add(groupShiftCmb);
		
		errorGroupShiftLbl = new JLabel();
		errorGroupShiftLbl.setBounds(345,200,150,20);
		add(errorGroupShiftLbl);
		
		//TODO Shift Area
		shiftLbl = new JLabel("Shift");
		shiftLbl.setBounds(30,240,150,20);
		add(shiftLbl);
		
		shiftCmb = new ComboBox<>();
		shiftCmb.setBounds(190,240,150,20);
		add(shiftCmb);
		
		errorShiftLbl = new JLabel();
		errorShiftLbl.setBounds(345,240,150,20);
		add(errorShiftLbl);
		
		//TODO Line Area
		lineLbl = new JLabel("Line");
		lineLbl.setBounds(30,280,150,20);
		add(lineLbl);
		
		lineCmb = new ComboBox<>();
		lineCmb.setBounds(190,280,150,20);
		add(lineCmb);
		
		errorLineLbl = new JLabel();
		errorLineLbl.setBounds(345,280,150,20);
		add(errorLineLbl);

		
		//TODO Button Area
		inputMaterialBtn = new JButton("View Bahan Baku");
		inputMaterialBtn.setBounds(470,550,150,30);
		add(inputMaterialBtn);
		
		inputProductionResultBtn = new JButton("View Hasil Produksi");
		inputProductionResultBtn.setBounds(630,550,150,30);
		add(inputProductionResultBtn);
		
		printBtn = new JButton("Cetak");
		printBtn.setBounds(790,550,150,30);
		add(printBtn);
		
		editBtn = new JButton("Ubah");
		editBtn.setBounds(950,550,150,30);
		add(editBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,550,150,30);
		add(backBtn);
	}
	
	

	@Override
	public void invokeObjects(Object... objects) {
		if(objects.length!=0)production = (ProdPK)objects[0];
		if(production!=null){
			productionCodeField.setText(production.getProdPKCode());
			productionDateChooser.setDate(production.getProductionDate());
			groupShiftCmb.setSelectedItem(production.getGroupShiftDescription());
			shiftCmb.setSelectedItem(production.getShiftName());
			lineCmb.setSelectedItem(production.getLineDescription());
			
			productionDateChooser.setEnabled(false);
			groupShiftCmb.setEnabled(false);
			shiftCmb.setEnabled(false);
			lineCmb.setEnabled(false);	
			
			if(production.getStatus().equals("Final")){
				editBtn.setEnabled(false);
			}
		}
	}

	public ProdPK getProduction() {
		return production;
	}

	public void setProduction(ProdPK production) {
		this.production = production;
	}

	public TextField getProductionCodeField() {
		return productionCodeField;
	}

	public void setProductionCodeField(TextField productionCodeField) {
		this.productionCodeField = productionCodeField;
	}
	
	
	
}
