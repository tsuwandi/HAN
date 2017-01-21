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
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.MSPosition;
import module.personalia.model.PayrollComponent;
import module.personalia.model.PayrollMapping;

public class CreatePayrollMappingPanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<MSPosition> msPositionCmbox;
	private ComboBox<PayrollComponent> payrollComponentCmbox;
	private JTextField payrollComponentCodeField;
	private JTextField referenceDocumentField;
	private JRadioButton skipStatusYesRdbtn;
	private JRadioButton skipStatusNoRdbtn;
	private JRadioButton permitStatusYesRdbtn;
	private JRadioButton permitStatusNoRdbtn;

	public CreatePayrollMappingPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Payroll > Pemetaan Gaji > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 330, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN PEMETAAN GAJI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 250, 25);
		add(lblHeader);
		// code
		JLabel lblKodeKomponenPayroll = new JLabel("<html>Kode Pemetaan Gaji</html>");
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
		// jabatan
		JLabel lbldeskripsiKomponenPayroll = new JLabel("<html>Jabatan</html>");
		lbldeskripsiKomponenPayroll.setBounds(30, 120, 100, 30);
		add(lbldeskripsiKomponenPayroll);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		msPositionCmbox = new ComboBox<>();
		msPositionCmbox.setBounds(140, 120, 200, 30);
		add(msPositionCmbox);
		// komponen payroll
		JLabel lblstatusGaji = new JLabel("<html>Komponen Payroll</html>");
		lblstatusGaji.setBounds(30, 160, 100, 30);
		add(lblstatusGaji);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		payrollComponentCmbox = new ComboBox<>();
		payrollComponentCmbox.setBounds(140, 160, 200, 30);
		add(payrollComponentCmbox);
		// status Mangkir
		JLabel lblstatusThr = new JLabel("<html>Status Mangkir</html>");
		lblstatusThr.setBounds(30, 200, 100, 30);
		add(lblstatusThr);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 200, 10, 30);
		add(label_5);
		
		skipStatusYesRdbtn = new JRadioButton("Ya");
		skipStatusYesRdbtn.setBounds(140, 200, 100, 30);
		add(skipStatusYesRdbtn);
		
		skipStatusNoRdbtn = new JRadioButton("Tidak");
		skipStatusNoRdbtn.setBounds(240, 200, 100, 30);
		add(skipStatusNoRdbtn);
		
		ButtonGroup skipStatusButtonGroup = new ButtonGroup();
		skipStatusButtonGroup.add(skipStatusYesRdbtn);
		skipStatusButtonGroup.add(skipStatusNoRdbtn);
		skipStatusYesRdbtn.setSelected(true);
		// status ijin
		JLabel lblstatusBonus = new JLabel("<html><center>Status Ijin</center></html>");
		lblstatusBonus.setBounds(30, 240, 100, 30);
		add(lblstatusBonus);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 240, 10, 30);
		add(label_7);
		
		permitStatusYesRdbtn = new JRadioButton("Ya");
		permitStatusYesRdbtn.setBounds(140, 240, 100, 30);
		add(permitStatusYesRdbtn);
		
		permitStatusNoRdbtn = new JRadioButton("Tidak");
		permitStatusNoRdbtn.setBounds(240, 240, 100, 30);
		add(permitStatusNoRdbtn);
		
		ButtonGroup permitStatusGroupButton = new ButtonGroup();
		permitStatusGroupButton.add(permitStatusYesRdbtn);
		permitStatusGroupButton.add(permitStatusNoRdbtn);
		permitStatusYesRdbtn.setSelected(true);
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
		getData();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				msPositionCmbox.requestFocusInWindow();
			}
		});
	}

	private void getData() {
		msPositionCmbox.setList(ServiceFactory.getPersonaliaBL().getMSPositions(""));
		payrollComponentCmbox.setList(ServiceFactory.getPersonaliaBL().getPayrollComponents(""));
	}

	protected void attach() {
		
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.PayrollMappingConfigPanel");
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("SALMAP");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		payrollComponentCodeField.setText(lastId.toString());
	}

	protected void save() {
		PayrollMapping payrollMapping = new PayrollMapping();
		payrollMapping.setCode(payrollComponentCodeField.getText());
		payrollMapping.setMsPosition(msPositionCmbox.getDataIndex());
		payrollMapping.setPositionId(msPositionCmbox.getDataIndex().getId());
		payrollMapping.setPayrollComponent(payrollComponentCmbox.getDataIndex());
		payrollMapping.setPayrollComponentCode(payrollComponentCmbox.getDataIndex().getCode());
		if (skipStatusYesRdbtn.isSelected()) payrollMapping.setIsAbsent(1);
		else payrollMapping.setIsAbsent(0);
		if (permitStatusYesRdbtn.isSelected()) payrollMapping.setIsLeave(1);
		else payrollMapping.setIsLeave(0);
		payrollMapping.setInputDate(new Date());
		payrollMapping.setInputBy("");
		payrollMapping.setEditDate(new Date());
		payrollMapping.setEditBy("");
		
		try {
			ServiceFactory.getPersonaliaBL().savePayrollMapping(payrollMapping);
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
			MainPanel.changePanel("module.personalia.ui.PayrollMappingConfigPanel");
		}
	}

	private void clear() {
		getLastID();
	}
}