package com.practice.pages;

import com.practice.base.Page;

public class HomePage extends Page{
	
	
	public void goToCustomers() {
		
		click("customersLink_CSS");
	}

	public void goToSupport() {
		
		click("supportLink_CSS");
	}

	public void goToContactSales() {
		
		click("contactLink_CSS");
	}
	
	public LoginPage goToLogin() {
		
		click("loginLink_CSS");
		
		return new LoginPage();
	}
	
	public void goToFreeSignUp() {
	
		click("signUpLink_CSS");
	}
	
	public void goToLearnMore() {
		
		click("learnMoreLink_CSS");
	}
	
	public void validateFooterLinks() {
		
	}

}
