package com.w2a.pages.crm.accounts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.w2a.base.Page;
public class CreateAccountPage extends Page{
		
	public void CreateAccount(String accName) {
		type("accountName_CSS",accName);
		type("phoneNum_CSS","(111)-111-2222");
		click("ratingSelect_XPATH");
		click("ratingValue_XPATH");
	}
}
