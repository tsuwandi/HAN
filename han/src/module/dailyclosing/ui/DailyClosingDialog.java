package module.dailyclosing.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.ServiceFactory;
import module.pembelian.model.Received;
import module.dailyclosing.bl.DailyClosingBL;
import module.dryin.model.DryIn;
import module.dryout.model.DryOut;

public class DailyClosingDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JPanel panel;
	
	JButton btnOk;
	private JRadioButton rdbtnMonitor;
	private JRadioButton rdbtnFile;
	private JRadioButton rdbtnPrinter;
	
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
					List<Received> listOfReceived = ServiceFactory.getDailyClosingBL().getAllReceivedForDailyClosing();
					List<DryIn> listOfDryIn = ServiceFactory.getDailyClosingBL().getAllDryInForDailyClosing();
					List<DryOut> listOfDryOut = ServiceFactory.getDailyClosingBL().getAllDryOutForDailyClosing();
					
					ServiceFactory.getDailyClosingBL().save(listOfReceived, listOfDryIn, listOfDryOut, confirmCode);
					
					setVisible(false);
					
					JOptionPane.showMessageDialog(null, "Tutup Harian Berhasil", "Send To Finance",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Gagal Memproses Tutup Harian", "Send To Finance",
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
