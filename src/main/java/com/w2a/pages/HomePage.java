package com.w2a.pages;
import com.w2a.base.Page;
public class HomePage extends Page{	
	
	public LoginPage gotoLogin(){		
		click("loginlink_XPATH");
		return new LoginPage();
	}
	
}
