package com.dell.emc.imap.page;

// ***********************************************************************
// Author           : pichos1
// Created          : 12/10/2018 12.33.27 PM
//
// Last Modified By : pichos1
// Last Modified On : 12/10/2018 12.33.27 PM
// ***********************************************************************
// <copyright file="CustomerCloneRequestPage" company="Dell Technologies">
//     Copyright (c) Dell 2017. All rights reserved.
// </copyright>
// <summary>Describe Page Details</summary>
// ***********************************************************************


//import com.dell.devote.core.*;
//import com.dell.emc.IMAPortal.Pages.IDD_Pages.IDD_Home_Page;
import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;
import com.dell.emc.imap.utils.PageBase;
import com.dell.emc.imap.utils.generichelper.JavaScriptHelper;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

/**
 * This base class is where all specific page classes will be derived.
 */
public class CustomerCloneRequestPage extends PageBase {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(IDD_Home_Page.class);
	WaitHelper waitHelper;

	public CustomerCloneRequestPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//iframe[@name='tasks']")
	public WebElement iframe_tasks;

	@FindBy(xpath = "//span[contains(text(),'Customer clone Request')]")
	public WebElement customer_clone_Request1;

	public boolean verifyCustomerCloneRequestPage() throws Exception{
		try {
			BaseUtilityHelper.wait3Seconds();
			if (customer_clone_Request1.isDisplayed()) {
				log.info("User should able to see the Customer clone Request Page");
				return true;
			} else {
				log.info("User is unable to see the Customer clone Request Page");
				return false;
			}
		} catch (Exception e) {
			log.info("Exception:User is unable to see the Customer clone Request Page ");
			throw e;
		}
	}

	@FindBy(xpath = ".//*[@id='rtm']")
	public WebElement dropdown_RTM;
	
	@FindBy(xpath = ".//*[@id='rtm']/following-sibling::img")
	public WebElement click_drop;
	

	public CustomerCloneRequestPage selectRTM(String property) throws Exception{
		try {
			JavaScriptHelper jss=new JavaScriptHelper(driver);	
			jss.scrollIntoView(dropdown_RTM);
			this.click_drop.click();
			WebElement ele=driver.findElement(By.xpath("//div[contains(text(),'"+property+"')]"));
			ele.click();
			log.info("User selected the RTM Value as:"+property);
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("User not able to select the RTM Value as:"+property);
			throw e;
		}
	}
	
	@FindBy(xpath = ".//*[@id='reqPurpose']")
	public WebElement dropdown_RequestPurpose;
	
	@FindBy(xpath = ".//*[@id='reqPurpose']/following-sibling::img")
	public WebElement click_RequestPurpose;

