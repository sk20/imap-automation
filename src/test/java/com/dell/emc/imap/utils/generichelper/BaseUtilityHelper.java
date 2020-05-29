package com.dell.emc.imap.utils.generichelper;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.dell.emc.imap.utils.BrowserManager;

public class BaseUtilityHelper extends BrowserManager {

	private static WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(BaseUtilityHelper.class);


	public BaseUtilityHelper(WebDriver driver){
		//super(driver);
		this.driver = driver;
		log.info("BaseUtilityHelper object created..");
	}

	public static void enterInputValues(String field,String value) throws Exception{
		WebDriverWait wait=new WebDriverWait(driver, 120);
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+field+"']/following::input[1]")));
		//WebElement usearname = driver.findElement(By.xpath("//*[text()='Username:']/following::input[1]"));
		JavaScriptHelper jss=new JavaScriptHelper(driver);	
		jss.scrollIntoView(element);
		element.clear();
		element.sendKeys(value);
		log.info("Value selected for "+field+" element is:-"+value);
		//HTMLReport.updateDetailsHTMLReport("Value for the field "+field+" should be selected","Value for the field "+field+" is selected successfully and having value "+value); 
	}

	public static void clickbutton(String elem) throws Exception{
		WebDriverWait wait=new WebDriverWait(driver, 120);
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='submitChangeCustomerBtnOnWindow''"+elem+"']")));
		element.click();
		log.info("Button "+elem+" is successfully clicked");//
		//HTMLReport.updateDetailsHTMLReport("Button "+elem+" should be clicked","Button "+elem+" is clicked successfully");
	}

	public static void clickElement(String attribute,String value) throws Exception{		
			WebDriverWait wait=new WebDriverWait(driver, 240);
			WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"+attribute+"[text()='"+value+"']")));
			element.click();
			log.info("Web Element "+value+" is successfully clicked");
			//HTMLReport.updateDetailsHTMLReport(""+value+" should be clicked",""+value+" is clicked successfully");		
	}
	
	//Ravindra: "Customer Modify" pop-up Window: Clicking on Submit button
	public static void clickButtonOnCustModifyWindow(String attribute,String value) throws Exception{		
		WebDriverWait wait=new WebDriverWait(driver, 240);
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"+attribute+"[text()='"+value+"']/ancestor::table[@id='submitChangeCustomerBtnOnWindow']")));
		element.click();
		log.info("Web Element "+value+" is successfully clicked");
	}	
	
	//Ravindra: Created below method to handle Field Level validations on "Customer Modify" pop-up Window
	public static void verifyingFieldLevelValidations(String attribute,String value) throws Exception{		
		WebDriverWait wait=new WebDriverWait(driver, 120);
		
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"+attribute+"[@id='"+value+"']")));
		String get_element_val = element.getAttribute("value");
		System.out.println("Web Element "+value+" Value is:"+get_element_val);
		element.clear();
		BaseUtilityHelper.wait3Seconds();	
		WebElement  btnSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']/ancestor::table[@id='submitChangeCustomerBtnOnWindow']")));
		btnSubmit.click();
		WebElement  error_msg_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Please correct your data before modifying the record']")));
		String error_msg = error_msg_element.getText();
		log.info("Error Message: "+error_msg);
		BaseUtilityHelper.wait5Seconds();		
		WebElement  error_ok_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		error_ok_element.click();
		log.info("Button 'OK' clicked on message pop-up window");
		element.sendKeys(get_element_val);
		BaseUtilityHelper.wait3Seconds();	
		log.info("Web Element "+value+" is validated successfully.");
	}
	
	//Ravindra: Created below method to handle Field level validations on "Customer Create Request" pop-up Window
		public static void checkingFieldValidationsCustomerCreateRequest(String attribute,String value) throws Exception{		
			WebDriverWait wait=new WebDriverWait(driver, 120);
			WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"+attribute+"[@id='"+value+"']")));
			String get_element_val = element.getAttribute("value");
			element.clear();
			BaseUtilityHelper.wait3Seconds();	
			WebElement  btnSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
			btnSubmit.click();
			BaseUtilityHelper.wait5Seconds();		
			WebElement  error_ok_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
			error_ok_element.click();
			log.info("Error pop-up message window is displayed. User clicked on OK button.");
			element.sendKeys(get_element_val);
			BaseUtilityHelper.wait3Seconds();	
			log.info("User entered correct data into filed:"+value);
		}
	
	//Ravindra: Focusing Hidden WebElement and performing Click operation -- String attribute,String value
	public static void clickHiddenElement(String attribute,String value) throws Exception{
		System.out.println("Trying to click Assing button");
		WebDriverWait wait=new WebDriverWait(driver, 240);		
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"+attribute+"[text()='"+value+"']")));
		System.out.println("Assing Button:"+element);
		//WebElement  element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//"+attribute+"[text()='"+value+"']")));	
		//WebElement element = driver.findElement(By.xpath("//td[text()='Assign']"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		//js.executeScript("arguments[0].click();", element);
		//js.executeScript("document.getElementByXPath('//td[text()='Assign']').click();");
		
		js.executeScript("document.getElement(By.xpath('//td[text()='Assign']/ancestor::div[@class='sipButCommon globalButton']/table').click();");
		
		/*Actions build = new Actions(driver); 		
	    build.moveToElement(element).sendKeys(Keys.ENTER).build().perform();*/
				
		//log.info("Web Element "+value+" is successfully clicked");
		System.out.println("Clicked on Assing button");
		//HTMLReport.updateDetailsHTMLReport(""+value+" should be clicked",""+value+" is clicked successfully");
	}
	
	public static void enterInTextArea(String field,String value) throws Exception{
		System.out.println("Text Area");
		WebDriverWait wait=new WebDriverWait(driver, 120);
		WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+field+"')]/following::textarea[1]")));
		element.clear();
		element.sendKeys(value);
		log.info("Value entered for field: "+field+" is :"+value);
		//HTMLReport.updateDetailsHTMLReport("Value for the field "+field+" should be entered","Value for the field "+field+" is entered successfully and entered value is"+value);
	}
	
	public static void wait3Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 3000){

		}             
	} 

	public static void wait5Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 5000){

		}             
	}   

	public static void wait10Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 10000){

		}             
	}   
	
	public static void waitNSeconds(int n){
		long start = new Date().getTime();
		while(new Date().getTime() - start < n*1000){

		}             
	} 
	public static String captureScreen(String fileName, WebDriver driver) throws Exception {
//		String screenshotPath=System.getProperty("user.dir");
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		if(fileName==""){
			fileName = "Image";
		}
		File reportDirectory;
		reportDirectory = new File(ResourceHelper.getResourcePath("src/com/dell/emc/imapportal_testcase/Screenshots"));
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
			log.info("Screen Image should be captured"); 
		}
		catch(Exception e){
			log.info("Screen Image should be capture");
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public static boolean IsElementPresent(WebDriver driver, String locator, int timeoutInSeconds)
	{
		try
		{
			driver.findElement(By.xpath(locator));

			return true;
		}
		catch (Exception NoSuchElementException)
		{

			return false;
		}
	}	

	/**
	 * Function to Generate the Random String generation
	 * 
	 * @param length used to pass limit
	 * @return : Random string for value
	 * @throws Exception used to capture any fault
	 */
	public static String Random_StringGenerator(int length) throws Exception {
		String Random = "";
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < length; i++) {
			double a = Math.random();
			int x = (int) (a * 100);

			if (x > 25 && x < 52) {
				x = x - 26;
			} else if (x > 51 && x < 78) {
				x = x - 52;
			} else if (x > 77 && x < 99) {
				x = x - 78;
			} else {
				x = 1;
			}

			char letter = abc.charAt(x);
			Random = Random + String.valueOf(letter);
		}
		// System.out.println(Random);
		return Random;

	}

	/**
	 * Function to Generate the Random String generation
	 * 
	 * @param length used to set the limit
	 * @return : Random string for value
	 * @throws Exception to handle fault
	 */
	public static String Random_StringGeneratorLowerCase(int length) throws Exception {
		String Random = "";
		String abc = "abcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < length; i++) {
			double a = Math.random();
			int x = (int) (a * 100);

			if (x > 25 && x < 52) {
				x = x - 26;
			} else if (x > 51 && x < 78) {
				x = x - 52;
			} else if (x > 77 && x < 99) {
				x = x - 78;
			} else {
				x = 1;
			}

			char letter = abc.charAt(x);
			Random = Random + String.valueOf(letter);
		}
		// System.out.println(Random);
		return Random;

	}
	
	/**
	 * Method to generate Random number
	 * 
	 * @param length used to pass limit
	 * @return : Random string for value
	 * @throws Exception used to capture any fault
	 */
	public static String Random_NumberGenerator(int length) throws Exception {
		double number, value;
		int len;
		number = Math.random();

		StringBuffer s = new StringBuffer(length);
		s.append("1");
		for (int k = 0; k < length; k++) {
			s.append("0");
		}

		value = Double.parseDouble(s.toString());

		number = number * value;
		int random = (int) (number);
		String randomno = Integer.toString(random);
		len = randomno.length();

		if (len < length) {
			int diff = length - len;
			StringBuffer s1 = new StringBuffer(randomno);
			for (int k = 0; k < diff; k++) {
				s1.append("0");
			}
			randomno = s1.toString();
		}

		// System.out.println(randomno);
		return randomno;

	}

	public static boolean IsElementPresent(WebElement custnameN) throws Exception {
		try {
			custnameN.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}
	}
	
	
	public static boolean IsElementPresent1(WebElement Ucid) throws Exception {
		try {
			Ucid.isDisplayed();
			log.info("User should not be able to view the ucid field");
			return true;
		} catch (NoSuchElementException e) {
			log.info("User should not be able to view the ucid field");
			return false;

		}
	}
	
	
	
	//subbarayudu
	public static void WaitForObjectToBeClickable(WebElement element, int time) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	//subbarayudu
	public static void WaitForObjectToBePresent(WebElement element, int time) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	//subbarayudu
	public static void WaitForElementPresent(WebElement element, int time) throws Exception {
		WebDriverWait newWait = new WebDriverWait(driver, time);
		newWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void wait1Min(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 100000){
		}	
	}
	/** 
	 * @param message when report failed
	 */
	public static void reportFail(String message) {
		System.out.println("Fail: " + message);
		Reporter.log("Fail: " + message);
	}
	
	public static void wait20Seconds(){
		long start = new Date().getTime();
		while(new Date().getTime() - start < 20000){
		}		
	}  

	public static void waitTime(int n){
		long start = new Date().getTime();
		int x = n*1000;
		while(new Date().getTime() - start < x){
		}		
	}
	
	/**
	 * Click on object
	 * 
	 * @author pichos1
	 * @param objname is used to pass element value
	 * @throws Exception to hanld the fault
	 */
	public static void Click(WebElement objname) throws Exception {
		try {
			WaitForObjectToBeClickable(objname, 60);
			WaitForElementPresent(objname, 60);
			if (objname.isDisplayed())
				objname.click();
			// reportPass("Click on the object" + objname);

		} catch (Exception e) {
			System.out.println("element '" + objname + "' is not displayed");
			log.info("Unable to Click on the" + objname);
			reportFail("Unable to click on the object" + objname);
			Assert.fail("element '" + objname + "' is not displayed");

			throw e;// Better not no handle the exception still in specific
					// scenario if you are handling the exception then just
					// throw like throw e;
		}
	}	
	public static String getText(WebElement ele) {
		String objText = ""; // Unnecessary variable declaration. it can be like
		// return ele.getText();
		objText = ele.getText();
		return objText;
	}
	@FindBy (xpath ="//*[contains(text(),'Loading...')]")
	public static WebElement searchresultloading;
	
	//Manjunath: Created below method for identifying and clicking webelement.
	public static WebElement find_WebElement(String attribute,String value) throws Exception{		
		try {
			WebDriverWait wait=new WebDriverWait(driver, 30);
			WebElement  element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//"+attribute+"[text()='"+value+"']")));					
			return element;
		}
		catch (Exception e) {
			e.getStackTrace();
			throw e;
		}

	}
	public static void click_element_byID(String nameID,String value) throws Exception{		
		try {
		driver.findElement(By.id(nameID)).sendKeys(value);
		}
		catch (Exception e) {
			e.getStackTrace();
			throw e;
		}

	}
}		
	