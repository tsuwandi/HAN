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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import module.sn.city.model.City;
import module.sn.province.model.Province;
import module.supplier.model.SuppAddress;
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
	JTextField txtZipCode;
	ComboBox<Province> cbProvince;
	ComboBox<City> cbCity;
	JTextField txtPhone;
	JTextField txtFax;

	JButton btnInsert;

	JLabel lblErrorAddressType;
	JLabel lblErrorAddress;
	JLabel lblErrorCity;

	private boolean isEdit;
	private SuppAddress suppAddress;
	private SupplierCreatePanel supplierCreate;
	private SupplierEditPanel supplierEdit;

	List<Province> listOfProvince;
	List<City> listOfCity;

	private Integer index;

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
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);

		listOfProvince = new ArrayList<Province>();
		listOfCity = new ArrayList<City>();

		try {
			listOfProvince = ServiceFactory.getSupplierBL().getAllProvince();
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

		txtZipCode = new JTextField();
		txtZipCode.setBounds(150, 135, 150, 30);
		txtZipCode.setDocument(new JTextFieldLimit(5));
		getContentPane().add(txtZipCode);

		lblProvince = new JLabel("Provinsi");
		lblProvince.setBounds(25, 170, 150, 30);
		getContentPane().add(lblProvince);

		cbProvince = new ComboBox<Province>();
		cbProvince.addItem("-- Pilih Provinsi --");
		cbProvince.setList(listOfProvince);
		cbProvince.setBounds(150, 170, 150, 30);
		getContentPane().add(cbProvince);

		lblCity = new JLabel("Kota");
		lblCity.setBounds(25, 205, 150, 30);
		getContentPane().add(lblCity);

		cbCity = new ComboBox<City>();
		cbCity.addItem("-- Pilih Kota --");
		cbCity.setBounds(150, 205, 150, 30);

		int provinceId = cbProvince.getDataIndex().getId();
		try {
			listOfCity = ServiceFactory.getSupplierBL().getAllCityByProvinceId(provinceId);
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		cbCity.removeAllItems();
		cbCity.setList(listOfCity);
		cbCity.updateUI();
		lblCity.setText("<html>Kota <font color=\"red\">*</font></html>");

		cbProvince.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if (cbProvince.getSelectedIndex() != 0) {
				int provinceId = cbProvince.getDataIndex().getId();
				try {
					listOfCity = ServiceFactory.getSupplierBL().getAllCityByProvinceId(provinceId);
				} catch (SQLException e1) {
					e1.printStackTrace();
					DialogBox.showErrorException();
				}

				cbCity.removeAllItems();
				cbCity.setList(listOfCity);
				cbCity.updateUI();
				lblCity.setText("<html>Kota <font color=\"red\">*</font></html>");
				// } else {
				// cbCity.removeAllItems();
				// lblCity.setText("Kota");
				// lblErrorCity.setText("");
				// }
			}
		});

		getContentPane().add(cbCity);

		lblErrorCity = new JLabel();
		lblErrorCity.setForeground(Color.RED);
		lblErrorCity.setBounds(335, 205, 200, 30);
		getContentPane().add(lblErrorCity);

		lblPhone = new JLabel("Telepon");
		lblPhone.setBounds(25, 240, 150, 30);
		getContentPane().add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(150, 240, 150, 30);
		txtPhone.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPhone);

		lblFax = new JLabel("Fax");
		lblFax.setBounds(25, 275, 150, 30);
		getContentPane().add(lblFax);

		txtFax = new JTextField();
		txtFax.setBounds(150, 275, 150, 30);
		txtFax.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtFax);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
//					int response = DialogBox.showInsertChoice();
//					if (response == JOptionPane.YES_OPTION) {
						doInsert();
//					}
			}
		});
		btnInsert.setBounds(460, 315, 100, 30);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			cbAddressType.setSelectedItem(suppAddress.getAddressType());
			txtAddress.setText(suppAddress.getAddress());
			txtZipCode.setText(suppAddress.getZipCode());
			txtPhone.setText(suppAddress.getPhone());
			txtFax.setText(suppAddress.getFax());
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

		if (cbProvince.getSelectedItem() == null) {
			if (cbCity.getSelectedItem() == null) {
				lblErrorCity.setText("Combobox Kota harus dipilih.");
				isValid = false;
			}
		}

		return isValid;
	}

	protected void doInsert() {

		// suppAddress = new SuppAddress();
		suppAddress.setAddressType(String.valueOf(cbAddressType.getSelectedItem()));
		suppAddress.setAddress(txtAddress.getText());
		suppAddress.setZipCode(txtZipCode.getText());

		if (cbProvince.getSelectedIndex() != 0 && cbCity.getSelectedIndex() != 0)
			suppAddress.setCityId(cbCity.getDataIndex().getId());

		suppAddress.setPhone(txtPhone.getText());
		suppAddress.setFax(txtFax.getText());

		try {
			if (isEdit == false) {
				if (supplierCreate != null)
					supplierCreate.listOfSuppAddress.add(suppAddress);
				else if (supplierEdit != null)
					supplierEdit.listOfSuppAddress.add(suppAddress);

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
