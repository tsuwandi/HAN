package module.report.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import module.dailyclosing.model.InventoryLog;
import module.pembelian.model.Received;
import module.report.model.DryStockFlow;
import module.sendtofinance.ui.SendToFinanceDialog;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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
import java.awt.event.ActionEvent;

public class DryStokFlowReportDialog extends JDialog{
private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DryStokFlowReportDialog.class);

	JPanel panel;

	JLabel lblReceivedDate;
	JLabel lblFromTo;
	JLabel lblSupplierName;
	JLabel lblWoodType;
	
	JDateChooser dcDateFrom;
	JDateChooser dcDateTo;
	JTextField txtSupplierName;
	JTextField txtWoodType;
	
	public DryStokFlowReportDialog() {
		LOGGER.info("START FORM REPORT DIALOG.");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 219);
		getContentPane().setLayout(null);
		
		lblReceivedDate = new JLabel("Tanggal Penerimaan");
		lblReceivedDate.setBounds(30, 20, 120, 25);
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
		
		lblSupplierName = new JLabel("Nama Supplier");
		lblSupplierName.setBounds(30, 60, 120, 25);
		getContentPane().add(lblSupplierName);
		
		txtSupplierName = new JTextField();
		txtSupplierName.setBounds(150, 60, 120, 25);
		getContentPane().add(txtSupplierName);
		
		lblWoodType = new JLabel("Tipe Kayu");
		lblWoodType.setBounds(30, 100, 110, 25);
		getContentPane().add(lblWoodType);
		
		txtWoodType = new JTextField();
		txtWoodType.setBounds(150, 100, 120, 25);
		getContentPane().add(txtWoodType);
		
		JButton button = new JButton("Proses");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DryStockFlow dryStockFlow = new DryStockFlow();
				dryStockFlow.setStartFrom(dcDateFrom.getDate());
				dryStockFlow.setEndTo(dcDateTo.getDate());
				
				if(doValidate(dryStockFlow) == true) {
					
					List<InventoryLog> results = new ArrayList<InventoryLog>();
					try {
						results = ServiceFactory.getReportBL().getAllDryStock(dryStockFlow);
					} catch (SQLException e) {
						e.printStackTrace();
						LOGGER.error(e.getMessage());
					}
					
					if(results.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tidak ada data yang diproses.",
								"Laporan Stok Flow Basah", JOptionPane.INFORMATION_MESSAGE);
					} else {
						/* Convert List to JRBeanCollectionDataSource */
						JRBeanCollectionDataSource itemsJRBeanResults = new JRBeanCollectionDataSource(
								results);
						
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
						SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

						try {
							String startFrom = formatter.format(dryStockFlow.getStartFrom());
							String endTo = formatter.format(dryStockFlow.getEndTo());
							String generateDate = formatter.format(new Date());
							String generateTime = formatterTime.format(new Date());
							String user = "ADMIN";
							
							/* Map to hold Jasper report Parameters */
							Map<String, Object> parameters = new HashMap<String, Object>();
							parameters.put("ItemDataSourceResults", itemsJRBeanResults);
							parameters.put("startFrom",startFrom);
							parameters.put("endTo",endTo);
							parameters.put("generateDate",generateDate);
							parameters.put("generateTime",generateTime);
							parameters.put("user",user);
							/*
							 * Using compiled version(.jasper) of Jasper report to
							 * generate PDF
							 */
							try {
								JasperPrint jasperPrint = JasperFillManager.fillReport(
										"src/module/report/jasper/DryStockFlow.jasper", parameters, new JREmptyDataSource());
								
								JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
	
								JDialog dialog = new JDialog();
								dialog.setContentPane(jasperViewer.getContentPane());
								dialog.setSize(jasperViewer.getSize());
								dialog.setTitle("Laporan Stock Flow Basah");
								dialog.setVisible(true);
	
								setVisible(false);
								dialog.toFront();
								dialog.addWindowListener(new WindowAdapter() {
									public void windowClosing(WindowEvent e) {
										dialog.dispose();
									}
								});
							} catch (JRException e) {
								e.printStackTrace();
								LOGGER.error(e.getMessage());
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							LOGGER.error(e1.getMessage());
						}
					}
				}
				
			}
		});
		button.setBounds(305, 140, 120, 25);
		getContentPane().add(button);
	}
	
	private boolean doValidate(DryStockFlow dryStockFlow) {
		boolean isValid = false;
		if(dryStockFlow.getStartFrom() == null || dryStockFlow.getEndTo() == null) {
			JOptionPane.showMessageDialog(null, "Periode Tidak Boleh Kosong.",
					"Laporan Stok Flow Basah", JOptionPane.ERROR_MESSAGE);
			isValid = false;
		} else {
			isValid = true;
		}
		
		if(dryStockFlow.getStartFrom().compareTo(dryStockFlow.getEndTo())>0) {
			JOptionPane.showMessageDialog(null, "Periode Mulai tidak boleh lebih besar dari periode akhir.",
					"Laporan Stok Flow Basah", JOptionPane.ERROR_MESSAGE);
			isValid = false;
		} else {
			isValid = true;
		}
		
		return isValid;
	}
}
