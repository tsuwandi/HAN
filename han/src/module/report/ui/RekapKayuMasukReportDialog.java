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

public class RekapKayuMasukReportDialog extends JDialog{
private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingDialog.class);

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnPerincianPembelianBalken;
	private JRadioButton rdbtnRekapitulasiPembelianBalken;
	private JRadioButton rdbtnPerincianPembelianBarecore;
	private JRadioButton rdbtnRekapitulasiPembelianBarecore;
	
	JLabel lblReceivedDate;
	JLabel lblFromTo;
	
	JDateChooser dcDateFrom;
	JDateChooser dcDateTo;

	public RekapKayuMasukReportDialog() {
		LOGGER.info("START FORM REPORT DIALOG.");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 280);
		getContentPane().setLayout(null);
		
		lblReceivedDate = new JLabel("Tanggal ");
		lblReceivedDate.setBounds(35, 20, 120, 25);
		getContentPane().add(lblReceivedDate);
		
		dcDateFrom = new JDateChooser(new Date());
		dcDateFrom.setBounds(150, 20, 120, 25);
		dcDateFrom.setDateFormatString("dd-MM-yyyy");
		getContentPane().add(dcDateFrom);
		
		lblFromTo = new JLabel("s/d");
		lblFromTo.setBounds(280, 20, 20, 25);
		getContentPane().add(lblFromTo);
		
		dcDateTo = new JDateChooser(new Date());
		dcDateTo.setBounds(305, 20, 120, 25);
		dcDateTo.setDateFormatString("dd-MM-yyyy");
		getContentPane().add(dcDateTo);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				String startDate;
				String endDate;
				
				if (rdbtnPerincianPembelianBalken.isSelected()) {
					JasperPrint jasperPrint;
					try {
						List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
						
						JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
								results);
						
						if(dcDateFrom.getDate() != null && dcDateTo.getDate() != null) {
							startDate = formatter.format(dcDateFrom.getDate());
							endDate = formatter.format(dcDateTo.getDate());
						} else {
							startDate = formatter.format(new Date());
							endDate = formatter.format(new Date());
						}
						
						parameters.put("ItemDataSourceResults", itemsJRBeanResults);
						parameters.put("startDate",startDate);
						parameters.put("endDate",endDate);
						
						jasperPrint = JasperFillManager.fillReport(
								"src/module/report/jasper/rekapkayumasuk/LaporanPerincianPembelianBalken.jasper", parameters, new JREmptyDataSource());
					
						JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
	
						showDialog(jasperViewer, "Laporan Perincian Pembelian Balken");
					} catch (JRException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Perincian Pembelian Balken", "Perincian Pembelian Balken",
								JOptionPane.ERROR_MESSAGE);
						setVisible(false);
					}
				}
				
				else if (rdbtnRekapitulasiPembelianBalken.isSelected()) {
					JasperPrint jasperPrint;
					try {
						jasperPrint = JasperFillManager.fillReport(
								"src/module/report/jasper/rekapkayumasuk/LaporanRekapitulasiPembelianBalken.jasper", parameters, new JREmptyDataSource());
					
						List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
						
						
						JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
								results);
						
						if(dcDateFrom.getDate() != null && dcDateTo.getDate() != null) {
							startDate = formatter.format(dcDateFrom.getDate());
							endDate = formatter.format(dcDateTo.getDate());
						} else {
							startDate = formatter.format(new Date());
							endDate = formatter.format(new Date());
						}
						
						parameters.put("ItemDataSourceResults", itemsJRBeanResults);
						parameters.put("startDate",startDate);
						parameters.put("endDate",endDate);
					
						JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
	
						showDialog(jasperViewer, "Laporan Rekapitulasi Pembelian Balken");
						
					} catch (JRException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Rekapitulasi Pembelian Balken", "Rekapitulasi Pembelian Balken",
								JOptionPane.ERROR_MESSAGE);
						setVisible(false);
					}
				}
				
				else if (rdbtnPerincianPembelianBarecore.isSelected()) {
					JasperPrint jasperPrint;
					try {
						List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
						
						JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
								results);
						
						if(dcDateFrom.getDate() != null && dcDateTo.getDate() != null) {
							startDate = formatter.format(dcDateFrom.getDate());
							endDate = formatter.format(dcDateTo.getDate());
						} else {
							startDate = formatter.format(new Date());
							endDate = formatter.format(new Date());
						}
						
						parameters.put("ItemDataSourceResults", itemsJRBeanResults);
						parameters.put("startDate",startDate);
						parameters.put("endDate",endDate);
						
						jasperPrint = JasperFillManager.fillReport(
								"src/module/report/jasper/rekapkayumasuk/LaporanPerincianPembelianBarecore.jasper", parameters, new JREmptyDataSource());
					
						JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
						
						showDialog(jasperViewer, "Laporan Perincian Pembelian Barecore");
						
					} catch (JRException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Perincian Pembelian Barecore", "Perincian Pembelian Barecore",
								JOptionPane.ERROR_MESSAGE);
						setVisible(false);
					}
				}
				
				else if (rdbtnRekapitulasiPembelianBarecore.isSelected()) {
					JasperPrint jasperPrint;
					try {
						jasperPrint = JasperFillManager.fillReport(
								"src/module/report/jasper/rekapkayumasuk/LaporanRekapitulasiPembelianBarecore.jasper", parameters, new JREmptyDataSource());
					
						List<RekapKayuMasuk> results = new ArrayList<RekapKayuMasuk>();
						
						
						JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
								results);
						
						if(dcDateFrom.getDate() != null && dcDateTo.getDate() != null) {
							startDate = formatter.format(dcDateFrom.getDate());
							endDate = formatter.format(dcDateTo.getDate());
						} else {
							startDate = formatter.format(new Date());
							endDate = formatter.format(new Date());
						}
						
						parameters.put("ItemDataSourceResults", itemsJRBeanResults);
						parameters.put("startDate",startDate);
						parameters.put("endDate",endDate);
						
						JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
	
						showDialog(jasperViewer, "Laporan Rekapitulasi Pembelian Barecore");
						
					} catch (JRException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Gagal Memproses Laporan Rekapitulasi Pembelian Barecore", "Rekapitulasi Pembelian Barecore",
								JOptionPane.ERROR_MESSAGE);
						setVisible(false);
					}
				}
				
			}
		});
		btnOk.setBounds(325, 200, 100, 30);
		getContentPane().add(btnOk);
		
		rdbtnPerincianPembelianBalken = new JRadioButton("Perincian Pembelian Balken");
		rdbtnPerincianPembelianBalken.setBounds(15, 54, 200, 23);
		getContentPane().add(rdbtnPerincianPembelianBalken);
		
		rdbtnRekapitulasiPembelianBalken = new JRadioButton("Rekapitulasi Pembelian Balken");
		rdbtnRekapitulasiPembelianBalken.setBounds(15, 89, 200, 23);
		getContentPane().add(rdbtnRekapitulasiPembelianBalken);

		rdbtnPerincianPembelianBarecore = new JRadioButton("Perincian Pembelian Barecore");
		rdbtnPerincianPembelianBarecore.setBounds(15, 124, 200, 23);
		getContentPane().add(rdbtnPerincianPembelianBarecore);
		
		rdbtnRekapitulasiPembelianBarecore = new JRadioButton("Rekapitulasi Pembelian Barecore");
		rdbtnRekapitulasiPembelianBarecore.setBounds(15, 158, 200, 23);
		getContentPane().add(rdbtnRekapitulasiPembelianBarecore);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPerincianPembelianBalken);
		group.add(rdbtnRekapitulasiPembelianBalken);
		group.add(rdbtnPerincianPembelianBarecore);
		group.add(rdbtnRekapitulasiPembelianBarecore);
		
		rdbtnPerincianPembelianBalken.setSelected(true);

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