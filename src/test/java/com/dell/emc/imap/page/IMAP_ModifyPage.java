package com.dell.emc.imap.page;

// ***********************************************************************

// Author           : rs15
// Created          : 05/10/2018 12.24.54 PM
//
// Last Modified By : rs15
// Last Modified On : 05/10/2018 12.24.54 PM
// ***********************************************************************
// <copyright file="IMAP_ModifyPage" company="Dell Technologies">
//     Copyright (c) Dell 2017. All rights reserved.
// </copyright>
// <summary>Describe Page Details</summary>
// ***********************************************************************

//import com.dell.devote.core.*;
import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;
import com.dell.emc.imap.utils.generichelper.JavaScriptHelper;
import com.dell.emc.imap.utils.PageBase;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * This base class is where all specific page classes will be derived.
 */
public class IMAP_ModifyPage extends PageBase {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(IMAP_ModifyPage.class);
	WaitHelper waitHelper;
	public WebElement companyPhone;
	/**
	 * Constructor to hand off WebDriver.
	 */
	public IMAP_ModifyPage(WebDriver driver) {
		super(driver);
		// Please set the values for the application name, uri and segment name
		// here.
		// super.name = "";
		// super.uri = "";
		// super.segmentName = "";
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement elem = getElement(Identifier.name, "name"); // getting locator
	// element using name
	// WebElement elem = getElement(Identifier.xpath, "xpath"); // getting
	// locator element using xpath
	// WebElement elem = getElement(Identifier.id, "id"); // getting locator
	// element using id

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]//span[contains(text(),'Customer Modify Request')]")
	public WebElement CustModifyDiv;

	@FindBy(xpath = "//input[@id='customer_name']")
	public WebElement CustName;

	@FindBy(xpath = "	.//*[@id='inActive']")
	public WebElement radio_inActive;

	@FindBy(xpath = "//input[@id='customer_name']/following-sibling::div[contains(@class,'x-form-invalid-icon')][contains(@style ,'visibility: visible')]")
	public WebElement CustNameError;

	@FindBy(xpath = "//input[@id='address_line_1']")
	public WebElement AddrLine1;

	@FindBy(xpath = "//input[@id='address_line_1']/following-sibling::div[contains(@class,'x-form-invalid-icon')][contains(@style ,'visibility: visible')]")
	public WebElement AddrLine1Error;

	@FindBy(xpath = "//input[@id='cityModify']")
	public WebElement City;

	@FindBy(xpath = "//input[@id='cityModify']/following-sibling::div[contains(@class,'x-form-invalid-icon')][contains(@style ,'visibility: visible')]")
	public WebElement CityError;

	@FindBy(xpath = "//input[@id='custCountry']")
	public WebElement Country;

	// table[@id='submitChangeCustomerBtn']
	@FindBy(xpath = "//table[@id='submitChangeCustomerBtn']")
	public WebElement submitBtn;

	@FindBy(xpath = "//table[@id='submitChangeCustomerBtnOnWindow']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//input[@id='rtm']")
	public WebElement RTMInput;

	@FindBy(xpath = "//input[@id='rtm']/following-sibling::img")
	public WebElement RTMDpdnImg;

	@FindBy(xpath = "(//*[contains(text(),'Change/Add New Parent:')]/../../../..)//following-sibling::div//*[contains(text(),'Search')]/..")
	public WebElement button_search;

	@FindBy(xpath = "(//*[contains(text(),'Comment:')]/..)//following-sibling::div//textarea")
	public WebElement field_comments;

	@FindBy(xpath = "(//*[contains(text(),'Parent Search')]/..)//div")
	public WebElement closeParentsearch_window;

	@FindBy(xpath = "//input[@id='assignToCSDistrict']")
	public WebElement assignCSDistChk;

	@FindBy(xpath = "//input[@id='amcsDistrict']")
	public WebElement AMCSDistInput;

	@FindBy(xpath = "//input[@id='cmcsDistrict']")
	public WebElement CMCSDistInput;

	@FindBy(xpath = "//*[contains(text(),'Customer Modify')]")
	public WebElement imap_modifypage;

