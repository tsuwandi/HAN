package module.dryin.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.NumberFormat;
import main.panel.MainPanel;
import module.dryin.model.DryIn;
import module.dryin.model.DryInPallet;
import module.dryin.model.PicTally;
import module.pembelian.model.PalletCard;
import module.sn.chamber.model.Chamber;
import module.util.Bridging;
import module.util.DateUtil;

public class DryInEditPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DryInEditPanel.class);

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	JLabel lblDryInCode;
	JLabel lblDateIn;
	JLabel lblChamber;

	JLabel lblErrorDryInCode;
	JLabel lblErrorDateIn;
	JLabel lblErrorChamber;
	JLabel lblErrorPalletCard;

	JLabel lblPicTally;
	JLabel lblPalletCardCode;
	JLabel lblRitNo;
	JLabel lblPalletCardCodeConstant;
	JLabel lblDate;
	JLabel lblMonth;
	JLabel lblYear;
	JLabel lblOrdinal;
	JLabel lblTotalVolumePalletCard;
	JLabel lblTotalVolumeUomPalletCard;
	JLabel lblTotalVolume;
	JLabel lblTotalVolumeUom;

	JTextField txtDryInCode;
	JDateChooser dcDateIn;
	ComboBox<String> cbDateInHour;
	ComboBox<String> cbDateInMinute;
	ComboBox<Chamber> cbChamber;

	NumberField txtRitNo;
	NumberField txtDate;
	NumberField txtMonth;
	NumberField txtYear;
	NumberField txtOrdinal;
	JTextField txtTotalVolumePalletCard;
	JTextField txtTotalVolume;

	JScrollPane scrollPaneDryInPallet;
	JTable tblDryInPallet;
	JButton btnSearchPalletCard;
	JButton btnInsertDryInPallet;

	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	private DryInPalletTableModel dryInPalletTableModel;
	private List<DryInPallet> listOfDryInPallet;
	private PalletCard palletCard;
	private DryInEditPanel dryInCreatePanel;
	private DryIn dryIn;
	private List<Chamber> listOfChamber;
	public List<DryInPallet> listOfDeletedDryInPallet;

	@SuppressWarnings("deprecation")
	public DryInEditPanel() {
		dryInCreatePanel = this;

		setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(getPreferredSize());
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pengeringan > Pemasukan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Ubah");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblDryInCode = new JLabel("<html>Kode Pemasukan <font color=\"red\">*</font></html>");
		lblDryInCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		lblDryInCode.setBounds(50, 80, 150, 25);
		panel.add(lblDryInCode);

		txtDryInCode = new JTextField();
		txtDryInCode.setBounds(220, 80, 150, 25);
		txtDryInCode.setEnabled(false);
		panel.add(txtDryInCode);

		lblErrorDryInCode = new JLabel("");
		lblErrorDryInCode.setForeground(Color.RED);
		lblErrorDryInCode.setBounds(380, 80, 270, 25);
		panel.add(lblErrorDryInCode);

		lblDateIn = new JLabel("<html>Tanggal Masuk <font color=\"red\">*</font></html>");
		lblDateIn.setBounds(50, 110, 150, 25);
		panel.add(lblDateIn);

		dcDateIn = new JDateChooser(new Date());
		dcDateIn.setBounds(220, 110, 150, 25);
		panel.add(dcDateIn);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		cbDateInHour = new ComboBox<String>();
		for (int i = 0; i < 24; i++)
			cbDateInHour.addItem(String.format("%02d", i));
		cbDateInHour.setSelectedItem(String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)));
		cbDateInHour.setBounds(380, 110, 45, 25);
		panel.add(cbDateInHour);

		cbDateInMinute = new ComboBox<String>();
		for (int i = 0; i < 60; i++)
			cbDateInMinute.addItem(String.format("%02d", i));
		cbDateInMinute.setSelectedItem(String.format("%02d", cal.get(Calendar.MINUTE)));
		cbDateInMinute.setBounds(440, 110, 45, 25);
		panel.add(cbDateInMinute);

		lblErrorDateIn = new JLabel("");
		lblErrorDateIn.setForeground(Color.RED);
		lblErrorDateIn.setBounds(500, 110, 270, 25);
		panel.add(lblErrorDateIn);

		lblChamber = new JLabel("<html>Chamber <font color=\"red\">*</font></html>");
		lblChamber.setBounds(50, 140, 150, 25);
		panel.add(lblChamber);

		lblErrorChamber = new JLabel("");
		lblErrorChamber.setForeground(Color.RED);
		lblErrorChamber.setBounds(380, 140, 270, 25);
		panel.add(lblErrorChamber);
		try {
			listOfChamber = new ArrayList<Chamber>();
			listOfChamber = ServiceFactory.getDryInBL().getAllChamber();
			listOfChamber.add(0, new Chamber("-- Pilih Chamber --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbChamber = new ComboBox<Chamber>();
		cbChamber.addItem("-- Pilih Chamber --");
		cbChamber.setList(listOfChamber);
		cbChamber.setBounds(220, 140, 150, 25);
		panel.add(cbChamber);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddDryInPalletDialog(dryInCreatePanel);
			}
		});
		btnSearchPalletCard.setBounds(50, 175, 150, 25);
		panel.add(btnSearchPalletCard);

		lblPalletCardCode = new JLabel("Kode Kartu Pallet");
		lblPalletCardCode.setBounds(50, 205, 150, 25);
		panel.add(lblPalletCardCode);

		//////////////////
		lblRitNo = new JLabel("Rit No");
		lblRitNo.setBounds(220, 205, 150, 25);
		panel.add(lblRitNo);

		txtRitNo = new NumberField(4);
		txtRitNo.setBounds(220, 230, 50, 25);
		panel.add(txtRitNo);

		lblPalletCardCodeConstant = new JLabel(" / BL / ");
		lblPalletCardCodeConstant.setBounds(275, 230, 150, 25);
		panel.add(lblPalletCardCodeConstant);

		lblDate = new JLabel("Tanggal");
		lblDate.setBounds(310, 205, 150, 25);
		panel.add(lblDate);

		txtDate = new NumberField(2);
		txtDate.setBounds(310, 230, 50, 25);
		panel.add(txtDate);

		JLabel lblA = new JLabel(" / ");
		lblA.setBounds(370, 230, 150, 25);
		panel.add(lblA);

		lblMonth = new JLabel("Bulan");
		lblMonth.setBounds(390, 205, 150, 25);
		panel.add(lblMonth);

		txtMonth = new NumberField(2);
		txtMonth.setBounds(390, 230, 50, 25);
		panel.add(txtMonth);

		JLabel lblB = new JLabel(" / ");
		lblB.setBounds(450, 230, 150, 25);
		panel.add(lblB);

		lblYear = new JLabel("Tahun");
		lblYear.setBounds(470, 205, 150, 25);
		panel.add(lblYear);

		txtYear = new NumberField(2);
		txtYear.setBounds(470, 230, 50, 25);
		panel.add(txtYear);

		JLabel lblC = new JLabel(" / ");
		lblC.setBounds(530, 230, 150, 25);
		panel.add(lblC);

		lblOrdinal = new JLabel("Sequence");
		lblOrdinal.setBounds(220, 255, 150, 25);
		panel.add(lblOrdinal);

		txtOrdinal = new NumberField(4);
		txtOrdinal.setBounds(220, 280, 50, 25);
		panel.add(txtOrdinal);

		lblErrorPalletCard = new JLabel("");
		lblErrorPalletCard.setBounds(285, 405, 150, 25);
		lblErrorPalletCard.setForeground(Color.RED);
		panel.add(lblErrorPalletCard);

		lblTotalVolumePalletCard = new JLabel("Total Volume");
		lblTotalVolumePalletCard.setBounds(50, 315, 150, 25);
		panel.add(lblTotalVolumePalletCard);

		txtTotalVolumePalletCard = new JTextField();
		txtTotalVolumePalletCard.setBounds(220, 315, 150, 25);
		txtTotalVolumePalletCard.setEnabled(false);
		panel.add(txtTotalVolumePalletCard);

		lblTotalVolumeUomPalletCard = new JLabel("cm3");
		lblTotalVolumeUomPalletCard.setBounds(380, 315, 150, 25);
		panel.add(lblTotalVolumeUomPalletCard);

		btnInsertDryInPallet = new JButton("Insert");
		btnInsertDryInPallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsertDryInPallet();
			}
		});
		btnInsertDryInPallet.setBounds(220, 345, 100, 25);
		panel.add(btnInsertDryInPallet);

		scrollPaneDryInPallet = new JScrollPane();
		scrollPaneDryInPallet.setBounds(50, 375, 975, 150);
		panel.add(scrollPaneDryInPallet);

		listOfDryInPallet = new ArrayList<DryInPallet>();
		dryInPalletTableModel = new DryInPalletTableModel(listOfDryInPallet);
		tblDryInPallet = new JTable(dryInPalletTableModel);
		tblDryInPallet.setFocusable(false);
		tblDryInPallet.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblDryInPallet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 5)
						doDeleteDryInPallet(listOfDryInPallet.get(row));
				}
			}
		});
		scrollPaneDryInPallet.setViewportView(tblDryInPallet);

		lblTotalVolume = new JLabel("Total Volume");
		lblTotalVolume.setBounds(50, 535, 150, 25);
		panel.add(lblTotalVolume);

		txtTotalVolume = new JTextField();
		txtTotalVolume.setBounds(220, 535, 150, 25);
		txtTotalVolume.setEnabled(false);
		panel.add(txtTotalVolume);

		lblTotalVolumeUomPalletCard = new JLabel("cm3");
		lblTotalVolumeUomPalletCard.setBounds(380, 535, 150, 25);
		panel.add(lblTotalVolumeUomPalletCard);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
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
		btnSave.setBounds(925, 570, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.dryin.ui.DryInViewPanel", dryIn);
				}
			}
		});
		btnCancel.setBounds(49, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		txtRitNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtRitNo.setText(NumberFormat.onTypeNum(txtRitNo.getText().length(), txtRitNo.getText().toString()));

				if (txtOrdinal.getText().length() > 3)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtRitNo.setNextFocusableComponent(txtOrdinal);

		txtDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtDate.getText().length() > 1)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtMonth.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtMonth.getText().length() > 1)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtYear.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtYear.getText().length() > 1)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtOrdinal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtOrdinal.setText(
						NumberFormat.onTypeNum(txtOrdinal.getText().length(), txtOrdinal.getText().toString()));

				if (txtOrdinal.getText().length() > 3)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		makeDefaultDatePalletCardCode();

		listOfDeletedDryInPallet = new ArrayList<DryInPallet>();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dcDateIn.requestFocusInWindow();
			}
		});
	}

	public boolean doValidate() {
		boolean isValid = true;

		lblErrorDryInCode.setText("");
		lblErrorDateIn.setText("");
		lblErrorChamber.setText("");

		if (dcDateIn.getDate() == null) {
			lblErrorDateIn.setText("Tanggal harus dipilih.");
			isValid = false;
		}

		if ((cbDateInHour.getSelectedItem() == null) && (cbDateInMinute.getSelectedItem() == null)) {
			lblErrorDateIn.setText("Jam dan menit harus dipilih.");
			isValid = false;
		}

		if (cbChamber.getSelectedItem() == null || cbChamber.getSelectedIndex() == 0) {
			lblErrorChamber.setText("Combobox chamber harus dipilih.");
			isValid = false;
		}

		return isValid;
	}

	public void doSave() {

		dryIn.setDryInCode(txtDryInCode.getText());
		dryIn.setDateIn(
				DateUtil.setTimeStamp(dcDateIn.getDate(), Integer.parseInt(cbDateInHour.getSelectedItem().toString()),
						Integer.parseInt(cbDateInMinute.getSelectedItem().toString()), 0));
		dryIn.setChamberId(cbChamber.getDataIndex().getId());

		if (!txtTotalVolume.getText().equals(""))
			dryIn.setTotalVolume(Double.parseDouble(txtTotalVolume.getText()));
		else
			dryIn.setTotalVolume(0);

		try {
			ServiceFactory.getDryInBL().update(dryIn, listOfDryInPallet, listOfDeletedDryInPallet);
			DialogBox.showInsert();
			MainPanel.changePanel("module.dryin.ui.DryInViewPanel", dryIn);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void makeDefaultDatePalletCardCode() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

		txtDate.setText(date);
		txtMonth.setText(month);
		txtYear.setText(year);
	}

	/**
	 * Method to display add pallet card dialog
	 */
	protected void showAddDryInPalletDialog(DryInEditPanel dryInEditPanel) {
		DryInPalletDialog dryInPalletDialog = new DryInPalletDialog(null, dryInEditPanel);
		dryInPalletDialog.setTitle("Detail");
		dryInPalletDialog.setLocationRelativeTo(null);
		dryInPalletDialog.setVisible(true);
	}

	public void searchPalletCardByCode(String ritNo, String date, String month, String year, String ordinal) {
		final String constant = "BL";

		if ("".equals(ritNo) && "".equals(date) && "".equals(month) && "".equals(year) && "".equals(ordinal))
			return;

		String palletCardCode = new StringBuilder().append(ritNo).append("/").append(constant).append("/").append(date)
				.append("/").append(month).append("/").append(year).append("/").append(ordinal).toString();

		try {
			palletCard = new PalletCard();
			palletCard = ServiceFactory.getDryInBL().getPalletByPalletCardCode(palletCardCode);

			if (palletCard != null) {
				txtTotalVolumePalletCard.setText(String.valueOf(palletCard.getVolume()));
			} else {
				txtTotalVolumePalletCard.setText("");
				palletCard = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	protected void doInsertDryInPallet() {
		boolean isExists = false;
		for (DryInPallet dryInPallet : listOfDryInPallet) {
			if (dryInPallet.getPalletCard().equals(palletCard)) {
				DialogBox.showErrorIsExists();
				isExists = true;
				break;
			}
		}

		if (palletCard != null && isExists == false) {
			palletCard.setFlag(true);
			DryInPallet dryInPallet = new DryInPallet();
			dryInPallet.setPalletCard(palletCard);
			dryInPallet.setPalletCardCode(palletCard.getPalletCardCode());
			listOfDryInPallet.add(dryInPallet);

			clearPalletCard();

			refreshTableDryInPallet();

			countTotalVolumeDryInPalletCard();
		} else if (palletCard == null && isExists == false) {
			lblErrorPalletCard.setText("Data tidak ditemukan.");
		}
	}

	protected void doDeleteDryInPallet(DryInPallet dryInPallet) {
		listOfDeletedDryInPallet.add(dryInPallet);
		listOfDryInPallet.remove(dryInPallet);

		int index = 0;
		for (DryInPallet tDryInPallet : dryInPallets) {
			if (dryInPallet.getPalletCardCode().equals(tDryInPallet.getPalletCardCode())) {
				dryInPallet.getPalletCard().setFlag(false);
				dryInPallets.set(index, dryInPallet);
			}
			index++;
		}

		refreshTableDryInPallet();
		countTotalVolumeDryInPalletCard();
		DialogBox.showDelete();
	}

	public void clearPalletCard() {
		// clear
		txtRitNo.setText("");
		txtOrdinal.setText("");
		txtTotalVolumePalletCard.setText("");
		palletCard = null;
	}

	public void refreshTableDryInPallet() {
		try {
			tblDryInPallet.setModel(new DryInPalletTableModel(listOfDryInPallet));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	public void countTotalVolumeDryInPalletCard() {
		double totalVolume = 0.00;
		for (DryInPallet dryInPallet : listOfDryInPallet) {
			totalVolume += dryInPallet.getPalletCard().getVolume();
		}

		txtTotalVolume.setText(String.valueOf(totalVolume));
	}

	/**
	 * Class as TableModel for Supp Address table
	 * 
	 * @author TSI
	 *
	 */
	class PicTallyTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<PicTally> listOfPicTally;

		public PicTallyTableModel(List<PicTally> listOfPicTally) {
			this.listOfPicTally = listOfPicTally;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPicTally.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			PicTally p = listOfPicTally.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getEmployee().getEmployeeId();
			case 2:
				return p.getEmployee().getEmployeeName();
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
				return "Nik";
			case 2:
				return "Nama";
			default:
				return "";
			}
		}
	}

	/**
	 * Class as TableModel for Dry In Pallet table
	 * 
	 * @author TSI
	 *
	 */
	class DryInPalletTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<DryInPallet> listOfDryInPallet;

		public DryInPalletTableModel(List<DryInPallet> listOfDryInPallet) {
			this.listOfDryInPallet = listOfDryInPallet;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfDryInPallet.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 6;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			DryInPallet p = listOfDryInPallet.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getPalletCardCode();
			case 1:
				return p.getPalletCard().getLength();
			case 2:
				return p.getPalletCard().getWidth();
			case 3:
				return p.getPalletCard().getThickness();
			case 4:
				return p.getPalletCard().getVolume();
			case 5:
				return "Delete";
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
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			case 3:
				return Integer.class;
			case 4:
				return Integer.class;
			case 5:
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
				return "Kode Kartu Pallet";
			case 1:
				return "Panjang";
			case 2:
				return "Lebar";
			case 3:
				return "Tebal";
			case 4:
				return "Total Volume";
			case 5:
				return "Tindakan";
			default:
				return "";
			}
		}

	}

	@Override
	public void invokeObjects(Object... objects) {
		this.dryIn = (DryIn) objects[0];

		loadData(dryIn.getId());
	}

	public List<DryInPallet> getListOfDryInPallet() {
		return listOfDryInPallet;
	}

	public void setListOfDryInPallet(List<DryInPallet> listOfDryInPallet) {
		this.listOfDryInPallet = listOfDryInPallet;
	}

	List<DryInPallet> dryInPallets = new ArrayList<DryInPallet>();

	public List<DryInPallet> getDryInPallets() {
		return dryInPallets;
	}

	public void setDryInPallets(List<DryInPallet> dryInPallets) {
		this.dryInPallets = dryInPallets;
	}

	protected void loadData(Integer dryInId) {
		try {
			dryIn = ServiceFactory.getDryInBL().getDryInById(dryInId);
			listOfDryInPallet = ServiceFactory.getDryInBL().getDryInPalletByDryInCode(dryIn.getDryInCode());

			// copy object
			for (DryInPallet dryInPallet : listOfDryInPallet) {
				dryInPallet.getPalletCard().setFlag(true);
				dryInPallets.add(dryInPallet);
			}

			if (dryIn != null) {
				txtDryInCode.setText(dryIn.getDryInCode());
				dcDateIn.setDate(DateUtil.toDate(dryIn.getDateIn()));

				Calendar cal = Calendar.getInstance();
				cal.setTime(dryIn.getDateIn());
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int minutes = cal.get(Calendar.MINUTE);

				for (int i = 0; i < 24; i++)
					cbDateInHour.addItem(String.format("%02d", i));

				cbDateInHour.setSelectedItem(String.format("%02d", hours));

				for (int i = 0; i < 60; i++)
					cbDateInMinute.addItem(String.format("%02d", i));

				cbDateInMinute.setSelectedItem(String.format("%02d", minutes));

				cbChamber.setSelectedItem(dryIn.getChamber().getChamber());
				txtTotalVolume.setText(String.valueOf(dryIn.getTotalVolume()));

				refreshTableDryInPallet();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
}
