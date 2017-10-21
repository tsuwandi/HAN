package module.employeemanagement.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.TextField;
import main.panel.MainPanel;
import module.employeemanagement.model.PayrollComponent;
import module.util.Bridging;

public class CreatePayrollComponentPanel extends JPanel implements Bridging{
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(CreatePayrollComponentPanel.class.getName());
	
	private JLabel lblHeader;
	private JLabel payrollComponentCodeLbl;
	private JLabel payrollComponentDescriptionLbl;
	private JLabel isDailyLbl;
	private JLabel isSalaryLbl;
	private JLabel isThrLbl;
	private JLabel isBonusLbl;
	private JLabel payrollComponentDescriptionErrorLbl;
	
	private TextField payrollComponentCodeField;
	private TextField payrollComponentDescriptionField;
	private JCheckBox dailyYesCb;
	private JCheckBox dailyNoCb;
	private JCheckBox salaryYesCb;
	private JCheckBox salaryNoCb;
	private JCheckBox thrYesCb;
	private JCheckBox thrNoCb;
	private JCheckBox bonusYesCb;
	private JCheckBox bonusNoCb;
	
	private ButtonGroup dailyBtnGroup;
	private ButtonGroup salaryBtnGroup;
	private ButtonGroup thrBtnGroup;
	private ButtonGroup bonusBtnGroup;
	
	private JButton saveBtn;
	private JButton backBtn;
	
	private PayrollComponent payrollComponent;
	private boolean editMode;
	
	public CreatePayrollComponentPanel(){
		createGUI();
		setData();
		listener();
	}
	
	private void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Payroll Komponen");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("INPUT PAYROLL KOMPONEN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		payrollComponentCodeLbl = new JLabel("Kode");
		payrollComponentCodeLbl.setBounds(30,120,150,20);
		add(payrollComponentCodeLbl);
		
		payrollComponentCodeField = new TextField();
		payrollComponentCodeField.setBounds(180,120,150,20);
		payrollComponentCodeField.setEnabled(false);
		add(payrollComponentCodeField);
		
		payrollComponentDescriptionLbl = new JLabel("Deskripsi");
		payrollComponentDescriptionLbl.setBounds(30,160,150,20);
		add(payrollComponentDescriptionLbl);
		
		payrollComponentDescriptionField = new TextField();
		payrollComponentDescriptionField.setBounds(180,160,150,20);
		add(payrollComponentDescriptionField);
		
		payrollComponentDescriptionErrorLbl = new JLabel();
		payrollComponentDescriptionErrorLbl.setBounds(340,160,150,20);
		add(payrollComponentDescriptionErrorLbl);
		
		isDailyLbl = new JLabel("Deskripsi");
		isDailyLbl.setBounds(30,200,150,20);
		add(isDailyLbl);
		
		dailyYesCb = new JCheckBox("Yes");
		dailyYesCb.setBounds(180,200,50,20);
		add(dailyYesCb);
		
		dailyNoCb = new JCheckBox("No");
		dailyNoCb.setBounds(240,200,50,20);
		dailyNoCb.setSelected(true);
		add(dailyNoCb);
		
		dailyBtnGroup = new ButtonGroup();
		dailyBtnGroup.add(dailyYesCb);
		dailyBtnGroup.add(dailyNoCb);
		
		isSalaryLbl = new JLabel("Gaji");
		isSalaryLbl.setBounds(30,240,150,20);
		add(isSalaryLbl);
		
		salaryYesCb = new JCheckBox("Yes");
		salaryYesCb.setBounds(180,240,50,20);
		add(salaryYesCb);
		
		salaryNoCb = new JCheckBox("No");
		salaryNoCb.setBounds(240,240,50,20);
		salaryNoCb.setSelected(true);
		add(salaryNoCb);
		
		salaryBtnGroup = new ButtonGroup();
		salaryBtnGroup.add(salaryYesCb);
		salaryBtnGroup.add(salaryNoCb);
		
