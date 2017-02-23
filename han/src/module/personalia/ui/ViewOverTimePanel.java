package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.Division;
import module.personalia.model.Employee;

public class ViewOverTimePanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JTextField employeeNameField;
	private JTextField employeeCodeField;
	private JTextField msPositionField;
	private JTextField departmentField;
	private JTextField divisionField;
	private JDateChooser overTimeDateField;
	private JTextField startTimeField;
	private JTextField endTimeField;
	private JTextField referenceDocField;
	private JTextField descField;
	private Employee employee;

	public ViewOverTimePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Lembur > Edit Lembur");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 290, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("VIEW LEMBUR");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 150, 25);
		add(lblHeader);
		// nik
		JLabel lblNik = new JLabel("<html>NIK<font color='red'> * </font></html>");
		lblNik.setBounds(30, 80, 100, 30);
		add(lblNik);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		employeeCodeField = new JTextField();
		employeeCodeField.setBounds(140, 80, 200, 30);
		add(employeeCodeField);
		
		employeeCodeField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					search();
				}
			}
		});
		// name
		JLabel lblNamaKaryawan = new JLabel("Nama Karyawan");
		lblNamaKaryawan.setBounds(30, 120, 100, 30);
		add(lblNamaKaryawan);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		employeeNameField = new JTextField();
		employeeNameField.setEnabled(false);
		employeeNameField.setEditable(false);
		employeeNameField.setBounds(140, 120, 200, 30);
		add(employeeNameField);
		//msposition
		JLabel lblJabatan = new JLabel("Jabatan");
		lblJabatan.setBounds(30, 160, 100, 30);
		add(lblJabatan);
		
		JLabel label = new JLabel(":");
		label.setBounds(130, 160, 10, 30);
		add(label);
		
		msPositionField = new JTextField();
		msPositionField.setEnabled(false);
		msPositionField.setEditable(false);
		msPositionField.setBounds(140, 160, 200, 30);
		add(msPositionField);
		//departmen
		JLabel lblDepartemen = new JLabel("Departemen");
		lblDepartemen.setBounds(30, 200, 100, 30);
		add(lblDepartemen);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 200, 10, 30);
		add(label_2);
		
		departmentField = new JTextField();
		departmentField.setEnabled(false);
		departmentField.setEditable(false);
		departmentField.setBounds(140, 200, 200, 30);
		add(departmentField);
		// divisi
		JLabel lblDivisi = new JLabel("Divisi");
		lblDivisi.setBounds(30, 240, 100, 30);
		add(lblDivisi);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 240, 10, 30);
		add(label_4);
		
		divisionField = new JTextField();
		divisionField.setEnabled(false);
		divisionField.setEditable(false);
		divisionField.setBounds(140, 240, 200, 30);
		add(divisionField);
		//tanggal lembur
		JLabel lblTanggalLembur = new JLabel("<html>Tanggal Lembur<font color='red'> * </font></html>");
		lblTanggalLembur.setBounds(30, 280, 100, 30);
		add(lblTanggalLembur);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 280, 10, 30);
		add(label_5);
		
		overTimeDateField = new JDateChooser();
		overTimeDateField.setBounds(140, 280, 200, 30);
		add(overTimeDateField);
		//jam mulai
		JLabel lblJamMulai = new JLabel("<html>Jam Mulai<font color='red'> * </font></html>");
		lblJamMulai.setBounds(30, 320, 100, 30);
		add(lblJamMulai);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		add(label_6);
		
		startTimeField = new JTextField();
		startTimeField.setBounds(140, 320, 200, 30);
		add(startTimeField);
		// jam selesai
		JLabel lblJamSelesai = new JLabel("<html>Jam Selesai<font color='red'> * </font></html>");
		lblJamSelesai.setBounds(30, 360, 100, 30);
		add(lblJamSelesai);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 360, 10, 30);
		add(label_7);
		
		endTimeField = new JTextField();
		endTimeField.setBounds(140, 360, 200, 30);
		add(endTimeField);
		// referensi
		JLabel lblDokumenReferensi = new JLabel("<html>Dokumen Referensi<font color='red'> * </font></html>");
		lblDokumenReferensi.setBounds(30, 400, 100, 30);
		add(lblDokumenReferensi);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(130, 400, 10, 30);
		add(label_8);
		
		referenceDocField = new JTextField();
		referenceDocField.setBounds(140, 400, 200, 30);
		add(referenceDocField);
		// desc
		JLabel lblKeterangan = new JLabel("<html>Keterangan<font color='red'> * </font></html>");
		lblKeterangan.setBounds(30, 440, 100, 30);
		add(lblKeterangan);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 440, 10, 30);
		add(label_9);
		
		descField = new JTextField();
		descField.setBounds(140, 440, 200, 30);
		add(descField);
		
		JButton backBtn = new JButton("Kembali");
		backBtn.setBounds(10, 589, 90, 30);
		add(backBtn);
	
		JButton searchNikBtn = new JButton("Pencarian Lanjut");
		searchNikBtn.setBounds(350, 80, 120, 30);
		add(searchNikBtn);
		
		JButton button = new JButton("Cetak");
		button.setBounds(724, 589, 90, 30);
		add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.setEnabled(false);
		button_1.setBounds(824, 589, 90, 30);
		add(button_1);
		
		JButton button_2 = new JButton("Edit");
		button_2.setBounds(924, 589, 90, 30);
		add(button_2);
		
		searchNikBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				back();
			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				employeeNameField.requestFocusInWindow();
			}
		});
		
		//getLastID();
	}

	protected void search() {
		employee = ServiceFactory.getPersonaliaBL().getEmployees(" and LOWER(emp_code) like ('"+employeeCodeField.getText()+"')").get(0);
		if (employee!=null) {
			employeeNameField.setText(employee.getName());
			msPositionField.setText(employee.getMsPosition().getName());
			departmentField.setText(employee.getDepartment().getName());
			divisionField.setText(employee.getDivision().getName());
		} else {
			DialogBox.showError("Data tidak ditemukan, silahkan coba lagi");
		}
	}

	protected void back() {
		MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel");
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("DIV");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
		employeeCodeField.setText(lastId.toString());
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
		employeeNameField.setText("");
	}
}