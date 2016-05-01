package module.dryout.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.dryout.model.DryOutPallet;
import module.pembelian.model.Pallet;
import module.util.DateUtil;

public class DryOutPalletDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JScrollPane scrollPanePalletCard;
	JTable tblPalletCard;
	JTextField txtSearch;
	JButton btnSearch;
	JButton btnInsert;
	JLabel lblError;

	private PalletCardTableModel palletCardTableModel;

	List<Pallet> listOfPalletCard;

	private DryOutCreatePanel dryOutCreatePanel;
	private DryOutEditPanel dryOutEditPanel;

	public DryOutPalletDialog(DryOutCreatePanel dryOutCreatePanel, DryOutEditPanel dryOutEditPanel) {
		this.dryOutCreatePanel = dryOutCreatePanel;
		this.dryOutEditPanel = dryOutEditPanel;

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		getContentPane().setLayout(null);

		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 10, 225, 30);
		getContentPane().add(lblError);

		txtSearch = new JTextField();
		txtSearch.setBounds(320, 10, 150, 30);
		getContentPane().add(txtSearch);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearchPalletCard(txtSearch.getText());
			}
		});
		btnSearch.setBounds(480, 10, 95, 30);
		getContentPane().add(btnSearch);

		scrollPanePalletCard = new JScrollPane();
		scrollPanePalletCard.setBounds(10, 55, 564, 190);
		getContentPane().add(scrollPanePalletCard);

		try {
			listOfPalletCard = new ArrayList<Pallet>();
			if (dryOutCreatePanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL()
						.getAllPalletByChamberId(dryOutCreatePanel.cbChamber.getDataIndex().getId());

				for (DryOutPallet dryOutPallet : dryOutCreatePanel.getListOfDryOutPallet()) {
					Integer index = listOfPalletCard.indexOf(dryOutPallet.getPalletCard());
					listOfPalletCard.set(index, dryOutPallet.getPalletCard());
				}
			} else if (dryOutEditPanel != null) {
				for (DryOutPallet dryOutPallet : dryOutEditPanel.getListOfDryOutPallet()) {
					dryOutPallet.getPalletCard().setFlag(true);
					dryOutPallet.getPalletCard().setRowNum(dryOutPallet.getId());
					if (!listOfPalletCard.contains(dryOutPallet.getPalletCard())) {
						listOfPalletCard.add(dryOutPallet.getPalletCard());
					} else {
						Integer index = listOfPalletCard.indexOf(dryOutPallet.getPalletCard());
						listOfPalletCard.set(index, dryOutPallet.getPalletCard());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showErrorException();
		}

		palletCardTableModel = new PalletCardTableModel(listOfPalletCard);
		tblPalletCard = new JTable(palletCardTableModel);
		tblPalletCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblPalletCard.setFocusable(false);
		scrollPanePalletCard.setViewportView(tblPalletCard);

		tblPalletCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblPalletCard.getValueAt(tblPalletCard.getSelectedRow(), 0).equals(true))
					listOfPalletCard.get(tblPalletCard.getSelectedRow()).setFlag(false);
				else
					listOfPalletCard.get(tblPalletCard.getSelectedRow()).setFlag(true);

				refreshTablePalletCard();
			}
		});

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsert();
			}
		});
		btnInsert.setBounds(480, 250, 95, 30);
		getContentPane().add(btnInsert);
	}

	private void doInsert() {
		List<DryOutPallet> listOfDryOutPallet = new ArrayList<DryOutPallet>();

		for (Pallet palletCard : listOfPalletCard) {
			DryOutPallet dryOutPallet = new DryOutPallet();
			if (palletCard.isFlag()) {
				dryOutPallet.setPalletCard(palletCard);
				dryOutPallet.setPalletCardCode(palletCard.getPalletCardCode());
				if (palletCard.getRowNum() != 0 && dryOutEditPanel != null)
					dryOutPallet.setId(palletCard.getRowNum());
				listOfDryOutPallet.add(dryOutPallet);
			} else {
				if (palletCard.getRowNum() != 0 && dryOutEditPanel != null) {
					dryOutPallet.setPalletCard(palletCard);
					dryOutPallet.setPalletCardCode(palletCard.getPalletCardCode());
					dryOutPallet.setId(palletCard.getRowNum());
					dryOutEditPanel.listOfDeletedDryOutPallet.add(dryOutPallet);
				}
			}
		}

		if (dryOutCreatePanel != null)
			dryOutCreatePanel.setListOfDryOutPallet(listOfDryOutPallet);
		else if (dryOutEditPanel != null) {
			dryOutEditPanel.setListOfDryOutPallet(listOfDryOutPallet);
		}

		closeDialog();
	}

	protected void closeDialog() {
		if (dryOutCreatePanel != null) {
			dryOutCreatePanel.refreshTableDryOutPallet();
			dryOutCreatePanel.countTotalVolumeDryOutPalletCard();
		} else if (dryOutEditPanel != null) {
			dryOutEditPanel.refreshTableDryOutPallet();
			dryOutEditPanel.countTotalVolumeDryOutPalletCard();
		}

		dispose();
	}

	private void doSearchPalletCard(String value) {
		try {
			listOfPalletCard = new ArrayList<Pallet>();
			if (dryOutCreatePanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL().getAllPalletBySearchAndChamberId(value,
						dryOutCreatePanel.cbChamber.getDataIndex().getId());
			}

			if (dryOutEditPanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL().getAllPalletBySearchAndChamberId(value,
						dryOutEditPanel.cbChamber.getDataIndex().getId());
			}
			refreshTablePalletCard();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	public void refreshTablePalletCard() {
		try {
			tblPalletCard.setModel(new PalletCardTableModel(listOfPalletCard));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	/**
	 * Class as TableModel for Pallet Card table
	 * 
	 * @author TSI
	 *
	 */
	class PalletCardTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<Pallet> listOfPalletCard;

		public PalletCardTableModel(List<Pallet> listOfPalletCard) {
			this.listOfPalletCard = listOfPalletCard;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfPalletCard.size();
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
		 * @return ({@link SupplierAddress}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Pallet p = listOfPalletCard.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return DateUtil.setFormatedDate(p.getDateIn());
			case 2:
				return p.getReceived().getRitNo();
			case 3:
				return p.getPalletCardCode();
			case 4:
				return p.getTotalVolume();
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
			case 3:
				return String.class;
			case 4:
				return Integer.class;
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
				return "Tanggal Pemasukan";
			case 2:
				return "No Rit";
			case 3:
				return "Kode Kartu Pallet";
			case 4:
				return "Total Volume";
			default:
				return "";
			}
		}
	}
}
