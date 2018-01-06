package module.sales.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.customer.model.CustAddress;
import module.customer.model.CustType;
import module.customer.model.Customer;
import module.sales.model.Sales;
import module.sales.model.SalesDetail;
import module.sales.model.SalesInsuranceDetail;
import module.sales.model.ShipmentSalesOrder;
import module.sn.bank.model.BankCust;
import module.sn.currency.model.Currency;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class SalesCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SalesCreatePanel.class);

	private Sales sales;
	public List<SalesDetail> listOfSalesDetail = new ArrayList<SalesDetail>();
	private SalesDetailTableModel salesDetailTableModel;
	public List<SalesInsuranceDetail> listOfSalesInsuranceDetail = new ArrayList<SalesInsuranceDetail>();
	private SalesInsuranceDetailTableModel salesInsuranceDetailTableModel;
	public List<ShipmentSalesOrder> listOfSalesShipmentDetail = new ArrayList<ShipmentSalesOrder>();
	private SalesShipmentDetailTableModel salesShipmentDetailTableModel;

	JLabel lblCustCode;
	JLabel lblCustName;
	JLabel lblCreatedBy;
	JLabel lblCreatedOn;
	JLabel lblShipTo;
	JLabel lblDeliveryAddress;
	JLabel lblPoNo;
	JLabel lblPoDate;
	JLabel lblSoNo;
	JLabel lblSoDate;
	JLabel lblCurrency;
	JLabel lblSurcharge;
	JLabel lblDiscount;
	JLabel lblFreightCost;
	JLabel lblVat;
	JLabel lblVatPercentage;
	JLabel lblFcCurrency;
	JLabel lblIcCurrency;
	JLabel lblTotalWeight;
	JLabel lblTotalVolume;
	JLabel lblTotalItem;
	JLabel lblDescription;
	JLabel lblSalesDetail;
	JLabel lblInsuranceDetail;
	JLabel lblShipmentDetail;
	JLabel lblPriceTerm;
	JLabel lblBankName;
	JLabel lblBankAccountNo;
	JLabel lblBankSwiftCode;
	JLabel lblBankAccountName;
	JLabel lblAccountCurrency;
	JLabel lblCurrencyToRupiah;

	JTextField txtCustCode;
	JTextField txtCustId;
	JTextField txtCustName;
	JTextField txtCreatedBy;
	JTextField txtCreatedOn;
	ComboBox<CustAddress> cbCustAddress;
	ComboBox<String> cbPriceTerm;
	JTextField txtAddress;
	JTextField txtPoNo;
	JTextField txtSoNo;
	JDateChooser poDateChooser;
	JDateChooser soDateChooser;
	ComboBox<Currency> cbCurrency;
	NumberField txtSurcharge;
	NumberField txtDiscount;
	NumberField txtFreightCost;
	NumberField txtVat;
	JTextField txtTotalWeight;
	JTextField txtTotalVolume;
	JTextField txtTotalItem;
	JTextArea txtDescription;
	ComboBox<BankCust> cbBankCust;
	JTextField txtBankAccountNo;
	JTextField txtBankSwiftCode;
	JTextField txtBankAccountName;
	JTextField txtCurrency;
	JTextField txtCurrencyToRupiah;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JLabel lblErrorSalesDetail;
	JScrollPane scrollPaneSalesDetail;
	JTable tblSalesDetail;
	JButton btnAddSalesDetail;
	JButton btnDeleteSalesDetail;
	JLabel lblErrorInsuranceDetail;
	JScrollPane scrollPaneInsuranceDetail;
	JTable tblInsuranceDetail;
	JButton btnAddInsuranceDetail;
	JButton btnDeleteInsuranceDetail;
	JLabel lblErrorShipmentDetail;
	JScrollPane scrollPaneShipmentDetail;
	JTable tblShipmentDetail;
	JButton btnAddShipmentDetail;
	JButton btnDeleteShipmentDetail;
	JButton btnSelectCustomer;
	JButton btnSelectProduct;

	JPanel panel;
	JScrollPane scrollPane;

	JButton btnCancel;
	JButton btnSave;

	JLabel lblErrorCustCode;
	JLabel lblErrorShipTo;
	JLabel lblErrorCurrency;
	JLabel lblErrorFreightCost;
	JLabel lblErrorInsuranceCost;
	JLabel lblErrorFcCurrency;
	JLabel lblErrorIcCurrency;
	JLabel lblErrorVat;
	JLabel lblErrorPriceTerm;
	JLabel lblErrorBankCust;

	private SalesCreatePanel salesCreate;

	List<CustAddress> listOfCustAddress;
	List<BankCust> listOfBankCust;
	List<Currency> listOfCurrency;
	List<String> listOfPriceTerm;
	Customer customer;

	public SalesCreatePanel() {
		sales = new Sales();
		salesCreate = this;

		DocumentFilter filter = new UppercaseDocumentFilter();

		setLayout(null);
		panel = new JPanel();
		// panel.setPreferredSize(new Dimension(MainPanel.bodyPanel.getWidth() - 100,
		// MainPanel.bodyPanel.getHeight()));
		panel.setPreferredSize(new Dimension(800, 1600));
		panel.setLayout(null);

		lblCustCode = new JLabel("<html>Kode Customer <font color=\"red\">*</font></html>");
		lblCustCode.setBounds(50, 80, 150, 25);
		panel.add(lblCustCode);

		txtCustCode = new JTextField();
		txtCustCode.setBounds(220, 80, 150, 25);

		txtCustId = new JTextField();
		txtCustId.setVisible(false);
		panel.add(txtCustId);

		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					customer = new Customer();
					List<CustAddress> listOfCustAddress = new ArrayList<CustAddress>();
					List<BankCust> listOfBankCust = new ArrayList<BankCust>();

					customer = ServiceFactory.getSalesBL().getCustomerByCode(txtCustCode.getText());
					if (customer == null) {
						txtCustName.setText("");

						cbCustAddress.setEnabled(false);
						cbCustAddress.removeAllItems();
						
						txtAddress.setText("");
						
						cbBankCust.setEnabled(false);
						cbBankCust.removeAllItems();
						
						txtBankAccountNo.setText("");
						txtBankSwiftCode.setText("");
						txtBankAccountName.setText("");
						txtCurrency.setText("");
						txtCurrencyToRupiah.setText("");
					} else {
						txtCustName.setText(customer.getCustName());
						txtCustId.setText(Integer.toString(customer.getId()));

						cbCustAddress.removeAllItems();
						listOfCustAddress = ServiceFactory.getSalesBL()
								.getCustAddressByCustCode(customer.getCustCode());
						if (listOfCustAddress.isEmpty()) {
							cbCustAddress.setEnabled(false);
							txtAddress.setEnabled(false);
						} else {
							for (CustAddress custAddress : listOfCustAddress) {
								cbCustAddress.addItem(custAddress);
							}
							cbCustAddress.setEnabled(true);
						}
						
						cbBankCust.removeAllItems();
						listOfBankCust = ServiceFactory.getSalesBL()
								.getBankCustByCustCode(customer.getCustCode());
						if (listOfBankCust.isEmpty()) {
							cbBankCust.setEnabled(false);
						} else {
							for (BankCust bankCust : listOfBankCust) {
								cbBankCust.addItem(bankCust);
							}
							cbBankCust.setEnabled(true);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		txtCustCode.addActionListener(action);

		((AbstractDocument) txtCustCode.getDocument()).setDocumentFilter(filter);
		panel.add(txtCustCode);

		btnSelectCustomer = new JButton("...");
		btnSelectCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCustomerListDialog(salesCreate);
			}
		});
		btnSelectCustomer.setBounds(390, 80, 50, 25);
		panel.add(btnSelectCustomer);

		lblErrorCustCode = new JLabel();
		lblErrorCustCode.setForeground(Color.RED);
		lblErrorCustCode.setBounds(500, 80, 225, 25);
		panel.add(lblErrorCustCode);

		lblCustName = new JLabel("<html>Nama Customer</html>");
		lblCustName.setBounds(50, 110, 150, 25);
		panel.add(lblCustName);

		txtCustName = new JTextField();
		txtCustName.setBounds(220, 110, 150, 25);
		txtCustName.setDocument(new JTextFieldLimit(200));
		txtCustName.setEnabled(false);
		((AbstractDocument) txtCustName.getDocument()).setDocumentFilter(filter);
		panel.add(txtCustName);

		lblCreatedBy = new JLabel("Created by");
		lblCreatedBy.setBounds(600, 80, 150, 25);
		panel.add(lblCreatedBy);

		txtCreatedBy = new JTextField();
		txtCreatedBy.setBounds(720, 80, 150, 25);
		txtCreatedBy.setEnabled(false);
		txtCreatedBy.setText(ServiceFactory.getSystemBL().getUsernameActive());
		((AbstractDocument) txtCreatedBy.getDocument()).setDocumentFilter(filter);
		panel.add(txtCreatedBy);

		lblCreatedOn = new JLabel("<html>Created on</html>");
		lblCreatedOn.setBounds(600, 110, 150, 25);
		panel.add(lblCreatedOn);

		txtCreatedOn = new JTextField();
		txtCreatedOn.setBounds(720, 110, 150, 25);
		txtCreatedOn.setEnabled(false);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currDateString = dateFormat.format(new Date());
		txtCreatedOn.setText(currDateString);
		((AbstractDocument) txtCreatedOn.getDocument()).setDocumentFilter(filter);
		panel.add(txtCreatedOn);

		lblShipTo = new JLabel("Ship to");
		lblShipTo.setBounds(50, 140, 150, 25);
		panel.add(lblShipTo);

		cbCustAddress = new ComboBox<CustAddress>();
		cbCustAddress.setEnabled(false);
		cbCustAddress.setBounds(220, 140, 150, 25);
		panel.add(cbCustAddress);

		lblErrorShipTo = new JLabel();
		lblErrorShipTo.setForeground(Color.RED);
		lblErrorShipTo.setBounds(425, 140, 225, 25);
		panel.add(lblErrorCustCode);

		cbCustAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustAddress selectedCustAddr = (CustAddress) cbCustAddress.getSelectedItem();
				if (selectedCustAddr == null) {
					txtAddress.setText("");
				} else {
					txtAddress.setText(selectedCustAddr.getAddress());
				}
			}
		});

		lblDeliveryAddress = new JLabel("Alamat Pengiriman");
		lblDeliveryAddress.setBounds(50, 170, 150, 25);
		panel.add(lblDeliveryAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(220, 170, 150, 25);
		txtAddress.setEnabled(false);
		txtAddress.setDocument(new JTextFieldLimit(30));
		((AbstractDocument) txtAddress.getDocument()).setDocumentFilter(filter);
		panel.add(txtAddress);

		lblPoNo = new JLabel("PO No");
		lblPoNo.setBounds(50, 200, 150, 25);
		panel.add(lblPoNo);

		txtPoNo = new JTextField();
		txtPoNo.setBounds(220, 200, 150, 25);
		txtPoNo.setDocument(new JTextFieldLimit(30));
		((AbstractDocument) txtPoNo.getDocument()).setDocumentFilter(filter);
		panel.add(txtPoNo);

		lblPoDate = new JLabel("PO Date");
		lblPoDate.setBounds(50, 230, 150, 25);
		panel.add(lblPoDate);

		poDateChooser = new JDateChooser(new Date());
		poDateChooser.setBounds(220, 230, 150, 25);
		panel.add(poDateChooser);

		lblSoNo = new JLabel("SO No");
		lblSoNo.setBounds(50, 260, 150, 25);
		panel.add(lblSoNo);

		Date soNoDate = new Date();
		Calendar soNoCal = Calendar.getInstance();
		soNoCal.setTime(soNoDate);
		Integer soNoTanggal = soNoCal.get(Calendar.DATE);
		Integer soNoMonth = soNoCal.get(Calendar.MONTH) + 1;
		Integer soNoYear = soNoCal.get(Calendar.YEAR);

		String soNo = new String();
		Integer codeIncrement = ServiceFactory.getSalesBL().getLatestIncrementSalesId();
		soNo = String.format("%04d", codeIncrement) + "/SC" + "/" + soNoTanggal + "/" + soNoMonth + "/" + +soNoYear;
		txtSoNo = new JTextField();
		txtSoNo.setBounds(220, 260, 150, 25);
		txtSoNo.setEnabled(false);
		txtSoNo.setText(soNo);
		((AbstractDocument) txtSoNo.getDocument()).setDocumentFilter(filter);
		panel.add(txtSoNo);

		lblSoDate = new JLabel("SO Date");
		lblSoDate.setBounds(50, 290, 150, 25);
		panel.add(lblSoDate);

		soDateChooser = new JDateChooser(new Date());
		soDateChooser.setBounds(220, 290, 150, 25);
		panel.add(soDateChooser);

		lblCurrency = new JLabel("<html>Kurs <font color=\"red\">*</font></html>");
		lblCurrency.setBounds(50, 320, 150, 25);
		panel.add(lblCurrency);

		listOfCurrency = new ArrayList<Currency>();
		try {
			listOfCurrency = ServiceFactory.getSalesBL().getAllCurrency();
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

		cbCurrency.setBounds(220, 320, 150, 25);
		panel.add(cbCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 320, 225, 25);
		panel.add(lblErrorCurrency);

		lblBreadcrumb = new JLabel("ERP > Penjualan > Sales");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		/////// Table SalesDetail ///////
		lblSalesDetail = new JLabel("Produk");
		lblSalesDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalesDetail.setBounds(50, 350, 150, 25);
		panel.add(lblSalesDetail);

		lblErrorSalesDetail = new JLabel("");
		lblErrorSalesDetail.setForeground(Color.RED);
		lblErrorSalesDetail.setBounds(220, 350, 225, 25);
		panel.add(lblErrorSalesDetail);

		scrollPaneSalesDetail = new JScrollPane();
		scrollPaneSalesDetail.setBounds(50, 380, 975, 170);
		panel.add(scrollPaneSalesDetail);

		salesDetailTableModel = new SalesDetailTableModel(new ArrayList<SalesDetail>());
		tblSalesDetail = new JTable(salesDetailTableModel);
		tblSalesDetail.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblSalesDetail.setFocusable(false);
		scrollPaneSalesDetail.setViewportView(tblSalesDetail);

		tblSalesDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblSalesDetail.getValueAt(tblSalesDetail.getSelectedRow(), 0).equals(true))
					listOfSalesDetail.get(tblSalesDetail.getSelectedRow()).setFlag(false);
				else
					listOfSalesDetail.get(tblSalesDetail.getSelectedRow()).setFlag(true);

				tblSalesDetail.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 7)
						showEditSalesDetailDialog(listOfSalesDetail.get(row), salesCreate, row);
				}
			}
		});

		btnAddSalesDetail = new JButton("Tambah");
		btnAddSalesDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddSalesDetailDialog(salesCreate);
			}
		});
		btnAddSalesDetail.setBounds(820, 350, 100, 25);
		panel.add(btnAddSalesDetail);

		btnDeleteSalesDetail = new JButton("Hapus");
		btnDeleteSalesDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteSalesDetail();
			}
		});
		btnDeleteSalesDetail.setBounds(925, 350, 100, 25);
		panel.add(btnDeleteSalesDetail);

		lblSurcharge = new JLabel("Customer Surcharge");
		lblSurcharge.setBounds(50, 570, 150, 25);
		panel.add(lblSurcharge);

		txtSurcharge = new NumberField(12);
		txtSurcharge.setBounds(220, 570, 150, 25);
		panel.add(txtSurcharge);

		lblDiscount = new JLabel("Customer Discount");
		lblDiscount.setBounds(50, 600, 150, 25);
		panel.add(lblDiscount);

		txtDiscount = new NumberField(12);
		txtDiscount.setBounds(220, 600, 150, 25);
		panel.add(txtDiscount);

		lblFreightCost = new JLabel("<html>Freight Cost <font color=\"red\">*</font></html>");
		lblFreightCost.setBounds(50, 630, 150, 25);
		panel.add(lblFreightCost);

		txtFreightCost = new NumberField(12);
		txtFreightCost.setBounds(220, 630, 150, 25);
		panel.add(txtFreightCost);

		lblErrorFreightCost = new JLabel();
		lblErrorFreightCost.setForeground(Color.RED);
		lblErrorFreightCost.setBounds(390, 630, 225, 25);
		panel.add(lblErrorFreightCost);

		/////// Table SalesInsuranceDetail ///////
		lblInsuranceDetail = new JLabel("Asuransi");
		lblInsuranceDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInsuranceDetail.setBounds(50, 660, 150, 25);
		panel.add(lblInsuranceDetail);

		lblErrorInsuranceDetail = new JLabel("");
		lblErrorInsuranceDetail.setForeground(Color.RED);
		lblErrorInsuranceDetail.setBounds(220, 660, 225, 25);
		panel.add(lblErrorInsuranceDetail);

		scrollPaneInsuranceDetail = new JScrollPane();
		scrollPaneInsuranceDetail.setBounds(50, 690, 975, 170);
		panel.add(scrollPaneInsuranceDetail);

		salesInsuranceDetailTableModel = new SalesInsuranceDetailTableModel(new ArrayList<SalesInsuranceDetail>());
		tblInsuranceDetail = new JTable(salesInsuranceDetailTableModel);
		tblInsuranceDetail.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblInsuranceDetail.setFocusable(false);
		scrollPaneInsuranceDetail.setViewportView(tblInsuranceDetail);

		tblInsuranceDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblInsuranceDetail.getValueAt(tblInsuranceDetail.getSelectedRow(), 0).equals(true))
					listOfSalesInsuranceDetail.get(tblInsuranceDetail.getSelectedRow()).setFlag(false);
				else
					listOfSalesInsuranceDetail.get(tblInsuranceDetail.getSelectedRow()).setFlag(true);

				tblInsuranceDetail.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4)
						showEditInsuranceDetailDialog(listOfSalesInsuranceDetail.get(row), salesCreate, row);
				}
			}
		});

		btnAddInsuranceDetail = new JButton("Tambah");
		btnAddInsuranceDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddInsuranceDetailDialog(salesCreate);
			}
		});
		btnAddInsuranceDetail.setBounds(820, 660, 100, 25);
		panel.add(btnAddInsuranceDetail);

		btnDeleteInsuranceDetail = new JButton("Hapus");
		btnDeleteInsuranceDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteSalesInsuranceDetail();
			}
		});
		btnDeleteInsuranceDetail.setBounds(925, 660, 100, 25);
		panel.add(btnDeleteInsuranceDetail);

		/////// Table SalesShipmentDetail ///////
		lblShipmentDetail = new JLabel("Shipment");
		lblShipmentDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShipmentDetail.setBounds(50, 880, 150, 25);
		panel.add(lblShipmentDetail);

		lblErrorShipmentDetail = new JLabel("");
		lblErrorShipmentDetail.setForeground(Color.RED);
		lblErrorShipmentDetail.setBounds(220, 880, 225, 25);
		panel.add(lblErrorShipmentDetail);

		scrollPaneShipmentDetail = new JScrollPane();
		scrollPaneShipmentDetail.setBounds(50, 910, 975, 170);
		panel.add(scrollPaneShipmentDetail);

		salesShipmentDetailTableModel = new SalesShipmentDetailTableModel(new ArrayList<ShipmentSalesOrder>());
		tblShipmentDetail = new JTable(salesShipmentDetailTableModel);
		tblShipmentDetail.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblShipmentDetail.setFocusable(false);
		scrollPaneShipmentDetail.setViewportView(tblShipmentDetail);

		tblShipmentDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblShipmentDetail.getValueAt(tblShipmentDetail.getSelectedRow(), 0).equals(true))
					listOfSalesShipmentDetail.get(tblShipmentDetail.getSelectedRow()).setFlag(false);
				else
					listOfSalesShipmentDetail.get(tblShipmentDetail.getSelectedRow()).setFlag(true);

				tblShipmentDetail.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 8)
						showEditShipmentDetailDialog(listOfSalesShipmentDetail.get(row), salesCreate, row);
				}
			}
		});

		btnAddShipmentDetail = new JButton("Tambah");
		btnAddShipmentDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddShipmentDetailDialog(salesCreate);
			}
		});
		btnAddShipmentDetail.setBounds(820, 880, 100, 25);
		panel.add(btnAddShipmentDetail);

		btnDeleteShipmentDetail = new JButton("Hapus");
		btnDeleteShipmentDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteSalesShipmentDetail();
			}
		});
		btnDeleteShipmentDetail.setBounds(925, 880, 100, 25);
		panel.add(btnDeleteShipmentDetail);
		
		lblPriceTerm = new JLabel("<html>Price Term<font color=\"red\">*</font></html>");
		lblPriceTerm.setBounds(50, 1100, 150, 25);
		panel.add(lblPriceTerm);

		listOfPriceTerm = new ArrayList<String>();
		listOfPriceTerm.add(0, new String("-- Pilih Price Term --"));
		listOfPriceTerm.add(1, new String("CNF"));
		listOfPriceTerm.add(2, new String("CFR"));
		listOfPriceTerm.add(3, new String("FOB"));
		cbPriceTerm = new ComboBox<String>();
		cbPriceTerm.setList(listOfPriceTerm);
		cbPriceTerm.setBounds(220, 1100, 150, 25);
		panel.add(cbPriceTerm);

		lblErrorPriceTerm = new JLabel();
		lblErrorPriceTerm.setForeground(Color.RED);
		lblErrorPriceTerm.setBounds(425, 1100, 225, 25);
		panel.add(lblErrorPriceTerm);
		
		lblBankName = new JLabel("<html>Bank Name <font color=\"red\">*</font></html>");
		lblBankName.setBounds(50, 1130, 150, 25);
		panel.add(lblBankName);

		cbBankCust = new ComboBox<BankCust>();
		cbBankCust.setBounds(220, 1130, 150, 25);
		panel.add(cbBankCust);

		lblErrorBankCust = new JLabel();
		lblErrorBankCust.setForeground(Color.RED);
		lblErrorBankCust.setBounds(425, 1130, 225, 25);
		panel.add(lblErrorCustCode);

		cbBankCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankCust selectedBankCust = (BankCust) cbBankCust.getSelectedItem();
				if (selectedBankCust == null) {
					txtBankAccountNo.setText("");
					txtBankSwiftCode.setText("");
					txtBankAccountName.setText("");
					txtCurrency.setText("");
					txtCurrencyToRupiah.setText("");
				} else {
					txtBankAccountNo.setText(selectedBankCust.getAccountno());
					txtBankSwiftCode.setText(selectedBankCust.getSwiftcode());
					txtBankAccountName.setText(selectedBankCust.getAccname());
					txtCurrency.setText(selectedBankCust.getCurrency().getCurrency());
					txtCurrencyToRupiah.setText(String.valueOf(sales.getCurrencyToRupiah()));
				}
			}
		});
		
		lblBankAccountNo = new JLabel("Bank Account No");
		lblBankAccountNo.setBounds(50, 1160, 150, 25);
		panel.add(lblBankAccountNo);

		txtBankAccountNo = new JTextField();
		txtBankAccountNo.setBounds(220, 1160, 150, 25);
		txtBankAccountNo.setDocument(new JTextFieldLimit(30));
		txtBankAccountNo.setEnabled(false);
		((AbstractDocument) txtBankAccountNo.getDocument()).setDocumentFilter(filter);
		panel.add(txtBankAccountNo);
		
		lblBankSwiftCode = new JLabel("Bank Swift Code");
		lblBankSwiftCode.setBounds(50, 1190, 150, 25);
		panel.add(lblBankSwiftCode);

		txtBankSwiftCode = new JTextField();
		txtBankSwiftCode.setBounds(220, 1190, 150, 25);
		txtBankSwiftCode.setDocument(new JTextFieldLimit(30));
		txtBankSwiftCode.setEnabled(false);
		((AbstractDocument) txtBankSwiftCode.getDocument()).setDocumentFilter(filter);
		panel.add(txtBankSwiftCode);
		
		lblBankAccountName = new JLabel("Bank Account Name");
		lblBankAccountName.setBounds(50, 1220, 150, 25);
		panel.add(lblBankAccountName);

		txtBankAccountName = new JTextField();
		txtBankAccountName.setBounds(220, 1220, 150, 25);
		txtBankAccountName.setDocument(new JTextFieldLimit(30));
		txtBankAccountName.setEnabled(false);
		((AbstractDocument) txtBankAccountName.getDocument()).setDocumentFilter(filter);
		panel.add(txtBankAccountName);
		
		lblAccountCurrency = new JLabel("Currency");
		lblAccountCurrency.setBounds(50, 1250, 150, 25);
		panel.add(lblAccountCurrency);

		txtCurrency = new JTextField();
		txtCurrency.setBounds(220, 1250, 150, 25);
		txtCurrency.setDocument(new JTextFieldLimit(30));
		txtCurrency.setEnabled(false);
		((AbstractDocument) txtCurrency.getDocument()).setDocumentFilter(filter);
		panel.add(txtCurrency);
		
		lblCurrencyToRupiah = new JLabel("Currency to Rupiah");
		lblCurrencyToRupiah.setBounds(50, 1280, 150, 25);
		panel.add(lblCurrencyToRupiah);

		txtCurrencyToRupiah = new JTextField();
		txtCurrencyToRupiah.setBounds(220, 1280, 150, 25);
		txtCurrencyToRupiah.setDocument(new JTextFieldLimit(30));
		txtCurrencyToRupiah.setEnabled(false);
		((AbstractDocument) txtCurrencyToRupiah.getDocument()).setDocumentFilter(filter);
		panel.add(txtCurrencyToRupiah);

		lblVat = new JLabel("<html>VAT <font color=\"red\">*</font></html>");
		lblVat.setBounds(50, 1310, 150, 25);
		panel.add(lblVat);

		txtVat = new NumberField(3);
		txtVat.setBounds(220, 1310, 150, 25);
		panel.add(txtVat);

		lblVatPercentage = new JLabel("%");
		lblVatPercentage.setBounds(390, 1310, 225, 25);
		panel.add(lblVatPercentage);

		lblErrorVat = new JLabel();
		lblErrorVat.setForeground(Color.RED);
		lblErrorVat.setBounds(425, 1310, 225, 25);
		panel.add(lblErrorVat);

		lblTotalWeight = new JLabel("Total Weight");
		lblTotalWeight.setBounds(50, 1340, 150, 25);
		panel.add(lblTotalWeight);

		txtTotalWeight = new JTextField();
		txtTotalWeight.setBounds(220, 1340, 150, 25);
		txtTotalWeight.setDocument(new JTextFieldLimit(30));
		txtTotalWeight.setEnabled(false);
		((AbstractDocument) txtTotalWeight.getDocument()).setDocumentFilter(filter);
		panel.add(txtTotalWeight);

		lblTotalVolume = new JLabel("Total Volume");
		lblTotalVolume.setBounds(50, 1370, 150, 25);
		panel.add(lblTotalVolume);

		txtTotalVolume = new JTextField();
		txtTotalVolume.setBounds(220, 1370, 150, 25);
		txtTotalVolume.setDocument(new JTextFieldLimit(30));
		txtTotalVolume.setEnabled(false);
		((AbstractDocument) txtTotalVolume.getDocument()).setDocumentFilter(filter);
		panel.add(txtTotalVolume);

		lblTotalItem = new JLabel("Total Item");
		lblTotalItem.setBounds(50, 1400, 150, 25);
		panel.add(lblTotalItem);

		txtTotalItem = new JTextField();
		txtTotalItem.setBounds(220, 1400, 150, 25);
		txtTotalItem.setDocument(new JTextFieldLimit(30));
		txtTotalItem.setEnabled(false);
		((AbstractDocument) txtTotalItem.getDocument()).setDocumentFilter(filter);
		panel.add(txtTotalItem);

		lblDescription = new JLabel("Description");
		lblDescription.setBounds(50, 1430, 150, 25);
		panel.add(lblDescription);

		txtDescription = new JTextArea();
		txtDescription.setBounds(220, 1430, 150, 85);
		Border border = BorderFactory.createLineBorder(Color.gray);
		txtDescription.setBorder(border);
		panel.add(txtDescription);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPane);

		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidate() == false) {
					return;
				}
				int response = DialogBox.showInsertChoice();
				if (response == JOptionPane.YES_OPTION) {
					doSave();
				}
			}
		});
		btnSave.setBounds(925, 1550, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.sales.ui.SalesListPanel");
				}
			}
		});
		btnCancel.setBounds(50, 1550, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtCustCode.requestFocusInWindow();
			}
		});
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorCustCode.setText("");
		lblErrorShipTo.setText("");
		lblErrorCurrency.setText("");
		lblErrorFreightCost.setText("");
		lblErrorPriceTerm.setText("");
		lblErrorBankCust.setText("");
		lblErrorVat.setText("");

		if (txtCustCode.getText() == null || txtCustCode.getText().length() == 0) {
			lblErrorCustCode.setText("Textbox Kode Customer harus diisi.");
			isValid = false;
		}

		if (cbCustAddress.getSelectedItem() == null) {
			lblErrorShipTo.setText("Combobox Alamat harus dipilih.");
			isValid = false;
		}

		if (cbCurrency.getSelectedItem() == null || cbCurrency.getSelectedIndex() == 0) {
			lblErrorCurrency.setText("Combobox Currency harus dipilih.");
			isValid = false;
		}

		if (txtFreightCost.getText() == null || txtFreightCost.getText().length() == 0) {
			lblErrorFreightCost.setText("Textbox Freight Cost harus diisi.");
			isValid = false;
		}
		
		if (cbPriceTerm.getSelectedItem() == null || cbPriceTerm.getSelectedIndex() == 0) {
			lblErrorPriceTerm.setText("Combobox Price Term harus dipilih");
			isValid = false;
		}

		if (txtVat.getText() == null || txtVat.getText().length() == 0) {
			lblErrorVat.setText("Textbox VAT harus diisi.");
			isValid = false;
		}

		if (!"".equals(txtVat.getText())) {
			if (Double.valueOf(txtVat.getText()) > 100.00) {
				lblErrorVat.setText("VAT tidak dapat lebih dari 100%");
				isValid = false;
			}
		}

		return isValid;
	}

	protected void doSave() {
		sales = new Sales();
		CustAddress custAddress = (CustAddress) cbCustAddress.getSelectedItem();
		BankCust selectedBankCust = new BankCust();
		selectedBankCust = (BankCust) cbBankCust.getSelectedItem();

		sales.setCustomerId(Integer.valueOf(txtCustId.getText()));
		sales.setCustAddrId(custAddress.getId());
		sales.setCurrencyId(cbCurrency.getDataIndex().getId());
		sales.setPoNo(txtPoNo.getText());
		sales.setPoDate(poDateChooser.getDate());
		sales.setSoNo(txtSoNo.getText());
		sales.setSoDate(soDateChooser.getDate());
		sales.setSurcharge(Double.valueOf(txtSurcharge.getText()));
		sales.setDiscount(Double.valueOf(txtDiscount.getText()));
		sales.setFreightCost(Double.valueOf(txtFreightCost.getText()));
		sales.setPriceTerm(cbPriceTerm.getSelectedItem().toString());
		sales.setVat(Double.valueOf(txtVat.getText()));
		sales.setBankId(selectedBankCust.getId());
		sales.setCurrencyToRupiah(0);
		sales.setDescription(txtDescription.getText());

		try {
			ServiceFactory.getSalesBL().save(sales, listOfSalesDetail, listOfSalesInsuranceDetail, listOfSalesShipmentDetail);
			DialogBox.showInsert();
			MainPanel.changePanel("module.sales.ui.SalesListPanel");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add cust address dialog
	 */
	protected void showCustomerListDialog(SalesCreatePanel salesCreate) {
		CustomerListDialog customerListDialog = new CustomerListDialog(salesCreate);
		customerListDialog.setTitle("Customer");
		customerListDialog.setLocationRelativeTo(null);
		customerListDialog.setVisible(true);
	}

	/**
	 * Method to display add cust address dialog
	 */
	protected void showAddSalesDetailDialog(SalesCreatePanel salesCreate) {
		SalesDetailDialog salesDetailDialog = new SalesDetailDialog(false, new SalesDetail(), salesCreate, null);
		salesDetailDialog.setTitle("Produk");
		salesDetailDialog.setLocationRelativeTo(null);
		salesDetailDialog.setVisible(true);
	}

	protected void showEditSalesDetailDialog(SalesDetail salesDetail, SalesCreatePanel salesCreate, Integer index) {
		SalesDetailDialog salesDetailDialog = new SalesDetailDialog(true, salesDetail, salesCreate, index);
		salesDetailDialog.setTitle("Produk");
		salesDetailDialog.setLocationRelativeTo(null);
		salesDetailDialog.setVisible(true);
	}

	/**
	 * Method to display add sales insurance dialog
	 */
	protected void showAddInsuranceDetailDialog(SalesCreatePanel salesCreate) {
		SalesInsuranceDetailDialog insuranceDetailDialog = new SalesInsuranceDetailDialog(false,
				new SalesInsuranceDetail(), salesCreate, null);
		insuranceDetailDialog.setTitle("Asuransi");
		insuranceDetailDialog.setLocationRelativeTo(null);
		insuranceDetailDialog.setVisible(true);
	}

	protected void showEditInsuranceDetailDialog(SalesInsuranceDetail salesInsuranceDetail,
			SalesCreatePanel salesCreate, Integer index) {
		SalesInsuranceDetailDialog insuranceDetailDialog = new SalesInsuranceDetailDialog(true, salesInsuranceDetail,
				salesCreate, index);
		insuranceDetailDialog.setTitle("Asuransi");
		insuranceDetailDialog.setLocationRelativeTo(null);
		insuranceDetailDialog.setVisible(true);
	}

	/**
	 * Method to display add sales shipment dialog
	 */
	protected void showAddShipmentDetailDialog(SalesCreatePanel salesCreate) {
		SalesShipmentDetailDialog shipmentDetailDialog = new SalesShipmentDetailDialog(false,
				new ShipmentSalesOrder(), salesCreate, null);
		shipmentDetailDialog.setTitle("Shipment");
		shipmentDetailDialog.setLocationRelativeTo(null);
		shipmentDetailDialog.setVisible(true);
	}

	protected void showEditShipmentDetailDialog(ShipmentSalesOrder shipmentSalesOrder,
			SalesCreatePanel salesCreate, Integer index) {
		SalesShipmentDetailDialog shipmentDetailDialog = new SalesShipmentDetailDialog(true, shipmentSalesOrder,
				salesCreate, index);
		shipmentDetailDialog.setTitle("Shipment");
		shipmentDetailDialog.setLocationRelativeTo(null);
		shipmentDetailDialog.setVisible(true);
	}

	protected void doDeleteSalesDetail() {
		if (listOfSalesDetail.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<SalesDetail> temp = new ArrayList<SalesDetail>();
			for (SalesDetail s : listOfSalesDetail) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else
					count += 1;
			}

			if (count == listOfSalesDetail.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (SalesDetail s : temp) {
					listOfSalesDetail.remove(s);
				}
				refreshTableSalesDetail();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Class as TableModel for Cust Address table
	 * 
	 * @author TLO
	 *
	 */
	class SalesDetailTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<SalesDetail> listOfSalesDetail;

		public SalesDetailTableModel(List<SalesDetail> listOfSalesDetail) {
			this.listOfSalesDetail = listOfSalesDetail;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSalesDetail.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 8;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SalesDetail}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SalesDetail p = listOfSalesDetail.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getProduct().getProductCode();
			case 2:
				return p.getProduct().getProductName();
			case 3:
				return p.getUom().getUom();
			case 4:
				return p.getQuantity();
			case 5:
				return p.getNettPrice();
			case 6:
				return p.getTotalPrice();
			case 7:
				return "<html><u>Edit</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "Kode Produk";
			case 2:
				return "Nama Produk";
			case 3:
				return "UOM";
			case 4:
				return "QTY";
			case 5:
				return "Net Price";
			case 6:
				return "Total Price";
			case 7:
				return "Tindakan";
			default:
				return "";
			}
		}
	}

	protected void doDeleteSalesInsuranceDetail() {
		if (listOfSalesInsuranceDetail.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<SalesInsuranceDetail> temp = new ArrayList<SalesInsuranceDetail>();
			for (SalesInsuranceDetail s : listOfSalesInsuranceDetail) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else
					count += 1;
			}

			if (count == listOfSalesInsuranceDetail.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (SalesInsuranceDetail s : temp) {
					listOfSalesInsuranceDetail.remove(s);
				}
				refreshTableInsuranceDetail();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Class as TableModel for Insurance Detail table
	 * 
	 * @author TLO
	 *
	 */
	class SalesInsuranceDetailTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<SalesInsuranceDetail> listOfSalesInsuranceDetail;

		public SalesInsuranceDetailTableModel(List<SalesInsuranceDetail> listOfSalesInsuranceDetail) {
			this.listOfSalesInsuranceDetail = listOfSalesInsuranceDetail;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSalesInsuranceDetail.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 5;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link InsuranceDetail}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			SalesInsuranceDetail p = listOfSalesInsuranceDetail.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getInsurance().getInsuranceCompanyName();
			case 2:
				return p.getInsurance().getInsuranceType();
			case 3:
				return p.getCost();
			case 4:
				return "<html><u>Edit</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "Insurance Company";
			case 2:
				return "Insurance Type";
			case 3:
				return "Insurance Cost";
			case 4:
				return "Tindakan";
			default:
				return "";
			}
		}
	}

	protected void doDeleteSalesShipmentDetail() {
		if (listOfSalesShipmentDetail.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<ShipmentSalesOrder> temp = new ArrayList<ShipmentSalesOrder>();
			for (ShipmentSalesOrder s : listOfSalesShipmentDetail) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else
					count += 1;
			}

			if (count == listOfSalesShipmentDetail.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (ShipmentSalesOrder s : temp) {
					listOfSalesShipmentDetail.remove(s);
				}
				refreshTableShipmentDetail();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Class as TableModel for Shipment Detail table
	 * 
	 * @author TLO
	 *
	 */
	class SalesShipmentDetailTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<ShipmentSalesOrder> listOfSalesShipmentDetail;

		public SalesShipmentDetailTableModel(List<ShipmentSalesOrder> listOfSalesShipmentDetail) {
			this.listOfSalesShipmentDetail = listOfSalesShipmentDetail;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfSalesShipmentDetail.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 9;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link InsuranceDetail}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			ShipmentSalesOrder p = listOfSalesShipmentDetail.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getShipmentAgent();
			case 2:
				return p.getShipmentType();
			case 3:
				return p.getOriginAddress();
			case 4:
				return p.getDestionationAddress();
			case 5:
				return p.getDateOfShipment();
			case 6:
				return p.getPickupDate();
			case 7:
				return p.getShipmentCost();
			case 8:
				return "<html><u>Edit</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			case 8:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "Shipment Agent";
			case 2:
				return "Shipment Type";
			case 3:
				return "Origin Address";
			case 4:
				return "Destination Address";
			case 5:
				return "Date Of Shipment";
			case 6:
				return "Pickup Date";
			case 7:
				return "Shipment Cost";
			case 8:
				return "Tindakan";
			default:
				return "";
			}
		}
	}

	public void refreshTableSalesDetail() {
		try {
			tblSalesDetail.setModel(new SalesDetailTableModel(listOfSalesDetail));

			int totalItem = 0;
			double totalVolume = 0;
			double totalPrice = 0;
			for (SalesDetail salesDetail : listOfSalesDetail) {
				totalVolume = salesDetail.getTotalVolume() + totalVolume;
				totalItem = salesDetail.getQuantity() + totalItem;
				totalPrice = salesDetail.getTotalPrice() + totalPrice;
			}
			txtTotalItem.setText(Integer.toString(totalItem));
			txtTotalVolume.setText(Double.toString(totalVolume));

		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void refreshTableInsuranceDetail() {
		try {
			tblInsuranceDetail.setModel(new SalesInsuranceDetailTableModel(listOfSalesInsuranceDetail));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void refreshTableShipmentDetail() {
		try {
			tblShipmentDetail.setModel(new SalesShipmentDetailTableModel(listOfSalesShipmentDetail));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
	}
}
