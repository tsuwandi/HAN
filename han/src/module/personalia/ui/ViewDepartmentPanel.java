package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Department;
import module.personalia.model.Division;
import module.util.Bridging;
import controller.ServiceFactory;

public class ViewDepartmentPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = 6956229404383584838L;
	private JTextField departementNameField;
	private JTextField departementIdField;
	private ComboBox<Division> divisionCmbBox;
	private JButton editBtn;
	private Department departement;
	private boolean editMode = false;
	private JButton deleteBtn;
	
	public ViewDepartmentPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Departemen > Edit Departemen");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("VIEW DEPARTEMEN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("ID Departement");
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
		
		departementNameField = new JTextField();
		departementNameField.setBounds(140, 120, 200, 30);
		departementNameField.setEditable(false);
		departementNameField.setEnabled(false);
		add(departementNameField);
		
		departementIdField = new JTextField();
		departementIdField.setBounds(140, 80, 200, 30);
		departementIdField.setEditable(false);
		departementIdField.setEnabled(false);
		add(departementIdField);
		
		divisionCmbBox = new ComboBox<Division>();
		divisionCmbBox.setEnabled(false);
		divisionCmbBox.setBounds(140, 160, 200, 30);
		add(divisionCmbBox);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(924, 589, 90, 30);
		add(editBtn);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(isEditMode());
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
		
		getData();
	}
	
	private void getData() {
		divisionCmbBox.setList(ServiceFactory.getPersonaliaBL().getDivisions(""));
		
	}

	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			departement.setDeleteDate(new Date());
			departement.setDeleteBy("");
			ServiceFactory.getPersonaliaBL().deleteDepartment(departement);
			MainPanel.changePanel("module.personalia.ui.DepartementConfigPanel");
		} else {
			
		}
	}

	protected void update() {
		Department departement = new Department();
		departement.setId(departementIdField.getText());
		departement.setName(departementNameField.getText());
		departement.setDivisionId(divisionCmbBox.getDataIndex().getId());
		departement.setEditDate(new Date());
		departement.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().updateDepartment(departement);
			DialogBox.showEdit();
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		departement = (Department) objects[0];
		
		departementIdField.setText(departement.getId());
		departementNameField.setText(departement.getName());
		divisionCmbBox.setSelectedIndex(0);
	}
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		departementIdField.setEditable(true);
		departementIdField.setEnabled(true);
		departementNameField.setEditable(true);
		departementNameField.setEnabled(true);
		divisionCmbBox.setEnabled(true);
		deleteBtn.setEnabled(true);
		
		editBtn.setText("Simpan");
		editBtn.updateUI();
	}
}
