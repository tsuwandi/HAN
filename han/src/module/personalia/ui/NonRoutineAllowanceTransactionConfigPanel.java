package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.personalia.model.NonRoutineAllowanceTransaction;
import module.util.Bridging;

public class NonRoutineAllowanceTransactionConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -4588733710608236153L;
	private JTable nonRoutineAllowanceTransactionConfigTable;
	private JTextField searchField;
	private List<NonRoutineAllowanceTransaction> nonRoutineAllowanceTransactions = new ArrayList<>();
	private NonRoutineAllowanceTransactionConfigTableModel nonRoutineAllowanceMasterConfigTableModel;

	public NonRoutineAllowanceTransactionConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Payroll > Tunjangan Non Rutin Karyawan");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 350, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR TUNJANGAN NON RUTIN KARYAWAN");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 350, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		nonRoutineAllowanceTransactionConfigTable = new JTable();
		nonRoutineAllowanceTransactionConfigTable.setFocusable(false);
		nonRoutineAllowanceTransactionConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(nonRoutineAllowanceTransactionConfigTable);
		nonRoutineAllowanceTransactionConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nonRoutineAllowanceTransactionConfigTable.columnAtPoint(e.getPoint())==7) {
					MainPanel.changePanel("module.personalia.ui.ViewNonRoutineAllowanceTransactionPanel", getSelectedData());
				}
			}
		});

		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(724, 140, 190, 30);
		add(searchField);

		JButton newBtn = new JButton("Buat Baru");
		newBtn.setBounds(724, 100, 90, 30);
		add(newBtn);
		newBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.personalia.ui.CreateNonRoutineAllowanceTransactionPanel");
			}
		});

		JButton exportBtn = new JButton("Export");
		exportBtn.setBounds(824, 100, 90, 30);
		add(exportBtn);

		JButton explicitSearchBtn = new JButton("<html>Pencarian<br/>Lanjut</html>");
		explicitSearchBtn.setBounds(924, 100, 90, 30);
		add(explicitSearchBtn);
		explicitSearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.personalia.ui.SearchNonRoutineAllowancePanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getUserData();
	}

	protected NonRoutineAllowanceTransaction getSelectedData() {
		int row = nonRoutineAllowanceTransactionConfigTable.getSelectedRow();
		return nonRoutineAllowanceTransactions.get(row);
	}

	private void getUserData() {
		nonRoutineAllowanceTransactions.clear();
		nonRoutineAllowanceTransactions = ServiceFactory.getPersonaliaBL().getNonRoutineAllowanceTransactions("");
		nonRoutineAllowanceMasterConfigTableModel = new NonRoutineAllowanceTransactionConfigTableModel(nonRoutineAllowanceTransactions);
		nonRoutineAllowanceTransactionConfigTable.setModel(nonRoutineAllowanceMasterConfigTableModel);
	}

	class NonRoutineAllowanceTransactionConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -6612060578598517739L;
		private List<NonRoutineAllowanceTransaction> nonRoutineAllowanceTransactions;

		public NonRoutineAllowanceTransactionConfigTableModel(List<NonRoutineAllowanceTransaction> nonRoutineAllowanceTransactions) {
			this.nonRoutineAllowanceTransactions = nonRoutineAllowanceTransactions;
		}

		@Override
		public int getColumnCount() {
			return 8;
		}

		@Override
		public int getRowCount() {
			return nonRoutineAllowanceTransactions == null ? 0 : nonRoutineAllowanceTransactions.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			NonRoutineAllowanceTransaction nonRoutineAllowanceTransaction = nonRoutineAllowanceTransactions.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return nonRoutineAllowanceTransactions.indexOf(nonRoutineAllowanceTransaction) + 1;
			case 1:
				return nonRoutineAllowanceTransaction.getEmployeeCode();
			case 2:
				return nonRoutineAllowanceTransaction.getEmployeeName();
			case 3:
				return nonRoutineAllowanceTransaction.getNonRoutineAllowanceMasterType().getTnrType();
			case 4:
				return nonRoutineAllowanceTransaction.getNonRoutineAllowanceMaster().getTnr();
			case 5:
				return nonRoutineAllowanceTransaction.getNominal().toString();
			case 6:
				return nonRoutineAllowanceTransaction.getReferenceNumber();
			case 7:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			default:
				return String.class;
			}
		}

		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "No";
			case 1:
				return "NIK";
			case 2:
				return "Nama Karyawan";
			case 3:
				return "Jenis Tunjangan non Rutin";
			case 4:
				return "Tunjangan non Rutin";
			case 5:
				return "Nominal";
			case 6:
				return "Nomer Referensi";
			case 7:
				return "Action";
			default:
				return "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invokeObjects(Object... objects) {
		nonRoutineAllowanceTransactions = (List<NonRoutineAllowanceTransaction>) objects[0];
		nonRoutineAllowanceMasterConfigTableModel = new NonRoutineAllowanceTransactionConfigTableModel(nonRoutineAllowanceTransactions);
		nonRoutineAllowanceTransactionConfigTable.setModel(nonRoutineAllowanceMasterConfigTableModel);
	}
}