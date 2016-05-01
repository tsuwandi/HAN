package module.dryin.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import module.sn.chamber.model.Chamber;
import module.util.Bridging;
import module.util.DateUtil;

public class DryInViewPanel extends JPanel implements Bridging {

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
	JButton btnPrint;
	JButton btnDelete;
	JButton btnEdit;

	JPanel panel;
	JScrollPane scrollPane;

	private PicTallyTableModel picTallyTableModel;
	private DryInPalletTableModel dryInPalletTableModel;
	private List<PicTally> listOfPicTally;
	private List<DryInPallet> listOfDryInPallet;
	private DryIn dryIn;

	private List<Chamber> listOfChamber;

	public DryInViewPanel() {
		setPreferredSize(new Dimension(1080, 600));
		setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1080, 850));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pengeringan > Pemasukan");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("VIEW DETAIL");
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
		dcDateIn.setEnabled(false);
		panel.add(dcDateIn);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		cbDateInHour = new ComboBox<String>();
		cbDateInHour.setBounds(380, 120, 45, 30);
		cbDateInHour.setEnabled(false);
		panel.add(cbDateInHour);

		cbDateInMinute = new ComboBox<String>();
		cbDateInMinute.setBounds(440, 120, 45, 30);
		cbDateInMinute.setEnabled(false);
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

		cbChamber = new ComboBox<Chamber>();
		cbChamber.addItem("-- Pilih Chamber --");
		cbChamber.setBounds(220, 160, 150, 30);
		cbChamber.setEnabled(false);
		panel.add(cbChamber);

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
		tblPicTally.setEnabled(false);
		scrollPanePicTally.setViewportView(tblPicTally);

		btnSearchPicTally = new JButton("Cari");
		btnSearchPicTally.setBounds(820, 200, 100, 30);
		btnSearchPicTally.setEnabled(false);
		panel.add(btnSearchPicTally);

		btnDeletePicTally = new JButton("Hapus");
		btnDeletePicTally.setBounds(925, 200, 100, 30);
		btnDeletePicTally.setEnabled(false);
		panel.add(btnDeletePicTally);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.setBounds(49, 405, 150, 30);
		btnSearchPalletCard.setEnabled(false);
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
		txtRitNo.setEnabled(false);
		panel.add(txtRitNo);

		lblPalletCardCodeConstant = new JLabel(" / BL / ");
		lblPalletCardCodeConstant.setBounds(275, 470, 150, 30);
		panel.add(lblPalletCardCodeConstant);

		lblDate = new JLabel("Tanggal");
		lblDate.setBounds(310, 445, 150, 30);
		panel.add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(310, 470, 50, 30);
		txtDate.setEnabled(false);
		panel.add(txtDate);

		JLabel lblA = new JLabel(" / ");
		lblA.setBounds(370, 470, 150, 30);
		panel.add(lblA);

		lblMonth = new JLabel("Bulan");
		lblMonth.setBounds(390, 445, 150, 30);
		panel.add(lblMonth);

		txtMonth = new JTextField();
		txtMonth.setBounds(390, 470, 50, 30);
		txtMonth.setEnabled(false);
		panel.add(txtMonth);

		JLabel lblB = new JLabel(" / ");
		lblB.setBounds(450, 470, 150, 30);
		panel.add(lblB);

		lblYear = new JLabel("Tahun");
		lblYear.setBounds(470, 445, 150, 30);
		panel.add(lblYear);

		txtYear = new JTextField();
		txtYear.setBounds(470, 470, 50, 30);
		txtYear.setEnabled(false);
		panel.add(txtYear);

		JLabel lblC = new JLabel(" / ");
		lblC.setBounds(530, 470, 150, 30);
		panel.add(lblC);

		lblOrdinal = new JLabel("Sequence");
		lblOrdinal.setBounds(550, 445, 150, 30);
		panel.add(lblOrdinal);

		txtOrdinal = new JTextField();
		txtOrdinal.setBounds(550, 470, 50, 30);
		txtOrdinal.setEnabled(false);
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
		btnInsertDryInPallet.setBounds(220, 540, 100, 30);
		btnInsertDryInPallet.setEnabled(false);
		panel.add(btnInsertDryInPallet);

		scrollPaneDryInPallet = new JScrollPane();
		scrollPaneDryInPallet.setBounds(50, 585, 975, 150);
		panel.add(scrollPaneDryInPallet);

		listOfDryInPallet = new ArrayList<DryInPallet>();
		dryInPalletTableModel = new DryInPalletTableModel(listOfDryInPallet);
		tblDryInPallet = new JTable(dryInPalletTableModel);
		tblDryInPallet.setFocusable(false);
		tblDryInPallet.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblDryInPallet.setEnabled(false);
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

		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPrint();
			}
		});
		btnPrint.setBounds(715, 810, 100, 30);
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
		btnDelete.setBounds(820, 810, 100, 30);
		panel.add(btnDelete);

		btnEdit = new JButton("Ubah");
		btnEdit.setBounds(925, 810, 100, 30);
		panel.add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.dryin.ui.DryInEditPanel", dryIn);
			}
		});

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryin.ui.DryInListPanel");
			}
		});
		btnCancel.setBounds(50, 810, 100, 30);
		panel.add(btnCancel);
	}

	protected void loadData(Integer supplierId) {
		try {
			dryIn = ServiceFactory.getDryInBL().getDryInById(supplierId);
			listOfPicTally = ServiceFactory.getDryInBL().getPicTallyByDryInCode(dryIn.getDryInCode());
			listOfDryInPallet = ServiceFactory.getDryInBL().getDryInPalletByDryInCode(dryIn.getDryInCode());

			if (dryIn != null) {
				txtDryInCode.setText(dryIn.getDryInCode());
				dcDateIn.setDate(DateUtil.toDate(dryIn.getDateIn()));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(dryIn.getDateIn());
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int minutes = cal.get(Calendar.MINUTE);
				
				cbDateInHour.addItem(String.format("%02d", hours));
				cbDateInMinute.addItem(String.format("%02d", minutes));
				
				cbChamber.addItem(dryIn.getChamber().getChamber());
				cbChamber.setSelectedIndex(1);
				txtTotalVolume.setText(String.valueOf(dryIn.getTotalVolume()));

				refreshTablePicTally();
				refreshTableDryInPallet();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	private void doPrint() {
		// TODO Auto-generated method stub

	}

	protected void doDelete() {
		try {
			ServiceFactory.getDryInBL().deleteAll(dryIn);
			DialogBox.showDelete();
			MainPanel.changePanel("module.dryin.ui.DryInListPanel");
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
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

	public void refreshTableDryInPallet() {
		try {
			tblDryInPallet.setModel(new DryInPalletTableModel(listOfDryInPallet));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for Pic Tally table
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
		this.dryIn = (DryIn) objects[0];

		loadData(dryIn.getId());
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
