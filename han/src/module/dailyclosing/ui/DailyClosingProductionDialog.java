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
import module.prodpk.model.ProdPKMaterial;
import module.prodpk.model.ProdPKResultProduct;
import module.production.model.ProdRM;
import module.production.model.Production;
import module.production.model.ProductionResult;
import module.production.model.ProductionResultProduct;
import module.productionwaste.model.ProductionResultProductWaste;
import module.purchaseprodresult.model.PPRProduct;
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
import module.packingresult.model.PackingRM;
import module.packingresult.model.PackingResult;

public class DailyClosingProductionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DailyClosingProductionDialog.class);

	JPanel panel;

	JButton btnOk;
	private JRadioButton rdbtnMonitor;
	private JRadioButton rdbtnFile;
	private JRadioButton rdbtnPrinter;
	private JTextField filename = new JTextField(), dir = new JTextField();

	public DailyClosingProductionDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 263, 203);
		getContentPane().setLayout(null);

		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String confirmCode = DailyClosingBL.makeConfirmCode();
					List<ProdRM> listOfProdRM = ServiceFactory.getDailyClosingBL().getAllProdRM();
					List<ProductionResultProduct> listOfProductionResultProduct = ServiceFactory.getDailyClosingBL().getAllProdResult();
					List<ProductionResultProductWaste> listOfProductionResultProductWaste = ServiceFactory.getDailyClosingBL().getAllProdWasteResult();
					List<ProdPKMaterial> listOfProdPKMaterial = ServiceFactory.getDailyClosingBL().getAllProdPKMaterial();
					List<ProdPKResultProduct> listOfProdPKResultProduct = ServiceFactory.getDailyClosingBL().getAllProdPKResultProduct();
					List<PPRProduct> listOfPPRProduct = ServiceFactory.getDailyClosingBL().getAllPPRProduct();
					List<PackingRM> listOfPackingRM = ServiceFactory.getDailyClosingBL().getAllPackingRM();
					List<PackingResult> listOfPackingResult = ServiceFactory.getDailyClosingBL().getAllPackingResult();
					
					
					/* Convert List to JRBeanCollectionDataSource */
					JRBeanCollectionDataSource itemsJRBeanListOfProdRM = new JRBeanCollectionDataSource(listOfProdRM);
					JRBeanCollectionDataSource itemsJRBeanListOfProductionResultProduct = new JRBeanCollectionDataSource(listOfProductionResultProduct);
					JRBeanCollectionDataSource itemsJRBeanListOfProductionResultProductWaste = new JRBeanCollectionDataSource(listOfProductionResultProductWaste);
					JRBeanCollectionDataSource itemsJRBeanListOfProdPKMaterial = new JRBeanCollectionDataSource(listOfProdPKMaterial);
					JRBeanCollectionDataSource itemsJRBeanListOfProdPKResultProduct = new JRBeanCollectionDataSource(listOfProdPKResultProduct);
					JRBeanCollectionDataSource itemsJRBeanListOfPPRProduct = new JRBeanCollectionDataSource(listOfPPRProduct);
					JRBeanCollectionDataSource itemsJRBeanListOfPackingRM = new JRBeanCollectionDataSource(listOfPackingRM);
					JRBeanCollectionDataSource itemsJRBeanListOfPackingResult = new JRBeanCollectionDataSource(listOfPackingResult);
					
					/* Map to hold Jasper report Parameters */
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("ItemDataSourceListOfProdRM", itemsJRBeanListOfProdRM);
					parameters.put("ItemDataSourceListOfProductionResultProduct", itemsJRBeanListOfProductionResultProduct);
					parameters.put("ItemDataSourceListOfProductionResultProductWaste", itemsJRBeanListOfProductionResultProductWaste);
					parameters.put("ItemDataSourceListOfProdPKMaterial", itemsJRBeanListOfProdPKMaterial);
					parameters.put("ItemDataSourceListOfProdPKResultProduct", itemsJRBeanListOfProdPKResultProduct);
					parameters.put("ItemDataSourceListOfPPRProduct", itemsJRBeanListOfPPRProduct);
					parameters.put("ItemDataSourceListOfPackingRM", itemsJRBeanListOfPackingRM);
					parameters.put("ItemDataSourceListOfPackingResult", itemsJRBeanListOfPackingResult);
					
					/*
					 * Using compiled version(.jasper) of Jasper report to
					 * generate PDF
					 */
