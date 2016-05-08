package module.dryout.ui;

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
import module.dryout.model.DryOut;
import module.dryout.model.DryOutPallet;
import module.sn.chamber.model.Chamber;
import module.util.Bridging;
import module.util.DateUtil;

public class DryOutViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	JLabel lblDryOutCode;
	JLabel lblDateIn;
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

	JScrollPane scrollPaneDryOutPallet;
	JTable tblDryOutPallet;
	JButton btnSearchPalletCard;
	JButton btnInsertDryOutPallet;

	JButton btnCancel;
	JButton btnPrint;
	JButton btnDelete;
	JButton btnEdit;

	JPanel panel;
	JScrollPane scrollPane;

	private DryOutPalletTableModel dryOutPalletTableModel;
	private List<DryOutPallet> listOfDryOutPallet;
	private DryOut dryOut;
	
	public DryOutViewPanel() {
		setPreferredSize(new Dimension(1080, 600));
		setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1080, 650));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pengeringan > Pengeluaran");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("VIEW DETAIL");
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

		lblDateIn = new JLabel("<html>Tanggal Keluar <font color=\"red\">*</font></html>");
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
		cbChamber.setBounds(220, 160, 150, 30);
		cbChamber.setEnabled(false);
		panel.add(cbChamber);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.setBounds(49, 200, 150, 30);
		btnSearchPalletCard.setEnabled(false);
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
		txtRitNo.setEnabled(false);
		panel.add(txtRitNo);

		lblPalletCardCodeConstant = new JLabel(" / BL / ");
		lblPalletCardCodeConstant.setBounds(275, 265, 150, 30);
		panel.add(lblPalletCardCodeConstant);

		lblDate = new JLabel("Tanggal");
		lblDate.setBounds(310, 240, 150, 30);
		panel.add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(310, 265, 50, 30);
		txtDate.setEnabled(false);
		panel.add(txtDate);

		JLabel lblA = new JLabel(" / ");
		lblA.setBounds(370, 265, 150, 30);
		panel.add(lblA);

		lblMonth = new JLabel("Bulan");
		lblMonth.setBounds(390, 240, 150, 30);
		panel.add(lblMonth);

		txtMonth = new JTextField();
		txtMonth.setBounds(390, 265, 50, 30);
		txtMonth.setEnabled(false);
		panel.add(txtMonth);

		JLabel lblB = new JLabel(" / ");
		lblB.setBounds(450, 265, 150, 30);
		panel.add(lblB);

		lblYear = new JLabel("Tahun");
		lblYear.setBounds(470, 240, 150, 30);
		panel.add(lblYear);

		txtYear = new JTextField();
		txtYear.setBounds(470, 265, 50, 30);
		txtYear.setEnabled(false);
		panel.add(txtYear);

		JLabel lblC = new JLabel(" / ");
		lblC.setBounds(530, 265, 150, 30);
		panel.add(lblC);

		lblOrdinal = new JLabel("Sequence");
		lblOrdinal.setBounds(550, 240, 150, 30);
		panel.add(lblOrdinal);

		txtOrdinal = new JTextField();
		txtOrdinal.setBounds(550, 265, 50, 30);
		txtOrdinal.setEnabled(false);
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
		btnInsertDryOutPallet.setBounds(220, 340, 100, 30);
		btnInsertDryOutPallet.setEnabled(false);
		panel.add(btnInsertDryOutPallet);

		scrollPaneDryOutPallet = new JScrollPane();
		scrollPaneDryOutPallet.setBounds(50, 380, 975, 150);
		panel.add(scrollPaneDryOutPallet);

		listOfDryOutPallet = new ArrayList<DryOutPallet>();
		dryOutPalletTableModel = new DryOutPalletTableModel(listOfDryOutPallet);
		tblDryOutPallet = new JTable(dryOutPalletTableModel);
		tblDryOutPallet.setFocusable(false);
		tblDryOutPallet.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblDryOutPallet.setEnabled(false);
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

		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPrint();
			}
		});
		btnPrint.setBounds(715, 605, 100, 30);
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
		btnDelete.setBounds(820, 605, 100, 30);
		panel.add(btnDelete);

		btnEdit = new JButton("Ubah");
		btnEdit.setBounds(925, 605, 100, 30);
		panel.add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.dryout.ui.DryOutEditPanel", dryOut);
			}
		});

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
			}
		});
		btnCancel.setBounds(49, 605, 100, 30);
		panel.add(btnCancel);
	}

	protected void loadData(Integer dryOutId) {
		try {
			dryOut = ServiceFactory.getDryOutBL().getDryOutById(dryOutId);
			listOfDryOutPallet = ServiceFactory.getDryOutBL().getDryOutPalletByDryOutCode(dryOut.getDryOutCode());

			if (dryOut != null) {
				txtDryOutCode.setText(dryOut.getDryOutCode());
				dcDateIn.setDate(DateUtil.toDate(dryOut.getDateOut()));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(dryOut.getDateOut());
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int minutes = cal.get(Calendar.MINUTE);
				
				cbDateInHour.addItem(String.format("%02d", hours));
				cbDateInMinute.addItem(String.format("%02d", minutes));
				
				cbChamber.addItem("-- Pilih Chamber --");
				cbChamber.addItem(dryOut.getChamber().getChamber());
				cbChamber.setSelectedIndex(0);
				txtTotalVolume.setText(String.valueOf(dryOut.getTotalVolume()));

				refreshTableDryOutPallet();
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
			ServiceFactory.getDryOutBL().deleteAll(dryOut);
			DialogBox.showDelete();
			MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
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
}
