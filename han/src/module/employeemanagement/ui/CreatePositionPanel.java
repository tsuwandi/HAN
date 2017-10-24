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

public class CreatePositionPanel extends JPanel implements Bridging{
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
	
	private JButton saveBtn;
	private JButton backBtn;
	
	private boolean editMode;
	
	private Position position;
	
	public CreatePositionPanel(){
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
		
		lblHeader = new JLabel("INPUT POSITION");
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
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(950,550,150,30);
		add(saveBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(30,550,150,30);
		backBtn.setFocusable(false);
		add(backBtn);

	}
	
	private void setData(){
		positionCodeField.setText(ServiceFactory.getEmployeeManagementBL().lastPositionCode()); 
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
				if(DialogBox.showBackChoice()==JOptionPane.YES_OPTION)MainPanel.changePanel("module.employeemanagement.ui.ListPositionPanel");				
			}
		});
	}
	
	private void save(){
		int error = 0;
		if (positionNameField.getText().equals("")) {
			positionNameErrorLbl.setText("<html><font color='red'>Nama harus diisi!</font></html>");
			error++;
		}else{
			positionNameErrorLbl.setText("");
		}
		if (minSalaryField.getText().equals("")) {
			minSalaryErrorLbl.setText("<html><font color='red'>Gaji Minimum harus harus diisi!</font></html>");
			error++;
		}else{
			minSalaryErrorLbl.setText("");
		}
		if (maxSalaryField.getText().equals("")) {
			maxSalaryErrorLbl.setText("<html><font color='red'>Gaji Maksimum harus harus diisi!</font></html>");
			error++;
		}else{
			if(Long.valueOf(maxSalaryField.getText())<Long.valueOf(minSalaryField.getText())){
				maxSalaryErrorLbl.setText("<html><font color='red'>Gaji Maksimum tidak boleh lebih kecil dari minimum!</font></html>");
				error++;
			}else{
				maxSalaryErrorLbl.setText("");
			}
			
		}
		
		if(error==0){
			if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
				if(!editMode)position = new Position();
				position.setCode(positionCodeField.getText());
				position.setName(positionNameField.getText());
				position.setMaxSalary(new BigDecimal(maxSalaryField.getText()));
				position.setMinSalary(new BigDecimal(minSalaryField.getText()));
				if(!editMode)ServiceFactory.getEmployeeManagementBL().savePosition(position);
				else ServiceFactory.getEmployeeManagementBL().updatePosition(position);
				
				MainPanel.changePanel("module.employeemanagement.ui.ListPositionPanel");
			}
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		position = (Position)objects[0];
		positionCodeField.setText(position.getCode());
		positionNameField.setText(position.getName());
		minSalaryField.setText(String.valueOf(position.getMinSalary().intValue()));
		maxSalaryField.setText(String.valueOf(position.getMaxSalary().intValue()));
		editMode=true;
		lblHeader.setText("EDIT POSITION");
	}
	
	
	
}
