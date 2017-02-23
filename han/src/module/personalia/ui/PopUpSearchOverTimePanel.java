package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.personalia.model.Division;
import module.personalia.model.OverTime;

public class PopUpSearchOverTimePanel extends JDialog {

	private static final long serialVersionUID = 4895635283165466566L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JPanel parent;
	private JTextField roleField;
	private JTextField departmentField;
	private JTextField divisionField;
	private JDateChooser overtimeStartDateField;
	private JTextField overtimeStartTimeField;
	private JTextField referenceDocField;
	private JDateChooser overtimeEndDateField;
	private JTextField overtimeEndTimeField;
	
	public JPanel getParent() {
		return parent;
	}

	public void setParent(JPanel parent) {
		this.parent = parent;
	}

	public PopUpSearchOverTimePanel(JPanel parent) {
		super((JFrame) parent.getTopLevelAncestor());
		
		setSize(1024, 630);
		getContentPane().setLayout(null);
		getContentPane().setSize(1024, 630);
		getContentPane().setLayout(null);
		
		JLabel lblHeader = new JLabel("PENCARIAN LANJUT LEMBUR");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 10, 210, 25);
		getContentPane().add(lblHeader);

		JLabel lblNik = new JLabel("NIK");
		lblNik.setBounds(10, 40, 100, 30);
		getContentPane().add(lblNik);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 40, 10, 30);
		getContentPane().add(label_1);
		
		JLabel lblNamaKaryawan = new JLabel("Nama Karyawan");
		lblNamaKaryawan.setBounds(10, 80, 100, 30);
		getContentPane().add(lblNamaKaryawan);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(110, 80, 10, 30);
		getContentPane().add(label_3);
		
		employeeNameField = new JTextField();
		employeeNameField.setBounds(120, 80, 200, 30);
		getContentPane().add(employeeNameField);
		
		employeeCodeField = new JTextField();
		employeeCodeField.setBounds(120, 40, 200, 30);
		getContentPane().add(employeeCodeField);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(220, 400, 90, 30);
		getContentPane().add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(120, 400, 90, 30);
		getContentPane().add(searchBtn);
		
		JLabel lblJabatan = new JLabel("Jabatan");
		lblJabatan.setBounds(10, 121, 100, 30);
		getContentPane().add(lblJabatan);
		
		JLabel lblDepartemen = new JLabel("Departemen");
		lblDepartemen.setBounds(10, 162, 100, 30);
		getContentPane().add(lblDepartemen);
		
		JLabel lblDivisi = new JLabel("Divisi");
		lblDivisi.setBounds(10, 203, 100, 30);
		getContentPane().add(lblDivisi);
		
		JLabel lblTanggalLembur = new JLabel("Tanggal Lembur");
		lblTanggalLembur.setBounds(10, 244, 100, 30);
		getContentPane().add(lblTanggalLembur);
		
		JLabel lblJamLembur = new JLabel("Jam Lembur");
		lblJamLembur.setBounds(10, 285, 100, 30);
		getContentPane().add(lblJamLembur);
		
		JLabel lblDokumenReferensi = new JLabel("Dokumen Referensi");
		lblDokumenReferensi.setBounds(10, 326, 100, 30);
		getContentPane().add(lblDokumenReferensi);
		
		JLabel label = new JLabel(":");
		label.setBounds(110, 121, 10, 30);
		getContentPane().add(label);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(110, 162, 10, 30);
		getContentPane().add(label_2);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(110, 203, 10, 30);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(110, 244, 10, 30);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(110, 285, 10, 30);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(110, 326, 10, 30);
		getContentPane().add(label_7);
		
		roleField = new JTextField();
		roleField.setBounds(120, 121, 200, 30);
		getContentPane().add(roleField);
		
		departmentField = new JTextField();
		departmentField.setBounds(120, 162, 200, 30);
		getContentPane().add(departmentField);
		
		divisionField = new JTextField();
		divisionField.setBounds(120, 203, 200, 30);
		getContentPane().add(divisionField);
		
		overtimeStartDateField = new JDateChooser();
		overtimeStartDateField.setBounds(120, 244, 75, 30);
		getContentPane().add(overtimeStartDateField);
		
		overtimeStartTimeField = new JTextField();
		overtimeStartTimeField.setBounds(120, 285, 75, 30);
		getContentPane().add(overtimeStartTimeField);
		
		referenceDocField = new JTextField();
		referenceDocField.setBounds(120, 326, 200, 30);
		getContentPane().add(referenceDocField);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(205, 244, 10, 30);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(205, 285, 10, 30);
		getContentPane().add(label_9);
		
		overtimeEndDateField = new JDateChooser();
		overtimeEndDateField.setBounds(220, 244, 75, 30);
		getContentPane().add(overtimeEndDateField);
		
		overtimeEndTimeField = new JTextField();
		overtimeEndTimeField.setBounds(220, 285, 75, 30);
		getContentPane().add(overtimeEndTimeField);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
	}

	protected void search() {
		StringBuilder buffer = new StringBuilder();
		if(!"".equals(employeeCodeField.getText())) {
			buffer.append(" and id like '%");
			buffer.append(employeeCodeField.getText());
			buffer.append("%' ");
		}
		if(!"".equals(employeeNameField.getText())) {
			buffer.append(" and name like '%");
			buffer.append(employeeNameField.getText());
			buffer.append("%' ");
		}
		List<Division> divisions = ServiceFactory.getPersonaliaBL().getDivisions(buffer.toString());
		//List<OverTime> overTimes = ServiceFactory.getPersonaliaBL().
		MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel",divisions);
	}

	protected void reset() {
		employeeCodeField.setText("");
		employeeNameField.setText("");
		roleField.setText("");
		departmentField.setText("");
		divisionField.setText("");
		overtimeStartDateField.setDate(null);
		overtimeEndDateField.setDate(null);
		overtimeStartTimeField.setText("");
		overtimeEndTimeField.setText("");
		referenceDocField.setText("");
	}
}