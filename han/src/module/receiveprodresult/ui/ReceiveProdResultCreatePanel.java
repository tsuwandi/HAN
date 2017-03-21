package module.receiveprodresult.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.receiveprodresult.model.RPRNote;
import module.receiveprodresult.model.RPRProduct;
import module.receiveprodresult.model.ReceiveProdResult;
import module.receiveprodresult.model.ReceiveProdResult;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;

public class ReceiveProdResultCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ReceiveProdResultCreatePanel.class);

	JLabel lblPurchaseProductResultCode;
	JLabel lblPurchaseDate;
	JLabel lblReceiveProductResultCode;
	JLabel lblReceiveDate;
	
	JButton btnInsertRPRProduct;
	JButton btnDeleteRPRProduct;
	
	JButton btnInsertRPRNote;
	JButton btnDeleteRPRNote;
	
	JButton btnCancel;
	JButton btnSave;
	JButton btnDelete;
	JButton btnPrint;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtPurchaseProductResultCode;
	JTextField txtReceiveProductResultCode;
	JDateChooser dcPurchaseDate;
	JDateChooser dcReceiveDate;

	JLabel lblErrorPurchaseProductResultCode;
	JLabel lblErrorPurchaseDate;
	JLabel lblErrorReceiveProductResultCode;
	JLabel lblErrorReceiveDate;

	ReceiveProdResult receiveProductResult;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<RPRProduct> listOfRPRProduct = null;
	List<RPRNote> listOfRPRNote = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JScrollPane scrollPaneRPRNote;
	JTable tblRPRNote;
	
	JScrollPane scrollPaneRPRProduct;
	JTable tblRPRProduct;

	RPRProductTableModel rprProductTableModel = null;
	RPRNoteTableModel rprNoteTableModel = null;

	private ReceiveProdResultCreatePanel rprCreatePanel;
	
	JLabel lblRPRProduct;
	JLabel lblRPRNote;
	

	public List<RPRProduct> listOfDeletedRPRProduct = new ArrayList<RPRProduct>();
	public List<RPRNote> listOfDeletedRPRNote = new ArrayList<RPRNote>();

	public ReceiveProdResultCreatePanel() {
		rprCreatePanel = this;
		receiveProductResult = new ReceiveProdResult();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 700));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Input Penerimaan Hasil Produksi");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblPurchaseProductResultCode = new JLabel("<html>Kode Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseProductResultCode.setBounds(50, 80, 150, 25);
		panel.add(lblPurchaseProductResultCode);

		txtPurchaseProductResultCode = new JTextField();
		txtPurchaseProductResultCode.setBounds(220, 80, 150, 25);
		txtPurchaseProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtPurchaseProductResultCode.getDocument()).setDocumentFilter(filter);
		txtPurchaseProductResultCode.setEnabled(false);
		panel.add(txtPurchaseProductResultCode);
		
		lblErrorPurchaseProductResultCode = new JLabel();
		lblErrorPurchaseProductResultCode.setForeground(Color.RED);
		lblErrorPurchaseProductResultCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorPurchaseProductResultCode);
		
		lblPurchaseDate = new JLabel("<html>Tanggal Pembelian <font color=\"red\">*</font></html>");
		lblPurchaseDate.setBounds(50, 110, 150, 25);
		panel.add(lblPurchaseDate);

		dcPurchaseDate = new JDateChooser();
		dcPurchaseDate.setBounds(220, 110, 150, 25);
		dcPurchaseDate.setEnabled(false);
		dcPurchaseDate.setDateFormatString("dd-MM-yyyy");
		panel.add(dcPurchaseDate);

		lblErrorPurchaseDate = new JLabel();
		lblErrorPurchaseDate.setForeground(Color.RED);
		lblErrorPurchaseDate.setBounds(425, 110, 225, 25);
		panel.add(lblErrorPurchaseDate);
		
		lblReceiveProductResultCode = new JLabel("<html>Kode Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveProductResultCode.setBounds(50, 140, 150, 25);
		panel.add(lblReceiveProductResultCode);

		txtReceiveProductResultCode = new JTextField();
		txtReceiveProductResultCode.setBounds(220, 140, 150, 25);
		txtReceiveProductResultCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtReceiveProductResultCode.getDocument()).setDocumentFilter(filter);
		txtReceiveProductResultCode.setEnabled(false);
		panel.add(txtReceiveProductResultCode);
		
		lblErrorReceiveProductResultCode = new JLabel();
		lblErrorReceiveProductResultCode.setForeground(Color.RED);
		lblErrorReceiveProductResultCode.setBounds(425, 140, 225, 25);
		panel.add(lblErrorReceiveProductResultCode);
		
		lblReceiveDate = new JLabel("<html>Tanggal Penerimaan <font color=\"red\">*</font></html>");
		lblReceiveDate.setBounds(50, 170, 150, 25);
		panel.add(lblReceiveDate);

		dcReceiveDate = new JDateChooser();
		dcReceiveDate.setBounds(220, 170, 150, 25);
		dcReceiveDate.setDateFormatString("dd-MM-yyyy");
		dcReceiveDate.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			               makeCodeNumber(dcReceiveDate.getDate());
			            }
			        }
			    });
		panel.add(dcReceiveDate);

		lblErrorReceiveDate = new JLabel();
		lblErrorReceiveDate.setForeground(Color.RED);
		lblErrorReceiveDate.setBounds(425, 170, 225, 25);
		panel.add(lblErrorReceiveDate);
		
		lblRPRNote = new JLabel("Nota");
		lblRPRNote.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRPRNote.setBounds(50, 220, 150, 25);
		panel.add(lblRPRNote);
		
		btnInsertRPRNote = new JButton("Tambah");
		btnInsertRPRNote.setBounds(820, 220, 100, 25);
		btnInsertRPRNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddRPRNoteDialog(rprCreatePanel);
			}
		});
		panel.add(btnInsertRPRNote);

		btnDeleteRPRNote = new JButton("Hapus");
		btnDeleteRPRNote.setBounds(925, 220, 100, 25);
		btnDeleteRPRNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteRPRNote();
			}
		});
		panel.add(btnDeleteRPRNote);
		
		scrollPaneRPRNote = new JScrollPane();
		scrollPaneRPRNote.setBounds(50, 260, 975, 150);
		panel.add(scrollPaneRPRNote);
		
		listOfRPRNote = new ArrayList<RPRNote>();
		rprNoteTableModel = new RPRNoteTableModel(listOfRPRNote);
		tblRPRNote = new JTable(rprNoteTableModel);
		tblRPRNote.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblRPRNote.setFocusable(false);
		tblRPRNote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblRPRNote.getValueAt(tblRPRNote.getSelectedRow(), 0).equals(true))
					listOfRPRNote.get(tblRPRNote.getSelectedRow()).setFlag(false);
				else
					listOfRPRNote.get(tblRPRNote.getSelectedRow()).setFlag(true);

				tblRPRNote.updateUI();
			}
		});
		scrollPaneRPRNote.setViewportView(tblRPRNote);

		
		lblRPRProduct = new JLabel("Hasil Produksi");
		lblRPRProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRPRProduct.setBounds(50, 430, 150, 25);
		panel.add(lblRPRProduct);

		btnInsertRPRProduct = new JButton("Tambah");
		btnInsertRPRProduct.setBounds(820, 430, 100, 25);
		btnInsertRPRProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddRPRProductDialog(rprCreatePanel);
			}
		});
		panel.add(btnInsertRPRProduct);

		btnDeleteRPRProduct = new JButton("Hapus");
		btnDeleteRPRProduct.setBounds(925, 430, 100, 25);
		btnDeleteRPRProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteRPRProduct();
			}
		});
		panel.add(btnDeleteRPRProduct);

		scrollPaneRPRProduct = new JScrollPane();
		scrollPaneRPRProduct.setBounds(50, 470, 975, 150);
		panel.add(scrollPaneRPRProduct);

		listOfRPRProduct = new ArrayList<RPRProduct>();
		rprProductTableModel = new RPRProductTableModel(listOfRPRProduct);
		tblRPRProduct = new JTable(rprProductTableModel);
		tblRPRProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblRPRProduct.setFocusable(false);
		tblRPRProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblRPRProduct.getValueAt(tblRPRProduct.getSelectedRow(), 0).equals(true))
					listOfRPRProduct.get(tblRPRProduct.getSelectedRow()).setFlag(false);
				else
					listOfRPRProduct.get(tblRPRProduct.getSelectedRow()).setFlag(true);

				tblRPRProduct.updateUI();
				
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4) {
						showEditRPRProductDialog(listOfRPRProduct.get(row), rprCreatePanel, row);
					}
				}
			}
		});
		scrollPaneRPRProduct.setViewportView(tblRPRProduct);
		
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
		btnSave.setBounds(925, 640, 100, 25);
		panel.add(btnSave);
		
