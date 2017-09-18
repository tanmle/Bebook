package pages;

import common.AutomationActions;

public class AbstractActions extends AutomationActions {

	public void waitForAjaxLoading(int timeWait)
	{
		for (int i = 0; i < timeWait; i++) {
			if (!isControlDisplayed(bebook.AbstractPage.imgAjaxLoading)) {
				break;
			}
			sleep(1);
		}
	}
	
	public boolean doesMenuSelected(String menu)
	{
		return isDynamicControlDisplayed(bebook.AbstractPage.lblSelectedMenu, menu);
	}
}
