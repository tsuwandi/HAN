package main.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.DialogBox;
import module.util.Bridging;
import module.util.DateUtil;

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
					
					
					/*Timestamp currentVersionDate = ServiceFactory.getSystemBL().validateVersion();
					
					if(currentVersionDate != null)
					{
						File path = getPath();
						
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(path.lastModified());
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					    Date parsedDate = dateFormat.parse(timeStamp);
					    Timestamp lastModified = new java.sql.Timestamp(parsedDate.getTime());
						//Timestamp lastModified = new Timestamp(path.lastModified());
						//int version = currentVersionDate.compareTo(lastModified);
						if(currentVersionDate.compareTo(lastModified) == 0)
						{
							loginPanel = new LoginPanel();
							loginPanel.setBounds(450, 200, 450, 250);
							glassPane.add(loginPanel);
						}
						else
						{
							DialogBox.showError("Version app tidak sesuai.");
							loginPanel = new LoginPanel();
							loginPanel.setBounds(450, 200, 450, 250);
							glassPane.add(loginPanel);
						}
					}*/
					
					loginPanel = new LoginPanel();
					loginPanel.setBounds(450, 200, 450, 250);
					glassPane.add(loginPanel);
					
					load();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static void load(){
		DecimalFormatSymbols df = new DecimalFormatSymbols(Locale.ENGLISH);
		df.setDecimalSeparator('.');
		df.setGroupingSeparator(',');
		AppConstants.FOUR_DIGIT_DECIMAL_FORMAT = new DecimalFormat("0.0000",df);
		
		AppConstants.commonMap = ServiceFactory.getSystemBL().commonConfigMap();
	}
	
	public static File getPath() throws URISyntaxException {
		return new File(MainPanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
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