	@FindBy(xpath = "//label[text()='Change/Add New Parent:']")
	public WebElement Chg_Add_Parent;
	
	@FindBy(xpath = "(//button[contains(text(),'Search')])[2]")
	public WebElement Search2;
	
	@FindBy(xpath = "(//div[contains(@class,'x-tool x-tool-close')])[3]")
	public WebElement Close_tab;
	
	@FindBy(xpath = "//label[text()='Phone:']")
	public WebElement Phonenum; 
	
	@FindBy(xpath = "//label[text()='Affinity ID:']")
	public WebElement Affinity_ID;
	
	@FindBy(xpath = "//label[text()='Comment:']")
	public WebElement comments_text;
	
	// div[starts-with(@id,'ext-comp')][contains(@style ,'visibility:
	// visible')][contains(@class,'x-window-dlg')]

	// div[contains(@class,'x-form-invalid-icon')]

	/**
	 * Treat this like a BVT of the page. If Validate does not pass, throw
	 * exception and write the message to the console from the test.
	 * 
	 */

	@FindBy(xpath = ".//*[@id='active']")
	public WebElement radio_btn_Active;

	public void clickOnActive() throws Exception {
		try {
			BaseUtilityHelper.WaitForObjectToBePresent(radio_btn_Active, 80);
			log.info("clicked on OK button.." + radio_btn_Active);
			this.radio_btn_Active.click();
			BaseUtilityHelper.wait3Seconds();
			log.info("user should click on Active button");
		} catch (Exception e) {
			log.info("Exception: Unable to clicked on the Active button");
			throw e;
		}
	}

	public void clickOnStatus_RadioButton(String status) throws Exception {
		try {
			WebElement radioValue = driver.findElement(By.xpath(".//*[@id='" + status + "']"));
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(radioValue);
			BaseUtilityHelper.WaitForObjectToBePresent(radioValue, 80);
			log.info("clicked on OK button.." + radioValue);
			radioValue.click();
			BaseUtilityHelper.wait3Seconds();
			log.info("user should click on " + status + " button");
		} catch (Exception e) {
			log.info("Exception: Unable to clicked on the Inactive button");
			throw e;
		}
	}

	public void verifyDisabledFields() throws Exception {
		try {
			BaseUtilityHelper.WaitForObjectToBePresent(radio_btn_Active, 80);
			String[] inputs = { "CS Name", "Address line 1", "Address line 2", "Address line 3", "Address line 4",
					"Building", "Floor", "Department", "Room", "Suite", "Mailstop", "Care of", "Miscellaneous",
					"Affinity ID", "PO Box", "City", "County", "Postal Code", "Phone", "Website", "Site D&B number",
					"GU DUNS", "GU parent", "Change/Add New", "Account Relationship", "Segmentation", "AM CS District:",
					"CM CS District:", "Website" };
			for (String input : inputs) {
				WebElement value = driver.findElement(By
						.xpath("//label[contains(text(),'" + input + "')]/following-sibling::div/input[@readonly='']"));
				JavaScriptHelper jss = new JavaScriptHelper(driver);
				jss.scrollIntoView(value);
				// in above xpath @readonly='' is locating disabled fields, So
				// if it displayed that input field is disabled
				if (value.isDisplayed()) {
					log.info(input + " field should be disabled ");
				} else {
					log.info("Exception: " + input + " field is enabled");
				}
				// below commented section is also used for this disabled
				// functionality
				/*
				 * if(value.isEnabled()){ log.info(
				 * input+" field should be disabled ",
				 * input+" field is in disabled"); }else{
				 * log.info("Exception: "
				 * +input+" field is enabled", input+" field should disabled",
				 * input+" field is ebabled"); }
				 */
			}
		} catch (Exception e) {
			log.info("Exception: Unable to clicked on the Active button");
			throw e;
		}
	}

	@FindBy(xpath = ".//*[@id='inactReason']")
	public WebElement dropdown_InactiveReason;
	@FindBy(xpath = ".//*[@id='inactReason']/following-sibling::img")
	public WebElement click_drop;

