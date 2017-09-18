package pages;

import common.Constants;

public class HomeActions extends AbstractActions {
	
	
	/**
	 * Click Catalog button
	 */
	public void clickCatalogButton()
	{
		waitForControl(bebook.HomePage.btnCatalog, Constants.MEDIUM_TIME);
		clickControl(bebook.HomePage.btnCatalog);
	}
	
	public boolean doesCatalogButtonDisplay()
	{
		return isControlDisplayed(bebook.HomePage.btnCatalog);
	}
}
