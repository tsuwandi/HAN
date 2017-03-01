package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.EmployeeType;
import module.util.Bridging;

public class ViewEmployeeTypePanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 5085049929139496641L;
	private JTextField employeeTypeNameField;
	private JTextField employeeTypeIdField;
	private JButton editBtn;
	private EmployeeType employeeType;
	private boolean editMode = false;
	private JButton deleteBtn;

	public ViewEmployeeTypePanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Tipe Karyawan > Edit Tipe Karyawan");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 330, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("VIEW TIPE KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);

		JLabel label = new JLabel("ID Tipe Karyawan");
		label.setBounds(30, 80, 100, 30);
		add(label);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);

		JLabel label_2 = new JLabel("<html>Tipe Karyawan<font color='red'> * </font></html>");
		label_2.setBounds(30, 120, 100, 30);
		add(label_2);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);

		employeeTypeNameField = new JTextField();
		employeeTypeNameField.setBounds(140, 120, 200, 30);
		employeeTypeNameField.setEditable(false);
		employeeTypeNameField.setEnabled(false);
		add(employeeTypeNameField);

		employeeTypeIdField = new JTextField();
		employeeTypeIdField.setBounds(140, 80, 200, 30);
		employeeTypeIdField.setEditable(false);
		employeeTypeIdField.setEnabled(false);
		add(employeeTypeIdField);

		editBtn = new JButton("Edit");
		editBtn.setBounds(924, 589, 90, 30);
		add(editBtn);
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isEditMode()==false) {
					setEditMode(true);
				}
				else {
					update();
				}
			}
		});

		deleteBtn = new JButton("Hapus");
		deleteBtn.setEnabled(false);
		deleteBtn.setBounds(824, 589, 90, 30);
		add(deleteBtn);

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		JButton printBtn = new JButton("Cetak");
		printBtn.setBounds(724, 589, 90, 30);
		add(printBtn);
	}
	
	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			employeeType.setDeleteDate(new Date());
			employeeType.setDeleteBy(ServiceFactory.getSystemBL().getUsernameActive());
			ServiceFactory.getPersonaliaBL().deleteEmployeeType(employeeType);
			MainPanel.changePanel("module.personalia.ui.EmployeeTypeConfigPanel");
		} else {

		}
	}

	protected void update() {
		EmployeeType employeeType = new EmployeeType();
		employeeType.setId(employeeTypeIdField.getText());
		employeeType.setName(employeeTypeNameField.getText());
		employeeType.setEditDate(new Date());
		employeeType.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());

		try {
			ServiceFactory.getPersonaliaBL().updateEmployeeType(employeeType);
			DialogBox.showEdit();
			MainPanel.changePanel("module.personalia.ui.EmployeeTypeConfigPanel");
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		employeeType = (EmployeeType) objects[0];

		employeeTypeIdField.setText(employeeType.getId());
		employeeTypeNameField.setText(employeeType.getName());
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		employeeTypeIdField.setEditable(true);
		employeeTypeIdField.setEnabled(true);
		employeeTypeNameField.setEditable(true);
		employeeTypeNameField.setEnabled(true);
		deleteBtn.setEnabled(true);

		editBtn.setText("Simpan");
		editBtn.updateUI();
	}
}
