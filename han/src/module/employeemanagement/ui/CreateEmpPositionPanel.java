package module.employeemanagement.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import main.component.ComboBox;
import main.component.TextField;
import module.employeemanagement.model.EmployeeStructure;
import module.employeemanagement.model.PayrollComponent;
import module.personalia.model.Tax;

public class CreateEmpPositionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblHeader;
	private JLabel empCodeLbl;
	private JLabel empNameLbl;
	private JLabel empStructureCodeLbl;
	private JLabel positionLbl;
	private JLabel divisionLbl;
	private JLabel startDateLbl;
	private JLabel endDateLbl;
	private JLabel separator1Lbl;
	private JLabel payrollComponentLbl;
	private JLabel payrollNominalLbl;
	private JLabel brutoSalaryLbl;
	private JLabel separator2Lbl;
	private JLabel taxLbl;
	private JLabel nominalLbl;
	private JLabel salaryCutLbl;
	private JLabel nettSalaryLbl;
	
	private JLabel empCodeErrorLbl;
	private JLabel empStructureCodeErrorLbl;
	private JLabel endDateErrorlbl;
	
	private TextField empCodeField;
	private TextField empNameField;
	private TextField positionField;
	private TextField divisionField;
	
	private JDateChooser startChooser;
	private JDateChooser endChooser;
	
	private ComboBox<EmployeeStructure> empStructureCmb;
	private ComboBox<PayrollComponent> payrollCmb;
	private ComboBox<Tax> taxCmb;
	
	private JButton addPayrollBtn;
	private JButton addTaxBtn;
	
	private JButton saveBtn;
	private JButton backBtn;
	
	private JTable payrollTable;
	private JTable taxTable;
	

	public CreateEmpPositionPanel(){
		
	}
	
	private void createGUI(){
		
	}
}
