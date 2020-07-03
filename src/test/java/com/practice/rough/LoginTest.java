package com.practice.rough;

import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.pages.ZohoAppPage;
import com.practice.pages.crm.CRMHomePage;
import com.practice.pages.crm.accounts.CreateAccount;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {

		HomePage home = new HomePage();
		
		LoginPage login = home.goToLogin();
		
		ZohoAppPage app = login.doLogin("24x7work08@gmail.com", "Tekion@123");
		
		CRMHomePage crm = app.goToCRM();
	
		CreateAccount account = crm.selectAccounts();
		
		account.createAccount("Ritesh K");
		
	}

}
