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

import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.MSPosition;
import module.personalia.model.PayrollComponent;
import module.personalia.model.PayrollMapping;
import module.util.Bridging;

public class ViewPayrollMappingPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -9009351103530748031L;
	private ComboBox<MSPosition> msPositionCmbox;
	private ComboBox<PayrollComponent> payrollComponentCmbox;
	private JTextField payrollMappingCodeField;
	private JTextField referenceDocumentField;
	private JRadioButton skipStatusYesRdbtn;
	private JRadioButton skipStatusNoRdbtn;
	private JRadioButton permitStatusYesRdbtn;
	private JRadioButton permitStatusNoRdbtn;
	private PayrollMapping payrollMapping;
	private boolean editMode = false;
	private JButton editBtn;

	public ViewPayrollMappingPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Pemetaan Gaji > Edit Pemetaan Gaji");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 370, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("DISPLAY PEMETAAN GAJI");
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
		
		payrollMappingCodeField = new JTextField();
		payrollMappingCodeField.setBounds(140, 80, 200, 30);
		payrollMappingCodeField.setEditable(false);
		payrollMappingCodeField.setEnabled(false);
		add(payrollMappingCodeField);
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
		// status gaji
		JLabel lblstatusGaji = new JLabel("<html>Komponen Payroll</html>");
		lblstatusGaji.setBounds(30, 160, 100, 30);
		add(lblstatusGaji);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		payrollComponentCmbox = new ComboBox<PayrollComponent>();
		payrollComponentCmbox.setBounds(140, 160, 200, 30);
		add(payrollComponentCmbox);
		// status mangkir
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
		
		JButton deleteBtn = new JButton("Hapus");
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
	}

	protected void attach() {
		
	}

	protected void back() {
		
	}

	protected void delete() {
		if (DialogBox.showDeleteChoice()==0) {
			payrollMapping.setDeleteDate(new Date());
			payrollMapping.setDeleteBy("");
			//ServiceFactory.getPersonaliaBL().deleteDivision(division);
			MainPanel.changePanel("module.personalia.ui.PayrollMappingConfigPanel");
		} else {
			
		}
	}

	protected void update() {
		PayrollMapping payrollComponent = new PayrollMapping();
		payrollComponent.setCode(payrollMappingCodeField.getText());
		payrollComponent.setMsPosition(msPositionCmbox.getDataIndex());
		payrollComponent.setPayrollComponent(payrollComponentCmbox.getDataIndex());
		if (skipStatusYesRdbtn.isSelected()) payrollComponent.setIsAbsent(1);
		else payrollComponent.setIsAbsent(0);
		if (permitStatusYesRdbtn.isSelected()) payrollComponent.setIsLeave(1);
		else payrollComponent.setIsLeave(0);
		
		payrollComponent.setReferenceDocument(referenceDocumentField.getText());
		
		try {
			//ServiceFactory.getPersonaliaBL().updateDivision(division);
			DialogBox.showEdit();
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		payrollMapping = (PayrollMapping) objects[0];
		
		payrollMappingCodeField.setText(payrollMapping.getCode());
		msPositionCmbox.setSelectedItem(payrollMapping.getMsPosition());
		payrollComponentCmbox.setSelectedItem(payrollMapping.getPayrollComponent());
		if (payrollMapping.getIsAbsent() == 1) {
			skipStatusYesRdbtn.setSelected(true);
		} else skipStatusNoRdbtn.setSelected(true);
		if (payrollMapping.getIsLeave() == 1) {
			permitStatusYesRdbtn.setSelected(true);
		} else permitStatusNoRdbtn.setSelected(true);
		referenceDocumentField.setText(payrollMapping.getReferenceDocument());
	}
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		payrollMappingCodeField.setEnabled(true);
		msPositionCmbox.setEnabled(true);
		payrollComponentCmbox.setEnabled(true);
		skipStatusYesRdbtn.setEnabled(true);
		skipStatusNoRdbtn.setEnabled(true);
		permitStatusYesRdbtn.setEnabled(true);
		permitStatusNoRdbtn.setEnabled(true);
		referenceDocumentField.setEnabled(true);
		
		editBtn.setText("Simpan");
		editBtn.updateUI();
	}
}