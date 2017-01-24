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
import module.personalia.model.Division;
import module.util.Bridging;

public class ViewDivisionPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField divisionNameField;
	private JTextField divisionIdField;
	private JButton editBtn;
	private Division division;
	private boolean editMode = false;
	private JButton deleteBtn;

	public ViewDivisionPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Divisi > Edit Divisi");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("VIEW DIVISI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("ID Divisi");
		label.setBounds(30, 80, 100, 30);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		JLabel label_2 = new JLabel("<html>Nama Divisi<font color='red'> * </font></html>");
		label_2.setBounds(30, 120, 100, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		divisionNameField = new JTextField();
		divisionNameField.setBounds(140, 120, 200, 30);
		divisionNameField.setEditable(false);
		divisionNameField.setEnabled(false);
		add(divisionNameField);
		
		divisionIdField = new JTextField();
		divisionIdField.setBounds(140, 80, 200, 30);
		divisionIdField.setEditable(false);
		divisionIdField.setEnabled(false);
		add(divisionIdField);
		
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
		
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				
			}
		});
	}

	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			division.setDeleteDate(new Date());
			division.setDeleteBy("");
			ServiceFactory.getPersonaliaBL().deleteDivision(division);
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		} else {
			
		}
	}

	protected void update() {
		Division division = new Division();
		division.setId(divisionIdField.getText());
		division.setName(divisionNameField.getText());
		division.setEditDate(new Date());
		division.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().updateDivision(division);
			DialogBox.showEdit();
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		division = (Division) objects[0];
		
		divisionIdField.setText(division.getId());
		divisionNameField.setText(division.getName());
	}
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		divisionIdField.setEditable(true);
		divisionIdField.setEnabled(true);
		divisionNameField.setEditable(true);
		divisionNameField.setEnabled(true);
		deleteBtn.setEnabled(true);
		
		editBtn.setText("Simpan");
		editBtn.updateUI();
	}
}