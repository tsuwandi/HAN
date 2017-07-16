package module.customer.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.customer.model.CustAddress;
import module.util.EmailValidator;
import module.util.JTextFieldLimit;

public class CustomerAddressDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CustomerAddressDialog.class);

	JPanel panel;

	JLabel lblAddressType;
	JLabel lblAddress;
	JLabel lblZipCode;
	JLabel lblProvince;
	JLabel lblCity;
	JLabel lblPhone;
	JLabel lblFax;

	JComboBox<String> cbAddressType;
	JTextArea txtAddress;
	NumberField txtZipCode;
	JTextField txtProvince;
	JTextField txtCity;
	NumberField txtPhone;
	NumberField txtFax;

	JButton btnInsert;

	JLabel lblErrorAddressType;
	JLabel lblErrorAddress;
	JLabel lblErrorProvince;
	JLabel lblErrorCity;

	private boolean isEdit;
	private boolean isView;
	private CustAddress custAddress;
	private CustomerCreatePanel customerCreate;
	private CustomerEditPanel customerEdit;
	private CustomerViewPanel customerView;

	private Integer index;

	JLabel lblContactPerson;
	JLabel lblEmail;
	JTextField txtContactPerson;
	JTextField txtEmail;
	JLabel lblErrorContactPerson;
	JLabel lblErrorEmail;

	public CustomerAddressDialog(boolean edit, CustAddress custAddress, CustomerCreatePanel customerCreate,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.custAddress = custAddress;
		this.customerCreate = customerCreate;
		this.index = index;
		init();
	}

	public CustomerAddressDialog(boolean edit, CustAddress custAddress, CustomerEditPanel customerEdit, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.custAddress = custAddress;
		this.customerEdit = customerEdit;
		this.index = index;
		init();
	}

	public CustomerAddressDialog(boolean view, CustAddress custAddress, CustomerViewPanel customerView, Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.custAddress = custAddress;
		this.customerView = customerView;
		this.index = index;
		init();
	}

	/*
	 * public SuppAddressDialog(boolean edit, SuppAddress suppAddress,
	 * SupplierEditPanel supplierEdit, Integer index) { this.isEdit = edit;
	 * this.isView = false; this.suppAddress = suppAddress; this.supplierEdit =
	 * supplierEdit; this.index = index; init(); }
	 * 
	 * public SuppAddressDialog(boolean view, SuppAddress suppAddress,
	 * SupplierViewPanel supplierView, Integer index) { this.isEdit = true;
	 * this.isView = view; this.suppAddress = suppAddress; this.supplierView =
	 * supplierView; this.index = index; init(); }
	 */

	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 420);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblAddressType = new JLabel("<html>Tipe Alamat <font color=\"red\">*</font></html>");
		lblAddressType.setBounds(25, 15, 150, 25);
		getContentPane().add(lblAddressType);

		lblErrorAddressType = new JLabel();
		lblErrorAddressType.setForeground(Color.RED);
		lblErrorAddressType.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorAddressType);

		cbAddressType = new JComboBox<String>();
		cbAddressType.addItem("-- Pilih Tipe Alamat --");
		cbAddressType.addItem("Billing");
		cbAddressType.addItem("Warehouse");
		cbAddressType.addItem("Office");
		cbAddressType.addItem("Shipping");
		cbAddressType.setBounds(150, 15, 150, 25);
		getContentPane().add(cbAddressType);

		lblAddress = new JLabel("<html>Alamat <font color=\"red\">*</font></html>");
		lblAddress.setBounds(25, 45, 150, 25);
		getContentPane().add(lblAddress);

		txtAddress = new JTextArea();
		txtAddress.setRows(3);
		txtAddress.setColumns(3);
		txtAddress.setBounds(150, 45, 150, 70);
		txtAddress.setDocument(new JTextFieldLimit(200));
		((AbstractDocument) txtAddress.getDocument()).setDocumentFilter(filter);
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (e.getModifiers() > 0) {
						txtAddress.transferFocusBackward();
					} else {
						txtAddress.transferFocus();
					}
					e.consume();
				}
			}
		});

		JScrollPane spTxtAddress = new JScrollPane(txtAddress);
		spTxtAddress.setBounds(150, 45, 150, 70);
		getContentPane().add(spTxtAddress);

		lblErrorAddress = new JLabel();
		lblErrorAddress.setForeground(Color.RED);
		lblErrorAddress.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorAddress);

		lblZipCode = new JLabel("Kode Pos");
		lblZipCode.setBounds(25, 120, 150, 25);
		getContentPane().add(lblZipCode);

		txtZipCode = new NumberField(5);
		txtZipCode.setBounds(150, 120, 150, 25);
		getContentPane().add(txtZipCode);

		lblProvince = new JLabel("<html>Provinsi <font color=\"red\">*</font></html>");
		lblProvince.setBounds(25, 150, 150, 25);
		getContentPane().add(lblProvince);

		txtProvince = new JTextField();
		txtProvince.setBounds(150, 150, 150, 25);
		txtProvince.setDocument(new JTextFieldLimit(256));
		((AbstractDocument) txtProvince.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtProvince);

		lblErrorProvince = new JLabel();
		lblErrorProvince.setForeground(Color.RED);
		lblErrorProvince.setBounds(335, 150, 200, 25);
		getContentPane().add(lblErrorProvince);

		lblCity = new JLabel("<html>Kota <font color=\"red\">*</font></html>");
		lblCity.setBounds(25, 180, 150, 25);
		getContentPane().add(lblCity);

		txtCity = new JTextField();
		txtCity.setBounds(150, 180, 150, 25);
		txtCity.setDocument(new JTextFieldLimit(256));
		((AbstractDocument) txtCity.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtCity);

		lblErrorCity = new JLabel();
		lblErrorCity.setForeground(Color.RED);
		lblErrorCity.setBounds(335, 180, 200, 25);
		getContentPane().add(lblErrorCity);

		lblContactPerson = new JLabel("<html>Contact Person <font color=\"red\">*</font></html>");
		lblContactPerson.setBounds(25, 210, 150, 25);
		getContentPane().add(lblContactPerson);

		txtContactPerson = new JTextField();
		txtContactPerson.setBounds(150, 210, 150, 25);
		txtContactPerson.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtContactPerson.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtContactPerson);

		lblErrorContactPerson = new JLabel();
		lblErrorContactPerson.setForeground(Color.RED);
		lblErrorContactPerson.setBounds(335, 210, 225, 25);
		getContentPane().add(lblErrorContactPerson);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(25, 240, 150, 25);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(150, 240, 150, 25);
		txtEmail.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtEmail.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtEmail);

		lblErrorEmail = new JLabel();
		lblErrorEmail.setForeground(Color.RED);
		lblErrorEmail.setBounds(335, 240, 225, 25);
		getContentPane().add(lblErrorEmail);

		lblPhone = new JLabel("Telepon");
		lblPhone.setBounds(25, 270, 150, 25);
		getContentPane().add(lblPhone);

		txtPhone = new NumberField(15);
		txtPhone.setBounds(150, 270, 150, 25);
		getContentPane().add(txtPhone);

		lblFax = new JLabel("Fax");
		lblFax.setBounds(25, 300, 150, 25);
		getContentPane().add(lblFax);

		txtFax = new NumberField(15);
		txtFax.setBounds(150, 300, 150, 25);
		getContentPane().add(txtFax);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
				doInsert();
			}
		});
		btnInsert.setBounds(460, 335, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			cbAddressType.setSelectedItem(custAddress.getAddressType());
			txtAddress.setText(custAddress.getAddress());
			txtZipCode.setText(custAddress.getZipCode());
			txtPhone.setText(custAddress.getPhone());
			txtFax.setText(custAddress.getFax());
			txtCity.setText(custAddress.getCity());
			txtContactPerson.setText(custAddress.getName());
			txtEmail.setText(custAddress.getEmail());
			txtProvince.setText(custAddress.getProvince());

		}

		if (isView == true) {
			txtAddress.setEnabled(false);
			txtZipCode.setEnabled(false);
			cbAddressType.setEnabled(false);
			txtProvince.setEnabled(false);
			txtCity.setEnabled(false);
			txtContactPerson.setEnabled(false);
			txtEmail.setEnabled(false);
			txtFax.setEnabled(false);
			txtPhone.setEnabled(false);
			btnInsert.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorAddressType.setText("");
		lblErrorAddress.setText("");
		lblErrorCity.setText("");
		lblErrorContactPerson.setText("");
		lblErrorEmail.setText("");

		if (cbAddressType.getSelectedItem() == null || cbAddressType.getSelectedIndex() == 0) {
			lblErrorAddressType.setText("Combobox Tipe alamat harus dipilih.");
			isValid = false;
		}

		if (txtAddress.getText() == null || txtAddress.getText().length() == 0) {
			lblErrorAddress.setText("Textarea Alamat harus diisi.");
			isValid = false;
		}

		if (txtProvince.getText() == null || txtProvince.getText().length() == 0) {
			lblErrorProvince.setText("Textbox Province harus diisi.");
			isValid = false;
		}

		if (txtCity.getText() == null || txtCity.getText().length() == 0) {
			lblErrorCity.setText("Textbox Kota harus diisi.");
			isValid = false;
		}

		if (txtContactPerson.getText() == null || txtContactPerson.getText().length() == 0) {
			lblErrorContactPerson.setText("Textbox Contact Person harus diisi.");
			isValid = false;
		}

		if (!"".equals(txtEmail.getText())) {
			if (Boolean.FALSE.equals(new EmailValidator().validate(txtEmail.getText()))) {
				lblErrorEmail.setText("Format Email salah.");
				isValid = false;
			}
		}

		return isValid;
	}

	protected void doInsert() {
		custAddress.setAddressType(String.valueOf(cbAddressType.getSelectedItem()));
		custAddress.setAddress(txtAddress.getText());
		custAddress.setZipCode(txtZipCode.getText());
		custAddress.setProvince(txtProvince.getText());
		custAddress.setCity(txtCity.getText());

		custAddress.setPhone(txtPhone.getText());
		custAddress.setFax(txtFax.getText());

		custAddress.setName(txtContactPerson.getText());
		custAddress.setEmail(txtEmail.getText());

		try {
			if (isEdit == false) {
				if (customerCreate != null) {
					customerCreate.listOfCustAddress.add(custAddress);
				} else if (customerEdit != null) {
					customerEdit.listOfCustAddress.add(custAddress);
				}

				DialogBox.showInsert();
			} else {
				if (customerCreate != null) {
					customerCreate.listOfCustAddress.set(index, custAddress);
				} else if (customerEdit != null) {
					customerEdit.listOfCustAddress.set(index, custAddress);
				}

				DialogBox.showInsert();
			}
			closeDialog();

		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (customerCreate != null) {
			customerCreate.refreshTableCustAddress();
		}
		else if (customerEdit != null) {
			customerEdit.refreshTableCustAddress();
		}

		dispose();
	}
}
