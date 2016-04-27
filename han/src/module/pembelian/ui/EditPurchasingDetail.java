package module.pembelian.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EditPurchasingDetail extends JPanel{
	
	JScrollPane scrollPane;
	JPanel smallPanel;
	
	JButton btn;
	public EditPurchasingDetail() {
		setLayout(null);
		smallPanel = new JPanel();
		smallPanel.setBounds(0, 0, 1100, 2000);
		smallPanel.setLayout(null);
		
		btn = new JButton("Test");
		btn.setBounds(100,100,100,30);
		smallPanel.add(btn);
		
		
		scrollPane = new JScrollPane(smallPanel);
		scrollPane.setBounds(0,0,1100,630);
		add(scrollPane);
	}
}
