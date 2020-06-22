package com.w2a.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu 	extends Page{
	
	WebDriver driver;
	public TopMenu(WebDriver driver){
		this.driver = driver;
	}

	public AccountsPage gotoAccounts(){
		click("accountsMenu_XPATH");
		return new AccountsPage();
	}
	
	public void gotoDeals(){
	}
	
	public void gotoReports(){
	}
	
	public void gotoContacts(){
		
	}
	public void signOut(){
		driver.findElement(By.cssSelector("//*[@id='topdivuserphoto_4505767000000273001']")).click();
		driver.findElement(By.cssSelector("//a[contains(text(),'Sign Out')]")).click();
	}
	
}
