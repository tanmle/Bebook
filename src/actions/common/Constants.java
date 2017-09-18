package common;

import org.openqa.selenium.WebDriver;

public class Constants {
	/********** WebDriver *********/
	public static WebDriver DRIVER = null;
	
	/********** Value from ADH Configuration *********/
	public static String LOGIN_URL = DataActions.readXmlData(Constants.CONFIG_FILE_PATH, "adh/login_url");
	public static String USERNAME = DataActions.readXmlData(Constants.CONFIG_FILE_PATH, "adh/username");
	public static String PASSWORD = DataActions.readXmlData(Constants.CONFIG_FILE_PATH, "adh/password");
	public static String GRID = DataActions.readXmlData(Constants.CONFIG_FILE_PATH, "adh/grid");

	/********** File Location **********/
	public static final String CONFIG_FILE_PATH = "src\\configurations\\BebookConfiguration.xml";
	public static final String DATA_FILE_PATH = "src//Data//BebookData.json";
	public static final String CHROME_DRIVER_WIN_PATH = "src//resources//chromedriver.exe";
	public static final String CHROME_DRIVER_LINUX_PATH = "src//resources//chromedriver";
	public static final String GECKO_DRIVER_WIN_PATH = "src//resources//geckodriver.exe";
	public static final String GECKO_DRIVER_LINUX_PATH = "src//resources//geckodriver";

	/********** Timing Settings *******/
	public static final int MINI_TIME = 2;
	public static final int SHORT_TIME = 5;
	public static final int MEDIUM_TIME = 30;
	public static final int LONG_TIME = 120;
}
