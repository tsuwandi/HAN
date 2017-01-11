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

public class StockBalkenBasahReportDialog extends JDialog{
private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingDialog.class);

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnRincianStockBalkenBasahBySupplier;
	private JRadioButton rdbtnRincianStockBalkenBasahByThick;
	private JRadioButton rdbtnTotalStockBalkenBasah;
	private JRadioButton rdbtnStockPembelianBarecore;
	private JRadioButton rdbtnJumlahKayuAfkir;
	JLabel lblReceivedDate;
	//JLabel lblFromTo;
	
	JDateChooser dcDateFrom;
	//JDateChooser dcDateTo;
	JTextField txtSupplierName;
	JTextField txtThick;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
	
	Map<String, Object> parameters = new HashMap<String, Object>();
	String startDate;
	String endDate;
	JTextField txtSupplierNameAfkir;

	public StockBalkenBasahReportDialog() {
		LOGGER.info("START FORM REPORT DIALOG.");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 320);
		getContentPane().setLayout(null);
		
		lblReceivedDate = new JLabel("Tanggal");
		lblReceivedDate.setBounds(35, 20, 120, 25);
		getContentPane().add(lblReceivedDate);
		
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
		
		txtSupplierName = new JTextField();
		txtSupplierName.setBounds(305, 60, 120, 25);
		getContentPane().add(txtSupplierName);
		
		txtThick = new NumberField(10);
		txtThick.setBounds(305, 96, 120, 25);
		getContentPane().add(txtThick);
		
	
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (rdbtnRincianStockBalkenBasahBySupplier.isSelected()) {
					if("".equals(txtSupplierName.getText())) {
						JOptionPane.showMessageDialog(null, "Silahkan isi nama supplier.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						doRincianStockBalkenBasahBySupplier();
					}
					
				} else if (rdbtnRincianStockBalkenBasahByThick.isSelected()) {
					if("".equals(txtThick.getText())) {
						JOptionPane.showMessageDialog(null, "Silahkan isi ketebalan.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						doRincianStockBalkenBasahByThick();
					}
					
				} else if (rdbtnTotalStockBalkenBasah.isSelected()) {
					doTotalStockBalkenBasah();
				} else if (rdbtnStockPembelianBarecore.isSelected()) {
					doStockPembelianBarecore();
				}  else if (rdbtnJumlahKayuAfkir.isSelected()) {
					if("".equals(txtSupplierNameAfkir.getText())) {
						JOptionPane.showMessageDialog(null, "Silahkan isi nama supplier afkir.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						doJumlahKayuAfkir();
					}
				}
			}
		});
		btnOk.setBounds(325, 229, 100, 30);
		getContentPane().add(btnOk);
		
		rdbtnRincianStockBalkenBasahBySupplier = new JRadioButton("Rincian Stock Balken Basah Berdasarkan Supplier");
		rdbtnRincianStockBalkenBasahBySupplier.setBounds(15, 54, 280, 23);
		getContentPane().add(rdbtnRincianStockBalkenBasahBySupplier);
		
		rdbtnRincianStockBalkenBasahByThick = new JRadioButton("Rincian Stock Balken Basah Berdasarkan Tebal");
		rdbtnRincianStockBalkenBasahByThick.setBounds(15, 89, 280, 23);
		getContentPane().add(rdbtnRincianStockBalkenBasahByThick);

		rdbtnTotalStockBalkenBasah = new JRadioButton("Total Stock Balken Basah");
		rdbtnTotalStockBalkenBasah.setBounds(15, 124, 280, 23);
		getContentPane().add(rdbtnTotalStockBalkenBasah);
		
		rdbtnStockPembelianBarecore = new JRadioButton("Stock Pembelian Barecore");
		rdbtnStockPembelianBarecore.setBounds(15, 158, 280, 23);
		getContentPane().add(rdbtnStockPembelianBarecore);
		
		rdbtnJumlahKayuAfkir = new JRadioButton("Jumlah Kayu Afkir");
		rdbtnJumlahKayuAfkir.setBounds(15, 194, 280, 23);
		getContentPane().add(rdbtnJumlahKayuAfkir);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRincianStockBalkenBasahBySupplier);
		group.add(rdbtnRincianStockBalkenBasahByThick);
		group.add(rdbtnTotalStockBalkenBasah);
		group.add(rdbtnStockPembelianBarecore);
		group.add(rdbtnJumlahKayuAfkir);
		
		rdbtnRincianStockBalkenBasahBySupplier.setSelected(true);
		
		txtSupplierNameAfkir = new JTextField();
		txtSupplierNameAfkir.setBounds(305, 193, 120, 25);
		getContentPane().add(txtSupplierNameAfkir);
		

	}
	
	private void doRincianStockBalkenBasahBySupplier() {
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
					"src/module/report/jasper/stockbalkenbasah/LaporanRincianStockBalkenBasahBySupplier.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan Rincian Pembelian Balken Berdasarkan Supplier");
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Rincian Pembelian Balken Berdasarkan Supplier", "Rincian Pembelian Balken Berdasarkan Supplier",
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doRincianStockBalkenBasahByThick() {
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
					"src/module/report/jasper/stockbalkenbasah/LaporanRincianStockBalkenBasahByThick.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan Rincian Pembelian Balken Berdasarkan Ukuran Tebal");
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Rincian Pembelian Balken Berdasarkan Ukuran Tebal", "Rincian Pembelian Balken Berdasarkan Ukuran Tebal",
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doTotalStockBalkenBasah() {
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
					"src/module/report/jasper/stockbalkenbasah/LaporanTotalStockBalkenBasah.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan Total Stock Balken Basah");
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Total Stock Balken Basah", "Total Stock Balken Basah",
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	
	private void doStockPembelianBarecore() {
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
					"src/module/report/jasper/stockbalkenbasah/LaporanStockPembelianBarecore.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan Stock Pembelian Barecore");
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Stock Pembelian Barecore", "Stock Pembelian Barecore",
					JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}
	}
	


	private void doJumlahKayuAfkir() {
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
					"src/module/report/jasper/stockbalkenbasah/LaporanJumlahKayuAfkir.jasper", parameters, new JREmptyDataSource());
		
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

			showDialog(jasperViewer, "Laporan Jumlah Kayu Afkir");
		} catch (JRException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Jumlah Kayu Afkir", "Jumlah Kayu Afkir",
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
