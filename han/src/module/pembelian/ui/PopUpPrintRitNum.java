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

import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
//					printComponenet(panel);
					print(addReceivedDetailSecurityPanel.getRitNumberField().getText());
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

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		      log.error(ex.getMessage());
		      DialogBox.showError("Error While printing");
		  }
		}
	
	public void print(String ritNo){
		try {
			JTextArea text = new JTextArea();
			text.setText(ritNo);
			text.setAlignmentX(CENTER_ALIGNMENT);
			text.setFont(new Font("Courier New", Font.BOLD, 40));
			PrintRequestAttributeSet attrSet = new HashPrintRequestAttributeSet();

			// Set Margins
			// For A4 paper(width = 210mm X height = 297mm)
			attrSet.add(new MediaPrintableArea(50,10,200,50,Size2DSyntax.MM));
			
			//
			text.print(null, null, false, PrintServiceLookup.lookupDefaultPrintService(), attrSet, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
