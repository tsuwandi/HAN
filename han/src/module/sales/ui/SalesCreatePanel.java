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
import module.customer.model.Customer;
import module.sales.model.Sales;
import module.sales.model.SalesDetail;
import module.sn.currency.model.Currency;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class SalesCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SalesCreatePanel.class);

	private Sales sales;
	public List<SalesDetail> listOfSalesDetail = new ArrayList<SalesDetail>();
	private SalesDetailTableModel salesDetailTableModel;

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
	JLabel lblInsuranceCost;
	JLabel lblVat;
	JLabel lblVatPercentage;
	JLabel lblFcCurrency;
	JLabel lblIcCurrency;
	JLabel lblTotalWeight;
	JLabel lblTotalVolume;
	JLabel lblTotalItem;
	JLabel lblGrossAmount;
	JLabel lblNettAmount;
	JLabel lblDescription;
	JLabel lblSalesDetail;

	JTextField txtCustCode;
	JTextField txtCustId;
	JTextField txtCustName;
	JTextField txtCreatedBy;
	JTextField txtCreatedOn;
	ComboBox<CustAddress> cbCustAddress;
	JTextField txtAddress;
	JTextField txtPoNo;
	JTextField txtSoNo;
	JDateChooser poDateChooser;
	JDateChooser soDateChooser;
	ComboBox<Currency> cbCurrency;
	NumberField txtSurcharge;
	NumberField txtDiscount;
	NumberField txtFreightCost;
	NumberField txtInsuranceCost;
	ComboBox<Currency> cbFcCurrency;
	ComboBox<Currency> cbIcCurrency;
	NumberField txtVat;
	JTextField txtTotalWeight;
	JTextField txtTotalVolume;
	JTextField txtTotalItem;
	JTextField txtGrossAmount;
	JTextField txtNettAmount;
	JTextArea txtDescription;

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	
	JLabel lblErrorSalesDetail;
	JScrollPane scrollPaneSalesDetail;
	JTable tblSalesDetail;
	JButton btnAddSalesDetail;
	JButton btnDeleteSalesDetail;
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

	private SalesCreatePanel salesCreate;

	List<CustAddress> listOfCustAddress;
	List<Currency> listOfCurrency;
	List<Currency> listOfFcCurrency;
	List<Currency> listOfIcCurrency;
	Customer customer;

	public SalesCreatePanel() {
		sales = new Sales();
		salesCreate = this;

		DocumentFilter filter = new UppercaseDocumentFilter();

		setLayout(null);
		panel = new JPanel();
		// panel.setPreferredSize(new Dimension(MainPanel.bodyPanel.getWidth() - 100,
		// MainPanel.bodyPanel.getHeight()));
		panel.setPreferredSize(new Dimension(800, 1050));
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

					customer = ServiceFactory.getSalesBL().getCustomerByCode(txtCustCode.getText());
					if (customer == null) {
						txtCustName.setText("");

						cbCustAddress.setEnabled(false);
						cbCustAddress.removeAllItems();

						txtAddress.setText("");
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
		lblCreatedBy.setBounds(600, 110, 150, 25);
		panel.add(lblCreatedBy);

		txtCreatedBy = new JTextField();
		txtCreatedBy.setBounds(720, 110, 150, 25);
		txtCreatedBy.setEnabled(false);
		txtCreatedBy.setText(ServiceFactory.getSystemBL().getUsernameActive());
		((AbstractDocument) txtCreatedBy.getDocument()).setDocumentFilter(filter);
		panel.add(txtCreatedBy);

		lblCreatedOn = new JLabel("<html>Created on</html>");
		lblCreatedOn.setBounds(600, 140, 150, 25);
		panel.add(lblCreatedOn);

		txtCreatedOn = new JTextField();
		txtCreatedOn.setBounds(720, 140, 150, 25);
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
		soNo = String.format("%04d", codeIncrement) + "/SC" + "/" + soNoTanggal + "/" + soNoMonth + "/" +
				+ soNoYear;
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

		listOfFcCurrency = new ArrayList<Currency>();
		try {
			listOfFcCurrency = ServiceFactory.getSalesBL().getAllCurrency();
			listOfFcCurrency.add(0, new Currency("-- Pilih Kurs --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		cbFcCurrency = new ComboBox<Currency>();
		cbFcCurrency.setList(listOfFcCurrency);

		// default IDR

		int x = 0;
		for (Currency currency : listOfFcCurrency) {
			if (AppConstants.CURRENCY_IDR.equals(currency.getCurrencyAbbr())) {
				cbFcCurrency.setSelectedIndex(x);
				break;
			}
			x++;
		}

		cbFcCurrency.setBounds(390, 630, 150, 25);
		panel.add(cbFcCurrency);

		lblErrorFreightCost = new JLabel();
		lblErrorFreightCost.setForeground(Color.RED);
		lblErrorFreightCost.setBounds(600, 630, 225, 25);
		panel.add(lblErrorFreightCost);

		lblErrorFcCurrency = new JLabel();
		lblErrorFcCurrency.setForeground(Color.RED);
		lblErrorFcCurrency.setBounds(600, 630, 225, 25);
		panel.add(lblErrorFcCurrency);

		lblInsuranceCost = new JLabel("<html>Insurance Cost <font color=\"red\">*</font></html>");
		lblInsuranceCost.setBounds(50, 660, 150, 25);
		panel.add(lblInsuranceCost);

		txtInsuranceCost = new NumberField(12);
		txtInsuranceCost.setBounds(220, 660, 150, 25);
		panel.add(txtInsuranceCost);

		listOfIcCurrency = new ArrayList<Currency>();
		try {
			listOfIcCurrency = ServiceFactory.getSalesBL().getAllCurrency();
			listOfIcCurrency.add(0, new Currency("-- Pilih Kurs --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		cbIcCurrency = new ComboBox<Currency>();
		cbIcCurrency.setList(listOfIcCurrency);

		// default IDR

		int j = 0;
		for (Currency currency : listOfIcCurrency) {
			if (AppConstants.CURRENCY_IDR.equals(currency.getCurrencyAbbr())) {
				cbIcCurrency.setSelectedIndex(j);
				break;
			}
			j++;
		}

		cbIcCurrency.setBounds(390, 660, 150, 25);
		panel.add(cbIcCurrency);

		lblErrorInsuranceCost = new JLabel();
		lblErrorInsuranceCost.setForeground(Color.RED);
		lblErrorInsuranceCost.setBounds(600, 660, 225, 25);
		panel.add(lblErrorInsuranceCost);

		lblErrorIcCurrency = new JLabel();
		lblErrorIcCurrency.setForeground(Color.RED);
		lblErrorIcCurrency.setBounds(600, 660, 225, 25);
		panel.add(lblErrorIcCurrency);

		lblVat = new JLabel("<html>VAT <font color=\"red\">*</font></html>");
		lblVat.setBounds(50, 690, 150, 25);
		panel.add(lblVat);

		txtVat = new NumberField(3);
		txtVat.setBounds(220, 690, 150, 25);
		panel.add(txtVat);
		
		lblVatPercentage = new JLabel("%");
		lblVatPercentage.setBounds(390, 690, 225, 25);
		panel.add(lblVatPercentage);

		lblErrorVat = new JLabel();
		lblErrorVat.setForeground(Color.RED);
		lblErrorVat.setBounds(425, 690, 225, 25);
		panel.add(lblErrorVat);

		lblTotalWeight = new JLabel("Total Weight");
		lblTotalWeight.setBounds(50, 720, 150, 25);
		panel.add(lblTotalWeight);

		txtTotalWeight = new JTextField();
		txtTotalWeight.setBounds(220, 720, 150, 25);
		txtTotalWeight.setDocument(new JTextFieldLimit(30));
		txtTotalWeight.setEnabled(false);
		((AbstractDocument) txtTotalWeight.getDocument()).setDocumentFilter(filter);
		panel.add(txtTotalWeight);

		lblTotalVolume = new JLabel("Total Volume");
		lblTotalVolume.setBounds(50, 750, 150, 25);
		panel.add(lblTotalVolume);

		txtTotalVolume = new JTextField();
		txtTotalVolume.setBounds(220, 750, 150, 25);
		txtTotalVolume.setDocument(new JTextFieldLimit(30));
		txtTotalVolume.setEnabled(false);
		((AbstractDocument) txtTotalVolume.getDocument()).setDocumentFilter(filter);
		panel.add(txtTotalVolume);

		lblTotalItem = new JLabel("Total Item");
		lblTotalItem.setBounds(50, 780, 150, 25);
		panel.add(lblTotalItem);

		txtTotalItem = new JTextField();
		txtTotalItem.setBounds(220, 780, 150, 25);
		txtTotalItem.setDocument(new JTextFieldLimit(30));
		txtTotalItem.setEnabled(false);
		((AbstractDocument) txtTotalItem.getDocument()).setDocumentFilter(filter);
		panel.add(txtTotalItem);

		lblGrossAmount = new JLabel("Gross Amount");
		lblGrossAmount.setBounds(50, 810, 150, 25);
		panel.add(lblGrossAmount);

		txtGrossAmount = new JTextField();
		txtGrossAmount.setBounds(220, 810, 150, 25);
		txtGrossAmount.setDocument(new JTextFieldLimit(30));
		txtGrossAmount.setEnabled(false);
		((AbstractDocument) txtGrossAmount.getDocument()).setDocumentFilter(filter);
		panel.add(txtGrossAmount);

		lblNettAmount = new JLabel("Net Amount");
		lblNettAmount.setBounds(50, 840, 150, 25);
		panel.add(lblNettAmount);

		txtNettAmount = new JTextField();
		txtNettAmount.setBounds(220, 840, 150, 25);
		txtNettAmount.setDocument(new JTextFieldLimit(30));
		txtNettAmount.setEnabled(false);
		((AbstractDocument) txtNettAmount.getDocument()).setDocumentFilter(filter);
		panel.add(txtNettAmount);

		lblDescription = new JLabel("Description");
		lblDescription.setBounds(50, 870, 150, 25);
		panel.add(lblDescription);

		txtDescription = new JTextArea();
		txtDescription.setBounds(220, 870, 150, 85);
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
		btnSave.setBounds(925, 1000, 100, 25);
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
		btnCancel.setBounds(50, 1000, 100, 25);
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
		lblErrorInsuranceCost.setText("");
		lblErrorFcCurrency.setText("");
		lblErrorIcCurrency.setText("");
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
		
		if (txtInsuranceCost.getText() == null || txtInsuranceCost.getText().length() == 0) {
			lblErrorInsuranceCost.setText("Textbox Insurance Cost harus diisi.");
			isValid = false;
		}
		
		if (cbFcCurrency.getSelectedItem() == null || cbFcCurrency.getSelectedIndex() == 0) {
			lblErrorFcCurrency.setText("Combobox Currency harus dipilih.");
			isValid = false;
		}
		
		if (cbIcCurrency.getSelectedItem() == null || cbIcCurrency.getSelectedIndex() == 0) {
			lblErrorIcCurrency.setText("Combobox Currency harus dipilih.");
			isValid = false;
		}
		
		if (txtVat.getText() == null || txtCustCode.getText().length() == 0) {
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

		sales.setCustomerId(Integer.valueOf(txtCustId.getText()));
		sales.setCustAddrId(custAddress.getId());
		sales.setCurrencyId(cbCurrency.getDataIndex().getId());
		sales.setFreightCostCurrencyId(cbFcCurrency.getDataIndex().getId());
		sales.setInsuranceCostCurrencyId(cbIcCurrency.getDataIndex().getId());
		sales.setPoNo(txtPoNo.getText());
		sales.setPoDate(poDateChooser.getDate());
		sales.setSoNo(txtSoNo.getText());
		sales.setSoDate(soDateChooser.getDate());
		sales.setSurcharge(Double.valueOf(txtSurcharge.getText()));
		sales.setDiscount(Double.valueOf(txtDiscount.getText()));
		sales.setFreightCost(Double.valueOf(txtFreightCost.getText()));
		sales.setInsuranceCost(Double.valueOf(txtInsuranceCost.getText()));
		sales.setVat(Double.valueOf(txtVat.getText()));
		sales.setDescription(txtDescription.getText());

		try {
			ServiceFactory.getSalesBL().save(sales, listOfSalesDetail);
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
			txtGrossAmount.setText(Double.toString(totalPrice));

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
