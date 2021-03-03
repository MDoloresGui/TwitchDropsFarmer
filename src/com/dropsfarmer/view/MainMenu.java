package com.dropsfarmer.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.dropsfarmer.core.MainFunction;
import com.dropsfarmer.var.Variables;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8633468977431973508L;
	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField tfPassword;
	MainFunction mf = new MainFunction();
	private JTextField tfGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbLogin = new JLabel("ENTER TWITCH CREDENTIALS");
		lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogin.setFont(new Font("Calibri", Font.BOLD, 17));
		lbLogin.setBounds(10, 11, 310, 30);
		contentPane.add(lbLogin);
		
		tfUser = new JTextField();
		tfUser.setToolTipText("USER");
		tfUser.setBounds(10, 48, 310, 20);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setToolTipText("PASSWORD");
		tfPassword.setBounds(10, 79, 310, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		final JButton btExit = new JButton("EXIT");
		JButton btWebDriver = new JButton("SET WEBDRIVER");
		btWebDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DriverSelector ds = new DriverSelector();
				ds.setVisible(true);
				ds.setLocationRelativeTo(null);
				ds.setTitle("Choose Chrome Web Driver");
			}
		});
		
		btWebDriver.setBounds(10, 141, 310, 23);
		contentPane.add(btWebDriver);
		
		final JButton btStop = new JButton("STOP");
		btStop.setEnabled(false);
		
		final JButton btStart = new JButton("START");
		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Variables.setLogin_user(tfUser.getText().trim());
				Variables.setLogin_pass(new String(tfPassword.getPassword()));
				if (Variables.getChrome_driver_route() == null
						|| Variables.getChrome_driver_route().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please, set a Chrome WebDriver.");
				} else if(tfGame.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please, enter a Game to farm");
				} else {
					Variables.setGame(tfGame.getText().trim());
					btStart.setText("STARTED");
					btStart.setEnabled(false);
					btStop.setText("STOP");
					btStop.setEnabled(true);
					btExit.setEnabled(false);
					mf.execute();
				}
			}
		});
		btStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		btStart.setForeground(new Color(0, 128, 0));
		btStart.setBounds(10, 175, 310, 23);
		contentPane.add(btStart);
		
		
		btStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Variables.getDriver() != null) {
					mf.cancel(true);
					stopProcess(btStop, btStart, btExit);
				}
			}
		});
		btStop.setForeground(new Color(165, 42, 42));
		btStop.setFont(new Font("Tahoma", Font.BOLD, 11));
		btStop.setBounds(10, 209, 310, 23);
		contentPane.add(btStop);
		
		mf.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")
						&& StateValue.DONE.equals(mf.getState())) {
					stopProcess(btStop, btStart, btExit);
				}
				
			}
		});
		
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Variables.getDriver() != null) {
					Variables.getDriver().quit();
				}
				dispose();
				System.exit(0);
			}
		});
		btExit.setForeground(new Color(0, 0, 128));
		btExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btExit.setBounds(10, 243, 310, 23);
		contentPane.add(btExit);
		
		tfGame = new JTextField();
		tfGame.setToolTipText("GAME");
		tfGame.setBounds(10, 110, 310, 20);
		contentPane.add(tfGame);
		tfGame.setColumns(10);
	}
	
	private void stopProcess(JButton btStop, JButton btStart, JButton btExit) {
		Variables.getDriver().quit();
		mf = new MainFunction();
		btStop.setText("STOPPED");
		btStop.setEnabled(false);
		btStart.setEnabled(true);
		btStart.setText("START");
		btExit.setEnabled(true);
	}
}
