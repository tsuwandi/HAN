package module.dailyclosing.ui;

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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import com.sun.istack.internal.logging.Logger;

import controller.ServiceFactory;
import module.pembelian.model.Received;
import module.sendtofinance.ui.SendToFinanceDialog;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import module.dailyclosing.bl.DailyClosingBL;
import module.dryin.model.DryIn;
import module.dryout.model.DryOut;

public class DailyClosingDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingDialog.class);

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnMonitor;
	private JRadioButton rdbtnFile;
	private JRadioButton rdbtnPrinter;
	private JRadioButton rdbtnProcess;
	private JTextField filename = new JTextField(), dir = new JTextField();

	public DailyClosingDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 263, 203);
		getContentPane().setLayout(null);

		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String confirmCode = DailyClosingBL.makeConfirmCode();
					
					/* Convert List to JRBeanCollectionDataSource */
//					JRBeanCollectionDataSource itemsJRBeanListOfReceived = new JRBeanCollectionDataSource(
//							listOfReceived);
//					JRBeanCollectionDataSource itemsJRBeanListOfDryIn = new JRBeanCollectionDataSource(listOfDryIn);
//					JRBeanCollectionDataSource itemsJRBeanListOfDryOut = new JRBeanCollectionDataSource(listOfDryOut);
//
//					/* Map to hold Jasper report Parameters */
//					Map<String, Object> parameters = new HashMap<String, Object>();
//					parameters.put("ItemDataSourceListOfReceived", itemsJRBeanListOfReceived);
//					parameters.put("ItemDataSourceListOfDryIn", itemsJRBeanListOfDryIn);
//					parameters.put("ItemDataSourceListOfDryOut", itemsJRBeanListOfDryOut);

					/*
					 * Using compiled version(.jasper) of Jasper report to
					 * generate PDF
					 */
//					JasperPrint jasperPrint = JasperFillManager.fillReport(
//							"src/module/dailyclosing/jasper/DailyClosing.jasper", parameters, new JREmptyDataSource());

//					if (rdbtnMonitor.isSelected()) {
//						if (isValid(listOfReceived, listOfDryIn, listOfDryOut) == true) {
//							JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//
//							JDialog dialog = new JDialog();
//							dialog.setContentPane(jasperViewer.getContentPane());
//							dialog.setSize(jasperViewer.getSize());
//							dialog.setTitle("Laporan Tutup Harian");
//							dialog.setVisible(true);
//
//							setVisible(false);
//							dialog.toFront();
//							dialog.addWindowListener(new WindowAdapter() {
//								public void windowClosing(WindowEvent e) {
//									int response = JOptionPane.showConfirmDialog(null, "Apakah data sudah benar ?",
//											"Peringatan", JOptionPane.WARNING_MESSAGE);
//
//									if (response == JOptionPane.YES_OPTION) {
//										dialog.dispose();
//										try {
//											save(listOfReceived, listOfDryIn, listOfDryOut, confirmCode);
//										} catch (SQLException e1) {
//											e1.printStackTrace();
//											JOptionPane.showMessageDialog(null, "Gagal Memproses Tutup Harian",
//													"Tutup Harian", JOptionPane.ERROR_MESSAGE);
//										}
//									}
//								}
//							});
//						} else {
//							JOptionPane.showMessageDialog(null, "Tidak ada data yang diproses.", "Tutup Harian",
//									JOptionPane.INFORMATION_MESSAGE);
//							dispose();
//						}
//					} else if (rdbtnFile.isSelected()) {
//						JFileChooser c = new JFileChooser();
//						c.addChoosableFileFilter(new FileFilter() {
//						    public String getDescription() {
//						        return "PDF Documents (*.pdf)";
//						    }
//						 
//						    public boolean accept(File f) {
//						        if (f.isDirectory()) {
//						            return true;
//						        } else {
//						            return f.getName().toLowerCase().endsWith(".pdf");
//						        }
//						    }
//						});
//						// Demonstrate "Save" dialog:
//						int rVal = c.showSaveDialog(DailyClosingDialog.this);
//						if (rVal == JFileChooser.APPROVE_OPTION) {
//							filename.setText(c.getSelectedFile().getName());
//							dir.setText(c.getCurrentDirectory().toString());
//							
//							SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
//
//							String outputFile = dir.getText() + File.separatorChar + filename.getText()
//									+ sdf.format(new Date()).toString() + ".pdf";
//							
//							if (isValid(listOfReceived, listOfDryIn, listOfDryOut) == true) {
//								/* outputStream to create PDF */
//								OutputStream outputStream = new FileOutputStream(new File(outputFile));
//								/* Write content to PDF file */
//								JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//								save(listOfReceived, listOfDryIn, listOfDryOut, confirmCode);
//							} else {
//								JOptionPane.showMessageDialog(null, "Tidak ada data yang diproses.",
//										"Tutup Harian", JOptionPane.INFORMATION_MESSAGE);
//								dispose();
//							}
//						}
//						if (rVal == JFileChooser.CANCEL_OPTION) {
//							filename.setText("You pressed cancel");
//							dir.setText("");
//						}
//					}	
					if(rdbtnProcess.isSelected()) {
						ServiceFactory.getDailyClosingBL().doProcessDailyClosing();
						JOptionPane.showMessageDialog(null, "Sukses Memproses Tutup Harian", "Tutup Harian",
								JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Gagal Memproses Tutup Harian", "Tutup Harian",
							JOptionPane.ERROR_MESSAGE);
					setVisible(false);
				}

			}
		});
		btnOk.setBounds(137, 123, 100, 30);
		getContentPane().add(btnOk);
		
		rdbtnProcess = new JRadioButton("Process");
		rdbtnProcess.setBounds(15, 19, 109, 23);
		getContentPane().add(rdbtnProcess);
		
//		rdbtnMonitor = new JRadioButton("Monitor");
//		rdbtnMonitor.setBounds(15, 19, 109, 23);
//		getContentPane().add(rdbtnMonitor);

//		rdbtnFile = new JRadioButton("File");
//		rdbtnFile.setBounds(15, 54, 109, 23);
//		getContentPane().add(rdbtnFile);
//
//		rdbtnPrinter = new JRadioButton("Printer");
//		rdbtnPrinter.setBounds(15, 89, 109, 23);
//		getContentPane().add(rdbtnPrinter);

		ButtonGroup group = new ButtonGroup();
//		group.add(rdbtnMonitor);
//		group.add(rdbtnFile);
//		group.add(rdbtnPrinter);
		group.add(rdbtnProcess);
		
		rdbtnProcess.setSelected(true);

	}

	public boolean isValid(List<Received> listOfReceived, List<DryIn> listOfDryIn, List<DryOut> listOfDryOut) {
		if (!listOfReceived.isEmpty() || !listOfDryIn.isEmpty() || !listOfDryOut.isEmpty())
			return true;

		return false;
	}

	public void save(List<Received> listOfReceived, List<DryIn> listOfDryIn, List<DryOut> listOfDryOut,
			String confirmCode) throws SQLException {

		ServiceFactory.getDailyClosingBL().save(listOfReceived, listOfDryIn, listOfDryOut, confirmCode);

		JOptionPane.showMessageDialog(null, "Tutup Harian Berhasil", "Tutup Harian", JOptionPane.INFORMATION_MESSAGE);

	}
}
