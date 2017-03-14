package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import module.personalia.model.Attendance;

public class PopUpSearchAttendancePanel extends JDialog {

	private static final long serialVersionUID = 4895635283165466566L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JPanel parent;
	private JTextField roleField;
	private JTextField departmentField;
	private JTextField divisionField;
	private JDateChooser attendanceDateField;
	private JCheckBox inTimeChkbox;
	private JCheckBox manualChkbox;
	private JCheckBox outTimeChkbox;
	private JCheckBox machineChkbox;
	private AttendanceConfigPanel attendanceConfigPanel;
	
	public JPanel getParent() {
		return parent;
	}

	public void setParent(JPanel parent) {
		this.parent = parent;
	}

	public PopUpSearchAttendancePanel(JPanel parent) {
		super((JFrame) parent.getTopLevelAncestor());
		
		attendanceConfigPanel = (AttendanceConfigPanel) parent;
		
		setSize(530, 630);
		getContentPane().setLayout(null);
		getContentPane().setSize(530, 630);
		
		JLabel lblHeader = new JLabel("PENCARIAN LANJUT PRESENSI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 10, 200, 25);
		getContentPane().add(lblHeader);

		JLabel lblNik = new JLabel("NIK");
		lblNik.setBounds(10, 40, 100, 30);
		getContentPane().add(lblNik);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 40, 10, 30);
		getContentPane().add(label_1);
		
		JLabel lblNamaKayawan = new JLabel("Nama Kayawan");
		lblNamaKayawan.setBounds(10, 80, 100, 30);
		getContentPane().add(lblNamaKayawan);
		
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
		resetBtn.setBounds(120, 375, 90, 30);
		getContentPane().add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(220, 375, 90, 30);
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
		
		JLabel lblTanggalPresensi = new JLabel("Tanggal Presensi");
		lblTanggalPresensi.setBounds(10, 244, 100, 30);
		getContentPane().add(lblTanggalPresensi);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(110, 121, 10, 30);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(110, 162, 10, 30);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(110, 203, 10, 30);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(110, 244, 10, 30);
		getContentPane().add(label_9);
		
		roleField = new JTextField();
		roleField.setBounds(120, 121, 200, 30);
		getContentPane().add(roleField);
		
		departmentField = new JTextField();
		departmentField.setBounds(120, 162, 200, 30);
		getContentPane().add(departmentField);
		
		divisionField = new JTextField();
		divisionField.setBounds(120, 203, 200, 30);
		getContentPane().add(divisionField);
		
		attendanceDateField = new JDateChooser();
		attendanceDateField.setBounds(120, 244, 200, 30);
		getContentPane().add(attendanceDateField);
		
		JLabel lblJam = new JLabel("Jam");
		lblJam.setBounds(10, 285, 100, 30);
		getContentPane().add(lblJam);
		
		JLabel lblSumberData = new JLabel("Sumber Data");
		lblSumberData.setBounds(10, 326, 100, 30);
		getContentPane().add(lblSumberData);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(110, 285, 10, 30);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(110, 326, 10, 30);
		getContentPane().add(label_5);
		
		inTimeChkbox = new JCheckBox("Jam Masuk");
		inTimeChkbox.setBounds(120, 289, 90, 23);
		getContentPane().add(inTimeChkbox);
		
		manualChkbox = new JCheckBox("Manual Input");
		manualChkbox.setBounds(120, 330, 90, 23);
		getContentPane().add(manualChkbox);
		
		outTimeChkbox = new JCheckBox("Jam Pulang");
		outTimeChkbox.setBounds(212, 289, 90, 23);
		getContentPane().add(outTimeChkbox);
		
		machineChkbox = new JCheckBox("Mesin Fingerprint");
		machineChkbox.setBounds(212, 330, 120, 23);
		getContentPane().add(machineChkbox);
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
		if(attendanceDateField.getDate()== null) {
			buffer.append(" and attendance_date = '");
			buffer.append(attendanceDateField.getDate());
			buffer.append("' ");
		}
		List<Attendance> attendances = ServiceFactory.getPersonaliaBL().getAttendances(buffer.toString());
		attendanceConfigPanel.setAttendances(attendances);
	}

	protected void reset() {
		employeeCodeField.setText("");
		employeeNameField.setText("");
		roleField.setText("");
		departmentField.setText("");
		divisionField.setText("");
		attendanceDateField.setDate(null);
		inTimeChkbox.setSelected(false);
		outTimeChkbox.setSelected(false);
		machineChkbox.setSelected(false);
		manualChkbox.setSelected(false);
	}
}