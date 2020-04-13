package com.valbetafarmer.core;

import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.valbetafarmer.var.Constants;
import com.valbetafarmer.var.Variables;

public class MainFunction extends SwingWorker<Void, Void> {
	private static long millisRefresh = 60 * 1000;
	
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
		
		while (true) {
			Random r = new Random();
			goToValorantStream(Variables.getDriver());
			Thread.sleep(millisRefresh * (r.nextInt(20) + 15));
		}
		
	}


	private static void goToValorantStream(WebDriver driver) throws InterruptedException {
		driver.get(Constants.TWITCH_VALORANT_PAGE);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.className(Constants.TW_LINK));
		for (WebElement link : links) {
			String att = link.getAttribute(Constants.DATA_A_TARGET);
			if (att != null && att.equals(Constants.DATA_A_TARGET_EXPCTD_VALUE)) {
				link.click();
				break;
			}
		}
	}


	@Override
	protected Void doInBackground() throws Exception {
		startFarm();
		return null;
	}
}
