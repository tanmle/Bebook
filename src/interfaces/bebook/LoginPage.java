package bebook;

import org.openqa.selenium.By;

public class LoginPage {

	// -------------------Static Control--------------------
	public static final By loginIframe = By.cssSelector(".main_menu ~ iframe");
	public static final By txtUsername = By.id("login");
	public static final By txtPassword = By.id("password");
	public static final By btnLogin = By.id("loginSubmit");
}
