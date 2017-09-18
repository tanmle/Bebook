package bebook;

import org.openqa.selenium.By;

public class AbstractPage {

	public static final By imgAjaxLoading = By.cssSelector("#ajax_status img");
	
	// -------------------Dynamic Control--------------------
	public static final String lblSelectedMenu = "//span[@class='ui-selectmenu-text'][text()='%s']";
}
