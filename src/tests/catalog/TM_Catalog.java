package catalog;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bebook.HomePage;
import common.AbstractTest;
import common.Constants;
import pages.LoginActions;
import pages.CatalogActions;
import pages.HomeActions;;

public class TM_Catalog extends AbstractTest {
	private LoginActions loginActions = new LoginActions();
	private HomeActions homeActions = new HomeActions();
	private CatalogActions catalogActions = new CatalogActions();

	@BeforeMethod
	@Parameters({ "browser", "ip", "port" })
	public void set_up(String browser, String ip, String port) {
		log.info("Initiate webdriver");
		openBrowser(browser, ip, port);
		log.info("Pre-condition: Navigate to Login page");
		navigateURL(Constants.LOGIN_URL);
	}
	
	@Test(description = "Verify user can login with valid account")
	public void TC_LOGIN_001() {
		String orgCatalogName = "test20firsttest20201";
		String newCatalogName = "edit20firsttest20201";
		String orgCategoryName = "test20firsttest20201";
		String orgProductName = "test20firsttest20201";
		
		log.info("Step 1: Login with a valid account");
		loginActions.login(Constants.USERNAME, Constants.PASSWORD);
		
		log.info("VP 1: Verify that user is landing in Bebook Studio");
		verifyTrue(homeActions.doesCatalogButtonDisplay());
		
		log.info("Step 2: Click catalog button");
		homeActions.clickCatalogButton();
		
		log.info("VP 2: Verify that user is landing on the catalog overflow");
		verifyTrue(catalogActions.doesNewCatalogButtonDisplay());
		
		log.info("Step 3: Click New Catalog button");
		catalogActions.clickNewCatalogButton();
		
		log.info("VP 3: Verify that user is on the draft section");
		verifyTrue(catalogActions.doesMenuSelected("Draft"));
		
		log.info("Step 4: Click Validate button");
		catalogActions.clickValidateButton();
		
		log.info("Step 5: Enter catalog name");
		catalogActions.enterCatalogName(orgCatalogName);
		
		log.info("Step 6: Click catalog button");
		homeActions.clickCatalogButton();
		
		log.info("VP 4: Verify that title for created catalog displays properly");
		verifyTrue(catalogActions.doesCataLogTitleDisplay(orgCatalogName));
		
		log.info("Step 7: Edit catalog name");
		catalogActions.editCataLogName(orgCatalogName, newCatalogName);
		
		log.info("Step 8: Click catalog button");
		homeActions.clickCatalogButton();
		
		log.info("VP 5: Verify that new title for created catalog displays properly");
		verifyTrue(catalogActions.doesCataLogTitleDisplay(newCatalogName));
		
		log.info("Step 9: Click edit catalog button");
		catalogActions.clickEditCatalogButton();
		
		log.info("VP 6: Verify that edit catalog displays");
		verifyTrue(catalogActions.doesNewCategoryButtonDisplay());
		
		log.info("Step 10: Click add new category button");
		catalogActions.clickNewCategoryButton();
		
		log.info("Step 11: Enter category name");
		catalogActions.enterCategoryName(orgCategoryName);
		
		log.info("VP 7: Verify that new category name displays proprely");
		verifyTrue(catalogActions.doesCategoryDisplay(orgCategoryName));
		
		log.info("Step 12: Click add new product button");
		catalogActions.clickNewProductButton();
		
		log.info("Step 13: Enter new product name");
		catalogActions.enterProductName(orgProductName);
		
		log.info("VP 8: Verify that new product name displays proprely");
		verifyTrue(catalogActions.doesProductDisplay(orgProductName));
		
		log.info("Step 14: Delete catalog");
		homeActions.clickCatalogButton();
		catalogActions.clickCatalog(newCatalogName);
		catalogActions.clickDeleteCatalogButton();
		catalogActions.acceptDeleteCatalog();
		
		log.info("VP 9: Verify that catalog is deleted properly");
		verifyFalse(catalogActions.doesCataLogTitleDisplay(orgCatalogName));
	}

	@AfterMethod
	public void clean_up() {
		log.info("Post Condition: Close Browser");
		quitBrowser();
	}
}
