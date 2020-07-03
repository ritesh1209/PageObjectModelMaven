package com.practice.pages;

import org.openqa.selenium.By;

import com.practice.base.Page;
import com.practice.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {

	
	public void goToBooks() {
		
		driver.findElement(By.xpath("//*[@id='zl-myapps']/div[1]/div[1]/div/a/div")).click();
	}

	public void goToCalendar() {
		
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[2]/div/a/div")).click();
	}

	public void goToCampaigns() {
		
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[3]/div/a/div")).click();
	}
	
	public void goToCliq() {
		
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[4]/div/a/div")).click();
	}
	
	public void goToConnect() {
	
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[5]/div/a/div")).click();
	}
	
	public CRMHomePage goToCRM() {
		
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[6]/div/a/div")).click();
		
		return new CRMHomePage();
	}
}
