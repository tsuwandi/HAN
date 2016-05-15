package module.product.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.panel.MainPanel;
import sun.tools.jar.resources.jar;

public class TaxPanel extends JDialog {

	
	private JLabel taxLbl;
	private JLabel taxValueLbl;
	
	public JComboBox<String> taxField;
	public JTextField taxValueField;
	public JButton insertBtn;
	/**
	 * Create the dialog.
	 */
	public TaxPanel() {
		setLayout(null);
		setTitle("Pajak");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		taxLbl = new JLabel("Pajak*");
		taxLbl.setBounds(20, 20, 100, 30);
		
		taxValueLbl = new JLabel("Nilai Pajak*");
		taxValueLbl.setBounds(20, 50, 100, 30);
		
		taxField = new JComboBox<>();
		taxField.setBounds(145, 20, 150, 25);
		
		taxValueField = new JTextField();
		taxValueField.setBounds(145, 50, 150, 25);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(300, 225, 75, 25);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				MainPanel.glassPane.setVisible(false);
				setVisible(false);
			}
			public void windowClosing(WindowEvent e){
				MainPanel.glassPane.setVisible(false);
				setVisible(false);
			}
		});
		
		add(taxLbl);
		add(taxValueLbl);
		add(taxField);
		add(taxValueField);
		add(insertBtn);
	}

}
