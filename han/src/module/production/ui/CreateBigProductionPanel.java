package module.production.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.TextField;
import main.panel.MainPanel;
import module.pembelian.ui.PopUpAdvanceSearchSecurity;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.Shift;
import module.util.Bridging;
import module.production.model.Production;
import module.production.model.ProductionType;

public class CreateBigProductionPanel extends JPanel implements Bridging{
	Logger log = LogManager.getLogger(CreateBigProductionPanel.class.getName());
	private static final long serialVersionUID = 1L;
	
	private JLabel productionCodeLbl;
	private JLabel productionDateLbl;
	private JLabel groupShiftLbl;
	private JLabel shiftLbl;
	private JLabel lineLbl;
	private JLabel productionTypeLbl;
	
	private JLabel errorGroupShiftLbl;
	private JLabel errorLineLbl;
	private JLabel errorShiftLbl;
	private JLabel errorProductionTypeLbl;
	
	private TextField productionCodeField;
	private JDateChooser productionDateChooser;
	private ComboBox<GroupShift> groupShiftCmb;
	private ComboBox<Shift> shiftCmb;
	private ComboBox<Line> lineCmb;
	private ComboBox<ProductionType> productionTypeCmb;
	
	private JButton inputMaterialBtn;
	private JButton inputProductionResultBtn;
	private JButton saveBtn;
	private JButton backBtn;
	
	private Production production;
	private CreateBigProductionPanel parent;
	private boolean editMode=false;
	private String lastProductionCode;
	
	public CreateBigProductionPanel(){
		parent = this;
		production = new Production();
		createGUI();
		listener();
		initData();
	}
	
