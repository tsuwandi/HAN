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

import main.panel.MainPanel;
import module.personalia.model.Division;
import module.personalia.model.PayrollComponent;
import module.util.Bridging;

public class PayrollComponentConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -3127283027621703632L;
	private JTable payrollComponentConfigTable;
	private JTextField searchField;
	private List<PayrollComponent> payrollComponents = new ArrayList<>();
	private PayrollComponentConfigTableModel payrollComponentConfigTableModel;

	public PayrollComponentConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > KOMPONEN PAYROLL");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 230, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR KOMPONEN PAYROLL");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 230, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		payrollComponentConfigTable = new JTable();
		payrollComponentConfigTable.setFocusable(false);
		payrollComponentConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(payrollComponentConfigTable);
		payrollComponentConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (payrollComponentConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewDivisionPanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreatePayrollComponentPanel");
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
				MainPanel.changePanel("module.personalia.ui.SearchDivisionPanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getUserData();
	}

	protected Division getSelectedData() {
		int row = payrollComponentConfigTable.getSelectedRow();

		Division division = new Division();
		division.setId(payrollComponentConfigTable.getValueAt(row, 1).toString());
		division.setName(payrollComponentConfigTable.getValueAt(row, 2).toString());

		return division;
	}

	private void getUserData() {
		payrollComponents.clear();
		//payrollComponents = ServiceFactory.getPersonaliaBL().getDivisions("");
		payrollComponentConfigTableModel = new PayrollComponentConfigTableModel(payrollComponents);
		payrollComponentConfigTable.setModel(payrollComponentConfigTableModel);
	}

	class PayrollComponentConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<PayrollComponent> payrollComponents;

		public PayrollComponentConfigTableModel(List<PayrollComponent> payrollComponents) {
			this.payrollComponents = payrollComponents;
		}

		@Override
		public int getColumnCount() {
			return 8;
		}

		@Override
		public int getRowCount() {
			return payrollComponents == null ? 0 : payrollComponents.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			PayrollComponent payrollComponent = payrollComponents.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return payrollComponents.indexOf(payrollComponent) + 1;
			case 1:
				return payrollComponent.getCode();
			case 2:
				return payrollComponent.getDescription();
			case 3:
				return payrollComponent.getPayrollStatus();
			case 4:
				return payrollComponent.getThrStatus();
			case 5:
				return payrollComponent.getBonusStatus();
			case 6:
				return payrollComponent.getReferenceDocument();
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
				return "Kode Komponen Payroll";
			case 2:
				return "Deskripsi Komponen Payroll";
			case 3:
				return "Status Gaji";
			case 4:
				return "Status THR";
			case 5:
				return "Status Bonus";
			case 6:
				return "Dokumen Referensi";
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
		payrollComponents = (List<PayrollComponent>) objects[0];
		payrollComponentConfigTableModel = new PayrollComponentConfigTableModel(payrollComponents);
		payrollComponentConfigTable.setModel(payrollComponentConfigTableModel);
	}
}