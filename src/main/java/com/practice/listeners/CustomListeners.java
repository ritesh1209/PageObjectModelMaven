package com.practice.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.practice.base.Page;
import com.practice.utilities.MonitoringMail;
import com.practice.utilities.TestConfig;
import com.practice.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;


public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	public String messageBody;
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.captureScreenshot();
			Reporter.log("Capturing Screenshot");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">" + "<img src="
					+ Utilities.screenshotName + " height=100 width=100></img></a>");
		} catch (IOException e) {

			e.printStackTrace();
		}

		test.log(LogStatus.FAIL, arg0.getName() + " Failed with exception: " + arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		rep.endTest(test);
		rep.flush();

	}

	public void onTestSkipped(ITestResult arg0) {

		test.log(LogStatus.SKIP, "Skipped the Test " + arg0.getName() + " as the run mode is set to N");
		rep.endTest(test);
		rep.flush();

	}

	public void onTestStart(ITestResult arg0) {

		test = rep.startTest(arg0.getName().toUpperCase());
		if (!Utilities.isTestRunnable(arg0.getName(), excel)) {
			throw new SkipException("Skipping the Test " + arg0.getName() + " as the run mode is set to N");
		}
	}

	public void onTestSuccess(ITestResult arg0) {

		test.log(LogStatus.PASS, arg0.getName() + " PASS");
		rep.endTest(test);
		rep.flush();

	}

	public void onStart(ISuite arg0) {

	}

	public void onFinish(ISuite arg0) {

		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/PageObjectModelBasics/Extent_20Report/";
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
