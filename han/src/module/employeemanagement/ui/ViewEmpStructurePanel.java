package module.employeemanagement.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.TextField;
import main.panel.MainPanel;
import module.employeemanagement.model.EmployeeStructure;
import module.employeemanagement.model.OrgValue;
import module.employeemanagement.model.PayrollComponent;
import module.employeemanagement.model.Position;
import module.util.Bridging;

public class ViewEmpStructurePanel extends JPanel implements Bridging{
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ViewEmpStructurePanel.class.getName());
	
	private JLabel lblHeader;
	private JLabel empStructureCodeLbl;
	private JLabel positionLbl;
	private JLabel divisionLbl;
	private JLabel positionErrorLbl;
	private JLabel divisionErrorLbl;
	
	private TextField empStructureCodeField;
	private ComboBox<Position> posCmb;
	private ComboBox<OrgValue> divCmb;
	
	private JButton editBtn;
	private JButton backBtn;
	private JButton deleteBtn;
	
	private EmployeeStructure empStructure;
	
	public ViewEmpStructurePanel(){
		createGUI();
		setData();
		listener();
	}
	
	private void createGUI(){
		setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("ERP > Emp Structure");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		lblHeader = new JLabel("INPUT EMP STRUCTURE");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);
		
		empStructureCodeLbl = new JLabel("Kode");
		empStructureCodeLbl.setBounds(30,120,150,20);
		add(empStructureCodeLbl);
		
		empStructureCodeField = new TextField();
		empStructureCodeField.setBounds(180,120,150,20);
		empStructureCodeField.setEnabled(false);
		add(empStructureCodeField);
		
		positionLbl = new JLabel("Deskripsi");
		positionLbl.setBounds(30,160,150,20);
		add(positionLbl);
		
		posCmb = new ComboBox<>();
		posCmb.setBounds(180,160,150,20);
		add(posCmb);
		
		positionErrorLbl = new JLabel();
		positionErrorLbl.setBounds(340,160,150,20);
		add(positionErrorLbl);
		
		divisionLbl = new JLabel("Deskripsi");
		divisionLbl.setBounds(30,200,150,20);
		add(divisionLbl);
		
		divCmb = new ComboBox<>();
		divCmb.setBounds(180,200,200,20);
		add(divCmb);
		
		divisionErrorLbl= new JLabel();
		divisionErrorLbl.setBounds(390,200,150,20);
		add(divisionErrorLbl);
		
		editBtn = new JButton("Ubah");
		editBtn.setBounds(850,550,150,30);
		add(editBtn);
		
		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(690,550,150,30);
		add(deleteBtn);
		
		backBtn = new JButton("Kembali");
		backBtn.setBounds(50,550,150,30);
		add(backBtn);
	}
	
	private void setData(){
		empStructureCodeField.setText(ServiceFactory.getEmployeeManagementBL().lastEmpStructureCode());
		List<Position> positions = new ArrayList<>();
		positions = ServiceFactory.getEmployeeManagementBL().getPosition();
		positions.add(0,new Position("--Pilih--"));
		posCmb.setList(positions);
		
		List<OrgValue> divisions = new ArrayList<>();
		divisions = ServiceFactory.getEmployeeManagementBL().getDivisons();
		divisions.add(0,new OrgValue("-Pilih--",""));
		divCmb.setList(divisions);
	}
	
	private void listener(){
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.CreateEmpStructurePanel",empStructure);
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DialogBox.showDeleteChoice()==JOptionPane.YES_OPTION){
					ServiceFactory.getEmployeeManagementBL().deleteEmpStructure(empStructure);
					DialogBox.showDelete();
					MainPanel.changePanel("module.employeemanagement.ui.ListEmpStructurePanel");				
				}
			}
		});
		
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.employeemanagement.ui.ListEmpStructurePanel");				
			}
		});
	}
	
	
	@Override
	public void invokeObjects(Object... objects) {
		empStructure = (EmployeeStructure)objects[0];
		empStructureCodeField.setText(empStructure.getCode());
		posCmb.setSelectedItem(empStructure.getPosition());
		divCmb.setSelectedItem(empStructure.getOrgValue());
	
		posCmb.setEnabled(false);
		divCmb.setEnabled(false);
	}
	
}
