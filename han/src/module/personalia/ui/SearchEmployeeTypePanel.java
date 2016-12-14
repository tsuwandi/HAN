package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.personalia.model.EmployeeType;

import javax.swing.JButton;

public class SearchEmployeeTypePanel extends JPanel {

	private static final long serialVersionUID = -9069167247125640810L;
	private JTextField employeeTypeNameField;
	private JTextField employeeTypeIdField;

	public SearchEmployeeTypePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel lblHeader = new JLabel("SEARCH TIPE KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 10, 210, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("ID Tipe Karyawan");
		label.setBounds(10, 45, 100, 30);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 45, 10, 30);
		add(label_1);
		
		JLabel label_2 = new JLabel("<html>Tipe Karyawan<font color='red'> * </font></html>");
		label_2.setBounds(10, 85, 100, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(110, 85, 10, 30);
		add(label_3);
		
		employeeTypeNameField = new JTextField();
		employeeTypeNameField.setBounds(120, 85, 200, 30);
		add(employeeTypeNameField);
		
		employeeTypeIdField = new JTextField();
		employeeTypeIdField.setBounds(120, 45, 200, 30);
		employeeTypeIdField.setEditable(false);
		employeeTypeIdField.setEnabled(false);
		add(employeeTypeIdField);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(231, 125, 90, 30);
		add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(131, 125, 90, 30);
		add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
	}

	protected void search() {
		StringBuilder stringBuilder = new StringBuilder();
		if(!"".equals(employeeTypeIdField.getText())) {
			stringBuilder.append(" and id like '%");
			stringBuilder.append(employeeTypeIdField.getText());
			stringBuilder.append("%' ");
		}
		if(!"".equals(employeeTypeNameField.getText())) {
			stringBuilder.append(" and name like '%");
			stringBuilder.append(employeeTypeNameField.getText());
			stringBuilder.append("%' ");
		}
		
		List<EmployeeType> employeeTypes = ServiceFactory.getPersonaliaBL().getEmployeeTypes(stringBuilder.toString());
		MainPanel.changePanel("", employeeTypes);
	}

	protected void reset() {
		employeeTypeIdField.setText("");
		employeeTypeNameField.setText("");
	}
}
