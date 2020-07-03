package com.practice.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.utilities.Utilities;

public class LoginTest extends BaseTest{

	@Test (dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) throws InterruptedException {
		
		HomePage home = new HomePage();
		LoginPage login = home.goToLogin();
		login.doLogin(data.get("userName"), data.get("password"));

	}
}
