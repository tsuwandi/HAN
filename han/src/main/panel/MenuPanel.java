package main.panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.component.menu.AccordionItem;
import main.component.menu.AccordionLeafItem;
import main.component.menu.AccordionMenu;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setPreferredSize(new Dimension(500, 830));
		setLayout(null);

		AccordionMenu menu = new AccordionMenu();
		menu.setBounds(1, 0, 200, 360);
		
		menu.addNewMenu("General", "General");
		menu.addNewLeafTo("General", "Supplier", "Supplier", "module.supplier.ui.SupplierListPanel");
		menu.addNewLeafTo("General", "Produk", "Produk", "module.product.ui.ProductListPanel");
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Penerimaan", "Penerimaan");
		menu.addNewLeafTo("Penerimaan", "Penerimaan", "Penerimaan", "module.pembelian.ui.ListReceivedSecurityPanel");
		menu.addNewLeafTo("Penerimaan", "STTK", "STTK", "module.pembelian.ui.ListReceivedPanel");
		menu.addNewLeafTo("Penerimaan", "Pengeringan In", "Pengeringan In", "module.dryin.ui.DryInListPanel");
		menu.addNewLeafTo("Penerimaan", "Pengeringan Out", "Pengeringan Out", "module.dryout.ui.DryOutListPanel");
		menu.addNewLeafTo("Penerimaan", "Tutup Harian", "Tutup Harian", "module.dailyclosing.ui.DailyClosingReceivePanel");
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Laporan", "Laporan");
		//menu.addNewLeafTo("Laporan", "Stok Flow Basah", "Stok Flow Basah", "module.report.ui.DryStokFlowReportPanel");
		menu.addNewLeafTo("Laporan", "Rekap Kayu Masuk", "Rekap Kayu Masuk", "module.report.ui.RekapKayuMasukReportPanel");
		menu.addNewLeafTo("Laporan", "Stock Balken Basah", "Stock Balken Basah", "module.report.ui.StockBalkenBasahReportPanel");
		menu.addNewLeafTo("Laporan", "Penerimaan Kayu", "Penerimaan Kayu", "module.report.ui.ReceivedReportPanel");
		menu.addNewLeafTo("Laporan", "KD IN", "KD IN", "module.report.ui.KDINReportPanel");
		menu.addNewLeafTo("Laporan", "KD OUT", "KD OUT", "module.report.ui.KDOUTReportPanel");
		
		
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Tutup Harian", "Tutup Harian");
		menu.addNewLeafTo("Tutup Harian", "Send To Finance", "Send To Finance", "module.sendtofinance.ui.SendToFinancePanel");
		menu.addNewLeafTo("Tutup Harian", "Tutup Harian", "Tutup Harian", "module.dailyclosing.ui.DailyClosingPanel");
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Produksi", "Produksi");
		menu.addNewLeafTo("Produksi", "Produksi", "Produksi", "module.production.ui.ListProductionPanel");
		menu.addNewLeafTo("Produksi", "Sisa Produksi", "Downsize (Sisa Produksi)", "module.productionwaste.ui.ProductionWasteListPanel");
		menu.addNewLeafTo("Produksi", "Produksi PK", "Hasil Klem (Produksi PK)", "module.prodpk.ui.ProdPKListPanel");
		menu.addNewLeafTo("Produksi", "Pembelian", "Pembelian", "module.purchaseprodresult.ui.PurchaseProdResultListPanel");
		menu.addNewLeafTo("Produksi", "Pembelian", "Hasil Packing", "module.packingresult.ui.ListPackingResultPanel");
		menu.addNewLeafTo("Produksi", "Tutup Harian", "Tutup Harian", "module.dailyclosing.ui.DailyClosingProductionPanel");
		
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Finance", "Finance");
		menu.addNewLeafTo("Finance", "Finance", "Finance", "module.purchaseprodresultpayment.ui.PurchaseProdResultPaymentListPanel");
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Konfigurasi", "Konfigurasi");
		menu.addNewLeafTo("Konfigurasi", "Nama Latin Kayu", "Nama Latin Kayu", "module.sn.woodgenus.ui.WoodGenusListPanel");
		menu.addNewLeafTo("Konfigurasi", "Jenis Kayu", "Jenis Kayu", "module.sn.woodtype.ui.WoodTypeListPanel");
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Personalia", "Personalia");
		menu.addNewLeafTo("Personalia", "Divisi", "Divisi", "module.personalia.ui.DivisionConfigPanel");
		menu.addNewLeafTo("Personalia", "Departemen", "Departemen", "module.personalia.ui.DepartmentConfigPanel");
		menu.addNewLeafTo("Personalia", "Jabatan", "Jabatan", "module.personalia.ui.MSPositionConfigPanel");
		menu.addNewLeafTo("Personalia", "Tipe Karyawan", "Tipe Karyawan", "module.personalia.ui.EmployeeTypeConfigPanel");
		menu.addNewLeafTo("Personalia", "Karyawan", "Karyawan", "module.personalia.ui.EmployeeConfigPanel");
		menu.calculateAvaiableSpace();
		
		menu.addNewMenu("Payroll", "Payroll");
		menu.addNewLeafTo("Payroll", "Komponen Payroll", "Komponen Payroll", "module.personalia.ui.PayrollComponentConfigPanel");
		menu.addNewLeafTo("Payroll", "Pemetaan Gaji", "Pemetaan Gaji", "module.personalia.ui.PayrollMappingConfigPanel");
		menu.addNewLeafTo("Payroll", "Setting Gaji", "Setting Gaji", "module.personalia.ui.SalarySettingConfigPanel");
		menu.addNewLeafTo("Payroll", "Master Jenis Tunjangan Non Rutin", "Master Jenis Tunjangan Non Rutin", "module.personalia.ui.NonRoutineAllowanceMasterTypeConfigPanel");
		menu.addNewLeafTo("Payroll", "Master Tunjangan Non Rutin", "Master Tunjangan Non Rutin", "module.personalia.ui.NonRoutineAllowanceMasterConfigPanel");
		menu.addNewLeafTo("Payroll", "Tunjangan Non Rutin Karyawan", "Tunjangan Non Rutin Karyawan", "module.personalia.ui.NonRoutineAllowanceTransactionConfigPanel");
		
		menu.addNewMenu("Konfigurasi Sistem", "Konfigurasi Sistem");
		menu.addNewLeafTo("Konfigurasi Sistem", "Group", "Group", "module.system.ui.GroupConfigPanel");
		menu.addNewLeafTo("Konfigurasi Sistem", "User", "User", "module.system.ui.UserConfigPanel");
		menu.addNewLeafTo("Konfigurasi Sistem", "Group Akses", "Group Akses", "module.system.ui.GroupAccessConfigPanel");
		menu.calculateAvaiableSpace();
		
		setMouseAdapter(menu);

		add(menu);

	}
	
	/**
	 * Simple example to browse all Leaf of menu. In this case for each leaf it
	 * adds a new Mouse Adapter.
	 * 
	 * @param menu
	 *            Target Menu to modify.
	 */
	public void setMouseAdapter(AccordionMenu menu) {
		for (AccordionLeafItem leaf : menu.getAllLeafs()) {
			leaf.addMouseListener(getSimpleMouseAdapter());
		}
	}

	/**
	 * Creates a simple MouseAdapter binded to an AccordionItem. On mouse
	 * Pressed it writes on a textBox the source of event.
	 * 
	 * @return {@link MouseAdapter} object.
	 */
	public MouseAdapter getSimpleMouseAdapter() {
		return new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				AccordionItem item = (AccordionItem) e.getSource();
				MainPanel.changePanel(item.getModule());
			}
		};
	}
}
