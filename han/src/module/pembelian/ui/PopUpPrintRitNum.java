package module.pembelian.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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

import main.component.DialogBox;

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
					printComponenet(panel);
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
	
	public void printComponenet(JPanel panel){

		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		      if (pageNum > 0){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      panel.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		      log.error(ex.getMessage());
		      DialogBox.showError("Error While printing");
		  }
		}

}
