package module.report.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.NumberField;
import module.dailyclosing.bl.DailyClosingBL;
import module.dailyclosing.model.InventoryLog;
import module.dailyclosing.ui.DailyClosingDialog;
import module.dryin.model.DryIn;
import module.dryout.model.DryOut;
import module.pembelian.model.Received;
import module.report.model.DryStockFlow;
import module.report.model.RekapKayuMasuk;
import module.sendtofinance.ui.SendToFinanceDialog;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

public class KDINReportDialog extends JDialog{
private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingDialog.class);

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnRincianHasilTallyKayuMasukKdPerChamber;
	private JRadioButton rdbtnRekapitulasiKayuMasukKdPerChamber;
	private JRadioButton rdbtnRekapitulasiKayuMasukKdUntukSemuaChamber;
	private JRadioButton rdbtnRincianStockKayuDalamKd;
	private JRadioButton rdbtnRekapitulasiStockKayuDalamKd;
	JLabel lblDateIn;
	JLabel lblChamberNo;
	JLabel lblNoPengeringan;
	//JLabel lblFromTo;
	
	JDateChooser dcDateFrom;
	//JDateChooser dcDateTo;
	JTextField txtChamberNo;
	JTextField txtNoPengeringan;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
	
	Map<String, Object> parameters = new HashMap<String, Object>();
	String startDate;
	String endDate;
	
	String title1 = "Rincian Hasil Tally Kayu Masuk KD Per Chamber";
	String title2 = "Rekapitulasi Kayu Masuk Kd Per Chamber";
	String title3 = "Rekapitulasi Kayu Masuk Kd Untuk Semua Chamber";
	String title4 = "Rincian Stock Kayu dalam KD";
	String title5 = "Rekapitulasi Stock Kayu dalam KD";

	public KDINReportDialog() {
		LOGGER.info("START FORM REPORT DIALOG.");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 378);
		getContentPane().setLayout(null);
		
		lblDateIn = new JLabel("Tanggal Masuk");
		lblDateIn.setBounds(35, 20, 120, 25);
		getContentPane().add(lblDateIn);
		
		dcDateFrom = new JDateChooser(new Date());
		dcDateFrom.setBounds(305, 20, 120, 25);
		dcDateFrom.setDateFormatString("dd-MM-yyyy");
		getContentPane().add(dcDateFrom);
		
