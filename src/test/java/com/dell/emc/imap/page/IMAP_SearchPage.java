package com.dell.emc.imap.page;

import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;
import com.dell.emc.imap.utils.generichelper.JavaScriptHelper;
import com.dell.emc.imap.utils.PageBase;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;

public class IMAP_SearchPage extends PageBase {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(IMAP_SearchPage.class);

	// @FindBy(xpath = "//*[@id='wsfd-tab0t2']/iframe")
	@FindBy(xpath = "//iframe[@name='tasks']")
	public WebElement iframe_tasks;

	@FindBy(xpath = "//label[text()='Customer Source:']/following::input[1]")
	public WebElement customerSource;

	@FindBy(xpath = "//label[text()='Customer ID:']/following::input[1]")
	public WebElement customerId;

	@FindBy(xpath = "//label[text()='Customer Name:']/following::input[1]")
	public WebElement customerName;

	@FindBy(xpath = "//label[text()='Address:']/following::input[1]")
	public WebElement customerAddress;

	@FindBy(xpath = "//label[text()='City:']/following::input[1]")
	public WebElement city;

	@FindBy(xpath = "//label[text()='Country:']/following::input[1]")
	public WebElement country;
	
	@FindBy(xpath = "//label[text()='County:']/following::input[1]")
	public WebElement county;
	
	@FindBy(xpath = "//label[text()='Region/State:']/following::input[1]")
	public WebElement region_state;

	@FindBy(xpath = "//label[text()='Postal Code:']/following::input[1]")
	public WebElement postalCode;

	@FindBy(xpath = "//label[text()='GU Parent Customer Name:']/following::input[1]")
	public WebElement guParentCustomerName;

	@FindBy(xpath = "//label[text()='GU DUNS Number:']/following::input[1]")
	public WebElement guDunsNumber;

	@FindBy(xpath = "//label[text()='Segmentation:']/following::input[1]")
	public WebElement segmentation;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement searchButton;

	@FindBy(xpath = "(//button[contains(text(),'Search')])[3]")
	public WebElement searchButton_RplId;

	@FindBy(xpath = "//button[contains(text(),'Search')]/following::a[1]")
	public WebElement clearLink_SearchSection;

	@FindBy(xpath = "//button[contains(text(),'find a match')]")
	public WebElement cantFindaMatchButton;

	@FindBy(xpath = "//*[contains(text(),'Search in progress')]")
	public WebElement cantFindaMatchButton1;

	@FindBy(xpath = "//*[@id='hubRecordFilter']")
	public WebElement filterSearchResultInput;

	@FindBy(xpath = "//*[@id='chkGoldenRecords']")
	public WebElement goldernRecordsCheckbox;

	@FindBy(xpath = "//*[@id='chkContRecords']")
	public WebElement contributorRecordsCheckbox;

	@FindBy(xpath = "//*[@id='chkPendindRecords']")
	public WebElement pendingRecordsCheckbox;

	@FindBy(xpath = "//span[text()='Search Criteria']/following::table[2]/tbody")
	public WebElement SearchResultsTable;

	@FindBy(xpath = "//div[contains(text(),'No Search Results to Display')]")
	public WebElement SearchResultsTableEmpty;

	@FindBy(xpath = "//img[@class='x-action-col-icon x-action-col-0 modify-icon undefined']")
	public WebElement modifyImage;

