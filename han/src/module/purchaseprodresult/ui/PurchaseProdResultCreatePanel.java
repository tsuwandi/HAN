package module.purchaseprodresult.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import module.purchaseprodresult.model.PPRProduct;
import module.purchaseprodresult.model.PurchaseProdResult;
import module.sn.currency.model.Currency;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class PurchaseProdResultCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
	}

	private static final Logger LOGGER = Logger
			.getLogger(PurchaseProdResultCreatePanel.class);

	JLabel lblPurchaseProductResultCode;
	JLabel lblSupplier;
	JLabel lblPurchaseDate;
	JLabel lblTotal;
	JTextField txtTotal;

	JButton btnInsertPPRProduct;
	JButton btnDeletePPRProduct;

	JButton btnInsertPPRNote;
	JButton btnDeletePPRNote;
	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtPurchaseProductResultCode;
	ComboBox<Supplier> cbSupplier;
	JDateChooser dcPurchaseDate;

	JLabel lblErrorPurchaseProductResultCode;
	JLabel lblErrorSupplier;
	JLabel lblErrorPurchaseDate;

	PurchaseProdResult purchaseProductResult;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<Supplier> listOfSupplier = null;

	List<PPRProduct> listOfPPRProduct = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPanePPRProduct;
	JTable tblPPRProduct;

	PPRProductTableModel pprProductTableModel = null;

	PurchaseProdResultCreatePanel pprCreatePanel;

	JLabel lblPPRProduct;

	public PurchaseProdResultCreatePanel() {
		purchaseProductResult = new PurchaseProdResult();

		pprCreatePanel = this;

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Hasil Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru PO Hasil Produksi");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblPurchaseProductResultCode = new JLabel(
				"<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductResultCode.setBounds(50, 80, 150, 25);
		panel.add(lblPurchaseProductResultCode);

		txtPurchaseProductResultCode = new JTextField();
		txtPurchaseProductResultCode.setBounds(220, 80, 150, 25);
		txtPurchaseProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtPurchaseProductResultCode.getDocument())
				.setDocumentFilter(filter);
		txtPurchaseProductResultCode.setEnabled(false);
		panel.add(txtPurchaseProductResultCode);

		lblErrorPurchaseProductResultCode = new JLabel();
		lblErrorPurchaseProductResultCode.setForeground(Color.RED);
		lblErrorPurchaseProductResultCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorPurchaseProductResultCode);

		lblSupplier = new JLabel(
				"<html>Supplier <font color=\"red\">*</font></html>");
		lblSupplier.setBounds(50, 110, 150, 25);
		panel.add(lblSupplier);

		listOfSupplier = new ArrayList<Supplier>();
		try {
			listOfSupplier = ServiceFactory.getPurchaseProductResultBL()
					.getAllSupplierBySuppTypeId(
							AppConstants.SUPP_TYPE_ID_HASIL_PRODUKSI);
			listOfSupplier.add(0, new Supplier("-- Pilih Supplier --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbSupplier = new ComboBox<Supplier>();
		cbSupplier.setList(listOfSupplier);
		cbSupplier.setBounds(220, 110, 150, 25);
		panel.add(cbSupplier);

		lblErrorSupplier = new JLabel();
		lblErrorSupplier.setForeground(Color.RED);
		lblErrorSupplier.setBounds(425, 110, 225, 25);
		panel.add(lblErrorSupplier);

		lblPurchaseDate = new JLabel(
				"<html>Tanggal Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseDate.setBounds(50, 140, 150, 25);
		panel.add(lblPurchaseDate);

		dcPurchaseDate = new JDateChooser();
		dcPurchaseDate.setBounds(220, 140, 150, 25);
		dcPurchaseDate.setDateFormatString("dd-MM-yyyy");

		dcPurchaseDate.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							makeCodeNumber(dcPurchaseDate.getDate());
						}
					}
				});
		makeCodeNumber(dcPurchaseDate.getDate());
		panel.add(dcPurchaseDate);

		lblErrorPurchaseDate = new JLabel();
		lblErrorPurchaseDate.setForeground(Color.RED);
		lblErrorPurchaseDate.setBounds(425, 140, 225, 25);
		panel.add(lblErrorPurchaseDate);

		lblPPRProduct = new JLabel("Hasil Produksi");
		lblPPRProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPPRProduct.setBounds(50, 220, 150, 25);
		panel.add(lblPPRProduct);

		btnInsertPPRProduct = new JButton("Tambah");
		btnInsertPPRProduct.setBounds(820, 220, 100, 25);
		btnInsertPPRProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddPPRProductDialog(pprCreatePanel);
			}
		});
		panel.add(btnInsertPPRProduct);

		btnDeletePPRProduct = new JButton("Hapus");
		btnDeletePPRProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeletePPRProduct();
			}
		});
		btnDeletePPRProduct.setBounds(925, 220, 100, 25);
		panel.add(btnDeletePPRProduct);

		scrollPanePPRProduct = new JScrollPane();
		scrollPanePPRProduct.setBounds(50, 260, 975, 150);
		panel.add(scrollPanePPRProduct);

		listOfPPRProduct = new ArrayList<PPRProduct>();
		pprProductTableModel = new PPRProductTableModel(listOfPPRProduct);
		tblPPRProduct = new JTable(pprProductTableModel);
		tblPPRProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPPRProduct.setFocusable(false);
		tblPPRProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblPPRProduct.getValueAt(tblPPRProduct.getSelectedRow(), 0)
						.equals(true))
					listOfPPRProduct.get(tblPPRProduct.getSelectedRow())
							.setFlag(false);
				else
					listOfPPRProduct.get(tblPPRProduct.getSelectedRow())
							.setFlag(true);

				tblPPRProduct.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 6) {
						showEditPPRProductDialog(listOfPPRProduct.get(row),
								pprCreatePanel, row);
					}
				}
			}
		});
		scrollPanePPRProduct.setViewportView(tblPPRProduct);

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
		btnSave.setBounds(925, 560, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel
							.changePanel("module.purchaseprodresult.ui.PurchaseProdResultListPanel");
				}
			}
		});
		btnCancel.setBounds(50, 560, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		lblTotal = new JLabel("<html>Total <font color=\"red\">*</font></html>");
		lblTotal.setBounds(820, 430, 150, 25);
		panel.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(875, 430, 150, 25);
		txtTotal.setText("0.00");
		txtTotal.setEnabled(false);
		panel.add(txtTotal);

		scrollPane = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(scrollPane);

	}

	protected String doCalculateSubTotal() {
		BigDecimal total = new BigDecimal("0");
		for (PPRProduct pprProduct : listOfPPRProduct) {
			total = total.add(pprProduct.getSubTotal());
		}

		return total.toString();
	}

	protected void doSave() {
		purchaseProductResult = new PurchaseProdResult();
		purchaseProductResult
				.setPprCode(txtPurchaseProductResultCode.getText());
		purchaseProductResult.setSuppCode(cbSupplier.getDataIndex()
				.getSuppCode());
		purchaseProductResult.setPurchaseDate(dcPurchaseDate.getDate());

		try {
			ServiceFactory.getPurchaseProductResultBL().save(
					purchaseProductResult, listOfPPRProduct);
			DialogBox.showInsert();
			MainPanel
					.changePanel("module.purchaseprodresult.ui.PurchaseProdResultListPanel");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorPurchaseProductResultCode.setText("");
		lblErrorSupplier.setText("");
		lblErrorPurchaseDate.setText("");

		if (txtPurchaseProductResultCode.getText() == null
				|| txtPurchaseProductResultCode.getText().length() == 0) {
			lblErrorPurchaseProductResultCode
					.setText("Textbox Kode Pembelian harus diisi.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory
						.getPurchaseProductResultBL()
						.isPPRCodeExists(txtPurchaseProductResultCode.getText()) > 0) {
					lblErrorPurchaseProductResultCode
							.setText("Kode Pembelian sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		if (cbSupplier.getSelectedItem() == null
				|| cbSupplier.getSelectedIndex() == 0) {
			lblErrorSupplier.setText("Combobox Supplier harus dipilih.");
			isValid = false;
		}

		if (dcPurchaseDate.getDate() == null) {
			lblErrorPurchaseDate.setText("Tanggal Pembelian harus dipilih.");
			isValid = false;
		}

		return isValid;
	}

	protected void showAddPPRProductDialog(
			PurchaseProdResultCreatePanel pprCreatePanel) {
		PPRProductDialog pprProductDialog = new PPRProductDialog(false,
				new PPRProduct(), pprCreatePanel, null);
		pprProductDialog.setTitle("Barang");
		pprProductDialog.setLocationRelativeTo(null);
		pprProductDialog.setVisible(true);
	}

	protected void showEditPPRProductDialog(PPRProduct pprProduct,
			PurchaseProdResultCreatePanel pprCreatePanel, Integer index) {
		PPRProductDialog pprProductDialog = new PPRProductDialog(true,
				pprProduct, pprCreatePanel, index);
		pprProductDialog.setTitle("Barang");
		pprProductDialog.setLocationRelativeTo(null);
		pprProductDialog.setVisible(true);
	}

	protected void doDeletePPRProduct() {
		if (listOfPPRProduct.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<PPRProduct> temp = new ArrayList<PPRProduct>();
			for (PPRProduct s : listOfPPRProduct) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfPPRProduct.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (PPRProduct s : temp) {
					listOfPPRProduct.remove(s);
				}
				refreshTablePPRProduct();
				DialogBox.showDelete();
			}
		}
	}

	/**
	 * Class as TableModel for PPR Product table
	 * 
	 * @author TSI
	 *
	 */
	class PPRProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PPRProduct> listOfPPRProduct;

		public PPRProductTableModel(List<PPRProduct> listOfPPRProduct) {
			this.listOfPPRProduct = listOfPPRProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPPRProduct.size();
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
		 * @return ({@link PPRProduct}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PPRProduct p = listOfPPRProduct.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getProduct().getProductionType();
			case 2:
				return p.getProduct().getProductName();
			case 3:
				return p.getQty();
			case 4:
				return p.getUnitPrice();
			case 5:
				return p.getSubTotal();
			case 6:
				return "<html><u>View</u></html>";
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
				return BigDecimal.class;
			case 4:
				return BigDecimal.class;
			case 5:
				return BigDecimal.class;
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
				return "Tipe Hasil Produksi";
			case 2:
				return "Produk";
			case 3:
				return "Qty";
			case 4:
				return "Harga Satuan";
			case 5:
				return "Sub Total";
			case 6:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTablePPRProduct() {
		try {
			tblPPRProduct.setModel(new PPRProductTableModel(listOfPPRProduct));

			String total = doCalculateSubTotal();

			txtTotal.setText(total);
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void makeCodeNumber(Date producationDate) {
		final String constant = "POH";

		Calendar cal = Calendar.getInstance();
		if(producationDate != null) {
			cal.setTime(producationDate);

			String date = String.valueOf(cal.get(Calendar.DATE));
			String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
			String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);

			String ordinal = null;
			try {
				ordinal = ServiceFactory.getPurchaseProductResultBL()
						.getOrdinalOfCodeNumber(Integer.valueOf(year));
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}

			txtPurchaseProductResultCode.setText(new StringBuilder()
					.append(ordinal).append("/").append(constant).append("/")
					.append(date).append("/").append(month).append("/")
					.append(year).toString());
		}
		
	}
}
