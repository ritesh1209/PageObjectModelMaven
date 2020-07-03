package com.practice.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.pages.crm.accounts.CreateAccount;

public class TopMenu {
	
	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		
		this.driver = driver;
	}

	public void goToLeads() {
		
		driver.findElement(By.cssSelector("div.lyteItem > a[href*='Leads']")).click();
	}

	public void goToContacts() {
		
		driver.findElement(By.cssSelector("div.lyteItem > a[href*='Contacts']")).click();
	}

	public CreateAccount goToAccounts() {
		
		Page.click("goToAccountTab_CSS");
		return new CreateAccount();
	}
	
	public void goToDeals() {
		
		driver.findElement(By.cssSelector("div.lyteItem > a[href*='Deals']")).click();
	}

	public void goToActivities() {
		
		driver.findElement(By.cssSelector("div.lyteItem > a[href*='Activities']")).click();
	}

	public void goToReports() {
		
		driver.findElement(By.cssSelector("div.lyteItem > a[href*='Reports']")).click();
	}
}
