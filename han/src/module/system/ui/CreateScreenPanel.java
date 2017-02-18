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

import com.sun.glass.ui.Menu;

import module.system.model.Group;
import module.system.model.Screen;
import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;

public class CreateScreenPanel extends JPanel{

	private static final long serialVersionUID = 2513611527485742789L;
	private ComboBox<Menu> menuCmbox;
	private JTextField screenNameField;
	private JTextField screenTitleField;
	private JTextArea moduleNameField;

	public CreateScreenPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Konfigurasi > Sistem > Screen");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 214, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("BUAT SCREEN BARU");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		
		JLabel lblnamaMenu = new JLabel("<html>Nama Menu<font color='red'> * </font></html>");
		lblnamaMenu.setBounds(30, 80, 100, 30);
		add(lblnamaMenu);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		JLabel lblNamaScreen = new JLabel("<html>Nama Layar<font color='red'> * </font></html>");
		lblNamaScreen.setBounds(30, 120, 100, 30);
		add(lblNamaScreen);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		menuCmbox = new ComboBox<>();
		menuCmbox.setBounds(140, 80, 200, 30);
		add(menuCmbox);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JLabel lblJudulLayar = new JLabel("Judul Layar");
		lblJudulLayar.setBounds(30, 160, 100, 30);
		add(lblJudulLayar);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 161, 10, 30);
		add(label_2);
		
		JLabel lblNamaModule = new JLabel("<html>Nama Module<font color='red'> * </font></html>");
		lblNamaModule.setBounds(30, 200, 100, 30);
		add(lblNamaModule);
		
		JLabel label = new JLabel(":");
		label.setBounds(130, 201, 10, 30);
		add(label);
		
		screenNameField = new JTextField();
		screenNameField.setBounds(140, 120, 200, 30);
		add(screenNameField);
		screenNameField.setColumns(10);
		
		screenTitleField = new JTextField();
		screenTitleField.setColumns(10);
		screenTitleField.setBounds(140, 161, 200, 30);
		add(screenTitleField);
		
		moduleNameField = new JTextArea();
		moduleNameField.setBounds(140, 203, 200, 60);
		add(moduleNameField);
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		getData();
	}

	private void getData() {
		
	}

	protected void save() {
		
		Screen screen = new Screen();
		
		screen.setMenuName(menuCmbox.getSelectedItem().toString());
		screen.setScreenName(screenNameField.getText());
		screen.setScreenTitle(screenTitleField.getText());
		screen.setModuleName(moduleNameField.getText());
		screen.setInputDate(new Date());
		screen.setInputBy("");
		screen.setEditDate(new Date());
		screen.setEditedBy("");
		
		try {
			ServiceFactory.getSystemBL().saveScreen(screen);;
			DialogBox.showInsert();
			clearField();
			MainPanel.changePanel("module.system.ui.GroupConfigPanel");
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Group baru tidak berhasil disimpan");
		}
	}

	private void clearField() {
		
	}
}
