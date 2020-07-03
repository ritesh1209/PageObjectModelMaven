package com.practice.pages.crm;


import com.practice.base.Page;
import com.practice.base.TopMenu;
import com.practice.pages.crm.accounts.CreateAccount;

public class CRMHomePage extends Page{
	
	TopMenu menu = new TopMenu(driver);
	
	/*public void selectLeads() {
		
		menu.goToLeads();
		
	}*/

	public CreateAccount selectAccounts() {
		
		menu.goToAccounts();
		
		return new CreateAccount();
	}
}
