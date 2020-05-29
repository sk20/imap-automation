package com.dell.emc.imap.page;

// ***********************************************************************
// Author           : rs15
// Created          : 29/11/2018 07.07.00 PM
//
// Last Modified By : rs15
// Last Modified On : 29/11/2018 07.07.00 PM
// ***********************************************************************
// <copyright file="MyQuotes_CreatePage" company="Dell Technologies">
//     Copyright (c) Dell 2017. All rights reserved.
// </copyright>
// <summary>Describe Page Details</summary>
// ***********************************************************************


//import com.dell.devote.core.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import com.dell.emc.imap.utils.PageBase;

/**
* This base class is where all specific page classes will be derived.
*/
public class MyQuotes_CreatePage extends PageBase {

	WebDriver driver;
	
	/**
	* Constructor to hand off WebDriver.
	*/
	public MyQuotes_CreatePage (WebDriver driver) {
		super(driver);
		// Please set the values for the application name, uri and segment name here.
		super.name = "MyQuotes";
		// super.uri = "";
		// super.segmentName = "";
		this.driver = driver;
	}
		
	
//	WebElement elem = getElement(Identifier.name, "name"); // getting locator element using name
//	WebElement elem = getElement(Identifier.xpath, "xpath"); // getting locator element using xpath
//	WebElement elem = getElement(Identifier.id, "id"); // getting locator element using id
	
	@FindBy(xpath = "//input[@id='quoteNumberDotVersion']")
	public WebElement quoteNumber_SearchField;
	
	@FindBy(xpath = "//button[@id='idAccessQuotesHome']")
	public WebElement quoteNumber_SearchBtn;
	
	@FindBy(xpath = "//table[contains(@class,'tablehome table my-quotes-indirect')]")
	public WebElement quoteSearchListTable;
	
	@FindBy(xpath ="//table[contains(@class,'tablehome table my-quotes-indirect')]//table/tbody/tr")
	public By quoteSearchListTableRows;	
		
	@FindBy(xpath ="//input[@id='quote-name']")
	public WebElement copyQuoteName;
	
	@FindBy(xpath ="//button[@id='nextButton']")
	public WebElement copyQuoteNextBtn;
	
	@FindBy(xpath ="//h1[contains(text(),'Copy Quote (Step 1)')]")
	public WebElement copyQuotePage;
	
	@FindBy(xpath ="//button[@id='create-quote-submit-btn']")
	public WebElement copyQuoteCreateBtn;
	
	@FindBy(xpath ="//a[contains(text(),'Shipping, Billing & Install')]")
	public WebElement shippingTab;
	
	@FindBy(xpath ="//a[contains(@class,'nav-item-save')]")
	public WebElement saveBtn;
	
	@FindBy(xpath ="")
	public WebElement copyQuoteNextBtn1;

	@FindBy(xpath ="//div[@class='dropdown-toggle nav-item-copy']")
	public WebElement copyQuote;
	
	@FindBy(xpath ="//div[contains(@class,'col-xs-14')]//ul[1]//li[2]//ul[1]//li[1]/a")
	public WebElement copymenu;	
//	
	@FindBy(xpath ="//input[@id='shipToForm']")
	public WebElement shipToInput;
	
	@FindBy(xpath ="//div[@class='col-xs-6 form-col']//button[@class='btn btn-default']")
	public WebElement shipToSearch;
	//Customer search results page	
	@FindBy(xpath ="//input[@id='customer-name']")
	public WebElement CustName_SearchResult;
	
	@FindBy(xpath ="//button[@id='customerSubmit']")
	public WebElement CustSearchBtn;
	@FindBy(xpath ="//button[contains(text(),'Clear')]")
	public WebElement clearBtn;
	@FindBy(xpath ="//button[contains(text(),'OK')]")
	public WebElement okBtn;
	@FindBy(xpath ="//a[@class='btn btn-default']")
	public WebElement RequestNewCustBtn;
	
	
	@FindBy(xpath ="//input[@id='city']")
	public WebElement CustSearchCity;
	
	@FindBy(xpath ="//input[@id='postal-code']")
	public WebElement CustSearchPostalCode;
	
	@FindBy(xpath ="//select[@id='country']")
	public By CustSearchCountry;
	
	@FindBy(xpath ="//button[contains(@class,'confirm')]")
	public WebElement continueSearchBtn;
	
	//Request New Customer Page	
	@FindBy(xpath ="//button[contains(text(),'Request New Customer')]")
	public WebElement NewCustCreateBtn;
	
	
	@FindBy(xpath =" //input[@id='name']")
	public WebElement NewCustName;
	//select[@id='country']	
	
	@FindBy(xpath ="//input[@id='address']")
	public WebElement NewCustAddr;
	//input[@id='city']
	
	@FindBy(xpath ="//input[@id='postalCode']")
	public WebElement NewCustPostalCode;
		
	@FindBy(xpath ="//select[@id='region']")
	public By NewCustRegoin;
	
	
	@FindBy(xpath ="//span[@id='quoteNumber']")
	public By quoteNumber;
	/**
	* Treat this like a BVT of the page. If Validate does not pass, throw exception and write the message to the console from the test.
	*/
	
	public void setQuoteNumber(String QuoteNumber)
	{		
		this.quoteNumber_SearchField.sendKeys(QuoteNumber);
	}
	public void clickQuoteSearch()
	{
		this.quoteNumber_SearchBtn.click();
		
	}
	
	
	
}