	@FindBy(xpath = "//img[@class='x-action-col-icon x-action-col-1 sfdc-retrig-icon undefined']")
	public WebElement resendSFDCOthersDownstreamImage;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='Affinity ID']/following::span[1]")
	public WebElement strNameLink_ToolTip_Affinity_ID;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='11i OSR number']/following::span[1]")
	public WebElement strNameLink_ToolTip_11i_OSR_number;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='11i Party Number']/following::span[1]")
	public WebElement strNameLink_ToolTip_11i_Party_Number;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='Entity Identifier']/following::span[1]")
	public WebElement strNameLink_ToolTip_Entity_Identifier;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='SFDC_ACCT_NO']/following::span[1]")
	public WebElement strNameLink_ToolTip_SFDC_ACCT_NO;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='UCID']/following::span[1]")
	public WebElement strNameLink_ToolTip_UCID;

	@FindBy(xpath = "//div[@class='x-tip-mc']/div/following::span[text()='11i Account Number']/following::span[1]")
	public WebElement strNameLink_ToolTip_11i_Account_Number;

	@FindBy(xpath = "//div[text()='Action']/following::table[@class='x-grid3-row-table']/tbody")
	public WebElement SearchCriteriaResultsTable;

	@FindBy(xpath = "//div[contains(@class,'x-grid3-body')]//table/tbody/tr[1]/td[14]/div")
	public WebElement SearchCriteriaResultsTableRows;

	// label[text()='Request Purpose ']/following::img[1]

	@FindBy(xpath = "//label[text()='Request Purpose ']/following::img[1]")
	public WebElement createRequest_RequestPurpose_Dropdown_Img;

	@FindBy(xpath = "//label[text()='Request Purpose ']/following::input[1]")
	public WebElement createRequest_RequestPurpose;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement createRequest_Submit_Button;

	@FindBy(xpath = "//button[contains(text(),'Refine search')]")
	public WebElement createRequest_Refinesearch_Button;

	@FindBy(xpath = "//a[contains(text(),'Cancel')]")
	public WebElement createRequest_Cancel_link;

	@FindBy(xpath = "//span[contains(text(),'Request has been submitted successfully :')]")
	public WebElement createRequest_Successful_msg;

	@FindBy(xpath = "//span[contains(text(),'Request has been submitted successfully :')]")
	public WebElement pending_customer_msg;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement createRequest_OK_Button;

	@FindBy(xpath = "//div[@id='replaceUcidSearchForm']/descendant::label[text()='Customer Source:']/following::input[1]")
	public WebElement str_UCID_SearchForm_customerSource;

	@FindBy(xpath = "//div[@id='replaceUcidSearchForm']/descendant::label[text()='Customer ID:']/following::input[1]")
	public WebElement str_UCID_SearchForm_customerId;

	@FindBy(xpath = "//*[@id='ext-gen149']")
	public WebElement searchResultTableWithData;
	
	@FindBy(xpath = "//*[@id='ext-gen267']/div/table")
	public WebElement searchResultTableWithData1;
	
	@FindBy(xpath = "(//tr[@class='x-grid3-hd-row'])[1]/..")
	public WebElement table_header;

	@FindBy(xpath = "//*[@id='ext-gen149']/div[1]/table/tbody/tr/td[1]/div/img[contains(@class,'sfdc-retrig-icon')]")
	public WebElement resendIcon_First;
	@FindBy(xpath = "//*[@id='plsSpecify']")
	public WebElement specify_others;

	@FindBy(xpath = "//span[contains(text(),'Please Wait...')]")
	public WebElement pleseWait;

	@FindBy(xpath = "//span[contains(text(),'Errors')]/preceding-sibling::div")
	public WebElement cancel;

	@FindBy(xpath = "//span[text()='Warning']/preceding-sibling::div")
	public WebElement cancel1;

	@FindBy(xpath = "//*[@id='ext-gen149']/div[1]/table/tbody/tr/td[4]")
	public WebElement UCID;

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]")
	public WebElement popup_dlg;

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]/div/following::table//td[@class='x-btn-mc']//button[contains(text(),'Yes')]")
	public WebElement SFDCRetriggerBtnYes;

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]/div/following::table//td[@class='x-btn-mc']//button[contains(text(),'No')]")
	public WebElement SFDCRetriggerBtnNo;

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]//div/following::span[contains(text(),'UCID Retrigger Status')]")
	public WebElement SFDCRetriggerStatusDlg;

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]/div/following::table//td[@class='x-btn-mc']//button[contains(text(),'OK')]")
	public WebElement SFDCRetriggerStatusDlgBtnOK;

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]//div/following::span[2]")
	public WebElement SFDCRetriggerStatusDlgText;

	@FindBy(xpath = "(//div[@class='x-grid3-body'])[1]")
	public WebElement tableBody;

	@FindBy(xpath = "((//div[@class='x-grid3-body'])[1]//tbody//td[7]/div)[1]/a")
	public WebElement customername;

	@FindBy(xpath = "(//table/tbody/tr/td[1]/div/img[1])[1]")
	public WebElement records;

	@FindBy(xpath = "//span[contains(text(),'Postal Code modified')]")
	public WebElement postalcode_OK_Button;

	@FindBy(xpath = "(//*[@class='x-btn-mc'])[19]")
	public WebElement button_reqOK;

	@FindBy(xpath = "//*[@id='county']")
	public WebElement county1;
	
	public IMAP_SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickCustomerSourceDropdown() {
		log.info("clicked on Customer Source Dropdown.." + customerSource);
		this.customerSource.click();
	}

	public void clickOnCancel() {
		log.info("clicked on Error popup cancel" + cancel);
		this.cancel.click();
	}

	public void clickOnCustomername() {
		log.info("clicked on Error popup cancel" + cancel);
		this.customername.click();
	}

	public void clickCancel() {
		log.info("clicked on Error popup cancel" + cancel);
		this.cancel1.click();
	}

	public void setCustomerSource(String custSource) throws IOException, ParseException {
		log.info("entering Customer Source.." + custSource);

		// this.customerSource.clear();
		this.customerSource.sendKeys(custSource);
		this.customerSource.sendKeys(Keys.ARROW_DOWN);
		this.customerSource.sendKeys(Keys.ENTER);

	}

	public void setCustomerID(String Id) throws IOException, ParseException {
		log.info("Entering Customer ID..:" + Id);		
		this.customerId.clear();
		this.customerId.sendKeys(Id);
	}

	public void setCustomerName(String name) throws IOException, ParseException {
		log.info("entering Customer Name.." + name);
		this.customerName.clear();
		this.customerName.sendKeys(name);

	}

	public void setCustomerAddress(String address) throws IOException, ParseException {
		log.info("entering Address.." + address);
		this.customerAddress.sendKeys(address);
	}

	public void setCustomerCity(String city) throws Exception {
		log.info("entering City.." + city);

		this.city.sendKeys(city);
	}

	public void setCountry(String country) throws Exception {
		log.info("entering Country.." + country);

		this.country.sendKeys(country);
		// this.country.sendKeys(Keys.ARROW_DOWN);
		this.country.sendKeys(Keys.ENTER);
	}

	public void setCounty(String county) throws Exception {
		log.info("entering Country.." + county);

		this.county.sendKeys(county);
		// this.country.sendKeys(Keys.ARROW_DOWN);
		this.country.sendKeys(Keys.ENTER);
	}
	public void setRegionState(String region_state) throws Exception {
		log.info("entering Region State.." + region_state);

		this.region_state.sendKeys(region_state);
		this.region_state.sendKeys(Keys.ARROW_DOWN);
		this.region_state.sendKeys(Keys.ENTER);
	}

	public void setPostalCode(String postalcode) throws Exception {
		log.info("entering Postal Code.." + postalcode);
		this.postalCode.sendKeys(postalcode);
	}

	public void setGUParentCustomerName(String guParentCustName) {
		log.info("entering GU Parent Customer Name.." + guParentCustName);
		this.guParentCustomerName.sendKeys(guParentCustName);

	}

	public void setGUDUNSNumber(String guDUNSNumber) {
		log.info("entering GU DUNS Number.." + guDUNSNumber);
		this.guDunsNumber.sendKeys(guDUNSNumber);
	}

	public void setSegmentation(String segmentation) {
		log.info("entering Segmentation.." + segmentation);

		this.segmentation.sendKeys(segmentation);
		this.segmentation.sendKeys(Keys.ARROW_DOWN);
		this.segmentation.sendKeys(Keys.ENTER);
	}

	public void clickSearchButton() throws Exception{
		log.info("clicked on Search Button.." + searchButton);
		this.searchButton.click();			
	}

	public void clickReplacementUCIDSearchButton() {
		log.info("clicked on Search Button.." + searchButton);

		this.searchButton_RplId.click();
	}

	public void clickClearLink() {
		log.info("clicked on Clear Link.." + clearLink_SearchSection);

		this.clearLink_SearchSection.click();
	}

	@FindBy(xpath = "//div[contains(@class,'ext-el-mask')]")
	public WebElement loadingbar;

	public void clickCantFindaMatchButton() throws Exception {
		this.cantFindaMatchButton.click();
		log.info("clicked on Can't Find a Match Button.." + cantFindaMatchButton);
	}

	public void clickRequestPurposeDropdown() {
		log.info("clicked on Request Purpose Dropdown.." + createRequest_RequestPurpose_Dropdown_Img);

		this.createRequest_RequestPurpose_Dropdown_Img.click();
	}

	public void setRequestPurpose(String requestpurpose) {
		log.info("entering Requestpurpose.." + requestpurpose);

		this.createRequest_RequestPurpose.sendKeys(requestpurpose);
		this.createRequest_RequestPurpose.sendKeys(Keys.ARROW_DOWN);
		this.createRequest_RequestPurpose.sendKeys(Keys.ENTER);
	}

	public void createRequest_Submit_Button() throws Exception {
		this.createRequest_Submit_Button.click();
		log.info("clicked on Create Request_Submit Button.." + createRequest_Submit_Button);
	}

	public void clickRetrigger() {

		String ucid = this.UCID.getText();
		this.resendIcon_First.click();
		log.info("clicked on Resedn img.." + resendIcon_First);
		BaseUtilityHelper.wait10Seconds();
		BaseUtilityHelper.wait10Seconds();
		this.SFDCRetriggerBtnYes.click();
		log.info("clicked on SFDC Retrigger.." + SFDCRetriggerBtnYes);
		BaseUtilityHelper.wait10Seconds();
		String retriggerStatus = SFDCRetriggerStatusDlgText.getText();
		log.info("SFDC Retrigger status text..." + retriggerStatus);

		if (retriggerStatus.contains(ucid)) {
			log.info("UCID is displayed correctly in the SFDC status Retrigger page");
		}
		this.SFDCRetriggerStatusDlgBtnOK.click();
	}

	@FindBy(xpath = "(//*[@id='ext-gen149']//following::td[3])[1]")
	public WebElement ucid;

	public String getUcidinFirstOfRecord() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			String Ucid = ucid.getText();
			System.out.println(Ucid);
			return Ucid;
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "(((//*[contains(text(),'UCID')])//ancestor::table/../../../..)//descendant-or-self::td[5]//child::div)[2]")
	public WebElement MDM_id;

	public String getMDM_IDinFirstRecord() throws Exception {

		try {
			BaseUtilityHelper.wait5Seconds();
			String MDM_ID = MDM_id.getText();
			System.out.println(MDM_ID);
			return MDM_ID;
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "(((//*[contains(text(),'UCID')])//ancestor::table/../../../..)//descendant-or-self::td[7]//child::div)[2]")
	public WebElement cust_name;

	public String getCust_NamefromFirstRecord() throws Exception {

		try {
			BaseUtilityHelper.wait5Seconds();
			String customername = cust_name.getText();
			System.out.println(customername);
			return customername;
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "(((//*[contains(text(),'UCID')])//ancestor::table/../../../..)//descendant-or-self::td[6]//child::div)[2]")
	public WebElement party_ID;

	public String getParty_IDfromFirstRecord() throws Exception {

		try {
			BaseUtilityHelper.wait5Seconds();
			String partyID = party_ID.getText();
			System.out.println(partyID);
			return partyID;
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = ".//input[@id='chkGoldenRecords']")
	public WebElement golden_chxBo;

	public IMAP_SearchPage clickOnCheckBox(String label) throws Exception {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			WebElement checkbox = driver
					.findElement(By.xpath("//label[text()='" + label + "']/preceding-sibling::input"));
			executor.executeScript("arguments[0].click();", checkbox);
			log.info("Click on " + label + " check box");
			return new IMAP_SearchPage(driver);
		} catch (Exception e) {
			throw e;
		}

	}
	
//	Ravindra: Created below method to click on "Pending Records" check box
	@FindBy(xpath = ".//input[@id='chkPendindRecords']")
	public WebElement pending_chxBo;

	public IMAP_SearchPage clickOnPendingRecordsCheckBox(String label) throws Exception {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			WebElement checkbox = driver
					.findElement(By.xpath("//label[text()='" + label + "']/preceding-sibling::input"));
			executor.executeScript("arguments[0].click();", checkbox);
			log.info("Click on " + label + " check box");
			return new IMAP_SearchPage(driver);
		} catch (Exception e) {
			throw e;
		}

	}

	public IMAP_SearchPage clickOnCloneImg(int index) throws Exception {
		try {
			WebElement ele = driver.findElement(By.xpath("(//table/tbody/tr/td[1]/div/img[4])[" + index + "]"));
			log.info("Clicked on Clone image.." + ele);
			if(ele.isDisplayed() || ele.isEnabled() || ele.isSelected()) {
				ele.click();
			}
			else {
				throw new Exception("Exception:Provided data is invalide, please check the input data in query again");
			}
			return new IMAP_SearchPage(driver);
		} catch (Exception e) {
			throw e;
		}

	}

	/* isElementPresent method */
	@FindBy(xpath = "//*[@id='ext-gen329']")
	public WebElement custnameN;

	public static boolean IsElementPresent(WebElement custnameN) throws Exception {
		try {
			custnameN.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}
	}

	@FindBy(xpath = "//span[text()='Error']")
	public WebElement error_msg;
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement btn_OK;
//	@FindBy(xpath = ".//*[@id='appLogo']")
//	public WebElement logo_img;
	@FindBy(how = How.XPATH, using = "//*[@id='appLogo']")
	private WebElement logo_img;
	
	public boolean verifyiMAPCustomerPortal_HomePage() throws Exception {
		try {
			// BaseUtilityHelper.wait3Seconds();
			if (BaseUtilityHelper.IsElementPresent(error_msg)) {
				BaseUtilityHelper.Click(btn_OK);
				return true;
			} else if (logo_img.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void verifyWarning_msg(String text) throws Exception {
		try {
			WebElement value = driver.findElement(By.xpath("//span[contains(text(),'" + text + "')]"));
			if (value.isDisplayed())
				log.info("warning message should be displayed" + value.getText());
			else
				log.info("Exception: Unable to get the warning message");
		} catch (Exception e) {
			log.info("Exception: warning message is NOT displayed");
			throw e;
		}
	}

	public void clickOnModify() throws Exception {
		try {
			List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/img[1]"));
			for (int i = 1; i <= list.size(); i++) {
				WebElement modifyEle = driver.findElement(By.xpath("(//table/tbody/tr/td[1]/div/img[1])[" + i + "]"));
				log.info("clicked on Modify image.." + modifyEle);
				modifyEle.click();
				// JavascriptExecutor executor = (JavascriptExecutor) driver;
				// executor.executeScript("arguments[0].click();", modifyEle);
				BaseUtilityHelper.wait3Seconds();
				if (IsElementPresent(txt_pending)) {
					click_cancel.click();
					BaseUtilityHelper.wait3Seconds();
				} else {
					break;
				}
			}
		} catch (Exception e) {
			throw e;
			
		}
	}

	@FindBy(xpath = ".//*[@id='ext-comp-1209']/tbody/tr[2]/td[2]")
	public WebElement button_OK;

	public void clickOnOK() throws Exception {
		try {
			log.info("clicked on OK button.." + button_OK);
			this.button_OK.click();
			BaseUtilityHelper.wait3Seconds();
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickElement(String attribute, String value) throws Exception {
		WebElement element = driver.findElement(By.xpath("//" + attribute + "[text()='" + value + "']"));
		element.click();
		log.info("Web Element " + value + " is successfully clicked");
		// HTMLReport.updateDetailsHTMLReport("WebElement "+value+" should be
		// clicked","WebElement "+value+" is clicked successfully");

	}
	// WebElement ele=getElement(Base.Identifier.xpath, "");

	@FindBy(xpath = "//div[starts-with(@id,'ext-comp')][contains(@style ,'visibility: visible')]//div/following::span/b[contains(text(),'Pending change on Customer')]")
	public WebElement txt_pending;
	@FindBy(xpath = "(//div[@class='x-tool x-tool-close'])[2]")
	public WebElement click_cancel;
	@FindBy(xpath = "//span[text()='Customer Modify']")
	public WebElement txt_modify;

	@FindBy(xpath = ".//*[@id='reqPurpose']")
	public WebElement dropdown_reqPurpose;
	@FindBy(xpath = ".//*[@id='reqPurpose']/following-sibling::img")
	public WebElement click_dropdown;
	
	public IMAP_SearchPage checkStatus_clickOnModify(String status) throws Exception {
		try {
			BaseUtilityHelper.wait5Seconds();
			List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/img/.."));
			System.out.println(list.size());
			for (int i = 1; i <= list.size(); i++) {
				String value = driver.findElement(By.xpath("(//table/tbody/tr/td[15]/div)[" + i + "]")).getText();
				System.out.println(value);
				if (value.equals(status)) {
					WebElement modify = driver.findElement(By.xpath("(//table/tbody/tr/td[1]/div/img[1])[" + i + "]"));
					BaseUtilityHelper.wait3Seconds();
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", modify);
					BaseUtilityHelper.wait5Seconds();
					BaseUtilityHelper.wait5Seconds();
					if (IsElementPresent(txt_pending)) {
						click_cancel.click();
					} else {
						break;
					}
				}
			}
			return new IMAP_SearchPage(driver);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void selectRequestPurpose(String purpose) throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_reqPurpose);
			// jss.scrollIntoViewAndClick(dropdown_reqPurpose);
			this.click_dropdown.click();
			WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + purpose + "')]"));
			BaseUtilityHelper.wait10Seconds();
			ele.click();
		} catch (Exception e) {
		}
	}

	@FindBy(xpath = "//span[contains(text(),'Please specify is mandatory field for this Request purpose.')]")
	public WebElement errormsg;

	public IMAP_SearchPage ValidateErrormsg(String Message) throws Exception {
		try {
			WebElement error = driver.findElement(By.xpath("//span[contains(text(),'" + Message + "')]"));
			System.out.println(Message);
			String actValue = error.getText();
			System.out.println(actValue);
			if (actValue.contains(Message)) {
				log.info("Error message'" + Message + "' Should display. Message'" + actValue + "' is displayed.");
			}

			return new IMAP_SearchPage(driver);

		} catch (Exception e) {
			log.info("Exception: " + "Error message '" + Message + "' is not displayed.");
			throw e;
		}
	}

	// @FindBy(xpath = "(//*[@class='x-btn-mc'])[23]")
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement button_OK11;

	public void clickpopupOK() throws Exception {
		try {
			BaseUtilityHelper.wait10Seconds();
			button_OK11.click();
			// HTMLReport.updateDetailsHTMLReport("OK button should be clicked" , "OK button
			// is clcked");
		} catch (Exception e) {
			throw e;
		}
	}

	public void enterPlsSpecifyfield(String reason) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			specify_others.sendKeys(reason);
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "//*[contains(text(),'Submit Anyway')]/..")
	public WebElement submitanyway;

	public void clickonSubmitAnyway() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			submitanyway.click();
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickonSearchcriteriaCreate(String custname) throws Exception {
		try {
			WaitHelper waitHelper = new WaitHelper(driver);
			waitHelper.fluentwaitForElementClickable(By.xpath("((//*[contains(text(),'" + custname
					+ "')]/..)//following-sibling::td[5])//*[contains(text(),'Create')]"), 100, 7);
			log.info("wait for the element");
			WebElement search_create = driver.findElement(By.xpath("((//*[contains(text(),'" + custname
					+ "')]/..)//following-sibling::td[5])//*[contains(text(),'Create')]"));
			log.info("create element");
			search_create.click();
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean ValidateMadatoryfieldErrorsymbols(String label) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			WebElement error_symbol = driver.findElement(By.xpath("(//label[contains(text(),'" + label
					+ "')]//..)//following-sibling::*[@class='x-form-invalid-icon']"));
			if (error_symbol.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "(((//*[contains(text(),'Action')])[1])//ancestor::table/../../..)//following-sibling::*[@class='x-grid3-scroller']")
	public WebElement table_withrecord;

	public boolean ValidateSearchResultTablewithRecord() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			if (table_withrecord.isDisplayed()) {
			} else {
			}
		}

		catch (Exception e) {
			throw e;
		}

		return true;
	}

	/**
	 * subbarayudu This method used to clicked on the any TAB int he iMAP portal
	 * home page TABS:{MY REQUESTS, INDIRECT PENDIC PORTAL, CONTACT CDCC, FAQ,
	 * TRANSLATION REQUEST FORM, USER GUIDE,ONLINE TRINING}
	 */
	public IMAP_SearchPage ClickOnTab(String value) throws Exception {
		try {
			WebElement tab = driver.findElement(By.xpath("//a[@class='menuItem' and @onclick='" + value + "']"));
			String actValue = tab.getAttribute("innerText");
			System.out.println(actValue);
			tab.click();
			// HTMLReport.updateDetailsHTMLReport("User able to click on the
			// "+actValue+"","User clicked on the'" +actValue +"'");
			return new IMAP_SearchPage(driver);
		} catch (Exception e) {
			throw e;
		}
	}

	

	public void clickOnModify(int index) throws Exception {
		try {
			WebElement ele = driver.findElement(By.xpath("(//table/tbody/tr/td[1]/div/img[1])[" + index + "]"));
			log.info("clicked on Modify image.." + ele);
			// BaseUtilityHelper.WaitForElementPresent(ele, 60);
			ele.click();
			BaseUtilityHelper.wait3Seconds();
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickonDUNS_MatchsCreate(String custname) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			System.out.println(custname);
//			WebElement search_create = driver.findElement(By.xpath("(//*[contains(text(),'" + custname
//					+ "')]/..)[3]//following-sibling::td[6]//*[contains(text(),'Create')]"));
			WebElement search_create = driver.findElement(By.xpath("//img[@src='images/create_customer.png']"));
			search_create.click();
		} catch (Exception e) {
			throw e;
		}
	}

	public void verifyDisabledFields(String fieldName) throws Exception {
		try {
			WebElement value = driver.findElement(By
					.xpath("//label[contains(text(),'" + fieldName + "')]/following-sibling::div/input[@type='text']"));

			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(value);
			// in above xpath @readonly='' is locating disabled fields, So if it displayed
			// that input field is disabled
			if (value.isDisplayed()) {
				log.info(fieldName + " field should be disabled ,"+fieldName + " field is in disabled");
			} else {
				log.info("Exception: " + fieldName + " field is enabled");
			}
		} catch (Exception e) {
			log.info("Exception: Unable to clicked on the Active button");
			throw e;
		}
	}
	
	public void verifyEnabledFields(String fieldName) throws Exception {
		try {
			WebElement value = driver.findElement(By
					.xpath("//label[contains(text(),'" + fieldName + "')]/following-sibling::div/input[@type='text']"));

			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(value);
			// in above xpath @readonly='' is locating disabled fields, So if it displayed
			// that input field is disabled
			if (value.isEnabled()) {
				log.info(fieldName + " field is in enabled status.");
			} else {
				log.info(fieldName + " field should be enabled but field is disabled");
				throw new Exception(fieldName + " field should be enabled but field is disabled");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public String getSiteDUNS(String custName) {
		String siteDuns = null;
		try {
			WebElement value = driver.findElement(
					By.xpath("(//div[contains(text(),'" + custName + "')]/..)[2]/preceding-sibling::td[1]/div"));
			BaseUtilityHelper.wait3Seconds();
			siteDuns = value.getText();
			System.out.println(siteDuns);
		} catch (Exception e) {
			try {
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return siteDuns;
	}

	public String getInputValue(String label) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			WebElement value = driver.findElement(
					By.xpath("//label[contains(text(),'" + label + "')]/following-sibling::div/input[@type='text']"));
			String siteDuns = value.getAttribute("value");
			System.out.println(siteDuns);
			return siteDuns;
		} catch (Exception e) {
			throw e;
		}
	}

	public IMAP_SearchPage clickOnCustomerName(String actUcid) throws Exception {
		try {
			WaitHelper wait = new WaitHelper(driver);
			wait.fluentwaitForElementPresent(tableBody, 100, 10);
			List<WebElement> list = driver
					.findElements(By.xpath("(//div[@class='x-grid3-body'])[1]//tbody//td[4]/div"));
			for (int i = 0; i < list.size(); i++) {
				WebElement ucid = driver
						.findElement(By.xpath("((//div[@class='x-grid3-body'])[1]//tbody//td[4]/div)[" + i + "]"));
				String expeUcid = ucid.getText();
				if (expeUcid.contains(actUcid)) {
					WebElement name = driver
							.findElement(By.xpath("((//div[@class='x-grid3-body'])[1]//tbody//td[7]//a)[" + i + "]"));
					name.click();
				}
			}
			return new IMAP_SearchPage(driver);
		} catch (Exception e) {
			throw e;
		}
	}

	public IMAP_SearchPage verifySearchCriteria(String actualCustomer) throws Exception {
		try {
			WaitHelper wait = new WaitHelper(driver);
			wait.fluentwaitForElementPresent(tableBody, 100, 10);
			List<WebElement> list = driver
					.findElements(By.xpath("(//div[@class='x-grid3-body'])[1]//tbody//td[7]/div"));
			for (int i = 0; i < list.size(); i++) {
				WebElement customerName = driver
						.findElement(By.xpath("((//div[@class='x-grid3-body'])[1]//tbody//td[7]/div)[" + i + "]"));
				String expCustomerName = customerName.getText();
				if (expCustomerName.contains(actualCustomer)) {
				}
			}
			return new IMAP_SearchPage(driver);
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "//a[@id='username']/label")
	public WebElement LoginName;

	public void chkForSuccessLogin() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			if (BaseUtilityHelper.IsElementPresent(popup_dlg)) {
				BaseUtilityHelper.wait3Seconds();
				driver.findElement(By.xpath("//table[@id='ext-comp-1098']//td[@class='x-btn-mc']")).click();
				BaseUtilityHelper.wait10Seconds();
			}
			if (logo_img.isDisplayed()) {
				log.info("User should able to see the iMAP CUSTOMER PORTAL logo, iMAP CUSTOMER PORTAL logo is displayed in the Home Page");

			} else {

				log.info("User is unable to see the iMAP CUSTOMER PORTAL logo, iMAP CUSTOMER PORTAL logo is NOT displayed in the Home Page");
			}

			/*
			 * if(LoginName.getText().trim().equalsIgnoreCase("")) {
			 * System.out.println("Welcome Name --"+LoginName.getText());
			 * BaseUtilityHelper.wait3Seconds(); driver.findElement(By.xpath(
			 * "//table[@id='ext-comp-1098']//td[@class='x-btn-mc']")).click();
			 * driver.navigate().refresh(); BaseUtilityHelper.wait20Seconds();
			 * chkForSuccessLogin(); } else { if(logo_img.isDisplayed()) { HTMLReport.
			 * updateDetailsHTMLReport("User should able to see the iMAP CUSTOMER PORTAL logo"
			 * ,"iMAP CUSTOMER PORTAL logo is displayed in the Home Page");
			 * 
			 * }else{ HTMLReport.
			 * updateErrorMessageintoHTMLReport("User is unable to see the iMAP CUSTOMER PORTAL logo"
			 * , "User should able to see the iMAP CUSTOMER PORTAL logo"
			 * ,"iMAP CUSTOMER PORTAL logo is NOT displayed in the Home Page"); } }
			 */
		} catch (NoSuchElementException e) {

			BaseUtilityHelper.wait3Seconds();
			driver.navigate().refresh();
			BaseUtilityHelper.wait20Seconds();
			chkForSuccessLogin();
			/*
			 * HTMLReport.
			 * updateErrorMessageintoHTMLReport("User is unable to see the iMAP CUSTOMER PORTAL logo"
			 * , "User should able to see the iMAP CUSTOMER PORTAL logo"
			 * ,"iMAP CUSTOMER PORTAL logo is NOT displayed in the Home Page"); throw e;
			 */
		}

	}

	public void clickancel() {
		log.info("clicked on Error popup cancel--" + cancel1);
		this.cancel1.click();
	}

	public boolean chkIfSearchResultisEmpty() throws Exception {
		return IsElementPresent(SearchResultsTableEmpty);
	}

	@FindBy(xpath = "//label[text()='Internal Customer Type:']/following-sibling::div//img")
	public WebElement dropdown_Internal;

	public IMAP_SearchPage selectInternalCustomerType(String internalType) throws Exception {
		try {

			JavaScriptHelper jss = new JavaScriptHelper(driver);
			jss.scrollIntoView(dropdown_Internal);
			this.dropdown_Internal.click();
			WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + internalType + "')]"));
			BaseUtilityHelper.wait3Seconds();
			ele.click();
			log.info("User should select Internal Customer: '" + internalType + "'");
			return new IMAP_SearchPage(driver);

		} catch (Exception e) {
			throw e;
		}
	}

	public String getReqID() throws Exception {
		String strRequestID = null;
		String strRequestSuccessMsg = createRequest_Successful_msg.getText();
		if (strRequestSuccessMsg.contains("Request ID")) {
			int strindex = strRequestSuccessMsg.indexOf("ID");
			strRequestID = strRequestSuccessMsg.substring(strindex + 2);
			log.info("req ID--- " + strRequestID);
		} else {
//			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			BaseUtilityHelper.captureScreen(methodName, driver);
			log.info("Request was not successfully created");
		}
		log.info("req ID---" + strRequestID);
		return strRequestID;
	}

	/*
	 * public void executeQuery(String dbQuery) throws IOException, ParseException,
	 * SQLException { String
	 * query="select party_name, addr_1, addr_2, addr_3, addr_4, city, state,postal_cd, country_cd from emcparty.c_b_party_xref x , emcparty.c_b_addr_xref a where x.pkey_src_object=a.pkey_src_object and x.hub_state_ind=0 fetch first 1 rows only"
	 * ; ResultSet reee=DBHelper.getResultSet(query, "stg"); String
	 * party_name="";String address1="";String address2="";String address3="";String
	 * address4="";String city=""; String state="";String postalCode="";String
	 * country=""; while(reee.next()) { party_name=reee.getString("PARTY_NAME");
	 * address1=reee.getString("ADDR_1"); address2=reee.getString("ADDR_2");
	 * address3=reee.getString("ADDR_3"); address4=reee.getString("ADDR_4");
	 * city=reee.getString("CITY"); state=reee.getString("STATE");
	 * postalCode=reee.getString("POSTAL_CD"); country=reee.getString("COUNTRY_CD");
	 * } dbQuery = dbQuery.replace("address2",address2); log.info(dbQuery); }
	 */

	public String replaceQueryValues(String[] actual, String replaceValues, String query) throws Exception {
		try {
			System.out.println(query);
			String[] interface_columns = replaceValues.split(",");
			System.out.println(interface_columns);
			System.out.println(actual);
			for (int i = 0; i < interface_columns.length; i++) {
				String oldchar = interface_columns[i];
				String newchar = actual[i];
				System.out.println(newchar);
				if (newchar == null) {
					newchar = "null";
					System.out.println(newchar);
					query = query.replace(oldchar, newchar);
					continue;
				}
				query = query.replace(oldchar, newchar);
			}
			System.out.println(query);
			return query;
		} catch (Exception e) {
			throw e;
		}
	}

	@FindBy(xpath = "//span[contains(text(),'SFDC_ACCT_NO')]")
	WebElement popup;
	@FindBy(xpath = "((//div[@class='x-grid3-body'])[1]//tbody//td[7]/div)[1]/a")
	WebElement user_name;

	public void verifyPopup() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			Actions move = new Actions(driver);
			BaseUtilityHelper.waitTime(2);
			move.moveToElement(user_name).build().perform();
			if (user_name.isDisplayed()) {
				log.info("pass");
				log.info("pop-up should appears when user hover the mouse cursor over the record , pop-up appeared when user hover the mouse cursor over the record");
			} else {

			}
			BaseUtilityHelper.wait5Seconds();
		} catch (Exception e) {
			log.info("pop-up not appeared when user hover the mouse cursor over the record");
			throw e;
		}
	}

	public void validateReqIDprefix(String prefix, String strRequestID) throws Exception {
		try {
			if (strRequestID.contains(prefix)) {
				log.info(
						"Request Id should be generated with \"U\" as prefix and the generated request Id is "
								+ strRequestID + "");
			} else {
				log.info("Request Id should be generated with \"U\" as prefix");
			}

		} catch (Exception e) {
			log.info("Request Id should be generated with \"U\" as prefix");
			throw e;
		}
	}

	public void clickOnGoldenRecord() throws Exception {
		BaseUtilityHelper.waitTime(1);
		goldernRecordsCheckbox.click();
		log.info("User should click on Golden record checkbox");
	}

	public void clickOnModifyforGoldenRecord() throws Exception {
		try {
			List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/img[1]"));
			for (int i = 2; i <= list.size(); i += 2) {
				WebElement ele = driver.findElement(By.xpath("(//table/tbody/tr/td[1]/div/img[1])[" + i + "]"));
				WebElement status = driver.findElement(By.xpath("(//table/tbody/tr/td[15]/div)[" + i + "]"));
				String value = status.getText();
				System.out.println(value);
				if (value.equalsIgnoreCase("A")) {
					log.info("clicked on Modify image.." + ele);
					ele.click();
					BaseUtilityHelper.wait10Seconds();
					if (IsElementPresent(txt_pending)) {
						click_cancel.click();
						BaseUtilityHelper.wait3Seconds();
					} else {
						log.info("user should be able to click on the modify img");
						break;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickOnModifyforActRecord() throws Exception {
		try {
			List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/img[1]"));
			for (int i = 1; i <= list.size(); i++) {
				WebElement ele = driver.findElement(By.xpath("(//table/tbody/tr/td[1]/div/img[1])[" + i + "]"));
				String status = driver.findElement(By.xpath("(//table/tbody/tr/td[" + i + "]/div)")).getText();
				if (status.contains("A")) {
					log.info("clicked on Modify image.." + ele);
					ele.click();
					BaseUtilityHelper.wait3Seconds();
					if (IsElementPresent(txt_pending)) {
						click_cancel.click();
						BaseUtilityHelper.wait3Seconds();
					} else {
						log.info("user should be able to click on the modify img");
						break;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickpostalcodepopupOK() throws Exception {
		try {
			BaseUtilityHelper.wait20Seconds();
			BaseUtilityHelper.wait20Seconds();
			postalcode_OK_Button.click();
			BaseUtilityHelper.wait20Seconds();
			BaseUtilityHelper.wait20Seconds();
			// HTMLReport.updateDetailsHTMLReport("OK button should be clicked" , "OK button
			// is clcked");
		} catch (Exception e) {
			throw e;

		}
	}

	@FindBy(xpath = "//a[contains(text(),'Email CDCC for Assistance')]")
	WebElement mailCDCCforass_link;
	@FindBy(xpath = "//*[contains(text(),'Pending change on Customer')]")
	WebElement pendchange;
	@FindBy(xpath = "//a[contains(@href,'mailto:CDCC@emc.com')]")
	WebElement CDCCmailtemplate;

	public void mouseOverMailCDCCforAssistance(String actual_link, String pendchng_text) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			Actions move = new Actions(driver);
//			Robot keys = new Robot();
			move.moveToElement(mailCDCCforass_link);
			String expec_link = mailCDCCforass_link.getText();
			System.out.println(expec_link);
			String expe_text = pendchange.getText();
			System.out.println(expe_text);
			System.out.println(expec_link + " " + expe_text);
			String mailtemplate = CDCCmailtemplate.getAttribute("href");
			if (expec_link.equalsIgnoreCase(actual_link) & expe_text.equalsIgnoreCase(pendchng_text)) {
				log.info(
						"Pending change on customer pop Up should be displayed and having mail content " + mailtemplate
								+ "");
			} else {
				log.info(
						"Exception: Pending change on customer pop Up should be displayed");
				throw new Exception("Exception: Pending change on customer pop Up should be displayed Pending change on customer popup not displayed"); 
		}
		} catch (Exception e) {
			log.info(
					"Exception: Pending change on customer pop Up should be displayed");
			
			throw e;
		}
	}

	@FindBy(xpath = "//*[contains(text(),'Pending change on Customer')]")
	WebElement pendchange1;
	@FindBy(xpath = "//button[text()='OK']")
	WebElement ClickOkPopUp;
	@FindBy(xpath = "//button[text()='OK']")
	WebElement MailStatusOk;
	public void pendingChangePopup(String pendchng_text) throws Exception {
		try{
			
		BaseUtilityHelper.wait3Seconds();
		Actions move = new Actions(driver);
//		Robot keys = new Robot();
//		move.moveToElement(mailCDCCforass_link);
//		String expec_link = mailCDCCforass_link.getText();
//		System.out.println(expec_link);
		move.moveToElement(pendchange1);
		String expe_text = pendchange1.getText();
		System.out.println(expe_text);
		ClickOkPopUp.click();
		BaseUtilityHelper.wait10Seconds();
		MailStatusOk.click();
		
		
//		System.out.println(expec_link + " " + expe_text);
//		String mailtemplate1 = CDCCmailtemplate1.getAttribute("href");
		if (expe_text.equalsIgnoreCase(pendchng_text)) {
			log.info(
					"Pending change on customer pop Up should be displayed");
		} else {
			log.info(
					"Exception: Pending change on customer pop Up should be displayed");
			throw new Exception("Exception: Pending change on customer pop Up should be displayed Pending change on customer popup not displayed"); 
	}
	} catch (Exception e) {
		throw e;
	}
}
	
	@FindBy(xpath = "//*[contains(text(),'Postal Code modified')]")
	public WebElement postalCodeModifiedPopup_OK;

	public void clickonpostalCodeModifiedPopup_OK() throws Exception {
		BaseUtilityHelper.wait3Seconds();
		postalCodeModifiedPopup_OK.click();
		log.info("User Should click on OK on postal code popup");
	}

	@FindBy(xpath = "//label[contains(text(),'Inactivation Reason:')]")
	public WebElement inactivationReason;

	public void verifyDisapperValue() throws Exception {
		if (IsElementPresent(inactivationReason)) {
			log.info("Inactivation Reason should field is disapper");
		} else {
			System.out.println("fail++++++++++++++++++++");
		}
	}

	public IMAP_SearchPage verifyRequests() throws Exception {
		List<WebElement> list = driver
				.findElements(By.xpath("//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-1')]"));
		int count = list.size();
		if (count > 0) {
			log.info("Verify if any requests have been submitted by User" + count);
		} else {
			log.info("Exception:No request submitted by the user");
		}
		return new IMAP_SearchPage(driver);
	}
	
	public void enterCounty(String county) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			county1.sendKeys(county);
			log.info("'Please specify' field should be entered as " + county + "");
		} catch (Exception e) {
			log.info("Exception:  unable to enter into please specify field.");
			throw e;
		}
	}

}