	private void listener(){
		inputMaterialBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpBigInputMaterial pop = new PopUpBigInputMaterial(parent);
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
//				pop.setModal(true);
			}
		});
		
		inputProductionResultBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpBigProductionResult pop = new PopUpBigProductionResult(parent);
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
//				pop.setModal(true);
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.production.ui.ListBigProductionPanel");
			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				productionDateLbl.requestFocusInWindow();
			}
		});
		
		productionDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if(e.getPropertyName()=="date"){
					Date currentDate = (Date)e.getNewValue();
					String date = new SimpleDateFormat("dd").format(currentDate);
					String month = new SimpleDateFormat("MM").format(currentDate);
					String year = new SimpleDateFormat("yy").format(currentDate);
					productionCodeField.setText(lastProductionCode+"/PD/"+date+"/"+month+"/"+year);
				}
			}
		});
		
		groupShiftCmb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				lineCmb.setSelectedItem(groupShiftCmb.getDataIndex().getLineDescription());
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
			lines = ServiceFactory.getProductionBL().getLine();
			shifts = ServiceFactory.getProductionBL().getShift();
			groupShifts = ServiceFactory.getProductionBL().getGroupShift();
			productionTypes = ServiceFactory.getProductionBL().getProductionType();
			
			lines.add(0,new Line("--Pilih--"));
			shifts.add(0,new Shift("--Pilih--"));
			groupShifts.add(0,new GroupShift("--Pilih--"));
			productionTypes.add(0,new ProductionType("--Pilih--"));
			
			lineCmb.setList(lines);
			lineCmb.setEnabled(false);
			shiftCmb.setList(shifts);
			groupShiftCmb.setList(groupShifts);
			productionTypeCmb.setList(productionTypes);
			productTypeLoop:
			for(int i = 0; i < productionTypes.size(); i++) {
				if(AppConstants.BC_TYPE_12.equalsIgnoreCase(productionTypes.get(i).getProductionTypeCode())) {
					productionTypeCmb.setSelectedIndex(i);
					break productTypeLoop;
				}
			}
			productionTypeCmb.setSelectedItem(AppConstants.BC_TYPE_12);
			productionTypeCmb.setEnabled(false);
			
			lastProductionCode = ServiceFactory.getProductionBL().getProductionLastCode();
			
			Date currentDate = new Date();
			String date = new SimpleDateFormat("dd").format(currentDate);
			String month = new SimpleDateFormat("MM").format(currentDate);
			String year = new SimpleDateFormat("yy").format(currentDate);
			productionCodeField.setText(lastProductionCode+"/PD/"+date+"/"+month+"/"+year);
			productionDateChooser.setDate(currentDate);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void createGUI(){
		setLayout(null);
		
		//TODO Title Area
		JLabel lblBreadcrumb = new JLabel("ERP > Produksi 12");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("INPUT PRODUKSI 12");
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
		productionDateLbl = new JLabel("<html>Tanggal Produksi <font color='red'>*</font></html>");
		productionDateLbl.setBounds(30,160,150,20);
		add(productionDateLbl);
		
		productionDateChooser = new JDateChooser();
		productionDateChooser.setBounds(190,160,150,20);
		productionDateChooser.setDateFormatString("dd-MM-yyyy");
		add(productionDateChooser);
		
		//TODO GroupShift Area
		groupShiftLbl = new JLabel("<html>Group Shift <font color='red'>*</font></html>");
		groupShiftLbl.setBounds(30,200,150,20);
		add(groupShiftLbl);
		
		groupShiftCmb = new ComboBox<>();
		groupShiftCmb.setBounds(190,200,150,20);
		add(groupShiftCmb);
		
		errorGroupShiftLbl = new JLabel();
		errorGroupShiftLbl.setBounds(345,200,150,20);
		add(errorGroupShiftLbl);
		
		//TODO Shift Area
		shiftLbl = new JLabel("<html>Shift <font color='red'>*</font></html>");
		shiftLbl.setBounds(30,240,150,20);
		add(shiftLbl);
		
		shiftCmb = new ComboBox<>();
		shiftCmb.setBounds(190,240,150,20);
		add(shiftCmb);
		
		errorShiftLbl = new JLabel();
		errorShiftLbl.setBounds(345,240,150,20);
		add(errorShiftLbl);
		
		//TODO Line Area
		lineLbl = new JLabel("<html>Line <font color='red'>*</font></html>");
		lineLbl.setBounds(30,280,150,20);
		add(lineLbl);
		
		lineCmb = new ComboBox<>();
		lineCmb.setBounds(190,280,150,20);
		add(lineCmb);
		
		errorLineLbl = new JLabel();
		errorLineLbl.setBounds(345,280,150,20);
		add(errorLineLbl);
		
		//TODO Production Type Area
		productionTypeLbl = new JLabel("<html>Tipe Produksi <font color='red'>*</font></html>");
		productionTypeLbl.setBounds(30,320,150,20);
		add(productionTypeLbl);
		
		productionTypeCmb = new ComboBox<>();
		productionTypeCmb.setBounds(190,320,150,20);
		add(productionTypeCmb);
		
		errorProductionTypeLbl = new JLabel();
		errorProductionTypeLbl.setBounds(345,320,150,20);
		add(errorProductionTypeLbl);
		
		//TODO Button Area
		inputMaterialBtn = new JButton("Input Bahan Baku");
		inputMaterialBtn.setBounds(630,550,150,30);
		add(inputMaterialBtn);
		
		inputProductionResultBtn = new JButton("Input Hasil Produksi");
		inputProductionResultBtn.setBounds(790,550,150,30);
		add(inputProductionResultBtn);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(950,550,150,30);
		add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,550,150,30);
		backBtn.setFocusable(false);
		add(backBtn);
	}
	
	private void save(){
		int error = 0;
		if(groupShiftCmb.getSelectedIndex()==0){
			errorGroupShiftLbl.setText("<html><font color='red'>Group Shift harus dipilih !</font></html>");
			error++;
		}else{
			errorGroupShiftLbl.setText("");
		}
		
		if(shiftCmb.getSelectedIndex()==0){
			errorShiftLbl.setText("<html><font color='red'>Shift harus dipilih !</font></html>");
			error++;
		}else{
			errorShiftLbl.setText("");
		}
		
		if(lineCmb.getSelectedIndex()==0){
			errorLineLbl.setText("<html><font color='red'>Line harus dipilih !</font></html>");
			error++;
		}else{
			errorLineLbl.setText("");
		}
		
		if(productionTypeCmb.getSelectedIndex()==0){
			errorProductionTypeLbl.setText("<html><font color='red'>Tipe Produksi harus dipilih !</font></html>");
			error++;
		}else{
			errorProductionTypeLbl.setText("");
		}
		
		if(error==0){
			if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
				try {
					production.setProductionCode(productionCodeField.getText());
					production.setGroupShiftCode(groupShiftCmb.getDataIndex().getGroupShiftCode());
					production.setShiftCode(shiftCmb.getDataIndex().getShiftCode());
					production.setLineCode(lineCmb.getDataIndex().getLineCode());
					production.setProductionDate(productionDateChooser.getDate());
					production.setProductionTypeCode(productionTypeCmb.getDataIndex().getProductionTypeCode());
					if(editMode){
						ServiceFactory.getProductionBL().updateAll(production);
						DialogBox.showEdit();
					}else {
						production.setType("12");
						ServiceFactory.getProductionBL().saveAll(production);
						DialogBox.showInsert();
					}
					MainPanel.changePanel("module.production.ui.ListBigProductionPanel");
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		if(objects.length!=0)production = (Production)objects[0];
		if(production!=null){
			editMode=true;
			productionDateChooser.setEnabled(false);
			lastProductionCode=production.getProductionCode().split("/")[0];
			productionCodeField.setText(production.getProductionCode());
			productionDateChooser.setDate(production.getProductionDate());
			groupShiftCmb.setSelectedItem(production.getGroupShiftDescription());
			shiftCmb.setSelectedItem(production.getShiftName());
			lineCmb.setSelectedItem(production.getLineDescription());
			productionTypeCmb.setSelectedItem(production.getProductionTypeDescription());
		}
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public TextField getProductionCodeField() {
		return productionCodeField;
	}

	public void setProductionCodeField(TextField productionCodeField) {
		this.productionCodeField = productionCodeField;
	}
	
	
	
}
