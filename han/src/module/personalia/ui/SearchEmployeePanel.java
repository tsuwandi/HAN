package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.personalia.model.Division;

import javax.swing.JButton;

public class SearchEmployeePanel extends JPanel {

	private static final long serialVersionUID = 4895635283165466566L;
	private JTextField nameField;
	private JTextField nikField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	public SearchEmployeePanel() {
		setSize(1024, 630);
		setLayout(null);
		
		JLabel lblHeader = new JLabel("PENCARIAN LANJUT KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 10, 230, 25);
		add(lblHeader);
		// nik
		JLabel label = new JLabel("NIK");
		label.setBounds(10, 40, 100, 30);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 40, 10, 30);
		add(label_1);
		
		nikField = new JTextField();
		nikField.setBounds(120, 40, 200, 30);
		add(nikField);
		// nama
		JLabel label_2 = new JLabel("Nama Karyawan");
		label_2.setBounds(10, 80, 100, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(110, 80, 10, 30);
		add(label_3);
		
		nameField = new JTextField();
		nameField.setBounds(120, 80, 200, 30);
		add(nameField);
		
		JLabel lblTipeKaryawan = new JLabel("Tipe Karyawan");
		lblTipeKaryawan.setBounds(10, 121, 100, 30);
		add(lblTipeKaryawan);
		
		JLabel label_5 = new JLabel(":");
		label_5.setBounds(110, 121, 10, 30);
		add(label_5);
		// nomer ktp
		JLabel lblNomerKtp = new JLabel("Nomer KTP");
		lblNomerKtp.setBounds(10, 160, 100, 30);
		add(lblNomerKtp);
		
		JLabel label_4 = new JLabel(":");
		label_4.setBounds(110, 160, 10, 30);
		add(label_4);
		
		JLabel lblJabatan = new JLabel("Jabatan");
		lblJabatan.setBounds(10, 203, 100, 30);
		add(lblJabatan);
		
		JLabel lblDepartment = new JLabel("Departemen");
		lblDepartment.setBounds(10, 244, 100, 30);
		add(lblDepartment);
		
		JLabel lblDivisi = new JLabel("Divisi");
		lblDivisi.setBounds(10, 285, 100, 30);
		add(lblDivisi);
		
		JLabel lblTanggalMulaiKerja = new JLabel("Tanggal Mulai Kerja");
		lblTanggalMulaiKerja.setBounds(10, 326, 100, 30);
		add(lblTanggalMulaiKerja);
		
		JLabel lblKotaAsal = new JLabel("Kota Asal");
		lblKotaAsal.setBounds(10, 367, 100, 30);
		add(lblKotaAsal);
		
		JLabel lblTanggalLahir = new JLabel("Tanggal Lahir");
		lblTanggalLahir.setBounds(10, 408, 100, 30);
		add(lblTanggalLahir);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 449, 100, 30);
		add(lblGender);
		
		JLabel lblStatusPerkawinan = new JLabel("Status Perkawinan");
		lblStatusPerkawinan.setBounds(10, 490, 100, 30);
		add(lblStatusPerkawinan);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 531, 100, 30);
		add(lblStatus);
		
		
		
		JLabel label_6 = new JLabel(":");
		label_6.setBounds(110, 203, 10, 30);
		add(label_6);
		
		JLabel label_7 = new JLabel(":");
		label_7.setBounds(110, 244, 10, 30);
		add(label_7);
		
		JLabel label_8 = new JLabel(":");
		label_8.setBounds(110, 285, 10, 30);
		add(label_8);
		
		JLabel label_9 = new JLabel(":");
		label_9.setBounds(110, 326, 10, 30);
		add(label_9);
		
		JLabel label_10 = new JLabel(":");
		label_10.setBounds(110, 367, 10, 30);
		add(label_10);
		
		JLabel label_11 = new JLabel(":");
		label_11.setBounds(110, 408, 10, 30);
		add(label_11);
		
		JLabel label_12 = new JLabel(":");
		label_12.setBounds(110, 449, 10, 30);
		add(label_12);
		
		JLabel label_13 = new JLabel(":");
		label_13.setBounds(110, 490, 10, 30);
		add(label_13);
		
		JLabel label_14 = new JLabel(":");
		label_14.setBounds(110, 531, 10, 30);
		add(label_14);
		
		textField = new JTextField();
		textField.setBounds(120, 121, 200, 30);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 162, 200, 30);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(120, 208, 200, 30);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(120, 249, 200, 30);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(120, 290, 200, 30);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(120, 331, 200, 30);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(120, 372, 200, 30);
		add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(120, 413, 200, 30);
		add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setBounds(120, 454, 200, 30);
		add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setBounds(120, 495, 200, 30);
		add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setBounds(120, 530, 200, 30);
		add(textField_10);
		
		JButton button = new JButton("Reset");
		button.setBounds(210, 571, 90, 30);
		add(button);
		
		JButton button_1 = new JButton("Cari");
		button_1.setBounds(110, 571, 90, 30);
		add(button_1);
	}
}