	// test comment
	public CustomerCloneRequestPage selectInactiveReason(String reason) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_InactiveReason);
			this.click_drop.click();
			WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + reason + "')]"));
			ele.click();
			log.info("User abel to select the Inactivation Reason");
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("Exception:User is unable to select inactivation reason ");
			throw e;
		}
	}

	@FindBy(xpath = "//label[contains(text(),'Inactivation Reason:')]/following-sibling::div/div/input")
	public WebElement field_InactiveReason;
	@FindBy(xpath = "//*[contains(text(),'Comment:')]/following::textarea[1]")
	public WebElement textArea_cmt;
	@FindBy(xpath = "//label[contains(text(),'Inactivation Reason:')]/following-sibling::div//div[2]")
	public WebElement alert_Required;

	/*
	 * This method checking for Inactivation Reason,Comment Sections are enabled
	 * or not and Inactivation Reason is required or not
	 */
	public void verifyEnabledFieldsForInactive() throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(field_InactiveReason);
			BaseUtilityHelper.WaitForObjectToBePresent(field_InactiveReason, 80);
			if (field_InactiveReason.isEnabled() && alert_Required.isEnabled()) {
				jss.scrollIntoView(textArea_cmt);
				BaseUtilityHelper.WaitForObjectToBePresent(textArea_cmt, 40);
				if (textArea_cmt.isEnabled()) {
					log.info("Inactivation reason, comments should enable and that inactivation reason should required");
				}
			} else {
				log.info("Inactivation reason, comments are disabled and that inactivation reason not showing");
			}
		} catch (Exception e) {
			log.info("Exception: Inactivation reason, comments are disabled and that inactivation reason not showing");
			throw e;
		}
	}

	@FindBy(xpath = "//label[contains(text(),'Request Purpose')]/following-sibling::div//input[1]")
	public WebElement field_requestPurpose;

	public void verifyEnabledFieldsForActive() throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			BaseUtilityHelper.WaitForObjectToBePresent(field_requestPurpose, 80);
			if (field_requestPurpose.isEnabled()) {
				jss.scrollIntoView(textArea_cmt);
				BaseUtilityHelper.WaitForObjectToBePresent(textArea_cmt, 40);
				if (textArea_cmt.isEnabled()) {
					log.info("Request Purpose, comments should enable and that Request purpose should required");
				}
			} else {
				log.info("Request Purpose, comments disabled and that Request purpose not showing");
			}
		} catch (Exception e) {
			log.info("Exception: Request Purpose, comments disabled and that Request purpose not showing");
			throw e;
		}
	}

	@FindBy(xpath = ".//*[@id='reqPurpose']")
	public WebElement dropdown_reqPurpose;
	@FindBy(xpath = ".//*[@id='reqPurpose']/following-sibling::img")
	public WebElement click_dropdown;

	// test comment
	public CustomerCloneRequestPage selectRequestPurpose(String purpose) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_reqPurpose);
			// jss.scrollIntoViewAndClick(dropdown_reqPurpose);
			this.click_dropdown.click();
			WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + purpose + "')]"));
			BaseUtilityHelper.wait10Seconds();
			ele.click();
			log.info("User abel to select the Request purpose");
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("Exception:User is unable to select Request purpose ");
			throw e;
		}
	}

	@FindBy(xpath = "//table[@class='x-btn emc-button-green emc-button x-btn-noicon x-item-disabled']//button[contains(text(),'Submit')]")
	public WebElement btn_sbmit;
	@FindBy(xpath = "//label[text()='Inactivation Reason:']//following-sibling::div//input[1]")
	public WebElement input_field;

	public boolean verifySubmitButtonDisabled(String label) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			WebElement input = driver
					.findElement(By.xpath("//label[text()='" + label + "']//following-sibling::div//input[1]"));
			jss.scrollIntoView(input_field);
			String input_value = input.getText();
			input_value.isEmpty();
			jss.scrollIntoView(btn_sbmit);
			if (btn_sbmit.isDisplayed()) {
				log.info("verify can not submit if " + label + " is not populated");
			} else {
				log.info("unable to verify can not submit if " + label + " is not populated");
			}
		} catch (Exception e) {
			log.info("Exception:unable to verify can not submit if " + label + " is not populated");
			throw e;
		}
		return true;
	}

	@FindBy(xpath = ".//*[@id='customer_name']")
	public WebElement input_CN;

	public boolean verifyDiscard(String name, String text) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			WebElement input = driver.findElement(By.xpath("//input[@name='" + name + "']"));
			jss.scrollIntoView(input);
			input.clear();// clear the input field
			input.sendKeys(text);
			jss.scrollIntoView(radio_inActive);
			radio_inActive.click();
			BaseUtilityHelper.wait3Seconds();
			jss.scrollIntoView(input);
			String inputValue = input.getAttribute("value");
			if (!(inputValue).equals(text)) {
				log.info("verify that any updates prior to selecting Inactive status are discarded");
			} else {
				log.info("unable to verify can not submit if  is not populated");
			}
		} catch (Exception e) {
			log.info("Exception:unable to verify can not submit if is not populated");
			throw e;
		}
		return true;
	}

	public IMAP_ModifyPage enterInput(String name, String text) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			WebElement input = driver.findElement(By.xpath("//input[@name='" + name + "']"));
			jss.scrollIntoView(input);
			input.clear();// clear the input field
			input.sendKeys(text);
			log.info("User entered text as:" + text);
			log.info("User should enter text for as: " + text);
			return new IMAP_ModifyPage(driver);
		} catch (Exception e) {
			log.info("User unable to entered text as:" + text);
			throw e;
		}
	}

	@FindBy(xpath = "(//*[contains(text(),'Remove Parent:')]/..)//descendant::input")
	public WebElement checkbox_removeParent;

	public void clickonRemoveParent() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(checkbox_removeParent, 40);
		checkbox_removeParent.click();
		log.info("User should be able to Remove Parent");
	}

	public void clickonSubmitButton() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(btnSubmit, 40);
		btnSubmit.click();
		log.info("User should be able to submit modify request");
	}

	@FindBy(xpath = "((//*[contains(text(),'Customer Name:')])[3]/..)//*[@name='customerName']")
	public WebElement field_customerName;

	public void enterCustomerName(String value) throws Exception {
		BaseUtilityHelper.WaitForElementPresent(field_customerName, 40);
		field_customerName.sendKeys(value);
		log.info("User should be able to Enter cuastomer name");

	}

	@FindBy(xpath = "(//button[contains(text(),'Search')])[4]")
	public WebElement button_searchparent;

	public void clickonSearchbutttonparent() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(button_searchparent, 40);
		button_searchparent.click();
		log.info("User should be able to click on search");
	}

	@FindBy(xpath = "(((((//*[contains(text(),'Action')])[3])//ancestor::table)/../../..)//following-sibling::div//*[contains(text(),'Select')])[2]")
	public WebElement select_parent;

	public void clickonSelectparent() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(select_parent, 40);
		select_parent.click();
		log.info("User should be able to select the parent");
	}

	public String selectParentgetvalues(int index) throws Exception {
		try {
			BaseUtilityHelper.waitTime(2);
			String temp = "";
			for (int i = 1; i <= 3; i++) {
				WebElement select = driver.findElement(By
						.xpath("((((((//*[contains(text(),'Action')])[3])//ancestor::table)/../../..)//following-sibling::div//*[contains(text(),'Select')])["
								+ index + "]/../..)//following-sibling::td[" + i + "]"));
				BaseUtilityHelper.WaitForElementPresent(select, 40);
				String value = select.getText();
				System.out.println(value);
				temp = temp + value + "|";
			}
			System.out.println(temp);
			String req_value = temp.substring(0, temp.length() - 1);
			// String req_value = temp.replace(temp.substring(temp.length()-1),
			// "");
			System.out.println(req_value);
			WebElement select = driver.findElement(By
					.xpath("(((((//*[contains(text(),'Action')])[3])//ancestor::table)/../../..)//following-sibling::div//*[contains(text(),'Select')])["
							+ index + "]"));
			select.click();
			return req_value;
		} catch (Exception e) {
			log.info("Exception:User is unable to select Request purpose ");
			throw e;
		}
	}

	public void clickonsearch() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(button_search, 40);
		button_search.click();
		log.info("User should be able to search the parent");
	}
	/*
	 * public void enterComment(String comments) throws Exception{
	 * BaseUtilityHelper.waitTime(2); field_comments.sendKeys(comments);
	 * HTMLReport.
	 * updateDetailsHTMLReport("User should enter a comments in comments field"
	 * ,"User entered comments in comment field"); }
	 */

	public void closeParentsearchwindow() throws Exception {
		BaseUtilityHelper.waitTime(2);
		closeParentsearch_window.click();
		log.info("User should close the parent search window");
	}

	@FindBy(xpath = "(//*[contains(text(),'Investigate Customer Hierarchy:')]/..)//child::div//input")
	WebElement checkbox_investparenthierarchy;

	public void clickInvestigateParentHierarchy() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(button_search, 40);
		checkbox_investparenthierarchy.click();
		log.info("User should click on investigate parent hierarchy request");
	}

	public void enterComment(int index, String comments) throws Exception {
		BaseUtilityHelper.waitTime(2);
		WebElement field = driver
				.findElement(By.xpath("(//*[contains(text(),'Comment')]//following::textarea)[" + index + "]"));
		field.sendKeys(comments);
		log.info("User should enter a comments in comments field");
	}

	@FindBy(xpath = "(//*[contains(text(),'Comment')]//following::textarea)[2]")
	WebElement comment1;

	public void enterComment1(int index, String comments) throws Exception {
		try {
			JavaScriptHelper js = new JavaScriptHelper(driver);
			js.scrollIntoView(submitBtn);
			comment1.clear();
			comment1.sendKeys(comments);
			// js.scrollIntoViewAndClick(comment1.sendKeys(comments));
			BaseUtilityHelper.waitTime(2);
			// WebElement field =
			// driver.findElement(By.xpath("(//*[contains(text(),'Comment')]//following::textarea)[3]"));
			log.info("User should enter a comments in comments field");
		} catch (Exception e) {
			log.info("Request purpose dropdown should be validated");
			throw e;
		}
	}

	public void clickOnrequestproposedropdown() throws Exception {
		BaseUtilityHelper.waitTime(2);
		click_dropdown.click();
		log.info("User should click on request purpose dropdown");
	}

	public void validateAllReqPurpDropdowns(String first_dropdown, String allvalues) throws Exception {
		try {
			BaseUtilityHelper.waitTime(2);
			List<WebElement> all_dropdowns = driver
					.findElements(By.xpath("(//*[contains(text(),'" + first_dropdown + "')]/..)//child::div"));
			String[] actual = allvalues.split(";");
			for (int i = 0; i < actual.length; i++) {
				String expected = all_dropdowns.get(i).getText();
				System.out.println(expected);
				System.out.println(actual[i]);
				if (expected.contains(actual[i])) {
					log.info("Request purpose dropdown " + actual[i] + " should be present");
				} else {
					log.info(
							"Request purpose dropdown " + actual[i] + " should be present");
				}
			}
		} catch (Exception e) {
			log.info("Request purpose dropdown should be validated");
			throw e;
		}

	}

	/*
	 * @FindBy(xpath =
	 * "//span[contains(text(),'Please specify is mandatory field for this Request purpose.')]"
	 * ) public WebElement errormsg;
	 */
	public IMAP_SearchPage validateSubmitPopupErrormsg(String Message) throws Exception {
		try {
			BaseUtilityHelper.waitTime(2);
			WebElement popup_error = driver.findElement(By.xpath("//span[contains(text(),'" + Message + "')]"));
			System.out.println(Message);
			String actValue = popup_error.getText();
			System.out.println(actValue);
			if (actValue.contains(Message)) {
				log.info("Error message'" + actValue + "' Should display.");
			}

			return new IMAP_SearchPage(driver);

		} catch (Exception e) {
			log.info(
					"Exception: " + "Error message '" + Message + "' is not displayed.");
			throw e;
		}
	}

	@FindBy(xpath = "(//*[@class='x-btn-mc'])[25]")
	public WebElement Error_Popupbutton_OK;

	public void clickErrorPopupOK() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			Error_Popupbutton_OK.click();
			// log.info("OK button should be clicked"
			// , "OK button is clcked");
		} catch (Exception e) {
			log.info("Exception:  Button is not displayed.");
			throw e;
		}
	}

	@FindBy(xpath = "//*[@id='plsSpecify']")
	public WebElement specify_others;

	public void enterPlsSpecifyfield(String reason) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			specify_others.sendKeys(reason);
			log.info("'Please specify' field should be entered as " + reason + "");
		} catch (Exception e) {
			log.info("Exception:  unable to enter into please specify field.");
			throw e;
		}
	}

	@FindBy(xpath = "(//*[contains(text(),'Inactivation Reason:')]/..)//following-sibling::img")
	public WebElement inactive_dropdown;

	public void clickOnInactivationReasonDropdown() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(inactive_dropdown, 40);
		inactive_dropdown.click();
		log.info("User should click on request purpose dropdown");
	}

	@FindBy(xpath = "//table[@class='x-btn emc-button-green emc-button x-btn-noicon x-item-disabled']")
	WebElement disabled_SubmitBtn;

	public void validateSubmitButtonDisabled() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(submitBtn, 40);
		if (disabled_SubmitBtn.isDisplayed()) {
			log.info("Submit button should be disabled");
		} else {
			log.info("Submit button should be disable");
		}
	}

	public CustomerCloneRequestPage verifyInactivationDropDownValues() throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_InactiveReason);
			this.click_drop.click();
			String values = "Acquired;Bldg Closed, Out of Bus.;Duplicate Record Exists;Mass Inactivation Candidate;Relocated";
			String[] req = values.split(";");
			List<WebElement> list = driver.findElements(By.xpath("//div[@class='x-combo-list-item']"));
			for (int i = 1; i < list.size(); i++) {
				WebElement Reason = driver.findElement(By.xpath("(//div[@class='x-combo-list-item'])[" + i + "]"));
				String ReasonValue = Reason.getText();
				if (ReasonValue.contains(req[i])) {
					log.info("user should Verify the Inactivation Reason dropdown,Dropdown value" + ReasonValue);
				} else {
					log.info("User unable to get the dropdown values");
				}
			}
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("Exception:User is unable to select inactivation reason");
			throw e;
		}
	}

	public CustomerCloneRequestPage verifyRequestPurposeDropDownValues() throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_reqPurpose);
			this.click_dropdown.click();
			String values = "MUP;Billing;Quoting / Forecast;Install Base / Shipping / Deal Reg.;Other";
			String[] req = values.split(";");
			List<WebElement> list = driver.findElements(By.xpath("//div[@class='x-combo-list-item']"));
			for (int i = 1; i < list.size(); i++) {
				WebElement Reason = driver.findElement(By.xpath("(//div[@class='x-combo-list-item'])[" + i + "]"));
				String ReasonValue = Reason.getText();
				if (ReasonValue.contains(req[i])) {
					log.info("user should Verify the Inactivation Reason dropdown, Dropdown value::" + ReasonValue);
				} else {
					log.info("User unable to get the dropdown values");
				}
			}
			return new CustomerCloneRequestPage(driver);

		} catch (Exception e) {
			log.info("Exception:User is unable to select inactivation reason ");
			throw e;
		}
	}

	public static boolean IsElementPresent(WebElement objname) throws Exception {
		try {
			objname.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}
	}

	@FindBy(xpath = "//label[contains(text(),'Inactivation Reason:')]")
	public WebElement inactivationReason;

	public void verifyDisapperValue() throws Exception {
		if (IsElementPresent(inactivationReason)) {
			log.info("Inactivation Reason should field is disapper");
		} else {
			log.info("Exception:Inactivation reason dropdown is not disappeared");
		}
	}

	public void EnterComments(String comment1) throws Exception {
		try {
			textArea_cmt.sendKeys(comment1);
			log.info("User should be able to Enter comment");

		} catch (Exception e) {
			log.info("Exception: comments are disabled");
			throw e;
		}
	}

	@FindBy(xpath = "//table[@id='searchButton1']")
	public WebElement button_searchReplaceUCID;

	public void clickonSearchbutttonReplaceUCID() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(button_searchReplaceUCID, 40);
		JavaScriptHelper jss = new JavaScriptHelper(driver);
		jss.scrollIntoView(button_searchReplaceUCID);
		button_searchReplaceUCID.click();
		BaseUtilityHelper.wait3Seconds();
		log.info("User should be able to click on search");
	}

	@FindBy(xpath = "(//label[text()='Customer Name:'])[2]/following::input[1]")
	public WebElement customerName;

	public void setCustomerName(String name) throws IOException, ParseException {
		log.info("entering Customer Name.." + name);
		this.customerName.clear();
		this.customerName.sendKeys(name);

		log.info("Customer name should be entered as:- " + name + "");
	}

	@FindBy(xpath = "//table[@id='searchReplaceUcidBtn']")
	public WebElement button_searc;

	public void clickonSearchButton() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(button_searc, 40);
		JavaScriptHelper jss = new JavaScriptHelper(driver);
		jss.scrollIntoView(button_searc);
		button_searc.click();
		BaseUtilityHelper.wait3Seconds();
		log.info("User should be able to click on search");
	}

	public void clearFields(String field) {
		WebElement fieldName = driver.findElement(By.xpath("(//label[text()='" + field + "'])[2]/following::input[1]"));
		fieldName.clear();
	}

	public void clickOnSelect() {
		WebElement fieldName = driver.findElement(By.xpath("(//a[contains(text(),'Select')]/..)[2]//a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", fieldName);
	}

	@FindBy(xpath = "//span[contains(text(),'Please correct your Data before creating the record')]")
	public WebElement validate_screen;

	public String get_Screen_info() {
		String msg1 = validate_screen.getText();
		return msg1;
	}

	@FindBy(xpath = "//span[text()='Errors']")
	public WebElement Error_msg;

	public String get_Error_msg() {
		String msg2 = Error_msg.getText();
		return msg2;
	}

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement validate_screen_ok_button;

	public void validate_screen_ok_button() {
		validate_screen_ok_button.click();

	}

	public WebElement address_line4_Modify;

	public void address_line4_Modify(String text) {
		address_line4_Modify.sendKeys(text);
		BaseUtilityHelper.wait3Seconds();
	}

	public String get_text_address_line4() {
		String addressl4 = address_line4_Modify.getText();
		return addressl4;

	}

	public WebElement address_line_1;

	public void scrolltoaddressline() {
		JavaScriptHelper jss = new JavaScriptHelper(driver);
		try {
			jss.scrollIntoView(address_line_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * UCID Search Page.
	 */	

//	Ravindra: Created below multiple lines of code to automate "UCID Search" page.
	@FindBy(xpath = "//span[contains(text(),'UCID Search')]")
	public WebElement ucid_search_page;
	
	public void ucid_search_page() throws Exception {
		String ucid_page = ucid_search_page.getText();
		log.info(ucid_page+" page is displayed.");
		log.info("UCID Search page should be displayed"+ ucid_page);
	}
	
	public void clearCustAddressField(String custAddress) {
		WebElement fieldName = driver.findElement(By.xpath("(//input[@name='" + custAddress + "'])[2]"));
		fieldName.clear();
//		log.info(custAddress+" page is displayed.");
//		log.info("UCID Search page should be displayed", ucid_page+" page is displayed.");
	}
	
	public void clearCityField(String custCity) {
		WebElement fieldName = driver.findElement(By.xpath("(//input[@name='" + custCity + "'])[3]"));
		fieldName.clear();
	}
	
	public void clearPostalCode(String custPCode) {
		WebElement fieldName = driver.findElement(By.xpath("(//input[@name='" + custPCode + "'])[3]"));
		fieldName.clear();
	}
	
	public void clearCustName(String custName) {
		WebElement fieldName = driver.findElement(By.xpath("(//input[@name='" + custName + "'])[2]"));
		fieldName.clear();
	}

	@FindBy(xpath = "(//button[contains(text(),'Search')])[4]")
	public WebElement button_ucidsearch;

	public void clickonSearchButtonUCIDSearch() throws Exception {
		BaseUtilityHelper.WaitForElementPresent(button_ucidsearch, 40);
		button_ucidsearch.click();
		log.info("User should be able to click on Search button UCID Search page.");
	}	
	
	public void verifyErrorMessageOnUCIDSearchPage() throws Exception {
		String error_msg = driver.findElement(By.xpath("(//label[contains(text(),'Enter data in at least one of the red fields.')])[2]")).getText();
		log.info("Error message '"+error_msg+"' is displayed.");
		log.info("Error message '"+error_msg+"' should be displayed on UCID Search page.");
	}	
	
	@FindBy(xpath = "(//input[@name='customerId'])[2]")
	public WebElement replace_cust_id;
	
	public void setCustIDonUCIDSearchPage(String custID) throws Exception{
//		WebElement cust_ID = driver.findElement(By.xpath("(//input[@name='" + custID + "'])[2]"));
//		cust_ID.sendKeys("custID");						 
		this.replace_cust_id.clear();
		this.replace_cust_id.sendKeys(custID);
		log.info("User entered replacement '"+custID+"' in Customer ID field.");
		log.info("User should entered replacement UCID in Customer ID field.");
	}	

	@FindBy(xpath = "(//input[@name='customerSourceId'])[2]")
	public WebElement customer_Source;
		
	public void setCustomerSourceOnUCIDSearchPage(String custSource) throws IOException, ParseException {
		log.info("Entering Customer Source.." + custSource);
		this.customer_Source.clear();
		this.customer_Source.sendKeys(custSource);
//		this.customer_Source.sendKeys(Keys.ARROW_DOWN);
		this.customer_Source.sendKeys(Keys.ENTER);
		log.info("Customer Source should be selected as " + custSource + "");
	}
	
	@FindBy(xpath = "(//input[@name='country'])[2]")
	public WebElement country_ucid_search_page;
	
	public void setCountryOnUCIDSearchPage(String country) throws IOException, ParseException {
		log.info("Entering Customer Source.." + country);

		this.country_ucid_search_page.clear();
		this.country_ucid_search_page.sendKeys(country);
//		this.country_ucid_search_page.sendKeys(Keys.ARROW_DOWN);
		this.country_ucid_search_page.sendKeys(Keys.ENTER);
		log.info("Country should be selected as " + country + "");
	}
		
	@FindBy(xpath = "(//input[@name='region'])[3]")
	public WebElement region_ucid_search_page;
	
	public void setRegionOnUCIDSearchPage(String region) throws IOException, ParseException {
		log.info("Entering Customer Source.." + region);

		this.region_ucid_search_page.clear();
		this.region_ucid_search_page.sendKeys(region);
//		this.region_ucid_search_page.sendKeys(Keys.ARROW_DOWN);
		this.region_ucid_search_page.sendKeys(Keys.ENTER);
		log.info("Region should be selected as " + region + "");
	}
	
	@FindBy(xpath = "//a[contains(text(),'Select')][1]")
	public WebElement select_link_ucid_search_page;
	
	public void clickSelectLinkOnUCIDSearchPage() throws IOException, ParseException {
		this.select_link_ucid_search_page.click();
		log.info("Click on Select link on UCID Search page..");
		log.info("User should click on Select link on UCID Search page.");
	}

//	Manjunath: Created below method to handle different Identifiers on Customer Modify popup window
	public void clickOn_Button(String status) throws Exception {
		try {
			WebElement WB = driver.findElement(By.xpath(".//*[@id='" + status + "']"));
			WB.click();
			BaseUtilityHelper.wait3Seconds();
			log.info("user should click on " + status + " button");
		} catch (Exception e) {
			log.info("Exception: Unable to clicked on the button");
			throw e;
		}
	}
	
	public String getText(String value) throws Exception{
		String gettxt ="";			
		gettxt = driver.findElement(By.xpath("//span[contains(text(),'"+value+"')]")).getText().trim();				
		return gettxt;
	} 
	
//  Ravindra: Created below method to handle different Identifiers on Customer Modify popup window
    public String getCustomerIdentifierValue(String identifierValue) throws Exception {
                   String ident_value = driver.findElement(By.xpath("//td[contains(text(),'" + identifierValue + "')]/following-sibling::td")).getText().trim();
                   return ident_value;                         
    }

}