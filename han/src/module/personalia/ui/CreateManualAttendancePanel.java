package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Division;

public class CreateManualAttendancePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField nameField;
	private JTextField codeField;
	private JTextField roleField;
	private JTextField departmentField;
	private JTextField divisionField;
	private JTextField attendanceDateField;
	private JTextField startTimeField;
	private JTextField endTimeField;
	private JTextField informationField;

	public CreateManualAttendancePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Presensi > Input Presensi Manual");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("INPUT PRESENSI MANUAL");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 180, 25);
		add(lblHeader);
		
		JLabel lblNik = new JLabel("<html>NIK<font color='red'> * </font></html>");
		lblNik.setBounds(30, 80, 100, 30);
		add(lblNik);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		JLabel lblnamaKaryawan = new JLabel("<html>Nama Karyawan</html>");
		lblnamaKaryawan.setBounds(30, 120, 100, 30);
		add(lblnamaKaryawan);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		nameField = new JTextField();
		nameField.setEnabled(false);
		nameField.setEditable(false);
		nameField.setBounds(140, 120, 200, 30);
		add(nameField);
		
		codeField = new JTextField();
		codeField.setBounds(140, 80, 200, 30);
		add(codeField);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
		
		JLabel lblJabatan = new JLabel("Jabatan");
		lblJabatan.setBounds(30, 160, 100, 30);
		add(lblJabatan);
		
		JLabel lblDepartemen = new JLabel("Departemen");
		lblDepartemen.setBounds(30, 200, 100, 30);
		add(lblDepartemen);
		
		JLabel lblDivisi = new JLabel("Divisi");
		lblDivisi.setBounds(30, 240, 100, 30);
		add(lblDivisi);
		
		JLabel lblTanggalPresensi = new JLabel("<html>Tanggal Presensi<font color='red'> * </font></html>");
		lblTanggalPresensi.setBounds(30, 280, 100, 30);
		add(lblTanggalPresensi);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 161, 10, 30);
		add(label_6);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 200, 10, 30);
		add(label_7);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(130, 240, 10, 30);
		add(label_8);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 280, 10, 30);
		add(label_9);
		
		roleField = new JTextField();
		roleField.setEnabled(false);
		roleField.setEditable(false);
		roleField.setBounds(140, 160, 200, 30);
		add(roleField);
		
		departmentField = new JTextField();
		departmentField.setEnabled(false);
		departmentField.setEditable(false);
		departmentField.setBounds(140, 200, 200, 30);
		add(departmentField);
		
		divisionField = new JTextField();
		divisionField.setEnabled(false);
		divisionField.setEditable(false);
		divisionField.setBounds(140, 240, 200, 30);
		add(divisionField);
		
		attendanceDateField = new JTextField();
		attendanceDateField.setBounds(140, 280, 200, 30);
		add(attendanceDateField);
		
		JLabel lblJamMasuk = new JLabel("Jam Masuk");
		lblJamMasuk.setBounds(30, 320, 100, 30);
		add(lblJamMasuk);
		
		JLabel lblJamPulang = new JLabel("Jam Pulang");
		lblJamPulang.setBounds(30, 360, 100, 30);
		add(lblJamPulang);
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(30, 400, 100, 30);
		add(lblKeterangan);
		
		JLabel label_13 = new JLabel(":");
		label_13.setBounds(130, 321, 10, 30);
		add(label_13);
		
		JLabel label_14 = new JLabel(":");
		label_14.setBounds(130, 360, 10, 30);
		add(label_14);
		
		JLabel label_15 = new JLabel(":");
		label_15.setBounds(130, 400, 10, 30);
		add(label_15);
		
		startTimeField = new JTextField();
		startTimeField.setBounds(140, 320, 200, 30);
		add(startTimeField);
		
		endTimeField = new JTextField();
		endTimeField.setBounds(140, 360, 200, 30);
		add(endTimeField);
		
		informationField = new JTextField();
		informationField.setBounds(140, 400, 200, 30);
		add(informationField);
		
		JButton searchBtn = new JButton("Pencarian Lanjut");
		searchBtn.setBounds(350, 80, 120, 30);
		add(searchBtn);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				back();
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				nameField.requestFocusInWindow();
			}
		});
		
		getLastID();
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("DIV");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		codeField.setText(lastId.toString());
	}

	protected void save() {
		Division division = new Division();
		division.setId(codeField.getText());
		division.setName(nameField.getText());
		division.setInputDate(new Date());
		division.setInputBy(ServiceFactory.getSystemBL().getUsernameActive());
		division.setEditDate(new Date());
		division.setEditBy(ServiceFactory.getSystemBL().getUsernameActive());
		
		try {
			ServiceFactory.getPersonaliaBL().saveDivision(division);
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
			MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
		}
	}

	private void clear() {
		getLastID();
		nameField.setText("");
	}
}