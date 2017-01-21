package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.PayrollComponent;

public class CreatePayrollComponentPanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField payrollComponentDescriptionField;
	private JTextField payrollComponentCodeField;
	private JTextField referenceDocumentField;
	private JRadioButton payrollStatusYesRdbtn;
	private JRadioButton payrollStatusNoRdbtn;
	private JRadioButton thrStatusYesRdbtn;
	private JRadioButton thrStatusNoRdbtn;
	private JRadioButton bonusStatusYesRdbtn;
	private JRadioButton bonusStatusNoRdbtn;

	public CreatePayrollComponentPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Payroll > Komponen Payroll > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 330, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN KOMPONEN PAYROLL");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 250, 25);
		add(lblHeader);
		// code
		JLabel lblKodeKomponenPayroll = new JLabel("<html>Kode Komponen Payroll</html>");
		lblKodeKomponenPayroll.setBounds(30, 80, 100, 30);
		add(lblKodeKomponenPayroll);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		payrollComponentCodeField = new JTextField();
		payrollComponentCodeField.setBounds(140, 80, 200, 30);
		payrollComponentCodeField.setEditable(false);
		payrollComponentCodeField.setEnabled(false);
		add(payrollComponentCodeField);
		// deskripsi
		JLabel lbldeskripsiKomponenPayroll = new JLabel("<html>Deskripsi Komponen Payroll</html>");
		lbldeskripsiKomponenPayroll.setBounds(30, 120, 100, 30);
		add(lbldeskripsiKomponenPayroll);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		payrollComponentDescriptionField = new JTextField();
		payrollComponentDescriptionField.setBounds(140, 120, 200, 30);
		add(payrollComponentDescriptionField);
		// status gaji
		JLabel lblstatusGaji = new JLabel("<html>Status Gaji</html>");
		lblstatusGaji.setBounds(30, 160, 100, 30);
		add(lblstatusGaji);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		payrollStatusYesRdbtn = new JRadioButton("Ya");
		payrollStatusYesRdbtn.setBounds(140, 160, 100, 30);
		add(payrollStatusYesRdbtn);
		
		payrollStatusNoRdbtn = new JRadioButton("Tidak");
		payrollStatusNoRdbtn.setBounds(240, 160, 100, 30);
		add(payrollStatusNoRdbtn);
		
		ButtonGroup payrollStatusButtonGroup = new ButtonGroup();
		payrollStatusButtonGroup.add(payrollStatusYesRdbtn);
		payrollStatusButtonGroup.add(payrollStatusNoRdbtn);
		payrollStatusYesRdbtn.setSelected(true);
		// status THR
		JLabel lblstatusThr = new JLabel("<html>Status THR</html>");
		lblstatusThr.setBounds(30, 200, 100, 30);
		add(lblstatusThr);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 200, 10, 30);
		add(label_5);
		
		thrStatusYesRdbtn = new JRadioButton("Ya");
		thrStatusYesRdbtn.setBounds(140, 200, 100, 30);
		add(thrStatusYesRdbtn);
		
		thrStatusNoRdbtn = new JRadioButton("Tidak");
		thrStatusNoRdbtn.setBounds(240, 200, 100, 30);
		add(thrStatusNoRdbtn);
		
		ButtonGroup thrStatusButtonGroup = new ButtonGroup();
		thrStatusButtonGroup.add(thrStatusYesRdbtn);
		thrStatusButtonGroup.add(thrStatusNoRdbtn);
		thrStatusYesRdbtn.setSelected(true);
		// status bonus
		JLabel lblstatusBonus = new JLabel("<html><center>Status Bonus</center></html>");
		lblstatusBonus.setBounds(30, 240, 100, 30);
		add(lblstatusBonus);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 240, 10, 30);
		add(label_7);
		
		bonusStatusYesRdbtn = new JRadioButton("Ya");
		bonusStatusYesRdbtn.setBounds(140, 240, 100, 30);
		add(bonusStatusYesRdbtn);
		
		bonusStatusNoRdbtn = new JRadioButton("Tidak");
		bonusStatusNoRdbtn.setBounds(240, 240, 100, 30);
		add(bonusStatusNoRdbtn);
		
		ButtonGroup bonusStatusGroupButton = new ButtonGroup();
		bonusStatusGroupButton.add(bonusStatusYesRdbtn);
		bonusStatusGroupButton.add(bonusStatusNoRdbtn);
		bonusStatusYesRdbtn.setSelected(true);
		// dokumen referensi
		JLabel lbldokumenReferensi = new JLabel("<html>Dokumen Referensi</html>");
		lbldokumenReferensi.setBounds(30, 280, 100, 30);
		add(lbldokumenReferensi);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 280, 10, 30);
		add(label_9);
		
		referenceDocumentField = new JTextField();
		referenceDocumentField.setBounds(140, 280, 200, 30);
		add(referenceDocumentField);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton attachBtn = new JButton("Attach");
		attachBtn.setBounds(350, 280, 70, 30);
		add(attachBtn);
		
		attachBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				attach();
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
				payrollComponentDescriptionField.requestFocusInWindow();
			}
		});
	}

	protected void attach() {
		
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.PayrollComponentConfigPanel");
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("PAYCOMP");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdPayrollComponent()));
		payrollComponentCodeField.setText(lastId.toString());
	}

	protected void save() {
		PayrollComponent payrollComponent = new PayrollComponent();
		payrollComponent.setCode(payrollComponentCodeField.getText());
		payrollComponent.setDescription(payrollComponentDescriptionField.getText());
		if(payrollStatusYesRdbtn.isSelected()) payrollComponent.setIsSalary(1);
		else payrollComponent.setIsSalary(0);
		if(thrStatusYesRdbtn.isSelected()) payrollComponent.setIsThr(1);
		else payrollComponent.setIsThr(0);
		if(bonusStatusYesRdbtn.isSelected()) payrollComponent.setIsBonus(1);
		else payrollComponent.setIsBonus(0);
		payrollComponent.setReferenceDocument(referenceDocumentField.getText());
		payrollComponent.setInputDate(new Date());
		payrollComponent.setInputBy("");
		payrollComponent.setEditDate(new Date());
		payrollComponent.setEditBy("");
		try {
			ServiceFactory.getPersonaliaBL().savePayrollComponent(payrollComponent);
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
			MainPanel.changePanel("module.personalia.ui.PayrollComponentConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		payrollComponentDescriptionField.setText("");
	}
}