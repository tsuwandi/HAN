package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.panel.MainPanel;
import module.personalia.model.Department;
import module.personalia.model.Division;


public class SearchDepartmentPanel extends JPanel {

	private static final long serialVersionUID = 2708997445150245596L;
	private JTextField departmentNameField;
	private JTextField departmentIdField;
	private ComboBox<Division> divisionCmbBox;
	
	public SearchDepartmentPanel() {
		setSize(500, 200);
		setLayout(null);
		
		JLabel lblHeader = new JLabel("SEARCH DEPARTEMEN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 10, 180, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("ID Departemen");
		label.setBounds(10, 40, 110, 30);
		add(label);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 40, 10, 30);
		add(label_1);

		JLabel label_2 = new JLabel("<html>Nama Departement<font color='red'> * </font></html>");
		label_2.setBounds(10, 80, 110, 30);
		add(label_2);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 80, 10, 30);
		add(label_3);
		
		JLabel label_4 = new JLabel("<html>Divisi<font color='red'> * </font></html>");
		label_4.setBounds(10, 120, 110, 30);
		add(label_4);

		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 120, 10, 30);
		add(label_5);

		departmentNameField = new JTextField();
		departmentNameField.setBounds(140, 80, 200, 30);
		add(departmentNameField);

		departmentIdField = new JTextField();
		departmentIdField.setBounds(140, 40, 200, 30);
		departmentIdField.setEditable(false);
		departmentIdField.setEnabled(false);
		add(departmentIdField);
		
		divisionCmbBox = new ComboBox<Division>();
		divisionCmbBox.setBounds(140, 120, 200, 30);
		add(divisionCmbBox);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(250, 160, 90, 30);
		add(resetBtn);
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetMode();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(150, 160, 90, 30);
		add(searchBtn);
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		getData();
	}

	private void getData() {
		divisionCmbBox.setList(ServiceFactory.getPersonaliaBL().getDivisions(""));
	}

	protected void search() {
		StringBuilder sb = new StringBuilder();
		
		if(!"".equals(departmentIdField.getText())) {
			sb.append(" and id like '%");
			sb.append(departmentIdField.getText());
			sb.append("%'");
		}
		if(!"".equals(departmentNameField.getText())) {
			sb.append(" and name like '%");
			sb.append(departmentNameField.getText());
			sb.append("%'");
		}
		
		sb.append(" and division_di = ");
		sb.append(divisionCmbBox.getDataIndex().getId());
		
		List<Department> departments = ServiceFactory.getPersonaliaBL().getDepartments(sb.toString());
		
		MainPanel.changePanel("module.personalia.ui.DepartmentConfigPanel",departments);
	}

	protected void resetMode() {
		departmentIdField.setText("");
		departmentNameField.setText("");
		divisionCmbBox.setSelectedIndex(0);
	}
}