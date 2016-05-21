package module.dryout.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.dryout.model.DryOut;
import module.dryout.model.DryOutPallet;
import module.pembelian.model.Pallet;
import module.sn.chamber.model.Chamber;
import module.util.Bridging;
import module.util.DateUtil;
import module.util.JTextFieldLimit;

public class DryOutEditPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

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

	JTextField txtRitNo;
	JTextField txtDate;
	JTextField txtMonth;
	JTextField txtYear;
	JTextField txtOrdinal;
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
	private Pallet palletCard;
	private DryOutEditPanel dryOutEditPanel;
	private List<Chamber> listOfChamber;
	private DryOut dryOut;
	public List<DryOutPallet> listOfDeletedDryOutPallet;

	public DryOutEditPanel() {
		dryOutEditPanel = this;

		setPreferredSize(new Dimension(1366, 650));
		setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(getPreferredSize());
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pengeringan > Pengeluaran");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("UBAH");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		panel.add(lblHeader);

		lblDryOutCode = new JLabel("<html>Kode Pengeluaran <font color=\"red\">*</font></html>");
		lblDryOutCode.setBounds(50, 80, 150, 30);
		panel.add(lblDryOutCode);

		txtDryOutCode = new JTextField();
		txtDryOutCode.setBounds(220, 80, 150, 30);
		txtDryOutCode.setEnabled(false);
		panel.add(txtDryOutCode);

		lblErrorDryOutCode = new JLabel("");
		lblErrorDryOutCode.setForeground(Color.RED);
		lblErrorDryOutCode.setBounds(380, 80, 270, 30);
		panel.add(lblErrorDryOutCode);

		lblDateOut = new JLabel("<html>Tanggal Keluar <font color=\"red\">*</font></html>");
		lblDateOut.setBounds(50, 120, 150, 30);
		panel.add(lblDateOut);

		dcDateOut = new JDateChooser();
		dcDateOut.setBounds(220, 120, 150, 30);
		panel.add(dcDateOut);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		cbDateInHour = new ComboBox<String>();
		for (int i = 0; i < 24; i++)
			cbDateInHour.addItem(String.format("%02d", i));
		cbDateInHour.setSelectedItem(String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)));
		cbDateInHour.setBounds(380, 120, 45, 30);
		panel.add(cbDateInHour);

		cbDateInMinute = new ComboBox<String>();
		for (int i = 0; i < 60; i++)
			cbDateInMinute.addItem(String.format("%02d", i));
		cbDateInMinute.setSelectedItem(String.format("%02d", cal.get(Calendar.MINUTE)));
		cbDateInMinute.setBounds(440, 120, 45, 30);
		panel.add(cbDateInMinute);

		lblErrorDateIn = new JLabel("");
		lblErrorDateIn.setForeground(Color.RED);
		lblErrorDateIn.setBounds(500, 120, 270, 30);
		panel.add(lblErrorDateIn);

		lblChamber = new JLabel("<html>Chamber <font color=\"red\">*</font></html>");
		lblChamber.setBounds(50, 160, 150, 30);
		panel.add(lblChamber);

		lblErrorChamber = new JLabel("");
		lblErrorChamber.setForeground(Color.RED);
		lblErrorChamber.setBounds(380, 160, 270, 30);
		panel.add(lblErrorChamber);
		try {
			listOfChamber = new ArrayList<Chamber>();
			listOfChamber = ServiceFactory.getDryOutBL().getAllChamber();
			listOfChamber.add(0, new Chamber("-- Pilih Chamber --"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		cbChamber = new ComboBox<Chamber>();
		cbChamber.setList(listOfChamber);
		cbChamber.setBounds(220, 160, 150, 30);
		cbChamber.setEnabled(false);
		panel.add(cbChamber);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddDryOutPalletDialog(dryOutEditPanel);
			}
		});
		btnSearchPalletCard.setBounds(49, 200, 150, 30);
		panel.add(btnSearchPalletCard);

		lblPalletCardCode = new JLabel("Kode Kartu Pallet");
		lblPalletCardCode.setBounds(50, 240, 150, 30);
		panel.add(lblPalletCardCode);
		//////////////////
		lblRitNo = new JLabel("Rit No");
		lblRitNo.setBounds(220, 240, 150, 30);
		panel.add(lblRitNo);

		txtRitNo = new JTextField();
		txtRitNo.setBounds(220, 265, 50, 30);
		panel.add(txtRitNo);

		lblPalletCardCodeConstant = new JLabel(" / BL / ");
		lblPalletCardCodeConstant.setBounds(275, 265, 150, 30);
		panel.add(lblPalletCardCodeConstant);

		lblDate = new JLabel("Tanggal");
		lblDate.setBounds(310, 240, 150, 30);
		panel.add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(310, 265, 50, 30);
		panel.add(txtDate);

		JLabel lblA = new JLabel(" / ");
		lblA.setBounds(370, 265, 150, 30);
		panel.add(lblA);

		lblMonth = new JLabel("Bulan");
		lblMonth.setBounds(390, 240, 150, 30);
		panel.add(lblMonth);

		txtMonth = new JTextField();
		txtMonth.setBounds(390, 265, 50, 30);
		panel.add(txtMonth);

		JLabel lblB = new JLabel(" / ");
		lblB.setBounds(450, 265, 150, 30);
		panel.add(lblB);

		lblYear = new JLabel("Tahun");
		lblYear.setBounds(470, 240, 150, 30);
		panel.add(lblYear);

		txtYear = new JTextField();
		txtYear.setBounds(470, 265, 50, 30);
		panel.add(txtYear);

		JLabel lblC = new JLabel(" / ");
		lblC.setBounds(530, 265, 150, 30);
		panel.add(lblC);

		lblOrdinal = new JLabel("Sequence");
		lblOrdinal.setBounds(550, 240, 150, 30);
		panel.add(lblOrdinal);

		txtOrdinal = new JTextField();
		txtOrdinal.setBounds(550, 265, 50, 30);
		panel.add(txtOrdinal);

		lblErrorPalletCard = new JLabel("");
		lblErrorPalletCard.setBounds(610, 265, 150, 30);
		lblErrorPalletCard.setForeground(Color.RED);
		panel.add(lblErrorPalletCard);

		lblTotalVolumePalletCard = new JLabel("Total Volume");
		lblTotalVolumePalletCard.setBounds(50, 300, 150, 30);
		panel.add(lblTotalVolumePalletCard);

		txtTotalVolumePalletCard = new JTextField();
		txtTotalVolumePalletCard.setBounds(220, 300, 150, 30);
		txtTotalVolumePalletCard.setEnabled(false);
		panel.add(txtTotalVolumePalletCard);

		lblTotalVolumeUomPalletCard = new JLabel("m3");
		lblTotalVolumeUomPalletCard.setBounds(380, 300, 150, 30);
		panel.add(lblTotalVolumeUomPalletCard);

		btnInsertDryOutPallet = new JButton("Insert");
		btnInsertDryOutPallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// int response = DialogBox.showInsertChoice();
				// if (response == JOptionPane.YES_OPTION) {
				doInsertDryOutPallet();
				// }
			}
		});
		btnInsertDryOutPallet.setBounds(220, 340, 100, 30);
		panel.add(btnInsertDryOutPallet);

		scrollPaneDryOutPallet = new JScrollPane();
		scrollPaneDryOutPallet.setBounds(50, 380, 975, 150);
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

					if (column == 2)
						doDeleteDryOutPallet(listOfDryOutPallet.get(row));
				}
			}
		});
		scrollPaneDryOutPallet.setViewportView(tblDryOutPallet);

		lblTotalVolume = new JLabel("Total Volume");
		lblTotalVolume.setBounds(50, 540, 150, 30);
		panel.add(lblTotalVolume);

		txtTotalVolume = new JTextField();
		txtTotalVolume.setBounds(220, 540, 150, 30);
		txtTotalVolume.setEnabled(false);
		panel.add(txtTotalVolume);

		lblTotalVolumeUomPalletCard = new JLabel("m3");
		lblTotalVolumeUomPalletCard.setBounds(380, 540, 150, 30);
		panel.add(lblTotalVolumeUomPalletCard);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 1155, 605);
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
		btnSave.setBounds(925, 605, 100, 30);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
			}
		});
		btnCancel.setBounds(49, 605, 100, 30);
		panel.add(btnCancel);

		txtRitNo.setDocument(new JTextFieldLimit(4));
		txtRitNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
					return;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (txtRitNo.getText().length() > 3)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
			}
		});

		txtDate.setDocument(new JTextFieldLimit(2));
		txtDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
					return;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (txtDate.getText().length() > 1)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtMonth.setDocument(new JTextFieldLimit(2));
		txtMonth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
					return;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (txtMonth.getText().length() > 1)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtYear.setDocument(new JTextFieldLimit(2));
		txtYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
					return;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (txtYear.getText().length() > 1)
					searchPalletCardByCode(txtRitNo.getText(), txtDate.getText(), txtMonth.getText(), txtYear.getText(),
							txtOrdinal.getText());
				else {
					txtTotalVolumePalletCard.setText("");
					palletCard = null;
				}
			}
		});

		txtOrdinal.setDocument(new JTextFieldLimit(4));
		txtOrdinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
					return;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
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
		dryOut.setTotalVolume(Double.parseDouble(txtTotalVolume.getText()));

		try {
			ServiceFactory.getDryOutBL().update(dryOut, listOfDryOutPallet, listOfDeletedDryOutPallet);
			DialogBox.showInsert();
			MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
		} catch (SQLException e) {
			e.printStackTrace();
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
			palletCard = new Pallet();
			palletCard = ServiceFactory.getDryOutBL().getPalletByPalletCardCodeAndChamberId(palletCardCode,
					cbChamber.getDataIndex().getId());

			if (palletCard != null) {
				txtTotalVolumePalletCard.setText(String.valueOf(palletCard.getTotalVolume()));
			} else {
				txtTotalVolumePalletCard.setText("");
				palletCard = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

		refreshTableDryOutPallet();
		countTotalVolumeDryOutPalletCard();
		DialogBox.showDelete();
	}

	public void clearPalletCard() {
		// clear
		txtRitNo.setText("");
		makeDefaultDatePalletCardCode();
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
			totalVolume += dryOutPallet.getPalletCard().getTotalVolume();
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
			DryOutPallet p = listOfDryOutPallet.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getPalletCardCode();
			case 1:
				return p.getPalletCard().getTotalVolume();
			case 2:
				return "Delete";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public Class getColumnClass(int column) {
			switch (column) {
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
				return "Kode Kartu Pallet";
			case 1:
				return "Total Volume";
			case 2:
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

	protected void loadData(Integer dryOutId) {
		try {
			dryOut = ServiceFactory.getDryOutBL().getDryOutById(dryOutId);
			listOfDryOutPallet = ServiceFactory.getDryOutBL().getDryOutPalletByDryOutCode(dryOut.getDryOutCode());

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
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}
}
