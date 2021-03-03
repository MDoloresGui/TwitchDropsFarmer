package com.dropsfarmer.var;

public class Constants {
	public static final String PROP_DRIVER = "webdriver.chrome.driver";
	public static final String WEB_DRIVER = "https://chromedriver.chromium.org/";
	public static final String TWITCH_LOGIN_PAGE = "https://www.twitch.tv/login";
	public static final String TWITCH_GAME_ROOT_PAGE = "https://www.twitch.tv/directory/game/";
	
	public static final String ID_USER = "login-username";
	public static final String ID_PASS = "password-input";
	public static final String TW_LINK = "tw-link";
	public static final String DATA_A_TARGET = "data-a-target";
	public static final String DATA_A_TARGET_EXPCTD_VALUE = "preview-card-title-link";
	public static final String LIVE_INDICATOR = "live-indicator-container";
	
	public static final String HOME_ENV = System.getProperty("user.home");
	public static final String WEBDRIVER_FILE = "tdf_webdriver.properties";
	public static final String WEBDRIVER_PROP_PATH = HOME_ENV + "\\" + WEBDRIVER_FILE;
}
