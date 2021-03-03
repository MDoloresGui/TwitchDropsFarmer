package com.dropsfarmer.core;

import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.dropsfarmer.var.Constants;
import com.dropsfarmer.var.Variables;

public class MainFunction extends SwingWorker<Void, Void> {	
	private static void startFarm() throws InterruptedException {
		String user = Variables.getLogin_user();
		String pass = Variables.getLogin_pass();
		System.setProperty(Constants.PROP_DRIVER, Variables.getChrome_driver_route());
		Variables.setDriver(new ChromeDriver());
		Variables.getDriver().get(Constants.TWITCH_LOGIN_PAGE); Thread.sleep(2000); WebElement
		loginEl = Variables.getDriver().findElement(By.id(Constants.ID_USER));
		loginEl.sendKeys(user); loginEl =
				Variables.getDriver().findElement(By.id(Constants.ID_PASS)); loginEl.sendKeys(pass);
		loginEl.sendKeys(Keys.ENTER); Thread.sleep(20000);
		var farm = Boolean.TRUE;
		while (farm) {
			 farm = goToDropsStreams(Variables.getDriver());
		}
	}

	private static boolean goToDropsStreams(WebDriver driver) throws InterruptedException {
		Random r = new Random();
		long initialTime = System.currentTimeMillis();
		long maxTime = 60 * 1000 * (r.nextInt(15) + 15);
		driver.get(Constants.TWITCH_GAME_ROOT_PAGE + Variables.getGame());
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.className(Constants.TW_LINK));
		var dropsFound = Boolean.FALSE;
		for (WebElement link : links) {
			String att = link.getAttribute(Constants.DATA_A_TARGET);
			if (att != null
					&& att.equals(Constants.DATA_A_TARGET_EXPCTD_VALUE)
					&& link.getText().toUpperCase().contains("DROPS")) {
				link.click();
				dropsFound = true;
				break;
			}
		}
		
		if (!dropsFound) {
			JOptionPane.showMessageDialog(null,
					String.format("The game %s doesn't have any streams with active drops", Variables.getGame()));
			return Boolean.FALSE;
		} else {
			Thread.sleep(2000);
			boolean live = Boolean.TRUE;
			while (live) {
				List<WebElement> liveList = driver.findElements(By.className(Constants.LIVE_INDICATOR));
				long diff = System.currentTimeMillis() - initialTime;
				if (liveList == null || liveList.size() == 0 || diff > maxTime) {
					live = Boolean.FALSE;
				}
				
				System.out.println("diff -> " + diff);
				System.out.println("maxTime -> " + maxTime);
				System.out.println("liveList -> " + liveList == null ? "null" : liveList.size());
				Thread.sleep(1000);
			}
			return Boolean.TRUE;
		}
		
	}


	@Override
	protected Void doInBackground() throws Exception {
		startFarm();
		this.done();
		return null;
	}
}
