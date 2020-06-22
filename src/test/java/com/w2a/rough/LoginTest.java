package com.w2a.rough;
import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.CRMHomePage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class LoginTest {
	public static void main(String[] args) {	
	HomePage homePg = new HomePage();	
	LoginPage lp = homePg.gotoLogin();
	ZohoAppPage zp=lp.doLogin("sourav2u06@gmail.com", "1qaz!QAZ_1");
	zp.gotoCRM();	
	AccountsPage accounts =Page.menu.gotoAccounts();
	CreateAccountPage cap = accounts.gotoCreateAccount();
	cap.CreateAccount("RAKSHAK");
	}
}
