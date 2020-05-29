package com.dell.emc.imap.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import com.dell.emc.imap.utils.PageBase;

/**
 * This base class is where all specific page classes will be derived.
 */
public class IMAP_PageFactory extends PageBase {

	WebDriver driver;

	public IMAP_PageFactory(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public IMAP_SearchPage IMAP_SearchPage() throws Exception{
		IMAP_SearchPage IMAP_Page_obj;
		try {
			IMAP_Page_obj = PageFactory.initElements(driver, IMAP_SearchPage.class);
		} catch (Exception e) {   
			throw e;
		}
		return IMAP_Page_obj;
	}

	public CustomerCloneRequestPage CustomerCloneRequestPage() throws Exception{
		CustomerCloneRequestPage IMAP_Page_obj;
		try {
			IMAP_Page_obj = PageFactory.initElements(driver, CustomerCloneRequestPage.class);
		} catch (Exception e) {   
			throw e;
		}
		return IMAP_Page_obj;
	}
	
	public CustomerCreateRequestPage CustomerCreateRequestPage() throws Exception{
		CustomerCreateRequestPage IMAP_Page_obj;
		try {
			IMAP_Page_obj = PageFactory.initElements(driver, CustomerCreateRequestPage.class);
		} catch (Exception e) {   
			throw e;
		}
		return IMAP_Page_obj;
	}	
	
	public IMAP_ModifyPage IMAP_ModifyPage() throws Exception{
		IMAP_ModifyPage IMAP_Page_obj;
		try {
			IMAP_Page_obj = PageFactory.initElements(driver, IMAP_ModifyPage.class);
		} catch (Exception e) {   
			throw e;
		}
		return IMAP_Page_obj;
	}
	
	public IMAP_MyRequestsPage IMAP_MyRequestsPage() throws Exception{
		IMAP_MyRequestsPage IMAP_Page_obj;
		try {
			IMAP_Page_obj = PageFactory.initElements(driver, IMAP_MyRequestsPage.class);
		} catch (Exception e) {   
			throw e;
		}
		return IMAP_Page_obj;
	}
	
	public MyQuotes_CreatePage MyQuotes_CreatePage() throws Exception{
		MyQuotes_CreatePage IMAP_Page_obj;
		try {
			IMAP_Page_obj = PageFactory.initElements(driver, MyQuotes_CreatePage.class);
		} catch (Exception e) {   
			throw e;
		}
		return IMAP_Page_obj;
	}

}
