package module.sendtofinance.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.apache.log4j.Logger;

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
	
	private static final Logger LOGGER = Logger.getLogger(SendToFinanceDialog.class);

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

					File file = new File("D:\\Output");
					if (!file.exists()) {
						file.mkdir();
					}

					SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyyyhhmmss");

					String outputFile = "D:" + File.separatorChar + "SendToFinanceFile_"
							+ sdf.format(new Date()).toString()
							+ ".pdf";

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
							"src/module/sendtofinance/jasper/SendToFinance.jasper", parameters, new JREmptyDataSource());

					if (rdbtnMonitor.isSelected()) {
						if (isValid(listOfReceived) == true) {
							JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

							JDialog dialog = new JDialog();
							dialog.setContentPane(jasperViewer.getContentPane());
							dialog.setSize(jasperViewer.getSize());
							dialog.setTitle("Laporan Send To Finance");
							dialog.setVisible(true);

							setVisible(false);
							dialog.toFront();
							dialog.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent e) {
									int response = JOptionPane.showConfirmDialog(null, "Apakah data sudah benar ?",
											"Peringatan", JOptionPane.WARNING_MESSAGE);

									if (response == JOptionPane.YES_OPTION) {
										dialog.dispose();
										try {
											update(listOfReceived);
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, "Gagal Memproses Send To Finance",
													"Send To Finance", JOptionPane.ERROR_MESSAGE);
										}
									}
								}
							});

						} else {
							JOptionPane.showMessageDialog(null, "Tidak ada data yang dikirim ke finance",
									"Send To Finance", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					} else if (rdbtnFile.isSelected()) {
						if (isValid(listOfReceived) == true) {
							/* outputStream to create PDF */
							OutputStream outputStream = new FileOutputStream(new File(outputFile));
							/* Write content to PDF file */
							JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

							update(listOfReceived);
						} else {
							JOptionPane.showMessageDialog(null, "Tidak ada data yang dikirim ke finance",
									"Send To Finance", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}

				} catch (Exception e1) {
					LOGGER.error(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Gagal Memproses Send To Finance", "Send To Finance",
							JOptionPane.ERROR_MESSAGE);
					dispose();
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

	public boolean isValid(List<Received> listOfReceived) {
		if (listOfReceived.isEmpty())
			return false;
		return true;
	}

	public void update(List<Received> listOfReceived) throws SQLException {

		ServiceFactory.getSendToFinanceBL().update(listOfReceived);

		JOptionPane.showMessageDialog(null, "Send To Finance Berhasil", "Send To Finance",
				JOptionPane.INFORMATION_MESSAGE);

	}
}
