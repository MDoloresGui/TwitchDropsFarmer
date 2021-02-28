package com.dropsfarmer.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dropsfarmer.var.Constants;
import com.dropsfarmer.var.Variables;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DriverSelector extends JFrame {

	private JPanel contentPane;
	private JTextField tfRuta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverSelector frame = new DriverSelector();
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
	public DriverSelector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfRuta = new JTextField();
		tfRuta.setBounds(10, 57, 215, 20);
		contentPane.add(tfRuta);
		tfRuta.setColumns(10);

		JButton tfAbrir = new JButton("Browse");
		tfAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File selFile = openFileChooser();
				if (selFile != null) {
					setFileInTf(selFile);
				}
			}
		});
		tfAbrir.setBounds(235, 56, 89, 23);
		contentPane.add(tfAbrir);

		JLabel lblNewLabel = new JLabel("Select your Chrome WebDriver");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 314, 35);
		contentPane.add(lblNewLabel);

		JButton btCopyURL = new JButton("Copy DownloadURL to Clipboard");
		btCopyURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection selection = new StringSelection(Constants.WEB_DRIVER);
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				cb.setContents(selection, null);
			}
		});
		btCopyURL.setBounds(10, 84, 314, 23);
		contentPane.add(btCopyURL);

		JButton btOK = new JButton("OK");
		btOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFileChoosedInVariable();
			}

		});
		btOK.setBounds(334, 56, 83, 21);
		contentPane.add(btOK);

		JButton btClose = new JButton("Close");
		btClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btClose.setBounds(334, 84, 83, 23);
		contentPane.add(btClose);

		JButton btnNewButton = new JButton("INFO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This program needs a Chrome WebDriver to run. "
						+ "\nPlease, install Chrome on your PC and download the "
						+ "WebDriver from the download button's URL from the previous window.",
						"About WebDrivers", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBounds(334, 19, 83, 23);
		contentPane.add(btnNewButton);
	}

	private File openFileChooser() {
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(this);
		File file = jfc.getSelectedFile();

		return file;
	}

	private void setFileInTf(File selFile) {
		tfRuta.setText(selFile.getAbsolutePath());
	}

	private void setFileChoosedInVariable() {
		Variables.setChrome_driver_route(tfRuta.getText().trim());
		this.dispose();
	}
}
