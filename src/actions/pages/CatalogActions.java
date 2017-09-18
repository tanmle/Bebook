package pages;

import common.Constants;

public class CatalogActions extends AbstractActions {

	/**
	 * Click New Catalog button
	 */
	public void clickNewCatalogButton()
	{
		waitForControl(bebook.CatalogPage.btnNewCatalog, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnNewCatalog);
	}
	
	/**
	 * Click Validate button
	 */
	public void clickValidateButton()
	{
		waitForControl(bebook.CatalogPage.btnValidate, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnValidate);
		waitForAjaxLoading(10);
	}
	
	/**
	 * Enter Catalog name
	 * @param name
	 */
	public void enterCatalogName(String name)
	{
		waitForControl(bebook.CatalogPage.divCatalogName, Constants.MEDIUM_TIME);
		doubleClickControl(bebook.CatalogPage.divCatalogName);
		waitForControl(bebook.CatalogPage.txtCatalogName, Constants.MEDIUM_TIME);
		clearControl(bebook.CatalogPage.txtCatalogName);
		sendKeyControl(bebook.CatalogPage.txtCatalogName, name);
		sendEnterKey(bebook.CatalogPage.txtCatalogName);
		waitForAjaxLoading(10);
	}
	
	public boolean doesNewCatalogButtonDisplay()
	{
		return isControlDisplayed(bebook.CatalogPage.btnNewCatalog);
	}
	
	public boolean doesCataLogTitleDisplay(String title)
	{
		switchToIframe(bebook.CatalogPage.ifmCatalog);
		String text = getControlText(bebook.CatalogPage.txtCatalogTitle);
		switchToDefault();
		return text.equals(title);
	}
	
	public void editCataLogName(String orgName, String newName)
	{
		doubleClickDynamicControl(bebook.CatalogPage.lblDynamicCatalogName, orgName);
		clearControl(bebook.CatalogPage.txtCatalogName);
		sendKeyControl(bebook.CatalogPage.txtCatalogName, newName);
		sendEnterKey(bebook.CatalogPage.txtCatalogName);
		waitForAjaxLoading(10);
	}
	
	public void clickEditCatalogButton()
	{
		waitForControl(bebook.CatalogPage.btnEditCatalog, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnEditCatalog);
	}
	
	public boolean doesNewCategoryButtonDisplay()
	{
		return isControlDisplayed(bebook.CatalogPage.btnNewCategory);
	} 
	
	public void clickNewCategoryButton()
	{
		waitForControl(bebook.CatalogPage.btnNewCategory, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnNewCategory);
		waitForAjaxLoading(10);
	}
	
	public void enterCategoryName(String name)
	{
		waitForControl(bebook.CatalogPage.spnFirstCategory, Constants.MEDIUM_TIME);
		doubleClickControl(bebook.CatalogPage.spnFirstCategory);
		waitForControl(bebook.CatalogPage.txtCategoryName, Constants.MEDIUM_TIME);
		clearControl(bebook.CatalogPage.txtCategoryName);
		sendKeyControl(bebook.CatalogPage.txtCategoryName, name);
		sendEnterKey(bebook.CatalogPage.txtCategoryName);
		waitForAjaxLoading(10);
	}
	
	public boolean doesCategoryDisplay(String name)
	{
		return isDynamicControlDisplayed(bebook.CatalogPage.lblDynamicCategoryName, name);
	}
	
	public void clickNewProductButton()
	{
		waitForControl(bebook.CatalogPage.btnNewProduct, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnNewProduct);
		waitForAjaxLoading(10);
	}
	
	public void enterProductName(String name)
	{
		waitForControl(bebook.CatalogPage.spnFirstProduct, Constants.MEDIUM_TIME);
		doubleClickControl(bebook.CatalogPage.spnFirstProduct);
		waitForControl(bebook.CatalogPage.txtProductName, Constants.MEDIUM_TIME);
		clearControl(bebook.CatalogPage.txtProductName);
		sendKeyControl(bebook.CatalogPage.txtProductName, name);
		sendEnterKey(bebook.CatalogPage.txtProductName);
		waitForAjaxLoading(10);
	}
	
	public boolean doesProductDisplay(String name)
	{
		return isDynamicControlDisplayed(bebook.CatalogPage.lblDynamicProductName, name);
	}
	
	public void clickCatalog(String name)
	{
		clickDynamicControl(bebook.CatalogPage.lblDynamicCatalogName, name);
	}
	
	public void clickDeleteCatalogButton()
	{
		waitForControl(bebook.CatalogPage.btnDeleteCatalog, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnDeleteCatalog);
		waitForAjaxLoading(10);
	}
	
	public void acceptDeleteCatalog()
	{
		waitForControl(bebook.CatalogPage.btnAccptDelete, Constants.MEDIUM_TIME);
		clickControl(bebook.CatalogPage.btnAccptDelete);
		waitForAjaxLoading(10);
	}
}
