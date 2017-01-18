package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import module.personalia.model.EmpPosition;
import module.personalia.model.EmployeeType;
import module.personalia.model.MSPosition;

public class PopUpPositionHistoryPanel extends JDialog {

	private static final long serialVersionUID = -9009351103530748031L;
	private JDateChooser endWorkDateField;
	private JDateChooser startWorkDateField;
	private NumberField prohibitionTimeField;
	List<MSPosition> msPositions;
	private ComboBox<MSPosition> positionCmbox;
	private JTextField departmentField;
	private JTextField divisionField;
	List<EmployeeType> employeeTypes;
	private ComboBox<EmployeeType> employeeTypeCmbox;
	private JTextField referenceDocumentField;
	private JTextArea noteField;
	private CreateEmployeePanel parent;

	public PopUpPositionHistoryPanel(CreateEmployeePanel parent) {
		super((JFrame)parent.getTopLevelAncestor());
		this.parent = parent;
		setSize(530, 630);
		getContentPane().setLayout(null);
		getContentPane().setSize(530, 630);
		
		JLabel breadCrumbLbl = new JLabel("Personalia > Employee > History Jabatan > Pendaftaran Baru");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 390, 25);
		getContentPane().add(breadCrumbLbl);
		
		JLabel lblHeader = new JLabel("PENDAFTARAN HISTORY JABATAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 230, 25);
		getContentPane().add(lblHeader);
		// tanggal mulai kerja
		JLabel lblTanggalMulaiKerja = new JLabel("<html>Tanggal Mulai Kerja<font color='red'> * </font></html>");
		lblTanggalMulaiKerja.setBounds(30, 80, 100, 30);
		getContentPane().add(lblTanggalMulaiKerja);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(130, 80, 10, 30);
		getContentPane().add(label_1);
		
		startWorkDateField = new JDateChooser();
		startWorkDateField.setBounds(140, 80, 200, 30);
		getContentPane().add(startWorkDateField);
		// tanggal akhir kerja
		JLabel lbltanggalAkhirKerja = new JLabel("Tanggal Akhir Kerja");
		lbltanggalAkhirKerja.setBounds(30, 120, 100, 30);
		getContentPane().add(lbltanggalAkhirKerja);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(130, 120, 10, 30);
		getContentPane().add(label_3);
		
		endWorkDateField = new JDateChooser();
		endWorkDateField.setBounds(140, 120, 200, 30);
		getContentPane().add(endWorkDateField);
		// masa percobaan
		JLabel lblMasaJabatan = new JLabel("Masa Percobaan");
		lblMasaJabatan.setBounds(30, 160, 100, 30);
		getContentPane().add(lblMasaJabatan);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(130, 160, 10, 30);
		getContentPane().add(label_2);
		
		prohibitionTimeField = new NumberField(2);
		prohibitionTimeField.setBounds(140, 160, 200, 30);
		getContentPane().add(prohibitionTimeField);
		prohibitionTimeField.setColumns(10);
		// jabatan
		JLabel lblJabatan = new JLabel("<html>Jabatan<font color='red'> * </font></html>");
		lblJabatan.setBounds(30, 200, 100, 30);
		getContentPane().add(lblJabatan);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(130, 200, 10, 30);
		getContentPane().add(label_5);
		
		positionCmbox = new ComboBox<>();
		positionCmbox.setBounds(140, 200, 200, 30);
		getContentPane().add(positionCmbox);
		
		positionCmbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				update();
			}
		});
		// department
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(30, 240, 100, 30);
		getContentPane().add(lblDepartment);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(130, 240, 10, 30);
		getContentPane().add(label_4);
		
		departmentField = new JTextField();
		departmentField.setBounds(140, 240, 200, 30);
		departmentField.setEditable(false);
		departmentField.setEnabled(false);
		getContentPane().add(departmentField);
		// division
		JLabel lblDivision = new JLabel("Divisi");
		lblDivision.setBounds(30, 280, 100, 30);
		getContentPane().add(lblDivision);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(130, 280, 10, 30);
		getContentPane().add(label_7);
		
		divisionField = new JTextField();
		divisionField.setBounds(140, 280, 200, 30);
		divisionField.setEditable(false);
		divisionField.setEnabled(false);
		getContentPane().add(divisionField);
		// tipe karyawan
		JLabel lblTipeKaryawan = new JLabel("<html>Tipe Karyawan<font color='red'> * </font></html>");
		lblTipeKaryawan.setBounds(30, 320, 100, 30);
		getContentPane().add(lblTipeKaryawan);
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(130, 320, 10, 30);
		getContentPane().add(label_6);
		
		employeeTypeCmbox = new ComboBox<>();
		employeeTypeCmbox.setBounds(140, 320, 200, 30);
		getContentPane().add(employeeTypeCmbox);
		// dokument referensi
		JLabel lblDokumenReferensi = new JLabel("Dokumen Referensi");
		lblDokumenReferensi.setBounds(30, 360, 100, 30);
		getContentPane().add(lblDokumenReferensi);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(130, 360, 10, 30);
		getContentPane().add(label_9);
		
		referenceDocumentField = new JTextField();
		referenceDocumentField.setBounds(140, 360, 200, 30);
		getContentPane().add(referenceDocumentField);
		// keterangan
		JLabel lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setBounds(30, 400, 100, 30);
		getContentPane().add(lblKeterangan);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(130, 400, 10, 30);
		getContentPane().add(label_8);
		
		noteField = new JTextArea();
		noteField.setBounds(140, 400, 200, 90);
		getContentPane().add(noteField);
		
		JButton saveBtn = new JButton("Tambah");
		saveBtn.setBounds(410, 550, 90, 30);
		getContentPane().add(saveBtn);
		
		JButton searchFileBtn = new JButton("Cari File");
		searchFileBtn.setBounds(350, 360, 70, 30);
		getContentPane().add(searchFileBtn);
		
		JButton uploadBtn = new JButton("Upload");
		uploadBtn.setBounds(430, 360, 70, 30);
		getContentPane().add(uploadBtn);
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		getData();
	}

	protected void update() {
		departmentField.setText(positionCmbox.getDataIndex().getDepartementName());
		divisionField.setText(positionCmbox.getDataIndex().getDivisionName());
	}

	private void getData() {
		positionCmbox.setList(ServiceFactory.getPersonaliaBL().getMSPositions(""));
		employeeTypeCmbox.setList(ServiceFactory.getPersonaliaBL().getEmployeeTypes(""));
	}

	protected void save() {
		EmpPosition empPosition = new EmpPosition();
		empPosition.setEmployeeId("");
		empPosition.setStartDate(startWorkDateField.getDate());
		empPosition.setEndDate(endWorkDateField.getDate());
		empPosition.setProbation(Integer.parseInt(prohibitionTimeField.getText()));
		empPosition.setMsPosition(positionCmbox.getDataIndex());
		empPosition.setPositionId(positionCmbox.getDataIndex().getId());
		empPosition.setEmployeeType(employeeTypeCmbox.getDataIndex());
		empPosition.setReferenceDoc(referenceDocumentField.getText());
		empPosition.setNotes(noteField.getText());
		
		try {
			parent.getEmpPositions().add(empPosition);
			parent.getEmployeePositionHistoryTableModel().setEmployeePositions(parent.getEmpPositions());
			parent.getEmpPositionTable().updateUI();
			parent.getPopUpPositionHistoryPanel().setVisible(false);
			clear();
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError("Data tidak berhasil disimpan");
		}
		
	}

	private void clear() {
		startWorkDateField.setDate(null);
		endWorkDateField.setDate(null);
		prohibitionTimeField.setText("0");
		positionCmbox.setSelectedIndex(0);
		employeeTypeCmbox.setSelectedIndex(0);
		referenceDocumentField.setText("");
		noteField.setText("");
	}
}