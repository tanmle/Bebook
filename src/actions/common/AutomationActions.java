package common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationActions {
	public WebElement findWebElement(By control) {
		return Constants.DRIVER.findElement(control);
	}

	public List<WebElement> findWebElements(By control) {
		return Constants.DRIVER.findElements(control);
	}

	public void waitControlVisibility(By control) {
		try {
			WebDriverWait wait = new WebDriverWait(Constants.DRIVER, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(control));
		} catch (Exception ex) {

		}
	}

	public String getControlText(By control) {
		return Constants.DRIVER.findElement(control).getText();
	}

	public String getControlAttribute(By control, String name) {
		return Constants.DRIVER.findElement(control).getAttribute(name);
	}

	public void typeControl(By control, String value) {
		WebElement object = findWebElement(control);
		object.clear();
		object.sendKeys(value);
	}

	public void typeTextArea(By area, String text) {
		Constants.DRIVER.switchTo().frame(findWebElement(area));
		WebElement editable = Constants.DRIVER.switchTo().activeElement();
		editable.sendKeys(text);
		Constants.DRIVER.switchTo().defaultContent();
	}

	public void sendKeyControl(By control, CharSequence... keys) {
		findWebElement(control).sendKeys(keys);
	}
	
	public void sendEnterKey(By control) {
		findWebElement(control).sendKeys(Keys.RETURN);
	}

	public void clearControl(By control) {
		findWebElement(control).clear();
	}

	public void clickControl(By control) {
		findWebElement(control).click();
	}
	
	public void doubleClickControl(By control) {
		Actions action = new Actions(Constants.DRIVER);
		action.doubleClick(findWebElement(control)).perform();
	}
	
	public void doubleClickDynamicControl(String control, String value) {
		Actions action = new Actions(Constants.DRIVER);
		action.doubleClick(findWebElement(By.xpath(String.format(control, value)))).perform();
	}

	public void clickControlByJS(By control) {
		WebElement element = findWebElement(control);
		JavascriptExecutor js = (JavascriptExecutor) Constants.DRIVER;
		js.executeScript("arguments[0].click();", element);
	}

	public void clickDynamicControl(String dynamicString, String... dynamicValue) {
		waitForDynamicControl(Constants.LONG_TIME, dynamicString, dynamicValue);
		clickControl(By.xpath(String.format(dynamicString, dynamicValue)));
	}

	public void selectComboboxItem(By combobox, String dynamicCombobox, String itemvalue) {
		clickControl(combobox);
		sleep(Constants.MINI_TIME);

		boolean temp = isDynamicControlDisplayed(dynamicCombobox, itemvalue);
		if (temp == false) {
			clickControl(combobox);
			sleep(Constants.MINI_TIME);
		}

		clickDynamicControl(dynamicCombobox, itemvalue);
	}

	public void selectCheckBoxOfItem(String dynamicControl, String itemName) {
		waitForDynamicControl(Constants.LONG_TIME, dynamicControl, itemName);
		clickControlByJS(By.xpath(String.format(dynamicControl, itemName)));
	}

	public void waitForDynamicControl(int timeout, String dynamicString, String... value) {
		String dynamicControl = String.format(dynamicString, value);
		WebDriverWait wait = new WebDriverWait(Constants.DRIVER, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicControl)));
	}

	public void waitForControl(By control, int timeout) {
		try {
			new FluentWait<WebDriver>(Constants.DRIVER).withTimeout(timeout, TimeUnit.SECONDS)
					.pollingEvery(timeout, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
					.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver driver) {
							WebElement element = findWebElement(control);
							return (element.isDisplayed());
						}
					});
		} catch (TimeoutException e) {
			System.out.println("Can not find the control in limited time");
		}
	}

	public void sleep(int timeout) {
		try {
			if (Constants.DRIVER.toString().contains("internet explorer"))
				Thread.sleep(timeout * 1000 * 2);
			else
				Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForPageLoad(int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(Constants.DRIVER, timeout);
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		} catch (Exception ex) {
		}

		waitForAjaxCompleted(timeout);
	}

	public void waitForAjaxCompleted(int timeout) {
		Boolean isJqueryUsed = (Boolean) ((JavascriptExecutor) Constants.DRIVER)
				.executeScript("return (typeof(jQuery) != 'undefined')");
		if (isJqueryUsed) {
			while (true) {
				Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) Constants.DRIVER)
						.executeScript("return jQuery.active == 0"));
				if (ajaxIsComplete)
					break;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void scrollWindowUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
	}

	public void scrollWindowdown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");
	}

	public boolean isControlDisplayed(By control) {
		try {
			if (findWebElement(control) != null)
				return findWebElement(control).isDisplayed();
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isDynamicControlDisplayed(String dynamicString, String... value) {
		try {
			String dynamicControl = String.format(dynamicString, value);
			if (findWebElement(By.xpath(dynamicControl)) != null)
				return (findWebElement(By.xpath(dynamicControl)).isDisplayed());
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isChildWindowDisplayed(String title) {
		boolean result = false;
		String parentWindow = Constants.DRIVER.getWindowHandle();
		for (String childWindow : Constants.DRIVER.getWindowHandles()) {
			sleep(Constants.MINI_TIME);
			Constants.DRIVER.switchTo().window(childWindow);
			if (Constants.DRIVER.getTitle().equals(title))
				result = true;
		}

		Constants.DRIVER.close();
		Constants.DRIVER.switchTo().window(parentWindow);

		return result;
	}

	public boolean isWindowDisplayed(String title) {
		boolean result = false;
		String getTitle = Constants.DRIVER.getTitle();
		result = getTitle.contains(title);
		return result;
	}

	public boolean isBorderColorCorrect(By control, String color) {
		boolean result = false;
		String getColor = findWebElement(control).getCssValue("border-bottom-color");
		String colorHex = Color.fromString(getColor).asHex();
		result = colorHex.equals(color);
		return result;
	}

	public void hoverControl(By element) {
		Actions hoverAction = new Actions(Constants.DRIVER);
		WebElement hoverElement = findWebElement(element);
		hoverAction.moveToElement(hoverElement);
	}

	// -----Alert messages-----//
	public void acceptAlertMessage() {
		Alert alert = Constants.DRIVER.switchTo().alert();
		alert.accept();
	}

	public void dismissAlertMessage() {
		Alert alert = Constants.DRIVER.switchTo().alert();
		alert.dismiss();
	}
	
	public void switchToIframe(By control)
	{
		Constants.DRIVER.switchTo().frame(findWebElement(control));
	}
	
	public void switchToDefault()
	{
		Constants.DRIVER.switchTo().defaultContent();
	}
}
