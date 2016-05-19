package main.panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import imcacat.jaccordion.JAccordion;
import menu.panel.Menu1Panel;
import module.report.ui.ReportDialog;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel menuBtn1;
	private JLabel menuBtn2;
	private JLabel menuBtn3;
	private JLabel menuBtn4;
	private JLabel menuBtn5;
	private JLabel menuBtn6;
	private JLabel menuBtn7;

	public Menu1Panel menu1Panel;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setPreferredSize(new Dimension(200, 630));
		setLayout(null);

		menu1Panel = new Menu1Panel();
		menu1Panel.setBounds(0, 0, 1150, 630);

		menuBtn1 = new JLabel("<html><p style=padding:5px;>Master Supplier</p><html>");
		menuBtn1.setBounds(50, 35, 195, 100);
		menuBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
			}
		});
		
		menuBtn2 = new JLabel("<html><p style=padding:5px;>Master Produk</p><html>");
		menuBtn2.setBounds(0, 35, 195, 35);
		menuBtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.product.ui.ProductListPanel");
			}
		});
		
		menuBtn3 = new JLabel("<html><p style=padding:5px;>Penerimaan</p><html>");
		menuBtn3.setBounds(0, 35, 195, 35);
		menuBtn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel");
			}
		});
		
		menuBtn4 = new JLabel("<html><p style=padding:5px;>STTK</p><html>");
		menuBtn4.setBounds(0, 35, 195, 35);
		menuBtn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
			}
		});
	
		menuBtn5 = new JLabel("<html><p style=padding:5px;>Pengeringan In</p><html>");
		menuBtn5.setBounds(0, 35, 195, 35);
		menuBtn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.dryin.ui.DryInListPanel");
			}
		});
		
		menuBtn6 = new JLabel("<html><p style=padding:5px;>Pengeringan Out</p><html>");
		menuBtn6.setBounds(0, 35, 195, 35);
		menuBtn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
			}
		});
		
		menuBtn7 = new JLabel("<html><p style=padding:5px;>Laporan</p><html>");
		menuBtn7.setBounds(0, 35, 195, 35);
		menuBtn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReportDialog reportDialog = new ReportDialog();
				reportDialog.setTitle("Laporan");
				reportDialog.setLocationRelativeTo(null);
				reportDialog.setVisible(true);
			}
		});
		
		
		JAccordion menu = new JAccordion();
		menu.setBounds(1, 0, 199, 200);
	
		
		JAccordion subMenu = new JAccordion();
		Dimension menuSize = menu.getPreferredSize();
		Dimension subMenuSize = new Dimension(198, 235);
		subMenu.addSection("Supplier", menuBtn1);
		subMenu.addSection("Produk", menuBtn2);
		subMenu.addSection("Penerimaan", menuBtn3);
		subMenu.addSection("STTK", menuBtn4);
		subMenu.addSection("Pengeringan In", menuBtn5);
		subMenu.addSection("Pengeringan Out", menuBtn6);
		subMenu.addSection("Laporan", menuBtn7);
		subMenu.setPreferredSize(subMenuSize);
		subMenu.enableMaxOneSectionOpen();
		
		
		if (subMenuSize.width > menuSize.width)
			menuSize.width = subMenuSize.width;
		if (subMenuSize.height > menuSize.height)
			menuSize.height = subMenuSize.height;
		
		menu.setSize(menuSize);
		menu.addSection("Pembelian", subMenu);
		menu.getSection(0).setOpen(false);

		menu.getSection(0).setOpen(true);
	
		add(menu);
		
		
		
//
//		menuBtn2 = new JButton("Master Supplier");
//		menuBtn2.setBounds(0, 35, 200, 35);
//		menuBtn2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
//			}
//		});
//
//		menuBtn3 = new JButton("Master Produk");
//		menuBtn3.setBounds(0, 70, 200, 35);
//		menuBtn3.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.product.ui.ProductListPanel");
//			}
//		});
//
//		menuBtn4 = new JButton("Pemasukan");
//		menuBtn4.setBounds(0, 105, 200, 35);
//		menuBtn4.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.dryin.ui.DryInListPanel");
//			}
//		});
//
//		menuBtn5 = new JButton("Pengeluaran");
//		menuBtn5.setBounds(0, 140, 200, 35);
//		menuBtn5.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
//			}
//		});
//
//		menuBtn6 = new JButton("Penerimaan dari Truk");
//		menuBtn6.setBounds(0, 175, 200, 35);
//		menuBtn6.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
//			}
//		});
//
//		menuBtn7 = new JButton("Penerimaan Balken");
//		menuBtn7.setBounds(0, 210, 200, 35);
//		menuBtn7.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel");
//			}
//		});
//
//		add(menuBtn1);
//		add(menuBtn2);
//		add(menuBtn3);
//		add(menuBtn4);
//		add(menuBtn5);
//		add(menuBtn6);
//		add(menuBtn7);
	}
}
