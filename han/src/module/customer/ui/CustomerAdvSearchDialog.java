package module.customer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.customer.model.Customer;
import module.util.JTextFieldLimit;

public class CustomerAdvSearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(CustomerAdvSearchDialog.class);

	JPanel panel;

	JLabel lblCustName;
	JLabel lblCustCode;
	JLabel lblPt;

	JTextField txtCustName;
	JTextField txtCustCode;
	JTextField txtPt;

	JButton btnSearch;

	private CustomerListPanel customerList;

	public CustomerAdvSearchDialog(CustomerListPanel customerList) {
		this.customerList = customerList;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 210);
		getContentPane().setLayout(null);
		
		lblCustCode = new JLabel("Kode Customer");
		lblCustCode.setBounds(25, 15, 150, 25);
		getContentPane().add(lblCustCode);

		txtCustCode = new JTextField();
		txtCustCode.setBounds(120, 15, 150, 25);
		txtCustCode.setDocument(new JTextFieldLimit(100));
		getContentPane().add(txtCustCode);

		lblCustName = new JLabel("Nama Customer");
		lblCustName.setBounds(25, 45, 150, 25);
		getContentPane().add(lblCustName);

		txtCustName = new JTextField();
		txtCustName.setBounds(120, 45, 150, 25);
		txtCustName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtCustName);

		lblPt = new JLabel("PT");
		lblPt.setBounds(25, 75, 150, 25);
		getContentPane().add(lblPt);

		txtPt = new JTextField();
		txtPt.setBounds(120, 75, 150, 25);
		txtPt.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtPt);

		btnSearch = new JButton("Cari");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdvancedSearch();
			}
		});
		btnSearch.setBounds(170, 130, 100, 25);
		getContentPane().add(btnSearch);
	}

	public void doAdvancedSearch() {
		try {
			Customer customer = new Customer();
			customer.setCustCode(txtCustCode.getText());
			customer.setCustName(txtCustName.getText());
			customer.setPt(txtPt.getText());
			customerList.listOfCustomer = ServiceFactory.getCustomerBL().getAllCustomerByAdvancedSearch(customer);
			closeDialog();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}

	protected void closeDialog() {
		customerList.refreshTableCustomer();

		dispose();
	}
}
