package module.dryin.ui;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.component.DialogBox;
import module.dryin.model.DryInPallet;
import module.pembelian.model.Pallet;
import module.util.DateUtil;

public class DryInPalletDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JScrollPane scrollPanePalletCard;
	JTable tblPalletCard;
	JTextField txtSearch;
	JButton btnSearch;
	JButton btnInsert;
	JLabel lblError;

	private PalletCardTableModel palletCardTableModel;

	List<Pallet> listOfPalletCard;

	private DryInCreatePanel dryInCreatePanel;
	private DryInEditPanel dryInEditPanel;

	public DryInPalletDialog(DryInCreatePanel dryInCreatePanel, DryInEditPanel dryInEditPanel) {
		this.dryInCreatePanel = dryInCreatePanel;
		this.dryInEditPanel = dryInEditPanel;

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
			listOfPalletCard = ServiceFactory.getDryInBL().getAllPallet();
			if (dryInCreatePanel != null) {
				for (DryInPallet dryInPallet : dryInCreatePanel.getListOfDryInPallet()) {
					Integer index = listOfPalletCard.indexOf(dryInPallet.getPalletCard());
					listOfPalletCard.set(index, dryInPallet.getPalletCard());
				}
			} else if (dryInEditPanel != null) {
				for (DryInPallet dryInPallet : dryInEditPanel.getListOfDryInPallet()) {
					dryInPallet.getPalletCard().setFlag(true);
					dryInPallet.getPalletCard().setRowNum(dryInPallet.getId());
					if (!listOfPalletCard.contains(dryInPallet.getPalletCard())) {
						listOfPalletCard.add(dryInPallet.getPalletCard());
					} else {
						Integer index = listOfPalletCard.indexOf(dryInPallet.getPalletCard());
						listOfPalletCard.set(index, dryInPallet.getPalletCard());
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
//				int response = DialogBox.showInsertChoice();
//				if (response == JOptionPane.YES_OPTION) {
					doInsert();
//				}
			}
		});
		btnInsert.setBounds(480, 250, 95, 30);
		getContentPane().add(btnInsert);
	}

	private void doInsert() {
		List<DryInPallet> listOfDryInPallet = new ArrayList<DryInPallet>();

		for (Pallet palletCard : listOfPalletCard) {
			DryInPallet dryInPallet = new DryInPallet();
			if (palletCard.isFlag()) {
				dryInPallet.setPalletCard(palletCard);
				dryInPallet.setPalletCardCode(palletCard.getPalletCardCode());
				if (palletCard.getRowNum() != 0 && dryInEditPanel != null)
					dryInPallet.setId(palletCard.getRowNum());
				listOfDryInPallet.add(dryInPallet);
			} else {
				if (palletCard.getRowNum() != 0 && dryInEditPanel != null) {
					dryInPallet.setPalletCard(palletCard);
					dryInPallet.setPalletCardCode(palletCard.getPalletCardCode());
					dryInPallet.setId(palletCard.getRowNum());
					dryInEditPanel.listOfDeletedDryInPallet.add(dryInPallet);
				}
			}
		}

		if (dryInCreatePanel != null)
			dryInCreatePanel.setListOfDryInPallet(listOfDryInPallet);
		else if (dryInEditPanel != null)
			dryInEditPanel.setListOfDryInPallet(listOfDryInPallet);

		closeDialog();
	}

	protected void closeDialog() {
		if (dryInCreatePanel != null) {
			dryInCreatePanel.refreshTableDryInPallet();
			dryInCreatePanel.countTotalVolumeDryInPalletCard();
		} else if (dryInEditPanel != null) {
			dryInEditPanel.refreshTableDryInPallet();
			dryInEditPanel.countTotalVolumeDryInPalletCard();
		}

		dispose();
	}

	private void doSearchPalletCard(String value) {
		try {
			listOfPalletCard = new ArrayList<Pallet>();
			listOfPalletCard = ServiceFactory.getDryInBL().getAllPalletBySearch(value);
			
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
				return DateUtil.setFormatedDate(p.getReceived().getReceivedDate());
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
				return "Tanggal Penerimaan";
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
