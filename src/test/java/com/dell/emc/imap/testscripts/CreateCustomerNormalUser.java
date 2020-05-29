package com.dell.emc.imap.testscripts;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dell.emc.imap.page.IMAP_PageFactory;
import com.dell.emc.imap.utils.BrowserManager;
import com.dell.emc.imap.utils.ConfigFileReader;
import com.dell.emc.imap.utils.JSONReader;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;
import com.dell.emc.imap.utils.generichelper.JavaScriptHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;

public class CreateCustomerNormalUser extends BrowserManager{

	@BeforeTest
	public void beforeTest() throws Throwable {
		testBaseBeforeTest();
	}
	
	@BeforeMethod
	public void testBeforeMethod() throws Exception{
		testBaseBeforeMethod();
		ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment"));
		String clickURL = fileReader.getProperties().getProperty("IMAP_PORTAL_URL");
		driver.manage().deleteAllCookies();
		driver.navigate().to(clickURL);
	}
	@Test
	public void T01_CreateCustomer() throws Exception{
		Map<String, Object> dataMap = JSONReader.readJSONData(".\\Data\\CreateCustomerNormalUser.json");
		System.out.println("Customer Source"+dataMap.get("Customersource"));
		IMAP_PageFactory obj_PageFac = PageFactory.initElements(driver, IMAP_PageFactory.class);
		JavaScriptHelper js = new JavaScriptHelper(driver);
		String custumername = BaseUtilityHelper.Random_StringGenerator(6)+ BaseUtilityHelper.Random_NumberGenerator(2);
		BaseUtilityHelper.wait5Seconds();
		System.out.println("before success login");
		obj_PageFac.IMAP_SearchPage().chkForSuccessLogin();
		
		obj_PageFac.IMAP_SearchPage().setCustomerSource(dataMap.get("Customersource").toString());
		obj_PageFac.IMAP_SearchPage().setCustomerName("AUTOTEST_"+custumername);
		obj_PageFac.IMAP_SearchPage().setCustomerAddress(dataMap.get("Address").toString());
		obj_PageFac.IMAP_SearchPage().setCustomerCity(dataMap.get("City").toString());
		obj_PageFac.IMAP_SearchPage().setCountry(dataMap.get("Country").toString());
		obj_PageFac.IMAP_SearchPage().setPostalCode(dataMap.get("Postalcode").toString());
		BaseUtilityHelper.wait10Seconds();		
		obj_PageFac.IMAP_SearchPage().clickSearchButton();
		BaseUtilityHelper.wait5Seconds();
		WaitHelper waithelper=new WaitHelper(driver);
		waithelper.WaitForSearchLoading();
		obj_PageFac.IMAP_SearchPage().clickCantFindaMatchButton();
		
		//*************************create new customer************************
		obj_PageFac.IMAP_SearchPage().clickonSearchcriteriaCreate(custumername);
		BaseUtilityHelper.wait10Seconds();
		//window.switchToWindow(1);
		js.scrollIntoView(obj_PageFac.IMAP_SearchPage().createRequest_RequestPurpose);
		BaseUtilityHelper.wait3Seconds();		
		
		obj_PageFac.IMAP_SearchPage().createRequest_Submit_Button();
		BaseUtilityHelper.wait3Seconds();
		obj_PageFac.IMAP_SearchPage().clickpopupOK();
		obj_PageFac.IMAP_SearchPage().selectRequestPurpose(dataMap.get("Requestpurpose").toString());
		obj_PageFac.IMAP_SearchPage().enterPlsSpecifyfield(dataMap.get("Others_reason").toString());
		BaseUtilityHelper.waitTime(2);
		obj_PageFac.IMAP_SearchPage().createRequest_Submit_Button();
		BaseUtilityHelper.wait5Seconds();
		if(BaseUtilityHelper.IsElementPresent(obj_PageFac.IMAP_SearchPage().submitanyway)) {
			obj_PageFac.IMAP_SearchPage().clickonSubmitAnyway();
		}
		BaseUtilityHelper.wait5Seconds();
		waithelper.WaitForSubmitLoading();
		//********************Get request ID**********************
		String strRequestID = obj_PageFac.IMAP_SearchPage().getReqID();
		System.out.println("Request ID: "+strRequestID);
		obj_PageFac.IMAP_SearchPage().clickpopupOK();
		BaseUtilityHelper.wait10Seconds();
		obj_PageFac.IMAP_SearchPage().ClickOnTab(dataMap.get("TabName").toString());
		BaseUtilityHelper.wait1Min();						
		obj_PageFac.IMAP_MyRequestsPage().validateMyRequestsPage();
		obj_PageFac.IMAP_MyRequestsPage().verifyRequestStatus2(strRequestID, "REQUEST PENDING");
		BaseUtilityHelper.wait1Min();
	}
	
	@AfterMethod(description = "Executes After Each Iteration")
	public void afterMethod(ITestResult res) throws Exception {
		testBaseAfterMethod(res);
	}

	@AfterTest
	public void tearDown() throws Throwable {
		testBaseAfterTest();
	}
}
