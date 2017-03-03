package main.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.DataSourceFactory;
import controller.ServiceFactory;
import main.component.DialogBox;
import model.User;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel usernameLbl;
	public JTextField usernameField;
	public JLabel passwordLbl;
	public JPasswordField passwordField;
	public JButton submitBtn;
	private User user;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setPreferredSize(new Dimension(450, 250));
		setBorder(new LineBorder(Color.WHITE, 2));
		// setBounds(400, 400, 400, 400);

		usernameLbl = new JLabel("NIK");
		usernameLbl.setBounds(30, 40, 150, 30);
		usernameLbl.setFont(new Font(null, Font.BOLD, 24));

		usernameField = new JTextField();
		usernameField.setBounds(200, 40, 230, 30);

		passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(30, 120, 150, 30);
		passwordLbl.setFont(new Font(null, Font.BOLD, 24));

		passwordField = new JPasswordField();
		passwordField.setBounds(200, 120, 230, 30);
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					login();
				}
			}
		});

		submitBtn = new JButton("Submit");
		submitBtn.setBounds(325, 200, 100, 30);
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//doLogin();
				login();
			}
		});
		setLayout(null);

		add(usernameLbl);
		add(usernameField);
		add(passwordLbl);
		add(passwordField);
		add(submitBtn);
		
		checkConnection();
	}

	@SuppressWarnings("deprecation")
	public void doLogin() {
		setUser(null);
		boolean test;
		Connection con;
		try {
			con = DataSourceFactory.getDataSource().getConnection();
			if (con != null) {
				setUser(new User());
				setVisible(false);
				MainPanel.glassPane.setVisible(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// user =
		// DaoFactory.getUserDao().getUserByIdAndPassword(usernameField.getText(),
		// passwordField.getText());
		// if(user != null){
		// setVisible(false);
		// MainPanel.glassPane.setVisible(false);
		// }
		// } catch (SQLException e) {
		// DialogBox.showErrorException();
		// e.printStackTrace();
		// }
		//

	}

	private void checkConnection(){
		Connection con;
		try {
			con = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			DialogBox.showError("Tidak dapat terhubung dengan database !");
			System.exit(1);
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void login() {
		module.system.model.User user = new module.system.model.User();
		user.setUsername(usernameField.getText());
		user.setPassword(new String(passwordField.getPassword()));
		
		if (ServiceFactory.getSystemBL().validateUser(user)) {
			ServiceFactory.getSystemBL().checkLogin(user);
			setVisible(false);
			MainPanel.glassPane.setVisible(false);
		} else {
			reset();
			DialogBox.showError("username atau password tidak sesuai");
		}
	}
	
	private void reset() {
		usernameField.setText("");
		passwordField.setText("");
	}
}