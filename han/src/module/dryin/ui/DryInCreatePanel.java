package module.dryin.ui;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.dryin.model.DryIn;
import module.dryin.model.DryInPallet;
import module.dryin.model.PicTally;
import module.pembelian.model.Pallet;
import module.sn.chamber.model.Chamber;
import module.util.Bridging;
import module.util.DateUtil;
import module.util.JTextFieldLimit;

public class DryInCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

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

	JTextField txtRitNo;
	JTextField txtDate;
	JTextField txtMonth;
	JTextField txtYear;
	JTextField txtOrdinal;
	JTextField txtTotalVolumePalletCard;
	JTextField txtTotalVolume;

	JScrollPane scrollPanePicTally;
	JTable tblPicTally;
	JButton btnSearchPicTally;
	JButton btnDeletePicTally;

	JScrollPane scrollPaneDryInPallet;
	JTable tblDryInPallet;
	JButton btnSearchPalletCard;
	JButton btnInsertDryInPallet;

	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	private PicTallyTableModel picTallyTableModel;
	private DryInPalletTableModel dryInPalletTableModel;
	private List<PicTally> listOfPicTally;
	private List<DryInPallet> listOfDryInPallet;
	private Pallet palletCard;
	private DryInCreatePanel dryInCreatePanel;

	private List<Chamber> listOfChamber;

	public DryInCreatePanel() {
		dryInCreatePanel = this;

		setPreferredSize(new Dimension(1080, 600));
		setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1080, 850));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pengeringan > Pemasukan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("CREATE NEW");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		panel.add(lblHeader);

		lblDryInCode = new JLabel("<html>Kode Pemasukan <font color=\"red\">*</font></html>");
		lblDryInCode.setBounds(50, 80, 150, 30);
		panel.add(lblDryInCode);

		txtDryInCode = new JTextField();
		txtDryInCode.setBounds(220, 80, 150, 30);
		txtDryInCode.setEnabled(false);
		panel.add(txtDryInCode);

		lblErrorDryInCode = new JLabel("");
		lblErrorDryInCode.setForeground(Color.RED);
		lblErrorDryInCode.setBounds(380, 80, 270, 30);
		panel.add(lblErrorDryInCode);

		lblDateIn = new JLabel("<html>Tanggal Masuk <font color=\"red\">*</font></html>");
		lblDateIn.setBounds(50, 120, 150, 30);
		panel.add(lblDateIn);

		dcDateIn = new JDateChooser();
		dcDateIn.setBounds(220, 120, 150, 30);
		panel.add(dcDateIn);

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
			listOfChamber = ServiceFactory.getDryInBL().getAllChamber();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		cbChamber = new ComboBox<Chamber>();
		cbChamber.addItem("-- Pilih Chamber --");
		cbChamber.setList(listOfChamber);
		cbChamber.setBounds(220, 160, 150, 30);
		panel.add(cbChamber);

		/////// Table SuppAddress ///////
		lblPicTally = new JLabel("Pic Tally");
		lblPicTally.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPicTally.setBounds(50, 200, 150, 30);
		panel.add(lblPicTally);

		scrollPanePicTally = new JScrollPane();
		scrollPanePicTally.setBounds(50, 240, 975, 150);
		panel.add(scrollPanePicTally);

		listOfPicTally = new ArrayList<PicTally>();
		picTallyTableModel = new PicTallyTableModel(listOfPicTally);
		tblPicTally = new JTable(picTallyTableModel);
		tblPicTally.setFocusable(false);
		tblPicTally.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPanePicTally.setViewportView(tblPicTally);

		tblPicTally.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblPicTally.getValueAt(tblPicTally.getSelectedRow(), 0).equals(true))
					listOfPicTally.get(tblPicTally.getSelectedRow()).setFlag(false);
				else
					listOfPicTally.get(tblPicTally.getSelectedRow()).setFlag(true);

				refreshTablePicTally();
			}
		});

		btnSearchPicTally = new JButton("Cari");
		btnSearchPicTally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddPicTallyDialog(dryInCreatePanel);
			}
		});
		btnSearchPicTally.setBounds(820, 200, 100, 30);
		panel.add(btnSearchPicTally);

		btnDeletePicTally = new JButton("Hapus");
		btnDeletePicTally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// int response = DialogBox.showDeleteChoice();
				// if (response == JOptionPane.YES_OPTION) {
				doDeletePicTally();
				// }
			}
		});
		btnDeletePicTally.setBounds(925, 200, 100, 30);
		panel.add(btnDeletePicTally);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddDryInPalletDialog(dryInCreatePanel);
			}
		});
		btnSearchPalletCard.setBounds(49, 405, 150, 30);
		panel.add(btnSearchPalletCard);

		lblPalletCardCode = new JLabel("Kode Kartu Pallet");
		lblPalletCardCode.setBounds(50, 445, 150, 30);
		panel.add(lblPalletCardCode);
		//////////////////
		lblRitNo = new JLabel("Rit No");
		lblRitNo.setBounds(220, 445, 150, 30);
		panel.add(lblRitNo);

		txtRitNo = new JTextField();
		txtRitNo.setBounds(220, 470, 50, 30);
		panel.add(txtRitNo);

		lblPalletCardCodeConstant = new JLabel(" / BL / ");
		lblPalletCardCodeConstant.setBounds(275, 470, 150, 30);
		panel.add(lblPalletCardCodeConstant);

		lblDate = new JLabel("Tanggal");
		lblDate.setBounds(310, 445, 150, 30);
		panel.add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(310, 470, 50, 30);
		panel.add(txtDate);

		JLabel lblA = new JLabel(" / ");
		lblA.setBounds(370, 470, 150, 30);
		panel.add(lblA);

		lblMonth = new JLabel("Bulan");
		lblMonth.setBounds(390, 445, 150, 30);
		panel.add(lblMonth);

		txtMonth = new JTextField();
		txtMonth.setBounds(390, 470, 50, 30);
		panel.add(txtMonth);

		JLabel lblB = new JLabel(" / ");
		lblB.setBounds(450, 470, 150, 30);
		panel.add(lblB);

		lblYear = new JLabel("Tahun");
		lblYear.setBounds(470, 445, 150, 30);
		panel.add(lblYear);

		txtYear = new JTextField();
		txtYear.setBounds(470, 470, 50, 30);
		panel.add(txtYear);

		JLabel lblC = new JLabel(" / ");
		lblC.setBounds(530, 470, 150, 30);
		panel.add(lblC);

		lblOrdinal = new JLabel("Sequence");
		lblOrdinal.setBounds(550, 445, 150, 30);
		panel.add(lblOrdinal);

		txtOrdinal = new JTextField();
		txtOrdinal.setBounds(550, 470, 50, 30);
		panel.add(txtOrdinal);

		lblErrorPalletCard = new JLabel("");
		lblErrorPalletCard.setBounds(610, 470, 150, 30);
		lblErrorPalletCard.setForeground(Color.RED);
		panel.add(lblErrorPalletCard);

		lblTotalVolumePalletCard = new JLabel("Total Volume");
		lblTotalVolumePalletCard.setBounds(50, 505, 150, 30);
		panel.add(lblTotalVolumePalletCard);

		txtTotalVolumePalletCard = new JTextField();
		txtTotalVolumePalletCard.setBounds(220, 505, 150, 30);
		txtTotalVolumePalletCard.setEnabled(false);
		panel.add(txtTotalVolumePalletCard);

		lblTotalVolumeUomPalletCard = new JLabel("m3");
		lblTotalVolumeUomPalletCard.setBounds(380, 505, 150, 30);
		panel.add(lblTotalVolumeUomPalletCard);

		btnInsertDryInPallet = new JButton("Insert");
		btnInsertDryInPallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// int response = DialogBox.showInsertChoice();
				// if (response == JOptionPane.YES_OPTION) {
				doInsertDryInPallet();
				// }
			}
		});
		btnInsertDryInPallet.setBounds(220, 540, 100, 30);
		panel.add(btnInsertDryInPallet);

		scrollPaneDryInPallet = new JScrollPane();
		scrollPaneDryInPallet.setBounds(50, 585, 975, 150);
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

					if (column == 2)
						doDeleteDryInPallet(listOfDryInPallet.get(row));
				}
			}
		});
		scrollPaneDryInPallet.setViewportView(tblDryInPallet);

		lblTotalVolume = new JLabel("Total Volume");
		lblTotalVolume.setBounds(50, 745, 150, 30);
		panel.add(lblTotalVolume);

		txtTotalVolume = new JTextField();
		txtTotalVolume.setBounds(220, 745, 150, 30);
		txtTotalVolume.setEnabled(false);
		panel.add(txtTotalVolume);

		lblTotalVolumeUomPalletCard = new JLabel("m3");
		lblTotalVolumeUomPalletCard.setBounds(380, 745, 150, 30);
		panel.add(lblTotalVolumeUomPalletCard);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 1155, 605);
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
		btnSave.setBounds(925, 810, 100, 30);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryin.ui.DryInListPanel");
			}
		});
		btnCancel.setBounds(49, 810, 100, 30);
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
		makeCodeNumber();
	}

	public boolean doValidate() {
		boolean isValid = true;

		lblErrorDryInCode.setText("");
		lblErrorDateIn.setText("");
		lblErrorChamber.setText("");

		if (txtDryInCode.getText() == null || txtDryInCode.getText().length() == 0) {
			lblErrorDryInCode.setText("Textbox Kode Pemasukan tidak ada nilai.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getDryInBL().isDryInCodeExists(txtDryInCode.getText()) > 0) {
					lblErrorDryInCode.setText("Kode Pemasukan sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		if (dcDateIn.getDate() == null) {
			lblErrorDateIn.setText("Tanggal harus dipilih.");
			isValid = false;
		}

		if ((cbDateInHour.getSelectedItem() == null) && (cbDateInMinute.getSelectedItem() == null)) {
			lblErrorDateIn.setText("Jam dan menit harus dipilih.");
			isValid = false;
		}

		if (cbChamber.getSelectedItem() == null) {
			lblErrorChamber.setText("Combobox chamber harus dipilih.");
			isValid = false;
		}

		return isValid;
	}

	public void doSave() {
		DryIn dryIn = new DryIn();
		dryIn.setDryInCode(txtDryInCode.getText());
		dryIn.setDateIn(
				DateUtil.setTimeStamp(dcDateIn.getDate(), Integer.parseInt(cbDateInHour.getSelectedItem().toString()),
						Integer.parseInt(cbDateInMinute.getSelectedItem().toString()), 0));
		dryIn.setChamberId(cbChamber.getDataIndex().getId());
		dryIn.setTotalVolume(Double.parseDouble(txtTotalVolume.getText()));

		try {
			ServiceFactory.getDryInBL().save(dryIn, listOfPicTally, listOfDryInPallet);
			DialogBox.showInsert();
			MainPanel.changePanel("module.dryin.ui.DryInListPanel");
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

	public void makeCodeNumber() {
		final String constant = "M";

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		String ordinal = null;
		try {
			ordinal = ServiceFactory.getDryInBL().getOrdinalOfCodeNumber();
		} catch (SQLException e) {
			e.printStackTrace();
			DialogBox.showErrorException();
		}
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.format("%02d", cal.get(Calendar.MONTH));

		txtDryInCode.setText(new StringBuilder().append(constant).append("/").append(year).append("/").append(month)
				.append("/").append(ordinal).toString());
	}

	/**
	 * Method to display add pic tally dialog
	 */
	protected void showAddPicTallyDialog(DryInCreatePanel dryInCreatePanel) {
		PicTallyDialog picTallyDialog = new PicTallyDialog(dryInCreatePanel, null);
		picTallyDialog.setTitle("Pic Tally");
		picTallyDialog.setLocationRelativeTo(null);
		picTallyDialog.setVisible(true);
	}

	protected void doDeletePicTally() {
		List<PicTally> temp = new ArrayList<PicTally>();
		for (PicTally s : listOfPicTally) {
			if (Boolean.TRUE.equals(s.isFlag())) {
				temp.add(s);
			}
		}

		if (Boolean.FALSE.equals(temp.isEmpty())) {
			for (PicTally s : temp) {
				listOfPicTally.remove(s);
			}
			refreshTablePicTally();
			DialogBox.showDelete();
		}
	}

	public void refreshTablePicTally() {
		try {
			tblPicTally.setModel(new PicTallyTableModel(listOfPicTally));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	/**
	 * Method to display add pallet card dialog
	 */
	protected void showAddDryInPalletDialog(DryInCreatePanel dryInCreatePanel) {
		DryInPalletDialog dryInPalletDialog = new DryInPalletDialog(dryInCreatePanel, null);
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
			palletCard = new Pallet();
			palletCard = ServiceFactory.getDryInBL().getPalletByPalletCardCode(palletCardCode);

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
		listOfDryInPallet.remove(dryInPallet);

		refreshTableDryInPallet();
		countTotalVolumeDryInPalletCard();
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
			totalVolume += dryInPallet.getPalletCard().getTotalVolume();
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
			DryInPallet p = listOfDryInPallet.get(rowIndex);
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

	}

	public List<PicTally> getListOfPicTally() {
		return listOfPicTally;
	}

	public void setListOfPicTally(List<PicTally> listOfPicTally) {
		this.listOfPicTally = listOfPicTally;
	}

	public List<DryInPallet> getListOfDryInPallet() {
		return listOfDryInPallet;
	}

	public void setListOfDryInPallet(List<DryInPallet> listOfDryInPallet) {
		this.listOfDryInPallet = listOfDryInPallet;
	}
}
