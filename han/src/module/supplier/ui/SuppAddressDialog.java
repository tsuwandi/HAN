package module.supplier.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import module.sn.city.model.City;
import module.sn.province.model.Province;
import module.supplier.model.SuppAddress;
import module.util.EmailValidator;
import module.util.JTextFieldLimit;

public class SuppAddressDialog extends JDialog {

	private static final long serialVersionUID = 1L;

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
	ComboBox<Province> cbProvince;
	ComboBox<City> cbCity;
	NumberField txtPhone;
	NumberField txtFax;

	JButton btnInsert;

	JLabel lblErrorAddressType;
	JLabel lblErrorAddress;
	JLabel lblErrorProvince;
	JLabel lblErrorCity;

	private boolean isEdit;
	private SuppAddress suppAddress;
	private SupplierCreatePanel supplierCreate;
	private SupplierEditPanel supplierEdit;

	List<Province> listOfProvince;
	List<City> listOfCity;

	private Integer index;
	
	JLabel lblContactPerson;
	JLabel lblEmail;
	JTextField txtContactPerson;
	JTextField txtEmail;
	JLabel lblErrorContactPerson;
	JLabel lblErrorEmail;

	public SuppAddressDialog(boolean edit, SuppAddress suppAddress, SupplierCreatePanel supplierCreate, Integer index) {
		this.isEdit = edit;
		this.suppAddress = suppAddress;
		this.supplierCreate = supplierCreate;
		this.index = index;
		init();
	}

