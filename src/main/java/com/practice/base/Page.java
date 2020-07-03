package com.practice.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.practice.utilities.ExcelReader;
import com.practice.utilities.ExtentManager;
import com.practice.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page {

	public static WebDriver driver;
	public static TopMenu menu;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	public Page() {

		if (driver == null) {
			
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\properties\\Config.properties");
				config.load(fis);
				log.debug("Config File Loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\properties\\OR.properties");
				OR.load(fis);
				log.debug("OR File Loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Jenkins Browser Filter Configuration
			if(System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			// Select Browser for Execution
			if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\executables\\chromedriver.exe");
				log.debug("Chrome Launched");
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver = new ChromeDriver(options);

			} else if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox Launched");
			}			

			driver.get(config.getProperty("testSiteUrl"));
			log.debug("Navigated to: " + config.getProperty("testSiteUrl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			menu = new TopMenu(driver);
		}
	}
	
	public boolean isElementPresent(String locator) {

		try {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator)));
			}
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		log.debug("Clicking on Element: " + locator);
		test.log(LogStatus.INFO, "Clicking on " + locator);
	}

	public static void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		log.debug("Typing in Element: " + locator + " with value as: " + value);
		test.log(LogStatus.INFO, "Typing in " + locator + " with value as " + "\"" + value + "\"");
	}
	
	static WebElement dropdown; 
	
	public void select(String locator, String value) {
		
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		log.debug("Selecting Element: " + locator + " with value as: " + value);
		test.log(LogStatus.INFO, "Selected dropdown " + locator + " with value as " + "\"" + value + "\"");
	}
	
	public static void verifyEquals(String expected, String actual) throws IOException {
		
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			Utilities.captureScreenshot();
			Reporter.log("<br>" + "Verification failure: " + e.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+">"
					+ "<img src="+Utilities.screenshotName+" height=100 width=100></img></a>");
			Reporter.log("<br>");
			
	    	test.log(LogStatus.FAIL, "Verification Failed with exception: "+ e.getMessage());
	    	test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		}
	}
	
	public static void quit() {
		driver.quit();
	}

}
