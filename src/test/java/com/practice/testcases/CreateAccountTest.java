package com.practice.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.practice.pages.ZohoAppPage;
import com.practice.pages.crm.CRMHomePage;
import com.practice.pages.crm.accounts.CreateAccount;
import com.practice.utilities.Utilities;

public class CreateAccountTest {
	
	@Test(dataProviderClass=Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String, String> data) throws InterruptedException {
		
		ZohoAppPage zapp = new ZohoAppPage();
		
		CRMHomePage crm = zapp.goToCRM();
		
		CreateAccount account = crm.selectAccounts();
		
		account.createAccount(data.get("accountName"));
		
	}

}
