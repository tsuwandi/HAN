package main.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import menu.panel.Menu1Panel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton menuBtn1;
	private JButton menuBtn2;
	private JButton menuBtn3;
	private JButton menuBtn4;
	private JButton menuBtn5;
	private JButton menuBtn6;
	private JButton menuBtn7;

	public Menu1Panel menu1Panel;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setPreferredSize(new Dimension(200, 630));
		setLayout(null);

		menu1Panel = new Menu1Panel();
		menu1Panel.setBounds(0, 0, 1150, 630);

		menuBtn1 = new JButton("Master Karyawan");
		menuBtn1.setBounds(0, 0, 200, 35);
		menuBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.employee.ui.EmployeeListPanel");
			}
		});

		menuBtn2 = new JButton("Master Supplier");
		menuBtn2.setBounds(0, 35, 200, 35);
		menuBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
			}
		});

		menuBtn3 = new JButton("Master Produk");
		menuBtn3.setBounds(0, 70, 200, 35);
		menuBtn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.product.ui.ProductListPanel");
			}
		});

		menuBtn4 = new JButton("Pemasukan");
		menuBtn4.setBounds(0, 105, 200, 35);
		menuBtn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.dryin.ui.DryInListPanel");
			}
		});

		menuBtn5 = new JButton("Pengeluaran");
		menuBtn5.setBounds(0, 140, 200, 35);
		menuBtn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
			}
		});

		menuBtn6 = new JButton("Penerimaan dari Truk");
		menuBtn6.setBounds(0, 175, 200, 35);
		menuBtn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
			}
		});

		menuBtn7 = new JButton("Penerimaan Balken");
		menuBtn7.setBounds(0, 210, 200, 35);
		menuBtn7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainPanel.changePanel("module.pembelian.ui.ListReceived");
			}
		});

		add(menuBtn1);
		add(menuBtn2);
		add(menuBtn3);
		add(menuBtn4);
		add(menuBtn5);
		add(menuBtn6);
		add(menuBtn7);
	}

}
