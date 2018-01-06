package module.sales.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.sales.model.SalesInsuranceDetail;
import module.sn.insurance.model.Insurance;

public class SalesInsuranceDetailDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SalesInsuranceDetailDialog.class);

	JPanel panel;

	JLabel lblInsuranceCompanyName;
	JLabel lblInsuranceType;
	JLabel lblInsuranceCost;

	ComboBox<Insurance> cbInsurance;
	JTextField txtInsuranceType;
	JTextField txtInsuranceCost;

	JButton btnInsert;

	JLabel lblErrorInsuranceCompanyName;
	JLabel lblErrorInsuranceCost;

	private boolean isEdit;
	private boolean isView;
	protected SalesInsuranceDetail salesInsuranceDetail;
	private SalesInsuranceDetailDialog salesInsuranceDetailDialog;
	private SalesCreatePanel salesCreate;
	private SalesEditPanel salesEdit;
	private SalesViewPanel salesView;

	List<Insurance> listOfInsurance;
	List<SalesInsuranceDetail> listOfSalesInsuranceDetail;
	
	private Integer index;

	public SalesInsuranceDetailDialog(boolean edit, SalesInsuranceDetail salesInsuranceDetail, SalesCreatePanel salesCreate,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.salesInsuranceDetail = salesInsuranceDetail;
		this.salesCreate = salesCreate;
		this.index = index;
		init();
	}

	public SalesInsuranceDetailDialog(boolean edit, SalesInsuranceDetail salesInsuranceDetail, SalesEditPanel salesEdit, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.salesInsuranceDetail = salesInsuranceDetail;
		this.salesEdit = salesEdit;
		this.index = index;
		init();
	}

	public SalesInsuranceDetailDialog(boolean view, SalesInsuranceDetail salesInsuranceDetail, SalesViewPanel salesView, Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.salesInsuranceDetail = salesInsuranceDetail;
		this.salesView = salesView;
		this.index = index;
		init();
	}

	public void init() {
		salesInsuranceDetailDialog = this;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 210);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblInsuranceCompanyName = new JLabel("<html>Nama Perusahaan Asuransi <font color=\"red\">*</font></html>");
		lblInsuranceCompanyName.setBounds(25, 15, 150, 25);
		getContentPane().add(lblInsuranceCompanyName);
		
		listOfInsurance = new ArrayList<Insurance>();
		try {
			listOfInsurance = ServiceFactory.getSalesBL().getAllInsurance();
			listOfInsurance.add(0, new Insurance("-- Pilih Kurs --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		cbInsurance = new ComboBox<Insurance>();
		cbInsurance.setList(listOfInsurance);
		cbInsurance.setBounds(200, 15, 150, 25);
		getContentPane().add(cbInsurance);
		
		cbInsurance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insurance selectedInsurance = (Insurance) cbInsurance.getSelectedItem();
				if (selectedInsurance == null) {
					txtInsuranceType.setText("");
				} else {
					txtInsuranceType.setText(selectedInsurance.getInsuranceType());
				}
			}
		});

		lblErrorInsuranceCompanyName = new JLabel();
		lblErrorInsuranceCompanyName.setForeground(Color.RED);
		lblErrorInsuranceCompanyName.setBounds(385, 15, 225, 25);
		getContentPane().add(lblErrorInsuranceCompanyName);

		lblInsuranceType = new JLabel("Tipe Perusahaan Asuransi");
		lblInsuranceType.setBounds(25, 45, 150, 25);
		getContentPane().add(lblInsuranceType);
		
		txtInsuranceType = new JTextField();
		txtInsuranceType.setBounds(200, 45, 150, 25);
		txtInsuranceType.setEnabled(false);
		txtInsuranceType.setBackground(Color.LIGHT_GRAY);
		((AbstractDocument) txtInsuranceType.getDocument()).setDocumentFilter(filter);
		Border border = BorderFactory.createLineBorder(Color.gray);
		txtInsuranceType.setBorder(border);
		getContentPane().add(txtInsuranceType);

		lblInsuranceCost = new JLabel("<html>Biaya Asuransi <font color=\"red\">*</font></html>");
		lblInsuranceCost.setBounds(25, 75, 150, 25);
		getContentPane().add(lblInsuranceCost);

		txtInsuranceCost = new JTextField();
		txtInsuranceCost.setBounds(200, 75, 150, 25);
		getContentPane().add(txtInsuranceCost);
		
		lblErrorInsuranceCost = new JLabel();
		lblErrorInsuranceCost.setForeground(Color.RED);
		lblErrorInsuranceCost.setBounds(385, 75, 225, 25);
		getContentPane().add(lblErrorInsuranceCost);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
				doInsert();
			}
		});
		btnInsert.setBounds(390, 125, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			cbInsurance.addItem(salesInsuranceDetail.getInsurance().getInsuranceCompanyName());
			cbInsurance.setSelectedIndex(1);
			txtInsuranceType.setText(salesInsuranceDetail.getInsurance().getInsuranceType());
			txtInsuranceCost.setText(String.valueOf((salesInsuranceDetail.getCost())));
		}
		if (isView == true) {
			cbInsurance.setEnabled(false);
			txtInsuranceCost.setEnabled(false);
			txtInsuranceType.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorInsuranceCompanyName.setText("");
		lblErrorInsuranceCost.setText("");
		
		if (cbInsurance.getSelectedIndex() == 0) {
			lblErrorInsuranceCompanyName.setText("Combobox Insurance harus dipilih.");
			isValid = false;
		}

		if (txtInsuranceType.getText() == null || txtInsuranceType.getText().length() == 0) {
			lblErrorInsuranceCost.setText("Textbox Biaya Asuransi harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		Insurance selectedInsurance = (Insurance) cbInsurance.getSelectedItem();
		System.out.println(selectedInsurance.getInsuranceId());
		salesInsuranceDetail = new SalesInsuranceDetail();
		salesInsuranceDetail.setInsuranceId(selectedInsurance.getInsuranceId());
		salesInsuranceDetail.getInsurance().setInsuranceCompanyName(selectedInsurance.getInsuranceCompanyName());
		salesInsuranceDetail.getInsurance().setInsuranceType(txtInsuranceType.getText());
		salesInsuranceDetail.setCost(Double.parseDouble(txtInsuranceCost.getText()));

		try {
			if (isEdit == false) {
				if (salesCreate != null) {
					salesCreate.listOfSalesInsuranceDetail.add(salesInsuranceDetail);
				} else if (salesEdit != null) {
					salesEdit.listOfSalesInsuranceDetail.add(salesInsuranceDetail);
				}

				DialogBox.showInsert();
			} else {
				if (salesCreate != null) {
					salesCreate.listOfSalesInsuranceDetail.set(index, salesInsuranceDetail);
				} else if (salesEdit != null) {
					salesEdit.listOfSalesInsuranceDetail.set(index, salesInsuranceDetail);
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
			salesCreate.refreshTableInsuranceDetail();
		} else if (salesEdit != null) {
			salesEdit.refreshTableInsuranceDetail();
		}

		dispose();
	}
}
