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

public class KDOUTReportDialog extends JDialog{
private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingDialog.class);

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnRincianBalkenKeluarKdPerChamber;
	private JRadioButton rdbtnRincianTotalStockBalkenKering;
	private JRadioButton rdbtnRekapitulasiTotalStockBalkenKering;
	JLabel lblDateIn;
	JLabel lblDateOut;
	JLabel lblNoPengeringan;
	//JLabel lblFromTo;
	
	JDateChooser dcDateFrom;
	JDateChooser dcDateTo;
	JTextField txtChamberNo;
	JTextField txtNoPengeringan;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
	
	Map<String, Object> parameters = new HashMap<String, Object>();
	String startDate;
	String endDate;
	
	String title1 = "Rincian Balken Keluar Kd Per Chamber";
	String title2 = "Rincian Total Stock Balken Kering";
	String title3 = "Rekapitulasi Total Stock Balken Kering";
	private JLabel lblChamberNo;

	public KDOUTReportDialog() {
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

		txtChamberNo = new JTextField();
		txtChamberNo.setBounds(305, 132, 120, 25);
		getContentPane().add(txtChamberNo);
		
		txtNoPengeringan = new NumberField(10);
		txtNoPengeringan.setBounds(305, 96, 120, 25);
		getContentPane().add(txtNoPengeringan);
		
	
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (rdbtnRincianBalkenKeluarKdPerChamber.isSelected()) {
					doRincianBalkenKeluarKdPerChamber();
				} else if (rdbtnRincianTotalStockBalkenKering.isSelected()) {
					doRincianTotalStockBalkenKering();
				} else if (rdbtnRekapitulasiTotalStockBalkenKering.isSelected()) {
					doRekapitulasiTotalStockBalkenKering();
				}
			}
		});
		btnOk.setBounds(325, 298, 100, 30);
		getContentPane().add(btnOk);
		
		rdbtnRincianBalkenKeluarKdPerChamber = new JRadioButton(title1);
		rdbtnRincianBalkenKeluarKdPerChamber.setBounds(15, 165, 280, 23);
		getContentPane().add(rdbtnRincianBalkenKeluarKdPerChamber);

		rdbtnRincianTotalStockBalkenKering = new JRadioButton(title2);
		rdbtnRincianTotalStockBalkenKering.setBounds(15, 200, 280, 23);
		getContentPane().add(rdbtnRincianTotalStockBalkenKering);
		
		rdbtnRekapitulasiTotalStockBalkenKering = new JRadioButton(title3);
		rdbtnRekapitulasiTotalStockBalkenKering.setBounds(15, 235, 280, 23);
		getContentPane().add(rdbtnRekapitulasiTotalStockBalkenKering);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRincianBalkenKeluarKdPerChamber);
		group.add(rdbtnRincianTotalStockBalkenKering);
		group.add(rdbtnRekapitulasiTotalStockBalkenKering);
		
		rdbtnRincianBalkenKeluarKdPerChamber.setSelected(true);
		
		lblDateOut = new JLabel("Tanggal Keluar");
		lblDateOut.setBounds(35, 56, 120, 25);
		getContentPane().add(lblDateOut);
		
		lblNoPengeringan = new JLabel("No Pengeringan");
		lblNoPengeringan.setBounds(35, 96, 120, 25);
		getContentPane().add(lblNoPengeringan);
		
		lblChamberNo = new JLabel("Chamber No");
		lblChamberNo.setBounds(35, 132, 120, 25);
		getContentPane().add(lblChamberNo);
		
		dcDateTo = new JDateChooser(new Date());
		dcDateTo.setDateFormatString("dd-MM-yyyy");
		dcDateTo.setBounds(305, 56, 120, 25);
		getContentPane().add(dcDateTo);
		

	}
	
	private void doRincianBalkenKeluarKdPerChamber() {
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
					"src/module/report/jasper/kdout/LaporanRincianBalkenKeluarKdPerChamber.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title1);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " + title1, title1,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doRincianTotalStockBalkenKering() {
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
					"src/module/report/jasper/kdout/LaporanRincianTotalStockBalkenKering.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title2);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " +title2, title2,
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doRekapitulasiTotalStockBalkenKering() {
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
					"src/module/report/jasper/kdout/LaporanRekapitulasiTotalStockBalkenKering.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan " + title3);
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan " +title3, title3,
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
