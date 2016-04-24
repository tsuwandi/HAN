package module.product.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel titleLbl;
	public ProductListPanel() {
		setLayout(null);
		
		titleLbl = new JLabel("PRODUK");
		titleLbl.setBounds(20, 20, 200, 50);
		
		add(titleLbl);
	}

}
