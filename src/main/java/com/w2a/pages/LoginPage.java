package com.w2a.pages;
import com.w2a.base.Page;
public class LoginPage extends Page {
	public ZohoAppPage doLogin(String userName, String password) {
		type("userid_XPATH", userName);
		click("signinButton_XPATH");
		type("password_XPATH", password);
		click("signinButton_XPATH");
		return new ZohoAppPage();
	}
}