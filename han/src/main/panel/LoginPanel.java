package main.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.DaoFactory;
import dao.UserDao;
import model.User;

import java.awt.BorderLayout;
import java.awt.Color;

public class LoginPanel extends JPanel {

	public JLabel usernameLbl;
	public JTextField usernameField;
	public JLabel passwordLbl;
	public JPasswordField passwordField;
	public JButton submitBtn;
	public User user;
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setPreferredSize(new Dimension(450, 250));
		setBorder(new LineBorder(Color.WHITE, 2));
		//setBounds(400, 400, 400, 400);
		
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
		
		submitBtn = new JButton("Submit");
		submitBtn.setBounds(325, 200, 100, 30);
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doLogin();
					
			}
		});
		setLayout(null);
		
		add(usernameLbl);
		add(usernameField);
		add(passwordLbl);
		add(passwordField);
		add(submitBtn);
	}
	
	public void doLogin(){
		user = new User();
		boolean test;
		try {
			user = DaoFactory.getUserDao().getUserByIdAndPassword(usernameField.getText(), passwordField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null){
			System.out.println("Sukses");
			setVisible(false);
			MainPanel.glassPane.setVisible(false);
		}else{
			JOptionPane.showMessageDialog(null, "Username atau password salah");
			System.out.println("gagal");
		}
	}

}
