package com.dell.emc.imap.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	static WebDriver driver;
	static ReadConfig rc;
	static String DesiredCapabilitiesType;
	static String browserName = "Firefox";
	static int maxRetry = 3;
	
	private static Properties loadConfig() throws FileNotFoundException, IOException {
		Properties config = new Properties();
		config.load(new FileInputStream("Configuration/Config.properties"));
		return config;
	}
	
	public static String getDesCapType() throws FileNotFoundException, IOException {
		Properties prop = loadConfig();
		String DesCapType = System.getProperty("desCaps");
		if (DesCapType != null) {
			DesiredCapabilitiesType = DesCapType.split(";")[0].split("#")[1];
		} else {
			DesiredCapabilitiesType = prop.getProperty("DesiredCapabilityType");
		}
		return DesiredCapabilitiesType;
	}
	
	public static String getDesCaps() throws FileNotFoundException, IOException {
		Properties prop = loadConfig();
		System.out.println(prop);
		String desCaps = System.getProperty("desCaps");
		String caps;
		if (desCaps != null) {
			caps = desCaps.split(";")[1].split("#")[1];
		} else {
			caps = rc.getKeyValues("caps");
		}
		return caps;
	}
	
	public static HashMap<String, Object> getOpts(String cap) {
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		System.out.println(cap);
		String b = cap.substring(1, cap.length() - 1);
		int size = b.split(",").length;
		String[] caps = new String[size];
		caps = b.split(",");
		for (int i = 0; i < caps.length; ++i) {
			String s = caps[i];
			System.out.println(s.split("=")[0] + "  :   " + s.split("=")[1]);
			prefs.put(s.split("=")[0], s.split("=")[1]);
		}
		return prefs;
	}
	
	public static String getBrowserName(String configFilePath) throws FileNotFoundException, IOException {
		Properties prop = loadConfig();
		String browser = prop.getProperty("Browser");
		if (browser != null) {
			browserName = browser;
		}
		System.out.println("Selected Browser : " + browserName);
		return browserName;
	}
	
	public static WebDriver tryBrowser(String browserName) throws Exception {
		for (int i = 1; i <= maxRetry; ++i) {
			driver = startBrowser(browserName);
			if (driver != null) {
				try {
					driver.manage().window().maximize();
					break;
				} catch (Exception ex) {
					System.out.println(
							"Exception: " + ex + " \nException while maximizing the browser. Current try Count: " + i
									+ ". Maximum retry count: " + maxRetry);
				}
			} else {
				System.out.println("Driver returned NULL while opening browser. Current try Count: " + i
						+ ". Maximum retry count: " + maxRetry);
			}
		}
		return driver;
	}
	
	public static WebDriver startBrowser(String browserName) throws Exception {
		rc = new ReadConfig();
		getDesCapType();
		System.out.println("Browser Name : " + browserName);
		if (driver != null) {
			System.out.println("Sleeping for 5 sec before next iteration...");
			Thread.sleep(5000L);
		}

		if (!browserName.toLowerCase().contains("firefox") && !browserName.toLowerCase().contains("ff")
				&& !browserName.toLowerCase().contains("mozilla firefox")) {
			if (!browserName.toLowerCase().contains("chrome") && !browserName.toLowerCase().contains("google chrome")) {
				if (!browserName.toLowerCase().contains("ie") && !browserName.toLowerCase().contains("InternetExplorer")
						&& !browserName.toLowerCase().contains("microsoft ie")
						&& !browserName.toLowerCase().contains("microsoft internetexplorer")
						&& !browserName.toLowerCase().contains("microsoft internet explorer")
						&& !browserName.toLowerCase().contains("internet explorer")) {
					if (!browserName.toLowerCase().contains("phantomjs") && !browserName.toLowerCase().contains("ghost")
							&& !browserName.toLowerCase().contains("phantom js")) {
						if (browserName.toLowerCase().contains("edge")
								|| browserName.toLowerCase().contains("Microsoft Edge")) {
							System.setProperty("webdriver.edge.driver",
									"C:\\Program Files (x86)\\Dell Technologies\\Titanium\\Driver\\MicrosoftWebDriver.exe");
							driver = new EdgeDriver();
						}
					} 
//					else {
//						File file = new File(
//								"C:\\Program Files (x86)\\Dell Technologies\\Titanium\\Driver\\phantomjs.exe");
//						System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
//						driver = new PhantomJSDriver();
//					}
				} else {
					driver = openIEBrowser(DesiredCapabilitiesType);
				}
			} else {
				System.out.println("in start browser");
				driver = openChromeBrowser(DesiredCapabilitiesType);
			}
		} else {
			System.setProperty("webdriver.firefox.marionette",
					"C:\\Program Files (x86)\\Dell Technologies\\Titanium\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		System.out.println("driver :::: " + driver);
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	public static WebDriver openIEBrowser(String cap) throws Exception {
		String var1;
		switch ((var1 = cap.toLowerCase()).hashCode()) {
			case -1249474914 :
				if (var1.equals("options")) {
					driver = new InternetExplorerDriver(setIECapabilities(getDesCaps()));
					break;
				}
			default :
				System.out.println("**********DESIRED CAPABILITIES NOT SET*******");
				driver = new InternetExplorerDriver();
		}
		return driver;
	}
	
	public static DesiredCapabilities setIECapabilities(String cap) {
		DesiredCapabilities desiredCaps = DesiredCapabilities.internetExplorer();
		System.out.println(cap);
		String b = cap.substring(1, cap.length() - 1);
		System.out.println(b);
		int size = b.split(";").length;
		String[] caps = new String[size];
		caps = b.split(";");

		for (int i = 0; i < caps.length; ++i) {
			String s = caps[i];
			System.out.println(s.split("=")[0] + "  :   " + s.split("=")[1]);
			desiredCaps.setCapability(s.split("=")[0], s.split("=")[1]);
		}
		return desiredCaps;
	}
	
	@SuppressWarnings("deprecation")
	public static WebDriver openChromeBrowser(String cap) throws Exception {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		String var2;
		switch ((var2 = cap.toLowerCase()).hashCode()) {
			case -1249474914 :
				if (var2.equals("options")) {
					ChromeOptions opts = new ChromeOptions();
					opts.setExperimentalOption("prefs", getOpts(getDesCaps()));
					caps.setCapability("acceptSslCerts", true);
					caps.setCapability("chromeOptions", opts);
					driver = new ChromeDriver(caps);
					break;
				}
			default :
				System.out.println("**********DESIRED CAPABILITIES NOT SET For Chrome*******");
				driver = new ChromeDriver();
		}

		return driver;
	}
	
	public static void closeBrowser(WebDriver driver) throws IOException {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

	public static void closeAllDrivers() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		Runtime.getRuntime().exec("taskkill /F /IM Chrome.exe");
		Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
	}
}