//		btnPrint = new JButton("Cetak");
//		btnPrint.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				//doPrint();
//			}
//		});
//		btnPrint.setBounds(820, 640, 100, 25);
//		panel.add(btnPrint);
		
//		btnDelete = new JButton("Hapus");
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				int response = DialogBox.showDeleteChoice();
//				if (response == JOptionPane.YES_OPTION) {
//					doDelete();
//				}
//			}
//		});
//		btnDelete.setBounds(820, 560, 100, 25);
//		panel.add(btnDelete);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int response = DialogBox.showCloseChoice();
				 if (response == JOptionPane.YES_OPTION) {
					 MainPanel.changePanel("module.receiveprodresult.ui.ReceiveProdResultListPanel");
				 }
			}
		});
		btnCancel.setBounds(50, 640, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(scrollPane);

	}
	
	protected void doSave() {
		if(AppConstants.NEW.equals(receiveProductResult.getStatus())) {
			try {
				receiveProductResult = new ReceiveProdResult();
				receiveProductResult.setPprCode(txtPurchaseProductResultCode.getText());
				receiveProductResult.setRprCode(txtReceiveProductResultCode.getText());
				receiveProductResult.setReceiveDate(dcReceiveDate.getDate());
				ServiceFactory.getReceiveProductResultBL().save(receiveProductResult, listOfRPRProduct, listOfRPRNote);
				DialogBox.showInsert();
				MainPanel.changePanel("module.receiveprodresult.ui.ReceiveProdResultListPanel");
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}
		} else if(AppConstants.COMPLETED.equals(receiveProductResult.getStatus())) {
			try {
				receiveProductResult.setPprCode(txtPurchaseProductResultCode.getText());
				receiveProductResult.setRprCode(txtReceiveProductResultCode.getText());
				receiveProductResult.setReceiveDate(dcReceiveDate.getDate());
				ServiceFactory.getReceiveProductResultBL().update(receiveProductResult, listOfRPRProduct, listOfDeletedRPRProduct, listOfRPRNote, listOfDeletedRPRNote);
				DialogBox.showEdit();
				MainPanel.changePanel("module.receiveprodresult.ui.ReceiveProdResultViewPanel", receiveProductResult);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				DialogBox.showErrorException();
			}
		}
	}
	
	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorPurchaseProductResultCode.setText("");
		lblErrorReceiveProductResultCode.setText("");
		lblErrorPurchaseDate.setText("");
		lblErrorReceiveDate.setText("");
		
		if (txtPurchaseProductResultCode.getText() == null || txtPurchaseProductResultCode.getText().length() == 0) {
			lblErrorPurchaseProductResultCode.setText("Textbox Kode Pembelian harus diisi.");
			isValid = false;
		}
		
		if (dcPurchaseDate.getDate() == null) {
			lblErrorPurchaseDate.setText("Tanggal Pembelian harus dipilih.");
			isValid = false;
		}
		
		if (txtReceiveProductResultCode.getText() == null || txtReceiveProductResultCode.getText().length() == 0) {
			lblErrorReceiveProductResultCode.setText("Textbox Kode Penerimaan harus diisi.");
			isValid = false;
		} else {
			try {
				if (AppConstants.NEW.equals(receiveProductResult.getStatus())){
					if (ServiceFactory.getReceiveProductResultBL().isRPRCodeExists(txtReceiveProductResultCode.getText()) > 0) {
						lblErrorReceiveProductResultCode.setText("Kode Penerimaan sudah pernah diinput.");
						isValid = false;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DialogBox.showErrorException();
				isValid = false;
			}
		}
		
		if (dcReceiveDate.getDate() == null) {
			lblErrorReceiveDate.setText("Tanggal Penerimaan harus dipilih.");
			isValid = false;
		}
		
		return isValid;
	}
	
	protected void showAddRPRProductDialog(ReceiveProdResultCreatePanel rprCreatePanel) {
		RPRProductDialog rprProductDialog = new RPRProductDialog(false, new RPRProduct(),
				rprCreatePanel, null);
		rprProductDialog.setTitle("Barang");
		rprProductDialog.setLocationRelativeTo(null);
		rprProductDialog.setVisible(true);
	}

	protected void showEditRPRProductDialog(RPRProduct rprProduct,
			ReceiveProdResultCreatePanel rprCreatePanel, Integer index) {
		RPRProductDialog rprProductDialog = new RPRProductDialog(true, rprProduct, rprCreatePanel, index);
		rprProductDialog.setTitle("Barang");
		rprProductDialog.setLocationRelativeTo(null);
		rprProductDialog.setVisible(true);
	}

	protected void doDeleteRPRProduct() {
		if (listOfRPRProduct.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<RPRProduct> temp = new ArrayList<RPRProduct>();
			for (RPRProduct s : listOfRPRProduct) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfRPRProduct.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (RPRProduct s : temp) {
					listOfDeletedRPRProduct.add(s);
					listOfRPRProduct.remove(s);
				}
				refreshTableRPRProduct();
				DialogBox.showDelete();
			}
		}
	}
	
	protected String doCalculateSubTotal()
	{
		BigDecimal total = new BigDecimal("0");
//		for (PPRProduct pprProduct : listOfRPRProduct) {
//			total = total.add(pprProduct.getSubTotal());
//		}
		
		return total.toString();
	}
	
	protected void showViewPPRProductDialog(RPRProduct pprProduct,
			ReceiveProdResultCreatePanel pprViewPanel, Integer index) {
//		PPRProductDialog pprProductDialog = new PPRProductDialog(true, pprProduct, pprViewPanel, index);
//		pprProductDialog.setTitle("Barang");
//		pprProductDialog.setLocationRelativeTo(null);
//		pprProductDialog.setVisible(true);
	}
	
	protected void doDelete() {
		try {
			ServiceFactory.getReceiveProductResultBL().deleteAll(receiveProductResult);
			DialogBox.showDelete();
			MainPanel.changePanel("module.purchaseprodresult.ui.ReceiveProdResultListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for PPR Product table
	 * 
	 * @author TSI
	 *
	 */
	class RPRProductTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<RPRProduct> listOfRPRProduct;

		public RPRProductTableModel(List<RPRProduct> listOfRPRProduct) {
			this.listOfRPRProduct = listOfRPRProduct;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfRPRProduct.size();
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
		 * @return ({@link PPRProduct}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			RPRProduct p = listOfRPRProduct.get(rowIndex);
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
			case 6:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	public void refreshTableRPRProduct() {
		try {
			tblRPRProduct.setModel(new RPRProductTableModel(listOfRPRProduct));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	protected void showAddRPRNoteDialog(ReceiveProdResultCreatePanel rprCreatePanel) {
		RPRNoteDialog rprNoteDialog = new RPRNoteDialog(false, new RPRNote(),
				rprCreatePanel, null);
		rprNoteDialog.setTitle("Nota");
		rprNoteDialog.setLocationRelativeTo(null);
		rprNoteDialog.setVisible(true);
	}

	protected void showEditRPRNoteDialog(RPRNote rprNote,
			ReceiveProdResultCreatePanel pprCreatePanel, Integer index) {
		RPRNoteDialog pprNoteDialog = new RPRNoteDialog(true, rprNote, rprCreatePanel, index);
		pprNoteDialog.setTitle("Nota");
		pprNoteDialog.setLocationRelativeTo(null);
		pprNoteDialog.setVisible(true);
	}
	
	/**
	 * Class as TableModel for PPR Note table
	 * 
	 * @author TSI
	 *
	 */
	class RPRNoteTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<RPRNote> listOfRPRNote;

		public RPRNoteTableModel(List<RPRNote> listOfRPRNote) {
			this.listOfRPRNote = listOfRPRNote;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfRPRNote.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 2;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link PPRNote}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			RPRNote p = listOfRPRNote.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getNote();
//			case 2:
//				return "<html><u>View</u></html>";
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
//			case 2:
//				return String.class;
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
				return "Note";
//			case 2:
//				return "Tindakan";
			default:
				return "";
			}
		}
	}
	
	public void refreshTableRPRNote() {
		try {
			tblRPRNote.setModel(new RPRNoteTableModel(listOfRPRNote));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	public void makeCodeNumber(Date receiveDate) {
		final String constant = "STTB";

		Calendar cal = Calendar.getInstance();
		if(AppConstants.NEW.equals(receiveProductResult.getStatus())) {
			if(receiveDate != null) {
				cal.setTime(receiveDate);
				
				String date = String.valueOf(cal.get(Calendar.DATE));
				String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
				String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		
				String ordinal = null;
				try {
					ordinal = ServiceFactory.getReceiveProductResultBL().getOrdinalOfCodeNumber(Integer.valueOf(year));
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
					DialogBox.showErrorException();
				}
				
				txtReceiveProductResultCode.setText(new StringBuilder().append(ordinal).append("/").append(constant)
						.append("/").append(date).append("/").append(month)
						.append("/").append(year).toString());
				updateUI();
			}
		}
	}
	
	protected void doDeleteRPRNote() {
		if (listOfRPRNote.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<RPRNote> temp = new ArrayList<RPRNote>();
			for (RPRNote s : listOfRPRNote) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfRPRNote.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (RPRNote s : temp) {
					listOfDeletedRPRNote.add(s);
					listOfRPRNote.remove(s);
				}
				refreshTableRPRNote();
				DialogBox.showDelete();
			}
		}
	}
	
	
	@Override
	public void invokeObjects(Object... objects) {
		this.receiveProductResult = (ReceiveProdResult) objects[0];

		loadData(receiveProductResult.getId(), receiveProductResult.getStatus());
	}
	
	private String statusApp;
	
	protected void loadData(Integer pprId, String status) {
		try {
			if(AppConstants.NEW.equals(status)) {
				receiveProductResult = ServiceFactory.getReceiveProductResultBL().getPPRById(pprId);
				statusApp = AppConstants.NEW;
				if (receiveProductResult != null) {
					listOfRPRProduct = ServiceFactory.getReceiveProductResultBL().getPPRProductByPPRCode(receiveProductResult.getPurchaseProdResult().getPprCode());
					listOfRPRNote = new ArrayList<RPRNote>();
				}
			} else {
				receiveProductResult = ServiceFactory.getReceiveProductResultBL().getRPRById(pprId);
				if (receiveProductResult != null) {
					listOfRPRProduct = ServiceFactory.getReceiveProductResultBL().getRPRProductByRPRCode(receiveProductResult.getRprCode());
					listOfRPRNote = ServiceFactory.getReceiveProductResultBL().getRPRNoteByRPRCode(receiveProductResult.getRprCode());
				}
			}
		
			if (receiveProductResult != null) {
				txtPurchaseProductResultCode.setText(receiveProductResult.getPprCode());
				dcPurchaseDate.setDate(receiveProductResult.getPurchaseProdResult().getPurchaseDate());
				if(receiveProductResult.getRprCode() != null)
					txtReceiveProductResultCode.setText(receiveProductResult.getRprCode());
				if(receiveProductResult.getReceiveDate() != null)
					dcReceiveDate.setDate(receiveProductResult.getReceiveDate());
				
				if(AppConstants.COMPLETED.equals(receiveProductResult.getStatus()))
				{
					dcReceiveDate.setEnabled(false);
				} 
				if (AppConstants.NEW.equals(receiveProductResult.getStatus())){
					if(dcReceiveDate.getDate() != null) {
					makeCodeNumber(dcReceiveDate.getDate());
					
					
					}
				}
				refreshTableRPRProduct();
				refreshTableRPRNote();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
}
 