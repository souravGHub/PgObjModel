package com.w2a.pages.crm.accounts;

import org.openqa.selenium.By;

import com.w2a.base.Page;

public class AccountsPage extends Page{

	public CreateAccountPage gotoCreateAccount() {
		 click("createaccIcon_CSS");
		 click("createaccIconsub_CSS");
		 return new CreateAccountPage();
		 
	 }
	 public void gotoImportAccount() {
		 
	 }
	
}