//					JasperPrint jasperPrint = JasperFillManager.fillReport(
//							"src/module/dailyclosing/jasper/DailyClosing.jasper", parameters, new JREmptyDataSource());

					if (rdbtnMonitor.isSelected()) {
						if (isValid(listOfProdRM, listOfProductionResultProduct, listOfProductionResultProductWaste,
								listOfProdPKMaterial, listOfProdPKResultProduct, listOfPPRProduct, listOfPackingRM, listOfPackingResult) == true) {
							//JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

//							JDialog dialog = new JDialog();
//							//dialog.setContentPane(jasperViewer.getContentPane());
//							//dialog.setSize(jasperViewer.getSize());
//							dialog.setTitle("Laporan Tutup Harian Produksi");
//							dialog.setVisible(true);
//
//							setVisible(false);
//							dialog.toFront();
//							dialog.addWindowListener(new WindowAdapter() {
//								public void windowClosing(WindowEvent e) {
									int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin melakukan tutup harian produksi ?",
											"Peringatan", JOptionPane.WARNING_MESSAGE);

									if (response == JOptionPane.YES_OPTION) {
										//dialog.dispose();
										try {
											save(listOfProdRM,  listOfProductionResultProduct, listOfProductionResultProductWaste,
													listOfProdPKMaterial, listOfProdPKResultProduct, listOfPPRProduct, listOfPackingRM, listOfPackingResult, confirmCode);
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Gagal Memproses Tutup Harian",
													"Tutup Harian Produksi", JOptionPane.ERROR_MESSAGE);
										}
									}
//								}
//							});
						} else {
							JOptionPane.showMessageDialog(null, "Tidak ada data yang diproses.", "Tutup Harian Produksi",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
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
//						int rVal = c.showSaveDialog(DailyClosingProductionDialog.this);
//						if (rVal == JFileChooser.APPROVE_OPTION) {
//							filename.setText(c.getSelectedFile().getName());
//							dir.setText(c.getCurrentDirectory().toString());
//							
//							SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
//
//							String outputFile = dir.getText() + File.separatorChar + filename.getText()
//									+ sdf.format(new Date()).toString() + ".pdf";
//							
//							if (isValid(listOfProduction, listOfProdRM, listOfProductionResult, listOfProductionResultProduct) == true) {
//								/* outputStream to create PDF */
//								OutputStream outputStream = new FileOutputStream(new File(outputFile));
//								/* Write content to PDF file */
//								JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//								save(listOfProduction, listOfProdRM, listOfProductionResult, listOfProductionResultProduct, confirmCode);
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
//						
//					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Gagal Memproses Tutup Harian Produksi", "Tutup Harian Produksi",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnOk.setBounds(137, 123, 100, 30);
		getContentPane().add(btnOk);
		
		rdbtnMonitor = new JRadioButton("Process");
		rdbtnMonitor.setBounds(15, 19, 109, 23);
		getContentPane().add(rdbtnMonitor);

//		rdbtnMonitor = new JRadioButton("Monitor");
//		rdbtnMonitor.setBounds(15, 19, 109, 23);
//		getContentPane().add(rdbtnMonitor);
//
//		rdbtnFile = new JRadioButton("File");
//		rdbtnFile.setBounds(15, 54, 109, 23);
//		getContentPane().add(rdbtnFile);
//
//		rdbtnPrinter = new JRadioButton("Printer");
//		rdbtnPrinter.setBounds(15, 89, 109, 23);
//		getContentPane().add(rdbtnPrinter);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMonitor);
//		group.add(rdbtnMonitor);
//		group.add(rdbtnFile);
//		group.add(rdbtnPrinter);
		
		rdbtnMonitor.setSelected(true);

	}
	
	protected boolean isValid(List<ProdRM> listOfProdRM, List<ProductionResultProduct> listOfProductionResultProduct,
			List<ProductionResultProductWaste> listOfProductionResultProductWaste,
			List<ProdPKMaterial> listOfProdPKMaterial, List<ProdPKResultProduct> listOfProdPKResultProduct,
			List<PPRProduct> listOfPPRProduct,
			List<PackingRM> listOfPackingRM, List<PackingResult> listOfPackingResult) {
		if (listOfProdRM.isEmpty() 
				|| listOfProductionResultProduct.isEmpty()
				|| listOfProductionResultProductWaste.isEmpty()
				|| listOfProdPKMaterial.isEmpty()
				|| listOfProdPKResultProduct.isEmpty()
				|| listOfPPRProduct.isEmpty()
				|| listOfPackingRM.isEmpty()
				|| listOfProductionResultProduct.isEmpty())
		{
			return false;
		}
		
		return true;
	}

	public void save(List<ProdRM> listOfProdRM, List<ProductionResultProduct> listOfProductionResultProduct,
			List<ProductionResultProductWaste> listOfProductionResultProductWaste,
			List<ProdPKMaterial> listOfProdPKMaterial, List<ProdPKResultProduct> listOfProdPKResultProduct,
			List<PPRProduct> listOfPPRProduct,
			List<PackingRM> listOfPackingRM, List<PackingResult> listOfPackingResult,
			String confirmCode) throws SQLException {

		ServiceFactory.getDailyClosingBL().save(listOfProdRM,  listOfProductionResultProduct, listOfProductionResultProductWaste,
				listOfProdPKMaterial, listOfProdPKResultProduct, listOfPPRProduct, listOfPackingRM, listOfPackingResult, confirmCode);

		JOptionPane.showMessageDialog(null, "Tutup Harian Berhasil", "Tutup Harian Produksi", JOptionPane.INFORMATION_MESSAGE);

	}
}
