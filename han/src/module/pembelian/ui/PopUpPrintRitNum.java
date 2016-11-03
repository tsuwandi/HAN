package module.pembelian.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PopUpPrintRitNum extends JDialog {
	
	AddReceivedDetailSecurityPanel addReceivedDetailSecurityPanel;
	Logger log = LogManager.getLogger(PopUpPrintRitNum.class.getName());
	JDialog dialog;
	JPanel panel;
	JLabel label;
	JButton btnPrint;
	public PopUpPrintRitNum(JPanel parentPanel){
		super((JFrame)parentPanel.getTopLevelAncestor());
		addReceivedDetailSecurityPanel = (AddReceivedDetailSecurityPanel) parentPanel;
		setLayout(null);
		setSize(350,350);
		setTitle("Print");
//		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		this.dialog = this;
		
		panel = new JPanel();
		panel.setBounds(0,0,350,250);
		panel.setBackground(Color.white);
		panel.setLayout(null);
		add(panel);
		
		label = new JLabel(addReceivedDetailSecurityPanel.getRitNumberField().getText());
		label.setBounds(125,75,100,100);
		label.setFont(new Font("Tahoma", 1, 40));
		panel.add(label);
		
		btnPrint = new JButton("Cetak");
		btnPrint.setBounds(250,260,80,50);
		add(btnPrint);
		
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String defaultPrinter = 
				    PrintServiceLookup.lookupDefaultPrintService().getName();
				    System.out.println("Default printer: " + defaultPrinter);
				    PrintService service = PrintServiceLookup.lookupDefaultPrintService();

				    // prints the famous hello world! plus a form feed
				    InputStream is = new ByteArrayInputStream(addReceivedDetailSecurityPanel.getRitNumberField().getText().getBytes("UTF8"));

				    PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
				    pras.add(new Copies(1));

				    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				    Doc doc = new SimpleDoc(is, flavor, null);
				    DocPrintJob job = service.createPrintJob();

				    PrintJobWatcher pjw = new PrintJobWatcher(job);
				    job.print(doc, pras);
				    pjw.waitForDone();
				    is.close();
				    dialog.dispose();
				    addReceivedDetailSecurityPanel.save();
				} catch (Exception e2) {
					log.error("Error While Printing "+ e2.getMessage());
				}
				
			}
		});
		
//		dialog.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
//					dialog.dispose();
//				}
//			}
//		});
	}
	
	class PrintJobWatcher {
		  boolean done = false;

		  PrintJobWatcher(DocPrintJob job) {
		    job.addPrintJobListener(new PrintJobAdapter() {
		      public void printJobCanceled(PrintJobEvent pje) {
		        allDone();
		      }
		      public void printJobCompleted(PrintJobEvent pje) {
		        allDone();
		      }
		      public void printJobFailed(PrintJobEvent pje) {
		        allDone();
		      }
		      public void printJobNoMoreEvents(PrintJobEvent pje) {
		        allDone();
		      }
		      void allDone() {
		        synchronized (PrintJobWatcher.this) {
		          done = true;
		          System.out.println("Printing done ...");
		          log.info("Printin done ...");
		          PrintJobWatcher.this.notify();
		        }
		      }
		    });
		  }
		  public synchronized void waitForDone() {
		    try {
		      while (!done) {
		        wait();
		      }
		    } catch (InterruptedException e) {
		    }
		  }
		}
}
