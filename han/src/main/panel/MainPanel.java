package main.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import module.util.Bridging;

public class MainPanel extends JFrame {

	public JPanel headerPanel;
	// public JPanel menuPanel;
	public static JPanel bodyPanel;
	public static JPanel glassPane;
	public static LoginPanel loginPanel;
	public MenuPanel menuPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Windows".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {

				}

				try {
					MainPanel frame = new MainPanel();

					glassPane = new JPanel() {
						public void paintComponent(Graphics g) {
							g.setColor(new Color(0, 0, 0, 170));
							g.fillRect(0, 0, 1366, 768);
						}
					};
					// glassPane.setPreferredSize(new Dimension(1366, 768));
					glassPane.setOpaque(false);
					glassPane.setLayout(null);

					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setGlassPane(glassPane);
					glassPane.setVisible(true);

					bodyPanel = new JPanel();
					bodyPanel.setLayout(null);
					bodyPanel.setBorder(new LineBorder(Color.BLACK, 1));
					bodyPanel.setBounds(200, 100, 1166, 630);
					bodyPanel.setBackground(Color.LIGHT_GRAY);
					frame.add(bodyPanel);

					loginPanel = new LoginPanel();
					loginPanel.setBounds(450, 200, 450, 250);
					glassPane.add(loginPanel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);

		setLayout(null);
		setTitle("PT. HAN");

		headerPanel = new JPanel();
		headerPanel.setBorder(new LineBorder(Color.BLACK, 1));
		headerPanel.setBounds(0, 0, 1366, 100);
		headerPanel.setBackground(Color.WHITE);

		menuPanel = new MenuPanel();
		menuPanel.setBounds(0, 100, 200, 630);

		add(headerPanel);
		add(menuPanel);
	}

	public static void changePanel(String panel) {
		JPanel classPane;
		try {
			classPane = (JPanel) Class.forName(panel).newInstance();
			bodyPanel.removeAll();
			bodyPanel.revalidate();
			bodyPanel.repaint();
			classPane.setBounds(0, 0, 1366, 630);
			classPane.setBorder(new LineBorder(Color.BLACK, 1));
			bodyPanel.add(classPane);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static void changePanel(String panel, Object... objects) {
		JPanel classPane;
		try {
			classPane = (JPanel) Class.forName(panel).newInstance();
			if (classPane instanceof Bridging)
				((Bridging) classPane).invokeObjects(objects);
			bodyPanel.removeAll();
			bodyPanel.revalidate();
			bodyPanel.repaint();
			classPane.setBounds(0, 0, 1366, 630);
			classPane.setBorder(new LineBorder(Color.BLACK, 1));
			bodyPanel.add(classPane);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}
