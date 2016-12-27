package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.personalia.model.EmployeeType;
import module.personalia.model.MSPosition;

public class CreatePositionHistoryPanel extends JPanel {

	private static final long serialVersionUID = -9009351103530748031L;
	private JDateChooser endWorkDateField;
	private JDateChooser startWorkDateField;
	private JTextField prohibitionTimeField;
	private ComboBox<MSPosition> positionCmbox;
	private JTextField departmentField;
	private JTextField divisionField;
	private ComboBox<EmployeeType> employeeTypeCmbox;
	private JTextField referenceDocumentField;
	private JTextArea noteField;

	public CreatePositionHistoryPanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Employee > History Jabatan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 390, 25);
		add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN HISTORY JABATAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 230, 25);
		add(lblHeader);
		// tanggal mulai kerja
		JLabel lblTanggalMulaiKerja = new JLabel("<html>Tanggal Mulai Kerja<font color='red'> * </font></html>");
		lblTanggalMulaiKerja.setBounds(30, 80, 100, 30);
		add(lblTanggalMulaiKerja);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		add(label_1);
		
		startWorkDateField = new JDateChooser();
		startWorkDateField.setBounds(140, 80, 200, 30);
		add(startWorkDateField);
		// tanggal akhir kerja
		JLabel lbltanggalAkhirKerja = new JLabel("Tanggal Akhir Kerja");
		lbltanggalAkhirKerja.setBounds(30, 120, 100, 30);
		add(lbltanggalAkhirKerja);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		add(label_3);
		
		endWorkDateField = new JDateChooser();
		endWorkDateField.setBounds(140, 120, 200, 30);
		add(endWorkDateField);
		// masa percobaan
		JLabel lblMasaJabatan = new JLabel("Masa Percobaan");
		lblMasaJabatan.setBounds(30, 160, 100, 30);
		add(lblMasaJabatan);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		add(label_2);
		
		prohibitionTimeField = new JTextField();
		prohibitionTimeField.setBounds(140, 160, 200, 30);
		add(prohibitionTimeField);
		prohibitionTimeField.setColumns(10);
		// jabatan
		JLabel lblJabatan = new JLabel("<html>Jabatan<font color='red'> * </font></html>");
		lblJabatan.setBounds(30, 200, 100, 30);
		add(lblJabatan);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 200, 10, 30);
		add(label_5);
		
		positionCmbox = new ComboBox<>();
		positionCmbox.setBounds(140, 200, 200, 30);
		add(positionCmbox);
		// department
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(30, 240, 100, 30);
		add(lblDepartment);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 240, 10, 30);
		add(label_4);
		
		departmentField = new JTextField();
		departmentField.setBounds(140, 240, 200, 30);
		add(departmentField);
		// division
		JLabel lblDivision = new JLabel("Divisi");
		lblDivision.setBounds(30, 280, 100, 30);
		add(lblDivision);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 280, 10, 30);
		add(label_7);
		
		divisionField = new JTextField();
		divisionField.setBounds(140, 280, 200, 30);
		add(divisionField);
		// tipe karyawan
		JLabel lblTipeKaryawan = new JLabel("<html>Tipe Karyawan<font color='red'> * </font></html>");
		lblTipeKaryawan.setBounds(30, 320, 100, 30);
		add(lblTipeKaryawan);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		add(label_6);
		
		employeeTypeCmbox = new ComboBox<>();
		employeeTypeCmbox.setBounds(140, 320, 200, 30);
		add(employeeTypeCmbox);
		// dokument referensi
		JLabel lblDokumenReferensi = new JLabel("Dokumen Referensi");
		lblDokumenReferensi.setBounds(30, 360, 100, 30);
		add(lblDokumenReferensi);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 360, 10, 30);
		add(label_9);
		
		referenceDocumentField = new JTextField();
		referenceDocumentField.setBounds(140, 360, 200, 30);
		add(referenceDocumentField);
		// keterangan
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(30, 400, 100, 30);
		add(lblKeterangan);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(130, 400, 10, 30);
		add(label_8);
		
		noteField = new JTextArea();
		noteField.setBounds(140, 400, 200, 30);
		add(noteField);
		
		JButton saveBtn = new JButton("Simpan");
		saveBtn.setBounds(924, 589, 90, 30);
		add(saveBtn);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		getLastID();
	}

	private void getLastID() {
		StringBuffer lastId = new StringBuffer();
		lastId.append("DIV");
		lastId.append(String.format("%03d", ServiceFactory.getPersonaliaBL().getLastIdDivision()));
	}

	protected void save() {
		
		
		try {
			
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
	}
}