package module.supplier.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import module.sn.vehicletype.model.VehicleType;
import module.supplier.model.SuppVehicle;
import module.util.JTextFieldLimit;

public class SuppVehicleDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JPanel panel;

	JLabel lblLicensePlate;
	JLabel lblVehicleType;

	JTextField txtLicensePlate;
	JComboBox<String> cbVehicleType;

	JButton btnInsert;

	JLabel lblErrorLicensePlate;
	JLabel lblErrorVehicleType;

	private boolean isEdit;
	private SuppVehicle suppVehicle;
	private SupplierCreatePanel supplierCreate;
	private SupplierEditPanel supplierEdit;

	HashMap<String, Integer> mapVehicleType;

	private Integer index;

	public SuppVehicleDialog(boolean edit, SuppVehicle suppVehicle, SupplierEditPanel supplierEdit, Integer index) {
		this.isEdit = edit;
		this.suppVehicle = suppVehicle;
		this.supplierEdit = supplierEdit;
		this.index = index;
		init();
	}

	public SuppVehicleDialog(boolean edit, SuppVehicle suppVehicle, SupplierCreatePanel supplierCreate, Integer index) {
		this.isEdit = edit;
		this.suppVehicle = suppVehicle;
		this.supplierCreate = supplierCreate;
		this.index = index;
		init();
	}

	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 180);
		getContentPane().setLayout(null);

		lblLicensePlate = new JLabel("<html>No Kendaraan <font color=\"red\">*</font></html>");
		lblLicensePlate.setBounds(25, 15, 150, 30);
		getContentPane().add(lblLicensePlate);

		txtLicensePlate = new JTextField();
		if (isEdit == true)
			txtLicensePlate.setEnabled(false);
		txtLicensePlate.setBounds(150, 15, 150, 30);
		txtLicensePlate.setDocument(new JTextFieldLimit(10));
		getContentPane().add(txtLicensePlate);

		lblErrorLicensePlate = new JLabel();
		lblErrorLicensePlate.setForeground(Color.RED);
		lblErrorLicensePlate.setBounds(335, 15, 225, 30);
		getContentPane().add(lblErrorLicensePlate);

		lblVehicleType = new JLabel("<html>Tipe Kendaraan <font color=\"red\">*</font></html>");
		lblVehicleType.setBounds(25, 50, 150, 30);
		getContentPane().add(lblVehicleType);

		mapVehicleType = new HashMap<String, Integer>();
		try {
			mapVehicleType = ServiceFactory.getSupplierBL().getAllVehicleType();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		cbVehicleType = new JComboBox<String>();
		cbVehicleType.addItem("-- Pilih Tipe Kendaraan --");
		cbVehicleType.setBounds(150, 50, 150, 30);
		for (String s : mapVehicleType.keySet()) {
			cbVehicleType.addItem(s);
		}
		getContentPane().add(cbVehicleType);

		lblErrorVehicleType = new JLabel();
		lblErrorVehicleType.setForeground(Color.RED);
		lblErrorVehicleType.setBounds(335, 50, 225, 30);
		getContentPane().add(lblErrorVehicleType);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsert();
			}
		});
		btnInsert.setBounds(460, 95, 100, 30);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtLicensePlate.setText(suppVehicle.getLicensePlate());
			cbVehicleType.setSelectedItem(suppVehicle.getVehicleType().getVehicleType());
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorLicensePlate.setText("");
		lblErrorVehicleType.setText("");

		if (txtLicensePlate.getText() == null || txtLicensePlate.getText().length() == 0) {
			lblErrorLicensePlate.setText("Textbox No Kendaraan harus diisi.");
			isValid = false;
		}

		if (cbVehicleType.getSelectedItem() == null || cbVehicleType.getSelectedIndex() == 0) {
			lblErrorVehicleType.setText("Combobox Tipe Kendaraan harus dipilih.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getSupplierBL().isLicensePlateExists(txtLicensePlate.getText()) > 0 && isEdit == false) {
					lblErrorLicensePlate.setText("No Kendaraan sudah pernah diinput.");
					isValid = false;
				}

				if (supplierCreate != null && isEdit == false) {
					for (SuppVehicle s : supplierCreate.listOfSuppVehicle) {
						if (txtLicensePlate.getText().equals(s.getLicensePlate())) {
							lblErrorLicensePlate.setText("No Kendaraan sudah pernah diinput.");
							isValid = false;
						}
					}
				} else if (supplierEdit != null && isEdit == false) {
					for (SuppVehicle s : supplierEdit.listOfSuppVehicle) {
						if (txtLicensePlate.getText().equals(s.getLicensePlate())) {
							lblErrorLicensePlate.setText("No Kendaraan sudah pernah diinput.");
							isValid = false;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No Kendaraan gagal diinput.", "Error", JOptionPane.ERROR_MESSAGE);
				isValid = false;
			}
		}

		return isValid;
	}

	protected void doInsert() {
		if (doValidate() == true) {
			//suppVehicle = new SuppVehicle();
			suppVehicle.setLicensePlate(txtLicensePlate.getText());
			suppVehicle.setVehicleTypeId(mapVehicleType.get(cbVehicleType.getSelectedItem().toString()));
			// add object vehicle type
			VehicleType vehicleType = new VehicleType();
			vehicleType.setId(suppVehicle.getVehicleTypeId());
			vehicleType.setVehicleType(cbVehicleType.getSelectedItem().toString());
			suppVehicle.setVehicleType(vehicleType);
			try {
				if (isEdit == false) {
					if (supplierCreate != null) {
						supplierCreate.listOfSuppVehicle.add(suppVehicle);
					} else if (supplierEdit != null) {
						supplierEdit.listOfSuppVehicle.add(suppVehicle);
					}
					
					JOptionPane.showMessageDialog(null, "Data berhasil ditambah", "Informasi",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (supplierCreate != null) {
						supplierCreate.listOfSuppVehicle.set(index, suppVehicle);
					} else if (supplierEdit != null) {
						supplierEdit.listOfSuppVehicle.set(index, suppVehicle);
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
			supplierCreate.refreshTableSuppVehicle();
		else if (supplierEdit != null)
			supplierEdit.refreshTableSuppVehicle();

		dispose();
	}
}
