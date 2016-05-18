package main.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import imcacat.jaccordion.JAccordion;
import menu.panel.Menu1Panel;
import javax.swing.SpringLayout;

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
		
		

		menuBtn1 = new JButton("Master Supplier");
		menuBtn1.setBounds(0, 35, 195, 35);
		menuBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.supplier.ui.SupplierListPanel");
			}
		});
		
		menuBtn2 = new JButton("Master Produk");
		menuBtn2.setBounds(0, 35, 195, 35);
		menuBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.product.ui.ProductListPanel");
			}
		});
		
		menuBtn3 = new JButton("Penerimaan");
		menuBtn3.setBounds(0, 35, 195, 35);
		menuBtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel");
			}
		});
		
		menuBtn4 = new JButton("STTK");
		menuBtn4.setBounds(0, 35, 195, 35);
		menuBtn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
			}
		});
	
		menuBtn5 = new JButton("Pengeringan In");
		menuBtn5.setBounds(0, 35, 195, 35);
		menuBtn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.dryin.ui.DryInListPanel");
			}
		});
		
		menuBtn6 = new JButton("Pengeringan Out");
		menuBtn6.setBounds(0, 35, 195, 35);
		menuBtn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.dryout.ui.DryOutListPanel");
			}
		});
		
		
		JAccordion menu = new JAccordion();
		menu.setBounds(1, 0, 199, 200);
	
		
		JAccordion subMenu = new JAccordion();
		Dimension menuSize = menu.getPreferredSize();
		Dimension subMenuSize = new Dimension(198, 207);
		subMenu.addSection("Supplier", menuBtn1);
		subMenu.addSection("Produk", menuBtn2);
		subMenu.addSection("Penerimaan", menuBtn3);
		subMenu.addSection("STTK", menuBtn4);
		subMenu.addSection("Pengeringan In", menuBtn5);
		subMenu.addSection("Pengeringan Out", menuBtn6);
		subMenu.setPreferredSize(subMenuSize);
		subMenu.enableMaxOneSectionOpen();
		
		
		if (subMenuSize.width > menuSize.width)
			menuSize.width = subMenuSize.width;
		if (subMenuSize.height > menuSize.height)
			menuSize.height = subMenuSize.height;
		
		menu.setSize(menuSize);
		menu.addSection("Pembelian", subMenu);
		menu.getSection(0).setOpen(true);;
		
//		JAccordion subAccordion = new JAccordion();
//		subAccordion.setBounds(0, 0, 500, 500);
//		subAccordion.addSection("Supplier", menuBtn1);
//		
//		JAccordion accordion = new JAccordion();
//		accordion.setBounds(0, 0, 600, 1800);
//		accordion.addSection("Pembelian", subAccordion);
		
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
