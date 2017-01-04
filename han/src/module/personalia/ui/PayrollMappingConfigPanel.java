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
import module.personalia.model.PayrollMapping;
import module.util.Bridging;
import controller.ServiceFactory;

public class PayrollMappingConfigPanel extends JPanel implements Bridging{

	private static final long serialVersionUID = -1096646174228910632L;
	private JTable payrollMappingConfigTable;
	private JTextField searchField;
	private List<PayrollMapping> payrollMappings = new ArrayList<>();
	private PayrollMappingConfigTableModel payrollMappingConfigTableModel;

	public PayrollMappingConfigPanel() {
		setSize(1024, 630);
		setLayout(null);

		JLabel breadCrumbLbl = new JLabel("Personalia > Pemetaan Gaji");
		breadCrumbLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		breadCrumbLbl.setBounds(50, 10, 210, 25);
		add(breadCrumbLbl);

		JLabel lblHeader = new JLabel("DAFTAR PEMETAAN GAJI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 46, 210, 25);
		add(lblHeader);

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(null);
		pnlTable.setBounds(10, 215, 1004, 363);
		add(pnlTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1004, 363);
		pnlTable.add(scrollPane);

		payrollMappingConfigTable = new JTable();
		payrollMappingConfigTable.setFocusable(false);
		payrollMappingConfigTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(payrollMappingConfigTable);
		payrollMappingConfigTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (payrollMappingConfigTable.columnAtPoint(e.getPoint())==3) {
					MainPanel.changePanel("module.personalia.ui.ViewPayrollMappingPanel", getSelectedData());
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
				MainPanel.changePanel("module.personalia.ui.CreatePayrollMappingPanel");
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
				MainPanel.changePanel("module.personalia.ui.SearchPayrollMappingPanel");
			}
		});

		JButton search = new JButton("Pencarian");
		search.setBounds(924, 140, 90, 30);
		add(search);

		getUserData();
	}

	protected PayrollMapping getSelectedData() {
		int row = payrollMappingConfigTable.getSelectedRow();

		PayrollMapping payrollMapping = new PayrollMapping();
		

		return payrollMapping;
	}

	private void getUserData() {
		payrollMappings.clear();
		//payrollMappings = ServiceFactory.getPersonaliaBL().getDivisions("");
		payrollMappingConfigTableModel = new PayrollMappingConfigTableModel(payrollMappings);
		payrollMappingConfigTable.setModel(payrollMappingConfigTableModel);
	}

	class PayrollMappingConfigTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -5786040815921137590L;
		private List<PayrollMapping> payrollMappings;

		public PayrollMappingConfigTableModel(List<PayrollMapping> payrollMappings) {
			this.payrollMappings = payrollMappings;
		}

		@Override
		public int getColumnCount() {
			return 8;
		}

		@Override
		public int getRowCount() {
			return payrollMappings == null ? 0 : payrollMappings.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			PayrollMapping payrollMapping = payrollMappings.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return payrollMappings.indexOf(payrollMapping) + 1;
			case 1:
				return payrollMapping.getCode();
			case 2:
				return payrollMapping.getMsPosition().getName();
			case 3:
				return payrollMapping.getPayrollComponent().getDescription();
			case 4:
				return payrollMapping.getIsAbsent();
			case 5:
				return payrollMapping.getIsLeave();
			case 6:
				return payrollMapping.getReferenceDocument();
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
				return "Kode Pemetaan Gaji";
			case 2:
				return "Jabatan";
			case 3:
				return "Komponen Payroll";
			case 4:
				return "Status Mangkir";
			case 5:
				return "Status Ijin";
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
		payrollMappings = (List<PayrollMapping>) objects[0];
		payrollMappingConfigTableModel = new PayrollMappingConfigTableModel(payrollMappings);
		payrollMappingConfigTable.setModel(payrollMappingConfigTableModel);
	}
}