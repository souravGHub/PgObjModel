package com.w2a.pages;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;
public class ZohoAppPage extends Page{		

	public CRMHomePage gotoCRM() {		
		click("crmIcon_XPATH");
		return new CRMHomePage();
	}	
}
