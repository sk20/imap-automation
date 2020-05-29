package com.dell.emc.imap.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class BrowserManager {
	public String browserName;
	public String desiredCapabilitiesType;
	protected ReadConfig rc;
	public String reportLocation;
	public String projectLocation;
	public static WebDriver driver;
	public ArrayList<ITestResult> testResult = new ArrayList<ITestResult>();
	ArrayList<String> result = new ArrayList<String>();
	
	public void testBaseBeforeTest() throws Exception {
		this.browserName = System.getProperty("browser");
		this.desiredCapabilitiesType = BrowserFactory.getDesCapType();
		this.rc = new ReadConfig();
		String launchPadValues = System.getProperty("launchPadArgs");
		this.reportLocation = this.getReportLocation(launchPadValues, this.rc);
		if (this.browserName == null) {
			this.browserName = BrowserFactory.getBrowserName(this.projectLocation);
		}
	}
	
	public String getReportLocation(String launchPadValues, ReadConfig rc) throws FileNotFoundException, IOException {
		if (launchPadValues == null) {
			this.reportLocation = rc.getKeyValues("ReportLocation");
		} else {
			this.reportLocation = launchPadValues.split(";")[0].split("#")[1];
		}

		return this.reportLocation;
	}
	
	public WebDriver testBaseBeforeMethod() throws Exception {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		Runtime.getRuntime().exec("taskkill /F /IM Chrome.exe");
		Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
		driver = BrowserFactory.tryBrowser(this.browserName);
		return driver;
	}
	
	public void testBaseAfterMethod(ITestResult res) throws Exception {
		this.testResult.add(res);
		BrowserFactory.closeBrowser(driver);
		BrowserFactory.closeAllDrivers();
	}
	
	public void testBaseAfterTest() throws Throwable {
		Iterator<ITestResult> itr = this.testResult.iterator();
		while (itr.hasNext()) {
			ITestResult res = (ITestResult) itr.next();
			if (res.getStatus() == 1) {
				this.result.add("PASS");
			} else if (res.getStatus() == 2) {
				this.result.add("FAIL");
			} else if (res.getStatus() == 3) {
				this.result.add("SKIP");
			}
		}

		this.rc = new ReadConfig();
//		this.rc.updateExecutionStatus(this.result, this.reportLocation, this.browserName);
	}
}
