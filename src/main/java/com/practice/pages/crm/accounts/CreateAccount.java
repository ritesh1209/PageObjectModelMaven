package com.practice.pages.crm.accounts;

import com.practice.base.Page;

public class CreateAccount extends Page{
	

	public void createAccount(String accountName) throws InterruptedException {
		
		
		click("createAccIcon_CSS");
		click("subMenuAccLink_CSS");
		type("accountNameTxt_CSS",accountName);
		Thread.sleep(3000);
		
	}
	
	

}
