package module.supplier.ui;

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

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;
import module.util.JTextFieldLimit;

public class SupplierAdvSearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(SupplierAdvSearchDialog.class);

	JPanel panel;

	JLabel lblSuppName;
	JLabel lblSuppCode;
	JLabel lblSuppType;
	JLabel lblPt;
	JLabel lblNpwp;

	JTextField txtSuppName;
	JTextField txtSuppCode;
	ComboBox<SuppType> cbSuppType;
	JTextField txtPt;
	JTextField txtNpwp;

	JButton btnSearch;

	private SupplierListPanel supplierList;

	List<SuppType> listOfSuppType;

	public SupplierAdvSearchDialog(SupplierListPanel supplierList) {
		this.supplierList = supplierList;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 210);
		getContentPane().setLayout(null);

		lblSuppName = new JLabel("Nama Supplier");
		lblSuppName.setBounds(25, 15, 150, 25);
		getContentPane().add(lblSuppName);

		txtSuppName = new JTextField();
		txtSuppName.setBounds(120, 15, 150, 25);
		txtSuppName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtSuppName);

		lblSuppCode = new JLabel("Kode Supplier");
		lblSuppCode.setBounds(25, 45, 150, 25);
		getContentPane().add(lblSuppCode);

		txtSuppCode = new JTextField();
		txtSuppCode.setBounds(120, 45, 150, 25);
		txtSuppCode.setDocument(new JTextFieldLimit(100));
		getContentPane().add(txtSuppCode);

		lblPt = new JLabel("PT");
		lblPt.setBounds(25, 75, 150, 25);
		getContentPane().add(lblPt);

		txtPt = new JTextField();
		txtPt.setBounds(120, 75, 150, 25);
		txtPt.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtPt);

		lblNpwp = new JLabel("NPWP");
		lblNpwp.setBounds(25, 105, 150, 25);
		getContentPane().add(lblNpwp);

		txtNpwp = new JTextField();
		txtNpwp.setBounds(120, 105, 150, 25);
		txtNpwp.setDocument(new JTextFieldLimit(30));
		getContentPane().add(txtNpwp);

		lblSuppType = new JLabel("Tipe Supplier");
		lblSuppType.setBounds(305, 15, 150, 25);
		getContentPane().add(lblSuppType);

		listOfSuppType = new ArrayList<SuppType>();
		try {
			listOfSuppType = ServiceFactory.getSupplierBL().getAllSuppType();
			listOfSuppType.add(0, new SuppType("-- Pilih Tipe Supplier --"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
		cbSuppType = new ComboBox<SuppType>();
		cbSuppType.setList(listOfSuppType);
		cbSuppType.setBounds(410, 15, 150, 25);
		getContentPane().add(cbSuppType);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdvancedSearch();
			}
		});
		btnSearch.setBounds(459, 130, 100, 25);
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
			supplierList.listOfSupplier = ServiceFactory.getSupplierBL().getAllSupplierByAdvancedSearch(supplier);
			closeDialog();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}

	protected void closeDialog() {
		supplierList.refreshTableSupplier();

		dispose();
	}
}
