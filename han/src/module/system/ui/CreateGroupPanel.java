package module.system.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import module.system.model.Group;
import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;

public class CreateGroupPanel extends JPanel{

	private static final long serialVersionUID = 2513611527485742789L;
	private JTextField groupNameField;
	private JTextArea groupDescField;

	public CreateGroupPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Sistem > Group");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 214, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("BUAT GROUP BARU");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		
		JLabel label = new JLabel("<html>Nama Group<font color='red'> * </font></html>");
		label.setBounds(30, 80, 100, 30);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		JLabel label_2 = new JLabel("Deskripsi Group");
		label_2.setBounds(30, 120, 100, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		groupDescField = new JTextArea();
		groupDescField.setBounds(140, 120, 200, 90);
		add(groupDescField);
		
		groupNameField = new JTextField();
		groupNameField.setColumns(10);
		groupNameField.setBounds(140, 80, 200, 30);
		add(groupNameField);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton button = new JButton("Kembali");
		button.setBounds(10, 593, 90, 30);
		add(button);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	}

	protected void save() {
		
		Group group = new Group();
		
		if (!groupNameField.getText().equals(null)) {
			group.setGroupName(groupNameField.getText());
		} else {
			DialogBox.showError("Nama Group tidak boleh kosong");
		}
		group.setGroupDesc(groupDescField.getText());
		group.setInputDate(new Date());
		group.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		group.setEditDate(new Date());
		group.setEditedBy(ServiceFactory.getSystemBL().getUsernameActive());
		
		try {
			ServiceFactory.getSystemBL().saveGroup(group);
			option();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Group baru tidak berhasil disimpan");
		}
	}

	private void clearField() {
		groupNameField.setText("");
		groupDescField.setText("");
	}
	
	private void option() {
		if (DialogBox.showAfterChoiceInsert()==0) {
			clearField();
		} else {
			MainPanel.changePanel("module.personalia.ui.GroupConfigPanel");
		}
	}
}