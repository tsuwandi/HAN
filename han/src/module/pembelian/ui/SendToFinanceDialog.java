package module.pembelian.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.ServiceFactory;
import module.pembelian.model.Received;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class SendToFinanceDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnMonitor;
	private JRadioButton rdbtnFile;
	private JRadioButton rdbtnPrinter;

	public SendToFinanceDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 263, 203);
		getContentPane().setLayout(null);

		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/*
					 * java.sql.Connection conn =
					 * DataSourceFactory.getDataSource().getConnection();
					 * JasperDesign jDesign = JRXmlLoader.load(
					 * "src/module/report/StockBalkenBasahReport.jrxml"); String
					 * sql = "SELECT * FROM received order by id desc";
					 * JRDesignQuery jDesignQuery = new JRDesignQuery();
					 * jDesignQuery.setText(sql);
					 * jDesign.setQuery(jDesignQuery); JasperReport jreprt =
					 * JasperCompileManager.compileReport(jDesign); JasperPrint
					 * jprintt = JasperFillManager.fillReport(jreprt, null,
					 * conn); JasperViewer.viewReport(jprintt, false);
					 */

					String outputFile = "D:" + File.separatorChar + "OneDrive" + File.separatorChar
							+ "SendToFinanceFile.pdf";

					List<Received> listOfReceived = ServiceFactory.getSendToFinanceBL()
							.getAllBySendToFinanceDateIsNull();

					/* Convert List to JRBeanCollectionDataSource */
					JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listOfReceived);

					/* Map to hold Jasper report Parameters */
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("ItemDataSource", itemsJRBean);

					/*
					 * Using compiled version(.jasper) of Jasper report to
					 * generate PDF
					 */
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							"src/module/pembelian/jasper/SendToFinance.jasper", parameters, new JREmptyDataSource());

					if (rdbtnMonitor.isSelected()) {
						JasperViewer.viewReport(jasperPrint, false);
						int a = JasperViewer.EXIT_ON_CLOSE;
						System.out.println(JasperViewer.EXIT_ON_CLOSE );
						if(JasperViewer.EXIT_ON_CLOSE == 3) {
							setVisible(false);

							JOptionPane.showMessageDialog(null, "Send To Finance Berhasil", "Send To Finance",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (rdbtnFile.isSelected()) {
						/* outputStream to create PDF */
						OutputStream outputStream = new FileOutputStream(new File(outputFile));
						/* Write content to PDF file */
						JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
					} 
					
					// ServiceFactory.getSendToFinanceBL().update(listOfReceived);

//					setVisible(false);
//
//					JOptionPane.showMessageDialog(null, "Send To Finance Berhasil", "Send To Finance",
//							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Gagal Memproses Send To Finance", "Send To Finance",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnOk.setBounds(137, 123, 100, 30);
		getContentPane().add(btnOk);

		rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setBounds(15, 19, 109, 23);
		getContentPane().add(rdbtnMonitor);

		rdbtnFile = new JRadioButton("File");
		rdbtnFile.setBounds(15, 54, 109, 23);
		getContentPane().add(rdbtnFile);

		rdbtnPrinter = new JRadioButton("Printer");
		rdbtnPrinter.setBounds(15, 89, 109, 23);
		getContentPane().add(rdbtnPrinter);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMonitor);
		group.add(rdbtnFile);
		group.add(rdbtnPrinter);

		rdbtnMonitor.setSelected(true);

	}
}
