package com.dropsfarmer.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.dropsfarmer.var.Constants;
import com.dropsfarmer.var.Variables;

public class WebDriverChecker implements Runnable {

	@Override
	public void run() {
		File f = new File(Constants.WEBDRIVER_PROP_PATH);
		try {
			if (!f.createNewFile()) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String dir = br.readLine();
				br.close();
				if ((new File(dir).exists())) {
					Variables.setChrome_driver_route(dir);
				} else {
					Variables.setChrome_driver_route(null);
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error reading your webdriver properties file");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
