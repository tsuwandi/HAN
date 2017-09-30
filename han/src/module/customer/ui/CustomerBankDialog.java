package module.customer.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.customer.model.CustBank;
import module.sn.currency.model.Currency;
import module.util.JTextFieldLimit;

public class CustomerBankDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CustomerBankDialog.class);

	JPanel panel;

	JLabel lblSwiftCode;
	JLabel lblNote;
	JLabel lblBankName;
	JLabel lblAccountNumber;
	JLabel lblAccountName;
	JLabel lblCurrency;

	ComboBox<Currency> cbCurrency;
	JTextArea txtNote;
	JTextField txtSwiftCode;
	JTextField txtBankName;
	JTextField txtAccountName;
	NumberField txtAccountNumber;

	JButton btnInsert;

	JLabel lblErrorSwiftCode;
	JLabel lblErrorNote;
	JLabel lblErrorBankName;
	JLabel lblErrorAccountNumber;
	JLabel lblErrorAccountName;
	JLabel lblErrorCurrency;

	private boolean isEdit;
	private boolean isView;
	private CustBank custBank;
	private CustomerCreatePanel customerCreate;
	private CustomerEditPanel customerEdit;
	private CustomerViewPanel customerView;

	private Integer index;
	
	List<Currency> listOfCurrency;

	public CustomerBankDialog(boolean edit, CustBank custBank, CustomerCreatePanel customerCreate, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.custBank = custBank;
		this.customerCreate = customerCreate;
		this.index = index;
		init();
	}

	public CustomerBankDialog(boolean edit, CustBank custBank, CustomerEditPanel customerEdit, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.custBank = custBank;
		this.customerEdit = customerEdit;
		this.index = index;
		init();
	}

	public CustomerBankDialog(boolean view, CustBank custBank, CustomerViewPanel customerView, Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.custBank = custBank;
		this.customerView = customerView;
		this.index = index;
		init();
	}

	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 360);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblSwiftCode = new JLabel("<html>Kode Swift <font color=\"red\">*</font></html>");
		lblSwiftCode.setBounds(25, 15, 150, 25);
		getContentPane().add(lblSwiftCode);

		lblErrorSwiftCode = new JLabel();
		lblErrorSwiftCode.setForeground(Color.RED);
		lblErrorSwiftCode.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorSwiftCode);

		txtSwiftCode = new NumberField(5);
		txtSwiftCode.setBounds(150, 15, 150, 25);
		((AbstractDocument) txtSwiftCode.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtSwiftCode);

		lblNote = new JLabel("<html>Note <font color=\"red\">*</font></html>");
		lblNote.setBounds(25, 45, 150, 25);
		getContentPane().add(lblNote);

		txtNote = new JTextArea();
		txtNote.setRows(3);
		txtNote.setColumns(3);
		txtNote.setBounds(150, 45, 150, 70);
		txtNote.setDocument(new JTextFieldLimit(200));
		((AbstractDocument) txtNote.getDocument()).setDocumentFilter(filter);
		txtNote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (e.getModifiers() > 0) {
						txtNote.transferFocusBackward();
					} else {
						txtNote.transferFocus();
					}
					e.consume();
				}
			}
		});

		JScrollPane spTxtNote = new JScrollPane(txtNote);
		spTxtNote.setBounds(150, 45, 150, 70);
		getContentPane().add(spTxtNote);

		lblErrorNote = new JLabel();
		lblErrorNote.setForeground(Color.RED);
		lblErrorNote.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorNote);

		lblBankName = new JLabel("<html>Nama Bank <font color=\"red\">*</font></html>");
		lblBankName.setBounds(25, 120, 150, 25);
		getContentPane().add(lblBankName);

		txtBankName = new NumberField(5);
		txtBankName.setBounds(150, 120, 150, 25);
		((AbstractDocument) txtBankName.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtBankName);

		lblErrorBankName = new JLabel();
		lblErrorBankName.setForeground(Color.RED);
		lblErrorBankName.setBounds(335, 120, 225, 25);
		getContentPane().add(lblErrorBankName);

		lblAccountNumber = new JLabel("<html>Nomor Akun <font color=\"red\">*</font></html>");
		lblAccountNumber.setBounds(25, 150, 150, 25);
		getContentPane().add(lblAccountNumber);

		txtAccountNumber = new NumberField(30);
		txtAccountNumber.setBounds(150, 150, 150, 25);
		getContentPane().add(txtAccountNumber);

		lblErrorAccountNumber = new JLabel();
		lblErrorAccountNumber.setForeground(Color.RED);
		lblErrorAccountNumber.setBounds(335, 150, 200, 25);
		getContentPane().add(lblErrorAccountNumber);

		lblAccountName = new JLabel("<html>Nama Akun <font color=\"red\">*</font></html>");
		lblAccountName.setBounds(25, 180, 150, 25);
		getContentPane().add(lblAccountName);

		txtAccountName = new JTextField();
		txtAccountName.setBounds(150, 180, 150, 25);
		txtAccountName.setDocument(new JTextFieldLimit(256));
		((AbstractDocument) txtAccountName.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtAccountName);

		lblErrorAccountName = new JLabel();
		lblErrorAccountName.setForeground(Color.RED);
		lblErrorAccountName.setBounds(335, 180, 200, 25);
		getContentPane().add(lblErrorAccountName);

		lblCurrency = new JLabel("<html>Kurs <font color=\"red\">*</font></html>");
		lblCurrency.setBounds(25, 210, 150, 25);
		getContentPane().add(lblCurrency);

		listOfCurrency = new ArrayList<Currency>();
		try {
			listOfCurrency = ServiceFactory.getCustomerBL().getAllCurrency();
			listOfCurrency.add(0, new Currency("-- Pilih Kurs --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		cbCurrency = new ComboBox<Currency>();
		cbCurrency.setList(listOfCurrency);

		// default IDR

		int i = 0;
		for (Currency currency : listOfCurrency) {
			if (AppConstants.CURRENCY_IDR.equals(currency.getCurrencyAbbr())) {
				cbCurrency.setSelectedIndex(i);
				break;
			}
			i++;
		}

		cbCurrency.setBounds(150, 210, 150, 25);
		getContentPane().add(cbCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(335, 210, 225, 25);
		getContentPane().add(lblErrorCurrency);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
				doInsert();
			}
		});
		btnInsert.setBounds(460, 275, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			cbCurrency.setSelectedIndex(custBank.getCurrencyId());
			txtNote.setText(custBank.getNote());
			txtAccountNumber.setText(custBank.getAccountNo());
			txtAccountName.setText(custBank.getAccountName());
			txtBankName.setText(custBank.getBankName());
			txtSwiftCode.setText(custBank.getSwiftCode());

		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorNote.setText("");
		lblErrorBankName.setText("");
		lblErrorAccountNumber.setText("");
		lblErrorAccountName.setText("");
		lblErrorSwiftCode.setText("");
		lblErrorCurrency.setText("");
		
		if (cbCurrency.getSelectedItem() == null || cbCurrency.getSelectedIndex() == 0) {
			lblErrorCurrency.setText("Combobox Tipe kurs harus dipilih.");
			isValid = false;
		}

		if (txtNote.getText() == null || txtNote.getText().length() == 0) {
			lblErrorNote.setText("Textarea Note harus diisi.");
			isValid = false;
		}
		
		if (txtBankName.getText() == null || txtBankName.getText().length() == 0) {
			lblErrorBankName.setText("Textbox Nama Bank harus diisi.");
			isValid = false;
		}
		
		if (txtAccountName.getText() == null || txtAccountName.getText().length() == 0) {
			lblErrorAccountName.setText("Textbox Nama Account harus diisi.");
			isValid = false;
		}
		
		if (txtAccountNumber.getText() == null || txtAccountNumber.getText().length() == 0) {
			lblErrorAccountNumber.setText("Textbox Nomor Account harus diisi.");
			isValid = false;
		}
		
		if (txtSwiftCode.getText() == null || txtSwiftCode.getText().length() == 0) {
			lblErrorSwiftCode.setText("Textbox Kode Swift harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		custBank.setCurrencyId(cbCurrency.getDataIndex().getId());
		custBank.setNote(txtNote.getText());
		custBank.setBankName(txtBankName.getText());
		custBank.setAccountName(txtAccountName.getText());
		custBank.setAccountNo(txtAccountNumber.getText());
		custBank.setSwiftCode(txtSwiftCode.getText());

		try {
			if (isEdit == false) {
				if (customerCreate != null) {
					customerCreate.listOfCustBank.add(custBank);
				} else if (customerEdit != null) {
					customerEdit.listOfCustBank.add(custBank);
				}

				DialogBox.showInsert();
			} else {
				if (customerCreate != null) {
					customerCreate.listOfCustBank.set(index, custBank);
				} else if (customerEdit != null) {
					customerEdit.listOfCustBank.set(index, custBank);
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
		if (customerCreate != null) {
			customerCreate.refreshTableCustBank();
		} else if (customerEdit != null) {
			customerEdit.refreshTableCustBank();
		}

		dispose();
	}
}
