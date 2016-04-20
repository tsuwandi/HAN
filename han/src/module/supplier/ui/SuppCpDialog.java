package module.supplier.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import module.supplier.model.SuppCp;
import module.util.EmailValidator;
import module.util.JTextFieldLimit;

public class SuppCpDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JPanel panel;

	JLabel lblName;
	JLabel lblDepartment;
	JLabel lblPhone;
	JLabel lblEmail;

	JTextField txtName;
	JTextField txtDepartment;
	JTextField txtPhone;
	JTextField txtEmail;

	JButton btnInsert;

	JLabel lblErrorName;
	JLabel lblErrorDepartment;
	JLabel lblErrorPhone;
	JLabel lblErrorEmail;

	private boolean isEdit;
	private SuppCp suppCp;
	private SupplierCreatePanel supplierCreate;
	private SupplierEditPanel supplierEdit;

	private Integer index;

	public SuppCpDialog(boolean edit, SuppCp suppCp, SupplierCreatePanel supplierCreate, Integer index) {
		this.isEdit = edit;
		this.suppCp = suppCp;
		this.supplierCreate = supplierCreate;
		this.index = index;
		init();
	}

	public SuppCpDialog(boolean edit, SuppCp suppCp, SupplierEditPanel supplierEdit, Integer index) {
		this.isEdit = edit;
		this.suppCp = suppCp;
		this.supplierEdit = supplierEdit;
		this.index = index;
		init();
	}

	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		getContentPane().setLayout(null);

		lblName = new JLabel("<html>Nama <font color=\"red\">*</font></html>");
		lblName.setBounds(25, 15, 150, 30);
		getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 15, 150, 30);
		txtName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtName);

		lblErrorName = new JLabel();
		lblErrorName.setForeground(Color.RED);
		lblErrorName.setBounds(335, 15, 225, 30);
		getContentPane().add(lblErrorName);

		lblDepartment = new JLabel("<html>Department <font color=\"red\">*</font></html>");
		lblDepartment.setBounds(25, 50, 150, 30);
		getContentPane().add(lblDepartment);

		txtDepartment = new JTextField();
		txtDepartment.setBounds(150, 50, 150, 30);
		txtDepartment.setDocument(new JTextFieldLimit(100));
		getContentPane().add(txtDepartment);

		lblErrorDepartment = new JLabel();
		lblErrorDepartment.setForeground(Color.RED);
		lblErrorDepartment.setBounds(335, 50, 225, 30);
		getContentPane().add(lblErrorDepartment);

		lblPhone = new JLabel("<html>Telepon <font color=\"red\">*</font></html>");
		lblPhone.setBounds(25, 85, 150, 30);
		getContentPane().add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(150, 85, 150, 30);
		txtPhone.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPhone);

		lblErrorPhone = new JLabel();
		lblErrorPhone.setForeground(Color.RED);
		lblErrorPhone.setBounds(335, 85, 225, 30);
		getContentPane().add(lblErrorPhone);

		lblEmail = new JLabel("<html>Email <font color=\"red\">*</font></html>");
		lblEmail.setBounds(25, 120, 150, 30);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(150, 120, 150, 30);
		txtEmail.setDocument(new JTextFieldLimit(50));
		getContentPane().add(txtEmail);

		lblErrorEmail = new JLabel();
		lblErrorEmail.setForeground(Color.RED);
		lblErrorEmail.setBounds(335, 120, 225, 30);
		getContentPane().add(lblErrorEmail);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsert();
			}
		});
		btnInsert.setBounds(459, 165, 100, 30);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtName.setText(suppCp.getName());
			txtDepartment.setText(suppCp.getDepartment());
			txtPhone.setText(suppCp.getPhone());
			txtEmail.setText(suppCp.getEmail());
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorName.setText("");
		lblErrorDepartment.setText("");
		lblErrorPhone.setText("");
		lblErrorEmail.setText("");

		if (txtName.getText() == null || txtName.getText().length() == 0) {
			lblErrorName.setText("Textbox Nama harus diisi.");
			isValid = false;
		}

		if (txtDepartment.getText() == null || txtDepartment.getText().length() == 0) {
			lblErrorDepartment.setText("Textbox Department harus diisi.");
			isValid = false;
		}

		if (txtPhone.getText() == null || txtPhone.getText().length() == 0) {
			lblErrorPhone.setText("Textbox Telepon harus diisi.");
			isValid = false;
		}

		if (txtEmail.getText() == null || txtEmail.getText().length() == 0) {
			lblErrorEmail.setText("Textbox Email harus diisi.");
			isValid = false;
		}

		if (Boolean.FALSE.equals(new EmailValidator().validate(txtEmail.getText()))) {
			lblErrorEmail.setText("Format Email salah.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		if (doValidate() == true) {
			//suppCp = new SuppCp();
			suppCp.setName(txtName.getText());
			suppCp.setDepartment(txtDepartment.getText());
			suppCp.setPhone(txtPhone.getText());
			suppCp.setEmail(txtEmail.getText());

			try {
				if (isEdit == false) {
					if (supplierCreate != null)
						supplierCreate.listOfSuppCp.add(suppCp);
					else if (supplierEdit != null)
						supplierEdit.listOfSuppCp.add(suppCp);
					
					JOptionPane.showMessageDialog(null, "Data berhasil ditambah", "Informasi",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (supplierCreate != null) {
						supplierCreate.listOfSuppCp.set(index, suppCp);
					} else if (supplierEdit != null) {
						supplierEdit.listOfSuppCp.set(index, suppCp);
					}
					JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Informasi",
							JOptionPane.INFORMATION_MESSAGE);
				}
				closeDialog();
				
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Data gagal ditambah", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			return;
		}
	}

	protected void closeDialog() {
		if (supplierCreate != null)
			supplierCreate.refreshTableSuppCp();
		else if (supplierEdit != null)
			supplierEdit.refreshTableSuppCp();

		dispose();
	}
}
