package module.supplier.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import module.sn.vehicletype.model.VehicleType;
import module.supplier.model.SuppVehicle;
import module.util.JTextFieldLimit;

public class SuppVehicleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(SuppVehicleDialog.class);

	JPanel panel;

	JLabel lblLicensePlate;
	JLabel lblVehicleType;

	JTextField txtLicensePlate;
	ComboBox<VehicleType> cbVehicleType;

	JButton btnInsert;

	JLabel lblErrorLicensePlate;
	JLabel lblErrorVehicleType;

	private boolean isEdit;
	private SuppVehicle suppVehicle;
	private SupplierCreatePanel supplierCreate;
	private SupplierEditPanel supplierEdit;

	List<VehicleType> listOfVehicleType;

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
		setBounds(100, 100, 600, 170);
		getContentPane().setLayout(null);
		
		DocumentFilter filter = new UppercaseDocumentFilter();

		lblLicensePlate = new JLabel("<html>No Kendaraan <font color=\"red\">*</font></html>");
		lblLicensePlate.setBounds(25, 15, 150, 25);
		getContentPane().add(lblLicensePlate);

		txtLicensePlate = new JTextField();
		if (isEdit == true)
			txtLicensePlate.setEnabled(false);
		txtLicensePlate.setBounds(150, 15, 150, 25);
		txtLicensePlate.setDocument(new JTextFieldLimit(10));
		((AbstractDocument) txtLicensePlate.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtLicensePlate);

		lblErrorLicensePlate = new JLabel();
		lblErrorLicensePlate.setForeground(Color.RED);
		lblErrorLicensePlate.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorLicensePlate);

		lblVehicleType = new JLabel("<html>Tipe Kendaraan <font color=\"red\">*</font></html>");
		lblVehicleType.setBounds(25, 45, 150, 25);
		getContentPane().add(lblVehicleType);

		listOfVehicleType = new ArrayList<VehicleType>();
		try {
			listOfVehicleType = ServiceFactory.getSupplierBL().getAllVehicleType();
			listOfVehicleType.add(0, new VehicleType("-- Pilih Tipe Kendaraan --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbVehicleType = new ComboBox<VehicleType>();
		cbVehicleType.setBounds(150, 45, 150, 25);
		cbVehicleType.setList(listOfVehicleType);
		getContentPane().add(cbVehicleType);

		lblErrorVehicleType = new JLabel();
		lblErrorVehicleType.setForeground(Color.RED);
		lblErrorVehicleType.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorVehicleType);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
				
				doInsert();
			}
		});
		btnInsert.setBounds(460, 80, 100, 25);
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
				if (ServiceFactory.getSupplierBL().isLicensePlateExists(txtLicensePlate.getText()) > 0
						&& isEdit == false) {
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
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		return isValid;
	}

	protected void doInsert() {

		suppVehicle.setLicensePlate(txtLicensePlate.getText());
		suppVehicle.setVehicleTypeId(cbVehicleType.getDataIndex().getId());
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

				DialogBox.showInsert();
			} else {
				if (supplierCreate != null) {
					supplierCreate.listOfSuppVehicle.set(index, suppVehicle);
				} else if (supplierEdit != null) {
					supplierEdit.listOfSuppVehicle.set(index, suppVehicle);
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
		if (supplierCreate != null)
			supplierCreate.refreshTableSuppVehicle();
		else if (supplierEdit != null)
			supplierEdit.refreshTableSuppVehicle();

		dispose();
	}
}
