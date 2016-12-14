package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.panel.MainPanel;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.personalia.model.MSPosition;

import javax.swing.JButton;

public class SearchMSPositionPanel extends JPanel {

	private static final long serialVersionUID = 5171959280309224375L;
	private JTextField msPositionNameField;
	private JTextField msPositionIdField;
	private ComboBox<Department> departemenCmbBox;
	private ComboBox<Division> divisionCmbBox;

	public SearchMSPositionPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel lblHeader = new JLabel("SEARCH JABATAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 11, 180, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("ID Jabatan");
		label.setBounds(10, 47, 100, 30);
		add(label);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 47, 10, 30);
		add(label_1);

		JLabel label_2 = new JLabel("Nama Jabatan");
		label_2.setBounds(10, 87, 100, 30);
		add(label_2);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(110, 87, 10, 30);
		add(label_3);
		
		JLabel label_4 = new JLabel("Departemen");
		label_4.setBounds(10, 127, 100, 30);
		add(label_4);

		JLabel label_5 = new JLabel(":");
		label_5.setBounds(110, 127, 10, 30);
		add(label_5);
		
		JLabel label_6 = new JLabel("Divisi");
		label_6.setBounds(10, 167, 100, 30);
		add(label_6);

		JLabel label_7 = new JLabel(":");
		label_7.setBounds(110, 167, 10, 30);
		add(label_7);

		msPositionIdField = new JTextField();
		msPositionIdField.setBounds(120, 47, 200, 30);
		msPositionIdField.setEditable(false);
		msPositionIdField.setEnabled(false);
		add(msPositionIdField);
		
		msPositionNameField = new JTextField();
		msPositionNameField.setBounds(120, 87, 200, 30);
		add(msPositionNameField);

		departemenCmbBox = new ComboBox<Department>();
		departemenCmbBox.setBounds(120, 127, 200, 30);
		add(departemenCmbBox);
		
		divisionCmbBox = new ComboBox<Division>();
		divisionCmbBox.setBounds(120, 167, 200, 30);
		add(divisionCmbBox);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(231, 208, 90, 30);
		add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(130, 208, 90, 30);
		add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		getData();
	}

	protected void search() {
		StringBuilder stringBuilder = new StringBuilder();
		
		if(!"".equals(msPositionIdField.getText())) {
			stringBuilder.append(" and id like '%");
			stringBuilder.append(msPositionIdField.getText());
			stringBuilder.append("%' ");
		}
		if(!"".equals(msPositionNameField.getText())) {
			stringBuilder.append(" and name like '%");
			stringBuilder.append(msPositionNameField.getText());
			stringBuilder.append("%' ");
		}
		
		stringBuilder.append(" and division_id = '%");
		stringBuilder.append(divisionCmbBox.getDataIndex().getId());
		stringBuilder.append("%' ");
		
		stringBuilder.append(" and department_id = '%");
		stringBuilder.append(departemenCmbBox.getDataIndex().getId());
		stringBuilder.append("%' ");
		
		List<MSPosition> msPositions = ServiceFactory.getPersonaliaBL().getMSPositions(stringBuilder.toString());
		MainPanel.changePanel("module.personalia.ui.MSPositionConfigPanel", msPositions);
	}

	protected void reset() {
		msPositionIdField.setText("");
		msPositionNameField.setText("");
	}

	private void getData() {
		divisionCmbBox.setList(ServiceFactory.getPersonaliaBL().getDivisions(""));
		departemenCmbBox.setList(ServiceFactory.getPersonaliaBL().getDepartments(""));
	}
	
	
}
