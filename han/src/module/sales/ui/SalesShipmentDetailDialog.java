package module.sales.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import module.sales.model.SalesInsuranceDetail;
import module.sales.model.ShipmentSalesOrder;
import module.sn.insurance.model.Insurance;
import module.sn.shipment.model.Shipment;

public class SalesShipmentDetailDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SalesShipmentDetailDialog.class);

	JPanel panel;

	JLabel lblShipmentAgent;
	JLabel lblShipmentType;
	JLabel lblOriginAddress;
	JLabel lblDestinationAddress;
	JLabel lblShipmentDate;
	JLabel lblPickupDate;
	JLabel lblShipmentCost;

	ComboBox<Shipment> cbShipment;
	JTextField txtShipmentType;
	JTextField txtOriginAddress;
	JTextField txtDestinationAddress;
	JDateChooser shipmentDateChooser;
	JDateChooser pickupDateChooser;
	JTextField txtShipmentCost;

	JButton btnInsert;

	JLabel lblErrorShipment;
	JLabel lblErrorOriginAddress;
	JLabel lblErrorDestinationAddress;
	JLabel lblErrorShipmentDateChooser;
	JLabel lblErrorPickupDateChooser;
	JLabel lblErrorShipmentCost;

	private boolean isEdit;
	private boolean isView;
	protected ShipmentSalesOrder salesShipmentDetail;
	private SalesShipmentDetailDialog salesShipmentDetailDialog;
	private SalesCreatePanel salesCreate;
	private SalesEditPanel salesEdit;
	private SalesViewPanel salesView;

	List<Shipment> listOfShipment;
	List<ShipmentSalesOrder> listOfSalesShipmentDetail;

	private Integer index;

	public SalesShipmentDetailDialog(boolean edit, ShipmentSalesOrder salesShipmentDetail,
			SalesCreatePanel salesCreate, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.salesShipmentDetail = salesShipmentDetail;
		this.salesCreate = salesCreate;
		this.index = index;
		init();
	}

	public SalesShipmentDetailDialog(boolean edit, ShipmentSalesOrder salesShipmentDetail, SalesEditPanel salesEdit,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.salesShipmentDetail = salesShipmentDetail;
		this.salesEdit = salesEdit;
		this.index = index;
		init();
	}

	public SalesShipmentDetailDialog(boolean view, ShipmentSalesOrder salesShipmentDetail, SalesViewPanel salesView,
			Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.salesShipmentDetail = salesShipmentDetail;
		this.salesView = salesView;
		this.index = index;
		init();
	}

	public void init() {
		salesShipmentDetailDialog = this;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 310);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblShipmentAgent = new JLabel("<html>Shipment Agent<font color=\"red\">*</font></html>");
		lblShipmentAgent.setBounds(25, 15, 150, 25);
		getContentPane().add(lblShipmentAgent);

		listOfShipment = new ArrayList<Shipment>();
		try {
			listOfShipment = ServiceFactory.getSalesBL().getAllShipment();
			listOfShipment.add(0, new Shipment("-- Pilih Shipment --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		cbShipment = new ComboBox<Shipment>();
		cbShipment.setList(listOfShipment);
		cbShipment.setBounds(200, 15, 150, 25);
		getContentPane().add(cbShipment);

		cbShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shipment selectedShipment = (Shipment) cbShipment.getSelectedItem();
				if (selectedShipment == null) {
					txtShipmentType.setText("");
				} else {
					txtShipmentType.setText(selectedShipment.getShipmentType());
				}
			}
		});

		lblErrorShipment = new JLabel();
		lblErrorShipment.setForeground(Color.RED);
		lblErrorShipment.setBounds(385, 15, 225, 25);
		getContentPane().add(lblErrorShipment);

		lblShipmentType = new JLabel("Tipe Pengiriman");
		lblShipmentType.setBounds(25, 45, 150, 25);
		getContentPane().add(lblShipmentType);

		txtShipmentType = new JTextField();
		txtShipmentType.setBounds(200, 45, 150, 25);
		txtShipmentType.setEnabled(false);
		txtShipmentType.setBackground(Color.LIGHT_GRAY);
		((AbstractDocument) txtShipmentType.getDocument()).setDocumentFilter(filter);
		Border border = BorderFactory.createLineBorder(Color.gray);
		txtShipmentType.setBorder(border);
		getContentPane().add(txtShipmentType);

		lblOriginAddress = new JLabel("<html>Origin Address<font color=\"red\">*</font></html>");
		lblOriginAddress.setBounds(25, 75, 150, 25);
		getContentPane().add(lblOriginAddress);

		txtOriginAddress = new JTextField();
		txtOriginAddress.setBounds(200, 75, 150, 25);
		getContentPane().add(txtOriginAddress);

		lblErrorOriginAddress = new JLabel();
		lblErrorOriginAddress.setForeground(Color.RED);
		lblErrorOriginAddress.setBounds(385, 75, 225, 25);
		getContentPane().add(lblErrorOriginAddress);

		lblDestinationAddress = new JLabel("<html>Destination Address<font color=\"red\">*</font></html>");
		lblDestinationAddress.setBounds(25, 105, 150, 25);
		getContentPane().add(lblDestinationAddress);

		txtDestinationAddress = new JTextField();
		txtDestinationAddress.setBounds(200, 105, 150, 25);
		getContentPane().add(txtDestinationAddress);

		lblErrorDestinationAddress = new JLabel();
		lblErrorDestinationAddress.setForeground(Color.RED);
		lblErrorDestinationAddress.setBounds(385, 105, 225, 25);
		getContentPane().add(lblErrorDestinationAddress);
		
		lblShipmentDate = new JLabel("<html>Shipment Date<font color=\"red\">*</font></html>");
		lblShipmentDate.setBounds(25, 135, 150, 25);
		getContentPane().add(lblShipmentDate);
		
		shipmentDateChooser = new JDateChooser(new Date());
		shipmentDateChooser.setBounds(200, 135, 150, 25);
		getContentPane().add(shipmentDateChooser);

		lblErrorShipmentDateChooser = new JLabel();
		lblErrorShipmentDateChooser.setForeground(Color.RED);
		lblErrorShipmentDateChooser.setBounds(385, 135, 225, 25);
		getContentPane().add(lblErrorShipmentDateChooser);
		
		lblPickupDate = new JLabel("<html>Pickup Date<font color=\"red\">*</font></html>");
		lblPickupDate.setBounds(25, 165, 150, 25);
		getContentPane().add(lblPickupDate);
		
		pickupDateChooser = new JDateChooser(new Date());
		pickupDateChooser.setBounds(200, 165, 150, 25);
		getContentPane().add(pickupDateChooser);

		lblErrorPickupDateChooser = new JLabel();
		lblErrorPickupDateChooser.setForeground(Color.RED);
		lblErrorPickupDateChooser.setBounds(385, 165, 225, 25);
		getContentPane().add(lblErrorPickupDateChooser);
		
		lblShipmentCost = new JLabel("<html>Shipment Cost<font color=\"red\">*</font></html>");
		lblShipmentCost.setBounds(25, 195, 150, 25);
		getContentPane().add(lblShipmentCost);

		txtShipmentCost = new JTextField();
		txtShipmentCost.setBounds(200, 195, 150, 25);
		getContentPane().add(txtShipmentCost);

		lblErrorShipmentCost = new JLabel();
		lblErrorShipmentCost.setForeground(Color.RED);
		lblErrorShipmentCost.setBounds(385, 195, 225, 25);
		getContentPane().add(lblErrorShipmentCost);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
				doInsert();
			}
		});
		btnInsert.setBounds(390, 225, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			cbShipment.addItem(salesShipmentDetail.getShipmentAgent());
			cbShipment.setSelectedIndex(1);
			txtShipmentType.setText(salesShipmentDetail.getShipmentType());
			txtOriginAddress.setText(salesShipmentDetail.getOriginAddress());
			txtDestinationAddress.setText(salesShipmentDetail.getDestionationAddress());
			shipmentDateChooser.setDate(salesShipmentDetail.getDateOfShipment());
			pickupDateChooser.setDate(salesShipmentDetail.getPickupDate());
			txtShipmentCost.setText(String.valueOf((salesShipmentDetail.getShipmentCost())));
		}
		if (isView == true) {
			cbShipment.setEnabled(false);
			cbShipment.setEnabled(false);
			txtShipmentType.setEnabled(false);
			txtOriginAddress.setEnabled(false);
			txtDestinationAddress.setEnabled(false);
			shipmentDateChooser.setEnabled(false);
			pickupDateChooser.setEnabled(false);
			txtShipmentCost.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorShipment.setText("");
		lblErrorOriginAddress.setText("");
		lblErrorDestinationAddress.setText("");
		lblErrorShipmentDateChooser.setText("");
		lblErrorPickupDateChooser.setText("");
		lblErrorShipmentCost.setText("");

		if (cbShipment.getSelectedIndex() == 0) {
			lblErrorShipment.setText("Combobox Shipment harus dipilih.");
			isValid = false;
		}

		if (txtOriginAddress.getText() == null || txtOriginAddress.getText().length() == 0) {
			lblErrorOriginAddress.setText("Textbox Origin Address harus diisi.");
			isValid = false;
		}
		
		if (txtDestinationAddress.getText() == null || txtDestinationAddress.getText().length() == 0) {
			lblErrorDestinationAddress.setText("Textbox Destination Address harus diisi.");
			isValid = false;
		}
		
		if (shipmentDateChooser.getDate() == null) {
			lblErrorShipmentDateChooser.setText("Textbox Shipment Date harus diisi.");
			isValid = false;
		}
		
		if (pickupDateChooser.getDate() == null) {
			lblErrorPickupDateChooser.setText("Textbox Pickup Date harus diisi.");
			isValid = false;
		}
		
		if (txtShipmentCost.getText() == null || txtShipmentCost.getText().length() == 0) {
			lblErrorShipmentCost.setText("Textbox Shipment Cost harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		Shipment selectedShipment = (Shipment) cbShipment.getSelectedItem();
		salesShipmentDetail = new ShipmentSalesOrder();
		salesShipmentDetail.setShipmentAgent(selectedShipment.getShipmentAgent());
		salesShipmentDetail.setShipmentType(txtShipmentType.getText());
		salesShipmentDetail.setOriginAddress(txtOriginAddress.getText());
		salesShipmentDetail.setDestinationAddress(txtDestinationAddress.getText());
		salesShipmentDetail.setDateOfShipment(shipmentDateChooser.getDate());
		salesShipmentDetail.setPickupDate(pickupDateChooser.getDate());
		salesShipmentDetail.setShipmentCost(Double.parseDouble(txtShipmentCost.getText()));

		try {
			if (isEdit == false) {
				if (salesCreate != null) {
					salesCreate.listOfSalesShipmentDetail.add(salesShipmentDetail);
				} else if (salesEdit != null) {
					salesEdit.listOfSalesShipmentDetail.add(salesShipmentDetail);
				}

				DialogBox.showInsert();
			} else {
				if (salesCreate != null) {
					salesCreate.listOfSalesShipmentDetail.set(index, salesShipmentDetail);
				} else if (salesEdit != null) {
					salesEdit.listOfSalesShipmentDetail.set(index, salesShipmentDetail);
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
		if (salesCreate != null) {
			salesCreate.refreshTableShipmentDetail();
		} else if (salesEdit != null) {
			salesEdit.refreshTableShipmentDetail();
		}

		dispose();
	}
}
