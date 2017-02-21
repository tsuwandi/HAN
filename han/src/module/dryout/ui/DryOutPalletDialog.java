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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import module.dryout.model.DryOutPallet;
import module.pembelian.model.PalletCard;
import module.util.DateUtil;

public class DryOutPalletDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DryOutPalletDialog.class);

	JScrollPane scrollPanePalletCard;
	JTable tblPalletCard;
	JTextField txtSearch;
	JButton btnSearch;
	JButton btnInsert;
	JLabel lblError;

	private PalletCardTableModel palletCardTableModel;

	List<PalletCard> listOfPalletCard;

	private DryOutCreatePanel dryOutCreatePanel;
	private DryOutEditPanel dryOutEditPanel;
	
	private int totalPallet;

	public DryOutPalletDialog(DryOutCreatePanel dryOutCreatePanel, DryOutEditPanel dryOutEditPanel) {
		this.dryOutCreatePanel = dryOutCreatePanel;
		this.dryOutEditPanel = dryOutEditPanel;
		DocumentFilter filter = new UppercaseDocumentFilter();

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		getContentPane().setLayout(null);

		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 10, 225, 25);
		getContentPane().add(lblError);

		txtSearch = new JTextField();
		txtSearch.setBounds(320, 10, 150, 25);
		((AbstractDocument) txtSearch.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtSearch);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearchPalletCard(txtSearch.getText());
			}
		});
		btnSearch.setBounds(480, 10, 95, 25);
		getContentPane().add(btnSearch);

		scrollPanePalletCard = new JScrollPane();
		scrollPanePalletCard.setBounds(10, 50, 564, 190);
		getContentPane().add(scrollPanePalletCard);

		try {
			listOfPalletCard = new ArrayList<PalletCard>();
			if (dryOutCreatePanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL()
						.getAllPalletByChamberId(dryOutCreatePanel.cbChamber.getDataIndex().getId());
				
				totalPallet = listOfPalletCard.size();
				
				for (DryOutPallet dryOutPallet : dryOutCreatePanel.getListOfDryOutPallet()) {
					Integer index = listOfPalletCard.indexOf(dryOutPallet.getPalletCard());
					listOfPalletCard.set(index, dryOutPallet.getPalletCard());
				}
			} else if (dryOutEditPanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL()
						.getAllPalletByChamberId(dryOutEditPanel.cbChamber.getDataIndex().getId());

				totalPallet = listOfPalletCard.size();
				
				for (DryOutPallet dryOutPallet : dryOutEditPanel.getDryOutPallets()) {
					dryOutPallet.getPalletCard().setRowNum(dryOutPallet.getId());
					if (!listOfPalletCard.contains(dryOutPallet.getPalletCard())) {
						listOfPalletCard.add(dryOutPallet.getPalletCard());
					} else {
						Integer index = listOfPalletCard.indexOf(dryOutPallet.getPalletCard());
						listOfPalletCard.set(index, dryOutPallet.getPalletCard());
					}
				}
				
				for (DryOutPallet dryOutPallet : dryOutEditPanel.getListOfDryOutPallet()) {
					dryOutPallet.getPalletCard().setFlag(true);
					Integer index = listOfPalletCard.indexOf(dryOutPallet.getPalletCard());
					listOfPalletCard.set(index, dryOutPallet.getPalletCard());
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
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
		btnInsert.setBounds(480, 250, 95, 25);
		getContentPane().add(btnInsert);
	}

	private void doInsert() {
		List<DryOutPallet> listOfDryOutPallet = new ArrayList<DryOutPallet>();

		int sizeIN = listOfPalletCard.size();
		
		for (PalletCard palletCard : listOfPalletCard) {
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
		
		int sizeOut = listOfDryOutPallet.size();

		int total = totalPallet - listOfDryOutPallet.size();
		
		if (total > 0)
		{
			JOptionPane.showMessageDialog(null, "Masih terdapat " + total + " kartu pallet didalam oven.", "Info", JOptionPane.INFORMATION_MESSAGE);
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
			listOfPalletCard = new ArrayList<PalletCard>();

			if (dryOutCreatePanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL().getAllPalletBySearchAndChamberId(value,
						dryOutCreatePanel.cbChamber.getDataIndex().getId());
			}

			if (dryOutEditPanel != null) {
				listOfPalletCard = ServiceFactory.getDryOutBL().getAllPalletBySearchAndChamberId(value,
						dryOutEditPanel.cbChamber.getDataIndex().getId());
			}
			
			if (value.equals("") && dryOutEditPanel != null) {
				for (DryOutPallet dryOutPallet : dryOutEditPanel.getListOfDryOutPallet()) {
					dryOutPallet.getPalletCard().setRowNum(dryOutPallet.getId());
					if (!listOfPalletCard.contains(dryOutPallet.getPalletCard())) {
						listOfPalletCard.add(dryOutPallet.getPalletCard());
					} else {
						Integer index = listOfPalletCard.indexOf(dryOutPallet.getPalletCard());
						listOfPalletCard.set(index, dryOutPallet.getPalletCard());
					}
				}
			}
			refreshTablePalletCard();
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	public void refreshTablePalletCard() {
		try {
			tblPalletCard.setModel(new PalletCardTableModel(listOfPalletCard));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
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

		private List<PalletCard> listOfPalletCard;

		public PalletCardTableModel(List<PalletCard> listOfPalletCard) {
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
			return 8;
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
			PalletCard p = listOfPalletCard.get(rowIndex);
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
				return p.getLength();
			case 5:
				return p.getWidth();
			case 6:
				return p.getThickness();
			case 7:
				return p.getVolume();
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
				return String.class;
			case 4:
				return Integer.class;
			case 5:
				return Integer.class;
			case 6:
				return Integer.class;
			case 7:
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
				return "Panjang";
			case 5:
				return "Lebar";
			case 6:
				return "Tebal";
			case 7:
				return "Total Volume";
			default:
				return "";
			}
		}
	}
}
