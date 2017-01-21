package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.EmployeeType;

public class CreateEmployeeTypePanel extends JPanel {

	private static final long serialVersionUID = -7395785136651915827L;
	private JTextField employeeTypeNameField;
	private JTextField employeeTypeIdField;

	public CreateEmployeeTypePanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Tipe Karyawan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 350, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN TIPE KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 210, 25);
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
		add(employeeTypeNameField);
		
		employeeTypeIdField = new JTextField();
		employeeTypeIdField.setBounds(140, 80, 200, 30);
		employeeTypeIdField.setEditable(false);
		employeeTypeIdField.setEnabled(false);
		add(employeeTypeIdField);
		
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
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				employeeTypeNameField.requestFocusInWindow();
			}
		});
	}
	

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("EMT");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdEmployeeType()));
		employeeTypeIdField.setText(lastId.toString());
	}

	protected void save() {
		EmployeeType employeeType = new EmployeeType();
		employeeType.setId(employeeTypeIdField.getText());
		employeeType.setName(employeeTypeNameField.getText());
		employeeType.setInputDate(new Date());
		employeeType.setInputBy("");
		employeeType.setEditDate(new Date());
		employeeType.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().saveEmployeeType(employeeType);
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
			MainPanel.changePanel("module.personalia.ui.EmployeeTypeConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		employeeTypeNameField.setText("");
	}
}