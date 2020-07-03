package com.practice.pages;

import com.practice.base.Page;

public class LoginPage extends Page {

	
	public ZohoAppPage doLogin(String username, String password) throws InterruptedException {
		
		type("loginIdTxtBx_CSS", username);
		click("nextBtn_CSS");
		type("passwordTxtBx_CSS", password);
		Thread.sleep(1000);
		click("nextBtn_CSS");
		
		return new ZohoAppPage();
	}
	
	
}