//		lblFromTo = new JLabel("s/d");
//		lblFromTo.setBounds(280, 20, 20, 25);
//		getContentPane().add(lblFromTo);
//		
//		dcDateTo = new JDateChooser(new Date());
//		dcDateTo.setBounds(305, 20, 120, 25);
//		dcDateTo.setDateFormatString("dd-MM-yyyy");
//		getContentPane().add(dcDateTo);
		
		txtChamberNo = new JTextField();
		txtChamberNo.setBounds(305, 60, 120, 25);
		getContentPane().add(txtChamberNo);
		
		txtNoPengeringan = new NumberField(10);
		txtNoPengeringan.setBounds(305, 96, 120, 25);
		getContentPane().add(txtNoPengeringan);
		
	
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (rdbtnRincianHasilTallyKayuMasukKdPerChamber.isSelected()) {
//					if("".equals(txtChamberNo.getText()) || dcDateFrom.getDate() == null || 
//							"".equals(txtNoPengeringan.getText())) {
//						JOptionPane.showMessageDialog(null, "Silahkan isi salah satu paremeter pencarian.", "Error",
//								JOptionPane.ERROR_MESSAGE);
//					} else {
					doRincianHasilTallyKayuMasukKdPerChamber();
//					}
					
				} else if (rdbtnRekapitulasiKayuMasukKdPerChamber.isSelected()) {
					doRekapitulasiKayuMasukKdPerChamber();
				} else if (rdbtnRekapitulasiKayuMasukKdUntukSemuaChamber.isSelected()) {
					doRekapitulasiKayuMasukKdUntukSemuaChamber();
				} else if (rdbtnRincianStockKayuDalamKd.isSelected()) {
					doRincianStockKayuDalamKd();
				}  else if (rdbtnRekapitulasiStockKayuDalamKd.isSelected()) {
					doRekapitulasiStockKayuDalamKd();
				}
			}
		});
		btnOk.setBounds(325, 298, 100, 30);
		getContentPane().add(btnOk);
		
		rdbtnRincianHasilTallyKayuMasukKdPerChamber = new JRadioButton(title1);
		rdbtnRincianHasilTallyKayuMasukKdPerChamber.setBounds(15, 130, 280, 23);
		getContentPane().add(rdbtnRincianHasilTallyKayuMasukKdPerChamber);
		
		rdbtnRekapitulasiKayuMasukKdPerChamber = new JRadioButton(title2);
		rdbtnRekapitulasiKayuMasukKdPerChamber.setBounds(15, 165, 280, 23);
		getContentPane().add(rdbtnRekapitulasiKayuMasukKdPerChamber);

		rdbtnRekapitulasiKayuMasukKdUntukSemuaChamber = new JRadioButton(title3);
		rdbtnRekapitulasiKayuMasukKdUntukSemuaChamber.setBounds(15, 200, 280, 23);
		getContentPane().add(rdbtnRekapitulasiKayuMasukKdUntukSemuaChamber);
		
		rdbtnRincianStockKayuDalamKd = new JRadioButton(title4);
		rdbtnRincianStockKayuDalamKd.setBounds(15, 235, 280, 23);
		getContentPane().add(rdbtnRincianStockKayuDalamKd);
		
		rdbtnRekapitulasiStockKayuDalamKd = new JRadioButton(title5);
		rdbtnRekapitulasiStockKayuDalamKd.setBounds(15, 270, 280, 23);
		getContentPane().add(rdbtnRekapitulasiStockKayuDalamKd);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRincianHasilTallyKayuMasukKdPerChamber);
		group.add(rdbtnRekapitulasiKayuMasukKdPerChamber);
		group.add(rdbtnRekapitulasiKayuMasukKdUntukSemuaChamber);
		group.add(rdbtnRincianStockKayuDalamKd);
		group.add(rdbtnRekapitulasiStockKayuDalamKd);
		
		rdbtnRincianHasilTallyKayuMasukKdPerChamber.setSelected(true);
		
		lblChamberNo = new JLabel("Chamber No");
		lblChamberNo.setBounds(35, 56, 120, 25);
		getContentPane().add(lblChamberNo);
		
		lblNoPengeringan = new JLabel("No Pengeringan");
		lblNoPengeringan.setBounds(35, 96, 120, 25);
		getContentPane().add(lblNoPengeringan);
		

	}
	
	private void doRincianHasilTallyKayuMasukKdPerChamber() {
		JasperPrint jasperPrint;
		try {
			List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
			
			JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
					results);
			
			if(dcDateFrom.getDate() != null) { //&& dcDateTo.getDate() != null) {
				startDate = formatter.format(dcDateFrom.getDate());
				//endDate = formatter.format(dcDateTo.getDate());
			} else {
				startDate = formatter.format(new Date());
				//endDate = formatter.format(new Date());
			}
			
			parameters.put("ItemDataSourceResults", itemsJRBeanResults);
			parameters.put("startDate",startDate);
			//parameters.put("endDate",endDate);
			
			jasperPrint = JasperFillManager.fillReport(
					"src/module/report/jasper/kdin/LaporanRincianHasilTallyKayuMasukKdPerChamber.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title1);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses " + title1, title1,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doRekapitulasiKayuMasukKdPerChamber() {
		JasperPrint jasperPrint;
		try {
			List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
			
			JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
					results);
			
			if(dcDateFrom.getDate() != null) { //&& dcDateTo.getDate() != null) {
				startDate = formatter.format(dcDateFrom.getDate());
				//endDate = formatter.format(dcDateTo.getDate());
			} else {
				startDate = formatter.format(new Date());
				//endDate = formatter.format(new Date());
			}
			
			parameters.put("ItemDataSourceResults", itemsJRBeanResults);
			parameters.put("startDate",startDate);
			//parameters.put("endDate",endDate);
			
			jasperPrint = JasperFillManager.fillReport(
					"src/module/report/jasper/kdin/LaporanRekapitulasiKayuMasukKdPerChamber.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title2);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " + title2, title2,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doRekapitulasiKayuMasukKdUntukSemuaChamber() {
		JasperPrint jasperPrint;
		try {
			List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
			
			JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
					results);
			
			if(dcDateFrom.getDate() != null) { //&& dcDateTo.getDate() != null) {
				startDate = formatter.format(dcDateFrom.getDate());
				//endDate = formatter.format(dcDateTo.getDate());
			} else {
				startDate = formatter.format(new Date());
				//endDate = formatter.format(new Date());
			}
			
			parameters.put("ItemDataSourceResults", itemsJRBeanResults);
			parameters.put("startDate",startDate);
			//parameters.put("endDate",endDate);
			
			jasperPrint = JasperFillManager.fillReport(
					"src/module/report/jasper/kdin/LaporanRekapitulasiKayuMasukKdUntukSemuaChamber.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title3);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " +title3, title3,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doRincianStockKayuDalamKd() {
		JasperPrint jasperPrint;
		try {
			List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
			
			JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
					results);
			
			if(dcDateFrom.getDate() != null) { //&& dcDateTo.getDate() != null) {
				startDate = formatter.format(dcDateFrom.getDate());
				//endDate = formatter.format(dcDateTo.getDate());
			} else {
				startDate = formatter.format(new Date());
				//endDate = formatter.format(new Date());
			}
			
			parameters.put("ItemDataSourceResults", itemsJRBeanResults);
			parameters.put("startDate",startDate);
			//parameters.put("endDate",endDate);
			
			jasperPrint = JasperFillManager.fillReport(
					"src/module/report/jasper/kdin/LaporanRincianStockKayuDalamKd.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title4);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " +title4, title4,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	


	private void doRekapitulasiStockKayuDalamKd() {
		JasperPrint jasperPrint;
		try {
			List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
			
			JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
					results);
			
			if(dcDateFrom.getDate() != null) { //&& dcDateTo.getDate() != null) {
				startDate = formatter.format(dcDateFrom.getDate());
				//endDate = formatter.format(dcDateTo.getDate());
			} else {
				startDate = formatter.format(new Date());
				//endDate = formatter.format(new Date());
			}
			
			parameters.put("ItemDataSourceResults", itemsJRBeanResults);
			parameters.put("startDate",startDate);
			//parameters.put("endDate",endDate);
			
			jasperPrint = JasperFillManager.fillReport(
					"src/module/report/jasper/kdin/LaporanRekapitulasiStockKayuDalamKd.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title5);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " + title5, title5,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void showDialog(JasperViewer jasperViewer, String title) {
		JDialog dialog = new JDialog();
		dialog.setContentPane(jasperViewer.getContentPane());
		dialog.setSize(jasperViewer.getSize());
		dialog.setTitle(title);
		dialog.setVisible(true);

		setVisible(false);
		dialog.toFront();
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
			}
		});
	}
}
