package module.employeemanagement.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumericField;
import main.component.TextField;
import main.panel.MainPanel;
import module.employeemanagement.model.Position;
import module.util.Bridging;

public class ViewPositionPanel extends JPanel implements Bridging{
	private static final long serialVersionUID = 1L;
	private JLabel lblHeader;
	private JLabel positionCodeLbl;
	private JLabel positionNameLbl;
	private JLabel minSalaryLbl;
	private JLabel maxSalaryLbl;
	
	private JLabel positionNameErrorLbl;
	private JLabel minSalaryErrorLbl;
	private JLabel maxSalaryErrorLbl;
	
	private TextField positionCodeField;
	private TextField positionNameField;
	private NumericField minSalaryField;
	private NumericField maxSalaryField;
	
	private JButton editBtn;
	private JButton deleteBtn;
	private JButton backBtn;
	
	
	private Position position;
	
	public ViewPositionPanel(){
		createGUI();
		setData();
		listener();
	}
	
	private void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Position");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);
		
		lblHeader = new JLabel("VIEW POSITION");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		positionCodeLbl = new JLabel("Kode Posisi");
		positionCodeLbl.setBounds(30,120,150,20);
		add(positionCodeLbl);
		
		positionCodeField = new TextField();
		positionCodeField.setBounds(180,120,150,20);
		positionCodeField.setEnabled(false);
		add(positionCodeField);
		
		positionNameLbl = new JLabel("Nama Posisi");
		positionNameLbl.setBounds(30,160,150,20);
		add(positionNameLbl);
		
		positionNameField = new TextField();
		positionNameField.setBounds(180,160,150,20);
		add(positionNameField);
		
		positionNameErrorLbl = new JLabel();
		positionNameErrorLbl.setBounds(340,160,150,20);
		add(positionNameErrorLbl);
		
		minSalaryLbl = new JLabel("Min Gaji");
		minSalaryLbl.setBounds(30,200,150,20);
		add(minSalaryLbl);
		
		minSalaryField = new NumericField(12,"###,###,###,###");
		minSalaryField.setBounds(180,200,150,20);
		add(minSalaryField);
		
		minSalaryErrorLbl = new JLabel();
		minSalaryErrorLbl.setBounds(340,200,150,20);
		add(minSalaryErrorLbl);
		
		maxSalaryLbl = new JLabel("Min Gaji");
		maxSalaryLbl.setBounds(30,240,150,20);
		add(maxSalaryLbl);
		
		maxSalaryField = new NumericField(12,"###,###,###,###");
		maxSalaryField.setBounds(180,240,150,20);
		add(maxSalaryField);
		
		maxSalaryErrorLbl = new JLabel();
		maxSalaryErrorLbl.setBounds(340,240,150,20);
		add(maxSalaryErrorLbl);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(950,550,150,30);
		add(editBtn);
		
		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(640,550,150,30);
		add(editBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,550,150,30);
		backBtn.setFocusable(false);
		add(backBtn);

	}
	
	private void setData(){
		positionCodeField.setText(ServiceFactory.getEmployeeManagementBL().lastPositionCode()); 
	}
	
	private void listener(){
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.employeemanagement.ui.CreatePositionPanel",position);
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showDeleteChoice()==JOptionPane.YES_OPTION){
					ServiceFactory.getEmployeeManagementBL().deletePosition(position);
					MainPanel.changePanel("module.employeemanagement.ui.ListPositionPanel");
				}
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.employeemanagement.ui.ListPositionPanel");				
			}
		});
	}
	

	@Override
	public void invokeObjects(Object... objects) {
		position = (Position)objects[0];
		positionCodeField.setText(position.getCode());
		positionNameField.setText(position.getName());
		minSalaryField.setText(position.getMinSalary().toString());
		maxSalaryField.setText(position.getMaxSalary().toString());
	}
	
	
	
}