	public SuppAddressDialog(boolean edit, SuppAddress suppAddress, SupplierEditPanel supplierEdit, Integer index) {
		this.isEdit = edit;
		this.suppAddress = suppAddress;
		this.supplierEdit = supplierEdit;
		this.index = index;
		init();
	}

	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 440);
		getContentPane().setLayout(null);

		listOfProvince = new ArrayList<Province>();
		listOfCity = new ArrayList<City>();

		try {
			listOfProvince = ServiceFactory.getSupplierBL().getAllProvince();
			listOfProvince.add(0, new Province("-- Pilih Provinsi --"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		lblAddressType = new JLabel("<html>Tipe Alamat <font color=\"red\">*</font></html>");
		lblAddressType.setBounds(25, 15, 150, 30);
		getContentPane().add(lblAddressType);

		lblErrorAddressType = new JLabel();
		lblErrorAddressType.setForeground(Color.RED);
		lblErrorAddressType.setBounds(335, 15, 225, 30);
		getContentPane().add(lblErrorAddressType);

		cbAddressType = new JComboBox<String>();
		cbAddressType.addItem("-- Pilih Tipe Alamat --");
		cbAddressType.addItem("Billing");
		cbAddressType.addItem("Warehouse");
		cbAddressType.addItem("Office");
		cbAddressType.addItem("Shipping");
		cbAddressType.setBounds(150, 15, 150, 30);
		getContentPane().add(cbAddressType);

		lblAddress = new JLabel("<html>Alamat <font color=\"red\">*</font></html>");
		lblAddress.setBounds(25, 60, 150, 30);
		getContentPane().add(lblAddress);

		txtAddress = new JTextArea();
		txtAddress.setRows(3);
		txtAddress.setColumns(3);
		txtAddress.setBounds(150, 55, 150, 70);
		txtAddress.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtAddress);

		lblErrorAddress = new JLabel();
		lblErrorAddress.setForeground(Color.RED);
		lblErrorAddress.setBounds(335, 55, 225, 30);
		getContentPane().add(lblErrorAddress);

		lblZipCode = new JLabel("Kode Pos");
		lblZipCode.setBounds(25, 135, 150, 30);
		getContentPane().add(lblZipCode);

		txtZipCode = new NumberField();
		txtZipCode.setBounds(150, 135, 150, 30);
		txtZipCode.setDocument(new JTextFieldLimit(5));
		getContentPane().add(txtZipCode);

		lblProvince = new JLabel("<html>Provinsi <font color=\"red\">*</font></html>");
		lblProvince.setBounds(25, 170, 150, 30);
		getContentPane().add(lblProvince);

		cbProvince = new ComboBox<Province>();
		cbProvince.setList(listOfProvince);
		cbProvince.setBounds(150, 170, 150, 30);
		getContentPane().add(cbProvince);
		
		lblErrorProvince = new JLabel();
		lblErrorProvince.setForeground(Color.RED);
		lblErrorProvince.setBounds(335, 175, 200, 30);
		getContentPane().add(lblErrorProvince);

		lblCity = new JLabel("<html>Kota <font color=\"red\">*</font></html>");
		lblCity.setBounds(25, 205, 150, 30);
		getContentPane().add(lblCity);

		cbCity = new ComboBox<City>();
		cbCity.setBounds(150, 205, 150, 30);

		lblErrorCity = new JLabel();
		lblErrorCity.setForeground(Color.RED);
		lblErrorCity.setBounds(335, 205, 200, 30);
		getContentPane().add(lblErrorCity);

		if (cbProvince.getSelectedIndex() != 0) {
			int provinceId = cbProvince.getDataIndex().getId();
			getAllCityByProvinceId(provinceId);

			cbCity.removeAllItems();
			cbCity.setList(listOfCity);
			cbCity.updateUI();
			lblCity.setText("<html>Kota <font color=\"red\">*</font></html>");
		} else {
			cbCity.removeAllItems();
			getAllCityByProvinceId(0);
			lblCity.setText("Kota");
			lblErrorCity.setText("");
		}

		cbProvince.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbProvince.getSelectedIndex() != 0) {
					int provinceId = cbProvince.getDataIndex().getId();
					getAllCityByProvinceId(provinceId);

					cbCity.removeAllItems();
					cbCity.setList(listOfCity);
					cbCity.updateUI();
					lblCity.setText("<html>Kota <font color=\"red\">*</font></html>");
				} else {
					cbCity.removeAllItems();
					getAllCityByProvinceId(0);
					lblCity.setText("Kota");
					lblErrorCity.setText("");
				}
			}
		});

		getContentPane().add(cbCity);
		
		lblContactPerson = new JLabel("<html>Contact Person <font color=\"red\">*</font></html>");
		lblContactPerson.setBounds(25, 240, 150, 30);
		getContentPane().add(lblContactPerson);

		txtContactPerson = new JTextField();
		txtContactPerson.setBounds(150, 240, 150, 30);
		txtContactPerson.setDocument(new JTextFieldLimit(50));
		getContentPane().add(txtContactPerson);

		lblErrorContactPerson = new JLabel();
		lblErrorContactPerson.setForeground(Color.RED);
		lblErrorContactPerson.setBounds(335, 240, 225, 30);
		getContentPane().add(lblErrorContactPerson);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(25, 275, 150, 30);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(150, 275, 150, 30);
		txtEmail.setDocument(new JTextFieldLimit(50));
		getContentPane().add(txtEmail);

		lblErrorEmail = new JLabel();
		lblErrorEmail.setForeground(Color.RED);
		lblErrorEmail.setBounds(335, 275, 225, 30);
		getContentPane().add(lblErrorEmail);

		lblPhone = new JLabel("Telepon");
		lblPhone.setBounds(25, 310, 150, 30);
		getContentPane().add(lblPhone);

		txtPhone = new NumberField();
		txtPhone.setBounds(150, 310, 150, 30);
		txtPhone.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPhone);

		lblFax = new JLabel("Fax");
		lblFax.setBounds(25, 345, 150, 30);
		getContentPane().add(lblFax);

		txtFax = new NumberField();
		txtFax.setBounds(150, 345, 150, 30);
		txtFax.setDocument(new JTextFieldLimit(15));
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
		btnInsert.setBounds(460, 345, 100, 30);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			cbAddressType.setSelectedItem(suppAddress.getAddressType());
			txtAddress.setText(suppAddress.getAddress());
			txtZipCode.setText(suppAddress.getZipCode());
			txtPhone.setText(suppAddress.getPhone());
			txtFax.setText(suppAddress.getFax());
			
			txtContactPerson.setText(suppAddress.getSuppCp().getName());
			txtEmail.setText(suppAddress.getSuppCp().getEmail());
		
			if (suppAddress.getCityId() != 0) {
				getAllCityByProvinceId(suppAddress.getCity().getProvinceId());
				cbProvince.setSelectedItem(suppAddress.getCity().getProvince().getProvince());
				
				cbCity.setSelectedItem(suppAddress.getCity().getCity());
			}
		}
	}

	public void getAllCityByProvinceId(int provinceId) {
		try {
			listOfCity = ServiceFactory.getSupplierBL().getAllCityByProvinceId(provinceId);
			listOfCity.add(0, new City("-- Pilih Kota --"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorAddressType.setText("");
		lblErrorAddress.setText("");
		lblErrorCity.setText("");

		if (cbAddressType.getSelectedItem() == null || cbAddressType.getSelectedIndex() == 0) {
			lblErrorAddressType.setText("Combobox Tipe alamat harus dipilih.");
			isValid = false;
		}

		if (txtAddress.getText() == null || txtAddress.getText().length() == 0) {
			lblErrorAddress.setText("Textarea Alamat harus diisi.");
			isValid = false;
		}

		if (cbProvince.getSelectedItem() == null || cbProvince.getSelectedIndex() == 0) {
			lblErrorProvince.setText("Combobox Kota harus dipilih.");
			isValid = false;
		} 
		
		if (cbCity.getSelectedItem() == null || cbCity.getSelectedIndex() == 0) {
			lblErrorCity.setText("Combobox Kota harus dipilih.");
			isValid = false;
		}
		
		if (txtContactPerson.getText() == null || txtContactPerson.getText().length() == 0) {
			lblErrorContactPerson.setText("Textbox Contact Person harus diisi.");
			isValid = false;
		}
		
		if (Boolean.FALSE.equals(new EmailValidator().validate(txtEmail.getText()))) {
			lblErrorEmail.setText("Format Email salah.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {

		// suppAddress = new SuppAddress();
		suppAddress.setAddressType(String.valueOf(cbAddressType.getSelectedItem()));
		suppAddress.setAddress(txtAddress.getText());
		suppAddress.setZipCode(txtZipCode.getText());
		
		if (cbProvince.getSelectedIndex() != 0 && cbCity.getSelectedIndex() != 0) {
			suppAddress.setCityId(cbCity.getDataIndex().getId());
			
			Province province = new Province();
			province.setId(cbProvince.getDataIndex().getId());
			province.setProvince(cbProvince.getDataIndex().getProvince());
			
			City city = new City();
			city.setId(cbCity.getDataIndex().getId());
			city.setCity(cbCity.getDataIndex().getCity());
			city.setProvinceId(cbProvince.getDataIndex().getId());
			city.setProvince(province);
			suppAddress.setCity(city);
		}

		suppAddress.setPhone(txtPhone.getText());
		suppAddress.setFax(txtFax.getText());
		
		suppAddress.getSuppCp().setName(txtContactPerson.getText());
		suppAddress.getSuppCp().setEmail(txtEmail.getText());
		
		try {
			if (isEdit == false) {
				if (supplierCreate != null) {
					supplierCreate.listOfSuppAddress.add(suppAddress);
				}
				else if (supplierEdit != null) {
					supplierEdit.listOfSuppAddress.add(suppAddress);
				}

				DialogBox.showInsert();
			} else {
				if (supplierCreate != null) {
					supplierCreate.listOfSuppAddress.set(index, suppAddress);
				} else if (supplierEdit != null) {
					supplierEdit.listOfSuppAddress.set(index, suppAddress);
				}

				DialogBox.showInsert();
			}
			closeDialog();

		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (supplierCreate != null)
			supplierCreate.refreshTableSuppAddress();
		else if (supplierEdit != null)
			supplierEdit.refreshTableSuppAddress();

		dispose();
	}
}
