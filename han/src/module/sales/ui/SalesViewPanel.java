package module.sales.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import module.customer.model.CustAddress;
import module.customer.model.Customer;
import module.sales.model.Sales;
import module.sales.model.SalesDetail;
import module.sn.currency.model.Currency;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class SalesViewPanel extends JPanel implements Bridging {

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
	JButton btnDelete;
	JButton btnPrint;
	JButton btnEdit;

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

	private SalesViewPanel salesView;

	List<CustAddress> listOfCustAddress;
	List<Currency> listOfCurrency;
	List<Currency> listOfFcCurrency;
	List<Currency> listOfIcCurrency;
	Customer customer;

	public SalesViewPanel() {
		salesView = this;
		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 1050));
		panel.setLayout(null);

		lblCustCode = new JLabel("<html>Kode Customer <font color=\"red\">*</font></html>");
		lblCustCode.setBounds(50, 80, 150, 25);
		panel.add(lblCustCode);

		txtCustCode = new JTextField();
		txtCustCode.setBounds(220, 80, 150, 25);
		txtCustCode.setEnabled(false);
		panel.add(txtCustCode);

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
		panel.add(txtCustName);

		lblCreatedBy = new JLabel("Created by");
		lblCreatedBy.setBounds(600, 110, 150, 25);
		panel.add(lblCreatedBy);

		txtCreatedBy = new JTextField();
		txtCreatedBy.setBounds(720, 110, 150, 25);
		txtCreatedBy.setEnabled(false);
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

		lblDeliveryAddress = new JLabel("Alamat Pengiriman");
		lblDeliveryAddress.setBounds(50, 170, 150, 25);
		panel.add(lblDeliveryAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(220, 170, 150, 25);
		txtAddress.setEnabled(false);
		txtAddress.setDocument(new JTextFieldLimit(30));
		panel.add(txtAddress);

		lblPoNo = new JLabel("PO No");
		lblPoNo.setBounds(50, 200, 150, 25);
		panel.add(lblPoNo);

		txtPoNo = new JTextField();
		txtPoNo.setBounds(220, 200, 150, 25);
		txtPoNo.setDocument(new JTextFieldLimit(30));
		txtPoNo.setEnabled(false);
		panel.add(txtPoNo);

		lblPoDate = new JLabel("PO Date");
		lblPoDate.setBounds(50, 230, 150, 25);
		panel.add(lblPoDate);

		poDateChooser = new JDateChooser(new Date());
		poDateChooser.setBounds(220, 230, 150, 25);
		poDateChooser.setEnabled(false);
		panel.add(poDateChooser);

		lblSoNo = new JLabel("SO No");
		lblSoNo.setBounds(50, 260, 150, 25);
		panel.add(lblSoNo);

		txtSoNo = new JTextField();
		txtSoNo.setBounds(220, 260, 150, 25);
		txtSoNo.setEnabled(false);
		panel.add(txtSoNo);

		lblSoDate = new JLabel("SO Date");
		lblSoDate.setBounds(50, 290, 150, 25);
		panel.add(lblSoDate);

		soDateChooser = new JDateChooser(new Date());
		soDateChooser.setBounds(220, 290, 150, 25);
		soDateChooser.setEnabled(false);
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
		cbCurrency.setBounds(220, 320, 150, 25);
		cbCurrency.setEnabled(false);
		panel.add(cbCurrency);

		lblErrorCurrency = new JLabel();
		lblErrorCurrency.setForeground(Color.RED);
		lblErrorCurrency.setBounds(425, 320, 225, 25);
		panel.add(lblErrorCurrency);

		lblBreadcrumb = new JLabel("ERP > Penjualan > Sales");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("View Detail");
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

		btnAddSalesDetail = new JButton("Tambah");
		btnAddSalesDetail.setEnabled(false);
		btnAddSalesDetail.setBounds(820, 320, 100, 25);
		panel.add(btnAddSalesDetail);

		btnDeleteSalesDetail = new JButton("Hapus");
		btnDeleteSalesDetail.setEnabled(false);
		btnDeleteSalesDetail.setBounds(925, 320, 100, 25);
		panel.add(btnDeleteSalesDetail);

		lblSurcharge = new JLabel("Customer Surcharge");
		lblSurcharge.setBounds(50, 570, 150, 25);
		panel.add(lblSurcharge);

		txtSurcharge = new NumberField(12);
		txtSurcharge.setBounds(220, 570, 150, 25);
		txtSurcharge.setEnabled(false);
		panel.add(txtSurcharge);

		lblDiscount = new JLabel("Customer Discount");
		lblDiscount.setBounds(50, 600, 150, 25);
		panel.add(lblDiscount);

		txtDiscount = new NumberField(12);
		txtDiscount.setBounds(220, 600, 150, 25);
		txtDiscount.setEnabled(false);
		panel.add(txtDiscount);

		lblFreightCost = new JLabel("<html>Freight Cost <font color=\"red\">*</font></html>");
		lblFreightCost.setBounds(50, 630, 150, 25);
		panel.add(lblFreightCost);

		txtFreightCost = new NumberField(12);
		txtFreightCost.setBounds(220, 630, 150, 25);
		txtFreightCost.setEnabled(false);
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
		cbFcCurrency.setBounds(390, 630, 150, 25);
		cbFcCurrency.setEnabled(false);
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
		txtInsuranceCost.setEnabled(false);
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
		cbIcCurrency.setBounds(390, 660, 150, 25);
		cbIcCurrency.setEnabled(false);
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
		txtVat.setEnabled(false);
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
		panel.add(txtTotalWeight);

		lblTotalVolume = new JLabel("Total Volume");
		lblTotalVolume.setBounds(50, 750, 150, 25);
		panel.add(lblTotalVolume);

		txtTotalVolume = new JTextField();
		txtTotalVolume.setBounds(220, 750, 150, 25);
		txtTotalVolume.setDocument(new JTextFieldLimit(30));
		txtTotalVolume.setEnabled(false);
		panel.add(txtTotalVolume);

		lblTotalItem = new JLabel("Total Item");
		lblTotalItem.setBounds(50, 780, 150, 25);
		panel.add(lblTotalItem);

		txtTotalItem = new JTextField();
		txtTotalItem.setBounds(220, 780, 150, 25);
		txtTotalItem.setDocument(new JTextFieldLimit(30));
		txtTotalItem.setEnabled(false);
		panel.add(txtTotalItem);

		lblGrossAmount = new JLabel("Gross Amount");
		lblGrossAmount.setBounds(50, 810, 150, 25);
		panel.add(lblGrossAmount);

		txtGrossAmount = new JTextField();
		txtGrossAmount.setBounds(220, 810, 150, 25);
		txtGrossAmount.setDocument(new JTextFieldLimit(30));
		txtGrossAmount.setEnabled(false);
		panel.add(txtGrossAmount);

		lblNettAmount = new JLabel("Net Amount");
		lblNettAmount.setBounds(50, 840, 150, 25);
		panel.add(lblNettAmount);

		txtNettAmount = new JTextField();
		txtNettAmount.setBounds(220, 840, 150, 25);
		txtNettAmount.setDocument(new JTextFieldLimit(30));
		txtNettAmount.setEnabled(false);
		panel.add(txtNettAmount);

		lblDescription = new JLabel("Description");
		lblDescription.setBounds(50, 870, 150, 25);
		panel.add(lblDescription);

		txtDescription = new JTextArea();
		txtDescription.setBounds(220, 870, 150, 85);
		Border border = BorderFactory.createLineBorder(Color.gray);
		txtDescription.setBorder(border);
		txtDescription.setEnabled(false);
		txtDescription.setBackground(UIManager.getColor("Textfield.background"));
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

		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPrint();
			}
		});
		btnPrint.setBounds(715, 1000, 100, 25);
		panel.add(btnPrint);

		btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showDeleteChoice();
				if (response == JOptionPane.YES_OPTION) {
					doDelete();
				}
			}
		});
		btnDelete.setBounds(820, 1000, 100, 25);
		panel.add(btnDelete);

		btnEdit = new JButton("Ubah");
		btnEdit.setBounds(925, 1000, 100, 25);
		panel.add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.sales.ui.SalesEditPanel", sales);
			}
		});

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.sales.ui.SalesListPanel");
			}
		});
		btnCancel.setBounds(50, 1000, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
	}

	protected void loadData(Integer salesId) {
		try {
			sales = ServiceFactory.getSalesBL().getSalesById(salesId);
			listOfSalesDetail = ServiceFactory.getSalesBL().getSalesDetailBySalesId(salesId);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			if (sales != null) {
				txtCustCode.setText(sales.getCustomer().getCustCode());
				txtCustName.setText(sales.getCustomer().getCustName());
				txtCreatedBy.setText(sales.getInputBy());
				txtCreatedOn.setText(dateFormat.format(sales.getInputDate()));
				cbCustAddress.addItem(sales.getCustAddress());
				cbCustAddress.setSelectedIndex(0);
				txtAddress.setText(sales.getCustAddress().getAddress());
				txtPoNo.setText(sales.getPoNo());
				poDateChooser.setDate(sales.getPoDate());
				txtSoNo.setText(sales.getSoNo());
				soDateChooser.setDate(sales.getSoDate());
				cbCurrency.addItem(sales.getCurrency().getCurrency());
				cbCurrency.setSelectedIndex(1);
				cbFcCurrency.addItem(sales.getFcCurrency().getCurrency());
				cbFcCurrency.setSelectedIndex(1);
				cbIcCurrency.addItem(sales.getIcCurrency().getCurrency());
				cbIcCurrency.setSelectedIndex(1);
				txtSurcharge.setText(Double.toString(sales.getSurcharge()));
				txtDiscount.setText(Double.toString(sales.getDiscount()));
				txtFreightCost.setText(Double.toString(sales.getFreightCost()));
				txtInsuranceCost.setText(Double.toString(sales.getInsuranceCost()));
				txtVat.setText(Double.toString(sales.getVat()));
				txtDescription.setText(sales.getDescription());
				
				refreshTableSalesDetail();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
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
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void doPrint() {

	}

	protected void doDelete() {
		try {
			ServiceFactory.getSalesBL().deleteAll(sales);
			DialogBox.showDelete();
			MainPanel.changePanel("module.customer.ui.SalesListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
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
			return 7;
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
			default:
				return "";
			}
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.sales = (Sales) objects[0];

		loadData(sales.getId());
	}
}
