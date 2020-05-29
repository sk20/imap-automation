package com.dell.emc.imap.page;

import com.dell.emc.imap.utils.PageBase;

// ***********************************************************************
// Author           : pichos1
// Created          : 31/10/2018 03.53.34 PM
//
// Last Modified By : pichos1
// Last Modified On : 31/10/2018 03.53.34 PM
// ***********************************************************************
// <copyright file="IMAP_MyRequestsPage" company="Dell Technologies">
//     Copyright (c) Dell 2017. All rights reserved.
// </copyright>
// <summary>Describe Page Details</summary>
// ***********************************************************************


//import com.dell.devote.core.*;
//import com.dell.emc.IMAPortal.Pages.IDD_Pages.IDD_Home_Page;
import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 * This base class is where all specific page classes will be derived.
 */
public class IMAP_MyRequestsPage extends PageBase {

	//WebDriver driver;
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(IDD_Home_Page.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//span[text()='My Requests']")
	public WebElement text_myRequestPage;

	@FindBy(xpath = "(//table[@class='x-btn x-btn-noicon']/tbody//td[2])[2]")
	public WebElement clk_ok;

	
	@FindBy(xpath = "//input[@id='internal_customer1']")
	public WebElement interCustYes;

	/**
	 * Constructor to hand off WebDriver.
	 */
	public IMAP_MyRequestsPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public IMAP_MyRequestsPage validateMyRequestsPage()throws Exception {
		try {
			//BaseUtilityHelper.WaitForElementPresent(text_myRequestPage, 200);
//			String value=text_myRequestPage.getText();
			if(text_myRequestPage.isDisplayed()){
				log.info("click on 'My Requests page' Tab and the page should be opened");
			}else{
				log.info("Exception: My Requests Page is not open");
			}
			return new IMAP_MyRequestsPage(driver);
		} catch (Exception ex) {			
			throw ex;
		}
	}

	public IMAP_MyRequestsPage verifyTableheaders() throws Exception {
		try {
			String ExpectedHeaders = "Request ID;Party Name;Request Date;Request Type;Request Status;UCID";
			String[] ExpHead = ExpectedHeaders.split(";");				int i=0;
			List<WebElement> tableHeaders = driver.findElements(By.xpath("//div[@class='x-window-bwrap']//table//thead//td/div"));
			System.out.println(tableHeaders);
			for (WebElement tableHead : tableHeaders) {
				String value=tableHead.getText();
				if(value.contains(ExpHead[i])){
					i++;
					log.info("Table header should display as:"+value);
					System.out.println("i value"+i);
				}else{
					log.info("Exception:"+value+" not displayed");
				}
			}
			return new IMAP_MyRequestsPage(driver);
		} catch (Exception ex) {
			log.info("Exception: Unable to See Table header ");
			throw ex;
		}
	}

	public IMAP_MyRequestsPage verifyRequestStatus(String partyName,String status) throws Exception {
		try {
			List<WebElement> tableHeaders = driver.findElements(By.xpath(".//table/tbody/tr/td[2]/div"));
			System.out.println(tableHeaders);
			for (int i=1;i<tableHeaders.size();i++) {
				WebElement cellValue=driver.findElement(By.xpath("(.//table/tbody/tr/td[2]/div)["+i+"]"));
				String actPartyName=cellValue.getText();
				if(actPartyName.contains(partyName)){
					WebElement cellValu=driver.findElement(By.xpath("(.//table/tbody/tr/td[5]/div)["+i+"]"));
					String actPendingStatus=cellValu.getText();
					if(actPendingStatus.contains(status)){
						log.info("Created or Modified Requests should Auto-Approval");
						break;
					}else{
						log.info("Exception:Created or Modified Requests are not Auto-Approval");
					}
				}
			}
			return new IMAP_MyRequestsPage(driver);
		} catch (Exception ex) {
			log.info("Exception: Unable to See Table header ");
			throw ex;
		}
	}
	//rs15
	//will check on the status of the request
	public void verifyRequestStatus1(String partyName,String status) throws Exception {
		List<WebElement> rows=driver.findElements(By.xpath("//div[@id='myRequestsSearchResultsGrid']//div[contains(@class,'x-grid3-scroller')]//table//tr"));		
		int rowSize=rows.size();
		System.out.println("---rows  "+rowSize);
		//if status is approved
		//chk ucid
		if(status.equalsIgnoreCase("APPROVED"))
		{
			for (int i=1;i<=rowSize;i++)
			{
				if(driver.findElement(By.xpath("//div[@id='myRequestsSearchResultsGrid']//div[contains(@class,'x-grid3-scroller')]/div/div["+i+"]//table//tr/td[2]/div")).getText().contains(partyName))
				{
					WebElement statusElement=driver.findElement(By.xpath("//div[@id='myRequestsSearchResultsGrid']//div[contains(@class,'x-grid3-scroller')]/div/div["+i+"]//table//tr/td[5]/div"));
					WebElement ucidElement=driver.findElement(By.xpath("//div[@id='myRequestsSearchResultsGrid']//div[contains(@class,'x-grid3-scroller')]/div/div["+i+"]//table//tr/td[6]/div"));
					String actualStatus=statusElement.getText();
					String actUCID=ucidElement.getText();
					log.info("status"+actualStatus);
					log.info("UCID"+actUCID);
					if(actualStatus.contains(status)){
						log.info("Created Request Status should be in '"+status+"' status");
						//check for the UCID
						if(actUCID.length()>0)
						{
							log.info("UCID should get updated when the status is APPROVED,The UCID '"+actUCID+"' is available for the Party '"+partyName+"'");
							break;
						}
						else
						{
							log.info("Exception: Created Request , UCID should get updated when the status is APPROVED, UCID is NOT available for the '"+partyName +"'");
							Assert.fail();
						}
						
					}else{
						log.info("Exception: Created Requests are NOT in Expected Status, Created Requests should be in '"+status+"' status , Created Request is in '"+actualStatus+"' status");
						Assert.fail();
					}
					
					
					
				}
			}
		}
		//else part should work for Rejected/Duplicate
		else
		{
			for (int i=1;i<=rowSize;i++)
			{
				if(driver.findElement(By.xpath("//div[@id='myRequestsSearchResultsGrid']//div[contains(@class,'x-grid3-scroller')]/div/div["+i+"]//table//tr/td[2]/div")).getText().contains(partyName))
				{
					WebElement statusElement=driver.findElement(By.xpath("//div[@id='myRequestsSearchResultsGrid']//div[contains(@class,'x-grid3-scroller')]/div/div["+i+"]//table//tr/td[5]/div"));
					String actualStatus=statusElement.getText();
					log.info("status"+actualStatus);					
					if(actualStatus.contains(status)){
						log.info("Created Request Status should be in '"+status+"' status, Created Request status is in '"+status+"' as expected");
						break;
					}else{
						log.info("Exception: Created Requests are NOT in Expected Status , Created Requests should be in '"+status+"' status , Created Request is in '"+actualStatus+"' status");
						Assert.fail();
					}
				}
			}
		}
		
		
		
	}
	
	public IMAP_MyRequestsPage verifyRequestStatus2(String requestID,String status) throws Exception {
		try {
			List<WebElement> tableHeaders = driver.findElements(By.xpath(".//table/tbody/tr/td[2]/div"));
			System.out.println(tableHeaders);
			for (int i=1;i<tableHeaders.size();i++) {
				WebElement cellValue=driver.findElement(By.xpath("(.//table/tbody/tr/td[2]/div)["+i+"]"));
				String actPartyName=cellValue.getText();
				if(actPartyName.contains(requestID)){
					WebElement cellValu=driver.findElement(By.xpath("(.//table/tbody/tr/td[5]/div)["+i+"]"));
					String actPendingStatus=cellValu.getText();
					if(actPendingStatus.contains(status)){
						log.info("Created or Modified Requests should Auto-Approval");
						break;
					}else{
						log.info("Exception:Created or Modified Requests are not Auto-Approval");
					}
				}
			}
			return new IMAP_MyRequestsPage(driver);
		} catch (Exception ex) {
			log.info("Exception: Unable to See Table header ");
			throw ex;
		}
	}
	
	
	
	public IMAP_MyRequestsPage clickOnOK()throws Exception {
		try {
			this.clk_ok.click();
			log.info("click on OK button");
			return new IMAP_MyRequestsPage(driver);
		} catch (Exception ex) {
			log.info("Exception: Unable to click on OK button ");
			throw ex;
		}
	}

}
