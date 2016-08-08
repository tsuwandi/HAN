package main.panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import imcacat.jaccordion.JAccordion;
import menu.panel.Menu1Panel;
import module.dailyclosing.ui.DailyClosingDialog;
import module.sendtofinance.ui.SendToFinanceDialog;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel menuBtn1;
	private JLabel menuBtn2;
	private JLabel menuBtn3;
	private JLabel menuBtn4;
	private JLabel menuBtn5;
	private JLabel menuBtn6;
	private JLabel menuBtn7;
	private JLabel menuBtn8;
	private JLabel menuBtn9;
	private JLabel menuBtn10;
	public Menu1Panel menu1Panel;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setPreferredSize(new Dimension(200, 630));
		setLayout(null);

		menu1Panel = new Menu1Panel();
		menu1Panel.setBounds(0, 0, 1150, 30);

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
				MainPanel.changePanel("module.pembelian.ui.ListReceivedSecurityPanel");
			}
		});

		menuBtn4 = new JLabel("<html><p style=padding:5px;>STTK</p><html>");
		menuBtn4.setBounds(0, 35, 195, 35);
		menuBtn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.pembelian.ui.ListReceivedPanel");
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

		// menuBtn7 = new JLabel("<html><p
		// style=padding:5px;>Laporan</p><html>");
		// menuBtn7.setBounds(0, 35, 195, 35);
		// menuBtn7.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// ReportDialog reportDialog = new ReportDialog();
		// reportDialog.setTitle("Laporan");
		// reportDialog.setLocationRelativeTo(null);
		// reportDialog.setVisible(true);
		// }
		// });

		menuBtn8 = new JLabel("<html><p style=padding:5px;>Send To Finance</p><html>");
		menuBtn8.setBounds(0, 35, 195, 35);
		menuBtn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk send to finance ?",
						"Peringatan", JOptionPane.WARNING_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					SendToFinanceDialog sendToFinanceDialog = new SendToFinanceDialog();
					sendToFinanceDialog.setTitle("Send To Finance");
					sendToFinanceDialog.setLocationRelativeTo(null);
					sendToFinanceDialog.setVisible(true);
				}
			}
		});

		menuBtn9 = new JLabel("<html><p style=padding:5px;>Tutup Harian</p><html>");
		menuBtn9.setBounds(0, 35, 195, 35);
		menuBtn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk tutup harian ?",
						"Peringatan", JOptionPane.WARNING_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {

					DailyClosingDialog dailyClosingDialog = new DailyClosingDialog();
					dailyClosingDialog.setTitle("Tutup Harian");
					dailyClosingDialog.setLocationRelativeTo(null);
					dailyClosingDialog.setVisible(true);

				}
			}
		});

		menuBtn10 = new JLabel("<html><p style=padding:5px;>Produksi</p><html>");
		menuBtn10.setBounds(0, 35, 195, 35);
		menuBtn10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanel.changePanel("module.production.ui.ListProductionPanel");
			}
		});

		JAccordion menu = new JAccordion();
		menu.setBounds(1, 0, 199, 200);

		JAccordion subMenu = new JAccordion();
		Dimension menuSize = new Dimension(198, 500);
		Dimension subMenuSize = new Dimension(198, 180);
		subMenu.addSection("Supplier", menuBtn1);
		subMenu.addSection("Produk", menuBtn2);
		subMenu.addSection("Penerimaan", menuBtn3);
		subMenu.addSection("STTK", menuBtn4);
		subMenu.addSection("Pengeringan In", menuBtn5);
		subMenu.addSection("Pengeringan Out", menuBtn6);
		subMenu.setPreferredSize(subMenuSize);
		subMenu.enableMaxOneSectionOpen();

		
		
		JAccordion subMenuTutupHarian = new JAccordion();
		Dimension menuSizeTutupHarian = new Dimension(198, 500);
		Dimension subMenuSizeTutupHarian = new Dimension(198, 80);
		subMenuTutupHarian.addSection("Send To Finance", menuBtn8);
		subMenuTutupHarian.addSection("Tutup Harian", menuBtn9);
		subMenuTutupHarian.setPreferredSize(subMenuSizeTutupHarian);
		subMenuTutupHarian.enableMaxOneSectionOpen();
		
		JAccordion subMenuProduksi = new JAccordion();
		Dimension menuSizeProduksi = new Dimension(198, 500);
		Dimension subMenuSizeProduksi = new Dimension(198, 55);
		subMenuProduksi.addSection("Produksi", menuBtn10);
		subMenuProduksi.setPreferredSize(subMenuSizeProduksi);
		subMenuProduksi.enableMaxOneSectionOpen();
		
		if (subMenuSize.width > menuSize.width)
			menuSize.width = subMenuSize.width;
		if (subMenuSize.height > menuSize.height)
			menuSize.height = subMenuSize.height;
		
		if (subMenuSizeTutupHarian.width > menuSizeTutupHarian.width)
			menuSizeTutupHarian.width = subMenuSizeTutupHarian.width;
		if (subMenuSizeTutupHarian.height > menuSizeTutupHarian.height)
			menuSizeTutupHarian.height = subMenuSizeTutupHarian.height;
		
		if (subMenuSizeProduksi.width > menuSizeProduksi.width)
			menuSizeProduksi.width = subMenuSizeProduksi.width;
		if (subMenuSizeProduksi.height > menuSizeProduksi.height)
			menuSizeProduksi.height = subMenuSizeProduksi.height;

		menu.setSize(menuSize);
		menu.addSection("Penerimaan", subMenu);
		menu.addSection("Tutup Harian", subMenuTutupHarian);
		menu.addSection("Produksi", subMenuProduksi);
		menu.enableMaxOneSectionOpen();

		add(menu);

	}
}
