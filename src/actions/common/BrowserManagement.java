package common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserManagement {

	private WebDriver driver;

	public void openBrowser(String browser, String ip, String port) {
		try {
			if (Constants.GRID.equals("yes")) {
				String Node = "http://" + ip + ":" + port + "/wd/hub";
				if (browser.equals("chrome")) {
					new DesiredCapabilities();
					DesiredCapabilities caps = DesiredCapabilities.chrome();
					caps.setBrowserName("chrome");
					caps.setAcceptInsecureCerts(true);
					driver = new RemoteWebDriver(new URL(Node), caps);
				} else if (browser.equals("ie")) {
					new DesiredCapabilities();
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setBrowserName("internet explorer");
					caps.setJavascriptEnabled(true);
					caps.setAcceptInsecureCerts(true);
					driver = new RemoteWebDriver(new URL(Node), caps);
				} else {
					new DesiredCapabilities();
					DesiredCapabilities caps = DesiredCapabilities.firefox();
					caps.setBrowserName("firefox");
					caps.setAcceptInsecureCerts(true);
					driver = new RemoteWebDriver(new URL(Node), caps);
				}
			} else {
				if (browser.equals("chrome")) {
					if (System.getProperty("os.name").toLowerCase().contains("linux")) {
						System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LINUX_PATH);
					} else {
						System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_WIN_PATH);
					}
					
					DesiredCapabilities caps = DesiredCapabilities.chrome();
					caps.setBrowserName("chrome");
					caps.setAcceptInsecureCerts(true);
					driver = new ChromeDriver(caps);
				} else if (browser.equals("firefox")) {
					if (System.getProperty("os.name").toLowerCase().contains("linux")) {
						System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_LINUX_PATH);
					} else {
						System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_WIN_PATH);
					}
					
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setAcceptInsecureCerts(true);
					caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					driver = new FirefoxDriver(caps);
				}
			}

			driver.manage().timeouts().implicitlyWait(Constants.MEDIUM_TIME, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Constants.LONG_TIME, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Constants.DRIVER = driver;
	}

	public void navigateURL(String url) {
		Constants.DRIVER.get(url);
		Constants.DRIVER.manage().timeouts().pageLoadTimeout(Constants.LONG_TIME, TimeUnit.SECONDS);
	}

	public void closeBrowser() {
		Constants.DRIVER.close();
	}

	public void quitBrowser() {
		Constants.DRIVER.quit();
	}
}