	public CustomerCloneRequestPage selectRequestPurpose(String RequestPurpose) throws Exception{
		try {
			JavaScriptHelper jss=new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_RequestPurpose);
			clickOnSubmit();
			BaseUtilityHelper.wait3Seconds();
			cloneRequest_OK_Button.click();
			BaseUtilityHelper.wait3Seconds();
			this.click_RequestPurpose.click();
			BaseUtilityHelper.wait3Seconds();
			WebElement ele=driver.findElement(By.xpath("//div[contains(text(),'"+RequestPurpose+"')]"));
			BaseUtilityHelper.wait3Seconds();
			ele.click();
			log.info("User selected the Request purpose as:"+RequestPurpose);
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("User not able to select the Request purpose as:"+RequestPurpose);
			throw e;
		}
	}
	
	@FindBy(xpath = ".//*[@id='plsSpecify']")
	public WebElement input_pleaseSpecify;

	public CustomerCloneRequestPage enterValueIntoPleaseSpecify(String specify) throws Exception{
		try {
			this.input_pleaseSpecify.sendKeys(specify);
			log.info("User is entered the value into the Please Specity field successfully.");
			return new CustomerCloneRequestPage(driver);
		} catch (Exception e) {
			log.info("User is not able to entered the value into the Please Specity field.");
			throw e;
		}
	}
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement cloneRequest_OK_Button;

	public CustomerCloneRequestPage clickOnOK() throws Exception{
		try {
			if(this.cloneRequest_OK_Button.isDisplayed()) {
				this.cloneRequest_OK_Button.click();
				return new CustomerCloneRequestPage(driver);
			}
			else
				return new CustomerCloneRequestPage(driver);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement button_submit;

	@FindBy(xpath = "//table[@id='submitChangeCustomerBtnOnWindow']")
	public WebElement btnSubmit;
	
	public CustomerCloneRequestPage clickOnSubmit() throws Exception{
		try {
			if(this.button_submit.isDisplayed())
			{
			this.button_submit.click();
			log.info("User clicked on the Submit button successfully.");
//			clickOnSubmitAnyway();
			}
			else
			{
				this.btnSubmit.click();
				log.info("User did not clicked on the Submit button.");
			}
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			throw e;
		}
	}
	
	@FindBy(xpath = "//button[text()='Submit Anyway']")
	public WebElement button_submit_anyway;
	
	public void clickOnSubmitAnyway() throws Exception{
		try {
			BaseUtilityHelper.wait3Seconds();
			if(button_submit_anyway != null) {				
				if (button_submit_anyway.isDisplayed()) {
					button_submit_anyway.click();
					//return true;
				} 
			}
			else {
				log.info("User is unable to see the Submit Anyway button ");
				//return false;
			}
		} catch (Exception e) {
			log.info("Exception:User is unable to Click Submit anyway button in Warnign popup window ");
			throw e;
		}
	}
	
	@FindBy(xpath = "//input[@name='suite']")
	public WebElement input_suit;

	public CustomerCloneRequestPage enterSuiteValue(String suite) throws Exception{
		try {
			this.input_suit.clear();
			this.input_suit.sendKeys(suite);
			log.info("User entered the suite value as:" +suite);
			return new CustomerCloneRequestPage(driver);
		} catch (Exception e) {
			log.info("Exception:User is unable to enter the suite value");
			throw e;
		}
	}

	@FindBy(xpath = "//span[contains(@class,'ext-mb-text') and contains(text(),'Request has been submitted successfully')]")
	public WebElement createRequest_Successful_msg;

	
	public String getRequestId() throws Exception{
		try {
			//BaseUtilityHelper.WaitForElementPresent(createRequest_Successful_msg, 200);
			String strRequestSuccessMsg=createRequest_Successful_msg.getText();
			int strindex = strRequestSuccessMsg.indexOf("ID");
			String strRequestID=strRequestSuccessMsg.substring(strindex+2);
			System.out.println(strRequestID);
			log.info("Request was successfully created and Request ID:-" + strRequestID);
			return strRequestID;
		} catch (Exception e) {
			log.info("Exception:Request ID is not created:-" + e.getStackTrace());
			throw e;
		}
	}
	@FindBy(xpath = ".//*[@id='reqPurpose']")
	public WebElement dropdown_Internal;
	
	public CustomerCloneRequestPage selectInternalCustomerType(String internalType) throws Exception{
		try {
			
			JavaScriptHelper jss=new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_Internal);
			this.dropdown_Internal.click();
			WebElement ele=driver.findElement(By.xpath("//div[contains(text(),'"+internalType+"')]"));
			BaseUtilityHelper.wait3Seconds();
			ele.click();
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("Exception:User is unable to select the Request purpose");
			throw e;
		}
	}
	
	// Ravindra: Created below code to handle 'InternalCustomerType' on Customer Clone Request Page
	@FindBy(xpath = ".//*[ @id='int_cust']")
	public WebElement Internal_Cust_Type;
	
	public CustomerCloneRequestPage InternalCustomerType() throws Exception{		
		try{
			String IntCustType =  (String)((JavascriptExecutor) driver).executeScript("return arguments[0].value;",driver.findElement(By.id("int_cust")));
			System.out.println("Internal Customer Type is: "+IntCustType);
			log.info("Internal Customer Type is: "+IntCustType);
			log.info("Intenal Customer Type "+IntCustType+" is displaying.");
			return new CustomerCloneRequestPage(driver);	
		}
		catch(Exception e) {
			log.info("Exception:Intenal Customer Type External is not displaying");
			throw e;
		}			
	}
	
	// Ravindra: Created below code to handle 'Internal Customer' (Yes/No) on Customer Clone Request Page
	@FindBy(xpath = ".//*[ @id='internal_customer1']")
	public WebElement Internal_Cust_Status;
	
	public CustomerCloneRequestPage verifyInternalCustomerStatus() throws Exception{		
		try{
			String IntCustStatus =  (String)((JavascriptExecutor) driver).executeScript("return arguments[0].value;",driver.findElement(By.id("internal_customer1")));
			System.out.println("Internal Customer value is: "+IntCustStatus);
			log.info("Internal Customer value is: "+IntCustStatus);
			return new CustomerCloneRequestPage(driver);	
		}
		catch(Exception e) {
			log.info("Exception:Selected Customer is not Intenal Customer");
			throw e;
		}			
	}
	
	// Ravindra: Created below code to enter data in UCID field for Internal Customer
	@FindBy(xpath = "//input[@id='ucid']")
	public WebElement Internal_Ucid;
	
	public CustomerCloneRequestPage enterUCIDForInternalCustomer(String value) throws Exception{
		try {			
			JavaScriptHelper jss=new JavaScriptHelper(driver);
			jss.scrollIntoView(Internal_Ucid);
			BaseUtilityHelper.wait3Seconds();
			button_submit.click();
			log.info("User should click on Submit button without entering the UCID value.");
			BaseUtilityHelper.wait3Seconds();
			WebElement error_msg = driver.findElement(By.xpath("//span[contains(text(),'For inter-company/plant/related customers, Please enter the UCID')]"));
//			error_msg.click();
			String get_error_msg = error_msg.getText();
			
			log.info("Error message popup is displayed as expected: "+get_error_msg);			
			log.info("Error message popup should be displayed:"+get_error_msg);
			
			WebElement error_ok = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
			error_ok.click();
			
			log.info("Click on OK button on error message popup window.");			
			log.info("Click on OK button on error message popup window.");
			
			Internal_Ucid.sendKeys(value);
			
			log.info("User entered the UCID value: "+value);			
			log.info("User should entered the UCID value:"+value);
			
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("Exception:User is unable to enter the UCID value");
			throw e;
		}		
	}	


}
