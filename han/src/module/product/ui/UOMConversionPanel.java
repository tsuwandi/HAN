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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.panel.MainPanel;
import sun.tools.jar.resources.jar;

public class UOMConversionPanel extends JDialog {

	private JLabel titleLbl;
	private JLabel uomRefLbl;
	private JLabel qtyLbl;
	private JLabel destinationLbl;
	
	public JComboBox<String> uomRefField;
	public JTextField qtyField;
	public JComboBox<String> destinationField;
	
	public JButton insertBtn;

	/**
	 * Create the dialog.
	 */
	public UOMConversionPanel() {
		setLayout(null);
		setTitle("Konversi Unit of Measurement");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		uomRefLbl = new JLabel("UOM Acuan");
		uomRefLbl.setBounds(20, 20, 100, 30);
		
		qtyLbl = new JLabel("Jumlah");
		qtyLbl.setBounds(20, 50, 100, 30);
		
		destinationLbl = new JLabel("UOM Tujuan");
		destinationLbl.setBounds(20, 80, 100, 30);
		
		uomRefField = new JComboBox<>();
		uomRefField.setBounds(145, 20, 150, 25);
		
		qtyField = new JTextField();
		qtyField.setBounds(145, 50, 150, 25);
		
		destinationField = new JComboBox<>();
		destinationField.setBounds(145, 80, 150, 25);
		
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
		
		add(uomRefLbl);
		add(qtyLbl);
		add(destinationLbl);
		add(uomRefField);
		add(qtyField);
		add(destinationField);
		add(insertBtn);
	}

}
