package module.dryin.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import main.component.ComboBox;
import main.panel.MainPanel;
import module.dryin.model.DryIn;
import module.dryin.model.DryInPallet;
import module.dryin.model.PicTally;
import module.sn.chamber.model.Chamber;
import module.util.Bridging;

public class DryInCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	JLabel lblDryInCode;
	JLabel lblDateIn;
	JLabel lblChamber;

	JLabel lblPicTally;
	JLabel lblPalletCardCode;
	JLabel lblSequential;
	JLabel lblPalletCardCodeConstant;
	JLabel lblDate;
	JLabel lblMonth;
	JLabel lblYear;
	JLabel lblTotalVolumePalletCard;
	JLabel lblTotalVolumeUomPalletCard;
	JLabel lblTotalVolume;
	JLabel lblTotalVolumeUom;

	JTextField txtDryInCode;
	JDateChooser dcDateIn;
	ComboBox<Chamber> cbChamber;

	JTextField txtSequential;
	JTextField txtDate;
	JTextField txtMonth;
	JTextField txtYear;
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
	
	public DryInCreatePanel() {
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

		lblDateIn = new JLabel("<html>Tanggal Masuk <font color=\"red\">*</font></html>");
		lblDateIn.setBounds(50, 120, 150, 30);
		panel.add(lblDateIn);

		dcDateIn = new JDateChooser();
		dcDateIn.setBounds(220, 120, 150, 30);
		panel.add(dcDateIn);

		lblChamber = new JLabel("<html>Chamber <font color=\"red\">*</font></html>");
		lblChamber.setBounds(50, 160, 150, 30);
		panel.add(lblChamber);

		cbChamber = new ComboBox<Chamber>();
		cbChamber.setBounds(220, 160, 150, 30);
		panel.add(cbChamber);

		/////// Table SuppAddress ///////
		lblPicTally = new JLabel("Pic Tally");
		lblPicTally.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPicTally.setBounds(50, 200, 150, 30);
		panel.add(lblPicTally);
		
		scrollPanePicTally = new JScrollPane();
		scrollPanePicTally.setBounds(50, 240, 600, 150);
		panel.add(scrollPanePicTally);
		
		picTallyTableModel = new PicTallyTableModel(new ArrayList<PicTally>());
		tblPicTally = new JTable(picTallyTableModel);
		tblPicTally.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPanePicTally.setViewportView(tblPicTally);
		
		btnSearchPicTally = new JButton("Cari");
		btnSearchPicTally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSearchPicTally.setBounds(445, 200, 100, 30);
		panel.add(btnSearchPicTally);

		btnDeletePicTally = new JButton("Hapus");
		btnDeletePicTally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnDeletePicTally.setBounds(550, 200, 100, 30);
		panel.add(btnDeletePicTally);

		btnSearchPalletCard = new JButton("Cari Kartu Pallet");
		btnSearchPalletCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSearchPalletCard.setBounds(49, 405, 150, 30);
		panel.add(btnSearchPalletCard);
		
		lblPalletCardCode = new JLabel("Kode Kartu Pallet");
		lblPalletCardCode.setBounds(50, 445, 150, 30);
		panel.add(lblPalletCardCode);
		
		lblSequential = new JLabel("Sequential");
		lblSequential.setBounds(220, 445, 150, 30);
		panel.add(lblSequential);
		
		txtSequential = new JTextField();
		txtSequential.setBounds(220, 470, 80, 30);
		panel.add(txtSequential);
		
		lblPalletCardCodeConstant = new JLabel(" / BL / ");
		lblPalletCardCodeConstant.setBounds(305, 470, 150, 30);
		panel.add(lblPalletCardCodeConstant);
		
		lblDate = new JLabel("Tanggal");
		lblDate.setBounds(340, 445, 150, 30);
		panel.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(340, 470, 50, 30);
		panel.add(txtDate);
		
		JLabel lblA = new JLabel(" / ");
		lblA.setBounds(395, 470, 150, 30);
		panel.add(lblA);
		
		lblMonth = new JLabel("Bulan");
		lblMonth.setBounds(410, 445, 150, 30);
		panel.add(lblMonth);
		
		txtMonth = new JTextField();
		txtMonth.setBounds(410, 470, 50, 30);
		panel.add(txtMonth);
		
		JLabel lblB = new JLabel(" / ");
		lblB.setBounds(465, 470, 150, 30);
		panel.add(lblB);
		
		lblYear = new JLabel("Tahun");
		lblYear.setBounds(480, 445, 150, 30);
		panel.add(lblYear);
		
		txtYear = new JTextField();
		txtYear.setBounds(480, 470, 50, 30);
		panel.add(txtYear);
		
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
		panel.add(btnInsertDryInPallet);
		
		scrollPaneDryInPallet = new JScrollPane();
		scrollPaneDryInPallet.setBounds(50, 585, 600, 150);
		panel.add(scrollPaneDryInPallet);
		
		dryInPalletTableModel = new DryInPalletTableModel(new ArrayList<DryInPallet>());
		tblDryInPallet = new JTable(dryInPalletTableModel);
		tblDryInPallet.setBorder(new EmptyBorder(5, 5, 5, 5));
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
				
			}
		});
		btnSave.setBounds(925, 810, 100, 30);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setBounds(49, 810, 100, 30);
		panel.add(btnCancel);
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
	 * Class as TableModel for Supp Address table
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
				return "";
			case 1:
				return "";
			case 2:
				return "";
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
}
