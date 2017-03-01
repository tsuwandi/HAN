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
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumericField;
import main.panel.MainPanel;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.personalia.model.MSPosition;
import module.util.DateUtil;

public class CreateMSPositionPanel extends JPanel {

	private static final long serialVersionUID = -7130979348188499012L;
	private JTextField msPositionNameField;
	private JTextField msPositionIdField;
	private ComboBox<Department> departemenCmbBox;
	private ComboBox<Division> divisionCmbBox;
	private NumericField salaryMinField;
	private NumericField salaryMaxField;

	public CreateMSPositionPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Jabatan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("PENDAFTARAN JABATAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 180, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("ID Jabatan");
		label.setBounds(30, 80, 100, 30);
		add(label);

		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);

		JLabel label_2 = new JLabel("<html>Nama Jabatan<font color='red'> * </font></html>");
		label_2.setBounds(30, 120, 100, 30);
		add(label_2);

		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		JLabel label_4 = new JLabel("<html>Departemen<font color='red'> * </font></html>");
		label_4.setBounds(30, 160, 100, 30);
		add(label_4);

		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 160, 10, 30);
		add(label_5);
		
		JLabel label_6 = new JLabel("<html>Divisi<font color='red'> * </font></html>");
		label_6.setBounds(30, 200, 100, 30);
		add(label_6);

		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 200, 10, 30);
		add(label_7);
		
		JLabel label_8 = new JLabel("<html>Minimal Gaji<font color='red'> * </font></html>");
		label_8.setBounds(30, 240, 100, 30);
		add(label_8);

		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 240, 10, 30);
		add(label_9);
		
		JLabel label_10 = new JLabel("<html>Maksimal Gaji<font color='red'> * </font></html>");
		label_10.setBounds(30, 280, 100, 30);
		add(label_10);

		JLabel label_11 = new JLabel(":");
		label_11.setBounds(130, 280, 10, 30);
		add(label_11);

		msPositionIdField = new JTextField();
		msPositionIdField.setBounds(140, 80, 200, 30);
		msPositionIdField.setEditable(false);
		msPositionIdField.setEnabled(false);
		add(msPositionIdField);
		
		msPositionNameField = new JTextField();
		msPositionNameField.setBounds(140, 120, 200, 30);
		add(msPositionNameField);

		departemenCmbBox = new ComboBox<Department>();
		departemenCmbBox.setBounds(140, 160, 200, 30);
		add(departemenCmbBox);
		
		divisionCmbBox = new ComboBox<Division>();
		divisionCmbBox.setBounds(140, 200, 200, 30);
		add(divisionCmbBox);
		
		salaryMinField = new NumericField(12, "###,###,###,###");
		salaryMinField.setBounds(140, 240, 200, 30);
		add(salaryMinField);
		
		salaryMaxField = new NumericField(12, "###,###,###,###");
		salaryMaxField.setBounds(140, 280, 200, 30);
		add(salaryMaxField);

		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});

		getLastID();
		
		getData();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				msPositionNameField.requestFocusInWindow();
			}
		});
	}
	
	protected void back() {
		MainPanel.changePanel("module.personalia.ui.MSPositionConfigPanel");
	}

	private void getData() {
		departemenCmbBox.setList(ServiceFactory.getPersonaliaBL().getDepartments(""));
		divisionCmbBox.setList(ServiceFactory.getPersonaliaBL().getDivisions(""));
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("ROLE");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdMSPosition()));
		msPositionIdField.setText(lastId.toString());
	}

	protected void save() {
		MSPosition msPosition = new MSPosition();
		msPosition.setId(msPositionIdField.getText());
		msPosition.setName(msPositionNameField.getText());
		msPosition.setDepartementId(departemenCmbBox.getDataIndex().getId());
		msPosition.setDivisionId(divisionCmbBox.getDataIndex().getId());
		msPosition.setSalaryMin(salaryMinField.getValue());
		msPosition.setSalaryMax(salaryMaxField.getValue());
		msPosition.setInputDate(DateUtil.toDate(new Date()));
		msPosition.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		msPosition.setEditDate(DateUtil.toDate(new Date()));
		msPosition.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());
		
		try {
			ServiceFactory.getPersonaliaBL().saveMSPosition(msPosition);
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
			MainPanel.changePanel("module.personalia.ui.MSPositionConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		msPositionNameField.setText("");
		msPositionNameField.setText("");
		departemenCmbBox.setSelectedIndex(0);
		divisionCmbBox.setSelectedIndex(0);
		salaryMinField.setText("0");
		salaryMaxField.setText("0");
	}
}