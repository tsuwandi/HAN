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
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.personalia.model.MSPosition;
import module.util.Bridging;

public class ViewMSPositionPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 3129731092103243876L;
	private JTextField msPositionNameField;
	private JTextField msPositionIdField;
	private ComboBox<Department> departemenCmbBox;
	private ComboBox<Division> divisionCmbBox;
	private JTextField salaryMinField;
	private JTextField salaryMaxField;
	private MSPosition msPosition;
	private JButton editBtn;
	private boolean editMode = false;

	public ViewMSPositionPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Jabatan > Edit Jabatan");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("VIEW JABATAN");
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
		msPositionIdField.setEnabled(false);
		msPositionIdField.setEditable(false);
		msPositionIdField.setBounds(140, 80, 200, 30);
		add(msPositionIdField);
		
		msPositionNameField = new JTextField();
		msPositionNameField.setEnabled(false);
		msPositionNameField.setEditable(false);
		msPositionNameField.setBounds(140, 120, 200, 30);
		add(msPositionNameField);

		departemenCmbBox = new ComboBox<Department>();
		departemenCmbBox.setEnabled(false);
		departemenCmbBox.setBounds(140, 160, 200, 30);
		add(departemenCmbBox);
		
		divisionCmbBox = new ComboBox<Division>();
		divisionCmbBox.setEnabled(false);
		divisionCmbBox.setBounds(140, 200, 200, 30);
		add(divisionCmbBox);
		
		salaryMinField = new JTextField();
		salaryMinField.setEnabled(false);
		salaryMinField.setEditable(false);
		salaryMinField.setBounds(140, 240, 200, 30);
		add(salaryMinField);
		
		salaryMaxField = new JTextField();
		salaryMaxField.setEnabled(false);
		salaryMaxField.setEditable(false);
		salaryMaxField.setBounds(140, 280, 200, 30);
		add(salaryMaxField);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(924, 589, 90, 30);
		add(editBtn);
		
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (isEditMode()==false) {
					setEditMode(true);
				}
				else {
					update();
				}
			}
		});
		
		JButton deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(824, 589, 90, 30);
		add(deleteBtn);
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				delete();
			}
		});
		
		JButton printBtn = new JButton("Cetak");
		printBtn.setBounds(724, 589, 90, 30);
		add(printBtn);
		
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				
			}
		});
		
		getData();
	}
	
	protected void update() {
		MSPosition msPosition = new MSPosition();
		msPosition.setId(msPositionIdField.getText());
		msPosition.setName(msPositionNameField.getText());
		msPosition.setDepartment(departemenCmbBox.getDataIndex());
		msPosition.setDivision(divisionCmbBox.getDataIndex());
		msPosition.setDepartementId(departemenCmbBox.getDataIndex().getId());
		msPosition.setDivisionId(divisionCmbBox.getDataIndex().getId());
		msPosition.setDivisionName(divisionCmbBox.getDataIndex().getName());
		msPosition.setDepartementName(departemenCmbBox.getDataIndex().getName());
		msPosition.setSalaryMax(Integer.parseInt(salaryMaxField.getText()));
		msPosition.setSalaryMin(Integer.parseInt(salaryMinField.getText()));
		msPosition.setEditDate(new Date());
		msPosition.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().updateMSPosition(msPosition);
			DialogBox.showEdit();
			MainPanel.changePanel("module.personalia.ui.MSPositionConfigPanel");
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			msPosition.setDeleteDate(new Date());
			msPosition.setDeleteBy("");
			ServiceFactory.getPersonaliaBL().deleteMSPosition(msPosition);
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		} else {
			
		}
	}

	private void getData() {
		departemenCmbBox.setList(ServiceFactory.getPersonaliaBL().getDepartments(""));
		divisionCmbBox.setList(ServiceFactory.getPersonaliaBL().getDivisions(""));
	}
	
	@Override
	public void invokeObjects(Object... objects) {
		msPosition = (MSPosition) objects[0];
		
		msPositionIdField.setText(msPosition.getId());
		msPositionNameField.setText(msPosition.getName());
		departemenCmbBox.setSelectedItem(msPosition.getDepartment());
		divisionCmbBox.setSelectedItem(msPosition.getDivision());
		salaryMinField.setText(msPosition.getSalaryMin().toString());
		salaryMaxField.setText(msPosition.getSalaryMax().toString());
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		msPositionIdField.setEnabled(true);
		msPositionIdField.setEditable(true);
		msPositionNameField.setEnabled(true);
		msPositionNameField.setEditable(true);
		departemenCmbBox.setEnabled(true);
		departemenCmbBox.setEditable(true);
		divisionCmbBox.setEnabled(true);
		divisionCmbBox.setEditable(true);
		salaryMinField.setEnabled(true);
		salaryMinField.setEditable(true);
		salaryMaxField.setEnabled(true);
		salaryMaxField.setEditable(true);
		
		editBtn.setText("Simpan");
		editBtn.repaint();
	}
}