package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.Utilities;

public class CreateAccountTest {
	
	@Test(dataProviderClass = Utilities.class,dataProvider ="dpPOModel")
	public void createAccountTest(Hashtable <String,String> data) {
		ZohoAppPage zp = new ZohoAppPage();
		zp.gotoCRM();
		AccountsPage ap = Page.menu.gotoAccounts();
		CreateAccountPage cap = ap.gotoCreateAccount();
		cap.CreateAccount(data.get("accountname"));		
		System.out.println("Logged in successfully");
	}
}
