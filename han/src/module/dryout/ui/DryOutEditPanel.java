package module.dryout.ui;

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
import module.dryout.model.DryOut;
import module.dryout.model.DryOutPallet;
import module.pembelian.model.PalletCard;
import module.sn.chamber.model.Chamber;
import module.util.Bridging;
import module.util.DateUtil;

public class DryOutEditPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DryOutEditPanel.class);

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	JLabel lblDryOutCode;
	JLabel lblDateOut;
	JLabel lblChamber;

	JLabel lblErrorDryOutCode;
	JLabel lblErrorDateIn;
	JLabel lblErrorChamber;
	JLabel lblErrorPalletCard;
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

	JTextField txtDryOutCode;
	JDateChooser dcDateOut;
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

	JScrollPane scrollPaneDryOutPallet;
	JTable tblDryOutPallet;
	JButton btnSearchPalletCard;
	JButton btnInsertDryOutPallet;

	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	private DryOutPalletTableModel dryOutPalletTableModel;
	private List<DryOutPallet> listOfDryOutPallet;
	private PalletCard palletCard;
	private DryOutEditPanel dryOutEditPanel;
	private List<Chamber> listOfChamber;
	private DryOut dryOut;
	public List<DryOutPallet> listOfDeletedDryOutPallet;

	@SuppressWarnings("deprecation")
	public DryOutEditPanel() {
		dryOutEditPanel = this;

		// setPreferredSize(new Dimension(1366, 725));
		setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(getPreferredSize());
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pengeringan > Pengeluaran");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Ubah");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblDryOutCode = new JLabel("<html>Kode Pengeluaran <font color=\"red\">*</font></html>");
		lblDryOutCode.setBounds(50, 80, 150, 25);
		panel.add(lblDryOutCode);

		txtDryOutCode = new JTextField();
		txtDryOutCode.setBounds(220, 80, 150, 25);
		txtDryOutCode.setEnabled(false);
		panel.add(txtDryOutCode);

		lblErrorDryOutCode = new JLabel("");
		lblErrorDryOutCode.setForeground(Color.RED);
		lblErrorDryOutCode.setBounds(380, 80, 270, 25);
		panel.add(lblErrorDryOutCode);

		lblDateOut = new JLabel("<html>Tanggal Keluar <font color=\"red\">*</font></html>");
		lblDateOut.setBounds(50, 110, 150, 25);
		panel.add(lblDateOut);

		dcDateOut = new JDateChooser(new Date());
		dcDateOut.setBounds(220, 110, 150, 25);
		dcDateOut.setDateFormatString("dd-MM-yyyy");
		panel.add(dcDateOut);

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
			listOfChamber = ServiceFactory.getDryOutBL().getAllChamber();
			listOfChamber.add(0, new Chamber("-- Pilih Chamber --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbChamber = new ComboBox<Chamber>();
		cbChamber.setList(listOfChamber);
		cbChamber.setBounds(220, 150, 140, 25);
		cbChamber.setEnabled(false);
		panel.add(cbChamber);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbChamber.getSelectedIndex() == 0)
					DialogBox.showChooseChamberForSearchPalletInDryOut();
				else
					showAddDryOutPalletDialog(dryOutEditPanel);
			}
		});
		btnSearchPalletCard.setBounds(49, 175, 150, 25);
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

		lblOrdinal = new JLabel("No Pallet");
		lblOrdinal.setBounds(220, 255, 150, 25);
		panel.add(lblOrdinal);

		txtOrdinal = new NumberField(4);
		txtOrdinal.setBounds(220, 280, 50, 25);
		panel.add(txtOrdinal);

		lblErrorPalletCard = new JLabel("");
		lblErrorPalletCard.setBounds(405, 315, 150, 25);
		lblErrorPalletCard.setForeground(Color.RED);
		panel.add(lblErrorPalletCard);

		lblTotalVolumePalletCard = new JLabel("Total Volume");
		lblTotalVolumePalletCard.setBounds(50, 315, 150, 25);
		panel.add(lblTotalVolumePalletCard);

		txtTotalVolumePalletCard = new JTextField();
		txtTotalVolumePalletCard.setBounds(220, 315, 150, 25);
		txtTotalVolumePalletCard.setEnabled(false);
		panel.add(txtTotalVolumePalletCard);

		lblTotalVolumeUomPalletCard = new JLabel("m3");
		lblTotalVolumeUomPalletCard.setBounds(380, 315, 150, 25);
		panel.add(lblTotalVolumeUomPalletCard);

		btnInsertDryOutPallet = new JButton("Insert");
		btnInsertDryOutPallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsertDryOutPallet();
			}
		});
		btnInsertDryOutPallet.setBounds(220, 345, 100, 25);
		panel.add(btnInsertDryOutPallet);

		scrollPaneDryOutPallet = new JScrollPane();
		scrollPaneDryOutPallet.setBounds(50, 375, 975, 150);
		panel.add(scrollPaneDryOutPallet);

		listOfDryOutPallet = new ArrayList<DryOutPallet>();
		dryOutPalletTableModel = new DryOutPalletTableModel(listOfDryOutPallet);
		tblDryOutPallet = new JTable(dryOutPalletTableModel);
		tblDryOutPallet.setFocusable(false);
		tblDryOutPallet.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblDryOutPallet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 5)
						doDeleteDryOutPallet(listOfDryOutPallet.get(row));
				}
			}
		});
		scrollPaneDryOutPallet.setViewportView(tblDryOutPallet);

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
					MainPanel.changePanel("module.dryout.ui.DryOutViewPanel", dryOut);
				}
			}
		});
		btnCancel.setBounds(49, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		txtRitNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
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
				
				if (txtDate.getText().length() > 0) {
					int formatTxtDate = Integer.valueOf(txtDate.getText());
				
					if(formatTxtDate < 10) {
						txtDate.setText(String.format("%02d",formatTxtDate));
					}
				}
				
				if (txtDate.getText().length() > 1) {
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				} else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtMonth.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMonth.getText().length() > 0) {
					int formatTxtMonth = Integer.valueOf(txtMonth.getText());
					
					if(formatTxtMonth < 10) {
						txtMonth.setText(String.format("%02d",formatTxtMonth));
					}
				}
				
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
		listOfDeletedDryOutPallet = new ArrayList<DryOutPallet>();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dcDateOut.requestFocusInWindow();
			}
		});
	}

	public boolean doValidate() {
		boolean isValid = true;

		lblErrorDryOutCode.setText("");
		lblErrorDateIn.setText("");
		lblErrorChamber.setText("");

		if (dcDateOut.getDate() == null) {
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

		dryOut.setDryOutCode(txtDryOutCode.getText());
		dryOut.setDateOut(
				DateUtil.setTimeStamp(dcDateOut.getDate(), Integer.parseInt(cbDateInHour.getSelectedItem().toString()),
						Integer.parseInt(cbDateInMinute.getSelectedItem().toString()), 0));
		dryOut.setChamberId(cbChamber.getDataIndex().getId());

		if (!txtTotalVolume.getText().equals(""))
			dryOut.setTotalVolume(Double.parseDouble(txtTotalVolume.getText()));
		else
			dryOut.setTotalVolume(0);

		try {
			ServiceFactory.getDryOutBL().update(dryOut, listOfDryOutPallet, listOfDeletedDryOutPallet);
			DialogBox.showInsert();
			MainPanel.changePanel("module.dryout.ui.DryOutViewPanel", dryOut);
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
	protected void showAddDryOutPalletDialog(DryOutEditPanel dryOutEditPanel) {
		DryOutPalletDialog dryOutPalletDialog = new DryOutPalletDialog(null, dryOutEditPanel);
		dryOutPalletDialog.setTitle("Detail");
		dryOutPalletDialog.setLocationRelativeTo(null);
		dryOutPalletDialog.setVisible(true);
	}

	public void searchPalletCardByCode(String ritNo, String date, String month, String year, String ordinal) {
		final String constant = "BL";

		if ("".equals(ritNo) && "".equals(date) && "".equals(month) && "".equals(year) && "".equals(ordinal))
			return;

		String palletCardCode = new StringBuilder().append(ritNo).append("/").append(constant).append("/").append(date)
				.append("/").append(month).append("/").append(year).append("/").append(ordinal).toString();

		try {
			palletCard = new PalletCard();
			palletCard = ServiceFactory.getDryOutBL().getPalletByPalletCardCodeAndChamberId(palletCardCode,
					cbChamber.getDataIndex().getId());

			if (palletCard != null) {
				txtTotalVolumePalletCard.setText(String.valueOf(palletCard.getVolume()));
			} else {
				txtTotalVolumePalletCard.setText("");
				palletCard = null;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void doInsertDryOutPallet() {
		boolean isExists = false;
		for (DryOutPallet dryOutPallet : listOfDryOutPallet) {
			if (dryOutPallet.getPalletCard().equals(palletCard)) {
				DialogBox.showErrorIsExists();
				isExists = true;
				break;
			}
		}

		if (palletCard != null && isExists == false) {
			palletCard.setFlag(true);
			DryOutPallet dryOutPallet = new DryOutPallet();
			dryOutPallet.setPalletCard(palletCard);
			dryOutPallet.setPalletCardCode(palletCard.getPalletCardCode());
			listOfDryOutPallet.add(dryOutPallet);

			clearPalletCard();

			refreshTableDryOutPallet();

			countTotalVolumeDryOutPalletCard();
		} else if (palletCard == null && isExists == false) {
			lblErrorPalletCard.setText("Data tidak ditemukan.");
		}
	}

	protected void doDeleteDryOutPallet(DryOutPallet dryOutPallet) {
		listOfDeletedDryOutPallet.add(dryOutPallet);
		listOfDryOutPallet.remove(dryOutPallet);

		int index = 0;
		for (DryOutPallet tDryOutPallet : dryOutPallets) {
			if (dryOutPallet.getPalletCardCode().equals(tDryOutPallet.getPalletCardCode())) {
				dryOutPallet.getPalletCard().setFlag(false);
				dryOutPallets.set(index, dryOutPallet);
			}
			index++;
		}

		refreshTableDryOutPallet();
		countTotalVolumeDryOutPalletCard();
		DialogBox.showDelete();
	}

	public void clearPalletCard() {
		// clear
		txtRitNo.setText("");
		txtOrdinal.setText("");
		txtTotalVolumePalletCard.setText("");
		lblErrorPalletCard.setText("");
		palletCard = null;
	}

	public void refreshTableDryOutPallet() {
		try {
			tblDryOutPallet.setModel(new DryOutPalletTableModel(listOfDryOutPallet));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	public void countTotalVolumeDryOutPalletCard() {
		double totalVolume = 0.00;
		for (DryOutPallet dryOutPallet : listOfDryOutPallet) {
			totalVolume += dryOutPallet.getPalletCard().getVolume();
		}

		txtTotalVolume.setText(String.valueOf(totalVolume));
	}

	/**
	 * Class as TableModel for Dry In Pallet table
	 * 
	 * @author TSI
	 *
	 */
	class DryOutPalletTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<DryOutPallet> listOfDryOutPallet;

		public DryOutPalletTableModel(List<DryOutPallet> listOfDryOutPallet) {
			this.listOfDryOutPallet = listOfDryOutPallet;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfDryOutPallet.size();
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
			DryOutPallet p = listOfDryOutPallet.get(rowIndex);
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
		this.dryOut = (DryOut) objects[0];

		loadData(dryOut.getId());
	}

	public List<DryOutPallet> getListOfDryOutPallet() {
		return listOfDryOutPallet;
	}

	public void setListOfDryOutPallet(List<DryOutPallet> listOfDryOutPallet) {
		this.listOfDryOutPallet = listOfDryOutPallet;
	}

	List<DryOutPallet> dryOutPallets = new ArrayList<DryOutPallet>();

	public List<DryOutPallet> getDryOutPallets() {
		return dryOutPallets;
	}

	public void setDryOutPallets(List<DryOutPallet> dryOutPallets) {
		this.dryOutPallets = dryOutPallets;
	}

	protected void loadData(Integer dryOutId) {
		try {
			dryOut = ServiceFactory.getDryOutBL().getDryOutById(dryOutId);
			listOfDryOutPallet = ServiceFactory.getDryOutBL().getDryOutPalletByDryOutCode(dryOut.getDryOutCode());

			// copy object
			for (DryOutPallet dryOutPallet : listOfDryOutPallet) {
				dryOutPallet.getPalletCard().setFlag(true);
				dryOutPallets.add(dryOutPallet);
			}

			if (dryOut != null) {
				txtDryOutCode.setText(dryOut.getDryOutCode());
				dcDateOut.setDate(DateUtil.toDate(dryOut.getDateOut()));

				Calendar cal = Calendar.getInstance();
				cal.setTime(dryOut.getDateOut());
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int minutes = cal.get(Calendar.MINUTE);

				for (int i = 0; i < 24; i++)
					cbDateInHour.addItem(String.format("%02d", i));

				cbDateInHour.setSelectedItem(String.format("%02d", hours));

				for (int i = 0; i < 60; i++)
					cbDateInMinute.addItem(String.format("%02d", i));

				cbDateInMinute.setSelectedItem(String.format("%02d", minutes));

				cbChamber.setSelectedItem(dryOut.getChamber().getChamber());
				txtTotalVolume.setText(String.valueOf(dryOut.getTotalVolume()));

				refreshTableDryOutPallet();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}
}
