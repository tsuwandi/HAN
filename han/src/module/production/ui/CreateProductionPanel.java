package module.production.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import main.component.ComboBox;
import module.production.model.GroupShift;
import module.production.model.Shift;
import module.production.model.Line;

public class CreateProductionPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JLabel productionCodeLbl;
	JLabel productionDateLbl;
	JLabel groupShiftLbl;
	JLabel shiftLbl;
	JLabel lineLbl;
	
	JTextField productionCodeField;
	JDateChooser productionDateChooser;
	ComboBox<GroupShift> groupShiftCmb;
	ComboBox<Shift> shiftCmb;
	ComboBox<Line> lineCmb;
	
	JButton inputMaterialBtn;
	JButton inputProductionResultBtn;
	
	public CreateProductionPanel(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("INPUT PRODUKSI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		productionCodeLbl = new JLabel("Kode Produksi");
		productionCodeLbl.setBounds(30,120,150,20);
		add(productionCodeLbl);
		
		productionCodeField = new JTextField();
		productionCodeField.setBounds(190, 120, 150, 20);
		add(productionCodeField);
		
		productionDateLbl = new JLabel("Tanggal Produksi");
		productionDateLbl.setBounds(30,160,150,20);
		add(productionDateLbl);
		
		productionDateChooser = new JDateChooser();
		productionDateChooser.setBounds(190,160,150,20);
		add(productionDateChooser);
		
		groupShiftLbl = new JLabel("Group Shift");
		groupShiftLbl.setBounds(30,200,150,20);
		add(groupShiftLbl);
		
		groupShiftCmb = new ComboBox<>();
		groupShiftCmb.setBounds(190,200,150,20);
		add(groupShiftCmb);
		
		shiftLbl = new JLabel("Shift");
		shiftLbl.setBounds(30,240,150,20);
		add(shiftLbl);
		
		shiftCmb = new ComboBox<>();
		shiftCmb.setBounds(190,240,150,20);
		add(shiftCmb);
		
		lineLbl = new JLabel("Line");
		lineLbl.setBounds(30,280,150,20);
		add(lineLbl);
		
		lineCmb = new ComboBox<>();
		lineCmb.setBounds(190,280,150,20);
		add(lineCmb);
		
		inputMaterialBtn = new JButton("Input Bahan Baku");
		inputMaterialBtn.setBounds(280,360,150,50);
		add(inputMaterialBtn);
		
		inputProductionResultBtn = new JButton("Input Hasil Produksi");
		inputProductionResultBtn.setBounds(450,360,150,50);
		add(inputProductionResultBtn);
		
	}
	
}
