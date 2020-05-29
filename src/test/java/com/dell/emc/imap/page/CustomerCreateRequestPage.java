/**
 * 
 */
package com.dell.emc.imap.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;
import com.dell.emc.imap.utils.generichelper.JavaScriptHelper;
import com.dell.emc.imap.utils.PageBase;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;

/**
 * @author Ravindra_reddy_Gopal
 *
 */
public class CustomerCreateRequestPage extends PageBase {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(IDD_Home_Page.class);
	WaitHelper waitHelper;

	public CustomerCreateRequestPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Customer Create Request')]")
	public WebElement customer_create_Request;

	public boolean verifyCustomerCloneRequestPage() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			if (customer_create_Request.isDisplayed()) {
				log.info("User should able to see the Customer create Request Page");
				return true;
			} else {
				log.info("User is unable to see the Customer create Request Page");
				return false;
			}
		} catch (Exception e) {
			log.info("Exception:User is unable to see the Customer create Request Page ");
			throw e;
		}
	}

	@FindBy(xpath = ".//*[@id='county']")
	public WebElement input_county;

	public void setCounty(String county) throws Exception {
		log.info("Entering County as:" + county);

		this.input_county.clear();
		this.input_county.sendKeys(county);
		// this.country.sendKeys(Keys.ARROW_DOWN);
		this.input_county.sendKeys(Keys.ENTER);
		log.info("County should be entered as " + county + "");
	}

	@FindBy(xpath = ".//*[@name='isUSFederal']/following-sibling::label[text()='Yes']")
	public WebElement USFederalGovtYes;

	public void clickUSFederalGovtYes(String USFederalGovtYes) throws Exception {
		log.info("Clicked on US Federal Government as:" + USFederalGovtYes);

		this.USFederalGovtYes.click();
		log.info("User should click on US Federal Government as: " + USFederalGovtYes + "");
	}

	@FindBy(xpath = ".//*[@name='isUSFederal']/following-sibling::label[text()='No']")
	public WebElement USFederalGovtNo;

	public void clickUSFederalGovtNo(String USFederalGovtNo) throws Exception {
		log.info("Clicked on US Federal Government as:" + USFederalGovtNo);

		this.USFederalGovtNo.click();
		log.info("User should click on US Federal Government as: " + USFederalGovtNo + "");
	}

	@FindBy(xpath = ".//*[@id='usFederalDepartment']")
	public WebElement dropdown_Department;

	@FindBy(xpath = ".//*[@id='usFederalDepartment']/following-sibling::img")
	public WebElement click_Department;

	public CustomerCreateRequestPage selectDepartmet(String Department) throws Exception {
		try {

			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_Department);
			this.click_Department.click();
			BaseUtilityHelper.wait3Seconds();
			WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + Department + "')]"));
			BaseUtilityHelper.wait3Seconds();
			ele.click();
			log.info("User should able to select Department as:" + Department);
			return new CustomerCreateRequestPage(driver);

		} catch (Exception e) {
			log.info("User not able to select the Department as:" + Department);
			throw e;
		}
	}

	@FindBy(xpath = ".//*[@id='reqPurpose']")
	public WebElement dropdown_RequestPurpose;

	@FindBy(xpath = ".//*[@id='reqPurpose']/following-sibling::img")
	public WebElement click_RequestPurpose;

	public CustomerCreateRequestPage selectRequestPurpose(String RequestPurpose) throws Exception {
		try {

			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_RequestPurpose);
			this.click_RequestPurpose.click();
			BaseUtilityHelper.wait3Seconds();
			WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + RequestPurpose + "')]"));
			BaseUtilityHelper.wait3Seconds();
			ele.click();
			log.info("User selected the Request purpose as:" + RequestPurpose);
			return new CustomerCreateRequestPage(driver);

		} catch (Exception e) {
			log.info("User not able to select the Request purpose as:" + RequestPurpose);
			throw e;
		}
	}

	@FindBy(xpath = ".//button[contains(text(),'Submit')]")
	public WebElement customer_createRequest_Submit_Button;

	public void customer_CreateRequest_Submit_Button() throws Exception {
		this.customer_createRequest_Submit_Button.click();
		log.info("Clicked on Create Request_Submit Button.." + customer_createRequest_Submit_Button);
	}

	// Ravindra: Created below method to handle RequestPurpose Field level
	// validations on "Customer Create Request" pop-up Window
	public CustomerCreateRequestPage checkingFieldValidationsRequestPurpose() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavaScriptHelper jss = new JavaScriptHelper(driver);
		jss.scrollIntoView(dropdown_RequestPurpose);
		BaseUtilityHelper.wait3Seconds();
		WebElement btnSubmit = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
		btnSubmit.click();
		BaseUtilityHelper.wait5Seconds();
		WebElement error_ok_element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		error_ok_element.click();
		log.info("Error pop-up message window is displayed. User clicked on OK button.");
		BaseUtilityHelper.wait3Seconds();
		return new CustomerCreateRequestPage(driver);
	}

	@FindBy(xpath = ".//*[@id='regionCreate']")
	public WebElement dropdown_regisonstate;

	@FindBy(xpath = ".//*[@id='regionCreate']/following-sibling::img")
	public WebElement click_regisonstate;

	public CustomerCloneRequestPage setRegion_State(String regionst) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_regisonstate);
			BaseUtilityHelper.wait3Seconds();
			this.click_regisonstate.click();
			BaseUtilityHelper.wait3Seconds();
			WebElement ele = driver.findElement(By.xpath("(//div[contains(text(),'" + regionst + "')])[3]"));
			BaseUtilityHelper.wait3Seconds();
			ele.click();
			log.info("User selected the Region/State as::" + regionst);
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("User not able to select the Region/State as:" + regionst);
			throw e;
		}
	}

}