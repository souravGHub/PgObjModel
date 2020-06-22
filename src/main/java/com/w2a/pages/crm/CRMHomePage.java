package com.w2a.pages.crm;
import org.openqa.selenium.By;
import com.w2a.base.Page;
public class CRMHomePage extends Page {
	public void pageTitle(){
		System.out.println(driver.getTitle());
	}
	public void verifyUserName(){
	boolean userName=	driver.findElement(By.xpath("//span[@id='show-uName']")).getText().contains("Sourav");
	if(userName){System.out.println("User name verified");}
	else{System.out.println("Wrong username");}
	}
}
