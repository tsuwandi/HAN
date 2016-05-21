package module.supplier.ui;

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
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.JTextFieldLimit;

public class SupplierAdvSearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JPanel panel;

	JLabel lblSuppName;
	JLabel lblSuppCode;
	JLabel lblSuppType;
	JLabel lblSuppStatus;
	JLabel lblPt;
	JLabel lblNpwp;

	JTextField txtSuppName;
	JTextField txtSuppCode;
	ComboBox<SuppType> cbSuppType;
	JComboBox<String> cbSuppStatus;
	JTextField txtPt;
	JTextField txtNpwp;

	JButton btnSearch;

	private SupplierListPanel supplierList;

	List<SuppType> listOfSuppType;

	public SupplierAdvSearchDialog(SupplierListPanel supplierList) {
		this.supplierList = supplierList;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		getContentPane().setLayout(null);

		lblSuppName = new JLabel("Nama Supplier");
		lblSuppName.setBounds(25, 15, 150, 30);
		getContentPane().add(lblSuppName);

		txtSuppName = new JTextField();
		txtSuppName.setBounds(120, 15, 150, 30);
		txtSuppName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtSuppName);

		lblSuppCode = new JLabel("Kode Supplier");
		lblSuppCode.setBounds(25, 50, 150, 30);
		getContentPane().add(lblSuppCode);

		txtSuppCode = new JTextField();
		txtSuppCode.setBounds(120, 50, 150, 30);
		txtSuppCode.setDocument(new JTextFieldLimit(100));
		getContentPane().add(txtSuppCode);

		lblPt = new JLabel("PT");
		lblPt.setBounds(25, 85, 150, 30);
		getContentPane().add(lblPt);

		txtPt = new JTextField();
		txtPt.setBounds(120, 85, 150, 30);
		txtPt.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtPt);

		lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(25, 120, 150, 30);
		getContentPane().add(lblNpwp);

		txtNpwp = new JTextField();
		txtNpwp.setBounds(120, 120, 150, 30);
		txtNpwp.setDocument(new JTextFieldLimit(30));
		getContentPane().add(txtNpwp);

		lblSuppType = new JLabel("Tipe Supplier");
		lblSuppType.setBounds(305, 15, 150, 30);
		getContentPane().add(lblSuppType);

		listOfSuppType = new ArrayList<SuppType>();
		try {
			listOfSuppType = ServiceFactory.getSupplierBL().getAllSuppType();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
		cbSuppType = new ComboBox<SuppType>();
		cbSuppType.addItem("-- Pilih Tipe Supplier --");
		cbSuppType.setList(listOfSuppType);
		cbSuppType.setBounds(410, 15, 150, 30);
		getContentPane().add(cbSuppType);

		lblSuppStatus = new JLabel("Status Supplier ");
		lblSuppStatus.setBounds(305, 50, 150, 30);
		getContentPane().add(lblSuppStatus);

		cbSuppStatus = new JComboBox<String>();
		cbSuppStatus.addItem("-- Pilih Status Supplier --");
		cbSuppStatus.addItem("Aktif");
		cbSuppStatus.addItem("Nonaktif Sementara");
		cbSuppStatus.addItem("Nonaktif");
		cbSuppStatus.setBounds(410, 50, 150, 30);
		getContentPane().add(cbSuppStatus);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdvancedSearch();
			}
		});
		btnSearch.setBounds(459, 165, 100, 30);
		getContentPane().add(btnSearch);
	}

	public void doAdvancedSearch() {
		try {
			Supplier supplier = new Supplier();
			supplier.setSuppCode(txtSuppCode.getText());
			supplier.setSuppName(txtSuppName.getText());
			supplier.setPt(txtPt.getText());
			supplier.setNpwp(txtNpwp.getText());
			SuppType suppType = new SuppType();
			if (cbSuppType.getSelectedIndex() != 0) {
				suppType.setSuppType(cbSuppType.getSelectedItem().toString());
				supplier.setSuppType(suppType);
			}
			if (cbSuppStatus.getSelectedIndex() != 0)
				supplier.setSuppStatus(cbSuppStatus.getSelectedItem().toString());

			supplierList.listOfSupplier = ServiceFactory.getSupplierBL().getAllSupplierByAdvancedSearch(supplier);
			closeDialog();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

	}

	protected void closeDialog() {
		supplierList.refreshTableSupplier();

		dispose();
	}
}