		isThrLbl = new JLabel("THR");
		isThrLbl.setBounds(30,280,150,20);
		add(isThrLbl);
		
		thrYesCb = new JCheckBox("Yes");
		thrYesCb.setBounds(180,280,50,20);
		add(thrYesCb);
		
		thrNoCb = new JCheckBox("No");
		thrNoCb.setBounds(240,280,50,20);
		thrNoCb.setSelected(true);
		add(thrNoCb);
		
		thrBtnGroup = new ButtonGroup();
		thrBtnGroup.add(thrYesCb);
		thrBtnGroup.add(thrNoCb);
		
		isBonusLbl = new JLabel("Bonus");
		isBonusLbl.setBounds(30,320,150,20);
		add(isBonusLbl);
		
		bonusYesCb = new JCheckBox("Yes");
		bonusYesCb.setBounds(180,320,50,20);
		add(bonusYesCb);
		
		bonusNoCb = new JCheckBox("No");
		bonusNoCb.setBounds(240,320,50,20);
		bonusNoCb.setSelected(true);
		add(bonusNoCb);
		
		bonusBtnGroup = new ButtonGroup();
		bonusBtnGroup.add(bonusYesCb);
		bonusBtnGroup.add(bonusNoCb);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(850,550,150,30);
		add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,550,150,30);
		add(backBtn);
	}
	
	private void setData(){
		payrollComponentCodeField.setText(ServiceFactory.getEmployeeManagementBL().lastPayrollComponentCode());
	}
	
	private void listener(){
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.employeemanagement.ui.ListComponentPayrollPanel");				
			}
		});
	}
	
	private void save(){
		int error = 0;
		if(payrollComponentDescriptionField.getText().equals("")){
			payrollComponentDescriptionErrorLbl.setText("<html><font color='red'>deskripsi harus diisi!</font></html>");
			error++;
		}else{
			payrollComponentDescriptionErrorLbl.setText("");
		}
		
		if(error==0){
			if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
				if(!editMode) payrollComponent = new PayrollComponent();
				payrollComponent.setCode(payrollComponentCodeField.getText());
				payrollComponent.setDescription(payrollComponentDescriptionField.getText());
				payrollComponent.setIsDaily(dailyYesCb.isSelected()?1:2);
				payrollComponent.setIsSalary(salaryYesCb.isSelected()?1:2);
				payrollComponent.setIsThr(thrYesCb.isSelected()?1:2);
				payrollComponent.setIsBonus(bonusYesCb.isSelected()?1:2);
				if(!editMode) {
					ServiceFactory.getEmployeeManagementBL().savePayrollComponent(payrollComponent);
					DialogBox.showInsert();
				}
				else{
					ServiceFactory.getEmployeeManagementBL().updatePayrollComponent(payrollComponent);
					DialogBox.showEdit();
				}
				MainPanel.changePanel("module.employeemanagement.ui.ListComponentPayrollPanel");
			}
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		payrollComponent = (PayrollComponent)objects[0];
		payrollComponentCodeField.setText(payrollComponent.getCode());
		payrollComponentDescriptionField.setText(payrollComponent.getDescription());
		if (payrollComponent.getIsDaily()==1)dailyYesCb.setSelected(true);	 
		else dailyNoCb.setSelected(true);
		if (payrollComponent.getIsSalary()==1)salaryYesCb.setSelected(true);	 
		else salaryNoCb.setSelected(true);
		if (payrollComponent.getIsThr()==1)thrYesCb.setSelected(true);	 
		else thrNoCb.setSelected(true);
		if (payrollComponent.getIsBonus()==1)bonusYesCb.setSelected(true);	 
		else bonusNoCb.setSelected(true);
		
		editMode=true;
		lblHeader.setText("EDIT PAYROLL KOMPONEN");
	}
	
}
