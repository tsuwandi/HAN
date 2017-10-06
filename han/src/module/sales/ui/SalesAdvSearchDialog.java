package module.sales.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.customer.model.Customer;
import module.sales.model.Sales;
import module.util.JTextFieldLimit;

public class SalesAdvSearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(SalesAdvSearchDialog.class);

	JPanel panel;

	JLabel lblSoNo;
	JLabel lblSoDate;
	JLabel lblCustomerName;

	JTextField txtSoNo;
	JDateChooser soDateChooser;
	JTextField txtCustomerName;

	JButton btnSearch;

	private SalesListPanel salesList;

	public SalesAdvSearchDialog(SalesListPanel salesList) {
		this.salesList = salesList;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 210);
		getContentPane().setLayout(null);
		
		lblSoDate = new JLabel("Tanggal SO");
		lblSoDate.setBounds(25, 45, 150, 25);
		getContentPane().add(lblSoDate);
		
		soDateChooser = new JDateChooser();
		soDateChooser.setBounds(120, 45, 150, 25);
		soDateChooser.setDateFormatString("dd-MM-yyyy");
		getContentPane().add(soDateChooser);

		lblSoNo = new JLabel("Nomor SO");
		lblSoNo.setBounds(25, 15, 150, 25);
		getContentPane().add(lblSoNo);

		txtSoNo = new JTextField();
		txtSoNo.setBounds(120, 15, 150, 25);
		txtSoNo.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtSoNo);

		lblCustomerName = new JLabel("Nama Customer");
		lblCustomerName.setBounds(25, 75, 150, 25);
		getContentPane().add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(120, 75, 150, 25);
		txtCustomerName.setDocument(new JTextFieldLimit(200));
		getContentPane().add(txtCustomerName);

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
			Sales sales = new Sales();
			Customer customer = new Customer();
			sales.setCustomer(customer);
			sales.setSoDate(soDateChooser.getDate());
			sales.setSoNo(txtSoNo.getText());
			sales.getCustomer().setCustName(txtCustomerName.getText());
			salesList.listOfSales = ServiceFactory.getSalesBL().getAllSalesByAdvancedSearch(sales);
			closeDialog();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

	}

	protected void closeDialog() {
		salesList.refreshTableSales();

		dispose();
	}
}
