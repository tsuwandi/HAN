package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Department;
import module.personalia.model.Division;
import controller.ServiceFactory;

public class CreateDepartmentPanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField departmentNameField;
	private JTextField departementIdField;
	private ComboBox<Division> divisionCmbBox;

	public CreateDepartmentPanel() {
		
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Departemen > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN DEPARTEMEN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 180, 25);
		add(lblHeader);

		JLabel label = new JLabel("ID Departemen");
		label.setBounds(30, 80, 100, 30);
		add(label);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);

		JLabel label_2 = new JLabel("<html>Nama Departement<font color='red'> * </font></html>");
		label_2.setBounds(30, 120, 100, 30);
		add(label_2);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		JLabel label_4 = new JLabel("<html>Divisi<font color='red'> * </font></html>");
		label_4.setBounds(30, 160, 100, 30);
		add(label_4);

		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 160, 10, 30);
		add(label_5);

		departmentNameField = new JTextField();
		departmentNameField.setBounds(140, 120, 200, 30);
		add(departmentNameField);

		departementIdField = new JTextField();
		departementIdField.setBounds(140, 80, 200, 30);
		departementIdField.setEditable(false);
		departementIdField.setEnabled(false);
		add(departementIdField);
		
		divisionCmbBox = new ComboBox<Division>();
		divisionCmbBox.setBounds(140, 160, 200, 30);
		add(divisionCmbBox);

		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		getLastID();
		
		getData();
	}
	
	private void getData() {
		divisionCmbBox.setList(ServiceFactory.getPersonaliaBL().getDivisions(""));
	}

	private void getLastID() {
		departementIdField.setText(ServiceFactory.getPersonaliaBL().getLastIdDivision().toString());
	}

	protected void save() {
		Department departement = new Department();

		try {
			ServiceFactory.getPersonaliaBL().saveDepartment(departement);
			option();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}

	}

	private void option() {
		if (DialogBox.showAfterChoiceInsert()==0) {
			clear();
		} else {
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		departmentNameField.setText("");
	}
}
