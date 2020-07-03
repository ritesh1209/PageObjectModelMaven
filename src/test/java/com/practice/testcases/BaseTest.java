package com.practice.testcases;

import org.testng.annotations.AfterSuite;

import com.practice.base.Page;

public class BaseTest {
	
	@AfterSuite
	public void tearDown() {
		Page.quit();
	}

}
