package com.valbetafarmer.var;

import org.openqa.selenium.WebDriver;

public class Variables {
	private static String chrome_driver_route = "";
	private static String login_user = "";
	private static String login_pass = "";
	private static WebDriver driver = null;
	
	
	public static String getChrome_driver_route() {
		return chrome_driver_route;
	}
	public static void setChrome_driver_route(String chrome_driver_route) {
		Variables.chrome_driver_route = chrome_driver_route;
	}
	public static String getLogin_user() {
		return login_user;
	}
	public static void setLogin_user(String login_user) {
		Variables.login_user = login_user;
	}
	public static String getLogin_pass() {
		return login_pass;
	}
	public static void setLogin_pass(String login_pass) {
		Variables.login_pass = login_pass;
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		Variables.driver = driver;
	}
	
	
}
