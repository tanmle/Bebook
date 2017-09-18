package pages;

public class LoginActions extends AbstractActions {
	
	/***
	 * Login to Bebook
	 * 
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		switchToIframe(bebook.LoginPage.loginIframe);
		typeControl(bebook.LoginPage.txtUsername, username);
		typeControl(bebook.LoginPage.txtPassword, password);
		clickControl(bebook.LoginPage.btnLogin);
		switchToDefault();
	}
}
